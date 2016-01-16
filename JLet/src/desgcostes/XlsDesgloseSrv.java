package desgcostes;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import jxl.Workbook;
import stock.bd.ListStockBD;
import stock.bd.ListStockBDIn;
import utils.PropiedadesJLet;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import common.ExcelCreator;
import common.bd.ListDivEmisorBD;
import common.bd.ListDivEmisorBDIn;

import desgcostes.bd.ListCodigoCostesBD;
import desgcostes.bd.ListCodigoCostesBDIn;
import desgcostes.bd.ListDesgloseCostesBD;
import desgcostes.bd.ListDesgloseCostesBDIn;
import desgcostes.bd.ListDesgloseDetalleBD;
import desgcostes.bd.ListDesgloseDetalleBDIn;
import facturacion.parents.FacturaPDF;


public class XlsDesgloseSrv extends Service {
	
	//HEREDA METODOS
	FacturaPDF FRAparen = new FacturaPDF();
	
	Document documento = null;
	PdfWriter writer;
	
	//DATOS DEL DOCUMENTO
	int pagetotal = 1;
	
	int initabla  = 780;
	int anctabla  = 250;
	
	int lineResu  = 250;
	
	int margeniz  = 35;
	int inmargde  = 415;	
	
	
	
	//DATOS DEL INFORME
	ExcelCreator creador = null;
	Grid gdEmisor = null;
	Grid gdCodecs = null;
	String cddivisa = "";
	 
	int nLinea   	= 0;

	//PARÁMETROS A RELLENAR EN LA FACTURA
	String[] arIdUnic = null;
	ArrayList<HashMap<String,String>> arHMcost = new ArrayList<HashMap<String,String>>();
	String benefici = "";
	
	//FORMATOS NUMÉRICOS
	DecimalFormat formatDivi = new DecimalFormat("###,##0.00");
	DecimalFormat formatPorc = new DecimalFormat("##0.00%");

	//PARAMETROS DE ENTRADA
	String idemisor = "";
	String idunicos = "";
	String codinfor = "";


	//PARÁMETROS DE SALIDA
	String namefile = "";
	String filecrea = "";
	
	//OTROS PARÁMETROS
	HashSet<String> hsTotale = new HashSet<String>();
	Grid gdProduc = null;
	
    //COLORES
	String colocorp = "AAAAAA";
	BaseColor colBlanc = new BaseColor(255,255,255);
	BaseColor colCorpo = new BaseColor(13,71,117);
	BaseColor colLetra = new BaseColor(13,71,117);
	BaseColor colCabec = new BaseColor(13,71,117);
	BaseColor colCelda = new BaseColor(245,246,250);
	
	

    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();

			input.addVariable("idemisor");
			input.addVariable("idunicox");
			input.addVariable("codinfor");
						
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
			output.addVariable("txmensaj");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
	public void recuperaInput(ObjectIO input){
		
		try {
    		idemisor = input.getStringValue("idemisor");
    		idunicos = input.getStringValue("idunicox");
    		codinfor = input.getStringValue("codinfor");
			FRAparen.setConexion(this.getConnection());
		} catch (Exception e1) {
			System.err.println(this.getClass().getName() +"- ERROR - "+ e1.getMessage());
			e1.printStackTrace();
		}
		
	}
	
