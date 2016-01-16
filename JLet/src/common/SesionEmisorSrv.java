package common;


import arquitectura.controller.Service;
import arquitectura.objects.ObjectIO;


public class SesionEmisorSrv extends Service {

    public SesionEmisorSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();			
			input.addVariable("idemisor");
			input.addVariable("tpclient");

		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor = "";
    	String tpclient = "";
        
    	//Otras Variables
     
        try {        	        	        	        	
        	
        	idemisor = input.getStringValue("idemisor");
        	tpclient = input.getStringValue("tpclient");
        	sesion.setAttribute("idemisor",idemisor);
        	sesion.setAttribute("tpclient",tpclient);
        	
        	System.out.println(" Se ha establecido el emisor: "+ idemisor +" y tipocliente ="+tpclient+" en la sesion del usuario. "+ username);
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
}
	