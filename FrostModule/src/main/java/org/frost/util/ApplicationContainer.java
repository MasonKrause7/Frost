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


   private Map<Class, Object> clientObjects;

    public Map<Class, Object> getClientObjects() {
        return clientObjects;
    }

    /**
     * /called by the client main method
     * @param mainClass
     */


    public void start(Class<?> mainClass)  {
        PackageScanner packageScanner = new PackageScanner();


       Set<Class<?>> classSet = packageScanner.scanPath(mainClass);



       try {
           ComponentCreator componentCreator = new ComponentCreator(classSet);
           this.clientObjects = componentCreator.getClientMap();

       } catch (Exception e) {
           System.out.println("Error getting clientMap from ComponentCreator...");
           System.out.println(e.getMessage());
       }


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






