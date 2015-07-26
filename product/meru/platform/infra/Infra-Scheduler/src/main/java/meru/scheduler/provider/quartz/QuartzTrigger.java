package meru.scheduler.provider.quartz;

import meru.scheduler.Trigger;

public class QuartzTrigger implements Trigger {

    private org.quartz.Trigger trigger;

    public QuartzTrigger(org.quartz.Trigger trigger) {
        this.trigger = trigger;
    }

    public org.quartz.Trigger getTrigger() {
        return trigger;
    }

}