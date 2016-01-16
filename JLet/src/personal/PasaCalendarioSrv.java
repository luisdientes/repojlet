package personal;



import personal.bd.ListFechasBD;
import personal.bd.ListFechasBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import common.ExcelCreator;


public class PasaCalendarioSrv extends Service {

	ExcelCreator creador = null;
	
	public PasaCalendarioSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();	
			input.addVariable("tipoda");				 
			input.addVariable("cduserid");						 
			input.addVariable("fhpulsad");					 
			input.addVariable("fhdesdex");					 
			input.addVariable("fhhastax");
			input.addVariable("idproyec");
			input.addVariable("idempres");
			
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
			output.addVariable("txmensaj");
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
    	Grid gridProy = null;
    	Grid gridEmpr = null;
    	String tipodato  = "";
        String fhpulsad  = "";
        String fhdesdex  = "";
        String fhhastax  = "";
        String txmesxxx  = "";
        String txanioxx  = "";
        String idproyec	=  "";
        String idempres	 = "";
        Grid gridFech = null;
 

   
        
        //Otras Variables
        
        String cduserid = "";
        String idtrabaj = "";
        
        
        try {
     
        	//cduserid = (String) sesion.getAttribute("idusuari");
        	cduserid = input.getStringValue("cduserid");
        	//RECUPERO LOS VALORES DEL INPUT
        	idproyec = input.getStringValue("idproyec");
        	fhpulsad = input.getStringValue("fhpulsad");
        	fhdesdex = input.getStringValue("fhdesdex");
        	fhhastax = input.getStringValue("fhhastax");
        	idempres = input.getStringValue("idempres");
        	
     
        	try{
        		ListFechasBDIn fhDatosBDIn = new ListFechasBDIn();
        		fhDatosBDIn.setValue("cduserid",cduserid);
        		ListFechasBD fhDatosBD = new ListFechasBD(fhDatosBDIn);
        		fhDatosBD.setConnection(con);
        		gridFech = fhDatosBD.execSelect();
	
        	   }catch(Exception e1){
        		   e1.printStackTrace();
        	   }
        	output.setValue("fhpulsad", fhpulsad);
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
	