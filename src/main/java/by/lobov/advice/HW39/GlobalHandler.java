package by.lobov.advice.HW39;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ControllerAdvice
@Slf4j
public class GlobalHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class) //будем ловить вот эту ошибку
    public String handlerException(HttpServletRequest req, Exception ex) {
        log.info("We have found the error: {} using URI {}, URL {}", ex.getMessage(),
                req.getRequestURI(), req.getRequestURL().toString());
        return "studentNotFoundErrorPage";
    }
}
