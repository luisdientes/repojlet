package stock;


import java.text.DecimalFormat;

import common.bd.ListClientesBD;
import common.bd.ListClientesBDIn;

import stock.bd.DetalleProdBD;
import stock.bd.DetalleProdBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;


public class DetalleProducSrv extends Service {

    public DetalleProducSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();	
			input.addVariable("idemisor");
			input.addVariable("idunicox");
			

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
			output.addVariable("benefici");
			output.addVariable("porcbene");
			output.addVariable("rzsocial");
			
			output.addVariable("gridProd");
			
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor = null;
    	String imeicode = null;
    	String prusdvnt = null;
    	String prusdcmp = null;
    	String strbenef = null;
    	String strporbe = null;
    	
    	String idclient = null;
    	String tpclient = null;
    	String rzsocial = null;
    	
    	double benefici = 0;
    	double porcbene = 0;

    	DecimalFormat formatPorc = new DecimalFormat("##0.00%");
    	DecimalFormat formatDivi = new DecimalFormat("###,##0.00");
    	
        //Varibales de salida
    	Grid gridProd = null;
    	Grid gdClient = null;
    	
    	
        try {
        	
        	idemisor = input.getStringValue("idemisor");
        	imeicode = input.getStringValue("idunicox");
        	
        	
	        try {
	        	 
	        	DetalleProdBDIn detalleBDIn= new DetalleProdBDIn();
	        	detalleBDIn.setValue("idemisor",idemisor);		        	
	        	detalleBDIn.setValue("imeicode",imeicode);
	        	DetalleProdBD detalleBD = new DetalleProdBD(detalleBDIn);
	        	detalleBD.setConnection(con);
	        	gridProd = detalleBD.execSelect();
	        	
	        	prusdcmp = gridProd.getStringCell(0,"prusdcmp");
	        	prusdvnt = gridProd.getStringCell(0,"prusdvnt");
	        	
	        	
	        	idclient = gridProd.getStringCell(0,"idclient");
				tpclient = gridProd.getStringCell(0,"tpclient");
				
				try{
					ListClientesBDIn listCliBDIn = new ListClientesBDIn();
		       		listCliBDIn.setValue("idemisor",idemisor);
		       		listCliBDIn.setValue("tpclient",tpclient);
		       		listCliBDIn.setValue("idclient",idclient);
		       		ListClientesBD listCliBD = new ListClientesBD(listCliBDIn);
		       		listCliBD.setConnection(con);
		        			
		       		gdClient = listCliBD.execSelect();
		       		
		       		rzsocial = gdClient.getStringCell(0,"rzsocial");
				}catch(Exception ex){
					System.out.println("Error al recuperar cliente ¿existe?");
				}

	        	try{
						benefici = Double.parseDouble(prusdvnt) - Double.parseDouble(prusdcmp);
						porcbene = benefici / Double.parseDouble(prusdcmp);
						
						if(Double.isNaN(porcbene)){
							porcbene=0.0;
						}
						
						strporbe = formatPorc.format(porcbene);
						strbenef = formatDivi.format(benefici);
					}catch (Exception ex){
						System.out.println("Error al calcular el beneficio");
						strporbe = "-";
						strbenef = "-";
					}
	        	
	        	
	        	
	        } catch (Exception e) {
				System.err.println(this.getClass().getName() +" ERROR recogiendo productos. "+ e.getMessage());
			}
    
        	output.setValue("benefici", strbenef);
        	output.setValue("porcbene", strporbe);
        	output.setValue("rzsocial", rzsocial);
        	output.setValue("idemisor", idemisor);
		    output.setValue("gridProd", gridProd);
		    output.setValue("txmensaj", "OK");

        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
}
	