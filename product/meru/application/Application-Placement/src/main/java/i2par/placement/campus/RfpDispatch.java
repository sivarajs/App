package i2par.placement.campus;

public class RfpDispatch {

    private Long id;
    private Long rfpId;
    private String employerIds;
    private String emails;

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getRfpId() {
        return rfpId;
    }

    public void setRfpId(Long rfpId) {
        this.rfpId = rfpId;
    }

    public String getEmployerIds() {
        return employerIds;
    }

    public void setEmployerIds(String employerIds) {
        this.employerIds = employerIds;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

}
