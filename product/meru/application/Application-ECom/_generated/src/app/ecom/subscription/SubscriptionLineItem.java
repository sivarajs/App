package app.ecom.subscription;

import app.domain.AppEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name="subs_subscription_line_item")
public class SubscriptionLineItem extends AppEntity {


    @Column(name="subscription_id", nullable=false)
    private Long subscriptionId;

    @OneToOne
    @JoinColumn(name="product_line_item_id", nullable=false)
    private app.erp.mdm.catalog.ProductLineItem productLineItem;

    @Column(name="quantity", nullable=false)
    private Integer quantity;

    public Long getSubscriptionId() {
        
        return subscriptionId;
    }

    public void setSubscriptionId(Long subscriptionId) {

        this.subscriptionId = subscriptionId;
    }

    public app.erp.mdm.catalog.ProductLineItem getProductLineItem() {
        
        return productLineItem;
    }

    public void setProductLineItem(app.erp.mdm.catalog.ProductLineItem productLineItem) {

        this.productLineItem = productLineItem;
    }

    public Integer getQuantity() {
        
        return quantity;
    }

    public void setQuantity(Integer quantity) {

        this.quantity = quantity;
    }
}
