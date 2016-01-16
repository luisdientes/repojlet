package subasta;


import subasta.bd.UpdInfoSubastaBD;
import subasta.bd.UpdInfoSubastaBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.ObjectIO;

import common.ExcelCreator;


public class AltaInfoSubastaSrv extends Service {

	ExcelCreator creador = null;
	
	public AltaInfoSubastaSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("idcodsub");
			input.addVariable("tpevento");			
			input.addVariable("txusuari");
			input.addVariable("txmailxx");
			input.addVariable("telefono");
			input.addVariable("isopaisx");
			input.addVariable("cantidad");
			input.addVariable("cddivisa");
			input.addVariable("direnvio");
			input.addVariable("txcoment");
			input.addVariable("nunidade");
			input.addVariable("pretrans");
			input.addVariable("fhventax");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
			output.addVariable("resualta");
			output.addVariable("tpevento");		//VACIO
			output.addVariable("idcodsub");		//VACIO
			output.addVariable("cduserxx");		//VACIO
			output.addVariable("txmensaj");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idcodsub = "";
    	String tpevento = "";
    	String txusuari = "";
    	String txmailxx = "";
    	String telefono = "";
    	String isopaisx = "";
    	String cantidad = "";
    	String cddivisa = "";
    	String direnvio = "";
    	String txcoment = "";
    	String nunidade = "";
    	String pretrans = "";
    	String fhventax = "";

        //Varibales de salida
    	String resualta = "";
        String txmensaj = "";
        
        //Otras Variables
        
        
        try {
        	idcodsub = input.getStringValue("idcodsub");
        	tpevento = input.getStringValue("tpevento");
        	txusuari = input.getStringValue("txusuari");
        	txmailxx = input.getStringValue("txmailxx");
        	telefono = input.getStringValue("telefono");
        	isopaisx = input.getStringValue("isopaisx");
        	cantidad = input.getStringValue("cantidad");
        	cddivisa = input.getStringValue("cddivisa");
        	direnvio = input.getStringValue("direnvio");
        	txcoment = input.getStringValue("txcoment");
        	nunidade = input.getStringValue("nunidade");
        	pretrans = input.getStringValue("pretrans");
        	fhventax = fechaMySQL(input.getStringValue("fhventax"));

        	int insertado = 0;
     
        	try{
        		
	        		UpdInfoSubastaBDIn upInfoSubaBDIn = new UpdInfoSubastaBDIn();
	        		upInfoSubaBDIn.setValue("idcodsub",idcodsub);
	        		upInfoSubaBDIn.setValue("tpevento",tpevento);
	        		upInfoSubaBDIn.setValue("txusuari",txusuari);
	        		upInfoSubaBDIn.setValue("txmailxx",txmailxx);
	        		upInfoSubaBDIn.setValue("telefono",telefono);
	        		upInfoSubaBDIn.setValue("isopaisx",isopaisx);
	        		upInfoSubaBDIn.setValue("cantidad",cantidad);
	        		upInfoSubaBDIn.setValue("cddivisa",cddivisa);
	        		upInfoSubaBDIn.setValue("direnvio",direnvio);
	        		upInfoSubaBDIn.setValue("txcoment",txcoment);
	        		upInfoSubaBDIn.setValue("nunidade",nunidade);
	        		upInfoSubaBDIn.setValue("pretrans",pretrans);
	        		upInfoSubaBDIn.setValue("fhventax",fhventax);
	
	        		UpdInfoSubastaBD updInfoSubasBD = new UpdInfoSubastaBD(upInfoSubaBDIn);
	        		updInfoSubasBD.setConnection(con);
        			
        			insertado = updInfoSubasBD.execInsert();
					
        			if( insertado >0){
						System.out.println("Linea Informacion Subasta introducida correctamente");
						resualta = "OK";
        		
					}else{
						System.err.println("ERROR ----- Fallo al actualizar la linea -- ERROR");
						resualta = "KO";
					}
        		
        	   }catch(Exception e1){
        		   e1.printStackTrace();
        	   }
        	
        	output.setValue("resualta", resualta);
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
	