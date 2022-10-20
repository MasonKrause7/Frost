package org.example.model;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class InvoiceDAO {
    public void saveInvoice(Invoice invoice) {
        Session session = HibernateConfiguration.getSessionFactory().openSession();


        Transaction transaction = session.beginTransaction();
        session.persist(invoice);
        transaction.commit();
        session.close();
    }
}
