package common;

import jxl.biff.DisplayFormat;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WriteException;

public class Formato {
	
	

	WritableCellFormat formato; 
	WritableFont fuente;
	
	public  Colour COLOR_BLANCO   		= Colour.WHITE;
	public  Colour COLOR_AUTOMATICO   	= Colour.AUTOMATIC;
    public  Colour COLOR_ROJO  			= Colour.RED;
    public  Colour COLOR_NEGRO  		= Colour.BLACK;
    public  Colour COLOR_VERDE	 		= Colour.GREEN;
    public  Colour COLOR_VERDE_CLARO 	= Colour.BRIGHT_GREEN;
    public  Colour COLOR_AZUL_OSCURO 	= Colour.DARK_BLUE2;
    public  Colour COLOR_AZUL		 	= Colour.BLUE_GREY;
	public  Colour COLOR_NARANJA  		= Colour.ORANGE;
	public  Colour COLOR_MARRON 		= Colour.BROWN;
	public  Colour COLOR_AMARILLO	  	= Colour.YELLOW;
	public  Colour COLOR_GRIS_CLARO  	= Colour.GREY_25_PERCENT;
	public  Colour COLOR_GRIS_OSCURO 	= Colour.GREY_50_PERCENT;
	public  Colour COLOR_SALMON 		= Colour.TAN;
	
	public  Border BORDE_ARRIBA      = Border.TOP;
	public  Border BORDE_ABAJO       = Border.BOTTOM;
	public  Border BORDE_IZQUIERDA   = Border.LEFT;
	public  Border BORDE_DERECHA     = Border.RIGHT;
	public  Border BORDE_NINGUNO     = Border.NONE;
	public  Border BORDE_TODOS       = Border.ALL;
	
	public  BorderLineStyle BORDE_ESTILO_DOBLE  = BorderLineStyle.DOUBLE;
	public  BorderLineStyle BORDE_ESTILO_MEDIUM = BorderLineStyle.MEDIUM;
	public  BorderLineStyle BORDE_ESTILO_DDD    = BorderLineStyle.DASH_DOT_DOT;
	public  BorderLineStyle BORDE_ESTILO_PUNTOS = BorderLineStyle.HAIR;
	public  BorderLineStyle BORDE_ESTILO_FINO = BorderLineStyle.THIN;
	
	public  Alignment ALINEAR_H_CENTRADO  = Alignment.CENTRE;
	public  Alignment ALINEAR_H_IZQUIERDA = Alignment.LEFT;
	public  Alignment ALINEAR_H_DERECHA   = Alignment.RIGHT;
	public  Alignment ALINEAR_H_FILL      = Alignment.FILL;
	
	public  VerticalAlignment ALINEAR_V_ARRIBA      = VerticalAlignment.TOP;
	public  VerticalAlignment ALINEAR_V_ABAJO       = VerticalAlignment.BOTTOM;
	public  VerticalAlignment ALINEAR_V_CENTRADO    = VerticalAlignment.CENTRE;
	public  VerticalAlignment ALINEAR_V_JUSTIFICADO = VerticalAlignment.JUSTIFY;
	
	public  UnderlineStyle SUBRAYADO_SIMPLE    = UnderlineStyle.SINGLE;
	public  UnderlineStyle SUBRAYADO_DOBLE     = UnderlineStyle.DOUBLE;
	public  UnderlineStyle SUBRAYADO_PUNTEADO  = UnderlineStyle.SINGLE_ACCOUNTING;
	public  UnderlineStyle SUBRAYADO_NONE      = UnderlineStyle.NO_UNDERLINE;
	
	public  DisplayFormat ENTERO 		   			    = new NumberFormat("##0;-##0");
	public  DisplayFormat ENTERO_CON_SEPARADOR 			= new NumberFormat("#,##0;-#,##0");
	public  DisplayFormat DECIMAL_CON_SEPARADOR_2DEC 		= new NumberFormat("#,##0.00;-#,##0.00");
	public  DisplayFormat DECIMAL_CON_SEPARADOR_8DEC 		= new NumberFormat("#,##0.00000000;-#,##0.00000000");
	public  DisplayFormat DECIMAL_CON_SEPARADOR_10DEC 		= new NumberFormat("#,##0.0000000000;-#,##0.0000000000");
	public  DisplayFormat PORCENTAJE_CON_SEPARADOR_2DEC 	= new NumberFormat("#,##0.00%;-#,##0.00%");
	public  DisplayFormat PORCENTAJE_CON_SEPARADOR_6DEC 	= new NumberFormat("#,##0.000000%;-#,##0.000000%");
	public  DisplayFormat SINPORCENTAJE	= new NumberFormat("#,##0;-#,##0");
	
	public  boolean CURSIVA    = true;
	public  boolean NO_CURSIVA = false;
	
    public Formato() {
    	formato = new WritableCellFormat(); 
    	fuente = new WritableFont(WritableFont.ARIAL);   	
		formato.setFont(fuente);
    }
    
    protected WritableCellFormat crearFormato() {    	
    	
    	return formato;
    }

