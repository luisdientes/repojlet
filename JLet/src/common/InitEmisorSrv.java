package common;


import java.util.ArrayList;
import java.util.HashMap;

import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import common.bd.ListEmisoresBD;
import common.bd.ListEmisoresBDIn;


public class InitEmisorSrv extends Service {
	
	HashMap<String,String> permEmis = new HashMap<String,String>();
	
    public InitEmisorSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();						 			
			
			input.addVariable("cdpantal");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();

			output.addVariable("cdpantal");
			output.addVariable("gridEmis");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String pantalla = null;
        
        //Varibales de salida
        Grid gridPeEm = new Grid();      
        
        //Otras Variables
     
        try{        	
        	        	
        	pantalla = input.getStringValue("cdpantal");
        	System.out.println("Pantalla: "+ pantalla);
        	
        	permEmis = (HashMap<String, String>) sesion.getAttribute("permEmis");
        	
        	// tipo facturas
        	ListEmisoresBDIn ListEmiBDIn  = new ListEmisoresBDIn();  
        	ListEmisoresBD ListEmiBD = new ListEmisoresBD(ListEmiBDIn);
        	ListEmiBD.setConnection(con);
        	Grid gridEmis = ListEmiBD.execSelect();
        	
        	gridPeEm.addColumn("idemisor");
        	gridPeEm.addColumn("tpclient");
        	gridPeEm.addColumn("rzsocial");
        	gridPeEm.addColumn("logoimgx");
        	
        	for (int i = 0; i < gridEmis.rowCount(); i++){
        		
        		String idemisor = gridEmis.getStringCell(i,"idclient");
        		String tpclient = gridEmis.getStringCell(i,"tpclient");
        		
        		if (permEmis.containsKey(idemisor)){
        			ArrayList<String> row = new ArrayList<String>();
        			
        			row.add(idemisor);
        			row.add(tpclient);
        			row.add(gridEmis.getStringCell(i,"rzsocial"));
        			row.add(gridEmis.getStringCell(i,"logoclie"));
        			
        			gridPeEm.addRow(row);
        			
        		}
        	}
        	
        	output.setValue("cdpantal", pantalla);
        	output.setValue("gridEmis", gridPeEm);
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
       
}