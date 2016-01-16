package factura;


import java.text.DecimalFormat;

import arquitectura.controller.Service;
import arquitectura.objects.ObjectIO;
import facturacion.FRAizumbaDesgAlbaranSrv;


public class GeneraDesgAlbaranSrv extends Service {

    public GeneraDesgAlbaranSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();

			input.addVariable("emisclie");
			input.addVariable("receclie");
			input.addVariable("tpclient");
			input.addVariable("aniofact");
			input.addVariable("tipofact");
			input.addVariable("mcagrupa");
			input.addVariable("fhfactur");
			input.addVariable("formpago");
			input.addVariable("condpago");
			input.addVariable("catefact");
			input.addVariable("tipologo");
			input.addVariable("idorderx");
			input.addVariable("facalbar");
			input.addVariable("idtmpfra");
			input.addVariable("marcados");
			input.addVariable("informda");
			input.addVariable("factasoc");
			input.addVariable("cdfactur");
						
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
			output.addVariable("filecrea");	
			output.addVariable("txmensaj");		
			output.addVariable("idemisor");	
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor = null;
    	String receclie = null;
    	String tpclient = null;
    	String aniofact = null;
    	String tipofact = null;
    	String mcagrupa = null;
    	String fhfactur = null;
    	String formpago = null;
    	String condpago = null;
    	String catefact = null;
    	String tipologo = null;
    	String idorderx = null;
    	String facalbar = null;
        String idtmpfra = null;
        String marcados = null;
        String informda = null;
        String factasoc = null;
        String cdfactur = null;
        
        
        //Varibales de salida
        String filecrea = "";
        String txmensaj = null;
        
        //Otras Variables
        DecimalFormat dfconduce = new DecimalFormat("00000000");
        
        try{         	
        	
        	idemisor = input.getStringValue("emisclie");
        	receclie = input.getStringValue("receclie");
        	tpclient = input.getStringValue("tpclient");
        	aniofact = input.getStringValue("aniofact");
        	tipofact = input.getStringValue("tipofact");
        	mcagrupa = input.getStringValue("mcagrupa");
        	fhfactur = input.getStringValue("fhfactur");
        	formpago = input.getStringValue("formpago");
        	condpago = input.getStringValue("condpago");
        	catefact = input.getStringValue("catefact"); 
        	tipologo = input.getStringValue("tipologo");
        	idorderx = input.getStringValue("idorderx");
        	facalbar = input.getStringValue("facalbar"); 
        	idtmpfra = input.getStringValue("idtmpfra"); 
        	marcados = input.getStringValue("marcados");
        	informda = input.getStringValue("informda"); 
        	factasoc = input.getStringValue("factasoc");
        	cdfactur = input.getStringValue("cdfactur");
        	
        	try {
        		cdfactur = dfconduce.format(Double.parseDouble(cdfactur));
        	} catch (Exception e) {
        		cdfactur = "00000000";
        		System.out.println(" ERROR FORMATEANDO EL CDFACTUR");
        	}
        	
        	Service srv = null;
        	
        	ObjectIO srvIn = null;
        	ObjectIO srvOut = null;
        	
        	String[] listimei = marcados.split(",");
        	String imeicode = "";
        	
        	for (int i = 0; i < listimei.length; i++){
        		
        		imeicode = listimei[i];
        		
	        	if (idemisor.equals("1")){
	        		srv = new FRAizumbaDesgAlbaranSrv();        		
	        	} 
	        	
				srvIn  = srv.instanceOfInput();
				srvOut = srv.instanceOfOutput();
				srvIn.setValue("emisclie", idemisor);
				srvIn.setValue("receclie", receclie);
				srvIn.setValue("tpclient", tpclient);
				srvIn.setValue("aniofact", aniofact);
				srvIn.setValue("tipofact", tipofact);
				srvIn.setValue("mcagrupa", mcagrupa);
				srvIn.setValue("fhfactur", fhfactur);
				srvIn.setValue("formpago", formpago);
				srvIn.setValue("condpago", condpago);
				srvIn.setValue("catefact", catefact);
				srvIn.setValue("tipologo", tipologo);
				srvIn.setValue("imeicode", imeicode);
				srvIn.setValue("cdfactur", cdfactur);
				srvIn.setValue("idtmpfra", idtmpfra);
				
				if (idemisor.equals("1")){
					srvIn.setValue("facalbar", facalbar);
					srvIn.setValue("idlineas", idtmpfra);
					srvIn.setValue("marcados", marcados);
					srvIn.setValue("factasoc", factasoc);
					srvIn.setValue("informda", informda);
					
					
	        		if (tipofact.equals("6")){
	        			srvIn.setValue("idorderx", idorderx);
	        		}
				}
				
				if (idemisor.equals("6") || idemisor.equals("8")){
					srvIn.setValue("informda", informda);
				}
		
				srv.setConnection(con);
				srv.process(srvIn, srvOut);
	
				filecrea += srvOut.getStringValue("filecrea") +"#";
				txmensaj = srvOut.getStringValue("txmensaj");
        	}
				
        	output.setValue("idemisor", idemisor);
        	output.setValue("filecrea", filecrea);
        	output.setValue("txmensaj", txmensaj);
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
       
}