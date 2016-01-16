package informes;


import informes.bd.ListFacturasInformeClienteBD;
import informes.bd.ListFacturasInformeClienteBDIn;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.jfree.data.category.DefaultCategoryDataset;

import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import factura.bd.ListFacturasBD;
import factura.bd.ListFacturasBDIn;
import graficas.constants.GraficoBarras;


public class ListFacturasGraficaSrv extends Service {

	//Varibales de entrada
	String idemisor 	= null;
	String tpclient		= null;
	String tipodata 	= null;
	String idtrimes 	= null;
	String idclient 	= null; 
	String aniofact 	= null;
	String numerome		= null;
	String txmensaj 	= "";
	String mesinitr = "";
	String mesfintr = "";
	String nameclie = "";
 
	//Variables de clase
	Grid grfactur		= null;
	String fhdesdexMy 	= null;
	String fhhastaxMy	= null;
	ArrayList<Integer> arNFaMes = new ArrayList<Integer>();
	ArrayList<Double> arImpMes = new ArrayList<Double>();
	
	DecimalFormat formatEnte = new DecimalFormat("###,##0");
	DecimalFormat formatDivi = new DecimalFormat("###,##0.00");
	
    public ListFacturasGraficaSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();

			input.addVariable("idemisor");
			input.addVariable("idclient");
			input.addVariable("tpclient");
			input.addVariable("tipodata");
			input.addVariable("idtrimes");
			input.addVariable("numerome");	
			input.addVariable("aniofact");
			input.addVariable("grfactur");			
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
			output.addVariable("tipodata");		
			output.addVariable("filecrea");	
			output.addVariable("txmensaj");	
			
			output.addVariable("vntTotal");
			output.addVariable("numVenta");
			output.addVariable("medVenta");
			output.addVariable("maxVenta");
			output.addVariable("cddivisa");	
			output.addVariable("grfactur");
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
        	idclient = input.getStringValue("idclient");
        	tpclient = input.getStringValue("tpclient");
        	tipodata = input.getStringValue("tipodata");
        	numerome = input.getStringValue("numerome");
        	aniofact = input.getStringValue("aniofact");
        	idtrimes = input.getStringValue("idtrimes");
        	
        	
        	
        	if (tipodata != null && tipodata.equals("mejorcli")){
        		ListFacturasBDIn listFactMejorCliBDIn = new ListFacturasBDIn();
        		listFactMejorCliBDIn.setValue("idemisor", idemisor);
        		listFactMejorCliBDIn.setValue("idclient", idclient);
        		listFactMejorCliBDIn.setValue("tpclient", tpclient);
        		listFactMejorCliBDIn.setValue("aniofact", aniofact);
        		ListFacturasBD listFactMejorCliBD = new ListFacturasBD(listFactMejorCliBDIn);
        		listFactMejorCliBD.setConnection(con);
        		
        		grfactur = listFactMejorCliBD.execSelect();
        		
        		nameclie = grfactur.getStringCell(0, "rzsocial");
        		txmensaj = "LISTADO FACTURAS CLIENTE: "+nameclie;
        		
        	}else if (tipodata != null && tipodata.equals("cliimpa")){
        		ListFacturasBDIn listFactImpaBDIn= new ListFacturasBDIn();
        		listFactImpaBDIn.setValue("idemisor", idemisor);
        		listFactImpaBDIn.setValue("idclient", idclient);
        		listFactImpaBDIn.setValue("tpclient", tpclient);
        		listFactImpaBDIn.setValue("mcpagado", "N");
        		ListFacturasBD listFactImpaBD = new ListFacturasBD(listFactImpaBDIn);
        		listFactImpaBD.setConnection(con);
        		
        		grfactur = listFactImpaBD.execSelect();
        		
        		nameclie = grfactur.getStringCell(0, "rzsocial");
        		txmensaj = "LISTADO FACTURAS IMPAGADAS CLIENTE: "+nameclie;
        		
        	}else if (tipodata != null && tipodata.equals("trimest")){
        		dameTrimestre(idtrimes);
        		
        		ListFacturasBDIn listFactImpaBDIn= new ListFacturasBDIn();
        		listFactImpaBDIn.setValue("idemisor", idemisor);
        		listFactImpaBDIn.setValue("fhdesdex", fhdesdexMy);
        		listFactImpaBDIn.setValue("fhhastax", fhhastaxMy);
        		ListFacturasBD listFactImpaBD = new ListFacturasBD(listFactImpaBDIn);
        		listFactImpaBD.setConnection(con);
        		
        		grfactur = listFactImpaBD.execSelect();
        		
        		txmensaj = "LISTADO FACTURAS TRIMESTRE DE " + mesinitr + " A "+ mesfintr +" DE "+aniofact ;
        		
        		
        	}else if (tipodata != null && tipodata.equals("filtrmes")){
        		
        		filtraMes(numerome);
        		String namemesx = dameMes(Integer.valueOf(numerome)+1);
        		ListFacturasBDIn listFactImpaBDIn= new ListFacturasBDIn();
        		listFactImpaBDIn.setValue("idemisor", idemisor);
        		listFactImpaBDIn.setValue("fhdesdex", fhdesdexMy);
        		listFactImpaBDIn.setValue("fhhastax", fhhastaxMy);
        		ListFacturasBD listFactImpaBD = new ListFacturasBD(listFactImpaBDIn);
        		listFactImpaBD.setConnection(con);
        		
        		grfactur = listFactImpaBD.execSelect();
        		
        		txmensaj = "LISTADO FACTURAS DE " + namemesx + " DE "+aniofact ;
        		
        	}

