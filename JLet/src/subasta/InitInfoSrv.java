package subasta;


import java.util.HashMap;

import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import common.bd.ListPaisesBD;
import common.bd.ListPaisesBDIn;


public class InitInfoSrv extends Service {
	
	HashMap<String,String> permEmis = new HashMap<String,String>();
	
    public InitInfoSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();						 			
			
			input.addVariable("tipoinfo");
			input.addVariable("idcodsub");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();

			output.addVariable("tipoinfo");
			output.addVariable("idcodsub");
			output.addVariable("gdPaises");
			output.addVariable("resualta");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String tipoinfo = null;
    	String idcodsub = null;
        
        //Varibales de salida
    	String resualta = "NEW";
        Grid gdPaises = new Grid();      
        
        //Otras Variables
     
        try{        	
        	        	
        	tipoinfo = input.getStringValue("tipoinfo");
        	idcodsub = input.getStringValue("idcodsub");
        	
        	try {
        		ListPaisesBDIn paisesBDIn = new ListPaisesBDIn();
        		paisesBDIn.setValue("mcactivo", "S");
        		ListPaisesBD paisesBD = new ListPaisesBD(paisesBDIn);
        		paisesBD.setConnection(con);
        		gdPaises = paisesBD.execSelect();
        		
        	} catch (Exception e) {
        		System.err.println(this.getClass().getName() +" [ERROR ] Alta Info Subasta. "+ e.getMessage());
        	}
        	
        	output.setValue("idcodsub", idcodsub);
        	output.setValue("tipoinfo", tipoinfo);
        	output.setValue("gdPaises", gdPaises);
        	output.setValue("resualta", resualta);
        	
        	
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
       
}