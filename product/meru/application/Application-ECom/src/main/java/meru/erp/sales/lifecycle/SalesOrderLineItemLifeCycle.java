package meru.erp.sales.lifecycle;

import meru.app.engine.entity.AbstractEntityLifeCycle;
import meru.erp.mdm.catalog.lifecycle.ProductLineItemLifeCycle;
import meru.erp.sales.SalesOrderBag;
import app.domain.PropertyGroup;
import app.erp.finance.Tax;
import app.erp.marketing.SalesOffer;
import app.erp.mdm.catalog.ProductLineItem;
import app.erp.sales.SalesOrderLineItem;

public class SalesOrderLineItemLifeCycle extends
    AbstractEntityLifeCycle<SalesOrderLineItem> {

  private ProductLineItemLifeCycle mProductLineItemLifeCycle;

  @Override
  public void init() {
    mProductLineItemLifeCycle = appEngine.getEntityLifeCycle(ProductLineItem.class,
                                                             ProductLineItemLifeCycle.class);
  }

  public void popuplateSalesOrderLineItem(SalesOrderLineItem lineItem,
                                          SalesOffer salesOffer) {

    ProductLineItem productItem = lineItem.getProductLineItem();

    Float mrp = lineItem.getUnitMrp();
    if (mrp == null || mrp == 0) {
      lineItem.setUnitMrp(productItem.getMrp());

      Tax tax = mProductLineItemLifeCycle.getTax(productItem);
      if (tax == null) {
        lineItem.setTaxRate(0F);
      }
      else {
        lineItem.setTaxRate(tax.getRate());
      }
    }

    Float discount = null;
    PropertyGroup discountType = null;

    if (lineItem.getDiscount() == null) {

      if (salesOffer != null
          && salesOffer.getDiscount() > lineItem.getDiscount()) {
        discount = salesOffer.getDiscount();
        discountType = salesOffer.getDiscountType();
      }
      else {
        discount = productItem.getDiscount();
        discountType = productItem.getDiscountType();
      }

      lineItem.setDiscount(discount);
      lineItem.setDiscountType(discountType);
    }
    else {

      discount = lineItem.getDiscount();
      discountType = lineItem.getDiscountType();
    }

    float unitPrice = SalesOrderBag.getUnitPrice(lineItem.getUnitMrp(),
                                               discount,
                                               discountType);

    lineItem.setUnitPrice(unitPrice);

    float totalPrice = SalesOrderBag.getTotalPrice(lineItem.getUnitMrp(),
                                                 (lineItem.getNetQuantity() == null) ? lineItem.getQuantity()
                                                     : lineItem.getNetQuantity(),
                                                 lineItem.getDiscount(),
                                                 lineItem.getDiscountType());

    lineItem.setTotalPrice(totalPrice);

    if (lineItem.getTaxRate() == null || lineItem.getTaxRate() == -1) {

      Tax tax = mProductLineItemLifeCycle.getTax(productItem);
      if (tax == null) {
        lineItem.setTaxRate(0F);
      }
      else {
        lineItem.setTaxRate(tax.getRate());
      }

    }
  }

  @Override
  public boolean preCreate(SalesOrderLineItem lineItem) {
    popuplateSalesOrderLineItem(lineItem,
                                null);
    return true;
  }

  @Override
  public boolean preModify(SalesOrderLineItem lineItem) {

    popuplateSalesOrderLineItem(lineItem,
                                null);

    return true;
  }

}
