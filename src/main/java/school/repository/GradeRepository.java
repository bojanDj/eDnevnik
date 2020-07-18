/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school.repository;

import school.dto.Professor;
import school.dto.FinalGrade;
import school.dto.Grade;
import school.dto.Class;
import school.dto.Subject;
import school.dto.Student;
import java.util.ArrayList;
import java.util.List;
import school.dto.Authorities;
import school.dto.Parent;
import school.dto.Users;

/**
 *
 * @author laptop-02
 */
public interface GradeRepository {
    void deleteGrade(Long numberId);
    void updateGrade(Grade ocena);
    void addGrade(Grade ocena);
    Grade getGradeByID(Long ocenaID);
    void zakljuciOcenu(FinalGrade k);
    void izmeniZakljucenu(FinalGrade k);
    FinalGrade findKonacna(Long studentID, Long predmetID);
}
