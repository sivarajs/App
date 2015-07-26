package meru.scheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import meru.scheduler.provider.SchedulerProvider;
import meru.scheduler.provider.TriggerFactory;
import meru.scheduler.provider.quartz.QuartzSchedulerProvider;

public class SchedulerManager {

  private final Map<String, String> mPropertyMap = new HashMap<String, String>(1);
  private final Map<String, Scheduler> mSchedulerMap = new ConcurrentHashMap<String, Scheduler>(1);
  private TriggerFactory mTriggerFactory;

  private SchedulerProvider mSchedulerProvider = new QuartzSchedulerProvider();


  public SchedulerManager(Map<String, String> properties) {

    if (properties != null) {
      this.mPropertyMap.putAll(properties);
    }

    this.mTriggerFactory = mSchedulerProvider.getTriggerFactory();
  }


  public TriggerFactory getTriggerFactory() {

    return this.mTriggerFactory;
  }


  public Scheduler getScheduler(String name) {

    Scheduler scheduler = (Scheduler) this.mSchedulerMap.get(name);

    if (scheduler == null) {
      scheduler = createScheduler(name);
    }

    return scheduler;
  }


  private synchronized Scheduler createScheduler(String name) {

    if (this.mSchedulerMap.containsKey(name)) {
      return (Scheduler) this.mSchedulerMap.get(name);
    }

    Scheduler scheduler = mSchedulerProvider.getScheduler(name);
    this.mSchedulerMap.put(name, scheduler);

    return scheduler;
  }


  void destroy() {

  }
}