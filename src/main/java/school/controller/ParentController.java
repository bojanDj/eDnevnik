package school.controller;

import school.dto.Grade;
import java.security.Principal;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import school.service.ParentService;

@Controller
public class ParentController {

        private ParentService userService;

        @Autowired
        public ParentController(ParentService userService) {
            this.userService = userService;
        }

        @GetMapping(value = "{id}/{student}")
        public ModelAndView getGrades(@PathVariable(name = "student") Long studentID,
                                        Principal principal) {
            ModelAndView modelAndView = new ModelAndView("homeUser");
            modelAndView.addObject("student", studentID);
            Long id = userService.getIDByUsername(principal.getName());
            modelAndView.addObject("id", id);
            modelAndView.addObject("kids", userService.getAllKids(id));
            ArrayList<Grade> ocene = userService.getAllGrades(studentID);
            modelAndView.addObject("ocene",ocene);
            modelAndView.addObject("konacne", userService.getKonacna(studentID));
            ArrayList<String> bezPondavljanja = new ArrayList<>();
            for (Grade o: ocene) {
                if (!bezPondavljanja.contains(o.getPredmet().getNaziv())) {
                    bezPondavljanja.add(o.getPredmet().getNaziv());
                }
            }
            modelAndView.addObject("predmetiBezPonavljanja", bezPondavljanja);
            return modelAndView;
        }

}
