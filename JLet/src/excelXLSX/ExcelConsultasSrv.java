package excelXLSX;

import java.util.ArrayList;
import java.util.logging.Logger;



import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;

import utils.PropiedadesJLet;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import excelXLSX.GeneraExcel;

public class ExcelConsultasSrv extends Service {

	int nLinea = 10;
	
	public ObjectIO instanceOfInput() {
		ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("cdgestor");
			input.addVariable("txcompan");
			input.addVariable("txpantal");
			input.addVariable("gdParame");
			input.addVariable("gdValore");
			input.addVariable("gdResult");
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
			output.addVariable("cderror");
			output.addVariable("txerror");

		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
        String txcompan  = "";
        String txpantal  = "";
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
        ArrayList<Integer> arAltoCe = new ArrayList<Integer>();
        ArrayList<String>  arAlinea = new ArrayList<String>();
        ArrayList<String>  arTipCel = new ArrayList<String>();
        String username = null;
        String pathrepo = "";
        String random3c = "";
        String maskhoyx = "";
        
        
        try {
        	
        	txcompan = input.getStringValue("txcompan");
        	txpantal = input.getStringValue("txpantal");
        	gdParame = input.getGrid("gdParame");
        	gdValore = input.getGrid("gdValore");
        	gdResult = input.getGrid("gdResult");

        	username ="Luis";// (String)sesion.getAttribute("username");
        //	pathrepo = PropiedadesJLet.getInstance().getProperty("path.report.consultas");
        //	random3c = VariablesStatic.randomCaracteres(3);
        	
        	/*try {
        		String fechahoy = VariablesStatic.fechaHoy();
        		maskhoyx = fechahoy.substring(3,5) + fechahoy.substring(0,2);
        	} catch (Exception e) {
        		
        	}*/
        	
        	filecrea =  username + maskhoyx +"_"+ random3c +"_"+ txpantal +".xlsx";
        	
        	GeneraExcel creador = new GeneraExcel(txpantal, pathrepo,filecrea);
    		
        	for (int i = 0; i < gdValore.rowCount(); i++) {
        		arNomCam.add(gdValore.getStringCell(i,"cabcampo"));
        		arCdCamp.add(gdValore.getStringCell(i,"cdcampox"));
    			arAnchoc.add(Integer.parseInt(gdValore.getStringCell(i,"anchocam")));
    			arAltoCe.add(Integer.parseInt(gdValore.getStringCell(i,"altoceld")));
    			arTipCel.add(gdValore.getStringCell(i,"tipocamp"));
    			arAlinea.add(gdValore.getStringCell(i,"alincamp"));
    		}
        	
    		creador.establecerAnchoColumnas(arAnchoc);
    		
    		XSSFColor colorCorp = creador.crearColor(166,180,249);
    	
    		try {
	    		String stcolCor = (String) sesion.getAttribute("colorcp1d");
	    		String [] colorCor = stcolCor.split(",");
	    		colorCorp = creador.crearColor(Integer.parseInt(colorCor[0]),Integer.parseInt(colorCor[1]),Integer.parseInt(colorCor[2]));
    		} catch (Exception e ){
    			//LOGGER.getLogger("LRA").warning(this.getClass().getName() +" - Error recupuerando color corporativo");
    		}
    		
    		Font fontcorp = creador.crearFuente(10, "Trebuchet MS", IndexedColors.WHITE.getIndex(), false, true);
    		
    		XSSFCellStyle estcorpoI = creador.crearCellStyle(colorCorp,fontcorp,"I");
    		XSSFCellStyle estcorpoC = creador.crearCellStyle(colorCorp,fontcorp,"C");
    		XSSFCellStyle estcorpoD = creador.crearCellStyle(colorCorp,fontcorp,"D");
    		
    		Font fontnorm = creador.crearFuente(10, "Trebuchet MS", IndexedColors.GREY_80_PERCENT.getIndex(), false, true);
    		
    	    XSSFColor colorNorm = creador.crearColor(254,254,254);				// CASI BLANCO
    	    XSSFCellStyle estnormI = creador.crearCellStyle(null,fontnorm,"I");
    	    XSSFCellStyle estnormC = creador.crearCellStyle(null,fontnorm,"C");
    	    XSSFCellStyle estnormD = creador.crearCellStyle(null,fontnorm,"D");
    	    
    	//    String pathimgx = PropiedadesJLet.getInstance().getProperty("path.report.img.logos");
    	 //   String nameimgx ="";// (String) sesion.getAttribute("cdticker") +".png";
    	/*    String pathimgx = "E:/DATOS/tmp/pieces/";
            String nameimgx = "1_T-PEN.jpg";  		
    	    
    	    creador.insertaImagen(pathimgx,nameimgx,0,0);
    		
    	    creador.combinarCeldas(2, 2, 0, 4);
    		creador.rellenarCelda(2, 0, "Datos Informe", estcorpoC);
    		creador.rellenarCelda(3, 0, "Sociedad", estcorpoI);
    		creador.combinarCeldas(3, 3, 1, 4);
    		creador.rellenarCelda(3, 1, txcompan, estnormI);
    		creador.rellenarCelda(4, 0, "Pantalla", estcorpoI);
    		creador.combinarCeldas(4, 4, 1, 4);
    		creador.rellenarCelda(4, 1, txpantal, estnormI);
    		creador.rellenarCelda(5, 0, "Usuario", estcorpoI);
    		creador.combinarCeldas(5, 5, 1, 4);
    		creador.rellenarCelda(5, 1, username, estnormI);
    		creador.rellenarCelda(6, 0, "Generado", estcorpoI);
    		creador.combinarCeldas(6, 6, 1, 4);
    		//creador.rellenarCelda(6, 1, VariablesStatic.fechaHoy() +" "+  VariablesStatic.horaSeg() , estnormI);
    		
    		creador.combinarCeldas(9, 9, 0, 4);
    		creador.rellenarCelda(9, 0, "Parámetros", estcorpoC);*/
    	    String pathimgx = "E:/DATOS/tmp/pieces/";
            String nameimgx = "1_T-PEN.jpg";  	
    		
    		nLinea = 1;
    		for (int i = 0; i < gdParame.rowCount(); i++) {
	    		creador.rellenarCelda(nLinea, 0, gdParame.getStringCell(i,"nomparam"), estcorpoC);
	    		creador.combinarCeldas(nLinea, nLinea, 1, 4);
	    		creador.rellenarCelda(nLinea++, 1, gdParame.getStringCell(i,"valparam"), estnormI);
    		}
    		
    		System.out.println("Num GDDatos "+ gdResult.rowCount());
    		
    		nLinea = nLinea + 2;
    		
    		for (int i = 0; i < arNomCam.size(); i++) {			
    			creador.rellenarCelda(nLinea, i, arNomCam.get(i), estcorpoC);
    		}
    		
    		nLinea++;
    		
    		// String pathimgx = "E:/DATOS/tmp/";
            		
     	    
     	   
    		
    		for (int i = 0; i < gdResult.rowCount(); i++) {			
    			for (int j = 0; j < arNomCam.size(); j++) {
    				try {
    					nameimgx = gdResult.getStringCell(i,"imgdefau");
    					if ((arAlinea.get(j) != null) && (arAlinea.get(j).equals("D"))) {
    						creador.rellenarCelda(nLinea, j, devuelveValor(gdResult.getStringCell(i,arCdCamp.get(j)),arCdCamp.get(j)), estnormD, arTipCel.get(j),arAltoCe.get(j));
    					} else if ((arAlinea.get(j) != null) && (arAlinea.get(j).equals("C"))) {
    						creador.rellenarCelda(nLinea, j, devuelveValor(gdResult.getStringCell(i,arCdCamp.get(j)),arCdCamp.get(j)), estnormC, arTipCel.get(j),arAltoCe.get(j));
    					} else {
    						creador.rellenarCelda(nLinea, j, devuelveValor(gdResult.getStringCell(i,arCdCamp.get(j)),arCdCamp.get(j)), estnormI, arTipCel.get(j),arAltoCe.get(j));
    					}
    					
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
    			}
    			try{
    				creador.insertaImagenResize(pathimgx,nameimgx,nLinea,0,1);
    			}catch(Exception ex){
    				System.out.println("Error al crear imagen");
    			}
    			nLinea++;
    		}
    		
    		
    		creador.ponerEncabezado(txpantal +" - "+ txcompan);
    		
    		creador.ponerPiePagina("JLet Reporting");
    		
    		creador.establecerZoom(9,10);
    		
    		creador.cerrarLibro();
        	
			output.setValue("filecrea",filecrea);
			
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
    	
    	if (cdcampox.equals("cdcountr")) {
    		//campodev = VariablesStatic.getPaises(txdatosx);
    	}
    	
    	return campodev;
    	
    }
    
}
    
