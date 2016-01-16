package reparaciones;


import java.util.HashMap;

import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;


public class InitReparaSrv extends Service {
	
	HashMap<String,String> permEmis = new HashMap<String,String>();
	
    public InitReparaSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();						 			
			
			input.addVariable("cdpantal");
			input.addVariable("idemisor");
			input.addVariable("tpclient");
			input.addVariable("logoemis");
			
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();

			output.addVariable("cdpantal");
			output.addVariable("idemisor");
			output.addVariable("tpclient");
			output.addVariable("logoemis");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor = null;
    	String tpclient = null;
    	String logoemis = null;
        
        //Varibales de salida
        Grid gridPeEm = new Grid();      
        
        //Otras Variables
     
        try{        	
        	        	
        	idemisor = input.getStringValue("idemisor");
        	tpclient = input.getStringValue("tpclient");
        	logoemis = input.getStringValue("logoemis"); 
        	
        	output.setValue("idemisor", idemisor);
        	output.setValue("tpclient", tpclient);
        	output.setValue("logoemis", logoemis);
       
        } catch (Exception e1) {
			e1.printStackTrace();
		}               
    }
       
}