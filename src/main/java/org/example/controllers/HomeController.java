package org.example.controllers;

import org.example.repository.Item;
import org.example.repository.TestDataBase;
import org.frost.util.annotations.Component;
import org.frost.util.annotations.Controller;
import org.frost.util.annotations.GetMapping;
import org.frost.util.annotations.Inject;
import org.w3c.dom.html.HTMLObjectElement;

@Controller
public class HomeController {

    public HomeController() {

    }

    private TestDataBase testDataBase;
    @Inject
    public HomeController(TestDataBase testDataBase) {

        this.testDataBase = testDataBase;
    }
    @GetMapping("/")
    public Item[] getHome () {

        return testDataBase.getMenu();
    }
    @GetMapping("/getmessage")
    public String getMessage() {
    return "Message Page";
    }

    @GetMapping("/getMenu")
    public String getMenu() {return "Menu Page";}

}
