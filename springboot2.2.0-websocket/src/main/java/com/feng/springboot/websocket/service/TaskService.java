package com.feng.springboot.websocket.service;

import com.alibaba.fastjson.JSON;
import com.feng.springboot.websocket.common.JobStatusEnum;
import com.feng.springboot.websocket.entity.Task;
import com.feng.springboot.websocket.quartz.QuartzManager;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskService {

    @Autowired
    private QuartzManager quartzManager;

    public void initSchedule() {
        // 这里获取任务信息数据
//        List<TaskDO> jobList = taskMapper.list();
//        for (TaskDO task : jobList) {
//            if (JobStatusEnum.RUNNING.getCode().equals(task.getJobStatus())) {
//                quartzManager.addJob(task);
//            }
//        }
        try {
            Task task = new Task();
            task.setTaskId(UUID.randomUUID().toString());
            task.setBeanClass("com.feng.springboot.websocket.quartz.task.HelloJob");
            task.setTaskName("helloword");
            task.setTaskDesc("跑批配置测试");
            task.setTaskCronExpression("*/5 * * * * ?");
            task.setTaskStatus("0");
            task.setTaskGroup("group");
            task.setCreatorName("fengjianbo");
            task.setRecordFlag(1);
            task.setStartAt(null);
            task.setRepeatCount(null);
            Map<String, Object> businessKeys = new HashMap<>();
            businessKeys.put("userName", "feng");
            businessKeys.put("userPwd", "123456");
            task.setBusinessKey(JSON.toJSONString(businessKeys));
            quartzManager.addJob(task);

            task = new Task();
            task.setTaskId(UUID.randomUUID().toString());
            task.setBeanClass("com.feng.springboot.websocket.quartz.task.MailJob");
            task.setTaskName("邮件发送测试");
            task.setTaskDesc("邮件发送测试");
            task.setTaskCronExpression("0 0/10 * * * ?");
            task.setTaskStatus("0");
            task.setTaskGroup("邮件发送组");
            task.setCreatorName("fengjianbo");
            task.setRecordFlag(1);
            task.setStartAt(null);
            task.setRepeatCount(0); // 仅执行一次，不再重复
            businessKeys = new HashMap<>();
            businessKeys.put("邮件发送着", "445121408@qq.com");
            businessKeys.put("邮件接收者", "405727062@qq.com");
            task.setBusinessKey(JSON.toJSONString(businessKeys));
            quartzManager.addJob(task);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
