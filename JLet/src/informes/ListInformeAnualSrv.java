package informes;


import informes.bd.ListFacturasInformeClienteBD;
import informes.bd.ListFacturasInformeClienteBDIn;

import java.text.DecimalFormat;
import java.util.ArrayList;

import org.jfree.data.category.DefaultCategoryDataset;

import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import factura.bd.ListFacturasBD;
import factura.bd.ListFacturasBDIn;
import graficas.constants.GraficoBarras;


public class ListInformeAnualSrv extends Service {

	//Varibales de entrada
	String idemisor 	= null;
	String tipodata 	= null;
	String fhdesdex 	= null; //al devolver la fecha para mostrar en el input no la pude devolver en formato mysql
	String fhhastax 	= null;
	String aniofact 	= null;
	
	//Variables de salida
    String filename 	= null;
    String filecrea		= null;
    String cddivisa 	= null;
	double vntTotal		= 0;
	double numVenta 	= 0;
	double medVenta 	= 0;
	double maxVenta 	= 0;
    Grid gdMejCli 		= null;
    Grid gdDeuCli 		= null;        
    Grid gdFacMes 		= null;     
    Grid gdFacTri 		= null;
 
	//Variables de clase
	Grid gridAgru		= null;
	String fhdesdexMy 	= null;
	String fhhastaxMy	= null;
	ArrayList<Integer> arNFaMes = new ArrayList<Integer>();
	ArrayList<Double> arImpMes = new ArrayList<Double>();
	
	DecimalFormat formatEnte = new DecimalFormat("###,##0");
	DecimalFormat formatDivi = new DecimalFormat("###,##0.00");
	
    public ListInformeAnualSrv() {
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
			output.addVariable("filecrea");	
			output.addVariable("vntTotal");
			output.addVariable("numVenta");
			output.addVariable("medVenta");
			output.addVariable("maxVenta");
			output.addVariable("cddivisa");	
			output.addVariable("gdMejCli");
			output.addVariable("gdDeuCli");
			output.addVariable("gdFacMes");
			output.addVariable("gdFacTri");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
        try{        	
        	
        	idemisor = input.getStringValue("idemisor");
        	tipodata = input.getStringValue("tipodata");
        	fhdesdex = input.getStringValue("fhdesdex");
        	fhhastax = input.getStringValue("fhhastax");
        	aniofact = input.getStringValue("aniofact");
        	        	       	
        	if((fhdesdex  != null) && (!fhdesdex.equals("")) && (!fhdesdex.equals("0000-00-00"))){
        		fhdesdexMy = fechaMySQL(fhdesdex);
        	} else {
        		fhdesdexMy = aniofact +"-01-01";
        	}
        	
        	if((fhhastax != null) && (!fhhastax.equals("")) && (!fhhastax.equals("0000-00-00"))){
        		fhhastaxMy = fechaMySQL(fhhastax);
        	} else {
        		fhhastaxMy = aniofact +"-12-31";
        	}
        	
        	gdMejCli = new Grid();
        	gdMejCli.addColumn("idclient");
        	gdMejCli.addColumn("tpclient");
        	gdMejCli.addColumn("txnombre");
        	gdMejCli.addColumn("totalimp");

        	gdDeuCli = new Grid();
        	gdDeuCli.addColumn("idclient");
        	gdDeuCli.addColumn("txnombre");
        	gdDeuCli.addColumn("totalimp");

        	gdFacMes = new Grid();
        	gdFacMes.addColumn("idmesxxx");
        	gdFacMes.addColumn("txmesxxx");
        	gdFacMes.addColumn("numopera");
        	gdFacMes.addColumn("totalimp");
        	
        	gdFacTri = new Grid();
        	gdFacTri.addColumn("idtrixxx");
        	gdFacTri.addColumn("txtrixxx");
        	gdFacTri.addColumn("numopera");
        	gdFacTri.addColumn("totalimp");
        	
        	ListFacturasBDIn ListFactBDIn  = new ListFacturasBDIn();  
        	ListFactBDIn.setValue("idemisor", idemisor);
        	ListFactBDIn.setValue("aniofact", aniofact);
        	ListFactBDIn.setValue("fhdesdex", fhdesdexMy);
        	ListFactBDIn.setValue("fhhastax", fhhastaxMy);
        	ListFactBDIn.setValue("orderbyx", "fhfactur");
        	ListFacturasBD ListTipoBD = new ListFacturasBD(ListFactBDIn);
        	ListTipoBD.setConnection(con);
        	Grid gridFact = ListTipoBD.execSelect();
        	
        	agrupaFacturaMeses(gridFact);
        	
        	mejoresClientes();
        	
        	clientesDeudores();
        	
        	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        	
        	dataset = getDataSetMensual();
        	
        	GraficoBarras grafBarras = new graficas.constants.GraficoBarras();
        	
        	grafBarras.setEmisor(idemisor);
        	filename = grafBarras.crearGrafica("ListFact"+ tipodata, " ", dataset, cddivisa.replaceAll("&euro;","€"));
        	
        	output.setValue("idemisor", idemisor);
        	output.setValue("aniofact", aniofact);
        	output.setValue("filename", filename);
        	output.setValue("filecrea", filecrea);
        	output.setValue("vntTotal", formatDivi.format(vntTotal));
        	output.setValue("numVenta", formatEnte.format(numVenta));
        	output.setValue("medVenta", formatDivi.format(medVenta));
        	output.setValue("maxVenta", formatDivi.format(maxVenta));
        	output.setValue("cddivisa", cddivisa);
        	output.setGrid("gdMejCli", gdMejCli);
        	output.setGrid("gdDeuCli", gdDeuCli);
        	output.setGrid("gdFacMes", gdFacMes);
        	output.setGrid("gdFacTri", gdFacTri);
			
        } catch (Exception e1) {
			e1.printStackTrace();
			System.out.println("  ");
		}            
        
    }
    
