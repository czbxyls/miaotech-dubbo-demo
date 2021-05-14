package com.miaotech.user.task;

import com.miaotech.common.utils.TimeUtils;
import com.miaotech.user.task.helper.ScheduleEnable;
import com.miaotech.user.task.helper.TaskProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DayStatisticsTask {
    /**
     * 两种方式启动配置是否开启定时任务（通过Nacos热更新）
     * A. 设置同一个配置文件类，配置属性。参考TaskProperties。优势是配置简单，劣势是不是很SRP，别的类可以访问到配置属性
     * B. 通过Bean注入方式，在具体的Task类配置。参考ScheduleEnable。解决了A的问题，缺点是比较复杂。
     * 至于为什么不把属性直接定义在Task任务中 ，然后把Task标记为RefreshScope。是因为标记为RefreshScope的Bean初始化时会创建动态代理。
     * 当属性热刷新了，bean会被destory再create，这时会失去schedule的功能
     */

    @Autowired
    private TaskProperties taskProperties;

    @Autowired
    ScheduleEnable enablePeriodStatisticsTask;

    @Scheduled(cron = "30 * * * * ?")
    public void cronScheduled(){
        long current = System.currentTimeMillis();
        log.info("当前时间：{}, 读取配置，scheduling.dayStatTask.enable: {}", TimeUtils.yyyyMMddHHmmss(current),
                taskProperties.isEnableCronStatisticsTask());
        if(!taskProperties.isEnableCronStatisticsTask()) return;
        log.info("当前时间：{}, 这是个测试Cron统计的任务，在每分钟第30秒时执行", TimeUtils.yyyyMMddHHmmss(current));
    }

    @Scheduled(fixedRate = 5000)
    public void periodScheduled() {
        long current = System.currentTimeMillis();
        log.info("当前时间：{}, 读取配置，scheduling.dayStatTask.enable: {}",
                TimeUtils.yyyyMMddHHmmss(current), enablePeriodStatisticsTask.isEnabled());
        if(!enablePeriodStatisticsTask.isEnabled()) return;
        log.info("当前时间：{}, 这是个测试定时执行的任务，每隔五秒执行一次",
                TimeUtils.yyyyMMddHHmmss(System.currentTimeMillis()));
    }

    @Bean
    @RefreshScope
    public ScheduleEnable enablePeriodStatisticsTask(@Value("${scheduling.periodTask.enable:true}") boolean enable) {
        return new ScheduleEnable(enable);
    }
}
