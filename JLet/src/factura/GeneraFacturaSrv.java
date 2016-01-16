package factura;


import java.util.ArrayList;

import utils.PropiedadesJLet;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import factura.bd.ListLineasBD;
import factura.bd.ListLineasBDIn;
import facturacion.FRAautonJejlESSrv;
import facturacion.FRAcrinbuESSrv;
import facturacion.FRAgenericESSrv;
import facturacion.FRAizumbaRNCSrv;
import facturacion.FRAizumbaWebSrv;
import facturacion.FRAleppardRNCSrv;
import facturacion.FRArosefitness4uSrv;
import facturacion.FRAsatchmoMoghkinESSrv;
import facturacion.FRAtradensESSrv;
import facturacion.FRAtradensNatelESSrv;
import facturacion.FRAtradensNatelWebESSrv;
import facturacion.FRAvetustaCSSrv;


public class GeneraFacturaSrv extends Service {

	
	ArrayList<String> emiGenES = new ArrayList<String>();
	
    public GeneraFacturaSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();

			input.addVariable("idemisor");
			input.addVariable("idclient");
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
			input.addVariable("codvende");
			input.addVariable("mcpagado");
			input.addVariable("listimei");
			input.addVariable("tipoclie");
			input.addVariable("albawebx");	
			input.addVariable("porcrete");
			input.addVariable("idfactur");
			input.addVariable("devoluci");
			
			
			
						
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
			output.addVariable("idemisor");	
			output.addVariable("txmensaj");			
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String emisclie = null;
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
        String codvende = null;
        String mcpagado = null;
        String cduserid = null;
        String listimei = null;
        String tipoclie = null;
        String albawebx = null;
        String porcrete = null;
        String idfactur = null;
        String devoluci = null;
        //Varibales de salida
        String filecrea = null;
        String txmensaj = null;
        
