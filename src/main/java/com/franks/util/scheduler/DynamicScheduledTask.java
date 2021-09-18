package com.franks.util.scheduler;

import com.franks.util.date.DateUtil;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 创建动态定时任务
 *
 * @module
 * @author frank
 * @date 2021/8/21 11:50
 */
@Component
public class DynamicScheduledTask<T extends SchedulerBaseVo> {

    private final Logger logger = LoggerFactory.getLogger(DynamicScheduledTask.class);

    @Autowired
    Scheduler scheduler;

    /**
     * 创建并执行任务
     */
    private void createScheduler(T t, Date tsTime, String jobName, String triggerName, String groupName, int intervalInSeconds, Class<? extends Job> clazz) throws SchedulerException {
        logger.info("添加动态定时任务,任务名称:{},触发器名称:{},分组名称:{},触发间隔:{},执行时间:{},内容:{}" , jobName, triggerName,groupName,intervalInSeconds,tsTime,t);
        JobDetail job = JobBuilder.newJob(clazz).withIdentity(jobName, groupName).build();
        job.getJobDataMap().put(t.getClass().getName(), t);
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, groupName)
            .startAt(tsTime)
            .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(intervalInSeconds).withRepeatCount(0))
            .build();
        // 创建实例
        try {
            scheduler.scheduleJob(job, trigger);
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (SchedulerException e) {
            scheduler.shutdown(true);
            logger.error("创建动态定时任务失败：",e);
        }
    }
    /**
     * 创建并执行任务
     */
    public void createSchedulerHour(T t, Integer delay, Class<? extends Job> clazz)  {
        try {
            createScheduler(t, DateUtil.addHour(delay), t.getJobName(), t.getTriggerName(), t.getGroupName(), 10,clazz);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    /**
     * 创建并执行任务
     */
    public void createSchedulerSecond(T t, Integer delay, Class<? extends Job> clazz) {
        try {
            createScheduler(t, DateUtil.addSecond(delay), t.getJobName(), t.getTriggerName(), t.getGroupName(), 10,clazz);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    /**
     * 删除任务
     */
    public boolean deleteJob(String jobName, String groupName) {
        try {
            return scheduler.deleteJob(JobKey.jobKey(jobName, groupName));
        } catch (SchedulerException e) {
        }
        return Boolean.FALSE;
    }

    //--
    public void getAllJobs() throws SchedulerException {
        for (String groupName : scheduler.getJobGroupNames()) {
            for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                String jobName = jobKey.getName();
                String jobGroup = jobKey.getGroup();

                List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
                Date nextFireTime = triggers.get(0).getNextFireTime();
                logger.debug("========== jobName: {}, groupName: {}, nextTime: {}", jobName, jobGroup, nextFireTime);
            }
        }
    }
}

