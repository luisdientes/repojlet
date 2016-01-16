package stock;


import java.util.logging.Logger;

import stock.bd.UpdCodVentasBD;
import stock.bd.UpdCodVentasBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import common.bd.ListDivEmisorBD;
import common.bd.ListDivEmisorBDIn;
import common.bd.ListMarcasBD;
import common.bd.ListMarcasBDIn;
import common.varstatic.VariablesStatic;


public class ListCodVentasSrv extends Service {

    public ListCodVentasSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();	
			input.addVariable("idemisor");
			input.addVariable("codprodu");
			input.addVariable("txmarcax");
			input.addVariable("txdescri");
			input.addVariable("impdefve");
			input.addVariable("cantidad");
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
			output.addVariable("txmarcax");
			output.addVariable("cddivisa");
			output.addVariable("gridCodi");
			output.addVariable("gridMarc");
			
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor = null;
    	String txdescri = null;
    	String txmarcax = null;
    	String cddivisa = null;
    	Grid gridCodi = null;
    	Grid gridMarc = null;
    	Grid gridDivi = null;
    	
        try {
        	
        	idemisor = input.getStringValue("idemisor");
        	txdescri = input.getStringValue("txdescri");
        	txmarcax = input.getStringValue("txmarcax");
	        try {
	        	 
	        	UpdCodVentasBDIn lineasBDIn= new UpdCodVentasBDIn();
	        	lineasBDIn.setValue("idemisor", idemisor);
	        	lineasBDIn.setValue("txdescri", txdescri);
	        	lineasBDIn.setValue("txmarcax", txmarcax);	        	
	        	UpdCodVentasBD lineasBD = new UpdCodVentasBD(lineasBDIn);
				lineasBD.setConnection(con);
				gridCodi = lineasBD.execSelect();
				
	        	try {
	        		gridMarc = VariablesStatic.getMarcasEmisor(idemisor);
	        	} catch (Exception e) {
	        		Logger.getLogger("JLet").warning(this.getClass().getName() +" Error recuperando las marcas de VariablesStatic");
	        	}
	        	
	        	if (gridMarc == null) {
		        	ListMarcasBDIn listMarcaBDIn  = new ListMarcasBDIn();  
		        	//listMarcaBDIn.setValue("idemisor", idemisor);
		        	ListMarcasBD listMarcasBD = new ListMarcasBD(listMarcaBDIn);
		        	listMarcasBD.setConnection(con);
		        	gridMarc= listMarcasBD.execSelect();
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
        	output.setValue("txmarcax", txmarcax);
		    output.setValue("gridCodi", gridCodi);
		    output.setValue("gridMarc", gridMarc);
		    output.setValue("txmensaj", "OK");

        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
}
	