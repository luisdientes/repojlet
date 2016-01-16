package facturacion;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import utils.PropiedadesJLet;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import factura.bd.ListFormaPagoBD;
import factura.bd.ListFormaPagoBDIn;
import facturacion.bd.ListClienteWebBD;
import facturacion.bd.ListClienteWebBDIn;
import facturacion.bd.ListLineasTmpBD;
import facturacion.bd.ListLineasTmpBDIn;
import facturacion.bd.ListTipoFacturaBD;
import facturacion.bd.ListTipoFacturaBDIn;
import facturacion.bd.UpdFacturaBD;
import facturacion.bd.UpdFacturaBDIn;
import facturacion.bd.UpdFacturaWebBD;
import facturacion.bd.UpdFacturaWebBDIn;
import facturacion.bd.UpdLineasBD;
import facturacion.bd.UpdLineasBDIn;
import facturacion.parents.FacturaPDF;


public class FRAizumbaWebSrv extends Service {
	
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
	
	int initabla  = 730;
	int anctabla  = 520;
	
	int lineResu  = 250;
	
	int margeniz  = 35;
	int inmargde  = 415;	
	
	//DATOS DE LA FACTURA
	double porTaxes = 18;
	String divisa = " $RD";
	
	//PAR�METROS A RELLENAR EN LA FACTURA
	String cabecNFC = "A0100100101";
	String numerNFC = "00000001";
	String txtipnfc = "FACTURA CON VALOR FISCAL";
	String imglogox = "";
	String idtmpfra = "";
	double impbasei = 0;
	double impitbis = 0;
	double imptotal = 0;
	
	//FORMATOS NUM�RICOS
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
	String idorderx = "";
	int tipofact = 0;
	int mcagrupa = 0;
	String formpago = "";
	String condpago = "";
	String catefact = "";
	String tipologo = "";
	String factasoc = "";
	
	String regenera = "";
	String idlineas = "";
	String cdfactur = "";
	String facalbar = "";
	String estaalba = "N";

	String informda = "";
	String codvende = "";
	String mcpagado = "";
	String cduserid = "";
	String marcados = "";
	String idfacreg = "";
	//PAR�METROS DE SALIDA
	String namefile = "";
	String filecrea = "";
	
	//OTROS PAR�METROS
	Grid datosEmi = new Grid();
	Grid datosRec = new Grid();
	
	Connection izucon = null;
	      
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
			input.addVariable("catefact");
			input.addVariable("tipologo");
			input.addVariable("idorderx");
			input.addVariable("factasoc");

			input.addVariable("regenera");
			input.addVariable("idlineas");
			input.addVariable("cdfactur");
			input.addVariable("facalbar");
			input.addVariable("marcados");
			input.addVariable("informda");
			input.addVariable("idfactur");
			input.addVariable("codvende");
			input.addVariable("mcpagado");
			input.addVariable("cduserid");
			input.addVariable("listimei");
			input.addVariable("porcrete");
			input.addVariable("totalpen");
			input.addVariable("totalpag");
			input.addVariable("devoluci");
			
						
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
        	idorderx = input.getStringValue("idorderx");
        	aniofact = input.getStringValue("aniofact");	
        	fhfactur = input.getStringValue("fhfactur");
        	tipofact = Integer.parseInt(input.getStringValue("tipofact"));
        	mcagrupa = Integer.parseInt(input.getStringValue("mcagrupa"));
        	formpago = input.getStringValue("formpago");
        	condpago = input.getStringValue("condpago");
        	catefact = input.getStringValue("catefact");
        	tipologo = input.getStringValue("tipologo");
      
