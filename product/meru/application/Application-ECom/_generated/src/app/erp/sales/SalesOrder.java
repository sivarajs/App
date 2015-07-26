package app.erp.sales;

import app.domain.AppEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name="sales_sales_order")
public class SalesOrder extends AppEntity {


    @Column(name="order_id")
    private java.lang.String orderId;

    @Column(name="transaction_id")
    private java.lang.String transactionId;

    @OneToOne
    @JoinColumn(name="business_partner_id", nullable=false)
    private app.erp.mdm.bp.BusinessPartner businessPartner;

    @Column(name="session_id", nullable=false)
    private java.lang.String sessionId;

    @Column(name="placed_on", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Calendar placedOn;

    @OneToOne
    @JoinColumn(name="state_id", nullable=false)
    private app.domain.AppEntityState state;

    @OneToOne
    @JoinColumn(name="payment_method_id", nullable=false)
    private app.erp.finance.PaymentMethod paymentMethod;

    @Column(name="amount", nullable=false)
    private Float amount;

    @Column(name="notes")
    private java.lang.String notes;

    @Column(name="flexi_items")
    private java.lang.String flexiItems;

    @OneToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="delivery_id")
    private app.erp.sales.SalesOrderDelivery delivery;

    public java.lang.String getOrderId() {
        
        return orderId;
    }

    public void setOrderId(java.lang.String orderId) {

        this.orderId = orderId;
    }

    public java.lang.String getTransactionId() {
        
        return transactionId;
    }

    public void setTransactionId(java.lang.String transactionId) {

        this.transactionId = transactionId;
    }

    public app.erp.mdm.bp.BusinessPartner getBusinessPartner() {
        
        return businessPartner;
    }

    public void setBusinessPartner(app.erp.mdm.bp.BusinessPartner businessPartner) {

        this.businessPartner = businessPartner;
    }

    public java.lang.String getSessionId() {
        
        return sessionId;
    }

    public void setSessionId(java.lang.String sessionId) {

        this.sessionId = sessionId;
    }

    public java.util.Calendar getPlacedOn() {
        
        return placedOn;
    }

    public void setPlacedOn(java.util.Calendar placedOn) {

        this.placedOn = placedOn;
    }

    public app.domain.AppEntityState getState() {
        
        return state;
    }

    public void setState(app.domain.AppEntityState state) {

        this.state = state;
    }

    public app.erp.finance.PaymentMethod getPaymentMethod() {
        
        return paymentMethod;
    }

    public void setPaymentMethod(app.erp.finance.PaymentMethod paymentMethod) {

        this.paymentMethod = paymentMethod;
    }

    public Float getAmount() {
        
        return amount;
    }

    public void setAmount(Float amount) {

        this.amount = amount;
    }

    public java.lang.String getNotes() {
        
        return notes;
    }

    public void setNotes(java.lang.String notes) {

        this.notes = notes;
    }

    public java.lang.String getFlexiItems() {
        
        return flexiItems;
    }

    public void setFlexiItems(java.lang.String flexiItems) {

        this.flexiItems = flexiItems;
    }

    public app.erp.sales.SalesOrderDelivery getDelivery() {

        if (delivery == null) {
        }
        
        return delivery;
    }

    public void setDelivery(app.erp.sales.SalesOrderDelivery delivery) {

        this.delivery = delivery;
    }
}
