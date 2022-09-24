package org.frost.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.lang.annotation.*;

public class ApplicationContainer {


    /**
     * /called by the client main method
     * @param mainClass
     */
   public static void start(Class<?> mainClass) {
       PackageScanner packageScanner = new PackageScanner();


       Set<Class<?>> classSet = packageScanner.scanPath(mainClass);

       Injector injector = new Injector(classSet);
       Map<Class, Object> objectMap;

       try {
           objectMap = injector.mapClassObjects(); //this method maps each class in classSet that's annotated with @component to its Instance.
       } catch (Exception e) {
           System.out.println(e.getMessage());
           throw new RuntimeException(e);
       }

       System.out.println("Object Map : " + objectMap.toString()); //testing what the map reads out



   }



}






