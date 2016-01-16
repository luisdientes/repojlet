package stock;

import java.util.ArrayList;
import java.util.logging.Logger;









import excelXLSX.bd.ListClientesBD;
import excelXLSX.bd.ListClientesBDIn;
import excelXLSX.bd.ListPiezasBD;
import excelXLSX.bd.ListPiezasBDIn;
import stock.bd.ListStockBD;
import stock.bd.ListStockBDIn;
import utils.PropiedadesJLet;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

public class ExcelSrv extends Service {

	Grid gdGestor 	 = new Grid();
    Grid gdCompan 	 = new Grid();
    Grid gdResult 	 = new Grid();

	public ObjectIO instanceOfInput() {
		ObjectIO input = null;
		try {
			input = new ObjectIO();	
			input.addVariable("idemisor");
			input.addVariable("listpiec");
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
	
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
		
			output.addVariable("filecrea");
			output.addVariable("pantalla");
			output.addVariable("idemisor");
			output.addVariable("cderror");
			output.addVariable("txerror");

		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    public void process(ObjectIO input, ObjectIO output){
    	
    	//Varibales de entrada
        String consulta  = "XLS";
        String idemisor = "";
        String listpiec = "";
       
        
		//Varibales de salida
        String cderror   = "";
        String txerror   = "";  
       
        
        String txcompan	 = "";
    
   
        String filasxpg  = "300";
        String filecrea  = "";
      

        try {
        	
        	idemisor = input.getStringValue("idemisor");
        	listpiec = input.getStringValue("listpiec");
        	listpiec = listpiec.substring(0, listpiec.length());
			
        	if ((consulta != null) && (consulta.equals("XLS"))) {
			
				Grid gdValore = new Grid();
				gdValore.addColumn("cabcampo");
				gdValore.addColumn("cdcampox");
				gdValore.addColumn("anchocam");
				gdValore.addColumn("altoceld");
				gdValore.addColumn("tipocamp");
				gdValore.addColumn("alincamp");
				
				ArrayList<String> rowvalor = new ArrayList<String>();
				
				rowvalor = new ArrayList<String>();
				rowvalor.add("Imagen");
				rowvalor.add("");
				rowvalor.add("35");
				rowvalor.add("3500");
				rowvalor.add("S");
				rowvalor.add("D");
				gdValore.addRow(rowvalor);
				
				
				rowvalor = new ArrayList<String>();
				rowvalor.add("Descripción");
				rowvalor.add("txdescri");
				rowvalor.add("90");
				rowvalor.add("3500");
				rowvalor.add("S");
				rowvalor.add("I");
				gdValore.addRow(rowvalor);
				
				rowvalor = new ArrayList<String>();
				rowvalor.add("Telefonos compatibles");
				rowvalor.add("namephon");
				rowvalor.add("65");
				rowvalor.add("3500");
				rowvalor.add("S");
				rowvalor.add("I");
				gdValore.addRow(rowvalor);
	
			/*	rowvalor = new ArrayList<String>();
				rowvalor.add("Imagen");
				rowvalor.add("imgdefau");
				rowvalor.add("20");
				rowvalor.add("3900");
				rowvalor.add("S");
				rowvalor.add("D");
				gdValore.addRow(rowvalor);*/
				
			
	
				
				Grid gdParame = new Grid();
				gdParame.addColumn("nomparam");
				gdParame.addColumn("valparam");
				
				ArrayList<String> rowparam = new ArrayList<String>();
				
				/*if ((cdholder != null) && (!cdholder.equals(""))) {
					rowparam = new ArrayList<String>();
					rowparam.add("N. Accionista");
					rowparam.add(cdholder);
					gdParame.addRow(rowparam);
				}
	
			
				if ((cdnifxxx != null) && (!cdnifxxx.equals(""))) {
					rowparam = new ArrayList<String>();
					rowparam.add("NIF");
					rowparam.add(cdnifxxx);
					gdParame.addRow(rowparam);
				}*/
	
				
				
				ListPiezasBDIn lineasBDIn= new ListPiezasBDIn();
	        	lineasBDIn.setValue("idemisor",idemisor);
	        	lineasBDIn.setValue("idpiezas",listpiec);
	        	
	        	ListPiezasBD lineasBD = new ListPiezasBD(lineasBDIn);
			  	lineasBD.setConnection(con);
			  	gdResult = lineasBD.execSelect();	        	
				
				ExcelConsultasSrv genExcelSrv = new ExcelConsultasSrv();
				ObjectIO inputXLS  = genExcelSrv.instanceOfInput();
				ObjectIO outputXLS = genExcelSrv.instanceOfOutput();
	    		
	    		inputXLS.setValue("txcompan", txcompan);
	    		inputXLS.setValue("idemisor", idemisor);
	    		inputXLS.setValue("txpantal", "PIEZAS");
	    		inputXLS.setValue("gdParame", gdParame);
	    		inputXLS.setValue("gdValore", gdValore);
	    		inputXLS.setValue("gdResult", gdResult);
	    		
	    		genExcelSrv.setConnection(this.getConnection());
	    		genExcelSrv.setSesion(sesion);
	    		genExcelSrv.process(inputXLS, outputXLS);
			
	    		filecrea = outputXLS.getStringValue("filecrea");
	    		
	    		//filecrea = "C:/DATOS/tmp/test_excel/"+filecrea;
	    		
			}
			
			cderror = "0";
			txerror = "OK";
			
			output.setValue("idemisor", idemisor);
			output.setValue("txerror", txerror);
			output.setValue("filecrea", filecrea);
			
        } catch(Exception se) {
        	cderror = "1";
        	txerror = "Error al recue perare los valores del INPUT";
			try {
				output.setValue("cderror", cderror);
				output.setValue("txerror", txerror);
			} catch (Exception e) {
	        	Logger.getLogger("LRA").warning(this.getClass().getName() +" Asignando Output valores de ERROR - "+ e.getMessage());
			}
        	Logger.getLogger("LRA").warning(this.getClass().getName() +" Error en el process "+ se.getMessage());
        }   
        
    }


    
}
    