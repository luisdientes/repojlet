package facturacion.parents;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import common.constructors.xls.ExcelContabilidadSrv;
import contabilidad.bd.UpdateApunteBD;
import contabilidad.bd.UpdateApunteBDIn;
import desgcostes.ActualizaDetalleSrv;
import desgcostes.ActualizaCostesSrv;
import desgcostes.bd.UpdCostesBD;
import desgcostes.bd.UpdCostesBDIn;
import desgcostes.bd.UpdDetalleBD;
import desgcostes.bd.UpdDetalleBDIn;
import factura.bd.ListLineasBD;
import factura.bd.ListLineasBDIn;
import factura.bd.MaxFacturaBD;
import factura.bd.MaxFacturaBDIn;
import factura.bd.UpdDevFacturaBD;
import factura.bd.UpdDevFacturaBDIn;
import facturacion.bd.ListCodigoFacturaBD;
import facturacion.bd.ListCodigoFacturaBDIn;
import facturacion.bd.ListDetalleClienteBD;
import facturacion.bd.ListDetalleClienteBDIn;
import facturacion.bd.ListLineasFactAgruBD;
import facturacion.bd.ListLineasFactAgruBDIn;
import facturacion.bd.ListLineasTmpAgruBD;
import facturacion.bd.ListLineasTmpAgruBDIn;
import facturacion.bd.ListLineasTmpBD;
import facturacion.bd.ListLineasTmpBDIn;
import facturacion.bd.UpdCodigoFacturaBD;
import facturacion.bd.UpdCodigoFacturaBDIn;
import facturacion.bd.ListCodigoFacturaCronBD;
import facturacion.bd.ListCodigoFacturaCronBDIn;
import facturacion.bd.UpdCodigoFacturaCronBD;
import facturacion.bd.UpdCodigoFacturaCronBDIn;
import facturacion.bd.UpdFacturaBD;
import facturacion.bd.UpdFacturaBDIn;
import facturacion.bd.UpdLineasBD;
import facturacion.bd.UpdLineasBDIn;

public class FacturaPDF {

	Connection conexion = null;
	
	public void setConexion(Connection con){
		conexion = con;
	}
			
