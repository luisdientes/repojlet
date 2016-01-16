package desgcostes;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import stock.bd.ListStockBD;
import stock.bd.ListStockBDIn;
import utils.PropiedadesJLet;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import common.bd.ListDivEmisorBD;
import common.bd.ListDivEmisorBDIn;
import common.varstatic.VariablesStatic;
import desgcostes.bd.ListDesgloseCostesBD;
import desgcostes.bd.ListDesgloseCostesBDIn;
import desgcostes.bd.ListDesgloseDetalleBD;
import desgcostes.bd.ListDesgloseDetalleBDIn;
import facturacion.bd.ListDetalleClienteBD;
import facturacion.bd.ListDetalleClienteBDIn;
import facturacion.parents.FacturaPDF;
import graficas.constants.GraficoCircular;


public class PdfDesgloseSrv extends Service {
	
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
	
	//DATOS DE LA FACTURA
	double porTaxes = 21;
	String txdivisa = " &euro;";
	String cddivisa = "€";
	

	//PARÁMETROS A RELLENAR EN LA FACTURA
	String imgflMar = "";
	String imgflCos = "";
	String benefici = "";
	String imglogox = "";
	String txemisor = "";
	String txpaisxx = "";
	String fhdesdex = "";
	String fhhastax = "";
	String txcanalx = "";
	String ingrtota = "";
	String benebrut = "";
	
	//FORMATOS NUMÉRICOS
	DecimalFormat formatDivi = new DecimalFormat("###,##0.00");
	DecimalFormat formatNorm = new DecimalFormat("##0.00");
	DecimalFormat formatPorc = new DecimalFormat("##0.00%");

	//PARAMETROS DE ENTRADA
	String idemisor = "";
	String idunicox = "";
	String codinfor = "";
	String mostriva = "";

	//PARÁMETROS DE SALIDA
	String namefile = "";
	String filecrea = "";
	
	//OTROS PARÁMETROS
	HashMap<String,String> hmCatego = new HashMap<String,String>();
	HashMap<String,String> hmValore = new HashMap<String,String>();
	HashMap<String,Color>  hmColors = new HashMap<String,Color>();
	Grid gdProduc = null;

	int numcoste = 0;
	int altocost = 13;
	
    //COLORES
	String colocorp = "AAAAAA";
	BaseColor colBlanc = new BaseColor(255,255,255);
	BaseColor colCorpo = new BaseColor(13,71,117);
	BaseColor colLetra = new BaseColor(13,71,117);
	BaseColor colCabec = new BaseColor(13,71,117);
	BaseColor colCelda = new BaseColor(245,246,250);
	
	//GRID
	Grid gdMedias = null;
	Grid dtgdCost = new Grid();			//Grid Grafica Costes
	Grid dtgdMarg = new Grid();			//Grid Grafica Margen

	//Fuentes
	Font fuenteCab 	= new Font(FontFamily.HELVETICA, 8, Font.BOLD, colBlanc);
	Font fuente		= new Font(FontFamily.HELVETICA, 8, Font.NORMAL, colLetra);
	Font fuenteNeg 	= new Font(FontFamily.HELVETICA, 8, Font.BOLD, colLetra);

	//VARIABLES COSTES/INGRESOS
	double dccaduana = 0;		//COSTES COMPRA ADUANA
	double dcccompra = 0;		//COSTES COMPRA ADUANA
	double dcctransp = 0;		//COSTES COMPRA ADUANA
	double dcvaduana = 0;		//COSTES COMPRA ADUANA
	double dcvcompra = 0;		//COSTES COMPRA ADUANA
	double dcvtransp = 0;		//COSTES COMPRA ADUANA
	double dcostetot = 0;		//COSTES TOTALE BRUTOS
	double dcosnetot = 0;		//COSTES TOTALE NETOS
	double dingtotal = 0;		//INGRESOS BRUTOS
	double dingnetot = 0;		//INGRESOS NETO
	double dbenefici = 0;		//BENEFICIO BRUTO
	double dbeneneto = 0;		//BENEFICIO NETO
	//double dcostrans = 0;		//COSTE TRANSPORTE
	double dingtrans = 0;		//INGRESO TRANSPORTE
	double dcostprod = 0;		//COSTE PRODUCTO
	
	
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();

