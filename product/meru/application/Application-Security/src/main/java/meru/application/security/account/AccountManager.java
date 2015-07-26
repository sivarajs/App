package meru.application.security.account;

import javax.persistence.PersistenceException;

import app.domain.security.NewUser;
import app.domain.security.Role;
import app.domain.security.SecuredApplication;
import app.domain.security.User;
import meru.app.AppContext;
import meru.app.engine.AppEngine;
import meru.app.security.AuthToken;
import meru.application.security.Password;
import meru.application.security.UserType;
import meru.exception.InvalidLoginException;
import meru.persistence.EntityQuery;
import meru.sys.SystemCalendar;
import meru.sys.lang.StringHelper;
import meru.sys.uid.UIDGenerator;
import meru.sys.uid.key.RandomIntegerKeyGenerator;

public class AccountManager {

    private AppEngine appEngine;
    private UIDGenerator uidGenerator;
    private RandomIntegerKeyGenerator randomIntegerKeyGenerator;
    private SystemCalendar systemCalendar;

    // TODO
    private static AccountManager INSTANCE;

    public AccountManager(AppContext appContext, AppEngine appEngine) {
        this.appEngine = appEngine;
        this.uidGenerator = appContext.getUIDGenerator();
        randomIntegerKeyGenerator = new RandomIntegerKeyGenerator((byte) 4);
        systemCalendar = SystemCalendar.getInstance();

        INSTANCE = this;

    }

    public static AccountManager getInstance() {
        return INSTANCE;
    }

    public SecuredApplication getSecuredApplication(String appId) {
        EntityQuery<SecuredApplication> entityQuery = appEngine.createQuery(SecuredApplication.class);
        entityQuery.addQueryParameter("uid",
                                      appId);

        SecuredApplication app = appEngine.getFirst(entityQuery);

        if (app == null) {
            throw new RuntimeException("Unknonwn application : " + appId);
        }

        return app;
    }

    public AuthToken authenticate(String userName,
                                  String password,
                                  String appId) {

        SecuredApplication app = getSecuredApplication(appId);

        User user = validateUser(userName,
                                 password,
                                 appId);

        String info = user.getInfo();

        return new AuthToken(uidGenerator.getUId(),
                             user.getName(),
                             (info == null) ? user.getId()
                                     : Long.parseLong(info),
                             app.getRoot() + "/" + user.getPrimaryRole()
                                                       .getHome());
    }

    private User validateUser(String userName, String password, String appId) {
        if (StringHelper.isNullOrEmpty(userName)
                || StringHelper.isNullOrEmpty(password)) {
            throw new InvalidLoginException("Invalid User Name or Password");
        }

        /*
         * UserType userType = UserType.getUserType(userName); if (userType ==
         * UserType.MOBILE) { userName = "91" + userName; }
         */
        User user = getUser(userName,
                            appId);
        password = Password.encryptPassword(password);

        if (!password.equals(user.getPassword())) {
            throw new InvalidLoginException("Invalid User Name or Password");
        }

        return user;
    }

    private User getUserNoThrow(String userName, String appId) {

        SecuredApplication secApp = getSecuredApplication(appId);

        UserType userType = UserType.getUserType(userName);

        EntityQuery<User> entityFilter = appEngine.createQuery(User.class);
        entityFilter.addQueryParameter("appId",
                                       secApp.getId());
        entityFilter.addQueryParameter(userType.getAttrName(),
                                       userName);

        return (User) appEngine.getFirst(entityFilter);

    }

    private User getUser(String userName, String appId) {

        User user = getUserNoThrow(userName,
                                   appId);

        if (user == null) {
            throw new InvalidLoginException("The user '" + userName
                    + "' does not exist");
        }

        return user;
    }

    public boolean userExists(String userName, String appId) {

        return getUserNoThrow(userName,
                              appId) != null;
    }

    private void checkUserExistence(String userId, String appId) {
        if (userExists(userId,
                       appId)) {
            throw new RuntimeException("The user [" + userId
                    + "] already exists");
        }
    }

    private void checkNewUserExistence(String userId, String appId) {

        UserType userType = UserType.getUserType(userId);

        SecuredApplication secApp = getSecuredApplication(appId);

        EntityQuery<NewUser> entityFilter = appEngine.createQuery(NewUser.class);
        entityFilter.addQueryParameter("appId",
                                       secApp.getId());
        entityFilter.addQueryParameter(userType.getAttrName(),
                                       userId);

        NewUser newUser = appEngine.getFirst(entityFilter);

        if (newUser != null) {
            throw new RuntimeException("The user [" + userId
                    + "] already exists");
        }
    }

    public NewUser createTempNewUser(String userName,
                                     String email,
                                     String mobile,
                                     String password,
                                     long roleId,
                                     String appId) {

        NewUser user = new NewUser();

        String userId = null;

        if (!StringHelper.isNullOrEmpty(userName)) {
            checkNewUserExistence(userName,
                                  appId);
            checkUserExistence(userName,
                               appId);
            userId = userName;
            user.setName(userId);
        }
        if (!StringHelper.isNullOrEmpty(email)) {
            checkNewUserExistence(email,
                                  appId);
            checkUserExistence(email,
                               appId);
            userId = email;
            user.setEmail(userId);
            user.setEmailAccessToken(uidGenerator.getUId());
        }
        if (!StringHelper.isNullOrEmpty(mobile)) {
            userId = (mobile.length() == 10) ? "91" + mobile : mobile;
            checkNewUserExistence(userId,
                                  appId);
            checkUserExistence(userId,
                               appId);
            user.setMobile(userId);
            user.setMobileAccessToken(randomIntegerKeyGenerator.getKey());
        }

        if (StringHelper.isNullOrEmpty(userId)
                || StringHelper.isNullOrEmpty(password)) {

            throw new RuntimeException("Invalid UserName or Password");
        }

        EntityQuery<SecuredApplication> entityQuery = appEngine.createQuery(SecuredApplication.class);
        entityQuery.addQueryParameter("name",
                                      appId);

        SecuredApplication app = getSecuredApplication(appId);

        user.setAppId(app.getId());

        Role role = appEngine.get(Role.class, roleId);
        
        if (role == null) {
            throw new RuntimeException("Unknown Role: "+roleId);
        }

        if (password != null) {
            password = Password.encryptPassword(password);
        }

        user.setPassword(password);
        user.setPrimaryRole(role);
        user.setRegisteredOn(systemCalendar.getUTCCalendar());
        try {

            appEngine.save(user);

        } catch (PersistenceException e) {

            Throwable throwable = e.getCause();
            if ("ConstraintViolationException".equalsIgnoreCase(throwable.getClass()
                                                                         .getSimpleName())) {

                throw new RuntimeException("The user [" + userId
                        + "] already exists");

            }

            throw e;

        }

        return user;
    }

}
