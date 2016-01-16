package facturacion;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

import utils.PropiedadesJLet;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import comercio.bd.UpdStockBD;
import comercio.bd.UpdStockBDIn;
import common.Divisa;
import factura.TempFacturaSrv;
import factura.bd.ListFormaPagoBD;
import factura.bd.ListFormaPagoBDIn;
import factura.bd.ListLineasBD;
import factura.bd.ListLineasBDIn;
import factura.bd.ListRecibosBD;
import factura.bd.ListRecibosBDIn;
import facturacion.bd.ListLineasFactAgruBD;
import facturacion.bd.ListLineasFactAgruBDIn;
import facturacion.bd.ListLineasTmpBD;
import facturacion.bd.ListLineasTmpBDIn;
import facturacion.bd.ListTipoFacturaBD;
import facturacion.bd.ListTipoFacturaBDIn;
import facturacion.bd.UpdFacturaBD;
import facturacion.bd.UpdFacturaBDIn;
import facturacion.bd.UpdLineasBD;
import facturacion.bd.UpdLineasBDIn;
import facturacion.parents.FacturaPDF;


public class FRAtradensESSrv extends Service {
	
	//HEREDA METODOS
	FacturaPDF FRAparen = new FacturaPDF();
	
	Document documento = null;
	PdfWriter writer;
	
	//DATOS DEL DOCUMENTO
	int pagetotal = 1;
	int filasxpag = 22;
	int filasprim = 22;
	int nfilascon = 30;
	int nfilasdet = 4;
	int pxfrombt  = 800;
	int posicioy  = 800;
	
	int initabla  = 550;
	int anctabla  = 520;
	
	int lineResu  = 250;
	
	int margeniz  = 35;
	int inmargde  = 415;	
	
	//DATOS DE LA FACTURA
	double porTaxes = 8;
	String divisa = " Fr";
	
	//PARÁMETROS A RELLENAR EN LA FACTURA
	String cabecNFC = "FACT";
	String numerNFC = "00000001";
	String txtipnfc = "FACTURE";
	String imglogox = "";
	String idtmpfra = "";
	String mcefecti = ""; 
	double impbasei = 0;
	double impitbis = 0;
	double imptotal = 0;
	
	
	//FORMATOS NUMÉRICOS
	DecimalFormat formatUnit = new DecimalFormat("###,##0");
	DecimalFormat formatDivi = estableceFormato();
	
	
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
	String tipologo = "";
	String formpago = "";
	String catefact = "";
	String condpago = "";
	String idfacreg = "";
	String regenera = "";
	String cdfactur = "";
	String idlineas = "";
	String marcados = "";
	String listimei = "";
	String codvende = "";
	//PARÁMETROS DE SALIDA
	String namefile = "";
	String filecrea = "";
	String factasoc = "";
	String informda = "";
	String mcpagado = "";
	String totalpen = "";
	String totalpag = "";
	String devoluci = "";
	String idfactur = "";
	String facalbar = "";
	String estaalba ="P";
	String idunicox = "";
	String agrupada = "";
	
	
	
