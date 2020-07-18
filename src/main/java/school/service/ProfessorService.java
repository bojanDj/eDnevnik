package school.service;

import school.dto.Professor;
import school.dto.FinalGrade;
import school.dto.Class;
import school.dto.Student;
import school.dto.Grade;
import school.dto.Subject;
import java.util.ArrayList;
import java.util.List;
import school.dto.Authorities;
import school.dto.Parent;
import school.dto.Users;

public interface ProfessorService {
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
