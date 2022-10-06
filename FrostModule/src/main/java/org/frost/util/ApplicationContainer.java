package org.frost.util;

import org.frost.util.applicationserver.TomcatServer;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.lang.annotation.*;

public class ApplicationContainer {
    private TomcatServer tomcatServer;
   private static Map<Class, Object> clientObjects;

    public static Map<Class, Object> getComponents() {
        return clientObjects;
    }

   public void start(Class<?> mainClass) {
       PackageScanner packageScanner = new PackageScanner(mainClass);
       tomcatServer = new TomcatServer();

       Set<Class<?>> classSet = null;

       classSet = packageScanner.scan();

       try {
            ComponentCreator componentCreator = new ComponentCreator(classSet);
            clientObjects = componentCreator.getComponentMap();
        } catch (Exception e) {
            System.out.println("Error getting clientMap from ComponentCreator....");
            System.out.println(e.getMessage());
        }
       tomcatServer.start();





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