        //Otras Variables
     
        
        try{         	
        	System.out.println("ANTES DE user id");
        	
        	try{
        		cduserid = (String) sesion.getAttribute("idusuari");
        	}catch(Exception ex){
        		System.out.println("Error al recuperar sesion de usuario");
        		cduserid = "-1";
        	}
        	emisclie = input.getStringValue("idemisor");
        	receclie = input.getStringValue("idclient");
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
        	codvende = input.getStringValue("codvende");
        	mcpagado = input.getStringValue("mcpagado");
        	listimei = input.getStringValue("listimei");
        	tipoclie = input.getStringValue("tipoclie");
        	albawebx = input.getStringValue("albawebx");
        	porcrete = input.getStringValue("porcrete");
        	devoluci = input.getStringValue("devoluci");
        	idfactur = input.getStringValue("idfactur");
        	
        	System.out.println("FECHA EN españa"+fhfactur);
   
        	
        	if (listPendiente(emisclie,tpclient,receclie,facalbar) == 0){
        		txmensaj ="ERROR";
        	}else{
		        	fhfactur = fechaMySQL(fhfactur);
		       
		        	cargaFacturasGenericas();
		        	
		        	Service srv = null;
		        	
		        	ObjectIO srvIn = null;
		        	ObjectIO srvOut = null;
		        	
		        	if (emisclie.equals("1")){
		        		if (tipofact.equals("6")){
		        			srv = new FRAizumbaWebSrv();
		        			
		        			System.out.println("Entra en izumabWeb");
		        		} else {
		        			srv = new FRAizumbaRNCSrv();
		        		}
		        	} else if (emisclie.equals("2")){
		        		srv = new FRAcrinbuESSrv();
		        	} else if (emisclie.equals("3")){
		        		srv = new FRAtradensESSrv();
		        	} else if (emisclie.equals("4")){
		        		srv = new FRArosefitness4uSrv();
		        	} else if (emisclie.equals("5")){
		        		srv = new FRAleppardRNCSrv();
		        	} else if (emisclie.equals("6")){
		        		srv = new FRAvetustaCSSrv();
		        	} else if (emisclie.equals("7")){
		        		srv = new FRAsatchmoMoghkinESSrv();
		        	} else if (emisclie.equals("8")){
		        		
		        		if (tipofact.equals("6")){
		        			srv = new FRAtradensNatelWebESSrv();
		        			
		        			System.out.println("Entra en izumabWeb");
		        		} else {
		        			
		        			
		        			srv = new FRAtradensNatelESSrv();
		        		}
		        		
		        		
		        	} else if (emisclie.equals("9")){
		        		srv = new FRAautonJejlESSrv();
		        	} else if (emisclie.equals("10")){
		        		srv = new FRAautonJejlESSrv();
		        	} else if (emiGenES.contains(emisclie)) {
		        		srv = new FRAgenericESSrv();
		        	}
		        	
					srvIn  = srv.instanceOfInput();
					srvOut = srv.instanceOfOutput();
					srvIn.setValue("emisclie", emisclie);
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
					srvIn.setValue("listimei", listimei);
					srvIn.setValue("porcrete", porcrete);
					srvIn.setValue("informda", informda);
					srvIn.setValue("mcpagado", mcpagado);
					srvIn.setValue("devoluci", devoluci);
					srvIn.setValue("idfactur", idfactur);
					srvIn.setValue("marcados", marcados);
					srvIn.setValue("facalbar", facalbar);
					
					
					if (emisclie.equals("1")){
						
						srvIn.setValue("idlineas", idtmpfra);
						srvIn.setValue("factasoc", factasoc);
						srvIn.setValue("informda", informda);
						srvIn.setValue("codvende", codvende);
						srvIn.setValue("cduserid", cduserid);
						srvIn.setValue("tipoclie", tipoclie);
						
						
						
		        		if (tipofact.equals("6")){
		        			srvIn.setValue("idorderx", idorderx);
		        		}
					}
					
					if (emisclie.equals("6") || emisclie.equals("8")){
						
						srvIn.setValue("factasoc", factasoc);
						srvIn.setValue("albawebx", albawebx);
						srvIn.setValue("marcados", marcados);
						srvIn.setValue("facalbar", facalbar);
						srvIn.setValue("idorderx", idorderx);
					}
			
					srv.setConnection(con);
					srv.process(srvIn, srvOut);
		
					filecrea = srvOut.getStringValue("filecrea");
					txmensaj = srvOut.getStringValue("txmensaj");
        	}
			
			output.setValue("idemisor", emisclie);
        	output.setValue("filecrea", filecrea);
        	output.setValue("txmensaj", txmensaj);
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
    public void cargaFacturasGenericas(){    	
    	
    	String stGeneES = "";
    	
    	stGeneES = PropiedadesJLet.getInstance().getProperty("invoice.generic.ES");
    	//stGeneES = "11,12,13,14,15,16,17,18,19";
    	
    	String[] lsGeneES = stGeneES.split(",");
    	
    	for (int i = 0; i < lsGeneES.length; i++){    	
    		emiGenES.add(lsGeneES[i]);
    	}
    	
    	
    }
    
    
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
    
    
    public int listPendiente(String emisclie,String tpclient,  String receclie, String facalbar){
    
    	    	
    	    	Grid gdLineasBD = null;
    	    	int lineas = 0;
    	    	
    	    	try {
    	    		ListLineasBDIn lineasBDIn= new ListLineasBDIn();
    				lineasBDIn.setValue("idclient",receclie);
    				lineasBDIn.setValue("tpclient",tpclient);
    				lineasBDIn.setValue("idemisor",emisclie);
    				lineasBDIn.setValue("cdestado","V");
    				ListLineasBD lineasBD = new ListLineasBD(lineasBDIn);
    				lineasBD.setConnection(con);
    			    gdLineasBD = lineasBD.execSelect();
    			    lineas = gdLineasBD.rowCount();
    			    
    			    if (facalbar!=null && (facalbar.equals("D") || facalbar.equals("S"))){
    			    	lineas = 1;
    			    }
    			    
    			    
    				
    			} catch (Exception e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    			return lineas;
    }
    
    

}