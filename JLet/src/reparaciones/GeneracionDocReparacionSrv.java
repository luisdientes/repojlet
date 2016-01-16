package reparaciones;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import reparaciones.bd.UpdReparaBD;
import reparaciones.bd.UpdReparaBDIn;
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

import factura.bd.MaxReciboEntradaBD;
import factura.bd.MaxReciboEntradaBDIn;
import facturacion.parents.FacturaPDF;

public class GeneracionDocReparacionSrv extends Service {
	
	//HEREDA METODOS
	
	
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
	String txnombre = "";
	String txmodelo = "";
	String txmarcax = "";
	String txcolorx = "";
	String txdescri = "";
	String tximeixx = "";
	String fhentrad = "";
	String costcheq = "";
	String costordx = "";
	String tiempent = "";
	String garantia = "";
	String entregad = "";
	String recibido = "";
	String txrecibo = "";
	String pnaconta = "FALTA RECIBIR";
	String telefono = "";
	String txmailxx = "";
	
	
	//PARAMETROS DE ENTRADA
	
	//PARÁMETROS DE SALIDA
	String namefile = "";
	String filecrea = "";
	
	//OTROS PARÁMETROS
	Grid datosEntr = new Grid();
	
	String checkcel = "100.00";
	String checkiph = "300.00";
	
	      
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("idemisor");
			input.addVariable("idrecibo");
						
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
			output.addVariable("idrecibo");
			
			output.addVariable("filecrea");
			output.addVariable("txmensaj");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
	
	public void recuperaRecibo(String idemisor,String reciboxx){
		
		try {
		
		
			UpdReparaBDIn  maxReciboBDIn =  new UpdReparaBDIn();
			maxReciboBDIn.setValue("idemisor", idemisor);
			maxReciboBDIn.setValue("idrecibo", reciboxx);
			UpdReparaBD	maxReciboBD 	= new UpdReparaBD(maxReciboBDIn);
			maxReciboBD.setConnection(con);
			datosEntr = maxReciboBD.execSelect();
			
			idrecibo = datosEntr.getStringCell(0, "idrecibo"); 
			idemisor = datosEntr.getStringCell(0, "idemisor");  
			txnombre = datosEntr.getStringCell(0, "txnombre"); 
			txmodelo = datosEntr.getStringCell(0, "txmodelo");
			txcolorx = datosEntr.getStringCell(0, "txcolorx");
			txmarcax = datosEntr.getStringCell(0, "txmarcax"); 
			txdescri = datosEntr.getStringCell(0, "txdescri"); 
			tximeixx = datosEntr.getStringCell(0, "tximeixx"); 
			fhentrad = datosEntr.getStringCell(0, "fhentrad"); 
			costordx = datosEntr.getStringCell(0, "costordx").replaceAll("RD$","").trim();;
			costcheq = datosEntr.getStringCell(0, "costcheq").replaceAll("RD$","").trim();;
			pnaconta = datosEntr.getStringCell(0, "perconta");
			telefono = datosEntr.getStringCell(0, "telefono"); 
			txmailxx = datosEntr.getStringCell(0, "txmailxx"); 
			tiempent = datosEntr.getStringCell(0, "tiempent"); 
			garantia = datosEntr.getStringCell(0, "garantia"); 
			entregad = datosEntr.getStringCell(0, "entregad"); 
			recibido = datosEntr.getStringCell(0, "recibido"); 


			
		} catch (Exception e) {
			System.err.println(this.getClass().getName() +"- ERROR - alta recibo "+ e.getMessage());
			e.printStackTrace();
		}	
	}
	
	
	public void actualizaRecibo(){
		
		try{
		
			UpdReparaBDIn updRepBDIn = new UpdReparaBDIn();
			updRepBDIn.setValue("idemisor", idemisor);
			updRepBDIn.setValue("idrecibo", idrecibo);
			updRepBDIn.setValue("cdrecibo", txrecibo);
			updRepBDIn.setValue("filereci", namefile);
			
			UpdReparaBD updRepBD = new UpdReparaBD(updRepBDIn);
			updRepBD.setConnection(con);
			updRepBD.execUpdate();
		  }catch(Exception ex){
			  System.out.println("Error alactualizar recibo");
		  }	
	}
	
