package i2par.campus;

import app.domain.AppEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name="cp_university")
public class University extends AppEntity {


    @Column(name="name", nullable=false)
    private java.lang.String name;

    @OneToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="address_id", nullable=false)
    private app.domain.location.Address address;

    public java.lang.String getName() {
        
        return name;
    }

    public void setName(java.lang.String name) {

        this.name = name;
    }

    public app.domain.location.Address getAddress() {

        if (address == null) {
        }
        
        return address;
    }

    public void setAddress(app.domain.location.Address address) {

        this.address = address;
    }

    public String toString() {
        return name;
    }
}
