package factura;


import utils.PropiedadesJLet;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import factura.bd.ListAnioFactBD;
import factura.bd.ListAnioFactBDIn;
import factura.bd.ListClientesFactBD;
import factura.bd.ListClientesFactBDIn;
import factura.bd.ListFacturasBD;
import factura.bd.ListFacturasBDIn;
import factura.bd.MaxFacturaTipoBD;
import factura.bd.MaxFacturaTipoBDIn;
import factura.bd.MaxFechaFacturaBD;
import factura.bd.MaxFechaFacturaBDIn;


public class ListFacturasEmisorSrv extends Service {

    public ListFacturasEmisorSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();

			input.addVariable("idemisor");
			input.addVariable("aniofact");		
			input.addVariable("isalbara");
			input.addVariable("tpclient");
			input.addVariable("idcliere");
			input.addVariable("fhdesdex");
			input.addVariable("fhhastax");
			input.addVariable("predesde");
			input.addVariable("prehasta");
			input.addVariable("logoemis");
			input.addVariable("conitbis");
			
						
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
			output.addVariable("aniofact");	
			output.addVariable("grfactur");	
			output.addVariable("gridMaxF");	
			output.addVariable("gdAniosx");
			output.addVariable("isalbara");
			output.addVariable("gridClie");
			output.addVariable("fhdesdex");
			output.addVariable("fhhastax");
			output.addVariable("idcliere");
			output.addVariable("predesde");
			output.addVariable("prehasta");
			output.addVariable("logoemis");
			output.addVariable("tpclient");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor 	= null;
    	String isalbara 	= null;
    	String aniofact 	= null;
    	String tpclient 	= null;
    	String idcliere 	= null;
    	String fhdesdexMy 	= null; //al devolver la fecha para mostrar en el input no la pude devolver en formato mysql
    	String fhhastaMy 	= null;
    	String predesde		= null;
    	String prehasta 	= null;
    	String logoemis		= null;
    	String conitbis     = null;
        
        Grid gridFact 	= null;
        Grid gridMaxF   = null;
        Grid gridClie 	= null;
        String fhdesdex = null;
    	String fhhastax = null;
     
        
        try{        	
        	
        	idemisor = input.getStringValue("idemisor");
        	aniofact = input.getStringValue("aniofact");
        	isalbara = input.getStringValue("isalbara");
        	tpclient = input.getStringValue("tpclient");
        	idcliere = input.getStringValue("idcliere");
        	fhdesdex = input.getStringValue("fhdesdex");
        	fhhastax = input.getStringValue("fhhastax");
        	predesde = input.getStringValue("predesde");
        	prehasta = input.getStringValue("prehasta");
        	logoemis = input.getStringValue("logoemis");
        	conitbis = input.getStringValue("conitbis");
        	
        	if((fhdesdex  != null) && (!fhdesdex.equals(""))){
        		fhdesdexMy = fechaMySQL(fhdesdex);
        	}
        	
        	if((fhhastax != null) && (!fhhastax.equals(""))){
        		fhhastaMy = fechaMySQL(fhhastax);
        	}
        	
        	String intpfact = PropiedadesJLet.getInstance().getProperty("jlet.fact.orden"); 
        	// listado facturas
        	ListFacturasBDIn ListFactBDIn  = new ListFacturasBDIn();  
        	ListFactBDIn.setValue("idemisor", idemisor);
        	ListFactBDIn.setValue("aniofact", aniofact);
        	ListFactBDIn.setValue("isalbara", isalbara);
        	ListFactBDIn.setValue("tpclient", tpclient);
        	ListFactBDIn.setValue("idclient", idcliere);
        	ListFactBDIn.setValue("fhdesdex", fhdesdexMy);
        	ListFactBDIn.setValue("fhhastax", fhhastaMy);
        	ListFactBDIn.setValue("predesde", predesde);
        	ListFactBDIn.setValue("prehasta", prehasta);
        	ListFactBDIn.setValue("conitbis", conitbis);
        	ListFactBDIn.setValue("intpfact", intpfact);
        	
        	ListFacturasBD ListTipoBD = new ListFacturasBD(ListFactBDIn);
        	ListTipoBD.setConnection(con);
        	gridFact = ListTipoBD.execSelect();
        	
        	//clientes con facturas del emisor
        	ListClientesFactBDIn ListCliFactBDIn  = new ListClientesFactBDIn();  
        	ListCliFactBDIn.setValue("idemisor", idemisor);
        	ListCliFactBDIn.setValue("aniofact", aniofact);
        	ListCliFactBDIn.setValue("isalbara", isalbara);
        	ListCliFactBDIn.setValue("idclient", idcliere);
        	ListClientesFactBD ListCliTipoBD = new ListClientesFactBD(ListCliFactBDIn);
        	ListCliTipoBD.setConnection(con);
        	gridClie= ListCliTipoBD.execSelect();
        	
        	
        	/*AÑOS FACTURA*/
        	ListAnioFactBDIn ListAnioFaBDIn  = new ListAnioFactBDIn();  
        	ListAnioFaBDIn.setValue("idemisor", idemisor);
        	ListAnioFactBD ListAnioFaBD = new ListAnioFactBD(ListAnioFaBDIn);
        	ListAnioFaBD.setConnection(con);
        	Grid gdAniosx = ListAnioFaBD.execSelect();
        	
        	
        	//ULTIMA FECHA FACTURA
        	MaxFacturaTipoBDIn FechaFaBDIn = new MaxFacturaTipoBDIn();
        	FechaFaBDIn.setValue("idemisor", idemisor);
        	MaxFacturaTipoBD FechaFacBD = new MaxFacturaTipoBD(FechaFaBDIn);
        	FechaFacBD.setConnection(con);
        	gridMaxF = FechaFacBD.execSelect();
        	
        	//RECUPERO LOS VALORES DEL INPUT
        	
        	output.setValue("idemisor", idemisor);
        	output.setValue("tpclient", tpclient);
        	output.setValue("aniofact", aniofact);
        	output.setValue("isalbara", isalbara);
        	output.setValue("fhdesdex", fhdesdex);
        	output.setValue("fhhastax", fhhastax);
        	output.setValue("predesde", predesde);
        	output.setValue("prehasta", prehasta);
        	output.setValue("idcliere", idcliere);
        	output.setValue("logoemis",logoemis);
        	output.setGrid("grfactur", gridFact);
        	output.setGrid("gridClie", gridClie);
        	output.setGrid("gridMaxF", gridMaxF);
        	
        	output.setGrid("gdAniosx", gdAniosx);
        	
        } catch (Exception e1) {
			e1.printStackTrace();
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
       
}
