package factura;


import java.util.ArrayList;

import utils.PropiedadesJLet;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import factura.bd.DetalleFacturasBD;
import factura.bd.DetalleFacturasBDIn;
import factura.bd.UpdFacturaBD;
import factura.bd.UpdFacturaBDIn;
import factura.bd.UpdLineasBD;
import factura.bd.UpdLineasBDIn;
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
import facturacion.FRAvetustaCSSrv;


public class UpdateFacturaSrv extends Service {

	ArrayList<String> emiGenES = new ArrayList<String>();
	
    public UpdateFacturaSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			
			input.addVariable("idemisor");
			input.addVariable("idfactur");
			input.addVariable("idclient");
			input.addVariable("tpclient");
			input.addVariable("formpago");
			input.addVariable("condpago");
			input.addVariable("idtmpfra");
			/*input.addVariable("receclie");
			input.addVariable("tpclient");
			input.addVariable("aniofact");
			input.addVariable("tipofact");
			input.addVariable("mcagrupa");
			input.addVariable("fhfactur");
			input.addVariable("formpago");
			input.addVariable("condpago");
			input.addVariable("catefact");
			input.addVariable("tipologo");
			input.addVariable("idorderx");*/
						
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
			
			
			output.addVariable("idfactur");
			output.addVariable("idemisor");
			output.addVariable("filecrea");	
			output.addVariable("txmensaj");			
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idfactur = null;
    	String idemisor = null;
    	String idclient = null;
    	String tpclient = null;
    	String formpago = null;
    	String condpago = null;
    	String mcagrupa = null;

    	
    	String aniofact = null;
    	String tipofact = null;
    	String fhfactur = null;
  
    	String catefact = null;
    	String tipologo = null;
    	String idorderx = null;
    	String idtmpfra = null;
    	String cdfactur = null;
    	String informda = null;
    	String factasoc = null;
    	String codvende = null;
    	
