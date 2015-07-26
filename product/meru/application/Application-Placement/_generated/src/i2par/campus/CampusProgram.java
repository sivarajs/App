package i2par.campus;

import app.domain.AppEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name="cp_campus_program")
public class CampusProgram extends AppEntity {


    @Column(name="campus_id", nullable=false)
    private Long campusId;

    @OneToOne
    @JoinColumn(name="program_id", nullable=false)
    private app.domain.PropertyGroup program;

    public Long getCampusId() {
        
        return campusId;
    }

    public void setCampusId(Long campusId) {

        this.campusId = campusId;
    }

    public app.domain.PropertyGroup getProgram() {
        
        return program;
    }

    public void setProgram(app.domain.PropertyGroup program) {

        this.program = program;
    }
}
