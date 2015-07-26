package app.erp.finance;

import app.domain.AppEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name="fin_tax_category")
public class TaxCategory extends AppEntity implements app.entity.Nameable {


    @Column(name="name", nullable=false)
    private java.lang.String name;

    @OneToOne
    @JoinColumn(name="state_id", nullable=false)
    private app.domain.location.State state;

    public java.lang.String getName() {
        
        return name;
    }

    public void setName(java.lang.String name) {

        this.name = name;
    }

    public app.domain.location.State getState() {
        
        return state;
    }

    public void setState(app.domain.location.State state) {

        this.state = state;
    }

    public String toString() {
        return name;
    }
}
