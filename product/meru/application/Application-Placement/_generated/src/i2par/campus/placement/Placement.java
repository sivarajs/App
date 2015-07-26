package i2par.campus.placement;

import app.domain.AppEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name="cp_placement")
public class Placement extends AppEntity {


    @Column(name="campus_id", nullable=false)
    private Long campusId;

    @Column(name="year", nullable=false)
    private Integer year;

    @Column(name="academic_start", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date academicStart;

    @Column(name="academic_end", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date academicEnd;

    @Column(name="rollout_start", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date rolloutStart;

    @Column(name="rollout_end", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date rolloutEnd;

    @Column(name="commencement", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date commencement;

    @Column(name="joining_period", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date joiningPeriod;

    @OneToOne
    @JoinColumn(name="state_id", nullable=false)
    private app.domain.PropertyGroup state;

    public Long getCampusId() {
        
        return campusId;
    }

    public void setCampusId(Long campusId) {

        this.campusId = campusId;
    }

    public Integer getYear() {
        
        return year;
    }

    public void setYear(Integer year) {

        this.year = year;
    }

    public java.util.Date getAcademicStart() {
        
        return academicStart;
    }

    public void setAcademicStart(java.util.Date academicStart) {

        this.academicStart = academicStart;
    }

    public java.util.Date getAcademicEnd() {
        
        return academicEnd;
    }

    public void setAcademicEnd(java.util.Date academicEnd) {

        this.academicEnd = academicEnd;
    }

    public java.util.Date getRolloutStart() {
        
        return rolloutStart;
    }

    public void setRolloutStart(java.util.Date rolloutStart) {

        this.rolloutStart = rolloutStart;
    }

    public java.util.Date getRolloutEnd() {
        
        return rolloutEnd;
    }

    public void setRolloutEnd(java.util.Date rolloutEnd) {

        this.rolloutEnd = rolloutEnd;
    }

    public java.util.Date getCommencement() {
        
        return commencement;
    }

    public void setCommencement(java.util.Date commencement) {

        this.commencement = commencement;
    }

    public java.util.Date getJoiningPeriod() {
        
        return joiningPeriod;
    }

    public void setJoiningPeriod(java.util.Date joiningPeriod) {

        this.joiningPeriod = joiningPeriod;
    }

    public app.domain.PropertyGroup getState() {
        
        return state;
    }

    public void setState(app.domain.PropertyGroup state) {

        this.state = state;
    }
}
