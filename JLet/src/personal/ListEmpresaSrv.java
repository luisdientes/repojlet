package personal;


import personal.bd.ListEmpreBD;
import personal.bd.ListEmpreBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import common.ExcelCreator;


public class ListEmpresaSrv extends Service {

	ExcelCreator creador = null;
	
	public ListEmpresaSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();	
			input.addVariable("idemisor");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
			output.addVariable("idemisor");
			output.addVariable("gridEmpr");
			output.addVariable("txmensaj");
			
			
		
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	Grid gridEmpr = null;
    	String idemisor = null;
        //Otras Variables
        
        
        try {
        	idemisor = input.getStringValue("idemisor");
  
     
        	try{
            	/* emisor*/
        		
        		ListEmpreBDIn listEmpBDIn = new ListEmpreBDIn();
        		listEmpBDIn.setValue("idemisor", idemisor);
        		ListEmpreBD listEmpBD = new ListEmpreBD(listEmpBDIn);
        		listEmpBD.setConnection(con);
        		gridEmpr = listEmpBD.execSelect();
	
        	   }catch(Exception e1){
        		   e1.printStackTrace();
        	   }
        	output.setGrid("gridEmpr", gridEmpr);
        	output.setValue("idemisor", idemisor);
        	
        	output.setValue("txmensaj", "OK");
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
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
}
	