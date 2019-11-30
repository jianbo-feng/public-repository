package com.feng.quartz.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 任务信息
 */
public class Task implements Serializable {

    /**
     * 任务ID：唯一标识
     */
    private String taskId;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 任务描述
     */
    private String taskDesc;

    /**
     * cron表达式
     */
    private String taskCronExpression;

    /**
     * 任务执行时调用哪个类的方法 包名+类名
     */
    private String beanClass;

    /**
     * 任务状态
     */
    private String taskStatus;

    /**
     * 任务分组
     */
    private String taskGroup;

    /**
     * 业务配置信息
     */
    private String businessKey;

    /**
     * 执行次数(若为null,则表示无限次数；0：执行一次之后不再执行)
     */
    private Integer repeatCount;

    /**
     * 任务启动时间
     */
    private String startAt;

    /**
     * 信息记录时间
     */
    private String recordTime;

    /**
     * 信息标识(1,正常;0,删除;)
     */
    private Integer recordFlag;

    /**
     * 信息创建者ID
     */
    private String creatorId;

    /**
     * 信息更新者ID
     */
    private String updatorId;

    /**
     * 信息更新时间
     */
    private String updateTime;

    /**
     * 信息创建者姓名
     */
    private String creatorName;

    /**
     * 信息更新者姓名
     */
    private String updatorName;

    /**
     * 检索关键字
     */
    private String key;

    /**
     * 任务ID组
     */
    private List<String> taskIds;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String getTaskCronExpression() {
        return taskCronExpression;
    }

    public void setTaskCronExpression(String taskCronExpression) {
        this.taskCronExpression = taskCronExpression;
    }

    public String getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(String beanClass) {
        this.beanClass = beanClass;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskGroup() {
        return taskGroup;
    }

    public void setTaskGroup(String taskGroup) {
        this.taskGroup = taskGroup;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public Integer getRepeatCount() {
        return repeatCount;
    }

    public void setRepeatCount(Integer repeatCount) {
        this.repeatCount = repeatCount;
    }

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public Integer getRecordFlag() {
        return recordFlag;
    }

    public void setRecordFlag(Integer recordFlag) {
        this.recordFlag = recordFlag;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getUpdatorId() {
        return updatorId;
    }

    public void setUpdatorId(String updatorId) {
        this.updatorId = updatorId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getUpdatorName() {
        return updatorName;
    }

    public void setUpdatorName(String updatorName) {
        this.updatorName = updatorName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getTaskIds() {
        return taskIds;
    }

    public void setTaskIds(List<String> taskIds) {
        this.taskIds = taskIds;
    }

    public Task() {
    }

    public Task(String taskId, String taskName, String taskDesc, String taskCronExpression, String beanClass,
                String taskStatus, String taskGroup, String businessKey, Integer repeatCount, String startAt,
                String recordTime, Integer recordFlag, String creatorId, String updatorId, String updateTime) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDesc = taskDesc;
        this.taskCronExpression = taskCronExpression;
        this.beanClass = beanClass;
        this.taskStatus = taskStatus;
        this.taskGroup = taskGroup;
        this.businessKey = businessKey;
        this.repeatCount = repeatCount;
        this.startAt = startAt;
        this.recordTime = recordTime;
        this.recordFlag = recordFlag;
        this.creatorId = creatorId;
        this.updatorId = updatorId;
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId='" + taskId + '\'' +
                ", taskName='" + taskName + '\'' +
                ", taskDesc='" + taskDesc + '\'' +
                ", taskCronExpression='" + taskCronExpression + '\'' +
                ", beanClass='" + beanClass + '\'' +
                ", taskStatus='" + taskStatus + '\'' +
                ", taskGroup='" + taskGroup + '\'' +
                ", businessKey='" + businessKey + '\'' +
                ", repeatCount=" + repeatCount +
                ", startAt='" + startAt + '\'' +
                ", recordTime='" + recordTime + '\'' +
                ", recordFlag=" + recordFlag +
                ", creatorId='" + creatorId + '\'' +
                ", updatorId='" + updatorId + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", creatorName='" + creatorName + '\'' +
                ", updatorName='" + updatorName + '\'' +
                '}';
    }
}
