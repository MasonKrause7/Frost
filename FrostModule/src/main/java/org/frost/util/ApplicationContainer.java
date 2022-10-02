package org.frost.util;

import org.frost.util.applicationserver.TomcatServer;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.lang.annotation.*;

public class ApplicationContainer {
    private static TomcatServer tomcatServer = new TomcatServer();

    private static Map<Class, Object> clientObjects = new HashMap<>();


    public static void start(Class<?> mainClass) throws IOException {

        PackageScanner packageScanner = new PackageScanner(mainClass);
        Set<Class<?>> classSet = packageScanner.scan();
        Injector injector = new Injector(classSet);

        try {
            injector.mapClassObjects();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        clientObjects = injector.getMappedObjects();

    }


}






