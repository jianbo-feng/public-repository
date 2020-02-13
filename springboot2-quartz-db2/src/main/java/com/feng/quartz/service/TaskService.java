package com.feng.quartz.service;

import com.alibaba.fastjson.JSON;
import com.feng.quartz.entity.Task;
import com.feng.quartz.quartz.QuartzManager;
import com.feng.quartz.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 任务业务处理
 */
@Service
public class TaskService {

    @Autowired
    private QuartzManager quartzManager;

    /**
     * 初始化调度器
     */
    public void initSchedule() {

        try {
            Task task = null;
            Map<String, Object> businessKeys = null;
            String taskName = "HelloWorldJob", taskGroup = "HelloWorldJobGroup";
            // 不存在则注册
            if (!quartzManager.checkJobKeyExists(taskName, taskGroup)) {
                task = new Task();
                task.setTaskId(StringUtil.getRandomUUID());
                task.setBeanClass("com.feng.quartz.quartz.job.HelloWorldJob");
                task.setTaskName(taskName);
                task.setTaskDesc("跑批配置测试");
                task.setTaskCronExpression("*/5 * * * * ?");
                task.setTaskStatus("0");
                task.setTaskGroup(taskGroup);
                task.setCreatorName("fengjianbo");
                task.setRecordFlag(1);
                task.setStartAt(null);
                task.setRepeatCount(null);
                businessKeys = new HashMap<>();
                businessKeys.put("用户名", "feng");
                businessKeys.put("密码", "123456");
                task.setBusinessKey(JSON.toJSONString(businessKeys));
                quartzManager.addJob(task);
            }

//            taskName = "Test2Job"; taskGroup = "Test2JobGroup";
//            // 不存在则注册
//            if (!quartzManager.checkJobKeyExists(taskName, taskGroup)) {
//                task = new Task();
//                task.setTaskId(StringUtil.getRandomUUID());
//                task.setBeanClass("com.feng.quartz.quartz.job.Test2Job");
//                task.setTaskName(taskName);
//                task.setTaskDesc("仅执行一次");
//                task.setTaskCronExpression("*/10 * * * * ?");
//                task.setTaskStatus("0");
//                task.setTaskGroup(taskGroup);
//                task.setCreatorName("fengjianbo-Test2Job");
//                task.setRecordFlag(1);
//                task.setStartAt(null);
//                task.setRepeatCount(0); // 仅执行一次，不再重复
//                businessKeys = new HashMap<>();
//                businessKeys.put("email1", "445121408@qq.com");
//                businessKeys.put("email2", "405727062@qq.com");
//                task.setBusinessKey(JSON.toJSONString(businessKeys));
//                quartzManager.addJob(task);
//            }

            taskName = "问候测试2222222222"; taskGroup = "问候测试22222222222-Group";
            // 不存在则注册
            if (!quartzManager.checkJobKeyExists(taskName, taskGroup)) {
                task = new Task();
                task.setTaskId(StringUtil.getRandomUUID());
                task.setBeanClass("com.feng.quartz.quartz.job.TestJob");
                task.setTaskName(taskName);
                task.setTaskDesc("跑批测试222");
                task.setTaskCronExpression("*/25 * * * * ?");
                task.setTaskStatus("0");
                task.setTaskGroup(taskGroup);
                task.setCreatorName("fengjianbo");
                task.setRecordFlag(1);
                task.setStartAt(null);
                task.setRepeatCount(null);
                businessKeys = new HashMap<>();
                businessKeys.put("发件箱", "445121408@qq.com");
                businessKeys.put("收件箱", "405727062@qq.com");
                task.setBusinessKey(JSON.toJSONString(businessKeys));
                quartzManager.addJob(task);
            }

            quartzManager.startJobs();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
