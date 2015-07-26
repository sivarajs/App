package meru.app.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ServiceManager {

    public static final String MAIL_BOX_NAME = "app.MailBox";
    public static final String SERVICE_TRANSACTION_MANAGER = "app.TransactionManager";
    public static final String MESSAGE_SENDER = "app.messageSender";

    private Map<String, Object> mAppServices = new HashMap<String, Object>(2);
    protected Properties mAppProperties = new Properties();

    private static ServiceManager INSTANCE;

    protected ServiceManager() {
        INSTANCE = this;
    }

    public static final ServiceManager getInstance() {
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    public <T> T getService(String name, Class<T> serviceType) {
        return (T) mAppServices.get(name);
    }

    public void addService(String name, Object object) {
        mAppServices.put(name,
                         object);
    }

    public String getAppProperty(String name) {
        return mAppProperties.getProperty(name);
    }

    public void addAppProperty(String name, String value) {
        mAppProperties.put(name,
                           value);
    }

}
