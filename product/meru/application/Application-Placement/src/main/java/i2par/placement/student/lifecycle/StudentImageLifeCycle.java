package i2par.placement.student.lifecycle;

import i2par.campus.student.Student;
import i2par.placement.ApplicationEntityLifeCycle;
import i2par.placement.student.StudentImage;
import i2par.placement.student.StudentImageRepository;

public class StudentImageLifeCycle extends ApplicationEntityLifeCycle<StudentImage> {

    public StudentImageRepository imageRepository;

    @Override
    public void init() {
        imageRepository = new StudentImageRepository(this.appContext.getApplicationRoot(),
                                                     150,
                                                     150);
    }

    @Override
    public boolean preCreate(StudentImage image) {
        setCampusId(image);
        imageRepository.storeImage(image);
        return false;
    }

    @Override
    public boolean preModify(StudentImage image) {
        setCampusId(image);
        imageRepository.storeImage(image);
        return false;
    }

    @Override
    public boolean preDelete(Class<StudentImage> resourceClass,
                             Object id) {
        imageRepository.deleteImage((String) id);
        return false;
    }

    @Override
    public StudentImage preGet(Class<StudentImage> entityClass,
                               Object id) {
        
        StudentImage studentImage = new StudentImage();
        studentImage.setStudentId((Long) id);
        setCampusId(studentImage);
        String image = imageRepository.getStudentImageRelativePath(studentImage.getCampusId(), studentImage.getStudentId());
        studentImage.setImage(image);
        return studentImage;
    }

    private void setCampusId(StudentImage studentImage) {

        Student student = appEngine.get(Student.class,
                                        studentImage.getStudentId());
        if (student == null) {
            throw new RuntimeException("Unknown Student : " + studentImage.getStudentId());
        }
        studentImage.setCampusId(student.getCampusId());

    }
}
