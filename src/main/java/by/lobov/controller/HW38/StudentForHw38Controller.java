package by.lobov.controller.HW38;


import by.lobov.annotation.AnnotationForExceptionHandlerForStudentHw38Controller;
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
@AnnotationForExceptionHandlerForStudentHw38Controller
public class StudentForHw38Controller {

    public final StudentForHw38Service service;
    @Autowired
    private final CountOfVisitHw38 getCountOfVisit;

    @GetMapping
    public String getAllStudents(Model model) {

        getCountOfVisit.getIterableCount();
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
}
