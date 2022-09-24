package org.frost.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.lang.annotation.*;

public class ApplicationContainer {

   private static Map<Class, Object> clientObjects = new HashMap<>();
    /**
     * /called by the client main method
     * @param mainClass
     */
   public static void start(Class<?> mainClass)  {
        PackageScanner packageScanner = new PackageScanner();


       Set<Class<?>> classSet = packageScanner.scanPath(mainClass);

       Injector injector = new Injector(classSet);

       try {
           injector.mapClassObjects();
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
       clientObjects = injector.getMappedObjects();



   }

   public Object getObject(Class classz) {
       Object ob = null;
       try {

           ob = clientObjects.get(classz);
           if(ob == null) {
               throw new Exception("Object of Class " + classz.getName() + ".class not Found");
           }

       } catch (Exception e) {
           e.printStackTrace();
       }

       return ob;
   }



}






