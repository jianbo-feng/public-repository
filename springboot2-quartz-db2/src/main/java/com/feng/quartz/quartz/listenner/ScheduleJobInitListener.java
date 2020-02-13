package com.feng.quartz.quartz.listenner;

import com.feng.quartz.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 调度作业初始化监听器
 */
@Component
@Order(value = 1)
public class ScheduleJobInitListener implements CommandLineRunner {

    @Autowired
    private TaskService taskService;

    @Override
    public void run(String... args) throws Exception {
        try {
            taskService.initSchedule();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
