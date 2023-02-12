package by.lobov.advice.HW40;

import by.lobov.annotation.AnnotationForExceptionHandlerForPupilHw40Controllers;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ControllerAdvice(annotations = AnnotationForExceptionHandlerForPupilHw40Controllers.class)
@Slf4j
public class ExceptionHandlerForPupilHw40Controllers {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String handlerNoSuchElementException(HttpServletRequest req, Exception ex) {
        log.info("We have found the error: {} using URI {}, URL {}", ex.getMessage(),
                req.getRequestURI(), req.getRequestURL().toString());
        return "pupilNotFoundErrorPage";
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(BindException.class)
//    public String handlerBindException(HttpServletRequest req, Exception ex) {
//        log.info("We have found the error: {} using URI {}, URL {}", ex.getMessage(),
//                req.getRequestURI(), req.getRequestURL().toString());
//        return "pupilBadRequestErrorPage";
//    }

}