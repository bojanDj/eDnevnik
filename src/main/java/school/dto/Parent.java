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
@Table(name = "Roditelj")
public class Parent implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roditeljID;
    private String username;
    private String email;

    public Parent(long roditeljID, String username, String email) {
        this.roditeljID = roditeljID;
        this.username = username;
        this.email = email;
    }

    public Parent() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public long getRoditeljID() {
        return roditeljID;
    }

    public void setRoditeljID(long roditeljID) {
        this.roditeljID = roditeljID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
