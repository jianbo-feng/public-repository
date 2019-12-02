package com.feng.task.scheduler;

import com.feng.task.util.DateUtil;
import net.javacrumbs.shedlock.core.SchedulerLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TestScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestScheduler.class);
    private static final String FOUR_SEC = "PT4S";

    @Scheduled(cron = "0/5 * * * * ?")
    @SchedulerLock(name = "test", lockAtMostForString = FOUR_SEC, lockAtLeastForString = FOUR_SEC)
    public void test1() {
        LOGGER.info("redis-02 test1>>>" + DateUtil.currDateFormat(null));
    }

//    @Scheduled(cron = "0/8 * * * * ?")
//    @SchedulerLock(name = "test2", lockAtMostForString = FOUR_SEC, lockAtLeastForString = FOUR_SEC)
//    public void test2() {
//        LOGGER.error("redis-02 test2>>>" + DateUtil.currDateFormat(null));
//    }
}
