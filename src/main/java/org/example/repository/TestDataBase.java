package org.example.repository;

import org.frost.util.annotations.Component;

@Component
public class TestDataBase {
    private String item = "Burgers";
    public TestDataBase() {

    }

    public String getItem() {
        return item;
    }
}
