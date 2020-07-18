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
import school.repository.ProfessorRepository;

/**
 *
 * @author laptop-02
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class ProfessorRepositoryImpl implements ProfessorRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Professor findByNumber(Long numberId) {
        return entityManager.find(Professor.class, numberId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ArrayList<Class> getAllClasses(Long godina) {
        String query = "Select o from Class o where o.odeljenjeID = "+godina+"%";
        
        return (ArrayList<Class>) entityManager.createQuery(query, Class.class).getResultList();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ArrayList<Student> getAllStudents(Long razred) {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            TransactionStatus status = TransactionAspectSupport.currentTransactionStatus();
        }
        String query = "Select u from Student u where u.odeljenje.odeljenjeID = "+razred;
        
        return (ArrayList<Student>) entityManager.createQuery(query, Student.class).getResultList();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ArrayList<Grade> getAllGrades(Long studentID, Long predmetID) {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            TransactionStatus status = TransactionAspectSupport.currentTransactionStatus();
        }
        String query = "Select o from Grade o where o.ucenik.ucenikID =  " +studentID+" and o.predmet.predmetID = "+predmetID;
        
        return (ArrayList<Grade>) entityManager.createQuery(query, Grade.class).getResultList();  
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Long getIDByUsername(String username) {
        String query = "Select p from Professor p where p.username = '" +username+"'";
        Professor admin = entityManager.createQuery(query,Professor.class).getResultList().get(0);
        return admin.getProfesorID();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Student findStudentByID(Long id) {
        return entityManager.find(Student.class, id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Subject findPredmetByID(Long id) {
        return entityManager.find(Subject.class, id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ArrayList<Parent> getAllParents() {
        String query = "Select r from Parent r";
        
        return (ArrayList<Parent>) entityManager.createQuery(query, Parent.class).getResultList();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addNewParent(Parent r) {
        entityManager.persist(r);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateStudent(Student u) {
        entityManager.merge(u);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Parent getParentByID(Long roditeljID) {
        return entityManager.find(Parent.class, roditeljID);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addNewUserWithRole(Authorities a) {
        entityManager.persist(a);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addNewUser(Users us) {
        entityManager.persist(us);
    }

  
}