	//OTROS PARÁMETROS
	Grid datosEmi = new Grid();
	Grid datosRec = new Grid();
	Grid gdUnicox = new Grid();
	Font fontboldwhite 	= FontFactory.getFont(BaseFont.HELVETICA, 10, Font.BOLD,new BaseColor(155, 155, 155));
	      
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("emisclie");
			input.addVariable("tpclient");
			input.addVariable("receclie");
			input.addVariable("aniofact");
			input.addVariable("tipofact");
			input.addVariable("mcagrupa");
			input.addVariable("fhfactur");
			input.addVariable("formpago");
			input.addVariable("condpago");
			input.addVariable("catefact");
			input.addVariable("tipologo");
			input.addVariable("idfactur");
			input.addVariable("regenera");
			input.addVariable("cdfactur");
			input.addVariable("idlineas");
			input.addVariable("marcados");
			input.addVariable("factasoc");
			input.addVariable("listimei");
			input.addVariable("codvende");
			input.addVariable("porcrete");
			input.addVariable("informda");
			input.addVariable("mcpagado");
			input.addVariable("totalpen");
			input.addVariable("totalpag");
			input.addVariable("devoluci");
			input.addVariable("facalbar");
			
			
			
			
			
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
        	catefact = input.getStringValue("catefact");
        	tipologo = input.getStringValue("tipologo");
        	idfacreg = input.getStringValue("idfactur");
        	regenera = input.getStringValue("regenera");
        	cdfactur = input.getStringValue("cdfactur");
        	idlineas = input.getStringValue("idlineas");
        	marcados = input.getStringValue("marcados");
        	listimei = input.getStringValue("listimei"); 
        	codvende = input.getStringValue("codvende");
        	informda = input.getStringValue("informda");
        	mcpagado = input.getStringValue("mcpagado");
        	totalpen = input.getStringValue("totalpen");
        	totalpag = input.getStringValue("totalpag");
        	idfactur = input.getStringValue("idfactur");
        	devoluci = input.getStringValue("devoluci");
        	facalbar = input.getStringValue("facalbar");
        	
        	
			FRAparen.setConexion(this.getConnection());
		} catch (Exception e1) {
			System.err.println(this.getClass().getName() +"- ERROR - "+ e1.getMessage());
			e1.printStackTrace();
		}
		
	}
	
    public void process(ObjectIO input, ObjectIO output){
    	
    	try {
    		
	    	recuperaInput(input);
	    	
	    	 if(regenera == null || !regenera.equals("S")){
	    		 estableceCodigosFRA();
	    		 
	    		
	    	 }else{
	    		 numerNFC =  cdfactur;
	    	 }
	    
	    	
	    	rellenaDatosRNC(tipofact);
	    	
	    	Rectangle pageSize = new Rectangle(PageSize.A4);
	    	pageSize.setBackgroundColor(new BaseColor(255, 255, 255));
	    	pageSize.rotate();
	        documento = new Document(pageSize,20, 20, 30, 30);
	        
	        
	        namefile ="TRAD_"+aniofact +"_"+ cabecNFC +"_"+ numerNFC +".pdf";
	        String folderin = PropiedadesJLet.getInstance().getProperty("path.gen.invoice") + "emisor_"+ emisclie + "/"; 
	        File infolder = new File(folderin);
	        
	        if (!infolder.isDirectory()){
	        	infolder.mkdirs();
	        }
	       
	        filecrea = folderin + namefile;
	    	writer = PdfWriter.getInstance(documento, new FileOutputStream(filecrea));
	        documento.open();
	        pxfrombt = 715;
			
	        estableceFormato(); // formato cantidades y precios suizos
	        pintaCabecera();
	        
	        pintaFecha();
	        pintaDatosClienteEmisor();
	       
	        pintaOtrosDatos();
	        
	        
	        
	        pintaDetallesFRA();
	        
	        pintaDatosBancarios();
	        
	        pintaTotales();
	       
	       // pintaEnlace(imptotal);

	        pintaPiePagina();
	        
	        if( mcagrupa == 1){
	        	pintaAnexo();
	        	pintaPiePagina();
	        }
	        
	        if(regenera==null || !regenera.equals("S")){
	        
	        	
	        	 gdUnicox = selecPendientes();
		        	
		        	if(tipofact !=0){
		        		cambiaStock(gdUnicox,"VENDIDO");
		        	}else{
		        		cambiaStock(gdUnicox,"DEPOSITO");
		        	}
		        	realizarActualizaciones();
		        	
		        	if(devoluci!= null && devoluci.equals("S")){
			        	Grid gdImeis = FRAparen.selecImeis(idtmpfra,marcados);
			        	cambiaStock(gdImeis,"STOCK");
			        	FRAparen.actualizaLineasFactura(receclie,tpclient,emisclie, marcados);
			        	FRAparen.marcaDevolucion(idfactur);
	        		}
	        }else{
	        	recalCulaFactu(idfacreg);
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
    
    public void estableceCodigosFRA(){
    	
    	int codigoFR = 0;
    	try {
	    	codigoFR = FRAparen.obtenerCodigoFRA(emisclie,aniofact,tipofact);
	    	numerNFC = formatCNFC.format(codigoFR);
    	} catch (Exception e) {
    		
    	}
    	
    }
    
   
// ------------------------------------------ START PINTAR DATOS -----------------------------
    
    
    public void pintaCabecera(){
    	//INCLUYE LOGO
    	Image image;
    	
		try {
			imglogox = FRAparen.obtenerLogoEmisor(emisclie);
	        
	        String imageUrl = PropiedadesJLet.getInstance().getProperty("path.img.logoinvoice") + "logotradens.png";

			image = Image.getInstance(imageUrl);
		    image.setAbsolutePosition((documento.getPageSize().getWidth() / 2) - 130, 750);
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
			e.printStackTrace();
			System.err.println(this.getClass().getName() +" ERROR pintaCabecera() - "+ e.getMessage());
		}
    	
    }
    
    public void pintaFecha(){
    	
    	//INCLUYE EL CODIGO DE FACTURA
    	try {
    		
    		int inicioy = pxfrombt+90;
    		
    		// tipo
    		FRAparen.absTextBoldColor(writer,txtipnfc,margeniz+370,inicioy,15,new BaseColor(155, 155, 155));
    		inicioy -= 15;
    		// numero factura
    		FRAparen.absText(writer,"Facture Nº :",margeniz + 370,inicioy,10);
    		FRAparen.absText(writer,cabecNFC +" "+ numerNFC +" ",margeniz + 445,inicioy,10);
    		inicioy -= 15;
    		//fecha
			FRAparen.absText(writer,"Date : ",margeniz + 370,inicioy,10);
			FRAparen.absText(writer,FRAparen.fechaSuiza(fhfactur),margeniz + 445,inicioy,10);
			inicioy -= 15;
			//termes 
			FRAparen.absText(writer,"Termes : ",margeniz + 370,inicioy,10);
			FRAparen.absText(writer," 0 Jours",margeniz + 445,inicioy,10);
			inicioy -= 15;
			FRAparen.absText(writer,"Échéance : ",margeniz + 370,inicioy,10);
			FRAparen.absText(writer,FRAparen.fechaSuiza(fhfactur),margeniz + 445,inicioy,10);
			
	    	rellenaDatosRNC(tipofact);
	    	posicioy = inicioy;
			
			
    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() +" ERROR pintaFecha() - "+ e.getMessage());
    	}
    	
    }
    
    public void pintaDatosClienteEmisor(){
    	
    	try {
    		int inicioy = pxfrombt+90;
    		datosEmi = FRAparen.obtenerDatosCliente(emisclie,"0"); //emisclie
			
			ArrayList<String> lineaEmi = new ArrayList<String>();
			lineaEmi = FRAparen.obtenerCabeceraCliente(datosEmi);
			
			
			for (int i = 0; i < lineaEmi.size(); i++){
				String contLine = lineaEmi.get(i);
				if ((contLine != null) && (!"".equals(contLine))){
					if( i==0){
						FRAparen.absTextBoldColor(writer,contLine,margeniz,inicioy,8,new BaseColor(22, 138, 190));
						inicioy-=15;
					}else{
						FRAparen.absText(writer,contLine,margeniz,inicioy,8);
						inicioy-=15;	
					}
				}
			}
			
		
			int altRecep = posicioy-75;
			/*LINEAS CLIENTE*/
			
			
			datosRec = FRAparen.obtenerDatosCliente("",receclie); ////emisclie
			 
			
			ArrayList<String> lineaRec = new ArrayList<String>();
			lineaRec =FRAparen.obtenerCabeceraCliente(datosRec);
			//Altura de los datos del receptor
			
			for (int i = 0; i < lineaRec.size(); i++){
				
				String contLine = lineaRec.get(i);
				if ((contLine != null) && (!"".equals(contLine))){
					
					if( i == 0){
						FRAparen.absTextBoldColor(writer,"Facturé á",margeniz,altRecep,10,new BaseColor(155, 155, 155));
						FRAparen.absText(writer,contLine,margeniz+55,altRecep,10);
						altRecep-=15;
						
					}else{
					
						FRAparen.absText(writer,contLine,margeniz+55,altRecep,10);
						altRecep-=15;
					}
				}
			}

			
			altRecep = posicioy-75;
			
			for (int i = 0; i < lineaRec.size(); i++){
				
				String contLine = lineaRec.get(i);
				if ((contLine != null) && (!"".equals(contLine))){
					
					if( i == 0){
						
				
						
						
						FRAparen.absTextBoldColor(writer,"Livré á",margeniz+300,altRecep,10,new BaseColor(155, 155, 155));
						FRAparen.absText(writer,contLine,margeniz+335,altRecep,10);
						altRecep-=15;
						
					}else{
				
						
						FRAparen.absText(writer,contLine,margeniz+335,altRecep,10);
						altRecep-=15;
					}
	
				}
			}
			
			
			altRecep = posicioy-50;
			FRAparen.absLineaFina(writer,5,altRecep,580);
			/*Paragraph paragraph = new Paragraph();
			Anchor anchor = new Anchor(
			          "PAYPAL");
			          anchor.setReference(
			          "http://www.paypal.com");
			      
			      paragraph.add(anchor);*/
		
			
			FRAparen.absLineaFina(writer,5,altRecep-100,580);
			
    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() +" ERROR pintaDatosClienteEmisor() - "+ e.getMessage());
    	}
    	
    }
    
    public void pintaOtrosDatos(){
    	
    	int altRecep = posicioy-165;
    	
    	FRAparen.absText(writer,"Nº de suivi:",margeniz+70,altRecep,10);
		FRAparen.absText(writer," ",margeniz+70,altRecep-15,10);
		
		FRAparen.absText(writer,"Date d'envoi:",margeniz+170,altRecep,10);
		FRAparen.absText(writer,FRAparen.fechaSuiza(fhfactur),margeniz+170,altRecep-15,10);
		
		FRAparen.absText(writer,"Livré par:",margeniz+270,altRecep,10);
		FRAparen.absText(writer," ",margeniz+270,altRecep-15,10);
		
		FRAparen.absText(writer,"FOB",margeniz+370,altRecep,10);
		FRAparen.absText(writer," ",margeniz+370,altRecep-15,10);
		
		
    	
    	try {
	    	if (cabecNFC.equals("COND")){
			//	FRAparen.absTextBoldColor(writer,txtipnfc,margeniz,pxfrombt,16,new BaseColor(223,0,0));
			} else {
			//	FRAparen.absTextBold(writer,txtipnfc,margeniz,pxfrombt,10);
			}
	    	
	    	//FRAparen.absTextBold(writer,txtipnfc,margeniz,pxfrombt,10);
	    	
	    	if (tipofact == 1){
	    		//String adicinf1 = "Facture: Transaction - Ricardo.ch Nº XXXXXXXXX";
	    		//adicinf1 = tipologo;
		    	//FRAparen.absTextBold(writer,adicinf1,margeniz,pxfrombt-16,10);
	    	}
	    	
	    	if (tipofact == 2){
		    	//String adicinf2 = "Matériel d'occasion et non garanti.";
		    	//FRAparen.absTextBold(writer,adicinf2,margeniz,240,10);
	    	}
	    	
    	} catch (Exception e) {
    		
    	}
    	
    }
    
    public void pintaDetallesFRA() {
    	
    	try {
    		
	    	PdfPTable table = new PdfPTable(new float[] { 1, 7, 1 ,2, 1, 2});
			table.setTotalWidth(anctabla);
		
			FRAparen.insCabeceraTablaTradens(table);
			
			Font fuente = new Font(FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(64, 64, 64));
			BaseColor bkcolor = new BaseColor(255, 255, 255);
		
			int numpagex = 1;
			int contxpag = 0;
			int ultfilsh = 0;
			int finfilax = 0;
			String cdestado = "V";
			Grid grLineas = null;
			
			if(regenera !=null && regenera.equals("S")){
				cdestado = "R";
				if(mcagrupa == 1){
					 listimei = informda;
					 grLineas = FRAparen.recuperoLineasTmpAgru(emisclie,idfactur);
					 agrupada = "S";
					 
				}else{
					grLineas = recuperoLineasFacturadas(cdestado,idlineas);
					agrupada = "N";
				}
			}else{
				 
				 if(facalbar !=null && facalbar.equals("S")){
					 cdestado = "F";
					 grLineas = recuperoLineasFacturadas(cdestado,idlineas);
					 estaalba ="F"; // indica que se ha facturado esa linea del albaran
					 idtmpfra = FRAparen.maxIdmtpFRa(emisclie);
					 insLineas(grLineas);
				 }else if(facalbar !=null && facalbar.equals("D")){
					 cdestado = "F";
					 grLineas = recuperoLineasFacturadas(cdestado,idlineas);
					 estaalba ="D"; // indica que se ha facturado esa linea del albaran
					 idtmpfra = FRAparen.maxIdmtpFRa(emisclie);
					 insLineas(grLineas);
					 
				  }else if(mcagrupa == 1){
					 grLineas = FRAparen.recuperoLineasAgru(emisclie,receclie,listimei);
					 agrupada = "S";
				  }else{
					 cdestado = "V";
					 grLineas = recuperoLineas(cdestado);
					 agrupada = "N";
					 
				 }
			 }
			
			
			
			String concepto = "";
			String codprodu = "";
			String cantidad = "";
			String precioun = "";
			String descuent = "0";
			double precioto = 0;
			
			
			for (int i = 0; i < grLineas.rowCount(); i++){
				
				if( mcagrupa == 1){
					cantidad = grLineas.getStringCell(i,"unidades");
					codprodu = grLineas.getStringCell(i,"codprodu");
					precioun = grLineas.getStringCell(i,"pricevnt");
					concepto = grLineas.getStringCell(i,"txmarcax") +" "+grLineas.getStringCell(i,"txmodelo"); 
					precioto = Double.valueOf(precioun) * Double.valueOf(cantidad); 
				}else{
					cantidad = grLineas.getStringCell(i,"cantidad");
					precioun = grLineas.getStringCell(i,"precioun");
					precioto = Double.valueOf(grLineas.getStringCell(i,"precioto"));
					concepto = grLineas.getStringCell(i,"concepto"); 
					descuent = grLineas.getStringCell(i,"descuent");
					codprodu = grLineas.getStringCell(i,"codprodu");
					
				}
								
				int iniciotab = 550;//initabla;
				
				/*if (numpagex == 1){
					//iniciotab = initabla - 165;
					iniciotab = initabla - (initabla - pxfrombt + 100);
					iniciotab = initabla;
				}*/
				
			
				
				if ((i > 0) && ((i == filasprim) || (contxpag == filasxpag))){
					
					finfilax = ultfilsh + filasxpag + 1;
				
					if (numpagex == 1 ){
						finfilax = ultfilsh + filasprim + numpagex;
					}
					
					table.setTotalWidth(anctabla);
			        PdfContentByte canvas = writer.getDirectContent();	        
			        // draw the first two columns on one page
			       table.writeSelectedRows(ultfilsh, finfilax, margeniz, iniciotab, canvas);
			        
			        contxpag = 0;
			        numpagex++;			        			        
			        ultfilsh = finfilax;
			       
			        
					//Pie de Pagina
			    	int altlinea = 50;
			    	FRAparen.absLineaFina(writer, margeniz,altlinea,anctabla);
			    	FRAparen.absText(writer, "Pag. "+ writer.getCurrentPageNumber(),anctabla,altlinea - 10,6);
			    	
			        documento.newPage();
			        FRAparen.insCabeceraTablaTradens(table);
			        
				}
				
				double unit 	 = Double.valueOf(cantidad);
				double importe   = Double.valueOf(precioun);
				double totalln   = Double.valueOf(precioto);
				double descuento = Double.valueOf(descuent);
				
				if (!idtmpfra.equals("") && !idtmpfra.equals(grLineas.getStringCell(i,idtmpfra))) {
					System.out.println(" ATENCION!!! Existen mas de un idtmpfra en la factura. idtmpfra="+ idtmpfra +" vs grid "+ idtmpfra.equals("idtmpfra"));
				}
				
				idtmpfra = grLineas.getStringCell(i,"idtmpfra");
				idunicox = grLineas.getStringCell(i,"idunicox");
				
				//DEBIDO AL FORMATO
				descuento = descuento / 100;
				
				table.addCell(FRAparen.getCelda(codprodu,fuente,bkcolor,"center"));
				table.addCell(FRAparen.getCelda(concepto,fuente,bkcolor,"left"));
				table.addCell(FRAparen.getCelda(formatUnit.format(unit) +" un.",fuente,bkcolor,"center"));
				table.addCell(FRAparen.getCelda(formatDivi.format(importe) + divisa,fuente,bkcolor,"right"));
				if (descuento == 0){
					table.addCell(FRAparen.getCelda("-"+"    ",fuente,bkcolor,"right"));
				} else {
					table.addCell(FRAparen.getCelda(formatPorc.format(descuento),fuente,bkcolor,"right"));
				}
				
				//String strPreci = grLineas.getStringCell(i,"precioto");
				table.addCell(FRAparen.getCelda(formatDivi.format(Double.valueOf(precioto)) + divisa,fuente,bkcolor,"right"));

				impbasei += totalln;
				
				contxpag++;
				
			}
	
	
			//CÁLCULO DE IMPUESTO
			impitbis = impbasei * porTaxes / 100;
			//CÁLCULO DE TOTAL
			imptotal = impbasei + impitbis;
			
			BaseColor bkcolorCab = new BaseColor(200, 200, 200);
			
			Font fuenteTot = new Font(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(0, 0, 0));
			/*
			if (!cabecNFC.equals("COND")){
				
				table.addCell(FRAparen.getCelda2(" ",fuente,bkcolor,"center",0,3));
				table.addCell(FRAparen.getCelda2("NET HT",fuenteTot,bkcolorCab,"center",1,2));
				table.addCell(FRAparen.getCelda(formatDivi.format(impbasei).replaceFirst(".","'") + divisa,fuenteTot,bkcolor,"right"));
				
				table.addCell(FRAparen.getCelda2(" ",fuente,bkcolor,"center",0,3));
				if (tipofact == 2){
					table.addCell(FRAparen.getCelda2("HORS TVA POUR EXPORT",fuenteTot,bkcolorCab,"center",1,2));
				} else {
					table.addCell(FRAparen.getCelda2("CHE 449.260.892 TVA ("+ porTaxes +"%)",fuenteTot,bkcolorCab,"center",1,2));
				}
				table.addCell(FRAparen.getCelda( formatDivi.format(impbasei).replaceFirst(".","'")+ divisa,fuenteTot,bkcolor,"right"));
				
			}
			
			table.addCell(FRAparen.getCelda2(" ",fuente,bkcolor,"center",0,3));
			if (tipofact == 2){
				table.addCell(FRAparen.getCelda2("NET USD",fuenteTot,bkcolorCab,"center",1,2));
			} else {
				table.addCell(FRAparen.getCelda2("NET CHF",fuenteTot,bkcolorCab,"center",1,2));
			}
			
			table.addCell(FRAparen.getCelda(formatDivi.format(imptotal).replaceFirst(".", "'") + divisa,fuenteTot,bkcolor,"right"));
			*/
			if(informda != null && !informda.equals("") && (mcagrupa == 0)){
	    		
	    		PdfPTable tableAd = new PdfPTable(1);
				tableAd.setTotalWidth(anctabla);
				tableAd.addCell(FRAparen.getCelda2("Information additionnelle:",fuenteTot,bkcolor,"left",0,2));
				tableAd.addCell(FRAparen.getCelda2(informda,fuente,bkcolor,"left",0,2));
				PdfContentByte canvas2 = writer.getDirectContent();
		    	tableAd.writeSelectedRows(ultfilsh, -1, margeniz, lineResu+150, canvas2);
	    	
	    	}
			
			
			/*if (numpagex > 1){
	        	documento.newPage();
	        }  else {
	        	initabla -= 190;
	        }*/
			
			lineResu = 250;
			lineResu = (initabla - 110) - (contxpag * 13);
			if (numpagex > 1){
				initabla = 800;
			}
	        PdfContentByte canvas = writer.getDirectContent();
	    	table.writeSelectedRows(ultfilsh, -1, margeniz, initabla, canvas);
		    
    	} catch (Exception e) {
    		
    	}
			
    }
    
    
    
    
    public void pintaEnlace(Double importe){
    	
    	
    		Image image;
    	
    		try {
    			imglogox = FRAparen.obtenerLogoEmisor(emisclie);
	        
    			String imageUrl = PropiedadesJLet.getInstance().getProperty("path.img.logoinvoice") + "btnpaypal.png";

    			image = Image.getInstance(imageUrl);
    			image.setAbsolutePosition(margeniz+100, 240);
    			image.scaleAbsolute(100,40);
    			image.setAlignment(Image.LEFT | Image.TEXTWRAP);//Code 2
    			documento.add(image);
			
    			} catch (Exception ex) {
    				ex.printStackTrace();
    			} 
    	
    	
    	Font fuenteTot = new Font(FontFamily.HELVETICA, 0, Font.NORMAL, new BaseColor(255,255,255));
		Paragraph parrafo = new Paragraph();
		Anchor anchor = new Anchor("                             '");
		anchor.setFont(fuenteTot);
		anchor.setReference("https://www.paypal.com/es/cgi-bin/webscr?cmd=_xclick&business=info@tradens.ch&currency_code=CHF&item_name="+cabecNFC +" "+ numerNFC +"&amount="+importe);
		parrafo.add(anchor); 
		parrafo.add("\n");
		parrafo.add(anchor); 
		parrafo.add("\n");
		parrafo.add(anchor); 
		parrafo.add("\n");
		parrafo.add(anchor); 
		
		Chunk chunkNom 	= null;
		Phrase contcell = null;
		PdfPCell cell 	= null;
			    	
			    	try {
				    	
			    		int anctabla  = 600;
			    		 PdfPTable table = new PdfPTable(1);  
			    		table.setTotalWidth(anctabla);
				    	    contcell = new Phrase(parrafo);
				    	    
				    		cell = new PdfPCell(contcell);
				            cell.setColspan(2);
				            cell.setBorder(0);
				            table.addCell(cell);
				            PdfContentByte canvas = writer.getDirectContent();
					    	table.writeSelectedRows(0, 2, margeniz+100, 280, canvas);
				           // documento.add(table);
			    		}catch(Exception e){
			    			System.err.println("ERROR al rellenar informacion adicional. "+ e.getMessage());
			    		}
    }
    
    public void pintaDatosBancarios(){
    	try {
    		
    		
    		ListFormaPagoBDIn formPagoBDIn = new ListFormaPagoBDIn();
	    	formPagoBDIn.setValue("idemisor",emisclie);
			formPagoBDIn.setValue("idfrmpag",formpago);
			ListFormaPagoBD formPagoBD = new ListFormaPagoBD(formPagoBDIn);
	    	formPagoBD.setConnection(con);
	    	Grid gdFrmPag = formPagoBD.execSelect();
    		
	    	if (gdFrmPag.rowCount() > 0){
		    	
	    		mcefecti = gdFrmPag.getStringCell(0,"mcefecti");
	    		String datosBan = gdFrmPag.getStringCell(0,"txbancox");

		    	if (mcefecti.equals("N")){
		    		FRAparen.absText(writer,"Payez sur notre compte bancaire IBAN Nº:",margeniz,180,10);
		    		datosBan = " "+ gdFrmPag.getStringCell(0,"txcuenta") +" "+ gdFrmPag.getStringCell(0,"txbancox") +" "+ gdFrmPag.getStringCell(0,"txotrosx");
		    	}else{
		    		
		    		datosBan = " "+ gdFrmPag.getStringCell(0,"txcuenta") +" "+ gdFrmPag.getStringCell(0,"txbancox") +" "+ gdFrmPag.getStringCell(0,"txotrosx");
		
		    	}
		    	
		    	FRAparen.absText(writer,datosBan,margeniz,160,10);
	    	}
    		
    		/*
	    	if (tipofact == 1){
		    	//DATOS BANCARIOS
		    	String datosBan = "Paiment sur notre compte postal IBAN Nº : CH34 0900 0000 1406 3042 5 - Postfinance";
		    	FRAparen.absText(writer,datosBan,margeniz,180,10);
		    	//datosBan = "ou sur notre compte bancaire IBAN Nº : CH22 0883 1001 S000 0883 6 - NCG Banco SA - Genève";
		    	//FRAparen.absText(writer,datosBan,margeniz,160,10);
	    	} else if (tipofact == 2){
		    	String datosBan = "Paiment sur notre compte bancaire IBAN Nº : CH22 0883 1001 S000 0883 6  - BIC CAGELESMMXXX (NCG Banco SA - Madrid)";
		    	FRAparen.absText(writer,datosBan,margeniz,160,10);
	    	}
	    	*/
	    	
    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() + "ERROR pintaDatosBancarios() "+ e.getMessage());
    	}
    	
    }
    
    public void pintaPiePagina(){
    	
    	int altlinea = 50;
    	
    	try {
	    	
    		FRAparen.absTextBold(writer, "Merci beaucoup pour votre achat",400,100,10);
    		
    		FRAparen.absTextBoldColor(writer, "TRADENS SA - Bvd de Pérolles 7, CP 839",236,altlinea + 30,7,new BaseColor(0,112,192));
    		FRAparen.absTextBoldColor(writer, "+41265520030 - +41792507734 - info@tradens.ch",298 - 77,altlinea + 20,7,new BaseColor(0,112,192));
    		FRAparen.absTextBoldColor(writer, "CHE-449.260.892",298 - 20,altlinea + 10,7,new BaseColor(0,112,192));
    		
    		//Pie de Pagina
	    	FRAparen.absLineaFina(writer, margeniz,altlinea,anctabla);
	    	FRAparen.absText(writer, "Pag. "+ writer.getCurrentPageNumber(),anctabla,altlinea - 10,6);
			
    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() + "ERROR pintaPiePagina() "+ e.getMessage());
    	}
    	
    }
    
    
    public void pintaTotales(){
    	
    	impitbis = impbasei * porTaxes / 100;
		//CÁLCULO DE TOTAL
		imptotal = impbasei + impitbis;
    	
    	Image image;
    	
		try {
			String imageUrl = PropiedadesJLet.getInstance().getProperty("path.img.logoinvoice") + "paye.jpg";
			
			if(mcagrupa == 1 && totalpag == null){
				mcpagado = "N";
			}

			if (mcpagado !=null && mcpagado.equals("N")) {
				totalpen = String.valueOf(imptotal);
				totalpag = "0";
				if (mcefecti.equals("P")){
					pintaEnlace(imptotal);
				}
			} else {
				if (mcpagado !=null && mcpagado.equals("S")) {
					totalpen = "0";
					totalpag = String.valueOf(imptotal);
					image = Image.getInstance(imageUrl);
					image.setAbsolutePosition(margeniz+100, 240);
					image.scaleAbsolute(100,40);
					image.setAlignment(Image.LEFT | Image.TEXTWRAP);//Code 2
					documento.add(image);
				}else{
					
					if(totalpag == null){
						totalpag = "0";
					}
					
					double totalp =  imptotal -Double.parseDouble(totalpag);
					
					totalpen = String.valueOf(totalp) ; 
					if (mcefecti.equals("P")){
						pintaEnlace(Double.parseDouble(totalpen));	
					}
					
				}
				
			}
		}catch(Exception ex){
			System.out.println("Error al marcar como pagado");
		}
    	
    	
    	
    	
    	
		FRAparen.absLineaFina(writer,5,320,580);
    	FRAparen.absText(writer,"Total H.T",margeniz+300,300,10);
		FRAparen.absText(writer,"Fr."+formatDivi.format(impbasei),margeniz+430,300,10);
		
		FRAparen.absText(writer,"CHE-449.260.892 TVA ",margeniz+300,275,10);
		FRAparen.absText(writer,"Fr "+formatDivi.format(impitbis),margeniz+430,275,10);
		
		//FRAparen.absText(writer,"Port",margeniz+300,235,10);
		//FRAparen.absText(writer,"Fr. 0.00",margeniz+430,235,10);
		
		FRAparen.absText(writer,"Total T.T.C",margeniz+300,220,10);
		FRAparen.absText(writer,"Fr "+formatDivi.format(imptotal),margeniz+430,220,10);
		
		FRAparen.absText(writer,"Payé",margeniz+300,205,10);
		FRAparen.absText(writer,"Fr "+formatDivi.format(Double.parseDouble(totalpag)),margeniz+430,205,10);
		
		FRAparen.absTextBoldColor(writer,"NET A PAYER: ",margeniz+300,180,15,new BaseColor(22, 138, 190));
		FRAparen.absTextBoldColor(writer,"Fr "+formatDivi.format(Double.parseDouble(totalpen)),margeniz+430,180,15,new BaseColor(22, 138, 190));
		
	
    }
    
