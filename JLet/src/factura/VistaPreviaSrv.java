package factura;


import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import factura.bd.ListClientesBD;
import factura.bd.ListClientesBDIn;
import factura.bd.VistPreviaBD;
import factura.bd.VistPreviaBDIn;


public class VistaPreviaSrv extends Service {

    public VistaPreviaSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();						 			
			input.addVariable("idclient");
			input.addVariable("tpclient");
			input.addVariable("fechafac");
			input.addVariable("tipoPorc");
			input.addVariable("porcrete");
			input.addVariable("tipoFact");
			input.addVariable("idemisor");
			input.addVariable("tipologo");
			input.addVariable("formpago");
			input.addVariable("condpago");
			input.addVariable("catefact");
			input.addVariable("txtpfact");
			input.addVariable("txformpa");
			input.addVariable("txcatefa");
			input.addVariable("txcondpa");
			input.addVariable("numsegui");
			input.addVariable("entregpo");
			input.addVariable("fhenviox");
			input.addVariable("portetot");
			input.addVariable("tipovent");
			input.addVariable("cddivisa");
			input.addVariable("autopaga");
			input.addVariable("numtrans");
				
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
			output.addVariable("idclient");
			output.addVariable("tpclient");
			output.addVariable("cddivisa");
			output.addVariable("autopaga");
			output.addVariable("numtrans");
			
			output.addVariable("fechafac");
			output.addVariable("tipoPorc");
			output.addVariable("porcrete");
			output.addVariable("tipoFact");
			output.addVariable("idemisor");
			output.addVariable("tipologo");
			output.addVariable("formpago");
			output.addVariable("condpago");
			output.addVariable("catefact");
			output.addVariable("txtpfact");
			output.addVariable("txformpa");
			output.addVariable("txcatefa");
			output.addVariable("txcondpa");
			output.addVariable("numsegui");
			output.addVariable("entregpo");
			output.addVariable("tipovent");
			output.addVariable("fhenviox");
			output.addVariable("portetot");
			output.addVariable("gridClie");
			output.addVariable("gridLine");	
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
		String idclient = "";
		String tpclient = "";
		String fechafac = "";
		String tipologo = "";
		String formpago = "";
		String condpago = "";
		String catefact = "";
		String cddivisa = "";
        
        //Varibales de salida
        String txmensaj = "";
        Grid gridResu = null;
        Grid gridClie = null;
        String tipoPorc = "";
        String porcrete = "";
        String tipoFact = "";
        String idemisor = "";
        String autopaga = "";
        String numtrans = "";
        //Otras Variables
        
        
	
		
		String txtpfact = "";
		String txformpa = "";
		String txcatefa = "";
		String txcondpa = "";
		
		String numsegui = "";
		String entregpo = "";
		String fhenviox = "";
		String portetot = "";
		String tipovent = "";
	
     
        try {

			   idclient = input.getStringValue("idclient");
			   tpclient = input.getStringValue("tpclient");
			   fechafac = input.getStringValue("fechafac");
			   tipoPorc = input.getStringValue("tipoPorc");
			   tipoFact = input.getStringValue("tipoFact");
			   idemisor = input.getStringValue("idemisor");
			   tipologo = input.getStringValue("tipologo");
			   formpago = input.getStringValue("formpago");
			   condpago = input.getStringValue("condpago");
			   catefact = input.getStringValue("catefact");
			   txtpfact = input.getStringValue("txtpfact");
			   txformpa = input.getStringValue("txformpa");
			   txcatefa = input.getStringValue("txcatefa");
			   txcondpa = input.getStringValue("txcondpa");
			   porcrete = input.getStringValue("porcrete");
			   tipovent = input.getStringValue("tipovent");
			   cddivisa = input.getStringValue("cddivisa");
			   autopaga = input.getStringValue("autopaga");
			   numtrans = input.getStringValue("numtrans");

			   VistPreviaBDIn VistaPreBDIn= new VistPreviaBDIn();
			   VistaPreBDIn.setValue("idemisor",idemisor);
			   VistaPreBDIn.setValue("idclient",idclient);
			   VistaPreBDIn.setValue("tpclient",tpclient);
			   VistPreviaBD lineasBD = new VistPreviaBD(VistaPreBDIn);
			   lineasBD.setConnection(con);
			   gridResu = lineasBD.execSelect();
			   
			   
			   ListClientesBDIn DetCliBDIn= new ListClientesBDIn();
			   DetCliBDIn.setValue("idemisor",idemisor);
			   DetCliBDIn.setValue("tpclient",tpclient);
			   DetCliBDIn.setValue("idclient",idclient);
			   ListClientesBD DetCliBD = new ListClientesBD(DetCliBDIn);
			   DetCliBD.setConnection(con);
			   gridClie = DetCliBD.execSelect();
	
	           output.setValue("idclient", idclient);
	           output.setValue("tpclient", tpclient);
	           output.setValue("cddivisa", cddivisa);
	           
	           output.setValue("fechafac", fechafac);
	           output.setValue("tipoPorc", tipoPorc);
	           output.setValue("porcrete", porcrete);
	           output.setValue("tipoFact", tipoFact);
	           output.setValue("idemisor", idemisor);
	           output.setValue("tipologo", tipologo);
	           output.setValue("formpago", formpago);
	           output.setValue("condpago", condpago);
	           output.setValue("catefact", catefact);
	           output.setValue("txtpfact", txtpfact);
	           output.setValue("txformpa", txformpa);
	           output.setValue("txcatefa", txcatefa);
	           output.setValue("txcondpa", txcondpa);
	           output.setValue("tipovent", tipovent);
	           output.setValue("autopaga", autopaga);
	           output.setValue("numtrans", numtrans);
	           
	           
	           output.setGrid("gridLine", gridResu);
	           output.setGrid("gridClie", gridClie);
	            	
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
	