/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Bojan
 */
@Entity
@Table(name = "Ocena")
public class Grade implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ocenaID;
    private int broj;
    private String napomena;
    @ManyToOne 
    @JoinColumn(name="tipOceneID")
    private GradeType tipOcene;
    @ManyToOne 
    @JoinColumn(name="profesorID")
    private Professor profesor;
    @ManyToOne 
    @JoinColumn(name="ucenikID")
    private Student ucenik;
    @ManyToOne 
    @JoinColumn(name="predmetID")
    private Subject predmet;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datum;

    public Grade(long ocenaID, int broj, String napomena, GradeType tipOcene, Professor profesor, Student ucenik, Subject predmet) {
        this.ocenaID = ocenaID;
        this.broj = broj;
        this.napomena = napomena;
        this.tipOcene = tipOcene;
        this.profesor = profesor;
        this.ucenik = ucenik;
        this.predmet = predmet;
    }

    
    public Grade() {
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Subject getPredmet() {
        return predmet;
    }

    public void setPredmet(Subject predmet) {
        this.predmet = predmet;
    }

    public long getOcenaID() {
        return ocenaID;
    }

    public void setOcenaID(long ocenaID) {
        this.ocenaID = ocenaID;
    }

    public int getBroj() {
        return broj;
    }

    public void setBroj(int broj) {
        this.broj = broj;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public GradeType getTipOcene() {
        return tipOcene;
    }

    public void setTipOcene(GradeType tipOcene) {
        this.tipOcene = tipOcene;
    }

    public Professor getProfesor() {
        return profesor;
    }

    public void setProfesor(Professor profesor) {
        this.profesor = profesor;
    }

    public Student getUcenik() {
        return ucenik;
    }

    public void setUcenik(Student ucenik) {
        this.ucenik = ucenik;
    }
    
    
}
