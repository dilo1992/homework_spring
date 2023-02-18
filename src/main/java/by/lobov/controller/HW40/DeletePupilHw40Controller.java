package by.lobov.controller.HW40;

import by.lobov.annotation.AnnotationForExceptionHandlerForPupilHw40Controllers;
import by.lobov.entity.HW40.Pupil;
import by.lobov.service.HW40.PupilHw40Service;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        log.info("all pupils: {}", service.findAll());
        model.addAttribute("allPupils", service.findAll());
        return "deletePupilByIdHw40";
    }

    @PostMapping
    public String deletePupilById(@Valid Pupil pupil, Model model, HttpServletRequest req, SessionStatus status) {
        req.setAttribute("pupilId", pupil.getId());
        service.delete(service.findById(pupil.getId()));
        model.addAttribute("pupils", service.findAll());
        status.setComplete();

        pupil.setId(null);
        return "deletePupilByIdHw40";
    }

    @ModelAttribute("pupil")
    public Pupil getNewPupil() {
        Pupil pupil = new Pupil();
        pupil.setName("defaultNameForDelete");
        pupil.setCourse(1L);
        log.info("this new pupil: {}", pupil);
        return pupil;
    }
}
