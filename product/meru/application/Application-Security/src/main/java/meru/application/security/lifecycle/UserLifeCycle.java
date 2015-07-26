package meru.application.security.lifecycle;

import meru.exception.AppException;
import meru.exception.SystemException;
import meru.exception.code.SecurityErrorCode;
import meru.sys.lang.StringHelper;
import app.domain.security.Role;
import app.domain.security.User;

public class UserLifeCycle extends AccountLifeCycle<User> {

    @Override
    public boolean preCreate(User newUser) {

        String userName = newUser.getName();
        
        if (userName == null) {
          userName = newUser.getEmail();
        }
        
        if (userName == null) {
          userName = newUser.getMobile();
        }
        
        String password = newUser.getPassword();

        if (StringHelper.isNullOrEmpty(userName) || StringHelper.isNullOrEmpty(password)) {
            throw new AppException(SecurityErrorCode.INVALID_USER_PASSWORD);
        }

        if (userExists(userName, newUser.getAppId())) {
            throw new AppException(SecurityErrorCode.USER_ALREADY_EXISTS,
                                   userName);
        }
        
        
//        if (newUser.getName() == null) {
//          newUser.setName(userName);
//        }
        
        popuplateUser(newUser);

        newUser.setState("A");

        return true;
    }

    @Override
    public boolean preModify(User user) {
        popuplateUser(user);
        return true;

    }

    private void popuplateUser(User user) {
        Long roleId = user.getPrimaryRole()
                             .getId();

        Role role = appEngine.get(Role.class,
                                  roleId);
        if (role == null) {
            throw new SystemException("Unknown Role id : " + roleId);
        }

//        String password = Password.encryptPassword(user.getPassword());
//
//        user.setPassword(password);
    }
}