	public int obtenerCodigoFRA(String idemisor, String aniofact, int tipofact) {
		
		int codenfcx = 0;
		
        try {

        	ListCodigoFacturaBDIn codFacturaBDIn = new ListCodigoFacturaBDIn();
        	codFacturaBDIn.setValue("idemisor",idemisor);
        	codFacturaBDIn.setValue("aniofact",aniofact);
        	codFacturaBDIn.setValue("tipofact",tipofact);
        	ListCodigoFacturaBD codFacturaBD = new ListCodigoFacturaBD(codFacturaBDIn);
        	codFacturaBD.setConnection(conexion);
        	Grid grFactura = codFacturaBD.execSelect();
        	
        	if (grFactura.rowCount() > 0){
        		String strcodef = grFactura.getStringCell(0,"cdfactur");
        		codenfcx = Integer.parseInt(strcodef);
        	}
            
        } catch (Exception e) {
        	System.err.println(this.getClass().getName() +" ERROR - Al obtener el codigo de la factura. "+ e.getMessage());
        }
        
		return codenfcx;
		
    }
	
	
	
	
	public int actualizaCodigoFRA(String idemisor, String aniofact, int tipofact){
		
		int nresults = 0;
		
		try{
			
			UpdCodigoFacturaBDIn codFacturaBDIn = new UpdCodigoFacturaBDIn();
        	codFacturaBDIn.setValue("idemisor",idemisor);
        	codFacturaBDIn.setValue("aniofact",aniofact);
        	codFacturaBDIn.setValue("tipofact",tipofact);
        	UpdCodigoFacturaBD codFacturaBD = new UpdCodigoFacturaBD(codFacturaBDIn);
        	codFacturaBD.setConnection(conexion);
        	nresults = codFacturaBD.execUpdate();
			
	        if (nresults != 1){
	        	throw new Exception();
	        }
		} catch (Exception e) {
			System.err.println(this.getClass().getName() +" ERROR GRAVE - No se ha podido actualizar el codigo de factura. "+ e.getMessage());
		}
	        
        return nresults;
	}
	
	
	public int obtenerCodigoCronFRA(String idemisor, String tipofact) {
		
		int codenfcx = 0;
		
        try {

        	ListCodigoFacturaCronBDIn codFacturaBDIn = new ListCodigoFacturaCronBDIn();
        	codFacturaBDIn.setValue("idemisor",idemisor);
        	//codFacturaBDIn.setValue("aniofact",aniofact);
        	codFacturaBDIn.setValue("tipofact",tipofact);
        	ListCodigoFacturaCronBD codFacturaBD = new ListCodigoFacturaCronBD(codFacturaBDIn);
        	codFacturaBD.setConnection(conexion);
        	Grid grFactura = codFacturaBD.execSelect();
        	
        	if (grFactura.rowCount() > 0){
        		String strcodef = grFactura.getStringCell(0,"cdfactur");
        		codenfcx = Integer.parseInt(strcodef);
        	}
            
        } catch (Exception e) {
        	System.err.println(this.getClass().getName() +" ERROR - Al obtener el codigo de la factura. "+ e.getMessage());
        }
        
		return codenfcx;
		
    }
	
	
	
	
	public int actualizaCodigoCronFRA(String idemisor, String tipofact){
		
		int nresults = 0;
		
		try{
			
			UpdCodigoFacturaCronBDIn codFacturaBDIn = new UpdCodigoFacturaCronBDIn();
        	codFacturaBDIn.setValue("idemisor",idemisor);
        	//codFacturaBDIn.setValue("aniofact",aniofact);
        	codFacturaBDIn.setValue("tipofact",tipofact);
        	UpdCodigoFacturaCronBD codFacturaBD = new UpdCodigoFacturaCronBD(codFacturaBDIn);
        	codFacturaBD.setConnection(conexion);
        	nresults = codFacturaBD.execUpdate();
			
	        if (nresults != 1){
	        	throw new Exception();
	        }
		} catch (Exception e) {
			System.err.println(this.getClass().getName() +" ERROR GRAVE - No se ha podido actualizar el codigo de factura. "+ e.getMessage());
		}
	        
        return nresults;
	}
	
	
	
	
	
	public String obtenerLogoEmisor(String idemisor){
				
		String logoclie = "nologo.png";
		
		try{
			ListDetalleClienteBDIn logoBDIn = new ListDetalleClienteBDIn();
			logoBDIn.setValue("idemisor",idemisor);
			logoBDIn.setValue("idclient","0");
			
			ListDetalleClienteBD logoBD = new ListDetalleClienteBD(logoBDIn);
			logoBD.setConnection(conexion);
        	Grid grLogoCl = logoBD.execSelect();
        	
        	if (grLogoCl.rowCount() > 0){
        		logoclie = grLogoCl.getStringCell(0,"logoclie");
        	}
	        
		} catch (Exception e) {
			System.err.println(this.getClass().getName() +" ERROR -  Al obtener el logo del cliente. "+ e.getMessage());
		}
		
		return logoclie;
	}
	
	public Grid obtenerDatosCliente(String idemisor, String idclient) {
		
		Grid gridDeta = null;
		
		try{
			ListDetalleClienteBDIn detalleBDIn = new ListDetalleClienteBDIn();
			detalleBDIn.setValue("idemisor",idemisor);
			detalleBDIn.setValue("idclient",idclient);
			ListDetalleClienteBD logoBD = new ListDetalleClienteBD(detalleBDIn);
			logoBD.setConnection(conexion);
        	gridDeta = logoBD.execSelect();
        	
		} catch (Exception e) {
			System.err.println(this.getClass().getName() +" ERROR - Al obtener el logo del cliente. "+ e.getMessage());
		}
        
		return gridDeta;
		
    }
	
	public Grid obtenerDatosCliente(String idemisor, String idclient, String tpclient) {
		
		Grid gridDeta = null;
		
		try{
			ListDetalleClienteBDIn detalleBDIn = new ListDetalleClienteBDIn();
			detalleBDIn.setValue("idemisor",idemisor);
			detalleBDIn.setValue("idclient",idclient);
			detalleBDIn.setValue("tpclient",tpclient);
			ListDetalleClienteBD logoBD = new ListDetalleClienteBD(detalleBDIn);
			logoBD.setConnection(conexion);
        	gridDeta = logoBD.execSelect();
        	
		} catch (Exception e) {
			System.err.println(this.getClass().getName() +" ERROR - Al obtener el logo del cliente. "+ e.getMessage());
		}
        
		return gridDeta;
		
    }
	
