package com.feng.task.job;

import com.feng.task.util.DateUtil;
import net.javacrumbs.shedlock.core.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HelloJob {

    private static final int FOURTEEN_MIN = 14 * 60 * 1000;


    @Scheduled(cron = "0/10 * * * * ?")
    @SchedulerLock(name = "helloTask", lockAtMostFor = FOURTEEN_MIN)
    public void hello(){
        System.err.println("springboot-task-001 >>> hello wolrd >>>" + DateUtil.currDateFormat(null));
    }


}
