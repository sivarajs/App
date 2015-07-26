package i2par.placement.student;

public class StudentImage {

    private String id;
    private long campusId;
    private long studentId;
    private String image;

    public StudentImage() {

    }

    public StudentImage(String id,
                        long campusId,
                        long studentId,
                        String image) {
        this.id = id;
        this.campusId = campusId;
        this.studentId = studentId;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCampusId() {
        return campusId;
    }

    public void setCampusId(long campusId) {
        this.campusId = campusId;
    }
    
    public long getStudentId() {
        return studentId;
    }
    
    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
