package stock;

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

import facturacion.bd.ListLineasCodBarrasBD;
import facturacion.bd.ListLineasCodBarrasBDIn;
import facturacion.parents.FacturaPDF;


public class GenPegCodBarrasSrv extends Service {
	
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
	String cabecNFC = "PEGA";
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
	String idemisor = "";
	String listimei = "";
	String tpclient = "";
	String aniofact = "";	
	String fhfactur = "";
	int tipofact = 0;
	int mcagrupa = 0;
	int imeisxx = 0;
	String formpago = "";
	String condpago = "";
	String tipologo = "";
	String separaim = "";
	
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
			input.addVariable("listimei");
			input.addVariable("idemisor");
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
			output.addVariable("idemisor");
			output.addVariable("txmensaj");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
	public void recuperaInput(ObjectIO input){
		
		try {
    		idemisor = input.getStringValue("idemisor");
    		listimei = input.getStringValue("listimei");
    		
    		separaim = listimei.replaceAll(";", "','");
	    	imeisxx = separaim.length()-3;
        	/*tpclient = input.getStringValue("tpclient");
        	aniofact = input.getStringValue("aniofact");	
        	fhfactur = input.getStringValue("fhfactur");
        	tipofact = Integer.parseInt(input.getStringValue("tipofact"));
        	mcagrupa = Integer.parseInt(input.getStringValue("mcagrupa"));
        	formpago = input.getStringValue("formpago");
        	condpago = input.getStringValue("condpago");
        	tipologo = input.getStringValue("tipologo");*/
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
	        documento = new Document(pageSize);
	        
	        
	        System.out.println("WIDTH "+documento.getPageSize().getWidth());
	        System.out.println("HEIGHT "+documento.getPageSize().getHeight());
	        
	        namefile = cabecNFC +"_"+ numerNFC +".pdf";
	    	filecrea = PropiedadesJLet.getInstance().getProperty("path.gen.invoice")+"emisor_"+ idemisor+ "/"+ namefile;
	    	writer = PdfWriter.getInstance(documento, new FileOutputStream(filecrea));
	        documento.open();
	        pxfrombt = 665;
			
	        pintaCodigoBarras();
	        
	        
	    	try {
				output.setValue("filecrea", namefile);
				output.setValue("idemisor", idemisor);
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
    		
	    	PdfPTable table = new PdfPTable(new float[] {1 ,1, 1});
			//table.setTotalWidth(900);
			table.getDefaultCell().setBorder(0);
			imageUrl = PropiedadesJLet.getInstance().getProperty("path.img.logos") + "logo-mps-pegatina.png";

			imageLogo = Image.getInstance(imageUrl);
			
			ArrayList<String> listcode = new ArrayList<String>();
			ArrayList<String> nompieza = new ArrayList<String>();

//				ETA0U10EBE
			/**//*/
			 * 
			 * 
			 */
			
			ListLineasCodBarrasBDIn listBDin = new ListLineasCodBarrasBDIn();
			listBDin.setValue("imeisxxx",separaim.substring(0, imeisxx));
			listBDin.setValue("idemisor", idemisor);
			ListLineasCodBarrasBD listCodigoBD = new ListLineasCodBarrasBD(listBDin);
			listCodigoBD.setConnection(con);
			Grid gdCodBarras = listCodigoBD.execSelect();
			
			String codebarx = "";
			String nomprodu = "";
			
			for(int i=0; i<gdCodBarras.rowCount();i++){
				codebarx = gdCodBarras.getStringCell(i, "idunicox");
				nomprodu = gdCodBarras.getStringCell(i, "txmarcax")+" "+gdCodBarras.getStringCell(i, "txmodelo");
				listcode.add(codebarx);
				nompieza.add(nomprodu);
			}
		
			/*listcode.add("353942042187050");
			listcode.add("353942040984201");
			listcode.add("353942042203121");
			listcode.add("353942040984078");/*
			listcode.add("353942042194866");
			listcode.add("352961049345926");
			listcode.add("353942042219119");
			listcode.add("353942042135638");
			listcode.add("353942041010857");
			listcode.add("353942040989093");
			listcode.add("353942041174281");
			listcode.add("353942042177747");
			listcode.add("353942040976561");
			listcode.add("353942042219861");
			listcode.add("353942040976553");
			listcode.add("353942040984896");
			listcode.add("353942041010865");
			listcode.add("353942041096369");
			listcode.add("353942042203576");/*
			listcode.add("PI00122700000053");
			listcode.add("PI00122700000054");
			listcode.add("PI00122700000055");
			listcode.add("PI00122700000056");
			listcode.add("PI00122700000057");
			listcode.add("PI00122700000058");
			listcode.add("PI00122700000059");
			listcode.add("PI00122700000060");
			listcode.add("PI00122700000061");
			listcode.add("PI00122700000062");
			listcode.add("PI00122700000063");
			listcode.add("PI00122700000064");
			listcode.add("PI00122700000065");
			listcode.add("PI00122700000066");
			/*
			listcode.add("PI00115200000135");
			listcode.add("PI00115200000136");//
			listcode.add("PI00115000000017");
			listcode.add("PI00115000000018");
			listcode.add("PI00115000000019");
			listcode.add("PI00115000000020");
			
			listcode.add("PI00115000000021");
			listcode.add("PI00115000000022");
			listcode.add("PI00115000000023");
			listcode.add("PI00115000000024");
			listcode.add("PI00115000000025");
			listcode.add("PI00116000000001");
			
			listcode.add("PI00116000000002");
			listcode.add("PI00116000000003");
			listcode.add("PI00116000000004");
			listcode.add("PI00116000000005");
			listcode.add("PI00116000000006");
			listcode.add("PI00122900000021");
			
			listcode.add("PI00122900000022");
			listcode.add("PI00122900000023");
			listcode.add("PI00122900000024");
			listcode.add("PI00122900000025");
			listcode.add("PI00122900000026");
			listcode.add("PI00122900000027");
			
			
			
			/*listcode.add("PI00115200000003");
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
			
			
			
			/*nompieza.add("Samsung GT-P1000");
			nompieza.add("Samsung GT-P1000");
			nompieza.add("Samsung GT-P1000");
			nompieza.add("Samsung GT-P1000");
			nompieza.add("");
			nompieza.add("");
			nompieza.add("");
			nompieza.add("");
			nompieza.add("");
			nompieza.add("");
			nompieza.add("");
			nompieza.add("");
			nompieza.add("");
			nompieza.add("");
			nompieza.add("");
			nompieza.add("");
			nompieza.add("");
			nompieza.add("");
			nompieza.add("");
			nompieza.add("");
			nompieza.add("");
			nompieza.add("");*/
			
			/*
			nompieza.add("Cargador ETA0U10EBE");
			nompieza.add("Cargador ETA0U10EBE");
			nompieza.add("Cargador ETA0U10EBE");
			nompieza.add("Cargador ETA0U10EBE");
			nompieza.add("Cargador ETA0U10EBE");
			nompieza.add("Cargador ETA0U10EBE");
			nompieza.add("Cargador ETA0U10EBE");
			nompieza.add("Cargador ETA3S30EBE");
			nompieza.add("Cargador ETA3S30EBE");
			nompieza.add("Cargador ETA3S30EBE");
			nompieza.add("Cargador ETA3S30EBE");
			
			nompieza.add("Cargador ETA3S30EBE");
			nompieza.add("Cargador ETA3S30EBE");
			nompieza.add("Conector Americano (cabeza)");
			nompieza.add("Conector Americano (cabeza)");
			nompieza.add("Conector Americano (cabeza)");
			nompieza.add("Conector Americano (cabeza)");
			nompieza.add("Conector Americano (cabeza)");
			nompieza.add("Conector Americano (cabeza)");
			nompieza.add("Conector Americano (cabeza)");*/
		
			
			
			
			int yinic =793;
			int xinic = 19;
			int x =0;
			double ycorrige=0.0;
			
			int posicion = 0; //posicion donde comenzara a imprimir
	
			for (int i = 0; i < listcode.size(); i++){
				
				
				String codeunic = listcode.get(i);
				String txpiezax = nompieza.get(i);
				
				System.out.println(codeunic);
				
				
				
			imageBarc = getBarcode(documento,writer,codeunic); /* para piezas/
			
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
				imageBarc.scaleAbsoluteHeight(35);
				imageLogo.scaleAbsoluteWidth(84);
				imageBarc.scaleAbsoluteWidth(160);
				imageLogo.setAbsolutePosition(xinic+42, yinic);
				imageBarc.setAbsolutePosition(xinic, yinic-30);
				FRAparen.absText(writer,txpiezax,xinic+40,yinic-37,8);
				
				if(posicion <=i){
				
					documento.add(imageLogo);
					documento.add(imageBarc);
				}
				
				if( i==0){
					x=1;
				}else{
					x=i+1;
				}
		   
				if( x % 3== 0){
					ycorrige-=0.4;
		    		xinic =16;
		    		yinic -=(74+ycorrige);
			    	System.out.println("entra i = "+x);
			    }else{
			    	xinic +=195;
			    	}
				}	
				
    	} catch (Exception e) {
    		System.out.println("Error "+e.getMessage());
    	}
			
    }
    
    private static Image getBarcode(Document document,  PdfWriter pdfWriter, String servicio){
    
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