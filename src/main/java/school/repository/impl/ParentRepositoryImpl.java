/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school.repository.impl;

import school.dto.Professor;
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
import school.dto.FinalGrade;
import school.repository.ParentRepository;

/**
 *
 * @author laptop-02
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class ParentRepositoryImpl implements ParentRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ArrayList<Grade> getAllGrades(Long studentID) {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            TransactionStatus status = TransactionAspectSupport.currentTransactionStatus();
        }
        String query = "Select o from Grade o where o.ucenik.ucenikID =  " +studentID;
        
        return (ArrayList<Grade>) entityManager.createQuery(query, Grade.class).getResultList();  
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ArrayList<Student> getAllKids(Long userID) {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            TransactionStatus status = TransactionAspectSupport.currentTransactionStatus();
        }
        String query = "Select u from Student u where u.roditelj.roditeljID =  " +userID;
        
        return  (ArrayList<Student>) entityManager.createQuery(query, Student.class).getResultList();  
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Long getIDByUsername(String username) {
        String query = "Select r from Parent r where r.username = :username";
        Parent user = entityManager.createQuery(query,Parent.class).setParameter("username", username).getResultList().get(0);
        return user.getRoditeljID();

    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ArrayList<FinalGrade> getKonacna(Long studentID) {
        String query = "Select k from FinalGrade k where ucenikID = "+studentID;
        ArrayList<FinalGrade> lista = (ArrayList<FinalGrade>) entityManager.createQuery(query,FinalGrade.class).getResultList();
        if (lista.isEmpty()) return null;
        else return lista;
    }
}
