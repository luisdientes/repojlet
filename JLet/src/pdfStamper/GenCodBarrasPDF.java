package pdfStamper;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import utils.PropiedadesJLet;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import facturacion.parents.FacturaPDF;


public class GenCodBarrasPDF extends Service {
	
	//HEREDA METODOS
	FacturaPDF FRAparen = new FacturaPDF();
	
	Document documento = null;
	PdfWriter writer;
	
	//DATOS DEL DOCUMENTO
	
	

	//PARAMETROS DE ENTRADA
	String codvalue = "";
	float anchcdba = 0;
	float altocdba = 0;
	float xposicio = 0;	
	float yposicio = 0;
	String texto1xx = "";
	float xpostex1 = 0;	
	float ypostex1 = 0;
	String texto2xx = "";
	float xpostex2 = 0;	
	float ypostex2 = 0;
	
	
	//PARÁMETROS DE SALIDA
	String namefile = "";
	String filecrea = "";
	
	//OTROS PARÁMETROS

	
	      
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("codvalue");
			input.addVariable("anchcdba");
			input.addVariable("altocdba");
			input.addVariable("xposicio");
			input.addVariable("yposicio");
			input.addVariable("texto1xx");
			input.addVariable("xpostex1");
			input.addVariable("ypostex1");
			input.addVariable("texto2xx");
			input.addVariable("xpostex2");
			input.addVariable("ypostex2");
						
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
			output.addVariable("idemisor");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
	public void recuperaInput(ObjectIO input){
		
		try {
			codvalue = input.getStringValue("codvalue");
			anchcdba = input.getFloatValue("anchcdba");
			altocdba = input.getFloatValue("altocdba");
			xposicio = input.getFloatValue("xposicio");
			yposicio = input.getFloatValue("yposicio");
			texto1xx = input.getStringValue("texto1xx");
			xpostex1 = input.getFloatValue("xpostex1");
			ypostex1 = input.getFloatValue("ypostex1");
			texto2xx = input.getStringValue("texto2xx");
			xpostex2 = input.getFloatValue("xpostex2");
			ypostex2 = input.getFloatValue("ypostex2");
			FRAparen.setConexion(this.getConnection());
		} catch (Exception e1) {
			System.err.println(this.getClass().getName() +"- ERROR - "+ e1.getMessage());
			e1.printStackTrace();
		}
		
	}
	
    public void process(ObjectIO input, ObjectIO output){
    	
    	try {

    		//OJO pasarle el idemisor
    		
    		String idemisor = "1";
    		
    		recuperaInput(input);
	    	
	    	Rectangle pageSize = new Rectangle(PageSize.A4);
	    	//pageSize.setBackgroundColor(Color.TRANSLUCENT);
	    	pageSize.rotate();
	        documento = new Document(pageSize);
	        
	        System.out.println("WIDTH "+documento.getPageSize().getWidth());
	        System.out.println("HEIGHT "+documento.getPageSize().getHeight());
	        
	        namefile = "BarCode_"+ idemisor +"_codigoBARRAS.pdf";
	        filecrea = "C://DATOS//LRA//tmp//"+ namefile;	  
	    	//filecrea = PropiedadesJLet.getInstance().getProperty("path.gen.recibo") +"emisor_"+ idemisor +"/"+ namefile;	  
	    	System.out.println("***************************Se va a crear este codigo de barras: "+ filecrea);
	    	writer = PdfWriter.getInstance(documento, new FileOutputStream(filecrea));
	        documento.open();
	       
	        pintaCodigoBarras();
	        
	        absText(writer,texto1xx,xpostex1,ypostex1,8);
	        
	        absText(writer,texto2xx,xpostex2,ypostex2,8);
	        
	    	try {
	    		output.setValue("idemisor", idemisor);
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
    
       
    public void pintaCodigoBarras() {
    	
    	Image imageLogo;
    	Image imageBarc = null;
    	String imageUrl;
    	
		Font fuente = new Font(FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(64, 64, 64));
		BaseColor bkcolor = new BaseColor(255, 255, 255);
    	
    	try {
    		
	    	PdfPTable table = new PdfPTable(new float[] {1 ,1, 1, 1, 1});
			//table.setTotalWidth(900);
			table.getDefaultCell().setBorder(0);
		//	imageUrl = PropiedadesJLet.getInstance().getProperty("path.img.logos") + "logo_recibos.png";

			//imageLogo = Image.getInstance(imageUrl);
			
			int yinic =805;
			int xinic = 0;
			
			int posicion = 0; //posicion donde comenzara a imprimir
	
			//for (int i = 0; i < listcode.size(); i++){
				
				
			String codeunic = codvalue;
			
			//System.out.println(codeunic.substring(0,5) +"-"+ codeunic.substring(6,7));
			
			imageBarc = getBarcode(documento,writer,codeunic);
		
			
			PdfPTable tablein = new PdfPTable(new float[] {1f,3f,1f });

			tablein.getDefaultCell().setBorder(0);
			tablein.setTotalWidth(documento.getPageSize().getWidth());
			
			tablein.addCell(FRAparen.getCelda(" ",fuente,bkcolor,"center")).setBorder(0);
			//tablein.addCell(imageLogo);
			tablein.addCell(FRAparen.getCelda(" ",fuente,bkcolor,"center")).setBorder(0);
			
			tablein.addCell(FRAparen.getCelda(" ",fuente,bkcolor,"center")).setBorder(0);
			tablein.addCell(imageBarc);
			tablein.addCell(FRAparen.getCelda(" ",fuente,bkcolor,"center")).setBorder(0);
			
			table.addCell(tablein);

			imageBarc.scaleAbsolute(anchcdba, altocdba);
			imageBarc.setAbsolutePosition(xposicio, yposicio);
			
			documento.add(imageBarc);
				
			
    	} catch (Exception e) {
    		
    	}
			
    }
    
    private static Image getBarcode(Document document,  PdfWriter pdfWriter, String servicio){
    
    	PdfContentByte cimg = pdfWriter.getDirectContent();
    	Barcode128 code128 = new Barcode128();
    	code128.setCode(servicio);
    	code128.setCodeType(Barcode128.CODE128);
    	code128.setTextAlignment(Element.ALIGN_CENTER);
    	Image image = code128.createImageWithBarcode(cimg, null, null);
    	float scaler = (5);
    	image.scalePercent(scaler);
    	image.setAlignment(Element.ALIGN_CENTER);
    	image.scaleAbsoluteWidth(800);
    	
    	System.out.print(" Width: "+ image.getWidth());
    	System.out.println(" Heights: "+ image.getHeight());
    	return image;
    	
    }
    
    private static String addZeroLeft(String num) {
    	NumberFormat formatter = new DecimalFormat("0000000");
    	return formatter.format((num != null) ? Integer.parseInt(num) : 0000000);
    }
    
    public void absText(PdfWriter twriter, String text, float ypostex12, float ypostex22, int fontsize) {
        try {
          PdfContentByte cb = twriter.getDirectContent();
          BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
          cb.saveState();
          cb.beginText();
          cb.moveText(ypostex12, ypostex22);
          cb.setFontAndSize(bf, fontsize);
          cb.setColorFill(new BaseColor(0, 0, 0));
          cb.showText(text);
          cb.endText();
          cb.restoreState();
        } catch (DocumentException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    
}