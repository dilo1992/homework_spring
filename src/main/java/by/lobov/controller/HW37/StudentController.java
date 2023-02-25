package by.lobov.controller.HW37;

import by.lobov.service.HW37.StudentService;
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

    private final StudentService service;

    @GetMapping
    public String homework37() {
        service.init();
        return "viewAllStudentsHW37";
    }
}