    public void process(ObjectIO input, ObjectIO output){
    	
    	try {
    			try{
    				idemisor = input.getStringValue("idemisor");
    				idrecibo = input.getStringValue("idrecibo");
    			}catch(Exception ex){
    				System.out.println("Error -recuperando emisor en alta recibo");
    			}
    				
	 
		    	Rectangle pageSize = new Rectangle(PageSize.LETTER);
		    	pageSize.setBackgroundColor(new BaseColor(255, 255, 255));
		    	
		    	pageSize.rotate();
		        documento = new Document(pageSize,20, 20, 190, 190);
		        
		        
		        recuperaRecibo(idemisor,idrecibo);
		        txrecibo = "RPIZ" + addZeroLeft(idrecibo);
		     
		        namefile = "ReciboEntrada_"+ idemisor +"_"+ txrecibo +".pdf";
		    	filecrea = PropiedadesJLet.getInstance().getProperty("path.gen.recibo") +"emisor_"+ idemisor +"/"+ namefile;
		    	System.out.println("***********************Se va a crear este recibo: "+ filecrea);
		    	writer = PdfWriter.getInstance(documento, new FileOutputStream(filecrea));
		        documento.open();
		        pxfrombt = 715;
				
		        actualizaRecibo();
		        pintaCabecera();
		        pintaDatosTecnicos();
		        pintaEspacioBlanco(2);
		        pintaDatosContacto();
		        pintaEspacioBlanco(1);
		        pintaClausulas();
		        insFinalFra(writer,150);
		        pintaPiePagina();
	        
	    	try {
	    		
	    		System.out.println("AltaRecibo Srv ------------  "+namefile);
	    		
	    		
	    		output.setValue("cdrecibo", txrecibo);
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
    
    
    public void pintaCabecera(){
    	//INCLUYE LOGO
    	Image image;
    	int altlinea = 700;
    	
		try {
			
			String txrecibo = "RPIZ" + addZeroLeft(idrecibo);
    		
    		FRAparen.absTextBold(writer, "No. : ",margeniz,altlinea ,12);
    		FRAparen.absText(writer,txrecibo,margeniz+40,altlinea ,12);
			
			imglogoM = "logo_recibos.png";
	        String imageUrl = PropiedadesJLet.getInstance().getProperty("path.img.logos") + imglogoM;
	        
			image = Image.getInstance(imageUrl);
			image.scaleAbsolute(200,60);
		    image.setAbsolutePosition(200, 680);
		    documento.add(image);
			
		    documento.add(getBarcode(documento, writer, "RPIZ", idrecibo));
		    
		    altlinea -= 60;
		    FRAparen.absTextBold(writer, "Recibo de Entrada de Equipos",margeniz+155,altlinea ,16);
		    
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
	    		PdfPTable table = new PdfPTable(new float[] { 1, 1, 1, 1, 1, 1});
	    		table.setTotalWidth(anctabla);
	
	            //----------------------		
	    	    //Linea 1
	            //----------------------
	    	    //Nombre (6)
	    	    chunkNom = new Chunk("Nombre:", fontbold);
	    	    chunkSpa = new Chunk("  ", font);
	    	    chunkVal = new Chunk(txnombre, font);
	    	    
	    	    contcell = new Phrase(chunkNom);
	    	    contcell.add(chunkSpa);
	    	    contcell.add(chunkVal);
	    	    
	    		cell = new PdfPCell(contcell);
	            cell.setColspan(4);
	            table.addCell(cell);
	            
	            //IMEI (1)
	            chunkNom = new Chunk("IMEI / Serie:", fontbold);
	    	    chunkSpa = new Chunk("  ", font);
	    	    chunkVal = new Chunk(tximeixx, font);
	    	    
	    	    contcell = new Phrase(chunkNom);
	    	    contcell.add(chunkSpa);
	    	    contcell.add(chunkVal);
	            
	    		cell = new PdfPCell(new Phrase(contcell));
	            cell.setColspan(2);
	            table.addCell(cell);
	    		
	            //----------------------        
	            //Linea 2
	            //----------------------
	    	    //Marca (2)
	            chunkNom = new Chunk("Marca:", fontbold);
	    	    chunkSpa = new Chunk("  ", font);
	    	    chunkVal = new Chunk(txmarcax, font);
	    	    
	    	    contcell = new Phrase(chunkNom);
	    	    contcell.add(chunkSpa);
	    	    contcell.add(chunkVal);
	            
	    		cell = new PdfPCell(new Phrase(contcell));
	            cell.setColspan(2);
	            table.addCell(cell);
	
	            //Modelo (2)
	            chunkNom = new Chunk("Modelo:", fontbold);
	    	    chunkSpa = new Chunk("  ", font);
	    	    chunkVal = new Chunk(txmodelo, font);
	    	    
	    	    contcell = new Phrase(chunkNom);
	    	    contcell.add(chunkSpa);
	    	    contcell.add(chunkVal);
	            
	    		cell = new PdfPCell(new Phrase(contcell));
	            cell.setColspan(2);
	            table.addCell(cell);
	            
	            //Color (2)
	            chunkNom = new Chunk("Color:", fontbold);
	    	    chunkSpa = new Chunk("  ", font);
	    	    chunkVal = new Chunk(txcolorx, font);
	    	    
	    	    contcell = new Phrase(chunkNom);
	    	    contcell.add(chunkSpa);
	    	    contcell.add(chunkVal);
	            
	    		cell = new PdfPCell(new Phrase(contcell));
	            cell.setColspan(2);
	            table.addCell(cell);
	            
	            //----------------------        
	            //Linea 3
	            //----------------------
	    	    //Descripcion (6)
	            chunkNom = new Chunk("Descripcion:", fontbold);
	    	    chunkSpa = new Chunk("  ", font);
	    	    chunkVal = new Chunk(txdescri, font);
	    	    
	    	    contcell = new Phrase(chunkNom);
	    	    contcell.add(chunkSpa);
	    	    contcell.add(chunkVal);
	    	    
	    		cell = new PdfPCell(contcell);
	            cell.setColspan(6);
	            cell.setRowspan(3);
	            table.addCell(cell);
	            
	            //----------------------        
	            //Linea 4
	            //----------------------
	    	    //Coste Chequeo (3)
	            chunkNom = new Chunk("Coste Chequeo (RD$):", fontbold);
	    	    chunkSpa = new Chunk("  ", font);
	    	    chunkVal = new Chunk(costcheq, font);
	    	    
	    	    contcell = new Phrase(chunkNom);
	    	    contcell.add(chunkSpa);
	    	    contcell.add(chunkVal);
	    	    
	    		cell = new PdfPCell(contcell);
	            cell.setColspan(3);
	            table.addCell(cell);
	            
	    	    //Coste Reparacion (3)
	            chunkNom = new Chunk("Coste Reparacion (RD$):", fontbold);
	    	    chunkSpa = new Chunk("  ", font);
	    	    chunkVal = new Chunk(costordx, font);
	    	    
	    	    contcell = new Phrase(chunkNom);
	    	    contcell.add(chunkSpa);
	    	    contcell.add(chunkVal);
	    	    
	    		cell = new PdfPCell(contcell);
	            cell.setColspan(3);
	            table.addCell(cell);
	            
	            //----------------------        
	            //Linea 5
	            //----------------------
	    	    //Fecha de Entrada (3)
	            chunkNom = new Chunk("Fecha de Entrada:", fontbold);
	    	    chunkSpa = new Chunk("  ", font);
	    	    chunkVal = new Chunk(fhentrad, font);
	    	    
	    	    contcell = new Phrase(chunkNom);
	    	    contcell.add(chunkSpa);
	    	    contcell.add(chunkVal);
	    	    
	    		cell = new PdfPCell(contcell);
	            cell.setColspan(3);
	            table.addCell(cell);
	            
	    	    //Tiempo de Reparcion (3)
	            chunkNom = new Chunk("Tiempo de Entrega:", fontbold);
	    	    chunkSpa = new Chunk("  ", font);
	    	    chunkVal = new Chunk(tiempent, font);
	    	    
	    	    contcell = new Phrase(chunkNom);
	    	    contcell.add(chunkSpa);
	    	    contcell.add(chunkVal);
	    	    
	    		cell = new PdfPCell(contcell);
	            cell.setColspan(3);
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
                	    
    		//----------------------		
    	    //Linea 1
            //----------------------
    		
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
    
    public void pintaDatosContacto() throws DocumentException{
    	
    	Font font		= FontFactory.getFont("Times-Roman", 10);
	    Font fontbold 	= FontFactory.getFont("Times-Roman", 10, Font.BOLD);
		
	    Chunk chunkNom 	= null;
	    Chunk chunkSpa 	= null;
	    Chunk chunkVal 	= null;
	    
	    Phrase contcell = null;
	    
	    PdfPCell cell 	= null;
    	
    	try {
	    	
    		int anctabla  = 600;
    		PdfPTable table = new PdfPTable(new float[] { 1, 1, 1, 1, 1, 1});
    		table.setTotalWidth(anctabla);

            //----------------------		
    	    //Linea 1
            //----------------------
    	    //Persona de Contacto (6)
    	    chunkNom = new Chunk("Persona de Contacto:", fontbold);
    	    chunkSpa = new Chunk("  ", font);
    	    chunkVal = new Chunk(pnaconta, font);
    	    
    	    contcell = new Phrase(chunkNom);
    	    contcell.add(chunkSpa);
    	    contcell.add(chunkVal);
    	    
    		cell = new PdfPCell(contcell);
            cell.setColspan(6);
            table.addCell(cell);
    		
            //----------------------		
    	    //Linea 2
            //----------------------
    	    //Mail (3)
    	    chunkNom = new Chunk("Mail:", fontbold);
    	    chunkSpa = new Chunk("  ", font);
    	    chunkVal = new Chunk(txmailxx, font);
    	    
    	    contcell = new Phrase(chunkNom);
    	    contcell.add(chunkSpa);
    	    contcell.add(chunkVal);
    	    
    		cell = new PdfPCell(contcell);
            cell.setColspan(3);
            table.addCell(cell);
            
    	    //Telefono (3)
    	    chunkNom = new Chunk("Telefono:", fontbold);
    	    chunkSpa = new Chunk("  ", font);
    	    chunkVal = new Chunk(telefono, font);
    	    
    	    contcell = new Phrase(chunkNom);
    	    contcell.add(chunkSpa);
    	    contcell.add(chunkVal);
    	    
    		cell = new PdfPCell(contcell);
            cell.setColspan(3);
            table.addCell(cell);
            
            documento.add(table);
            
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    
    public void cargaClausulas(){
    	/*
    	arrClausulas.add("1. Hay un suplemento no reembolsable mínimo de $ 30,00 dólares a menos que se indique lo contrario para chequeo, diagnóstico, intento de resolver un problema de software, consulta o cualquier otro tipo de transacción de servicio entre un cliente y XXXXXXXXX.");
    	arrClausulas.add("2. XXXXXXXXX no se hace responsable de ninguna pérdida de datos debido a la reparación o cualquier daño consecuente o pérdida de cualquier tipo debido al servicio que se realiza. El usuario es responsable de Backup seguridad de los datos de su / su equipo. ");
    	arrClausulas.add("3. No hay garantía en DC Jack reparación del ordenador portátil, pantallas rotas un digitalizadores, tomas de audio, puertos USB o cualquier equipo físicamente dañado.");
    	arrClausulas.add("4. Todo el equipo podría ser tomado para el almacenamiento de 7 días calendario después de haber recibido, completa o parcialmente reparada. ");
    	arrClausulas.add("5. El tiempo de espera para recuperar cualquier equipo de almacenamiento es de 2 días naturales, un cargo de $ 5,00 dólares por día será aplicado después de 15 días naturales a partir de la fecha en la del registro de entrada en la recepción menos que se especifique por escrito por un persona autorizada de XXXXXXXXX, el cliente debe pagar (costo de reparación, los saldos y / o cuotas de almacenamiento) en total por adelantado antes de que el equipo se recupera de almacenamiento, ningún equipo se recuperará desde el almacenamiento hasta que se reciba el pago completo.");
    	arrClausulas.add("6. Todo el equipo se considerará abandonada después de 30 días calendario a partir de la fecha en el cheque en la recepción esta aplicable a todos. ");
    	arrClausulas.add("7. Cualquier depósito y los pagos se pierden después de que el equipo se considerará abandonada.");
    	arrClausulas.add("8. XXXXXXXXX se deshará cualquier equipo abandonado a su propia discreción");
    	arrClausulas.add("9. XXXXXXXXX se deshará cualquier equipo abandonado a su propia discreción");
    	arrClausulas.add("10. XXXXXXXXX no se hace responsable de ninguna pérdida debido a los desastres naturales, incendio y robo o daños por agua debido a problemas de edificación.");
    	arrClausulas.add("11. Dependiendo de la naturaleza de los equipos en reparación XXXXXXXXX puede usar nuestras instalaciones en XXXXXXXXXdirectionXXXXXXXXX para completar el trabajo");
    	arrClausulas.add("12. La comprobación inicial es de ninguna manera un cheque concluyente del sistema. Sólo después de un control de servicio del sistema puede se identifica la magnitud de los problemas. 9. El Servicio de Registro puede tomar al menos 24 horas y es la responsabilidad del cliente para llamar o venir en persona para obtener los resultados de la comprobación o SERVICIO DE EMPLEO SERVICIO.");
    	arrClausulas.add("13. Todos reapirs llevan a 30 días en componentes completos y garantía de trabajo. Los daños o defectos, cosa que son en nuestra opinión, directamente causado por un uso incorrecto o inadecuado, negligencia o por mal manejo por parte del usuario se exclueded específicamente de cualquier garantía otorgada o implícita.");
    	arrClausulas.add("14. Los sistemas informáticos reunidos por XXXXXXXXX llevan 3 meses en la garantía de la tienda. Por solicitud del cliente XXXXXXXXX. Se pudo realizar la reparación (s) fuera XXXXXXXXX ubicación (s) por una tarifa adicional. Otro producto (s) que garantizamos por 7 días a partir de la fecha de compra.");
    	*/
    	
    	int i = 1;
    	
    	arrClausulas.add(i++ +". Las reparaciones tienen garantía de "+ garantia +" días.");
    	arrClausulas.add(i++ +". No cubren maltrato o mal uso del equipo despues de entregado.");
    	arrClausulas.add(i++ +". La garantía de reparación es anulada si el aparato sufre golpes, se moja, se cae o es llevado a otro taller.");
    	arrClausulas.add(i++ +". Favor de recoger el equipo antes de 30 días, de lo conrario pasará a ser propiedad de la compañía sin derecho a reclamación.");
    	arrClausulas.add(i++ +". No aceptamos reclamación después de 48 horas de recibirlo conforme.");
    	arrClausulas.add(i++ +". Chequeo de Celulares RD$ "+ checkcel);
    	arrClausulas.add(i++ +". Chequeo de iPhones RD$ "+ checkiph);
    	
    	/*
    	arrClausulas.add("15. 20% o $ 30,00 dólares lo que se aplica la mayor tasa de reposición para abrir elementos de cuadro. 3. No se aceptan devoluciones en efectivo. Sólo intercambiar para el mismo artículo dentro de 7 días a partir de la fecha de compra, el embalaje original y la factura requerida.");
    	arrClausulas.add("16. Los siguientes artículos no pueden ser devueltos o intercambiados esta las ventas son finales y como es.");
    	arrClausulas.add("  - Abrir y OEM de software ");
    	arrClausulas.add("  - Artículos especiales ordenadas ");
    	arrClausulas.add("  - Abierto cartuchos LNK / tóner e impresoras ");
    	arrClausulas.add("  - Depósito (s) para permitir que el trabajo de pedido (s)  ");
    	arrClausulas.add("  - No retornable debido a la política de fabricación. Todos los productos de Apple, incluyendo el iPod si la unidad es defectuosa, que deben ser atendidos por la garantía del fabricante. ");
		*/
    }
    
    public void pintaClausulas(){
    	
    	cargaClausulas();
    	
    	Font font		= FontFactory.getFont("Times-Roman", 8);
		
	    Chunk chunkNom 	= null;
	    
	    Phrase contcell = null;
	    
	    PdfPCell cell 	= null;
    	
    	try {
	    	
    		int anctabla  = 600;
    		PdfPTable table = new PdfPTable(new float[] { 1, 1});
    		table.setTotalWidth(anctabla);

    		
    		for (int i = 0; i < arrClausulas.size(); i++){

    			chunkNom = new Chunk(arrClausulas.get(i), font);
	    	    
	    	    contcell = new Phrase(chunkNom);
	    	    
	    		cell = new PdfPCell(contcell);
	            cell.setColspan(2);
	            cell.setBorder(0);
	            table.addCell(cell);
	            
    		}
	            
    	    documento.add(table);
            
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    
    public void pintaPiePagina(){
    	try {
    		
    		
    		PdfPTable tabla = new PdfPTable(1);
    		tabla.setTotalWidth(650);
    		
        	
        	Font fuente = new Font(FontFamily.HELVETICA, 7, Font.NORMAL, new BaseColor(64, 64, 64));
    		BaseColor bkcolorCab = new BaseColor(255, 255, 255);
    		
    		tabla.addCell(FRAparen.getCelda("CENTRO COMERCIAL ACROPOLIS ",fuente,bkcolorCab,"center")).setBorder(0);
    		tabla.addCell(FRAparen.getCelda("Tercer Nivel Local P2-J1",fuente,bkcolorCab,"center")).setBorder(0);
    		tabla.addCell(FRAparen.getCelda("Av. Winston Churchill esq. C\\ Rafael Augusto Sánchez Ens. Piantini, Santo Domingo D.N. Rep.Dom.",fuente,bkcolorCab,"center")).setBorder(0);
    	    if ((tpclient != null) && (tpclient.equals("1"))){
    	    	tabla.addCell(FRAparen.getCelda("http://www.mallproshop.com Tels: (809) 547-4210 / (809) 707-3324",fuente,bkcolorCab,"center")).setBorder(0);
    	    } else {
    	    	tabla.addCell(FRAparen.getCelda("http://www.izumbashop.com Tels: (809) 547-4210 / (809) 707-3324",fuente,bkcolorCab,"center")).setBorder(0);
    	    }
    		//tabla.setHorizontalAlignment(1150);
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
	
