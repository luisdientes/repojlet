package personal;


import personal.bd.ListProyectBD;
import personal.bd.ListProyectBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import common.ExcelCreator;


public class InitTrabajadorSrv extends Service {

	ExcelCreator creador = null;
	
	public InitTrabajadorSrv() {
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
			output.addVariable("cduserid");
			output.addVariable("gridFech");
			output.addVariable("fhpulsad");
			output.addVariable("gridProy");
			output.addVariable("gridEmpl");
			output.addVariable("gridEmpr");
			output.addVariable("gridProT");
			output.addVariable("idempres");
			output.addVariable("idemisor");
			
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	Grid gridProy = null;
    	String idemisor ="";

   
        
        //Otras Variables
        
        
        try {
        	idemisor = input.getStringValue("idemisor");

        	int insertado = 0;
        	
     
        	try{
            	ListProyectBDIn listProBDIn = new ListProyectBDIn();
            	listProBDIn.setValue("idemisor", idemisor);
        		ListProyectBD listProBD = new ListProyectBD(listProBDIn);
        		listProBD.setConnection(con);
        		gridProy = listProBD.execSelect();
	
        	   }catch(Exception e1){
        		   e1.printStackTrace();
        	   }
        	
        	output.setValue("gridProy", gridProy);
        	output.setValue("idemisor", idemisor);
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
	