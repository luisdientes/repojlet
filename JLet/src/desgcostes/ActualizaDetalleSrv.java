package desgcostes;


import java.util.HashMap;

import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import desgcostes.bd.ListCodigoCostesBD;
import desgcostes.bd.ListCodigoCostesBDIn;
import desgcostes.bd.ListDesgloseCostesBD;
import desgcostes.bd.ListDesgloseCostesBDIn;
import desgcostes.bd.ListDesgloseDetalleBD;
import desgcostes.bd.ListDesgloseDetalleBDIn;
import desgcostes.bd.UpdDetalleBD;
import desgcostes.bd.UpdDetalleBDIn;

 
public class ActualizaDetalleSrv extends Service {

	HashMap<String, String> hmDetall = new HashMap<String,String>();
	
	public ActualizaDetalleSrv() {
		super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			
			input.addVariable("idemisor");
			input.addVariable("idunicox");
			input.addVariable("idclient");
			input.addVariable("txclient");
			input.addVariable("cdfactur");
			input.addVariable("fhfactur");
			input.addVariable("fhstockx");
			input.addVariable("fhventax");
			input.addVariable("txtransp");
			input.addVariable("tracking");
			input.addVariable("canalven");
			input.addVariable("cdextern");
			input.addVariable("cdpaisxx");
			
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
			output.addVariable("idunicox");
			output.addVariable("tipooper"); 
			output.addVariable("gdcdcost"); 
			output.addVariable("gddscost"); 
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String tipooper = "";
    	String idemisor = "";
    	String idunicox = "";
    	String idclient = "";
    	String txclient = "";
    	String cdfactur = "";
    	String fhfactur = "";
    	String fhstockx = "";
    	String fhventax = "";
    	String txtransp = "";
    	String tracking = "";
    	String canalven = "";
    	String cdextern = "";
    	String cdpaisxx = "";


        //Varibales de salida
        Grid gdcdcost = null;
        Grid gddscost = null;
    	
    	
        //Otras Variables
        
        
        try {
        	
        	idemisor = input.getStringValue("idemisor");
        	idunicox = input.getStringValue("idunicox");
        	idclient = input.getStringValue("idclient");
        	txclient = input.getStringValue("txclient");
        	cdfactur = input.getStringValue("cdfactur");
        	fhfactur = input.getStringValue("fhfactur");
        	fhstockx = input.getStringValue("fhstockx");
        	fhventax = input.getStringValue("fhventax");
        	txtransp = input.getStringValue("txtransp");
        	tracking = input.getStringValue("tracking");
        	canalven = input.getStringValue("canalven");
        	cdextern = input.getStringValue("cdextern");
        	cdpaisxx = input.getStringValue("cdpaisxx");

        	recuperaCodigosDetalle(idemisor,idunicox);
        	
        	if (actualizarCampo("IDCLIENT",idclient)){
	        	
        		UpdDetalleBDIn detalleBDIn = new UpdDetalleBDIn();
        		detalleBDIn.setValue("idunicox", idunicox);
        		detalleBDIn.setValue("idemisor", idemisor);
	        	detalleBDIn.setValue("codedeta", "IDCLIENT");
	        	detalleBDIn.setValue("desvalue", idclient);
	        	UpdDetalleBD detalleBD = new UpdDetalleBD(detalleBDIn);
	        	detalleBD.setConnection(con);
	        	
		        try {
		        	detalleBD.execInsert();
	        	} catch (Exception e) {
	        		detalleBD.setConnection(con);
	        		detalleBD.execUpdate();
	        	}
        	}
        	
        	if (actualizarCampo("TXCLIENT",txclient)){
	        	
        		UpdDetalleBDIn detalleBDIn = new UpdDetalleBDIn();
        		detalleBDIn.setValue("idunicox", idunicox);
        		detalleBDIn.setValue("idemisor", idemisor);
	        	detalleBDIn.setValue("codedeta", "TXCLIENT");
	        	detalleBDIn.setValue("desvalue", txclient);
	        	UpdDetalleBD detalleBD = new UpdDetalleBD(detalleBDIn);
	        	detalleBD.setConnection(con);
	        	
		        try {
		        	detalleBD.execInsert();
	        	} catch (Exception e) {
	        		detalleBD.setConnection(con);
	        		detalleBD.execUpdate();
	        	}
        	}

        	if (actualizarCampo("CDFACTUR",cdfactur)){
	        	
        		UpdDetalleBDIn detalleBDIn = new UpdDetalleBDIn();
        		detalleBDIn.setValue("idunicox", idunicox);
        		detalleBDIn.setValue("idemisor", idemisor);
	        	detalleBDIn.setValue("codedeta", "CDFACTUR");
	        	detalleBDIn.setValue("desvalue", cdfactur);
	        	UpdDetalleBD detalleBD = new UpdDetalleBD(detalleBDIn);
	        	detalleBD.setConnection(con);
	        	
		        try {
		        	detalleBD.execInsert();
	        	} catch (Exception e) {
	        		detalleBD.setConnection(con);
	        		detalleBD.execUpdate();
	        	}
        	}
        	
        	if (actualizarCampo("FHFACTUR",fhfactur)){
	        	
        		UpdDetalleBDIn detalleBDIn = new UpdDetalleBDIn();
        		detalleBDIn.setValue("idunicox", idunicox);
        		detalleBDIn.setValue("idemisor", idemisor);
	        	detalleBDIn.setValue("codedeta", "FHFACTUR");
	        	detalleBDIn.setValue("desvalue", fhfactur);
	        	UpdDetalleBD detalleBD = new UpdDetalleBD(detalleBDIn);
	        	detalleBD.setConnection(con);
	        	
		        try {
		        	detalleBD.execInsert();
	        	} catch (Exception e) {
	        		detalleBD.setConnection(con);
	        		detalleBD.execUpdate();
	        	}
        	}
        	
        	if (actualizarCampo("FHSTOCKX",fhstockx)){
	        	
        		UpdDetalleBDIn detalleBDIn = new UpdDetalleBDIn();
        		detalleBDIn.setValue("idunicox", idunicox);
        		detalleBDIn.setValue("idemisor", idemisor);
	        	detalleBDIn.setValue("codedeta", "FHSTOCKX");
	        	detalleBDIn.setValue("desvalue", fhstockx);
	        	UpdDetalleBD detalleBD = new UpdDetalleBD(detalleBDIn);
	        	detalleBD.setConnection(con);
	        	
		        try {
		        	detalleBD.execInsert();
	        	} catch (Exception e) {
	        		detalleBD.setConnection(con);
	        		detalleBD.execUpdate();
	        	}
        	}
 
        	if (actualizarCampo("FHVENTAX",fhventax)){
	        	
        		UpdDetalleBDIn detalleBDIn = new UpdDetalleBDIn();
        		detalleBDIn.setValue("idunicox", idunicox);
        		detalleBDIn.setValue("idemisor", idemisor);
	        	detalleBDIn.setValue("codedeta", "FHVENTAX");
	        	detalleBDIn.setValue("desvalue", fhventax);
	        	UpdDetalleBD detalleBD = new UpdDetalleBD(detalleBDIn);
	        	detalleBD.setConnection(con);
	        	
		        try {
		        	detalleBD.execInsert();
	        	} catch (Exception e) {
	        		detalleBD.setConnection(con);
	        		detalleBD.execUpdate();
	        	}
        	}

        	if (actualizarCampo("TXTRANSP",txtransp)){
	        	
        		UpdDetalleBDIn detalleBDIn = new UpdDetalleBDIn();
        		detalleBDIn.setValue("idunicox", idunicox);
        		detalleBDIn.setValue("idemisor", idemisor);
	        	detalleBDIn.setValue("codedeta", "TXTRANSP");
	        	detalleBDIn.setValue("desvalue", txtransp);
	        	UpdDetalleBD detalleBD = new UpdDetalleBD(detalleBDIn);
	        	detalleBD.setConnection(con);
	        	
		        try {
		        	detalleBD.execInsert();
	        	} catch (Exception e) {
	        		detalleBD.setConnection(con);
	        		detalleBD.execUpdate();
	        	}
        	}
        	
        	if (actualizarCampo("TRACKING",tracking)){
	        	
        		UpdDetalleBDIn detalleBDIn = new UpdDetalleBDIn();
        		detalleBDIn.setValue("idunicox", idunicox);
        		detalleBDIn.setValue("idemisor", idemisor);
	        	detalleBDIn.setValue("codedeta", "TRACKING");
	        	detalleBDIn.setValue("desvalue", tracking);
	        	UpdDetalleBD detalleBD = new UpdDetalleBD(detalleBDIn);
	        	detalleBD.setConnection(con);
	        	
		        try {
		        	detalleBD.execInsert();
	        	} catch (Exception e) {
	        		detalleBD.setConnection(con);
	        		detalleBD.execUpdate();
	        	}
        	}
        	
        	if (actualizarCampo("CANALVEN",canalven)){
	        	
        		UpdDetalleBDIn detalleBDIn = new UpdDetalleBDIn();
        		detalleBDIn.setValue("idunicox", idunicox);
        		detalleBDIn.setValue("idemisor", idemisor);
	        	detalleBDIn.setValue("codedeta", "CANALVEN");
	        	detalleBDIn.setValue("desvalue", canalven);
	        	UpdDetalleBD detalleBD = new UpdDetalleBD(detalleBDIn);
	        	detalleBD.setConnection(con);
	        	
		        try {
		        	detalleBD.execInsert();
	        	} catch (Exception e) {
	        		detalleBD.setConnection(con);
	        		detalleBD.execUpdate();
	        	}
        	}
        	
        	if (actualizarCampo("CDEXTERN",cdextern)){
	        	
        		UpdDetalleBDIn detalleBDIn = new UpdDetalleBDIn();
        		detalleBDIn.setValue("idunicox", idunicox);
        		detalleBDIn.setValue("idemisor", idemisor);
	        	detalleBDIn.setValue("codedeta", "CDEXTERN");
	        	detalleBDIn.setValue("desvalue", cdextern);
	        	UpdDetalleBD detalleBD = new UpdDetalleBD(detalleBDIn);
	        	detalleBD.setConnection(con);
	        	
		        try {
		        	detalleBD.execInsert();
	        	} catch (Exception e) {
	        		detalleBD.setConnection(con);
	        		detalleBD.execUpdate();
	        	}
        	}
        	
        	if (actualizarCampo("CDPAISXX",cdpaisxx)){
	        	
        		UpdDetalleBDIn detalleBDIn = new UpdDetalleBDIn();
        		detalleBDIn.setValue("idunicox", idunicox);
        		detalleBDIn.setValue("idemisor", idemisor);
	        	detalleBDIn.setValue("codedeta", "CDPAISXX");
	        	detalleBDIn.setValue("desvalue", cdpaisxx);
	        	UpdDetalleBD detalleBD = new UpdDetalleBD(detalleBDIn);
	        	detalleBD.setConnection(con);
	        	
		        try {
		        	detalleBD.execInsert();
	        	} catch (Exception e) {
	        		detalleBD.setConnection(con);
	        		detalleBD.execUpdate();
	        	}
        	}
	        	
        	ListCodigoCostesBDIn codCosteBDIn = new ListCodigoCostesBDIn();
        	codCosteBDIn.setValue("tipooper",tipooper);
        	codCosteBDIn.setValue("mcactivo","S");
        	ListCodigoCostesBD codCosteBD = new ListCodigoCostesBD(codCosteBDIn);
        	codCosteBD.setConnection(con);
			gdcdcost = codCosteBD.execSelect();
			
			ListDesgloseCostesBDIn desCosteBDIn =	 new ListDesgloseCostesBDIn();
        	desCosteBDIn.setValue("idunicox",idunicox);
        	desCosteBDIn.setValue("idemisor",idemisor);
        	desCosteBDIn.setValue("tipooper",tipooper);
        	desCosteBDIn.setValue("mcactivo","S");
        	ListDesgloseCostesBD desCosteBD = new ListDesgloseCostesBD(desCosteBDIn);
        	desCosteBD.setConnection(con);
        	gddscost = desCosteBD.execSelect();
			
			output.setValue("idemisor", idemisor);
			output.setValue("idunicox", idunicox);
			output.setValue("tipooper", tipooper);
        	output.setGrid("gdcdcost", gdcdcost);
        	output.setGrid("gddscost", gddscost);
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
    public boolean actualizarCampo(String codedeta, String valdetal){
    	
    	boolean actualiz = false;
    	
    	if (((valdetal != null) && !(valdetal.equals("")) && !(hmDetall.containsKey(codedeta))) || (hmDetall.containsKey(codedeta) && !(hmDetall.get(codedeta).equals(valdetal)))){
    		actualiz = true;
    	}
    	
    	return actualiz;
    	
    }
    
    public void recuperaCodigosDetalle(String idemisor, String idunicox){
    	
    	try {
    		
    		ListDesgloseDetalleBDIn codDetalleBDIn = new ListDesgloseDetalleBDIn();
    		codDetalleBDIn.setValue("idemisor",idemisor);
    		codDetalleBDIn.setValue("idunicox",idunicox);
    		ListDesgloseDetalleBD codDetalleBD = new ListDesgloseDetalleBD(codDetalleBDIn);
        	codDetalleBD.setConnection(con);
			Grid gdDetall = codDetalleBD.execSelect();
			
			for (int i = 0; i < gdDetall.rowCount(); i++){
				hmDetall.put(gdDetall.getStringCell(i, "codedeta"),gdDetall.getStringCell(i, "desvalue"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
}
	