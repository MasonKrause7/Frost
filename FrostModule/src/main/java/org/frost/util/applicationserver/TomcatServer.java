package org.frost.util.applicationserver;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.Context;

public class TomcatServer {

    private Tomcat tomcat = new Tomcat();

    public void start(){
        tomcat.setPort(8080);
        tomcat.getConnector();
        Context context = tomcat.addContext("",null);
        Tomcat.addServlet(context,"dispatcherServlet", new DispatcherServlet());
        context.addServletMappingDecoded("/","dispatcherServlet");

        try {
            tomcat.start();
            System.out.println("starting new thread for tomcat");
            new Thread(()-> tomcat.getServer().await());

        } catch (LifecycleException e) {
            e.printStackTrace();
        }

    }



}
