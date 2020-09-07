/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school.repository;

import school.dto.Professor;
import school.dto.Grade;
import school.dto.Class;
import school.dto.Student;
import java.util.ArrayList;
import java.util.List;
import school.dto.FinalGrade;

/**
 *
 * @author laptop-02
 */
public interface ParentRepository {
    ArrayList<Student> getAllKids(Long userID);
    ArrayList<Grade> getAllGrades(Long studentID);
    Long getIDByUsername (String username);
    ArrayList<FinalGrade> getKonacna(Long studentID);
}
