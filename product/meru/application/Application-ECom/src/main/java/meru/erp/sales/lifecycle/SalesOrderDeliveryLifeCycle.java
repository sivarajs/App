package meru.erp.sales.lifecycle;

import app.erp.sales.SalesOrderDelivery;
import meru.app.engine.entity.AbstractEntityLifeCycle;
import meru.sys.SystemCalendar;

public class SalesOrderDeliveryLifeCycle
        extends AbstractEntityLifeCycle<SalesOrderDelivery> {


    private SystemCalendar mSystemCalendar = SystemCalendar.getInstance();

    @Override
    public boolean preCreate(SalesOrderDelivery salesOrderDelivery) {

        salesOrderDelivery.setDate(mSystemCalendar.getUTCCalendar()
                                                  .getTime());
        return true;

    }


}
