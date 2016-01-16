package comercio;


import java.text.DecimalFormat;

import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import comercio.bd.ListStockBD;
import comercio.bd.ListStockBDIn;
import comercio.bd.UpdStockBD;
import comercio.bd.UpdStockBDIn;
import common.Divisa;


public class TempStockSrv extends Service {

    public TempStockSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("idemisor");
			input.addVariable("imeicode");
			input.addVariable("txmarcax");
			input.addVariable("txmodelo");
			input.addVariable("idcolorx");
			input.addVariable("pricechf");
			input.addVariable("fixingxx");
			input.addVariable("cddivisa");
			input.addVariable("porcmarg");
			input.addVariable("txprovid");
			input.addVariable("txbuyerx");
			input.addVariable("txfundin");
			input.addVariable("withboxx");
			input.addVariable("withusbx");
			input.addVariable("idcatego");
			input.addVariable("tipoOper");
			input.addVariable("idlineax");
			input.addVariable("ctusdchf");

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
    	String idemisor = "";
    	String imeicode = "";
    	String txmarcax = "";
    	String txmodelo = "";
    	String idcolorx = "";
    	String pricechf = "";
    	String fixingxx = "";
    	String cddivisa = "";
    	String porcmarg = "";
    	String txprovid = "";
    	String txbuyerx = "";
    	String txfundin = "";
    	String withboxx = "";
    	String withusbx = "";
    	String idcatego = ""; 
    	String tipoOper = "";
    	String idlineax = "";
    	String ctusdchf = "";
    	Grid gridResu 	=  null;
        
        //Varibales de salida
        String txmensaj = "";   	
			
        //Otras Variables
        String priceusd = "";
        String cotizusd = "";
        String pricecmp = "";
        String diviscmp = "";
        String prusdcmp = "";
        String pricevnt = "";
        String divisvnt = "";
        String prusdvnt = "";
        double fixinusd = 1;
        
        DecimalFormat formatDivi = new DecimalFormat("#####0.00");
        
	    try{
	    	idlineax = input.getStringValue("idlineax");
	    	tipoOper = input.getStringValue("tipoOper");
	    	idemisor = input.getStringValue("idemisor");
			imeicode = input.getStringValue("imeicode");
			txmarcax = input.getStringValue("txmarcax");
			txmodelo = input.getStringValue("txmodelo");
			idcolorx = input.getStringValue("idcolorx");
			pricechf = input.getStringValue("pricechf");
			fixingxx = input.getStringValue("fixingxx");
			cddivisa = input.getStringValue("cddivisa");
			porcmarg = input.getStringValue("porcmarg");
			txprovid = input.getStringValue("txprovid");
			txbuyerx = input.getStringValue("txbuyerx");
			txfundin = input.getStringValue("txfundin");
			withboxx = input.getStringValue("withboxx");
			withusbx = input.getStringValue("withusbx");
			idcatego = input.getStringValue("idcatego");
			ctusdchf = input.getStringValue("ctusdchf");
	    	
			try{
	
				if(idlineax != null){ //borrar Linea
					UpdStockBDIn delStockBDIn= new UpdStockBDIn();
					delStockBDIn.setValue("idlineax",idlineax);
			    	UpdStockBD delStockBD = new UpdStockBD(delStockBDIn);
			    	delStockBD.setConnection(con);
		    		
					int liInsert = delStockBD.execDelete();
					
					if (liInsert == 1){
						txmensaj = "Se ha borrado la línea correctamente.";
				    } else {
				    	txmensaj = "Se ha producido un error al borrar la línea ("+ liInsert +")";
				    }
					
				}else{
					
					try {
						//Tratamiento de Divisas
						pricecmp = pricechf;
						diviscmp = cddivisa;
						pricevnt = pricechf;
						divisvnt = cddivisa;
						
						fixingxx = fixingxx.replace(",", ".");
						
						Double dpricecm = Double.parseDouble(pricecmp);
						Double dfixingx = Double.parseDouble(fixingxx);
						Double dprusdcm = dpricecm * dfixingx;
						Double dporcmar = Double.parseDouble(porcmarg);
						Double dprusdvn = dpricecm * dfixingx + (1 + (dporcmar / 100));
						
						prusdcmp = String.valueOf(dprusdcm);
						prusdvnt = String.valueOf(dprusdvn);
						
						
		 	        	Divisa divisa = new Divisa();
		 	        	divisa.setConnection(con);

		 	        	String cotizchf = divisa.getFixingUSD("CHF");
		 	        	cotizchf = cotizchf.replace(",", ".");
		 	        	fixinusd = Double.parseDouble(cotizchf);
		 	        	double dpricchf = Double.parseDouble(pricechf);
		 	        	
		 	        	double dpricusd = dpricchf * fixinusd;
		 	        	
		 	        	priceusd = formatDivi.format(dpricusd);
		 	        	priceusd = priceusd.replaceAll(",",".");
		 	        	
		 	        } catch (Exception e) {
		 	        	System.out.println("ERROR realizando  operaciones de Fixing ");
		 	        }
					
			    	UpdStockBDIn insStockBDIn = new UpdStockBDIn();
			    	insStockBDIn.setValue("idemisor",idemisor);
			    	insStockBDIn.setValue("cdestado","PRESTOCK");
			    	insStockBDIn.setValue("imeicode",imeicode);
			    	insStockBDIn.setValue("txmarcax",txmarcax);
			    	insStockBDIn.setValue("txmodelo",txmodelo);
			    	insStockBDIn.setValue("idcolorx",idcolorx);
			    	insStockBDIn.setValue("pricechf",pricechf);
			    	insStockBDIn.setValue("priceusd",priceusd);
			    	insStockBDIn.setValue("pricecmp",pricecmp);
			    	insStockBDIn.setValue("diviscmp",diviscmp);
			    	insStockBDIn.setValue("prusdcmp",prusdcmp);
			    	insStockBDIn.setValue("porcmarg",porcmarg);
			    	insStockBDIn.setValue("pricevnt",pricevnt);
			    	insStockBDIn.setValue("divisvnt",divisvnt);
			    	insStockBDIn.setValue("prusdvnt",prusdvnt);
			    	insStockBDIn.setValue("txprovid",txprovid);
			    	insStockBDIn.setValue("txbuyerx",txbuyerx);
			    	insStockBDIn.setValue("txfundin",txfundin);
			    	insStockBDIn.setValue("withboxx",withboxx);
			    	insStockBDIn.setValue("withusbx",withusbx);
			    	insStockBDIn.setValue("idcatego",idcatego);
	
		    		UpdStockBD insStockBD = new UpdStockBD(insStockBDIn);
		    		insStockBD.setConnection(con);
		    		
					int liInsert = insStockBD.execInsert();
					
					if (liInsert == 1){
						txmensaj = "Se ha insertado la línea correctamente.";
				    } else {
				    	txmensaj = "Se ha producido un error al insertar la línea ("+ liInsert +")";
				    }
				}	
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			ListStockBDIn stockBDIn = new ListStockBDIn();
			stockBDIn.setValue("idemisor",idemisor);
			stockBDIn.setValue("cdestado","PRESTOCK");
		  	ListStockBD lineasBD = new ListStockBD(stockBDIn);
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
	