package meru.ecom.store.lifecycle;

import java.util.ArrayList;
import java.util.List;

import app.ecom.shopping.cart.ShoppingCart;
import app.ecom.shopping.cart.ShoppingCartLineItem;
import app.erp.mdm.catalog.ProductLineItem;
import meru.app.engine.entity.AbstractEntityLifeCycle;
import meru.ecom.store.SessionShoppingCart;
import meru.exception.AppEntityWarning;
import meru.persistence.EntityQuery;

public class ShoppingCartLineItemLifeCycle extends AbstractEntityLifeCycle<ShoppingCartLineItem> {

  private ShoppingCartLifeCycle mShoppingCartLifeCycle;

  @Override
  public void init() {
    mShoppingCartLifeCycle = appEngine.getEntityLifeCycle(ShoppingCart.class,
                                                          ShoppingCartLifeCycle.class);
  }

  @Override
  public boolean preCreate(ShoppingCartLineItem cartItem) {

    ShoppingCart shoppingCart = mShoppingCartLifeCycle.getCurrentCart();

    ShoppingCartLineItem prevItem = null;

    if (shoppingCart == null) {

      shoppingCart = mShoppingCartLifeCycle.createShoppingCart();
      appEngine.save(shoppingCart);
    }
    else {

      EntityQuery<ShoppingCartLineItem> entityQuery = appEngine.createQuery(ShoppingCartLineItem.class);
      entityQuery.addQueryParameter("shoppingCartId",
                                    shoppingCart.getId());

      entityQuery.addQueryParameter("productLineItem.id",
                                    cartItem.getProductLineItem()
                                            .getId());

      prevItem = appEngine.getFirst(entityQuery);

    }

    if (prevItem == null) {

      if (cartItem.getQuantity() != 0) {

        ProductLineItem productItem = appEngine.get(ProductLineItem.class,
                                                    cartItem.getProductLineItem()
                                                            .getId());
        cartItem.setProductLineItem(productItem);
        cartItem.setShoppingCartId(shoppingCart.getId());
        setTotalPrice(cartItem);
      }

    }
    else {

      String code = null;
      Object result = null;
      int currQty = cartItem.getQuantity();
      if (currQty == 0) {

        result = appEngine.remove(ShoppingCartLineItem.class,
                                  prevItem.getId());
        code = "Deleted";

      }
      else if (currQty != prevItem.getQuantity()) {

        prevItem.setQuantity(cartItem.getQuantity());
        setTotalPrice(prevItem);
        result = appEngine.save(prevItem);
        code = "Modified";
      }
      else {
        code = "NotModified";
      }

      throw new AppEntityWarning(code,
                                 result);
    }

    return true;
  }

  @Override
  public boolean preModify(ShoppingCartLineItem cartItem) {
    ShoppingCartLineItem cartLineItem = appEngine.get(ShoppingCartLineItem.class,
                                                      cartItem.getId());
    if (cartLineItem == null) {
      return preCreate(cartItem);
    }

    setTotalPrice(cartLineItem);

    return true;
  }

  @Override
  public Object postCreate(ShoppingCartLineItem cartItem) {
    return postCreateOrModify(cartItem);
  }

  @Override
  public Object postModify(ShoppingCartLineItem cartItem) {
    return postCreateOrModify(cartItem);
  }

  @Override
  public Object postDelete(ShoppingCartLineItem cartItem) {
    return postCreateOrModify(cartItem);
  }

  @Override
  public List<ShoppingCartLineItem> preGet(EntityQuery<ShoppingCartLineItem> entityQuery) {

    if (entityQuery.hasQueryParameter("shoppingCartId")) {
      return null;
    }

    ShoppingCart shoppingCart = mShoppingCartLifeCycle.getCurrentCart();
    if (shoppingCart != null) {
      entityQuery.addQueryParameter("shoppingCartId",
                                    shoppingCart.getId());
    }
    else {
      return new ArrayList<ShoppingCartLineItem>(0);
    }

    return null;
  }

  private Object postCreateOrModify(ShoppingCartLineItem cartItem) {

    SessionShoppingCart shoppingBag = mShoppingCartLifeCycle.getCurrentSessionShoppingCart();

    // String message = mShoppingAdvisor.advice(shoppingBag);
    //
    // if (message != null) {
    // result.setMessage(message);
    // }

    return shoppingBag;
  }

  private static void setTotalPrice(ShoppingCartLineItem cartItem) {
    cartItem.setTotalPrice(cartItem.getQuantity()
        * cartItem.getProductLineItem()
                  .getPrice());
  }
}
