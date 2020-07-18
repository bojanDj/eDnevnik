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
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Bojan
 */
@Entity
@Table(name = "Odeljenje")
public class Class implements Serializable{
    @Id
    private long odeljenjeID;
    private String naziv;
    @ManyToMany(mappedBy = "odeljenja", targetEntity = Professor.class)
    private List<Professor> profesori;
    public Class() {
    }

    public long getOdeljenjeID() {
        return odeljenjeID;
    }

    public void setOdeljenjeID(long odeljenjeID) {
        this.odeljenjeID = odeljenjeID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

//    public ArrayList<Ucenik> getUcenici() {
//        return ucenici;
//    }
//
//    public void setUcenici(ArrayList<Ucenik> ucenici) {
//        this.ucenici = ucenici;
//    }
    
    
}
