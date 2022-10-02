package org.frost.util;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Scans all packages and sub-packages of the clients Main Class and returns a collection of classes to be
 * maintained by the Application Container.
 */
public class PackageScanner {

     private String topLevelDir;
     private Set<Class<?>> loadedClasses;
     private Class<?> mainClass;



     private PackageScanner() {

     }

     public PackageScanner(Class<?> mainClass) {

         this.loadedClasses = new HashSet<>();//hashset of all Classes in the application classpath
         this.topLevelDir = mainClass.getPackageName();//path to the first package
         this.mainClass = mainClass;
     }

    //returns Hashset of classes
     public Set<Class<?>> scan() throws IOException {
         getClasses(topLevelDir);
        for(Class classz : loadedClasses) {
            System.out.println(classz.getName());
        }
         return this.loadedClasses;

     }


    //scans through all the packages recursively finding classes and loading them to he loadedClasses Hashset;
public void getClasses(String path) throws IOException {


    InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(path.replaceAll("[.]", "/"));//gets  resources from toplevel package and returns as a stream
    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));//wraps stream in Buffered reader to reade lines
    String line;
    int packagesTraversed= 0;//tracks how many packages have been traversed.

    while ((line = reader.readLine()) != null) {//execute only if reader is not empty

        if (line.endsWith(".class")) {//checks if resource ends in .class if true, pass string name and path to stringToClass() method to get Class.
            while(packagesTraversed > 0) {//if more than one package has been traversed, reset path the top level directory.
                path = path.substring(0,path.lastIndexOf("."));
                packagesTraversed--;
            }
            loadedClasses.add(stingToClass(line,path));//loads Class to loadedClasses Set after returning class type;


        } else {

            path += "." + line;// if no .class is found in the above if statement, line is a package and is appended to the path to create a new fully-qualified path to the package found.
            packagesTraversed++;//increment traversed packages.
            getClasses(path); //recursivley call getClasses passing the new fully qualified package name where an new resource stream is created for that package.
                              //if classes are found in the package, classes will be loaded to the loadeadClasses Set, that the path will be reset rescanning a new package.

        }

    }

}



    //takes string class name and path fully qualified path to class and converts it to a string.
    private Class<?> stingToClass(String className, String path){

        String fullyQulifedName = path + "." + className.substring(0, className.lastIndexOf("."));//concatenates path and class. replace all the "." with "/" to
                                                                                                        //to be passed to the Class.forName();
        Class classz = null;
        try {
            classz = Class.forName(fullyQulifedName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return classz;//returns class.


    }







}
