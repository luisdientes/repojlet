package upgrade;


import java.util.Calendar;
import java.util.GregorianCalendar;

import stock.bd.UpdInventBD;
import stock.bd.UpdInventBDIn;
import upgrade.bd.ListUpgradeBD;
import upgrade.bd.ListUpgradeBDIn;
import upgrade.bd.UpdStockBD;
import upgrade.bd.UpdStockBDIn;
import upgrade.bd.UpdUpgradeBD;
import upgrade.bd.UpdUpgradeBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;


public class SelecImeiSrv extends Service {

    public SelecImeiSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();	
			input.addVariable("idemisor");
			input.addVariable("tpclient");
			input.addVariable("inspieza");
			input.addVariable("imeicode");
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
			output.addVariable("gridLine");
			
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor = null;
    	String tpclient = null;
    	String imeicode = null;
    	String inspieza = null;
    	
        //Varibales de salida
 
    	Grid gridLine = null;
    	Grid gridUpgr = null;
    	String txmensaj = null;
    	
    	
    	/*VARIABLES para la hora de comienzo*/
    	
    	int horarepa = 0;
    	int minutosc = 0;
    	
        try {
        	
        	idemisor = input.getStringValue("idemisor");
        	imeicode = input.getStringValue("imeicode");
        	inspieza = input.getStringValue("inspieza");
        	tpclient = input.getStringValue("tpclient");
        	String claseant = "";
        	String codprodu = "";
        	String horacomi  ="";
        	
        	if(imeicode !=null && !imeicode.equals("")){
        	
        		UpdStockBDIn selStockBDIn = new UpdStockBDIn();
        		selStockBDIn.setValue("idemisor",idemisor);
        		selStockBDIn.setValue("imeicode",imeicode);
        		selStockBDIn.setValue("cdestado","STOCK");
				UpdStockBD lineasBD = new UpdStockBD(selStockBDIn);
				lineasBD.setConnection(con);
				gridLine = lineasBD.execSelect();
				
				if(gridLine.rowCount() == 0){
					txmensaj ="El imei no existe en stock";
				}else{
					
					if(inspieza == null){
						claseant = gridLine.getStringCell(0, "idcatego");
						codprodu = gridLine.getStringCell(0, "codprodu");
						
						ListUpgradeBDIn lineasBDIn= new ListUpgradeBDIn();
			        	lineasBDIn.setValue("idemisor", idemisor);
			        	lineasBDIn.setValue("imeicode", imeicode);
			
			        	ListUpgradeBD lineasUpgradeBD = new ListUpgradeBD(lineasBDIn);
			        	lineasUpgradeBD.setConnection(con);
						gridUpgr = lineasUpgradeBD.execSelect();
						
						
						if (gridUpgr.rowCount() == 0){
							UpdUpgradeBDIn insUpgradeBDIn = new UpdUpgradeBDIn();
							insUpgradeBDIn.setValue("idemisor", idemisor);
							insUpgradeBDIn.setValue("imeicode", imeicode);
							insUpgradeBDIn.setValue("claseant", claseant);
							insUpgradeBDIn.setValue("codprodu", codprodu);
						
							UpdUpgradeBD insUpgradeBD = new UpdUpgradeBD(insUpgradeBDIn);
							insUpgradeBD.setConnection(con);
							insUpgradeBD.execInsert();
							
						}
						
						
						
						
						Calendar calendario = new GregorianCalendar();
						
						horarepa = calendario.get(Calendar.HOUR_OF_DAY);
						minutosc = calendario.get(Calendar.MINUTE);
						horacomi = horarepa +":"+minutosc;
					}
					
				}
			
        	}
        	
        	output.setValue("idemisor", idemisor);
        	output.setValue("horacomi", horacomi);
        	output.setValue("imeicode", imeicode);
        	output.setValue("tpclient", tpclient);
        	output.setValue("claseant", claseant);
		    output.setValue("gridLine", gridLine);
		    output.setValue("txmensaj", "OK");

        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
}
	