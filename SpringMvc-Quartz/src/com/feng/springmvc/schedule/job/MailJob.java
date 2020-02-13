package com.feng.springmvc.schedule.job;

import com.alibaba.fastjson.JSON;
import com.feng.springmvc.service.MailService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 邮件任务
 */
@DisallowConcurrentExecution //作业不并发
public class MailJob implements Job {

	@Autowired
	private MailService mailService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
    	try {
    		mailService.sendSimpleMail();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
        System.err.println("MailJob.jobDetail => \n\t" + JSON.toJSONString(context.getJobDetail()));
        String jobDataMap = JSON.toJSONString(context.getJobDetail().getJobDataMap());
        System.err.println("MailJob.jobDetail.jobDataMap =>" + jobDataMap );
    }
}