        	output.setValue("idemisor", idemisor);
        	output.setValue("aniofact", aniofact);
        	output.setValue("tipodata", tipodata);
        	output.setValue("txmensaj", txmensaj);
        	output.setValue("grfactur", grfactur);
      
			
        } catch (Exception e1) {
			e1.printStackTrace();
			System.out.println("  ");
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
	        	rowDeuCl.add(gdFacMCl.getStringCell(i, "cdintern"));
	        	rowDeuCl.add(gdFacMCl.getStringCell(i, "rzsocial"));
	        	rowDeuCl.add(formatDivi.format(Double.parseDouble(gdFacMCl.getStringCell(i, "imptotal"))));
	        	
	        	//gdDeuCli.addRow(rowDeuCl);
	        	
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
    
    public void dameTrimestre(String numetrim) {
    	
    	if (numetrim.equals("1") ){
    		fhdesdexMy = aniofact+"-01-01";
    		fhhastaxMy = aniofact+"-03-31";
    		
    		mesinitr = "ENERO";
    		mesfintr = "MARZO";
    	}
    	
    	if (numetrim.equals("2") ){
    		fhdesdexMy = aniofact+"-04-01";
    		fhhastaxMy = aniofact+"-06-30";
    		
    		mesinitr = "ABRIL";
    		mesfintr = "JUNIO";
    	}
    	
    	if (numetrim.equals("3") ){
    		fhdesdexMy = aniofact+"-07-01";
    		fhhastaxMy = aniofact+"-09-30";
    		
    		mesinitr = "JULIO";
    		mesfintr = "SEPTIEMBRE";
    	}
    	
    	if (numetrim.equals("4") ){
    		fhdesdexMy = aniofact+"-10-01";
    		fhhastaxMy = aniofact+"-12-31";
    		
    		mesinitr = "OCTUBRE";
    		mesfintr = "DICIEMBRE";
    	}
    	
    }
    
    public void filtraMes(String numerMes){
    	Calendar cal = new GregorianCalendar(Integer.valueOf(aniofact), Integer.valueOf(numerMes), 1); 
    	String diames =  String.valueOf(cal.getActualMaximum(Calendar.DAY_OF_MONTH)); // 28
    	int mesmasuno = Integer.valueOf(numerMes) +1;
    	
    	fhdesdexMy = aniofact+"-"+mesmasuno+"-01";
		fhhastaxMy = aniofact+"-"+mesmasuno+"-"+diames;
    }
    

       
    public String dameMes(int numerMes) {
    	
    	String namemesx = "";
    	
    	if (numerMes == 1){
			namemesx = "ENERO";
		} else if (numerMes == 2){
			namemesx = "FEBRERO";
		} else if (numerMes == 3){
			namemesx = "MARZO";
		} else if (numerMes == 4){
			namemesx = "ABRIL";
		} else if (numerMes == 5){
			namemesx = "MAYO";
		} else if (numerMes == 6){
			namemesx = "JUNIO";
		} else if (numerMes == 7){
			namemesx = "JULIO";
		} else if (numerMes == 8){
			namemesx = "AGOSTO";
		} else if (numerMes == 9){
			namemesx = "SEPTIEMBRE";
		} else if (numerMes == 10){
			namemesx = "OCTUBRE";
		} else if (numerMes == 11){
			namemesx = "NOVIEMBRE";
		} else if (numerMes == 12){
			namemesx = "DICIEMBRE";
		} else {
			namemesx = "ERROR MES - "+ numerMes;
		}
    	
    	return namemesx;
    	
    }
    
}
