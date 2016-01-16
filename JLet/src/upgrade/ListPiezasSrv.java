package upgrade;


import java.util.ArrayList;

import stock.bd.ListStockBD;
import stock.bd.ListStockBDIn;
import upgrade.bd.ListStockPiezasBD;
import upgrade.bd.ListStockPiezasBDIn;
import upgrade.bd.UpdStockBD;
import upgrade.bd.UpdStockBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import common.bd.ListClientesBD;
import common.bd.ListClientesBDIn;


public class ListPiezasSrv extends Service {

    public ListPiezasSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();	
			input.addVariable("idemisor");
			input.addVariable("idgrupox");
			input.addVariable("tipocons");
			input.addVariable("claseant");
			input.addVariable("horacomi");
			input.addVariable("imeicode");
			input.addVariable("tpclient");
 
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
			output.addVariable("claseant");
			output.addVariable("imeicode");
			output.addVariable("horacomi");
			output.addVariable("tpclient");
			output.addVariable("gridLine");
			output.addVariable("gridProd");
			output.addVariable("grPrAgru");
			
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor = null;
    	String idgrupox = null;
    	String imeicode = null;
    	String idclient = null;
    	String tpclient = null;
    	String logoemis = null;
    	String claseant = null;
    	String horacomi = null;
    
        
        //Varibales de salida
    	Grid gridProd = null;
    	Grid grPrAgru = null; 
    	Grid gridLine = null;
    	Grid gridClie = null;
    	
        try {
        	
        	idemisor = input.getStringValue("idemisor");
        	idgrupox = input.getStringValue("idgrupox");
        	imeicode = input.getStringValue("imeicode");
        	claseant = input.getStringValue("claseant"); 
        	horacomi = input.getStringValue("horacomi"); 
        	tpclient = input.getStringValue("tpclient"); 
	        try {
	        	
	        	if(imeicode !=null && !imeicode.equals("")){
	            	
	        		UpdStockBDIn selStockBDIn = new UpdStockBDIn();
	        		selStockBDIn.setValue("idemisor",idemisor);
	        		selStockBDIn.setValue("imeicode",imeicode);
	        		selStockBDIn.setValue("cdestado","STOCK");
					UpdStockBD lineasBD = new UpdStockBD(selStockBDIn);
					lineasBD.setConnection(con);
					gridLine = lineasBD.execSelect();
	        	}
	        	 
	        	ListStockPiezasBDIn lineasBDIn= new ListStockPiezasBDIn();
	        	lineasBDIn.setValue("idemisor",idemisor);		        	
	        	lineasBDIn.setValue("cdgroupx",idgrupox);
	        	ListStockPiezasBD lineasBD = new ListStockPiezasBD(lineasBDIn);
				lineasBD.setConnection(con);
				gridProd = lineasBD.execSelect();

				
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
	        
	        output.setValue("imeicode", imeicode);
        	output.setValue("idemisor", idemisor);
        	output.setValue("claseant", claseant);
        	output.setValue("horacomi", horacomi);
        	output.setValue("tpclient", tpclient);
        	output.setValue("gridLine", gridLine);
		    output.setValue("gridProd", gridProd);
		    output.setValue("grPrAgru", grPrAgru);
		    
		    output.setValue("txmensaj", "OK");

        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
}
	