package meru.app.server;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

import meru.app.AppContext;
import meru.app.AppPort;
import meru.app.binding.BindingComponent;
import meru.app.binding.BindingComponentRegistry;
import meru.app.config.AppConfig;
import meru.app.config.AppEngineConfig;
import meru.app.config.BindingComponentConfig;
import meru.app.config.ConstructorArg;
import meru.app.config.EntityClassRegistryConfig;
import meru.app.config.PersistentStoreConfig;
import meru.app.config.ServiceConfig;
import meru.app.engine.AppEngine;
import meru.app.engine.entity.EntityAppEngine;
import meru.app.engine.entity.EntityLifeCycle;
import meru.app.registry.EntityClassRegistry;
import meru.app.security.SecurityGuardChain;
import meru.app.server.servlet.HttpAppContext;
import meru.app.service.AppServiceManager;
import meru.app.service.ServiceManager;
import meru.app.service.account.AccountService;
import meru.app.service.account.AccountServiceImpl;
import meru.app.session.SessionListener;
import meru.app.session.SessionManager;
import meru.app.session.http.HttpSessionManager;
import meru.messaging.http.HttpMessageSender;
import meru.persistence.PersistentStore;
import meru.sys.SystemDate;
import meru.transaction.TransactionManager;
import app.domain.Property;

public class AppServer {

    private static final String APPLICATION_CONFIG_FILE = "application.xml";

    private AppContext mAppContext;
    private AppConfig mAppConfig;
    private AppState mAppState;

    private EntityAppEngine mAppEngine;
    private SessionManager mSessionManager;

    private Properties mAppProperties;

    private Map<String, Object> mVariables;

    private AppServiceManager mServiceManager;

    public AppServer(AppContext appContext) {

        mAppContext = appContext;
        mAppState = AppState.Initing;
    }

