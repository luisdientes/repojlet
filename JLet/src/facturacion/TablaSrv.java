package facturacion;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import utils.PropiedadesJLet;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;



import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;



import facturacion.parents.FacturaPDF;

public class TablaSrv extends Service {
	
	//HEREDA METODOS
	
	
	Document documento = null;
	PdfWriter writer;
	Font fuenteCab = new Font(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(227, 4, 33));
	BaseColor bkcolorCab = new BaseColor(255, 255, 255);
	
	FacturaPDF FRAparen = new FacturaPDF();
	
	//DATOS DEL DOCUMENTO
	int pagetotal = 1;
	int pxfrombt  = 800;
	int margeniz  = 30;
	int inmargde  = 415;	
	int altlinea  = 650;
	int anctabla = 50;
	int longline = 350;
	

	
	//PARÁMETROS A RELLENAR EN EL RECIBO
	

	
	
	//PARAMETROS DE ENTRADA
	
	//PARÁMETROS DE SALIDA
	String namefile = "";
	String filecrea = "";
	
	//OTROS PARÁMETROS

	      
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
		
						
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
    

	
    public void process(ObjectIO input, ObjectIO output){
    	
    	try {
    				
	 
		    	Rectangle pageSize = new Rectangle(PageSize.LETTER);
		    	pageSize.setBackgroundColor(new BaseColor(255, 255, 255));
		    	
		    	pageSize.rotate();
		        documento = new Document(pageSize,20, 20, 190, 190);
		       
		
		      
		       
		     
		        namefile = "Tabla "+".pdf";
		    	filecrea = PropiedadesJLet.getInstance().getProperty("path.gen.invoice") +"emisor_3/"+ namefile;
		    	System.out.println("***********************Se va a crear este recibo: "+ filecrea);
		    	writer = PdfWriter.getInstance(documento, new FileOutputStream(filecrea));
		        documento.open();
		        pxfrombt = 715;
		      
		        pintaLineas();
		        pintaTabla();
		        pintaPieTabla();
		 
	        
	    	try {
	    		
	    		System.out.println("AltaRecibo Srv ------------  "+namefile);
	    		
	    		
	    		output.setValue("filecrea", namefile);
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
    

    
   
// ------------------------------------------ PINTAR DATOS -----------------------------
    
    
    public void pintaLineas(){
    	
    	FRAparen.absLineaFina(writer, margeniz,altlinea,longline);
    	FRAparen.absTextBold(writer, "Su cartera",margeniz,altlinea + 20,6);
    	FRAparen.absText(writer, "Distribución Patrimonial",margeniz,altlinea + 10,6);
    	
    }
    
    public void pintaPieTabla(){
    	
    	PdfContentByte canvas = writer.getDirectContent();
    	//cb.setColorFill(new BaseColor(0, 0, 0));
    	canvas.setColorFill(new BaseColor(227, 4, 33));
        canvas.saveState();
        canvas.setLineWidth((float) 5 / 10);
        canvas.moveTo(margeniz, altlinea-450);
        canvas.lineTo(margeniz + longline, altlinea-450);
        canvas.stroke();
        canvas.restoreState();
        
        FRAparen.absTextBoldColor(writer, "Rendimientos y resultados de posiciones vendidas",margeniz,altlinea-460,6,new BaseColor(227, 4, 33));
    }
    
    public void pintaTabla(){
    	 try {
 	    	
	    		int anctabla  = 100;
	    		PdfPTable table = new PdfPTable(4);
				table.setWidthPercentage(55f);
				table.setHorizontalAlignment(Element.ALIGN_LEFT);
				insCabeceraTabla(table);
				pintaEspacioBlanco(3);
				pintaCuerpoTabla(table);
			
				documento.add(table);
				
    	 }catch(Exception ex){
    		 System.out.println("error al crear tabla");
    	 }
    	
    }
    
    
 public void insCabeceraTabla (PdfPTable tabla){
    	
    
		
		tabla.addCell(FRAparen.getCelda(" ",fuenteCab,bkcolorCab,"center")).setBorderColor(new BaseColor(255,255,255));
		tabla.addCell(FRAparen.getCelda(" ",fuenteCab,bkcolorCab,"center")).setBorderColor(new BaseColor(255,255,255));
		tabla.addCell(FRAparen.getCelda("Valoración euros",fuenteCab,bkcolorCab,"center")).setBorderColor(new BaseColor(255,255,255));
		tabla.addCell(FRAparen.getCelda("Total",fuenteCab,bkcolorCab,"center")).setBorderColor(new BaseColor(255,255,255));
		
		tabla.addCell(FRAparen.getCelda("Tipo de activo ",fuenteCab,bkcolorCab,"center")).setBorderColor(new BaseColor(255,255,255));
		tabla.addCell(FRAparen.getCelda("31/08/2012",fuenteCab,bkcolorCab,"center")).setBorderColor(new BaseColor(255,255,255));
		tabla.addCell(FRAparen.getCelda("31/10/2012",fuenteCab,bkcolorCab,"center")).setBorderColor(new BaseColor(255,255,255));
		tabla.addCell(FRAparen.getCelda("% Inversión",fuenteCab,bkcolorCab,"center")).setBorderColor(new BaseColor(255,255,255));
		
		tabla.addCell(FRAparen.getCelda(" ",fuenteCab,bkcolorCab,"center")).setBorderColor(new BaseColor(255,255,255));
		tabla.addCell(FRAparen.getCelda(" ",fuenteCab,bkcolorCab,"center")).setBorderColor(new BaseColor(255,255,255));
		tabla.addCell(FRAparen.getCelda(" ",fuenteCab,bkcolorCab,"center")).setBorderColor(new BaseColor(255,255,255));
		tabla.addCell(FRAparen.getCelda(" ",fuenteCab,bkcolorCab,"center")).setBorderColor(new BaseColor(255,255,255));
		
    }
 
 
 public void pintaCuerpoTabla(PdfPTable tabla){
	
		
		BaseColor bkcolorCelda = new BaseColor(193, 193, 193);
		BaseColor bkcolorBorde = new BaseColor(225, 225, 225);
		
	    PdfPCell cell 	= null;

		Font font		= FontFactory.getFont("Times-Roman", 10);
	    Font fontbold 	= FontFactory.getFont("Times-Roman", 10, Font.BOLD);
		
	    Chunk chunkNom 	= null;
	 
	    Phrase contcell = null;
	    
		
	/*	tabla.addCell(FRAparen.getCelda(" Total liquidez",fuenteCab,bkcolorCab,"center")).setBorderColor(new BaseColor(255,255,255));
		tabla.addCell(FRAparen.getCelda("23.924,47 ",fuenteCab,bkcolorCab,"center")).setBorderColor(new BaseColor(255,255,255));
		tabla.addCell(FRAparen.getCelda("25.458,20",fuenteCab,bkcolorCab,"center")).setBorderColor(new BaseColor(255,255,255));
		tabla.addCell(FRAparen.getCelda("5,07",fuenteCab,bkcolorCab,"center")).setBorderColor(new BaseColor(255,255,255));*/
		
	    
	   
	    chunkNom = new Chunk("Total liquidez:", fontbold);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorder(0);
		cell.setFixedHeight(30);
		cell.setHorizontalAlignment((Element.ALIGN_LEFT));
		cell.setBackgroundColor(bkcolorCelda);
        tabla.addCell(cell);
        
        
        chunkNom = new Chunk("23.924,47", fontbold);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setFixedHeight(30);
		cell.setHorizontalAlignment((Element.ALIGN_CENTER));
		cell.setBackgroundColor(bkcolorCelda);
        tabla.addCell(cell);
        
        
        chunkNom = new Chunk("25.458,20", fontbold);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		cell.setFixedHeight(30);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_CENTER));
		cell.setBackgroundColor(bkcolorCelda);
        tabla.addCell(cell);
        
        
        chunkNom = new Chunk("5,07", fontbold);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		cell.setFixedHeight(30);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_CENTER));
		cell.setBackgroundColor(bkcolorCelda);
        tabla.addCell(cell);
        
        
        
        
        
        
        
