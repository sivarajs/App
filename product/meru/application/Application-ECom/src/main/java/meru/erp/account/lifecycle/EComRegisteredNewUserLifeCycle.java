package meru.erp.account.lifecycle;

import meru.app.engine.entity.AbstractEntityLifeCycle;
import app.domain.account.RegisteredNewUser;
import app.erp.mdm.bp.BusinessPartner;

public class EComRegisteredNewUserLifeCycle extends AbstractEntityLifeCycle<RegisteredNewUser> {

  @Override
  public boolean preCreate(RegisteredNewUser newUser) {

    BusinessPartner customer = new BusinessPartner();
    customer.setUserId(newUser.getId());

    String name = newUser.getName();
    String email = newUser.getEmail();
    if (name == null) {
      customer.setName(email.substring(0,
                                       email.indexOf('@')));
    }
    else {
      customer.setName(name);
    }
    customer.setEmail(email);
    customer.setMobile(newUser.getMobile());
    appEngine.save(customer);

    return false;
  }

  @Override
  public boolean preModify(RegisteredNewUser newUser) {
    preCreate(newUser);
    return false;
  }

}
