package comercio;


import java.text.DecimalFormat;

import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import comercio.bd.ListColoresBD;
import comercio.bd.ListColoresBDIn;
import comercio.bd.ListLineasEnvioBD;
import comercio.bd.ListLineasEnvioBDIn;


public class InitEnvioSrv extends Service {

    public InitEnvioSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();	
			input.addVariable("cdpantal");
			input.addVariable("idemisor");
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
			output.addVariable("tpclient");
			output.addVariable("gridLine");
			output.addVariable("gridColo");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada

        
        //Varibales de salida
    	Grid gridLine = null;
    	Grid gridColo = null;
    	Grid gdDivisa = null;
    	String cdpantal = null;
    	String idemisor = null;
    	String tipoenvi = null;
    	
        //Otras Variables
        DecimalFormat formatDivi = new DecimalFormat("#####0.00");
    	
        try {
        	
        	cdpantal = input.getStringValue("cdpantal");
        	idemisor = input.getStringValue("idemisor");
        	
        	if(cdpantal.equals("altapiez") ){
        		tipoenvi ="PI";
        	}else{
        		tipoenvi ="T";
        	}
        	
	        try {
	        	ListLineasEnvioBDIn lineasBDIn= new ListLineasEnvioBDIn();
			  	lineasBDIn.setValue("codeenvi",null);
			  	lineasBDIn.setValue("tipoenvi",tipoenvi);
			  	lineasBDIn.setValue("idemisor",idemisor);
				ListLineasEnvioBD lineasBD = new ListLineasEnvioBD(lineasBDIn);
				lineasBD.setConnection(con);
				gridLine = lineasBD.execSelect();
	        } catch (Exception e) {
				System.err.println(this.getClass().getName() +" ERROR recogiendo lineas. "+ e.getMessage());
			}
        	
        	System.out.println(this.getClass().getName() +"FLAG 2" + " ----------"+tipoenvi);
        	
				
			try {
				ListColoresBDIn coloresBDIn = new ListColoresBDIn();
			  	ListColoresBD coloresBD = new ListColoresBD(coloresBDIn);
			  	coloresBD.setConnection(con);
				gridColo = coloresBD.execSelect();
			} catch (Exception e) {
				System.err.println(this.getClass().getName() +" ERROR recogiendo los colores. "+ e.getMessage());
			}
        	
        	System.out.println(this.getClass().getName() +"FLAG 3");
			
			System.out.println(" FLAG He obtenido "+  gridColo.rowCount() +" colores. ");
        	
		    output.setValue("gridLine", gridLine);
		    output.setValue("gridColo", gridColo);
		    output.setValue("idemisor", idemisor);
		    output.setValue("txmensaj", "OK");

        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
    /*
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
    */ 
}
	