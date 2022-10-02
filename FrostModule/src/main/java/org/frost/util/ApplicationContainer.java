package org.frost.util;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class ApplicationContainer {

   private Map<Class, Object> clientObjects;

    private Map<Class, Object> getClientObjects() {
        return clientObjects;
    }
   public void start(Class<?> mainClass) {
       PackageScanner packageScanner = new PackageScanner(mainClass);

       Set<Class<?>> classSet = null;

       try {
          classSet = packageScanner.scan();
       } catch (IOException e) {
           e.printStackTrace();
       }

        try {
            ComponentCreator componentCreator = new ComponentCreator(classSet);
            this.clientObjects = componentCreator.getClientMap();
        } catch (Exception e) {
            System.out.println("Error getting clientMap from ComponentCreator....");
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






