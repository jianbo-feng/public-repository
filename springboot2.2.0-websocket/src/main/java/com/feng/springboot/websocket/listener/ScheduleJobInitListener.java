package com.feng.springboot.websocket.listener;

import com.feng.springboot.websocket.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class ScheduleJobInitListener implements CommandLineRunner {

    @Autowired
    TaskService taskService;

    @Override
    public void run(String... arg0) {
        try {
            taskService.initSchedule();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