//------------------------------------------ END PINTAR DATOS -----------------------------    

    
    public void rellenaDatosRNC(int tipofact){
		
    	cabecNFC = "VACIO";
    	txtipnfc = "- VACIO -";
    	
    	try {    		    
	    	ListTipoFacturaBDIn tpFacturaBDIn = new ListTipoFacturaBDIn();
	    	tpFacturaBDIn.setValue("tipofact", tipofact);
	    	tpFacturaBDIn.setValue("idemisor", emisclie);
	    	ListTipoFacturaBD tpFacturaBD = new ListTipoFacturaBD(tpFacturaBDIn);
	    	tpFacturaBD.setConnection(con);
	    	Grid grTpFact = tpFacturaBD.execSelect();
	    	
	    	if (grTpFact.rowCount() > 0){
		    	cabecNFC = grTpFact.getStringCell(0, "preffact");
		    	txtipnfc = grTpFact.getStringCell(0, "txnombre");
		    	porTaxes = Double.parseDouble(grTpFact.getStringCell(0, "porctaxe"));
	    	}
	    	
	    	
    	} catch (Exception e) {
    		System.err.println("ERROR al obtener los datos del TIPO de FACTURA. "+ e.getMessage());
    	}
    	
	}
    
    public Grid recuperoLineas(String cdestado) {
    	
    	Grid grLineas = null;
    	
    	try {
			
			ListLineasTmpBDIn lineasBDIn = new ListLineasTmpBDIn();
			lineasBDIn.setValue("idemisor", emisclie);
			lineasBDIn.setValue("idclient", receclie);
			lineasBDIn.setValue("cdestado", cdestado);
			ListLineasTmpBD lineasBD = new ListLineasTmpBD(lineasBDIn);
			lineasBD.setConnection(this.getConnection());
			grLineas = lineasBD.execSelect();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return grLineas;
    }
    
    
  
    
    public void realizarActualizaciones() {
    	
    	int nUpdates = FRAparen.actualizaCodigoFRA(emisclie, aniofact, tipofact);
    	String cdoldest ="";
    	String cdestado = "S";
    	
    	 if(facalbar !=null && facalbar.equals("S")){ //si viene de facturar albaran
			try {
				cdoldest = "P";
				idtmpfra = FRAparen.maxIdmtpFRa(emisclie);
				int idtmp = Integer.parseInt(idtmpfra);
				idtmp--;
				idtmpfra = Integer.toString(idtmp);	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		 }else if(facalbar !=null && facalbar.equals("D")){ // si viene de devolver albaran
			 try {
				idtmpfra = FRAparen.maxIdmtpFRa(emisclie);
				int idtmp = Integer.parseInt(idtmpfra);
				idtmp--;
				idtmpfra = Integer.toString(idtmp);	
				cdoldest = "F";
				cdestado = "D";
				estaalba = "D";
				 
			 } catch (Exception e) {
			
				e.printStackTrace();
			 }
			  
		 }else{// si es una generación nueva.
			 
			 if(tipofact == 0){
				 estaalba = "P";
			 }else{
				 estaalba = "N";
			 }
			 
			cdoldest = "V";
			 
		 }
    	 
    	 if(idtmpfra.equals("")){
    		 try{
    		 	idtmpfra = FRAparen.maxIdmtpFRa(emisclie);
				int idtmp = Integer.parseInt(idtmpfra);
				idtmp--;
				idtmpfra = Integer.toString(idtmp);	
    		 }catch(Exception ex){
    			 System.out.println("Error la obtener idtmpfra de agrupacion");
    		 }
    	 }
    	 
    	 
    	 
    	 /*guardamos los imeis en informacion adicional*/
    	 
    	 if(informda == null || informda.equals("")){
    		 informda = listimei;
    	 }
    	
    	if (nUpdates != 1){
    		System.err.println(" ERROR GRAVE - No se ha actualizado el codigo de la facturacion");
    	} else {
			try {
				UpdLineasBDIn InsLinBDIn= new UpdLineasBDIn();
				InsLinBDIn.setValue("idclient",receclie);
    			InsLinBDIn.setValue("idemisor",emisclie);
    			InsLinBDIn.setValue("tpclient",tpclient);
    			InsLinBDIn.setValue("idtmpfra",idtmpfra);
    			InsLinBDIn.setValue("cdoldest","V");
    			InsLinBDIn.setValue("cdnewest","F");
	    		UpdLineasBD InsLinBD= new UpdLineasBD(InsLinBDIn);
	    		InsLinBD.setConnection(con);
	    		int liUpdate = InsLinBD.execUpdate();
	    		System.out.println(" Se han actualizado "+ liUpdate +" lineas.");
	    		
	    		UpdFacturaBDIn factBDIn = new UpdFacturaBDIn();
	    		factBDIn.setValue("idemisor",emisclie);
	    		factBDIn.setValue("idclient",receclie);
	    		factBDIn.setValue("cdfactur",numerNFC);
	    		factBDIn.setValue("fhfactur",fhfactur);
	    		factBDIn.setValue("tipofact",tipofact);
	    		factBDIn.setValue("aniofact",aniofact);
	    		factBDIn.setValue("cddivisa",divisa);
	    		factBDIn.setValue("baseimpo",impbasei);
	    		factBDIn.setValue("imptaxes",impitbis);
	    		factBDIn.setValue("imptotal",imptotal);
	    		factBDIn.setValue("condpago",condpago);
	    		factBDIn.setValue("formpago",formpago);
	    		factBDIn.setValue("catefact",catefact);
	    		factBDIn.setValue("filecrea",namefile);
	    		factBDIn.setValue("idtmpfra",idtmpfra);
	    		factBDIn.setValue("informda",informda);
	    		factBDIn.setValue("mcpagado",mcpagado);
	    		factBDIn.setValue("mcagrupa",agrupada);
	    		
	    		if (mcpagado!=null && mcpagado.equals("S")){
	    			totalpag = String.valueOf(imptotal);
	    			factBDIn.setValue("totalpag",totalpag);
	    		}
	    		
	    		
	    		//factBDIn.setValue("codvende",codvende);
	    		factBDIn.setValue("cdestado","S");
	    		UpdFacturaBD factBD = new UpdFacturaBD(factBDIn);
	    		factBD.setConnection(con);
	    		int creaFact = factBD.execInsert();
	    		System.out.println(" Se ha creado "+ creaFact +" factura.");
	    		
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    }
    
    public void insLineas(Grid gdLineas){
     	String idtmpreg = "";
     	String nlineaxx = "";
     	String idlineax = "";
     	String codprodu = "";
     	String idfactur = "";
     	String fechafac = "";
     	String cantidad = "";
     	String tpclient = "";
     	String concepto = "";
     	String precioun = "";
     	String descuent = "";
     	String precioto = "";
     	String idunicox = "";
     	String cdestado = "";
     	

     	try{
     		
     		idtmpfra = FRAparen.maxIdmtpFRa(emisclie);
     		
     		if(facalbar !=null && facalbar.equals("D")){
     			estaalba = "D";
     			cdestado = "F";
     		}
     	
     	for(int i=0; i<gdLineas.rowCount();i++){
     		
     		idtmpreg = Long.toString(System.currentTimeMillis());
     		codprodu = gdLineas.getStringCell(i, "codprodu");
     		idfactur = gdLineas.getStringCell(i, "idtmpfra");
     		fechafac = gdLineas.getStringCell(i, "fhfechax");
     		cantidad = gdLineas.getStringCell(i, "cantidad");
     		concepto = gdLineas.getStringCell(i, "concepto");
     		precioun = gdLineas.getStringCell(i, "precioun");
     		descuent = gdLineas.getStringCell(i, "descuent");
     		precioto = gdLineas.getStringCell(i, "precioto");
     		idunicox = gdLineas.getStringCell(i, "idunicox");
     		nlineaxx = Integer.toString(i);
     	
    		    	Service srv = null;
    		    	
    		    	ObjectIO srvIn = null;
    		    	ObjectIO srvOut = null;
    		    	srv = new TempFacturaSrv();
    		    	
    		    	
    		    	srvIn  = srv.instanceOfInput();
    				srvOut = srv.instanceOfOutput();
    				srvIn.setValue("tipoOper", "I");
    				srvIn.setValue("idlineax", idtmpreg);
    				srvIn.setValue("codprodu", codprodu);
    				srvIn.setValue("idfactu", idtmpfra);
    				srvIn.setValue("idemisor", emisclie);
    				srvIn.setValue("idclient", receclie);
    				srvIn.setValue("tpclient", tpclient);
    				//srvIn.setValue("fechafac", fechafac);
    				srvIn.setValue("nlineaxx", nlineaxx);
    				srvIn.setValue("cantLinea", cantidad);
    				srvIn.setValue("concepto", concepto);
    				srvIn.setValue("precLinea", precioun);
    				srvIn.setValue("descLinea", descuent);
    				srvIn.setValue("totalLinea", precioto);
    				srvIn.setValue("idunicox", idunicox);
    				srvIn.setValue("cdestado", "F");// Lineas que se facturan en el momento;
    				srvIn.setValue("estaalba", estaalba);
    				
    				srv.setConnection(con);
    				srv.process(srvIn, srvOut);
     		}
     	
     	
     	String estadoal ="";
     	if(facalbar !=null && facalbar.equals("D")){
     		estadoal = "D";
     	}else{
     		estadoal = "F";	
     	}
     	
     	actualizaEstadoAlba(estadoal);
     	
     	//*actualizaEstadoAlba()
      	}catch(Exception ex){
      		System.out.println("Error al insertar lineas"+ ex.getMessage());
      	}
     }
     
     
    public void actualizaEstadoAlba(String cdestado){
     	
     	UpdLineasBDIn InsLinBDIn= new UpdLineasBDIn();
    		try {
    			InsLinBDIn.setValue("idclient",receclie);
    			InsLinBDIn.setValue("tpclient",tpclient);
    			InsLinBDIn.setValue("idemisor",emisclie);
    			InsLinBDIn.setValue("cdoldest","F");
    			InsLinBDIn.setValue("cdnewest","F");
    			InsLinBDIn.setValue("idtmpfra",idlineas);
    			InsLinBDIn.setValue("estaalba",cdestado);
    			InsLinBDIn.setValue("marcados",marcados);
    			InsLinBDIn.setValue("imeicode",idunicox);
    			UpdLineasBD InsLinBD= new UpdLineasBD(InsLinBDIn);
    			InsLinBD.setConnection(con);
    			int liUpdate = InsLinBD.execUpdate();
    			System.out.println(" Se han actualizadaaaao "+ liUpdate +" lineas.");
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    			    	
     	
     }
            
    
    
    public void pintaAnexo(){
    	
    	 documento.newPage();
    	 pagetotal++;
    	 try{
    		Image image;
    		Font fuente = new Font(FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(64, 64, 64));
			BaseColor bkcolor = new BaseColor(255, 255, 255);
		
    		//String imageUrl = PropiedadesJLet.getInstance().getProperty("path.img.logoinvoice") + "gtp100.jpg";

 			/*image = Image.getInstance(imageUrl);
 		    image.setAbsolutePosition((documento.getPageSize().getWidth() / 2) - 151, 750);
 		    image.scaleAbsolute(323,323);*/
 		  
    	 
    		 PdfPTable table = new PdfPTable(new float[] { 1});
    		 table.setTotalWidth(620);
    		// table.addCell(image);
    		 table.addCell(FRAparen.getCelda(listimei.replaceAll(";", "         -        "),fuente,bkcolor,"left"));
    		 documento.add(table);
    	 }catch(Exception ex){
    		 System.out.println("Error crear anexo nueva pagina");
    	 }
    	 
    	 
    }
    
    
   public Grid recuperoLineasFacturadas(String cdestado,String idtmpfra) {
    	
    	Grid grLineas = null;
    	
    	try {
			
			ListLineasTmpBDIn lineasBDIn = new ListLineasTmpBDIn();
			lineasBDIn.setValue("idemisor", emisclie);
			lineasBDIn.setValue("idclient", receclie);
			lineasBDIn.setValue("tpclient", tpclient);
			lineasBDIn.setValue("cdestado", cdestado);
			lineasBDIn.setValue("idtmpfra", idtmpfra);
			lineasBDIn.setValue("marcados", marcados);
			ListLineasTmpBD lineasBD = new ListLineasTmpBD(lineasBDIn);
			lineasBD.setConnection(this.getConnection());
			grLineas = lineasBD.execSelect();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return grLineas;
    }
    
 public void recalCulaFactu( String idfactur) {
    	
    	try{
	    	UpdFacturaBDIn factBDIn = new UpdFacturaBDIn();
	    	factBDIn.setValue("idfactur",idfactur);
			factBDIn.setValue("baseimpo",impbasei);
			factBDIn.setValue("imptaxes",impitbis);
			factBDIn.setValue("imptotal",imptotal);
			factBDIn.setValue("filecrea",namefile);
			factBDIn.setValue("totalpag",totalpag);
			UpdFacturaBD factBD = new UpdFacturaBD(factBDIn);
    		factBD.setConnection(con);
    		int creaFact = factBD.execUpdate();
    		System.out.println(" Se ha Actualizado "+ creaFact +" factura.");
			
    	   }catch(Exception ex){
    		   System.out.println("Error al recalcular Factura");
    	   }
    }
 
 public Grid selecPendientes(){ //para marcar como vendidos
	 	
	 	Grid gdLineasBD = null;
	 	
	 	try {
	 		ListLineasBDIn lineasBDIn= new ListLineasBDIn();
				lineasBDIn.setValue("idclient",receclie);
				lineasBDIn.setValue("tpclient",tpclient);
				lineasBDIn.setValue("idemisor",emisclie);
				lineasBDIn.setValue("cdestado","V");
				ListLineasBD lineasBD = new ListLineasBD(lineasBDIn);
				lineasBD.setConnection(con);
			    gdLineasBD = lineasBD.execSelect();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 	
			return gdLineasBD;
		
	 }
 public void cambiaStock(Grid gdLineasImei, String cdestado){ // si es devolucion
 	
		String imeicode ="";
		String idfactur = "";
		String pricevnt = "";
		double prusdvnt = 0;
		
		
		try{
		
			Grid gdMaxfactu = null;
			
			UpdFacturaBDIn factBDIn = new UpdFacturaBDIn();
			UpdFacturaBD factBD = new UpdFacturaBD(factBDIn);
			factBD.setConnection(con);
			gdMaxfactu = factBD.execSelect();
			idfactur = gdMaxfactu.getStringCell(0, "idfactur");
			
			int idfact = Integer.parseInt(idfactur);
			idfact = idfact+1;
			idfactur = Integer.toString(idfact);
			
			System.out.println(" Id factura "+ idfactur +" factura.");
		}catch(Exception ex){
			System.out.println("Errror al recuperar el idfactur");
		}
		
		
		System.out.println("Hay "+gdLineasImei.rowCount()+" LINEAS");
	
		for(int i=0; gdLineasImei.rowCount()>i; i++){
				imeicode = gdLineasImei.getStringCell(i, "idunicox");
				pricevnt = gdLineasImei.getStringCell(i, "precioun");
				pricevnt = gdLineasImei.getStringCell(i, "precioun");
				prusdvnt = fixingUSD(pricevnt);
				
				if(!imeicode.equals("")){
					try {
						UpdStockBDIn devStockBDIn= new UpdStockBDIn();
						devStockBDIn.setValue("idemisor",emisclie);
						devStockBDIn.setValue("imeicode",imeicode);
						devStockBDIn.setValue("cdestado",cdestado);
						devStockBDIn.setValue("idfactur",idfactur);
						devStockBDIn.setValue("pricevnt",pricevnt);
						devStockBDIn.setValue("idclient",receclie);
						devStockBDIn.setValue("tpclient",tpclient);
						devStockBDIn.setValue("prusdvnt",prusdvnt);
						devStockBDIn.setValue("divisvnt",divisa);
						
				    	UpdStockBD devStockBD = new UpdStockBD(devStockBDIn);
				    	devStockBD.setConnection(con);
						int liInsert = devStockBD.execUpdate();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}	
		}	
			
}
        
    public String getFileCreated(){
    	return this.filecrea;    	
    }
    
    
    public DecimalFormat estableceFormato(){
    	Locale locale = new Locale("fr", "FR");
    	
    	//DecimalFormatSymbols simbolo = new DecimalFormatSymbols(locale);
    	DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols(locale);
    	unusualSymbols.setGroupingSeparator('´');
    	unusualSymbols.setDecimalSeparator('.');
    	DecimalFormat formatDi = new DecimalFormat("###,##0.00",unusualSymbols);
    	//formatDi.setGroupingSize(4);
    	
    	String bizarre = formatDi.format(12345.678);
    	System.out.println("formatoooooooooooooooooooooooooooooooooooooooooo "+bizarre);
    	
    	return formatDi;
    }
    
    public double fixingUSD( String pricevnt){
    	//Obtengo el fixing del CHF
    	  String stfixing ="";
    	  double prusdvnt = 0;
    	  double dbfixing = 0;
    	  double preciovn = 0;
      	try {
           	Divisa divisa = new Divisa();
           	divisa.setConnection(con);

           	stfixing = divisa.getFixingUSD("CHF");
           	dbfixing = Double.parseDouble(stfixing);
           	prusdvnt = Double.parseDouble(pricevnt);
           	preciovn = prusdvnt / dbfixing;
           	
           	return preciovn;
           	
           } catch (Exception e) {
           	System.out.println("ERROR realizando operaciones de Fixing");
           	preciovn =0;
           }
    	return preciovn;
      }
    
}
	
