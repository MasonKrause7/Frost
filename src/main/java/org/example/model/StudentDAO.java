package org.example.model;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class StudentDAO {
    public void saveStudent(Student student) {
        //open session
        Session session = HibernateConfiguration.getSessionFactory().openSession();


        Transaction transaction = session.beginTransaction();
        session.persist(student);
        transaction.commit();
        session.close();

    }
}
