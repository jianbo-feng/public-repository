package com.feng.springmvc.schedule.job;

import com.alibaba.fastjson.JSON;
import com.feng.springmvc.util.DateUtil;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@DisallowConcurrentExecution //作业不并发
public class HelloJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.err.println("HelloJob.context=> \n\t" + context.getTrigger().getJobDataMap()
                + "\n\t" + context.getResult() + "\n\t" + JSON.toJSONString(context.getJobDetail()));
        System.out.println("欢迎使用这是一个定时任务"+ DateUtil.currDateFormat(DateUtil.DEFAULT_FORMAT));
    }
}
