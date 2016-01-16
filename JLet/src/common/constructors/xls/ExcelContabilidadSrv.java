package common.constructors.xls;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;

import utils.PropiedadesJLet;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import common.varstatic.VariablesStatic;


public class ExcelContabilidadSrv extends Service {

	int nLinea = 10;
	
	BasicExcel creador = null;
	
	public ObjectIO instanceOfInput() {
		ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("username");
			input.addVariable("idemisor");
			input.addVariable("filename");
			input.addVariable("hojaname");
			input.addVariable("fileimgx");
			input.addVariable("gdParame");
			input.addVariable("gdValore");
			input.addVariable("gdResult");
			input.addVariable("creador");
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
			output.addVariable("creador");
			output.addVariable("cderror");
			output.addVariable("txerror");

		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    public void process(ObjectIO input, ObjectIO output) throws Exception {
        
    	//Varibales de entrada
        String username  = "";
        String idemisor  = "";
        String filename  = "";
        String hojaname  = "";
        String txcatego  = "";
        String fileimgx  = "";
        Grid gdParame    = null;
        Grid gdValore    = null;
        Grid gdResult    = null;
        
        
        //Varibales de salida
        String filecrea  = "";
        String cderror   = "";
        String txerror   = "";

        //Variables de clase
        ArrayList<String>  arNomCam = new ArrayList<String>();
        ArrayList<String>  arCdCamp = new ArrayList<String>();
        ArrayList<Integer> arAnchoc = new ArrayList<Integer>();
        ArrayList<String>  arAlinea = new ArrayList<String>();
        ArrayList<String>  arTipCel = new ArrayList<String>();
        String infoname = "";
        String tinforme = "";
        String pathrepo = "";
        String random3c = "";
        String maskhoyx = "";
        
        
        try {
        	
        	
        	username = input.getStringValue("username");
        	idemisor = input.getStringValue("idemisor");
        	filename = input.getStringValue("filename");
        	hojaname = input.getStringValue("hojaname");
        	fileimgx = input.getStringValue("fileimgx");
        	gdParame = input.getGrid("gdParame");
        	gdValore = input.getGrid("gdValore");
        	gdResult = input.getGrid("gdResult");
        	
        	try {
	        	creador =  (BasicExcel) input.getValue("creador");
        	} catch (Exception e) {
        		creador = null;
        	}
        	
        	pathrepo = PropiedadesJLet.getInstance().getProperty("files.path");
        	random3c = VariablesStatic.randomCaracteres(3);
        	
        	tinforme = "genericJLetExcel";
        	
        	try {
        		String fechahoy = VariablesStatic.fechaHoy();
        		maskhoyx = fechahoy.substring(3,5) + fechahoy.substring(0,2);
        	} catch (Exception e) {
        		
        	}
        	
        	if (creador == null) {
        		if ((filename != null) && (!filename.equals(""))) {
        			filecrea = filename;
        		} else {
        			filecrea =  username + maskhoyx +"_"+ random3c +"_"+ tinforme +".xlsx";
        		}
	        	
        		if ((hojaname == null) || (hojaname.equals(""))) {
        			hojaname = "JLet export";
        		}
        		
	        	creador = new BasicExcel(tinforme, pathrepo, filecrea, hojaname);
        	} else {
        		filecrea = filename;
	        	creador.CrearHoja(hojaname);
        	}
        	
        	for (int i = 0; i < gdValore.rowCount(); i++) {
        		arNomCam.add(gdValore.getStringCell(i,"cabcampo"));
        		arCdCamp.add(gdValore.getStringCell(i,"cdcampox"));
    			arAnchoc.add(Integer.parseInt(gdValore.getStringCell(i,"anchocam")));
    			arTipCel.add(gdValore.getStringCell(i,"tipocamp"));
    			arAlinea.add(gdValore.getStringCell(i,"alincamp"));
    		}
        	
    		creador.establecerAnchoColumnas(arAnchoc);
    		
    		XSSFColor colorCorp = creador.crearColor(250,0,0);
    	
    		try {

    			String stcolCor = VariablesStatic.getColorDecimal(idemisor);
	    		String [] colorCor = stcolCor.split(",");
	    		colorCorp = creador.crearColor(Integer.parseInt(colorCor[0]),Integer.parseInt(colorCor[1]),Integer.parseInt(colorCor[2]));
    		
    		} catch (Exception e ){
    			Logger.getLogger("LRA").warning(this.getClass().getName() +" - Error recupuerando color corporativo "+  e.getMessage());
    		}
    		
    		Font fontcorp = creador.crearFuente(10, "Trebuchet MS", IndexedColors.WHITE.getIndex(), false, true);
    		
    		XSSFCellStyle estcorpoI = creador.crearCellStyle(colorCorp,fontcorp,"I");
    		XSSFCellStyle estcorpoC = creador.crearCellStyle(colorCorp,fontcorp,"C");
    		XSSFCellStyle estcorpoD = creador.crearCellStyle(colorCorp,fontcorp,"D");
    		
    		Font fontrojo = creador.crearFuente(10, "Trebuchet MS", IndexedColors.RED.getIndex(), false, true);
    		XSSFColor colorRojo = creador.crearColor(255,215,215);
    		
    		XSSFCellStyle estrojoxI = creador.crearCellStyle(colorRojo,fontrojo,"I");
    		XSSFCellStyle estrojoxC = creador.crearCellStyle(colorRojo,fontrojo,"C");
    		XSSFCellStyle estrojoxD = creador.crearCellStyle(colorRojo,fontrojo,"D");
    		
    		Font fontnorm = creador.crearFuente(10, "Trebuchet MS", IndexedColors.GREY_80_PERCENT.getIndex(), false, true);
    		
    	    XSSFColor colorNorm = creador.crearColor(254,254,254);				// CASI BLANCO
    	    XSSFCellStyle estnormI = creador.crearCellStyle(null,fontnorm,"I");
    	    XSSFCellStyle estnormC = creador.crearCellStyle(null,fontnorm,"C");
    	    XSSFCellStyle estnormD = creador.crearCellStyle(null,fontnorm,"D");
    	    
    	    String pathimgx = PropiedadesJLet.getInstance().getProperty("path.img.logoinvoice");
    	    String nameimgx = "xls_" +VariablesStatic.getLogoEmisor(idemisor,"0").toLowerCase();
    	    
    	    creador.insertaImagen(pathimgx,nameimgx,0,0);

    	    infoname = "Contabilidad";
    	    
    	    nLinea = 2;
    	    creador.combinarCeldas(nLinea, nLinea, 0, 4);
    		creador.rellenarCelda(nLinea++, 0, "Datos Informe", estcorpoC);
    		creador.rellenarCelda(nLinea, 0, "Informe", estcorpoC);
    		creador.combinarCeldas(nLinea, nLinea, 1, 4);
    		creador.rellenarCelda(nLinea++, 1, infoname, estnormI);
    		/*
    		creador.rellenarCelda(nLinea, 0, "Usuario", estcorpoC);
    		creador.combinarCeldas(nLinea, nLinea, 1, 4);
    		creador.rellenarCelda(nLinea++, 1, username, estnormI);
    		*/
    		creador.rellenarCelda(nLinea, 0, "Generado", estcorpoC);
    		creador.combinarCeldas(nLinea, nLinea, 1, 4);
    		creador.rellenarCelda(nLinea++, 1, VariablesStatic.fechaHoy() +" "+  VariablesStatic.horaSeg() , estnormI);
    		
    		nLinea = 8;
    		if (gdParame.rowCount() > 0) {
	    		creador.combinarCeldas(nLinea, nLinea, 0, 4);
	    		creador.rellenarCelda(nLinea++, 0, "Parámetros", estcorpoC);
	    		
	    		for (int i = 0; i < gdParame.rowCount(); i++) {
		    		creador.rellenarCelda(nLinea, 0, gdParame.getStringCell(i,"nomparam"), estcorpoC);
		    		creador.combinarCeldas(nLinea, nLinea, 1, 4);
		    		creador.rellenarCelda(nLinea++, 1, gdParame.getStringCell(i,"valparam"), estnormI);
		    		
	    		}
    		}
    		
    		nLinea = nLinea + 2;
    		
    		for (int i = 0; i < arNomCam.size(); i++) {			
    			creador.rellenarCelda(nLinea, i, arNomCam.get(i), estcorpoC);
    		}
    		
    		nLinea++;
    		
    		String importe = "0";
    		double dbimport = 0.0;
    		
    		for (int i = 0; i < gdResult.rowCount(); i++) {			
    			for (int j = 0; j < arNomCam.size(); j++) {
    				try {
    						if (gdResult.getStringCell(i,arCdCamp.get(j)).equals("- NO -")){
    							creador.rellenarCelda(nLinea, j, devuelveValor(gdResult.getStringCell(i,arCdCamp.get(j)),arCdCamp.get(j)), estrojoxC, "S");
    						} else {
    							
    							if(arCdCamp.get(j).equals("debhaber")){
    									importe = devuelveValor(gdResult.getStringCell(i,"cantidad"),"cantidad");
    								
    								if(devuelveValor(gdResult.getStringCell(i,arCdCamp.get(j)),arCdCamp.get(j)).equals("D")){
    									creador.rellenarCelda(nLinea, j,"+"+importe,creador.getTipoCelda(arAlinea.get(j)), arTipCel.get(j));
    									//creador.rellenarCelda(nLinea, j+1,"-",creador.getTipoCelda(arAlinea.get(j)), arTipCel.get(j));
    									dbimport = dbimport + Double.parseDouble(importe);
    									
    								}
    								if(devuelveValor(gdResult.getStringCell(i,arCdCamp.get(j)),arCdCamp.get(j)).equals("H")){
    									j++;
    									creador.rellenarCelda(nLinea, j,"-"+importe,creador.getTipoCelda(arAlinea.get(j)), arTipCel.get(j));
    									dbimport = dbimport - Double.parseDouble(importe);
    									//creador.rellenarCelda(nLinea, j-1,"-"+importe,creador.getTipoCelda(arAlinea.get(j)), arTipCel.get(j));

    								}
    								
    												
    							}else{
    								creador.rellenarCelda(nLinea, j, devuelveValor(gdResult.getStringCell(i,arCdCamp.get(j)),arCdCamp.get(j)), creador.getTipoCelda(arAlinea.get(j)), arTipCel.get(j));
    							}
    						}
    				} catch (Exception e) {
    					Logger.getLogger("LRA").warning(this.getClass().getName() +" ERROR exportando a excel"+ infoname +" | CAMPO: "+ arCdCamp.get(j) +" ---- "+ e.getMessage());
    					e.printStackTrace();
    				}
    			}
    			nLinea++;
    		}
    		
    		 
    		nLinea++;
    		nLinea++;
    		
    		creador.rellenarCelda(nLinea, 4,"Saldo", estcorpoC);
    		creador.rellenarCelda(nLinea, 5,String.valueOf(dbimport), estcorpoC,"2D");
    		
    		try {
    			if ((fileimgx != null) && (!fileimgx.equals(""))) {
		    		String pathgrap = PropiedadesJLet.getInstance().getProperty("path.gen.images");
		    		creador.insertaImagen(pathgrap,fileimgx,nLinea+3,1);
    			}
    		} catch (Exception e) {
    			Logger.getLogger("LRA").warning(this.getClass().getName() +" Fallo al incluir la imagen en el excel "+ fileimgx);
    		}
    		
    		creador.ponerEncabezado(infoname);
    		
    		creador.ponerPiePagina("JLet Reporting");
    		
    		creador.establecerZoom(9,10);
    		
    		creador.cerrarLibro();
        	
			output.setValue("filecrea",filecrea);
			output.setValue("creador",creador);
			
			cderror = "0";
			txerror = "OK";
			
        } catch(Exception se) {
        	cderror = "1";
        	txerror = "Error al recuperar los valores del INPUT";
        	Logger.getLogger("LRA").warning(this.getClass().getName() +" Error al recueperare los valores del INPUT ");
        } finally {
        	try {
    			output.setValue("cderror",cderror);
    			output.setValue("txerror",txerror);
        	} catch (Exception e) {
				e.printStackTrace();
			}
			
        }
        
    }
    
    public String devuelveValor(String txdatosx, String cdcampox) {
    	
    	String campodev = "";
    	
    	campodev = txdatosx;
    	
    	if (cdcampox.equals("mcactivo")) {
    		if (txdatosx.equals("S")) {
    			campodev = "ACTIVO";
    		} else if (txdatosx.equals("B")) {
    			campodev = "BLOQUEADO";
    		} else if (txdatosx.equals("N")) {
    			campodev = "BAJA";
    		}
    	}
    	
    	if (cdcampox.equals("mcpartne")) {
    		if (txdatosx.equals("S")) {
    			campodev = "SÍ";
    		} else if (txdatosx.equals("N")) {
    			campodev = " ";
    		} 
    	}
    	
    	if (cdcampox.equals("mctempor")) {
    		if (txdatosx.equals("S")) {
    			campodev = "SÍ";
    		} else if (txdatosx.equals("N")) {
    			campodev = " ";
    		} 
    	}
    	
    	try {
    		campodev = campodev.replaceAll(",",".");
    	} catch (Exception e) {
    		
    	}
    	
    	return campodev;
    	
    }

}
    
