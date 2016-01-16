package factura;


import common.bd.ListDivEmisorBD;
import common.bd.ListDivEmisorBDIn;

import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import factura.bd.ListAreasneBD;
import factura.bd.ListAreasneBDIn;
import factura.bd.ListClientesBD;
import factura.bd.ListClientesBDIn;
import factura.bd.ListFormaPagoBD;
import factura.bd.ListFormaPagoBDIn;
import factura.bd.ListProductsEmisorBD;
import factura.bd.ListProductsEmisorBDIn;
import factura.bd.ListStockBD;
import factura.bd.ListStockBDIn;
import factura.bd.ListTipoFacBD;
import factura.bd.ListTipoFacBDIn;
import factura.bd.MaxFacturaBD;
import factura.bd.MaxFacturaBDIn;
import factura.bd.MaxFechaFacturaBD;
import factura.bd.MaxFechaFacturaBDIn;
import facturacion.bd.ListDetalleClienteBD;
import facturacion.bd.ListDetalleClienteBDIn;
import reparaciones.bd.ListReparaBD;
import reparaciones.bd.ListReparaBDIn;


public class InitFacturaSrv extends Service {
	
	
	String idemisor = null;
	String tpclient = null;

    public InitFacturaSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();						 			
			
			input.addVariable("idemisor");
			input.addVariable("tpclient");
			input.addVariable("modofact");
			
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
			output.addVariable("tpclient");
			output.addVariable("idfactur");
			output.addVariable("logoemis");
			output.addVariable("cddivisa");
			output.addVariable("gridClie");	
			output.addVariable("gridPhon");
			output.addVariable("gridProd");
			output.addVariable("gridTpFa");
			output.addVariable("gridFrPg");
			output.addVariable("gridfmax");
			output.addVariable("gridArne");
			output.addVariable("gridVend");
			output.addVariable("gridDepo");
			output.addVariable("gridRepa");
			
		
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	
    	String modofact = null;
    	String logoemis = null;
    	String cddivisa = "";
        
        //Varibales de salida
        int idfactur = 0;
        String fecmaxfa = null;
        Grid gridClie = null;
        Grid gridPhon = null;
        Grid gridProd = null;
        Grid gridVend = null;
        Grid gridTpFa = null;
        Grid gridFech = null;
        Grid gridFrPg = null;
        Grid gridArne = null;
        Grid gridDepo = null;
        Grid gridRepa = null;
        Grid gridDivi = null;
        
        //Otras Variables
     
