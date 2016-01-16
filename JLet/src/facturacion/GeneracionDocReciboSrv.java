package facturacion;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;

import utils.PropiedadesJLet;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import factura.bd.ListFacturasBD;
import factura.bd.ListFacturasBDIn;
import factura.bd.ListRecibosBD;
import factura.bd.ListRecibosBDIn;
import facturacion.parents.FacturaPDF;

public class GeneracionDocReciboSrv extends Service {
	
	Document documento = null;
	PdfWriter writer;
	
	FacturaPDF FRAparen = new FacturaPDF();
	
	//DATOS DEL DOCUMENTO
	int pagetotal = 1;
	int pxfrombt  = 800;
	int margeniz  = 40;
	int inmargde  = 415;	
	
	ArrayList<String> arrClausulas = new ArrayList<String>();

	
	//PARÁMETROS A RELLENAR EN EL RECIBO
	
	String imglogoM = "";
	String imglogoI = "";
	String idrecibo = "";
	String idemisor = "";
	String tpclient = "";
	String numrecib = "";
	String cdfactur = "";
	String formpago = "";
	String txbancox = "";
	String concepto = "";
	String tximeixx = "";
	String fecharec = "";
	String costcheq = "";
	String costordx = "";
	String tiempent = "";
	String garantia = "";
	String entregad = "";
	String recibido = "";
	String txrecibo = "";
	String pnaconta = "FALTA RECIBIR";
	String telefono = "";
	String rzsocial = "";
	String idfactur = "";
	String canrecib = "";
	String reciboid = "";
	String numfactu = "";
	String tpfacrec = "";
	
	
	//PARAMETROS DE ENTRADA
	
	//PARÁMETROS DE SALIDA
	String filecrea = "";
	
	//PARÁMETROS DE CLASE
	String tipofact = "";
	
	//OTROS PARÁMETROS
	Grid gridReci = new Grid();
	
	//FORMATOS
	DecimalFormatSymbols  simboloDec = new DecimalFormatSymbols ();
	DecimalFormat formatDivisa = new DecimalFormat();
	
	
	String checkcel = "100.00";
	String checkiph = "300.00";
	
	      
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("idemisor");
			input.addVariable("idfactur");
			input.addVariable("idrecibo");
			input.addVariable("numfactu");
			input.addVariable("tpfacrec");
						
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
			
