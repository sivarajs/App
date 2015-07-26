package meru.sys;

import java.util.TimeZone;

public class ThreadContext {

    private TimeZone timeZone;

    private static final ThreadLocal<ThreadContext> threadContext = new ThreadLocal<ThreadContext>();

    public static ThreadContext getCurrentContext() {
        ThreadContext tContext = threadContext.get();
        if (tContext == null) {
            tContext = new ThreadContext();
            threadContext.set(tContext);
        }
        
        return tContext;
    }
    
    public static void clear() {
        threadContext.remove();
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

}