        chunkNom = new Chunk("Total Renta Fija:", fontbold);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		cell.setBorder(Rectangle.TOP);
		cell.setBorderWidth(5);
		cell.setBorderColor(bkcolorBorde);
		cell.setFixedHeight(30);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_LEFT));
		cell.setBackgroundColor(bkcolorCelda);
        tabla.addCell(cell);
        
        
        chunkNom = new Chunk("104.457,58", fontbold);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		cell.setBorder(Rectangle.TOP);
		cell.setBorderWidth(5);
		cell.setBorderColor(bkcolorBorde);
		cell.setFixedHeight(30);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_CENTER));
		cell.setBackgroundColor(bkcolorCelda);
        tabla.addCell(cell);
        
        
        chunkNom = new Chunk("105.056,39", fontbold);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		cell.setBorder(Rectangle.TOP);
		cell.setBorderWidth(5);
		cell.setBorderColor(bkcolorBorde);
		cell.setFixedHeight(30);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_CENTER));
		cell.setBackgroundColor(bkcolorCelda);
        tabla.addCell(cell);
        
        
        chunkNom = new Chunk("20,97", fontbold);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		cell.setBorder(Rectangle.TOP);
		cell.setBorderWidth(5);
		cell.setBorderColor(bkcolorBorde);
		cell.setFixedHeight(30);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_CENTER));
		cell.setBackgroundColor(bkcolorCelda);
        tabla.addCell(cell);
        
        
        
        
        
        
        
        chunkNom = new Chunk("Total Activos Mixtos, retorno absoluto y materias primas", fontbold);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		cell.setBorder(Rectangle.TOP);
		cell.setBorderWidth(5);
		cell.setBorderColor(bkcolorBorde);
		cell.setFixedHeight(45);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_LEFT));
		cell.setBackgroundColor(bkcolorCelda);
        tabla.addCell(cell);
        
        
        chunkNom = new Chunk("294.174,71", fontbold);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		cell.setBorder(Rectangle.TOP);
		cell.setBorderWidth(5);
		cell.setBorderColor(bkcolorBorde);
		cell.setFixedHeight(30);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_CENTER));
		cell.setBackgroundColor(bkcolorCelda);
        tabla.addCell(cell);
        
        
        chunkNom = new Chunk("295.503,11", fontbold);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		cell.setBorder(Rectangle.TOP);
		cell.setBorderWidth(5);
		cell.setBorderColor(bkcolorBorde);
		cell.setFixedHeight(30);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_CENTER));
		cell.setBackgroundColor(bkcolorCelda);
        tabla.addCell(cell);
        
        
        chunkNom = new Chunk("58,96", fontbold);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		cell.setBorder(Rectangle.TOP);
		cell.setBorderWidth(5);
		cell.setBorderColor(bkcolorBorde);
		cell.setFixedHeight(30);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_CENTER));
		cell.setBackgroundColor(bkcolorCelda);
        tabla.addCell(cell);
        
        
        
        
        
        
        chunkNom = new Chunk("Renta variable España", font);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		
		cell.setFixedHeight(30);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_LEFT));
		//cell.setBackgroundColor(bkcolorCelda);
        tabla.addCell(cell);
        
        
        chunkNom = new Chunk("25.799,55", font);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		cell.setFixedHeight(30);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_CENTER));
		//cell.setBackgroundColor(bkcolorCelda);
        tabla.addCell(cell);
        
        
        chunkNom = new Chunk("26.816,87", fontbold);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		cell.setFixedHeight(30);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_CENTER));
		//cell.setBackgroundColor(bkcolorCelda);
        tabla.addCell(cell);
        
        
        chunkNom = new Chunk("5,35", font);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		
		cell.setFixedHeight(30);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_CENTER));
		//cell.setBackgroundColor(bkcolorCelda);
        tabla.addCell(cell);
        
        
        
        
        chunkNom = new Chunk("Renta variable Europa", font);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		
		cell.setFixedHeight(30);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_LEFT));
		//cell.setBackgroundColor(bkcolorCelda);
        tabla.addCell(cell);
        
        
        chunkNom = new Chunk("7.394,40", font);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		cell.setFixedHeight(30);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_CENTER));
		//cell.setBackgroundColor(bkcolorCelda);
        tabla.addCell(cell);
        
        
        chunkNom = new Chunk("7.525,44", fontbold);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		cell.setFixedHeight(30);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_CENTER));
		//cell.setBackgroundColor(bkcolorCelda);
        tabla.addCell(cell);
        
        
        chunkNom = new Chunk("1,50", font);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		
		cell.setFixedHeight(30);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_CENTER));
		//cell.setBackgroundColor(bkcolorCelda);
        tabla.addCell(cell);
        
        
        
        
        
        
        chunkNom = new Chunk("Renta variable EEUU / Global", font);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		
		cell.setFixedHeight(40);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_LEFT));
		//cell.setBackgroundColor(bkcolorCelda);
        tabla.addCell(cell);
        
        
        chunkNom = new Chunk(" ", font);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		cell.setFixedHeight(30);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_CENTER));
		//cell.setBackgroundColor(bkcolorCelda);
        tabla.addCell(cell);
        
        
        chunkNom = new Chunk(" ", fontbold);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		cell.setFixedHeight(30);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_CENTER));
		//cell.setBackgroundColor(bkcolorCelda);
        tabla.addCell(cell);
        
        
        chunkNom = new Chunk(" ", font);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		
		cell.setFixedHeight(30);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_CENTER));
		//cell.setBackgroundColor(bkcolorCelda);
        tabla.addCell(cell);
        
        
        
        
        
        chunkNom = new Chunk("Renta variable Emergentes / Asia", font);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		
		cell.setFixedHeight(40);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_LEFT));
		//cell.setBackgroundColor(bkcolorCelda);
        tabla.addCell(cell);
        
        
        chunkNom = new Chunk(" ", font);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		cell.setFixedHeight(30);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_CENTER));
		//cell.setBackgroundColor(bkcolorCelda);
        tabla.addCell(cell);
        
        
        chunkNom = new Chunk(" ", fontbold);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		cell.setFixedHeight(30);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_CENTER));
		//cell.setBackgroundColor(bkcolorCelda);
        tabla.addCell(cell);
        
        
        chunkNom = new Chunk(" ", font);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		
		cell.setFixedHeight(30);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_CENTER));
		//cell.setBackgroundColor(bkcolorCelda);
        tabla.addCell(cell);
        
        
        

        
        chunkNom = new Chunk("Total Renta variable", fontbold);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell));
		cell.setBorder(0);
		cell.setFixedHeight(40);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_LEFT));
		cell.setBackgroundColor(bkcolorCelda);
        tabla.addCell(cell);
        
        
        chunkNom = new Chunk("33.193,95", font);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		cell.setFixedHeight(30);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_CENTER));
		cell.setBackgroundColor(bkcolorCelda);
        tabla.addCell(cell);
        
        
        chunkNom = new Chunk("34.342,31", fontbold);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		
		cell.setFixedHeight(30);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_CENTER));
		cell.setBackgroundColor(bkcolorCelda);
        tabla.addCell(cell);
        
        
        chunkNom = new Chunk("6,85", fontbold);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		cell.setFixedHeight(30);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_CENTER));
		cell.setBackgroundColor(bkcolorCelda);
        tabla.addCell(cell);
        
        
        
        chunkNom = new Chunk("Total Otros activos", fontbold);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		cell.setBorder(Rectangle.TOP);
		cell.setBorderWidth(5);
		cell.setBorderColor(bkcolorBorde);
		cell.setFixedHeight(40);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_LEFT));
		cell.setBackgroundColor(bkcolorCelda);
        tabla.addCell(cell);
        
        
        chunkNom = new Chunk("57.472,52", font);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		cell.setBorder(Rectangle.TOP);
		cell.setBorderWidth(5);
		cell.setBorderColor(bkcolorBorde);
		cell.setFixedHeight(30);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_CENTER));
		cell.setBackgroundColor(bkcolorCelda);
        tabla.addCell(cell);
        
        
        chunkNom = new Chunk("40.794,94", fontbold);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		cell.setBorder(Rectangle.TOP);
		cell.setBorderWidth(5);
		cell.setBorderColor(bkcolorBorde);
		cell.setFixedHeight(30);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_CENTER));
		cell.setBackgroundColor(bkcolorCelda);
        tabla.addCell(cell);
        
        
        chunkNom = new Chunk("8,15", fontbold);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		cell.setBorder(Rectangle.TOP);
		cell.setBorderWidth(5);
		cell.setBorderColor(bkcolorBorde);
		cell.setFixedHeight(30);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_CENTER));
		cell.setBackgroundColor(bkcolorCelda);
        tabla.addCell(cell);
        
        
        
        
        
        
        chunkNom = new Chunk("Total Inversiones en Euros", fontbold);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		cell.setBorder(Rectangle.TOP);
		cell.setBorderWidth(1);
		cell.setFixedHeight(40);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_LEFT));
        tabla.addCell(cell);
        
        
        chunkNom = new Chunk("513.223,23", font);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		cell.setBorder(Rectangle.TOP);
		cell.setBorderWidth(1);
		cell.setFixedHeight(30);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_CENTER));
        tabla.addCell(cell);
        
        
        chunkNom = new Chunk("501.154,95", fontbold);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		cell.setBorder(Rectangle.TOP);
		cell.setBorderWidth(1);
		cell.setFixedHeight(30);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_CENTER));
        tabla.addCell(cell);
        
        
        chunkNom = new Chunk("100", fontbold);
	    contcell = new Phrase(chunkNom);
		cell = new PdfPCell(new Phrase(contcell ));
		cell.setBorder(0);
		cell.setBorder(Rectangle.TOP);
		cell.setBorderWidth(1);
		cell.setFixedHeight(30);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment((Element.ALIGN_CENTER));
        tabla.addCell(cell);	
	 
 }
    
    
