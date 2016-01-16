package comercio;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import utils.PropiedadesJLet;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import comercio.bd.ListLineasEnvioBD;
import comercio.bd.ListLineasEnvioBDIn;
import comercio.bd.ListLineasEnvioTasasBD;
import comercio.bd.ListLineasEnvioTasasBDIn;
import comercio.bd.UpdEnvioBD;
import comercio.bd.UpdEnvioBDIn;
import comercio.bd.UpdLineasBD;
import comercio.bd.UpdLineasBDIn;
import common.ExcelCreator;


public class ListCotizadorPiezasSrv extends Service {

	ExcelCreator creador = null;
	
	String stfixing = "";
	Grid gridLine = null;
	
	double dbfixing = 1;
	
	int lineaCab 	= 4;
	int nLinea   	= 0;
	
	DecimalFormat formatDivi = new DecimalFormat("#####0.00");

	double dfixindop    = 0;
	
	double dprcustotax	= 0;
	double dprconsutax	= 0;
	double dprfletecst	= 0;
	double dpritbisimp	= 0;
	double dprtramadua	= 0;
	double dpralmacena	= 0;
	double dprmovconte	= 0;
	double dprcargnavi	= 0;
	double dprmargorig	= 0;
	double dprmargmpsp	= 0;
	double dprmargizum	= 0;
	
	double dcustotaxtt 	= 0;
	double dconsutaxtt 	= 0;
	double dfletecsttt 	= 0;
	double ditbisimptt 	= 0;
	double dtramaduatt 	= 0;
	double dalmacenatt 	= 0;
	double dmovcontett 	= 0;
	double dcargnavitt 	= 0;
	
    public ListCotizadorPiezasSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("codeenvi");
			input.addVariable("imptotal");
			input.addVariable("imptotrd");
			input.addVariable("ctusddop");
			input.addVariable("custotax");
			input.addVariable("consutax");
			input.addVariable("fletecst");
			input.addVariable("itbisimp");
			input.addVariable("tramadua");
			input.addVariable("almacena");
			input.addVariable("movconte");
			input.addVariable("cargnavi");
			input.addVariable("prcustotax");
			input.addVariable("prconsutax");
			input.addVariable("prfletecst");
			input.addVariable("pritbisimp");
			input.addVariable("prtramadua");
			input.addVariable("pralmacena");
			input.addVariable("prmovconte");
			input.addVariable("prcargnavi");
			input.addVariable("prmargorig");
			input.addVariable("prmargmpsp");
			input.addVariable("prmargizum");

		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
			output.addVariable("imptotal");
			output.addVariable("custotax");
			output.addVariable("consutax");
			output.addVariable("fletecst");
			output.addVariable("itbisimp");
			output.addVariable("tramadua");
			output.addVariable("almacena");
			output.addVariable("movconte");
			output.addVariable("cargnavi");
			output.addVariable("prcustotax");
			output.addVariable("prconsutax");
			output.addVariable("prfletecst");
			output.addVariable("pritbisimp");
			output.addVariable("prtramadua");
			output.addVariable("pralmacena");
			output.addVariable("prmovconte");
			output.addVariable("prcargnavi");
			output.addVariable("prmargorig");
			output.addVariable("prmargmpsp");
			output.addVariable("prmargizum");
			output.addVariable("txmensaj");

			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String codeenvi     = "";
    	
    	String imptotal 	= "";
    	String fixindop		= "";
    	String custotax 	= "";
    	String consutax 	= "";
    	String fletecst 	= "";
    	String itbisimp 	= "";
    	String tramadua 	= "";
    	String almacena 	= "";
    	String movconte 	= "";
    	String cargnavi 	= "";
    	String prcustotax	= "";
    	String prconsutax	= "";
    	String prfletecst	= "";
    	String pritbisimp	= "";
    	String prtramadua	= "";
    	String pralmacena	= "";
    	String prmovconte	= "";
    	String prcargnavi	= "";
    	String prmargorig	= "";
    	String prmargmpsp	= "";
    	String prmargizum	= "";

    	double dimptotal 	= 0;
    	double dcustotax 	= 0;
    	double dconsutax 	= 0;
    	double dfletecst 	= 0;
    	double ditbisimp 	= 0;
    	double dtramadua 	= 0;
    	double dalmacena 	= 0;
    	double dmovconte 	= 0;
    	double dcargnavi 	= 0;
    	
        //Varibales de salida
        
        //Otras Variables
        int nfactura = 0;
        String filecrea = "";
     
