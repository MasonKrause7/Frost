package org.frost.util;
import org.frost.util.annotations.Controller;
import org.frost.util.annotations.GetMapping;
import org.frost.util.annotations.PostMapping;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class URLResolver {
private Map<Class, Object> componentMap = ApplicationContainer.getComponents();
private Map<String,Class> urlMap = new HashMap<>();


    public URLResolver() {
        mapUrls();

    }

    public void mapUrls() {
        for(Class classz:componentMap.keySet()) {
            if (classz.isAnnotationPresent(Controller.class)) {
                Method[] methods = classz.getMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(GetMapping.class)) {
                        urlMap.put(method.getAnnotation(GetMapping.class).value(), classz);
                        System.out.println("Url mapping is " + method.getAnnotation(GetMapping.class).value());
                    }
                    else if(method.isAnnotationPresent(PostMapping.class)) {
                        urlMap.put(method.getAnnotation(PostMapping.class).value(),classz);
                    }
                }
            }

        }
    }

    public String getRequest(String url) {
        String string = null;
        if(!url.equals("/faveicon.ico")) {
            Class<?>  classz = urlMap.get("/");
            Method[] methods = classz.getMethods();
            for(Method method : methods) {
                if(method.isAnnotationPresent(GetMapping.class) && method.getAnnotation(GetMapping.class).value().equals(url)) {
                    try {
                        string = (String) method.invoke(componentMap.get(classz));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        }
        return string;



    }


}
