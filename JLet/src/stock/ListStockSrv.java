package stock;


import java.util.ArrayList;

import common.bd.ListMarcasBD;
import common.bd.ListMarcasBDIn;
import stock.bd.ListStockBD;
import stock.bd.ListStockBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import common.bd.ListClientesBD;
import common.bd.ListClientesBDIn;


public class ListStockSrv extends Service {

    public ListStockSrv() {
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
			output.addVariable("gridMarc");
			output.addVariable("gridProd");
			output.addVariable("grPrAgru");
			output.addVariable("gridColo");
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
    	String txmarfil = null;
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
        	txmarfil = input.getStringValue("txmarcax"); 
        	tpproduc = input.getStringValue("tpproduc"); 
        	
	        try {
	        	 
	        	ListStockBDIn lineasBDIn= new ListStockBDIn();
	        	lineasBDIn.setValue("idemisor",idemisor);		        	
	        	lineasBDIn.setValue("cdestado",cdestado);
	        	lineasBDIn.setValue("idclient",idclient);
	        	lineasBDIn.setValue("txmarcax",txmarfil);
	        	lineasBDIn.setValue("tpproduc",tpproduc);
	        	lineasBDIn.setValue("tpclient",tpclient);
	        	ListStockBD lineasBD = new ListStockBD(lineasBDIn);
				lineasBD.setConnection(con);
				gridProd = lineasBD.execSelect();
	        	
	        	ListClientesBDIn ListCliFactBDIn  = new ListClientesBDIn();  
	        	ListCliFactBDIn.setValue("idemisor", idemisor);
	        	ListCliFactBDIn.setValue("tpclient", tpclient);
	        	ListClientesBD ListCliTipoBD = new ListClientesBD(ListCliFactBDIn);
	        	ListCliTipoBD.setConnection(con);
	        	gridClie= ListCliTipoBD.execSelect();
	        	
				ListMarcasBDIn listMarcaBDIn  = new ListMarcasBDIn();  
	        	ListMarcasBD listMarcasBD = new ListMarcasBD(listMarcaBDIn);
	        	listMarcasBD.setConnection(con);
	        	gridMarc= listMarcasBD.execSelect();

	        	if (gridProd.rowCount() > 0){
		        
	        		String antcodpr = gridProd.getStringCell(0, "codprodu");
		        	
	        		grPrAgru = new Grid();
	        		grPrAgru.addColumn("codprodu");
	        		grPrAgru.addColumn("txmarcax");
	        		grPrAgru.addColumn("txmodelo");
	        		grPrAgru.addColumn("unistock");
	        		
	        		int unistock = 0;
	        		
	        		String txmarcax = "";
	        		String txmodelo = "";
	        		
	        		for (int i = 0; i < gridProd.rowCount(); i++) {
		        		
		        		String codprodu = gridProd.getStringCell(i, "codprodu");
		        		
		        		if (!codprodu.equals(antcodpr)) {
		        			ArrayList<String> row = new ArrayList<String>();
		        			
		        			row.add(antcodpr);
		        			row.add(txmarcax);
		        			row.add(txmodelo);
		        			row.add(String.valueOf(unistock));
		        		
		        			grPrAgru.addRow(row);
		        			
		        			unistock = 0;
		        			antcodpr = codprodu;
		        			
		        		}
		        		
		        		unistock++;
		        		txmarcax = gridProd.getStringCell(i, "txmarcax");
		        		txmodelo = gridProd.getStringCell(i, "txmodelo");
		        		
		        	}
	        		
	        		ArrayList<String> row = new ArrayList<String>();
        			
        			row.add(antcodpr);
        			row.add(txmarcax);
        			row.add(txmodelo);
        			row.add(String.valueOf(unistock));
        		
        			grPrAgru.addRow(row);
	        		
	        	}
	        } catch (Exception e) {
				System.err.println(this.getClass().getName() +" ERROR recogiendo productos. "+ e.getMessage());
			}
    
	        output.setValue("cdestado", cdestado);
        	output.setValue("idemisor", idemisor);
        	output.setValue("idclient", idclient);
        	output.setValue("tpclient", tpclient);
        	output.setValue("txmarcax", txmarfil);
        	output.setValue("tpproduc", tpproduc);
        	output.setValue("logoemis", logoemis);
		    output.setValue("gridProd", gridProd);
		    output.setValue("grPrAgru", grPrAgru);
		    output.setValue("gridClie", gridClie);
		    output.setValue("gridMarc", gridMarc);
		    output.setValue("txmensaj", "OK");

        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
}
	