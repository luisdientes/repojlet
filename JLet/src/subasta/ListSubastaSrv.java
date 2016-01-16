package subasta;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;

import subasta.bd.ListSubasCotiBD;
import subasta.bd.ListSubasCotiBDIn;
import subasta.bd.ListSubastasBD;
import subasta.bd.ListSubastasBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import common.bd.ListPaisesBD;
import common.bd.ListPaisesBDIn;

public class ListSubastaSrv extends Service {

	public ListSubastaSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("idcodsub");
			input.addVariable("txwebxxx");
			input.addVariable("txnombre");
			input.addVariable("idpaisxx");
			input.addVariable("tipovent");
			input.addVariable("fhdesdex");
			input.addVariable("fhhastax");
			input.addVariable("mcactivo");
			input.addVariable("idprodwe");
			
			
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
			output.addVariable("gdPaises");
			output.addVariable("arpaises");
			output.addVariable("arproduc");
			output.addVariable("txmensaj");
			output.addVariable("fechames");
			output.addVariable("mcactivo");
			output.addVariable("fhdesdex");
			output.addVariable("fhhastax");
			output.addVariable("idprodwe");
			
			
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
    	String fechaMes		= "";
    	String mcactivo		= "";
    	String idprodwe		= "";
    	

        //Varibales de salida
    	Grid gdSubast 		= null;
    	Grid gdSubCot		= null;
    	Grid gdPaises		= null;
        ArrayList<String> arpaises = new ArrayList<String>();
        ArrayList<String> arproduc = new ArrayList<String>();
    	String txmensaj 	= "";
        
        //Otras Variables
        
        
        try {
        	
        	idcodsub = input.getStringValue("idcodsub");
        	txwebxxx = input.getStringValue("txwebxxx");
        	txnombre = input.getStringValue("txnombre");
        	idpaisxx = input.getStringValue("idpaisxx");
        	tipovent = input.getStringValue("tipovent");
        	fhdesdex = input.getStringValue("fhdesdex");
        	fhhastax = input.getStringValue("fhhastax");
        	mcactivo = input.getStringValue("mcactivo");
        	idprodwe = input.getStringValue("idprodwe");
        	
        	try {
        		ListSubastasBDIn subastaBDIn = new ListSubastasBDIn();
        		//subastaBDIn.setValue("idcodsub", idcodsub);
        		subastaBDIn.setValue("txnombre", txnombre);
        		subastaBDIn.setValue("idpaisxx", idpaisxx);
        		subastaBDIn.setValue("tipovent", tipovent);
        		subastaBDIn.setValue("mcactivo", mcactivo);
        		subastaBDIn.setValue("idprodwe", idprodwe);
        		
        		//SIN INCLUIR EN LA QUERY
        		subastaBDIn.setValue("fhdesdex", fechaMySQL(fhdesdex));
        		subastaBDIn.setValue("fhhastax", fechaMySQL(fhhastax));
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
        		
        		if(fhdesdex!=null && !fhdesdex.equals("")){
        			fechaMes = fhdesdex;
        		}else{
        			fechaMes = fechaMesAnterior();	
        		}
        		
        		
        		
        	} catch (Exception e) {
        		System.err.println(this.getClass().getName() +" [ERROR ] Recuperando subastas. "+ e.getMessage());
        	}

        	
    		output.setValue("gdSubast", gdSubast);
    		output.setValue("gdCotiza", gdSubCot);
    		output.setValue("gdPaises", gdPaises);
    		output.setValue("arpaises", arpaises);
    		output.setValue("arproduc", arproduc);
		    output.setValue("txmensaj", txmensaj);
		    output.setValue("fechames", fechaMes);
		    output.setValue("mcactivo", mcactivo);
		    output.setValue("fhdesdex", fhdesdex);
		    output.setValue("fhhastax", fhhastax);
		    output.setValue("idprodwe", idprodwe);
		    
		    
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
    public String fechaMesAnterior(){
    	
    	Calendar c1 = GregorianCalendar.getInstance();
        System.out.println("Fecha actual: " + c1.getTime().toLocaleString());
        c1.add(Calendar.MONTH, -1);
        
    
    	String fechaStr = "";
    	SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
    	Date fecha = new Date();
    	fechaStr = formatoDelTexto.format(c1.getTime());
    	
    	return fechaStr;
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
	