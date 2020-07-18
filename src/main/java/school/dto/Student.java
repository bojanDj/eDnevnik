/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school.dto;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Bojan
 */
@Entity
@Table(name = "Ucenik")
public class Student implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ucenikID;
    private String ime;
    private String prezime;
    private String url;
    @ManyToOne 
    @JoinColumn(name="odeljenjeID")
    private Class odeljenje;
    @ManyToOne(cascade=CascadeType.ALL) 
    @JoinColumn(name="roditeljID")
    private Parent roditelj;

    public Student() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public long getUcenikID() {
        return ucenikID;
    }

    public void setUcenikID(long ucenikID) {
        this.ucenikID = ucenikID;
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

    public Class getOdeljenje() {
        return odeljenje;
    }

    public void setOdeljenje(Class odeljenje) {
        this.odeljenje = odeljenje;
    }

    public Parent getRoditelj() {
        return roditelj;
    }

    public void setRoditelj(Parent roditelj) {
        this.roditelj = roditelj;
    }
    
    
    
    
}
