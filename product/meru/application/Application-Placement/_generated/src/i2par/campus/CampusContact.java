package i2par.campus;

import app.domain.AppEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name="cp_campus_contact")
public class CampusContact extends AppEntity {


    @Column(name="name", nullable=false)
    private java.lang.String name;

    @Column(name="campus_id", nullable=false)
    private Long campusId;

    @OneToOne
    @JoinColumn(name="staff_type_id", nullable=false)
    private app.domain.PropertyGroup staffType;

    @Column(name="email", nullable=false)
    private java.lang.String email;

    @Column(name="phone", nullable=false)
    private java.lang.String phone;

    @Column(name="role_id", nullable=false)
    private Long roleId;

    @Column(name="role")
    private java.lang.String role;

    public java.lang.String getName() {
        
        return name;
    }

    public void setName(java.lang.String name) {

        this.name = name;
    }

    public Long getCampusId() {
        
        return campusId;
    }

    public void setCampusId(Long campusId) {

        this.campusId = campusId;
    }

    public app.domain.PropertyGroup getStaffType() {
        
        return staffType;
    }

    public void setStaffType(app.domain.PropertyGroup staffType) {

        this.staffType = staffType;
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

    public Long getRoleId() {
        
        return roleId;
    }

    public void setRoleId(Long roleId) {

        this.roleId = roleId;
    }

    public java.lang.String getRole() {
        
        return role;
    }

    public void setRole(java.lang.String role) {

        this.role = role;
    }

    public String toString() {
        return name;
    }
}