        	regenera = input.getStringValue("regenera");
        	idlineas = input.getStringValue("idlineas");
        	cdfactur = input.getStringValue("cdfactur");
        	facalbar = input.getStringValue("facalbar");
        	marcados = input.getStringValue("marcados");
        	factasoc = input.getStringValue("factasoc");
        	informda = input.getStringValue("informda"); 
        	idfacreg = input.getStringValue("idfactur");
        	codvende = input.getStringValue("codvende");
        	mcpagado = input.getStringValue("mcpagado"); 
        	cduserid = input.getStringValue("cduserid"); 
        	
        	
			FRAparen.setConexion(this.getConnection());
		} catch (Exception e1) {
			System.err.println(this.getClass().getName() +"- ERROR - "+ e1.getMessage());
			e1.printStackTrace();
		}
		
	}
	
    public void process(ObjectIO input, ObjectIO output){
    	
    	try {
	    	recuperaInput(input);
	    	estableceCodigosFRA();
	    	
	    	Rectangle pageSize = new Rectangle(PageSize.LETTER);
	    	pageSize.setBackgroundColor(new BaseColor(255, 255, 255));
	    	pageSize.rotate();
	        documento = new Document(pageSize,20, 20, 30, 30);
	        
	        System.out.println("WIDTH "+documento.getPageSize().getWidth());
	        System.out.println("HEIGHT "+documento.getPageSize().getHeight());
	        
	        rellenaDatosRNC(tipofact);
	        
	        namefile = cabecNFC +"_"+ numerNFC +"_"+aniofact+".pdf";
	        String folderin = PropiedadesJLet.getInstance().getProperty("path.gen.invoice") + "emisor_"+ emisclie + "/"; 
	        File infolder = new File(folderin);
	        
	        if (!infolder.isDirectory()){
	        	infolder.mkdirs();
	        }
	        
	        filecrea = folderin + namefile;
	    	writer = PdfWriter.getInstance(documento, new FileOutputStream(filecrea));
	        documento.open();
	        pxfrombt = 665;
			
	        
	        if (tipofact != 5){
	        	pintaCabecera();
	        }
	        
	        pintaFecha();
	        
	        pintaDatosClienteEmisor();
	        
	        pintaOtrosDatos();
	        
	        pintaDetallesFRA();

	        if (tipofact != 5){
	        	pintaDatosBancarios();
	        
	        	pintaPiePagina();
	        }
	        
	        realizarActualizaciones();
	        
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
	        
	        if ((tipologo != null) && (tipologo.equals("1"))){
	        	imglogox = "logomallproshop.png";
	        }
	        
	        String imageUrl = PropiedadesJLet.getInstance().getProperty("path.img.logos") + imglogox;

			image = Image.getInstance(imageUrl);
		    image.setAbsolutePosition(margeniz, 700);
		    image.scaleAbsolute(200,60);
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
			FRAparen.absTextBold(writer,"Fecha ",inmargde,730,10);
			FRAparen.absText(writer,FRAparen.fechaNormal(fhfactur),inmargde + 45,730,10);
			
			FRAparen.absText(writer,cabecNFC +" "+ numerNFC +" ",inmargde,710,10);
			
    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() +" ERROR pintaFecha() - "+ e.getMessage());
    	}
    	
    }
    
    public void pintaDatosClienteEmisor(){
    	
    	try {
    		
    		if (tipofact != 5){
	    		datosEmi = FRAparen.obtenerDatosCliente(emisclie,"0");
				
				ArrayList<String> lineaEmi = new ArrayList<String>();
				lineaEmi = FRAparen.obtenerCabeceraCliente(datosEmi);
				
				
				for (int i = 0; i < lineaEmi.size(); i++){
					String contLine = lineaEmi.get(i);
					if ((contLine != null) && (!"".equals(contLine))){
						FRAparen.absText(writer,contLine,margeniz,pxfrombt,8);
						pxfrombt-=15;
					}
				}
    		}
			
			datosRec = obtenerDatosClienteWeb(receclie);
			
			ArrayList<String> lineaRec = new ArrayList<String>();
			lineaRec =  obtenerCabeceraClienteWeb(datosRec);
			//Altura de los datos del receptor
			int altRecep = pxfrombt;
			
			for (int i = 0; i < lineaRec.size(); i++){
				
				String contLine = lineaRec.get(i);
				if ((contLine != null) && (!"".equals(contLine))){
					FRAparen.absText(writer,contLine,margeniz+300,altRecep,10);
					altRecep-=15;
				}
			}
			
    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() +" ERROR pintaDatosClienteEmisor() - "+ e.getMessage());
    	}
    	
    }
    
    public void pintaOtrosDatos(){
    	
    	pxfrombt-=25;
    	
    	try {
	    	if (cabecNFC.equals("COND")){
				FRAparen.absTextBoldColor(writer,txtipnfc,margeniz,pxfrombt,16,new BaseColor(223,0,0));
			} else {
				if (tipofact == 5){
					FRAparen.absTextBold(writer,"FACTURA ALMACEN",margeniz,pxfrombt,10);
				} else {
					FRAparen.absTextBold(writer,txtipnfc,margeniz,pxfrombt,10);
				}
			}
    	} catch (Exception e) {
    		
    	}
    	
    }
    
    public void pintaDetallesFRA() {
    	
    	Image image;
    	String imageUrl;
    	
    	try {
    		
	    	PdfPTable table = new PdfPTable(new float[] { 1, 7, 2, 1, 2});
			table.setTotalWidth(anctabla);
		
			FRAparen.insCabeceraTabla(table);
			
			Font fuente = new Font(FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(64, 64, 64));
			BaseColor bkcolor = new BaseColor(255, 255, 255);
		
			int numpagex = 1;
			int contxpag = 0;
			int ultfilsh = 0;
			int finfilax = 0;
		
		
			String cdestado = "V";
			Grid grLineas = recuperoLineas(cdestado);
			
			
			
			for (int i = 0; i < grLineas.rowCount(); i++){
								
				int iniciotab = initabla;
				
				if (numpagex == 1){
					//iniciotab = initabla - 165;
					iniciotab = initabla - (initabla - pxfrombt + 100);
				}
				
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
			    	FRAparen.absText(writer, "Pag. "+ writer.getCurrentPageNumber() +" / "+ pagetotal,anctabla,altlinea - 10,6);
			    	
			        documento.newPage();
			        FRAparen.insCabeceraTabla(table);
			        
				}
				
				double unit 	 = Double.valueOf(grLineas.getStringCell(i,"cantidad"));
				double importe   = Double.valueOf(grLineas.getStringCell(i,"precioun"));
				double totalln   = Double.valueOf(grLineas.getStringCell(i,"precioto"));
				double descuento = Double.valueOf(grLineas.getStringCell(i,"descuent"));
				
				if (!idtmpfra.equals("") && !idtmpfra.equals(grLineas.getStringCell(i,"idtmpfra"))) {
					System.out.println(" ATENCION!!! Existen mas de un idtmpfra en la factura. idtmpfra="+ idtmpfra +" vs grid "+ idtmpfra.equals("idtmpfra"));
				}
				
				idtmpfra = grLineas.getStringCell(i,"idtmpfra");
				
				//DEBIDO AL FORMATO
				descuento = descuento / 100;
				
				table.addCell(FRAparen.getCelda(formatUnit.format(unit) +" un.",fuente,bkcolor,"center"));
				table.addCell(FRAparen.getCelda(grLineas.getStringCell(i,"concepto"),fuente,bkcolor,"left"));
				table.addCell(FRAparen.getCelda(formatDivi.format(importe) + divisa,fuente,bkcolor,"right"));
				if (descuento == 0){
					table.addCell(FRAparen.getCelda("-"+"    ",fuente,bkcolor,"right"));
				} else {
					table.addCell(FRAparen.getCelda(formatPorc.format(descuento),fuente,bkcolor,"right"));
				}
				
				String strPreci = grLineas.getStringCell(i,"precioto");
				table.addCell(FRAparen.getCelda(formatDivi.format(Double.parseDouble(strPreci)) + divisa,fuente,bkcolor,"right"));

				impbasei += totalln;
				
				contxpag++;
				
			}
	
	
			//C�LCULO DE IMPUESTO
			impitbis = impbasei * porTaxes / 100;
			//C�LCULO DE TOTAL
			imptotal = impbasei + impitbis;
			
			BaseColor bkcolorCab = new BaseColor(200, 200, 200);
			
			Font fuenteTot = new Font(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(0, 0, 0));
			
			if (!cabecNFC.equals("COND")){
				
				if (porTaxes > 0){
					
					table.addCell(FRAparen.getCelda2(" ",fuente,bkcolor,"center",0,2));
					table.addCell(FRAparen.getCelda2("SUBTOTAL",fuenteTot,bkcolorCab,"center",1,2));
					table.addCell(FRAparen.getCelda(formatDivi.format(impbasei) + divisa,fuenteTot,bkcolor,"right"));
					
					table.addCell(FRAparen.getCelda2(" ",fuente,bkcolor,"center",0,2));
					table.addCell(FRAparen.getCelda2("ITBIS ("+ porTaxes +"%)",fuenteTot,bkcolorCab,"center",1,2));
					table.addCell(FRAparen.getCelda(formatDivi.format(impitbis) + divisa,fuenteTot,bkcolor,"right"));
				}
				
			}
			
			table.addCell(FRAparen.getCelda2(" ",fuente,bkcolor,"center",0,2));
			if (!cabecNFC.equals("COND")){
				table.addCell(FRAparen.getCelda2("TOTAL A PAGAR",fuenteTot,bkcolorCab,"center",1,2));
			} else {
				table.addCell(FRAparen.getCelda2("TOTAL",fuenteTot,bkcolorCab,"center",1,2));
			}
			
			table.addCell(FRAparen.getCelda(formatDivi.format(imptotal) + divisa,fuenteTot,bkcolor,"right"));
			
			if (numpagex > 1){
	        	documento.newPage();
	        }  else {
	        	initabla -= 190;
	        }
			
			lineResu = 250;
			lineResu = (initabla - 110) - (contxpag * 13);
			
	        PdfContentByte canvas = writer.getDirectContent();
	    	table.writeSelectedRows(ultfilsh, -1, margeniz, initabla, canvas);

	    	//Incluye Firma Proveedor
	    	FRAparen.absLineaFina(writer,margeniz,lineResu,174);
	    	FRAparen.absText(writer,"Firma Proveedor ",92,lineResu-10,8);
	    	
	    	FRAparen.absLineaFina(writer,382,lineResu,174);
	    	FRAparen.absText(writer,"Recibido Por ",449,lineResu-10,8);
	    	
	    	if (tipofact != 5) {
		    	//Incluye sello Proveedor
		    	if (tipologo.equals("1")){
			    	
					imageUrl = PropiedadesJLet.getInstance().getProperty("path.img.logos") + "mini-logomallproshop.png";
		
				    image = Image.getInstance(imageUrl);
				    image.setAbsolutePosition(margeniz, 52);
				    image.scaleAbsolute(40,20);
				    image.setAlignment(Image.LEFT | Image.TEXTWRAP);//Code 2
					documento.add(image);
			    	
			    	String trademar = "MallProShop es una marca comercial (trademark) de Izumba Dominicana S.R.L.";
			    	FRAparen.absText(writer,trademar,margeniz+40,60,6);
			    	
			    	imageUrl = PropiedadesJLet.getInstance().getProperty("path.img.logos") + "mini-logoizumba.png";
		
				    image = Image.getInstance(imageUrl);
				    image.setAbsolutePosition(margeniz+260, 55);
				    image.scaleAbsolute(40,20);
				    image.setAlignment(Image.LEFT | Image.TEXTWRAP);//Code 2
					documento.add(image);
					
		    	}
				
		    	if (tipologo.equals("1")){
					imageUrl = PropiedadesJLet.getInstance().getProperty("path.img.logos") + "firma-mallproshop.png";
					image = Image.getInstance(imageUrl);
				    image.setAbsolutePosition(75,lineResu-10);
				    image.scaleAbsolute(100,65);
				} else {
					imageUrl = PropiedadesJLet.getInstance().getProperty("path.img.logos") + "firma-izumba.png";
					image = Image.getInstance(imageUrl);
				    image.setAbsolutePosition(75,lineResu-10);
				    image.scaleAbsolute(100,44);
				}
			    
			    image.setAlignment(Image.LEFT | Image.TEXTWRAP);//Code 2
				documento.add(image);
    		}
	    	
	    	if(informda != null && !informda.equals("")){
	    		
	    		PdfPTable tableAd = new PdfPTable(1);
				tableAd.setTotalWidth(anctabla);
				tableAd.addCell(FRAparen.getCelda2("Referencia Pedido:",fuenteTot,bkcolor,"left",0,2));
				tableAd.addCell(FRAparen.getCelda2(informda,fuente,bkcolor,"left",0,2));
				PdfContentByte canvas2 = writer.getDirectContent();
		    	tableAd.writeSelectedRows(ultfilsh, -1, margeniz, lineResu-60, canvas2);
	    	
	    	}
    	} catch (Exception e) {
    		
    	}
			
    }
    
    public void pintaDatosBancarios(){
    	
    	try {
	    	
			
	    	if (!cabecNFC.equals("COND")){
	    		
	    		ListFormaPagoBDIn formPagoBDIn = new ListFormaPagoBDIn();
		    	formPagoBDIn.setValue("idemisor",emisclie);
				formPagoBDIn.setValue("idfrmpag",formpago);
				ListFormaPagoBD formPagoBD = new ListFormaPagoBD(formPagoBDIn);
		    	formPagoBD.setConnection(con);
		    	Grid gdFrmPag = formPagoBD.execSelect();
	    		
		    	if (gdFrmPag.rowCount() > 0){
			    	
		    		String mcefecti = gdFrmPag.getStringCell(0,"mcefecti");
		    		String mcdiasxx = gdFrmPag.getStringCell(0,"mcdiasxx");
		    		
			    	String datosBan = gdFrmPag.getStringCell(0,"txbancox");

			    	if (mcefecti.equals("N") && ((mcdiasxx.equals("N")) || (mcdiasxx.equals("")))){
			    		FRAparen.absText(writer,"Datos bancarios para efectuar pagos:",margeniz,140,8);
			    		datosBan += " "+ gdFrmPag.getStringCell(0,"txcuenta") +" "+ gdFrmPag.getStringCell(0,"txotrosx");
			    		FRAparen.absText(writer,datosBan,margeniz,130,8);
			    	} else {
			    		FRAparen.absTextBoldColor(writer, datosBan, margeniz, 140, 14, new BaseColor(175,175,175));
			    	}
		    	}
	    	}
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    	
    	
    	try {
	    	
    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() + "ERROR pintaDatosBancarios() "+ e.getMessage());
    	}
    	
    }
    
    public void pintaPiePagina(){

    	
    	Image image;
    	
    	try {
	    	String contmail = "info@izumbashop.com";
	    	
	    	//DATOS DE CONTACTO
	    	if (tipologo.equals("1")){
	    		contmail = "pedidos@mallproshop.com";
	    	}
	    	
	    	String conttfno = "8095495179 - 8097073324";
	    	String contwhat = "8097073324";
	    	String contblbe = "2A2572A2";
	    	FRAparen.absTextBold(writer,contmail,margeniz +  30,100,10);
	    	FRAparen.absTextBold(writer,conttfno,margeniz + 200,100,10);
	    	FRAparen.absTextBold(writer,contwhat,margeniz + 355,100,10);
	    	FRAparen.absTextBold(writer,contblbe,margeniz + 445,100,10);
	    	
	    	//IMAGENES DATOS DE CONTACTO
	    	String imgcolor = "-gris";
	    	String imgnmail = PropiedadesJLet.getInstance().getProperty("path.img.logos") + "mail"+ imgcolor +".png";
	    	String imgntfno = PropiedadesJLet.getInstance().getProperty("path.img.logos") + "tfno"+ imgcolor +".png";
	    	String imgnwhat = PropiedadesJLet.getInstance().getProperty("path.img.logos") + "whatsapp"+ imgcolor +".png";
	    	String imgnblbe = PropiedadesJLet.getInstance().getProperty("path.img.logos") + "bbpin"+ imgcolor +".png";
	    	
	    	image = Image.getInstance(imgnmail);
			image.setAbsolutePosition(margeniz, 90);
		    image.scaleAbsolute(25,25);
		    image.setAlignment(Image.LEFT | Image.TEXTWRAP);//Code 2
			documento.add(image);
	
	    	image = Image.getInstance(imgntfno);
			image.setAbsolutePosition(margeniz + 170, 92);
		    image.scaleAbsolute(20,20);
		    image.setAlignment(Image.LEFT | Image.TEXTWRAP);//Code 2
			documento.add(image);
	
	    	image = Image.getInstance(imgnwhat);
			image.setAbsolutePosition(margeniz + 325, 92);
		    image.scaleAbsolute(25,25);
		    image.setAlignment(Image.LEFT | Image.TEXTWRAP);//Code 2
			documento.add(image);
	
	    	image = Image.getInstance(imgnblbe);
			image.setAbsolutePosition(margeniz + 415, 92);
		    image.scaleAbsolute(25,25);
		    image.setAlignment(Image.LEFT | Image.TEXTWRAP);//Code 2
			documento.add(image);
	    	
			//Pie de Pagina
	    	int altlinea = 50;
	    	FRAparen.absLineaFina(writer, margeniz,altlinea,anctabla);
	    	FRAparen.absText(writer, "Pag. "+ writer.getCurrentPageNumber() +" / "+ pagetotal,anctabla,altlinea - 10,6);
			
    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() + "ERROR pintaPiePagina() "+ e.getMessage());
    	}
    	
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
			lineasBDIn.setValue("tpclient", tpclient);
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
    	
    	if (nUpdates != 1){
    		System.err.println(" ERROR GRAVE - No se ha actualizado el codigo de la facturacion");
    	} else {
			try {
				UpdLineasBDIn InsLinBDIn= new UpdLineasBDIn();
				InsLinBDIn.setValue("idclient",receclie);
				InsLinBDIn.setValue("tpclient",tpclient);
    			InsLinBDIn.setValue("idemisor",emisclie);
    			InsLinBDIn.setValue("cdoldest","V");
    			InsLinBDIn.setValue("cdnewest","F");
    			InsLinBDIn.setValue("idtmpfra",idtmpfra);
	    		UpdLineasBD InsLinBD= new UpdLineasBD(InsLinBDIn);
	    		InsLinBD.setConnection(con);
	    		int liUpdate = InsLinBD.execUpdate();
	    		System.out.println(" Se han actualizado "+ liUpdate +" lineas.");	    		    	
	    		
	    		
	    		UpdFacturaBDIn factBDIn = new UpdFacturaBDIn();
	    		factBDIn.setValue("idemisor",emisclie);
	    		factBDIn.setValue("idclient",receclie);
	    		factBDIn.setValue("tpclient",tpclient);
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
	    		factBDIn.setValue("idtmpfra",idtmpfra); //luis palacios
	    		factBDIn.setValue("cdestado","S");
	    		UpdFacturaBD factBD = new UpdFacturaBD(factBDIn);
	    		factBD.setConnection(con);
	    		int creaFact = factBD.execInsert();
	    		System.out.println(" Se ha creado "+ creaFact +" factura.");
	    		
	    		createIzuConnection();
	    		UpdFacturaWebBDIn factWebBDIn = new UpdFacturaWebBDIn();
	    		factWebBDIn.setValue("idorderx",idorderx);
	    		factWebBDIn.setValue("filecrea",namefile);
	    		UpdFacturaWebBD factWebBD = new UpdFacturaWebBD(factWebBDIn);
	    		factWebBD.setConnection(izucon);
	    		int factWeb = factWebBD.execUpdate();
	    		closeIzuConnection();
	    		System.out.println(" Se ha creado "+ factWeb +" factura en web izumbashop.");
	    		
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    }
        
    public String getFileCreated(){
    	return this.filecrea;    	
    }
    
    public Grid obtenerDatosClienteWeb(String idclient) {
    	
    	Grid gdCliWeb = null;
    	
    	try {
    		createIzuConnection();
    		ListClienteWebBDIn clieWebBDIn= new ListClienteWebBDIn();
    		//JEJ de momento para una sola tienda
			clieWebBDIn.setValue("idshopxx","1");
	    	clieWebBDIn.setValue("idcustom",idclient);
	    	ListClienteWebBD clieWebBD = new ListClienteWebBD(clieWebBDIn);
	    	clieWebBD.setConnection(izucon);
			gdCliWeb = clieWebBD.execSelect();
			closeIzuConnection();
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return gdCliWeb;
    }
    
    public ArrayList<String> obtenerCabeceraClienteWeb(Grid datosEmi) {
		
		ArrayList<String> lineaEmi = new ArrayList<String>();
		
		try {
			lineaEmi.add("("+ datosEmi.getStringCell(0, "idclient") +") "+ datosEmi.getStringCell(0, "txmailxx"));
			
			if ((datosEmi.getStringCell(0, "rzsocial") != null) && (!datosEmi.getStringCell(0, "idclient").equals(""))){
				lineaEmi.add(datosEmi.getStringCell(0, "rzsocial"));
			} else {
				lineaEmi.add(datosEmi.getStringCell(0, "txnombre") +" "+ lineaEmi.add(datosEmi.getStringCell(0, "txapelli")));
			}
			
			lineaEmi.add(datosEmi.getStringCell(0, "direcci1") +" "+ datosEmi.getStringCell(0, "direcci2"));
			lineaEmi.add(datosEmi.getStringCell(0, "txciudad") +" "+ datosEmi.getStringCell(0, "cdpostal"));
			lineaEmi.add(datosEmi.getStringCell(0, "telefono"));
		} catch (Exception e) {
			System.err.println(this.getClass().getName() +" ERROR - Al obtener la cabecera del Emisor. "+ e.getMessage());
		}
		
		return lineaEmi;
		
	}
    
    public void createIzuConnection(){
    	
   		String usuariox = "";
		String password = "";
		String bdschema = "";
		String bdhostxx = "";

		usuariox = PropiedadesJLet.getInstance().getProperty("izum.bd.usuariox");
		password = PropiedadesJLet.getInstance().getProperty("izum.bd.password");
		bdschema = PropiedadesJLet.getInstance().getProperty("izum.bd.bdschema");
		bdhostxx = PropiedadesJLet.getInstance().getProperty("izum.bd.bdhostxx");
		
		usuariox = "izumba_jlet";
		password = "^0xDh15f";
		bdschema = "izumba";
		bdhostxx = "85.214.140.64";
		
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser(usuariox);
		dataSource.setPassword(password);
		dataSource.setDatabaseName(bdschema);
		dataSource.setServerName(bdhostxx);
		dataSource.setPort(3306);
	
		try {
			izucon = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		System.out.println("[OPEN] - Abro Conexion Izumba SHOP");
		
	}
   	
   	public void closeIzuConnection(){
	   		
		try {
			izucon.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

   	}
    
    
}
	
