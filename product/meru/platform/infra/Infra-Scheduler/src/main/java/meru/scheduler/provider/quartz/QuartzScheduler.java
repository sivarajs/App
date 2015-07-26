package meru.scheduler.provider.quartz;

import java.util.Random;

import meru.scheduler.Job;
import meru.scheduler.JobId;
import meru.scheduler.Scheduler;
import meru.scheduler.SchedulerLifyCycleListener;
import meru.scheduler.Trigger;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzScheduler implements Scheduler, SchedulerLifyCycleListener {
    private String mName;
    private org.quartz.Scheduler mScheduler;

    public QuartzScheduler(String name) throws SchedulerException {

        this.mName = name;
        SchedulerFactory sf = new StdSchedulerFactory();
        this.mScheduler = sf.getScheduler();
        this.mScheduler.start();
    }

    public String getName() {

        return this.mName;
    }

    public JobId submitJob(Job job,
                           Trigger trigger) {

        JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class)
                                        .withIdentity(this.mName + new Random().nextInt(),
                                                      "default")
                                        .build();
        jobDetail.getJobDataMap()
                 .put("Job",
                      job);
        try {
            this.mScheduler.scheduleJob(jobDetail,
                                        ((QuartzTrigger) trigger).getTrigger());
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void stop() {

    }
}