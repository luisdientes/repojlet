package informes;


import graficas.constants.GraficoBarras;
import informes.bd.ListFacturasInformeBD;
import informes.bd.ListFacturasInformeBDIn;

import java.text.DecimalFormat;
import java.util.ArrayList;

import org.jfree.data.category.DefaultCategoryDataset;

import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;


public class ListDetalleInformeFacturasSrv extends Service {

	//Varibales de entrada
	String idemisor 	= null;
	String tipodata 	= null;
	String valordat		= null;
	String fhdesdex 	= null; //al devolver la fecha para mostrar en el input no la pude devolver en formato mysql
	String fhhastax 	= null;
	String aniofact 	= null;
	
	//Variables de salida
    String filename 	= null;
    String cddivisa 	= null;
    Grid gdFactur 		= null;
 
	//Variables de clase
    int numFilas		= 0;
	Grid gridAgru		= null;
	String fhdesdexMy 	= null;
	String fhhastaxMy	= null;
	
	ArrayList<Double>  arBasImp = new ArrayList<Double>();
	ArrayList<Double>  arTaxImp = new ArrayList<Double>();
	ArrayList<Double>  arRetImp = new ArrayList<Double>();
	ArrayList<Double>  arTotImp = new ArrayList<Double>();
	ArrayList<Double>  arPagImp = new ArrayList<Double>();
	ArrayList<Integer> arNumero = new ArrayList<Integer>();
	
	
	DecimalFormat formatEnte = new DecimalFormat("###,##0");
	DecimalFormat formatDivi = new DecimalFormat("###,##0.00");
	
    public ListDetalleInformeFacturasSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();

