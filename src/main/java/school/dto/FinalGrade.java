/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school.dto;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Bojan
 */
@Entity
@Table(name = "Konacna")
public class FinalGrade implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long konacnaID;
    private int broj;
    @ManyToOne 
    @JoinColumn(name="predmetID")
    private Subject predmet;
    @ManyToOne 
    @JoinColumn(name="ucenikID")
    private Student ucenik;

    public FinalGrade() {
    }

    public long getKonacnaID() {
        return konacnaID;
    }

    public void setKonacnaID(long konacnaID) {
        this.konacnaID = konacnaID;
    }

    public int getBroj() {
        return broj;
    }

    public void setBroj(int broj) {
        this.broj = broj;
    }

    public Subject getPredmet() {
        return predmet;
    }

    public void setPredmet(Subject predmet) {
        this.predmet = predmet;
    }

    public Student getUcenik() {
        return ucenik;
    }

    public void setUcenik(Student ucenik) {
        this.ucenik = ucenik;
    }
    
    
}
