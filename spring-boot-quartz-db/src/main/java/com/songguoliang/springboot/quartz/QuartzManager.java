package com.songguoliang.springboot.quartz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.songguoliang.springboot.entity.Task;
import com.songguoliang.springboot.util.DateUtil;
import com.songguoliang.springboot.util.StringUtil;
import org.quartz.*;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 计划任务管理
 * @author 小卖铺的老爷爷
 * @date 2018年6月17日
 * @website www.laoyeye.net
 */
@Service
public class QuartzManager {

    public final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Scheduler scheduler;

    /**
     * 添加任务
     * @param task 任务信息
     * @throws SchedulerException
     */
    @SuppressWarnings("unchecked")
    public void addJob(@NotNull Task task) {
        try {
            Class<? extends Job> jobClass = (Class<? extends Job>) (Class.forName(task.getBeanClass()).newInstance()
                    .getClass());

            JobDataMap jobDataMap = new JobDataMap();
            String businessKey = StringUtil.trim(task.getBusinessKey());
            if (!"".equals(businessKey)) {
                JSONObject jsonObject = JSON.parseObject(businessKey);
                if (jsonObject != null && !jsonObject.isEmpty()) {
                    for (String key : jsonObject.keySet()) {
                        jobDataMap.put(key, jsonObject.get(key));
                    }
                }
            }

            JobDetail jobDetail = JobBuilder.newJob(jobClass)
                    .setJobData(jobDataMap)
                    .withIdentity(task.getTaskName(), task.getTaskGroup())// 任务名称和组构成任务key
                    .build();

            // 定义调度触发规则
            // 使用cornTrigger规则
            Integer repeatCount = task.getRepeatCount();
            Date startAt = DateUtil.strToDate(task.getStartAt(), null);
            if (startAt == null) {
                startAt = DateBuilder.futureDate(1, IntervalUnit.SECOND);
            }

            Trigger trigger = null;
            ScheduleBuilder<?> scheduleBuilder = null;
            if (repeatCount == null) {
                scheduleBuilder = CronScheduleBuilder.cronSchedule(task.getTaskCronExpression());
            }
            else {
                scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(5)
                        .withRepeatCount(repeatCount); //执行重复次数
            }
            trigger = TriggerBuilder.newTrigger().withIdentity(task.getTaskName(), task.getTaskGroup())// 触发器key
                    .startAt(startAt)
                    .usingJobData(jobDataMap)     //可以在这里添加每个调度器的业务相关数据
                    .withSchedule(scheduleBuilder)
                    .startNow().build();
            /*
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(task.getTaskName(), task.getTaskGroup())// 触发器key
                    .startAt(DateBuilder.futureDate(1, IntervalUnit.SECOND))
                    .withSchedule(CronScheduleBuilder.cronSchedule(task.getTaskCronExpression())).startNow().build();

             */
            // 把作业和触发器注册到任务调度中
            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
//            if (!scheduler.isShutdown()) {
//                scheduler.start();
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取所有计划中的任务列表
     * @return
     * @throws SchedulerException
     */
    public List<Task> getAllJob() throws SchedulerException {
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
        List<Task> jobList = new ArrayList<>();
        for (JobKey jobKey : jobKeys) {
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            String businessKey = JSON.toJSONString(jobDetail.getJobDataMap());
            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
            for (Trigger trigger : triggers) {
                Task job = new Task();
                job.setTaskName(jobKey.getName());
                job.setTaskGroup(jobKey.getGroup());
                job.setTaskDesc("触发器:" + trigger.getKey());
                job.setBusinessKey(businessKey);
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                job.setTaskStatus(triggerState.name());
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    job.setTaskCronExpression(cronExpression);
                }
                jobList.add(job);
            }
        }
        return jobList;
    }

    /**
     * 所有正在运行的job
     *
     * @return
     * @throws SchedulerException
     */
    public List<Task> getRunningJob() throws SchedulerException {
        List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
        List<Task> jobList = new ArrayList<>(executingJobs.size());
        for (JobExecutionContext executingJob : executingJobs) {
            Task job = new Task();
            JobDetail jobDetail = executingJob.getJobDetail();
            JobKey jobKey = jobDetail.getKey();
            Trigger trigger = executingJob.getTrigger();
            job.setTaskName(jobKey.getName());
            job.setTaskGroup(jobKey.getGroup());
            job.setTaskDesc("触发器:" + trigger.getKey());
            job.setBusinessKey(JSON.toJSONString(jobDetail.getJobDataMap()));
            Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
            job.setTaskStatus(triggerState.name());
            if (trigger instanceof CronTrigger) {
                CronTrigger cronTrigger = (CronTrigger) trigger;
                String cronExpression = cronTrigger.getCronExpression();
                job.setTaskCronExpression(cronExpression);
            }
            jobList.add(job);
        }
        return jobList;
    }

    /**
     * 暂停一个job
     * @param task
     * @throws SchedulerException
     */
    public void pauseJob(@NotNull Task task) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(task.getTaskName(), task.getTaskGroup());
        scheduler.pauseJob(jobKey);
    }

