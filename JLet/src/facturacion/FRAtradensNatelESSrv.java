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
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import comercio.bd.UpdStockBD;
import comercio.bd.UpdStockBDIn;
import common.Divisa;
import contabilidad.bd.UpdateApunteBD;
import contabilidad.bd.UpdateApunteBDIn;
import factura.TempFacturaSrv;
import factura.bd.ListFormaPagoBD;
import factura.bd.ListFormaPagoBDIn;
import factura.bd.ListLineasBD;
import factura.bd.ListLineasBDIn;
import factura.bd.MaxFacturaBD;
import factura.bd.MaxFacturaBDIn;
import facturacion.bd.FacturaWebBD;
import facturacion.bd.FacturaWebBDIn;
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


public class FRAtradensNatelESSrv extends Service {
	
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
	
	int initabla  = 780;
	int anctabla  = 520;
	
	int lineResu  = 250;
	
	int margeniz  = 35;
	int inmargde  = 415;	
	
	//DATOS DE LA FACTURA
	double porTaxes = 21;
	String divisa = " &euro;";
	String divisaSim = " �";
	String divisacost = "EUR";
	
	//DATOS REGISTRO MERCANTIL
	String registro = "Madrid";
	String rmtomoxx = "22.676";
	String rmlibrox = "0";
	String rmfoliox = "206";
	String rmhojaxx = "M-405617";
	
	//PAR�METROS A RELLENAR EN LA FACTURA
	String cabecNFC = "FACT";
	String numerNFC = "00000001";
	String txtipnfc = "FACTURA CON VALOR FISCAL";
	String imglogox = "";
	String idtmpfra = "";
	String idunicox = "";
	double impbasei = 0;
	double impitbis = 0;
	double imptotal = 0;
	
	
	//FORMATOS NUM�RICOS
	DecimalFormat formatUnit = new DecimalFormat("###,##0");
	DecimalFormat formatDivi = new DecimalFormat("###,##0.00");
	DecimalFormat formatPorc = new DecimalFormat("##0.##%");
	DecimalFormat formatCNFC = new DecimalFormat("0000");		

	//PARAMETROS DE ENTRADA
	String emisclie = "";
	String receclie = "";
	String tpclient = "";
	String aniofact = "";	
	String fhfactur = "";
	String formpago = "";
	String condpago = "";
	String catefact = "";
	String regenera = "";
	String cdfactur = "";
	String idlineas = "";
	String marcados = "";
	String informda = "";
	String idfacreg = "";
	String factasoc = "";
	String listimei = "";
	String codvende = "";
	String albawebx = "";
	String facalbar = "";
	String estaalba = "P";
	String idorderx = "";
	String devoluci = "";
	String idfactur = "";
	String agrupada = "";
	String mcpagado = "";
	String txrazons = "";
	String cuentapu = "";
	String cduserid = "1";
	String isalbara = "";
	int tipofact = 0;
	int mcagrupa = 0;
	
	//PAR�METROS DE SALIDA
	String namefile = "";
	String filecrea = "";
	
