package org.example.repository;

import org.frost.util.annotations.Component;

@Component
public class TestDataBase {
    private Item[] menu = new Item[] {new Item("burger",4.99),
    new Item("fries",2.99), new Item("coke",.99)};
    public TestDataBase() {

    }

    public Item[] getMenu() {
        return menu;
    }


}
