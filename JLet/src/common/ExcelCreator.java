package common;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;

import javax.imageio.ImageIO;

import jxl.HeaderFooter;
import jxl.HeaderFooter.Contents;
import jxl.SheetSettings;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.PageOrientation;
import jxl.write.Boolean;
import jxl.write.DateTime;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExcelCreator {
 
	protected WritableWorkbook workbook; 	// Libro de Escritura
	private WritableSheet sheet;       		// Hoja de Escritura
	private Label label;			   		// Escribir un Texto
	private Number numero;			   		// Escribir un Numero
	private DateTime fecha;            		// Escribir un Fecha
	private Boolean booleano;          		// Escribir un Boolean
	private Formula formulas;		   		// Escribir una Formula
	private WritableImage imagenes;    		// Incrustar una Imagen
	
	private int sheetNumber = 0;
	
	Formato					formatoCabecera;
	Formato					formatoEntero;
	Formato					formatoEnteroN;
	Formato					formatoIzquierda;
	Formato					formatoCentro;
	Formato					formatoNegrita;
	Formato					formato2Decimal;
	Formato					formato2DecimalGris;
	
	
	
	Formato					formatoEnteroI;	
	Formato					formatoCentral;
	
	
	//HEREDADO cmcLRA
	Formato					styleNormal;
	Formato					styleCentradoBordeIzq;
	Formato					styleCentradoBordeIzqYAbajo;
	Formato					styleCentrado;
	Formato					styleCentradoCursiva;
	Formato					styleCentradoNegrita;
	Formato					styleCab;
	Formato					styleNum;
	Formato					styleNumBordeIzq;
	Formato					styleNumGris;
	Formato					stylePor;
	Formato					stylePorGris;
	
	 
	public int HEADER_FOOTER_IZQUIERDA = 0;
	public int HEADER_FOOTER_CENTRO = 1;
	public int HEADER_FOOTER_DERECHA = 2; 

	public ExcelCreator(){
	}
	
	public void crearLibro(String fileName){
		
		try {
			WorkbookSettings ws = new WorkbookSettings();
			Locale l = Locale.ENGLISH;
			ws.setLocale(l);
			workbook = Workbook.createWorkbook(new File(fileName),ws);
		} catch (IOException e) {
			System.out.println("[ CreadorDeExcel ] Se Ha producido un error al intentar crear el Fichero Excel");
			e.printStackTrace();
		}
	}
	
	public void cerrarLibro(){
		try {
			workbook.write();
			workbook.close();
		} catch (Exception e) {
			System.out.println("Se ha producido un Error al Finalizar el Documento");
			e.printStackTrace();
		}
	}
	
	public void crearHoja(String sheetName){
		try{
		sheet = workbook.createSheet(sheetName, sheetNumber);
        sheetNumber ++;
		}catch(Exception e){
			System.out.println("El número de hojas es " + sheetNumber);
			e.printStackTrace();
		}
	}
	
	public void crearHoja(String sheetName, int formato){

		try{
		// TODO: Hay que ver como identificar un Formato a traves de un Entero
		sheet = workbook.createSheet(sheetName, sheetNumber);
		}catch(Exception e){
			
			e.printStackTrace();
		}
	}
	
	public void definirLongitudCelda(int numberColum, int size){
		sheet.setColumnView(numberColum, size);
	}
	
	public void rellenarCelda (int fila, int columna, String valor, Formato f){
		try {
			if (f != null){
				label = new Label(columna, fila, valor, f.crearFormato());
			}
			else {
				label = new Label(columna, fila, valor);
			}
			sheet.addCell(label);
		} catch (RowsExceededException e) {
			System.out.println("Se ha Excedido el Numero de Columnas");
			e.printStackTrace();
		} catch (WriteException e) {
			System.out.println("Error al escribir en la Celda");
			e.printStackTrace();
		}
	}
	
	public void rellenarCelda (int fila, int columna, Formula valor, Formato f){
		try {			
			sheet.addCell(valor);
		} catch (RowsExceededException e) {
			System.out.println("Se ha Excedido el Numero de Columnas");
			e.printStackTrace();
		} catch (WriteException e) {
			System.out.println("Error al escribir en la Celda");
			e.printStackTrace();
		}
	}
	
	public void rellenarCelda (int fila, int columna, int valor, Formato f){
		
		try {
			if (f != null){
				numero = new Number(columna, fila, valor, f.crearFormato());
			}
			else {
				numero = new Number(columna, fila, valor);
			}
			sheet.addCell(numero);
		} catch (RowsExceededException e) {
			System.out.println("Se ha Excedido el Numero de Columnas");
			e.printStackTrace();
		} catch (WriteException e) {
			System.out.println("Error al escribir en la Celda");
			e.printStackTrace();
		}
	}
	
	public void rellenarCelda (int fila, int columna, BigDecimal valor, Formato f){
		
		try {
			if (f != null){
				numero = new Number(columna, fila, valor.doubleValue(), f.crearFormato());
			}
			else {
				numero = new Number(columna, fila, valor.doubleValue());
			}
			sheet.addCell(numero);
		} catch (RowsExceededException e) {
			System.out.println("Se ha Excedido el Numero de Columnas");
			e.printStackTrace();
		} catch (WriteException e) {
			System.out.println("Error al escribir en la Celda");
			e.printStackTrace();
		}
	}

	public void rellenarCelda (int fila, int columna, boolean valor, Formato f){
		
		try {
			if (f != null){
				booleano = new Boolean(columna, fila, valor, f.crearFormato());
			}
			else {
				booleano = new Boolean(columna, fila, valor);
			}
			sheet.addCell(booleano);
		} catch (RowsExceededException e) {
			System.out.println("Se ha Excedido el Numero de Columnas");
			e.printStackTrace();
		} catch (WriteException e) {
			System.out.println("Error al escribir en la Celda");
			e.printStackTrace();
		}
	}
		
	public void rellenarCelda (int fila, int columna, Date valor, Formato f){
		
		try {
			if (f != null){
				fecha = new DateTime(columna, fila, valor, f.crearFormato());
			}
			else {
				fecha = new DateTime(columna, fila, valor);
			}
			sheet.addCell(fecha);
		} catch (RowsExceededException e) {
			System.out.println("Se ha Excedido el Numero de Columnas");
			e.printStackTrace();
		} catch (WriteException e) {
			System.out.println("Error al escribir en la Celda");
			e.printStackTrace();
		}
	}
	
	public void rellenarFormula(int fila, int columna, String formula,Formato formato){
		
		try {
			if (formato!=null){
				formulas = new Formula(columna, fila, formula,formato.crearFormato());
			}
			else{
				formulas = new Formula(columna,fila,formula);
			}
			sheet.addCell(formulas);
		} catch (RowsExceededException e) {
			System.out.println("Se ha Excedido el Numero de Columnas");
			e.printStackTrace();
		} catch (WriteException e) {
			System.out.println("Error al escribir en la Celda");
			e.printStackTrace();
		}
	}
	
	public void combinarCeldas(int filaIni, int columnaIni, int filaFin, int columnaFin){
		try {
			sheet.mergeCells(columnaIni, filaIni, columnaFin, filaFin);
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
		
	public String dameContenidoCelda(int fila, int columna){
		return sheet.getCell(columna,fila).getContents();
	}
	
	public void ponEncabezado(String encabezado, int eleccionEncabezado, boolean cursiva, boolean negrita){
		SheetSettings propiedades = sheet.getSettings();
		HeaderFooter hf=propiedades.getHeader();
		Contents s;
		switch (eleccionEncabezado){
			case 0:
				s=hf.getLeft();
				break;
			case 1:
				s=hf.getCentre();
				break;
			case 2:
				s=hf.getRight();
				break;
			default: s=null;
		}
		if (cursiva){
			s.toggleItalics();
		}
		if (negrita){
			s.toggleBold();
		}
		s.append(encabezado);
		propiedades.setHeader(hf);
	}
	
	public void ponPie(String pie, int eleccionPie, boolean cursiva, boolean negrita){
		SheetSettings propiedades = sheet.getSettings();
		HeaderFooter hf=propiedades.getFooter(); 
		Contents s;
		switch (eleccionPie){
			case 0:
				s=hf.getLeft();
				break;
			case 1:
				s=hf.getCentre();
				break;
			case 2:
				s=hf.getRight();
				break;
			default: s=null;
		}
		if (cursiva){
			s.toggleItalics();
		}
		if (negrita){
			s.toggleBold();
		}
		s.append(pie);
		propiedades.setFooter(hf);
	}
	
	public void renombraHoja(String nombreAntiguo, String nombreNuevo){
		workbook.getSheet(nombreAntiguo).setName(nombreNuevo);
	}
	
	public void quitarTrama (){
		SheetSettings propiedades = sheet.getSettings();
		propiedades.setShowGridLines(false);
	}

	public void ponEscala(int tantoPorCiento){
		SheetSettings propiedades = sheet.getSettings();
		propiedades.setScaleFactor(tantoPorCiento);
	}
	
	public void ponerApaisado(boolean apaisado){
		SheetSettings propiedades = sheet.getSettings();
		if (apaisado){
			propiedades.setOrientation(PageOrientation.LANDSCAPE);
		}
		else{
			propiedades.setOrientation(PageOrientation.LANDSCAPE);
		}
	}
	
	public void inicializaEstilos() {
		Formato f = new Formato();
		
		formatoCabecera = new Formato();
		formatoCabecera.ponAlineacionHorizontal(f.ALINEAR_H_CENTRADO);
		formatoCabecera.ponColorCelda(f.COLOR_AZUL);
		formatoCabecera.ponBordes(f.BORDE_TODOS, f.BORDE_ESTILO_FINO, f.COLOR_BLANCO);
		formatoCabecera.ponColorFuente(f.COLOR_BLANCO);
		formatoCabecera.ponLetraNegrita();			
		
		formatoEntero = new Formato();
		formatoEntero.ponAlineacionHorizontal(f.ALINEAR_H_DERECHA);
		formatoEntero.copiaFormato(f.ENTERO_CON_SEPARADOR);
		formatoEntero.ponBordes(f.BORDE_ARRIBA, f.BORDE_ESTILO_FINO, f.COLOR_NEGRO);
		formatoEntero.ponBordes(f.BORDE_IZQUIERDA, f.BORDE_ESTILO_FINO, f.COLOR_NEGRO);
		formatoEntero.ponBordes(f.BORDE_DERECHA, f.BORDE_ESTILO_FINO, f.COLOR_NEGRO);
		formatoEntero.ponBordes(f.BORDE_ABAJO, f.BORDE_ESTILO_FINO, f.COLOR_NEGRO);
		
		formatoEnteroN = new Formato();
		formatoEnteroN.ponAlineacionHorizontal(f.ALINEAR_H_DERECHA);
		formatoEnteroN.copiaFormato(f.ENTERO_CON_SEPARADOR);
		formatoEnteroN.ponColorCelda(f.COLOR_GRIS_CLARO);
		formatoEnteroN.ponBordes(f.BORDE_ARRIBA, f.BORDE_ESTILO_FINO, f.COLOR_NEGRO);
		formatoEnteroN.ponBordes(f.BORDE_IZQUIERDA, f.BORDE_ESTILO_FINO, f.COLOR_NEGRO);
		formatoEnteroN.ponBordes(f.BORDE_DERECHA, f.BORDE_ESTILO_FINO, f.COLOR_NEGRO);
		formatoEnteroN.ponBordes(f.BORDE_ABAJO, f.BORDE_ESTILO_FINO, f.COLOR_NEGRO);
		formatoEnteroN.ponLetraNegrita();
		
		formatoIzquierda = new Formato();
		formatoIzquierda.ponAlineacionHorizontal(f.ALINEAR_H_IZQUIERDA);
		formatoIzquierda.ponBordes(f.BORDE_ARRIBA, f.BORDE_ESTILO_FINO, f.COLOR_NEGRO);
		formatoIzquierda.ponBordes(f.BORDE_IZQUIERDA, f.BORDE_ESTILO_FINO, f.COLOR_NEGRO);
		formatoIzquierda.ponBordes(f.BORDE_DERECHA, f.BORDE_ESTILO_FINO, f.COLOR_NEGRO);
		formatoIzquierda.ponBordes(f.BORDE_ABAJO, f.BORDE_ESTILO_FINO, f.COLOR_NEGRO);

		formatoCentro = new Formato();
		formatoCentro.ponAlineacionHorizontal(f.ALINEAR_H_CENTRADO);
		formatoCentro.ponBordes(f.BORDE_ARRIBA, f.BORDE_ESTILO_FINO, f.COLOR_NEGRO);
		formatoCentro.ponBordes(f.BORDE_IZQUIERDA, f.BORDE_ESTILO_FINO, f.COLOR_NEGRO);
		formatoCentro.ponBordes(f.BORDE_DERECHA, f.BORDE_ESTILO_FINO, f.COLOR_NEGRO);
		formatoCentro.ponBordes(f.BORDE_ABAJO, f.BORDE_ESTILO_FINO, f.COLOR_NEGRO);
		
		formatoNegrita = new Formato();
		formatoNegrita.ponAlineacionHorizontal(f.ALINEAR_H_IZQUIERDA);
		formatoNegrita.ponBordes(f.BORDE_ARRIBA, f.BORDE_ESTILO_FINO, f.COLOR_NEGRO);
		formatoNegrita.ponBordes(f.BORDE_IZQUIERDA, f.BORDE_ESTILO_FINO, f.COLOR_NEGRO);
		formatoNegrita.ponBordes(f.BORDE_DERECHA, f.BORDE_ESTILO_FINO, f.COLOR_NEGRO);
		formatoNegrita.ponBordes(f.BORDE_ABAJO, f.BORDE_ESTILO_FINO, f.COLOR_NEGRO);
		
		formato2Decimal = new Formato();
		formato2Decimal.ponAlineacionHorizontal(f.ALINEAR_H_DERECHA);
		formato2Decimal.copiaFormato(f.DECIMAL_CON_SEPARADOR_2DEC);
		formato2Decimal.ponBordes(f.BORDE_ARRIBA, f.BORDE_ESTILO_FINO, f.COLOR_NEGRO);
		formato2Decimal.ponBordes(f.BORDE_IZQUIERDA, f.BORDE_ESTILO_FINO, f.COLOR_NEGRO);
		formato2Decimal.ponBordes(f.BORDE_DERECHA, f.BORDE_ESTILO_FINO, f.COLOR_NEGRO);
		formato2Decimal.ponBordes(f.BORDE_ABAJO, f.BORDE_ESTILO_FINO, f.COLOR_NEGRO);
		
		formato2DecimalGris = new Formato();
		formato2DecimalGris.ponAlineacionHorizontal(f.ALINEAR_H_DERECHA);
		formato2DecimalGris.copiaFormato(f.DECIMAL_CON_SEPARADOR_2DEC);
		formato2DecimalGris.ponColorCelda(f.COLOR_GRIS_CLARO);
		formato2DecimalGris.ponBordes(f.BORDE_ARRIBA, f.BORDE_ESTILO_FINO, f.COLOR_NEGRO);
		formato2DecimalGris.ponBordes(f.BORDE_IZQUIERDA, f.BORDE_ESTILO_FINO, f.COLOR_NEGRO);
		formato2DecimalGris.ponBordes(f.BORDE_DERECHA, f.BORDE_ESTILO_FINO, f.COLOR_NEGRO);
		formato2DecimalGris.ponBordes(f.BORDE_ABAJO, f.BORDE_ESTILO_FINO, f.COLOR_NEGRO);
								
	}	
	
	public Formato getformatoCabecera(){		
		return formatoCabecera;
	}	
	
	public Formato getformatoIzquierda(){		
		return formatoIzquierda;
	}

	public Formato getformatoEntero(){		
		return formatoEntero;
	}
	
	public Formato getformatoEnteroI(){		
		return formatoEnteroI;
	}
	
	public Formato getformatoCentral(){		
		return formatoCentral;
	}
	
	public Formato getformatoCentro(){
		return formatoCentro;
	}
	
	public Formato getformatoNegrita(){
		return formatoNegrita;
	}
	
	public Formato getformato2Decimal(){
		return formato2Decimal;
	}
	
	public Formato getformato2DecimalGris(){
		return formato2DecimalGris;
	}
	
	public void incluirImagen (double fila, double columna, double alto, double ancho, File imagen){
		if (alto==0 || ancho==0){
		BufferedImage input;
		try {
			input = ImageIO.read(imagen);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(input, "PNG", baos);
			imagenes = new WritableImage(columna,fila,input.getWidth()/64,input.getHeight()/17,baos.toByteArray());
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			imagenes = new WritableImage(columna, fila, ancho, alto, imagen);
			}
		sheet.addImage(imagenes);
		
	}
	
	public void incluirImagen (int fila, int columna, int alto, int ancho, File imagen){
		if (alto==0 || ancho==0){
			BufferedImage input;
			try {
				input = ImageIO.read(imagen);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(input, "PNG", baos);
				imagenes = new WritableImage(columna,fila,input.getWidth(),input.getHeight(),baos.toByteArray());
				
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else{
				imagenes = new WritableImage(columna, fila, ancho, alto, imagen);
				}
			sheet.addImage(imagenes);
			
				
			
		}

	
	
	
}
