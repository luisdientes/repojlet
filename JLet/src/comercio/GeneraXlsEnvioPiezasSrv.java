package comercio;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import jxl.Workbook;
import utils.PropiedadesJLet;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import comercio.bd.ListEnviosBD;
import comercio.bd.ListEnviosBDIn;
import comercio.bd.ListLineasEnvioBD;
import comercio.bd.ListLineasEnvioBDIn;
import comercio.bd.UpdEnvioBD;
import comercio.bd.UpdEnvioBDIn;
import comercio.bd.UpdLineasBD;
import comercio.bd.UpdLineasBDIn;
import comercio.bd.UpdTaxesBD;
import comercio.bd.UpdTaxesBDIn;
import common.Divisa;
import common.ExcelCreator;

import factura.GeneraFacturaSrv;


public class GeneraXlsEnvioPiezasSrv extends Service {

	ExcelCreator creador = null;
	
	String stfixing = "";
	
	double dbfixing = 1;
	
	int lineaCab 	= 4;
	int nLinea   	= 0;
	   
    String idlineas = "";
    String custotax = ""; 
    String consutax = ""; 
    String fletecst = ""; 
    String itbisimp = ""; 
    String tramadua = ""; 
    String almacena = ""; 
    String movconte = ""; 
    String cargnavi = "";
    String tpproduc = "PI";
	
	DecimalFormat formatDivi = new DecimalFormat("#####0.00");
	
    public GeneraXlsEnvioPiezasSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("codeenvi");
		    input.addVariable("custotax");
		    input.addVariable("idlineas");
		    input.addVariable("consutax");
		    input.addVariable("fletecst");
		    input.addVariable("itbisimp");
		    input.addVariable("tramadua");
		    input.addVariable("almacena");
		    input.addVariable("movconte");
		    input.addVariable("cargnavi");
		    input.addVariable("tpproduc");

			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
			output.addVariable("gdEnvios");
			output.addVariable("filename");
			output.addVariable("txmensaj");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String codeenvi 	= "";

        
        //Varibales de salida
        String filename = "";
        String txmensaj = "";
        Grid gdEnvios = null;
        
        
        
        //Otras Variables
        String pathinpf = "";
        String pathfile = "";
        String cddivisa = "DOP";
        
      
        Grid datosPin = null;
        
        int filaInic 	= 1;
        int calcImpt	= 0;
        
        Workbook workbook;
        
