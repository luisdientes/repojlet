package stock;


import stock.bd.ListStockBD;
import stock.bd.ListStockBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import common.bd.ListColoresBD;
import common.bd.ListColoresBDIn;


public class InitStockSrv extends Service {

    public InitStockSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();	
			input.addVariable("cdpantal");
			input.addVariable("idemisor");
			input.addVariable("imeicode");
			input.addVariable("cdestado");
			input.addVariable("tipocons");

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
			output.addVariable("gridLine");
			output.addVariable("gridColo");
			output.addVariable("txmensaj");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor = null;
    	String imeicode = null;
    	String cdestado = null;
    	String tipocons = null;
        
        //Varibales de salida
    	Grid gridLine = null;
    	Grid gridColo = null;
    	String cdpantal = null;
    	
        //Otras Variables

    	try {
        	
        	cdpantal = input.getStringValue("cdpantal");
        	idemisor = input.getStringValue("idemisor");
        	imeicode = input.getStringValue("imeicode");
        	cdestado = input.getStringValue("cdestado");
        	tipocons = input.getStringValue("tipocons");
        	
	        try {
	        	ListStockBDIn lineasBDIn= new ListStockBDIn();
	        	lineasBDIn.setValue("idemisor",idemisor);
	        	lineasBDIn.setValue("imeicode",imeicode);
	        	lineasBDIn.setValue("cdestado",cdestado);
			  	ListStockBD lineasBD = new ListStockBD(lineasBDIn);
			  	lineasBD.setConnection(con);
				gridLine = lineasBD.execSelect();	        	
	        } catch (Exception e) {
				System.err.println(this.getClass().getName() +" ERROR recogiendo lineas. "+ e.getMessage());
			}
        	
			try {
				ListColoresBDIn coloresBDIn = new ListColoresBDIn();
			  	ListColoresBD coloresBD = new ListColoresBD(coloresBDIn);
			  	coloresBD.setConnection(con);
				gridColo = coloresBD.execSelect();
			} catch (Exception e) {
				System.err.println(this.getClass().getName() +" ERROR recogiendo los colores. "+ e.getMessage());
			}
        	
			output.setValue("idemisor", idemisor);
			output.setValue("gridLine", gridLine);
		    output.setValue("gridColo", gridColo);
		    output.setValue("txmensaj", "OK");

        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
}
	