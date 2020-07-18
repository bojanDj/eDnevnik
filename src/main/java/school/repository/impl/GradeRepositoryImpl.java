/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school.repository.impl;

import school.dto.Professor;
import school.dto.FinalGrade;
import school.dto.Grade;
import school.dto.Class;
import school.dto.Subject;
import school.dto.GradeType;
import school.dto.Student;
import school.dto.Parent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import school.dto.Authorities;
import school.dto.Users;
import school.repository.GradeRepository;
import school.repository.ProfessorRepository;

/**
 *
 * @author laptop-02
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class GradeRepositoryImpl implements GradeRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteGrade(Long numberId) {
        Grade o = entityManager.find(Grade.class, numberId);
        entityManager.remove(o);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateGrade(Grade ocena) {
        entityManager.merge(ocena);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addGrade(Grade ocena) {
        entityManager.persist(ocena);
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Grade getGradeByID(Long ocenaID) {
        return entityManager.find(Grade.class, ocenaID);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void zakljuciOcenu(FinalGrade k) {
        entityManager.persist(k);
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void izmeniZakljucenu(FinalGrade k) {
        entityManager.merge(k);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public FinalGrade findKonacna(Long studentID, Long predmetID) {
        String query = "Select k from FinalGrade k where k.ucenik.ucenikID =  " +studentID+" and k.predmet.predmetID = "+predmetID;
        List<FinalGrade> list = entityManager.createQuery(query, FinalGrade.class).getResultList();
        if (list.isEmpty()) return null;
        else return list.get(0);
    }
}
