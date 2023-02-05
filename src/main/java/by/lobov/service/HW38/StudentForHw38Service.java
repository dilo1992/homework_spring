package by.lobov.service.HW38;

import by.lobov.entity.HW38.StudentForHw38;
import by.lobov.repository.HW38.StudentForHw38Repository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StudentForHw38Service {

    private StudentForHw38Repository studentRepository;

    public StudentForHw38 save(StudentForHw38 student) {
        if (student.getCourse() == null) {
            throw new NullPointerException("Student cannot not have course");
        }
        return studentRepository.save(student);
    }

    public StudentForHw38 findById(Long id) {
        return studentRepository.findById(id).orElseThrow();
    }

    public List<StudentForHw38> findByCourse(String course) {
        return studentRepository.findByCourse(course);
    }

    public List<StudentForHw38> findAll() {
        return studentRepository.findAll();
    }

    public void delete(Long id) {
        studentRepository.delete(id);
    }
}
