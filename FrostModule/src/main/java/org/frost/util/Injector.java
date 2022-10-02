package org.frost.util;

import org.w3c.dom.ls.LSOutput;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.lang.annotation.*;

//the purpose of this class is to be called by the application container
//to scan the classSet and inject the dependencies for each component class and then instantiate the
//object of the class, and map it as the value with the class name as the type.
public class Injector {

    private Set<Class<?>> classSet;

    private Map<Class, Class> classMap = new HashMap<>();//Map of classes that are only annotated with @Component

    private Map<Class, Class[]> parameterTypes = new HashMap<>(); //Map where key is the class, and the value is an array of parameters that were found in the
    //constructor annotated with @Inject

    private Map<Class, Object> mappedObjects = new HashMap();//key is the class, and the value is the instantiated object with dependencies.


    public Injector() {

    }

    public Injector(Set<Class<?>> classSet) {
        this.classSet = classSet;
    }

    public void setClassSet(Set<Class<?>> classSet) {
        this.classSet = classSet;
    }

    public Map<Class, Object> getMappedObjects() {
        return mappedObjects;
    }// getter method to make map accessible to application container


    public void mapClassObjects() throws Exception {
        getComponentClasses();
        System.out.println("all classes marked with @Component " + classMap.keySet());
        getComponentConstructors();
        System.out.println("constructor parameters for all classes marked with @Inject " + parameterTypes.entrySet());
        createParameterObjects();
        System.out.println("Objects created for users " + mappedObjects.entrySet());

    }


    //creates objects to be added to the mappedObjects map.
    private void createParameterObjects() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //iterates through parameters types keys which is the class.
        for (Class classz : parameterTypes.keySet()) {
            //list to add parameter objects.
            ArrayList<Object> objectList = new ArrayList<>();
            // get the parameters of the current class type.
            Class[] paramters = parameterTypes.get(classz);
            //iterate to parameters and instiate each one, then add object to objectList;
            for (Class parameter : paramters) {
                Constructor constructor = parameter.getConstructor();//if class needs object parameter dependency, iterate and get paramters.
                Object object = constructor.newInstance();
                objectList.add(object);

            }

            //convert object list to an array
            Object[] objectParameters = objectList.toArray();
            //get specific constructor of class by passing the array objectParameters to getConstructor. the getConstructor method
            //takes an array of classes and will return the specific constructor that matched the arraytypes.
            Constructor clientConstructor = classz.getConstructor(paramters);

            //create new instance of object passing in the objectParameters as dependencies;
            Object clientObject = clientConstructor.newInstance(objectParameters);
            mappedObjects.put(classz, clientObject);
        }

    }

    private void getComponentConstructors() {
        //scans classMap and only gets the parameters of the constructor annotated with @Injecta and adds them to the parameterTypesMap.
        for (Class classz : classMap.keySet()) {

            Constructor[] constructors = classz.getConstructors();

            for (Constructor constructor : constructors) {
                if (constructor.isAnnotationPresent(Inject.class)) {//need to add validiations to ensure only one constructor or field has @Inject annotation
                    Class[] parameters = constructor.getParameterTypes();
                    parameterTypes.put(classz, parameters);
                }
            }

        }
    }

    private void getComponentClasses() {

        //scans classess and only add classes that are annotated with @Component to the classMap
        for (Class classz : classSet) {
            if (classz.isAnnotationPresent(Component.class)) {
                classMap.put(classz, classz);

            }


        }


    }

}
