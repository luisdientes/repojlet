package comercio;


import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;


public class ListStockSrv extends Service {

    public ListStockSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();	
			input.addVariable("idemisor");
			input.addVariable("cdestado");
			input.addVariable("tipocons");
			input.addVariable("tpclient");

		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
			output.addVariable("txmensaj");
			output.addVariable("idemisor");
			output.addVariable("gridLine");
			output.addVariable("tpclient");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor = null;
    	String cdestado = null;
    	String tipocons = null;
    	String tpclient = null;
    	
        
        //Varibales de salida
    	Grid gridLine = null;
    	
        try {
        	
        	idemisor = input.getStringValue("idemisor");
        	tpclient = input.getStringValue("tpclient");
        	cdestado = input.getStringValue("cdestado");
        	tipocons = input.getStringValue("tipocons");
        	
	        try {
	        	
	        	InitStockSrv lstStockSrv = new InitStockSrv();
	    		ObjectIO srvIn 	= lstStockSrv.instanceOfInput();
	    		ObjectIO srvOut = lstStockSrv.instanceOfOutput(); 
	    		
	    		srvIn.setValue("idemisor", idemisor);
	    		srvIn.setValue("tpclient", tpclient);
	    		srvIn.setValue("cdestado", cdestado);
	    		srvIn.setValue("tipocons", tipocons);
	    		
	    		lstStockSrv.setConnection(this.getConnection());
	    		lstStockSrv.process(srvIn, srvOut);
	    		
	    		gridLine = srvOut.getGrid("gridLine");
	 
	        } catch (Exception e) {
				System.err.println(this.getClass().getName() +" ERROR recogiendo lineas. "+ e.getMessage());
			}
    
        	output.setValue("idemisor", idemisor);
        	output.setValue("tpclient", tpclient);
		    output.setValue("gridLine", gridLine);
		    output.setValue("txmensaj", "OK");

        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
}
	