package utils;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletImage extends HttpServlet {


    public ServletImage(){
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
    	doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    	String imgdefaul = "noimage.png";
    	String rutafiles = null;
    	
    	String imgnamex = "";
    	String tipoimgx = "";
    	String idemisor = "1";
    	String rutafile = "";
    	
    	imgnamex = request.getParameter("imagenam");
    	tipoimgx = request.getParameter("tipoimgx");
    	
    	//No montamos esta entrada por seguridad
    	rutafile = request.getParameter("rutafile");
    	
    	
	    ServletOutputStream out = response.getOutputStream();
	    
	    response.setHeader("pragma", "no-cache");
		response.setHeader("Content-Disposition", "filename=\"" + imgnamex + "\"");
		
		if (imgnamex.endsWith(".png")){
			response.setHeader("Content-Type", "image/png");
		} else if (imgnamex.endsWith(".jpeg")){
			response.setHeader("Content-Type", "image/png");
		} else {
			response.setHeader("Content-Type", "image");
		}
	    
		rutafile = dameRutaImg(tipoimgx,idemisor);
		
		
		File file = new File(rutafile + imgnamex);
		     
		if (file.length() == 0) {
			rutafile = PropiedadesJLet.getInstance().getProperty("path.img.default");
			file = new File(rutafiles + imgdefaul);
		}
		
		byte[] resultado = new byte[(int)file.length()];              
		
		BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file));
        stream.read(resultado);

        OutputStream sos = response.getOutputStream();
        sos.write(resultado);
        sos.flush();
        sos.close();
        
    }
    
    public String dameRutaImg(String tpimagen, String idemisor){
    	
    	String rutafile = "";
    	
    	if ((tpimagen != null) && (tpimagen.equals("stat"))){
    		rutafile = PropiedadesJLet.getInstance().getProperty("path.img.statistics");
    	} else if ((tpimagen != null) && (tpimagen.equals("icon"))){
    		rutafile = PropiedadesJLet.getInstance().getProperty("path.img.icons.perso");
    		rutafile = rutafile + idemisor + PropiedadesJLet.getInstance().getProperty("path.separator");
    	} else if ((tpimagen != null) && (tpimagen.equals("logo"))){
    		rutafile = PropiedadesJLet.getInstance().getProperty("path.img.logos");
    	} else if ((tpimagen != null) && (tpimagen.equals("imen"))){
    		rutafile = PropiedadesJLet.getInstance().getProperty("path.img.logos");
    	} else if ((tpimagen != null) && (tpimagen.equals("favicon"))){
    		rutafile = PropiedadesJLet.getInstance().getProperty("path.img.favicon");
    	} else {
    		rutafile = PropiedadesJLet.getInstance().getProperty("path.img.default");
    	}
    	
    	return rutafile;
       
    }

}