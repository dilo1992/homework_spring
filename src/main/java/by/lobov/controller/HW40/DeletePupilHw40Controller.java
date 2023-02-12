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
@RequestMapping("deletePupilById")
@RequiredArgsConstructor
@AnnotationForExceptionHandlerForPupilHw40Controllers
public class DeletePupilHw40Controller {

    private final PupilHw40Service service;

    @GetMapping
    public String getAllPupils(Model model) {
        model.addAttribute("allPupils", service.findAll());
        return "deletePupilByIdHw40";
    }

    @PostMapping
    public String deletePupilById(Pupil pupil, Model model, BindingResult result, SessionStatus status) {
        log.info("this pupil: {}", service.findById(pupil.getId()));
        Pupil pupilForDelete = service.findById(pupil.getId());
        long idForAttribute = pupil.getId();
        log.info("this is id: {}", idForAttribute);
        if (result.hasErrors()) {
            log.info("pupil is incorrect: {}", result.getAllErrors());
            model.addAttribute("org.springframework.validation.BindingResult.pupil", result);
            model.addAttribute("pupils", service.findAll());
            model.addAttribute("id", pupil.getId());
            return "deletePupilByIdHw40";
        }
        log.info("pupil is correct: {}", pupilForDelete);
        service.delete(pupilForDelete);
        model.addAttribute("pupils", service.findAll());
        status.setComplete();

        pupil.setId(null);
//        pupil.setName("");
//        pupil.setCourse(null);
        return "deletePupilByIdHw40";
    }

    @ModelAttribute("pupil")
    public Pupil getNewPupil() {
        return new Pupil();
    }
}
