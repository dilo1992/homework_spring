package by.lobov.controller.HW40;

import by.lobov.annotation.AnnotationForExceptionHandlerForPupilHw40;
import by.lobov.entity.HW40.Pupil;
import by.lobov.service.HW40.PupilHw40Service;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("pupil")
@AnnotationForExceptionHandlerForPupilHw40
public class PupilHw40Controller {

    private final PupilHw40Service service;

    @GetMapping()
    public String getAllPupils(Model model) {
        List<Pupil> pupils = service.findAll();
        model.addAttribute("pupils", pupils);
        return "viewAllPupils";
    }


    @GetMapping("/createNewPupil")
    public String createNewPupil(Model model) {
        model.addAttribute("allPupils", service.findAll());
        return "createPupilsHw40";
    }

    @PostMapping("/createNewPupil")
    public String createPupil(@Valid Pupil pupil, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            log.info("Pupil is incorrect: {}", result.getAllErrors());
            model.addAttribute("org.springframework.validation.BindingResult.pupilForCreate", result);
            model.addAttribute("allPupils", service.findAll());
            return "createPupilsHw40";
        }
        log.info("Pupil is correct", pupil);
        service.save(new Pupil(0L, pupil.getName(), pupil.getCourse()));
        model.addAttribute("allPupils", service.findAll());
        status.setComplete();

        pupil.setName("");
        pupil.setCourse(null);
        return "createPupilsHw40";
    }

    @ModelAttribute("pupilForCreate")
    public Pupil getNewPupilForCreateMethod() {
        return new Pupil();
    }


    @GetMapping("deletePupilById")
    public String deletePupil(Model model) {
        log.info("all pupils: {}", service.findAll());
        model.addAttribute("allPupils", service.findAll());
        return "deletePupilByIdHw40";
    }

    @GetMapping("deletePupilById/id")
    public String deletePupilById(@RequestParam("id") Long id, Model model, HttpServletRequest request, SessionStatus status) {
        log.info("id of the form: {}", id);
        request.setAttribute("pupilId", id);
        service.delete(service.findById(id));
        model.addAttribute("allPupils", service.findAll());
        status.setComplete();
        return "deletePupilByIdHw40";
    }
}

