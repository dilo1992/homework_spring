package by.lobov.controller.HW40;

import by.lobov.annotation.AnnotationForExceptionHandlerForPupilHw40Controllers;
import by.lobov.entity.HW40.Pupil;
import by.lobov.service.HW40.PupilHw40Service;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("createNewPupil")
@AnnotationForExceptionHandlerForPupilHw40Controllers
public class CreatePupilHw40Controller {

    private final PupilHw40Service service;

    @GetMapping
    public String getAllPupils(Model model) {
        model.addAttribute("allPupils", service.findAll());
        return "createPupilsHw40";
    }

    @PostMapping
    public String createPupil(@Valid Pupil pupil, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            log.info("Pupil is incorrect: {}", result.getAllErrors());
            model.addAttribute("org.springframework.validation.BindingResult.pupil", result);
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

    @ModelAttribute("pupil")
    public Pupil getNewPupil() {
        return new Pupil();
    }
}
