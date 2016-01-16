package factura;


import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import factura.bd.ListAreasneBD;
import factura.bd.ListAreasneBDIn;
import factura.bd.ListClientesBD;
import factura.bd.ListClientesBDIn;
import factura.bd.ListFacturasBD;
import factura.bd.ListFacturasBDIn;
import factura.bd.ListFormaPagoBD;
import factura.bd.ListFormaPagoBDIn;
import factura.bd.ListLineasBD;
import factura.bd.ListLineasBDIn;
import factura.bd.ListProductsBD;
import factura.bd.ListProductsBDIn;
import factura.bd.ListStockBD;
import factura.bd.ListStockBDIn;
import factura.bd.ListTipoFacBD;
import factura.bd.ListTipoFacBDIn;
import factura.bd.MaxFacturaBD;
import factura.bd.MaxFacturaBDIn;
import factura.bd.MaxFechaFacturaBD;
import factura.bd.MaxFechaFacturaBDIn;


public class InitDevolucionSrv extends Service {

    public InitDevolucionSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();						 			
			
			input.addVariable("idemisor");
			input.addVariable("tpclient");
			input.addVariable("modofact");
			input.addVariable("logoemis");
			
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
			output.addVariable("gridClie");
			output.addVariable("gridPhon");
			output.addVariable("gridProd");
			output.addVariable("gridTpFa");
			output.addVariable("gridFrPg");
			output.addVariable("gridfmax");
			output.addVariable("gridArne");
			output.addVariable("gridResu");
			output.addVariable("gdFactur");
			
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor = null;
    	String tpclient = null;
    	String modofact = null;
    	String logoemis = null;
        
        //Varibales de salida
        int idfactur = 0;
        String fecmaxfa = null;
        Grid gridClie= null;
        Grid gridPhon= null;
        Grid gdFactur= null;
        Grid gridProd= null;
        Grid gridTpFa= null;
        Grid gridFech= null;
        Grid gridFrPg= null;
        Grid gridArne= null;
        
        //Otras Variables
     
        try{        	
        	
        	idemisor = input.getStringValue("idemisor");
        	tpclient = input.getStringValue("tpclient");
        	modofact = input.getStringValue("modofact");
        	logoemis = input.getStringValue("logoemis");
        	
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
        	if( modofact!= null && modofact.equals("D")){
        		cdestado = "VENDIDO";
        	}else{
        		cdestado = "STOCK";
        	}
        	
        	ListStockBDIn phonBDIn = new ListStockBDIn();
        	phonBDIn.setValue("idemisor",idemisor);
        	phonBDIn.setValue("cdestado","VENDIDO");
        	ListStockBD stockBD = new ListStockBD(phonBDIn);
        	stockBD.setConnection(con);
        	gridPhon = stockBD.execSelect();
        	
        	
        	//listfactur
        	ListFacturasBDIn ListFactBDIn  = new ListFacturasBDIn();  
        	ListFactBDIn.setValue("idemisor", idemisor);
        	ListFactBDIn.setValue("aniofact", "2014");
        	ListFactBDIn.setValue("isalbara", "N");
        	ListFacturasBD ListFactBD = new ListFacturasBD(ListFactBDIn);
        	ListFactBD.setConnection(con);
        	gdFactur = ListFactBD.execSelect();
        	
        	
        	//AREAS DE NEGOCIO
        	ListAreasneBDIn areasneBDIn = new ListAreasneBDIn();
        	areasneBDIn.setValue("idemisor",idemisor);
        	ListAreasneBD areasneBD = new ListAreasneBD(areasneBDIn);
        	areasneBD.setConnection(con);
        	gridArne = areasneBD.execSelect();
        	
        	//PRODUCTS
        	ListProductsBDIn prodBDIn = new ListProductsBDIn();
        	ListProductsBD prodBD = new ListProductsBD(prodBDIn);
        	prodBD.setConnection(con);
        	gridProd = prodBD.execSelect();
        	
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
        	
        	
        	// listado lineas
        	
        	 	ListLineasBDIn lineasBDIn= new ListLineasBDIn();
				lineasBDIn.setValue("idemisor",idemisor);
				lineasBDIn.setValue("tpclient",tpclient);
				lineasBDIn.setValue("cdestado","F");
				ListLineasBD lineasBD = new ListLineasBD(lineasBDIn);
				lineasBD.setConnection(con);
				Grid gridResu = lineasBD.execSelect();

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
        	output.setValue("idfactur", idfactur);
        	output.setValue("logoemis", logoemis);
        	output.setGrid("gridResu", gridResu);
        	output.setGrid("gdFactur", gdFactur);
        	
        	output.setGrid("gridClie", gridClie);
        	output.setGrid("gridPhon", gridPhon);
        	output.setGrid("gridProd", gridProd);
        	output.setGrid("gridTpFa", gridTpFa);
        	output.setGrid("gridFrPg", gridFrPg);
        	output.setGrid("gridfmax", gridFech);
        	output.setGrid("gridArne", gridArne);
        	
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
       
}