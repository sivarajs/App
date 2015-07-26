package app.ecom.subscription;

import app.domain.AppEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name="subs_subscription_frequency")
public class SubscriptionFrequency extends AppEntity {


    @OneToOne
    @JoinColumn(name="frequency_id", nullable=false)
    private app.domain.PropertyGroup frequency;

    @Column(name="info")
    private java.lang.String info;

    public app.domain.PropertyGroup getFrequency() {
        
        return frequency;
    }

    public void setFrequency(app.domain.PropertyGroup frequency) {

        this.frequency = frequency;
    }

    public java.lang.String getInfo() {
        
        return info;
    }

    public void setInfo(java.lang.String info) {

        this.info = info;
    }
}
