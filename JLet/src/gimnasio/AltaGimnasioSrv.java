package gimnasio;


import gimnasio.bd.MaxGimnasioBD;
import gimnasio.bd.MaxGimnasioBDIn;
import gimnasio.bd.UpdEquipamientoBD;
import gimnasio.bd.UpdEquipamientoBDIn;
import gimnasio.bd.UpdEquiposBD;
import gimnasio.bd.UpdEquiposBDIn;
import gimnasio.bd.UpdGimnasioBD;
import gimnasio.bd.UpdGimnasioBDIn;
import gimnasio.bd.UpdMarcasBD;
import gimnasio.bd.UpdMarcasBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import common.ExcelCreator;


public class AltaGimnasioSrv extends Service {

	ExcelCreator creador = null;
	
	public AltaGimnasioSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("idclient");
			input.addVariable("txnombre");
			input.addVariable("nifcifxx");
			input.addVariable("txcatego");
			input.addVariable("txciudad");
			input.addVariable("txdirecc");
			input.addVariable("cdpostal");
			input.addVariable("telefono");
			input.addVariable("txmailxx");
			input.addVariable("txrespon");
			input.addVariable("sexoxxxx");
			input.addVariable("txnomres");
			input.addVariable("tfnomovi");
			input.addVariable("mailresp");	
			input.addVariable("longitud");
			input.addVariable("latitudx");
		
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
			output.addVariable("gdEquipo");
			output.addVariable("gdEquipa");
			output.addVariable("gdMarcas");
			output.addVariable("txmensaj");
			output.addVariable("idclient");
			output.addVariable("accionxx");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idclient = "";
    	String txnombre = ""; 
    	String nifcifxx = ""; 
    	String txcatego = ""; 
    	String txciudad = ""; 
    	String txdirecc = ""; 
    	String cdpostal = "";
    	String telefono = ""; 
    	String txmailxx = ""; 
    	String txrespon = ""; 
    	String sexoxxxx = ""; 
    	String txnomres = ""; 
    	String tfnomovi = ""; 
    	String mailresp = "";
    	String longitud = ""; 
    	String latitudx = "";

        //Varibales de salida
    	Grid gdEquipo = null;
    	Grid gdEquipa = null;
    	Grid gdMarcas = null;
        String maxgimna = "";
        String accionxx = "";
        
        //Otras Variables
        
        
        try {
        	idclient = input.getStringValue("idclient");
        	txnombre = input.getStringValue("txnombre");
        	nifcifxx = input.getStringValue("nifcifxx");
        	txcatego = input.getStringValue("txcatego");
        	txciudad = input.getStringValue("txciudad");
        	txdirecc = input.getStringValue("txdirecc");
        	cdpostal = input.getStringValue("cdpostal");
        	telefono = input.getStringValue("telefono");
        	txmailxx = input.getStringValue("txmailxx");
        	txrespon = input.getStringValue("txrespon");
        	sexoxxxx = input.getStringValue("sexoxxxx");
        	txnomres = input.getStringValue("txnomres");
        	tfnomovi = input.getStringValue("tfnomovi");
        	mailresp = input.getStringValue("mailresp");
        	longitud = input.getStringValue("longitud");
        	latitudx = input.getStringValue("latitudx");

        	int insertado = 0;
        	
     
        	try{
        		
        	
        			UpdGimnasioBDIn upGimnaBDIn = new UpdGimnasioBDIn();
        			upGimnaBDIn.setValue("idclient",idclient);
        			upGimnaBDIn.setValue("txnombre",txnombre);
        			upGimnaBDIn.setValue("nifcifxx",nifcifxx);
        			upGimnaBDIn.setValue("txcatego",txcatego);
        			upGimnaBDIn.setValue("txciudad",txciudad);
        			upGimnaBDIn.setValue("txdirecc",txdirecc);
        			upGimnaBDIn.setValue("cdpostal",cdpostal);
        			upGimnaBDIn.setValue("telefono",telefono);
        			upGimnaBDIn.setValue("txmailxx",txmailxx);
        			upGimnaBDIn.setValue("txrespon",txrespon);
        			upGimnaBDIn.setValue("sexoxxxx",sexoxxxx);
        			upGimnaBDIn.setValue("txnomres",txnomres);
        			upGimnaBDIn.setValue("tfnomovi",tfnomovi);
        			upGimnaBDIn.setValue("mailresp",mailresp);
        			upGimnaBDIn.setValue("longitud",longitud);
        			upGimnaBDIn.setValue("latitudx",latitudx);
        			UpdGimnasioBD updGimnaBD = new UpdGimnasioBD(upGimnaBDIn);
        			updGimnaBD.setConnection(con);
        			
        			if(idclient!=null && !idclient.equals("")){
        				insertado = updGimnaBD.execUpdate();
        			}else{
        				insertado = updGimnaBD.execInsert();	
        			}
        			
        			
					
        			if( insertado >0){
						System.out.println("Linea actualizada correctamente");
						
						Grid gdGimnas = null;
						
						if(idclient!=null && !idclient.equals("")){
							maxgimna = idclient;
							accionxx = "M";
							
							UpdEquipamientoBDIn upEquipaBDIn = new UpdEquipamientoBDIn();
			    			upEquipaBDIn.setValue("idclient",idclient);
			    			UpdEquipamientoBD upEquipaBD = new UpdEquipamientoBD(upEquipaBDIn);
			    			upEquipaBD.setConnection(con);
			    			gdEquipa = upEquipaBD.execSelect();
							
							
	        			}else{
	        				
	        				MaxGimnasioBDIn maxGimnaBDIn = new MaxGimnasioBDIn();
							MaxGimnasioBD MaxGimnaBD = new MaxGimnasioBD(maxGimnaBDIn);
							MaxGimnaBD.setConnection(con);
							
							gdGimnas = MaxGimnaBD.execSelect();
							maxgimna = gdGimnas.getStringCell(0, "idclient");
							accionxx = "A";
	        			}
						
						
						UpdEquiposBDIn updEquiBDIn = new UpdEquiposBDIn();
						UpdEquiposBD updEquiBD = new UpdEquiposBD(updEquiBDIn);
						updEquiBD.setConnection(con);
						
						gdEquipo = updEquiBD.execSelect();
						
						UpdMarcasBDIn updMarcaBDIn = new UpdMarcasBDIn();
						UpdMarcasBD updMarcaBD 	= new UpdMarcasBD(updMarcaBDIn);
						updMarcaBD.setConnection(con);
						
						gdMarcas = updMarcaBD.execSelect();
						
					}else{
						System.err.println("ERROR ----- Fallo al actualizar la linea -- ERROR");
					}
        		
        			//gdSubast = listarSubastas();
        			
        			
        	   }catch(Exception e1){
        		   e1.printStackTrace();
        	   }
        	
        	output.setValue("gdEquipo", gdEquipo);
        	output.setValue("gdMarcas", gdMarcas);
        	output.setValue("gdEquipa", gdEquipa);
        	output.setValue("txmensaj", "OK");
        	output.setValue("accionxx", accionxx);
        	output.setValue("idclient", maxgimna);
        
        } catch (Exception e1) {
			e1.printStackTrace();
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
	