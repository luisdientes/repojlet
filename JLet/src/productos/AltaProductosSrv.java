package productos;


import productos.bd.UpdPiezasBD;
import productos.bd.UpdPiezasBDIn;
import productos.bd.UpdProducBD;
import productos.bd.UpdProducBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import common.ExcelCreator;


public class AltaProductosSrv extends Service {

	ExcelCreator creador = null;
	
	public AltaProductosSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("idmarcax");
			input.addVariable("txmodelo");
			input.addVariable("txcatego");
			input.addVariable("impdefco");
			input.addVariable("impdefve");
			input.addVariable("txdescri");
			input.addVariable("nameespa");
			input.addVariable("namephon");
			input.addVariable("codepiez");
			input.addVariable("preciopr");
			input.addVariable("txmarcax");
			input.addVariable("cdcolorx");
			input.addVariable("tpproduc");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
			output.addVariable("gdProduc");
			output.addVariable("gdPiezas");
			
			output.addVariable("txmensaj");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	 String tpproduc = "";
    	
    	//productos
    	String idmarcax = "";
    	String txmodelo = "";
    	String txcatego = "";
    	String impdefco = "";
    	String impdefve = "";
    	
    	//piezas
    	String txdescri = ""; 
    	String nameespa = ""; 
    	String namephon = ""; 
    	String codepiez = ""; 
    	String preciopr = ""; 
    	String txmarcax = ""; 
    	String cdcolorx = ""; 

        //Varibales de salida
    	Grid gdProduc = null;
    	Grid gdPiezas = null;
        String txmensaj = "";
        
        //Otras Variables
        
        
        try {
        	
        	tpproduc = input.getStringValue("tpproduc");
        	
        	// telefonos y productos
        	idmarcax = input.getStringValue("idmarcax");
        	txmodelo = input.getStringValue("txmodelo");
        	txcatego = input.getStringValue("txcatego");
        	impdefco = input.getStringValue("impdefco");
        	impdefve = input.getStringValue("impdefve");
        	
        	//piezas
        	txdescri = input.getStringValue("txdescri");
        	nameespa = input.getStringValue("nameespa");
        	namephon = input.getStringValue("namephon");
        	codepiez = input.getStringValue("codepiez");
        	preciopr = input.getStringValue("preciopr");
        	txmarcax = input.getStringValue("idmarcax");
        	cdcolorx = input.getStringValue("cdcolorx");

        	int insertado = 0;
     
        	try{
        		//piezas
        		
        		if(tpproduc !=null && tpproduc.equals("PI")){ 
        			
        			UpdPiezasBDIn upPiezBDIn = new UpdPiezasBDIn();
        			upPiezBDIn.setValue("txdescri",txdescri);
        			upPiezBDIn.setValue("nameespa",nameespa);
        			upPiezBDIn.setValue("namephon",namephon);
        			upPiezBDIn.setValue("codepiez",codepiez);
        			upPiezBDIn.setValue("preciopr",preciopr);
        			upPiezBDIn.setValue("txmarcax",txmarcax);
        			upPiezBDIn.setValue("cdcolorx",cdcolorx);
        			
        			UpdPiezasBD updPiezBD = new UpdPiezasBD(upPiezBDIn);
        			updPiezBD.setConnection(con);
        			
        			insertado = updPiezBD.execInsert();
        			
        			if( insertado >0){
						System.out.println("Linea actualizada correctamente");
						UpdPiezasBDIn selpiezBDIn = new UpdPiezasBDIn();
						UpdPiezasBD selPiezBD = new UpdPiezasBD(selpiezBDIn);
						selPiezBD.setConnection(con);
						gdPiezas = selPiezBD.execSelect();
        		
					}else{
						System.err.println("ERROR ----- Fallo al actualizar la linea -- ERROR");
					}
        			
        			
        			//telefonos y poroductos
        		}else if(tpproduc !=null && tpproduc.equals("PR")){ 
        			UpdProducBDIn upProdBDIn = new UpdProducBDIn();
        			upProdBDIn.setValue("idmarcax",idmarcax);
        			upProdBDIn.setValue("txmodelo",txmodelo);
        			upProdBDIn.setValue("txcatego",txcatego);
        			upProdBDIn.setValue("impdefco",impdefco);
        			upProdBDIn.setValue("impdefve",impdefve);
        			UpdProducBD updProdBD = new UpdProducBD(upProdBDIn);
        			updProdBD.setConnection(con);
        			
        			insertado = updProdBD.execInsert();
        			if( insertado >0){
						System.out.println("Linea actualizada correctamente");
						UpdProducBDIn selproBDIn = new UpdProducBDIn();
						UpdProducBD selproBD = new UpdProducBD(selproBDIn);
						selproBD.setConnection(con);
						gdProduc = selproBD.execSelect();
        		
					}else{
						System.err.println("ERROR ----- Fallo al actualizar la linea -- ERROR");
					}
 			
        		}
	
        	   }catch(Exception e1){
        		   e1.printStackTrace();
        	   }
        	
        	output.setValue("gdProduc", gdProduc);
        	output.setValue("gdPiezas", gdPiezas);
        	
        	output.setValue("txmensaj", "OK");
        
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
	