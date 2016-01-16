package common;


import arquitectura.controller.Service;
import arquitectura.objects.ObjectIO;


public class InitMenuPrincSrv extends Service {

    public InitMenuPrincSrv() {
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
			output.addVariable("cdpadrex");
			output.addVariable("txmensaj");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String cdpantal = "";
        
        //Varibales de salida
    	String cdpadrex = "";

    	//Otras Variables
     
        try {        	        	        	        	
        	
        	cdpantal = input.getStringValue("cdpantal");
        	cdpadrex = cdpantal;
        	
		    output.setValue("cdpadrex", cdpadrex);		    
		    output.setValue("txmensaj", "OK");

        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
}
	