/*
    
    public void pintaDatosTecnicos() throws DocumentException{
    	
    	try {
	    	
    		Font font		= FontFactory.getFont("Times-Roman", 10);
    	    Font fontbold 	= FontFactory.getFont("Times-Roman", 10, Font.BOLD);
    		
    	    Chunk chunkNom 	= null;
    	    Chunk chunkSpa 	= null;
    	    Chunk chunkVal 	= null;
    	    
    	    Phrase contcell = null;
    	    
    	    PdfPCell cell 	= null;
    		
    	    try {
    	    	
	    		int anctabla  = 600;
	    		PdfPTable table = new PdfPTable(new float[] {1});
	    		table.setTotalWidth(anctabla);
	    		
	
	            //----------------------		
	    	    //Linea 1
	            //----------------------
	    	    //Nombre (6)
	    	    chunkNom = new Chunk("Borde Superior:", fontbold);
	    	    chunkSpa = new Chunk("  ", font);
	    	    chunkVal = new Chunk(rzsocial, font);
	    	    
	    	    contcell = new Phrase(chunkNom);
	    	    contcell.add(chunkSpa);
	    	    contcell.add(chunkVal);
	    	    
	    		cell = new PdfPCell(contcell);
	            cell.setBorder(Rectangle.TOP);
	            table.addCell(cell);
	            
	            
	            
	            // EPACIO BLanco
	            chunkNom = new Chunk(" ", fontbold);
	    	    chunkSpa = new Chunk("  ", font);
	    	    chunkVal = new Chunk(rzsocial, font);
	    	    
	    	    contcell = new Phrase(chunkNom);
	    	    contcell.add(chunkSpa);
	    	    contcell.add(chunkVal);
	    	    
	    		cell = new PdfPCell(contcell);
	            cell.setBorder(Rectangle.NO_BORDER);
	            table.addCell(cell);
	            
	            
	            
	            
	            //borde inferior
	            chunkNom = new Chunk("Borde bottom y tamaño :", fontbold);
	    	    chunkSpa = new Chunk("  ", font);
	    	    chunkVal = new Chunk(fecharec, font);
	    	    
	    	    contcell = new Phrase(chunkNom);
	    	    contcell.add(chunkSpa);
	    	    contcell.add(chunkVal);
	    	   
	    		cell = new PdfPCell(new Phrase(contcell));
	    		cell.setBorder(Rectangle.BOTTOM);
		    	cell.setBorderColorBottom(BaseColor.BLACK);
		        cell.setBorderWidthBottom(2f);
	            table.addCell(cell);
	           
	        // EPACIO BLanco
	            chunkNom = new Chunk(" ", fontbold);
	    	    chunkSpa = new Chunk("  ", font);
	    	    chunkVal = new Chunk(rzsocial, font);
	    	    
	    	    contcell = new Phrase(chunkNom);
	    	    contcell.add(chunkSpa);
	    	    contcell.add(chunkVal);
	    	    
	    		cell = new PdfPCell(contcell);
	            cell.setBorder(Rectangle.NO_BORDER);
	            table.addCell(cell);
	            
	           
	           
	           // BORDE IZQUIERDA
	            chunkNom = new Chunk("Borde izquierda :", fontbold);
	    	    chunkSpa = new Chunk("  ", font);
	    	    chunkVal = new Chunk(fecharec, font);
	    	    
	    	    contcell = new Phrase(chunkNom);
	    	    contcell.add(chunkSpa);
	    	    contcell.add(chunkVal);
	    	   
	    		cell = new PdfPCell(new Phrase(contcell));
	    		cell.setBorder(Rectangle.LEFT);
	            table.addCell(cell);
	            
	         // EPACIO BLanco
	            chunkNom = new Chunk(" ", fontbold);
	    	    chunkSpa = new Chunk("  ", font);
	    	    chunkVal = new Chunk("", font);
	    	    
	    	    contcell = new Phrase(chunkNom);
	    	    contcell.add(chunkSpa);
	    	    contcell.add(chunkVal);
	    	    
	    		cell = new PdfPCell(contcell);
	            cell.setBorder(Rectangle.NO_BORDER);
	            table.addCell(cell);
	            
	            
	            
	            // BORDE DERECHA
	            chunkNom = new Chunk("Borde Derecha :", fontbold);
	    	    chunkSpa = new Chunk("  ", font);
	    	    chunkVal = new Chunk("", font);
	    	    
	    	    contcell = new Phrase(chunkNom);
	    	    contcell.add(chunkSpa);
	    	    contcell.add(chunkVal);
	    	   
	    		cell = new PdfPCell(new Phrase(contcell));
	    		cell.setBorder(Rectangle.RIGHT);
	            table.addCell(cell);
	            
	            
	            
	            
	            
	            
	         // EPACIO BLanco
	            chunkNom = new Chunk(" ", fontbold);
	    	    chunkSpa = new Chunk("  ", font);
	    	    chunkVal = new Chunk("", font);
	    	    
	    	    contcell = new Phrase(chunkNom);
	    	    contcell.add(chunkSpa);
	    	    contcell.add(chunkVal);
	    	    
	    		cell = new PdfPCell(contcell);
	            cell.setBorder(Rectangle.NO_BORDER);
	            table.addCell(cell);
	            
	            
	            
	            // TODOS LOS BORDES
	            chunkNom = new Chunk("TODOS BORDES:", fontbold);
	    	    chunkSpa = new Chunk("  ", font);
	    	    chunkVal = new Chunk("", font);
	    	    
	    	    contcell = new Phrase(chunkNom);
	    	    contcell.add(chunkSpa);
	    	    contcell.add(chunkVal);
	    	   
	    		cell = new PdfPCell(new Phrase(contcell));
	    		cell.setBorder(Rectangle.BOX);
	            table.addCell(cell);
	           
	           

	    		
	
	            
    			documento.add(table);
    			
    		} catch (DocumentException e) {
    			e.printStackTrace();
    		}
    		
    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() + "ERROR pintaCuerpo() "+ e.getMessage());
    	}
    	
    }
    */
    public void pintaEspacioBlanco(int numLinea) throws DocumentException{
    	
    	
	    
	    PdfPCell cell 	= null;
    	
    	try {
	    	
    		
                	    
    		//----------------------		
    	    //Linea 1
            //----------------------
    		
    		for (int i = 0; i < numLinea; i++){
	    		cell = new PdfPCell(new Phrase(" "));
	    		cell.setBorder(0);
	    		//tabla.addCell(cell);	            
    		}
           
           // documento.add(table);
            
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
    

    
   

    
//------------------------------------------ END PINTAR DATOS -----------------------------    

	public String getFileCreated(){
    	return this.filecrea;    	
    }
     
    
}
	
