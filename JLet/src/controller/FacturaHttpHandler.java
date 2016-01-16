package controller;

import arquitectura.objects.ObjectIO;
import arquitectura.webchannel.HttpHandler;


public class FacturaHttpHandler  extends HttpHandler{

	 private static final String servicePackage      = "factura";
	 //viewPackage VACIO
	 private static final String viewPackage         = "";
	 private static final String errorView           = "/comun/jsp/errorPrincipal.jsp";
	 private static final String formatBundleName    = "web.controller.Format";
	    
	 
   	/** Creates new NehmerHttpHandler */
    public FacturaHttpHandler() {
    }

    // Especificar el paquete desde el que se cargan los servicios
    protected String getServicePackage() {
        return servicePackage;
    }
        
    // Especificar el paquete desde el que se cargan las vistas
    protected String getViewPackage() {
        return viewPackage;
    }
           
    protected String getErrorView() {
        return errorView;
    }

    // Extender en las clases inferiores para cambiar el formateo
    protected String getFormatBundleName(){
        return formatBundleName;
    }

	
	public void buildInterface(ObjectIO output, ObjectIO format)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
		
	    
}
