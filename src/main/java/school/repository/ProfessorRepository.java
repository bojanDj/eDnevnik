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
public interface ProfessorRepository {
    Professor findByNumber(Long numberId);
    void addNewParent(Parent r);
    void updateStudent(Student u);
    ArrayList<Class> getAllClasses(Long godina);
    ArrayList<Student> getAllStudents(Long godina);
    ArrayList<Grade> getAllGrades(Long studentID, Long predmetID);
    ArrayList<Parent> getAllParents();
    Long getIDByUsername (String username);
    Student findStudentByID(Long id);
    Subject findPredmetByID(Long id);
    void addNewUser(Users us);
    Parent getParentByID(Long roditeljID);
    void addNewUserWithRole(Authorities a);
}
