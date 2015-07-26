package meru.ecom.store.lifecycle;

import app.ecom.shopping.cart.ShoppingCart;
import meru.app.engine.entity.AbstractEntityLifeCycle;
import meru.ecom.store.SessionShoppingCart;

public class SessionShoppingCartLifeCycle extends AbstractEntityLifeCycle<SessionShoppingCart> {

  private ShoppingCartLifeCycle mShoppingCartLifeCycle;

  public void init() {
    mShoppingCartLifeCycle = appEngine.getEntityLifeCycle(ShoppingCart.class,
                                                          ShoppingCartLifeCycle.class);
  }

  @Override
  public boolean preCreate(SessionShoppingCart shoppingBag) {

    throw new UnsupportedOperationException();
  }

  @Override
  public final SessionShoppingCart preGet(Class<SessionShoppingCart> entityClass,
                                                        Object id) {

    SessionShoppingCart shoppingBag = mShoppingCartLifeCycle.getCurrentSessionShoppingCart();
    return shoppingBag;
  }

}
