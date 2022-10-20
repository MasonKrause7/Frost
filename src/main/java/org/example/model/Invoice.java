package org.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "invoice")
public class Invoice {

    public Invoice(){};
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Invoice(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
