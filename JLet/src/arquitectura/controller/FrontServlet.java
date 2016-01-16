/*
 * FrontServlet.java
 *
 * Created on 4 de abril de 2002, 14:09
 */
 
package arquitectura.controller;           

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class FrontServlet extends HttpServlet {

    private static final String CONTROLLER_PARAMETER = "controller";
    //private static final String CONTROLLER_SUFFIX = "Handler";
         
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    
    protected abstract String getControllerPackage();
    
    protected Collection getControllerPackages() {

        ArrayList packages = new ArrayList();
        packages.add(getControllerPackage());
        return packages;
    }


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException{
        try {
            
            String controllerName = request.getParameter("controller");
            if(controllerName==null){
                throw new Exception("Controller not specified");
            }
            
            Controller controller = getControllerInstance(controllerName);
            controller.process(request, response);
            
        } catch (Exception e){
            e.printStackTrace();
            throw new ServletException(e.getClass().getName() + " " + e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException {
        processRequest(request, response);
    } 

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    }

    public String getServletInfo() {
        return "Front Controller Servlet.";
    }

    private Controller getControllerInstance(String controllerName)  
    throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Exception lastException = null;
        Controller controller = null;

        Iterator i = getControllerPackages().iterator();
        String message = "";
        while (i.hasNext() && controller==null) {
            String pack = (String) i.next();

            try {
                Class cls = Class.forName(pack + "." + controllerName);
                controller = (Controller)cls.newInstance();
            } catch (Exception e) {

            	controller = null;
                lastException = e;
                message = message + e.getMessage() + "\n";
            }
        }
        if (controller==null) {
            if (lastException==null){
                message = "Package not found for controller";
            }
            throw new ClassNotFoundException(message);
        }
        return controller;
    }
    
}
