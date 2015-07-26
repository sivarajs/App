package app.erp.finance;

import app.domain.AppEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name="fin_tax")
public class Tax extends AppEntity {


    @Column(name="name", nullable=false)
    private java.lang.String name;

    @OneToOne
    @JoinColumn(name="tax_category_id", nullable=false)
    private app.erp.finance.TaxCategory taxCategory;

    @Column(name="rate", nullable=false)
    private Float rate;

    public java.lang.String getName() {
        
        return name;
    }

    public void setName(java.lang.String name) {

        this.name = name;
    }

    public app.erp.finance.TaxCategory getTaxCategory() {
        
        return taxCategory;
    }

    public void setTaxCategory(app.erp.finance.TaxCategory taxCategory) {

        this.taxCategory = taxCategory;
    }

    public Float getRate() {
        
        return rate;
    }

    public void setRate(Float rate) {

        this.rate = rate;
    }

    public String toString() {
        return name;
    }
}
