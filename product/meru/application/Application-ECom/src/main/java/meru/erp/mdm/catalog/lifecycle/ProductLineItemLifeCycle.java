package meru.erp.mdm.catalog.lifecycle;

import java.text.DecimalFormat;
import java.util.List;

import meru.app.engine.entity.AbstractEntityLifeCycle;
import meru.erp.mdm.catalog.ProductLineItemPriceProvider;
import meru.image.ImageSize;
import meru.image.repository.ImageRepository;
import meru.persistence.EntityQuery;
import app.domain.EntityFeatureValue;
import app.domain.Property;
import app.erp.finance.Tax;
import app.erp.mdm.catalog.Product;
import app.erp.mdm.catalog.ProductCategory;
import app.erp.mdm.catalog.ProductImage;
import app.erp.mdm.catalog.ProductLineItem;
import app.erp.mdm.catalog.ProductLineItemImage;

public class ProductLineItemLifeCycle extends AbstractEntityLifeCycle<ProductLineItem> {

  public DecimalFormat priceFormat;
  private ProductLineItemPriceProvider productLineItemPriceProvider;

  @Override
  public void init() {
//    String format = appConfig.getMandatoryProperty("app.config.price-format");
//    priceFormat = new DecimalFormat(format);
/*    String priceProvider = appConfig.getProperty("app.ecom.price.provider");
    if (priceProvider != null) {
      try {
        productLineItemPriceProvider = (ProductLineItemPriceProvider) Class.forName(priceProvider)
                                                                           .newInstance();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
*/  }

  @Override
  public Class<?>[] observeableEntities() {
    return new Class<?>[] { Property.class, EntityFeatureValue.class };
  }

  @Override
  public boolean preCreate(ProductLineItem productItem) {

    updateProductLineItem(productItem);
    return true;
  }

  @Override
  public boolean preModify(ProductLineItem productItem) {
    updateProductLineItem(productItem);
    return true;
  }

  @Override
  public void onObserveableEntityCreate(Object entity) {

  }

  @Override
  public void onObserveableEntityModify(Object entity) {
    
    boolean isTrue = false;
    
    if (entity instanceof Property) {
      Property property = (Property)entity;
      if (property.getName().equals("price.silver")) {
        isTrue = true;
      }
    }
    else if (entity instanceof EntityFeatureValue) {
      isTrue = true;
    }
    
    if (isTrue) {
      EntityQuery<ProductLineItem> entityQuery = appEngine.createQuery(ProductLineItem.class);
      List<ProductLineItem> productLineItems = appEngine.get(entityQuery);
      for (ProductLineItem productLineItem : productLineItems) {
        updateProductLineItem(productLineItem);
      }
      
    }

  }

  public void onProductImage(ProductImage productImage) {
    updateTnImage(productImage.getEntityId(),
                  ImageRepository.getImagePath(productImage.getImage(),
                                               ImageSize.Size150x150));
  }

  public void onProductLineItemImage(ProductLineItemImage productLineItemImage) {

    updateTnImage(productLineItemImage.getProductId(),
                  ImageRepository.getImagePath(productLineItemImage.getImage(),
                                               ImageSize.Size150x150));
  }

  private void updateTnImage(Long productId,
                             String image) {
    EntityQuery<ProductLineItem> entityQuery = appEngine.createQuery(ProductLineItem.class);
    entityQuery.addQueryParameter("product.id",
                                  productId);
    List<ProductLineItem> productLineItems = appEngine.get(entityQuery);

    for (ProductLineItem productLineItem : productLineItems) {
      productLineItem.setTnImage(image);
      appEngine.save(productLineItem);
    }
  }

  private void updateProductLineItem(ProductLineItem productItem) {
    Product product = appEngine.get(Product.class,
                                    productItem.getProduct()
                                               .getId());
    productItem.setProduct(product);
    Float discount = productItem.getDiscount();

    float mrp = 0;
    if (productLineItemPriceProvider == null) {
      mrp = productItem.getMrp();
    }
    else {
      mrp = productLineItemPriceProvider.getPrice(productItem,
                                                  getTax(productItem),
                                                  appEngine);
      productItem.setMrp(mrp);
    }

    if (discount == null) {
      productItem.setPrice(mrp);
      productItem.setSavings(null);
    }
    else {

      float price = mrp - (mrp * (discount / 100));
      price = Float.valueOf(priceFormat.format(price));
      productItem.setPrice(price);
      float savings = mrp - price;
      savings = price = Float.valueOf(priceFormat.format(savings));
      productItem.setSavings(savings);
    }

    productItem.setSearchTerms("temp");

    if (productItem.getTnImage() == null) {
      productItem.setTnImage(product.getTnImage());
    }

    // productItem.setSearchTerms(ProductIndexBuilder.getSearchTerm(productItem));
  }

  public Tax getTax(ProductLineItem productItem) {

    Tax tax = productItem.getProduct()
                         .getTax();

    if (tax == null) {

      ProductCategory category = productItem.getProduct()
                                            .getProductCategory();
      tax = category.getTax();

      while (tax == null) {

        if (category.getParentId() == null) {
          break;
        }

        category = appEngine.get(ProductCategory.class,
                                 category.getParentId());
        tax = category.getTax();
      }

    }

    return tax;
  }
}
