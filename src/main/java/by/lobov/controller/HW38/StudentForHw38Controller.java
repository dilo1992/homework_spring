package by.lobov.controller.HW38;


import by.lobov.entity.HW38.CountOfVisitHw38;
import by.lobov.entity.HW38.StudentForHw38;
import by.lobov.repository.HW38.StudentForHw38Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("studentsHW38")
@RequiredArgsConstructor
public class StudentForHw38Controller {

    public final StudentForHw38Repository repository;
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
        model.addAttribute("allStudents", repository.findAll());
        return "viewAllStudents";
    }

    @GetMapping("{course}")
    public String getStudentsByCourse(@PathVariable String course, Model model) {
        getCountOfVisit.getIterableCount();
        model.addAttribute("studentsOfTheSameCourse", repository.findByCourse(course));
        return "viewStudentsOfTheSameCourse";
    }
}
