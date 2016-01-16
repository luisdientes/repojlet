package clientes;


import java.util.HashMap;

import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

public class InitClienteSrv extends Service {
	
	HashMap<String,String> permEmis = new HashMap<String,String>();
	
    public InitClienteSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();						 			
			
			input.addVariable("cdpantal");
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

			output.addVariable("cdpantal");
			output.addVariable("idemisor");
			output.addVariable("tpclient");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String pantalla = null;
    	String idemisor = null;
    	String tpclient = null;
        
        //Varibales de salida
        Grid gridPeEm = new Grid(); 
        
        //Otras Variables
     
        try{        	
        	        	
        	pantalla = input.getStringValue("cdpantal");
        	idemisor = input.getStringValue("idemisor");
        	tpclient = input.getStringValue("tpclient");
        	
        	output.setValue("cdpantal", pantalla);
        	output.setValue("idemisor", idemisor);
        	output.setValue("tpclient", tpclient);
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
       
}
