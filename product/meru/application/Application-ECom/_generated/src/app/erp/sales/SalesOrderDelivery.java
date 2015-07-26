package app.erp.sales;

import app.domain.AppEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name="sales_sales_order_delivery")
public class SalesOrderDelivery extends AppEntity {


    @OneToOne
    @JoinColumn(name="address_id", nullable=false)
    private app.erp.mdm.bp.BusinessPartnerAddress address;

    @Column(name="date", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date date;

    @Column(name="code")
    private java.lang.String code;

    public app.erp.mdm.bp.BusinessPartnerAddress getAddress() {
        
        return address;
    }

    public void setAddress(app.erp.mdm.bp.BusinessPartnerAddress address) {

        this.address = address;
    }

    public java.util.Date getDate() {
        
        return date;
    }

    public void setDate(java.util.Date date) {

        this.date = date;
    }

    public java.lang.String getCode() {
        
        return code;
    }

    public void setCode(java.lang.String code) {

        this.code = code;
    }
}