	//OTROS PAR�METROS
	Grid datosEmi = new Grid();
	Grid datosRec = new Grid();
	Grid gdUnicox = new Grid();
	
	
	Font font			= FontFactory.getFont(BaseFont.HELVETICA, 10);
    Font fontbold 		= FontFactory.getFont(BaseFont.HELVETICA, 10, Font.BOLD);
    Font fontboldVCS	= FontFactory.getFont(BaseFont.HELVETICA, 10, Font.BOLD,new BaseColor(13,71,117));
    Font fontboldwhite 	= FontFactory.getFont(BaseFont.HELVETICA, 10, Font.BOLD,new BaseColor(255,255,255));
    
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
			input.addVariable("regenera");
			input.addVariable("cdfactur");
			input.addVariable("idlineas");
			input.addVariable("marcados");
			input.addVariable("informda");
			input.addVariable("idfactur");
			input.addVariable("factasoc");
			input.addVariable("listimei");
			input.addVariable("codvende");
			input.addVariable("albawebx");
			input.addVariable("facalbar");
			input.addVariable("idorderx");
			input.addVariable("porcrete");
			input.addVariable("mcpagado");
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
        	aniofact = input.getStringValue("aniofact");	
        	fhfactur = input.getStringValue("fhfactur");
        	tipofact = Integer.parseInt(input.getStringValue("tipofact"));
        	mcagrupa = Integer.parseInt(input.getStringValue("mcagrupa"));
        	formpago = input.getStringValue("formpago");
        	condpago = input.getStringValue("condpago");
        	catefact = input.getStringValue("catefact");
        	regenera = input.getStringValue("regenera");
        	idlineas = input.getStringValue("idlineas");
        	cdfactur = input.getStringValue("cdfactur");
        	marcados = input.getStringValue("marcados");
        	informda = input.getStringValue("informda");
        	idfacreg = input.getStringValue("idfactur");
        	factasoc = input.getStringValue("factasoc"); 
        	listimei = input.getStringValue("listimei");  
        	codvende = input.getStringValue("codvende");
        	albawebx = input.getStringValue("albawebx");
        	facalbar = input.getStringValue("facalbar");
        	idorderx = input.getStringValue("idorderx");
        	devoluci = input.getStringValue("devoluci");
        	idfactur = input.getStringValue("idfactur");
        	mcpagado = input.getStringValue("mcpagado");
        	
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
    		
	    	
	    	Rectangle pageSize = new Rectangle(PageSize.A4);
	    	pageSize.setBackgroundColor(new BaseColor(255, 255, 255));
	    	pageSize.rotate();
	        documento = new Document(pageSize,20, 20, 30, 30);
	        
	        rellenaDatosTipoFactura(tipofact);
	        
	        namefile = "TRAD_"+aniofact +"_"+cabecNFC +"_"+ numerNFC +".pdf";
	        String folderin = PropiedadesJLet.getInstance().getProperty("path.gen.invoice") + "emisor_"+ emisclie + "/"; 
	        File infolder = new File(folderin);
	        
	        if (!infolder.isDirectory()){
	        	infolder.mkdirs();
	        }
	        
	       
	        filecrea = folderin + namefile;
	    	writer = PdfWriter.getInstance(documento, new FileOutputStream(filecrea));
	        documento.open();
	        pxfrombt = 700;
			
	        pintaCabecera();
	        
	       
	        
 	        pintaFecha();
	        
	        pintaDatosClienteEmisor();
	        
	        pintaOtrosDatos();
	        
	        pintaDetallesFRA();
	        
	        pintaDatosBancarios();
	        
