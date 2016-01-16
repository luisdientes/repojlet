package desgcostes;

import graficas.constants.GraficoCircular;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import desgcostes.bd.ListDesgloseCostesBD;
import desgcostes.bd.ListDesgloseCostesBDIn;
import desgcostes.bd.ListDesgloseDetalleBD;
import desgcostes.bd.ListDesgloseDetalleBDIn;

 
public class DetalleDesgloseSrv extends Service {

	ArrayList<String> arcdcost = new ArrayList<String>();	
	

	Color[] colores = null;
	
	HashMap<String,String> hmCatego = new HashMap<String,String>();
	HashMap<String,Color>  hmColors = new HashMap<String,Color>();
	
	public DetalleDesgloseSrv() {
		
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("idunicox");
			input.addVariable("idemisor");
			input.addVariable("mostriva");
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
			output.addVariable("idunicox");
			output.addVariable("imgfilex");
			output.addVariable("ingtotal");
			output.addVariable("benefici");
			output.addVariable("mostriva");
			output.addVariable("gdcdcost"); 
			output.addVariable("gddscost"); 
			output.addVariable("gddetall"); 
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idunicox = "";
    	String idemisor = "";
    	String mostriva = "";

        //Varibales de salida
    	String imgfilex = "VACIO";
    	String benefici = "";
        Grid gdcdcost = null;
        Grid gddscost = null;
        Grid gddetall = null;
    	
        //Otras Variables
        String ccaduana = "";
        String cccompra = "";
        String cctransp = "";
        String cvaduana = "";
        String cvcompra = "";
        String cvtransp = "";
        String ingtotal = "";
        String ccivaaduana = "";
        String ccivacompra = "";
        String ccivatransp = "";
        String cvivaaduana = "";
        String cvivacompra = "";
        String cvivatransp = "";
        String ingivatotal = "";
        
    	DecimalFormat formatDivi = new DecimalFormat("###,##0.00");
    	DecimalFormat formatNorm = new DecimalFormat("##0.00");
        
        try {
        	
        	idunicox = input.getStringValue("idunicox");
        	idemisor = input.getStringValue("idemisor");
        	mostriva = input.getStringValue("mostriva");
        	
        	if ((mostriva == null) || (mostriva.equals(""))) {
        		mostriva = "N";
        	}
        	
        	
        	ListDesgloseCostesBDIn desCosteBDIn =	 new ListDesgloseCostesBDIn();
        	desCosteBDIn.setValue("idunicox",idunicox);
        	desCosteBDIn.setValue("idemisor",idemisor);
        	desCosteBDIn.setValue("mostriva",mostriva);
        	desCosteBDIn.setValue("mcactivo","S");
        	ListDesgloseCostesBD desCosteBD = new ListDesgloseCostesBD(desCosteBDIn);
        	desCosteBD.setConnection(con);
        	gddscost = desCosteBD.execSelect();
        	
        	//codedesg
        	for (int i = 0; i < gddscost.rowCount(); i++){
        		if (gddscost.getStringCell(i, "codedesg").equals("CATOTAL")){
        			ccaduana = gddscost.getStringCell(i, "desvalue");
        		}
        		if (gddscost.getStringCell(i, "codedesg").equals("CPTOTAL")){
        			cccompra = gddscost.getStringCell(i, "desvalue");
        		}
        		if (gddscost.getStringCell(i, "codedesg").equals("CTTOTAL")){
        			cctransp = gddscost.getStringCell(i, "desvalue");
        		}
        		if (gddscost.getStringCell(i, "codedesg").equals("VATOTAL")){
        			cvaduana = gddscost.getStringCell(i, "desvalue");
        		}
        		if (gddscost.getStringCell(i, "codedesg").equals("VPTOTAL")){
        			cvcompra = gddscost.getStringCell(i, "desvalue");
        		}
        		if (gddscost.getStringCell(i, "codedesg").equals("VTTOTAL")){
        			cvtransp = gddscost.getStringCell(i, "desvalue");
        		}
        		if (gddscost.getStringCell(i, "codedesg").equals("IGTOTAL")){
        			ingtotal = gddscost.getStringCell(i, "desvalue");
        		}
        		//IVA
        		if (gddscost.getStringCell(i, "codedesg").equals("CAIVATOTAL")){
        			ccivaaduana = gddscost.getStringCell(i, "desvalue");
        		}
        		if (gddscost.getStringCell(i, "codedesg").equals("CPIVATOTAL")){
        			ccivacompra = gddscost.getStringCell(i, "desvalue");
        		}
        		if (gddscost.getStringCell(i, "codedesg").equals("CTIVATOTAL")){
        			ccivatransp = gddscost.getStringCell(i, "desvalue");
        		}
        		if (gddscost.getStringCell(i, "codedesg").equals("VAIVATOTAL")){
        			cvivaaduana = gddscost.getStringCell(i, "desvalue");
        		}
        		if (gddscost.getStringCell(i, "codedesg").equals("VPIVATOTAL")){
        			cvivacompra = gddscost.getStringCell(i, "desvalue");
        		}
        		if (gddscost.getStringCell(i, "codedesg").equals("VTIVATOTAL")){
        			cvivatransp = gddscost.getStringCell(i, "desvalue");
        		}
        		if (gddscost.getStringCell(i, "codedesg").equals("IGIVATOTAL")){
        			ingivatotal = gddscost.getStringCell(i, "desvalue");
        		}
        	}
        	
        	if (ccaduana.equals("")){
        		ccaduana = "0";
        	}
        	
        	if (cccompra.equals("")){
        		cccompra = "0";
        	}
        	
        	if (cctransp.equals("")){
        		cctransp = "0";
        	}
        	
        	if (cvaduana.equals("")){
        		cvaduana = "0";
        	}
        	
        	if (cvcompra.equals("")){
        		cvcompra = "0";
        	}
        	
        	if (cvtransp.equals("")){
        		cvtransp = "0";
        	}
        	
        	//IVA
        	
        	if (ccivaaduana.equals("")){
        		ccivaaduana = "0";
        	}
        	
        	if (ccivacompra.equals("")){
        		ccivacompra = "0";
        	}
        	
        	if (ccivatransp.equals("")){
        		ccivatransp = "0";
        	}
        	
        	if (cvivaaduana.equals("")){
        		cvivaaduana = "0";
        	}
        	
        	if (cvivacompra.equals("")){
        		cvivacompra = "0";
        	}
        	
        	if (cvivatransp.equals("")){
        		cvivatransp = "0";
        	}
        	
        	if (ingivatotal.equals("")){
        		ingivatotal = "0";
        	}
        	
        	
        	if ((ingtotal != null) && (!ingtotal.equals(""))){
        	
	        	double dccaduana = Double.parseDouble(ccaduana);
	        	double dcccompra = Double.parseDouble(cccompra);
	        	double dcctransp = Double.parseDouble(cctransp);
	        	double dcvaduana = Double.parseDouble(cvaduana);
	        	double dcvcompra = Double.parseDouble(cvcompra);
	        	double dcvtransp = Double.parseDouble(cvtransp);
	        	double dingtotal = Double.parseDouble(ingtotal);
	        	
	        	if ((mostriva != null) && (mostriva.equals("S"))) {
	        		dccaduana += Double.parseDouble(ccivaaduana);
	        		dcccompra += Double.parseDouble(ccivacompra);
		        	dcctransp += Double.parseDouble(ccivatransp);
		        	dcvaduana += Double.parseDouble(cvivaaduana);
		        	dcvcompra += Double.parseDouble(cvivacompra);
		        	dcvtransp += Double.parseDouble(cvivatransp);
		        	dingtotal += Double.parseDouble(ingivatotal);
		        	ingtotal = formatNorm.format(dingtotal).replaceAll(",",".");
	        	}
	        	
	        	double dtotalcom = dccaduana + dcccompra + dcctransp;
	        	double dtotalven = dcvaduana + dcvcompra + dcvtransp;
	        	double dbenefici = dingtotal - dtotalcom - dtotalven;
	        	
	        	Grid dataGrid = new Grid();
	        	dataGrid.addColumn("coluname");
	        	dataGrid.addColumn("coluvalu");
	        	
	        	ArrayList<String> row = new ArrayList<String>();
	        	
	        	if (dccaduana > 0) {
		        	row.add("Cost. C. Aduanero");
		        	row.add(String.valueOf(dccaduana));
		        	dataGrid.addRow(row);
		        	hmCatego.put("CATOTAL","Cost. C. Aduanero");
	        	}
	        	
	        	if (dcccompra > 0) {
		        	row = new ArrayList<String>();
		        	row.add("Cost. C. Compra");
		        	row.add(String.valueOf(dcccompra));
		        	dataGrid.addRow(row);
		        	hmCatego.put("CATOTAL","Cost. C. Compra");
	        	}
	        	
	        	if (dcctransp > 0) {
		        	row = new ArrayList<String>();
		        	row.add("Cost. C. Transporte");
		        	row.add(String.valueOf(dcctransp));
		        	dataGrid.addRow(row);
		        	hmCatego.put("CTTOTAL","Cost. C. Transporte");
	        	}
	        	
	        	if (dcvaduana > 0) {
		        	row = new ArrayList<String>();
		        	row.add("Cost. V. Aduanero");
		        	row.add(String.valueOf(dcvaduana));
		        	dataGrid.addRow(row);
		        	hmCatego.put("VATOTAL","Cost. V. Aduanero");
	        	}
	        	
	        	if (dcvcompra > 0) {
		        	row = new ArrayList<String>();
		        	row.add("Cost. V. Compra");
		        	row.add(String.valueOf(dcvcompra));
		        	dataGrid.addRow(row);
		        	hmCatego.put("VPTOTAL","Cost. V. Compra");
	        	}
	        	
	        	if (dcvtransp > 0) {	
		        	row = new ArrayList<String>();
		        	row.add("Cost. V. Transporte");
		        	row.add(String.valueOf(dcvtransp));
		        	dataGrid.addRow(row);
		        	hmCatego.put("VTTOTAL","Cost. V. Transporte");
	        	}
	        	
	        	row = new ArrayList<String>();
	        	row.add("Beneficio");
	        	row.add(String.valueOf(dbenefici));
	        	dataGrid.addRow(row);
	        	hmCatego.put("Benefici","Beneficio");
	        	
	        	coloresGrafica();
	        	GraficoCircular grafCircular = new GraficoCircular();
	        	grafCircular.setCategoryColors(hmColors);
	        	imgfilex = grafCircular.crearGrafica("desgVenta","Desglose Venta", dataGrid);
	        	
	        	benefici = formatDivi.format(dbenefici);
	        	
        	}
        	
        	try {
        		ListDesgloseDetalleBDIn venDetalleBDIn = new ListDesgloseDetalleBDIn();
            	venDetalleBDIn.setValue("idunicox",idunicox);
            	venDetalleBDIn.setValue("idemisor",idemisor);
            	ListDesgloseDetalleBD venDetalleBD = new ListDesgloseDetalleBD(venDetalleBDIn);
            	venDetalleBD.setConnection(con);
            	gddetall = venDetalleBD.execSelect();
        	} catch (Exception e) {
        		
        	}
        	
        	output.setValue("idemisor", idemisor);
        	output.setValue("idunicox", idunicox);
        	output.setValue("imgfilex", imgfilex);
        	output.setValue("ingtotal", ingtotal);
        	output.setValue("benefici", benefici);
        	output.setValue("mostriva", mostriva);
        	output.setGrid("gdcdcost", gdcdcost);
        	output.setGrid("gddscost", gddscost);
        	output.setGrid("gddetall", gddetall);
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
    
    
    public void coloresGrafica(){
		
	    if (hmCatego.containsKey("CATOTAL")){
	    	hmColors.put(hmCatego.get("CATOTAL"), new Color(0,140,184));
	    }

	    if (hmCatego.containsKey("CPTOTAL")){
	    	hmColors.put(hmCatego.get("CPTOTAL"), new Color(238,0,0));
	    }

	    if (hmCatego.containsKey("CTTOTAL")){
	    	hmColors.put(hmCatego.get("CTTOTAL"), new Color(255,132,28));
	    }
	    
	    if (hmCatego.containsKey("VATOTAL")){
	    	hmColors.put(hmCatego.get("VATOTAL"), new Color(8,96,179));
	    }

	    if (hmCatego.containsKey("VPTOTAL")){
	    	hmColors.put(hmCatego.get("VPTOTAL"), new Color(255,255,0));
	    }

	    if (hmCatego.containsKey("VTTOTAL")){
	    	hmColors.put(hmCatego.get("VTTOTAL"), new Color(3,85,162));
	    }
	    
	    if (hmCatego.containsKey("Benefici")){
	    	hmColors.put(hmCatego.get("Benefici"), new Color(34,139,34));
	    }

    }
    
    
}
	