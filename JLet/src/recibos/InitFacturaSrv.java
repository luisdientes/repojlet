package recibos;



import recibos.bd.ListFacturaBD;
import recibos.bd.ListFacturaBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import factura.bd.ListClientesBD;
import factura.bd.ListClientesBDIn;
import factura.bd.ListFormaPagoBD;
import factura.bd.ListFormaPagoBDIn;



public class InitFacturaSrv extends Service {

    public InitFacturaSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();						 			
			
			input.addVariable("idemisor");
			input.addVariable("tpclient");
			
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
			output.addVariable("gridClie");
			output.addVariable("gridFrPg");
			output.addVariable("gridFact");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor = null;
    	String tpclient = null;
        
        //Varibales de salida
        int idfactur = 0;
        String fecmaxfa = null;
        Grid gridClie= null;
        Grid gridPhon= null;
        Grid gridProd= null;
        Grid gridTpFa= null;
        Grid gridFech= null;
        Grid gridFrPg= null;
        Grid gridFact =null;
        
        //Otras Variables
     
        try{        	
        	
        	idemisor = input.getStringValue("idemisor");
        	tpclient = input.getStringValue("tpclient");
        	
        	//CLIENTES
        	ListClientesBDIn clieBDIn = new ListClientesBDIn();
        	clieBDIn.setValue("idemisor",idemisor);
        	clieBDIn.setValue("tpclient",tpclient);
        	ListClientesBD clieBD = new ListClientesBD(clieBDIn);
        	clieBD.setConnection(con);
        	gridClie = clieBD.execSelect();
        	
        	
        	//FACTURAS
        	ListFacturaBDIn listFacBDIn = new ListFacturaBDIn();
        	listFacBDIn.setValue("idemisor",idemisor);
        	listFacBDIn.setValue("tpclient",tpclient);
        	ListFacturaBD listFacBD= new ListFacturaBD(listFacBDIn);
        	listFacBD.setConnection(con);
        	gridFact = listFacBD.execSelect();
 
        	
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
        	output.setGrid("gridClie", gridClie);
        	output.setGrid("gridFrPg", gridFrPg);
        	output.setGrid("gridFact", gridFact);
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
       
}