package by.lobov.controller.HW38;


import by.lobov.entity.HW38.CountOfVisitHw38;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Data
@Controller
@RequestMapping("/count")
@RequiredArgsConstructor
@Slf4j
public class CountVisitsController {

    private final CountOfVisitHw38 getCountOfVisit;

    @GetMapping()
    public String getCountOfVisitToPage(Model model) {
        log.info("value of count: {}", getCountOfVisit.getCount());
        model.addAttribute("countOfVisit", getCountOfVisit.getCount());
        return "countOfVisit";
    }

}
