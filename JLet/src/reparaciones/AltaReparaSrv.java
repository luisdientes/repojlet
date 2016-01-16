package reparaciones;


import reparaciones.bd.UpdReparaBD;
import reparaciones.bd.UpdReparaBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.ObjectIO;

import common.ExcelCreator;


public class AltaReparaSrv extends Service {

	ExcelCreator creador = null;

	String filecrea = "";
	String txmensaj = "";
	String cdrecibo = "";
	
	//Varibales de entrada
	
	String idrecibo = "";
	String idemisor = ""; 
	String tpclient = ""; 
	String txnombre = "";
	String txmodelo = "";
	String txcolorx = "";
	String txmarcax = "";
	String txdescri = "";
	String tximeixx = "";
	String fhentrad = "";
	String costordx = "";
	String costcheq = "";
	String telefono = "";
	String txmailxx = "";
	String perconta = "";
	String tiempent = "";
	String garantia = "";
	String entregad = "";
	String recibido = "";
	String tipoacci = "";
	
	
	public AltaReparaSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("idrecibo");
			input.addVariable("tpclient");
			input.addVariable("idemisor");
			input.addVariable("txnombre");
			input.addVariable("txmodelo");
			input.addVariable("txcolorx");
			input.addVariable("txmarcax");
			input.addVariable("txdescri");
			input.addVariable("tximeixx");
			input.addVariable("fhentrad");
			input.addVariable("costordx");
			input.addVariable("costcheq");
			input.addVariable("telefono");
			input.addVariable("txmailxx");
			input.addVariable("perconta");
			input.addVariable("tiempent");
			input.addVariable("garantia");
			input.addVariable("entregad");
			input.addVariable("recibido");
			input.addVariable("tipoacci");
			

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
			output.addVariable("txmensaj");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	



        //Varibales de salida
    

        
        //Otras Variables
        
        
        try {
        	idrecibo = input.getStringValue("idrecibo");
        	idemisor = input.getStringValue("idemisor");
        	tpclient = input.getStringValue("tpclient");
        	txnombre = input.getStringValue("txnombre");
        	txmodelo = input.getStringValue("txmodelo");
        	txcolorx = input.getStringValue("txcolorx");
        	txmarcax = input.getStringValue("txmarcax");
        	txdescri = input.getStringValue("txdescri");
        	tximeixx = input.getStringValue("tximeixx");
        	fhentrad = input.getStringValue("fhentrad");
        	costordx = input.getStringValue("costordx").replaceAll("RD$","").trim();;
        	costcheq = input.getStringValue("costcheq").replaceAll("RD$","").trim();;
        	perconta = input.getStringValue("perconta");
        	telefono = input.getStringValue("telefono");
        	txmailxx = input.getStringValue("txmailxx");
        	tiempent = input.getStringValue("tiempent");
        	garantia = input.getStringValue("garantia");
        	entregad = input.getStringValue("entregad");
        	recibido = input.getStringValue("recibido");
        	tipoacci = input.getStringValue("tipoacci");


        	int insertado = 0;
     
        	try{
        		
        			UpdReparaBDIn upRepaBDIn = new UpdReparaBDIn(); 
        			upRepaBDIn.setValue("idemisor",idemisor); 
        			upRepaBDIn.setValue("tpclient",tpclient); 
        			upRepaBDIn.setValue("txnombre",txnombre);              
        			upRepaBDIn.setValue("txmodelo",txmodelo);
        			upRepaBDIn.setValue("txcolorx",txcolorx);
        			upRepaBDIn.setValue("txmarcax",txmarcax);              
        			upRepaBDIn.setValue("txdescri",txdescri);              
        			upRepaBDIn.setValue("tximeixx",tximeixx);              
        			upRepaBDIn.setValue("fhentrad",fechaMySQL(fhentrad));              
        			upRepaBDIn.setValue("costordx",costordx);  
        			upRepaBDIn.setValue("costcheq",costcheq);
        			upRepaBDIn.setValue("perconta",perconta);
        			upRepaBDIn.setValue("telefono",telefono);              
        			upRepaBDIn.setValue("txmailxx",txmailxx);              
        			upRepaBDIn.setValue("tiempent",tiempent);              
        			upRepaBDIn.setValue("garantia",garantia);              
        			upRepaBDIn.setValue("entregad",entregad);  
        			upRepaBDIn.setValue("recibido",recibido);              
        			upRepaBDIn.setValue("idrecibo",idrecibo); 	

        			UpdReparaBD updRepaBD = new UpdReparaBD(upRepaBDIn);
        			updRepaBD.setConnection(con);
        			
        			if(tipoacci !=null && tipoacci.equals("UPD")){ //ACTUALIZO
        				insertado = updRepaBD.execUpdate();
        			}else{ // ALTA
        				insertado = updRepaBD.execInsert();
        			}
        			
        			
					
        			if( insertado >0){
        				generaReparacion(idemisor);
						System.out.println("Linea actualizada correctamente");
        		
					}else{
						System.err.println("ERROR ----- Fallo al actualizar la linea -- ERROR");
					}
        						
        	   }catch(Exception e1){
        		   e1.printStackTrace();
        	   }
        	
        	output.setValue("idemisor", idemisor);
        	output.setValue("cdrecibo", cdrecibo);
        	output.setValue("filecrea", filecrea);
        	output.setValue("txmensaj", "OK");
        
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
    public void generaReparacion(String idemisor){
    	
    	try {
    		GeneracionDocReparacionSrv altaSrv = new GeneracionDocReparacionSrv();
    		ObjectIO input  = altaSrv.instanceOfInput();
    		input.setValue("idemisor", idemisor);
    		input.setValue("idrecibo", idrecibo);
    		ObjectIO output = altaSrv.instanceOfOutput();
    		altaSrv.setConnection(con);
    		altaSrv.process(input, output);
    		
    		cdrecibo = output.getStringValue("cdrecibo");
    		filecrea = output.getStringValue("filecrea");
			txmensaj = output.getStringValue("txmensaj");
    	} catch(Exception e) {
    		System.out.println("Error al crear Recibo");
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
	