package meru.ecom;

import meru.ecom.store.SessionShoppingCart;

public class EComEntityClassRegistry
        extends app.Application_EComEntityClassRegistry {
    protected void populateClassMap() {
        super.populateClassMap();
        addResourceClass("SessionShoppingCart",
                         SessionShoppingCart.class);
    }
}
