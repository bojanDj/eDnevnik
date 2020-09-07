/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school.controller;

import java.nio.charset.Charset;
import school.dto.Professor;
import school.dto.FinalGrade;
import school.dto.Grade;
import school.dto.Class;
import school.dto.GradeType;
import school.dto.Student;
import java.security.Principal;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.MimeMessage;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import school.validator.KonacnaValidator;
import school.validator.OcenaValidator;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import school.dto.Authorities;
import school.dto.Parent;
import school.dto.Users;
import school.service.GradeService;
import school.service.ProfessorService;

/**
 *
 * @author Bojan
 */
@ControllerAdvice
@RequestMapping(value = "/admin")
public class GradeController {
    
    private GradeService gradeService;
    private ProfessorService adminService;
    private OcenaValidator ocenaValidator;
    private KonacnaValidator konacnaValidator;

    @Autowired
    public GradeController(GradeService gradeService, ProfessorService adminService, OcenaValidator ocenaValidator, KonacnaValidator konacnaValidator) {
        this.gradeService = gradeService;
        this.ocenaValidator = ocenaValidator;
        this.konacnaValidator = konacnaValidator;
        this.adminService = adminService;
    }
    @InitBinder("ocenaAtr")
    private void initOcenaBinder(WebDataBinder binder) {
        binder.setValidator(ocenaValidator);
    }
    @InitBinder("konacna")
    private void initKonacnaBinder(WebDataBinder binder) {
        binder.setValidator(konacnaValidator);
    }
    @RequestMapping(value = "/odeljenja/{razred}/{student}/{ocenaID}/update", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable(name = "razred") Long razred,
                                    @PathVariable(name = "student") Long studentID,
                                    @PathVariable(name = "ocenaID") Long ocenaID,
                                    Principal principal) {
        ModelAndView modelAndView = new ModelAndView("admin/updateGrade");
        modelAndView.addObject("ocena",gradeService.getGradeByID(ocenaID));
        modelAndView.addObject("student", adminService.findStudentByID(studentID));

        return modelAndView;
    }
    @RequestMapping(value = "/odeljenja/{razred}/{student}/{ocena}/add", method = RequestMethod.GET)
    public ModelAndView add(@PathVariable(name = "razred") Long razred,
                                    @PathVariable(name = "student") Long studentID,
                                    @PathVariable(name = "ocena") Long ocena,
                                    Principal principal) {
        ModelAndView modelAndView = new ModelAndView("admin/addGrade");
        modelAndView.addObject("student", adminService.findStudentByID(studentID));

        return modelAndView;
    }
    
    @RequestMapping(value = "/odeljenja/{razred}/{student}/{predmetID}/finalGrade", method = RequestMethod.POST)
    public ModelAndView finalGrade(@PathVariable(name = "razred") Long razred,
                                    @PathVariable(name = "student") Long studentID,
                                    @PathVariable(name = "predmetID") Long predmetID,
                                    @ModelAttribute("konacna") @Validated FinalGrade k, BindingResult result,
                                    Principal principal, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/odeljenja/"+razred+"/"+studentID);
        Professor prof = adminService.findByNumber(adminService.getIDByUsername(principal.getName()));
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", "Ocena mora biti u intervalu od 1 do 5.");
        } else {
            k.setPredmet(adminService.findPredmetByID(predmetID));
            k.setUcenik(adminService.findStudentByID(studentID));
            //redirectAttributes.addFlashAttribute("konacna", gradeService.findKonacna(studentID,prof.getPredmet().getPredmetID()));
            FinalGrade kon = gradeService.findKonacna(studentID,prof.getPredmet().getPredmetID());
            if (kon != null) {
                k.setKonacnaID(kon.getKonacnaID());
                gradeService.izmeniZakljucenu(k);
            } else {
                gradeService.zakljuciOcenu(k);
            }            
        }
        return modelAndView;
    }
    
    @GetMapping(value = "/odeljenja/{razred}/{student}/{ocena}/delete")
    public ModelAndView delete(@PathVariable(name = "razred") Long razred,
                                    @PathVariable(name = "student") Long studentID,
                                    @PathVariable(name = "ocena") Long ocena,
                                    Principal principal) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/odeljenja/"+razred+"/"+studentID);
        
        gradeService.deleteGrade(ocena);
        return modelAndView;
    }
    
    @RequestMapping(value = "/odeljenja/{razred}/{student}/{ocenaID}/updateGrade", method = RequestMethod.POST)
    public ModelAndView updateGrade(@PathVariable(name = "ocenaID") Long ocenaID,
                                    @PathVariable(name = "student") Long studentID,
                                     @PathVariable(name = "razred") Long razredID,
                                    Principal principal,
                                    @ModelAttribute("ocenaAtr") @Validated Grade o, BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView;
        if (bindingResult.hasErrors()) {
            modelAndView = new ModelAndView("admin/updateGrade");
            modelAndView.addObject("student", adminService.findStudentByID(studentID));
            modelAndView.addObject("message", "Ocena mora biti u intervalu od 1 do 5.");
        } else {
            modelAndView = new ModelAndView("redirect:/admin/odeljenja/"+razredID+"/"+studentID);
            Professor prof = adminService.findByNumber(adminService.getIDByUsername(principal.getName()));
            o.setProfesor(prof);
            o.setPredmet(prof.getPredmet());
            o.setUcenik(adminService.findStudentByID(studentID));
            gradeService.updateGrade(o);
            redirectAttributes.addFlashAttribute("message", "Ocena je uspesno izmenjena.");
        }
        return modelAndView;
    }
    
    @RequestMapping(value = "/odeljenja/{razred}/{student}/addGrade", method = RequestMethod.POST)
    public ModelAndView addGrade(@PathVariable(name = "student") Long studentID,
                                     @PathVariable(name = "razred") Long razredID,
                                    @ModelAttribute("ocenaAtr") @Validated Grade o, BindingResult bindingResult,
                                    Principal principal, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView;
        if (bindingResult.hasErrors()) {
            modelAndView = new ModelAndView("admin/addGrade");
            modelAndView.addObject("student", adminService.findStudentByID(studentID));
            modelAndView.addObject("message", "Ocena mora biti u intervalu od 1 do 5.");
        } else {
            modelAndView = new ModelAndView("redirect:/admin/odeljenja/"+razredID+"/"+studentID);
            Professor prof = adminService.findByNumber(adminService.getIDByUsername(principal.getName()));
            o.setProfesor(prof);
            o.setPredmet(prof.getPredmet());
            o.setUcenik(adminService.findStudentByID(studentID));
            gradeService.addGrade(o);
            redirectAttributes.addFlashAttribute("message", "Ocena je uspesno dodata.");
        }
        return modelAndView;
    }
    
    @ModelAttribute(name = "ocenaAtr")
    private Grade getOcena() {
        return new Grade();
    }
    @ModelAttribute(name = "konacna")
    private void getKonacna(Model model) {
        model.addAttribute("konacna", new FinalGrade());
    }
}
