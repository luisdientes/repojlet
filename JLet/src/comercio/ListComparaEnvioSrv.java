package comercio;


import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import comercio.bd.ListComparaEnvioBD;
import comercio.bd.ListComparaEnvioBDIn;

import factura.bd.ListPhonesBD;
import factura.bd.ListPhonesBDIn;
import factura.bd.ListProductsBD;
import factura.bd.ListProductsBDIn;


public class ListComparaEnvioSrv extends Service {


	public ListComparaEnvioSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("codeenvi");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
			output.addVariable("gdEnvios");
			output.addVariable("gridPhon");
			output.addVariable("gridProd");
			output.addVariable("txmensaj");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String codeenvi 	= "";

        //Varibales de salida
    	Grid gdEnvios = null;
    	Grid gridPhon= null;
        Grid gridProd= null;
        String txmensaj = "";
        
        //Otras Variables
       
        
        try {
        	
        	codeenvi = input.getStringValue("codeenvi");
        	
        	try {
        		ListComparaEnvioBDIn envioBDIn = new ListComparaEnvioBDIn();
        		envioBDIn.setValue("codeenvi", codeenvi);
        		ListComparaEnvioBD envioBD = new ListComparaEnvioBD(envioBDIn);
        		envioBD.setConnection(con);
        		gdEnvios = envioBD.execSelect();
        		
        	   	//TELEFONOS
            	ListPhonesBDIn phonBDIn = new ListPhonesBDIn();
            	ListPhonesBD phonBD = new ListPhonesBD(phonBDIn);
            	phonBD.setConnection(con);
            	gridPhon = phonBD.execSelect();
            	
            	//PRODUCTS
            	ListProductsBDIn prodBDIn = new ListProductsBDIn();
            	ListProductsBD prodBD = new ListProductsBD(prodBDIn);
            	prodBD.setConnection(con);
            	gridProd = prodBD.execSelect();
        		
        	} catch (Exception e) {
        		System.err.println(this.getClass().getName() +" [ERROR ] Recuperando envíos. "+ e.getMessage());
        	}
    		
    		output.setValue("gdEnvios", gdEnvios);
    		output.setGrid("gridPhon", gridPhon);
        	output.setGrid("gridProd", gridProd);
		    output.setValue("txmensaj", txmensaj);
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
    
    public String fechaMySQL(String fhfechax){
        
		String fhMySql = "";
		try {
			String [] arrFecha = fhfechax.split("/");
			fhMySql = arrFecha[2] +"-"+ arrFecha[1] +"-"+ arrFecha[0];
		} catch (Exception e) {
			return "0000-00-00";
		}
		return fhMySql;
    }
       
}
	