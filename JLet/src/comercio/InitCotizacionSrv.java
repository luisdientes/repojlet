package comercio;


import java.text.DecimalFormat;
import java.util.HashSet;

import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import comercio.bd.ListFixingBD;
import comercio.bd.ListFixingBDIn;
import comercio.bd.ListLineasEnvioTasasBD;
import comercio.bd.ListLineasEnvioTasasBDIn;


public class InitCotizacionSrv extends Service {

	String imptotal = "";
	String porcmarg = "";
	
	String apcustotax = "N";
	String apconsutax = "N";
	String apfletecst = "N";
	String apitbisimp = "N";
	String aptramadua = "N";
	String apalmacena = "N";
	String apmovconte = "N";
	String apcargnavi = "N";

	
	HashSet hstTaxes = new HashSet();
	
    public InitCotizacionSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("codeenvi");

		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
			output.addVariable("codeenvi");
			output.addVariable("imptotal");
			output.addVariable("porcmarg");
			
			output.addVariable("apcustotax");
			output.addVariable("apconsutax");
			output.addVariable("apfletecst");
			output.addVariable("apitbisimp");
			output.addVariable("aptramadua");
			output.addVariable("apalmacena");
			output.addVariable("apmovconte");
			output.addVariable("apcargnavi");

			output.addVariable("gdDivisa");
			output.addVariable("txmensaj");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String codeenvi = "";
        
        //Varibales de salida
    	Grid gdDivisa = null;

    	//Otras Variables
    	Grid gridLine = null;
    	
        try {
        	
        	codeenvi = input.getStringValue("codeenvi");
        	
        	ListFixingBDIn fixingBDIn = new ListFixingBDIn();
        	fixingBDIn.setValue("cddivisa","USD");
        	ListFixingBD fixingBD = new ListFixingBD(fixingBDIn);
        	fixingBD.setConnection(con);
        	gdDivisa = fixingBD.execSelect();
        	
        	try {
        		ListLineasEnvioTasasBDIn lineasBDIn= new ListLineasEnvioTasasBDIn();
			  	lineasBDIn.setValue("codeenvi",codeenvi);
			  	ListLineasEnvioTasasBD lineasBD = new ListLineasEnvioTasasBD(lineasBDIn);
				lineasBD.setConnection(con);
				gridLine = lineasBD.execSelect();							
				
				calculaTotales(gridLine);
				
	        } catch (Exception e) {
				System.err.println(this.getClass().getName() +" ERROR recogiendo lineas. "+ e.getMessage());
			}
        	
        	output.setValue("codeenvi", codeenvi);
        	output.setValue("imptotal", imptotal);
        	output.setValue("porcmarg", porcmarg);
		    
		    output.setValue("apcustotax",apcustotax);
		    output.setValue("apconsutax",apconsutax);
		    output.setValue("apfletecst",apfletecst);
		    output.setValue("apitbisimp",apitbisimp);
		    output.setValue("aptramadua",aptramadua);
		    output.setValue("apalmacena",apalmacena);
		    output.setValue("apmovconte",apmovconte);
		    output.setValue("apcargnavi",apcargnavi);
		    
		    output.setValue("gdDivisa", gdDivisa);
		    output.setValue("txmensaj", "OK");
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
  
    public void calculaTotales(Grid gdLineas){
    	
    	double dimptota = 0;
    	double dmartota = 0;
    	DecimalFormat formatDivi = new DecimalFormat("###,##0.00");
    	
    	for (int i = 0; i < gdLineas.rowCount(); i++){
    		

    		double dquantit = 0;
    		double dtmpimpo = 0;
    		double dtmpporm = 0;
    		
    		double dmargpar = 0;
    		
    		try {
    			dquantit = Double.parseDouble(gdLineas.getStringCell(i, "quantity"));
    			dtmpporm = Double.parseDouble(gdLineas.getStringCell(i, "porcmarg"));
    			dtmpimpo = Double.parseDouble(gdLineas.getStringCell(i, "priceusd"));
    			dimptota += dquantit * dtmpimpo;
    			dmartota += dquantit * (dtmpimpo * dtmpporm / 100);

    			if (gdLineas.getStringCell(i, "custotax").equals("S")){
    				 apcustotax = "S";
    			}
    			
    			if (gdLineas.getStringCell(i, "consutax").equals("S")){
    				apconsutax = "S";
    			}
    			
    			if (gdLineas.getStringCell(i, "fletecst").equals("S")){
    				apfletecst = "S";
    			}
    			
    			if (gdLineas.getStringCell(i, "itbisimp").equals("S")){
    				apitbisimp = "S";
    			}
    			
    			if (gdLineas.getStringCell(i, "tramadua").equals("S")){
    				aptramadua = "S";
    			}
    			
    			if (gdLineas.getStringCell(i, "almacena").equals("S")){
    				apalmacena = "S";
    			}
    			
    			if (gdLineas.getStringCell(i, "movconte").equals("S")){
    				apmovconte = "S";
    			}
    			
    			if (gdLineas.getStringCell(i, "cargnavi").equals("S")){
    				apcargnavi = "S";
    			}
    			
    			
    		} catch (Exception e) {
    			System.err.println(" ERROR recuperando el priceusd de la línea. "+ i);
    		}
    		
    	}
    	
    	double dporctot = dmartota / dimptota * 100;
    	
    	dimptota += dmartota;
    	
    	imptotal = formatDivi.format(dimptota);
    	porcmarg = formatDivi.format(dporctot);
    	
    }
    
}
	