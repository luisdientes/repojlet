package gimnasio;


import gimnasio.bd.UpdEquipamientoBD;
import gimnasio.bd.UpdEquipamientoBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import common.ExcelCreator;


public class AltaEquiposSrv extends Service {

	ExcelCreator creador = null;
	
	public AltaEquiposSrv() {
      super();
      
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("idclient");
			input.addVariable("cdequipo");
			input.addVariable("idmarcax");
			input.addVariable("txmodelo");
			input.addVariable("tipogama");
			input.addVariable("cantidad");
			input.addVariable("aniocomp");
			input.addVariable("cdestado");
			input.addVariable("txrespon");
			input.addVariable("sexoxxxx");
			input.addVariable("txnomres");
			input.addVariable("tfnomovi");
			input.addVariable("mailresp");
			input.addVariable("accionxx");	
		
			
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
			output.addVariable("txmensaj");
			output.addVariable("maxgimna");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	
    	String idclient = "";
    	String cdequipo = ""; 
    	String idmarcax = ""; 
    	String txmodelo = ""; 
    	String tipogama = ""; 
    	String cantidad = ""; 
    	String aniocomp = ""; 
    	String cdestado = ""; 
    	String txrespon = ""; 
    	String sexoxxxx = ""; 
    	String txnomres = ""; 
    	String tfnomovi = ""; 
    	String mailresp = "";
    	String accionxx = "";

        //Varibales de salida
    	Grid gdEquipo = null;
        String txmensaj = "";
        String maxgimna = "";
        
        //Otras Variables
        
        
        try {
        	
        	String [] arrCdequi = null;
        	String [] arrMarcax = null;
        	String [] arrGamaxx = null;
        	String [] arrModelo = null;
        	String [] arrCantid = null;
        	String [] arrAnioco = null;
        	String [] arrEstado = null;
        	
        	
        	idclient = input.getStringValue("idclient");
        	cdequipo = input.getStringValue("cdequipo");
        	idmarcax = input.getStringValue("idmarcax");
        	txmodelo = input.getStringValue("txmodelo");
        	tipogama = input.getStringValue("tipogama");
        	cantidad = input.getStringValue("cantidad");
        	cdestado = input.getStringValue("cdestado");
        	aniocomp = input.getStringValue("aniocomp");
        	accionxx = input.getStringValue("accionxx");
        
        	
        	arrCdequi = cdequipo.split(",");
        	arrMarcax = idmarcax.split(",");
        	arrModelo = txmodelo.split(",");
        	arrGamaxx = tipogama.split(",");
        	arrCantid = cantidad.split(",");
        	arrAnioco = aniocomp.split(",");
        	arrEstado = cdestado.split(",");
        	
        	if(accionxx !=null && accionxx.equals("M") ){
        		UpdEquipamientoBDIn upEquipaBDIn = new UpdEquipamientoBDIn();
    			upEquipaBDIn.setValue("idclient",idclient);
    			UpdEquipamientoBD upEquipaBD = new UpdEquipamientoBD(upEquipaBDIn);
				upEquipaBD.setConnection(con);
				upEquipaBD.execDelete();	
			}
        	
        	for(int i=0 ;i< arrCdequi.length;i++){
        		
        		UpdEquipamientoBDIn upEquipaBDIn = new UpdEquipamientoBDIn();
        			upEquipaBDIn.setValue("idclient",idclient);
	        		upEquipaBDIn.setValue("cdequipa",arrCdequi[i]);
	        		upEquipaBDIn.setValue("idmarcax",arrMarcax[i]);
	        		upEquipaBDIn.setValue("txmodelo",arrModelo[i]);
	        		upEquipaBDIn.setValue("tipogama",arrGamaxx[i]);
	        		upEquipaBDIn.setValue("cantidad",arrCantid[i]);
	        		upEquipaBDIn.setValue("aniocomp",arrAnioco[i]);
	        		upEquipaBDIn.setValue("cdestado",arrEstado[i]);
	        		
    			UpdEquipamientoBD upEquipaBD = new UpdEquipamientoBD(upEquipaBDIn);
    			upEquipaBD.setConnection(con);
    			upEquipaBD.execInsert();
    		
    			
    			
        		
        		System.out.println("Lineas"+arrCdequi[i]);
        		System.out.println("------------marca"+arrMarcax[i]);
        	}

        	int insertado = 0;
        	
        	
        	output.setValue("gdEquipo", gdEquipo);
        	output.setValue("txmensaj", "OK");
        	output.setValue("maxgimna", maxgimna);
        
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
   /* public Grid listarSubastas(){
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
    }*/
       
    
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
	