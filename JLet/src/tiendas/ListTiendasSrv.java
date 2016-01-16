package tiendas;


import tiendas.bd.UpdTiendasBD;
import tiendas.bd.UpdTiendasBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

public class ListTiendasSrv extends Service {

	public ListTiendasSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("idemisor");
			input.addVariable("idtienda");
			input.addVariable("tpaccion");
			
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
			output.addVariable("gdTienda");
			output.addVariable("txmensaj");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor = "";
    	String idtienda = "";
    	String tpaccion = "";
    

        //Varibales de salida
    	Grid gdTienda 		= null;
        String txmensaj 	= "";
        
        //Otras Variables
        
        try {
        	
        	idemisor = input.getStringValue("idemisor");
        	idtienda = input.getStringValue("idtienda");
        	tpaccion = input.getStringValue("tpaccion");
        	try {
        		
        		if(tpaccion == null || !tpaccion.equals("AL")){
	        		UpdTiendasBDIn updTiendasBDIn = new UpdTiendasBDIn();
	        		updTiendasBDIn.setValue("idemisor", idemisor);
	        		updTiendasBDIn.setValue("idtienda", idtienda);
	        		UpdTiendasBD updTiendasBD = new UpdTiendasBD(updTiendasBDIn);
	        		updTiendasBD.setConnection(con);
	        		gdTienda = updTiendasBD.execSelect();
        		}
        		
        	} catch (Exception e) {
        		System.err.println(this.getClass().getName() +" [ERROR ] Recuperando subastas. "+ e.getMessage());
        	}

        	output.setValue("idemisor", idemisor);
    		output.setValue("gdTienda", gdTienda);
		    output.setValue("txmensaj", txmensaj);
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
}
	