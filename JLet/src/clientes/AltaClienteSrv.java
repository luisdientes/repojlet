package clientes;


import java.sql.SQLException;

import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import clientes.bd.ListMaxClientesBD;
import clientes.bd.ListMaxClientesBDIn;
import clientes.bd.UpdClientesBD;
import clientes.bd.UpdClientesBDIn;
import clientes.bd.UpdClientesDetalleBD;

import common.ExcelCreator;


public class AltaClienteSrv extends Service {

	ExcelCreator creador = null;
	
	String idclient = null;
	String cdintern = null;
	
	public AltaClienteSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			 input.addVariable("idclient");
			 input.addVariable("tpclient");
			 input.addVariable("rzsocial");
			 input.addVariable("idemisor");
			 input.addVariable("cdintern");
			 input.addVariable("idfiscal");
			 input.addVariable("txdirecc");
			 input.addVariable("txciudad");
			 input.addVariable("cdpostal");
			 input.addVariable("txmailxx");
			 input.addVariable("tfnofijo");
			 input.addVariable("tfnomovi");
			 input.addVariable("tfnofaxx");
			 input.addVariable("txwebxxx");
			 input.addVariable("txpaisxx");
			 input.addVariable("txprovin");
	
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
			output.addVariable("idclient");
			output.addVariable("cdintern");
			output.addVariable("idemisor");
			output.addVariable("tpclient");
			output.addVariable("gdClient");
			output.addVariable("gridEmis");
			
			output.addVariable("txmensaj");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada

    	String rzsocial = "";
    	String idemisor = "";
    	String tpclient = "";
    	String idfiscal = ""; 
    	String txdirecc = ""; 
    	String txciudad = ""; 
    	String cdpostal = ""; 
    	String txmailxx = ""; 
    	String tfnofijo = ""; 
    	String tfnomovi = ""; 
    	String tfnofaxx = ""; 
    	String txwebxxx = ""; 
    	String txpaisxx = ""; 
    	String txprovin = ""; 
    	
        //Varibales de salida
    	Grid listClie = null;
    	Grid gridEmis = null;
        String txmensaj = "";
        
        //Otras Variables
        