        DecimalFormat deciForm = new DecimalFormat("0.00#");
        
        try {
        	
        	codeenvi = input.getStringValue("codeenvi");
        	//imptotal = input.getStringValue("imptotal").replaceAll("\\.","").replaceAll(",",".");    
        	imptotal = input.getStringValue("imptotrd").replaceAll("\\.","").replaceAll(",",".");
        	fixindop = input.getStringValue("ctusddop").replaceAll("\\.","").replaceAll(",",".");
        	custotax = input.getStringValue("custotax").replaceAll(",",".");    
        	consutax = input.getStringValue("consutax").replaceAll(",",".");    
        	fletecst = input.getStringValue("fletecst").replaceAll(",",".");    
        	itbisimp = input.getStringValue("itbisimp").replaceAll(",",".");    
        	tramadua = input.getStringValue("tramadua").replaceAll(",",".");    
        	almacena = input.getStringValue("almacena").replaceAll(",",".");    
        	movconte = input.getStringValue("movconte").replaceAll(",",".");    
        	cargnavi = input.getStringValue("cargnavi").replaceAll(",",".");    
        	prcustotax = input.getStringValue("prcustotax").replaceAll(",",".");
        	prconsutax = input.getStringValue("prconsutax").replaceAll(",",".");
        	prfletecst = input.getStringValue("prfletecst").replaceAll(",",".");
        	pritbisimp = input.getStringValue("pritbisimp").replaceAll(",",".");
        	prtramadua = input.getStringValue("prtramadua").replaceAll(",",".");
        	pralmacena = input.getStringValue("pralmacena").replaceAll(",",".");
        	prmovconte = input.getStringValue("prmovconte").replaceAll(",",".");
        	prcargnavi = input.getStringValue("prcargnavi").replaceAll(",",".");
        	prmargorig = input.getStringValue("prmargorig").replaceAll(",",".");
        	prmargmpsp = input.getStringValue("prmargmpsp").replaceAll(",",".");
        	prmargizum = input.getStringValue("prmargizum").replaceAll(",",".");
        	
        	try {
        		dfixindop   = Double.parseDouble(fixindop);
        	} catch (Exception e) {
        		new Exception("No se ha recibido el Importe Total");
        	}
        	
        	calculaTotalesParciales(codeenvi);
        	
        	try {
        		dimptotal   = Double.parseDouble(imptotal);
        	} catch (Exception e) {
        		new Exception("No se ha recibido el Importe Total");
        	}
        	
        	
        	
        	
        	try {
        		dcustotax   = Double.parseDouble(custotax);
        	} catch (Exception e) {
        		
        	}
        	
        	try {
        		dconsutax   = Double.parseDouble(consutax);
        	} catch (Exception e) {
        		
        	}
        	
        	try {
        		dfletecst   = Double.parseDouble(fletecst);
        	} catch (Exception e) {
        		
        	}
        	try {
        		ditbisimp   = Double.parseDouble(itbisimp);
        	} catch (Exception e) {
        		
        	}
        	try {
        		dtramadua   = Double.parseDouble(tramadua);
        	} catch (Exception e) {
        		
        	}
        	try {
        		dalmacena   = Double.parseDouble(almacena);
        	} catch (Exception e) {
        		
        	}
        	try {
        		dmovconte   = Double.parseDouble(movconte);
        	} catch (Exception e) {
        		
        	}
        	try {
        		 dcargnavi   = Double.parseDouble(cargnavi);
        	} catch (Exception e) {
        		
        	}
        	try {
        		dprcustotax = Double.parseDouble(prcustotax) / 100;
        	} catch (Exception e) {
        		if (dcustotax > 0){
        			dprcustotax = dcustotax / dcustotaxtt;
        		}
        	}
        	try {
        		dprconsutax = Double.parseDouble(prconsutax) / 100;
        	} catch (Exception e) {
        		if (dconsutax > 0){
        			dprconsutax = dconsutax / dconsutaxtt;
        		}
        	}
        	try {
        		dprfletecst = Double.parseDouble(prfletecst) / 100;
        	} catch (Exception e) {
        		if (dfletecst > 0){
        			dprfletecst = dfletecst / dfletecsttt;
        		}
        	}
        	try {
        		dpritbisimp = Double.parseDouble(pritbisimp) / 100;
        	} catch (Exception e) {
        		if (ditbisimp > 0){
        			dpritbisimp = ditbisimp / ditbisimptt;
        		}
        	}
        	try {
        		dprtramadua = Double.parseDouble(prtramadua) / 100;
        	} catch (Exception e) {
        		if (dtramadua > 0){
        			dprtramadua = dtramadua / dtramaduatt;
        		}
        	}
        	try {
        		dpralmacena = Double.parseDouble(pralmacena) / 100;
        	} catch (Exception e) {
        		if (dalmacena > 0){
        			dpralmacena = dalmacena / dalmacenatt;
        		}
        	}
        	try {
        		dprmovconte = Double.parseDouble(prmovconte) / 100;
        	} catch (Exception e) {
        		if (dmovconte > 0){
        			dprmovconte = dmovconte / dmovcontett;
        		}
        	}
        	try {
        		dprcargnavi = Double.parseDouble(prcargnavi) / 100;
        	} catch (Exception e) {
        		if (dcargnavi > 0){
        			dprcargnavi = dcargnavi / dcargnavitt;
        		}
        	}
        	try {
        		dprmargorig = Double.parseDouble(prmargorig) / 100;
        	} catch (Exception e) {
        		
        	}
        	try {
        		dprmargmpsp = Double.parseDouble(prmargmpsp) / 100;
        	} catch (Exception e) {
        		
        	}
        	try {
        		dprmargizum = Double.parseDouble(prmargizum) / 100;
        	} catch (Exception e) {
        		
        	}        	        
        	
        	filecrea = generarExcel(codeenvi);        	
        	
        	
        	//UNA VEZ OBTENIDOS LOS PORCENTAJES, CALCULO LOS IMPORTES TOTALES
        	if (dcustotax <= 0){
        		dcustotax = dimptotal * dprcustotax;
        	}
        	
        	if (dconsutax <= 0){
        		dconsutax = dimptotal * dprconsutax;
        	}
        	if (dfletecst <= 0){
        		dfletecst = dimptotal * dprfletecst;
        	}
        	if (ditbisimp <= 0){
        		ditbisimp = dimptotal * dpritbisimp;
        	}
        	if (dtramadua <= 0){
        		dtramadua = dimptotal * dprtramadua;
        	}
        	if (dalmacena <= 0){
        		dalmacena = dimptotal * dpralmacena;
        	}
        	if (dmovconte <= 0){
        		dmovconte = dimptotal * dprmovconte;
        	}
        	if (dcargnavi <= 0){
        		dcargnavi = dimptotal * dprcargnavi;
        	}
        	
        	System.out.println("Valor imptotal (RD$) -| "+ dimptotal +" - ");
        	System.out.println("Valor fixindop -| "+ dfixindop +" - ");        	
        	System.out.println("Valor custotax -| "+ dcustotax +" - ");
        	System.out.println("Valor consutax -| "+ dconsutax +" - ");
        	System.out.println("Valor fletecst -| "+ dfletecst +" - ");
        	System.out.println("Valor itbisimp -| "+ ditbisimp +" - ");
        	System.out.println("Valor tramadua -| "+ dtramadua +" - ");
        	System.out.println("Valor almacena -| "+ dalmacena +" - ");
        	System.out.println("Valor movconte -| "+ dmovconte +" - ");
        	System.out.println("Valor cargnavi -| "+ dcargnavi +" - ");
        	System.out.println("Valor prcustotax -| "+ dprcustotax +" - ");
        	System.out.println("Valor prconsutax -| "+ dprconsutax +" - ");
        	System.out.println("Valor prfletecst -| "+ dprfletecst +" - ");
        	System.out.println("Valor pritbisimp -| "+ dpritbisimp +" - ");
        	System.out.println("Valor prtramadua -| "+ dprtramadua +" - ");
        	System.out.println("Valor pralmacena -| "+ dpralmacena +" - ");
        	System.out.println("Valor prmovconte -| "+ dprmovconte +" - ");
        	System.out.println("Valor prcargnavi -| "+ dprcargnavi +" - ");
        	System.out.println("Valor prmargorig -| "+ dprmargorig +" - ");
        	System.out.println("Valor prmargmpsp -| "+ dprmargmpsp +" - ");
        	System.out.println("Valor prmargizum -| "+ dprmargizum +" - ");
		    
        	try {
	        	imptotal   = deciForm.format(dimptotal).replaceAll(",",".");
	        	custotax   = deciForm.format(dcustotax).replaceAll(",",".");
	        	consutax   = deciForm.format(dconsutax).replaceAll(",",".");
	        	fletecst   = deciForm.format(dfletecst).replaceAll(",",".");
	        	itbisimp   = deciForm.format(ditbisimp).replaceAll(",",".");
	        	tramadua   = deciForm.format(dtramadua).replaceAll(",",".");
	        	almacena   = deciForm.format(dalmacena).replaceAll(",",".");
	        	movconte   = deciForm.format(dmovconte).replaceAll(",",".");
	        	cargnavi   = deciForm.format(dcargnavi).replaceAll(",",".");
	        	prcustotax = deciForm.format(dprcustotax).replaceAll(",",".");
	        	prconsutax = deciForm.format(dprconsutax).replaceAll(",",".");
	        	prfletecst = deciForm.format(dprfletecst).replaceAll(",",".");
	        	pritbisimp = deciForm.format(dpritbisimp).replaceAll(",",".");
	        	prtramadua = deciForm.format(dprtramadua).replaceAll(",",".");
	        	pralmacena = deciForm.format(dpralmacena).replaceAll(",",".");
	        	prmovconte = deciForm.format(dprmovconte).replaceAll(",",".");
	        	prcargnavi = deciForm.format(dprcargnavi).replaceAll(",",".");
	        	prmargorig = deciForm.format(dprmargorig).replaceAll(",",".");
	        	prmargmpsp = deciForm.format(dprmargmpsp).replaceAll(",",".");
	        	prmargizum = deciForm.format(dprmargizum).replaceAll(",",".");
        	} catch (Exception e) {
        		System.err.println(" - ERROR - formateando los Double en String "+ e.getMessage());
        	}
        	
        	
        	output.setValue("imptotal",imptotal);
        	output.setValue("custotax",custotax);
        	output.setValue("consutax",consutax);
        	output.setValue("fletecst",fletecst);
        	output.setValue("itbisimp",itbisimp);
        	output.setValue("tramadua",tramadua);
        	output.setValue("almacena",almacena);
        	output.setValue("movconte",movconte);
        	output.setValue("cargnavi",cargnavi);
        	output.setValue("prcustotax",prcustotax);
        	output.setValue("prconsutax",prconsutax);
        	output.setValue("prfletecst",prfletecst);
        	output.setValue("pritbisimp",pritbisimp);
        	output.setValue("prtramadua",prtramadua);
        	output.setValue("pralmacena",pralmacena);
        	output.setValue("prmovconte",prmovconte);
        	output.setValue("prcargnavi",prcargnavi);
        	output.setValue("prmargorig",prmargorig);
        	output.setValue("prmargmpsp",prmargmpsp);
        	output.setValue("prmargizum",prmargizum);
		    
		    output.setValue("txmensaj", " OK");

        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
    
    public void calculaTotalesParciales(String codeenvi) throws Exception{
    	
    	ListLineasEnvioTasasBDIn lineasBDIn= new ListLineasEnvioTasasBDIn();
	  	lineasBDIn.setValue("codeenvi",codeenvi);
	  	ListLineasEnvioTasasBD lineasBD = new ListLineasEnvioTasasBD(lineasBDIn);
		lineasBD.setConnection(con);
		gridLine = lineasBD.execSelect();
		
		for (int i = 0; i < gridLine.rowCount(); i++){
			
			Double dquantity = Double.parseDouble(gridLine.getStringCell(i,"quantity"));
			Double dpriceusd = Double.parseDouble(gridLine.getStringCell(i,"priceusd"));
			
			if (gridLine.getStringCell(i,"custotax").equals("S")){
				dcustotaxtt += dquantity * dpriceusd * dfixindop;
			}
			
			if (gridLine.getStringCell(i,"consutax").equals("S")){
				dconsutaxtt += dquantity * dpriceusd * dfixindop;
			}
			
			if (gridLine.getStringCell(i,"fletecst").equals("S")){
				dfletecsttt += dquantity * dpriceusd * dfixindop;
			}
			
			if (gridLine.getStringCell(i,"itbisimp").equals("S")){
				ditbisimptt += dquantity * dpriceusd * dfixindop;
			}
			
			if (gridLine.getStringCell(i,"tramadua").equals("S")){
				dtramaduatt += dquantity * dpriceusd * dfixindop;
			}
			
			if (gridLine.getStringCell(i,"almacena").equals("S")){
				dalmacenatt += dquantity * dpriceusd * dfixindop;
			}
			
			if (gridLine.getStringCell(i,"movconte").equals("S")){
				dmovcontett += dquantity * dpriceusd * dfixindop;
			}

			if (gridLine.getStringCell(i,"cargnavi").equals("S")){
				dcargnavitt += dquantity * dpriceusd * dfixindop;
			}
		}
		
		
    	
    }
    
    
    
    
    public String generarExcel(String codeenvi){
    	
    	String filecrea = "";
		
		String filename = "";
		String pathinpf = "";
		String pathfile = "";
		
		
    	try {
    		/*
    		ListLineasEnvioTasasBDIn lineasBDIn= new ListLineasEnvioTasasBDIn();
		  	lineasBDIn.setValue("codeenvi",codeenvi);
		  	ListLineasEnvioTasasBD lineasBD = new ListLineasEnvioTasasBD(lineasBDIn);
			lineasBD.setConnection(con);
			gridLine = lineasBD.execSelect();	
    		*/
    		
			//Construyo el Nombre
        	filename = "CotizacionTradens_"+ codeenvi +".xls";
        	pathinpf = PropiedadesJLet.getInstance().getProperty("comercio.file.input");
        	pathfile = pathinpf + filename;
        	System.out.println(this.getClass().getName() +" Path: "+ pathinpf +" - Filename: "+ filename +" - ");
        	
        	//Creo el fichero
        	creador = new ExcelCreator();		
    		creador.inicializaEstilos();
    		creador.crearLibro(pathfile);
    		creador.crearHoja(codeenvi);
        	
    		formateoExcel();
    		
    		pintoLineas(gridLine);

    		
    		crearCotizacion(codeenvi, filename);
    		
    	} catch (Exception e) {
    		
    	} finally {
    		creador.cerrarLibro();
    	}
    	
    	return filecrea;
    	
    }
    
    public void obtengoFixingUSD(String cddivisa){
        
    	Grid gdDivisa = null;
    	
    	try {
    	
		    InitDivisasSrv initDivisasSrv = new InitDivisasSrv();
		    ObjectIO inputDiv  = initDivisasSrv.instanceOfInput();
		    ObjectIO outputDiv = initDivisasSrv.instanceOfOutput();
		    initDivisasSrv.setConnection(con);
		    initDivisasSrv.process(inputDiv, outputDiv);
		    
		    gdDivisa = outputDiv.getGrid("gdDivisa");
		    
		    boolean fixingOK = false;
		    
		    for (int i = 0; i < gdDivisa.rowCount(); i++){
		    	
		    	if (cddivisa.equals(gdDivisa.getStringCell(i, "diviscam"))){
		    		stfixing = gdDivisa.getStringCell(i, "fixingxx");
		    		dbfixing = Double.parseDouble(stfixing);
		    		fixingOK = true;
		    	}
		    	
		    }
		    
		    if (!fixingOK) {
		    	throw new Exception("ERROR - Recuperando el fixing");
		    }
		    
    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() +" [ERROR ] Recuperando el fixing de USD "+ cddivisa);
    	}
    }
    
