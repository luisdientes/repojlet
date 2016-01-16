package stock;


import common.bd.ListMarcasBD;
import common.bd.ListMarcasBDIn;
import stock.bd.ListStockImgBD;
import stock.bd.ListStockImgBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import common.bd.ListClientesBD;
import common.bd.ListClientesBDIn;


public class ListStockImgSrv extends Service {

    public ListStockImgSrv() {
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
			input.addVariable("txmarcax");
			input.addVariable("tpproduc");
			

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
			output.addVariable("txmarcax");
			output.addVariable("tpproduc");
			output.addVariable("gridProd");
			output.addVariable("grPrAgru");
			output.addVariable("gridMarc");
			output.addVariable("gridClie");
			
			
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
    	String txmarcax = null;
    	String tpproduc = null;
        
        //Varibales de salida
    	Grid gridProd = null;
    	Grid grPrAgru = null; 
    	Grid gridMarc = null;
    	Grid gridClie = null;
    	
        try {
        	
        	idemisor = input.getStringValue("idemisor");
        	cdestado = input.getStringValue("cdestado");
        	tipocons = input.getStringValue("tipocons");
        	idclient = input.getStringValue("idclient");
        	tpclient = input.getStringValue("tpclient");
        	logoemis = input.getStringValue("logoemis"); 
        	txmarcax = input.getStringValue("txmarcax"); 
        	tpproduc = input.getStringValue("tpproduc"); 
	        try {
	        	 
	        	ListStockImgBDIn lineasBDIn= new ListStockImgBDIn();
	        	lineasBDIn.setValue("idemisor",idemisor);		        	
	        	lineasBDIn.setValue("cdestado",cdestado);
	        	lineasBDIn.setValue("idclient",idclient);
	        	lineasBDIn.setValue("txmarcax",txmarcax);
	        	lineasBDIn.setValue("tpproduc",tpproduc);
	        	
	        	
	        	ListStockImgBD lineasBD = new ListStockImgBD(lineasBDIn);
				lineasBD.setConnection(con);
				gridProd = lineasBD.execSelect();
	        	
	        	
	        	ListClientesBDIn ListCliFactBDIn  = new ListClientesBDIn();  
	        	ListCliFactBDIn.setValue("idemisor", idemisor);
	        	ListClientesBD ListCliTipoBD = new ListClientesBD(ListCliFactBDIn);
	        	ListCliTipoBD.setConnection(con);
	        	gridClie= ListCliTipoBD.execSelect();
	        	
	        	ListMarcasBDIn listMarcaBDIn  = new ListMarcasBDIn();  
	        	listMarcaBDIn.setValue("idemisor", idemisor);
	        	ListMarcasBD listMarcasBD = new ListMarcasBD(listMarcaBDIn);
	        	listMarcasBD.setConnection(con);
	        	gridMarc= listMarcasBD.execSelect();

	        } catch (Exception e) {
				System.err.println(this.getClass().getName() +" ERROR recogiendo productos. "+ e.getMessage());
			}
    
	        output.setValue("cdestado", cdestado);
        	output.setValue("idemisor", idemisor);
        	output.setValue("idclient", idclient);
        	output.setValue("tpclient", tpclient);
        	output.setValue("logoemis", logoemis);
        	output.setValue("txmarcax", txmarcax);
        	output.setValue("tpproduc", tpproduc);
		    output.setValue("gridProd", gridProd);
		    output.setValue("gridClie", gridClie);
		    output.setValue("gridMarc", gridMarc);
		    
		    output.setValue("txmensaj", "OK");

        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
}
	