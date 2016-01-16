package pdfStamper;

import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

public class PdfStamperExample {

  public static void main(String[] args) {
    try {
      PdfReader pdfReader = new PdfReader("C://DATOS//LRA//tmp//tarjetaIAG.pdf"); 	//PDF FONDO

      PdfStamper pdfStamper = new PdfStamper(pdfReader,
      new FileOutputStream("C://DATOS//LRA//tmp//exit_tarjetaIAG.pdf"));			//PDF RESULTANTE

    /*  Image image = Image.getInstance("C://DATOS//LRA//tmp//logo.png");				//IMAGEN

      for(int i=1; i<= pdfReader.getNumberOfPages(); i++){

          PdfContentByte content = pdfStamper.getUnderContent(i);

          image.setAbsolutePosition(100f, 700f);

      }
      
      pdfStamper.close();*/

      String input = "C://DATOS//LRA//tmp//origen.pdf";		 //PDF FONDO
      String water = "C://DATOS//LRA//tmp//luisito.pdf";  //PDF CODIGO DE BARRAS
      String outpu = "C://DATOS//LRA//tmp//pdfstamper11.pdf";  //PDF RESULTANTE
      
      superposePDF(input,water,outpu);
      
    } catch (IOException e) {
      e.printStackTrace();
    } catch (DocumentException e) {
      e.printStackTrace();
    }
    
  }
    private static void superposePDF(String m, String watermark, String output){
 	   try {
 		   	PdfReader document= new PdfReader(m);
 	      	int num_pages= document.getNumberOfPages();
 	      	PdfReader mark = new PdfReader(watermark);
 	      	Rectangle mark_page_size= mark.getPageSize(1);
 	     	document.eliminateSharedStreams();
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
 	     		writer.setRotateContents(false);
 	     		writer.close( );
 	     	}
 	   } catch( Exception ee ) {
 	   ee.printStackTrace( );
 	   }
 	 }  
}