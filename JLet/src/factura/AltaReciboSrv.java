package factura;


import com.itextpdf.text.BaseColor;

import reparaciones.bd.UpdReparaBD;
import reparaciones.bd.UpdReparaBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import common.ExcelCreator;
import factura.bd.ListFormaPagoBD;
import factura.bd.ListFormaPagoBDIn;
import factura.bd.ListRecibosBD;
import factura.bd.ListRecibosBDIn;
import factura.bd.MaxReciboCreditoBD;
import factura.bd.MaxReciboCreditoBDIn;
import factura.bd.UpdPagaFacturaBD;
import factura.bd.UpdPagaFacturaBDIn;
import facturacion.FRAizumbaWebSrv;
import facturacion.GeneracionDocReciboSrv;
import facturacion.GeneracionReciboTradensSrv;



public class AltaReciboSrv extends Service {

	ExcelCreator creador = null;
	
	String idemisor = "";
	String cdrecibo = "";
    String filecrea = "";
    String txmensaj = "";
    String idfrmpag = "";
    String txforpag = "";
    String cuentapu = "";
    
	
	public AltaReciboSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("numrecib");
			input.addVariable("idclient");
			input.addVariable("cantidad");
			input.addVariable("formpago");
			input.addVariable("txbancox");
			input.addVariable("concepto");
			input.addVariable("idfactur");
			input.addVariable("idemisor");
			input.addVariable("tpclient");
			input.addVariable("numfactu");
			input.addVariable("marcapag");
			input.addVariable("fecharec");
			input.addVariable("divisaxx");
			input.addVariable("tpfacrec");
			input.addVariable("totalpen");
			input.addVariable("totalpag");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
			output.addVariable("idemisor");
			output.addVariable("cdrecibo");
			output.addVariable("filecrea");
			output.addVariable("divisaxx");
			output.addVariable("txmensaj");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada


        //Varibales de salida
    
    	String tpclient = "";
    	String numrecib = "";
    	String idclient = "";
    	String cantidad = "";
    	
