package upgrade;



import java.text.DecimalFormat;

import common.bd.ListDivEmisorBD;
import common.bd.ListDivEmisorBDIn;
import upgrade.bd.InsProdPiezaBD;
import upgrade.bd.InsProdPiezaBDIn;
import upgrade.bd.ListUpgradeBD;
import upgrade.bd.ListUpgradeBDIn;
import upgrade.bd.UpdStockBD;
import upgrade.bd.UpdStockBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;



public class ListUpgradeSrv extends Service {

    public ListUpgradeSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();	
			input.addVariable("idemisor");
			input.addVariable("imeicode");
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
     
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
			output.addVariable("txmensaj");
			output.addVariable("idemisor");
			output.addVariable("cddivisa");	
			output.addVariable("gridUpgr");	
			output.addVariable("gridPiez");	
			output.addVariable("gridLine");	
			
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor = null;
    	String imeicode = null;
    	String cddivisa = null;
    	Grid gridUpgr = null;
    	Grid gridDivi = null;
    	Grid gridPiez = null;
    	Grid gridLine = null;
    	
    	double benefici = 0;
    	double porcbene = 0;

    	DecimalFormat formatDivi = new DecimalFormat("###,##0.00");
    	
    	
        try {
        	
        	idemisor = input.getStringValue("idemisor");
        	imeicode = input.getStringValue("imeicode");
 
	        try {
	        	 
	        	ListUpgradeBDIn lineasBDIn= new ListUpgradeBDIn();
	        	lineasBDIn.setValue("idemisor", idemisor);
	        	lineasBDIn.setValue("imeicode", imeicode);
	
	        	ListUpgradeBD lineasBD = new ListUpgradeBD(lineasBDIn);
				lineasBD.setConnection(con);
				gridUpgr = lineasBD.execSelect();
				
				if(imeicode !=null && !imeicode.equals("")){
					InsProdPiezaBDIn insProPiezBDIn = new InsProdPiezaBDIn();
	        		insProPiezBDIn.setValue("imeiprod", imeicode);
	        		insProPiezBDIn.setValue("cdestado","CONSINT");
	        		InsProdPiezaBD insLinBD = new InsProdPiezaBD(insProPiezBDIn);
					insLinBD.setConnection(con);
					gridPiez = insLinBD.execSelect();
				}

				ListDivEmisorBDIn divisaBDIn= new ListDivEmisorBDIn();
				divisaBDIn.setValue("idemisor", idemisor);
				ListDivEmisorBD divisaBD = new ListDivEmisorBD(divisaBDIn);
				divisaBD.setConnection(con);
				gridDivi = divisaBD.execSelect();
				cddivisa = gridDivi.getStringCell(0, "divsimbo");
	        } catch (Exception e) {
				System.err.println(this.getClass().getName() +" ERROR recogiendo productos. "+ e.getMessage());
			}
    
        	output.setValue("idemisor", idemisor);
        	output.setValue("cddivisa", cddivisa);
		    output.setValue("gridUpgr", gridUpgr);
		    output.setValue("gridPiez", gridPiez);
		    
		    output.setValue("txmensaj", "OK");

        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
}
	