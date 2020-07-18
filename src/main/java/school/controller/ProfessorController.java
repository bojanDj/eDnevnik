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
public class ProfessorController {
    
    private ProfessorService adminService;
    private GradeService gradeService;
    private OcenaValidator ocenaValidator;
    private KonacnaValidator konacnaValidator;

    @Autowired
    public ProfessorController(ProfessorService adminService,GradeService gradeService, OcenaValidator ocenaValidator, KonacnaValidator konacnaValidator) {
        this.adminService = adminService;
        this.ocenaValidator = ocenaValidator;
        this.konacnaValidator = konacnaValidator;
        this.gradeService = gradeService;
    }
    
    @RequestMapping(value = "/odeljenja", method = RequestMethod.GET)
    public String classes() {
        return "admin/classesAdmin";
    }
    
    @RequestMapping(value = "/ucenici", method = RequestMethod.GET)
    public String students() {
        return "admin/studentsAdmin";
    }
    @GetMapping(value = "/odeljenja/{godina}")
    public ModelAndView getClasses(@PathVariable(name = "godina") Long godina,
                                    Principal principal) {
        ModelAndView modelAndView = new ModelAndView("admin/classesAdmin");
        modelAndView.addObject("godina", godina);
        Professor prof = adminService.findByNumber(adminService.getIDByUsername(principal.getName()));
        modelAndView.addObject("odeljenja", prof.getOdeljenja());
        return modelAndView;
    }
    @GetMapping(value = "/ucenici/{razred}")
    public ModelAndView getStudents(@PathVariable(name = "razred") Long razred,
                                    Principal principal) {
        Professor prof = adminService.findByNumber(adminService.getIDByUsername(principal.getName()));
        for (Class o : prof.getOdeljenja()) {
            if (o.getOdeljenjeID() == razred) {
                ModelAndView modelAndView = new ModelAndView("admin/studentsAdmin");
                modelAndView.addObject("razred", razred);
                modelAndView.addObject("students", adminService.getAllStudents(razred));
                return modelAndView;
            }
        }
        Long godina = razred / 10;
        ModelAndView modelAndView = new ModelAndView("admin/classesAdmin");
        modelAndView.addObject("godina", godina);
        modelAndView.addObject("message", "Ne predajete izabranom odeljenju.");
        return modelAndView;
    }
    @GetMapping(value = "/odeljenja/{razred}/{student}")
    public ModelAndView getGrades(@PathVariable(name = "razred") Long razred,
                                    @PathVariable(name = "student") Long studentID,
                                    Principal principal) {
        ModelAndView modelAndView = new ModelAndView("admin/studentsAdmin");
        Professor prof = adminService.findByNumber(adminService.getIDByUsername(principal.getName()));
        modelAndView.addObject("zakljucena", gradeService.findKonacna(studentID,prof.getPredmet().getPredmetID()));
        modelAndView.addObject("predmetID", prof.getPredmet().getPredmetID());
        modelAndView.addObject("predmetNaziv", prof.getPredmet().getNaziv());
        modelAndView.addObject("razred", razred);
        modelAndView.addObject("studentID", studentID);
        modelAndView.addObject("student", adminService.findStudentByID(studentID));
        modelAndView.addObject("students", adminService.getAllStudents(razred));
        modelAndView.addObject("ocene",adminService.getAllGrades(studentID,prof.getPredmet().getPredmetID()));
        return modelAndView;
    }
    @GetMapping(value = "/odeljenja/{razred}/{student}/profilUcenika")
    public ModelAndView profile(@PathVariable(name = "razred") Long razred,
                                    @PathVariable(name = "student") Long studentID,
                                    Principal principal) {
        ModelAndView modelAndView = new ModelAndView("admin/profileStudent");
        modelAndView.addObject("razred", razred);
        modelAndView.addObject("student", adminService.findStudentByID(studentID));
        modelAndView.addObject("roditelji", adminService.getAllParents());
        return modelAndView;
    }
    @PostMapping(value = "/odeljenja/{razred}/{student}/profilUcenika/add")
    public ModelAndView profileAdd(@PathVariable(name = "razred") Long razred,
                                    @PathVariable(name = "student") Long studentID,
                                    Principal principal, @ModelAttribute("roditeljAtr") Parent r,
                                    BindingResult result, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/odeljenja/"+razred+"/"+studentID+"/profilUcenika");
        redirectAttributes.addFlashAttribute("razred", razred);
        Student u = adminService.findStudentByID(studentID);
        redirectAttributes.addFlashAttribute("roditelji", adminService.getAllParents());
        
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", "Nije uspelo!");
        } else {
            try {   
                u.setRoditelj(r);
                adminService.updateStudent(u);
                redirectAttributes.addFlashAttribute("student", u);
                String pass = getAlphaNumericString(10);
                sendEmail(r.getEmail(), r.getUsername(), pass);
                Users us = new Users(r.getUsername(), pass, true);
                Authorities a = new Authorities(r.getUsername(), "USER");
                adminService.addNewUserWithRole(a);
                adminService.addNewUser(us);
                redirectAttributes.addFlashAttribute("message", "Uspesno je promenjen roditelj!");    
            } catch (Exception ex) {
                redirectAttributes.addFlashAttribute("message", "Username vec postoji.");
                redirectAttributes.addFlashAttribute("student", adminService.findStudentByID(studentID));
            }
        }
        
