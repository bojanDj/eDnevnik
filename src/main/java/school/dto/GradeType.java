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
import javax.persistence.Table;

/**
 *
 * @author Bojan
 */
@Entity
@Table(name = "TipOcene")
public class GradeType implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tipOceneID;
    private String naziv;

    public GradeType(long tipOceneID, String naziv) {
        this.tipOceneID = tipOceneID;
        this.naziv = naziv;
    }

    public GradeType() {
    }
    

    public long getTipOceneID() {
        return tipOceneID;
    }

    public void setTipOceneID(long tipOceneID) {
        this.tipOceneID = tipOceneID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
    
}
