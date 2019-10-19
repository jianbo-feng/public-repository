package com.feng.springboot.websocket.quartz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.feng.springboot.websocket.entity.Task;
import com.feng.springboot.websocket.quartz.task.HelloJob;
import org.quartz.*;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 计划任务管理
 */
@Component
public class QuartzManager {

    public final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 调度器
     */
    @Autowired
    private Scheduler scheduler;

    /**
     * 添加任务
     *
     * @param task
     * @throws SchedulerException
     */
    @SuppressWarnings("unchecked")
    public void addJob(Task task) {
        try {
            // 创建jobDetail实例，绑定Job实现类
            // 指明job的名称，所在组的名称，以及绑定job类

            Class<? extends Job> jobClass = (Class<? extends Job>) (Class.forName(task.getBeanClass()).newInstance()
                    .getClass());

            JobDataMap jobDataMap = new JobDataMap();
            String businessKey = task.getBusinessKey().trim();
            if (businessKey != null && !"".equals(businessKey)) {
                JSONObject jsonObject = JSON.parseObject(businessKey);
                if (jsonObject != null && !jsonObject.isEmpty()) {
                    for (String key : jsonObject.keySet()) {
                        jobDataMap.put(key, jsonObject.get(key));
                    }
                }
            }
            JobDetail jobDetail = JobBuilder.newJob(jobClass)
                    .usingJobData(jobDataMap)
                    .withIdentity(task.getTaskName(), task.getTaskGroup())// 任务名称和组构成任务key
                    .build();
            // 定义调度触发规则
            // 使用cornTrigger规则
            Integer repeatCount = task.getRepeatCount();
            Date startAt = task.getStartAt();
            if (startAt == null) {
                startAt = DateBuilder.futureDate(1, IntervalUnit.SECOND);
            }

            Trigger trigger = null;
            ScheduleBuilder scheduleBuilder = null;
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
                    .withSchedule(scheduleBuilder)
                    .startNow().build();

            // 把作业和触发器注册到任务调度中
            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取所有计划中的任务列表
     *
     * @return
     * @throws SchedulerException
     */
    public List<Task> getAllJob() throws SchedulerException {
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
        List<Task> jobList = new ArrayList<>();
        for (JobKey jobKey : jobKeys) {
            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
            for (Trigger trigger : triggers) {
                Task job = new Task();
                job.setTaskName(jobKey.getName());
                job.setTaskGroup(jobKey.getGroup());
                job.setTaskDesc("触发器:" + trigger.getKey());
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
        List<Task> jobList = new ArrayList<Task>(executingJobs.size());
        for (JobExecutionContext executingJob : executingJobs) {
            Task job = new Task();
            JobDetail jobDetail = executingJob.getJobDetail();
            JobKey jobKey = jobDetail.getKey();
            Trigger trigger = executingJob.getTrigger();
            job.setTaskName(jobKey.getName());
            job.setTaskGroup(jobKey.getGroup());
            job.setTaskDesc("触发器:" + trigger.getKey());
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
     *
     * @param task
     * @throws SchedulerException
     */
    public void pauseJob(Task task) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(task.getTaskName(), task.getTaskGroup());
        scheduler.pauseJob(jobKey);
    }

    /**
     * 恢复一个job
     *
     * @param task
     * @throws SchedulerException
     */
    public void resumeJob(Task task) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(task.getTaskName(), task.getTaskGroup());
        scheduler.resumeJob(jobKey);
    }

    /**
     * 删除一个job
     *
     * @param task
     * @throws SchedulerException
     */
    public void deleteJob(Task task) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(task.getTaskName(), task.getTaskGroup());
        scheduler.deleteJob(jobKey);

    }

    /**
     * 立即执行job
     *
     * @param task
     * @throws SchedulerException
     */
    public void runJobNow(Task task) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(task.getTaskName(), task.getTaskGroup());
        scheduler.triggerJob(jobKey);
    }

    /**
     * 更新job时间表达式
     *
     * @param task
     * @throws SchedulerException
     */
    public void updateJobCron(Task task) throws SchedulerException {

        TriggerKey triggerKey = TriggerKey.triggerKey(task.getTaskName(), task.getTaskGroup());

        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(task.getTaskCronExpression());

        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

        scheduler.rescheduleJob(triggerKey, trigger);
    }
}
