package recibos;


import java.text.DecimalFormat;
import java.text.NumberFormat;

import recibos.bd.UpdRecibosBD;
import recibos.bd.UpdRecibosBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import common.ExcelCreator;


public class AltaRecibosSrv extends Service {

	ExcelCreator creador = null;
	
	public AltaRecibosSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("idclient");
			input.addVariable("idemisor");
			input.addVariable("tpclient");
			input.addVariable("cantidad");
			input.addVariable("concepto");
			input.addVariable("formpago");
			input.addVariable("txcajero");
			input.addVariable("cdfactur");
			input.addVariable("valortot");	
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
			output.addVariable("gdEquipo");
			output.addVariable("gdMarcas");
			output.addVariable("grListRe");
			output.addVariable("txmensaj");
			output.addVariable("idclient");
			output.addVariable("accionxx");
			
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
    	String cantidad = ""; 
    	String concepto = ""; 
    	String formpago = ""; 
    	String txcajero = ""; 
    	String cdfactur = ""; 
    	String valortot = ""; 
 

        //Varibales de salida
    	Grid gdEquipo = null;
    	Grid gdMarcas = null;
    	Grid grListRe = null;
        String maxgimna = "";
        
        //Otras Variables
        
        
        try {
        	idclient = input.getStringValue("idclient");
        	idemisor = input.getStringValue("idemisor");
        	tpclient = input.getStringValue("tpclient");
        	cantidad = input.getStringValue("cantidad");
        	concepto = input.getStringValue("concepto");
        	formpago = input.getStringValue("formpago");
        	txcajero = input.getStringValue("txcajero");
        	cdfactur = input.getStringValue("cdfactur");
        	valortot = input.getStringValue("valortot");
        	
        	cdfactur = addZeroLeft(cdfactur);
        	
        	

        	int insertado = 0;
        	
     
        	try{
        		
        			UpdRecibosBDIn upReciboBDIn = new UpdRecibosBDIn();
        			upReciboBDIn.setValue("idclient",idclient);
        			upReciboBDIn.setValue("idemisor",idemisor);
        			upReciboBDIn.setValue("tpclient",tpclient);
        			upReciboBDIn.setValue("cantidad",cantidad);
        			upReciboBDIn.setValue("concepto",concepto);
        			upReciboBDIn.setValue("formpago",formpago);
        			upReciboBDIn.setValue("txcajero",txcajero);
        			upReciboBDIn.setValue("cdfactur",cdfactur);
        			upReciboBDIn.setValue("valortot",valortot);
        		
        			UpdRecibosBD updReciboBD = new UpdRecibosBD(upReciboBDIn);
        			updReciboBD.setConnection(con);
        			
        			insertado = updReciboBD.execInsert();
					
        			if( insertado >0){
						System.out.println("Linea actualizada correctamente");
						
						UpdRecibosBDIn listReciboBDIn = new UpdRecibosBDIn();
						listReciboBDIn.setValue("idemisor",idemisor);
						listReciboBDIn.setValue("tpclient",tpclient);
						UpdRecibosBD listReciboBD = new UpdRecibosBD(listReciboBDIn);
						listReciboBD.setConnection(con);
	        			
	        			grListRe = listReciboBD.execSelect();
						
						/*Grid gdGimnas = null;
						
						MaxGimnasioBDIn maxGimnaBDIn = new MaxGimnasioBDIn();
						MaxGimnasioBD MaxGimnaBD = new MaxGimnasioBD(maxGimnaBDIn);
						MaxGimnaBD.setConnection(con);
						
						gdGimnas = MaxGimnaBD.execSelect();
						maxgimna = gdGimnas.getStringCell(0, "idclient");
						
						
						UpdEquiposBDIn updEquiBDIn = new UpdEquiposBDIn();
						UpdEquiposBD updEquiBD = new UpdEquiposBD(updEquiBDIn);
						updEquiBD.setConnection(con);
						
						gdEquipo = updEquiBD.execSelect();
						
						UpdMarcasBDIn updMarcaBDIn = new UpdMarcasBDIn();
						UpdMarcasBD updMarcaBD 	= new UpdMarcasBD(updMarcaBDIn);
						updMarcaBD.setConnection(con);
						
						gdMarcas = updMarcaBD.execSelect();*/
						
					}else{
						System.err.println("ERROR ----- Fallo al actualizar la linea -- ERROR");
					}
        		
        			//gdSubast = listarSubastas();
        			
        			
        	   }catch(Exception e1){
        		   e1.printStackTrace();
        	   }
        	
        	output.setValue("gdEquipo", gdEquipo);
        	output.setValue("gdMarcas", gdMarcas);
        	output.setValue("grListRe", grListRe);
        	output.setValue("txmensaj", "OK");
        	output.setValue("accionxx", "A");
        	output.setValue("idclient", maxgimna);
        
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
    private static String addZeroLeft(String num) {
    	NumberFormat formatter = new DecimalFormat("00000000");
    	return formatter.format((num != null) ? Integer.parseInt(num) : 0000000);
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
	