	        pintaPiePagina();
	        if(mcagrupa == 1){
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
	        //	rellenaInforAdicional(informda);
        		realizarActualizaciones();
        		
        		
        		if(devoluci!= null && devoluci.equals("S")){
		        	Grid gdImeis = FRAparen.selecImeis(idtmpfra, marcados);
		        	cambiaStock(gdImeis,"STOCK");
		        	FRAparen.actualizaLineasFactura(receclie,tpclient,emisclie, marcados);
		        	FRAparen.marcaDevolucion(idfactur);
        		}
	        }else{
				
				recalCulaFactu(idfacreg);
			}
	        
	        
	        if(albawebx != null && albawebx.equals("S")){
	        	creaFacturaWeb();
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
			e.printStackTrace();
			System.err.println(this.getClass().getName() +" ERROR pintaCabecera() - "+ e.getMessage());
		}
    	
    }
    
    public void pintaFecha(){
    	
    	//INCLUYE EL CODIGO DE FACTURA
    		
	    Chunk chunkNom 	= null;
	    
	    Phrase contcell = null;
	    
	    PdfPCell cell 	= null;
    	
    	try {
	    	
    		
    		PdfPTable table = new PdfPTable(new float[] { 0.5f, 0.5f, 1f});    		
    		table.setWidthPercentage(40);
    		//table.setTotalWidth(54f);
    		

            //----------------------		
    	    //Linea 1
            //----------------------
    	    //Fecha (1)
    	    chunkNom = new Chunk("Fecha:", fontboldVCS);        	            	    
    	    
    	    contcell = new Phrase(chunkNom);
    	    
    		cell = new PdfPCell(contcell);
            cell.setBackgroundColor(new BaseColor(221, 225, 234));
            cell.setBorder(1);
            cell.setBorderColor(new BaseColor(255,255,255));
            table.addCell(cell);
            
            chunkNom = new Chunk(FRAparen.fechaNormal(fhfactur), fontboldVCS);
            contcell = new Phrase(chunkNom);
    	    
    		cell = new PdfPCell(contcell);
            cell.setBorder(1);
            cell.setBorderColor(new BaseColor(255,255,255));
            table.addCell(cell);
            
            chunkNom = new Chunk("", fontboldVCS);
            contcell = new Phrase(chunkNom);
    		cell = new PdfPCell(contcell);
    		cell.setBorder(0);
    		table.addCell(cell);
    		
            //----------------------		
    	    //Linea 2
            //----------------------
    	    //Fecha (6)
    	    chunkNom = new Chunk("N/FACT.", fontboldVCS);        	            	    
    	    
    	    contcell = new Phrase(chunkNom);
    	    
    		cell = new PdfPCell(contcell);
            cell.setBackgroundColor(new BaseColor(221, 225, 234));
            cell.setBorder(1);
            cell.setBorderColor(new BaseColor(255,255,255));
            table.addCell(cell);
            
            chunkNom = new Chunk(cabecNFC+numerNFC, fontboldVCS);
            contcell = new Phrase(chunkNom);
    	    
    		cell = new PdfPCell(contcell);
            cell.setBorder(1);
            cell.setBorderColor(new BaseColor(255,255,255));
            table.addCell(cell);
            
            chunkNom = new Chunk("", fontbold);
            contcell = new Phrase(chunkNom);
    		cell = new PdfPCell(contcell);
    		cell.setBorder(0);
    		table.addCell(cell);
            

            table.setHorizontalAlignment(Element.ALIGN_RIGHT);
            documento.add(table);
			
    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() +" ERROR pintaFecha() - "+ e.getMessage());
    	}
    	
    }
    
    public void pintaDatosClienteEmisor(){
    	
    	try {
    		
    		//Altura de los datos del receptor
			int altRecep = pxfrombt;
    		
    		datosEmi = FRAparen.obtenerDatosCliente(emisclie,"0");
			
			ArrayList<String> lineaEmi = new ArrayList<String>();
			lineaEmi = FRAparen.obtenerCabeceraCliente(datosEmi);
			
			
			for (int i = 0; i < lineaEmi.size(); i++){
				String contLine = lineaEmi.get(i);
				if ((contLine != null) && (!"".equals(contLine))){
					FRAparen.absTextBoldColor(writer,contLine,margeniz,pxfrombt,8,new BaseColor(13,71,117));
					pxfrombt-=15;
				}
			}
			
			if(albawebx != null && albawebx.equals("S")){
				datosRec = obtenerDatosClienteWeb(receclie);
			}else{
				datosRec = FRAparen.obtenerDatosCliente("",receclie); ////emisclie
			}
			
			
			
			
			
			//datosRec = FRAparen.obtenerDatosCliente(emisclie,receclie);
			
			ArrayList<String> lineaRec = new ArrayList<String>();
			
			if(albawebx != null && albawebx.equals("S")){
				lineaRec = obtenerCabeceraClienteWeb(datosRec);
			}else{
				lineaRec =FRAparen.obtenerCabeceraCliente(datosRec); ////emisclie
			}
			
			
			
			for (int i = 0; i < lineaRec.size(); i++){
				
				String contLine = lineaRec.get(i);
				if ((contLine != null) && (!"".equals(contLine))){
					
					if( i == 0){
						
					}
					
					FRAparen.absTextColor(writer,contLine,margeniz+300,altRecep,8,new BaseColor(13,71,117));
					altRecep-=15;
				}
				
			}
			txrazons = lineaRec.get(0);
			
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
				FRAparen.absTextBoldColor(writer,txtipnfc,margeniz,pxfrombt,10,new BaseColor(13,71,117));
			}
	    	
	    	if (factasoc != null && !factasoc.equals("")){
	    		FRAparen.absTextBoldColor(writer,"  ("+ factasoc +")",margeniz+120,pxfrombt,10,new BaseColor(13,71,117));
    		}
	    	
	    	FRAparen.absTextBoldColor(writer,txtipnfc,margeniz,pxfrombt,10,new BaseColor(13,71,117));
	    	
	    	
    	} catch (Exception e) {
    		
    	}
    	
    }
    
    public void pintaDetallesFRA() {
    	
    	try {
    		
	    	PdfPTable table = new PdfPTable(new float[] { 1, 7, 2, 1, 2});
			table.setTotalWidth(anctabla);
		
			insCabeceraTabla(table);
			
			Font fuente = new Font(FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(13,71,117));
			BaseColor bkcolor = new BaseColor(245,246,250);
		
			int numpagex = 1;
			int contxpag = 0;
			int ultfilsh = 0;
			int finfilax = 0;
			
			String cdestado = "";
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
				String imeidesg = "";
				double precioto = 0;
				
			
			for (int i = 0; i < grLineas.rowCount(); i++){
								
				int iniciotab = initabla;
				
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
					//imeidesg =  grLineas.getStringCell(i,"idunicox");
							
					
				}
				
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
				
				double unit 	 = Double.valueOf(cantidad);
				double importe   = Double.valueOf(precioun);
				double totalln   = Double.valueOf(precioto);
				double descuento = Double.valueOf(descuent);
				/*
				double unit 	 = Double.valueOf(grLineas.getStringCell(i,"cantidad"));
				double importe   = Double.valueOf(grLineas.getStringCell(i,"precioun"));
				double totalln   = Double.valueOf(grLineas.getStringCell(i,"precioto"));
				double descuento = Double.valueOf(grLineas.getStringCell(i,"descuent"));*/
				
				if (!idtmpfra.equals("") && !idtmpfra.equals(grLineas.getStringCell(i,idtmpfra))) {
					System.out.println(" ATENCION!!! Existen mas de un idtmpfra en la factura. idtmpfra="+ idtmpfra +" vs grid "+ idtmpfra.equals("idtmpfra"));
				}
				
				idtmpfra = grLineas.getStringCell(i,"idtmpfra");
				idunicox = grLineas.getStringCell(i,"idunicox");
				
				if(tipofact != 0){
					actualizaEstadoAlba("F");
				}
				
				//DEBIDO AL FORMATO
				descuento = descuento / 100;
				
				table.addCell(FRAparen.getCelda(formatUnit.format(unit) +" un.",fuente,bkcolor,"center")).setBorderColor(new BaseColor(255,255,255));
				table.addCell(FRAparen.getCelda(concepto,fuente,bkcolor,"left")).setBorderColor(new BaseColor(255,255,255));
				table.addCell(FRAparen.getCelda(formatDivi.format(importe) + divisaSim,fuente,bkcolor,"right")).setBorderColor(new BaseColor(255,255,255));
				if (descuento == 0){
					table.addCell(FRAparen.getCelda("-"+"    ",fuente,bkcolor,"right")).setBorderColor(new BaseColor(255,255,255));
				} else {
					table.addCell(FRAparen.getCelda(formatPorc.format(descuento),fuente,bkcolor,"right")).setBorderColor(new BaseColor(255,255,255));
				}
				
				//String strPreci = grLineas.getStringCell(i,"precioto");
				table.addCell(FRAparen.getCelda(formatDivi.format(Double.valueOf(precioto)) + divisaSim,fuente,bkcolor,"right")).setBorderColor(new BaseColor(255,255,255));

				impbasei += totalln;
				
				contxpag++;
				/*
				if(codprodu !=null && codprodu.equals("IG00004")){
					FRAparen.actualizaCostes(imeidesg,emisclie,"IGIVA00001",pricevnt,divisa);
					
				}*/
				
			}
	
	
			//C�LCULO DE IMPUESTO
			impitbis = impbasei * porTaxes / 100;
			//C�LCULO DE TOTAL
			imptotal = impbasei + impitbis;
			
			BaseColor bkcolorCab = new BaseColor(221, 225, 234);
			
			Font fuenteTot = new Font(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(13, 71, 117));
			
			if (!cabecNFC.equals("COND")){
				
				table.addCell(FRAparen.getCelda2(" ",fuente,new BaseColor(255,255,255),"center",0,2)).setBorderColor(new BaseColor(255,255,255));
				table.addCell(FRAparen.getCelda2("SUBTOTAL",fuenteTot,bkcolorCab,"center",1,2)).setBorderColor(new BaseColor(255,255,255));
				table.addCell(FRAparen.getCelda(formatDivi.format(impbasei) + divisaSim,fuenteTot,bkcolor,"right")).setBorderColor(new BaseColor(255,255,255));
				
				table.addCell(FRAparen.getCelda2(" ",fuente,new BaseColor(255,255,255),"center",0,2)).setBorderColor(new BaseColor(255,255,255));
				table.addCell(FRAparen.getCelda2("IVA ("+ porTaxes +"%)",fuenteTot,bkcolorCab,"center",1,2)).setBorderColor(new BaseColor(255,255,255));
				table.addCell(FRAparen.getCelda(formatDivi.format(impitbis) + divisaSim,fuenteTot,bkcolor,"right")).setBorderColor(new BaseColor(255,255,255));
				
			}
			
			table.addCell(FRAparen.getCelda2(" ",fuente,new BaseColor(255,255,255),"center",0,2)).setBorderColor(new BaseColor(255,255,255));
			table.addCell(FRAparen.getCelda2("TOTAL",fuenteTot,bkcolorCab,"center",1,2)).setBorderColor(new BaseColor(255,255,255));
			
			table.addCell(FRAparen.getCelda(formatDivi.format(imptotal) + divisaSim,fuenteTot,bkcolor,"right")).setBorderColor(new BaseColor(255,255,255));
			
			if (numpagex > 1){
	        	documento.newPage();
	        }  else {
	        	initabla -= 190;
	        }
			
			lineResu = 250;
			lineResu = (initabla - 110) - (contxpag * 13);
			
	        PdfContentByte canvas = writer.getDirectContent();
	    	table.writeSelectedRows(ultfilsh, -1, margeniz, initabla, canvas);
	    	
	    	if(informda != null && !informda.equals("")  && (mcagrupa == 0)){
	    		
	    		PdfPTable tableAd = new PdfPTable(1);
				tableAd.setTotalWidth(anctabla);
				tableAd.addCell(FRAparen.getCelda2("Informaci�n adicional:",fuenteTot,bkcolor,"left",0,2));
				tableAd.addCell(FRAparen.getCelda2(informda,fuente,bkcolor,"left",0,2));
				PdfContentByte canvas2 = writer.getDirectContent();
		    	tableAd.writeSelectedRows(ultfilsh, -1, margeniz, lineResu-60, canvas2);
	    	}
	    	
		    
    	} catch (Exception e) {
    		
    	}
			
    }
    
    public void pintaDatosBancarios(){
    	try {
	    	if (tipofact != 0 && tipofact != 4){
		    	//DATOS BANCARIOS
		    //	String datosBan = "Forma de Pago: Transferencia Bancaria";
		    //	FRAparen.absTextBoldColor(writer,datosBan,margeniz + 200,180,10,new BaseColor(13,71,117));
		    	//datosBan = "IBAN ES69 0049 1015 2821 1075 9261";
		    	//FRAparen.absTextColor(writer,datosBan,margeniz + 200,160,10,new BaseColor(13,71,117));
	    	
	    	
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
		    	String numcuent = gdFrmPag.getStringCell(0,"txcuenta");
		    	
		    	cuentapu = gdFrmPag.getStringCell(0,"cuentapu");

		    	if (mcefecti.equals("N") && ((mcdiasxx.equals("N")) || (mcdiasxx.equals("")))){
		    		FRAparen.absText(writer,"Datos bancarios para efectuar pagos:",margeniz,140,8);
		    		datosBan += " "+ gdFrmPag.getStringCell(0,"txcuenta") +" "+ gdFrmPag.getStringCell(0,"txotrosx");
		    		FRAparen.absText(writer,datosBan,margeniz,130,8);
		    	} else {
		    		FRAparen.absTextBoldColor(writer,datosBan,margeniz + 200,180,10,new BaseColor(175,175,175));
		    		FRAparen.absTextColor(writer,numcuent,margeniz + 200,160,10,new BaseColor(175,175,175));
		    	}
	    	}
	    }
	    	
	    	
    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() + "ERROR pintaDatosBancarios() "+ e.getMessage());
    	}
    	
    }
    
    public void pintaPiePagina(){

    	Image image;
    	String imageUrl;
    	int altlinea = 50;
    	
    	try {
	    	
    		imageUrl = PropiedadesJLet.getInstance().getProperty("path.img.logoinvoice") + "mini-logoizumba.png";
    		
		    image = Image.getInstance(imageUrl);
		    image.setAbsolutePosition(margeniz, altlinea + 26);
		    image.scaleAbsolute(40,20);
		    image.setAlignment(Image.LEFT | Image.TEXTWRAP);//Code 2
			documento.add(image);
	    	
	    	String trademar = "Izumbashop es una marca comercial (trademark) de Vetusta Global Services S.L.";
	    	FRAparen.absTextColor(writer,trademar,margeniz + 50,altlinea + 32,6,new BaseColor(13,71,117));
    		
    		
    		FRAparen.absTextColor(writer, "VETUSTA GLOBAL SERVICES S.L. - Inscrito en el registro mercantil de "+ registro +", libro "+ rmlibrox +", tomo "+ rmtomoxx +", folio "+ rmfoliox +", hoja "+ rmhojaxx,margeniz,altlinea + 20,7,new BaseColor(13,71,117));
    	
    		//Pie de Pagina
	    	FRAparen.absLineaFina(writer, margeniz,altlinea,anctabla);
	    	FRAparen.absTextColor(writer, "Pag. "+ writer.getCurrentPageNumber() +" / "+ pagetotal,anctabla,altlinea - 10,6,new BaseColor(13,71,117));
			
    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() + "ERROR pintaPiePagina() "+ e.getMessage());
    	}
    	
    }
    
    public void insCabeceraTabla (PdfPTable tabla){
    	
    	Font fuenteCab = new Font(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(13,71,117));
		BaseColor bkcolorCab = new BaseColor(221, 225, 234);
		
		tabla.addCell(FRAparen.getCelda("CANT. ",fuenteCab,bkcolorCab,"center")).setBorderColor(new BaseColor(255,255,255));
		tabla.addCell(FRAparen.getCelda("CONCEPTO",fuenteCab,bkcolorCab,"center")).setBorderColor(new BaseColor(255,255,255));
		tabla.addCell(FRAparen.getCelda("PRECIO/UN",fuenteCab,bkcolorCab,"center")).setBorderColor(new BaseColor(255,255,255));
		tabla.addCell(FRAparen.getCelda("% DTO",fuenteCab,bkcolorCab,"center")).setBorderColor(new BaseColor(255,255,255));
		tabla.addCell(FRAparen.getCelda("TOTAL",fuenteCab,bkcolorCab,"center")).setBorderColor(new BaseColor(255,255,255));
		
    }
    
