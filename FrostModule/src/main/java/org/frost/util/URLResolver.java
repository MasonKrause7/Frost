package org.frost.util;
import com.google.gson.Gson;
import org.frost.util.annotations.Controller;
import org.frost.util.annotations.GetMapping;
import org.frost.util.annotations.PostMapping;
import org.frost.util.annotations.RequestBody;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * maps url paths to appropriate class which will later be used to access the objects in the component map to invoke methods.
 */

public class URLResolver {
    /**
     * library used to convert method return data to json
     */
    private Gson gson = new Gson();

    /**
     * componentMap from the application container
     */
    private Map<Class, Object> componentMap = ApplicationContainer.getComponents();

    /**
     * Maps url path to the corresponding class.
     */
    private Map<String,Class> urlMap = new HashMap<>();

    /**
     * maps all urls upon construction of object
     */
    public URLResolver() {
        mapUrls();

    }

    /**
     * maps all url paths to corresponding classes.
     */
    public void mapUrls() {
        for(Class classz:componentMap.keySet()) {//iterate through component map
            if (classz.isAnnotationPresent(Controller.class)) {//check for controller annotation
                Method[] methods = classz.getMethods();//get all methods of the class
                for (Method method : methods) {//iterate through all methods
                    if (method.isAnnotationPresent(GetMapping.class)) {//check if GetMapping annotation is present
                        urlMap.put(method.getAnnotation(GetMapping.class).value(), classz);//get the annotations value(ex.("/home") and store it in map as key, and the class as the value

                    }
                    else if(method.isAnnotationPresent(PostMapping.class)) {//same as get mapping but with PostMapping
                        urlMap.put(method.getAnnotation(PostMapping.class).value(),classz);
                    }
                }
            }

        }
    }

    /**
     * Any get request will use this method to invoke the proper methods
     * @param url
     * @return
     */

    public String getRequest(String url) {
        String string = null;//set to null, will append string response later after return is converted to json
        if(!url.equals("/faveicon.ico")) {//ignore favicon path
            Class<?>  classz = urlMap.get(url);//pass the url argument to the urlMap to get the appropriate class. these were mapped previously by the mapUrls method
            Method[] methods = classz.getMethods();//get all the methods of that class
            for(Method method : methods) {
                if(method.isAnnotationPresent(GetMapping.class) && method.getAnnotation(GetMapping.class).value().equals(url)) {//iterate through and find the get mapping annotation and get the value of the annotation and compare
                                                                                                                                 //it to the url argument if true invoke method.
                    try {
                        string = gson.toJson(method.invoke(componentMap.get(classz)));//method.invoke() requires the object the method is in to order for it to work.
                                                                                      //so we pass the class that we are in to the componentMap to get to proper object to pass
                                                                                        // method is wrapped in the gson.toJson method to convert returned data to json
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
            }


        }
        return string; // json string sent back to dispatcher servlet

    }

    public void postRequest(String item, String path) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        System.out.println("path " + path);
        Class c = urlMap.get(path);
        Method[] methods = c.getMethods();
        for(Method method: methods) {
            if(method.isAnnotationPresent(PostMapping.class) && method.getAnnotation(PostMapping.class).value().equals(path)) {
                Class[] classes = method.getParameterTypes();
                Class classz = classes[0];
                Object ob = gson.fromJson(item,classz);

                try {
                    method.invoke(componentMap.get(c),ob);
                } catch (InvocationTargetException ex) {
                    System.out.println(ex.getCause());
                }










            }

        }

    }




}
