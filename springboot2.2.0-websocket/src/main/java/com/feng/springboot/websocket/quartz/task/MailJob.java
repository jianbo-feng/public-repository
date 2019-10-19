package com.feng.springboot.websocket.quartz.task;

import com.alibaba.fastjson.JSON;
import com.feng.springboot.websocket.service.MailService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 邮件任务
 */
@DisallowConcurrentExecution //作业不并发
@Component
public class MailJob implements Job {

    @Autowired
    MailService mailService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.err.println("MailJob.jobDetail => \n\t" + JSON.toJSONString(context.getJobDetail()));
        String jobDataMap = JSON.toJSONString(context.getJobDetail().getJobDataMap());
        System.err.println("MailJob.jobDetail.jobDataMap =>" + jobDataMap );
        mailService.sendSimpleMail();
    }
}
