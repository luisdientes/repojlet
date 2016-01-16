package stock;


import stock.bd.UpdInventBD;
import stock.bd.UpdInventBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;


public class ListInventSrv extends Service {

    public ListInventSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();	
			input.addVariable("idemisor");
			input.addVariable("cdestado");
			input.addVariable("nameinve");
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
			output.addVariable("nameinve");
			output.addVariable("gridLine");
			
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
    	String nameinve = null;
        
        //Varibales de salida
 
    	Grid gridLine = null; 
  
    	
        try {
        	
        	idemisor = input.getStringValue("idemisor");
        	cdestado = input.getStringValue("cdestado");
        	nameinve = input.getStringValue("nameinve");
        	UpdInventBDIn inventBDIn = new UpdInventBDIn();
			inventBDIn.setValue("idemisor",idemisor);
			inventBDIn.setValue("cdestado","N");
			inventBDIn.setValue("nameinve",nameinve);
			UpdInventBD lineasBD = new UpdInventBD(inventBDIn);
			lineasBD.setConnection(con);
			gridLine = lineasBD.execSelect();
		
        	output.setValue("idemisor", idemisor);
        	output.setValue("nameinve", nameinve);
		    output.setValue("gridLine", gridLine);
		    output.setValue("txmensaj", "OK");

        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
}
	