package by.lobov.controller.HW38;


import by.lobov.entity.HW38.CountOfVisitHw38;
import by.lobov.entity.HW38.StudentForHw38;
import by.lobov.repository.HW38.StudentForHw38Repository;
import by.lobov.service.HW38.StudentForHw38Service;
import by.lobov.validator.HW38.CoursesForHw38;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Arrays;

@Slf4j
@Controller
@RequestMapping("studentsHW38")
@RequiredArgsConstructor
@SessionAttributes("student")
public class StudentForHw38Controller {

    public final StudentForHw38Service service;
    @Autowired
    private final CountOfVisitHw38 getCountOfVisit;

    @GetMapping
    public String getAllStudents(Model model) {

        getCountOfVisit.getIterableCount();
//        StudentForHw38 student1 = new StudentForHw38();
//        student1.setCourse("first");
//        student1.setName("Dima");
//
//        StudentForHw38 student2 = new StudentForHw38();
//        student2.setCourse("second");
//        student2.setName("Sasha");
//
//        StudentForHw38 student3 = new StudentForHw38();
//        student3.setCourse("first");
//        student3.setName("Vova");
//
//        repository.save(student1);
//        repository.save(student2);
//        repository.save(student3);
        model.addAttribute("allStudents", service.findAll());
        return "viewAllStudents";
    }

    @GetMapping("/course/{course}")
    public String getStudentsByCourse(@PathVariable("course") String course, Model model) {
        getCountOfVisit.getIterableCount();
        model.addAttribute("studentsOfTheSameCourse", service.findByCourse(course));
        return "viewStudentsOfTheSameCourse";
    }

    @GetMapping("/id/{id}")
    public String getStudentsById(@PathVariable("id") long id, Model model) {
        getCountOfVisit.getIterableCount();
        StudentForHw38 studentForHw38 = service.findById(id);
        log.info("A student with searched id: {}", studentForHw38);
        model.addAttribute("id", id);
        model.addAttribute("studentById", studentForHw38);
        return "findStudentById";
    }

//    @PostMapping("/create")
//    public String createUser(@Valid StudentForHw38 student, Errors errors, Model model, SessionStatus status) { //если есть ошибки они собираются в bindingResult
//        //из формы отправились параметры, и мы их достали (как в сервлете req.getParameter)
//        if (errors.hasErrors()) {
//            log.info("Student is incorrect: {}", errors.getAllErrors());
//            model.addAttribute("allStudents", service.findAll());
//            return "createStudent";
//        }
//        log.info("student is correct: {}", student);
//        service.save(new StudentForHw38(0L, student.getName(), student.getCourse()));
//        model.addAttribute("allStudents", service.findAll());
//        model.addAttribute("listOfCourses", Arrays.stream(CoursesForHw38.values()).toList());
//
//        status.setComplete();
//
//        student.setName("");
//        student.setCourse("");
//
//        return "createStudent";
//    }
//
//    //    для связи нового user с нашей формой
//    @ModelAttribute(name = "student")
//    public StudentForHw38 getNewStudent() {
//        return new StudentForHw38();
//    }
}
