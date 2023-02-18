package by.lobov.controller.HW38;

import by.lobov.entity.HW38.StudentForHw38;
import by.lobov.service.HW38.StudentForHw38Service;
import by.lobov.validator.HW38.CoursesForHw38;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Arrays;

@Controller
@RequestMapping("create")
@Slf4j
@RequiredArgsConstructor
public class CreateNewStudentController {

    public final StudentForHw38Service service;

    @GetMapping
    public String getAllStudent(Model model) {
        model.addAttribute("allStudents", service.findAll());
        model.addAttribute("listOfCourses", Arrays.stream(CoursesForHw38.values()).toList());
        return "createStudent";
    }

    @PostMapping
    public String createUser(@Valid StudentForHw38 student, BindingResult result, Model model, SessionStatus status) { //если есть ошибки они собираются в bindingResult
        //из формы отправились параметры, и мы их достали (как в сервлете req.getParameter)
        if (result.hasErrors()) {
            log.info("Student is incorrect: {}", result.getAllErrors());
            model.addAttribute("org.springframework.validation.BindingResult.student", result);
            model.addAttribute("allStudents", service.findAll());
            model.addAttribute("listOfCourses", Arrays.stream(CoursesForHw38.values()).toList());
            return "createStudent";
        }
        log.info("student is correct: {}", student);
        service.save(new StudentForHw38(0L, student.getName(), student.getCourse()));
        model.addAttribute("allStudents", service.findAll());
        model.addAttribute("listOfCourses", Arrays.stream(CoursesForHw38.values()).toList());

        status.setComplete();

        student.setName("");
        student.setCourse("");

        return "createStudent";
    }

    //    для связи нового user с нашей формой
    @ModelAttribute(name = "student")
    public StudentForHw38 getNewStudent() {
        return new @Valid StudentForHw38();
    }
}