        try {
        	
        	idclient = input.getStringValue("idclient");
        	tpclient = input.getStringValue("tpclient");
        	rzsocial = input.getStringValue("rzsocial");
        	idemisor = input.getStringValue("idemisor");
        	cdintern = input.getStringValue("cdintern");
        	idfiscal = input.getStringValue("idfiscal");
        	txdirecc = input.getStringValue("txdirecc");
        	txciudad = input.getStringValue("txciudad");
        	cdpostal = input.getStringValue("cdpostal");
        	txmailxx = input.getStringValue("txmailxx");
        	tfnofijo = input.getStringValue("tfnofijo");
        	tfnomovi = input.getStringValue("tfnomovi");
        	tfnofaxx = input.getStringValue("tfnofaxx");
        	txwebxxx = input.getStringValue("txwebxxx");
        	txpaisxx = input.getStringValue("txpaisxx");
        	txprovin = input.getStringValue("txprovin");
        	
        	int insertado = 0;
     
        	try {
        		
        		UpdClientesBDIn upCliBDIn = new UpdClientesBDIn();
    			upCliBDIn.setValue("idclient",idclient);
    			upCliBDIn.setValue("tpclient",tpclient);
    			upCliBDIn.setValue("rzsocial",rzsocial);
    			upCliBDIn.setValue("idemisor",idemisor);
    			upCliBDIn.setValue("cdintern",cdintern);
    			upCliBDIn.setValue("idfiscal",idfiscal);
    			upCliBDIn.setValue("txdirecc",txdirecc);
    			upCliBDIn.setValue("txciudad",txciudad);
    			upCliBDIn.setValue("cdpostal",cdpostal);
    			upCliBDIn.setValue("txmailxx",txmailxx);
    			upCliBDIn.setValue("tfnofijo",tfnofijo);
    			upCliBDIn.setValue("tfnomovi",tfnomovi);
    			upCliBDIn.setValue("tfnofaxx",tfnofaxx);
    			upCliBDIn.setValue("txwebxxx",txwebxxx);
    			upCliBDIn.setValue("txpaisxx",txpaisxx);
    			upCliBDIn.setValue("txprovin",txprovin);

    			UpdClientesBD updCliBD = new UpdClientesBD(upCliBDIn);
    			updCliBD.setConnection(con);
    			
    			
    			if ((idclient != null) && (!idclient.equals("")) && (!idclient.equals("0"))){
    				//ACTUALIZACIÓN
    				
    				insertado = updCliBD.execUpdate();
    			
    				if (insertado > 0){
						System.out.println("Linea actualizada correctamente");
						txmensaj ="Cliente Actualizado correctamente.";        		
					} else {
						System.err.println("ERROR ----- Fallo al actualizar la linea -- ERROR");
						txmensaj ="Cliente Actualizado correctamente.";
					}
    				
    				UpdClientesDetalleBD updCliDetBD = new UpdClientesDetalleBD(upCliBDIn);
    				updCliDetBD.setConnection(con);
        			
        			insertado = updCliDetBD.execUpdate();
        			
    				if (insertado > 0){
    					System.out.println("Linea actualizada correctamente");
						txmensaj ="Cliente Actualizado correctamente.";
					} else {
						System.err.println("ERROR ----- Fallo al actualizar la linea --  ERROR");
						txmensaj ="Cliente Actualizado correctamente.";
					}
    				
    			} else {
    				//NUEVO CLIENTE
    				
    				idclient = getNumIdClient(idemisor,tpclient);
    				
    	        	upCliBDIn.setValue("idclient",idclient);
    	        	upCliBDIn.setValue("tpclient",tpclient);
    	        	upCliBDIn.setValue("cdintern",cdintern);
    	        	upCliBDIn.setValue("idemisor",idemisor);
    	        	updCliBD = new UpdClientesBD(upCliBDIn);
    	        	updCliBD.setConnection(con);
    	        	
        			insertado = updCliBD.execInsert();
					
        			if (insertado > 0){
						System.out.println(this.getClass().getName() +"Cliente creado correctamente ("+ idclient +")");
						txmensaj =" Cliente dado de alta correctamente.";
        		
					} else {
						System.err.println(this.getClass().getName() +"ERROR ----- Fallo al insertar el cliente -- ERROR ("+ idclient +")");
						txmensaj ="Fallo al actualizar el cliente.";
					}
        			
        			UpdClientesDetalleBD updCliDetBD = new UpdClientesDetalleBD(upCliBDIn);
        			updCliDetBD.setConnection(con);
        			
        			insertado = updCliDetBD.execInsert();
        			
    				if (insertado > 0){
						System.out.println(this.getClass().getName() +"Detalle cliente insertado correctamente("+ idclient +")");
						txmensaj +=" - (Detalle OK)";
        		
					} else {
						System.err.println(this.getClass().getName() +"ERROR ----- Fallo al insertar el detalle del cliente ("+ idclient +")");
						txmensaj +="Cliente Actualizado correctamente.";
					}
    			}
    			
        	} catch(SQLException e1){
        		System.err.println(this.getClass().getName() +" ERROR en la insercion/actualizacion del cliente ("+ idclient +") - "+ e1.getMessage());
        	}
        	
        	/*ListClientesBDIn listEmiBDIn = new ListClientesBDIn();
        	ListClientesBD listEmiBD = new ListClientesBD(listEmiBDIn);
        	listEmiBD.setConnection(con);
        	gridEmis = listEmiBD.execSelect();
        	*/
        	
        	output.setValue("idclient", idclient);
        	output.setValue("gridEmis", gridEmis);
        	output.setValue("cdintern", cdintern);
        	output.setValue("idemisor", idemisor);
        	output.setValue("tpclient", tpclient);
        	//output.setValue("gdClient", listClie);
        	output.setValue("txmensaj", txmensaj);
        
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
    public String getNumIdClient(String idemisor, String tpclient){
    	
    	int nwclient = 0;
    	int cdemisor = 0;
    	int numinter = 0;
    	
    	String nexClien = null;
    	
    	try {
    		
    		cdemisor = Integer.valueOf(idemisor);
    		
    		ListMaxClientesBDIn maxClienBDIn = new ListMaxClientesBDIn();
    		maxClienBDIn.setValue("idemisor",idemisor);
    		maxClienBDIn.setValue("tpclient",tpclient);
    		ListMaxClientesBD maxClien = new ListMaxClientesBD(maxClienBDIn);
    		maxClien.setConnection(con);
    		Grid gdClient = maxClien.execSelect();
    		
    		if (gdClient.rowCount() > 0){
    			nwclient = Integer.valueOf(gdClient.getStringCell(0, "idclient")) + 1;
    			numinter = nwclient - (cdemisor * 10000);
    			
    			cdintern = String.valueOf(numinter);
    			nexClien = String.valueOf(nwclient);
    			
    		} else {
    			cdintern = "1";
    			
    			nwclient = (cdemisor * 10000) + 1;
    			nexClien = String.valueOf(nwclient);
    		}
    		
    	} catch (Exception e) {
    		System.out.println(this.getClass().getName() +" No se ha podido recuperar el nuevo código de cliente (emisor: "+ idemisor +"). "+ e.getMessage());
    	}
    	
    	return nexClien;
    	
    }
    
    
}
	