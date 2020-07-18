/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school.controller;

import school.dto.Grade;
import school.dto.Subject;
import java.security.Principal;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import school.service.ProfessorService;
import school.service.ParentService;

/**
 *
 * @author Bojan
 */
@Controller
public class HomeController {
    private ParentService userService;
    private ProfessorService adminService;

    @Autowired
    public HomeController(ParentService userService, ProfessorService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }
    
    @RequestMapping(value = "/admin/homeAdmin", method = RequestMethod.GET)
    public ModelAndView homeAdmin(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("admin/homeAdmin");
        modelAndView.addObject("username", principal.getName());
        return modelAndView;
    }
    @RequestMapping(value = "/homeUser", method = RequestMethod.GET)
    public ModelAndView homeUser(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("homeUser");
        
        Long id = userService.getIDByUsername(principal.getName());
        modelAndView.addObject("id", id);
        modelAndView.addObject("kids", userService.getAllKids(id));
        
        return modelAndView;
    }
}
