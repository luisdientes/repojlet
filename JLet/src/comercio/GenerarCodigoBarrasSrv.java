package comercio;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import utils.PropiedadesJLet;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class GenerarCodigoBarrasSrv {
 
 private static Font fontBold = new Font(Font.FontFamily.COURIER, 12, Font.BOLD);
    private static Font fontNormal = new Font(Font.FontFamily.COURIER, 11, Font.NORMAL);
    
    public GenerarCodigoBarrasSrv(){
     
     String datos[][]=new String[4][3];
     datos[0][0]="1";
     datos[0][1]="Java";
     datos[0][2]="1000";
     datos[1][0]="2";
     datos[1][1]="Zone";
     datos[1][2]="2000";
     datos[2][0]="221";
     datos[2][1]="3123";
     datos[2][2]="1332";
     datos[3][0]="221";
     datos[3][1]="3123";
     datos[3][2]="1332";
     
     try {
    	 generarFactura(datos, "MallProShop - Inventario", "Generación de Códigos de Barras");
     } catch (Exception e) {
    	 e.printStackTrace();
     }
     
     JOptionPane.showMessageDialog(null, "PDF Generado!");
     
    }
    
    public static void main(String arg[]){
    	new GenerarCodigoBarrasSrv();
    }
    
    public void generarFactura(String [][]datos, String nombre, String tituloFactura) throws IOException, DocumentException {
     
    	Rectangle pageSize = new Rectangle(PageSize.A4);
    	pageSize.setBackgroundColor(new BaseColor(255, 255, 255));
    	pageSize.rotate();
        
        Document document = new Document(pageSize,0, 0, 0, 0);
        
        String filepath = PropiedadesJLet.getInstance().getProperty("path.gen.invoice") + String.valueOf(System.currentTimeMillis()) +"_barcode.pdf";
        System.out.println("SE GENERA EN :"+ filepath);
        
        PdfWriter pw = PdfWriter.getInstance(document, new FileOutputStream(filepath));
        document.open();
      
        document.add(getHeader(tituloFactura));
        document.add(getInformation(" "));
        
        PdfPTable table = new PdfPTable(5); 
    
        table.setTotalWidth(document.getPageSize().getWidth());         
        table.setLockedWidth(true);
        
        //float[] medidaCeldas = {1f, 1f, 1f, 1f, 1f};
        //table.setWidths(medidaCeldas);
        
        for(int i=0 ; i<datos.length; i++){
        	for(int j=0; j<datos[0].length; j++){
        		if(datos[i][j]!=null){
        			try {
        				Image img = getBarcode(document, pw, "PI", String.valueOf(i));
        				img.scalePercent(5);
        		        img.setBorder(PdfPCell.NO_BORDER);
        				table.addCell(img);
        			} catch (Exception e) {
        				
        			}
        		}
        	}
        }
                
        document.add(table);
        document.add(getInformation(" "));
        document.add(getInformation("Generada a nombre de "+nombre));
        document.add(getInformation(" "));
        document.add(getInformationFooter("Gracias por visitarnos!"));
      
        document.add(getBarcode(document, pw, "PI", "852"));
        
      document.close();
      
     }
     
     private Document getDocument(){
     Document document = new Document(new Rectangle( getConvertCmsToPoints(13), getConvertCmsToPoints(10)));
       document.setMargins(0, 0, 1, 1);
       return document;
     }
     
     private Paragraph getHeader(String header) {
     Paragraph paragraph = new Paragraph();
    Chunk chunk = new Chunk();
  paragraph.setAlignment(Element.ALIGN_CENTER);
    chunk.append( header + getCurrentDateTime() + "\n");
    chunk.setFont(fontBold);
    paragraph.add(chunk);
    return paragraph;
     }
     
     private Paragraph getInformation(String informacion) {
     Paragraph paragraph = new Paragraph();
     Chunk chunk = new Chunk();
    paragraph.setAlignment(Element.ALIGN_CENTER);
    chunk.append(informacion);
    chunk.setFont(fontNormal);
    paragraph.add(chunk);
     return paragraph;
      }
     
     private Paragraph getInformationFooter(String informacion) {
      Paragraph paragraph = new Paragraph();
      Chunk chunk = new Chunk();
     paragraph.setAlignment(Element.ALIGN_CENTER);
     chunk.append(informacion);
     chunk.setFont(new Font(Font.FontFamily.COURIER, 8, Font.NORMAL));
     paragraph.add(chunk);
      return paragraph;
       }
  
     private PdfPTable getTable() throws DocumentException {
      PdfPTable table = new PdfPTable(3);
      table.setWidths(new int[]{5, 17, 17});
  return table;
     }
     
     private PdfPCell getCell(String text) throws DocumentException, IOException {
      Chunk chunk = new Chunk();
      chunk.append(text);
      chunk.setFont(fontNormal);
      PdfPCell cell = new PdfPCell(new Paragraph(chunk));
   cell.setHorizontalAlignment(Element.ALIGN_LEFT);
   cell.setBorder(Rectangle.NO_BORDER);
   return cell;
     }
     
     private float getConvertCmsToPoints(float cm) {
      return cm * 28.4527559067f;
     }
     
     private String getCurrentDateTime() {
      Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yy '-' hh:mm");
      return ft.format(dNow);
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
    	return image;
    }
    
    private static String addZeroLeft(String num) {
    	NumberFormat formatter = new DecimalFormat("0000000");
    	return formatter.format((num != null) ? Integer.parseInt(num) : 0000000);
    }

    public void imprimirFactura(){
     
    	Desktop d=Desktop.getDesktop();
    	try {
    		if(Desktop.isDesktopSupported()){
    			d.print(new File("factura.pdf"));
    		}
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
     
   }
    
}