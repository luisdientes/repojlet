package comercio;


import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import comercio.bd.ListEnviosBD;
import comercio.bd.ListEnviosBDIn;
import comercio.bd.UpdTaxesBD;
import comercio.bd.UpdTaxesBDIn;


public class AplicaTaxesEnvioSrv extends Service {


	

	public AplicaTaxesEnvioSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("codeenvi");
		    input.addVariable("custotax");
		    input.addVariable("idlineas");
		    input.addVariable("consutax");
		    input.addVariable("fletecst");
		    input.addVariable("itbisimp");
		    input.addVariable("tramadua");
		    input.addVariable("almacena");
		    input.addVariable("movconte");
		    input.addVariable("cargnavi");
			
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

        //Varibales de salida
    	Grid gdEnvios = null;
        String txmensaj = "";
        
        //Otras Variables
        String idlineas = "";
        String custotax = ""; 
        String consutax = ""; 
        String fletecst = ""; 
        String itbisimp = ""; 
        String tramadua = ""; 
        String almacena = ""; 
        String movconte = ""; 
        String cargnavi = "";
       
        
        try {
        	codeenvi = input.getStringValue("codeenvi");
        	
        	/* Obtengo si aplico tasas o no */
        	
        	idlineas = input.getStringValue("idlineas"); 
        	custotax = input.getStringValue("custotax");
        	consutax = input.getStringValue("consutax");
        	fletecst = input.getStringValue("fletecst");
        	itbisimp = input.getStringValue("itbisimp");
        	tramadua = input.getStringValue("tramadua");
        	almacena = input.getStringValue("almacena");
        	movconte = input.getStringValue("movconte");
        	cargnavi = input.getStringValue("cargnavi");
        	
        	try {
        		String [] arrLineas = null;
            	String [] arrCusTax = null;
            	String [] arrConTax = null;
            	String [] arrfletec = null;
            	String [] arrItbisx = null;
            	String [] arrTramad = null;
            	String [] arrAlmace = null;
            	String [] arrMovCon = null;
            	String [] arrCargaN = null;
            	
            	int numline = 0;
        
            	arrLineas = idlineas.split(",");
            	arrCusTax = custotax.split(",");
            	arrConTax = consutax.split(",");
            	arrfletec = fletecst.split(",");
            	arrItbisx = itbisimp.split(",");
            	arrTramad = tramadua.split(",");
            	arrAlmace = almacena.split(",");
            	arrMovCon = movconte.split(",");
            	arrCargaN = cargnavi.split(",");
            	
            	for(int i=0 ;i< arrLineas.length;i++){
            		
            		try{
        	    		UpdTaxesBDIn updTaxeBDIn = new UpdTaxesBDIn();
        	    		updTaxeBDIn.setValue("idlineax",arrLineas[i]); 
        	    		updTaxeBDIn.setValue("custotax",arrCusTax[i]); 
        	    		updTaxeBDIn.setValue("consutax",arrConTax[i]); 
        	    		updTaxeBDIn.setValue("fletecst",arrfletec[i]); 
        	    		updTaxeBDIn.setValue("itbisimp",arrItbisx[i]); 
        	    		updTaxeBDIn.setValue("tramadua",arrTramad[i]); 
        	    		updTaxeBDIn.setValue("almacena",arrAlmace[i]); 
        	    		updTaxeBDIn.setValue("movconte",arrMovCon[i]); 
        	    		updTaxeBDIn.setValue("cargnavi",arrCargaN[i]); 
        	
        	    		UpdTaxesBD updLinBD = new UpdTaxesBD(updTaxeBDIn);
        	    		updLinBD.setConnection(con);
        	    		numline+= updLinBD.execInsert();
        	    		
        	    		
        	    		
        	    		
            		 }catch(Exception ex){
            			 System.out.println("----Error------ Al insertar Lineas en las tasas");
            		 }	
            		
            	}
            	if(numline >0){
            		System.out.println("Se han actualizado  "+ numline +" lineas en las tasas");
            	}else{
            		System.out.println("Error al actualizar las tasas");
            	}
            	
            	ListEnviosBDIn envioBDIn = new ListEnviosBDIn();
    			//envioBDIn.setValue("codeenvi", codeenvi);
    			ListEnviosBD envioBD = new ListEnviosBD(envioBDIn);
    			envioBD.setConnection(con);
    			gdEnvios = envioBD.execSelect();
            	
        		
        	} catch (Exception e) {
        		System.err.println(this.getClass().getName() +" [ERROR ] Recuperando envíos. "+ e.getMessage());
        	}
        	
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
	