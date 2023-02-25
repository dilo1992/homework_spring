package by.lobov.interceptor.HW38;

import by.lobov.entity.HW38.CountOfVisitHw38;
import by.lobov.entity.HW38.StudentForHw38;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Slf4j
@RequiredArgsConstructor
public class CountOfStudentsInterceptor implements HandlerInterceptor {

    @Autowired
    private CountOfVisitHw38 getCountOfVisit;

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
        getCountOfVisit.incrementAndGetCount();

    }
}
