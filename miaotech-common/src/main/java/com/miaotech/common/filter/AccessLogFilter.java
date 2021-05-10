package com.miaotech.common.filter;

import com.alibaba.fastjson.JSON;
import com.miaotech.common.MsgException;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.apache.dubbo.common.utils.ConcurrentHashSet;
import org.apache.dubbo.common.utils.ConfigUtils;
import org.apache.dubbo.common.utils.NamedThreadFactory;
import org.apache.dubbo.rpc.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.apache.dubbo.common.constants.CommonConstants.PROVIDER;
import static org.apache.dubbo.rpc.Constants.ACCESS_LOG_KEY;

/**
 *  Record access log for the service.
 *  Logger key is dubbo.accesslog. In order to configure access log appear in the specified appender only, additivity need to be configured in log4j's config file, for example:
 *    <logger name="dubbo.accesslog" additivity="false">
 *       <level value="info" />
 *       <appender-ref ref="foo" />
 *    </logger>
 *
 */
@Activate(group = PROVIDER, value = "accesslog2")
public class AccessLogFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(org.apache.dubbo.rpc.filter.AccessLogFilter.class);

    private static final String LOG_KEY = "dubbo.accesslog";

    private static final int LOG_MAX_BUFFER = 5000;

    private static final long LOG_OUTPUT_INTERVAL = 5000;

    private static final String FILE_DATE_FORMAT = "yyyyMMdd";

    // It's safe to declare it as singleton since it runs on single thread only
    private static final DateFormat FILE_NAME_FORMATTER = new SimpleDateFormat(FILE_DATE_FORMAT);

    private static final Map<String, Set<AccessLogData>> LOG_ENTRIES = new ConcurrentHashMap<>();

    private static final ScheduledExecutorService LOG_SCHEDULED = Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory("Dubbo-Access-Log", true));

    /**
     * Default constructor initialize demon thread for writing into access log file with names with access log key
     * defined in url <b>accesslog</b>
     */
    public AccessLogFilter() {
        LOG_SCHEDULED.scheduleWithFixedDelay(this::writeLogToFile, LOG_OUTPUT_INTERVAL, LOG_OUTPUT_INTERVAL, TimeUnit.MILLISECONDS);
    }

    private class AccessLogData {
        String serviceName;
        String accessMessage;
        String invokeResult;

        public String getLogMessage () {
            return accessMessage + (invokeResult == null ? "" :  " " + invokeResult);
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public String getAccessMessage() {
            return accessMessage;
        }

        public void setAccessMessage(String accessMessage) {
            this.accessMessage = accessMessage;
        }

        public String getInvokeResult() {
            return invokeResult;
        }

        public void setInvokeResult(String invokeResult) {
            this.invokeResult = invokeResult;
        }
    };

    /**
     * This method logs the access log for service method invocation call.
     *
     * @param invoker service
     * @param inv     Invocation service method.
     * @return Result from service method.
     * @throws RpcException
     */
    @Override
    public Result invoke(Invoker<?> invoker, Invocation inv) throws RpcException {
        String accessLogKey = invoker.getUrl().getParameter(ACCESS_LOG_KEY);
        if (ConfigUtils.isEmpty(accessLogKey)) return invoker.invoke(inv);

        long start = System.currentTimeMillis();
        Result result = null;
        RpcException exception = null;
        AccessLogData logData = new AccessLogData();

        try {//get basic log message
            logData.setServiceName(invoker.getInterface().getSimpleName());
            logData.setAccessMessage(buildLogAccessMessage(invoker, inv));
        } catch (Exception e) {
            logger.warn("Exception in AcessLogFilter of service(" + invoker + " -> " +
                    inv + ")", e);
        }
        int status = 200;
        try {
            result = invoker.invoke(inv);
            if (result.hasException())
                if (result.getException() instanceof MsgException) {
                    status = 200;
                } else {
                    status = 500;
                }
        } catch (RpcException e) {
            exception = e;
            status = 500;
        } catch (Exception e) {
            exception = new RpcException(e);
            status = 500;
        }
        try {
            long time = System.currentTimeMillis() - start;
            logData.setInvokeResult(buildLogInvokeResult(status, time));
            log(accessLogKey, logData);
        } catch (Throwable t) {
            logger.warn("Exception in AcessLogFilter of service(" + invoker + " -> " +
                    inv + ")", t);
        }
        if (exception != null)
            throw exception;
        return result;
    }

    private String buildLogInvokeResult(int status, long time) {
        StringBuilder sb  = new StringBuilder();

        sb.append("[").append((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")).format(new Date())).append("] ");
        sb.append(" ").append(status);
        long end = System.currentTimeMillis();
        sb.append(" ").append(time);
        return sb.toString();
    }

    private String buildLogAccessMessage(Invoker<?> invoker, Invocation inv) throws Exception {
        RpcContext context = RpcContext.getContext();
        String serviceName = invoker.getInterface().getName();
        String version = invoker.getUrl().getParameter("version");
        String group = invoker.getUrl().getParameter("group");
        StringBuilder sb = new StringBuilder();
        sb.append("[").append((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")).format(new Date())).append("] ")
                .append(context.getRemoteHost()).append(":").append(context.getRemotePort()).append(" -> ")
                .append(context.getLocalHost()).append(":").append(context.getLocalPort()).append(" - ");
        if (null != group && group.length() > 0)
            sb.append(group).append("/");
        sb.append(serviceName);
        if (null != version && version.length() > 0)
            sb.append(":").append(version);
        sb.append(" ");
        sb.append(inv.getMethodName());
        sb.append("(");
        Class[] types = inv.getParameterTypes();
        if (types != null && types.length > 0) {
            sb.append(Arrays.stream(types).map(t->t.getName()).collect(Collectors.joining(",")));
        }
        sb.append(") ");
        Object[] args = inv.getArguments();
        if (args != null && args.length > 0)
            sb.append(JSON.toJSONString(args));
        return sb.toString();
    }

    private void log(String accessLog, AccessLogData accessLogData) {
        Set<AccessLogData> logSet = LOG_ENTRIES.computeIfAbsent(accessLog, k -> new ConcurrentHashSet<>());

        if (logSet.size() < LOG_MAX_BUFFER) {
            logSet.add(accessLogData);
        } else {
            logger.warn("AccessLog buffer is full. Do a force writing to file to clear buffer.");
            //just write current logSet to file.
            writeLogSetToFile(accessLog, logSet);
            //after force writing, add accessLogData to current logSet
            logSet.add(accessLogData);
        }
    }

    private void writeLogSetToFile(String accessLog, Set<AccessLogData> logSet) {
        try {
            if (ConfigUtils.isDefault(accessLog)) {
                processWithServiceLogger(logSet);
            } else {
                File file = new File(accessLog);
                createIfLogDirAbsent(file);
                if (logger.isDebugEnabled()) {
                    logger.debug("Append log to " + accessLog);
                }
                renameFile(file);
                processWithAccessKeyLogger(logSet, file);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void writeLogToFile() {
        if (!LOG_ENTRIES.isEmpty()) {
            for (Map.Entry<String, Set<AccessLogData>> entry : LOG_ENTRIES.entrySet()) {
                String accessLog = entry.getKey();
                Set<AccessLogData> logSet = entry.getValue();
                writeLogSetToFile(accessLog, logSet);
            }
        }
    }

    private void processWithAccessKeyLogger(Set<AccessLogData> logSet, File file) throws IOException {
        try (FileWriter writer = new FileWriter(file, true)) {
            for (Iterator<AccessLogData> iterator = logSet.iterator();
                 iterator.hasNext();
                 iterator.remove()) {
                writer.write(iterator.next().getLogMessage());
                writer.write(System.getProperty("line.separator"));
            }
            writer.flush();
        }
    }

    private void processWithServiceLogger(Set<AccessLogData> logSet) {
        for (Iterator<AccessLogData> iterator = logSet.iterator();
             iterator.hasNext();
             iterator.remove()) {
            AccessLogData logData = iterator.next();
            LoggerFactory.getLogger(LOG_KEY + "." + logData.getServiceName()).info(logData.getLogMessage());
        }
    }

    private void createIfLogDirAbsent(File file) {
        File dir = file.getParentFile();
        if (null != dir && !dir.exists()) {
            dir.mkdirs();
        }
    }

    private void renameFile(File file) {
        if (file.exists()) {
            String now = FILE_NAME_FORMATTER.format(new Date());
            String last = FILE_NAME_FORMATTER.format(new Date(file.lastModified()));
            if (!now.equals(last)) {
                File archive = new File(file.getAbsolutePath() + "." + last);
                file.renameTo(archive);
            }
        }
    }
}

