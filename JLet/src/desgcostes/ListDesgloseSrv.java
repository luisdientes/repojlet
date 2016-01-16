package desgcostes;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import common.constructors.xls.BasicExcel;
import common.constructors.xls.ExcelDesgloseSrv;
import common.varstatic.VariablesStatic;

import desgcostes.bd.ListDesgloseBD;
import desgcostes.bd.ListDesgloseBDIn;

public class ListDesgloseSrv extends Service {
	
	  BasicExcel creador = null;
	  String filename = "";
	  String txcuenta = "";
	  String numeroid = "";
	  String cddivisa = "";
	  String divsimul = "";
	  String idemisor = "";
	  String fhdesdex = "";
  	  String fhhastax = "";
  	  String tipocons = "";
  	  String fhdesmys = "";
  	  String fhhasmys = "";
  	  String txpaisxx = "";
  	  String canalven = "";
  	  String filepdfx = "";
  	  double benebrut = 0;
  	  double benefnet = 0;
	  int contador = 0;
	  
	  Grid gdMedias = null;
	  
	  DecimalFormat formatDivi = new DecimalFormat("###,##0.00");

	  public ListDesgloseSrv() {
		  super();
	  }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("idemisor");
			input.addVariable("txpaisxx");
			input.addVariable("canalven");
			input.addVariable("fhdesdex");
			input.addVariable("fhhastax");
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
			output.addVariable("txpaisxx");
			output.addVariable("canalven");
			output.addVariable("fhdesdex");
			output.addVariable("fhhastax");
			output.addVariable("tipocons");
			output.addVariable("filename");
			output.addVariable("filepdfx");
			output.addVariable("cddivisa");
			output.addVariable("gdDesglo");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	
    	
    	String fhdesmys 	= "";
    	String fhhasmys 	= "";
    
        //Varibales de salida
    	Grid gdDesglo 	= null;
    	Grid gdApuntes 	= null;
    	Grid gridDivi = null;
        
        //Otras Variables
        
