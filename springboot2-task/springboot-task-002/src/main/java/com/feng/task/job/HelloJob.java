package com.feng.task.job;

import com.feng.task.util.DateUtil;
import net.javacrumbs.shedlock.core.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class HelloJob {

    private static final int FOURTEEN_MIN = 14 * 60 * 1000;


    @Scheduled(cron = "0/10 * * * * ?")
    @SchedulerLock(name = "helloTask", lockAtMostFor = FOURTEEN_MIN)
    public void hello(){
        System.err.println("springboot-task-002 >>> hello wolrd " + DateUtil.currDateFormat(null));
    }


    @Scheduled(cron = "0/15 * * * * ?")
    @SchedulerLock(name = "hello2Task", lockAtMostFor = FOURTEEN_MIN)
    public void hello2(){
        System.err.println("springboot-task-002 >>> hello2 wolrd " + DateUtil.currDateFormat(null));
    }
}
