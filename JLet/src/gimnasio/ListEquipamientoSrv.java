package gimnasio;


import gimnasio.bd.UpdEquipamientoBD;
import gimnasio.bd.UpdEquipamientoBDIn;
import gimnasio.bd.UpdEquiposBD;
import gimnasio.bd.UpdEquiposBDIn;
import gimnasio.bd.UpdMarcasBD;
import gimnasio.bd.UpdMarcasBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

public class ListEquipamientoSrv extends Service {

	public ListEquipamientoSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("idclient");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
			output.addVariable("gdMarcas");
			output.addVariable("gdEquipo");
			output.addVariable("gdEquipa");
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
    	String idclient 	= "";
    

        //Varibales de salida
    	Grid gdMarcas 	= null;
    	Grid gdEquipo 	= null;
    	Grid gdGimnas 	= null;
    	Grid gdEquipa	= null;
        String txmensaj = "";
        
        //Otras Variables
        
        try {
        	
        	idclient = input.getStringValue("idclient");
        	try {
        		
        		
        		UpdEquiposBDIn updEquiBDIn = new UpdEquiposBDIn();
				UpdEquiposBD updEquiBD = new UpdEquiposBD(updEquiBDIn);
				updEquiBD.setConnection(con);
				gdEquipo = updEquiBD.execSelect();
				
				UpdMarcasBDIn updMarcaBDIn = new UpdMarcasBDIn();
				UpdMarcasBD updMarcaBD 	= new UpdMarcasBD(updMarcaBDIn);
				updMarcaBD.setConnection(con);
				gdMarcas = updMarcaBD.execSelect();
        		
        		UpdEquipamientoBDIn upEquipaBDIn = new UpdEquipamientoBDIn();
    			upEquipaBDIn.setValue("idclient",idclient);
    			UpdEquipamientoBD upEquipaBD = new UpdEquipamientoBD(upEquipaBDIn);
    			upEquipaBD.setConnection(con);
    			gdEquipa = upEquipaBD.execSelect();
        		
        		
        	} catch (Exception e) {
        		System.err.println(this.getClass().getName() +" [ERROR ] Recuperando subastas. "+ e.getMessage());
        	}
        	
        	output.setValue("gdMarcas", gdMarcas);
        	output.setValue("gdEquipo", gdEquipo);
        	output.setValue("gdEquipa", gdEquipa);
		    output.setValue("idclient", idclient);
		    output.setValue("accionxx", "M");
		    
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
    
    
}
	