        try {
        	
        	idemisor = input.getStringValue("idemisor");
        	txpaisxx = input.getStringValue("txpaisxx");
        	canalven = input.getStringValue("canalven");
        	fhdesdex = input.getStringValue("fhdesdex");
        	fhhastax = input.getStringValue("fhhastax");
        	tipocons = input.getStringValue("tipocons");
        	
        	cddivisa = VariablesStatic.getDivisa(idemisor);
        	divsimul = VariablesStatic.getDivisaSim(idemisor);
        	
        	try{
        		
        		ListDesgloseBDIn listDesgBdin = new ListDesgloseBDIn();
        		listDesgBdin.setValue("idemisor", idemisor);
        		listDesgBdin.setValue("fhdesdex", fhdesdex);
        		listDesgBdin.setValue("fhhastax", fhhastax);
        		listDesgBdin.setValue("canalven", canalven);
        		listDesgBdin.setValue("txpaisxx", txpaisxx);
        		ListDesgloseBD listDesgBd = new ListDesgloseBD(listDesgBdin);
        		listDesgBd.setConnection(con);
        		gdDesglo = listDesgBd.execSelect();
        		
        		if ((1 == 1) || ((tipocons != null) && (tipocons.equals("XLS")))){
	    			generacionExcel(idemisor, gdDesglo);
	        		generacionExcelIva(idemisor, gdDesglo);
        		}
    			
        		if ((1 == 1) || ((tipocons != null) && (tipocons.equals("PDF")))){
        			calculaResumenPDF(gdDesglo);
        			generacionPdf(gdDesglo);
        		}
            	
        	} catch (Exception e) {
        		System.err.println(this.getClass().getName() +" [ERROR ] Recuperando el desglose de costes. "+ e.getMessage());
        	}
        	
        	output.setValue("idemisor", idemisor);
        	output.setValue("txpaisxx", txpaisxx);
        	output.setValue("canalven", canalven);
        	output.setValue("fhdesdex", fhdesdex);
        	output.setValue("fhhastax", fhhastax);
        	output.setValue("tipocons", tipocons);
        	output.setValue("filename", filename);
        	output.setValue("filepdfx", filepdfx);
        	output.setValue("cddivisa", divsimul);
        	
        	output.setValue("gdDesglo", gdDesglo);
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
    

    public void generacionExcel(String idemisor, Grid gdApunte) {
    	
    	
    	
        Grid gdValore = new Grid();
        gdValore.addColumn("cabcampo");
        gdValore.addColumn("cdcampox");
        gdValore.addColumn("anchocam");
        gdValore.addColumn("tipocamp");
        gdValore.addColumn("alincamp");
       
        ArrayList<String> rowvalor = new ArrayList<String>();
        rowvalor.add("IMEI");
        rowvalor.add("idunicox");
        rowvalor.add("24");
        rowvalor.add("S");
        rowvalor.add("I");
        gdValore.addRow(rowvalor);
       
        rowvalor = new ArrayList<String>();
        rowvalor.add("MARCA");
        rowvalor.add("txmarcax");
        rowvalor.add("25");
        rowvalor.add("S");
        rowvalor.add("I");
        gdValore.addRow(rowvalor);
       
        
        rowvalor = new ArrayList<String>();
        rowvalor.add("MODELO");
        rowvalor.add("txmodelo");
        rowvalor.add("30");
        rowvalor.add("S");
        rowvalor.add("I");
        gdValore.addRow(rowvalor);
        
        rowvalor = new ArrayList<String>();
        rowvalor.add("CLASE");
        rowvalor.add("idcatego");
        rowvalor.add("15");
        rowvalor.add("S");
        rowvalor.add("I");
        gdValore.addRow(rowvalor);
        
       
        
        rowvalor = new ArrayList<String>();
        rowvalor.add("CANAL VENTA");
        rowvalor.add("canalven");
        rowvalor.add("50");
        rowvalor.add("S");
        rowvalor.add("I");
        gdValore.addRow(rowvalor);
        
        rowvalor = new ArrayList<String>();
        rowvalor.add("País Destino");
        rowvalor.add("txpaisxx");
        rowvalor.add("15");
        rowvalor.add("S");
        rowvalor.add("I");
        gdValore.addRow(rowvalor);
        
        rowvalor = new ArrayList<String>();
        rowvalor.add("Fecha venta");
        rowvalor.add("fhventax");
        rowvalor.add("15");
        rowvalor.add("S");
        rowvalor.add("C");
        gdValore.addRow(rowvalor);
        
        rowvalor = new ArrayList<String>();
        rowvalor.add("Aduana "+cddivisa);
        rowvalor.add("catotalx");
        rowvalor.add("20");
        rowvalor.add("2D");
        rowvalor.add("D");
        
        gdValore.addRow(rowvalor);
        rowvalor = new ArrayList<String>();
        rowvalor.add("Producto "+cddivisa);
        rowvalor.add("cptotalx");
        rowvalor.add("20");
        rowvalor.add("2D");
        rowvalor.add("D");
        
        gdValore.addRow(rowvalor);
        rowvalor = new ArrayList<String>();
        rowvalor.add("Transporte "+cddivisa);
        rowvalor.add("cttotalx");
        rowvalor.add("20");
        rowvalor.add("2D");
        rowvalor.add("D");
        gdValore.addRow(rowvalor);
        
        
        rowvalor = new ArrayList<String>();
        rowvalor.add("Aduana "+cddivisa);
        rowvalor.add("vatotalx");
        rowvalor.add("20");
        rowvalor.add("2D");
        rowvalor.add("D");
        
        gdValore.addRow(rowvalor);
        rowvalor = new ArrayList<String>();
        rowvalor.add("Producto "+cddivisa);
        rowvalor.add("vptotalx");
        rowvalor.add("20");
        rowvalor.add("2D");
        rowvalor.add("D");
        
        gdValore.addRow(rowvalor);
        rowvalor = new ArrayList<String>();
        rowvalor.add("Transporte "+cddivisa);
        rowvalor.add("vttotalx");
        rowvalor.add("20");
        rowvalor.add("2D");
        rowvalor.add("D");
        gdValore.addRow(rowvalor);
        rowvalor = new ArrayList<String>();
        rowvalor.add("Ingreso "+cddivisa);
        rowvalor.add("igtotalx");
        rowvalor.add("20");
        rowvalor.add("2D");
        rowvalor.add("D");
        gdValore.addRow(rowvalor);
        
        rowvalor = new ArrayList<String>();
        rowvalor.add(" ");
        rowvalor.add("");
        rowvalor.add("20");
        rowvalor.add("S");
        rowvalor.add("D");
        gdValore.addRow(rowvalor);
        
        rowvalor = new ArrayList<String>();
        rowvalor.add("Coste Total "+cddivisa);
        rowvalor.add("costtota");
        rowvalor.add("20");
        rowvalor.add("2D");
        rowvalor.add("D");
        gdValore.addRow(rowvalor);
        
        rowvalor = new ArrayList<String>();
        rowvalor.add("Beneficio "+cddivisa);
        rowvalor.add("benefici");
        rowvalor.add("20");
        rowvalor.add("2D");
        rowvalor.add("D");
        gdValore.addRow(rowvalor);
        
        rowvalor = new ArrayList<String>();
        rowvalor.add("% Beneficio");
        rowvalor.add("porcbene");
        rowvalor.add("20");
        rowvalor.add("2P");
        rowvalor.add("D");
        gdValore.addRow(rowvalor);
       
        
        String txemisor = VariablesStatic.getEmisor(idemisor, "0");
        
        Grid gdParame = new Grid();
        gdParame.addColumn("nomparam");
        gdParame.addColumn("valparam");
        ArrayList<String> rowparam = new ArrayList<String>();
        rowparam.add("Empresa");
        rowparam.add(txemisor);
        gdParame.addRow(rowparam);
        
        
        
        ExcelDesgloseSrv genExcelSrv = new ExcelDesgloseSrv();
        ObjectIO inputXLS = genExcelSrv.instanceOfInput();
        ObjectIO outputXLS = genExcelSrv.instanceOfOutput();
        try {
            inputXLS.setValue("username", "APUNTE");
            inputXLS.setValue("idemisor", idemisor);
            inputXLS.setValue("filename", filename);
            inputXLS.setValue("hojaname","Costes sin IVA");
            inputXLS.setValue("gdParame", gdParame);
            inputXLS.setValue("gdValore", gdValore);
            inputXLS.setValue("gdResult", gdApunte);
            inputXLS.setValue("creador", creador);
            genExcelSrv.setConnection(this.getConnection());
            genExcelSrv.process(inputXLS, outputXLS);
            filename = outputXLS.getStringValue("filecrea");
            creador =  (BasicExcel) outputXLS.getValue("creador");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
	@SuppressWarnings("unchecked")
	public void generacionExcelIva(String idemisor, Grid gdApunte) {
	    	
	    	
	    	
	        Grid gdValore = new Grid();
	        gdValore.addColumn("cabcampo");
	        gdValore.addColumn("cdcampox");
	        gdValore.addColumn("anchocam");
	        gdValore.addColumn("tipocamp");
	        gdValore.addColumn("alincamp");
		
	
	       
	        ArrayList<String> rowvalor = new ArrayList<String>();
	        rowvalor.add("IMEI");
	        rowvalor.add("idunicox");
	        rowvalor.add("24");
	        rowvalor.add("S");
	        rowvalor.add("I");
	        gdValore.addRow(rowvalor);
	       
	        rowvalor = new ArrayList<String>();
	        rowvalor.add("MARCA");
	        rowvalor.add("txmarcax");
	        rowvalor.add("25");
	        rowvalor.add("S");
	        rowvalor.add("I");
	        gdValore.addRow(rowvalor);
	       
	        
	        rowvalor = new ArrayList<String>();
	        rowvalor.add("MODELO");
	        rowvalor.add("txmodelo");
	        rowvalor.add("30");
	        rowvalor.add("S");
	        rowvalor.add("I");
	        gdValore.addRow(rowvalor);
	        
	        rowvalor = new ArrayList<String>();
	        rowvalor.add("CLASE");
	        rowvalor.add("idcatego");
	        rowvalor.add("15");
	        rowvalor.add("S");
	        rowvalor.add("I");
	        gdValore.addRow(rowvalor);
	        
	       
	        
	        rowvalor = new ArrayList<String>();
	        rowvalor.add("CANAL VENTA");
	        rowvalor.add("canalven");
	        rowvalor.add("50");
	        rowvalor.add("S");
	        rowvalor.add("I");
	        gdValore.addRow(rowvalor);
	        
	        rowvalor = new ArrayList<String>();
	        rowvalor.add("País Destino");
	        rowvalor.add("txpaisxx");
	        rowvalor.add("15");
	        rowvalor.add("S");
	        rowvalor.add("I");
	        gdValore.addRow(rowvalor);
	        
	        rowvalor = new ArrayList<String>();
	        rowvalor.add("Fecha venta");
	        rowvalor.add("fhventax");
	        rowvalor.add("15");
	        rowvalor.add("S");
	        rowvalor.add("C");
	        gdValore.addRow(rowvalor);
	        
	        rowvalor = new ArrayList<String>();
	        rowvalor.add("Aduana "+cddivisa);
	        rowvalor.add("catotalx");
	        rowvalor.add("20");
	        rowvalor.add("2D");
	        rowvalor.add("D");
	        gdValore.addRow(rowvalor);
	        
	        rowvalor = new ArrayList<String>();
	        rowvalor.add("Aduana IVA "+cddivisa);
	        rowvalor.add("caivatot");
	        rowvalor.add("20");
	        rowvalor.add("2D");
	        rowvalor.add("D");
	        gdValore.addRow(rowvalor);
	        
	        rowvalor = new ArrayList<String>();
	        rowvalor.add("Producto "+cddivisa);
	        rowvalor.add("cptotalx");
	        rowvalor.add("20");
	        rowvalor.add("2D");
	        rowvalor.add("D");
	        gdValore.addRow(rowvalor);
	        
	        rowvalor = new ArrayList<String>();
	        rowvalor.add("Producto IVA "+cddivisa);
	        rowvalor.add("cpivatot");
	        rowvalor.add("20");
	        rowvalor.add("2D");
	        rowvalor.add("D");
	        gdValore.addRow(rowvalor);
	        
	        rowvalor = new ArrayList<String>();
	        rowvalor.add("Transporte "+cddivisa);
	        rowvalor.add("cttotalx");
	        rowvalor.add("20");
	        rowvalor.add("2D");
	        rowvalor.add("D");
	        gdValore.addRow(rowvalor);
	        
	        rowvalor = new ArrayList<String>();
	        rowvalor.add("Transporte IVA "+cddivisa);
	        rowvalor.add("ctivatot");
	        rowvalor.add("20");
	        rowvalor.add("2D");
	        rowvalor.add("D");
	        gdValore.addRow(rowvalor);
	    
	        rowvalor = new ArrayList<String>();
	        rowvalor.add("Aduana "+cddivisa);
	        rowvalor.add("vatotalx");
	        rowvalor.add("20");
	        rowvalor.add("2D");
	        rowvalor.add("D");
	        gdValore.addRow(rowvalor);
	        
	        rowvalor = new ArrayList<String>();
	        rowvalor.add("Aduana IVA "+cddivisa);
	        rowvalor.add("vaivatot");
	        rowvalor.add("20");
	        rowvalor.add("2D");
	        rowvalor.add("D");
	        gdValore.addRow(rowvalor);
	        
	        rowvalor = new ArrayList<String>();
	        rowvalor.add("Producto "+cddivisa);
	        rowvalor.add("vptotalx");
	        rowvalor.add("20");
	        rowvalor.add("2D");
	        rowvalor.add("D");
	        gdValore.addRow(rowvalor);
	        
	        rowvalor = new ArrayList<String>();
	        rowvalor.add("Producto IVA "+cddivisa);
	        rowvalor.add("vpivatot");
	        rowvalor.add("20");
	        rowvalor.add("2D");
	        rowvalor.add("D");
	        gdValore.addRow(rowvalor);
	        
	        rowvalor = new ArrayList<String>();
	        rowvalor.add("Transporte "+cddivisa);
	        rowvalor.add("vttotalx");
	        rowvalor.add("20");
	        rowvalor.add("2D");
	        rowvalor.add("D");
	        gdValore.addRow(rowvalor);
	        
	        rowvalor = new ArrayList<String>();
	        rowvalor.add("Transporte IVA "+cddivisa);
	        rowvalor.add("vtivatot");
	        rowvalor.add("20");
	        rowvalor.add("2D");
	        rowvalor.add("D");
	        gdValore.addRow(rowvalor);
	        
	        rowvalor = new ArrayList<String>();
	        rowvalor.add("Ingreso "+cddivisa);
	        rowvalor.add("igtotalx");
	        rowvalor.add("20");
	        rowvalor.add("2D");
	        rowvalor.add("D");
	        gdValore.addRow(rowvalor);
	        
	        rowvalor = new ArrayList<String>();
	        rowvalor.add("Ingreso IVA "+cddivisa);
	        rowvalor.add("igivatot");
	        rowvalor.add("20");
	        rowvalor.add("2D");
	        rowvalor.add("D");
	        gdValore.addRow(rowvalor);
	
	        rowvalor = new ArrayList<String>();
	        rowvalor.add(" ");
	        rowvalor.add("");
	        rowvalor.add("20");
	        rowvalor.add("S");
	        rowvalor.add("D");
	        gdValore.addRow(rowvalor);
	        
	        rowvalor = new ArrayList<String>();
	        rowvalor.add("Coste Total "+cddivisa);
	        rowvalor.add("costtota");
	        rowvalor.add("20");
	        rowvalor.add("2D");
	        rowvalor.add("D");
	        gdValore.addRow(rowvalor);
	        
	        rowvalor = new ArrayList<String>();
	        rowvalor.add("Beneficio "+cddivisa);
	        rowvalor.add("benefici");
	        rowvalor.add("20");
	        rowvalor.add("2D");
	        rowvalor.add("D");
	        gdValore.addRow(rowvalor);
	        
	        rowvalor = new ArrayList<String>();
	        rowvalor.add("% Beneficio");
	        rowvalor.add("porcbene");
	        rowvalor.add("20");
	        rowvalor.add("2P");
	        rowvalor.add("D");
	        gdValore.addRow(rowvalor);
	       
	        
	        String txemisor = VariablesStatic.getEmisor(idemisor, "0");
	        
	        Grid gdParame = new Grid();
	        gdParame.addColumn("nomparam");
	        gdParame.addColumn("valparam");
	        ArrayList<String> rowparam = new ArrayList<String>();
	        rowparam.add("Empresa");
	        rowparam.add(txemisor);
	        gdParame.addRow(rowparam);
	        
	        ExcelDesgloseSrv genExcelSrv = new ExcelDesgloseSrv();
	        ObjectIO inputXLS = genExcelSrv.instanceOfInput();
	        ObjectIO outputXLS = genExcelSrv.instanceOfOutput();
	        try {
	            inputXLS.setValue("username", "APUNTE");
	            inputXLS.setValue("idemisor", idemisor);
	            inputXLS.setValue("filename", filename);
	            inputXLS.setValue("hojaname","Costes con IVA");
	            inputXLS.setValue("gdParame", gdParame);
	            inputXLS.setValue("gdValore", gdValore);
	            inputXLS.setValue("gdResult", gdApunte);
	            inputXLS.setValue("creador", creador);
	            genExcelSrv.setConnection(this.getConnection());
	            genExcelSrv.process(inputXLS, outputXLS);
	            filename = outputXLS.getStringValue("filecrea");
	            creador =  (BasicExcel) outputXLS.getValue("creador");
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
	
	public void generacionPdf( Grid gdDImeis){
		
		String mostriva = "S";
		
		ObjectIO input 	= null;
		ObjectIO output = null;
		
		ArrayList<String> arInform = new ArrayList<String>(); 
		arInform.add("DESGLOSE_"+idemisor);
		 String txemisor = VariablesStatic.getEmisor(idemisor, "0");
		
		for (int i = 0; i < arInform.size(); i++){
			
			Service srv = new PdfDesgloseSrv();
			
			input  = srv.instanceOfInput();
			output = srv.instanceOfOutput();
			
			String idunicox = dameIMEIVendidos(gdDImeis);
			
			try {
				
				input.setValue("idemisor", idemisor);
				input.setValue("idunicox", idunicox);
				input.setValue("mostriva", mostriva);
				input.setValue("txemisor", txemisor);
				input.setValue("ingrtota", formatDivi.format(benefnet));
				input.setValue("benebrut", formatDivi.format(benebrut));
				
				
				input.setValue("fhdesdex", fhdesdex);
				input.setValue("fhhastax", fhhastax);
				input.setValue("txpaisxx", txpaisxx);
				input.setValue("txcanalx", canalven);
				
				input.setValue("codinfor", arInform.get(i));
				input.setGrid("gdMedias", gdMedias);
				
				srv.setConnection(con);
				srv.process(input, output);
				
				filepdfx = output.getStringValue("filecrea");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void calculaResumenPDF(Grid gdImeiSl){
		
		double catotalx = 0;
		double cptotalx = 0;
		double cttotalx = 0;
		double vatotalx = 0;
		double vptotalx = 0;
		double vttotalx = 0;
		double igtotalx = 0;
		double caivatot = 0;
		double cpivatot = 0;
		double ctivatot = 0;
		double vaivatot = 0;
		double vpivatot = 0;
		double vtivatot = 0;
		double igivatot = 0;
		
		
		double camediax = 0;
		double cpmediax = 0;
		double ctmediax = 0;
		double vamediax = 0;
		double vpmediax = 0;
		double vtmediax = 0;
		double igmediax = 0;
		double caivamed = 0;
		double cpivamed = 0;
		double ctivamed = 0;
		double vaivamed = 0;
		double vpivamed = 0;
		double vtivamed = 0;
		double igivamed = 0;
		
		
		int contIMEI = gdImeiSl.rowCount();
		
		if (contIMEI > 1) {
		
			for (int i = 0; i < gdImeiSl.rowCount(); i++){
				catotalx += Double.parseDouble(gdImeiSl.getStringCell(i,"catotalx"));
				cptotalx += Double.parseDouble(gdImeiSl.getStringCell(i,"cptotalx"));
				cttotalx += Double.parseDouble(gdImeiSl.getStringCell(i,"cttotalx"));
				vatotalx += Double.parseDouble(gdImeiSl.getStringCell(i,"vatotalx"));
				vptotalx += Double.parseDouble(gdImeiSl.getStringCell(i,"vptotalx"));
				vttotalx += Double.parseDouble(gdImeiSl.getStringCell(i,"vttotalx"));
				igtotalx += Double.parseDouble(gdImeiSl.getStringCell(i,"igtotalx"));
				caivatot += Double.parseDouble(gdImeiSl.getStringCell(i,"caivatot"));
				cpivatot += Double.parseDouble(gdImeiSl.getStringCell(i,"cpivatot"));
				ctivatot += Double.parseDouble(gdImeiSl.getStringCell(i,"ctivatot"));
				vaivatot += Double.parseDouble(gdImeiSl.getStringCell(i,"vaivatot"));
				vpivatot += Double.parseDouble(gdImeiSl.getStringCell(i,"vpivatot"));
				vtivatot += Double.parseDouble(gdImeiSl.getStringCell(i,"vtivatot"));
				igivatot += Double.parseDouble(gdImeiSl.getStringCell(i,"igivatot"));
			}
			
			camediax = catotalx / contIMEI;
			cpmediax = cptotalx / contIMEI;
			ctmediax = cttotalx / contIMEI;
			vamediax = vatotalx / contIMEI;
			vpmediax = vptotalx / contIMEI;
			vtmediax = vttotalx / contIMEI;
			igmediax = igtotalx / contIMEI;

			caivamed = caivatot / contIMEI;
			cpivamed = cpivatot / contIMEI;
			ctivamed = ctivatot / contIMEI;
			vaivamed = vaivatot / contIMEI;
			vpivamed = vpivatot / contIMEI;
			vtivamed = vtivatot / contIMEI;
			igivamed = igivatot / contIMEI;
			
			gdMedias = new Grid();
			gdMedias.addColumn("camediax");
			gdMedias.addColumn("caivamed");
			gdMedias.addColumn("cpmediax");
			gdMedias.addColumn("cpivamed");
			gdMedias.addColumn("ctmediax");
			gdMedias.addColumn("ctivamed");
			gdMedias.addColumn("vamediax");
			gdMedias.addColumn("vaivamed");
			gdMedias.addColumn("vpmediax");
			gdMedias.addColumn("vpivamed");
			gdMedias.addColumn("vtmediax");
			gdMedias.addColumn("vtivamed");
			gdMedias.addColumn("igmediax");
			gdMedias.addColumn("igivamed");
			
			ArrayList<String> rowMedia = new ArrayList<String>();
			rowMedia.add(formatDivi.format(camediax));
			rowMedia.add(formatDivi.format(caivamed));
			rowMedia.add(formatDivi.format(cpmediax));
			rowMedia.add(formatDivi.format(cpivamed));
			rowMedia.add(formatDivi.format(ctmediax));
			rowMedia.add(formatDivi.format(ctivamed));
			rowMedia.add(formatDivi.format(vamediax));
			rowMedia.add(formatDivi.format(vaivamed));
			rowMedia.add(formatDivi.format(vpmediax));
			rowMedia.add(formatDivi.format(vpivamed));
			rowMedia.add(formatDivi.format(vtmediax));
			rowMedia.add(formatDivi.format(vtivamed));
			rowMedia.add(formatDivi.format(igmediax));
			rowMedia.add(formatDivi.format(igivamed));
			
			gdMedias.addRow(rowMedia);
			
		
			
			benefnet = igtotalx - catotalx - cptotalx - cttotalx - vatotalx - vptotalx - vttotalx;
			benebrut = (igtotalx + igivatot) - ((catotalx + cptotalx + cttotalx + caivatot + cpivatot + ctivatot) + (vatotalx + vptotalx + vttotalx + vaivatot + vpivatot + vtivatot));
			
			
		}
		
	}
	
	public  String dameIMEIVendidos (Grid gdIMEIls) {
		
		String lsimeixx = "";
	
		try {
			/*
			ListDesgloseBDIn listDesgBdin = new ListDesgloseBDIn();
			listDesgBdin.setValue("idemisor", idemisor);
			listDesgBdin.setValue("fhdesdex", fhdesdex);
			listDesgBdin.setValue("fhhastax", fhhastax);
			listDesgBdin.setValue("canalven", canalven);
			listDesgBdin.setValue("txpaisxx", txpaisxx);
			ListDesgloseBD listDesgBd = new ListDesgloseBD(listDesgBdin);
			listDesgBd.setConnection(con);*/
			//Grid gdIMEIls = listDesgBd.execSelect();
			/*
			ListIMEIFacturadoBDIn lisIMEIBDIn =	 new ListIMEIFacturadoBDIn();
			lisIMEIBDIn.setValue("idemisor",idemisor);
			lisIMEIBDIn.setValue("fhdesdex",fhdesdex);
			lisIMEIBDIn.setValue("fhhastax",fhhastax);
			ListIMEIFacturadoBD desDetalleBD = new ListIMEIFacturadoBD(lisIMEIBDIn);
	    	desDetalleBD.setConnection(con);
	    	Grid gdIMEIls = desDetalleBD.execSelect();
	    	*/
	    	for (int i = 0; i < gdIMEIls.rowCount(); i++){
	    		
	    		if (!gdIMEIls.getStringCell(i, "idunicox").equals("")){
	    			
	    			if (contador > 0){
		    			lsimeixx += ",";
		    		}
	    			
	    			lsimeixx += gdIMEIls.getStringCell(i, "idunicox"); 
	    			contador++;
	    		}
	    		
	    	}
	    	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    	
		return lsimeixx;
		
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
	 
	 public String FechaMesAnterior(){
		 
		  Calendar c1 = GregorianCalendar.getInstance();
	      SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMMM/yyyy hh:mm:ss");
	      sdf = new SimpleDateFormat("dd/MM/yyyy");
	      c1.add(Calendar.MONTH, -6);
	      
	      return sdf.format(c1.getTime());
	 }
	 
	 
	 public void calculoBeneficios(){
		 
	 }
    
    
    
}
	