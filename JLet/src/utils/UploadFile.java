package utils;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

public class UploadFile extends HttpServlet{

	private static final int BUFFER = 0;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			procesaFicheros(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void procesaFicheros(HttpServletRequest req,HttpServletResponse res) throws Exception {
    	
    	MultipartRequest multi = null;
    	String fileName="";
		
    	try {
			try {
				String dirDelet =  PropiedadesJLet.getInstance().getProperty("comercio.file.input");
				multi = new MultipartRequest(req,dirDelet);    			
	    	} catch (Exception e){
				System.out.println("ERROR AL RECUPERAR LA RUTA");
				e.printStackTrace();
	    	}
    		    		
			Enumeration files = multi.getFileNames();
			while (files.hasMoreElements()) {
				String name = (String)files.nextElement();
				fileName = multi.getFilesystemName(name);
				String sinBlancos="";
				for (int x=0; x < fileName.length(); x++) {
    				if (fileName.charAt(x) != ' '){
    					sinBlancos += fileName.charAt(x);
    				}
    			}
								
				String type = multi.getContentType(name);
				System.out.println("EL TIPO DE FICHERO ES: "+ type);
								
				RequestDispatcher rd =null;
		        req.setAttribute("filename", fileName);

		        rd = req.getRequestDispatcher("/comercio/altaFicheroOK.jsp");			        
		        rd.forward(req,res);
				
			}
    	} catch(Exception e){
    		System.out.println(e.toString());   
    		e.printStackTrace();
    	}        	
        
            
    }   
	
    
}