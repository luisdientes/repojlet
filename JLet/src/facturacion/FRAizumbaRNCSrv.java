package facturacion;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import stock.bd.ListStockBD;
import stock.bd.ListStockBDIn;
import stock.bd.UpdEstadoProBD;
import stock.bd.UpdEstadoProBDIn;
import stock.bd.UpdStockWebBD;
import stock.bd.UpdStockWebBDIn;
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
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import comercio.bd.UpdStockBD;
import comercio.bd.UpdStockBDIn;
import common.Divisa;
import common.varstatic.VariablesStatic;
import factura.TempFacturaSrv;
import factura.bd.ListFormaPagoBD;
import factura.bd.ListFormaPagoBDIn;
import factura.bd.ListLineasBD;
import factura.bd.ListLineasBDIn;
import factura.bd.MaxFacturaBD;
import factura.bd.MaxFacturaBDIn;
import factura.bd.UpdDevFacturaBD;
import factura.bd.UpdDevFacturaBDIn;
import factura.bd.UpdPagaFacturaBD;
import factura.bd.UpdPagaFacturaBDIn;
import facturacion.bd.ListLineasTmpBD;
import facturacion.bd.ListLineasTmpBDIn;
import facturacion.bd.ListTipoFacturaBD;
import facturacion.bd.ListTipoFacturaBDIn;
import facturacion.bd.UpdFacturaBD;
import facturacion.bd.UpdFacturaBDIn;
import facturacion.bd.UpdLineasBD;
import facturacion.bd.UpdLineasBDIn;
import facturacion.parents.FacturaPDF;


public class FRAizumbaRNCSrv extends Service {
	
	//HEREDA METODOS
	FacturaPDF FRAparen = new FacturaPDF();
	
	Document documento = null;
	PdfWriter writer;
	
	//DATOS DEL DOCUMENTO
	int pagetotal = 1;
	int filasxpag = 30;
	int filasprim = 30;
	int nfilascon = 30;
	int nfilasdet = 4;
	int pxfrombt  = 800;
	
	int initabla  = 710;
	int anctabla  = 520;
	
	int lineResu  = 250;
	
	int margeniz  = 35;
	int inmargde  = 415;	
	
	//DATOS DE LA FACTURA
	double porTaxes = 18;
	String divisa = " $RD";
	
	//PARÁMETROS A RELLENAR EN LA FACTURA
	String cabecNFC = "A0100100101";
	String numerNFC = "00000001";
	String codcrono = "";
	String txtipnfc = "FACTURA CON VALOR FISCAL";
	String imglogox = "";
	String idtmpfra = "";
	String marcados = "";
	 
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
	String regenera = "";
	String idlineas = "";
	String cdfactur = "";
	String facalbar = "";
	String estaalba = "N";
	String factasoc = "";
	String informda = "";
	String codvende = "";
	String mcpagado = "";
	String cduserid = "";
	int tipofact = 0;
	int mcagrupa = 0;
	String idfacreg = "";
	String formpago = "";
	String condpago = "";
	String catefact = "";
	String tipologo = "";
	String idunicox = "";
	String listimei = "";
	String tipoclie = "";
	String idfactur = "";
	String agrupada = "";
	//PARÁMETROS DE SALIDA
	String namefile = "";
	String filecrea = "";
	String txrazons = "";
	String cuentapu = "";
    String codespec = "";
	String isalbara = "";
	
	//OTROS PARÁMETROS
	Grid datosEmi = new Grid();
	Grid datosRec = new Grid();
	Grid gdUnicox = new Grid();
	
	Grid codProdu = new Grid();
	
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
			input.addVariable("idlineas");
			input.addVariable("cdfactur");
			input.addVariable("facalbar");
			input.addVariable("marcados");
			input.addVariable("factasoc");
			input.addVariable("informda");
			input.addVariable("idfactur");
			input.addVariable("codvende");
			input.addVariable("mcpagado");
			input.addVariable("cduserid");
			input.addVariable("listimei");
			input.addVariable("tipoclie");
			input.addVariable("cdcronol");
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
        	idfactur = input.getStringValue("idfactur");
        	informda = input.getStringValue("informda");  
        	idfacreg = input.getStringValue("idfactur");
        	listimei = input.getStringValue("listimei"); 
        	codvende = input.getStringValue("codvende");
        	mcpagado = input.getStringValue("mcpagado"); 
        	cduserid = input.getStringValue("cduserid"); 
        	tipoclie = input.getStringValue("tipoclie"); 
        	codcrono = input.getStringValue("cdcronol");
        	
        	
        
