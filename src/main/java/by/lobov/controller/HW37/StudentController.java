package by.lobov.controller.HW37;

import by.lobov.entity.HW37.Address;
import by.lobov.entity.HW37.Course;
import by.lobov.entity.HW37.Student;
import by.lobov.repository.HW37.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("studentsHW37")
@RequiredArgsConstructor
public class StudentController {

    public final StudentRepository repository;

    @GetMapping
    public String homework37() {

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
        repository.save(firstStudent);
        repository.save(secondStudent);
        log.info("Saved firstStudent: {}", firstStudent);
        log.info("Saved secondStudent: {}", secondStudent);
        return "index";
    }
}
