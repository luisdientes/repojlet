package comercio;


import java.text.DecimalFormat;

import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import comercio.bd.ListLineasEnvioBD;
import comercio.bd.ListLineasEnvioBDIn;
import comercio.bd.UpdLineasBD;
import comercio.bd.UpdLineasBDIn;
import common.Divisa;


public class TempEnvioSrv extends Service {

    public TempEnvioSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();	
			input.addVariable("imeicode");
			input.addVariable("txmarcax");
			input.addVariable("txmodelo");
			input.addVariable("idcolorx");
			input.addVariable("pricechf");
			input.addVariable("porcmarg");
			input.addVariable("txprovid");
			input.addVariable("txbuyerx");
			input.addVariable("txfundin");
			input.addVariable("withboxx");
			input.addVariable("withusbx");
			input.addVariable("idcatego");
			input.addVariable("tipoOper");
			input.addVariable("idlineax");
			input.addVariable("quantity");
			input.addVariable("tpproduc");
			input.addVariable("idemisor");

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
			output.addVariable("gridLine");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output) throws Exception{
        
    	//Varibales de entrada
    	String imeicode = "";
    	String txmarcax = "";
    	String txmodelo = "";
    	String idcolorx = "";
    	String pricechf = "";
    	String porcmarg = "";
    	String txprovid = "";
    	String txbuyerx = "";
    	String txfundin = "";
    	String withboxx = "";
    	String withusbx = "";
    	String idcatego = ""; 
    	String tipoOper = "";
    	String idlineax = "";
    	String quantity = "";
    	String tpproduc = "";
    	String idemisor = "";
    	Grid gridResu 	=  null;
        
        //Varibales de salida
        String txmensaj = "";   	
			
        //Otras Variables
        String priceusd = "";
        String cotizusd = "";
        double fixinusd = 1;
        
        DecimalFormat formatDivi = new DecimalFormat("#####0.00");
        
	    try{
	    	idlineax = input.getStringValue("idlineax");
	    	tipoOper = input.getStringValue("tipoOper");
			imeicode = input.getStringValue("imeicode");
			txmarcax = input.getStringValue("txmarcax");
			txmodelo = input.getStringValue("txmodelo");
			idcolorx = input.getStringValue("idcolorx");
			pricechf = input.getStringValue("pricechf");
			porcmarg = input.getStringValue("porcmarg");
			txprovid = input.getStringValue("txprovid");
			txbuyerx = input.getStringValue("txbuyerx");
			txfundin = input.getStringValue("txfundin");
			withboxx = input.getStringValue("withboxx");
			withusbx = input.getStringValue("withusbx");
			idcatego = input.getStringValue("idcatego");
			quantity = input.getStringValue("quantity");
			tpproduc = input.getStringValue("tpproduc");
			idemisor = input.getStringValue("idemisor");
	    	
			try{
				
				if(idlineax != null){
					UpdLineasBDIn InsLinBDIn= new UpdLineasBDIn();
			    	InsLinBDIn.setValue("idlineax",idlineax);
			    	UpdLineasBD insLinBD = new UpdLineasBD(InsLinBDIn);
		    		insLinBD.setConnection(con);
		    		
					int liInsert = insLinBD.execDelete();
					
					if (liInsert == 1){
						txmensaj = "Se ha borrado la línea correctamente.";
				    } else {
				    	txmensaj = "Se ha producido un error al borrar la línea ("+ liInsert +")";
				    }
					
				}else{
					
					try {
		 	        	Divisa divisa = new Divisa();
		 	        	divisa.setConnection(con);

		 	        	String cotizchf = divisa.getFixingUSD("CHF");
		 	        	fixinusd = Double.parseDouble(cotizchf);
		 	        	double dpricchf = Double.parseDouble(pricechf);
		 	        	
		 	        	double dpricusd = dpricchf * fixinusd;
		 	        	
		 	        	priceusd = formatDivi.format(dpricusd);
		 	        	priceusd = priceusd.replaceAll(",",".");
		 	        	
		 	        	if(quantity == null){
		 	        		quantity = "1";
		 	        	}
		 	        	
		 	        } catch (Exception e) {
		 	        	System.out.println("ERROR realizando operaciones de Fixing ");
		 	        }
					
			    	UpdLineasBDIn InsLinBDIn= new UpdLineasBDIn();
			    	InsLinBDIn.setValue("codeenvi",null);
			    	InsLinBDIn.setValue("imeicode",imeicode);
			    	InsLinBDIn.setValue("idemisor",idemisor);
			    	InsLinBDIn.setValue("txmarcax",txmarcax);
			    	InsLinBDIn.setValue("txmodelo",txmodelo);
			    	InsLinBDIn.setValue("idcolorx",idcolorx);
			    	InsLinBDIn.setValue("pricechf",pricechf);
			    	InsLinBDIn.setValue("priceusd",priceusd);
			    	InsLinBDIn.setValue("porcmarg",porcmarg);
			    	InsLinBDIn.setValue("txprovid",txprovid);
			    	InsLinBDIn.setValue("txbuyerx",txbuyerx);
			    	InsLinBDIn.setValue("txfundin",txfundin);
			    	InsLinBDIn.setValue("withboxx",withboxx);
			    	InsLinBDIn.setValue("withusbx",withusbx);
			    	InsLinBDIn.setValue("idcatego",idcatego);
			    	InsLinBDIn.setValue("quantity",quantity);
			    	InsLinBDIn.setValue("tpproduc",tpproduc);
			    	
	
		    		UpdLineasBD insLinBD = new UpdLineasBD(InsLinBDIn);
		    		insLinBD.setConnection(con);
		    		
					int liInsert = insLinBD.execInsert();
					
					if (liInsert == 1){
						txmensaj = "Se ha insertado la línea correctamente.";
				    } else {
				    	txmensaj = "Se ha producido un error al insertar la línea ("+ liInsert +")";
				    }
				}	
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			ListLineasEnvioBDIn lineasBDIn = new ListLineasEnvioBDIn();
		  	lineasBDIn.setValue("codeenvi",null);
		  	lineasBDIn.setValue("tipoenvi",tpproduc);
		  	lineasBDIn.setValue("idemisor",idemisor);
			ListLineasEnvioBD lineasBD = new ListLineasEnvioBD(lineasBDIn);
			lineasBD.setConnection(con);
			gridResu = lineasBD.execSelect();
	    	
	    	// variables de salida
	    	output.setValue("txmensaj", txmensaj);
	    	output.setGrid("gridLine", gridResu);
	    	
	    	
		} catch (Exception e1) {
			e1.printStackTrace();
		}   	
    }
       
}
	