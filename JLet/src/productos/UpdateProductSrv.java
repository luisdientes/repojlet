package productos;


import productos.bd.UpdPiezasBD;
import productos.bd.UpdPiezasBDIn;
import productos.bd.UpdProducBD;
import productos.bd.UpdProducBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import common.ExcelCreator;


public class UpdateProductSrv extends Service {

	ExcelCreator creador = null;
	
	public UpdateProductSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("imagefil");

			
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
			output.addVariable("txmensaj");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	 String imagefil = "";
    	 String tpproduc = "PR";
    	
    

        //Varibales de salida
    	Grid gdProduc = null;
        String txmensaj = "";
        
        //Otras Variables
        
        
        try {
        	
        	imagefil = input.getStringValue("imagefil");
        	
 

        	int insertado = 0;
     
        	try{
        		//piezas
        		
        		if(tpproduc !=null && tpproduc.equals("PI")){ 
        			
        			UpdPiezasBDIn upPiezBDIn = new UpdPiezasBDIn();
        			upPiezBDIn.setValue("imagefil",imagefil);
        		
        			
        			UpdPiezasBD updPiezBD = new UpdPiezasBD(upPiezBDIn);
        			updPiezBD.setConnection(con);
        			
        			insertado = updPiezBD.execUpdate();
        			
        			if( insertado >0){
						System.out.println("Linea actualizada correctamente");
						UpdPiezasBDIn selpiezBDIn = new UpdPiezasBDIn();
						UpdPiezasBD selPiezBD = new UpdPiezasBD(selpiezBDIn);
						selPiezBD.setConnection(con);
						gdProduc = selPiezBD.execSelect();
        		
					}else{
						System.err.println("ERROR ----- Fallo al actualizar la linea -- ERROR");
					}
        			
        			
        			//telefonos y poroductos
        		}else if(tpproduc !=null && tpproduc.equals("PR")){ 
        			
        			System.out.println("Linea actualizada correctamente");
					UpdProducBDIn selproBDIn = new UpdProducBDIn();
					UpdProducBD selproBD = new UpdProducBD(selproBDIn);
					selproBD.setConnection(con);
					gdProduc = selproBD.execSelect();
        			
					String idgrupox = gdProduc.getStringCell(0, "idgrupox");
        			
        			UpdProducBDIn upProdBDIn = new UpdProducBDIn();
        			upProdBDIn.setValue("imagefil",imagefil);
        			upProdBDIn.setValue("idgrupox",idgrupox);
        			UpdProducBD updProdBD = new UpdProducBD(upProdBDIn);
        			updProdBD.setConnection(con);
        			int actualiza = updProdBD.execUpdate();
        			if( actualiza >0){
        				System.out.println("Imagen actualizada!!!!!!!!!");
    				
    					gdProduc = selproBD.execSelect();
        			}
 			
        		}
	
        	   }catch(Exception e1){
        		   e1.printStackTrace();
        	   }
        	
        	output.setValue("gdProduc", gdProduc);
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
	