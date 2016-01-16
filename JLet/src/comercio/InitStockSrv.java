package comercio;


import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import comercio.bd.ListColoresBD;
import comercio.bd.ListColoresBDIn;
import comercio.bd.ListFixingBD;
import comercio.bd.ListFixingBDIn;
import comercio.bd.ListStockAgrupadoBD;
import comercio.bd.ListStockBD;
import comercio.bd.ListStockBDIn;
import common.bd.ListDivEmisorBD;
import common.bd.ListDivEmisorBDIn;


public class InitStockSrv extends Service {

    public InitStockSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();	
			input.addVariable("cdpantal");
			input.addVariable("idemisor");
			input.addVariable("tpclient");
			input.addVariable("imeicode");
			input.addVariable("cdestado");
			input.addVariable("tipocons");

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
			output.addVariable("cddivisa");
			output.addVariable("divsimbo");
			output.addVariable("fixingxx");
			output.addVariable("fhfixing");
			output.addVariable("gridLine");
			output.addVariable("gridColo");
			output.addVariable("txmensaj");
			output.addVariable("tpclient");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor = null;
    	String tpclient = null;
    	String imeicode = null;
    	String cdestado = null;
    	String tipocons = null;
        
        //Varibales de salida
    	String cddivisa = null;
    	String divsimbo = null;
    	String fixingxx = null;
    	String fhfixing = null;
    	Grid gridLine 	= null;
    	Grid gridColo 	= null;
    	String cdpantal = null;
    	
        //Otras Variables

    	try {
        	
        	cdpantal = input.getStringValue("cdpantal");
        	idemisor = input.getStringValue("idemisor");
        	tpclient = input.getStringValue("tpclient");
        	imeicode = input.getStringValue("imeicode");
        	cdestado = input.getStringValue("cdestado");
        	tipocons = input.getStringValue("tipocons");
        	
        	ListDivEmisorBDIn divEmisorBDIn= new ListDivEmisorBDIn();
        	divEmisorBDIn.setValue("idemisor",idemisor);
        	ListDivEmisorBD divEmisorBD= new ListDivEmisorBD(divEmisorBDIn);
        	divEmisorBD.setConnection(con);
        	Grid divEmis = divEmisorBD.execSelect();
        	
        	cddivisa = divEmis.getStringCell(0,"cddivisa");
        	divsimbo = divEmis.getStringCell(0,"divsimbo");
        	
        	ListFixingBDIn fixingBDIn= new ListFixingBDIn();
        	fixingBDIn.setValue("cddivisa","USD");
        	fixingBDIn.setValue("diviscam",cddivisa);
        	ListFixingBD fixingBD= new ListFixingBD(fixingBDIn);
        	fixingBD.setConnection(con);
        	Grid gdFixing = fixingBD.execSelect();
        	
        	fixingxx = gdFixing.getStringCell(0,"fixingxx");
        	fhfixing = gdFixing.getStringCell(0,"altatime");
        	
	        try {
	        	ListStockBDIn lineasBDIn= new ListStockBDIn();
	        	lineasBDIn.setValue("idemisor",idemisor);
	        	lineasBDIn.setValue("imeicode",imeicode);
	        	lineasBDIn.setValue("cdestado",cdestado);
	        	
	        	
	        	if ((tipocons != null) && (tipocons.equals("agrupado"))){
	        	 	ListStockAgrupadoBD lineasBD = new ListStockAgrupadoBD(lineasBDIn);
					lineasBD.setConnection(con);
					gridLine = lineasBD.execSelect();
	        	} else {
				  	ListStockBD lineasBD = new ListStockBD(lineasBDIn);
					lineasBD.setConnection(con);
					gridLine = lineasBD.execSelect();
	        	}
	        } catch (Exception e) {
				System.err.println(this.getClass().getName() +" ERROR recogiendo lineas. "+ e.getMessage());
			}
        	
			try {
				ListColoresBDIn coloresBDIn = new ListColoresBDIn();
			  	ListColoresBD coloresBD = new ListColoresBD(coloresBDIn);
			  	coloresBD.setConnection(con);
				gridColo = coloresBD.execSelect();
			} catch (Exception e) {
				System.err.println(this.getClass().getName() +" ERROR recogiendo los colores. "+ e.getMessage());
			}
        	
			output.setValue("idemisor", idemisor);
			output.setValue("cddivisa", cddivisa);
			output.setValue("fixingxx", fixingxx);
			output.setValue("fhfixing", fhfixing);
			output.setValue("divsimbo", divsimbo);
			output.setValue("tpclient", tpclient);
			output.setValue("gridLine", gridLine);
		    output.setValue("gridColo", gridColo);
		    output.setValue("txmensaj", "OK");

        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
}
	