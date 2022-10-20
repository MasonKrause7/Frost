package org.example.model;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class MenuDAO {

    public void saveItem(Item item) {
        Session session = HibernateConfiguration.getSessionFactory().openSession();


        Transaction transaction = session.beginTransaction();
        session.persist(item);
        transaction.commit();
        session.close();

    }
}
