package meru.erp.mdm.bp.lifecycle;

import meru.app.engine.entity.AbstractEntityLifeCycle;
import meru.app.session.Session;
import meru.app.session.SessionListener;
import meru.exception.AppException;
import meru.persistence.EntityQuery;
import app.erp.mdm.bp.BusinessPartner;

public class BusinessPartnerLifeCycle extends AbstractEntityLifeCycle<BusinessPartner> implements SessionListener {

  @Override
  public void init() {

  }

  @Override
  public boolean preCreate(BusinessPartner businessPartner) {

    return true;
  }

  @Override
  public void sessionInitialized(Session session) {

  }

  @Override
  public void userLoggedIn(Session session) {
    Long userId = session.getUserId();
    EntityQuery<BusinessPartner> entityQuery = appEngine.createQuery(BusinessPartner.class);
    entityQuery.addQueryParameter("userId",
                                  userId);
    BusinessPartner customer = appEngine.getFirst(entityQuery);
    if (customer == null) {
      throw new AppException("Unable to find Customer for the user : " + userId);
    }

    session.setUserId(customer.getId());
  }

  @Override
  public void userLoggedOut(Session session) {

  }

}
