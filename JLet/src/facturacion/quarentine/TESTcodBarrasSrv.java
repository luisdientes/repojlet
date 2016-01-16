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


public class TESTcodBarrasSrv extends Service {
	
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
	String emisclie = "";
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
			input.addVariable("emisclie");
			input.addVariable("receclie");
			input.addVariable("tpclient");
			input.addVariable("aniofact");
			input.addVariable("tipofact");
			input.addVariable("mcagrupa");
			input.addVariable("fhfactur");
			input.addVariable("formpago");
			input.addVariable("condpago");
			input.addVariable("tipologo");
						
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
    		emisclie = input.getStringValue("emisclie");
        	receclie = input.getStringValue("receclie");
        	tpclient = input.getStringValue("tpclient");
        	aniofact = input.getStringValue("aniofact");	
        	fhfactur = input.getStringValue("fhfactur");
        	tipofact = Integer.parseInt(input.getStringValue("tipofact"));
        	mcagrupa = Integer.parseInt(input.getStringValue("mcagrupa"));
        	formpago = input.getStringValue("formpago");
        	condpago = input.getStringValue("condpago");
        	tipologo = input.getStringValue("tipologo");
			FRAparen.setConexion(this.getConnection());
		} catch (Exception e1) {
			System.err.println(this.getClass().getName() +"- ERROR - "+ e1.getMessage());
			e1.printStackTrace();
		}
		
	}
	
    public void process(ObjectIO input, ObjectIO output){
    	
    	try {
	    	//recuperaInput(input);
	    	
	    	Rectangle pageSize = new Rectangle(PageSize.A4);
	    	pageSize.setBackgroundColor(new BaseColor(255, 255, 255));
	    	pageSize.rotate();
	        documento = new Document(pageSize);
	        
	        
	        System.out.println("WIDTH "+documento.getPageSize().getWidth());
	        System.out.println("HEIGHT "+documento.getPageSize().getHeight());
	        
	        namefile = cabecNFC +"_"+ numerNFC +".pdf";
	    	filecrea = PropiedadesJLet.getInstance().getProperty("path.gen.invoice")+ namefile;
	    	writer = PdfWriter.getInstance(documento, new FileOutputStream(filecrea));
	        documento.open();
	        pxfrombt = 665;
			
	        pintaCodigoBarras();
	        
	        
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
			imageUrl = PropiedadesJLet.getInstance().getProperty("path.img.logos") + "logoizumba.png";

			imageLogo = Image.getInstance(imageUrl);
			
			ArrayList<String> listcode = new ArrayList<String>();
			

			
			
			listcode.add("843654724122400001");
			listcode.add("71375778321700001");
			listcode.add("71375778491700001");
			listcode.add("843654724158300001");
			listcode.add("843654724109500001");
			listcode.add("71375778261600001");
			listcode.add("71375778141100001");
			listcode.add("843654724010400001");
			listcode.add("843654724145300001");
			listcode.add("888500525048100001");
			listcode.add("888500525075700001");
			listcode.add("888500525024500001");
			listcode.add("888500525014600001");
			listcode.add("888500525021400001");
			listcode.add("888500525084900001");
			listcode.add("888500525064100001");
			listcode.add("888500525079500001");
			listcode.add("888500525011500001");
			listcode.add("843654724138500001");

//				ETA0U10EBE
			/**//*
			listcode.add("PI00115600000001");
			listcode.add("PI00115600000002");
			listcode.add("PI00115500000001");
			listcode.add("PI00115500000002");
			listcode.add("PI00115700000001");
			listcode.add("PI00115700000002");
			listcode.add("PI00115700000003");
			listcode.add("PI00115900000001");
			
			listcode.add("PI00115900000002");
			listcode.add("PI00116200000001");
			listcode.add("PI00116200000002");
			listcode.add("PI00116300000001");
			listcode.add("PI00116300000002");
			listcode.add("PI00116400000001");
			listcode.add("PI00116400000002");
			
			listcode.add("PI00116500000001");
			listcode.add("PI00116500000002");/*
			listcode.add("PI00115000000017");/*
			listcode.add("PI00115000000018");
			listcode.add("PI00115000000019");
			listcode.add("PI00115000000020");
			
			listcode.add("PI00115000000021");
			listcode.add("PI00115000000022");
			listcode.add("PI00115000000023");
			listcode.add("PI00115000000024");
			listcode.add("PI00115000000025");
			
			listcode.add("PI00115000000026");
			listcode.add("PI00115000000027");
			listcode.add("PI00115000000028");
			listcode.add("PI00115000000029");
			listcode.add("PI00115000000030");


			//  ETA3U30EBE
			listcode.add("PI00115200000001");
			listcode.add("PI00115200000002");
			listcode.add("PI00115200000003");
			listcode.add("PI00115200000004");
			listcode.add("PI00115200000005");
			listcode.add("PI00115200000006");
			
			listcode.add("PI00115000000007");
			listcode.add("PI00115000000008");
			listcode.add("PI00115000000009");
			listcode.add("PI00115000000010");
			listcode.add("PI00115000000011");
			listcode.add("PI00115000000012");
			listcode.add("PI00115000000013");
			
			listcode.add("PI00115000000014");
			listcode.add("PI00115000000015");
			listcode.add("PI00115000000016");
			listcode.add("PI00115000000017");
			listcode.add("PI00115000000018");
			listcode.add("PI00115000000019");
			listcode.add("PI00115000000020");

			listcode.add("PI00115200000021");
			listcode.add("PI00115200000022");
			listcode.add("PI00115200000023");
			listcode.add("PI00115200000024");
			listcode.add("PI00115200000025");
			listcode.add("PI00115200000026");
		


			//  ETA3U30EBE
			listcode.add("PI00115200000027");
			listcode.add("PI00115200000028");
			listcode.add("PI00115200000029");
			listcode.add("PI00115200000030");
			listcode.add("PI00115200000031");
			listcode.add("PI00115200000032");
			listcode.add("PI00115200000033");
			listcode.add("PI00115200000034");
			listcode.add("PI00115200000035");
			*/
			
			int yinic =809;
			int xinic = 19;
			int x =0;
			
			int posicion = 0; //posicion donde comenzara a imprimir
	
			for (int i = 0; i < listcode.size(); i++){
				
				
				String codeunic = listcode.get(i);
				
				System.out.println(codeunic);
				
			imageBarc = getBarcode(documento,writer,codeunic,codeunic.substring(8,codeunic.length())); /* para piezas/
			
			//	imageBarc = getBarcode(documento,writer,codeunic,codeunic.substring(6,codeunic.length())); /* para imeis/
				
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
				imageLogo.scaleAbsoluteHeight(28);
				imageBarc.scaleAbsoluteHeight(28);
				imageLogo.scaleAbsoluteWidth(84);
				imageBarc.scaleAbsoluteWidth(84);
				imageLogo.setAbsolutePosition(xinic, yinic);
				imageBarc.setAbsolutePosition(xinic, yinic-26);
				
				if(posicion <=i){
				
					documento.add(imageLogo);
					documento.add(imageBarc);
				}
				
				if( i==0){
					x=1;
				}else{
					x=i+1;
				}
		   
				if( x % 5== 0){
		    		xinic =19;
		    		yinic -=64;
			    	System.out.println("entra i = "+x);
			    }else{
			    	xinic +=117;
			    	}
				}	
				
    	} catch (Exception e) {
    		
    	}
			
    }
    
    private static Image getBarcode(Document document,  PdfWriter pdfWriter, String servicio,String  codigoTransaccion){
    
    	PdfContentByte cimg = pdfWriter.getDirectContent();
    	Barcode128 code128 = new Barcode128();
    	code128.setCode(servicio );
    	code128.setCodeType(Barcode128.CODE128);
    	code128.setTextAlignment(Element.ALIGN_CENTER);
    	Image image = code128.createImageWithBarcode(cimg, null, null);
    	float scaler = (5);
    	image.scalePercent(scaler);
    	image.setAlignment(Element.ALIGN_CENTER);
    	image.scaleAbsoluteWidth(200f);
    	System.out.print(" Width: "+ image.getWidth());
    	System.out.println(" Heights: "+ image.getHeight());
    	return image;
    	
    }
    
    private static String addZeroLeft(String num) {
    	NumberFormat formatter = new DecimalFormat("0000000");
    	return formatter.format((num != null) ? Integer.parseInt(num) : 0000000);
    }
    
}