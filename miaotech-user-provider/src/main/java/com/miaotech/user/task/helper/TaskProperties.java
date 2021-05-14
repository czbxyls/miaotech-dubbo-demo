package com.miaotech.user.task.helper;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@RefreshScope
@Data
@Configuration
public class TaskProperties {

    //scheduling.enable配置是否启用定时任务
    @Value("${scheduling.cronTask.enable:true}")
    private boolean isEnableCronStatisticsTask;


}
