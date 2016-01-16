package comercio;


import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import comercio.bd.ListSedeDepositoBD;
import comercio.bd.ListSedeDepositoBDIn;
import comercio.bd.ListStockAgruBD;
import comercio.bd.ListStockAgruBDIn;
import common.ExcelCreator;


public class ValidarStockSrv extends Service {

	ExcelCreator creador = null;
	
	String stfixing = "";
	
	public ValidarStockSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
			output.addVariable("gdStockA");
			output.addVariable("gdSedepo");
			output.addVariable("txmensaj");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada

        //Varibales de salida
    	Grid gdStockA = null;
    	Grid gdSedepo = null;
        String txmensaj = "";
        
        //Otras Variables
        
        try {
        	
			ListStockAgruBDIn fixingBDIn = new ListStockAgruBDIn();
			ListStockAgruBD fixingBD = new ListStockAgruBD(fixingBDIn);
			fixingBD.setConnection(con);
			gdStockA = fixingBD.execSelect();
			
			ListSedeDepositoBDIn sedeDepoBDIn= new ListSedeDepositoBDIn();
			ListSedeDepositoBD sedeDepoBD = new ListSedeDepositoBD(sedeDepoBDIn);
			sedeDepoBD.setConnection(con);
			gdSedepo = sedeDepoBD.execSelect();
			
			output.setValue("gdStockA", gdStockA);
			output.setValue("gdSedepo", gdSedepo);
			output.setValue("txmensaj", "OK");
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
}
	