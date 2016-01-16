package recibos;


import recibos.bd.UpdRecibosBD;
import recibos.bd.UpdRecibosBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import common.ExcelCreator;


public class ListRecibosSrv extends Service {

	ExcelCreator creador = null;
	
	public ListRecibosSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("idemisor");
			input.addVariable("tpclient");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
			output.addVariable("grListRe");
			output.addVariable("txmensaj");
		
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	
    	String idclient = ""; 
    	String idemisor = ""; 
    	String tpclient = ""; 
    
 

        //Varibales de salida
 
    	Grid grListRe = null;
        String maxgimna = "";
        
        //Otras Variables
        
        
        try {
        	idemisor = input.getStringValue("idemisor");
        	tpclient = input.getStringValue("tpclient");
   
        
        	try{
        		UpdRecibosBDIn listReciboBDIn = new UpdRecibosBDIn();
				listReciboBDIn.setValue("idemisor",idemisor);
				listReciboBDIn.setValue("tpclient",tpclient);
				UpdRecibosBD listReciboBD = new UpdRecibosBD(listReciboBDIn);
				listReciboBD.setConnection(con);
    			
    			grListRe = listReciboBD.execSelect();
        		
        	   }catch(Exception e1){
        		   e1.printStackTrace();
        	   }
        	

        	output.setValue("grListRe", grListRe);
        	output.setValue("txmensaj", "OK");
        	output.setValue("idclient", maxgimna);
        
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
}
	