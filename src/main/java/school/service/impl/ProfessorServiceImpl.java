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
import school.service.ProfessorService;
import school.repository.ProfessorRepository;

@Service
public class ProfessorServiceImpl implements ProfessorService {
    @Autowired
    private final ProfessorRepository adminRepository;

    @Autowired
    public ProfessorServiceImpl(ProfessorRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Professor findByNumber(Long numberId) {
        return adminRepository.findByNumber(numberId);
    }

    @Override
    public ArrayList<Class> getAllClasses(Long godina) {
        return adminRepository.getAllClasses(godina);
    }

    @Override
    public ArrayList<Student> getAllStudents(Long godina) {
        return adminRepository.getAllStudents(godina);
    }

    @Override
    public ArrayList<Grade> getAllGrades(Long studentID, Long predmetID) {
        return adminRepository.getAllGrades(studentID, predmetID);
    }

    @Override
    public Long getIDByUsername(String username) {
        return adminRepository.getIDByUsername(username);
    }

    @Override
    public Student findStudentByID(Long id) {
        return adminRepository.findStudentByID(id);
    }

    @Override
    public Subject findPredmetByID(Long id) {
        return adminRepository.findPredmetByID(id);
    }

    @Override
    public ArrayList<Parent> getAllParents() {
        return adminRepository.getAllParents();
    }

    @Override
    public void addNewParent(Parent r) {
        adminRepository.addNewParent(r);
    }

    @Override
    public void updateStudent(Student u) {
        adminRepository.updateStudent(u);
    }

    @Override
    public Parent getParentByID(Long roditeljID) {
        return adminRepository.getParentByID(roditeljID);
    }

    @Override
    public void addNewUserWithRole(Authorities a) {
        adminRepository.addNewUserWithRole(a);
    }

    @Override
    public void addNewUser(Users us) {
        adminRepository.addNewUser(us);
    }
}
