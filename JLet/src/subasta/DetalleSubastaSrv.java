package subasta;


import java.util.ArrayList;
import java.util.Collections;

import subasta.bd.DetalleSubastasBD;
import subasta.bd.DetalleSubastasBDIn;
import subasta.bd.ListSubasCotiBD;
import subasta.bd.ListSubasCotiBDIn;
import subasta.bd.ListSubastasBD;
import subasta.bd.ListSubastasBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import common.bd.ListPaisesBD;
import common.bd.ListPaisesBDIn;

public class DetalleSubastaSrv extends Service {

	public DetalleSubastaSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("idcodsub");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
			output.addVariable("gdSubast");
			output.addVariable("gdCotiza");
			output.addVariable("gdDetSub");
			output.addVariable("idcodsub");
			output.addVariable("gdPaises");
			output.addVariable("arpaises");
			output.addVariable("arproduc");
			output.addVariable("txmensaj");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idcodsub 	= "";
    	String txwebxxx 	= "";
    	String txnombre 	= "";
    	String idpaisxx 	= "";
    	String tipovent 	= "";
    	String fhdesdex 	= "";
    	String fhhastax 	= "";

        //Varibales de salida
    	Grid gdSubast 		= null;
    	Grid gdSubCot		= null;
    	Grid gdPaises		= null;
    	Grid gdDetSub    	= null;
        ArrayList<String> arpaises = new ArrayList<String>();
        ArrayList<String> arproduc = new ArrayList<String>();
    	String txmensaj 	= "";
        
        //Otras Variables
        
        
        try {
        	
        	idcodsub = input.getStringValue("idcodsub");
        	
        	try {
        		ListSubastasBDIn subastaBDIn = new ListSubastasBDIn();
        		subastaBDIn.setValue("idcodsub", idcodsub);
        		ListSubastasBD subastaBD = new ListSubastasBD(subastaBDIn);
        		subastaBD.setConnection(con);
        		gdSubast = subastaBD.execSelect();
        		
        		ListPaisesBDIn paisesBDIn = new ListPaisesBDIn();
        		paisesBDIn.setValue("mcactivo", "S");
        		ListPaisesBD paisesBD = new ListPaisesBD(paisesBDIn);
        		paisesBD.setConnection(con);
        		gdPaises = paisesBD.execSelect();
        		
        		for (int i = 0; i < gdSubast.rowCount(); i++){
        			if (!arpaises.contains(gdSubast.getStringCell(i,"idpaisxx"))){
        				arpaises.add(gdSubast.getStringCell(i,"idpaisxx"));
        			}
        			if (!arproduc.contains(gdSubast.getStringCell(i,"txnombre"))){
        				arproduc.add(gdSubast.getStringCell(i,"txnombre"));
        			}
        		}
        		
        		Collections.sort(arpaises);
        		Collections.sort(arproduc);
        		
        		ListSubasCotiBDIn subasCotiBDIn = new ListSubasCotiBDIn();        		
        		ListSubasCotiBD subasCotiBD = new ListSubasCotiBD(subasCotiBDIn);
        		subasCotiBD.setConnection(con);
        		gdSubCot = subasCotiBD.execSelect();
        		
        		
        		/*DETALLE SUBASTA*/
        		DetalleSubastasBDIn detsubastaBDIn = new DetalleSubastasBDIn();
        		detsubastaBDIn.setValue("idcodsub", idcodsub);
        		DetalleSubastasBD detsubastaBD = new DetalleSubastasBD(detsubastaBDIn);
        		detsubastaBD.setConnection(con);
        		gdDetSub = detsubastaBD.execSelect();
        		
        		
        	} catch (Exception e) {
        		System.err.println(this.getClass().getName() +" [ERROR ] Recuperando subastas. "+ e.getMessage());
        	}

        	
    		output.setValue("gdSubast", gdSubast);
    		output.setValue("gdCotiza", gdSubCot);
    		output.setValue("gdPaises", gdPaises);
    		output.setValue("gdDetSub", gdDetSub);
    		output.setValue("arpaises", arpaises);
    		output.setValue("arproduc", arproduc);
    		output.setValue("idcodsub", idcodsub);
		    output.setValue("txmensaj", txmensaj);
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
}
	