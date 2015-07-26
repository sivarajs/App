package meru.application.security.lifecycle;

import meru.app.engine.entity.AbstractEntityLifeCycle;
import meru.application.security.UserType;
import meru.application.security.account.AccountState;
import meru.persistence.EntityQuery;
import app.domain.security.NewUser;
import app.domain.security.User;

public abstract class AccountLifeCycle<T> extends AbstractEntityLifeCycle<T> {

  
  protected User getUserNoThrow(String userName, long appId) {

    UserType userType = UserType.getUserType(userName);

    EntityQuery<User> entityQuery = appEngine.createQuery(User.class);
    entityQuery.addQueryParameter(userType.getAttrName(),
                                  userName);
    entityQuery.addQueryParameter("appId", appId);

    return (User) appEngine.getFirst(entityQuery);

  }

  protected User getUser(String userName, long appId) {

    User user = getUserNoThrow(userName, appId);

    if (user == null) {
      throw new RuntimeException("The user with '" + userName
          + "' does not exist");
    }

    return user;
  }

  protected boolean userExists(String userName, long appId) {

    return getUserNoThrow(userName, appId) != null;
  }

  protected User createUser(NewUser newUser) {

    User user = new User();

    user.setName(newUser.getName());
    user.setEmail(newUser.getEmail());
    user.setMobile(newUser.getMobile());
    user.setPassword(newUser.getPassword());
    user.setPrimaryRole(newUser.getPrimaryRole());
    user.setAppId(newUser.getAppId());
    user.setState(AccountState.Active.getCode());

    appEngine.save(user);

    return user;
  }

}
