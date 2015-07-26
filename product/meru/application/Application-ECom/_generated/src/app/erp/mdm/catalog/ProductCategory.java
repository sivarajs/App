package app.erp.mdm.catalog;

import app.domain.AppHierarchicalBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name="mdm_product_category")
public class ProductCategory extends AppHierarchicalBaseEntity implements app.entity.Hierarchical {


    @Column(name="sort_order")
    private Integer sortOrder;

    @Column(name="is_active")
    private String isActive = "N";
    private transient boolean isActiveBoolean;

    @Column(name="tags")
    private java.lang.String tags;

    @OneToOne
    @JoinColumn(name="tax_id")
    private app.erp.finance.Tax tax;

    public Integer getSortOrder() {
        
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {

        this.sortOrder = sortOrder;
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

    public app.erp.finance.Tax getTax() {
        
        return tax;
    }

    public void setTax(app.erp.finance.Tax tax) {

        this.tax = tax;
    }
}
