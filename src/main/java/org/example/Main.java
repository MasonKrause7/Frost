package org.example;

import jakarta.persistence.Entity;
import org.example.model.*;
import org.frost.CRUDImplementation;
import org.frost.util.ApplicationContainer;
import org.frost.util.PackageScanner;
import org.frost.util.ProxyFactory;
import org.frost.util.annotations.Repository;


import java.lang.reflect.Proxy;
import java.util.List;


public class Main {

    @SuppressWarnings("unchecked")
    static <T> T getProxyObject( Class<T> interfOutput) {
        return  (T) Proxy.newProxyInstance(interfOutput.getClassLoader(),new Class[] {interfOutput}, new ProxyFactory(new CRUDImplementation<>()));

    }
    public static void main(String[] args) {
      // ApplicationContainer applicationContainer = new ApplicationContainer();
      // applicationContainer.start(Main.class);


         PackageScanner scanner = new PackageScanner(Main.class);
         List<Class> list = scanner.finAnnotatedClasses(Repository.class);
        for(Class classz : list) {
            System.out.println(classz.getName());
            var object = getProxyObject();
            printClass(object);

        }






    }
    private static void printClass(StudentRepository ob) {

    }
}