			output.addVariable("cdrecibo");
			output.addVariable("filecrea");
			output.addVariable("txmensaj");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    public void process(ObjectIO input, ObjectIO output){
    	
    	String pathfile = "";
    	String namefile = "";
    	
    	try {
    			try {
    				idemisor = input.getStringValue("idemisor");
    				idfactur = input.getStringValue("idfactur");
    				idrecibo = input.getStringValue("idrecibo");
    				
    			} catch(Exception ex){
    				System.out.println("Error -recuperando emisor en alta recibo");
    			}
    			
    			recuperaRecibo(idemisor,idfactur,idrecibo);
    			
    			try {
			        ListFacturasBDIn factDetaBDIn = new ListFacturasBDIn();
			        factDetaBDIn.setValue("idemisor", idemisor);
			        factDetaBDIn.setValue("idfactur", idfactur);
			        ListFacturasBD factDetaBD = new ListFacturasBD(factDetaBDIn);
			        factDetaBD.setConnection(con);
			        Grid gdFactur = factDetaBD.execSelect();
			        
			        if (gdFactur.rowCount() > 0) {
			        	tipofact = gdFactur.getStringCell(0, "tipofact");
			        } else {
			        	System.err.println(" Factura no encontrada: idfactur "+ idfactur +" - idemisor "+ idemisor);
			        }
			        
		        } catch (Exception e) {
		        	
		        }
    			
    			Rectangle typePage = dameTipoPagina(idemisor);
    			
		    	Rectangle pageSize = new Rectangle(typePage);
		    	pageSize.setBackgroundColor(new BaseColor(255, 255, 255));
		    	
		    	//pageSize.rotate();   NO HACE NADA... debería ser pageSize = pageSize.rotate()
		    	
		        documento = new Document(pageSize,20, 20, 190, 190);
		        txrecibo = "RECIBO" + addZeroLeft(reciboid);

		        namefile = "ReciboPago_"+ idemisor +"_FRA"+ idfactur +"_"+ txrecibo +".pdf";
		        pathfile = PropiedadesJLet.getInstance().getProperty("path.gen.invoice");
		    	filecrea = pathfile + "emisor_"+ idemisor +"/"+ namefile;
		    	
		    	System.out.println(" Se va a crear este recibo: "+ filecrea);
		    	
		    	writer = PdfWriter.getInstance(documento, new FileOutputStream(filecrea));
		        documento.open();
		        pxfrombt = 715;
				
		        asignaFormato(idemisor);
		        
		        pintaCabecera();
		        pintaDatosTecnicos();
		        pintaEspacioBlanco(3);
		        insFinalFra(writer,150);
		        pintaPiePagina(idemisor,tipofact);
		        
		        actualizaRecibo(idemisor,idfactur,idrecibo,namefile);
	        
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
    
public void recuperaRecibo(String idemisor,String idfactur,String idrecibo){
		
		try {
			
			
			ListRecibosBDIn reciboBDIn = new ListRecibosBDIn();
        	reciboBDIn.setValue("idemisor",idemisor);
        	reciboBDIn.setValue("idfactur",idfactur);
        	reciboBDIn.setValue("numrecib",idrecibo);
        	ListRecibosBD reciboBD = new ListRecibosBD(reciboBDIn);
        	reciboBD.setConnection(con);
        	gridReci = reciboBD.execSelect();
		
        	if (gridReci.rowCount() > 0) {
				reciboid = gridReci.getStringCell(0, "idrecibo"); 
				idemisor = gridReci.getStringCell(0, "idemisor");  
				numrecib = gridReci.getStringCell(0, "numrecib"); 
				numfactu = gridReci.getStringCell(0, "cdfactur");
				txbancox = gridReci.getStringCell(0, "txbancox");
				formpago = gridReci.getStringCell(0, "formpago"); 
				concepto = gridReci.getStringCell(0, "concepto"); 
				tximeixx = gridReci.getStringCell(0, "tximeixx"); 
				fecharec = gridReci.getStringCell(0, "fecharec"); 
				rzsocial = gridReci.getStringCell(0, "rzsocial");
				canrecib = gridReci.getStringCell(0, "cantidad");
        	} else {
        		System.err.println(" ERROR - Recuperando el recibo: "+ reciboid +" del emisor "+ idemisor);
        	}

		} catch (Exception e) {
			System.err.println(this.getClass().getName() +"- ERROR - alta recibo "+ e.getMessage());
			e.printStackTrace();
		}	
	}
	
	public void actualizaRecibo(String idemisor,String idfactur,String idrecibo, String namefile){
		
		try{
		
			ListRecibosBDIn updRepBDIn = new ListRecibosBDIn();
			updRepBDIn.setValue("idemisor", idemisor);
			updRepBDIn.setValue("idfactur", idfactur);
			updRepBDIn.setValue("numrecib", numrecib);
			updRepBDIn.setValue("namefile", namefile);
			ListRecibosBD updRepBD = new ListRecibosBD(updRepBDIn);
			updRepBD.setConnection(con);
			updRepBD.execUpdate();
			
		  }catch(Exception ex){
			  System.out.println("Error alactualizar recibo");
		  }	
	}
    
    public String dameLogoRecibo(String idemisor, String tipofact){
  		
  		String imglogox = "recibo_default.png";
  		
  		if (idemisor.equals("1")) {
  			imglogox = "logo_recibos.png";
  		}	
  		
  		return imglogox;
  		
  	}
  	
	public boolean mostrarLogoRecibo(String idemisor, String tpfacrec){
  		
  		boolean mostrarlg = true;
  		
  		if (idemisor.equals("1") && tpfacrec.equals("5")) {
  			mostrarlg = false;
  		}	
  		
  		return mostrarlg;
  		
  	}
	
	public Rectangle dameTipoPagina(String idemisor){
  		
  		Rectangle tipopagi = PageSize.A4;
  		
  		
  		if (idemisor.equals("1")) {
  			tipopagi = PageSize.LETTER;
  		}	
  		
  		return tipopagi;
  		
  	}
	
	public void asignaFormato(String idemisor){
  		
  		if (idemisor.equals("1")) {
  			simboloDec.setDecimalSeparator('.');
  	  		simboloDec.setGroupingSeparator(',');
  			formatDivisa = new DecimalFormat ("###,##0.00",simboloDec);
  		} else {
  			simboloDec.setDecimalSeparator(',');
  	  		simboloDec.setGroupingSeparator('.');
  			formatDivisa = new DecimalFormat ("###,##0.00",simboloDec);
  		}
  		
  	}

    
   
// ------------------------------------------ PINTAR DATOS -----------------------------
    
    
    public void pintaCabecera(){
    	//INCLUYE LOGO
    	Image image;
    	int altlinea = 700;
    	
		try {
			
			String txrecibo = "RECIB" + addZeroLeft(reciboid);
    		
    		FRAparen.absTextBold(writer, "No. : ",margeniz,altlinea ,12);
    		FRAparen.absText(writer,txrecibo,margeniz+40,altlinea ,12);
			
    		if (mostrarLogoRecibo(idemisor,tipofact)){
    			
				imglogoM = dameLogoRecibo(idemisor,tipofact);
		        String imageUrl = PropiedadesJLet.getInstance().getProperty("path.img.logos") + imglogoM;
		        
				image = Image.getInstance(imageUrl);
				image.scaleAbsolute(200,60);
			    image.setAbsolutePosition(200, 680);
			    documento.add(image);
			    
    		}
			
		   // documento.add(getBarcode(documento, writer, "RPIZ", idrecibo));
		    
		    altlinea -= 60;
		    FRAparen.absTextBold(writer, "RECIBO DE INGRESO",margeniz+180,altlinea ,16);
		    
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
			System.err.println(this.getClass().getName() +" ERROR pintaCabecera() - "+ e.getMessage());
		}
    	
    }
    
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
	    		PdfPTable table = new PdfPTable(new float[] { 1, 1, 1});
	    		table.setTotalWidth(anctabla);
	
	            //----------------------		
	    	    //Linea 1
	            //----------------------
	    	    //Nombre (6)
	    	    chunkNom = new Chunk("RECIBIDO DE:", fontbold);
	    	    chunkSpa = new Chunk("  ", font);
	    	    chunkVal = new Chunk(rzsocial, font);
	    	    
	    	    contcell = new Phrase(chunkNom);
	    	    contcell.add(chunkSpa);
	    	    contcell.add(chunkVal);
	    	    
	    		cell = new PdfPCell(contcell);
	            cell.setColspan(2);
	            table.addCell(cell);
	            
	            //Fecha (1)
	            chunkNom = new Chunk("FECHA :", fontbold);
	    	    chunkSpa = new Chunk("  ", font);
	    	    chunkVal = new Chunk(fecharec, font);
	    	    
	    	    contcell = new Phrase(chunkNom);
	    	    contcell.add(chunkSpa);
	    	    contcell.add(chunkVal);
	            
	    		cell = new PdfPCell(new Phrase(contcell));
	           // cell.setColspan(2);
	            table.addCell(cell);
	    		
	            //----------------------        
	            //Linea 2
	            //----------------------
	    	    //Marca (2)
	            chunkNom = new Chunk("LA SUMA DE :", fontbold);
	    	    chunkSpa = new Chunk("  ", font);
	    	    chunkVal = new Chunk(formatDivisa.format(Double.parseDouble(canrecib)) +"  RD$", font);
	    	    
	    	    contcell = new Phrase(chunkNom);
	    	    contcell.add(chunkSpa);
	    	    contcell.add(chunkVal);
	            
	    		cell = new PdfPCell(new Phrase(contcell));
	            cell.setColspan(3);
	            table.addCell(cell);
	
	            //Modelo (2)
	           /* chunkNom = new Chunk("Modelo:", fontbold);
	    	    chunkSpa = new Chunk("  ", font);
	    	    chunkVal = new Chunk(cdfactur, font);
	    	    
	    	    contcell = new Phrase(chunkNom);
	    	    contcell.add(chunkSpa);
	    	    contcell.add(chunkVal);
	            
	    		cell = new PdfPCell(new Phrase(contcell));
	            cell.setColspan(2);
	            table.addCell(cell);
	            
	            //Color (2)
	            chunkNom = new Chunk("Color:", fontbold);
	    	    chunkSpa = new Chunk("  ", font);
	    	    chunkVal = new Chunk(txbancox, font);
	    	    
	    	    contcell = new Phrase(chunkNom);
	    	    contcell.add(chunkSpa);
	    	    contcell.add(chunkVal);
	            
	    		cell = new PdfPCell(new Phrase(contcell));
	            cell.setColspan(2);
	            table.addCell(cell);*/
	            
	            //----------------------        
	            //Linea 3
	            //----------------------
	    	    //Descripcion (6)
	            
	            chunkNom = new Chunk("POR CONCEPTO DE :", fontbold);
	    	    chunkSpa = new Chunk("  ", font);
	    	    chunkVal = new Chunk(concepto, font);
	    	    
	    	    contcell = new Phrase(chunkNom);
	    	    contcell.add(chunkSpa);
	    	    contcell.add(chunkVal);
	    	    
	    		cell = new PdfPCell(contcell);
	            cell.setColspan(3);
	            cell.setRowspan(3);
	            table.addCell(cell);
	            //forma pago
	            
	            chunkNom = new Chunk("No FACTURA :", fontbold);
	    	    chunkSpa = new Chunk("  ", font);
	    	    chunkVal = new Chunk(numfactu, font);
	    	    
	    	    contcell = new Phrase(chunkNom);
	    	    contcell.add(chunkSpa);
	    	    contcell.add(chunkVal);
	    	    
	    		cell = new PdfPCell(contcell);
	            cell.setColspan(3);
	            cell.setRowspan(3);
	            table.addCell(cell);
	            
	            chunkNom = new Chunk("FORMA DE PAGO  :", fontbold);
	    	    chunkSpa = new Chunk("  ", font);
	    	    chunkVal = new Chunk(formpago, font);
	    	    
	    	    contcell = new Phrase(chunkNom);
	    	    contcell.add(chunkSpa);
	    	    contcell.add(chunkVal);
	    	    
	    		cell = new PdfPCell(contcell);
	            cell.setColspan(3);
	            cell.setRowspan(3);
	            table.addCell(cell);
	            
	            chunkNom = new Chunk("BANCO :", fontbold);
	    	    chunkSpa = new Chunk("  ", font);
	    	    chunkVal = new Chunk(txbancox, font);
	    	    
	    	    contcell = new Phrase(chunkNom);
	    	    contcell.add(chunkSpa);
	    	    contcell.add(chunkVal);
	    	    
	    		cell = new PdfPCell(contcell);
	            cell.setColspan(3);
	            cell.setRowspan(3);
	            table.addCell(cell);
	            
	            chunkNom = new Chunk("CHEQUE No :", fontbold);
	    	    chunkSpa = new Chunk("  ", font);
	    	    chunkVal = new Chunk(idrecibo, font);
	    	    
	    	    contcell = new Phrase(chunkNom);
	    	    contcell.add(chunkSpa);
	    	    contcell.add(chunkVal);
	    	    
	    		cell = new PdfPCell(contcell);
	            cell.setColspan(3);
	            cell.setRowspan(3);
	            table.addCell(cell);
	            
    			documento.add(table);
    			
    		} catch (DocumentException e) {
    			e.printStackTrace();
    		}
    		
    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() + "ERROR pintaCuerpo() "+ e.getMessage());
    	}
    	
    }
    
    public void pintaEspacioBlanco(int numLinea) throws DocumentException{
    	
	    PdfPCell cell 	= null;
    	
    	try {
	    	
    		int anctabla  = 600;
    		PdfPTable table = new PdfPTable(new float[] { 1});
    		table.setTotalWidth(anctabla);
                	    
    		for (int i = 0; i < numLinea; i++){
	    		cell = new PdfPCell(new Phrase(" "));
	    		cell.setBorder(0);
	            table.addCell(cell);	            
    		}
           
            documento.add(table);
            
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void pintaPiePagina(String idemisor, String tipofact){
    	try {
    		
    		PdfPTable tabla = new PdfPTable(1);
    		tabla.setTotalWidth(650);
        	
        	Font fuente = new Font(FontFamily.HELVETICA, 7, Font.NORMAL, new BaseColor(64, 64, 64));
    		BaseColor bkcolorCab = new BaseColor(255, 255, 255);
    		
    		if (idemisor.equals("1")) {
    			if (!tipofact.equals("5")) {
	    			tabla.addCell(FRAparen.getCelda("CENTRO COMERCIAL ACROPOLIS ",fuente,bkcolorCab,"center")).setBorder(0);
		    		tabla.addCell(FRAparen.getCelda("Tercer Nivel Local P2-J1",fuente,bkcolorCab,"center")).setBorder(0);
		    		tabla.addCell(FRAparen.getCelda("Av. Winston Churchill esq. C\\ Rafael Augusto Sánchez Ens. Piantini, Santo Domingo D.N. Rep.Dom.",fuente,bkcolorCab,"center")).setBorder(0);
	    	    	tabla.addCell(FRAparen.getCelda("http://www.izumbashop.com Tels: (809) 547-4210 / (809) 707-3324",fuente,bkcolorCab,"center")).setBorder(0);
    			}
    		}

    	    PdfContentByte canvas = writer.getDirectContent();
	    	tabla.writeSelectedRows(0, 5, margeniz-50,60, canvas);

    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() + "ERROR pintaPiePagina() "+ e.getMessage());
    	}
    	
    }
    
    private void insFinalFra (PdfWriter twriter, int lineares){
	  
	  	FRAparen.absLineaFina(twriter,margeniz,lineares-10,174);
    	FRAparen.absText(twriter,"Recibido por "+recibido,62,lineares,8);
    	FRAparen.absLineaFina(twriter,382,lineares-10,174);
    	FRAparen.absText(twriter,"Aceptado Por "+entregad,419,lineares,8); //entregad
		
    }
  
  	
	
    
//------------------------------------------ END PINTAR DATOS -----------------------------    

	public String getFileCreated(){
    	return this.filecrea;    	
    }
    
    private static Image getBarcode(Document document,  PdfWriter pdfWriter, String servicio,String  codigoTransaccion){
    	PdfContentByte cimg = pdfWriter.getDirectContent();
    	Barcode128 code128 = new Barcode128();
    	code128.setCode(servicio + addZeroLeft(codigoTransaccion));
    	code128.setCodeType(Barcode128.CODE128);
    	code128.setTextAlignment(Element.ALIGN_CENTER);
    	Image image = code128.createImageWithBarcode(cimg, null, null);
    	float scaler = ((document.getPageSize().getWidth() - document.leftMargin()  - document.rightMargin() - 0) / image.getWidth()) * 15;
    	image.scalePercent(scaler);
    	image.setAlignment(Element.ALIGN_CENTER);
	    image.setAbsolutePosition(470, 700);
    	return image;
    }
    
    private static String addZeroLeft(String num) {
    	NumberFormat formatter = new DecimalFormat("00000000");
    	return formatter.format((num != null) ? Integer.parseInt(num) : 0000000);
    }
    
}
	
