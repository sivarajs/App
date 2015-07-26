package meru.scheduler.provider.thread;

import java.io.Serializable;
import java.util.Date;

import meru.scheduler.Trigger;

public class ThreadTrigger implements Trigger, Serializable {

    private static final long serialVersionUID = 1L;

    private Date mStartDate;
    private Date mEndDate;
    private long mInterval;

    public ThreadTrigger(Date startDate) {
        mStartDate = startDate;
    }

    public ThreadTrigger(Date startDate,
                         long repeatInterval) {
        mStartDate = startDate;
        mInterval = repeatInterval;
    }

    public ThreadTrigger(Date startDate,
                         Date endDate,
                         long repeatInterval) {

        mStartDate = startDate;
        mEndDate = endDate;
        mInterval = repeatInterval;
    }

    public Date getStartDate() {
        return mStartDate;
    }

    public Date getEndDate() {
        return mEndDate;
    }

    public long getInterval() {
        return mInterval;
    }
}