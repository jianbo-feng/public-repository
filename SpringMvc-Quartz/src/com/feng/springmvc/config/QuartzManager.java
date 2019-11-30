package com.feng.springmvc.config;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.DateBuilder;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.feng.springmvc.entity.Task;
import com.feng.springmvc.util.StringUtil;

/**
 * 
 * @author baby
 * @see <a href="https://blog.csdn.net/zengwende/article/details/87647458">参考</a>
 */
public class QuartzManager {

	@Autowired
	private Scheduler scheduler;  
	
	 
    /** 
     * @Description: 添加一个定时任务 
     *  
     * @param jobName 任务名 
     * @param jobGroupName  任务组名 
     * @param triggerName 触发器名 
     * @param triggerGroupName 触发器组名 
     * @param jobClass  任务 
     * @param cron   时间设置，参考quartz说明文档  
     */  
    @SuppressWarnings({ "unchecked", "rawtypes" })  
    public void addJob(String jobName, String jobGroupName, 
            String triggerName, String triggerGroupName, Class jobClass, String cron) {  
        try {  
            // 任务名，任务组，任务执行类
            JobDetail jobDetail= JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();
            // 触发器  
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            // 触发器名,触发器组  
            triggerBuilder.withIdentity(triggerName, triggerGroupName);
            triggerBuilder.startNow();
            // 触发器时间设定  
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
            // 创建Trigger对象
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();
 
            // 调度容器设置JobDetail和Trigger
            scheduler.scheduleJob(jobDetail, trigger);  
 
            // 启动  
//            if (!scheduler.isShutdown()) {  
//                scheduler.start();  
//            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
    
    /** 
     * @Description: 添加一个定时任务 
     *  
     * @param jobName 任务名 
     * @param jobGroupName  任务组名 
     * @param triggerName 触发器名 
     * @param triggerGroupName 触发器组名 
     * @param jobClass  任务 
     * @param cron   时间设置，参考quartz说明文档  
     * @param 业务key
     */  
    @SuppressWarnings({ "unchecked"})  
    public void addJob(String jobName, String jobGroupName, 
            String triggerName, String triggerGroupName, String jobClass, String cron, String businessKey) {  
        try {  
        	Class<? extends Job> __class = (Class<? extends Job>) (Class.forName(jobClass).newInstance()
                    .getClass());
        	
        	JobDataMap jobDataMap = new JobDataMap();
            if (businessKey != null && !"".equals(businessKey)) {
                JSONObject jsonObject = JSON.parseObject(businessKey);
                if (jsonObject != null && !jsonObject.isEmpty()) {
                    for (String key : jsonObject.keySet()) {
                        jobDataMap.put(key, jsonObject.get(key));
                    }
                }
            }
            // 任务名，任务组，任务执行类
            JobDetail jobDetail= JobBuilder.newJob(__class).setJobData(jobDataMap).withIdentity(jobName, jobGroupName).build();
            // 触发器  
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            // 触发器名,触发器组  
            triggerBuilder.withIdentity(triggerName, triggerGroupName);
            triggerBuilder.startNow();
            // 触发器时间设定  
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
            // 创建Trigger对象
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();
 
            // 调度容器设置JobDetail和Trigger
            scheduler.scheduleJob(jobDetail, trigger);  
 
            // 启动  
            if (!scheduler.isShutdown()) {  
                scheduler.start();  
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    } 
    
    /** 
     * @Description: 添加一个定时任务 
     *  
     * @param task 任务信息 
     */  
    @SuppressWarnings({ "unchecked"})  
    public void addJob(Task task) {  
        try {  
        	Class<? extends Job> __class = (Class<? extends Job>) (Class.forName(task.getBeanClass()).newInstance()
                    .getClass());
        	
        	JobDataMap jobDataMap = new JobDataMap();
        	String businessKey = StringUtil.trim(task.getBusinessKey());
            if (businessKey != null && !"".equals(businessKey)) {
                JSONObject jsonObject = JSON.parseObject(businessKey);
                if (jsonObject != null && !jsonObject.isEmpty()) {
                    for (String key : jsonObject.keySet()) {
                        jobDataMap.put(key, jsonObject.get(key));
                    }
                }
            }
            Integer repeatCount = task.getRepeatCount();
            Date startAt = task.getStartAt();
            if (startAt == null) {
                startAt = DateBuilder.futureDate(1, IntervalUnit.SECOND);
            }
            
            // 任务名，任务组，任务执行类
            JobDetail jobDetail= JobBuilder.newJob(__class).setJobData(jobDataMap).withIdentity(task.getTaskName(), task.getTaskGroup()).build();
//            // 触发器  
//            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
//            // 触发器名,触发器组  
//            triggerBuilder.withIdentity(task.getTaskName(), task.getTaskGroup());
//            triggerBuilder.startNow();
//            // 触发器时间设定  
//            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
//            // 创建Trigger对象
//            CronTrigger trigger = (CronTrigger) triggerBuilder.build();
            
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
                    .withSchedule(scheduleBuilder)
                    .startNow().build();
 
            // 调度容器设置JobDetail和Trigger
            scheduler.scheduleJob(jobDetail, trigger);  
 
            // 启动  
            /*
            if (!scheduler.isShutdown()) {  
                scheduler.start();  
            }  
            */
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    } 
 
    /** 
     * @Description: 修改一个任务的触发时间
     *  
     * @param jobName 
     * @param jobGroupName
     * @param triggerName 触发器名
     * @param triggerGroupName 触发器组名 
     * @param cron   时间设置，参考quartz说明文档   
     */  
    public void modifyJobTime(String jobName, 
            String jobGroupName, String triggerName, String triggerGroupName, String cron) {  
        try {  
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);  
            if (trigger == null) {  
                return;  
            }  
 
            String oldTime = trigger.getCronExpression();  
            if (!oldTime.equalsIgnoreCase(cron)) { 
                /** 方式一 ：调用 rescheduleJob 开始 */
                // 触发器  
                TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
                // 触发器名,触发器组  
                triggerBuilder.withIdentity(triggerName, triggerGroupName);
                triggerBuilder.startNow();
                // 触发器时间设定  
                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
                // 创建Trigger对象
                trigger = (CronTrigger) triggerBuilder.build();
                // 方式一 ：修改一个任务的触发时间
                scheduler.rescheduleJob(triggerKey, trigger);
                /** 方式一 ：调用 rescheduleJob 结束 */
 
                /** 方式二：先删除，然后在创建一个新的Job  */
                //JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(jobName, jobGroupName));  
                //Class<? extends Job> jobClass = jobDetail.getJobClass();  
                //removeJob(jobName, jobGroupName, triggerName, triggerGroupName);  
                //addJob(jobName, jobGroupName, triggerName, triggerGroupName, jobClass, cron); 
                /** 方式二 ：先删除，然后在创建一个新的Job */
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
 
    /** 
     * @Description: 移除一个任务 
     *  
     * @param jobName 
     * @param jobGroupName 
     * @param triggerName 
     * @param triggerGroupName 
     */  
    public void removeJob(String jobName, String jobGroupName,  
            String triggerName, String triggerGroupName) {  
        try {  
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
 
            scheduler.pauseTrigger(triggerKey);// 停止触发器  
            scheduler.unscheduleJob(triggerKey);// 移除触发器  
            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));// 删除任务  
        } catch (Exception e) {  
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
 
    public void init() {
    	try {
    		scheduler.clear();
    		
    		Task task = null;
    		Map<String, Object> businessKeys = null;
    		String taskName = "MyJob", taskGroup = "MyJobGroup";
    		// 不存在则注册
    		if (!checkJobKeyExists(taskName, taskGroup)) {
    			task = new Task();
        		task.setTaskId(StringUtil.getRandomUUID());
        		task.setBeanClass("com.feng.springmvc.schedule.job.MyJob");
        		task.setTaskName(taskName);
        		task.setTaskDesc("跑批配置测试");
        		task.setTaskCronExpression("*/5 * * * * ?");
        		task.setTaskStatus("0");
        		task.setTaskGroup(taskGroup);
        		task.setCreatorName("fengjianbo");
        		task.setRecordFlag(1);
        		task.setStartAt(null);
        		task.setRepeatCount(null);
        		businessKeys = new HashMap<>();
        		businessKeys.put("userName", "feng");
        		businessKeys.put("userPwd", "123456");
        		task.setBusinessKey(JSON.toJSONString(businessKeys));
        		addJob(task);
    		}
    		
    		taskName = "HelloJob"; taskGroup = "HelloJobGroup";
    		// 不存在则注册
    		if (!checkJobKeyExists(taskName, taskGroup)) {
    			task = new Task();
    	        task.setTaskId(StringUtil.getRandomUUID());
    	        task.setBeanClass("com.feng.springmvc.schedule.job.HelloJob");
    	        task.setTaskName(taskName);
    	        task.setTaskDesc("仅执行一次");
    	        task.setTaskCronExpression("*/10 * * * * ?");
    	        task.setTaskStatus("0");
    	        task.setTaskGroup(taskGroup);
    	        task.setCreatorName("fengjianbo");
    	        task.setRecordFlag(1);
    	        task.setStartAt(null);
    	        task.setRepeatCount(0); // 仅执行一次，不再重复
    	        businessKeys = new HashMap<>();
    	        businessKeys.put("email1", "445121408@qq.com");
    	        businessKeys.put("email2", "405727062@qq.com");
    	        task.setBusinessKey(JSON.toJSONString(businessKeys));
    	        addJob(task);
    		}
    		
    		taskName = "问候测试2222222222"; taskGroup = "问候测试22222222222-Group";
    		// 不存在则注册
    		if (!checkJobKeyExists(taskName, taskGroup)) {
    			task = new Task();
    	        task.setTaskId(StringUtil.getRandomUUID());
    	        task.setBeanClass("com.feng.springmvc.schedule.job.HelloJob");
    	        task.setTaskName(taskName);
    	        task.setTaskDesc("跑批测试222");
    	        task.setTaskCronExpression("*/25 * * * * ?");
    	        task.setTaskStatus("0");
    	        task.setTaskGroup(taskGroup);
    	        task.setCreatorName("fengjianbo");
    	        task.setRecordFlag(1);
    	        task.setStartAt(null);
    	        task.setRepeatCount(null); 
    	        businessKeys = new HashMap<>();
    	        businessKeys.put("email1", "445121408@qq.com");
    	        businessKeys.put("email2", "405727062@qq.com");
    	        task.setBusinessKey(JSON.toJSONString(businessKeys));
    	        addJob(task);
    		}
    		
	        
	        Task task1 = new Task();
	        task1.setTaskId(StringUtil.getRandomUUID());
	        task1.setBeanClass("com.feng.springmvc.schedule.job.MailJob");
	        task1.setTaskName("邮件发送测试");
	        task1.setTaskDesc("邮件发送测试");
	        task1.setTaskCronExpression("0/30 * * * * ?");
	        task1.setTaskStatus("0");
	        task1.setTaskGroup("邮件发送组");
	        task1.setCreatorName("fengjianbo");
	        task1.setRecordFlag(1);
	        task1.setStartAt(null);
	        task1.setRepeatCount(null); // 仅执行一次，不再重复
	        Map<String, Object> businessKeys2 = new HashMap<>();
	        businessKeys2.put("邮件发送着", "445121408@qq.com");
	        businessKeys2.put("邮件接收者", "445121408@qq.com");
	        task1.setBusinessKey(JSON.toJSONString(businessKeys2));
//	        addJob(task1);
    		
	        System.err.println("isInStandbyMode=>" + scheduler.isInStandbyMode());
	        System.err.println("isShutdown=>" + scheduler.isShutdown());
	        System.err.println("isStarted=>" + scheduler.isStarted());
    		if (!scheduler.isShutdown() && (!scheduler.isStarted() || scheduler.isInStandbyMode())) {
    			scheduler.startDelayed(60);	//延迟60秒
//    			Thread.sleep(60000L);
    		}
    		
    		if (scheduler.isStarted()) {
    			GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
    			Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
    			int len = jobKeys.size();
    			System.err.println("jobKeys.len ==> " + len);
    			List<Task> jobList = new ArrayList<>();
    			for (JobKey jobKey : jobKeys) {
    				//jobKey.getName();
    				String jobName = jobKey.getName(), jobGroup = jobKey.getGroup();
    				System.err.println("job.name=>" + jobName + ",job.group=>" + jobGroup);
    				if (scheduler.checkExists(jobKey)) {
    					System.err.println("job.name=>" + jobName + "定时任务存在");
    					scheduler.resumeJob(jobKey);
    					
    					JobDetail jobDetail = scheduler.getJobDetail(jobKey);
    					if (null == jobDetail) {
    						System.out.println("jobDetail is null");
    					}
    					else {
    						JobDataMap jobDataMap = jobDetail.getJobDataMap();
    					}
        				
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
    				else {
    					System.err.println("job.name=>" + jobName + "定时任务不存在");
    				}
    				
    			}
    			System.err.println("jobList=>" + jobList);
    		}
    		System.err.println("isInStandbyMode2=>" + scheduler.isInStandbyMode());
	        System.err.println("isShutdown2=>" + scheduler.isShutdown());
	        System.err.println("isStarted2=>" + scheduler.isStarted());
    	}
    	catch(Exception e) {
    		throw new RuntimeException(e);
    	}
    }
    
    /** 
     * @Description:启动所有定时任务 
     */  
    public void startJobs() {  
        try {  
            scheduler.start();  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
 
    /** 
     * @Description:关闭所有定时任务 
     */  
    public void shutdownJobs() {  
        try {  
            if (!scheduler.isShutdown()) {  
                scheduler.shutdown();  
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }
 
//    public Scheduler getScheduler() {
//        return scheduler;
//    }
// 
//    public void setScheduler(Scheduler scheduler) {
//        this.scheduler = scheduler;
//    }  
}
