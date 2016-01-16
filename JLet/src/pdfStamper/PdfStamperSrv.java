package pdfStamper;

import java.io.FileOutputStream;
import java.io.IOException;
import arquitectura.controller.Service;
import arquitectura.objects.ObjectIO;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;


public class PdfStamperSrv extends Service {
	
	//PARÁMETROS DE ENTRADA
	String fileorig = "";
	String filecrea = "";
	
	//OTROS PARÁMETROS

	
	      
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("fileorig");
						
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
			fileorig = input.getStringValue("fileorig");
		} catch (Exception e1) {
			System.err.println(this.getClass().getName() +"- ERROR - "+ e1.getMessage());
			e1.printStackTrace();
		}
		
	}
	
    public void process(ObjectIO input, ObjectIO output){
    	recuperaInput(input);
    	
    	try{
    
    	   	String pdfinp = "C://DATOS//LRA//tmp//origen2.pdf";		 //PDF FONDO
			String water = "C://DATOS//LRA//tmp//BarCode_1_codigoBARRAS.pdf";  //PDF CODIGO DE BARRAS
			String outpu = "C://DATOS//LRA//tmp///pdfstamper33.pdf";  //PDF RESULTANTE
			
			superposePDF(pdfinp,water,outpu);
    	 } catch (Exception e) {
    	      e.printStackTrace();
    	 } 
	    	
    }
    
    private static void superposePDF(String m, String watermark, String output){
  	   try {
  	      PdfReader document= new PdfReader(m);
  	      int num_pages= document.getNumberOfPages();
  	      PdfReader mark = new PdfReader(watermark);
  	     Rectangle mark_page_size= mark.getPageSize(1);
  	     PdfStamper writer = new PdfStamper(document, new FileOutputStream(output));
  	     PdfImportedPage mark_page = writer.getImportedPage(mark, 1);
  	     for( int ii= 0; ii< num_pages; ) {
  	       ++ii;
  	       Rectangle doc_page_size= document.getPageSize( ii );
  	       float h_scale= doc_page_size.getWidth()/mark_page_size.getWidth();
  	       float v_scale= doc_page_size.getHeight()/mark_page_size.getHeight();
  	       float mark_scale= (h_scale< v_scale) ? h_scale : v_scale;
  	       float h_trans = (float)((doc_page_size.getWidth()-
  	       mark_page_size.getWidth( )* mark_scale)/2.0);
  	       float v_trans= (float)((doc_page_size.getHeight( )-
  	       mark_page_size.getHeight( )* mark_scale)/2.0);
  	      PdfContentByte contentByte= writer.getOverContent(ii);
  	      contentByte.addTemplate( mark_page,
  	      mark_scale, 0,
  	      0, mark_scale,
  	      h_trans, v_trans );
  	      writer.close( );
  	    }
  	   } catch( Exception ee ) {
  	   ee.printStackTrace( );
  	   }
  	 }
}