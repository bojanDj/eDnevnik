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

public interface GradeService {
    void deleteGrade(Long numberId);
    void updateGrade(Grade ocena);
    void addGrade(Grade ocena);
    Grade getGradeByID(Long ocenaID);
    void zakljuciOcenu(FinalGrade k);
    void izmeniZakljucenu(FinalGrade k);
    FinalGrade findKonacna(Long studentID, Long predmetID);
}
