package personal;


import personal.bd.ListEmpleadosBD;
import personal.bd.ListEmpleadosBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import common.ExcelCreator;


public class ListPersonalSrv extends Service {

	ExcelCreator creador = null;
	
	public ListPersonalSrv() {
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
			output.addVariable("gridEmpl");
			output.addVariable("txmensaj");
			
			
		
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	Grid gridEmpl = null;
    	String idemisor ="";
        //Otras Variables
        
        
        try {
        	idemisor = input.getStringValue("idemisor");
     
        	try{
            	/* emisor*/
        		
        		ListEmpleadosBDIn listEmpBDIn = new ListEmpleadosBDIn();
        		listEmpBDIn.setValue("idemisor",idemisor);
        		ListEmpleadosBD listEmpBD = new ListEmpleadosBD(listEmpBDIn);
        		listEmpBD.setConnection(con);
        		gridEmpl = listEmpBD.execSelect();
	
        	   }catch(Exception e1){
        		   e1.printStackTrace();
        	   }
        	output.setValue("idemisor", idemisor);
        	output.setGrid("gridEmpl", gridEmpl);
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
	