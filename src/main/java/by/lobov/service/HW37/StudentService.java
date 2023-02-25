package by.lobov.service.HW37;

import by.lobov.entity.HW37.Address;
import by.lobov.entity.HW37.Course;
import by.lobov.entity.HW37.Student;
import by.lobov.repository.HW37.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;


    public Student save(Student student) {
        if (student.getCourse() == null) {
            throw new NullPointerException("Student cannot not have course");
        } else if (student.getAddress() == null) {
            throw new NullPointerException("Student cannot not have address");
        }
        return studentRepository.save(student);
    }

    public List<Student> init() {
        Student firstStudent = new Student();
        firstStudent.setName("Dima");

        Address firstAddress = new Address();
        firstAddress.setAddress("Minsk");

        firstStudent.setAddress(firstAddress);

        Student secondStudent = new Student();
        secondStudent.setName("Sasha");

        Address secondAddress = new Address();
        secondAddress.setAddress("Brest");

        secondStudent.setAddress(secondAddress);

        Course course = new Course();
        course.setCountOfStudents(30);
        course.setSpecialization("IT");

        course.addStudentsInCourse(firstStudent);

        course.addStudentsInCourse(secondStudent);
        studentRepository.save(firstStudent);
        studentRepository.save(secondStudent);
        log.info("Saved firstStudent: {}", firstStudent);
        log.info("Saved secondStudent: {}", secondStudent);
        return studentRepository.findAll();
    }
}