        try {
        	
        	codeenvi = input.getStringValue("codeenvi");
        	tpproduc = input.getStringValue("tpproduc");
        	
        	/* Obtengo si aplico tasas o no */
        	
        	idlineas = input.getStringValue("idlineas"); 
        	custotax = input.getStringValue("custotax");
        	consutax = input.getStringValue("consutax");
        	fletecst = input.getStringValue("fletecst");
        	itbisimp = input.getStringValue("itbisimp");
        	tramadua = input.getStringValue("tramadua");
        	almacena = input.getStringValue("almacena");
        	movconte = input.getStringValue("movconte");
        	cargnavi = input.getStringValue("cargnavi");
        	
        	//Obtengo el fixing del CHF
        	try {
 	        	Divisa divisa = new Divisa();
 	        	divisa.setConnection(con);

 	        	stfixing = divisa.getFixingUSD(cddivisa);
 	        	dbfixing = Double.parseDouble(stfixing);
 	        	
 	        } catch (Exception e) {
 	        	System.out.println("ERROR realizando operaciones de Fixing");
 	        }
        	
        	//Construyo el Nombre
        	filename = "EnvioTseYang_"+ codeenvi +".xls";
        	pathinpf = PropiedadesJLet.getInstance().getProperty("comercio.file.input");
        	pathfile = pathinpf + filename;
        	System.out.println(this.getClass().getName() +" Path: "+ pathinpf +" - Filename: "+ filename +" - ");
        	
        	//Creo el fichero
        	creador = new ExcelCreator();		
    		creador.inicializaEstilos();
    		creador.crearLibro(pathfile);
    		creador.crearHoja(codeenvi);
        	
    		formateoExcel();
    		
    		datosPin = recuperoDatosGrid(codeenvi);
    		
    		pintoLineas(datosPin);

    		int nlineasa = cambioEstadoLineas(codeenvi);
    		System.out.println("Se  han actualizado  "+ nlineasa +" lineas");
    		
    		crearEnvio(codeenvi, filename);
    		
    		//int lineaTasa = insertarTasas();
    		//System.out.println("Se han actualizado  "+ lineaTasa +" lineas en las tasas");
    		
    		creador.cerrarLibro();
    		
    		gdEnvios = getListEnvios(codeenvi);
    		
    		generarFactura(codeenvi);
    		
    		output.setValue("gdEnvios", gdEnvios);
    		output.setValue("filename", filename);
		    output.setValue("txmensaj", txmensaj);
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
    public void formateoExcel(){
        
    	int nColumn = 0;
    	
    	try {
    		
    		//Defino el ancho de las columnas
    		creador.definirLongitudCelda(nColumn++,20);		/* QUANTITY */
			creador.definirLongitudCelda(nColumn++,30);		/* TXMODELO */
			creador.definirLongitudCelda(nColumn++,10);		/* IDCOLORX */
			creador.definirLongitudCelda(nColumn++,20);		/* PRICEUSD */
			creador.definirLongitudCelda(nColumn++,20);		/* P. TOTAL */
			creador.definirLongitudCelda(nColumn++,20);		/* MARGINXX */
			creador.definirLongitudCelda(nColumn++,20);		/* SALEPRIC */
			creador.definirLongitudCelda(nColumn++,30);		/* PROVIDER */
			creador.definirLongitudCelda(nColumn++,20);		/* TXBUYERX */
			creador.definirLongitudCelda(nColumn++,20);		/* TXFUNDIN */
			creador.definirLongitudCelda(nColumn++,10);		/* CDCATEGO */
		    
			nColumn = 0;
			//Incluyo la cabecera de la tabla
			creador.rellenarCelda(lineaCab, nColumn++, "Quantity", creador.getformatoCabecera());					/* TXMARCAX */
			creador.rellenarCelda(lineaCab, nColumn++, "Model", creador.getformatoCabecera());						/* TXMODELO */
			creador.rellenarCelda(lineaCab, nColumn++, "Colour", creador.getformatoCabecera());						/* IDCOLORX */
			creador.rellenarCelda(lineaCab, nColumn++, "P/Unit USD", creador.getformatoCabecera());					/* PRICEUSD */
			creador.rellenarCelda(lineaCab, nColumn++, "P. Total", creador.getformatoCabecera());					/* PRICECHF */
			creador.rellenarCelda(lineaCab, nColumn++, "Marg. 15% USD", creador.getformatoCabecera());				/* MARGINXX */
			creador.rellenarCelda(lineaCab, nColumn++, "Sale P/Unit USD", creador.getformatoCabecera());			/* SALEPRIC */
			creador.rellenarCelda(lineaCab, nColumn++, "Provider", creador.getformatoCabecera());					/* PROVIDER */
			creador.rellenarCelda(lineaCab, nColumn++, "Buyer", creador.getformatoCabecera());						/* TXBUYERX */
			creador.rellenarCelda(lineaCab, nColumn++, "Funding", creador.getformatoCabecera());					/* TXFUNDIN */
			creador.rellenarCelda(lineaCab, nColumn++, "Quality", creador.getformatoCabecera());					/* CDCATEGO */
			
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
    		linEnvioBDIn.setValue("tipoenvi",tpproduc);
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
    	int filaInic = 0;
    	int filaFina = 0;
    	
    	
    	String txmarcax = "";
    	String txmodelo = "";
    	String idcolorx = "";
    	String imeicode = "";
    	String quantity = "";
    	String pricechf = "";
    	String priceusd = "";
    	String porcmarg = "";
    	String txprovid = "";
    	String txbuyerx = "";
    	String txfundin = "";
    	String withboxx = "";
    	String withusbx = "";
    	String idcatego = "";
    	String mcactivo = "";    	    	

    	filaInic = nLinea + 1;
    	
    	for (int i = 0; i < dataPint.rowCount(); i++) {
    		
    		
    		nColumn = 0;
    		
    		try {

	    		quantity = dataPint.getStringCell(i,"quantity");
	    		txmodelo = dataPint.getStringCell(i,"txmodelo");
	    		idcolorx = dataPint.getStringCell(i,"idcolorx");
	    		priceusd = dataPint.getStringCell(i,"priceusd");
	    		porcmarg = dataPint.getStringCell(i,"porcmarg");
	    		txprovid = dataPint.getStringCell(i,"txprovid");
	    		txbuyerx = dataPint.getStringCell(i,"txbuyerx");
	    		txfundin = dataPint.getStringCell(i,"txfundin");
	    		idcatego = dataPint.getStringCell(i,"idcatego");
	    		mcactivo = dataPint.getStringCell(i,"mcactivo");
	    		
	    		String salepric = "";
	    		
	    		try {
	    			double dpriceusd = Double.parseDouble(priceusd);
	    			double dporcmarg = Double.parseDouble(porcmarg);
	    			double doumargen = dpriceusd * dporcmarg / 100;
	    			double dsalepric = dpriceusd + doumargen;
	    			
	    			porcmarg = formatDivi.format(doumargen).replaceAll(",",".");
	    			salepric = formatDivi.format(dsalepric).replaceAll(",",".");
	    		} catch (Exception e) {
	    			System.err.println(" ERROR obteniendo el precio en dolares. "+ e.getMessage());
	    		}
    		
	    		int actLinea = nLinea + 1;
	    		creador.rellenarCelda(nLinea, nColumn++, new BigDecimal(quantity), creador.getformatoEntero());
	    		creador.rellenarCelda(nLinea, nColumn++, txmodelo, creador.getformatoIzquierda());
	    		creador.rellenarCelda(nLinea, nColumn++, idcolorx, creador.getformatoIzquierda());
	    		creador.rellenarCelda(nLinea, nColumn++, new BigDecimal(priceusd), creador.getformato2Decimal());
	    		creador.rellenarFormula(nLinea, nColumn++, "+(A"+ actLinea +"*D"+ actLinea +")", creador.getformato2DecimalGris());
	    		creador.rellenarCelda(nLinea, nColumn++, new BigDecimal(porcmarg), creador.getformato2Decimal());
	    		creador.rellenarCelda(nLinea, nColumn++, new BigDecimal(salepric), creador.getformato2Decimal());
	    		creador.rellenarCelda(nLinea, nColumn++, txprovid, creador.getformatoIzquierda());
	    		creador.rellenarCelda(nLinea, nColumn++, txbuyerx, creador.getformatoIzquierda());
	    		creador.rellenarCelda(nLinea, nColumn++, txfundin, creador.getformatoIzquierda());
	    		creador.rellenarCelda(nLinea, nColumn++, idcatego, creador.getformatoIzquierda());
	    		//creador.rellenarCelda(nLinea, nColumn++, mcactivo, creador.getformatoIzquierda());
	    		
	    		nLinea++;
	    		
    		} catch (Exception e) {
    			System.err.println(this.getClass().getName() +" [ERROR ] Recuperando la línea del Grid "+ i +" corresponderia a la fila "+ nLinea +" del Excel. "+ e.getMessage());
    		}
    	}
    	pintaTotales(String.valueOf(filaInic),String.valueOf(nLinea));    	
    }
    
    public void pintaTotales(String filInicia, String filFinal) {
    	
    	int nColumn = 4;
    	
    	creador.rellenarFormula(nLinea, nColumn++, "+SUM(E"+ filInicia +":E"+ filFinal +")", creador.getformato2DecimalGris());
    	//creador.rellenarFormula(nLinea, nColumn++, "+SUM(F"+ filInicia +":F"+ filFinal +")", creador.getformato2DecimalGris());
    	//creador.rellenarFormula(nLinea, nColumn++, "+SUM(G"+ filInicia +":G"+ filFinal +")", creador.getformato2DecimalGris());
    	//creador.rellenarFormula(nLinea, nColumn++, "+SUM(H"+ filInicia +":H"+ filFinal +")", creador.getformato2DecimalGris());
    	//creador.rellenarFormula(nLinea, nColumn++, "+SUM(I"+ filInicia +":I"+ filFinal +")", creador.getformato2DecimalGris());
    	nLinea++;
    	
    }
    
    public int cambioEstadoLineas(String codeenvi){
    	
    	int numlinea = 0;
    	
    	try {
    		
    		UpdLineasBDIn updLinBDIn = new UpdLineasBDIn();
    		updLinBDIn.setValue("codeenvi",codeenvi);
    		updLinBDIn.setValue("tpproduc",tpproduc);
    		UpdLineasBD updLinBD = new UpdLineasBD(updLinBDIn);
    		updLinBD.setConnection(con);
    		numlinea = updLinBD.execUpdate();
    		
    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() +" [ERROR ] Actualizando el estado de las lineas temporales. ");
    	}
    	
    	return numlinea;
    	
    }
    
    public int crearEnvio(String codeenvi, String filecrea){
    	
    	int numlinea = 0;
    	String fhcreaci = "";
    	
    	try {
    		
    		fhcreaci = fechaHoy();
    		fhcreaci = fechaMySQL(fhcreaci);
    		
    		UpdEnvioBDIn updLinBDIn = new UpdEnvioBDIn();
    		updLinBDIn.setValue("codeenvi",codeenvi);
    		updLinBDIn.setValue("fhcreaci",fhcreaci);
    		updLinBDIn.setValue("fileenvi",filecrea);
    		UpdEnvioBD updLinBD = new UpdEnvioBD(updLinBDIn);
    		updLinBD.setConnection(con);
    		numlinea = updLinBD.execInsert();
    		
    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() +" [ERROR ] Creando el envío. "+ e.getMessage());
    	}
    	
    	return numlinea;
    	
    }
    
