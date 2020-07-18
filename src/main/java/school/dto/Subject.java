/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Bojan
 */
@Entity
@Table(name = "predmet")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long predmetID;
    private String naziv;

    public Subject(long predmetID, String naziv) {
        this.predmetID = predmetID;
        this.naziv = naziv;
    }

    public Subject() {
    }
    

    public long getPredmetID() {
        return predmetID;
    }

    public void setPredmetID(long predmetID) {
        this.predmetID = predmetID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
}