    public void formateoExcel(){
        
    	int nColumn = 0;
    	
    	try {
    	
		    //Defino el ancho de las columnas
			creador.definirLongitudCelda(nColumn++,16);		/* QUANTITY */
    		creador.definirLongitudCelda(nColumn++,20);		/* TXMARCAX */
			creador.definirLongitudCelda(nColumn++,40);		/* TXMODELO */
			creador.definirLongitudCelda(nColumn++,10);		/* IDCOLORX */
			creador.definirLongitudCelda(nColumn++,12);		/* SALEPRIC */
			creador.definirLongitudCelda(nColumn++,12);		/* SALERDOP */
			creador.definirLongitudCelda(nColumn++,12);		/* CUSTOTAX */
			creador.definirLongitudCelda(nColumn++,12);		/* CONSUTAX */
			creador.definirLongitudCelda(nColumn++,12);		/* FLETECST */
			creador.definirLongitudCelda(nColumn++,12);		/* ITBISIMP */
			creador.definirLongitudCelda(nColumn++,12);		/* TRAMADUA */
			creador.definirLongitudCelda(nColumn++,12);		/* ALMACENA */
			creador.definirLongitudCelda(nColumn++,12);		/* CARGNAVI */
			creador.definirLongitudCelda(nColumn++,12);		/* MOVCONTE */
			creador.definirLongitudCelda(nColumn++,12);		/* COSTETOT */
			creador.definirLongitudCelda(nColumn++,12);		/* PRICMPSP */
			creador.definirLongitudCelda(nColumn++,12);		/* PRICIZUM */

			nColumn = 0;
			//Incluyo la cabecera de la tabla
			creador.rellenarCelda(lineaCab, nColumn++, "Quantity", creador.getformatoCabecera());					/* QUANTITY */
			creador.rellenarCelda(lineaCab, nColumn++, "Make", creador.getformatoCabecera());						/* TXMARCAX */
			creador.rellenarCelda(lineaCab, nColumn++, "Model", creador.getformatoCabecera());						/* TXMODELO */
			creador.rellenarCelda(lineaCab, nColumn++, "Colour", creador.getformatoCabecera());						/* IDCOLORX */
			creador.rellenarCelda(lineaCab, nColumn++, "Sale P/Unit USD", creador.getformatoCabecera());			/* IDCOLORX */
			creador.rellenarCelda(lineaCab, nColumn++, "Sale P/Unit RD$", creador.getformatoCabecera());			/* SALEPRIC */
			creador.rellenarCelda(lineaCab, nColumn++, "Gravamen Adu.", creador.getformatoCabecera());				/* CUSTOTAX */
			creador.rellenarCelda(lineaCab, nColumn++, "Sel. Consumo", creador.getformatoCabecera());				/* CONSUTAX */
			creador.rellenarCelda(lineaCab, nColumn++, "Flete Cost", creador.getformatoCabecera());					/* FLETECST */
			creador.rellenarCelda(lineaCab, nColumn++, "ITBIS Imp.", creador.getformatoCabecera());					/* ITBISIMP */
			creador.rellenarCelda(lineaCab, nColumn++, "Tram. Aduana", creador.getformatoCabecera());				/* TRAMADUA */
			creador.rellenarCelda(lineaCab, nColumn++, "Almacenaje", creador.getformatoCabecera());					/* ALMACENA */
			creador.rellenarCelda(lineaCab, nColumn++, "Carga Naviera", creador.getformatoCabecera());				/* CARGANAV */
			creador.rellenarCelda(lineaCab, nColumn++, "Trans. Contenedor", creador.getformatoCabecera());			/* MOVCONTE */
			creador.rellenarCelda(lineaCab, nColumn++, "Coste Total", creador.getformatoCabecera());				/* CARGNAVI */
			creador.rellenarCelda(lineaCab, nColumn++, "Precio MallProShop", creador.getformatoCabecera());			/* PRICMPSP */
			creador.rellenarCelda(lineaCab, nColumn++, "Precio Izumba", creador.getformatoCabecera());				/* PRICIZUM */
			
			nLinea = lineaCab + 1;
			
    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() +" [ERROR ] Formateando el excel. ");
    	}
    }
    
    public Grid recuperoDatosGrid(String codenvio) {
    	
    	Grid lineasEn = null;
    	
    	try {
    		ListLineasEnvioBDIn linEnvioBDIn = new ListLineasEnvioBDIn();
    		linEnvioBDIn.setValue("codeenvi", null);
    		ListLineasEnvioBD linEnvioBD = new ListLineasEnvioBD(linEnvioBDIn);
    		linEnvioBD.setConnection(con);
    		lineasEn = linEnvioBD.execSelect();
    		
    		if (lineasEn.rowCount() < 1){
    			throw new Exception("No existe ninguna línea para este pedido");
    		}
    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() +" [ERROR ] Recuperando las líneas del envío. "+ e.getMessage());
    	}
    	
		return lineasEn;
    	
    }
    
    public void pintoLineas(Grid dataPint){

    	int nColumn = 0;
    	
    	String txmarcax = "";
    	String txmodelo = "";
    	String idcolorx = "";
    	String quantity = "";
    	String priceusd = "";
    	String porcmarg = "";   	 
    	
		String custotax	= "";
		String consutax	= "";
		String fletecst	= "";
		String itbisimp	= "";
		String tramadua	= "";
		String almacena	= "";
		String movconte	= "";
		String cargnavi	= "";
		String margmpsp	= "";
		String margizum	= "";


    	for (int i = 0; i < dataPint.rowCount(); i++) {
    		
    		double impcalcu = 0;
    		nColumn = 0;
    		
    		try {

	    		quantity = dataPint.getStringCell(i,"quantity");
	    		txmarcax = dataPint.getStringCell(i,"txmarcax");
	    		txmodelo = dataPint.getStringCell(i,"txmodelo");
	    		idcolorx = dataPint.getStringCell(i,"idcolorx");
	    		priceusd = dataPint.getStringCell(i,"priceusd");
	    		porcmarg = dataPint.getStringCell(i,"porcmarg");
	    		
	    		String costetot = "";
	    		String salepric = "";
	    		String salprdop = "";
	    		
	    		try {
	    			double dpriceusd = Double.parseDouble(priceusd);
	    			double dporcmarg = Double.parseDouble(porcmarg);
	    			double doumargen = dpriceusd * dporcmarg / 100;
	    			double dsalepric = dpriceusd + doumargen;
	    			
	    			double dsalprdop = dsalepric * dfixindop;
	    			
	    			impcalcu = dsalprdop;
	    			
	    			double dcustotax	= 0;
	    			double dconsutax	= 0;
	    			double dfletecst	= 0;
	    			double ditbisimp	= 0;
	    			double dtramadua	= 0;
	    			double dalmacena	= 0;
	    			double dmovconte	= 0;
	    			double dcargnavi	= 0;

	    			if (gridLine.getStringCell(i,"custotax").equals("S")){
	    				dcustotax	= impcalcu * dprcustotax;
	    			}
	    			
	    			if (gridLine.getStringCell(i,"consutax").equals("S")){
	    				dconsutax	= impcalcu * dprconsutax;
	    			}
	    			
	    			if (gridLine.getStringCell(i,"fletecst").equals("S")){
	    				dfletecst	= impcalcu * dprfletecst;
	    			}
	    			
	    			if (gridLine.getStringCell(i,"itbisimp").equals("S")){
	    				ditbisimp	= impcalcu * dpritbisimp;
	    			}
	    			
	    			if (gridLine.getStringCell(i,"tramadua").equals("S")){
	    				dtramadua	= impcalcu * dprtramadua;
	    			}
	    			
	    			if (gridLine.getStringCell(i,"almacena").equals("S")){
	    				dalmacena	= impcalcu * dpralmacena;
	    			}
	    			
	    			if (gridLine.getStringCell(i,"movconte").equals("S")){
	    				dmovconte	= impcalcu * dprmovconte;
	    			}

	    			if (gridLine.getStringCell(i,"cargnavi").equals("S")){
	    				dcargnavi	= impcalcu * dprcargnavi;
	    			}
	    			
	    			double dcostetot	= impcalcu + dcustotax + dconsutax + dfletecst + ditbisimp + dtramadua + dalmacena + dmovconte + dcargnavi;
	    			
	    			double dmargmpsp	= dcostetot + (dcostetot * dprmargmpsp);
	    			double dmargizum	= dmargmpsp + (dmargmpsp * dprmargizum);
	    			
	    			custotax	= formatDivi.format(dcustotax).replaceAll(",",".");
	    			consutax	= formatDivi.format(dconsutax).replaceAll(",",".");
	    			fletecst	= formatDivi.format(dfletecst).replaceAll(",",".");
	    			itbisimp	= formatDivi.format(ditbisimp).replaceAll(",",".");
	    			tramadua	= formatDivi.format(dtramadua).replaceAll(",",".");
	    			almacena	= formatDivi.format(dalmacena).replaceAll(",",".");
	    			movconte	= formatDivi.format(dmovconte).replaceAll(",",".");
	    			cargnavi	= formatDivi.format(dcargnavi).replaceAll(",",".");
	    			margmpsp	= formatDivi.format(dmargmpsp).replaceAll(",",".");
	    			margizum	= formatDivi.format(dmargizum).replaceAll(",",".");

	    			costetot	= formatDivi.format(dcostetot).replaceAll(",",".");
	    			
	    			porcmarg = formatDivi.format(doumargen).replaceAll(",",".");
	    			salepric = formatDivi.format(dsalepric).replaceAll(",",".");
	    			salprdop = formatDivi.format(dsalprdop).replaceAll(",",".");
	    		} catch (Exception e) {
	    			System.err.println(" ERROR obteniendo el precio en dolares. "+ e.getMessage());
	    		}
    		
	    		
	    		int lineaFor = nLinea + 1;
	    		String formula = "+F"+ lineaFor +"+G"+ lineaFor +"+H"+ lineaFor +"+I"+ lineaFor +"+J"+ lineaFor +"+K"+ lineaFor +"+L"+ lineaFor +"+M"+ lineaFor +"+N"+ lineaFor;

	    		creador.rellenarCelda(nLinea, nColumn++, new BigDecimal(quantity), creador.getformatoEntero());
	    		creador.rellenarCelda(nLinea, nColumn++, txmarcax, creador.getformatoIzquierda());
	    		creador.rellenarCelda(nLinea, nColumn++, txmodelo, creador.getformatoIzquierda());
	    		creador.rellenarCelda(nLinea, nColumn++, idcolorx, creador.getformatoIzquierda());
	    		creador.rellenarCelda(nLinea, nColumn++, new BigDecimal(salepric), creador.getformato2Decimal());
	    		creador.rellenarCelda(nLinea, nColumn++, new BigDecimal(salprdop), creador.getformato2Decimal());
	    		creador.rellenarCelda(nLinea, nColumn++, new BigDecimal(custotax), creador.getformato2Decimal());
	    		creador.rellenarCelda(nLinea, nColumn++, new BigDecimal(consutax), creador.getformato2Decimal());
	    		creador.rellenarCelda(nLinea, nColumn++, new BigDecimal(fletecst), creador.getformato2Decimal());
	    		creador.rellenarCelda(nLinea, nColumn++, new BigDecimal(itbisimp), creador.getformato2Decimal());
	    		creador.rellenarCelda(nLinea, nColumn++, new BigDecimal(tramadua), creador.getformato2Decimal());
	    		creador.rellenarCelda(nLinea, nColumn++, new BigDecimal(almacena), creador.getformato2Decimal());
	    		creador.rellenarCelda(nLinea, nColumn++, new BigDecimal(movconte), creador.getformato2Decimal());
	    		creador.rellenarCelda(nLinea, nColumn++, new BigDecimal(cargnavi), creador.getformato2Decimal());
	    		creador.rellenarFormula(nLinea, nColumn++, formula, creador.getformato2Decimal());
                creador.rellenarCelda(nLinea, nColumn++, new BigDecimal(margmpsp), creador.getformato2Decimal());
                creador.rellenarCelda(nLinea, nColumn++, new BigDecimal(margizum), creador.getformato2Decimal());

                //creador.rellenarCelda(nLinea, nColumn++, new BigDecimal(costetot), creador.getformato2Decimal());
                
                
	    		//creador.rellenarCelda(nLinea, nColumn++, mcactivo, creador.getformatoIzquierda());
	    		
	    		nLinea++;
	    		
    		} catch (Exception e) {
    			System.err.println(this.getClass().getName() +" [ERROR ] Recuperando la línea del Grid "+ i +" corresponderia a la fila "+ nLinea +" del Excel. "+ e.getMessage());
    		} 
    		
    	}
    	
    }
    
    public int cambioEstadoLineas(String codeenvi){
    	
    	int numlinea = 0;
    	
    	try {
    		
    		UpdLineasBDIn updLinBDIn = new UpdLineasBDIn();
    		updLinBDIn.setValue("codeenvi",codeenvi);
    		UpdLineasBD updLinBD = new UpdLineasBD(updLinBDIn);
    		updLinBD.setConnection(con);
    		numlinea = updLinBD.execUpdate();
    		
    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() +" [ERROR ] Actualizando el estado de las lineas temporales. ");
    	}
    	
    	return numlinea;
    	
    }
    
    public int crearCotizacion(String codeenvi, String filecrea){
    	
    	int numlinea = 0;
    	String fhcreaci = "";
    	
    	try {
    		
    		fhcreaci = fechaHoy();
    		fhcreaci = fechaMySQL(fhcreaci);
    		
    		UpdEnvioBDIn updLinBDIn = new UpdEnvioBDIn();
    		updLinBDIn.setValue("codeenvi",codeenvi);
    		updLinBDIn.setValue("fhcotiza",fhcreaci);
    		updLinBDIn.setValue("filecoti",filecrea);
    		UpdEnvioBD updLinBD = new UpdEnvioBD(updLinBDIn);
    		updLinBD.setConnection(con);
    		numlinea = updLinBD.execUpdate();
    		
    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() +" [ERROR ] Creando el envío. "+ e.getMessage());
    	}
    	
    	return numlinea;
    	
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
    
    public String fechaHoy(){
        
    	String fechaHoy = "dd/mm/yyyy";
    	
    	try {
		    Calendar cal = GregorianCalendar.getInstance();
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    
		    System.out.println(" Fecha Hoy: "+sdf.format(cal.getTime()));
		    fechaHoy = sdf.format(cal.getTime());
		    
    	} catch (Exception e) {
    		fechaHoy = "01/01/2014";
    	}
    	
		return fechaHoy;
	    
    }
       
}
	