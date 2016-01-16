package utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

  
public class DownloadFile extends HttpServlet {

    public DownloadFile(){
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
    	doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	
    	 String idusuari = "";
    	 String pathfile = "";
    	 String idemisor = "";
    	 String filename = "";
    	 String tipofile = "";
    	 String directcl = "";
	     ServletOutputStream out = response.getOutputStream();
	    
	     idusuari = request.getParameter("idusuari");
	     pathfile = request.getParameter("pathfile");
	     idemisor = request.getParameter("idemisor");
	     filename = request.getParameter("filename");
	     tipofile = request.getParameter("tipofile");
	     directcl = request.getParameter("directcl");
	     
	     try {
		    
	    	 if (tipofile.equals("FRA")) {
	    		 pathfile = PropiedadesJLet.getInstance().getProperty("path.gen.invoice");
	    		 pathfile+= "emisor_"+ idemisor +"/";
    		 } else  if (tipofile.equals("REC")) {
	    		 pathfile = PropiedadesJLet.getInstance().getProperty("path.gen.recibo");
	    		 pathfile+= "emisor_"+ idemisor +"/";
    		 } else if (tipofile.equals("CLO")) {
	    		 pathfile = PropiedadesJLet.getInstance().getProperty("path.gen.cloud");
	    		 pathfile+= "emisor_"+ idemisor +"/"+directcl;
	    	 } else if (tipofile.equals("XLSX")) {
	    		 pathfile = PropiedadesJLet.getInstance().getProperty("files.path");
	    		 //pathfile+= "emisor_"+ idemisor +"/"+directcl;
	    	 } else if ((pathfile != null) && (pathfile.equals(""))){
	    		 pathfile = PropiedadesJLet.getInstance().getProperty("path.gen.invoice");
	    	 }
	    	 
	    	 if (tienePermiso(idusuari,tipofile,filename)){
	    	
	    		 String rutafich = pathfile + filename;
	    		 String nameopen = "FRA_JLet_"+ filename;
	    		 	    		 
	    		 System.out.println("FLAG - RUTA FICHERO - "+ rutafich);
	    	     System.out.println("FLAG - NOMBRE OPEN - "+ nameopen);
	    		 
	    	     File file = new File(rutafich);
		     		
			     if ( file.length()!=0){
			    	 response.reset();
			    	response.setHeader("Content-Disposition", "attachment;filename=\"" + nameopen + "\"");
			   		// response.setHeader("Content-Disposition", "filename=\"" + nameopen + "\"");
			   	//	 response.setBufferSize( (int) file.length() );
			    	 
			    	 if (rutafich.endsWith(".zip")){
			    		 response.setHeader("Content-Type", "application/zip");
			    		 response.setHeader("Cache-Control", "cache");
			    	 } else {
			    	 response.setHeader("pragma", "no-cache");        
			    	 response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
			    	 response.setHeader("Expires", "01 Apr 1995 01:10:10 GMT");
			    	 }
			    	 
			    	 if (rutafich.endsWith(".pdf")){
			    		 response.setHeader("Content-Type", "application/pdf");
			    	 } else if (rutafich.endsWith(".xls")){
			    		 response.setHeader("Content-Type", "application/vnd.ms-excel");
			    	 }else if (rutafich.endsWith(".xlsx")){
			    		 response.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			    	 } else if (rutafich.endsWith(".doc")){
			    		 response.setHeader("Content-Type", "application/msword");
			    	 } else if (rutafich.endsWith(".png")){
			    		 response.setHeader("Content-Type", "image/png");
			    	 } else if (rutafich.endsWith(".iso")){
			    		 response.setHeader("Content-Type", "application/iso");
			    		// response.setHeader("Cache-Control", "cache");
			    	 }
			    	 
			    	 
			    	 ServletOutputStream stream = response.getOutputStream();
			    	 BufferedInputStream buf = new BufferedInputStream(new FileInputStream (file));
			         int readBytes = 0;

			         while ((readBytes = buf.read()) != -1) {
			             stream.write(readBytes);
			         }

			         stream.flush();
			         stream.close();
			    	 
			    	 
			    	 
			    	 
			    	 /*
			    	 byte[] buf= null;
			    	 
			    	 try{
			
			    		 buf =  new byte[(int)file.length()];              
			    	 }catch(Exception ex){
			    		 System.out.println("ERROR BUFFER--------"+file.length());
			    	 }
			    	 int length; 
			    	 BufferedInputStream fileInBuf = null;
			
			    	 fileInBuf = new BufferedInputStream(new FileInputStream (file));
			
			    	 ByteArrayOutputStream baos = new ByteArrayOutputStream();
			    	 
			    	 while((length = fileInBuf.read(buf)) > 0) {
			    		 baos.write(buf, 0, length);
			    	 }
			
			    	 response.setBufferSize(baos.size());        
			    	 response.setContentLength(baos.size());
			    	 
			    	out.write(baos.toByteArray());
			    	 		    	 
			    	 fileInBuf.close();
			    	 
			    	 
			    	 out.flush();
			    	 out.close();
			    	 */
		     	}
	    		 
	    		 
	    	 }
		     
		     
	     } catch (Exception e){
	    	 System.out.println(" - ERROR - Download Servlet - Al recuperar la propiedad files.path:"+ e.getMessage());
	     }
    }
    
    public boolean tienePermiso(String idusuari, String tipofile, String filename){
    	return true;
    }

}