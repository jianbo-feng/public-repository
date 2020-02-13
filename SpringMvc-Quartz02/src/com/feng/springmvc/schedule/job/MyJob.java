package com.feng.springmvc.schedule.job;

import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.alibaba.fastjson.JSON;

@DisallowConcurrentExecution 
public class MyJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

        System.out.println(new Date() + ": job 1 doing something...");
        System.err.println("MyJob.jobDetail => \n\t" + JSON.toJSONString(context.getJobDetail()));
        String jobDataMap = JSON.toJSONString(context.getJobDetail().getJobDataMap());
        System.err.println("MyJob.jobDetail.jobDataMap =>" + jobDataMap );

	}

}
