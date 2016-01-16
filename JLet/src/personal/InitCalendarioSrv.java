package personal;


import personal.bd.ListFechasBD;
import personal.bd.ListFechasBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import common.ExcelCreator;


public class InitCalendarioSrv extends Service {

	ExcelCreator creador = null;
	
	public InitCalendarioSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();	
			input.addVariable("idemplea");
			
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
			output.addVariable("tprolxxx");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	Grid gridFech 	= null;
    	String cduserid = null;
    	String idemplea = null;

   
        
        //Otras Variables
        
        
        try {
        	
        	idemplea = input.getStringValue("idemplea");
        	
        	if(idemplea!=null && !idemplea.equals("")){
        		cduserid = idemplea;
        	}else{
        		cduserid = (String) sesion.getAttribute("idusuari");
        	}
        	
        	System.out.println("usuario----------------------------- = "+cduserid);
        	
     
        	try{
        		ListFechasBDIn fhDatosBDIn = new ListFechasBDIn();
        		fhDatosBDIn.setValue("cduserid",cduserid);
        		ListFechasBD fhDatosBD = new ListFechasBD(fhDatosBDIn);
        		fhDatosBD.setConnection(con);
        		gridFech = fhDatosBD.execSelect();
	
        	   }catch(Exception e1){
        		   e1.printStackTrace();
        	   }
        	
        	output.setValue("gridFech", gridFech);
        	output.setValue("cduserid", cduserid);
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
	