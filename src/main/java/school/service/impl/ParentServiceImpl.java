package school.service.impl;

import school.dto.Professor;
import school.dto.Grade;
import school.dto.Class;
import school.dto.Student;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.service.ParentService;
import school.repository.ParentRepository;

@Service
public class ParentServiceImpl implements ParentService {
    @Autowired
    private final ParentRepository userRepository;

    @Autowired
    public ParentServiceImpl(ParentRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ArrayList<Grade> getAllGrades(Long studentID) {
        return userRepository.getAllGrades(studentID);
    }

    @Override
    public ArrayList<Student> getAllKids(Long userID) {
        return userRepository.getAllKids(userID);
    }

    @Override
    public Long getIDByUsername(String username) {
        return userRepository.getIDByUsername(username);
    }
}
