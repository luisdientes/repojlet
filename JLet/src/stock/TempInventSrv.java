package stock;


import java.text.DecimalFormat;

import stock.bd.UpdInventBD;
import stock.bd.UpdInventBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import comercio.bd.UpdStockBD;
import comercio.bd.UpdStockBDIn;


public class TempInventSrv extends Service {

    public TempInventSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("idemisor");
			input.addVariable("idunicox");
			input.addVariable("tpclient");
			input.addVariable("idinvent");
			input.addVariable("nameinve");
			input.addVariable("tpproduc");
			


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
    
    
    public void process(ObjectIO input, ObjectIO output) throws Exception{
        
    	//Varibales de entrada
    	String idemisor = "";
    	String tpclient = "";
    	String idinvent = "";
    	String imeicode = "";
    	String nameinve = "";
    	String tpproduc = "";
    	Grid gridResu 	=  null;
        
        //Varibales de salida
        String txmensaj = "";   	
			
        //Otras Variables
        String priceusd = "";
        String cotizusd = "";
        double fixinusd = 1;
        
        DecimalFormat formatDivi = new DecimalFormat("#####0.00");
        
	    try{
	    	tpclient = input.getStringValue("tpclient");
	    	idemisor = input.getStringValue("idemisor");
			imeicode = input.getStringValue("idunicox");
			idinvent = input.getStringValue("idinvent");
			nameinve = input.getStringValue("nameinve");
			tpproduc = input.getStringValue("tpproduc");
			
			try{
				
				if(idinvent != null){
					UpdInventBDIn delInvenBDIn= new UpdInventBDIn();
					delInvenBDIn.setValue("idinvent",idinvent);
					UpdInventBD delInvenBD = new UpdInventBD(delInvenBDIn);
					delInvenBD.setConnection(con);
		    		
					int liInsert = delInvenBD.execDelete();
					
					if (liInsert == 1){
						txmensaj = "Se ha borrado la línea correctamente.";
				    } else {
				    	txmensaj = "Se ha producido un error al borrar la línea ("+ liInsert +")";
				    }
					
				}else{
					
					
			    	UpdInventBDIn insInvenBDIn = new UpdInventBDIn();
			    	insInvenBDIn.setValue("idemisor",idemisor);
			    	insInvenBDIn.setValue("cdestado","N");
			    	insInvenBDIn.setValue("idunicox",imeicode);
			    	insInvenBDIn.setValue("tpclient",tpclient);
			    	insInvenBDIn.setValue("nameinve",nameinve);
			    	insInvenBDIn.setValue("tpproduc",tpproduc);
			    	
		    		UpdInventBD insInventBD = new UpdInventBD(insInvenBDIn);
		    		insInventBD.setConnection(con);
		    		
					int liInsert = insInventBD.execInsert();
					
					if (liInsert == 1){
						txmensaj = "Se ha insertado la línea correctamente.";
				    } else {
				    	txmensaj = "Se ha producido un error al insertar la línea ("+ liInsert +")";
				    }
				}	
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			UpdInventBDIn inventBDIn = new UpdInventBDIn();
			inventBDIn.setValue("idemisor",idemisor);
			inventBDIn.setValue("cdestado","N");
			inventBDIn.setValue("nameinve",nameinve);
			UpdInventBD lineasBD = new UpdInventBD(inventBDIn);
			lineasBD.setConnection(con);
			gridResu = lineasBD.execSelect();
	    	
	    	// variables de salida
	    	output.setValue("txmensaj", txmensaj);
	    	output.setValue("idemisor", idemisor);
	    	output.setValue("nameinve", nameinve);
	    	output.setGrid("gridLine", gridResu);
	    	
	    	
		} catch (Exception e1) {
			e1.printStackTrace();
		}   	
    }
       
}
	