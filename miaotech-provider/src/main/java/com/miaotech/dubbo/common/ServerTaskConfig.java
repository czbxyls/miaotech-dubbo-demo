package com.miaotech.dubbo.common;


/**
 * 定时任务开关
 * @author duanjw
 */
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
//配置文件读取是否启用此配置
@ConditionalOnProperty(value = "server.scheduling.enable", matchIfMissing = false)
//启用定时任务
@EnableScheduling
public class ServerTaskConfig {

}
