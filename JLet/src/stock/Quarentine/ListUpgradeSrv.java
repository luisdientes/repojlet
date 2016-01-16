package stock;



import common.bd.ListDivEmisorBD;
import common.bd.ListDivEmisorBDIn;
import stock.bd.ListUpgradeBD;
import stock.bd.ListUpgradeBDIn;
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
				
				
				ListDivEmisorBDIn divisaBDIn= new ListDivEmisorBDIn();
				divisaBDIn.setValue("idemisor", idemisor);
				ListDivEmisorBD divisaBD = new ListDivEmisorBD(divisaBDIn);
				divisaBD.setConnection(con);
				gridDivi = divisaBD.execSelect();
				
				cddivisa = gridDivi.getStringCell(0, "divsimbol");
				
	
	        } catch (Exception e) {
				System.err.println(this.getClass().getName() +" ERROR recogiendo productos. "+ e.getMessage());
			}
    
        	output.setValue("idemisor", idemisor);
		    output.setValue("gridUpgr", gridUpgr);
		    output.setValue("txmensaj", "OK");

        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
}
	