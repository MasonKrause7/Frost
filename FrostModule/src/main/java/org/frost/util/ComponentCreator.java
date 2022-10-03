package org.frost.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.util.*;

//will replace the injector class
//Uses classSet from the ApplicationContainer to instantiate each Component, with its dependencies injected.
//Dependencies should be shared to create "shared state" between objects...?

//This classes constructor takes in the classSet from the ApplicationContainer, uses it to build the clientMap.
//The clientMap has keys that are the component classes, the values are the Object of that class, with its dependencies injected.
//This class only has a public constructor that takes the classSet as an argument, and uses it to create the other maps,
    //ending with the client map
//And only has one public getter method to get the clientMap.

//One note...: it only actually creates clientObjects if the constructor is marked w/ @inject...
//need to edit to where it creates each component object for the clientMap, even if it uses the default constructor to do so
//because the component class may not have a constructor with @inject but it still needs to be made.

public class ComponentCreator {


    private Set<Class<?>> classSet;
    private Map<Class, Object> componentMap;//gets the classes marked with @Component
    private Map<Constructor<?>, Class<?>[]> injectableConstructorMap;//holds the @inject constructors mapped to their parameters





    //Single constructor, only takes in the classSet, uses it to set the rest of the maps.
    public ComponentCreator(Set<Class<?>> classSet) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        this.classSet = classSet;
        createComponentMap(); //automatically using the classSet to set up the other 2 maps upon creation of a ComponentCreator
        mapInjectableConstructorsParameters();
        mapClientObjects();

    }
    public Map<Class, Object> getComponentMap(){
        return componentMap;
    }

    //uses the classSet to find the @Components, creates a new instance of each component, creates componentMap<componentClass, componentObject>
    private void createComponentMap() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Map<Class, Object> componentMap = new HashMap<>();

        for (Class classz: classSet ) {
            if (classz.getAnnotations().length > 0) { //if the class has more than one annotation... does not explicitly check for the @Component anno
                try { //try to create a new instance of classz and put them in the map.

                     Constructor<?> defaultConst = classz.getConstructor();
                    Object genericComponent = defaultConst.newInstance();
                    componentMap.put(classz, genericComponent);
                } catch (Exception e) {
                    System.out.println("Failed to instantiate a genericObject");
                    System.out.println(e.getMessage());
                }
            }
        }
        this.componentMap = componentMap;
    }
    //check each class in the component map for a Constructor w/ @Inject.
        //with the annotated constructor, get its parameter TYPES to match with the keys in component map
    private void mapInjectableConstructorsParameters(){
        Map<Constructor<?>, Class<?>[]> paramMap = new HashMap<>();

        for (Class classz: componentMap.keySet()) { //for each component class, get its constructors.
            Constructor[] constructArr = classz.getConstructors();
            for (Constructor construct: constructArr) { //for each constructor, check for @Inject
                if (construct.getAnnotations().length > 0){ //if constructor has an annotation present, DOES NOT EXPLICITLY LOOK FOR @Inject
                   paramMap.put(construct, construct.getParameterTypes()); //mapping the annotated constructor to its parameter TYPES
                }
            }
        }
        this.injectableConstructorMap = paramMap;
    }
    //NEXT STEP --> use the class[] in the injectableConstructorMap to inject the objects of the same type to that constructor.
    private void mapClientObjects() throws NullPointerException{

        //this is where I need to implement the changes to the way the clientMap is made
        //should be creating an object of every component for the client map, not just the
        //objects that take dependencies.


        for (Constructor c : injectableConstructorMap.keySet()) {
            //for each constructor in the injectableConstructorMap, get the array of parameters that are its value
            List<Object> obList = new ArrayList<>(); //creates a new list that holds each genericObject matching the parameters
            // and give it back to the constructor, so that the @inject constructor can create the clientObject.
            Class<?>[] paramTypes = injectableConstructorMap.get(c);
            for (Class<?> paramType: paramTypes) { //for each paramType in the constructors arguments, get the genericObject of that type

                Object dependencyObj = componentMap.get(paramType); //returns the generic object matching each parameter
                obList.add(dependencyObj);//adding the object to the list
            }
            Object[] obArr = obList.toArray();//once all parameter objects are added to list, convert it to array.
            try {
                Object clientObject = c.newInstance(obArr);//use the array of objects to create newInstance of client obj



                componentMap.replace(c.getDeclaringClass(),componentMap.get(c.getDeclaringClass()), clientObject);
                //^^^^^^^ replaces the genericObject in componentMap for the clientObject, which is a new instance that
                //has its dependencies injected. Updating the component map instead of mapping to the clientMap
                //allows the classes with no @inject constructor to still have an object created of their type

                //System.out.println("Added clientObj of type "+ c.getDeclaringClass().getName() +" to clientMap"); <--TEST LINE
            }
            catch(Exception e){
                System.out.println("Failed to create clientObject: "+ c.getDeclaringClass().getName());
                e.getMessage();
            }
        }
    }
}
