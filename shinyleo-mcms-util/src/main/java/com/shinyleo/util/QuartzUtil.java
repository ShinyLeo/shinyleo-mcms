package com.shinyleo.util;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by zhugh on 17/7/20.
 */
public class QuartzUtil {

    private static SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();
    private static String JOB_GROUP_NAME = "EXTJWEB_JOBGROUP_NAME";
    private static String TRIGGER_GROUP_NAME = "EXTJWEB_TRIGGERGROUP_NAME";

    public QuartzUtil() {
    }

    public static void addJob(String jobName, Class cls, String time) {
        try {
            Scheduler e = gSchedulerFactory.getScheduler();
            JobDetail jobDetail = JobBuilder.newJob(cls).withIdentity(jobName, JOB_GROUP_NAME).build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(new TriggerKey(jobName, TRIGGER_GROUP_NAME)).startNow().withSchedule(CronScheduleBuilder.cronSchedule(time)).build();
            e.scheduleJob(jobDetail, trigger);
            if(!e.isShutdown()) {
                e.start();
            }

        } catch (Exception var6) {
            throw new RuntimeException(var6);
        }
    }

    public static void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class jobClass, String time) {
        try {
            Scheduler e = gSchedulerFactory.getScheduler();
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(new TriggerKey(triggerName, triggerGroupName)).startNow().withSchedule(CronScheduleBuilder.cronSchedule(time)).build();
            e.scheduleJob(jobDetail, trigger);
        } catch (Exception var9) {
            throw new RuntimeException(var9);
        }
    }

    public static void modifyJobTime(String jobName, String time) {
        try {
            Scheduler e = gSchedulerFactory.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);
            CronTrigger trigger = (CronTrigger)e.getTrigger(triggerKey);
            if(trigger != null) {
                String oldTime = trigger.getCronExpression();
                if(!oldTime.equalsIgnoreCase(time)) {
                    JobKey job = JobKey.jobKey(jobName);
                    JobDetail jobDetail = e.getJobDetail(job);
                    Class objJobClass = jobDetail.getJobClass();
                    removeJob(jobName);
                    addJob(jobName, objJobClass, time);
                }

            }
        } catch (Exception var9) {
            throw new RuntimeException(var9);
        }
    }

    public static void modifyJobTime(String triggerName, String triggerGroupName, String time) {
        try {
            Scheduler e = gSchedulerFactory.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName);
            CronTrigger trigger = (CronTrigger)e.getTrigger(triggerKey);
            if(trigger != null) {
                String oldTime = trigger.getCronExpression();
                if(!oldTime.equalsIgnoreCase(time)) {
                    trigger.getTriggerBuilder().startNow().withSchedule(CronScheduleBuilder.cronSchedule(time));
                    e.resumeTrigger(triggerKey);
                }

            }
        } catch (Exception var7) {
            throw new RuntimeException(var7);
        }
    }

    public static void removeJob(String jobName) {
        try {
            Scheduler e = gSchedulerFactory.getScheduler();
            JobKey job = JobKey.jobKey(jobName, JOB_GROUP_NAME);
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);
            e.pauseTrigger(triggerKey);
            e.unscheduleJob(triggerKey);
            e.deleteJob(job);
        } catch (Exception var4) {
            throw new RuntimeException(var4);
        }
    }

    public static void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
        try {
            Scheduler e = gSchedulerFactory.getScheduler();
            JobKey job = JobKey.jobKey(jobName, jobGroupName);
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
            e.pauseTrigger(triggerKey);
            e.unscheduleJob(triggerKey);
            e.deleteJob(job);
        } catch (Exception var7) {
            throw new RuntimeException(var7);
        }
    }

    public static void startJobs() {
        try {
            Scheduler e = gSchedulerFactory.getScheduler();
            e.start();
        } catch (Exception var1) {
            throw new RuntimeException(var1);
        }
    }

    public static void shutdownJobs() {
        try {
            Scheduler e = gSchedulerFactory.getScheduler();
            if(!e.isShutdown()) {
                e.shutdown();
            }

        } catch (Exception var1) {
            throw new RuntimeException(var1);
        }
    }
}
