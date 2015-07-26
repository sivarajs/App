package meru.erp.mdm.catalog.lifecycle;

import meru.app.engine.entity.AbstractEntityLifeCycle;
import app.erp.mdm.catalog.Product;
import app.erp.mdm.catalog.ProductImage;
import app.erp.mdm.catalog.ProductLineItem;

public class ProductLifeCycle extends AbstractEntityLifeCycle<Product> {

  private ProductLineItemLifeCycle mProductLineItemLifeCycle;

  @Override
  public void init() {
    super.init();
    mProductLineItemLifeCycle = appEngine.getEntityLifeCycle(ProductLineItem.class,
                                                             ProductLineItemLifeCycle.class);
  }

  public void onProductImage(ProductImage productImage) {

    Product product = appEngine.get(Product.class,
                                    productImage.getEntityId());
    product.setTnImage(productImage.getImage());
    appEngine.save(product);

    mProductLineItemLifeCycle.onProductImage(productImage);

  }

}
