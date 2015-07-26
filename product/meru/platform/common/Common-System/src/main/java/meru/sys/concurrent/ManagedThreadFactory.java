package meru.sys.concurrent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class ManagedThreadFactory implements ThreadFactory {

    private final String mCategory;
    private final int mPriority;
    private final AtomicInteger mSeq = new AtomicInteger(1);

    public ManagedThreadFactory(String category) {
        this(category,
             Thread.NORM_PRIORITY);
    }

    public ManagedThreadFactory(String category,
                                int priority) {

        mCategory = category;
        mPriority = priority;
    }

    public Thread newThread(Runnable runnable) {

        String name = mCategory + ":" + mSeq.getAndIncrement();
        Thread thread = new Thread(runnable,
                                   name);
        thread.setPriority(mPriority);
        thread.setDaemon(true);
        return thread;

    }
}