package comercio;


import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import comercio.bd.ListEnviosBD;
import comercio.bd.ListEnviosBDIn;

public class ListEnvioSrv extends Service {

	public ListEnvioSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("codeenvi");
			input.addVariable("idemisor");
			input.addVariable("fhdesdex");
			input.addVariable("fhhastax");
			
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
			output.addVariable("txmensaj");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String codeenvi 	= "";
    	String idemisor 	= "";
    	String fhdesdex 	= "";
    	String fhhastax 	= "";

        //Varibales de salida
    	Grid gdEnvios = null;
        String txmensaj = "";
        
        //Otras Variables
       
        
        try {
        	

        	
        	System.out.println(this.getClass().getName() +"FLAG 0");
        	
        	codeenvi = input.getStringValue("codeenvi");
        	idemisor = input.getStringValue("idemisor");
        	

        	
        	System.out.println(this.getClass().getName() +"FLAG 1 "+ codeenvi);
        	
        	try {
        		ListEnviosBDIn envioBDIn = new ListEnviosBDIn();
        		envioBDIn.setValue("codeenvi", codeenvi);
        		envioBDIn.setValue("idemisor", idemisor);
        		//SIN INCLUIR EN LA QUERY
        		envioBDIn.setValue("fhdesdex", fhdesdex);
        		envioBDIn.setValue("fhhastax", fhhastax);
        		ListEnviosBD envioBD = new ListEnviosBD(envioBDIn);
        		envioBD.setConnection(con);
        		gdEnvios = envioBD.execSelect();
        		
        	} catch (Exception e) {
        		System.err.println(this.getClass().getName() +" [ERROR ] Recuperando envíos. "+ e.getMessage());
        	}

        	
        	System.out.println(this.getClass().getName() +"FLAG 2");
        	
    		output.setValue("gdEnvios", gdEnvios);
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
	