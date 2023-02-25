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

@Slf4j
@Controller
@RequestMapping("studentsHW38")
@RequiredArgsConstructor
@SessionAttributes("student")
public class StudentForHw38Controller {

    private final StudentForHw38Service service;

    @GetMapping
    public String getAllStudents(Model model) {
        model.addAttribute("allStudents", service.findAll());
        //model.addAttribute("listOfCourses", CoursesForHw38.values());
        return "viewAllStudents";
    }

    @GetMapping("/course/{course}")
    public String getStudentsByCourse(@PathVariable("course") String course, Model model) {
        model.addAttribute("studentsOfTheSameCourse", service.findByCourse(course));
        return "viewStudentsOfTheSameCourse";
    }

    @GetMapping("/id/{id}")
    public String getStudentsById(@PathVariable("id") long id, Model model) {
        StudentForHw38 studentForHw38 = service.findById(id);
        log.info("A student with searched id: {}", studentForHw38);
        model.addAttribute("id", id);
        model.addAttribute("studentById", studentForHw38);
        return "findStudentById";
    }

//

    @GetMapping("/newStudentHW38")
    public String getAllStudentForDeleteMethod(Model model) {
        model.addAttribute("allStudents", service.findAll());
        model.addAttribute("listOfCourses", CoursesForHw38.values());
        return "createStudent";
    }

    @PostMapping("/newStudentHW38")
    public String createUser(@Valid StudentForHw38 student, BindingResult result, Model model, SessionStatus status) { //если есть ошибки они собираются в bindingResult
        //из формы отправились параметры, и мы их достали (как в сервлете req.getParameter)
        if (result.hasErrors()) {
            log.info("Student is incorrect: {}", result.getAllErrors());
            model.addAttribute("org.springframework.validation.BindingResult.student", result);
            model.addAttribute("allStudents", service.findAll());
            return "createStudent";
        }
        log.info("student is correct: {}", student);
        service.save(new StudentForHw38(0L, student.getName(), student.getCourse()));
        model.addAttribute("allStudents", service.findAll());

        status.setComplete();

        student.setName("");
        student.setCourse("");

        return "createStudent";
    }

    //    для связи нового user с нашей формой
    @ModelAttribute(name = "student")
    public StudentForHw38 getNewStudent() {
        return new StudentForHw38();
    }
}