        return modelAndView;
    }
    private static String getAlphaNumericString(int n) 
    { 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
  
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) {  
            int index  = (int)(AlphaNumericString.length()* Math.random());
            sb.append(AlphaNumericString.charAt(index)); 
        } 
  
        return sb.toString(); 
    }
    
    @PostMapping(value = "/odeljenja/{razred}/{student}/profilUcenika/update")
    public ModelAndView profileUpdate(@PathVariable(name = "razred") Long razred,
                                    @PathVariable(name = "student") Long studentID,
                                    Principal principal, @ModelAttribute("roditeljID") Long roditeljID,
                                    BindingResult result, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/odeljenja/"+razred+"/"+studentID+"/profilUcenika");
        redirectAttributes.addFlashAttribute("razred", razred);
        Student u = adminService.findStudentByID(studentID);
        Parent r = adminService.getParentByID(roditeljID);
        u.setRoditelj(r);
        redirectAttributes.addFlashAttribute("student", u);
        redirectAttributes.addFlashAttribute("roditelji", adminService.getAllParents());
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", "Nije uspelo!");
        } else {
            try {
                adminService.updateStudent(u);
                redirectAttributes.addFlashAttribute("message", "Uspesno je promenjen roditelj!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("message", "Nije uspelo!");
                redirectAttributes.addFlashAttribute("student", adminService.findStudentByID(studentID));
            } 
        }
        
        return modelAndView;
    } 
 
    @ModelAttribute(name = "roditeljAtr")
    private Parent getRoditelj() {
        return new Parent();
    }
//    @ExceptionHandler(ConstraintViolationException.class)
//	public String exceptionHandler(Exception Exception,RedirectAttributes redirectAttributes) {
//		
//		System.out.println("====================================================================");
//		System.out.println("@ControllerAdvice exception ocured: ConstraintViolationException===========");
//		System.out.println("====================================================================");
//		
////		redirectAttributes.addFlashAttribute("errorMessage", nullPointerException.getMessage());
////		redirectAttributes.addFlashAttribute("errorObj", nullPointerException);
////		
//		return "redirect:/error/globalException";
//	}
        
        
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.mailtrap.io");
        mailSender.setPort(587);

        mailSender.setUsername("b17f2437c77ac9");
        mailSender.setPassword("c69b577193f838");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");

        return mailSender;
    }
 
    private void sendEmail(String receiver, String username, String password) throws Exception{
        JavaMailSender sender = getJavaMailSender();
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
         
        helper.setTo(receiver);
        helper.setText("Postovani, Ocene Vase dece mozete videti na adredi http://localhost:8080/Skola/ . Vas se korisnicko ime je " + username+" . Vasa sifra je "+password);
        helper.setSubject("EDnevnik");
         
        sender.send(message);
    }
}
