package factura;


import reparaciones.bd.UpdReparaBD;
import reparaciones.bd.UpdReparaBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import common.ExcelCreator;
import factura.bd.ListRecibosBD;
import factura.bd.ListRecibosBDIn;
import factura.bd.MaxReciboCreditoBD;
import factura.bd.MaxReciboCreditoBDIn;
import factura.bd.UpdPagaFacturaBD;
import factura.bd.UpdPagaFacturaBDIn;
import facturacion.GeneracionDocReciboSrv;


public class RegeneraReciboSrv extends Service {

	ExcelCreator creador = null;

	String cdrecibo = "";
    String filecrea = "";
    String txmensaj = "";
	
	public RegeneraReciboSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
	
			input.addVariable("idemisor");
			input.addVariable("idrecibo");
			input.addVariable("idfactur");
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
			output.addVariable("cdrecibo");
			output.addVariable("filecrea");
			output.addVariable("divisaxx");
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
    	String idrecibo = "";
    	String idfactur = "";
    	
        
        //Otras Variables
    	
        
        try {
        	idemisor = input.getStringValue("idemisor");
        	idrecibo = input.getStringValue("idrecibo");
        	idfactur = input.getStringValue("idfactur");
        	
        	generaRecibo(idemisor,idfactur,idrecibo);
        	
        	output.setValue("idemisor", idemisor);
        	output.setValue("filecrea", filecrea);
        	output.setValue("txmensaj", "OK");
        
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
    public void generaRecibo(String idemisor, String idfactur,String idrecibo){
    	
    	try {
    		GeneracionDocReciboSrv altaSrv = new GeneracionDocReciboSrv();
    		ObjectIO input  = altaSrv.instanceOfInput();
    		input.setValue("idemisor", idemisor);
    		input.setValue("idfactur", idfactur);
    		input.setValue("idrecibo", idrecibo);
    		ObjectIO output = altaSrv.instanceOfOutput();
    		altaSrv.setConnection(con);
    		altaSrv.process(input, output);
    		
    		filecrea = output.getStringValue("filecrea");
    		filecrea = output.getStringValue("filecrea");
			txmensaj = output.getStringValue("txmensaj");
    	} catch(Exception e) {
    		System.out.println("Error al crear recibo Izumba");
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
	