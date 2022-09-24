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

    //contains classes that are marked with @component
    private Map<Class, Class> classMap= new HashMap<>();
    //contains parameters of constructors marked with @Inject
    private Map<Class, Class[]> parameterTypes = new HashMap<>();

    private Map<Class, Object> mappedObjects = new HashMap();




    public Injector(){

    }
    public Injector(Set<Class<?>> classSet){
        this.classSet = classSet;
    }
    public void setClassSet(Set<Class<?>> classSet){
        this.classSet = classSet;
    }

    public Map<Class, Object> getMappedObjects() {
        return mappedObjects;
    }


    public void mapClassObjects() throws Exception {
        getComponentClasses();
        System.out.println("all classes marked with @Component " + classMap.keySet());
        getComponentConstructors();
        System.out.println("constructor parameters for all classes marked with @Inject " + parameterTypes.entrySet());
        createParameterObjects();
        System.out.println("Objects created for users " + mappedObjects.entrySet());

    }

    private void createParameterObjects() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        for(Class classz : parameterTypes.keySet()) {
            ArrayList<Object> objectList = new ArrayList<>();
            Class[] paramters = parameterTypes.get(classz);

            for(Class parameter: paramters) {
                Constructor constructor = parameter.getConstructor();
                Object object = constructor.newInstance();
                objectList.add(object);

            }

            Object[] objectParameters = objectList.toArray();
            Constructor clientConstructor = classz.getConstructor(paramters);
            Object clientObject = clientConstructor.newInstance(objectParameters);
            mappedObjects.put(classz,clientObject);
        }

    }

    private void getComponentConstructors() {
        for(Class classz : classMap.keySet()) {

            Constructor[] constructors = classz.getConstructors();

            for(Constructor constructor : constructors) {
                if(constructor.isAnnotationPresent(Inject.class)) {
                    Class[] parameters = constructor.getParameterTypes();
                      parameterTypes.put(classz,parameters);
                }
            }

        }
    }

    private void getComponentClasses() {


            for(Class classz : classSet) {
                if(classz.isAnnotationPresent(Component.class)) {
                    classMap.put(classz,classz);

                }


            }


        }

}
