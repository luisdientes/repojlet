package common.constructors.xls;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.poi.hssf.usermodel.HeaderFooter;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class BasicExcel {

	public Properties p;
	public String pathRepo = "C:/DATOS/tmp/test_excel/";
	public String fileRepo = "generaExcel";
	
	protected 	XSSFWorkbook wb; 		// Libro de Escritura
	private  	XSSFSheet sheet;       	// Hoja de Escritura
	
	public BasicExcel() {
		
		wb = new XSSFWorkbook();
		sheet = wb.createSheet("LRA");
		
	}
	
	public BasicExcel(String filename) {

		fileRepo = filename;
		
		wb = new XSSFWorkbook();
		
	}
	
	public BasicExcel( String pathname, String filename) {

		pathRepo = pathname;
		fileRepo = filename;
		
		wb = new XSSFWorkbook();
		
	}
	
	public BasicExcel(String sheetName, String pathname, String filename) {
		
		pathRepo = pathname;
		fileRepo = filename;
		
		wb = new XSSFWorkbook();
		sheet = wb.createSheet(sheetName);
		
	}
	
	public BasicExcel(String sheetName, String pathname, String filename, String sheetNm) {
		
		pathRepo = pathname;
		fileRepo = filename;
		
		wb = new XSSFWorkbook();
		sheet = wb.createSheet(sheetNm);
		
	}
	
	
	public void CrearHoja(String sheetName) {
		
		sheet = wb.createSheet(sheetName);
		
	}
	
	public int getNumeroHojas() {
		
		System.out.println("AAAA "+ wb.getNumberOfSheets());
		
		return 0;
		
	}
	
	public XSSFCellStyle crearCellStyle() {
		
		XSSFCellStyle cellStyleCorporativo = wb.createCellStyle();
	    
		return cellStyleCorporativo;
	
	}
	
	public XSSFCellStyle getTipoCelda(String alinea) {
    	
    	XSSFCellStyle cellStyleCorporativo = wb.createCellStyle();
		
    	if ((alinea != null) && (alinea.equals("C"))){
			cellStyleCorporativo.setAlignment(CellStyle.ALIGN_CENTER);
		} else if ((alinea != null) && (alinea.equals("D"))){
			cellStyleCorporativo.setAlignment(CellStyle.ALIGN_RIGHT);
		} else {
			cellStyleCorporativo.setAlignment(CellStyle.ALIGN_LEFT);
		}
	    
    	cellStyleCorporativo.setBorderBottom(XSSFCellStyle.BORDER_THIN);
	    cellStyleCorporativo.setBorderRight(XSSFCellStyle.BORDER_THIN);
	    cellStyleCorporativo.setBorderTop(XSSFCellStyle.BORDER_THIN);
	    cellStyleCorporativo.setBorderLeft(XSSFCellStyle.BORDER_THIN);
    	
	    return cellStyleCorporativo;
	    
    }
	
	public XSSFCellStyle crearCellStyle(XSSFColor color, Font font) {
	
		XSSFCellStyle cellStyleCorporativo = wb.createCellStyle();
		
		if (color != null) {
			cellStyleCorporativo.setFillPattern(CellStyle.SOLID_FOREGROUND);
		    cellStyleCorporativo.setFillForegroundColor(color);
		}
		
		
		cellStyleCorporativo.setFont(font);
	    
		return cellStyleCorporativo;
	
	}
	
	public XSSFCellStyle crearCellStyle(XSSFColor color, Font font, String alinea) {

		XSSFCellStyle cellStyleCorporativo = wb.createCellStyle();
		
		if ((alinea != null) && (alinea.equals("C"))){
			cellStyleCorporativo.setAlignment(CellStyle.ALIGN_CENTER);
		} else if ((alinea != null) && (alinea.equals("D"))){
			cellStyleCorporativo.setAlignment(CellStyle.ALIGN_RIGHT);
		} else {
			cellStyleCorporativo.setAlignment(CellStyle.ALIGN_LEFT);
		}
		
		if (color != null) {
		    cellStyleCorporativo.setFillPattern(CellStyle.SOLID_FOREGROUND);
		    cellStyleCorporativo.setFillForegroundColor(color);   
	        cellStyleCorporativo.setFont(font);
		}
		
		cellStyleCorporativo.setBorderBottom(XSSFCellStyle.BORDER_THIN);
	    cellStyleCorporativo.setBorderRight(XSSFCellStyle.BORDER_THIN);
	    cellStyleCorporativo.setBorderTop(XSSFCellStyle.BORDER_THIN);
	    cellStyleCorporativo.setBorderLeft(XSSFCellStyle.BORDER_THIN);
	    
		return cellStyleCorporativo;
	
	}
	
	public XSSFColor crearColor(int red, int green, int blue) {
		
		XSSFColor colorCorporativo = new XSSFColor(new Color(red,green,blue));
		return colorCorporativo;
		
	}
	
	
	
	public Font crearFuente(int fontsize, String fontname, short color, boolean isitalic, boolean isbold) {
		
		Font font = wb.createFont();
	    font.setFontHeightInPoints((short)fontsize);
	    font.setFontName(fontname);
	    font.setItalic(isitalic);
	    font.setBold(isbold);
	    //font.setStrikeout(true);
	    font.setColor(color);
	    
		return font;
	
	}
	
	public void establecerAnchoColumnas(ArrayList<Integer> arAnchoc){
		
		int unit = 256;
		
		for (int i = 0; i < arAnchoc.size(); i++){		
			sheet.setColumnWidth(i, arAnchoc.get(i) * unit);
		}
		
	}
	
	public void combinarCeldas(int firstrow, int lastrowx, int firstcol, int lastcolx){
	
		sheet.addMergedRegion(new CellRangeAddress(
				firstrow, //first row (0-based)
				lastrowx, //last row  (0-based)
				firstcol, //first column (0-based)
				lastcolx  //last column  (0-based)
	    ));
		
	}
	
	public void insertaImagen(String filepath, String nameimgx, int row, int columna){
		
		InputStream is;
		
		try {
			is = new FileInputStream(filepath+nameimgx);
	        byte[] bytes = IOUtils.toByteArray(is);
	        int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
	        is.close();
	        
	        CreationHelper helper = wb.getCreationHelper();

	        try {
	        	Drawing drawing = sheet.createDrawingPatriarch();
	       
		        //add a picture shape
		        ClientAnchor anchor = helper.createClientAnchor();
		        //set top-left corner of the picture,
		        //subsequent call of Picture#resize() will operate relative to it
		        anchor.setRow1(row);
		        anchor.setCol1(columna);
		        org.apache.poi.ss.usermodel.Picture pict = drawing.createPicture(anchor, pictureIdx);

		        //auto-size picture relative to its top-left corner
		        pict.resize();
		        
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	        
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void insertaImagenResize(String filepath, String nameimgx, int row, int columna, int resize){
		
		InputStream is;
		
		try {
			is = new FileInputStream(filepath+nameimgx);
	        byte[] bytes = IOUtils.toByteArray(is);
	        int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
	        is.close();
	        
	        CreationHelper helper = wb.getCreationHelper();

	        try {
	        	Drawing drawing = sheet.createDrawingPatriarch();
	       
		        //add a picture shape
		        ClientAnchor anchor = helper.createClientAnchor();
		        //set top-left corner of the picture,
		        //subsequent call of Picture#resize() will operate relative to it
		        anchor.setRow1(row);
		        anchor.setCol1(columna);
		        org.apache.poi.ss.usermodel.Picture pict = drawing.createPicture(anchor, pictureIdx);

		        //auto-size picture relative to its top-left corner
		        //pict.resize();
		        pict.resize(resize);
		        
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	        
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void rellenarCelda (int fila, int columna, String valor, XSSFCellStyle style){
		try {
			
			XSSFRow row = sheet.getRow(fila);
			if (row == null) {
				row = sheet.createRow(fila);
			}
			XSSFCell cell = row.createCell(columna);
			
			if (style != null){
				cell.setCellStyle(style);			        
			} 
			
			cell.setCellValue(valor);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
	public void rellenarCelda (int fila, int columna, String valor, XSSFCellStyle style, String tipodato){
		try {
			
			DataFormat format = wb.createDataFormat();
			
			XSSFCellStyle tmpstyle = style;
			
			if ((tipodato != null) && (tipodato.equals("E"))) {				//ENTERO
				tmpstyle.setDataFormat(format.getFormat("#,##0"));
			} else if ((tipodato != null) && (tipodato.equals("2D"))) {		//2 DECIMALES
				tmpstyle.setDataFormat(format.getFormat("#,##0.00"));
			} else if ((tipodato != null) && (tipodato.equals("8D"))) {		//8 DECIIMALES
				tmpstyle.setDataFormat(format.getFormat("#,##0.00000000"));
			} else if ((tipodato != null) && (tipodato.equals("2P"))) {		//2 DEC. PORCENTAJE
				tmpstyle.setDataFormat(format.getFormat("##0.00%"));
			} else if ((tipodato != null) && (tipodato.equals("8P"))) {		//8 DEC. PORCENTAJE
				tmpstyle.setDataFormat(format.getFormat("##0.00000000%"));
			} else if ((tipodato != null) && (tipodato.equals("CP"))) {		//5 CEROS IZQUIERDA
				tmpstyle.setDataFormat(format.getFormat("00000"));
			} else if ((tipodato != null) && (tipodato.equals("S"))) {		//STRING
				
			}
			
			XSSFRow row = sheet.getRow(fila);
			if (row == null) {
				row = sheet.createRow(fila);
			}
			XSSFCell cell = row.createCell(columna);
			
			cell.setCellStyle(tmpstyle);			        
			if ((tipodato != null) && ((tipodato.equals("E")) || (tipodato.equals("2D")) || (tipodato.equals("8D")) || (tipodato.equals("2P")) || (tipodato.equals("8P")))) {				//ENTERO
				try {
					cell.setCellValue(Double.parseDouble(valor));
				} catch (Exception e)  {
					cell.setCellValue(valor);
				}
			} else {
				cell.setCellValue(valor);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
	public void establecerZoom(int divisor, int dividendo){
		
		//Ejemplo divisor=3 & dividendo=4 --> 75%		
		//Ejemplo divisor=2 & dividendo=5 --> 40%
		
		sheet.setZoom(divisor,dividendo);
		
	}
	
	
	public void ponerEncabezado(String txheader) {
		
		Header header = sheet.getHeader();
		header.setCenter(txheader);
		
	}
	
	public void ponerPiePagina(String txfooter) {
		
		Footer footer = sheet.getFooter();
		footer.setCenter(txfooter);
		footer.setRight( "Pag. " + HeaderFooter.page() + " / " + HeaderFooter.numPages() );
		
	}
	
	
	public void cerrarLibro() {
		
		try {
			
			if (fileRepo.indexOf("xlsx") == -1){
				fileRepo += ".xlsx";
			}
			
        	FileOutputStream fileOut;
	        fileOut = new FileOutputStream(pathRepo + fileRepo);
	        wb.write(fileOut);
	        fileOut.close();
	        
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}

