package subasta;


import subasta.bd.UpdSubastaBD;
import subasta.bd.UpdSubastaBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import common.ExcelCreator;


public class AltaSubastaSrv extends Service {

	ExcelCreator creador = null;
	
	public AltaSubastaSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("txwebxxx");
			input.addVariable("txpaisxx");
			input.addVariable("txusuari");
			input.addVariable("tipovent");
			input.addVariable("idproduc");
			input.addVariable("txnombre");
			input.addVariable("preciosa");
			input.addVariable("preciotr");
			input.addVariable("divisaxx");
			input.addVariable("cdintern");
			input.addVariable("urlexter");
			input.addVariable("preciomi");
			input.addVariable("fechvent");
			input.addVariable("horavent");
			input.addVariable("finfecve");
			input.addVariable("finhorve");
			input.addVariable("desactiv");
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
			output.addVariable("txmensaj");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	
    	String txwebxxx = ""; 
    	String txpaisxx = ""; 
    	String txusuari = ""; 
    	String tipovent = ""; 
    	String idproduc = ""; 
    	String txnombre = ""; 
    	String preciosa = ""; 
    	String preciotr = ""; 
    	String divisaxx = ""; 
    	String cdintern = ""; 
    	String urlexter = ""; 
    	String preciomi = ""; 
    	String fechvent = ""; 
    	String horavent = ""; 
    	String finfecve = ""; 
    	String finhorve = "";
    	String desactiv = "";
    	String idcodsub = "";


        //Varibales de salida
    	Grid gdSubast = null;
        String txmensaj = "";
        
        //Otras Variables
        
        
        try {
        	txwebxxx = input.getStringValue("txwebxxx");
        	txpaisxx = input.getStringValue("txpaisxx");
        	txusuari = input.getStringValue("txusuari");
        	tipovent = input.getStringValue("tipovent");
        	idproduc = input.getStringValue("idproduc");
        	txnombre = input.getStringValue("txnombre");
        	preciosa = input.getStringValue("preciosa");
        	preciotr = input.getStringValue("preciotr");
        	divisaxx = input.getStringValue("divisaxx");
        	cdintern = input.getStringValue("cdintern");
        	urlexter = input.getStringValue("urlexter");
        	preciomi = input.getStringValue("preciomi");
        	fechvent = input.getStringValue("fechvent");
        	horavent = input.getStringValue("horavent");
        	finfecve = input.getStringValue("finfecve");
        	finhorve = input.getStringValue("finhorve");
        	desactiv = input.getStringValue("desactiv");
        	idcodsub = input.getStringValue("idcodsub");

        	int insertado = 0;
     
        	try{
        			if(desactiv !=null && !desactiv.equals("")){
        				UpdSubastaBDIn upSubaBDIn = new UpdSubastaBDIn();
	        			upSubaBDIn.setValue("desactiv",desactiv);
	        			upSubaBDIn.setValue("idcodsub",idcodsub);
	        			UpdSubastaBD updSubasBD = new UpdSubastaBD(upSubaBDIn);
	        			updSubasBD.setConnection(con);
	        			insertado = updSubasBD.execUpdate();

	        			if( insertado >0){
							System.out.println("Linea actualizada correctamente");
	        		
						}else{
							System.err.println("ERROR ----- Fallo al actualizar la linea -- ERROR");
						}
        				
        			}else{
        				
	        			UpdSubastaBDIn upSubaBDIn = new UpdSubastaBDIn();
	        			upSubaBDIn.setValue("txwebxxx",txwebxxx);
	        			upSubaBDIn.setValue("txpaisxx",txpaisxx);
	        			upSubaBDIn.setValue("txusuari",txusuari);
	        			upSubaBDIn.setValue("tipovent",tipovent);
	        			upSubaBDIn.setValue("idproduc",idproduc);
	        			upSubaBDIn.setValue("txnombre",txnombre);
	        			upSubaBDIn.setValue("preciosa",preciosa);
	        			upSubaBDIn.setValue("preciotr",preciotr);
	        			upSubaBDIn.setValue("divisaxx",divisaxx);
	        			upSubaBDIn.setValue("cdintern",cdintern);
	        			upSubaBDIn.setValue("urlexter",urlexter);
	        			upSubaBDIn.setValue("preciomi",preciomi);
	        			upSubaBDIn.setValue("fechvent",fechaMySQL(fechvent));
	        			upSubaBDIn.setValue("horavent",horavent);
	        			upSubaBDIn.setValue("finfecve",fechaMySQL(finfecve));
	        			upSubaBDIn.setValue("finhorve",finhorve);
	
	        			UpdSubastaBD updSubasBD = new UpdSubastaBD(upSubaBDIn);
	        			updSubasBD.setConnection(con);
	        			
	        			insertado = updSubasBD.execInsert();
						
	        			if( insertado >0){
							System.out.println("Linea dada correctamente");
	        		
						}else{
							System.err.println("ERROR ----- Fallo al actualizar la linea -- ERROR");
						}
	        			
        			}
        		
        			gdSubast = listarSubastas();
        			
        			
        	   }catch(Exception e1){
        		   e1.printStackTrace();
        	   }
        	
        	output.setValue("gdSubast", gdSubast);
        	output.setValue("txmensaj", "OK");
        
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
    public Grid listarSubastas(){
    	Grid listSuba = null;
    	
    	try {
    		ListSubastaSrv lstSubasSrv= new ListSubastaSrv();
    		ObjectIO input = lstSubasSrv.instanceOfInput();
    		ObjectIO output = lstSubasSrv.instanceOfOutput();    		    	
    		
    		lstSubasSrv.setConnection(this.getConnection());
    		lstSubasSrv.process(input, output);
    		listSuba = output.getGrid("gdSubast");
    		
    	} catch(Exception e) {
    		
    	}
    	
    	return listSuba; 
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
	