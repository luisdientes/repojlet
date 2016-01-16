package reparaciones;


import reparaciones.bd.ListReparaBD;
import reparaciones.bd.ListReparaBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

public class ListReparaSrv extends Service {

	public ListReparaSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("idemisor");
			input.addVariable("cdrecibo");
			input.addVariable("tpclient");
			input.addVariable("fhdesdex");
			input.addVariable("fhhastax");
			input.addVariable("logoemis");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
			output.addVariable("tpclient");
			output.addVariable("idemisor");
			output.addVariable("gdRepara");
			output.addVariable("txmensaj");
			output.addVariable("logoemis");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor = "";
    	String tpclient = "";
    	String cdrecibo	= "";
    	String fhdesdex = "";
    	String fhhastax = "";
    	String logoemis = "";

        //Varibales de salida
    	Grid gdRepara 		= null;
        String txmensaj 	= "";
        
        //Otras Variables
        
        try {
        	
        	idemisor = input.getStringValue("idemisor");
        	tpclient = input.getStringValue("tpclient");
        	cdrecibo = input.getStringValue("cdrecibo");
        	fhdesdex = input.getStringValue("fhdesdex");
        	fhdesdex = input.getStringValue("fhhastax");
        	logoemis = input.getStringValue("logoemis");
        	
        	
        	try {
        		ListReparaBDIn reparaBDIn = new ListReparaBDIn();
        		reparaBDIn.setValue("idemisor", idemisor);
        		reparaBDIn.setValue("tpclient", tpclient);
        		reparaBDIn.setValue("cdrecibo", cdrecibo);
        		//SIN INCLUIR EN LA QUERY
        		reparaBDIn.setValue("fhdesdex", fhdesdex);
        		reparaBDIn.setValue("fhhastax", fhhastax);
        		ListReparaBD reparaBD = new ListReparaBD(reparaBDIn);
        		reparaBD.setConnection(con);
        		gdRepara = reparaBD.execSelect();
        		
        	} catch (Exception e) {
        		System.err.println(this.getClass().getName() +" [ERROR ] Recuperando recibos. "+ e.getMessage());
        	}

        	
        	System.out.println(this.getClass().getName() +"FLAG 2");
        	
    		output.setValue("gdRepara", gdRepara);
    		output.setValue("idemisor", idemisor);
    		output.setValue("tpclient", tpclient);
    		output.setValue("logoemis", logoemis);
		    output.setValue("txmensaj", txmensaj);
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
}
	