package subasta;


import java.util.HashMap;

import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import common.bd.ListPaisesBD;
import common.bd.ListPaisesBDIn;


public class InitSubastaSrv extends Service {
	
	HashMap<String,String> permEmis = new HashMap<String,String>();
	
    public InitSubastaSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();						 			
			
			input.addVariable("cdpantal");
			
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
			output.addVariable("gdPaises");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String pantalla = null;
        
        //Varibales de salida
        Grid gdPaises = new Grid(); 
        
        //Otras Variables
     
        try{        	
        	        	
        	pantalla = input.getStringValue("cdpantal");
        	System.out.println("Pantalla: "+ pantalla);
        	
        	permEmis = (HashMap<String, String>) sesion.getAttribute("permEmis");
        	
        	try {
        		ListPaisesBDIn paisesBDIn = new ListPaisesBDIn();
        		paisesBDIn.setValue("mcactivo", "S");
        		ListPaisesBD paisesBD = new ListPaisesBD(paisesBDIn);
        		paisesBD.setConnection(con);
        		gdPaises = paisesBD.execSelect();
        		
        	} catch (Exception e) {
        		System.err.println(this.getClass().getName() +" [ERROR ] Recuperando subastas. "+ e.getMessage());
        	}
        	
        	
        	output.setValue("cdpantal", pantalla);
        	output.setValue("gdPaises", gdPaises);
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
       
}