package i2par.campus.placement.interview;

import app.domain.AppEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name="cp_interview_round")
public class InterviewRound extends AppEntity {


    @Column(name="rfp_response_id", nullable=false)
    private Long rfpResponseId;

    @OneToOne
    @JoinColumn(name="interview_type_id", nullable=false)
    private app.domain.PropertyGroup interviewType;

    @Column(name="description", nullable=false)
    private java.lang.String description;

    @Column(name="expected_date", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date expectedDate;

    @Column(name="duration", nullable=false)
    private Integer duration;

    public Long getRfpResponseId() {
        
        return rfpResponseId;
    }

    public void setRfpResponseId(Long rfpResponseId) {

        this.rfpResponseId = rfpResponseId;
    }

    public app.domain.PropertyGroup getInterviewType() {
        
        return interviewType;
    }

    public void setInterviewType(app.domain.PropertyGroup interviewType) {

        this.interviewType = interviewType;
    }

    public java.lang.String getDescription() {
        
        return description;
    }

    public void setDescription(java.lang.String description) {

        this.description = description;
    }

    public java.util.Date getExpectedDate() {
        
        return expectedDate;
    }

    public void setExpectedDate(java.util.Date expectedDate) {

        this.expectedDate = expectedDate;
    }

    public Integer getDuration() {
        
        return duration;
    }

    public void setDuration(Integer duration) {

        this.duration = duration;
    }
}
