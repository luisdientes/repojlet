package comercio;


import arquitectura.controller.Service;
import arquitectura.objects.ObjectIO;

import comercio.bd.UpdStockBD;
import comercio.bd.UpdStockBDIn;
import common.ExcelCreator;


public class ValidarStockLSrv extends Service {

	ExcelCreator creador = null;
	
	String stfixing = "";
	
	public ValidarStockLSrv() {
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
			output.addVariable("txmensaj");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada

        //Varibales de salida
    	String idemisor = "";
        String txmensaj = "";
        
        //Otras Variables
        
        try {
        	
        	idemisor = input.getStringValue("idemisor");
        	
			UpdStockBDIn upStockBDIn = new UpdStockBDIn();
			upStockBDIn.setValue("cdestado", "STOCK");
			upStockBDIn.setValue("oldcdest", "PRESTOCK");
			upStockBDIn.setValue("idemisor", idemisor);
			
		
			UpdStockBD updStockBD = new UpdStockBD(upStockBDIn);
			updStockBD.setConnection(con);
			int nlineasx = updStockBD.execUpdate();
			
			if(nlineasx >0){
				txmensaj = "Stock validado correctamente";
			}else{
				txmensaj = "Fallo al validar el stock";
			}
			
			
			output.setValue("idemisor", idemisor);
			output.setValue("txmensaj", txmensaj);
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
}
	