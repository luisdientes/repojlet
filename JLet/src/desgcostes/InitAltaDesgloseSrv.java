package desgcostes;


import java.util.ArrayList;

import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import desgcostes.bd.ListCodigoCostesBD;
import desgcostes.bd.ListCodigoCostesBDIn;
import desgcostes.bd.ListDesgloseCostesBD;
import desgcostes.bd.ListDesgloseCostesBDIn;
import desgcostes.bd.ListDesgloseDetalleBD;
import desgcostes.bd.ListDesgloseDetalleBDIn;


public class InitAltaDesgloseSrv extends Service {

	ArrayList<String> arcdcost = new ArrayList<String>();
	
	public InitAltaDesgloseSrv() {
		
		
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("idunicox");
			input.addVariable("idemisor");
			input.addVariable("tipooper");
			
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
			output.addVariable("gddsdeta");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idunicox = "";
    	String idemisor = "";
    	String tipooper = "";

        //Varibales de salida
        Grid gdcdcost = null;
        Grid gddscost = null;
        Grid gddsdeta = null;
    	
        //Otras Variables
        
        
        try {
        	
        	idunicox = input.getStringValue("idunicox");
        	idemisor = input.getStringValue("idemisor");
        	tipooper = input.getStringValue("tipooper");

        	if ((tipooper == null) || (tipooper.equals(""))){
        		tipooper = "C";
        	}
        	
        	//idunicox = "PRUEBA0011";
        	//idunicox = "353942042195509";
        	
        	ListCodigoCostesBDIn codCosteBDIn = new ListCodigoCostesBDIn();
        	codCosteBDIn.setValue("mcactivo","S");
        	codCosteBDIn.setValue("tipooper",tipooper);
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
        	
        	ListDesgloseDetalleBDIn venDetalleBDIn = new ListDesgloseDetalleBDIn();
        	venDetalleBDIn.setValue("idunicox",idunicox);
        	venDetalleBDIn.setValue("idemisor",idemisor);
        	ListDesgloseDetalleBD venDetalleBD = new ListDesgloseDetalleBD(venDetalleBDIn);
        	venDetalleBD.setConnection(con);
        	gddsdeta = venDetalleBD.execSelect();
        	
        	output.setValue("idemisor", idemisor);
        	output.setValue("idunicox", idunicox);
        	output.setValue("tipooper", tipooper);
        	output.setGrid("gdcdcost", gdcdcost);
        	output.setGrid("gddscost", gddscost);
        	output.setGrid("gddsdeta", gddsdeta);
        	
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
}
	 