        try{        	
        	
        	idemisor = input.getStringValue("idemisor");
        	tpclient = input.getStringValue("tpclient");
        	modofact = input.getStringValue("modofact");
        	logoemis = getLogoEmisor();
        	
        	
        	//MAX FACTURA
        	MaxFacturaBDIn factBDIn = new MaxFacturaBDIn();
        	factBDIn.setValue("idemisor",idemisor);
        	MaxFacturaBD factBD = new MaxFacturaBD(factBDIn);
        	factBD.setConnection(con);
        	Grid gridFact = factBD.execSelect();
        	
        	try {
	        	if (gridFact.rowCount() > 0){
	        		idfactur = Integer.parseInt(gridFact.getStringCell(0,"idfactur"));
	        		idfactur++;
	        	}
        	} catch (Exception e) {
        		idfactur = 1;
        	}
	        	
        	//CLIENTES
        	ListClientesBDIn clieBDIn = new ListClientesBDIn();
        	clieBDIn.setValue("idemisor",idemisor);
        	clieBDIn.setValue("tpclient",tpclient);
        	ListClientesBD clieBD = new ListClientesBD(clieBDIn);
        	clieBD.setConnection(con);
        	gridClie = clieBD.execSelect();
        	
        	//PRODUCTOS DE LA TABLA TRADSTOC
        	
        	String cdestado = "";
        	String stockyde = "";
        	if( modofact!= null && modofact.equals("D")){
        		cdestado = "VENDIDO";
        	}else{
        		cdestado = "STOCK";
        		stockyde = "S";
        	}
        	
        	
        	
        	ListStockBDIn phonBDIn = new ListStockBDIn();
        	phonBDIn.setValue("idemisor",idemisor);
        	phonBDIn.setValue("cdestado",cdestado);
        	phonBDIn.setValue("stockyde",stockyde);
        	
        	ListStockBD stockBD = new ListStockBD(phonBDIn);
        	stockBD.setConnection(con);
        	gridPhon = stockBD.execSelect();
        	
        	/*vendidos*/
        	ListStockBDIn stockVenBDIn = new ListStockBDIn();
        	stockVenBDIn.setValue("idemisor",idemisor);
        	stockVenBDIn.setValue("cdestado","VENDIDO");
        	ListStockBD stockVenBD = new ListStockBD(stockVenBDIn);
        	stockVenBD.setConnection(con);
        	gridVend = stockVenBD.execSelect();
        	
        	ListStockBDIn stockDepBDIn = new ListStockBDIn();
        	stockDepBDIn.setValue("idemisor",idemisor);
        	stockDepBDIn.setValue("cdestado","DEPOSITO");
        	ListStockBD stockDepBD = new ListStockBD(stockDepBDIn);
        	stockDepBD.setConnection(con);
        	gridDepo = stockDepBD.execSelect();
        	
        	
        	//AREAS DE NEGOCIO
        	ListAreasneBDIn areasneBDIn = new ListAreasneBDIn();
        	areasneBDIn.setValue("idemisor",idemisor);
        	ListAreasneBD areasneBD = new ListAreasneBD(areasneBDIn);
        	areasneBD.setConnection(con);
        	gridArne = areasneBD.execSelect();
        	
        	//PRODUCTS
        	ListProductsEmisorBDIn prodBDIn = new ListProductsEmisorBDIn();
        	prodBDIn.setValue("idemisor",idemisor);
        	ListProductsEmisorBD prodBD = new ListProductsEmisorBD(prodBDIn);
        	prodBD.setConnection(con);
        	gridProd = prodBD.execSelect();
        	
        	
        	//REPARACIONES
        	ListReparaBDIn reparaBDIn = new ListReparaBDIn();
        	reparaBDIn.setValue("idemisor",idemisor);
        	ListReparaBD reparaBD = new ListReparaBD(reparaBDIn);
        	reparaBD.setConnection(con);
        	gridRepa = reparaBD.execSelect();
        	
        	//TIPO FACTURA
        	ListTipoFacBDIn ListTipoBDIn  = new ListTipoFacBDIn();  
        	ListTipoBDIn.setValue("cdemisor", idemisor);
        	ListTipoBDIn.setValue("modofact", modofact);
        	
        	ListTipoFacBD ListTipoBD = new ListTipoFacBD(ListTipoBDIn);
        	ListTipoBD.setConnection(con);
        	gridTpFa = ListTipoBD.execSelect();
        	
        	//ULTIMA FECHA FACTURA
        	MaxFechaFacturaBDIn FechaFaBDIn = new MaxFechaFacturaBDIn();
        	FechaFaBDIn.setValue("idemisor", idemisor);
        	MaxFechaFacturaBD FechaFacBD = new MaxFechaFacturaBD(FechaFaBDIn);
        	FechaFacBD.setConnection(con);
        	gridFech = FechaFacBD.execSelect();
        	
        	//// divisa
			ListDivEmisorBDIn divisaBDIn= new ListDivEmisorBDIn();
			divisaBDIn.setValue("idemisor", idemisor);
			ListDivEmisorBD divisaBD = new ListDivEmisorBD(divisaBDIn);
			divisaBD.setConnection(con);
			gridDivi = divisaBD.execSelect();
			cddivisa = gridDivi.getStringCell(0, "divsimbo");
        	
        	try {
        		fecmaxfa = gridFech.getStringCell(0, "fechafac");
        	} catch (Exception e) {
        		fecmaxfa ="01/01/2014";
        	}
        	
        	//FORMA DE PAGO
        	ListFormaPagoBDIn formPagoBDIn = new ListFormaPagoBDIn();
        	formPagoBDIn.setValue("idemisor",idemisor);
        	ListFormaPagoBD formPagoBD = new ListFormaPagoBD(formPagoBDIn);
        	formPagoBD.setConnection(con);
        	gridFrPg = formPagoBD.execSelect();
        	
        	//RECUPERO LOS VALORES DEL INPUT
        	output.setValue("idemisor", idemisor);
        	output.setValue("tpclient", tpclient);
        	output.setValue("cddivisa", cddivisa);
        	output.setValue("idfactur", idfactur);
        	output.setValue("logoemis", logoemis);
        	
        	output.setGrid("gridClie", gridClie);
        	output.setGrid("gridPhon", gridPhon);
        	output.setGrid("gridProd", gridProd);
        	output.setGrid("gridTpFa", gridTpFa);
        	output.setGrid("gridFrPg", gridFrPg);
        	output.setGrid("gridfmax", gridFech);
        	output.setGrid("gridArne", gridArne);
        	output.setGrid("gridVend", gridVend);
        	output.setGrid("gridDepo", gridDepo);
        	output.setGrid("gridRepa", gridRepa);
	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
    
   public String getLogoEmisor(){
	   
	   Grid gridDeta = null;
		
		try{
			ListDetalleClienteBDIn detalleBDIn = new ListDetalleClienteBDIn();
			detalleBDIn.setValue("idemisor",idemisor);
			detalleBDIn.setValue("idclient","0");
			detalleBDIn.setValue("tpclient",tpclient);
			ListDetalleClienteBD logoBD = new ListDetalleClienteBD(detalleBDIn);
			logoBD.setConnection(con);
          gridDeta = logoBD.execSelect();
       	
		} catch (Exception e) {
			System.err.println(this.getClass().getName() +" ERROR - Al obtener el logo del cliente. "+ e.getMessage());
		}
		String logo = gridDeta.getStringCell(0,"logoclie");
       
		return logo;
    	
    }
       
}