    public int insertarTasas(){
    	       
    	String [] arrLineas = null;
    	String [] arrCusTax = null;
    	String [] arrConTax = null;
    	String [] arrfletec = null;
    	String [] arrItbisx = null;
    	String [] arrTramad = null;
    	String [] arrAlmace = null;
    	String [] arrMovCon = null;
    	String [] arrCargaN = null;
    	
    	int numline = 0;
    	
    
    	
    	arrLineas = idlineas.split(",");
    	arrCusTax = custotax.split(",");
    	arrConTax = consutax.split(",");
    	arrfletec = fletecst.split(",");
    	arrItbisx = itbisimp.split(",");
    	arrTramad = tramadua.split(",");
    	arrAlmace = almacena.split(",");
    	arrMovCon = movconte.split(",");
    	arrCargaN = cargnavi.split(",");
    	
    	for(int i=0 ;i< arrLineas.length;i++){
    		
    		try{
	    		UpdTaxesBDIn updTaxeBDIn = new UpdTaxesBDIn();
	    		updTaxeBDIn.setValue("idlineax",arrLineas[i]); 
	    		updTaxeBDIn.setValue("custotax",arrCusTax[i]); 
	    		updTaxeBDIn.setValue("consutax",arrConTax[i]); 
	    		updTaxeBDIn.setValue("fletecst",arrfletec[i]); 
	    		updTaxeBDIn.setValue("itbisimp",arrItbisx[i]); 
	    		updTaxeBDIn.setValue("tramadua",arrTramad[i]); 
	    		updTaxeBDIn.setValue("almacena",arrAlmace[i]); 
	    		updTaxeBDIn.setValue("movconte",arrMovCon[i]); 
	    		updTaxeBDIn.setValue("cargnavi",arrCargaN[i]); 
	
	    		UpdTaxesBD updLinBD = new UpdTaxesBD(updTaxeBDIn);
	    		updLinBD.setConnection(con);
	    		numline+= updLinBD.execInsert();
    		 }catch(Exception ex){
    			 System.out.println("----Error------ Al insertar Lineas en las tasas");
    		 }	
    		
    	}
    	
    	return numline;
    }
    
