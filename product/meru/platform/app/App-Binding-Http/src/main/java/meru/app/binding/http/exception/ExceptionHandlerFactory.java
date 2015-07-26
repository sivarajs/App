package meru.app.binding.http.exception;

import java.util.HashMap;
import java.util.Map;

import meru.exception.AppEntityWarning;
import meru.exception.AppException;
import meru.exception.InvalidLoginException;
import meru.exception.LoginRequiredException;
import meru.exception.RedirectException;

public class ExceptionHandlerFactory {

    
    private static Map<Class<?>, ExceptionHandler> exceptionHandlers = new HashMap<Class<?>, ExceptionHandler>(5);
    private static GenericExceptionHandler genericExceptionHandler = new GenericExceptionHandler();
    
    static {
        exceptionHandlers.put(AppException.class, new AppExceptionHandler());
        exceptionHandlers.put(RedirectException.class, new RedirectExceptionHandler());
        exceptionHandlers.put(InvalidLoginException.class, new InvalidLoginExceptionHandler());
        exceptionHandlers.put(LoginRequiredException.class, new LoginRequiredExceptionHandler());
        exceptionHandlers.put(AppEntityWarning.class, new AppEntityWarningHandler());
    }
    
    
    public static ExceptionHandler getExceptionHandler(Class<?> exceptionClass) {
        
        ExceptionHandler exceptionHandler = exceptionHandlers.get(exceptionClass);
        if (exceptionHandler == null) {
            exceptionHandler = genericExceptionHandler;
        }
        
        return exceptionHandler;
    }
}
