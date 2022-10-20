package org.example;

import jakarta.persistence.Entity;
import org.example.model.*;
import org.frost.util.ApplicationContainer;
import org.frost.util.PackageScanner;


import java.util.List;


public class Main {
    public static void main(String[] args) {
        ApplicationContainer applicationContainer = new ApplicationContainer();
        applicationContainer.start(Main.class);

        //test data
        StudentDAO studentDao = new StudentDAO();
        Student student = new Student("Grapes", "McGee", "james.smith.64@gmail.com");
        studentDao.saveStudent(student);
        //test data
        MenuDAO menuDAO = new MenuDAO();
        Item item = new Item("burger",10.99);
        menuDAO.saveItem(item);
        //test data
        InvoiceDAO invoiceDAO = new InvoiceDAO();
        Invoice invoice = new Invoice("invoice1", 50.00);
        invoiceDAO.saveInvoice(invoice);














    }
}
