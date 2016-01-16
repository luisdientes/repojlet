package stock;




import stock.bd.ListProductsEmisorBD;
import stock.bd.ListProductsEmisorBDIn;
import stock.bd.ListTmpStockBD;
import stock.bd.ListTmpStockBDIn;
import stock.bd.MaxAltaTmpStockBD;
import stock.bd.MaxAltaTmpStockBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import common.bd.ListColoresBD;
import common.bd.ListColoresBDIn;


public class SelecProducSrv extends Service {

    public SelecProducSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();	
			input.addVariable("idemisor");
			input.addVariable("tpclient");
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
			output.addVariable("claseant");
			output.addVariable("tpclient");
			output.addVariable("idemisor");
			output.addVariable("horacomi");
			output.addVariable("imeicode");
			output.addVariable("idgralta");
			
			output.addVariable("gridLine");
			output.addVariable("gridColo");
			output.addVariable("gridProd");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor = null;
    	String tpclient = null;
    	String tpproduc = null;
    	String cdestado = null;
    	int idgralta = 0;
 
    	
        //Varibales de salida
 
    	Grid gridLine = null;
    	Grid gridProd = null;
    	Grid gridMaxi = null;
    	Grid gridColo = null;
    	String txmensaj = null;
    	
    	
    	/*VARIABLES para la hora de comienzo*/
 
    	
        try {
        	
        	idemisor = input.getStringValue("idemisor");
        	tpproduc = input.getStringValue("tpproduc");
    
        	
        	if(tpproduc !=null && !tpproduc.equals("")){
        	
        		ListProductsEmisorBDIn selStockBDIn = new ListProductsEmisorBDIn();
        		selStockBDIn.setValue("idemisor",idemisor);
        		selStockBDIn.setValue("tpproduc",tpproduc);
        		ListProductsEmisorBD lineasBD = new ListProductsEmisorBD(selStockBDIn);
				lineasBD.setConnection(con);
				gridLine = lineasBD.execSelect();
				
				ListColoresBDIn listacoloresBDIn = new ListColoresBDIn();
				ListColoresBD listacoloresBD = new ListColoresBD(listacoloresBDIn);
				listacoloresBD.setConnection(con);
				gridColo = listacoloresBD.execSelect();
				
				
				
				if(gridLine.rowCount() == 0){
					txmensaj ="El tipo de producto no existe";
				}else{
					ListTmpStockBDIn LineasStockTmpBDIn = new ListTmpStockBDIn();
			    	LineasStockTmpBDIn.setValue("idemisor", idemisor);
			    	LineasStockTmpBDIn.setValue("cdestado", "P");
			    	LineasStockTmpBDIn.setValue("codprodu", tpproduc);
			    	ListTmpStockBD ListStockTmpBD = new ListTmpStockBD(LineasStockTmpBDIn);  
			    	ListStockTmpBD.setConnection(con);
			    	gridProd = ListStockTmpBD.execSelect();
			    	
			    	if(gridProd.rowCount() == 0){
			    		
			    		cdestado = "V";
			    	}else{
			    		cdestado = "P";
			    	}
			    	MaxAltaTmpStockBDIn MaxStockTmpBDIn = new MaxAltaTmpStockBDIn();
			    	MaxStockTmpBDIn.setValue("idemisor", idemisor);
			    	MaxStockTmpBDIn.setValue("mcestado", cdestado);
			    	MaxStockTmpBDIn.setValue("codprodu", tpproduc);
			    	MaxAltaTmpStockBD  MaxStockTmpBD = new MaxAltaTmpStockBD(MaxStockTmpBDIn);
			    	MaxStockTmpBD.setConnection(con);
			    	gridMaxi = MaxStockTmpBD.execSelect();
			    	
			    	if(cdestado.equals("P")){
			    		try{
			    			idgralta = Integer.parseInt(gridMaxi.getStringCell(0, "idgralta"));
			    		}catch(Exception ex){
			    			idgralta = 1;
			    		}
			    		
			    	}else{
			    		try{
			    			idgralta = Integer.parseInt(gridMaxi.getStringCell(0, "idgralta"));
			    			idgralta++;
			    		}catch(Exception ex){
			    			idgralta = 1;
			    		}
			    	}
			    	
			    	
				}
			
        	}
        	
        	
        	output.setValue("idgralta", idgralta);
        	output.setValue("idemisor", idemisor);
        	output.setValue("tpclient", tpclient);
		    output.setValue("gridLine", gridLine);
		    output.setValue("gridProd", gridProd);
		    output.setValue("gridColo", gridColo);
		    output.setValue("txmensaj", "OK");

        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
}
	