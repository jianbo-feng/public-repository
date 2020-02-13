package com.feng.quartz.quartz.job;

import com.alibaba.fastjson.JSON;
import com.feng.quartz.util.DateUtil;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution //作业不并发
public class TestJob implements Job {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        LOGGER.info("TestJob(2) >>>>"+ DateUtil.currDateFormat(null));
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
//        LOGGER.info("获取businessKey => " + JSON.toJSONString(jobDataMap)
//                + "\n\t" + JSON.toJSONString(context.getTrigger().getJobDataMap()));
    }
}
