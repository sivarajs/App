package app.domain;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AppEntity {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private Long id;

    @Column(name="app_id", nullable=false)
    private Long appId;

    @Column(name="client_id", nullable=false)
    private Long clientId;

    public Long getId() {
        
        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Long getAppId() {
        
        return appId;
    }

    public void setAppId(Long appId) {

        this.appId = appId;
    }

    public Long getClientId() {
        
        return clientId;
    }

    public void setClientId(Long clientId) {

        this.clientId = clientId;
    }
}