    public void agrupaFacturaMeses(Grid gdFactur) {
    	
    	
    	//Inicializa los ArrayList
    	for (int i = 0; i < 12; i++) {
    		arNFaMes.add(0);
    		arImpMes.add(Double.parseDouble("0"));
    	}
    	
    	for (int i = 0; i < gdFactur.rowCount(); i++) {
    		
    		String fhfactur = gdFactur.getStringCell(i, "fhfactur");
    		int indexMes = Integer.valueOf(fhfactur.substring(3,5)) - 1;
    		cddivisa = gdFactur.getStringCell(i, "cddivisa");
    		
    		int nfaAcumu = arNFaMes.get(indexMes) + 1;
    		double impAcumu = arImpMes.get(indexMes) + Double.parseDouble(gdFactur.getStringCell(i, "imptotal"));
    		
    		arNFaMes.set(indexMes, nfaAcumu);
    		arImpMes.set(indexMes, impAcumu);
    		
    		if (maxVenta < Double.parseDouble(gdFactur.getStringCell(i, "imptotal"))){
    			maxVenta = Double.parseDouble(gdFactur.getStringCell(i, "imptotal"));
    			filecrea = gdFactur.getStringCell(i, "filecrea");
    		}
    		
    		vntTotal += Double.parseDouble(gdFactur.getStringCell(i, "imptotal"));
    		numVenta++;
    		
    	}
    	
    	medVenta = vntTotal / numVenta;
    	
    	int contTrim = 1;
    	double numTrime = 0;
    	double impTrime = 0;
    	
    	for (int i = 0; i < 12; i++){
	    
    		int idmesxxx = i + 1;
    		
    		ArrayList<String> rowMesFa = new ArrayList<String>();
    		rowMesFa.add(String.valueOf(idmesxxx));
    		rowMesFa.add(dameMes(idmesxxx));
    		rowMesFa.add(formatEnte.format(arNFaMes.get(i)));
    		rowMesFa.add(formatDivi.format(arImpMes.get(i)));
    		
    		numTrime += arNFaMes.get(i);
    		impTrime += arImpMes.get(i);
    		
    		if (((i + 1) % 3) == 0) {
    			
    			ArrayList<String> rowMesTri = new ArrayList<String>();
    			rowMesTri.add(String.valueOf(contTrim));
    			rowMesTri.add(contTrim +" Trimestre");
    			rowMesTri.add(formatEnte.format(numTrime));
    			rowMesTri.add(formatDivi.format(impTrime));
        		
        		numTrime = 0;
            	impTrime = 0;
            	
            	gdFacTri.addRow(rowMesTri);
            	contTrim++;
            	
    		}
    		
    		gdFacMes.addRow(rowMesFa);
    		
    	}
    	
    }
    
