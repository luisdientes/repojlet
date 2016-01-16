package comercio;


import arquitectura.controller.Service;
import arquitectura.objects.ObjectIO;

import comercio.bd.UpdLineasIdProdBD;
import comercio.bd.UpdLineasIdProdBDIn;
import common.ExcelCreator;


public class AsignaProdEnvioSrv extends Service {

	ExcelCreator creador = null;
	
	public AsignaProdEnvioSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("idproenv");
			input.addVariable("idproduc");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
			output.addVariable("gridLine");
			output.addVariable("gridDivi");
			output.addVariable("txmensaj");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	
    	String idproenv = "";
    	String idproduc = "";

        //Varibales de salida
        String txmensaj = "";
        
        //Otras Variables
        
        
        try {
        	idproenv = input.getStringValue("idproenv");
        	idproduc = input.getStringValue("idproduc");
        	int actualiza = 0;
        	
        	String idlinea[] = idproenv.split(",");
        	String idpro[] 	 = idproduc.split(",");
        	
        	try{
        		
        		for (int i=0; i< idlinea.length;i++){
        			UpdLineasIdProdBDIn upIdProdBDIn = new UpdLineasIdProdBDIn();
        			upIdProdBDIn.setValue("codeenvi",idlinea[i]);
        			upIdProdBDIn.setValue("idproduc",idpro[i]);
        			UpdLineasIdProdBD upIdProdBD = new UpdLineasIdProdBD(upIdProdBDIn);
        			upIdProdBD.setConnection(con);
        			
        			actualiza = upIdProdBD.execUpdate();
					
        			if( actualiza >0){
						System.out.println("Linea actualizada correctamente");
        		
					}else{
						System.err.println("ERROR ----- Fallo al actualizar la linea -- ERROR");
					}
        		}	
				
        	   }catch(Exception e1){
        		   e1.printStackTrace();
        	   }
        	
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
	