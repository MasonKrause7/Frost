package org.frost.util.applicationserver;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.frost.util.ApplicationContainer;
import org.frost.util.URLResolver;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * will handle and forward all request to proper objects to invoke methods to get data
 */
public class DispatcherServlet extends HttpServlet {

    private URLResolver resolver = new URLResolver();

    /**
     *
     * @param req   an {@link HttpServletRequest} object that
     *                  contains the request the client has made
     *                  of the servlet
     *
     * @param resp  an {@link HttpServletResponse} object that
     *                  contains the response the servlet sends
     *                  to the client
     *
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(!req.getRequestURI().equals("/favicon.ico")) {//ignores favicon.ico
            System.out.println(resolver.getRequest(req.getRequestURI()));
            PrintWriter writer = resp.getWriter();//used to wrap response in character streams
            resp.setContentType("application/json");//content type must be set in order for browser to accept response
            resp.addHeader("Access-Control-Allow-Origin", "*");//enable cors so all origins can access resource
            resp.setCharacterEncoding("UTF-8");
            writer.print(resolver.getRequest(req.getRequestURI()));//wraps response in character stream
        }


    }


}
