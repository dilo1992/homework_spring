package by.lobov.interceptor.HW38;

import by.lobov.entity.HW38.StudentForHw38;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
public class CountOfStudentsInterceptor implements HandlerInterceptor {

    @Override
    //это то, что делает перехватчик до перехода
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LocalDateTime dateTime = LocalDateTime.now();
        log.info("student inquiry was made: {}", dateTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView.getModel().get("allStudents") != null) {
            log.info("students in the model: {}", ((List<StudentForHw38>) modelAndView.getModel().get("allStudents")).size());
        } else if (modelAndView.getModel().get("studentsOfTheSameCourse") != null) {
            log.info("students in the model: {}", ((List<StudentForHw38>) modelAndView.getModel().get("studentsOfTheSameCourse")).size());
        } else {
            log.info("students are not found in the model");
        }
    }
}
