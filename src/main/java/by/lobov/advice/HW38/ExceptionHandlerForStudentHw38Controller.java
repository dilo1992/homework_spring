package by.lobov.advice.HW38;

import by.lobov.annotation.AnnotationForExceptionHandlerForStudentHw38Controller;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ControllerAdvice(annotations = AnnotationForExceptionHandlerForStudentHw38Controller.class)
@Slf4j
public class ExceptionHandlerForStudentHw38Controller {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class) //будем ловить вот эту ошибку
    public String handlerException(HttpServletRequest req, Exception ex) {
        log.info("We have found the error: {} using URI {}, URL {}", ex.getMessage(),
                req.getRequestURI(), req.getRequestURL().toString());
        return "studentNotFoundErrorPage";
    }
}
