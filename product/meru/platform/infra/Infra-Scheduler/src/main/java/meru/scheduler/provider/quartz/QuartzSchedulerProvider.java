package meru.scheduler.provider.quartz;

import meru.scheduler.Scheduler;
import meru.scheduler.provider.SchedulerProvider;
import meru.scheduler.provider.TriggerFactory;

import org.quartz.SchedulerException;

public class QuartzSchedulerProvider extends SchedulerProvider {

  public TriggerFactory getTriggerFactory() {

    return new QuartzTriggerFactory();
  }
  
  
  public Scheduler getScheduler(String name) {

    try {
      return new QuartzScheduler(name);
    } catch (SchedulerException e) {
      throw new RuntimeException(e);
    }
  }
  
}
