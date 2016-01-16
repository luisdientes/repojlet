package stock;


import stock.bd.ListVendidoBD;
import stock.bd.ListVendidoBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import common.bd.ListClientesBD;
import common.bd.ListClientesBDIn;

import desgcostes.bd.ListDesgloseCostesBD;
import desgcostes.bd.ListDesgloseCostesBDIn;


public class ListVendidoSrv extends Service {

    public ListVendidoSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();	
			input.addVariable("idemisor");
			input.addVariable("cdestado");
			input.addVariable("tipocons");
			input.addVariable("idclient");
			input.addVariable("tpclient");
			input.addVariable("logoemis");

		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
			output.addVariable("txmensaj");
			output.addVariable("idemisor");
			output.addVariable("cdestado");
			output.addVariable("idclient");
			output.addVariable("tpclient");
			output.addVariable("logoemis");
			output.addVariable("gridProd");
			output.addVariable("grPrAgru");
			output.addVariable("gridColo");
			output.addVariable("gridClie");
			output.addVariable("imeiingr");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor = null;
    	String cdestado = null;
    	String tipocons = null;
    	String idclient = null;
    	String tpclient = null;
    	String logoemis = null;
        
        //Varibales de salida
    	Grid gridProd = null;
    	Grid grPrAgru = null; 
    	Grid gridColo = null;
    	Grid gridClie = null;
    	String imeiingr = "";
    	
        try {
        	
        	idemisor = input.getStringValue("idemisor");
        	cdestado = input.getStringValue("cdestado");
        	tipocons = input.getStringValue("tipocons");
        	idclient = input.getStringValue("idclient");
        	tpclient = input.getStringValue("tpclient");
        	logoemis = input.getStringValue("logoemis"); 
        	
	        try {
	        	 
	        	ListVendidoBDIn lineasBDIn= new ListVendidoBDIn();
	        	lineasBDIn.setValue("idemisor",idemisor);		        	
	        	lineasBDIn.setValue("cdestado",cdestado);
	        	ListVendidoBD lineasBD= new ListVendidoBD(lineasBDIn);
	        	lineasBD.setConnection(con);
	        	gridProd = lineasBD.execSelect();
	        	
	        	ListClientesBDIn ListCliTipoBDIn  = new ListClientesBDIn();  
	        	ListCliTipoBDIn.setValue("idemisor", idemisor);
	        	ListClientesBD ListCliTipoBD = new ListClientesBD(ListCliTipoBDIn);
	        	ListCliTipoBD.setConnection(con);
	        	gridClie= ListCliTipoBD.execSelect();
	        	
	        	ListDesgloseCostesBDIn desCosteBDIn =	 new ListDesgloseCostesBDIn();
	        	desCosteBDIn.setValue("idemisor",idemisor);
	        	desCosteBDIn.setValue("mcactivo","S");
	        	ListDesgloseCostesBD desCosteBD = new ListDesgloseCostesBD(desCosteBDIn);
	        	desCosteBD.setConnection(con);
	        	Grid gddscost = desCosteBD.execSelect();
	        	
	        	for (int i = 0; i < gddscost.rowCount(); i++){
	        		if (gddscost.getStringCell(i, "codedesg").equals("IG00001")){
	        			
	        			if (!imeiingr.equals("")){
	        				imeiingr += ",";
	        			}
	        			
	        			imeiingr += gddscost.getStringCell(i, "idunicox");
	        		}
	        					
	        	}
	        	
	        } catch (Exception e) {
				System.err.println(this.getClass().getName() +" ERROR recogiendo productos. "+ e.getMessage());
			}
    
	        output.setValue("cdestado", cdestado);
        	output.setValue("idemisor", idemisor);
        	output.setValue("idclient", idclient);
        	output.setValue("tpclient", tpclient);
        	output.setValue("logoemis", logoemis);
		    output.setValue("imeiingr", imeiingr);
		    output.setValue("gridProd", gridProd);
		    output.setValue("grPrAgru", grPrAgru);
		    output.setValue("gridClie", gridClie);
		    
		    output.setValue("txmensaj", "OK");

        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
}
	