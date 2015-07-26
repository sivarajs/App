package i2par.placement.campus;

public class InterviewRoundRunStatus {

    private long employerInterviewId;
    private int totalStudents;
    private int studentsAppeared;
    private String status;

    public InterviewRoundRunStatus() {

    }

    public InterviewRoundRunStatus(int totalStudents,
                                   int studentsAppeared,
                                   String status) {
        this.totalStudents = totalStudents;
        this.studentsAppeared = studentsAppeared;
        this.status = status;
    }

    public long getEmployerInterviewId() {
        return employerInterviewId;
    }

    public void setEmployerInterviewId(long employerInterviewId) {
        this.employerInterviewId = employerInterviewId;
    }

    public int getTotalStudents() {
        return totalStudents;
    }

    public int getStudentsAppeared() {
        return studentsAppeared;
    }

    public String getStatus() {
        return status;
    }

}
