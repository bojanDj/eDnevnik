package school.service.impl;

import school.dto.Professor;
import school.dto.FinalGrade;
import school.dto.Grade;
import school.dto.Class;
import school.dto.Subject;
import school.dto.Student;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.dto.Authorities;
import school.dto.Parent;
import school.dto.Users;
import school.repository.GradeRepository;
import school.service.GradeService;
import school.service.ProfessorService;
import school.repository.ProfessorRepository;

@Service
public class GradeServiceImpl implements GradeService {
    @Autowired
    private final GradeRepository gradeRepository;

    @Autowired
    public GradeServiceImpl(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Override
    public void deleteGrade(Long numberId) {
        gradeRepository.deleteGrade(numberId);
    }

    @Override
    public void updateGrade(Grade ocena) {
        gradeRepository.updateGrade(ocena);
    }

    @Override
    public void addGrade(Grade ocena) {
        gradeRepository.addGrade(ocena);
    }

    @Override
    public Grade getGradeByID(Long ocenaID) {
        return gradeRepository.getGradeByID(ocenaID);
    }

    @Override
    public void zakljuciOcenu(FinalGrade k) {
        gradeRepository.zakljuciOcenu(k);
    }

    @Override
    public FinalGrade findKonacna(Long studentID, Long predmetID) {
        return gradeRepository.findKonacna(studentID, predmetID);
    }

    @Override
    public void izmeniZakljucenu(FinalGrade k) {
        gradeRepository.izmeniZakljucenu(k);
    }
}
