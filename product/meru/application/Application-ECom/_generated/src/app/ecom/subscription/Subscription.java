package app.ecom.subscription;

import app.domain.AppEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name="subs_subscription")
public class Subscription extends AppEntity {


    @OneToOne
    @JoinColumn(name="business_partner_id", nullable=false)
    private app.erp.mdm.bp.BusinessPartner businessPartner;

    @OneToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="frequency_id", nullable=false)
    private app.ecom.subscription.SubscriptionFrequency frequency;

    @OneToMany(mappedBy="shoppingCartId", cascade={CascadeType.ALL})
    private java.util.List<app.ecom.subscription.SubscriptionLineItem> items;

    @Column(name="notes")
    private java.lang.String notes;

    public app.erp.mdm.bp.BusinessPartner getBusinessPartner() {
        
        return businessPartner;
    }

    public void setBusinessPartner(app.erp.mdm.bp.BusinessPartner businessPartner) {

        this.businessPartner = businessPartner;
    }

    public app.ecom.subscription.SubscriptionFrequency getFrequency() {

        if (frequency == null) {
        }
        
        return frequency;
    }

    public void setFrequency(app.ecom.subscription.SubscriptionFrequency frequency) {

        this.frequency = frequency;
    }

    public java.util.List<app.ecom.subscription.SubscriptionLineItem> getItems() {

        if (items == null) {
            this.items = new java.util.ArrayList<app.ecom.subscription.SubscriptionLineItem>();
        }
        
        return items;
    }

    public void setItems(java.util.List<app.ecom.subscription.SubscriptionLineItem> items) {

        this.items = items;
    }

    public java.lang.String getNotes() {
        
        return notes;
    }

    public void setNotes(java.lang.String notes) {

        this.notes = notes;
    }
}
