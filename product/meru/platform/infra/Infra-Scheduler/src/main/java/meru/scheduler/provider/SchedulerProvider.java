package meru.scheduler.provider;

import meru.scheduler.Scheduler;

public abstract class SchedulerProvider {

  public abstract Scheduler getScheduler(String name);
  
  public TriggerFactory getTriggerFactory() {
     return null; 
  }
  
}
