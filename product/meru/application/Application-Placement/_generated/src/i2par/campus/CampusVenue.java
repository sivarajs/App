package i2par.campus;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="cp_campus_venue")
public class CampusVenue implements app.entity.Nameable {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private Long id;

    @Column(name="campus_id", nullable=false)
    private Long campusId;

    @Column(name="name", nullable=false)
    private java.lang.String name;

    public Long getId() {
        
        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Long getCampusId() {
        
        return campusId;
    }

    public void setCampusId(Long campusId) {

        this.campusId = campusId;
    }

    public java.lang.String getName() {
        
        return name;
    }

    public void setName(java.lang.String name) {

        this.name = name;
    }

    public String toString() {
        return name;
    }
}
