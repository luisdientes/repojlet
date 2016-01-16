package factura;


import java.text.DecimalFormat;
import java.util.ArrayList;

import org.jfree.data.category.DefaultCategoryDataset;

import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import factura.bd.ListClientesFactBD;
import factura.bd.ListClientesFactBDIn;
import factura.bd.ListFacturasBD;
import factura.bd.ListFacturasBDIn;
import graficas.constants.GraficoBarras;


public class ListGraficaFacturasSrv extends Service {

	String tipodata = "Diaria";
	Grid gridAcum = new Grid();
	
	DecimalFormat formatDivi = new DecimalFormat("#####0.00");
	
    public ListGraficaFacturasSrv() {
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
			input.addVariable("idcliere");
			
						
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
			output.addVariable("gridAcum");
			output.addVariable("fhdesdex");
			output.addVariable("fhhastax");
			output.addVariable("tipodata");
			output.addVariable("idcliere");
			output.addVariable("grfactur");
			output.addVariable("gragrupa");
			output.addVariable("gridClie");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor 	= null;
    	String tipodata 	= null;
    	String fhdesdex 	= null; //al devolver la fecha para mostrar en el input no la pude devolver en formato mysql
    	String fhhastax 	= null;
    	String aniofact 	= null;
    	String idcliere 	= null;
    	
    	//Variables de salida
        Grid gridFact 		= null;
        Grid gridAgru 		= null;        
        Grid gridClie 		= null;
        String filename 	= null;
        String fhdesdexMy 	= null;
    	String fhhastaxMy	= null;
     
        
        try{        	
        	
        	idemisor = input.getStringValue("idemisor");
        	tipodata = input.getStringValue("tipodata");
        	fhdesdex = input.getStringValue("fhdesdex");
        	fhhastax = input.getStringValue("fhhastax");
        	aniofact = input.getStringValue("aniofact");
        	idcliere = input.getStringValue("idcliere");
        	        	       	
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
        	
        	ListFacturasBDIn ListFactBDIn  = new ListFacturasBDIn();  
        	ListFactBDIn.setValue("idemisor", idemisor);
        	ListFactBDIn.setValue("aniofact", aniofact);
        	ListFactBDIn.setValue("idclient", idcliere);
        	ListFactBDIn.setValue("fhdesdex", fhdesdexMy);
        	ListFactBDIn.setValue("fhhastax", fhhastaxMy);
        	ListFactBDIn.setValue("orderbyx", "fhfactur");
        	ListFacturasBD ListTipoBD = new ListFacturasBD(ListFactBDIn);
        	ListTipoBD.setConnection(con);
        	gridFact = ListTipoBD.execSelect();
        	
        	gridAgru = new Grid();
        	gridAgru.addColumn("txagrupa");
        	gridAgru.addColumn("cdagrupa");
        	gridAgru.addColumn("basacumu");
        	gridAgru.addColumn("taxacumu");
        	gridAgru.addColumn("totacumu");
        	
        	String fhanteri = "";
        	double basacumu = 0;
        	double taxacumu = 0;
        	double totacumu = 0;

        	for (int i = 0; i < gridFact.rowCount(); i++){
        		String fhactual = gridFact.getStringCell(i,"fhfactur");
        		
        		if (tipodata.equals("Diaria")){
	        		
        			if (!fhanteri.equals("") && !fhactual.equals(fhanteri)){
	        			ArrayList<String> row = new ArrayList<String>();
	        			
	        			String cdagrupa = fhanteri.substring(6,10) + fhanteri.substring(3,5) + fhanteri.substring(0,2);
	        			
	        			row.add(fhanteri);
	        			row.add(cdagrupa);
	        			row.add(String.valueOf(basacumu));
	        			row.add(String.valueOf(taxacumu));
	        			row.add(String.valueOf(totacumu));
	        			gridAgru.addRow(row);
	        			basacumu = 0;
	                	taxacumu = 0;
	                	totacumu = 0;
	        		}
        			
        		} else if (tipodata.equals("Mensual")){
        			
        			
        			if (!fhanteri.equals("")) {
        				fhactual = fhactual.substring(3,5);
        			}
        			
        			fhactual = gridFact.getStringCell(i,"fhfactur").substring(3,5);
        			
        			if (!fhanteri.equals("") && !fhactual.equals(fhanteri)){
	        			ArrayList<String> row = new ArrayList<String>();
	        			
	        			String cdagrupa = fhactual;
	        			
	        			row.add(fhanteri);
	        			row.add(cdagrupa);
	        			row.add(String.valueOf(basacumu));
	        			row.add(String.valueOf(taxacumu));
	        			row.add(String.valueOf(totacumu));
	        			gridAgru.addRow(row);
	        			basacumu = 0;
	                	taxacumu = 0;
	                	totacumu = 0;
	        		}
        		
        		}
        			
    			basacumu += Double.parseDouble(gridFact.getStringCell(i,"baseimpo"));
    			taxacumu += Double.parseDouble(gridFact.getStringCell(i,"imptaxes"));
    			totacumu += Double.parseDouble(gridFact.getStringCell(i,"imptotal"));
    			fhanteri = fhactual;
        		
        	}
        	
        	ArrayList<String> row = new ArrayList<String>();
			row.add(fhanteri);
			
			String cdagrupa = "";
			
			if (tipodata.equals("Diaria")){
				cdagrupa = fhanteri.substring(6,10) + fhanteri.substring(3,5) + fhanteri.substring(0,2);
        	} else if (tipodata.equals("Mensual")){
        		cdagrupa = fhanteri;
        	}
			row.add(cdagrupa);
			row.add(String.valueOf(basacumu));
			row.add(String.valueOf(taxacumu));
			row.add(String.valueOf(totacumu));
			gridAgru.addRow(row);
        	
        	String cddivisa = "";
        	
        	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        	
        	dataset = getDataSet(tipodata,gridFact);
        	
        	GraficoBarras grafBarras = new graficas.constants.GraficoBarras();
        	
        	grafBarras.setEmisor(idemisor);
        	filename = grafBarras.crearGrafica("ListFact"+ tipodata, "Facturación "+ tipodata, dataset, cddivisa);
        	
        	//clientes con facturas del emisor
        	ListClientesFactBDIn ListCliFactBDIn  = new ListClientesFactBDIn();  
        	ListCliFactBDIn.setValue("idemisor", idemisor);
        	ListCliFactBDIn.setValue("aniofact", aniofact);
        	ListCliFactBDIn.setValue("idclient", idcliere);
        	ListClientesFactBD ListCliTipoBD = new ListClientesFactBD(ListCliFactBDIn);
        	ListCliTipoBD.setConnection(con);
        	gridClie= ListCliTipoBD.execSelect();
        	
        	output.setValue("idemisor", idemisor);
        	output.setValue("aniofact", aniofact);
        	output.setValue("filename", filename);
        	output.setValue("gridAcum", gridAcum);
        	output.setValue("fhdesdex", fhdesdex);
        	output.setValue("fhhastax", fhhastax);
        	output.setValue("tipodata", tipodata);
        	output.setValue("idcliere", idcliere);
        	output.setGrid("grfactur", gridFact);
        	output.setGrid("gragrupa", gridAgru);
        	output.setGrid("gridClie", gridClie);
        	
        } catch (Exception e1) {
			e1.printStackTrace();
			System.out.println("  ");
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
    
    public DefaultCategoryDataset getDataSet(String tipodata, Grid gridData){
    	
    	DefaultCategoryDataset datasetx = new DefaultCategoryDataset();
    	
    	if (tipodata.equals("Mensual")) {
    		datasetx = getDataSetMensual(gridData);
    	} else if (tipodata.equals("Diaria")) {
    		datasetx = getDataSetDiario(gridData);
    	}
    	
    	return datasetx;
    }
    
    public DefaultCategoryDataset getDataSetMensual(Grid gridData){
    
    	DefaultCategoryDataset datasetx = new DefaultCategoryDataset();
    	
	    ArrayList<Double> acumumes = new ArrayList<Double>();
		
		for (int i = 0; i <= 12; i++){
			acumumes.add((double) 0);
		}
		
		for (int i = 0; i < gridData.rowCount(); i++){
			ArrayList<String> row = new ArrayList<String>(); 
			String fhfechax = gridData.getStringCell(i, "fhfactur");
			double baseimpo = Double.parseDouble(gridData.getStringCell(i, "baseimpo")); 
			//String baseimpo = gridFact.getStringCell(i, "baseimpo");
			row.add(fhfechax.substring(3,5));
			row.add(formatDivi.format(baseimpo) +" "+ gridData);
			acumumes.set(0, acumumes.get(0) + baseimpo);
			acumumes.set(Integer.parseInt(fhfechax.substring(3,5)), acumumes.get(Integer.parseInt(fhfechax.substring(3,5))) + baseimpo);              
		}
		
		for (int i = 1; i <= 12; i++){
		
			ArrayList<String> rowgridx = new ArrayList<String>(); 
			
			String namemesx = "";
			
			if (i == 1){
				namemesx = "Enero";
			} else if (i == 2){
				namemesx = "Febrero";
			} else if (i == 3){
				namemesx = "Marzo";
			} else if (i == 4){
				namemesx = "Abril";
			} else if (i == 5){
				namemesx = "Mayo";
			} else if (i == 6){
				namemesx = "Junio";
			} else if (i == 7){
				namemesx = "Julio";
			} else if (i == 8){
				namemesx = "Agosto";
			} else if (i == 9){
				namemesx = "Septiembre";
			} else if (i == 10){
				namemesx = "Octubre";
			} else if (i == 11){
				namemesx = "Noviembre";
			} else if (i == 12){
				namemesx = "Diciembre";
			}
			
			datasetx.addValue(acumumes.get(i), "iZumba", namemesx);
			
			rowgridx.add(namemesx);
			rowgridx.add(formatDivi.format(acumumes.get(i)));
			
			gridAcum.addRow(rowgridx);
			
		}
		
		return datasetx;
		
    }
    
    public DefaultCategoryDataset getDataSetDiario(Grid gridData){
        
    	DefaultCategoryDataset datasetx = new DefaultCategoryDataset();
    	
	    ArrayList<Double> acumumes = new ArrayList<Double>();
		
		for (int i = 0; i <= 31; i++){
			acumumes.add((double) 0);
		}
		
		for (int i = 0; i < gridData.rowCount(); i++){
			ArrayList<String> row = new ArrayList<String>(); 
			String fhfechax = gridData.getStringCell(i, "fhfactur");
			double baseimpo = Double.parseDouble(gridData.getStringCell(i, "baseimpo")); 
			//String baseimpo = gridFact.getStringCell(i, "baseimpo");
			row.add(fhfechax.substring(1,3));
			row.add(formatDivi.format(baseimpo) +" "+ gridData);
			acumumes.set(0, acumumes.get(0) + baseimpo);
			
			acumumes.set(Integer.parseInt(fhfechax.substring(0,2)), acumumes.get(Integer.parseInt(fhfechax.substring(0,2))) + baseimpo);              
		}
		
		for (int i = 1; i <= 31; i++){
			
			ArrayList<String> rowgridx = new ArrayList<String>();
			
			DecimalFormat formatDia = new DecimalFormat("00");
			
			datasetx.addValue(acumumes.get(i), "iZumba", formatDia.format(i));
			
			rowgridx.add(formatDia.format(i));
			rowgridx.add(formatDivi.format(acumumes.get(i)));
			
			gridAcum.addRow(rowgridx);
			
		}
		
		return datasetx;
		
    }
       
}