			input.addVariable("idemisor");
			input.addVariable("tipodata");
			input.addVariable("fhdesdex");
			input.addVariable("fhhastax");
			input.addVariable("aniofact");		
			
						
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
			output.addVariable("aniofact");	
			output.addVariable("filename");		
			output.addVariable("tipodata");	
			output.addVariable("valordat");
			output.addVariable("gdFactur");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
        try{        	
        	
        	idemisor = input.getStringValue("idemisor");
        	tipodata = input.getStringValue("tipodata");
        	valordat = input.getStringValue("tipodata");
        	fhdesdex = input.getStringValue("fhdesdex");
        	fhhastax = input.getStringValue("fhhastax");
        	aniofact = input.getStringValue("aniofact");

        	getFechas(tipodata,valordat);
        	
        	if((fhdesdex  != null) && (!fhdesdex.equals("")) && (!fhdesdex.equals("0000-00-00"))){
        		//fhdesdexMy = fechaMySQL(fhdesdex);
        	} else {
        		fhdesdexMy = aniofact +"-01-01";
        	}
        	
        	if((fhhastax != null) && (!fhhastax.equals("")) && (!fhhastax.equals("0000-00-00"))){
        		//fhhastaxMy = fechaMySQL(fhhastax);
        	} else {
        		fhhastaxMy = aniofact +"-12-31";
        	}
        	

        	gdFactur = new Grid();
        	gdFactur.addColumn("idmesxxx");
        	gdFactur.addColumn("txmesxxx");
        	gdFactur.addColumn("baseimpo");
        	gdFactur.addColumn("imptaxes");
        	gdFactur.addColumn("impreten");        	
        	gdFactur.addColumn("imptotal");
        	gdFactur.addColumn("totalpag");
        	gdFactur.addColumn("numfactu");
        	
        	ListFacturasInformeBDIn ListFactBDIn  = new ListFacturasInformeBDIn();  
        	ListFactBDIn.setValue("idemisor", idemisor);
        	ListFactBDIn.setValue("aniofact", aniofact);
        	ListFactBDIn.setValue("fhdesdex", fhdesdexMy);
        	ListFactBDIn.setValue("fhhastax", fhhastaxMy);
        	ListFactBDIn.setValue("orderbyx", "fhfactur");
        	ListFacturasInformeBD ListTipoBD = new ListFacturasInformeBD(ListFactBDIn);
        	ListTipoBD.setConnection(con);
        	Grid gridFact = ListTipoBD.execSelect();
        	
        	agrupaFacturaMeses(gridFact);
        	
        	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        	
        	dataset = getDataSetMensual();
        	
        	GraficoBarras grafBarras = new graficas.constants.GraficoBarras();
        	
        	grafBarras.setEmisor(idemisor);
        	filename = grafBarras.crearGrafica("ListFact"+ tipodata, " ", dataset, cddivisa.replaceAll("&euro;","€"));
        	
        	output.setValue("idemisor", idemisor);
        	output.setValue("aniofact", aniofact);
        	output.setValue("filename", filename);
        	output.setValue("tipodata", tipodata);
        	output.setGrid("gdFactur", gdFactur);
			
        } catch (Exception e1) {
			e1.printStackTrace();
			System.out.println("  ");
		}            
        
    }
    
    public void getFechas(String tipodato, String valdatox) {
    	
    	if (tipodato.equals("mes")) {
    		fhdesdex = aniofact +"-01-01";
    		fhhastax = aniofact +"-12-31";
    	}
    	
    }
    
    public void agrupaFacturaMeses(Grid gdFactur) {
    	
    	//Inicializa los ArrayList
    	for (int i = 0; i < 12; i++) {
    		arBasImp.add(Double.parseDouble("0"));
    		arTaxImp.add(Double.parseDouble("0"));
    		arRetImp.add(Double.parseDouble("0"));
    		arTotImp.add(Double.parseDouble("0"));
    		arPagImp.add(Double.parseDouble("0"));
    		arNumero.add(0);
    	}
    	
    	for (int i = 0; i < gdFactur.rowCount(); i++) {
    		
    		String fhfactur = gdFactur.getStringCell(i, "fhfactur");
    		int indexMes = Integer.valueOf(fhfactur.substring(3,5)) - 1;
    		cddivisa = gdFactur.getStringCell(i, "cddivisa");
    		
    		double acuBasei = arBasImp.get(indexMes) + Double.parseDouble(gdFactur.getStringCell(i, "baseimpo"));
    		double acuImptx = arTaxImp.get(indexMes) + Double.parseDouble(gdFactur.getStringCell(i, "imptaxes"));
    		//double acuReten = arRetImp.get(indexMes) + Double.parseDouble(gdFactur.getStringCell(i, "impreten"));
    		double acuTotal = arTotImp.get(indexMes) + Double.parseDouble(gdFactur.getStringCell(i, "imptotal"));
    		double acuPagad = arPagImp.get(indexMes) + Double.parseDouble(gdFactur.getStringCell(i, "totalpag"));
    		int nfaAcumu = arNumero.get(indexMes) + 1;
    		
    		arBasImp.set(indexMes, acuBasei);
    		arTaxImp.set(indexMes, acuImptx);
    		//arRetImp.set(indexMes, acuReten);
    		arTotImp.set(indexMes, acuTotal);
    		arPagImp.set(indexMes, acuPagad);
    		arNumero.set(indexMes, nfaAcumu);
    		
    	}
    	
    	int contTrim = 1;
    	double numTrime = 0;
    	double impTrime = 0;
    	
    	for (int i = 0; i < 12; i++){
	    
    		int idmesxxx = i + 1;
    		
    		ArrayList<String> rowMesFa = new ArrayList<String>();
    		rowMesFa.add(String.valueOf(idmesxxx));
    		rowMesFa.add(dameMes(idmesxxx));
    		rowMesFa.add(formatDivi.format(arBasImp.get(i)));
    		rowMesFa.add(formatDivi.format(arTaxImp.get(i)));
    		rowMesFa.add(formatDivi.format(arRetImp.get(i)));
    		rowMesFa.add(formatDivi.format(arTotImp.get(i)));
    		rowMesFa.add(formatDivi.format(arPagImp.get(i)));
    		rowMesFa.add(formatEnte.format(arNumero.get(i)));
    		
    		gdFactur.addRow(rowMesFa);
    		
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
    
    public DefaultCategoryDataset getDataSetMensual(){
    
    	DefaultCategoryDataset datasetx = new DefaultCategoryDataset();
    	
		for (int i = 1; i <= 12; i++){
		
			String namemesx = dameMes(i).substring(0,3);
			//String namemesx = String.valueOf(i);
			
			datasetx.addValue(arBasImp.get(i-1), "Base Imponible", namemesx);
			datasetx.addValue(arTaxImp.get(i-1), "Impuestos", namemesx);
			datasetx.addValue(arTotImp.get(i-1), "Total", namemesx);
			datasetx.addValue(arPagImp.get(i-1), "Impagado", namemesx);
			
			
		}
		
		return datasetx;
		
    }
       
    public String dameMes(int numerMes) {
    	
    	String namemesx = "";
    	
    	if (numerMes == 1){
			namemesx = "Enero";
		} else if (numerMes == 2){
			namemesx = "Febrero";
		} else if (numerMes == 3){
			namemesx = "Marzo";
		} else if (numerMes == 4){
			namemesx = "Abril";
		} else if (numerMes == 5){
			namemesx = "Mayo";
		} else if (numerMes == 6){
			namemesx = "Junio";
		} else if (numerMes == 7){
			namemesx = "Julio";
		} else if (numerMes == 8){
			namemesx = "Agosto";
		} else if (numerMes == 9){
			namemesx = "Septiembre";
		} else if (numerMes == 10){
			namemesx = "Octubre";
		} else if (numerMes == 11){
			namemesx = "Noviembre";
		} else if (numerMes == 12){
			namemesx = "Diciembre";
		} else {
			namemesx = "ERROR MES - "+ numerMes;
		}
    	
    	return namemesx;
    	
    }
    
}