			FRAparen.setConexion(this.getConnection());
		} catch (Exception e1) {
			System.err.println(this.getClass().getName() +"ewredsgf- ERROR - "+ e1.getMessage());
			e1.printStackTrace();
		}
		
	}
	
    public void process(ObjectIO input, ObjectIO output){
    	
    	try {
	    	recuperaInput(input);
	    	
	    	 if(regenera==null || !regenera.equals("S")){
	    		 estableceCodigosFRA();
	    		 
	    		 if(tipofact ==1 || tipofact ==2 || tipofact == 4){
	    			 estableceCodigosCronoFRA("S"); // variable
	    		 }
	    	 }else{
	    		 numerNFC =  cdfactur;
	    		 
	    		
	    	 }
	    	
	    	 
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
	        
	        
	    	//filecrea = PropiedadesJLet.getInstance().getProperty("path.gen.invoice")+ namefile;
	    	writer = PdfWriter.getInstance(documento, new FileOutputStream(filecrea));
	        documento.open();
	        pxfrombt = 665;
			
	        pintaCabecera();
	        
	        pintaFecha();
	        
	        pintaDatosClienteEmisor();
	        
	        pintaOtrosDatos();
	        
	        pintaDetallesFRA();
	        
	        if (tipofact != 10){
		    
	        	pintaDatosBancarios();
		        pintaPiePagina();
	        }
	        
	        if (tipofact == 0){  
	        	estaalba = "P"; //se ha generado albaran pero no factura
	        }
	        	        
	        if(regenera == null || !regenera.equals("S")){
	        	
	        	//rellenaInforAdicional(informda);
	        	
	        	gdUnicox = selecPendientes();
	        	realizarActualizaciones();
	        	
	        	
	        	if(tipofact == 0){
	        		cambiaStock(gdUnicox,"DEPOSITO");
	        	}else{
	        		if(tipofact != 7 && tipofact != 8 && tipofact !=10){
	        			codProdu = selectGrupoStock(gdUnicox);
	        			cambiaStock(gdUnicox,"VENDIDO");
	        			updateStockWeb(codProdu);
	        			
	        			
	        		}
	        	}
	        	
	        	if(tipofact == 7 || tipofact == 8 || tipofact == 10){
			        	Grid gdImeis = selecImeis();
			        	cambiaStock(gdImeis,"STOCK");
			        	FRAparen.actualizaLineasFactura(receclie,tpclient,emisclie, marcados);
			        	FRAparen.marcaDevolucion(idfactur);
			    }else if(tipofact !=0){
			    	
						if(facalbar !=null && facalbar.equals("S")){
							Grid gdImeis = selecImeis();
							codProdu = selectGrupoStock(gdUnicox);
				        	cambiaStock(gdImeis,"VENDIDO");
				        	updateStockWeb(codProdu);
					}	
				}     
			}else{
				
				recalCulaFactu(idfacreg);
			}
	        
	        
	        System.out.println("AGRUPADAAAAAAAA "+mcagrupa);
	        
	        if( mcagrupa == 1){
	        	pintaAnexo();
	        	pintaPiePagina();
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
    
    public void estableceCodigosCronoFRA(String tipofact){
    	
    	int codigoFR = 0;
    	try {
	    	codigoFR = FRAparen.obtenerCodigoCronFRA(emisclie,tipofact);
	    	codcrono = formatCNFC.format(codigoFR);
    	} catch (Exception e) {
    		
    	}
    	
    }
    
    
    
    
    
   
// ------------------------------------------ START PINTAR DATOS -----------------------------
    
    
    public void pintaCabecera(){
    	//INCLUYE LOGO
    	Image image;
    	
		try {
			
			if (tipofact != 5 && tipofact != 10){
				imglogox = FRAparen.obtenerLogoEmisor(emisclie);
		        
		        if (tipologo.equals("1")){
		        	imglogox = "logomallproshop.png";
		        }
			}
	        
	        String imageUrl = PropiedadesJLet.getInstance().getProperty("path.img.logoinvoice") + imglogox;

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
			FRAparen.absTextBold(writer,"Fecha ",inmargde,740,10);
			FRAparen.absText(writer,FRAparen.fechaNormal(fhfactur),inmargde + 45,740,10);
			
			if (cabecNFC.equals("COND") || cabecNFC.equals("R")){
				FRAparen.absText(writer,cabecNFC +" "+ numerNFC +" ",inmargde,720,10);
				
				if(codvende !=null && !codvende.equals("") && !codvende.equals("null")){
					FRAparen.absText(writer,"Cod. Vendedor: " +" "+ codvende +" ",inmargde,700,10);
				}
				documento.add(getBarcode(documento, writer, "COND", numerNFC,inmargde));
			} else {
				
					FRAparen.absText(writer,"NFC "+ cabecNFC +" "+ numerNFC +" ",inmargde,720,10);
				if(tipofact ==1 || tipofact ==2 || tipofact == 4){
					FRAparen.absText(writer,codcrono,inmargde,700,10);
				}
					
				if(codvende !=null && !codvende.equals("") && !codvende.equals("null")){
					FRAparen.absText(writer,"Cod. Vendedor: " +" "+ codvende +" ",inmargde,690,10);
				}
			}
			
    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() +" ERROR pintaFecha() - "+ e.getMessage());
    	}
    	
    }
    
    public void pintaDatosClienteEmisor(){
    	
    	int pxfroini = pxfrombt;
    	
    	try {
    		datosEmi = FRAparen.obtenerDatosCliente(emisclie,"0","0");
			
			ArrayList<String> lineaEmi = new ArrayList<String>();
			lineaEmi = FRAparen.obtenerCabeceraCliente(datosEmi);
			
			if (tipofact != 5 && tipofact != 10){
				for (int i = 0; i < lineaEmi.size(); i++){
					String contLine = lineaEmi.get(i);
					if ((contLine != null) && (!"".equals(contLine))){
						FRAparen.absText(writer,contLine,margeniz,pxfrombt-8,8);
						pxfrombt-=15;
					}
				}
			}
			
			//datosRec = FRAparen.obtenerDatosCliente("",receclie); ////emisclie
			
			//datosRec = FRAparen.obtenerDatosCliente(emisclie,receclie);
			
			
			if(tipoclie != null && tipoclie.equals("I") ){
				datosRec = FRAparen.obtenerDatosCliente(receclie,tpclient);
			}else{
				datosRec = FRAparen.obtenerDatosCliente(emisclie,receclie,tpclient);
			}
	        	
	        	
			
			
			
			ArrayList<String> lineaRec = new ArrayList<String>();
			lineaRec =FRAparen.obtenerCabeceraCliente(datosRec);
			txrazons = lineaRec.get(0);
			
			//Altura de los datos del receptor
			int altRecep = pxfroini-8;
			
			for (int i = 0; i < lineaRec.size(); i++){
				
				String contLine = lineaRec.get(i);
				if ((contLine != null) && (!"".equals(contLine))){
					FRAparen.absText(writer,contLine,margeniz+300,altRecep,8);
					altRecep-=15;
				}
			}
			
    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() +"    ERROR pintaDatosClienteEmisor() - "+ e.getMessage());
    	}
    	
    }
    
    public void pintaOtrosDatos(){
    	
    	pxfrombt-=25;
    	
    	try {
	    	if (cabecNFC.equals("COND")){
	    		if (factasoc != null && !factasoc.equals("")){
	    			FRAparen.absTextBoldColor(writer,txtipnfc,margeniz,pxfrombt,16,new BaseColor(223,0,0));
					FRAparen.absTextBoldColor(writer,"  ("+ factasoc +")",margeniz+120,pxfrombt,16,new BaseColor(223,0,0));
				
	    		}else{	
					FRAparen.absTextBoldColor(writer,txtipnfc,margeniz,pxfrombt,16,new BaseColor(223,0,0));
					
	    		}	
			} else {
				if (factasoc != null && !factasoc.equals("")){
					FRAparen.absTextBold(writer,txtipnfc,margeniz,pxfrombt,10);
					FRAparen.absText(writer,"  ("+ factasoc +")",margeniz+120,pxfrombt,10);
				
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
					idtmpfra =FRAparen.maxIdmtpFRa(emisclie);
					
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
				 }
				 
				 else{
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
				
			/*	double unit 	 = Double.valueOf(grLineas.getStringCell(i,"cantidad"));
				double importe   = Double.valueOf(grLineas.getStringCell(i,"precioun"));
				double totalln   = Double.valueOf(grLineas.getStringCell(i,"precioto"));
				double descuento = Double.valueOf(grLineas.getStringCell(i,"descuent"));*/
				
				if (!idtmpfra.equals("") && !idtmpfra.equals(grLineas.getStringCell(i,"idtmpfra"))) {
					System.out.println(" ATENCION!!! Existen mas de un idtmpfra en la factura. idtmpfra="+ idtmpfra +" vs grid "+ idtmpfra.equals("idtmpfra"));
				}
				
				idtmpfra = grLineas.getStringCell(i,"idtmpfra");
				
				idunicox = grLineas.getStringCell(i,"idunicox");
				
				if(tipofact != 0){
					actualizaEstadoAlba("F");
				}
				
				//DEBIDO AL FORMATO
				descuento = descuento / 100;
				
				table.addCell(FRAparen.getCelda(formatUnit.format(unit) +" un.",fuente,bkcolor,"center"));
				table.addCell(FRAparen.getCelda(concepto,fuente,bkcolor,"left"));
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
			
			if (!cabecNFC.equals("COND")){
				
				table.addCell(FRAparen.getCelda2(" ",fuente,bkcolor,"center",0,2));
				table.addCell(FRAparen.getCelda2("SUBTOTAL",fuenteTot,bkcolorCab,"center",1,2));
				table.addCell(FRAparen.getCelda(formatDivi.format(impbasei) + divisa,fuenteTot,bkcolor,"right"));
				
				if (porTaxes > 0){
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
	    	
	    	// incluye texto itbis
	    	if (tipofact == 0 ){
	    		FRAparen.absTextBoldColor(writer," ESTOS PRECIOS NO INCLUYEN ITBIS",margeniz+370,lineResu+65,8,new BaseColor(0,0,0));
	    	}
	    	//

	    	//Incluye Firma Proveedor
	    	FRAparen.absLineaFina(writer,margeniz,lineResu,174);
	    	FRAparen.absText(writer,"Firma Proveedor ",92,lineResu-10,8);
	    	
	    	FRAparen.absLineaFina(writer,382,lineResu,174);
	    	FRAparen.absText(writer,"Recibido Por ",449,lineResu-10,8);
	    	
	    	//Incluye sello Proveedor
	    	if (tipologo.equals("1") && tipofact!= 5){
		    	
				imageUrl = PropiedadesJLet.getInstance().getProperty("path.img.logoinvoice") + "mini-logomallproshop.png";
	
			    image = Image.getInstance(imageUrl);
			    image.setAbsolutePosition(margeniz, 52);
			    image.scaleAbsolute(40,20);
			    image.setAlignment(Image.LEFT | Image.TEXTWRAP);//Code 2
				documento.add(image);
		    	
		    	String trademar = "MallProShop es una marca comercial (trademark) de Izumba Dominicana S.R.L.";
		    	FRAparen.absText(writer,trademar,margeniz+40,60,6);
		    	
		    	imageUrl = PropiedadesJLet.getInstance().getProperty("path.img.logoinvoice") + "mini-logoizumba.png";
	
			    image = Image.getInstance(imageUrl);
			    image.setAbsolutePosition(margeniz+260, 55);
			    image.scaleAbsolute(40,20);
			    image.setAlignment(Image.LEFT | Image.TEXTWRAP);//Code 2
				documento.add(image);
				
	    	}
			
	    	
	    	if (tipofact != 5){
		    	if (tipologo.equals("1")){
					imageUrl = PropiedadesJLet.getInstance().getProperty("path.img.logoinvoice") + "firma-mallproshop.png";
					image = Image.getInstance(imageUrl);
				    image.setAbsolutePosition(75,lineResu-10);
				    image.scaleAbsolute(100,65);
				} else {
					imageUrl = PropiedadesJLet.getInstance().getProperty("path.img.logoinvoice") + "firma-izumba.png";
					image = Image.getInstance(imageUrl);
				    image.setAbsolutePosition(75,lineResu-10);
				    image.scaleAbsolute(100,44);
				}

		    		image.setAlignment(Image.LEFT | Image.TEXTWRAP);//Code 2
		    		documento.add(image);
	    	}
	    	
	    	
	    	if(informda != null && !informda.equals("") && (mcagrupa == 0)){
	    		
	    		PdfPTable tableAd = new PdfPTable(1);
				tableAd.setTotalWidth(anctabla);
				tableAd.addCell(FRAparen.getCelda2("Información adicional:",fuenteTot,bkcolor,"left",0,2));
				tableAd.addCell(FRAparen.getCelda2(informda,fuente,bkcolor,"left",0,2));
				PdfContentByte canvas2 = writer.getDirectContent();
		    	tableAd.writeSelectedRows(ultfilsh, -1, margeniz, lineResu-60, canvas2);
	    	
	    	}
	    	
	    	//rellenaInforAdicional(informda,ultfilsh+50);
				
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
			    	cuentapu = gdFrmPag.getStringCell(0,"cuentapu");
			    	
			    	 if (tipofact != 5){ //sin discriminar itbis
			    	
					    	if (mcefecti.equals("N") && ((mcdiasxx.equals("N")) || (mcdiasxx.equals("")))){
					    		FRAparen.absText(writer,"Datos bancarios para efectuar pagos:",margeniz,140,8);
					    		datosBan += " "+ gdFrmPag.getStringCell(0,"txcuenta") +" "+ gdFrmPag.getStringCell(0,"txotrosx");
					    		FRAparen.absText(writer,datosBan,margeniz,130,8);
					    	} else {
					    		FRAparen.absTextBoldColor(writer, datosBan, margeniz, 140, 14, new BaseColor(175,175,175));
					    	}
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
	    	
	    	if (tipofact != 5){
		    	String conttfno = "8095474210 - 8097073324";
		    	String contwhat = "8097073324";
		    	String contblbe = "2A2572A2";
		    	FRAparen.absTextBold(writer,contmail,margeniz +  30,100,10);
		    	FRAparen.absTextBold(writer,conttfno,margeniz + 200,100,10);
		    	FRAparen.absTextBold(writer,contwhat,margeniz + 355,100,10);
		    	FRAparen.absTextBold(writer,contblbe,margeniz + 445,100,10);
		    	
		    	//IMAGENES DATOS DE CONTACTO
		    	String imgcolor = "-gris";
		    	String imgnmail = PropiedadesJLet.getInstance().getProperty("path.img.logoinvoice") + "mail"+ imgcolor +".png";
		    	String imgntfno = PropiedadesJLet.getInstance().getProperty("path.img.logoinvoice") + "tfno"+ imgcolor +".png";
		    	String imgnwhat = PropiedadesJLet.getInstance().getProperty("path.img.logoinvoice") + "whatsapp"+ imgcolor +".png";
		    	String imgnblbe = PropiedadesJLet.getInstance().getProperty("path.img.logoinvoice") + "bbpin"+ imgcolor +".png";
		    	
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
		    //	FRAparen.absText(writer, "Pag. "+ writer.getCurrentPageNumber() +" / "+ pagetotal,anctabla,altlinea - 10,6);
			
    	    }
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
		    	isalbara = grTpFact.getStringCell(0, "isalbara");
		    	codespec = grTpFact.getStringCell(0, "codespec");
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
    
   public Grid recuperoLineasFacturadas(String cdestado,String idtmpfra) {
    	
    	Grid grLineas = null;
    	
    	try {
			
			ListLineasTmpBDIn lineasBDIn = new ListLineasTmpBDIn();
			lineasBDIn.setValue("idemisor", emisclie);
			lineasBDIn.setValue("idclient", receclie);
			lineasBDIn.setValue("tpclient", "-1");
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
				idtmpfra = maxIdmtpFRa();
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
    	 
 /*guardamos los imeis en informacion adicional*/
    	 
    	 if(mcagrupa == 1){
    		 informda = listimei;
    	 }
    	
    	if (nUpdates != 1){
    		System.err.println(" ERROR GRAVE - No se ha actualizado el codigo de la facturacion");
    	} else {
			try {
				
				if(facalbar ==null || !facalbar.equals("D")){ // NO ACTUALIZO SI DEVUELVE UN ALBARAN YA QUE SE ACTUALIZA ANTES
				
					UpdLineasBDIn InsLinBDIn= new UpdLineasBDIn();
					InsLinBDIn.setValue("idclient",receclie);
					InsLinBDIn.setValue("tpclient",tpclient);
	    			InsLinBDIn.setValue("idemisor",emisclie);
	    			InsLinBDIn.setValue("cdoldest",cdoldest);
	    			InsLinBDIn.setValue("cdnewest","F");
	    			InsLinBDIn.setValue("idtmpfra",idtmpfra);
	    			InsLinBDIn.setValue("estaalba",estaalba);
	    			InsLinBDIn.setValue("marcados",marcados);
	    			
    			
		    		UpdLineasBD InsLinBD= new UpdLineasBD(InsLinBDIn);
		    		InsLinBD.setConnection(con);
		    		int liUpdate = InsLinBD.execUpdate();
		    		System.out.println(" Se han actualizado "+ liUpdate +" lineas.");	    		    	
				}
	    		
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
	    		factBDIn.setValue("codvende",codvende); 
	    		factBDIn.setValue("mcpagado",mcpagado);
	    		factBDIn.setValue("cdestado",cdestado);
	    		factBDIn.setValue("informda",informda);
	    		factBDIn.setValue("factasoc",factasoc);
	    		factBDIn.setValue("codcrono",codcrono);
	    		factBDIn.setValue("mcagrupa",agrupada);
	    		
	    		
	    		UpdFacturaBD factBD = new UpdFacturaBD(factBDIn);
	    		factBD.setConnection(con);
	    		int creaFact = factBD.execInsert();
	    		
	    		 if(tipofact ==1 || tipofact ==2 || tipofact == 4){
	    			 	FRAparen.actualizaCodigoCronFRA(emisclie,"S"); //luis
	    		 }
	    		System.out.println(" Se ha creado "+ creaFact +" factura.");
	    		
	    		String idfactura = FRAparen.idUltimaFactu();
	    		System.out.println("IDDDDDDDDDDDDDD   "+idfactura);
	    		
	    		/*if (mcpagado !=null && mcpagado.equals("S") && !cuentapu.equals("0") && isalbara.equals("N") && codespec.equals("F") ){
	    			
	    			FRAparen.insertaApunte(emisclie, cuentapu, imptotal, fhfactur, cabecNFC +" "+ numerNFC,idfactura);
	    			
	    		}*/
	    		
			} catch (Exception e) {
				e.printStackTrace();
			}
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
    
    
    public String maxIdmtpFRa() throws Exception{
    	
    	MaxFacturaBDIn factBDIn = new MaxFacturaBDIn();
    	MaxFacturaBD factBD = new MaxFacturaBD(factBDIn);
    	factBD.setConnection(con);
    	Grid gridFact = factBD.execSelect();
    	
    	int idfactur =0;
    	
    	try {
        	if (gridFact.rowCount() > 0){
        		idfactur = Integer.parseInt(gridFact.getStringCell(0,"idfactur"));
        		idfactur++;
        	}
    	} catch (Exception e) {
    		idfactur = 1;
    	}
		return Integer.toString(idfactur);
        	
    	
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
   
   
   public void rellenaInforAdicional(String informadi,int ultfilsh){
		 
		 try{
			 
			 if(informadi != null && !informadi.equals("")){
				 
				  	Font font		= FontFactory.getFont("Times-Roman", 6);
					
				    Chunk chunkNom 	= null;
				    
				    Phrase contcell = null;
				    
				    PdfPCell cell 	= null;
			    	
			    	try {
				    	
			    		int anctabla  = 600;
			    		 PdfPTable table = new PdfPTable(1);  
			    		table.setTotalWidth(anctabla);

			    		

			    			chunkNom = new Chunk("Información adicional:", font);
				    	    contcell = new Phrase(chunkNom);
				    	    
				    		cell = new PdfPCell(contcell);
				    		table.addCell(cell);
				            chunkNom = new Chunk(informadi, font);
				    	    
				    	    contcell = new Phrase(chunkNom);
				    	    
				    		cell = new PdfPCell(contcell);
				            cell.setColspan(2);
				            cell.setBorder(0);
				            table.addCell(cell);
				            PdfContentByte canvas = writer.getDirectContent();
					    	table.writeSelectedRows(ultfilsh, -1, margeniz, initabla, canvas);
				            
				            documento.add(table);
			    		}catch(Exception e){
			    			System.err.println("ERROR al rellenar informacion adicional. "+ e.getMessage());
			    		}
				            
			    	  
			
		 		//FRAparen.absTextColor(writer,"Información adicional:",margeniz,270,9,new BaseColor(64, 64, 64));
		   		//FRAparen.absTextColor(writer,informadi,margeniz,255,8,new BaseColor(64, 64, 64));
			 }  	
		    	
	    	} catch (Exception e) {
	    		System.err.println("ERROR al rellenar informacion adicional. "+ e.getMessage());
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
    		
    		idtmpfra = maxIdmtpFRa();
    		
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
    
    public void cambiaStock(Grid gdLineasImei, String cdestado){ // si es devolucion
    	
    		String imeicode = "";
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
    			System.out.println(" Id factura "+ idfactur +" factura.");
    		}catch(Exception ex){
    			System.out.println("Errror al recuperar el idfactur");
    		}

    		for(int i=0; gdLineasImei.rowCount()>i; i++){
    			System.out.println("entraaaaaaaaaaaaaa "+gdLineasImei.rowCount()+" LINEAS");
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
							devStockBDIn.setValue("pricevnt",pricevnt);
							devStockBDIn.setValue("idclient",receclie);
							devStockBDIn.setValue("tpclient",tpclient);
							devStockBDIn.setValue("prusdvnt",prusdvnt);
							devStockBDIn.setValue("divisvnt",divisa);
					    	UpdStockBD devStockBD = new UpdStockBD(devStockBDIn);
					    	devStockBD.setConnection(con);
							int liInsert = devStockBD.execUpdate();
							
							updEstadoProducto(imeicode,cdestado);
							FRAparen.generaDetalleDesglose(imeicode, emisclie, receclie, txrazons, cabecNFC+numerNFC, FRAparen.fechaNormal(fhfactur));
							

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
    				}	
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
    
    public void updEstadoProducto( String idunicox, String newstate){
    	try{
	    	UpdEstadoProBDIn estadoBDIn = new UpdEstadoProBDIn();
	    	estadoBDIn.setValue("idemisor",emisclie);
	    	estadoBDIn.setValue("idclient",receclie);
	    	estadoBDIn.setValue("tpclient",tpclient);
	    	estadoBDIn.setValue("idunicox",idunicox);
			estadoBDIn.setValue("iduserxx",cduserid);
			estadoBDIn.setValue("newstate",newstate);
			
			UpdEstadoProBD estadoBD = new UpdEstadoProBD(estadoBDIn);
			estadoBD.setConnection(con);
			estadoBD.execInsert();
			
    	}catch(Exception ex){
    		System.err.println("Error al actualizar estado del producto");
    	}
    }
    
    public void updateStockWeb(Grid gdCodPro){
    	
    	String codprodu = "";
    	String idproIzu = "";
    	
    	try {

    		createIzuConnection();
    	
    		for(int i=0; gdCodPro.rowCount()>i; i++){
    			System.out.println("entraaaaaaaaaaaaaa "+gdCodPro.rowCount()+" LINEAS");
				codprodu = gdCodPro.getStringCell(i, "codprodu");
				
				if(!codprodu.equals("")){
			    	
			    	try{
			    		
			    		
			    		UpdStockWebBDIn liststockBDin = new UpdStockWebBDIn();
			    		liststockBDin.setValue("refeprod",codprodu);
			    		UpdStockWebBD listStockBD = new UpdStockWebBD(liststockBDin);
			    		listStockBD.setConnection(izucon);
			    		Grid gdIdProd = listStockBD.execSelect();
			    		
			    		
			    		idproIzu = gdIdProd.getStringCell(0, "id_product");
			    		
			    		UpdStockWebBDIn upstockBDin = new UpdStockWebBDIn();
			    		upstockBDin.setValue("id_shopx","1");
			    		upstockBDin.setValue("idproduc",idproIzu);
			    		UpdStockWebBD updateBD = new UpdStockWebBD(upstockBDin);
			    		updateBD.setConnection(izucon);
			    		updateBD.execUpdate();
			    	}catch(Exception ex){
			    		
			    	System.out.println(ex.getMessage());
			    	}
			}
    	
    	}
    	closeIzuConnection();
    	}  catch (Exception e) {
    		System.err.println(" ATENCIÓN! Se ha producido un error en la actualizacion del Stock de Izumba ");
    	}
    }	
    
    
    public Grid selectGrupoStock(Grid gdimeis){
    	String imeicode ="";
    	String codprodu = "";
    	
    	Grid gdStock = null;
    	Grid grCodPro = new Grid();
    	grCodPro.addColumn("codprodu");
    	
    	for(int i=0; gdimeis.rowCount()>i; i++){
			System.out.println("entraaaaaaaaaaaaaa "+gdimeis.rowCount()+" LINEAS");
				imeicode = gdimeis.getStringCell(i, "idunicox");
				
				if(!imeicode.equals("")){
    	
				    	try{
				    		ListStockBDIn listadoStockBDin = new ListStockBDIn();
				    		listadoStockBDin.setValue("idemisor",emisclie);
				    		listadoStockBDin.setValue("imeicode",imeicode);
				    		listadoStockBDin.setValue("cdestado","STOCK");
				    		ListStockBD listadoBD = new ListStockBD(listadoStockBDin);
				    		listadoBD.setConnection(con);
				    		gdStock = listadoBD.execSelect();
				    		
				    		
				    		
				    		ArrayList<String> row = new ArrayList<String>();
				    		codprodu = gdStock.getStringCell(0, "codprodu");
				    		row.add(codprodu);
				    		grCodPro.addRow(row);
				    		
				    	}catch(Exception ex){
				    		System.out.println(ex.getMessage());
				    	}
					}
    	}
    	
    	return grCodPro;
	}
    
    
    public void pintaAnexo(){
    	
      	 documento.newPage();
      	 pagetotal++;
      	 try{
      		Image image;
      		Font fuente = new Font(FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(64, 64, 64));
   		BaseColor bkcolor = new BaseColor(245,246,250);
   		
      	/*	String imageUrl = PropiedadesJLet.getInstance().getProperty("path.img.logoinvoice") + "gtp100.jpg";

   			image = Image.getInstance(imageUrl);
   		    image.setAbsolutePosition((documento.getPageSize().getWidth() / 2) - 151, 750);
   		    image.scaleAbsolute(323,323);
   		  */
      	 
      		 PdfPTable table = new PdfPTable(new float[] { 1});
      		 table.setTotalWidth(620);
    
      		 table.addCell(FRAparen.getCelda(listimei.replaceAll(";", "         -        "),fuente,bkcolor,"left"));
      		 documento.add(table);
      	 }catch(Exception ex){
      		 System.out.println("Error crear anexo nueva pagina");
      	 }
      	 
      	 
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
    
    
    private static Image getBarcode(Document document,  PdfWriter pdfWriter, String servicio,String  codigoTransaccion, int posicx){
    	PdfContentByte cimg = pdfWriter.getDirectContent();
    	Barcode128 code128 = new Barcode128();
    	code128.setCode(servicio + addZeroLeft(codigoTransaccion));
    	code128.setCodeType(Barcode128.CODE128);
    	code128.setTextAlignment(Element.ALIGN_CENTER);
    	Image image = code128.createImageWithBarcode(cimg, null, null);
    	float scaler = ((document.getPageSize().getWidth() - document.leftMargin()  - document.rightMargin() - 0) / image.getWidth()) * 15;
    	image.scalePercent(scaler);
    	image.setAlignment(Element.ALIGN_CENTER);
	    image.setAbsolutePosition(posicx,665);
    	return image;
    }
    
    private static String addZeroLeft(String num) {
    	NumberFormat formatter = new DecimalFormat("00000000");
    	return formatter.format((num != null) ? Integer.parseInt(num) : 0000000);
    }
    
        
    public String getFileCreated(){
    	return this.filecrea;    	
    }
    
    public  void actualizaLineasFactura(){
    	
    	try{
	    	UpdLineasBDIn InsLinBDIn= new UpdLineasBDIn();
			InsLinBDIn.setValue("idclient",receclie);
			InsLinBDIn.setValue("tpclient",tpclient);
			InsLinBDIn.setValue("idemisor",emisclie);
			InsLinBDIn.setValue("cdoldest","F");
			InsLinBDIn.setValue("cdnewest","D");
			InsLinBDIn.setValue("marcados",marcados);
			UpdLineasBD InsLinBD= new UpdLineasBD(InsLinBDIn);
			InsLinBD.setConnection(con);
			int liUpdate = InsLinBD.execUpdate();
			System.out.println(" Se han actualizado "+ liUpdate +" lineas.");	
    	}catch(Exception ex){
    		System.out.println("Error al actualizar desdde devolucion factura");
    		
    	}
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

           	stfixing = divisa.getFixingUSD("DOP");
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