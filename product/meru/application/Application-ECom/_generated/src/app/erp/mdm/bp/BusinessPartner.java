package app.erp.mdm.bp;

import app.domain.AppEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name="bp_business_partner")
public class BusinessPartner extends AppEntity {


    @Column(name="name")
    private java.lang.String name;

    @OneToOne
    @JoinColumn(name="group_id")
    private app.erp.mdm.bp.BusinessPartnerGroup group;

    @Column(name="email")
    private java.lang.String email;

    @Column(name="mobile")
    private java.lang.String mobile;

    @Column(name="alt_mobile")
    private java.lang.String altMobile;

    @Column(name="landline")
    private java.lang.String landline;

    @OneToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="address_id")
    private app.domain.location.Address address;

    @Column(name="user_id", nullable=false)
    private Long userId;

    public java.lang.String getName() {
        
        return name;
    }

    public void setName(java.lang.String name) {

        this.name = name;
    }

    public app.erp.mdm.bp.BusinessPartnerGroup getGroup() {
        
        return group;
    }

    public void setGroup(app.erp.mdm.bp.BusinessPartnerGroup group) {

        this.group = group;
    }

    public java.lang.String getEmail() {
        
        return email;
    }

    public void setEmail(java.lang.String email) {

        this.email = email;
    }

    public java.lang.String getMobile() {
        
        return mobile;
    }

    public void setMobile(java.lang.String mobile) {

        this.mobile = mobile;
    }

    public java.lang.String getAltMobile() {
        
        return altMobile;
    }

    public void setAltMobile(java.lang.String altMobile) {

        this.altMobile = altMobile;
    }

    public java.lang.String getLandline() {
        
        return landline;
    }

    public void setLandline(java.lang.String landline) {

        this.landline = landline;
    }

    public app.domain.location.Address getAddress() {

        if (address == null) {
        }
        
        return address;
    }

    public void setAddress(app.domain.location.Address address) {

        this.address = address;
    }

    public Long getUserId() {
        
        return userId;
    }

    public void setUserId(Long userId) {

        this.userId = userId;
    }

    public String toString() {
        return name;
    }
}
