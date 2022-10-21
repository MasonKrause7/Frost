package org.example;

import org.example.model.HibernateConfiguration;
import org.example.model.Student;
import org.example.model.StudentRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class Main {

   // @SuppressWarnings("unchecked")
    //static <T> T getProxyObject( Class<T> interfOutput) {
      //  return  (T) Proxy.newProxyInstance(interfOutput.getClassLoader(),new Class[] {interfOutput}, new ProxyFactory(new CRUDImplementation<>()));

    //}
    public static void main(String[] args) {
      // ApplicationContainer applicationContainer = new ApplicationContainer();
      // applicationContainer.start(Main.class);





        Student s1 = new Student();

        s1.setFirstName("Mason");
        s1.setLastName("Krause");
        s1.setEmail("masongkrause@yahoo.com");

        SessionFactory sf = HibernateConfiguration.getSessionFactory();
        Session smokesesh = sf.openSession();
        Transaction t = smokesesh.beginTransaction();
        smokesesh.persist(s1);
        t.commit();
        System.out.println(s1.getFirstName() + " succesfully saved");
        smokesesh.close();





    }
    private static void printClass(StudentRepository ob) {

    }
}
