package app.erp.mdm.catalog;

import app.domain.AppEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name="mdm_product")
public class Product extends AppEntity {


    @Column(name="name", nullable=false)
    private java.lang.String name;

    @Column(name="uid")
    private java.lang.String uid;

    @Column(name="brand")
    private java.lang.String brand;

    @OneToOne
    @JoinColumn(name="product_category_id", nullable=false)
    private app.erp.mdm.catalog.ProductCategory productCategory;

    @Column(name="short_description")
    private java.lang.String shortDescription;

    @Column(name="description")
    private java.lang.String description;

    @Column(name="rating")
    private Integer rating;

    @Column(name="is_active")
    private String isActive = "N";
    private transient boolean isActiveBoolean;

    @Column(name="tags")
    private java.lang.String tags;

    @Column(name="tn_image")
    private java.lang.String tnImage;

    @OneToOne
    @JoinColumn(name="tax_id")
    private app.erp.finance.Tax tax;

    public java.lang.String getName() {
        
        return name;
    }

    public void setName(java.lang.String name) {

        this.name = name;
    }

    public java.lang.String getUid() {
        
        return uid;
    }

    public void setUid(java.lang.String uid) {

        this.uid = uid;
    }

    public java.lang.String getBrand() {
        
        return brand;
    }

    public void setBrand(java.lang.String brand) {

        this.brand = brand;
    }

    public app.erp.mdm.catalog.ProductCategory getProductCategory() {
        
        return productCategory;
    }

    public void setProductCategory(app.erp.mdm.catalog.ProductCategory productCategory) {

        this.productCategory = productCategory;
    }

    public java.lang.String getShortDescription() {
        
        return shortDescription;
    }

    public void setShortDescription(java.lang.String shortDescription) {

        this.shortDescription = shortDescription;
    }

    public java.lang.String getDescription() {
        
        return description;
    }

    public void setDescription(java.lang.String description) {

        this.description = description;
    }

    public Integer getRating() {
        
        return rating;
    }

    public void setRating(Integer rating) {

        this.rating = rating;
    }

    public java.lang.String getIsActive() {
        
        return isActive;
    }

    public void setIsActive(java.lang.String isActive) {

        this.isActive = isActive;
    }

    public boolean isActive() {

        return "Y".equals(isActive);
    }

    public Boolean getIsActiveBoolean() {
        
        return isActive != null && isActive.equals("Y");
    }

    public void setIsActiveBoolean(Boolean isActiveBoolean) {

        isActive = isActiveBoolean ? "Y" : "N";
    }

    public boolean isActiveBoolean() {

        return "Y".equals(isActiveBoolean);
    }

    public java.lang.String getTags() {
        
        return tags;
    }

    public void setTags(java.lang.String tags) {

        this.tags = tags;
    }

    public java.lang.String getTnImage() {
        
        return tnImage;
    }

    public void setTnImage(java.lang.String tnImage) {

        this.tnImage = tnImage;
    }

    public app.erp.finance.Tax getTax() {
        
        return tax;
    }

    public void setTax(app.erp.finance.Tax tax) {

        this.tax = tax;
    }

    public String toString() {
        return name;
    }
}