    public void init() {

        mServiceManager = new AppServiceManager();

        bindPreInitVariables();
        mSessionManager = new HttpSessionManager();

        new SystemDate(TimeZone.getTimeZone("UTC"),
                       Locale.ENGLISH);

        InputStream inputStream = mAppContext.getInputStream("/WEB-INF"
                + File.separator + APPLICATION_CONFIG_FILE);

        mAppConfig = AppServerConfig.readAppConfig(inputStream);

        ((HttpAppContext) mAppContext).setAppPorts(createAppPort("http"),
                                                   createAppPort("https"));

        PersistentStoreConfig persistentStoreConfig = mAppConfig.getPersistentStoreConfig();

        EntityClassRegistryConfig entityClassRegistryConfig = mAppConfig.getEntityClassRegistryConfig();

        EntityClassRegistry entityClassRegistry = null;
        try {
            entityClassRegistry = (EntityClassRegistry) entityClassRegistryConfig.getEntityClassRegistryClass()
                                                                                 .newInstance();
        } catch (InstantiationException |
                 IllegalAccessException |
                 ClassNotFoundException e) {

            throw new RuntimeException(e);
        }

        try {

            PersistentStore persistentStore = null;

            Class<?> persistentStoreClass = persistentStoreConfig.getPersistentStoreClass();
            List<ConstructorArg> constructorArgs = persistentStoreConfig.getConstructorArgs();

            if (constructorArgs != null && !constructorArgs.isEmpty()) {
                Class<?>[] args = new Class<?>[constructorArgs.size()];
                Object[] values = new Object[constructorArgs.size()];

                int i = 0;
                for (ConstructorArg arg : constructorArgs) {
                    args[i] = arg.getType();

                    if (args[i] == AppConfig.class) {
                        values[i++] = mAppConfig;
                    }
                    else {
                        values[i++] = arg.getValue();
                    }
                }

                Constructor<?> cons = persistentStoreClass.getConstructor(args);

                persistentStore = (PersistentStore) cons.newInstance(values);
            }
            else {
                persistentStore = (PersistentStore) persistentStoreClass.newInstance();
            }

            AppEngineConfig appEngineConfig = mAppConfig.getAppEngineConfig();
            Class<?> appEngineClass = appEngineConfig.getEngineClass();

            Constructor<?> cons = appEngineClass.getConstructor(new Class[] {
                    AppConfig.class, AppContext.class, PersistentStore.class,
                    ServiceManager.class });

            mAppEngine = (EntityAppEngine) cons.newInstance(new Object[] {
                    mAppConfig, mAppContext, persistentStore,
                    mServiceManager });
        } catch (InstantiationException |
                 IllegalAccessException |
                 IllegalArgumentException |
                 InvocationTargetException |
                 NoSuchMethodException |
                 SecurityException |
                 ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        mServiceManager.setContext(mAppEngine);

        mAppProperties = new Properties();

        List<Property> appProperties = mAppEngine.get(mAppEngine.createQuery(Property.class));
        Map<String, String> configProps = mAppConfig.getProperties();
        for (Property property : appProperties) {
            String name = property.getName();
            if (property.getPrefix() != null) {
                name = property.getPrefix() + "." + name;
            }

            String value = property.getValue();
            mAppProperties.put(name,
                               value);
            configProps.put(name,
                            value);
            mServiceManager.addAppProperty(name,
                                           value);

        }

        try {
            List<EntityLifeCycle<?>> entityLifeCycles = mAppEngine.initializeLifeCycleListeners();

            for (EntityLifeCycle<?> entityLifeCycle : entityLifeCycles) {

                if (entityLifeCycle instanceof SessionListener) {
                    mSessionManager.addSessionListener((SessionListener) entityLifeCycle);
                }

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (Map.Entry<String, String> entry : mAppConfig.getProperties()
                                                         .entrySet()) {
            mServiceManager.addAppProperty(entry.getKey(),
                                           entry.getValue());
        }

        List<BindingComponentConfig> bcConfigs = mAppConfig.getBindingComponentConfigs();

        SecurityGuardChain securityGuardChain = null;

        // if (!appProperties.isEmpty()) {
        // securityGuardChain =
        // DefaultSecurityGuardChain.createSecurityGuardChain(mAppEngine);

        // }

        for (BindingComponentConfig bcConfig : bcConfigs) {

            try {
                BindingComponent<?> bindingComponent = (BindingComponent<?>) bcConfig.getBindingComponentClass()
                                                                                     .newInstance();
                bindingComponent.setContext(mAppConfig,
                                            mAppContext,
                                            mAppEngine,
                                            mSessionManager,
                                            securityGuardChain,
                                            entityClassRegistry);
                mSessionManager.setUIDGenerator(mAppContext.getUIDGenerator());
                BindingComponentRegistry.getInstance()
                                        .addComponentComponent(bindingComponent);

            } catch (InstantiationException |
                     IllegalAccessException |
                     ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        bindPostInitVariables();

        try {
            createServices();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        /*
         * try { Class<?> appClass = mAppConfig.getApplicationClass();
         * 
         * if (appClass != null) {
         * 
         * Application application = (Application) appClass.newInstance();
         * application.initialize(mAppConfig); }
         * 
         * } catch (ClassNotFoundException | InstantiationException |
         * IllegalAccessException e) { throw new RuntimeException(e); }
         */

        mServiceManager.startServices();
    }

    private void bindPreInitVariables() {

        mVariables = new HashMap<String, Object>(3);

        mVariables.put(ServiceManager.SERVICE_TRANSACTION_MANAGER,
                       TransactionManager.getInstance());
        mServiceManager.addService(ServiceManager.SERVICE_TRANSACTION_MANAGER,
                                   TransactionManager.getInstance());

        HttpMessageSender messageSender = new HttpMessageSender();
        mServiceManager.addService(ServiceManager.MESSAGE_SENDER,
                                   messageSender);

        AccountService accountService = new AccountServiceImpl(messageSender);
        mServiceManager.addService(AccountService.SERVICE_ACCOUNT,
                                   accountService);
    }

    private void bindPostInitVariables() {

        mVariables = new HashMap<String, Object>(3);
        mVariables.put("appConfig",
                       mAppConfig);
        mVariables.put("appContext",
                       mAppContext);
        mVariables.put("appEngine",
                       mAppEngine);

        mVariables.put(AppConfig.class.getName(),
                       mAppConfig);
        mVariables.put(AppContext.class.getName(),
                       mAppContext);
        mVariables.put(AppEngine.class.getName(),
                       mAppEngine);

    }

    private Object getVariable(String name) {

        Object object = mVariables.get(name);

        if (object == null) {
            throw new RuntimeException("No object bound for variable : "
                    + name);
        }

        return object;
    }

    private void createServices() throws ClassNotFoundException,
                                  InstantiationException,
                                  IllegalAccessException,
                                  IllegalArgumentException,
                                  InvocationTargetException {

        List<ServiceConfig> serviceConfigs = mAppConfig.getAppServiceConfigs();

        if (serviceConfigs != null) {
            for (ServiceConfig serviceConfig : serviceConfigs) {

                Class<?> appServiceClass = serviceConfig.getServiceClass();

                Constructor<?>[] constructors = appServiceClass.getConstructors();

                if (constructors.length == 0) {
                    throw new RuntimeException("No public constructor found in the class "
                            + appServiceClass);
                }
                else {

                    Class<?>[] classTypes = constructors[0].getParameterTypes();

                    if (classTypes.length == 0) {

                    }
                    else {
                        Object[] objTypes = new Object[classTypes.length];

                        for (int i = 0; i < classTypes.length; i++) {
                            objTypes[i] = getVariable(classTypes[i].getName());

                        }

                        Object service = constructors[0].newInstance(objTypes);
                        mServiceManager.addService(serviceConfig.getName(),
                                                   service);
                    }

                }
            }
        }

    }

    private AppPort createAppPort(String protocol) {
        String portProp = null;
        if (protocol == null || protocol.equals("http")) {
            portProp = "app.http.port";
        }

        String portStr = mAppConfig.getProperty(portProp);

        Integer port = null;
        if (portStr != null && portStr.trim()
                                      .length() != 0) {
            port = Integer.parseInt(portStr);
        }

        return new AppPort(protocol,
                           mAppConfig.getProperty("app.http.domain"),
                           port,
                           mAppContext.getApplicationRoot());

    }

    public String toString() {
        return mAppState.name();
    }

}