    /**
     * 恢复一个job
     * @param task 任务信息
     * @throws SchedulerException
     */
    public void resumeJob(@NotNull Task task) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(task.getTaskName(), task.getTaskGroup());
        scheduler.resumeJob(jobKey);
    }

    /**
     * 删除一个job
     * @param task 任务信息
     * @throws SchedulerException
     */
    public void deleteJob(@NotNull Task task) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(task.getTaskName(), task.getTaskGroup());
        scheduler.deleteJob(jobKey);

    }

    /**
     * 立即执行job
     * @param task 任务信息
     * @throws SchedulerException
     */
    public void runJobNow(@NotNull Task task) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(task.getTaskName(), task.getTaskGroup());
        scheduler.triggerJob(jobKey);
    }

    /**
     * 更新job时间表达式(定时任务不能被删除)
     * @param task 任务信息
     * @throws SchedulerException
     */
    public void updateJobCron(@NotNull Task task) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(task.getTaskName(), task.getTaskGroup());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(task.getTaskCronExpression());
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
        scheduler.rescheduleJob(triggerKey, trigger);
    }

    /**
     * 关闭所有定时任务(即关闭调度器, 不能使用start()启动调度器)
     */
    public void shutdownJobs() {
        try {
            if (!scheduler.isShutdown()) {
                scheduler.shutdown(true);   // 表示等待所有正在执行的job执行完毕之后，再关闭scheduler
                //scheduler.shutdown(false);  //表示直接关闭scheduler
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 启动所有定时任务
     */
    public void startJobs() {
        try {
            log.info("scheduler.isShutdown() =>" + scheduler.isShutdown());
            log.info("scheduler.isStarted() =>" + scheduler.isStarted());
            log.info("scheduler.isInStandbyMode() =>" + scheduler.isInStandbyMode());
            if (!scheduler.isStarted() || scheduler.isInStandbyMode()) {
                scheduler.start();
            }
            log.info("2scheduler.isShutdown() =>" + scheduler.isShutdown());
            log.info("2scheduler.isStarted() =>" + scheduler.isStarted());
            log.info("2scheduler.isInStandbyMode() =>" + scheduler.isInStandbyMode());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 调度器挂起(可以使用start()启动调度器)
     */
    public void standby() {
        try {
            if (!scheduler.isInStandbyMode()) {
                scheduler.standby();
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 检查jobKey是否存在
     */
    public boolean checkJobKeyExists(String name, String group) throws SchedulerException {
        JobKey checkJobKey = JobKey.jobKey(name, group);
        return scheduler.checkExists(checkJobKey);
    }

    /**
     * 调度器清理
     * @throws SchedulerException
     */
    public void clear() throws SchedulerException {
        scheduler.clear();
    }
}
