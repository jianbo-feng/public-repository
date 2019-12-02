package com.feng.task.scheduler;

import com.feng.task.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import net.javacrumbs.shedlock.core.SchedulerLock;

@Service
public class TestScheduler {

//    lockAtMostFor 表示成功执行任务的节点所能拥有独占锁的最长时间，单位是毫秒ms
//    lockAtLeastFor 表示成功执行任务的节点所能拥有独占所的最短时间，单位是毫秒ms

    private static final Logger LOGGER = LoggerFactory.getLogger(TestScheduler.class);
    private static final String FOUR_SEC = "PT4S";

    @Scheduled(cron = "0/5 * * * * ?")
    @SchedulerLock(name = "test", lockAtMostForString = FOUR_SEC, lockAtLeastForString = FOUR_SEC)
    public void test1() {
        LOGGER.info("redis-01 test1>>>" + DateUtil.currDateFormat(null));
    }

    @Scheduled(cron = "0/8 * * * * ?")
    @SchedulerLock(name = "test2", lockAtMostForString = FOUR_SEC, lockAtLeastForString = FOUR_SEC)
    public void test2() {
        LOGGER.error("redis-01 test2>>>" + DateUtil.currDateFormat(null));
    }
}
