package gimnasio;


import gimnasio.bd.ListEquipamientoBD;
import gimnasio.bd.ListEquipamientoBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

public class UpdEquipamientoSrv extends Service {

	public UpdEquipamientoSrv() {
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
    	Grid gdEquipa	= null;
        String txmensaj = "";
        
        //Otras Variables
        
        try {
        	
        	idclient = input.getStringValue("idclient");
        	try {
        		
        		
        		ListEquipamientoBDIn listEquiBDIn = new ListEquipamientoBDIn();
        		listEquiBDIn.setValue("idclient", idclient);
        		ListEquipamientoBD listEquiBD = new ListEquipamientoBD(listEquiBDIn);
        		listEquiBD.setConnection(con);
				gdEquipa = listEquiBD.execSelect();
        		
        		
        	} catch (Exception e) {
        		System.err.println(this.getClass().getName() +" [ERROR ] Recuperando subastas. "+ e.getMessage());
        	}
        	output.setValue("gdEquipa", gdEquipa);
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
    
    
}
	