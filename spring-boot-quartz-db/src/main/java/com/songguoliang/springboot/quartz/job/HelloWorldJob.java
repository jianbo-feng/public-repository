package com.songguoliang.springboot.quartz.job;

import com.alibaba.fastjson.JSON;
import com.songguoliang.springboot.util.DateUtil;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution //作业不并发
@Component
public class HelloWorldJob implements Job {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        LOGGER.info("欢迎使用 InsectControl ,这是一个定时任务  --Feng!"+ DateUtil.currDateFormat(null));
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        LOGGER.info("获取businessKey => " + JSON.toJSONString(jobDataMap));

    }

}
