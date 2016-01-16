package personal;


import personal.bd.ListProyectBD;
import personal.bd.ListProyectBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import common.ExcelCreator;


public class ListProyectosSrv extends Service {

	ExcelCreator creador = null;
	
	public ListProyectosSrv() {
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
			output.addVariable("gridProy");
			output.addVariable("txmensaj");
			
			
		
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	Grid gridProy = null;
    	String idemisor = "";
        //Otras Variables
        
        
        try {
        	idemisor = input.getStringValue("idemisor");
        	
            	ListProyectBDIn listProBDIn = new ListProyectBDIn();
            	listProBDIn.setValue("idemisor", idemisor);
        		ListProyectBD listProBD = new ListProyectBD(listProBDIn);
        		listProBD.setConnection(con);
        		gridProy = listProBD.execSelect();
	
        	output.setGrid("gridProy", gridProy);
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
	