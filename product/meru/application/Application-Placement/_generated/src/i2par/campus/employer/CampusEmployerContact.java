package i2par.campus.employer;

import app.domain.AppEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name="cp_campus_employer_contact")
public class CampusEmployerContact extends AppEntity {


    @Column(name="campus_employer_id", nullable=false)
    private Long campusEmployerId;

    @Column(name="first_name", nullable=false)
    private java.lang.String firstName;

    @Column(name="last_name", nullable=false)
    private java.lang.String lastName;

    @Column(name="email", nullable=false)
    private java.lang.String email;

    @Column(name="phone", nullable=false)
    private java.lang.String phone;

    @OneToOne
    @JoinColumn(name="type_id", nullable=false)
    private app.domain.PropertyGroup type;

    public Long getCampusEmployerId() {
        
        return campusEmployerId;
    }

    public void setCampusEmployerId(Long campusEmployerId) {

        this.campusEmployerId = campusEmployerId;
    }

    public java.lang.String getFirstName() {
        
        return firstName;
    }

    public void setFirstName(java.lang.String firstName) {

        this.firstName = firstName;
    }

    public java.lang.String getLastName() {
        
        return lastName;
    }

    public void setLastName(java.lang.String lastName) {

        this.lastName = lastName;
    }

    public java.lang.String getEmail() {
        
        return email;
    }

    public void setEmail(java.lang.String email) {

        this.email = email;
    }

    public java.lang.String getPhone() {
        
        return phone;
    }

    public void setPhone(java.lang.String phone) {

        this.phone = phone;
    }

    public app.domain.PropertyGroup getType() {
        
        return type;
    }

    public void setType(app.domain.PropertyGroup type) {

        this.type = type;
    }
}
