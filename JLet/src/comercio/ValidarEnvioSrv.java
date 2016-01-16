package comercio;


import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import comercio.bd.ListFixingBD;
import comercio.bd.ListFixingBDIn;
import comercio.bd.ListLineasEnvioBD;
import comercio.bd.ListLineasEnvioBDIn;
import common.ExcelCreator;


public class ValidarEnvioSrv extends Service {

	ExcelCreator creador = null;
	
	String stfixing = "";
	
	public ValidarEnvioSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("tpproduc");
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
			output.addVariable("gridLine");
			output.addVariable("gridDivi");
			output.addVariable("txmensaj");
			output.addVariable("idemisor");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada

        //Varibales de salida
    	Grid gdDivisa = null;
    	Grid gridLine = null;
        String txmensaj = "";
        String tpproduc = "";
        String idemisor = "";
        
        //Otras Variables
        
        try {
        	idemisor = input.getStringValue("idemisor");
        	tpproduc = input.getStringValue("tpproduc");
        	
        	
			ListFixingBDIn fixingBDIn = new ListFixingBDIn();
			fixingBDIn.setValue("cddivisa","USD");
			ListFixingBD fixingBD = new ListFixingBD(fixingBDIn);
			fixingBD.setConnection(con);
			gdDivisa = fixingBD.execSelect();
			
			ListLineasEnvioBDIn lineasBDIn= new ListLineasEnvioBDIn();
			lineasBDIn.setValue("codeenvi",null);
			lineasBDIn.setValue("tipoenvi",tpproduc);
			ListLineasEnvioBD lineasBD = new ListLineasEnvioBD(lineasBDIn);
			lineasBD.setConnection(con);
			gridLine = lineasBD.execSelect();
			
			output.setValue("gridLine", gridLine);
			output.setValue("gridDivi", gdDivisa);
			output.setValue("idemisor", idemisor);
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
	