package org.example;

import org.example.controllers.HomeController;
import org.frost.util.ApplicationContainer;
import org.frost.util.URLResolver;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        ApplicationContainer applicationContainer = new ApplicationContainer();
        applicationContainer.start(Main.class);






    }
}
