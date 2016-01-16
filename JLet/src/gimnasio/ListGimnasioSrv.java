package gimnasio;


import gimnasio.bd.UpdGimnasioBD;
import gimnasio.bd.UpdGimnasioBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

public class ListGimnasioSrv extends Service {

	public ListGimnasioSrv() {
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
			output.addVariable("gdGimnas");
			output.addVariable("txmensaj");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idclient 	= "";
    

        //Varibales de salida
    	Grid gdGimnas 		= null;
        String txmensaj 	= "";
        
        //Otras Variables
        
        try {
        	
        	idclient = input.getStringValue("idclient");
        	try {
        		UpdGimnasioBDIn updGimnaBDIn = new UpdGimnasioBDIn();
        		updGimnaBDIn.setValue("idclient", idclient);
        		UpdGimnasioBD updGimnaBD = new UpdGimnasioBD(updGimnaBDIn);
        		updGimnaBD.setConnection(con);
        		gdGimnas = updGimnaBD.execSelect();
        		
        		
        	} catch (Exception e) {
        		System.err.println(this.getClass().getName() +" [ERROR ] Recuperando subastas. "+ e.getMessage());
        	}

        	
    		output.setValue("gdGimnas", gdGimnas);
		    output.setValue("txmensaj", txmensaj);
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
}
	