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
import factura.bd.ListRecibosBD;
import factura.bd.ListRecibosBDIn;
import factura.bd.ListTipoFacBD;
import factura.bd.ListTipoFacBDIn;
import factura.bd.MaxFacturaBD;
import factura.bd.MaxFacturaBDIn;
import factura.bd.MaxFechaFacturaBD;
import factura.bd.MaxFechaFacturaBDIn;


public class ListRecibosSrv extends Service {

    public ListRecibosSrv() {
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
			input.addVariable("numfactu");
			input.addVariable("totalfac");
			input.addVariable("divisaxx");
			input.addVariable("mcpagado");
			input.addVariable("tpfacrec");
			
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
			output.addVariable("mcpagado");
			output.addVariable("idfactur");
			output.addVariable("idclient");
			output.addVariable("numfactu");
			output.addVariable("divisaxx");
			output.addVariable("tpfacrec");
			output.addVariable("totalfac");
			output.addVariable("gridReci");
			output.addVariable("gridFrPg");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor = null;
    	String idfactur = null;
    	String idclient = null;
    	String numfactu = null;
    	String totalfac = null;
    	String tpclient = null;
    	String mcpagado = null;
    	String tpfacrec = null;
    	String divisaxx = "";
        
        //Varibales de salida
  
        Grid gridReci = null;
        Grid gridFrPg = null;

        try{        	
        	
        	idemisor = input.getStringValue("idemisor");
        	idfactur = input.getStringValue("idfactur");
        	idclient = input.getStringValue("idclient");
        	numfactu = input.getStringValue("numfactu");
        	totalfac = input.getStringValue("totalfac"); 
        	tpclient = input.getStringValue("tpclient");
        	mcpagado = input.getStringValue("mcpagado");
        	divisaxx = input.getStringValue("divisaxx");
        	tpfacrec = input.getStringValue("tpfacrec");
        	totalfac = totalfac.replace(".", "");
        	totalfac = totalfac.replace(",", ".");
        	ListRecibosBDIn reciboBDIn = new ListRecibosBDIn();
        	reciboBDIn.setValue("idemisor",idemisor);
        	reciboBDIn.setValue("idfactur",idfactur);
        	reciboBDIn.setValue("tpclient",tpclient);
        	
        	ListRecibosBD reciboBD = new ListRecibosBD(reciboBDIn);
        	reciboBD.setConnection(con);
        	gridReci = reciboBD.execSelect();
        	
        	
        	//FORMA DE PAGO
        	//FORMA DE PAGO
        	ListFormaPagoBDIn formPagoBDIn = new ListFormaPagoBDIn();
        	formPagoBDIn.setValue("idemisor",idemisor);
        	ListFormaPagoBD formPagoBD = new ListFormaPagoBD(formPagoBDIn);
        	formPagoBD.setConnection(con);
        	gridFrPg = formPagoBD.execSelect();
        	
        	//MAX FACTURA
        	/*MaxReciboBDIn factBDIn = new MaxReciboBDIn();
        	factBDIn.setValue("idemisor",idemisor);
        	MaxReciboBD factBD = new MaxReciboBD(factBDIn);
        	factBD.setConnection(con);
        	Grid gridFact = factBD.execSelect();
        	
        	try {
	        	if (gridFact.rowCount() > 0){
	        		idfactur = Integer.parseInt(gridFact.getStringCell(0,"idfactur"));
	        		idfactur++;
	        	}
        	} catch (Exception e) {
        		idfactur = 1;
        	}*/
	        	
        
        	
        	//RECUPERO LOS VALORES DEL INPUT
        	output.setValue("idemisor", idemisor);
        	output.setValue("idfactur", idfactur);
        	output.setValue("idclient", idclient);
        	output.setValue("numfactu", numfactu);
        	output.setValue("tpclient", tpclient);
        	output.setValue("divisaxx", divisaxx);
        	output.setValue("tpfacrec", tpfacrec);
        	output.setValue("mcpagado", mcpagado);
        	output.setValue("totalfac", totalfac);
        	output.setGrid("gridReci", gridReci);
        	output.setGrid("gridFrPg", gridFrPg);
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
       
}