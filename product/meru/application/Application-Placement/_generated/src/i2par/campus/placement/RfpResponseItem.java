package i2par.campus.placement;

import app.domain.AppEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name="cp_rfp_response_item")
public class RfpResponseItem extends AppEntity {


    @Column(name="rfp_response_id", nullable=false)
    private Long rfpResponseId;

    @OneToOne
    @JoinColumn(name="course_placement_item_id", nullable=false)
    private i2par.campus.placement.CoursePlacementItem coursePlacementItem;

    @Column(name="is_eligible")
    private String isEligible = "N";
    private transient boolean isEligibleBoolean;

    @Column(name="accepted_pay")
    private Float acceptedPay;

    public Long getRfpResponseId() {
        
        return rfpResponseId;
    }

    public void setRfpResponseId(Long rfpResponseId) {

        this.rfpResponseId = rfpResponseId;
    }

    public i2par.campus.placement.CoursePlacementItem getCoursePlacementItem() {
        
        return coursePlacementItem;
    }

    public void setCoursePlacementItem(i2par.campus.placement.CoursePlacementItem coursePlacementItem) {

        this.coursePlacementItem = coursePlacementItem;
    }

    public java.lang.String getIsEligible() {
        
        return isEligible;
    }

    public void setIsEligible(java.lang.String isEligible) {

        this.isEligible = isEligible;
    }

    public boolean isEligible() {

        return "Y".equals(isEligible);
    }

    public Boolean getIsEligibleBoolean() {
        
        return isEligible != null && isEligible.equals("Y");
    }

    public void setIsEligibleBoolean(Boolean isEligibleBoolean) {

        isEligible = isEligibleBoolean ? "Y" : "N";
    }

    public boolean isEligibleBoolean() {

        return "Y".equals(isEligibleBoolean);
    }

    public Float getAcceptedPay() {
        
        return acceptedPay;
    }

    public void setAcceptedPay(Float acceptedPay) {

        this.acceptedPay = acceptedPay;
    }
}
