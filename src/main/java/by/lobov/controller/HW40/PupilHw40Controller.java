package by.lobov.controller.HW40;

import by.lobov.entity.HW40.Pupil;
import by.lobov.service.HW40.PupilHw40Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("pupil")
public class PupilHw40Controller {

    private final PupilHw40Service service;

    @GetMapping()
    public String getAllPupils(Model model) {
        List<Pupil> pupils = service.findAll();
        model.addAttribute("pupils", pupils);
        return "viewAllPupils";
    }

}
