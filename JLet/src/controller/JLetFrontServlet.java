package controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import arquitectura.controller.FrontServlet;

public class JLetFrontServlet extends FrontServlet {       

    public void init(ServletConfig config) throws ServletException {
        //super.init(config);
    }    
    
    public void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException ,IOException  {
    	
    	try {
	    	super.doPost(request,response);
    	} catch (Exception e) {
    		
    	} finally {
    		
    	}
    	
    }
    
    protected String getControllerPackage() {
    	return "controller";
    }
    
}
