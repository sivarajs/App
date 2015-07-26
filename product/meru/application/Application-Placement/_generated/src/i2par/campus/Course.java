package i2par.campus;

import app.domain.AppEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name="cp_course")
public class Course extends AppEntity {


    @Column(name="campus_id", nullable=false)
    private Long campusId;

    @OneToOne
    @JoinColumn(name="degree_id", nullable=false)
    private app.domain.PropertyGroup degree;

    @OneToOne
    @JoinColumn(name="discipline_id", nullable=false)
    private app.domain.PropertyGroup discipline;

    @OneToOne
    @JoinColumn(name="education_type_id", nullable=false)
    private app.domain.PropertyGroup educationType;

    @Column(name="duration", nullable=false)
    private Integer duration;

    @OneToOne
    @JoinColumn(name="time_unit_id", nullable=false)
    private app.domain.PropertyGroup timeUnit;

    public Long getCampusId() {
        
        return campusId;
    }

    public void setCampusId(Long campusId) {

        this.campusId = campusId;
    }

    public app.domain.PropertyGroup getDegree() {
        
        return degree;
    }

    public void setDegree(app.domain.PropertyGroup degree) {

        this.degree = degree;
    }

    public app.domain.PropertyGroup getDiscipline() {
        
        return discipline;
    }

    public void setDiscipline(app.domain.PropertyGroup discipline) {

        this.discipline = discipline;
    }

    public app.domain.PropertyGroup getEducationType() {
        
        return educationType;
    }

    public void setEducationType(app.domain.PropertyGroup educationType) {

        this.educationType = educationType;
    }

    public Integer getDuration() {
        
        return duration;
    }

    public void setDuration(Integer duration) {

        this.duration = duration;
    }

    public app.domain.PropertyGroup getTimeUnit() {
        
        return timeUnit;
    }

    public void setTimeUnit(app.domain.PropertyGroup timeUnit) {

        this.timeUnit = timeUnit;
    }
}
