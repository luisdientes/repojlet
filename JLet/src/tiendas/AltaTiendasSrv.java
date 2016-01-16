package tiendas;


import tiendas.bd.UpdTiendasBD;
import tiendas.bd.UpdTiendasBDIn;
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


public class AltaTiendasSrv extends Service {

	ExcelCreator creador = null;
	
	public AltaTiendasSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("idtienda");
			input.addVariable("idemisor");
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
			output.addVariable("gdTienda");
			output.addVariable("idemisor");
			output.addVariable("txmensaj");
			output.addVariable("idtienda");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor = "";
    	String idtienda = "";
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
    	Grid gdTienda = null;
        String maxgimna = "";
        String accionxx = "";
        
        //Otras Variables
        
        
        try {
        	idtienda = input.getStringValue("idtienda");
        	idemisor = input.getStringValue("idemisor");
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
        		
        	
        			UpdTiendasBDIn updTiendasBDIn = new UpdTiendasBDIn();
        			updTiendasBDIn.setValue("idtienda",idtienda);
        			updTiendasBDIn.setValue("idemisor",idemisor);
        			updTiendasBDIn.setValue("txnombre",txnombre);
        			updTiendasBDIn.setValue("nifcifxx",nifcifxx);
        			updTiendasBDIn.setValue("txcatego",txcatego);
        			updTiendasBDIn.setValue("txciudad",txciudad);
        			updTiendasBDIn.setValue("txdirecc",txdirecc);
        			updTiendasBDIn.setValue("cdpostal",cdpostal);
        			updTiendasBDIn.setValue("telefono",telefono);
        			updTiendasBDIn.setValue("txmailxx",txmailxx);
        			updTiendasBDIn.setValue("txrespon",txrespon);
        			updTiendasBDIn.setValue("sexoxxxx",sexoxxxx);
        			updTiendasBDIn.setValue("txnomres",txnomres);
        			updTiendasBDIn.setValue("tfnomovi",tfnomovi);
        			updTiendasBDIn.setValue("mailresp",mailresp);
        			updTiendasBDIn.setValue("longitud",longitud);
        			updTiendasBDIn.setValue("latitudx",latitudx);
        			UpdTiendasBD updTiendaBD = new UpdTiendasBD(updTiendasBDIn);
        			updTiendaBD.setConnection(con);
        			
        			if(idtienda!=null && !idtienda.equals("")){
        				insertado = updTiendaBD.execUpdate();
        			}else{
        				insertado = updTiendaBD.execInsert();	
        			}
        			
        			
					
        			if( insertado >0){
						System.out.println("Linea actualizada correctamente");
						
						UpdTiendasBDIn updTiendaBDIn = new UpdTiendasBDIn();
						updTiendaBDIn.setValue("idemisor", idemisor);
		        		UpdTiendasBD updTiendasBD = new UpdTiendasBD(updTiendaBDIn);
		        		updTiendasBD.setConnection(con);
		        		gdTienda = updTiendasBD.execSelect();
						
						
						
					}else{
						System.err.println("ERROR ----- Fallo al actualizar la linea -- ERROR");
					}
        		
        			//gdSubast = listarSubastas();
        			
        			
        	   }catch(Exception e1){
        		   e1.printStackTrace();
        	   }
        	
        	output.setValue("gdTienda", gdTienda);
        	output.setValue("idemisor", idemisor);
        	output.setValue("txmensaj", "OK");
        	output.setValue("idtienda", idtienda);
        
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
	