    public void mejoresClientes() {
    	
    	try {
    		
	    	ListFacturasInformeClienteBDIn ListFactCliBDIn  = new ListFacturasInformeClienteBDIn();  
	    	ListFactCliBDIn.setValue("idemisor", idemisor);
	    	ListFactCliBDIn.setValue("aniofact", aniofact);
	    	ListFactCliBDIn.setValue("fhdesdex", fhdesdexMy);
	    	ListFactCliBDIn.setValue("fhhastax", fhhastaxMy);
	    	ListFactCliBDIn.setValue("mcagrcli", "S");
	    	ListFacturasInformeClienteBD ListFactCliBD = new ListFacturasInformeClienteBD(ListFactCliBDIn);
	    	ListFactCliBD.setConnection(con);
	    	Grid gdFacMCl = ListFactCliBD.execSelect();
	    	
	    	for (int i = 0; (i < gdFacMCl.rowCount()) && (i < 10); i++) {
	    	
	        	ArrayList<String> rowMejCl = new ArrayList<String>();
	        	rowMejCl.add(gdFacMCl.getStringCell(i, "idclient"));
	        	rowMejCl.add(gdFacMCl.getStringCell(i, "tpclient"));
	        	rowMejCl.add(gdFacMCl.getStringCell(i, "rzsocial"));
	        	rowMejCl.add(formatDivi.format(Double.parseDouble(gdFacMCl.getStringCell(i, "imptotal"))));
	        	
	        	gdMejCli.addRow(rowMejCl);
	        	
	    	}
	    	
    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() +" ERROR recuperando los mejores clientes. (e) -> "+ e.getMessage());
    	}
    }
    
    public void clientesDeudores() {
    	
    	try {
    		
    		
    		//TENER EN CUENTA PAGOS PENDIENTES...
	    	ListFacturasInformeClienteBDIn ListFactCliBDIn  = new ListFacturasInformeClienteBDIn();  
	    	ListFactCliBDIn.setValue("idemisor", idemisor);
	    	ListFactCliBDIn.setValue("aniofact", aniofact);
	    	ListFactCliBDIn.setValue("fhdesdex", fhdesdexMy);
	    	ListFactCliBDIn.setValue("fhhastax", fhhastaxMy);
	    	ListFactCliBDIn.setValue("mcpagado", "N");
	    	ListFactCliBDIn.setValue("mcagrcli", "S");
	    	ListFacturasInformeClienteBD ListFactCliBD = new ListFacturasInformeClienteBD(ListFactCliBDIn);
	    	ListFactCliBD.setConnection(con);
	    	Grid gdFacMCl = ListFactCliBD.execSelect();
	    	
	    	for (int i = 0; (i < gdFacMCl.rowCount()) && (i < 10); i++) {
	    	
	        	ArrayList<String> rowDeuCl = new ArrayList<String>();
	        	rowDeuCl.add(gdFacMCl.getStringCell(i, "idclient"));
	        	rowDeuCl.add(gdFacMCl.getStringCell(i, "rzsocial"));
	        	rowDeuCl.add(formatDivi.format(Double.parseDouble(gdFacMCl.getStringCell(i, "imptotal"))));
	        	
	        	gdDeuCli.addRow(rowDeuCl);
	        	
	    	}
	    	
    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() +" ERROR recuperando los mejores clientes. (e) -> "+ e.getMessage());
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
			
			datasetx.addValue(arImpMes.get(i-1), "Total", namemesx);
			
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