//------------------------------------------ END PINTAR DATOS -----------------------------    

    
    public void rellenaDatosTipoFactura(int tipofact){
		
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
		    	isalbara = grTpFact.getStringCell(0, "isalbara");
	    	}
	    	
	    	
    	} catch (Exception e) {
    		System.err.println("ERROR al obtener los datos del TIPO de FACTURA. "+ e.getMessage());
    	}
    	
	}
    
 public void rellenaInforAdicional(String informadi){
	 
	 try{
		
	 		FRAparen.absTextColor(writer,"Informaci�n adicional:",margeniz,270,9,new BaseColor(13,71,117));
	   		
	   		FRAparen.absTextColor(writer,informadi,margeniz,255,8,new BaseColor(13,71,117));
	   	    	
	    	
    	} catch (Exception e) {
    		System.err.println("ERROR al rellenar informacion adicional. "+ e.getMessage());
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
 			  
 		 }else{// si es una generaci�n nueva.
 			 
 			 if(tipofact == 0){
 				 estaalba = "P";
 			 }else{
 				 estaalba = "N";
 			 }
 			 
 			cdoldest = "V";
 			 
 		 }
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
    			InsLinBDIn.setValue("cdoldest","V");
    			InsLinBDIn.setValue("cdnewest","F");
    			InsLinBDIn.setValue("idtmpfra",idtmpfra);
    			InsLinBDIn.setValue("idtmpfra",idtmpfra);
    			InsLinBDIn.setValue("estaalba",estaalba);
    			InsLinBDIn.setValue("marcados",marcados);
    			
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
	    		factBDIn.setValue("factasoc",factasoc);
	    		factBDIn.setValue("mcagrupa",agrupada);
	    		factBDIn.setValue("mcpagado",mcpagado);
	    		
	    		factBDIn.setValue("cdestado","S");
	    		UpdFacturaBD factBD = new UpdFacturaBD(factBDIn);
	    		factBD.setConnection(con);
	    		int creaFact = factBD.execInsert();
	    		System.out.println(" Se ha creado "+ creaFact +" factura.");
	    		
	    		String idfactura = FRAparen.idUltimaFactu();
	    		
	    		/*if (mcpagado !=null && mcpagado.equals("S") && !cuentapu.equals("0") && isalbara.equals("N") && (devoluci== null || !devoluci.equals("N"))){
	    			
	    			FRAparen.insertaApunte(emisclie, cuentapu, imptotal, fhfactur, cabecNFC +" "+ numerNFC,idfactura);
	    			
	    		}*/
	    		
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    }
    
    
    public void cambiaStock(Grid gdLineasImei, String cdestado){ // si es devolucion
    	
		String imeicode ="";
		String idfactur = "";
		String pricevnt = "";
		double prusdvnt = 0;
		double iva =0;
		double preciove = 0;
		
		
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
				prusdvnt = fixingUSD(pricevnt);
				
				if(!imeicode.equals("")){
					try {
						UpdStockBDIn devStockBDIn= new UpdStockBDIn();
						devStockBDIn.setValue("idemisor",emisclie);
						devStockBDIn.setValue("imeicode",imeicode);
						devStockBDIn.setValue("cdestado",cdestado);
						devStockBDIn.setValue("idfactur",idfactur);
						devStockBDIn.setValue("idclient",receclie);
						devStockBDIn.setValue("pricevnt",pricevnt);
						devStockBDIn.setValue("tpclient",tpclient);
						devStockBDIn.setValue("prusdvnt",prusdvnt);
						devStockBDIn.setValue("divisvnt",divisa);
				    	UpdStockBD devStockBD = new UpdStockBD(devStockBDIn);
				    	devStockBD.setConnection(con);
						int liInsert = devStockBD.execUpdate();
						
						iva = Double.parseDouble(pricevnt) * (porTaxes /100);
						iva = Math.rint(iva*100)/100;
						preciove = Math.rint(Double.parseDouble(pricevnt)*100)/100; 
					
						FRAparen.generaDetalleDesglose(imeicode, emisclie, receclie, txrazons, cabecNFC+numerNFC, FRAparen.fechaNormal(fhfactur));
						FRAparen.actualizaCostes(imeicode,emisclie,"IG00001",String.valueOf(preciove),divisacost);
						FRAparen.actualizaCostes(imeicode,emisclie,"IGTOTAL",String.valueOf(preciove),divisacost);
						FRAparen.actualizaCostes(imeicode,emisclie,"IGIVA00001",String.valueOf(iva),divisacost);
						FRAparen.actualizaCostes(imeicode,emisclie,"IGIVATOTAL",String.valueOf(iva),divisacost);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}	
		}	
			
}
    
    public void pintaAnexo(){
    	
   	 documento.newPage();
   	 pagetotal++;
   	 try{
   		Image image;
   		Font fuente = new Font(FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(13,71,117));
		BaseColor bkcolor = new BaseColor(245,246,250);
		
   		//String imageUrl = PropiedadesJLet.getInstance().getProperty("path.img.logoinvoice") + "gtp100.jpg";

			/*image = Image.getInstance(imageUrl);
		    image.setAbsolutePosition((documento.getPageSize().getWidth() / 2) - 151, 750);
		    image.scaleAbsolute(323,323);*/
		  
   	 
   		 PdfPTable table = new PdfPTable(new float[] { 1});
   		 table.setTotalWidth(620);
 
   		 table.addCell(FRAparen.getCelda(listimei.replaceAll(";", "         -        "),fuente,bkcolor,"left"));
   		 documento.add(table);
   	 }catch(Exception ex){
   		 System.out.println("Error crear anexo nueva pagina");
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
    
    
  
    
 public void recalCulaFactu( String idfactur) {
    	
    	try{
	    	UpdFacturaBDIn factBDIn = new UpdFacturaBDIn();
	    	factBDIn.setValue("idfactur",idfactur);
			factBDIn.setValue("baseimpo",impbasei);
			factBDIn.setValue("imptaxes",impitbis);
			factBDIn.setValue("imptotal",imptotal);
			factBDIn.setValue("filecrea",namefile);
			
			
			UpdFacturaBD factBD = new UpdFacturaBD(factBDIn);
    		factBD.setConnection(con);
    		int creaFact = factBD.execUpdate();
    		System.out.println(" Se ha Actualizado "+ creaFact +" factura.");
			
    	   }catch(Exception ex){
    		   System.out.println("Error al recalcular Factura");
    	   }
    }
        
    public String getFileCreated(){
    	return this.filecrea;    	
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
   	
   	public void creaFacturaWeb(){
   		
   	try{
   		
   		createIzuConnection();
   		FacturaWebBDIn factWebBDIn = new FacturaWebBDIn();
		factWebBDIn.setValue("idorderx",idorderx);
		factWebBDIn.setValue("filecrea",namefile);
		FacturaWebBD factWebBD = new FacturaWebBD(factWebBDIn);
		factWebBD.setConnection(izucon);
		int factWeb = factWebBD.execUpdate();
		closeIzuConnection();
		System.out.println(" Se ha creado "+ factWeb +" factura en web izumbashop.");
		
   	   }catch(Exception ex){
   		   System.out.println("Error al crear factura Web desde Albaran Web");
   	   }
   	}
   	
    public Grid selecImeis(){
    	
    	Grid gdLineasBD = null;
    	
    	try {
    		ListLineasBDIn lineasBDIn = new ListLineasBDIn();
    		lineasBDIn.setValue("idtmpfra",idtmpfra);
    		lineasBDIn.setValue("marcados",marcados);
    		lineasBDIn.setValue("tpclient","-1");
			ListLineasBD lineasBD = new ListLineasBD(lineasBDIn);
			lineasBD.setConnection(con);
			gdLineasBD = lineasBD.execSelect();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		return gdLineasBD;
	
    }
   	
  public Grid obtenerDatosClienteWeb(String idclient) {
    	
    	Grid gdCliWeb = null;
    	
    	try {
    		createIzuConnection();
    		ListClienteWebBDIn clieWebBDIn= new ListClienteWebBDIn();
    		//JEJ de momento para una sola tienda
			clieWebBDIn.setValue("idshopxx","3");
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
  
  public double fixingUSD( String pricevnt){
	//Obtengo el fixing del CHF
	  String stfixing ="";
	  double prusdvnt = 0;
	  double dbfixing = 0;
	  double preciovn = 0;
  	try {
       	Divisa divisa = new Divisa();
       	divisa.setConnection(con);

       	stfixing = divisa.getFixingUSD("EUR");
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
	