			input.addVariable("idemisor");
			input.addVariable("idunicox");
			input.addVariable("mostriva");
			input.addVariable("codinfor");
			input.addVariable("gdMedias");
			input.addVariable("fhdesdex");
			input.addVariable("fhhastax");
			input.addVariable("txpaisxx");
			input.addVariable("txcanalx");
			input.addVariable("txemisor");
			input.addVariable("ingrtota");
			input.addVariable("benebrut");
			
						
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
    		idunicox = input.getStringValue("idunicox");
    		mostriva = input.getStringValue("mostriva");
    		codinfor = input.getStringValue("codinfor");
    		fhdesdex = input.getStringValue("fhdesdex");
			fhhastax = input.getStringValue("fhhastax");
			txpaisxx = input.getStringValue("txpaisxx");
			txcanalx = input.getStringValue("txcanalx");
			txemisor = input.getStringValue("txemisor");
			ingrtota = input.getStringValue("ingrtota");
			benebrut = input.getStringValue("benebrut");
    		gdMedias = input.getGrid("gdMedias");
			FRAparen.setConexion(this.getConnection());
		} catch (Exception e1) {
			System.err.println(this.getClass().getName() +"- ERROR - "+ e1.getMessage());
			e1.printStackTrace();
		}
		
	}
	
    public void process(ObjectIO input, ObjectIO output){
    	
    	try {
    		
    		
    		recuperaInput(input);
    		
	    	Rectangle pageSize = new Rectangle(PageSize.A4);
	    	pageSize.setBackgroundColor(new BaseColor(255, 255, 255));
	    	pageSize.rotate();
	        documento = new Document(pageSize,20, 20, 30, 30);
	        namefile = "Costes_PH103_"+ codinfor +"_"+ System.currentTimeMillis() +".pdf";
	        //JEJL
	        //namefile = "Costes_PH734_"+ System.currentTimeMillis() +".pdf";
	    	filecrea = PropiedadesJLet.getInstance().getProperty("path.gen.invoice")+ "emisor_"+ idemisor + "/"+ namefile;
	    	writer = PdfWriter.getInstance(documento, new FileOutputStream(filecrea));
	        documento.open();
			
	        String[] arIdUnic = idunicox.split(",");
	        
	        
	        Grid gdEmisor = null;
	        
	        try {
	        	ListDivEmisorBDIn datEmisorBDIn = new ListDivEmisorBDIn();
				datEmisorBDIn.setValue("idemisor", idemisor);
				ListDivEmisorBD datEmisorBD = new ListDivEmisorBD(datEmisorBDIn);
				datEmisorBD.setConnection(con);
				gdEmisor = datEmisorBD.execSelect();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
	        
	        if (gdEmisor.rowCount() > 0){
	        	colocorp = gdEmisor.getStringCell(0, "colocorp");
	        	cddivisa = gdEmisor.getStringCell(0, "divsimbo");
	        	txdivisa = gdEmisor.getStringCell(0, "divsimbo");
	        	if (cddivisa.equals("&euro;")){
	        		cddivisa = "€";
	        	}
	        	cddivisa = "EUR";
	        } else {
	        	colocorp = "0080C8";
	        }
	        
	        colorOtherColors(colocorp);
	        generaPortada();
	        
	        pintarHojaResumen(arIdUnic.length,gdMedias);
	        
	        for (int i = 0; i < arIdUnic.length; i++){
	        
	        	numcoste = 0;
	        	
	        	gdProduc = new Grid();
	        	idunicox = arIdUnic[i];
	        	
	        	gdProduc = datosProducto(idunicox);
	        	
	        	if (pagetotal > 0){	        	
	        		documento.newPage();
	        	}
	        	
		        pintaCabecera();
		        
		        pintaDesgloseCostes();
		        
		        pintaGraficaCostes();
		        
		        pintaDetalleProducto();
		        
		        pintaRatios();
		        
		        pintaGraficaMargen();
		        
		        pintaGraficaCostes();
		        
		        pintaOtrosDatos();
		        
		        pagetotal++;
		        
	        }
	        
	    	try {
				output.setValue("filecrea", namefile);
				output.setValue("txmensaj", "Generacion Ok - ");
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	
        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        } finally {
        	documento.close();
        }
    }
    
// ------------------------------------------ START PINTAR DATOS -----------------------------
    
    
    
    
    public void pintaCabecera(){
    	//INCLUYE LOGO
    	Image image;
    	
		try {
			imglogox = FRAparen.obtenerLogoEmisor(idemisor);
	        
	        String imageUrl = PropiedadesJLet.getInstance().getProperty("path.img.logoinvoice") + imglogox;

			image = Image.getInstance(imageUrl);
		    image.setAbsolutePosition(margeniz, 750);
		    image.scaleAbsolute(200,50);
		    image.setAlignment(Image.LEFT | Image.TEXTWRAP);//Code 2
			documento.add(image);
			
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
      

    public void pintaDesgloseCostes() {
    	
    	String cddivisa = "";
    	
    	int ultfilsh 	= 0;
    	Grid grLineas	= null;
    	
    	try {
    		
	    	PdfPTable table = new PdfPTable(new float[] { 4, 2, 1});
			table.setTotalWidth(anctabla);
			
			
		
			grLineas = recuperoLineasDesglose(idemisor, idunicox);
			
			/* CABECERA DETALLE DE COSTES
			PdfPCell cell = new PdfPCell(new Paragraph("Detalle de Costes",fuenteCab));
	        cell.setColspan(3);
	        cell.setBorder(0);
	        cell.setBackgroundColor(colCabec);
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        
	        table.addCell(cell).setBorderColor(new BaseColor(255,255,255));	
			*/
			
			PdfPCell cellCoco = new PdfPCell(new Paragraph("Coste Compra (CC)",fuenteCab));
			cellCoco.setColspan(3);
			cellCoco.setBorder(0);
			cellCoco.setBackgroundColor(colCabec);
			cellCoco.setHorizontalAlignment(Element.ALIGN_CENTER);
	        
			table.addCell(cellCoco).setBorderColor(new BaseColor(255,255,255));	;			
			
			insCabeceraTablaCostes(table);
			
			double totalCos = 0;
			boolean costcomp = true;
			
			for (int i = 0; i < grLineas.rowCount(); i++){
				
				cddivisa = grLineas.getStringCell(i,"cddivisa");
				
				double douvalor = Double.parseDouble(grLineas.getStringCell(i,"desvalue"));
				double porcenta = douvalor / dcostetot;
				String porcform = formatPorc.format(porcenta);
				
				if ((!grLineas.getStringCell(i,"codedesg").substring(0,2).equals("IG") && (!grLineas.getStringCell(i,"desvalue").equals("0.0")))) {
				
					numcoste++;
					
					if  (costcomp && grLineas.getStringCell(i,"codedesg").substring(0,1).equals("V")){
						costcomp = false;
						PdfPCell cellCve = new PdfPCell(new Paragraph("Coste Venta (CV)",fuenteCab));
						cellCve.setColspan(3);
						cellCve.setBorder(0);
						cellCve.setBackgroundColor(colCabec);
						cellCve.setHorizontalAlignment(Element.ALIGN_CENTER);
						
						table.addCell(cellCve);	
						
						insCabeceraTablaCostes(table);
						
					}
					
					if (grLineas.getStringCell(i,"codedesg").indexOf("TOTAL") == -1) {
						table.addCell(FRAparen.getCelda(grLineas.getStringCell(i,"txnombre"),fuente,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
						table.addCell(FRAparen.getCelda(formatDivi.format(douvalor) +" "+ grLineas.getStringCell(i,"cddivisa"),fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
						table.addCell(FRAparen.getCelda(porcform,fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
					} else {
						table.addCell(FRAparen.getCelda(grLineas.getStringCell(i,"txnombre"),fuenteNeg,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
						table.addCell(FRAparen.getCelda(formatDivi.format(douvalor) +" "+ grLineas.getStringCell(i,"cddivisa"),fuenteNeg,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
						table.addCell(FRAparen.getCelda(porcform,fuenteNeg,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
						totalCos += douvalor;
					}
				
				}
				
			}
			
			double porcenta = totalCos / dcostetot;
			String porcform = formatPorc.format(porcenta);
			table.addCell(FRAparen.getCelda("TOTAL COSTES",fuenteCab,colCabec,"left")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(formatDivi.format(totalCos) +" "+ cddivisa,fuenteNeg,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(porcform,fuenteNeg,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
	
			
			PdfContentByte canvas = writer.getDirectContent();
	    	table.writeSelectedRows(ultfilsh, -1, margeniz+250, 700, canvas);
			
			table = new PdfPTable(new float[] { 4, 2, 1});
			table.setTotalWidth(anctabla);
			
			insCabeceraTablaIngresos(table);
			
			double totalIng = 0;
			
			for (int i = 0; i < grLineas.rowCount(); i++){
						
				double douvalor = Double.parseDouble(grLineas.getStringCell(i,"desvalue"));
				porcenta = douvalor / dingtotal;
				porcform = formatPorc.format(porcenta);
				
				if ((grLineas.getStringCell(i,"codedesg").substring(0,2).equals("IG") && (!grLineas.getStringCell(i,"desvalue").equals("0.0")))) {
					if (grLineas.getStringCell(i,"codedesg").indexOf("TOTAL") == -1) {
						table.addCell(FRAparen.getCelda(grLineas.getStringCell(i,"txnombre"),fuente,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
						table.addCell(FRAparen.getCelda(formatDivi.format(douvalor) +" "+ grLineas.getStringCell(i,"cddivisa"),fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
						table.addCell(FRAparen.getCelda(porcform,fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
					} else {
						table.addCell(FRAparen.getCelda(grLineas.getStringCell(i,"txnombre"),fuenteNeg,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
						table.addCell(FRAparen.getCelda(formatDivi.format(douvalor) +" "+ grLineas.getStringCell(i,"cddivisa"),fuenteNeg,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
						table.addCell(FRAparen.getCelda(porcform,fuenteNeg,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
						totalIng += douvalor;
					}
					
				}
			}
			
			porcenta = totalIng / dingtotal;
			porcform = formatPorc.format(porcenta);
			table.addCell(FRAparen.getCelda("TOTAL INGRESOS",fuenteCab,colCabec,"left")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(formatDivi.format(totalIng) +" "+ cddivisa,fuenteNeg,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(porcform,fuenteNeg,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
	
			int altoingr = 700 - 76 - (numcoste * altocost);
			
	    	table.writeSelectedRows(ultfilsh, -1, margeniz+250, altoingr, canvas);	    	
		    
    	} catch (Exception e) {
    		
    	}
			
    }
    
    public void pintaRatios() {
    	
    	cddivisa = "EUR";

    	int ultfilsh 	= 0;
    	
    	try {
    		
    		PdfContentByte canvas = writer.getDirectContent();
    		
	    	PdfPTable table = new PdfPTable(new float[] { 5, 3, 2});
			table.setTotalWidth(200);
			
			PdfPCell cellRati = new PdfPCell(new Paragraph("Ratios",fuenteCab));
			cellRati.setColspan(3);
			cellRati.setBorder(0);
			cellRati.setBackgroundColor(colCabec);
			cellRati.setHorizontalAlignment(Element.ALIGN_CENTER);	       
			table.addCell(cellRati).setBorderColor(new BaseColor(255,255,255));
			
			//BENEFICIO BRUTO
			double pcBenBru = dbenefici / dcostetot;
			table.addCell(FRAparen.getCelda("Beneficio Bruto",fuente,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(formatDivi.format(dbenefici) +" "+ cddivisa,fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(formatPorc.format(pcBenBru),fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
			
			//BENEFICIO NETO
			double pcBenNet = dbeneneto / dcosnetot;
			table.addCell(FRAparen.getCelda("Beneficio Neto",fuente,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(formatDivi.format(dbeneneto) +" "+ cddivisa,fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(formatPorc.format(pcBenNet),fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));

			//MARGEN BENEFICIO BRUTO			
			double pcMarBru = dbenefici / dingtotal;
			table.addCell(FRAparen.getCelda("Margen Beneficio Bruto",fuente,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(formatDivi.format(dbenefici) +" "+ cddivisa,fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(formatPorc.format(pcMarBru),fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
			
			//MARGEN BENEFICIO BRUTO			
			double pcMarNet = dbeneneto / dingnetot;
			table.addCell(FRAparen.getCelda("Margen Beneficio Neto",fuente,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(formatDivi.format(dbeneneto) +" "+ cddivisa,fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(formatPorc.format(pcMarNet),fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
			
			//% TRANSPORTE / COSTE TOTAL
			double deftrans = dingtrans - dcvtransp;
			double pcDefTra = deftrans / dcostetot;
			if (deftrans >= 0) {
				table.addCell(FRAparen.getCelda("Beneficio Transporte",fuente,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
			} else {
				table.addCell(FRAparen.getCelda("Deficit Transporte",fuente,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
			}
			table.addCell(FRAparen.getCelda(formatDivi.format(deftrans) +" "+ cddivisa,fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(formatPorc.format(pcDefTra),fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
			
			//% TRANSPORTE / COSTE TOTAL
			double pcTraCos = dcvtransp / dcostetot;
			table.addCell(FRAparen.getCelda("% Transporte / Coste Total",fuente,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(formatDivi.format(dcvtransp) +" "+ cddivisa,fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(formatPorc.format(pcTraCos),fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
			
			//% PRODUCTO / COSTE TOTAL
			double pcProCos = dcostprod / dcostetot;
			table.addCell(FRAparen.getCelda("% Producto / Coste Total",fuente,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(formatDivi.format(dcostprod) +" "+ cddivisa,fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(formatPorc.format(pcProCos),fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
			
			
	    	table.writeSelectedRows(ultfilsh, -1, margeniz, 480, canvas);	    	
		    
    	} catch (Exception e) {
    		
    	}
			
    }
    
    public void pintarHojaResumen(int numprodu, Grid gridMedi) {
    	
    	double costeTot = 0;
    	double costeTsi = 0;
    	
    	String cddivisa = "";
    	
    	try {
    		
	    	PdfPTable table = new PdfPTable(new float[] { 4, 2});
			table.setTotalWidth(anctabla);
		
			Font fuente = new Font(FontFamily.HELVETICA, 8, Font.NORMAL, colLetra);
			Font fuenteNeg = new Font(FontFamily.HELVETICA, 8, Font.BOLD, colLetra);
		
			
			PdfPCell cell = new PdfPCell(new Paragraph("Datos Medios Totales / "+ String.valueOf(numprodu) +" Productos",fuenteCab));
	        cell.setColspan(2);
	        cell.setBorder(0);
	        cell.setBackgroundColor(colCabec);
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        
	        table.addCell(cell).setBorderColor(new BaseColor(255,255,255));	
			
			table.addCell(FRAparen.getCelda("N. Productos",fuenteNeg,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(String.valueOf(numprodu),fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
			
			if (!gdMedias.getStringCell(0,"camediax").equals("0.00")) {
				table.addCell(FRAparen.getCelda("Coste Compra Aduana Medio",fuenteNeg,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
				table.addCell(FRAparen.getCelda(gdMedias.getStringCell(0,"camediax"),fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
				try {
					costeTot += Double.parseDouble(gdMedias.getStringCell(0,"camediax").replaceAll("//.","").replaceAll(",","."));
					costeTsi += Double.parseDouble(gdMedias.getStringCell(0,"camediax").replaceAll("//.","").replaceAll(",","."));
				} catch (Exception e) {
					System.out.println(" camediax -> "+ e.getMessage());
				}
			}
			
			if (!gdMedias.getStringCell(0,"caivamed").equals("0.00")) {
				table.addCell(FRAparen.getCelda("Coste IVA Compra Aduana Medio",fuenteNeg,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
				table.addCell(FRAparen.getCelda(gdMedias.getStringCell(0,"caivamed"),fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
				try {
					costeTot += Double.parseDouble(gdMedias.getStringCell(0,"caivamed").replaceAll("//.","").replaceAll(",","."));
				} catch (Exception e) {
					
				}				
			}

			if (!gdMedias.getStringCell(0,"cpmediax").equals("0.00")) {
				table.addCell(FRAparen.getCelda("Coste Compra Producto Medio",fuenteNeg,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
				table.addCell(FRAparen.getCelda(gdMedias.getStringCell(0,"cpmediax"),fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
				try {
					costeTot += Double.parseDouble(gdMedias.getStringCell(0,"cpmediax").replaceAll("//.","").replaceAll(",","."));
					costeTsi += Double.parseDouble(gdMedias.getStringCell(0,"cpmediax").replaceAll("//.","").replaceAll(",","."));
				} catch (Exception e) {
					
				}
			}

			if (!gdMedias.getStringCell(0,"cpivamed").equals("0.00")) {
				table.addCell(FRAparen.getCelda("Coste IVA Compra Producto Medio",fuenteNeg,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
				table.addCell(FRAparen.getCelda(gdMedias.getStringCell(0,"cpivamed"),fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
				try {
					costeTot += Double.parseDouble(gdMedias.getStringCell(0,"cpivamed").replaceAll("//.","").replaceAll(",","."));
				} catch (Exception e) {
					
				}
			}

			if (!gdMedias.getStringCell(0,"ctmediax").equals("0.00")) {
				table.addCell(FRAparen.getCelda("Coste Compra Transporte Medio",fuenteNeg,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
				table.addCell(FRAparen.getCelda(gdMedias.getStringCell(0,"ctmediax"),fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
				try {
					costeTot += Double.parseDouble(gdMedias.getStringCell(0,"ctmediax").replaceAll("//.","").replaceAll(",","."));
					costeTsi += Double.parseDouble(gdMedias.getStringCell(0,"ctmediax").replaceAll("//.","").replaceAll(",","."));
				} catch (Exception e) {
					
				}
			}

			if (!gdMedias.getStringCell(0,"ctivamed").equals("0.00")) {
				table.addCell(FRAparen.getCelda("Coste IVA Compra Transporte Medio",fuenteNeg,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
				table.addCell(FRAparen.getCelda(gdMedias.getStringCell(0,"ctivamed"),fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));		
				try {
					costeTot += Double.parseDouble(gdMedias.getStringCell(0,"ctmediax").replaceAll("//.","").replaceAll(",","."));
				} catch (Exception e) {
					
				}		
			}
			
			if (!gdMedias.getStringCell(0,"vamediax").equals("0.00")) {
				table.addCell(FRAparen.getCelda("Coste Venta Aduana Medio",fuenteNeg,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
				table.addCell(FRAparen.getCelda(gdMedias.getStringCell(0,"vamediax"),fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));	
				try {
					costeTot += Double.parseDouble(gdMedias.getStringCell(0,"vamediax").replaceAll("//.","").replaceAll(",","."));
					costeTsi += Double.parseDouble(gdMedias.getStringCell(0,"vamediax").replaceAll("//.","").replaceAll(",","."));
				} catch (Exception e) {
					
				}			
			}

			if (!gdMedias.getStringCell(0,"vaivamed").equals("0.00")) {
				table.addCell(FRAparen.getCelda("Coste IVA Venta Aduana Medio",fuenteNeg,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
				table.addCell(FRAparen.getCelda(gdMedias.getStringCell(0,"vaivamed"),fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));	
				try {
					costeTot += Double.parseDouble(gdMedias.getStringCell(0,"vaivamed").replaceAll("//.","").replaceAll(",","."));
				} catch (Exception e) {
					
				}			
			}

			if (!gdMedias.getStringCell(0,"vpmediax").equals("0.00")) {
				table.addCell(FRAparen.getCelda("Coste Venta Producto Medio",fuenteNeg,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
				table.addCell(FRAparen.getCelda(gdMedias.getStringCell(0,"vpmediax"),fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));		
				try {
					costeTot += Double.parseDouble(gdMedias.getStringCell(0,"vpmediax").replaceAll("//.","").replaceAll(",","."));
					costeTsi += Double.parseDouble(gdMedias.getStringCell(0,"vpmediax").replaceAll("//.","").replaceAll(",","."));
				} catch (Exception e) {
					
				}						
			}

			if (!gdMedias.getStringCell(0,"vpivamed").equals("0.00")) {
				table.addCell(FRAparen.getCelda("Coste IVA Venta Producto Medio",fuenteNeg,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
				table.addCell(FRAparen.getCelda(gdMedias.getStringCell(0,"vpivamed"),fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));		
				try {
					costeTot += Double.parseDouble(gdMedias.getStringCell(0,"vpivamed").replaceAll("//.","").replaceAll(",","."));
				} catch (Exception e) {
					
				}						
			}

			if (!gdMedias.getStringCell(0,"vtmediax").equals("0.00")) {
				table.addCell(FRAparen.getCelda("Coste Venta Transporte Medio",fuenteNeg,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
				table.addCell(FRAparen.getCelda(gdMedias.getStringCell(0,"vtmediax"),fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));	
				try {
					costeTot += Double.parseDouble(gdMedias.getStringCell(0,"vtmediax").replaceAll("//.","").replaceAll(",","."));
					costeTsi += Double.parseDouble(gdMedias.getStringCell(0,"vtmediax").replaceAll("//.","").replaceAll(",","."));
				} catch (Exception e) {
					
				}							
			}

			if (!gdMedias.getStringCell(0,"vtivamed").equals("0.00")) {
				table.addCell(FRAparen.getCelda("Coste IVA Venta Transporte Medio",fuenteNeg,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
				table.addCell(FRAparen.getCelda(gdMedias.getStringCell(0,"vtivamed"),fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));		
				try {
					costeTot += Double.parseDouble(gdMedias.getStringCell(0,"vtivamed").replaceAll("//.","").replaceAll(",","."));
				} catch (Exception e) {
					
				}						
			}
			
			table.addCell(FRAparen.getCelda(" ",fuente,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(" ",fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
			
			//COSTE TOTAL
			table.addCell(FRAparen.getCelda("Coste Total Medio (Sin IVA)",fuenteNeg,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(formatDivi.format(costeTsi),fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));		
			
			table.addCell(FRAparen.getCelda("Coste Total Medio (Con IVA)",fuenteNeg,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(formatDivi.format(costeTot),fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));		
			
			
			table.addCell(FRAparen.getCelda(" ",fuente,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(" ",fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
			
			if (!gdMedias.getStringCell(0,"igmediax").equals("0.00")) {
				table.addCell(FRAparen.getCelda("Ingresos (Sin IVA)",fuenteNeg,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
				table.addCell(FRAparen.getCelda(gdMedias.getStringCell(0,"igmediax"),fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));				
			}

			if (!gdMedias.getStringCell(0,"igivamed").equals("0.00")) {
				table.addCell(FRAparen.getCelda("Ingresos IVA ",fuenteNeg,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
				table.addCell(FRAparen.getCelda(gdMedias.getStringCell(0,"igivamed"),fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));				
			}
			
			double ingsitot = Double.parseDouble(gdMedias.getStringCell(0,"igmediax").replaceAll("//.","").replaceAll(",","."));
			double ingcitot = Double.parseDouble(gdMedias.getStringCell(0,"igivamed").replaceAll("//.","").replaceAll(",","."));
			double ingtotal = ingsitot + ingcitot; 
			
			if (!gdMedias.getStringCell(0,"igivamed").equals("0.00")) {
				table.addCell(FRAparen.getCelda("Ingresos Totales ",fuenteNeg,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
				table.addCell(FRAparen.getCelda(formatDivi.format(ingtotal),fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));				
			}
			
			table.addCell(FRAparen.getCelda(" ",fuente,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(" ",fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
			
			table.addCell(FRAparen.getCelda("Beneficio Neto",fuenteNeg,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(ingrtota,fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));				
			
			table.addCell(FRAparen.getCelda("Beneficio Bruto",fuenteNeg,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(benebrut,fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));					
			
			PdfContentByte canvas = writer.getDirectContent();
	    	table.writeSelectedRows(0, -1, margeniz+50, 700, canvas);
			
	    	
	    	//RATIOS
	    	table = new PdfPTable(new float[] { 5, 3, 2});
			table.setTotalWidth(200);
			
			PdfPCell cellRati = new PdfPCell(new Paragraph("Ratios / "+ String.valueOf(numprodu) +" Productos",fuenteCab));
			cellRati.setColspan(3);
			cellRati.setBorder(0);
			cellRati.setBackgroundColor(colCabec);
			cellRati.setHorizontalAlignment(Element.ALIGN_CENTER);	       
			table.addCell(cellRati).setBorderColor(new BaseColor(255,255,255));
			
			dbenefici = ingtotal - costeTot;
			dcostetot = costeTot;
			dbeneneto = ingsitot - costeTsi;
			dcosnetot = costeTsi;
			dingtotal = ingtotal;
			dingnetot = ingsitot;
			
			//BENEFICIO BRUTO
			double pcBenBru = dbenefici / dcostetot;
			table.addCell(FRAparen.getCelda("Beneficio Bruto",fuente,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(formatDivi.format(dbenefici) +" "+ cddivisa,fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(formatPorc.format(pcBenBru),fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
			
			//BENEFICIO NETO
			double pcBenNet = dbeneneto / dcosnetot;
			table.addCell(FRAparen.getCelda("Beneficio Neto",fuente,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(formatDivi.format(dbeneneto) +" "+ cddivisa,fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(formatPorc.format(pcBenNet),fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));

			//MARGEN BENEFICIO BRUTO			
			double pcMarBru = dbenefici / dingtotal;
			table.addCell(FRAparen.getCelda("Margen Beneficio Bruto",fuente,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(formatDivi.format(dbenefici) +" "+ cddivisa,fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(formatPorc.format(pcMarBru),fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
			
			//MARGEN BENEFICIO BRUTO			
			double pcMarNet = dbeneneto / dingnetot;
			table.addCell(FRAparen.getCelda("Margen Beneficio Neto",fuente,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(formatDivi.format(dbeneneto) +" "+ cddivisa,fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(formatPorc.format(pcMarNet),fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
			
			/*
			//% TRANSPORTE / COSTE TOTAL
			double deftrans = dingtrans - dcvtransp;
			double pcDefTra = deftrans / dcostetot;
			if (deftrans >= 0) {
				table.addCell(FRAparen.getCelda("Beneficio Transporte",fuente,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
			} else {
				table.addCell(FRAparen.getCelda("Deficit Transporte",fuente,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
			}
			table.addCell(FRAparen.getCelda(formatDivi.format(deftrans) +" "+ cddivisa,fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(formatPorc.format(pcDefTra),fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
			
			//% TRANSPORTE / COSTE TOTAL
			double pcTraCos = dcvtransp / dcostetot;
			table.addCell(FRAparen.getCelda("% Transporte / Coste Total",fuente,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(formatDivi.format(dcvtransp) +" "+ cddivisa,fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(formatPorc.format(pcTraCos),fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
			
			//% PRODUCTO / COSTE TOTAL
			double pcProCos = dcostprod / dcostetot;
			table.addCell(FRAparen.getCelda("% Producto / Coste Total",fuente,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(formatDivi.format(dcostprod) +" "+ cddivisa,fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda(formatPorc.format(pcProCos),fuente,colCelda,"right")).setBorderColor(new BaseColor(255,255,255));
			*/
			table.writeSelectedRows(0, -1, margeniz+50, 320, canvas);	
			
	    	
	    	documento.newPage();
	    	
    	} catch (Exception e) {
    		
    	}
			
    }
    
    public void pintaOtrosDatos(){
    	
    	try {
    		
    		PdfPTable table = new PdfPTable(new float[] { 2, 2,});
			table.setTotalWidth(200);
		
			Grid grOtrain = null;
		
			ListDesgloseDetalleBDIn desDetalleBDIn = new ListDesgloseDetalleBDIn();
			desDetalleBDIn.setValue("idunicox",idunicox);
			desDetalleBDIn.setValue("idemisor",idemisor);
			desDetalleBDIn.setValue("mcactivo","S");
        	ListDesgloseDetalleBD desDetalleBD = new ListDesgloseDetalleBD(desDetalleBDIn);
        	desDetalleBD.setConnection(con);
        	grOtrain = desDetalleBD.execSelect();
				
			for (int i = 0; i < grOtrain.rowCount(); i++){

				table.addCell(FRAparen.getCelda(grOtrain.getStringCell(i,"txnombre"),fuenteNeg,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
				table.addCell(FRAparen.getCelda(grOtrain.getStringCell(i,"desvalue"),fuente,colCelda,"left")).setBorderColor(new BaseColor(255,255,255));
				
			}
			
			PdfContentByte canvas = writer.getDirectContent();
	    	table.writeSelectedRows(0, -1, margeniz, 150, canvas);
			
    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() +" ERROR pintaDatosClienteEmisor() - "+ e.getMessage());
    	}
    	
    }
    
    public void pintaGraficaMargen(){

    	Image image;
    	String imageUrl;
    	
    	try {

    		imageUrl = PropiedadesJLet.getInstance().getProperty("path.img.statistics") + imgflMar;
    		
		    image = Image.getInstance(imageUrl);
		    image.setAbsolutePosition(margeniz, 530);
		    image.scaleAbsolute(200,200);
		    image.setAlignment(Image.LEFT | Image.TEXTWRAP);//Code 2
			documento.add(image);
			
			
			double dporcben = dbenefici / dcostetot;
			String porcbene = formatPorc.format(dporcben);
			
			Font fuenteBen = new Font(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(34,139,34));
			BaseColor bkcolor = new BaseColor(255,255,255);
			
			if (dbenefici < 0){
				fuenteBen = new Font(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(255,0,0));
			} else if (dbenefici < 10) {
				fuenteBen = new Font(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(255,128,0));
			}
			
			PdfPTable table = new PdfPTable(new float[] { 1});
			table.setTotalWidth(200);
			
			
			String isbeneto = " ";
			
			if ((mostriva != null) && (mostriva.equals("N"))){
				isbeneto += "Neto ";
			}
			
			table.addCell(FRAparen.getCelda("Beneficio "+ isbeneto + benefici +" "+ cddivisa +".",fuenteBen,bkcolor,"center")).setBorderColor(new BaseColor(255,255,255));
    		
			PdfContentByte canvas = writer.getDirectContent();
	    	table.writeSelectedRows(0, -1, margeniz, 520, canvas);
			
    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() + "ERROR pintaPiePagina() "+ e.getMessage());
    	}
    	
    }
    
    public void pintaGraficaCostes(){

    	Image image;
    	String imageUrl;
    	
    	try {

    		imageUrl = PropiedadesJLet.getInstance().getProperty("path.img.statistics") + imgflCos;
    		
		    image = Image.getInstance(imageUrl);
		    image.setAbsolutePosition(margeniz, 170);
		    image.scaleAbsolute(200,200);
		    image.setAlignment(Image.LEFT | Image.TEXTWRAP);//Code 2
			documento.add(image);
			
			
			double dporcben = dbenefici / dcostetot;
			String porcbene = formatPorc.format(dporcben);
			
			Font fuenteBen = new Font(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(34,139,34));
			BaseColor bkcolor = new BaseColor(255,255,255);
			
			if (dbenefici < 0){
				fuenteBen = new Font(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(255,0,0));
			} else if (dbenefici < 10) {
				fuenteBen = new Font(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(255,128,0));
			}
			
			PdfPTable table = new PdfPTable(new float[] { 1});
			table.setTotalWidth(200);
			
			String isbeneto = " ";
			
			if ((mostriva != null) && (mostriva.equals("N"))){
				isbeneto += "Neto ";
			}
			
			PdfContentByte canvas = writer.getDirectContent();
	    	table.writeSelectedRows(0, -1, margeniz, 470, canvas);
			
    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() + "ERROR pintaPiePagina() "+ e.getMessage());
    	}
    	
    }
        
    public void pintaDetalleProducto(){

    	Image image;
    	
    	try {

			
			Font fuenteBen = new Font(FontFamily.HELVETICA, 9, Font.BOLD, colLetra);
			
			BaseColor bkcolor = new BaseColor(255,255,255);
			
			image = getBarcode(writer,idunicox);
			image.setAbsolutePosition(margeniz+350, 760);
			documento.add(image);
			
			
			
			PdfPTable table = new PdfPTable(new float[] { 1});
			table.setTotalWidth(200);
			
			table.addCell(FRAparen.getCelda(gdProduc.getStringCell(0, "codprodu") +" - "+ gdProduc.getStringCell(0, "txmarcax") +" "+ gdProduc.getStringCell(0, "txmodelo"),fuenteBen,bkcolor,"center")).setBorderColor(new BaseColor(255,255,255));
			//table.addCell(FRAparen.getCelda("PH734 - Lenovo T410 + Docking ",fuenteBen,bkcolor,"center")).setBorderColor(new BaseColor(255,255,255));
    		
			PdfContentByte canvas = writer.getDirectContent();
	    	table.writeSelectedRows(0, -1, margeniz+250, 720, canvas);
			
	    	
	    	
    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() + "ERROR pintaPiePagina() "+ e.getMessage());
    	}
    	
    }
    
    public void pintaPiePagina(){

    	int altlinea = 50;
    	
    	try {
	    	
	    	String trademar = "Informe desglose de costes generado desde JLet";
	    	FRAparen.absTextColor(writer,trademar,margeniz + 50,altlinea + 32,6,colLetra);
			
    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() + "ERROR pintaPiePagina() "+ e.getMessage());
    	}
    	
    }
    
    public void insCabeceraTablaIngresos (PdfPTable tabla){
    	
		PdfPCell cell = new PdfPCell(new Paragraph("Detalle de Ingresos",fuenteCab));
        cell.setColspan(3);
        cell.setBorder(0);
        cell.setBackgroundColor(colCabec);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        
		tabla.addCell(cell).setBorderColor(new BaseColor(255,255,255));	
		
		tabla.addCell(FRAparen.getCelda("Tipo Ingreso ",fuenteCab,colCabec,"center")).setBorderColor(new BaseColor(255,255,255));
		tabla.addCell(FRAparen.getCelda("Valor",fuenteCab,colCabec,"center")).setBorderColor(new BaseColor(255,255,255));
		tabla.addCell(FRAparen.getCelda("%",fuenteCab,colCabec,"center")).setBorderColor(new BaseColor(255,255,255));

		
    }
   
    public void insCabeceraTablaCostes (PdfPTable tabla){
    	
		tabla.addCell(FRAparen.getCelda("Tipo Coste ",fuenteCab,colCabec,"center")).setBorderColor(new BaseColor(255,255,255));
		tabla.addCell(FRAparen.getCelda("Valor",fuenteCab,colCabec,"center")).setBorderColor(new BaseColor(255,255,255));
		tabla.addCell(FRAparen.getCelda("%",fuenteCab,colCabec,"center")).setBorderColor(new BaseColor(255,255,255));
		
    }
    
//------------------------------------------ END PINTAR DATOS -----------------------------    
   
    public Grid recuperoLineasDesglose(String idemisor, String idunicox) {
    	
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
    	
    	Grid gddscost = null;
    	
    	try {
			
	        	ListDesgloseCostesBDIn desCosteBDIn = new ListDesgloseCostesBDIn();
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
	        		//INGRESO DE TRANSPORTE
	        		if (gddscost.getStringCell(i, "codedesg").equals("IG00004")){
	        			dingtrans = Double.parseDouble(gddscost.getStringCell(i, "desvalue"));
	        		}
	        		//COSTE DEL PRODUCTO
	        		if (gddscost.getStringCell(i, "codedesg").equals("CP00001")){
	        			dcostprod = Double.parseDouble(gddscost.getStringCell(i, "desvalue"));
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
	        	
		        	dccaduana = Double.parseDouble(ccaduana);
		        	dcccompra = Double.parseDouble(cccompra);
		        	dcctransp = Double.parseDouble(cctransp);
		        	dcvaduana = Double.parseDouble(cvaduana);
		        	dcvcompra = Double.parseDouble(cvcompra);
		        	dcvtransp = Double.parseDouble(cvtransp);
		        	dingtotal = Double.parseDouble(ingtotal);
		        	
		        	//Valores Netos
		        	dcosnetot = dccaduana + dcccompra + dcctransp + dcvaduana + dcvcompra + dcvtransp;
		        	dingnetot = dingtotal;
		        	
		        	
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
		        	dcostetot = dtotalcom + dtotalven;
		        	dbenefici = dingtotal - dcostetot;
		        	dbeneneto = dingnetot - dcosnetot;
		        	
		        	dtgdCost.addColumn("coluname");
		        	dtgdCost.addColumn("coluvalu");

		        	dtgdMarg.addColumn("coluname");
		        	dtgdMarg.addColumn("coluvalu");
		        	
		        	ArrayList<String> row = new ArrayList<String>();
		        	
		        	if (dccaduana > 0) {
			        	row.add("Cost. C. Aduanero");
			        	row.add(String.valueOf(dccaduana));
			        	dtgdCost.addRow(row);
			        	hmCatego.put("CATOTAL","Cost. C. Aduanero");
		        	}
		        	
		        	if (dcccompra > 0) {
			        	row = new ArrayList<String>();
			        	row.add("Cost. C. Compra");
			        	row.add(String.valueOf(dcccompra));
			        	dtgdCost.addRow(row);
			        	hmCatego.put("CATOTAL","Cost. C. Compra");
		        	}
		        	
		        	if (dcctransp > 0) {
			        	row = new ArrayList<String>();
			        	row.add("Cost. C. Transporte");
			        	row.add(String.valueOf(dcctransp));
			        	dtgdCost.addRow(row);
			        	hmCatego.put("CTTOTAL","Cost. C. Transporte");
		        	}
		        	
		        	if (dcvaduana > 0) {
			        	row = new ArrayList<String>();
			        	row.add("Cost. V. Aduanero");
			        	row.add(String.valueOf(dcvaduana));
			        	dtgdCost.addRow(row);
			        	hmCatego.put("VATOTAL","Cost. V. Aduanero");
		        	}
		        	
		        	if (dcvcompra > 0) {
			        	row = new ArrayList<String>();
			        	row.add("Cost. V. Compra");
			        	row.add(String.valueOf(dcvcompra));
			        	dtgdCost.addRow(row);
			        	hmCatego.put("VPTOTAL","Cost. V. Compra");
		        	}
		        	
		        	if (dcvtransp > 0) {	
			        	row = new ArrayList<String>();
			        	row.add("Cost. V. Transporte");
			        	row.add(String.valueOf(dcvtransp));
			        	dtgdCost.addRow(row);
			        	hmCatego.put("VTTOTAL","Cost. V. Transporte");
		        	}
		        	
		        	
		        	row = new ArrayList<String>();
		        	row.add("Costes");
		        	row.add(String.valueOf(dcostetot));
		        	dtgdMarg.addRow(row);
		        	hmCatego.put("Costes","Beneficio");
		        	
		        	row = new ArrayList<String>();
		        	row.add("Beneficio");
		        	row.add(String.valueOf(dbenefici));
		        	dtgdMarg.addRow(row);
		        	hmCatego.put("Benefici","Beneficio");
		        	
		        	coloresGrafica();
		        	GraficoCircular grafCircular = new GraficoCircular();
		        	grafCircular.setCategoryColors(hmColors);
		        	imgflMar = grafCircular.crearGrafica("desgVenta","Desglose Venta", dtgdMarg);
		        	imgflCos = grafCircular.crearGrafica("desgVenta","Desglose Venta", dtgdCost);
		        	
		        	benefici = formatDivi.format(dbenefici);
		        	
	        	}
			
	        	for (int i = 0; i < gddscost.rowCount(); i++) {
	        		
	        		hmValore.put(gddscost.getStringCell(i,"codedesg"),gddscost.getStringCell(i,"desvalue"));
	        		
	        	}
	        	
	        	System.out.println("Se han cargado: "+ hmValore.size() +" costes ");
	        	
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return gddscost;
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
    
    public Grid datosProducto(String imeicode){
    	
    	Grid producto = null;
    	
    	try {
	    	ListStockBDIn productoBDIn = new ListStockBDIn();
	    	productoBDIn.setValue("idemisor",idemisor);
	    	productoBDIn.setValue("imeicode",imeicode);
	    	productoBDIn.setValue("cdestado","NOAPLICA");
	    	ListStockBD productoBD = new ListStockBD(productoBDIn);
	    	productoBD.setConnection(con);
	    	producto = productoBD.execSelect();
    	} catch (Exception e) {
    		
    	}
    	
    	return producto;
    	
    }
    
    public String getFileCreated(){
    	return this.filecrea;    	
    }
    
    private Image getBarcode(PdfWriter pdfWriter, String  codigoTransaccion){
    	PdfContentByte cimg = pdfWriter.getDirectContent();
    	Barcode128 code128 = new Barcode128();
    	code128.setCode(codigoTransaccion);
    	code128.setCodeType(Barcode128.CODE128);
    	code128.setTextAlignment(Element.ALIGN_CENTER);
    	Image image = code128.createImageWithBarcode(cimg, colCorpo, colCorpo);
    	image.scaleAbsolute(150,40);
    	image.setAlignment(Element.ALIGN_CENTER);
    	return image;
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
    
    public void generaPortada(){
    	
    	Image image;
    	int altlinea = 700;
    	String imglogoM = "";
    	
		try {
			 int posy = 180;
    			
				imglogoM = obtenerLogoEmisor(idemisor);
		        String imageUrl = PropiedadesJLet.getInstance().getProperty("path.img.logoinvoice") + imglogoM;
		        
				image = Image.getInstance(imageUrl);
				image.scaleAbsolute(200,50);
			    image.setAbsolutePosition(200, 550);
			    documento.add(image);

			    try {
				    FRAparen.absTextBoldColor(writer, "DESGLOSE DE COSTES",margeniz+170,460 ,16,colCorpo);
				    FRAparen.absTextBoldColor(writer,txemisor,margeniz+140,410 ,16,colCorpo);
			    } catch (Exception e) {
			    	
			    }
			    
			    
		   
		    
			    if (fhdesdex !=null && !fhdesdex.equals("")){
			    	
			    	 FRAparen.absTextBoldColor(writer, "Fecha desde: "+fhdesdex,margeniz+80,posy ,11,colCorpo);	
			    	 posy-=25;
			    }
			    
			    if (fhhastax !=null && !fhhastax.equals("")){
			    	
			    	 FRAparen.absTextBoldColor(writer, "Fecha Hasta: "+fhhastax,margeniz+80,posy ,11,colCorpo);	
			    	 posy-=25;
			    }
			    
			    if (txpaisxx !=null && !txpaisxx.equals("")){
			    	
			    	 FRAparen.absTextBoldColor(writer, "País: "+txpaisxx,margeniz+80,posy ,11,colCorpo);	
			    	 posy-=25;
			    }
			    
			    if (txcanalx !=null && !txcanalx.equals("")){
			    	
			    	 FRAparen.absTextBoldColor(writer, "Canal Venta: "+txcanalx,margeniz+80,posy ,11,colCorpo);
			    	 posy-=25;
			    }
		    
			    FRAparen.absTextColor(writer, "Informe generado desde la herramienta JLet, el "+VariablesStatic.fechaHoy() +" a las "+  VariablesStatic.horaSeg() ,250,20 ,8,colCorpo); 
		    
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
			System.err.println(this.getClass().getName() +" ERROR pintaCabecera() - "+ e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(this.getClass().getName() +" ERROR pintaCabecera() - "+ e.getMessage());
		}
    	
		documento.newPage();
    }
    
    public String obtenerLogoEmisor(String idemisor){
		
 		String logoclie = "nologo.png";
 		
 		try{
 			ListDetalleClienteBDIn logoBDIn = new ListDetalleClienteBDIn();
 			logoBDIn.setValue("idemisor",idemisor);
 			logoBDIn.setValue("idclient","0");
 			
 			ListDetalleClienteBD logoBD = new ListDetalleClienteBD(logoBDIn);
 			logoBD.setConnection(con);
         	Grid grLogoCl = logoBD.execSelect();
         	
         	if (grLogoCl.rowCount() > 0){
         		logoclie = grLogoCl.getStringCell(0,"logoclie");
         	}
 	        
 		} catch (Exception e) {
 			System.err.println(this.getClass().getName() +" ERROR -  Al obtener el logo del cliente. "+ e.getMessage());
 		}
 		
 		return logoclie;
 	}
    
}
	 

