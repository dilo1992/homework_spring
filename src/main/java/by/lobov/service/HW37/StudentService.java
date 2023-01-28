package by.lobov.service.HW37;

import by.lobov.entity.HW37.Student;
import by.lobov.repository.HW37.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StudentService {

    private StudentRepository studentRepository;

    public Student save(Student student) {
        if (student.getCourse() == null) {
            throw new NullPointerException("Student cannot not have course");
        } else if (student.getAddress() == null) {
            throw new NullPointerException("Student cannot not have address");
        }
        return studentRepository.save(student);
    }
}
