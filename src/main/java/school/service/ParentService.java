package school.service;

import school.dto.Professor;
import school.dto.Class;
import school.dto.Student;
import school.dto.Grade;
import java.util.ArrayList;
import java.util.List;
import school.dto.FinalGrade;

public interface ParentService {
    ArrayList<Student> getAllKids(Long userID);
    ArrayList<Grade> getAllGrades(Long studentID);
    Long getIDByUsername (String username);
    ArrayList<FinalGrade> getKonacna(Long studentID);
}