        //Varibales de salida
        String filecrea = null;
        String txmensaj = null;
        String cdcronol = null;
        String mcpagado = null;
        String totalpen = null;
        String totalpag = null;
        String agrupada = null;
        String porcrete = null;
        double porReten = 0.0;
        
        
        //Otras Variables
     
        
        try{   
       
        	idfactur = input.getStringValue("idfactur");
        	idemisor = input.getStringValue("idemisor");
        	idclient = input.getStringValue("idclient");
        	tpclient = input.getStringValue("tpclient");
        	formpago = input.getStringValue("formpago");
        	condpago = input.getStringValue("condpago");
        	idtmpfra = input.getStringValue("idtmpfra");
        	//for (int i = 1; i <= 600; i++) {
        		
        		try {
        			
        			// asigno las lineas al cliente
        			
        			UpdLineasBDIn updateLineaBDin = new UpdLineasBDIn();
        			updateLineaBDin.setValue("idemisor", idemisor);
        			updateLineaBDin.setValue("idfactur", idtmpfra);
        			updateLineaBDin.setValue("idclient", idclient);
        			updateLineaBDin.setValue("tpclient", tpclient);
        			updateLineaBDin.setValue("actualiz", "S");
        			UpdLineasBD updateLineaBD = new UpdLineasBD(updateLineaBDin);
        			updateLineaBD.setConnection(con);
        			updateLineaBD.execUpdate();
        			
        			UpdFacturaBDIn updateFacturaBDIn = new UpdFacturaBDIn();
        			updateFacturaBDIn.setValue("idclient", idclient);
        			updateFacturaBDIn.setValue("formpago", formpago);
        			updateFacturaBDIn.setValue("condpago", condpago);
        			updateFacturaBDIn.setValue("idfactur", idfactur);
        			
        			UpdFacturaBD updateFacturaBD = new UpdFacturaBD(updateFacturaBDIn);
        			updateFacturaBD.setConnection(con);
        			updateFacturaBD.execUpdate();
        			
        			//idfactur = String.valueOf(i);
        			
        			mcagrupa = "0";
        			
		        	DetalleFacturasBDIn detalleFaBDIn = new DetalleFacturasBDIn(); 
		        	detalleFaBDIn.setValue("idfactur", idfactur);
		        	DetalleFacturasBD detalleFaBD = new DetalleFacturasBD(detalleFaBDIn); 
		        	detalleFaBD.setConnection(con);
		        	Grid detFactu = detalleFaBD.execSelect();
		        	
		        	
		        	if (mcpagado == null || mcpagado.equals("")){
		        		mcpagado = detFactu.getStringCell(0, "mcpagado");
		        	}
		        	
		        	if (totalpag == null || totalpag.equals("")){
		        		totalpag = detFactu.getStringCell(0, "totalpag");
		        	}
		        
		        	
		        	idemisor = detFactu.getStringCell(0, "idemisor");
		        	idclient = detFactu.getStringCell(0, "idclient");
		        	tpclient = detFactu.getStringCell(0, "tpclient");
		        	idtmpfra = detFactu.getStringCell(0, "idtmpfra");
		        	cdfactur = detFactu.getStringCell(0, "cdfactur");
		        	aniofact = detFactu.getStringCell(0, "aniofact");
		        	tipofact = detFactu.getStringCell(0, "tipofact");
		        	fhfactur = detFactu.getStringCell(0, "fhfactur");
		        	formpago = detFactu.getStringCell(0, "formpago");
		        	condpago = detFactu.getStringCell(0, "condpago");
		        	catefact = detFactu.getStringCell(0, "catefact");
		        	informda = detFactu.getStringCell(0, "informad"); 
		        	factasoc = detFactu.getStringCell(0, "factasoc");  
		        	tipologo = detFactu.getStringCell(0, "tpclient"); 
		        	codvende = detFactu.getStringCell(0, "codvende");
		        	cdcronol = detFactu.getStringCell(0, "cdcronol");
		        	agrupada = detFactu.getStringCell(0, "mcagrupa");
		        	porcrete = detFactu.getStringCell(0, "porcrete");
		        	
		        	if(codvende.equals("0")){
						codvende = "";
					}
		        	
		        	if(agrupada.equals("S")){
						mcagrupa = "1";
					}else{
						mcagrupa = "0";
					}
		        	
		        	if(porcrete != null && !porcrete.equals("")){
		        		porReten = Double.parseDouble(porcrete) /100;
		        	}else{
		        		porReten = 0;
		        	}
		        	
		        	
		        	System.out.println(this.getClass() +"tpclient:  "+ tpclient);
		        	cargaFacturasGenericas();
		        	
		        	Service srv = null;
		        	
		        	ObjectIO srvIn = null;
		        	ObjectIO srvOut = null;
		        	
		        	if (idemisor.equals("1")){
		        		if (tipofact.equals("6")){
		        			srv = new FRAizumbaWebSrv();
		        		} else {
		        			srv = new FRAizumbaRNCSrv();
		        		}
		        	} else if (idemisor.equals("2")){
		        		srv = new FRAcrinbuESSrv();
		        	} else if (idemisor.equals("3")){
		        		srv = new FRAtradensESSrv();
		        	} else if (idemisor.equals("4")){
		        		srv = new FRArosefitness4uSrv();
		        	} else if (idemisor.equals("5")){
		        		srv = new FRAleppardRNCSrv();
		        	} else if (idemisor.equals("6")){
		        		srv = new FRAvetustaCSSrv();
		        	} else if (idemisor.equals("7")){
		        		srv = new FRAsatchmoMoghkinESSrv();
		        	} else if (idemisor.equals("8")){
		        		srv = new FRAtradensNatelESSrv();
		        	} else if (idemisor.equals("10")){
		        		srv = new FRAautonJejlESSrv();
		        	} else if (emiGenES.contains(idemisor)) {
		        		srv = new FRAgenericESSrv();
		        	}
		        	
					srvIn  = srv.instanceOfInput();
					srvOut = srv.instanceOfOutput();
					srvIn.setValue("regenera", "S");
					srvIn.setValue("emisclie", idemisor);
					srvIn.setValue("receclie", idclient);
					srvIn.setValue("tpclient", tpclient);
					srvIn.setValue("aniofact", aniofact);
					srvIn.setValue("tipofact", tipofact);
					srvIn.setValue("mcagrupa", mcagrupa);
					srvIn.setValue("fhfactur", fhfactur);
					srvIn.setValue("formpago", formpago);
					srvIn.setValue("condpago", condpago);
					srvIn.setValue("catefact", catefact);
					srvIn.setValue("tipologo", tipologo);
					srvIn.setValue("idlineas", idtmpfra);
					srvIn.setValue("cdfactur", cdfactur);
					srvIn.setValue("idfactur", idfactur);
					srvIn.setValue("informda", informda);
					srvIn.setValue("codvende", codvende);
					srvIn.setValue("mcpagado", mcpagado);
					srvIn.setValue("totalpen", totalpen);
					srvIn.setValue("totalpag", totalpag);
					srvIn.setValue("porcrete", porReten);
					
					

					try{
						srvIn.setValue("factasoc", factasoc);
					} catch (Exception e) {
						
					}
					
					if (idemisor.equals("1")){
		        		if (tipofact.equals("6")){
		        			srvIn.setValue("idorderx", idorderx);
		        		}
		        		srvIn.setValue("cdcronol", cdcronol);
					}
					
					
					
				
			
					srv.setConnection(con);
					
					srv.process(srvIn, srvOut);
					
					filecrea = srvOut.getStringValue("filecrea");
					txmensaj = srvOut.getStringValue("txmensaj");
        		
					System.out.println("");
					System.out.println("------------------------------------");
					System.out.println("idFactura: "+ idfactur +" REGENERADA  OK");
					System.out.println("------------------------------------");
					System.out.println("");
					
        		} catch (Exception e) {
        			System.out.println("");
					System.out.println("------------------------------------");
					System.out.println("idFactura: "+ idfactur +" REGENERADA  KO");
					System.out.println("------------------------------------");
					System.out.println(e.getMessage());
				}
				
					
        	//}
			
        	
        	output.setValue("idemisor", idemisor);
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
       
}