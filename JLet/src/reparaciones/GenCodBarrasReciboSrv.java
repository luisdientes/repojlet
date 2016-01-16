package reparaciones;

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
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import facturacion.parents.FacturaPDF;


public class GenCodBarrasReciboSrv extends Service {
	
	//HEREDA METODOS
	FacturaPDF FRAparen = new FacturaPDF();
	
	Document documento = null;
	PdfWriter writer;
	
	//DATOS DEL DOCUMENTO
	int pagetotal = 1;
	int filasxpag = 55;
	int filasprim = 45;
	int nfilascon = 30;
	int nfilasdet = 4;
	int pxfrombt  = 800;
	
	int initabla  = 750;
	int anctabla  = 600;
	
	int lineResu  = 250;
	
	int margeniz  = 0;
	int inmargde  = 415;	
	
	//DATOS DE LA FACTURA
	double porTaxes = 18;
	String divisa = " $RD";
	
	//PARÁMETROS A RELLENAR EN LA FACTURA
	String cabecNFC = "A0100100101";
	String numerNFC = "00000001";
	String txtipnfc = "FACTURA CON VALOR FISCAL";
	String imglogox = "";
	String idtmpfra = "";
	double impbasei = 0;
	double impitbis = 0;
	double imptotal = 0;
	
	//FORMATOS NUMÉRICOS
	DecimalFormat formatUnit = new DecimalFormat("###,##0");
	DecimalFormat formatDivi = new DecimalFormat("###,##0.00");
	DecimalFormat formatPorc = new DecimalFormat("##0.##%");
	DecimalFormat formatCNFC = new DecimalFormat("00000000");		

	//PARAMETROS DE ENTRADA
	String filereci = "";
	String receclie = "";
	String tpclient = "";
	String aniofact = "";	
	String fhfactur = "";
	int tipofact = 0;
	int mcagrupa = 0;
	String formpago = "";
	String condpago = "";
	String tipologo = "";
	
	//PARÁMETROS DE SALIDA
	String namefile = "";
	String filecrea = "";
	
	//OTROS PARÁMETROS
	Grid datosEmi = new Grid();
	Grid datosRec = new Grid();
	
	      
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("filereci");
						
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
			filereci = input.getStringValue("filereci");
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
	    	
	    	Rectangle pageSize = new Rectangle(PageSize.A9);
	    	pageSize.setBackgroundColor(new BaseColor(255, 255, 255));
	    	pageSize.rotate();
	        documento = new Document(pageSize);
	        
	        System.out.println("WIDTH "+documento.getPageSize().getWidth());
	        System.out.println("HEIGHT "+documento.getPageSize().getHeight());
	        
	        namefile = "BarCode_"+ idemisor +"_"+ filereci +".pdf";
	    	filecrea = PropiedadesJLet.getInstance().getProperty("path.gen.recibo") +"emisor_"+ idemisor +"/"+ namefile;	  
	    	System.out.println("***************************Se va a crear este codigo de barras: "+ filecrea);
	    	writer = PdfWriter.getInstance(documento, new FileOutputStream(filecrea));
	        documento.open();
	        pxfrombt = 665;
			
	        pintaCodigoBarras();
	        
	        
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
			imageUrl = PropiedadesJLet.getInstance().getProperty("path.img.logos") + "logo_recibos.png";

			imageLogo = Image.getInstance(imageUrl);
			
			int yinic =805;
			int xinic = 0;
			
			int posicion = 0; //posicion donde comenzara a imprimir
	
			//for (int i = 0; i < listcode.size(); i++){
				
				
			String codeunic = filereci;
			
			System.out.println(codeunic.substring(0,5) +"-"+ codeunic.substring(6,7));
			
			imageBarc = getBarcode(documento,writer,codeunic.substring(0,5),codeunic.substring(6,codeunic.length()));
		
			
			PdfPTable tablein = new PdfPTable(new float[] {1f,3f,1f });

			tablein.getDefaultCell().setBorder(0);
			tablein.setTotalWidth(documento.getPageSize().getWidth());
			
			tablein.addCell(FRAparen.getCelda(" ",fuente,bkcolor,"center")).setBorder(0);
			tablein.addCell(imageLogo);
			tablein.addCell(FRAparen.getCelda(" ",fuente,bkcolor,"center")).setBorder(0);
			
			tablein.addCell(FRAparen.getCelda(" ",fuente,bkcolor,"center")).setBorder(0);
			tablein.addCell(imageBarc);
			tablein.addCell(FRAparen.getCelda(" ",fuente,bkcolor,"center")).setBorder(0);
			
			table.addCell(tablein);

			/*LUIS*/
			//imageLogo.scaleAbsoluteHeight(28);
			imageBarc.scaleAbsoluteHeight(28);
			//imageLogo.scaleAbsoluteWidth(84);
			imageBarc.scaleAbsoluteWidth(84);
			//imageLogo.setAbsolutePosition(xinic, yinic);
			//imageBarc.setAbsolutePosition(xinic, yinic-26);
			
			//documento.add(imageLogo);
			
			imageUrl = PropiedadesJLet.getInstance().getProperty("path.img.logos") + "logo_recibos.png";
			imageLogo = Image.getInstance(imageUrl);
			imageLogo.scaleAbsoluteHeight(28);
			imageLogo.scaleAbsoluteWidth(84);
			imageLogo.setAbsolutePosition(6,115);
		
		documento.add(imageLogo);
		documento.add(imageBarc);
				
			
    	} catch (Exception e) {
    		
    	}
			
    }
    
    private static Image getBarcode(Document document,  PdfWriter pdfWriter, String servicio,String  codigoTransaccion){
    
    	PdfContentByte cimg = pdfWriter.getDirectContent();
    	Barcode128 code128 = new Barcode128();
    	code128.setCode(servicio + addZeroLeft(codigoTransaccion));
    	code128.setCodeType(Barcode128.CODE128);
    	code128.setTextAlignment(Element.ALIGN_CENTER);
    	Image image = code128.createImageWithBarcode(cimg, null, null);
    	float scaler = (5);
    	image.scalePercent(scaler);
    	image.setAlignment(Element.ALIGN_CENTER);
    	image.scaleAbsoluteWidth(400f);
    	System.out.print(" Width: "+ image.getWidth());
    	System.out.println(" Heights: "+ image.getHeight());
    	return image;
    	
    }
    
    private static String addZeroLeft(String num) {
    	NumberFormat formatter = new DecimalFormat("0000000");
    	return formatter.format((num != null) ? Integer.parseInt(num) : 0000000);
    }
    
}