    public void copiaFormato(DisplayFormat c){
    	formato=new WritableCellFormat(c);
    	formato.setFont(fuente);
    }
    
	public void ponColorFuente(Colour color){
		try {
			fuente.setColour(color);
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
	
	public void ponLetraNegrita(){
		try {
			fuente.setBoldStyle(WritableFont.BOLD);
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
	
	public void ponLetraNormal(){
		try {
			fuente.setBoldStyle(WritableFont.NO_BOLD);
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
	
	public void ponLetraGrande(){
		try {
			fuente.setPointSize(16);
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
	
	
	public void ponCursiva(boolean cursiva){
		try {
			fuente.setItalic(cursiva);
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
	
	public void ponSubrayado(UnderlineStyle underLine){
		try {
			fuente.setUnderlineStyle(underLine);
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
	
	public void ponColorCelda(Colour color){
		try {
			formato.setBackground(color);
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
	
	public void ponAlineacionHorizontal(Alignment halinear){
		try {
			formato.setAlignment(halinear);
		} catch (WriteException e) {
			e.printStackTrace();
		}
		
	}
	
	public void ponAlineacionVertical(VerticalAlignment valinear){
		try {
			formato.setVerticalAlignment(valinear);
		} catch (WriteException e) {
			e.printStackTrace();
		}	
	}
	
	public void ponBordes(Border borde, BorderLineStyle bol, Colour bordeColor){
		try {
			formato.setBorder(borde, bol, bordeColor);
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
		
	public WritableCellFormat getEstiloCabeceraPrincipal(){
		
		try {
			crearFormato();
			
			formato.setBorder(Border.ALL,BorderLineStyle.NONE);
			formato.setAlignment(Alignment.CENTRE);
			formato.setBackground(Colour.BLUE);
    		formato.setWrap(true);    	
    		fuente.setColour(Colour.WHITE);
    		fuente.setItalic(true);
    		fuente.setUnderlineStyle(UnderlineStyle.DOUBLE); 
    		formato.setFont(fuente);
    		
		} catch (WriteException e) {
			System.out.println("Se ha producido un Error al Establecer un Fromato Estandar");
			e.printStackTrace();
		}
        return formato;
	}
	
	public  String ponPuntosAEntero(int entero){
		String enteroS=Integer.toString(entero);
		int longitud= enteroS.length();
		int i=0;
		int pongoMarca=0;
		String resultado="";
		while (i<longitud){
			if (pongoMarca==3){
				resultado="."+resultado;
				pongoMarca=0;
			}
			resultado=enteroS.charAt(longitud-i-1)+resultado;
			pongoMarca++;
			i++;
		}
		return resultado;
	}
	
	
	
	
	/*
	public HSSFCellStyle getEstiloCabeceraSecundaria(){
		
		crearEstilo();
		style.setBorderTop((short)this.CELDA_SIN_BORDES);
		style.setBorderLeft((short)this.CELDA_SIN_BORDES);
		style.setBorderBottom((short)this.CELDA_SIN_BORDES);
		style.setBorderRight((short)this.CELDA_SIN_BORDES);
		style.setAlignment((short)this.CELDA_ALINEACION_H_CENTRADO);
		style.setVerticalAlignment((short)this.CELDA_ALINEACION_V_CENTRO);
		style.setFillPattern((short)1);
		style.setFillForegroundColor((short)this.CELDA_COLOR_SALMON);
    	style.setDataFormat(wb.createDataFormat().getFormat(this.CELDA_FORMATO_TEXTO));
    	style.setWrapText(true);
    	crearFuente();
    	font.setColor((short)this.COLOR_FUENTE_AZUL);
		font.setBoldweight((short)this.FUENTE_NEGRITA);
		font.setItalic(true);
		font.setUnderline(this.FUENTE_SUBRAYADA_NO);
        style.setFont(font);
        return style;
	}
	
	public HSSFCellStyle getEstiloRelleno(){
		
		crearEstilo();
		style.setBorderTop((short)this.CELDA_SIN_BORDES);
		style.setBorderLeft((short)this.CELDA_SIN_BORDES);
		style.setBorderBottom((short)this.CELDA_SIN_BORDES);
		style.setBorderRight((short)this.CELDA_SIN_BORDES);
		style.setAlignment((short)this.CELDA_ALINEACION_H_CENTRADO);
		style.setVerticalAlignment((short)this.CELDA_ALINEACION_V_CENTRO);
		style.setFillPattern((short)1);
		style.setFillForegroundColor((short)this.CELDA_COLOR_AMARILLO);
    	style.setDataFormat(wb.createDataFormat().getFormat(this.CELDA_FORMATO_TEXTO));
    	style.setWrapText(true);
    	crearFuente();
    	font.setColor((short)this.COLOR_FUENTE_AZUL);
		font.setBoldweight((short)this.FUENTE_NORMAL);
		font.setItalic(true);
		font.setUnderline(this.FUENTE_SUBRAYADA_NO);
        style.setFont(font);
        return style;
	}
	*/
}
