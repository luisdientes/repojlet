package comercio;


import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import comercio.bd.ListFixingBD;
import comercio.bd.ListFixingBDIn;


public class InitDivisasSrv extends Service {

    public InitDivisasSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();			

		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
			output.addVariable("txmensaj");
			output.addVariable("gdDivisa");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada

        
        //Varibales de salida
    	Grid gdDivisa = null;
        //Otras Variables
     
        try {
        	
        	ListFixingBDIn fixingBDIn = new ListFixingBDIn();
        	fixingBDIn.setValue("cddivisa","USD");
        	ListFixingBD fixingBD = new ListFixingBD(fixingBDIn);
        	fixingBD.setConnection(con);
        	gdDivisa = fixingBD.execSelect();
        	
		    output.setValue("txmensaj", "OK");
		    output.setValue("gdDivisa", gdDivisa);
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
    /*
	public String fechaMySQL(String fhfechax){
        
		String fhMySql = "";
		try {
			String [] arrFecha = fhfechax.split("/");
			fhMySql = arrFecha[2]+"-"+ arrFecha[1]+"-"+arrFecha[0];
		} catch (Exception e) {
			return "0000-00-00";
		}
		return fhMySql;
    }
    */ 
}
	