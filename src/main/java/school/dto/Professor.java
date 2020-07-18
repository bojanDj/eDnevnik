/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Bojan
 */
@Entity
@Table(name = "Professor")
public class Professor implements Serializable{
    @Id
    private long profesorID;
    private String ime;
    private String prezime;
    private String username;
    @ManyToOne 
    @JoinColumn(name="predmetID")
    private Subject predmet;
    @ManyToMany(fetch = FetchType.EAGER)//(targetEntity = Class.class)
    @JoinTable(
    name = "profesorodeljenje", 
    joinColumns = @JoinColumn(name = "profesorID"), 
    inverseJoinColumns = @JoinColumn(name = "odeljenjeID"))
    private List<Class> odeljenja;

    public Professor() {
    }

    public List<Class> getOdeljenja() {
        return odeljenja;
    }

    public void setOdeljenja(List<Class> odeljenja) {
        this.odeljenja = odeljenja;
    }
    
    

    public long getProfesorID() {
        return profesorID;
    }

    public void setProfesorID(long profesorID) {
        this.profesorID = profesorID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Subject getPredmet() {
        return predmet;
    }

    public void setPredmet(Subject predmet) {
        this.predmet = predmet;
    }
    
    
}
