package com.miaotech.user.task.helper;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScheduleEnable {

    //是否启用定时任务
    private boolean isEnabled;
}