    public int crearFactura(String codeenvi, String filecrea, String fhfactur){
    	
    	int numlinea = 0;

    	try {
    		
    		UpdEnvioBDIn updLinBDIn = new UpdEnvioBDIn();
    		updLinBDIn.setValue("codeenvi",codeenvi);
    		updLinBDIn.setValue("fhfactur",fhfactur);
    		updLinBDIn.setValue("filefact",filecrea);
    		updLinBDIn.setValue("filefact",filecrea);
    		UpdEnvioBD updLinBD = new UpdEnvioBD(updLinBDIn);
    		updLinBD.setConnection(con);
    		numlinea = updLinBD.execUpdate();
    		
    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() +" [ERROR ] Creando el envío. "+ e.getMessage());
    	}
    	
    	return numlinea;
    	
    }
    
    public Grid getListEnvios(String codeenvi) {
    
    	Grid gdEnvios = null;
    	
		try {
			ListEnviosBDIn envioBDIn = new ListEnviosBDIn();
			//envioBDIn.setValue("codeenvi", codeenvi);
			ListEnviosBD envioBD = new ListEnviosBD(envioBDIn);
			envioBD.setConnection(con);
			gdEnvios = envioBD.execSelect();
			
		} catch (Exception e) {
			System.err.println(this.getClass().getName() +" [ERROR ] Recuperando envíos. "+ e.getMessage());
		}
    
		return gdEnvios;
    }
    
    public void generarFactura(String codeenvi) {
    	
    	try {
    		
    		String fechaHoy = fechaHoy();
    		String hoymysql = fechaMySQL(fechaHoy);
    		
    		ListLineasEnvioBDIn lineasBDIn= new ListLineasEnvioBDIn();
		  	lineasBDIn.setValue("codeenvi",codeenvi);
			ListLineasEnvioBD lineasBD = new ListLineasEnvioBD(lineasBDIn);
			lineasBD.setConnection(con);
			Grid gdLineas = lineasBD.execSelect();
			
			for (int i = 0; i < gdLineas.rowCount(); i++){
				
				String idlineax = String.valueOf(System.currentTimeMillis());
				String codprodu = "";
				String idfactur = "";
				String idemisor = "";
				String idclient = "";
				String fechafac = "";
				String nlineaxx = "";
				String cantidad = "";
				String concepto = "";
				String precioun = "";
				String descuent = "";
				String precioto = "";
				
				codprodu = gdLineas.getStringCell(i,"idproduc");
				idfactur = "99";
				idemisor = "3";
				idclient = "1";
				fechafac = hoymysql;
				nlineaxx = String.valueOf(i);
				cantidad = "1";
				concepto = gdLineas.getStringCell(i,"txmarcax") +" "+ gdLineas.getStringCell(i,"txmodelo") +" - IMEI:"+gdLineas.getStringCell(i,"imeicode");
				
				String priceusd = gdLineas.getStringCell(i,"priceusd");
	    		String porcmarg = gdLineas.getStringCell(i,"porcmarg");
				
				double dpriceusd = Double.parseDouble(priceusd);
    			double dporcmarg = Double.parseDouble(porcmarg);
    			double doumargen = dpriceusd * dporcmarg / 100;
    			double dsalepric = dpriceusd + doumargen;
	    			
				precioun = formatDivi.format(dsalepric).replaceAll(",",".");
				descuent = "0";
				precioto = formatDivi.format(dsalepric).replaceAll(",",".");

				
				factura.bd.UpdLineasBDIn InsLinBDIn= new factura.bd.UpdLineasBDIn();
		    	InsLinBDIn.setValue("idlineax",idlineax);
		    	InsLinBDIn.setValue("codprodu"," ");
	    		InsLinBDIn.setValue("idfactur",idfactur);
	    		InsLinBDIn.setValue("idemisor",idemisor);
	    		InsLinBDIn.setValue("idclient",idclient);
	    		InsLinBDIn.setValue("fechafac",fechafac);
	    		InsLinBDIn.setValue("nlineaxx",nlineaxx);
	    		InsLinBDIn.setValue("cantidad",cantidad);
	    		InsLinBDIn.setValue("concepto",concepto);
	    		InsLinBDIn.setValue("precioun",precioun);
	    		InsLinBDIn.setValue("descuent",descuent);
	    		InsLinBDIn.setValue("precioto",precioto);
	    		InsLinBDIn.setValue("cdestado", "V");
	    		factura.bd.UpdLineasBD insLinBD = new factura.bd.UpdLineasBD(InsLinBDIn);
	    		insLinBD.setConnection(con);
				int liInsert = insLinBD.execInsert();
				
				System.out.println("Se ha insertado "+ liInsert +" nueva linea. "+ i);
				
			}
			
			GeneraFacturaSrv facturaSrv = new GeneraFacturaSrv();
			ObjectIO facturaIn 	= facturaSrv.instanceOfInput();
			ObjectIO facturaOut = facturaSrv.instanceOfOutput();
			
			facturaIn.setValue("emisclie","3");
			facturaIn.setValue("receclie","1");
			facturaIn.setValue("aniofact","2014");
			facturaIn.setValue("tipofact","1");
			facturaIn.setValue("mcagrupa","0");
			facturaIn.setValue("fhfactur",hoymysql);
			
			facturaSrv.setConnection(con);
			facturaSrv.process(facturaIn, facturaOut);
			
			String filecrea = facturaOut.getStringValue("filecrea");
			System.out.println("Se ha generado una nueva factura: "+ filecrea);
			
			crearFactura(codeenvi,filecrea,hoymysql);
			
    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() +" ERROR  generando factura.");
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
	