	public Grid getLineas(String emisclie, String cdestado) {
		
		Grid gridDeta = null;
		
		try{
			ListLineasTmpBDIn lineasBDIn = new ListLineasTmpBDIn();
			lineasBDIn.setValue("idclient", emisclie);
			lineasBDIn.setValue("cdestado", "V");
			ListLineasTmpBD lineasBD = new ListLineasTmpBD(lineasBDIn);
			lineasBD.setConnection(conexion);
        	gridDeta = lineasBD.execSelect();
        	
		} catch (Exception e) {
			System.err.println(this.getClass().getName() +" ERROR - Al obtener el logo del cliente. "+ e.getMessage());
		}
		
		return gridDeta;
		
	}
	
	public ArrayList<String> obtenerCabeceraCliente(Grid datosEmi) {
		
		ArrayList<String> lineaEmi = new ArrayList<String>();
		
		try {
			lineaEmi.add(datosEmi.getStringCell(0, "linea1xx"));
			lineaEmi.add(datosEmi.getStringCell(0, "linea2xx"));
			lineaEmi.add(datosEmi.getStringCell(0, "linea3xx"));
			lineaEmi.add(datosEmi.getStringCell(0, "linea4xx"));
			lineaEmi.add(datosEmi.getStringCell(0, "linea5xx"));
		} catch (Exception e) {
			System.err.println(this.getClass().getName() +" ERROR - Al obtener la cabecera del Emisor. "+ e.getMessage());
		}
		
		return lineaEmi;
		
	}
	