    public void process(ObjectIO input, ObjectIO output){
    	
    	try {
    		
    		 
            Workbook workbook;
    		
    		recuperaInput(input);
    		
    		arIdUnic = idunicos.split(",");
    		
    		//Construyo el Nombre
    		namefile = "DesgloseCostes_TESTING_"+ System.currentTimeMillis() +"_"+ idemisor +".xls";
        	String pathinpf = PropiedadesJLet.getInstance().getProperty("path.gen.invoice");
        	String pathfile = pathinpf + namefile;
        	System.out.println(this.getClass().getName() +" Path: "+ pathinpf +" - Filename: "+ namefile +" - ");
        	
        	//Creo el fichero
        	creador = new ExcelCreator();		
    		creador.inicializaEstilos();
    		creador.crearLibro(pathfile);
    		creador.crearHoja("Vertical");
        	
    		recuperoDatosGrid();
    		
    		formateoExcel();
    		
    		pintoLineas();

    		creador.cerrarLibro();
	        
	    	try {
				output.setValue("filecrea", namefile);
				output.setValue("txmensaj", "Generacion Ok - ");
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	
        } catch (Exception de) {
            System.err.println(de.getMessage());
        } finally {
        	documento.close();
        }
    }
    
    
// ------------------------------------------ START PINTAR DATOS -----------------------------
    
    
    
    public void recuperoDatosGrid(){
    	
    	Grid grOtrain = null;
    	Grid gddscost = null;
    	
    	try {
        	ListDivEmisorBDIn datEmisorBDIn = new ListDivEmisorBDIn();
			datEmisorBDIn.setValue("idemisor", idemisor);
			ListDivEmisorBD datEmisorBD = new ListDivEmisorBD(datEmisorBDIn);
			datEmisorBD.setConnection(con);
			gdEmisor = datEmisorBD.execSelect();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    	
    	try {
        	ListCodigoCostesBDIn codeCostesBDIn = new ListCodigoCostesBDIn();
			ListCodigoCostesBD codeCostesBD = new ListCodigoCostesBD(codeCostesBDIn);
			codeCostesBD.setConnection(con);
			gdCodecs = codeCostesBD.execSelect();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    	
    	
    	for (int i = 0; i < arIdUnic.length; i++){
    	
    		HashMap<String,String> hmDesglo = new HashMap<String,String>();
    		
	    	try {
		    	ListDesgloseDetalleBDIn desDetalleBDIn =	 new ListDesgloseDetalleBDIn();
				desDetalleBDIn.setValue("idunicox",arIdUnic[i]);
				desDetalleBDIn.setValue("idemisor",idemisor);
				desDetalleBDIn.setValue("mcactivo","S");
		    	ListDesgloseDetalleBD desDetalleBD = new ListDesgloseDetalleBD(desDetalleBDIn);
		    	desDetalleBD.setConnection(con);
		    	grOtrain = desDetalleBD.execSelect();
	    	} catch (Exception e1) {
				e1.printStackTrace();
			}
	    	
	    	try {
		    	ListDesgloseCostesBDIn desCosteBDIn =	 new ListDesgloseCostesBDIn();
				desCosteBDIn.setValue("idunicox",arIdUnic[i]);
		    	desCosteBDIn.setValue("idemisor",idemisor);
		    	desCosteBDIn.setValue("mcactivo","S");
		    	ListDesgloseCostesBD desCosteBD = new ListDesgloseCostesBD(desCosteBDIn);
		    	desCosteBD.setConnection(con);
		    	gddscost = desCosteBD.execSelect();
	    	} catch (Exception e1) {
				e1.printStackTrace();
			}

	    	
	    	Grid producto = null;
	    	try {
		    	ListStockBDIn productoBDIn = new ListStockBDIn();
		    	productoBDIn.setValue("idemisor",idemisor);
		    	productoBDIn.setValue("imeicode",arIdUnic[i]);
		    	productoBDIn.setValue("cdestado","NOAPLICA");
		    	ListStockBD productoBD = new ListStockBD(productoBDIn);
		    	productoBD.setConnection(con);
		    	producto = productoBD.execSelect();
	    	} catch (Exception e1) {
				e1.printStackTrace();
			}
		    	
		    	
	    	hmDesglo.put("idunicox",arIdUnic[i]);
	    	try {
		    	hmDesglo.put("codprodu",producto.getStringCell(0, "codprodu"));
		    	hmDesglo.put("txproduc",producto.getStringCell(0, "txmarcax") +" "+ producto.getStringCell(0, "txmodelo"));
	    	} catch (Exception e) {
	    		System.out.println("ERROR - "+ arIdUnic[i]);
	    		e.printStackTrace();
	    	}
	    	
	    	
	    	for (int j = 0; j < grOtrain.rowCount(); j++){
	    		
	    		hmDesglo.put(grOtrain.getStringCell(j,"codedeta"),grOtrain.getStringCell(j,"desvalue"));
	    		
	    	}
	    	
	    	for (int j = 0; j < gddscost.rowCount(); j++){
	    		
	    		hmDesglo.put(gddscost.getStringCell(j,"codedesg"),gddscost.getStringCell(j,"desvalue"));
	    		
	    	}
	    	
	    	arHMcost.add(hmDesglo);
	    	
    	}
    	
    }
    
    public void pintoLineas(){

    	int nColumn = 0;
    	
		creador.rellenarCelda(nLinea, nColumn++, "IMEI", creador.getformatoCabecera());
		creador.rellenarCelda(nLinea, nColumn++, "Codigo", creador.getformatoCabecera());
		creador.rellenarCelda(nLinea, nColumn++, "Producto", creador.getformatoCabecera());
		creador.rellenarCelda(nLinea, nColumn++, "Cod. Factura", creador.getformatoCabecera());
		creador.rellenarCelda(nLinea, nColumn++, "Fecha Factura", creador.getformatoCabecera());
		creador.rellenarCelda(nLinea, nColumn++, "Cliente", creador.getformatoCabecera());
		creador.rellenarCelda(nLinea, nColumn++, "Cd. Cliente", creador.getformatoCabecera());
		
    	for (int j = 0; j < gdCodecs.rowCount(); j++) {
    		creador.rellenarCelda(nLinea, nColumn++, gdCodecs.getStringCell(j, "codedesg"), creador.getformatoCabecera());
    	}
    	
    	nLinea++;
    	
    	
    	for (int i = 0; i < arHMcost.size(); i++) {
    		
    		nColumn = 0;
    		
    		HashMap<String,String> hmUnicox = new HashMap<String,String>();
    		hmUnicox = arHMcost.get(i);
    		
    		try {
    		
	    		creador.rellenarCelda(nLinea, nColumn++, hmUnicox.get("idunicox"), creador.getformatoIzquierda());
	    		
	    		if (hmUnicox.containsKey("codprodu")){
	    			creador.rellenarCelda(nLinea, nColumn++, hmUnicox.get("codprodu"), creador.getformatoIzquierda());
	    		}
	    		if (hmUnicox.containsKey("txproduc")){
	    			creador.rellenarCelda(nLinea, nColumn++, hmUnicox.get("txproduc"), creador.getformatoIzquierda());
	    		}
	    		if (hmUnicox.containsKey("CDFACTUR")){
	    			creador.rellenarCelda(nLinea, nColumn++, hmUnicox.get("CDFACTUR"), creador.getformatoIzquierda());
	    		}
	    		if (hmUnicox.containsKey("FHFACTUR")){
	    			creador.rellenarCelda(nLinea, nColumn++, hmUnicox.get("FHFACTUR"), creador.getformatoIzquierda());
	    		}
	    		if (hmUnicox.containsKey("TXCLIENT")){
	    			creador.rellenarCelda(nLinea, nColumn++, hmUnicox.get("TXCLIENT"), creador.getformatoIzquierda());
	    		}
	    		if (hmUnicox.containsKey("CANALVEN")){
	    			creador.rellenarCelda(nLinea, nColumn++, hmUnicox.get("CANALVEN"), creador.getformatoIzquierda());
	    		}
	    		
	    		
	    		for (int j = 0; j < gdCodecs.rowCount(); j++) {
	    			
	    			if (hmUnicox.containsKey(gdCodecs.getStringCell(j, "codedesg"))){
	    				creador.rellenarCelda(nLinea, nColumn++, new BigDecimal(hmUnicox.get(gdCodecs.getStringCell(j, "codedesg"))), creador.getformato2Decimal());
		    		} else {
		    			creador.rellenarCelda(nLinea, nColumn++, "", creador.getformatoIzquierda());
		    		}
	    		}
	    		
	    		nLinea++;
	    		
    		} catch (Exception e) {
    			System.err.println(this.getClass().getName() +" [ERROR ] Recuperando la línea del Grid "+ i +" corresponderia a la fila "+ nLinea +" del Excel. "+ e.getMessage());
    		}
    	}

    }
    
    
    
//------------------------------------------ END PINTAR DATOS -----------------------------    

    
    public String getFileCreated(){
    	return this.filecrea;    	
    }
    
    public String fechaNormal(String fhfechax){
        
		String fhMySql = "";
		try {
			String [] arrFecha = fhfechax.split("-");
			fhMySql = arrFecha[2]+"/"+ arrFecha[1]+"/"+arrFecha[0];
		} catch (Exception e) {
			return "0000-00-00";
		}
		return fhMySql;
    }

    
    public void colorOtherColors(String colocorp) {
    	
    	if (colocorp.equals("AC1F8D")) {
    		colCorpo = new BaseColor(172,31,141);
    		colLetra = new BaseColor(172,31,141);
    		colCabec = new BaseColor(219,161,206);
    		colCelda = new BaseColor(244,228,240);
    	} else if (colocorp.equals("0080C8")) {
    		colCorpo = new BaseColor(13,71,117);
    		colLetra = new BaseColor(13,71,117);
    		colCabec = new BaseColor(221,225,234);
    		colCelda = new BaseColor(245,246,250);
    	} 
    	
    }
    
    public void formateoExcel(){
        
    	int nColumn = 0;
    	
    	try {
    	
		    //Defino el ancho de las columnas
    		creador.definirLongitudCelda(nColumn++,18);		/* TXIMEIXX */
    		creador.definirLongitudCelda(nColumn++,6);		/* TXIMEIXX */
    		creador.definirLongitudCelda(nColumn++,20);		/* TXIMEIXX */
    		creador.definirLongitudCelda(nColumn++,20);		/* CDFACTUR */
    		creador.definirLongitudCelda(nColumn++,20);		/* FHFACTUR */
    		creador.definirLongitudCelda(nColumn++,20);		/* TXCLIENT */
    		creador.definirLongitudCelda(nColumn++,20);		/* CANALVEN */
    		
    		for (int i = 0; i < gdCodecs().rowCount(); i++) {
    			creador.definirLongitudCelda(nColumn++,16);
    		}
			
			nLinea++;
			
    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() +" [ERROR ] Formateando el excel. ");
    	}
    }

	private Grid gdCodecs() {
		// TODO Auto-generated method stub
		return null;
	}
    
    
}
	