    	String txbancox = "";
    	String concepto = "";
    	String idfactur = "";
    	String numfactu = "";
    	String marcapag = "";
    	String fecharec = "";
    	String divisaxx = "";
    	String tpfacrec = "";
        String totalpen = "";
        String totalpag = "";
        //Otras Variables
    	
        
        try {
        	idemisor = input.getStringValue("idemisor");
        	tpclient = input.getStringValue("tpclient");
        	numrecib = input.getStringValue("numrecib");
        	idclient = input.getStringValue("idclient");
        	cantidad = input.getStringValue("cantidad");
        	idfrmpag = input.getStringValue("formpago");
        	txbancox = input.getStringValue("txbancox");
        	concepto = input.getStringValue("concepto");
        	idfactur = input.getStringValue("idfactur");//
        	numfactu = input.getStringValue("numfactu");
        	marcapag = input.getStringValue("marcapag");
        	divisaxx = input.getStringValue("divisaxx");
        	tpfacrec = input.getStringValue("tpfacrec");
        	fecharec = fechaMySQL(input.getStringValue("fecharec"));
        	totalpen = input.getStringValue("totalpen");
        	totalpag = input.getStringValue("totalpag");
        	
        	//costordx = input.getStringValue("costordx").replaceAll("RD$","").trim();;
        	int insertado = 0;
        	
        	int idrecibo = 1;
        	Grid gdMaxRecibo= null;
     
        	try{
        		pintaDatosBancarios();
        		
        		MaxReciboCreditoBDIn MaxReciboBDin = new MaxReciboCreditoBDIn();
        		MaxReciboBDin.setValue("idemisor", idemisor);
        		MaxReciboBDin.setValue("tpfactur", tpfacrec);
        		MaxReciboCreditoBD MaxReciboBD = new MaxReciboCreditoBD(MaxReciboBDin);
        		MaxReciboBD.setConnection(con);
        		gdMaxRecibo = MaxReciboBD.execSelect();
        		
        		try{
        			if(gdMaxRecibo.rowCount()>0){
        				idrecibo = Integer.parseInt(gdMaxRecibo.getStringCell(0, "idrecibo"));
        				idrecibo+=1;
        			}
        			
        		}catch(Exception Ex){
        			idrecibo = 1;
        			System.out.println("Error recibiendo el ID recibo credito");
        		}
        		
        		
        		ListRecibosBDIn reciboBDIn = new ListRecibosBDIn();
        		
        		
        		
        		reciboBDIn.setValue("idrecibo",idrecibo);
        		reciboBDIn.setValue("tpfacrec",tpfacrec);
            	reciboBDIn.setValue("idemisor",idemisor);
            	reciboBDIn.setValue("tpclient",tpclient);
            	reciboBDIn.setValue("numrecib",numrecib);
            	reciboBDIn.setValue("idclient",idclient);
            	reciboBDIn.setValue("canrecib",cantidad);
            	reciboBDIn.setValue("formpago",txforpag);
            	reciboBDIn.setValue("idformpa",idfrmpag);
            	reciboBDIn.setValue("txbancox",txbancox);
            	reciboBDIn.setValue("concepto",concepto);
            	reciboBDIn.setValue("idfactur",idfactur);
            	reciboBDIn.setValue("cdfactur",numfactu);
            	reciboBDIn.setValue("fecharec",fecharec);
            	
            	ListRecibosBD reciboBD = new ListRecibosBD(reciboBDIn);
            	reciboBD.setConnection(con);
            	reciboBD.execInsert();
            	
            	/* marcamos como pagado si la cantidad pagada en el recibo es la cantidad adeudada*/
            	
            	if(marcapag != null && marcapag.equals("S")){
            		marcapag = "S";	
            	}else{
            		marcapag = "R";	
            	}
            	
            	UpdPagaFacturaBDIn updPagaFactuBDIn = new UpdPagaFacturaBDIn();
            	updPagaFactuBDIn.setValue("idfactur", idfactur);
            	updPagaFactuBDIn.setValue("mcpagado", marcapag);
            	updPagaFactuBDIn.setValue("totalpag", totalpag);
            	UpdPagaFacturaBD updPagaFactuBD =  new UpdPagaFacturaBD(updPagaFactuBDIn);
            	updPagaFactuBD.setConnection(con);
            	updPagaFactuBD.execUpdate();	
            	generaRecibo(idemisor, idfactur,numrecib);
        		
        		
            	try{
            		Service srv = null;
		        	
		        	ObjectIO srvIn = null;
		        	ObjectIO srvOut = null;
		        	srv = new RegeneraFacturaSrv();
		        	srvIn  = srv.instanceOfInput();
					srvOut = srv.instanceOfOutput();
					srvIn.setValue("idfactur", idfactur);
					srvIn.setValue("totalpen", totalpen);
					srvIn.setValue("totalpag", totalpag);
					
					srvIn.setValue("mcpagado", marcapag);
					
					srv.setConnection(con);
					srv.process(srvIn, srvOut);
            	}catch(Exception ex){
            		System.out.println("Error al regenerar factura desde Alta recibo");
            	}
        						
        	   }catch(Exception e1){
        		   e1.printStackTrace();
        	   }
        	
        	output.setValue("idemisor", idemisor);
        	output.setValue("divisaxx", divisaxx);
        	output.setValue("filecrea", filecrea);
        	output.setValue("txmensaj", "OK");
        
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
    public void generaRecibo(String idemisor, String idfactur,String idrecibo){
    	
    	try {
    		Service altaSrv = null;
        	
        	ObjectIO srvIn = null;
        	ObjectIO srvOut = null;
    		
    			if(idemisor.equals("3")){

    				 altaSrv = new  GeneracionReciboTradensSrv();
    			}else{
    				 altaSrv = new GeneracionDocReciboSrv();
    			}
    		
    		
    		ObjectIO input  = altaSrv.instanceOfInput();
    		input.setValue("idemisor", idemisor);
    		input.setValue("idfactur", idfactur);
    		input.setValue("idrecibo", idrecibo);
    		ObjectIO output = altaSrv.instanceOfOutput();
    		altaSrv.setConnection(con);
    		altaSrv.process(input, output);
    		
    		filecrea = output.getStringValue("filecrea");
    		filecrea = output.getStringValue("filecrea");
			txmensaj = output.getStringValue("txmensaj");
    	} catch(Exception e) {
    		System.out.println("Error al crear recibo Izumba");
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
    
 public void pintaDatosBancarios(){
    	
    	try {

	    		ListFormaPagoBDIn formPagoBDIn = new ListFormaPagoBDIn();
		    	formPagoBDIn.setValue("idemisor",idemisor);
				formPagoBDIn.setValue("idfrmpag",idfrmpag);
				ListFormaPagoBD formPagoBD = new ListFormaPagoBD(formPagoBDIn);
		    	formPagoBD.setConnection(con);
		    	Grid gdFrmPag = formPagoBD.execSelect();
	    		
		    	if (gdFrmPag.rowCount() > 0){
			    	
		    		txforpag = gdFrmPag.getStringCell(0,"txnombre");
			    	cuentapu = gdFrmPag.getStringCell(0,"cuentapu"); 	
		    	}
	    
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    	
    	
   
    }
    
    
}
	