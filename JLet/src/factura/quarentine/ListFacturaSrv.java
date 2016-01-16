package factura;


import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import factura.bd.ListClientesBD;
import factura.bd.ListClientesBDIn;
import factura.bd.ListFormaPagoBD;
import factura.bd.ListFormaPagoBDIn;
import factura.bd.ListPhonesBD;
import factura.bd.ListPhonesBDIn;
import factura.bd.ListProductsBD;
import factura.bd.ListProductsBDIn;
import factura.bd.ListTipoFacBD;
import factura.bd.ListTipoFacBDIn;
import factura.bd.MaxFacturaBD;
import factura.bd.MaxFacturaBDIn;
import factura.bd.MaxFechaFacturaBD;
import factura.bd.MaxFechaFacturaBDIn;


public class ListFacturaSrv extends Service {

    public ListFacturaSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();						 			
			
			input.addVariable("idemisor");
			
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
			output.addVariable("idfactur");
			output.addVariable("gridClie");
			output.addVariable("gridPhon");
			output.addVariable("gridProd");
			output.addVariable("gridTpFa");
			output.addVariable("gridFrPg");
			output.addVariable("fecmaxfa");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor = null;
        
        //Varibales de salida
        int idfactur = 0;
        String fecmaxfa = null;
        Grid gridClie= null;
        Grid gridPhon= null;
        Grid gridProd= null;
        Grid gridTpFa= null;
        Grid gridFech= null;
        Grid gridFrPg= null;
        
        //Otras Variables
     
        
        try{        	
        	
        	idemisor = input.getStringValue("idemisor");
        	
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
        	ListClientesBD clieBD = new ListClientesBD(clieBDIn);
        	clieBD.setConnection(con);
        	gridClie = clieBD.execSelect();
        	
        	//TELEFONOS
        	ListPhonesBDIn phonBDIn = new ListPhonesBDIn();
        	ListPhonesBD phonBD = new ListPhonesBD(phonBDIn);
        	phonBD.setConnection(con);
        	gridPhon = phonBD.execSelect();
        	
        	//PRODUCTS
        	ListProductsBDIn prodBDIn = new ListProductsBDIn();
        	ListProductsBD prodBD = new ListProductsBD(prodBDIn);
        	prodBD.setConnection(con);
        	gridProd = prodBD.execSelect();
        	
        	
        	// tipo facturas
        	ListTipoFacBDIn ListTipoBDIn  = new ListTipoFacBDIn();  
        	ListTipoBDIn.setValue("cdemisor", idemisor);
        	ListTipoFacBD ListTipoBD = new ListTipoFacBD(ListTipoBDIn);
        	ListTipoBD.setConnection(con);
        	gridTpFa = ListTipoBD.execSelect();
        	
        	// ultima fecha de fatura
        	MaxFechaFacturaBDIn FechaFaBDIn = new MaxFechaFacturaBDIn();
        	FechaFaBDIn.setValue("idemisor", idemisor);
        	MaxFechaFacturaBD FechaFacBD = new MaxFechaFacturaBD(FechaFaBDIn);
        	FechaFacBD.setConnection(con);
        	gridFech = FechaFacBD.execSelect();
        	try {
        		fecmaxfa = gridFech.getStringCell(0, "fechafac");
        	} catch (Exception e) {
        		fecmaxfa ="01/01/2014";
        	}
        	
        	//Listado Formas de Pago
        	ListFormaPagoBDIn formPagoBDIn = new ListFormaPagoBDIn();
        	formPagoBDIn.setValue("idemisor",idemisor);
        	ListFormaPagoBD formPagoBD = new ListFormaPagoBD(formPagoBDIn);
        	formPagoBD.setConnection(con);
        	gridFrPg = formPagoBD.execSelect();
        	
        	//RECUPERO LOS VALORES DEL INPUT
        	output.setValue("idemisor", idemisor);
        	output.setValue("idfactur", idfactur);
        	output.setGrid("gridClie", gridClie);
        	output.setGrid("gridPhon", gridPhon);
        	output.setGrid("gridProd", gridProd);
        	output.setGrid("gridTpFa", gridTpFa);
        	output.setGrid("gridFrPg", gridFrPg);
        	output.setGrid("fecmaxfa", gridFech);
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
       
}