	 public Grid recuperoLineasAgru(String emisclie,String receclie,String listimei) {
	    	
	    	Grid grLineas = null;
	    	
	    	String separaim = listimei.replaceAll(";", "','");
	    	int imeisxx = separaim.length()-3;
	    	
	    	try {
				
	    		ListLineasFactAgruBDIn lineasBDIn = new ListLineasFactAgruBDIn();
				lineasBDIn.setValue("idemisor", emisclie);
				lineasBDIn.setValue("idclient", receclie);
				lineasBDIn.setValue("imeisxxx", separaim.substring(0, imeisxx));
				ListLineasFactAgruBD lineasBD = new ListLineasFactAgruBD(lineasBDIn);
				lineasBD.setConnection(conexion);
				grLineas = lineasBD.execSelect();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	
	    	return grLineas;
	    }
	 public Grid recuperoLineasTmpAgru(String emisclie,String idfactur) {
	    	
	    	Grid grLineas = null;
	    	
	    	try {
	    		ListLineasTmpAgruBDIn lineasBDIn = new ListLineasTmpAgruBDIn();
				lineasBDIn.setValue("idemisor", emisclie);
				lineasBDIn.setValue("idfactur", idfactur);
				ListLineasTmpAgruBD lineasBD = new ListLineasTmpAgruBD(lineasBDIn);
				lineasBD.setConnection(conexion);
				grLineas = lineasBD.execSelect();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	
	    	return grLineas;
	    }
	
	public void absText(PdfWriter twriter, String text, int x, int y, int fontsize) {
        try {
          PdfContentByte cb = twriter.getDirectContent();
          BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
          cb.saveState();
          cb.beginText();
          cb.moveText(x, y);
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
    
	public void absTextBold(PdfWriter twriter, String text, int x, int y, int fontsize) {
        try {
          PdfContentByte cb = twriter.getDirectContent();
          BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
          cb.saveState();
          cb.beginText();
          cb.moveText(x, y);          
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
	
	public void absTextColor(PdfWriter twriter, String text, int x, int y, int fontsize, BaseColor color) {
        try {
          PdfContentByte cb = twriter.getDirectContent();
          BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
          cb.saveState();
          cb.beginText();
          cb.moveText(x, y);          
          cb.setFontAndSize(bf, fontsize);
          cb.setColorFill(color);
          cb.showText(text);
          cb.endText();
          cb.restoreState();
        } catch (DocumentException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
	
	public void absTextBoldColor(PdfWriter twriter, String text, int x, int y, int fontsize, BaseColor color) {
        try {
          PdfContentByte cb = twriter.getDirectContent();
          BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
          cb.saveState();
          cb.beginText();
          cb.moveText(x, y);          
          cb.setFontAndSize(bf, fontsize);
          cb.setColorFill(color);
          cb.showText(text);
          cb.endText();
          cb.restoreState();
        } catch (DocumentException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    
	public void absLineaFina(PdfWriter twriter, int x, int y, int l) {
    	
    	PdfContentByte canvas = twriter.getDirectContent();
    	//cb.setColorFill(new BaseColor(0, 0, 0));
        canvas.saveState();
        canvas.setLineWidth((float) 5 / 10);
        canvas.moveTo(x, y);
        canvas.lineTo(x + l, y);
        canvas.stroke();
        canvas.restoreState();
        
    }
    
	public PdfPCell getCelda(String textocel, Font tpfuente, BaseColor bkgcolor, String tpalineac){
		
    	PdfPCell celda = new PdfPCell(new Paragraph(textocel,tpfuente));
		celda.setBackgroundColor(bkgcolor);
		if ((tpalineac != null) && (tpalineac.equals("center"))){
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		} else if ((tpalineac != null) && (tpalineac.equals("right"))){
			celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
		}
    	
    	return celda;
    	
    }
    
	public PdfPCell getCelda2(String textocel, Font tpfuente, BaseColor bkgcolor, String tpalineac, int ancborde, int colspan){
		
    	PdfPCell celda = new PdfPCell(new Paragraph(textocel,tpfuente));
		celda.setBackgroundColor(bkgcolor);
		
		if ((tpalineac != null) && (tpalineac.equals("center"))){
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		} else if ((tpalineac != null) && (tpalineac.equals("right"))){
			celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
		}
		
		//celda.setBorder(ancborde);
		if (ancborde == 0){
			celda.setBorderWidthLeft(0);
			celda.setBorderWidthTop(0);
			celda.setBorderWidthRight(0);
			celda.setBorderWidthBottom(0);
		}
		celda.setColspan(colspan);
    	
    	return celda;
    	
    }
	
	public PdfPCell getCeldaImagen(Image image, int ancborde, int colspan){
		
		try {
	    	
	        PdfPCell celda = new PdfPCell(image);
	    	
			//celda.setBorder(ancborde);
			if (ancborde == 0){
				celda.setBorderWidthLeft(0);
				celda.setBorderWidthTop(0);
				celda.setBorderWidthRight(0);
				celda.setBorderWidthBottom(0);
			}
			celda.setColspan(colspan);
	    	
    	return celda;
		} catch (Exception e) {
			PdfPCell celda = new PdfPCell(new Paragraph("ERROR IMAGEN"));
			celda.setBackgroundColor(new BaseColor(255,0,0));
			return celda;
		}
    	
    }
    
	public void insCabeceraTabla (PdfPTable tabla){
    	
    	Font fuenteCab = new Font(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(64, 64, 64));
		BaseColor bkcolorCab = new BaseColor(200, 200, 200);
		
		tabla.addCell(getCelda("CANT. ",fuenteCab,bkcolorCab,"center"));
		tabla.addCell(getCelda("CONCEPTO",fuenteCab,bkcolorCab,"center"));
		tabla.addCell(getCelda("PRECIO/UN",fuenteCab,bkcolorCab,"center"));
		tabla.addCell(getCelda("% DTO",fuenteCab,bkcolorCab,"center"));
		tabla.addCell(getCelda("TOTAL",fuenteCab,bkcolorCab,"center"));
		
    }
	
	public void insCabeceraTablaTradens (PdfPTable tabla){
    	
    	Font fuenteCab = new Font(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(64, 64, 64));
		BaseColor bkcolorCab = new BaseColor(200, 200, 200);
		
		tabla.addCell(getCelda("Réf. ",fuenteCab,bkcolorCab,"center"));
		tabla.addCell(getCelda("Désignation",fuenteCab,bkcolorCab,"center"));
		tabla.addCell(getCelda("Quantité. ",fuenteCab,bkcolorCab,"center"));
		tabla.addCell(getCelda("P.U HT",fuenteCab,bkcolorCab,"center"));
		tabla.addCell(getCelda("Remise",fuenteCab,bkcolorCab,"center"));
		tabla.addCell(getCelda("Montant",fuenteCab,bkcolorCab,"center"));
		
    }
	
	 public void marcaDevolucion(String idfactur){
	    	try{
	    	UpdDevFacturaBDIn updPagaFactuBDIn = new UpdDevFacturaBDIn();
	    	updPagaFactuBDIn.setValue("idfactur", idfactur);
	    	updPagaFactuBDIn.setValue("cdestado", "D");
	    	UpdDevFacturaBD updPagaFactuBD =  new UpdDevFacturaBD(updPagaFactuBDIn);
	    	updPagaFactuBD.setConnection(conexion);
	    	updPagaFactuBD.execUpdate();
	    	}catch(Exception ex){
	    		
	    		System.out.println("ERROR AL MARCAR COMO DEVUELTA");
	    	}
	    }
	 
	 
	 public Grid selecImeis(String idtmpfra, String marcados){
	    	
	    	Grid gdLineasBD = null;
	    	
	    	try {
	    		ListLineasBDIn lineasBDIn = new ListLineasBDIn();
	    		lineasBDIn.setValue("idtmpfra",idtmpfra);
	    		lineasBDIn.setValue("marcados",marcados);
	    		lineasBDIn.setValue("tpclient","-1");
				ListLineasBD lineasBD = new ListLineasBD(lineasBDIn);
				lineasBD.setConnection(conexion);
				gdLineasBD = lineasBD.execSelect();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
			return gdLineasBD;
		
	    }
	 
	 
	 public  void actualizaLineasFactura(String receclie,String tpclient, String emisclie, String marcados){
	    	
	    	try{
		    	UpdLineasBDIn InsLinBDIn= new UpdLineasBDIn();
				InsLinBDIn.setValue("idclient",receclie);
				InsLinBDIn.setValue("tpclient",tpclient);
				InsLinBDIn.setValue("idemisor",emisclie);
				InsLinBDIn.setValue("cdoldest","F");
				InsLinBDIn.setValue("cdnewest","D");
				InsLinBDIn.setValue("marcados",marcados);
				UpdLineasBD InsLinBD= new UpdLineasBD(InsLinBDIn);
				InsLinBD.setConnection(conexion);
				int liUpdate = InsLinBD.execUpdate();
				System.out.println(" Se han actualizado "+ liUpdate +" lineas.");	
	    	}catch(Exception ex){
	    		System.out.println("Error al actualizar desdde devolucion factura");
	    		
	    	}
	    }
	 
	 public void generaDetalleDesglose(String idunicox,String idemisor,String idclient,String txclient, String cdfactur, String fhfactur){
		 
		 	ActualizaDetalleSrv insDetalleDesglSrv = new ActualizaDetalleSrv();
	        ObjectIO inputDesg 	= insDetalleDesglSrv.instanceOfInput();
	        ObjectIO outputDesg = insDetalleDesglSrv.instanceOfOutput();
	        
	        try {
	        	
	        	inputDesg.setValue("idemisor", idemisor);
	        	inputDesg.setValue("idunicox", idunicox);
	        	inputDesg.setValue("idclient", idclient);
	        	inputDesg.setValue("txclient", txclient);
	        	inputDesg.setValue("cdfactur", cdfactur);
	        	inputDesg.setValue("fhfactur", fhfactur);
	        	inputDesg.setValue("fhventax", fhfactur);
	        	insDetalleDesglSrv.setConnection(conexion);
	        	insDetalleDesglSrv.process(inputDesg, outputDesg);
	        	
	        }catch(Exception ex){
	        	System.out.println("Error al insertar desgloses");
	        }
		 
	 }
	 
	 public void actualizaCostes(String idunicox,String idemisor,String codedesg,String desvalue,String cddivisa){
			
		 try {	
					UpdCostesBDIn costesBDIn = new UpdCostesBDIn();
					costesBDIn.setValue("idunicox", idunicox);
					costesBDIn.setValue("idemisor", idemisor);
					costesBDIn.setValue("codedesg", codedesg);
					costesBDIn.setValue("desvalue", desvalue);
					costesBDIn.setValue("cddivisa", cddivisa);
					UpdCostesBD costesBD = new UpdCostesBD(costesBDIn);
					costesBD.setConnection(conexion);
					try {
						costesBD.execInsert();
					} catch(Exception e) {
						System.out.println(idunicox +" - "+ idemisor +" - "+ codedesg +" ACTUALIZADO!!" + e.getMessage());
						costesBD.setConnection(conexion);
						costesBD.execUpdate();
					}
			} catch (Exception e) {
				System.out.println("ERROR "+ e.getMessage());
			}
	 }
	 
	 
	 
	 
	 public String maxIdmtpFRa(String emisclie) throws Exception{
	    	
	    	MaxFacturaBDIn factBDIn = new MaxFacturaBDIn();
	    	factBDIn.setValue("idemisor", emisclie);
	    	MaxFacturaBD factBD = new MaxFacturaBD(factBDIn);
	    	factBD.setConnection(conexion);
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
	 
	  public void insertaApunte( String emisclie, String cuentapu,Double imptotal,String fhfactur,String  filedocu, String idfactur){
		  
		  try{
			   String concepto = "FACTURA "+filedocu;
				
				UpdateApunteBDIn updApuntesBDIn = new UpdateApunteBDIn();
			  	updApuntesBDIn.setValue("idemisor", emisclie);
			  	updApuntesBDIn.setValue("idcuenta", cuentapu);
			  	updApuntesBDIn.setValue("cantidad", imptotal);
			  	updApuntesBDIn.setValue("debhaber", "D");
			  	updApuntesBDIn.setValue("concepto", concepto);
			  	updApuntesBDIn.setValue("fhapunte", fhfactur);
			  	updApuntesBDIn.setValue("coddocum", idfactur);
			  	updApuntesBDIn.setValue("tpapunte", "AP");
			  	updApuntesBDIn.setValue("documint", "G");
			 	updApuntesBDIn.setValue("cduserid", "1");
			  	updApuntesBDIn.setValue("filedocu", filedocu);
			  	
			  	
			  	UpdateApunteBD updApuntesBD = new UpdateApunteBD(updApuntesBDIn);
			  	updApuntesBD.setConnection(conexion);
			  	updApuntesBD.execInsert();
		  }catch( Exception ex){
			  System.out.println("Error al dar de alta apunte desde factura");
		  }
				  
	  }
	  
	  
	  public String  idUltimaFactu(){
			 
		  String idfactur = "";
		  Grid gdMaxfactu = null;
		  
			  try{
					UpdFacturaBDIn factBDIn = new UpdFacturaBDIn();
					UpdFacturaBD factBD = new UpdFacturaBD(factBDIn);
					factBD.setConnection(conexion);
					gdMaxfactu = factBD.execSelect();
					idfactur = gdMaxfactu.getStringCell(0, "idfactur");
					System.out.println(" Id factura "+ idfactur +" facturass.");
			  }catch(Exception ex){
				System.out.println("Errror al recuperar el idfactur");
			  }
		  
		  return idfactur;
	  }
	
	
	public String fechaMySQL(String fhfechax){
        
		String fhMySql = "";
		try {
			String [] arrFecha = fhfechax.split("/");
			fhMySql = arrFecha[2]+"-"+ arrFecha[1]+"-"+arrFecha[0];
		} catch (Exception e) {
			return "0000-00-00";
		}
		return fhMySql;
    }
    
    public String fechaNormal(String fhfechax){
        
		String fhSpanis = "";
		try {
			String [] arrFecha = fhfechax.split("-");
			fhSpanis = arrFecha[2]+"/"+ arrFecha[1]+"/"+arrFecha[0];
		} catch (Exception e) {
			return "00/00/0000";
		}
		return fhSpanis;
    }
    
    public String fechaSuiza(String fhfechax){
        
		String fhSpanis = "";
		try {
			String [] arrFecha = fhfechax.split("-");
			fhSpanis = arrFecha[2]+"."+ arrFecha[1]+"."+arrFecha[0];
		} catch (Exception e) {
			return "00/00/0000";
		}
		return fhSpanis;
    }
	
}
