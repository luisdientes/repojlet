package servlet;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import utils.CloudFunctions;
import utils.PropiedadesJLet;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import cloud.CreaDirectorioSrv;
import cloud.bd.ListArchivosBD;
import cloud.bd.ListArchivosBDIn;
import cloud.bd.UpdateCloudBD;
import cloud.bd.UpdateCloudBDIn;
import cloud.bd.UpdateVersionBD;
import cloud.bd.UpdateVersionBDIn;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class ServletUploadFileCloud extends HttpServlet {
    

	Connection con = null;
	HttpSession session = null;
	String urldesti = "/cloud/resultado.jsp";
	String idemisor = "1";
	String filepath = "";
	String director = "";
	String permgrup = "";
	String propieta = "";
	String tipofich = "";
	String txnombre = "";
	String rutaabso = "";
	String cduserid = "";
	long   idtamano = 0;

 

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	
        System.out.println("Comenzamos procesamiento ficheros");
    	try{
    		procesaFicheros(request,response);
    	}catch(Exception e){
    		System.out.println(e.toString());
 
    	}
    }

    public void procesaFicheros(HttpServletRequest req,HttpServletResponse res) throws Exception {
    	
    	RequestDispatcher redireccion = null;
    	res.setContentType("text/html;charset=ISO-8859-1");
    	req.setCharacterEncoding("ISO-8859-1");
    	File file = null;
    	//MultipartParser mp = new MultipartParser(req,1024*1024);
    	/*
    	idemisor = mpar.getParameter("idemisor");
    	filepath = mpar.getParameter("filepath");
    	permgrup = mpar.getParameter("permgrup");
    	propieta = mpar.getParameter("propieta");
    	tipofich = mpar.getParameter("tipofich");
    	
    	*/
    	
    	crearConexion();
		session = req.getSession();
		
		
		
		try{
    		cduserid = (String) session.getAttribute("idusuari");
    	}catch(Exception ex){
    		System.out.println("Error al recuperar sesion de usuario");
    		cduserid = "-1";
    	}
    	
    	List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
       
    	for (FileItem item : items) {
            if (item.isFormField()) {
                // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
              
                if(item.getFieldName().equals("idemisor")){
                	idemisor = item.getString();
                }
                
                if(item.getFieldName().equals("filepath")){
                	filepath = item.getString();
                }
                
                if(item.getFieldName().equals("permgrup")){
                	permgrup = item.getString();
                }
                
                if(item.getFieldName().equals("propieta")){
                	propieta =  item.getString();
                }
                
                if(item.getFieldName().equals("tipofich")){
                	tipofich = item.getString(); 
                }
                // ... (do your job here)
            }else{
            	director = PropiedadesJLet.getInstance().getProperty("path.gen.cloud") + "emisor_"+ idemisor + "/"+filepath; 
            	file = new File( director, item.getName());
            	
            	
            	
            	txnombre = item.getName();
            	idtamano = item.getSize();
            	
            	
            	System.out.println("Nombre fichero UPLOADDDDDDDDDDDD ---------------- >>>>"+ txnombre +" ---- VS---- "+item.getName());
            	  /*try {
            		  txnombre = URLEncoder.encode(item.getName(), "ISO-8859-1");
                   
            		  System.out.println("URL-encoded by client with UTF-8: " + txnombre);
       
            		  String incorrectDecoded = URLDecoder.decode(txnombre, "ISO-8859-1");
            		  System.out.println("Then URL-decoded by server with ISO-8859-1: " + incorrectDecoded);
       
            		  String correctDecoded = URLDecoder.decode(txnombre, "UTF-8");
            		  System.out.println("Server should URL-decode with UTF-8: " + correctDecoded);
                  } catch (UnsupportedEncodingException e) {
                      // TODO Auto-generated catch block
                      e.printStackTrace();
                  }
            	*/
            	
            	if(file.exists()){
        			updateFichero();
        			versionaFichero();
        		}else{
        			insertaFichero();
        		}
            	
            	try{
            		
            		System.out.println("Nombre fichero ESCRIBE"+file);
            		item.write(file);
            		
            	}catch(Exception ex){
            		System.out.println("Error al subir el fichero----");
            	}
	
            }

        }
    	
    	
    	try{
    		
    		  		
    		session.setAttribute("filepath", filepath.substring(0, filepath.length()-1));
    		session.setAttribute("idemisor", idemisor);
    		
    		
    		System.out.println("este es el idemisor "+ idemisor);
    		System.out.println("este es el filepath "+ filepath);
    		redireccion = req.getRequestDispatcher(urldesti);
    		
 		    redireccion.forward(req, res);  
    	}catch(Exception ex){
    		
    	}
        
            
    } 
    
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    
        processRequest(request, response);
    }
    
    
    
    public void insertaFichero(){
    	   
    	try { 
    		CreaDirectorioSrv creaFichSrv = new CreaDirectorioSrv();
    		ObjectIO input  = creaFichSrv.instanceOfInput();
    		ObjectIO output = creaFichSrv.instanceOfOutput();
    		
    		input.setValue("idemisor", idemisor);
    		input.setValue("tipofich", tipofich);
    		input.setValue("txdirect", filepath);
    		input.setValue("permgrup", permgrup);
    		input.setValue("txnombre", txnombre);
    		input.setValue("idtamano", idtamano);
    		input.setValue("cduserid", cduserid);
    		
    		creaFichSrv.setConnection(con);
    		creaFichSrv.process(input, output);
    		
    		
    		
    		/*filecrea = output.getStringValue("filecrea");
			txmensaj = output.getStringValue("txmensaj");*/
    		
    	} catch(Exception e) {
    		System.out.println("Error al subir fichero");
    	}
    }
    
    
    public void updateFichero(){
    	
    	try{
	    	UpdateCloudBDIn archiBDIn = new UpdateCloudBDIn();
	    	archiBDIn.setValue("idemisor",idemisor);
	    	archiBDIn.setValue("txnombre",txnombre);
	    	archiBDIn.setValue("filepath",filepath);
	    	archiBDIn.setValue("tipofich","F");
	    	archiBDIn.setValue("idtamano",idtamano);
	    	
	    	archiBDIn.setValue("propieta",cduserid); 

	    	UpdateCloudBD  updCloud = new UpdateCloudBD(archiBDIn);
	    	updCloud.setConnection(con);
	    	updCloud.execUpdate();
	    	
    	}catch(Exception ex){
    		System.out.println("Error al actualizar estado" +ex.getMessage());
    	}
	    	
    }
    
    public void versionaFichero(){
    	
    	Grid grArchiv = null;
    	
    	String idinodox = "";
    	String versionx = "";
    	
    	long milisegu = System.currentTimeMillis();
   
    	
    	try{
    	
	    	ListArchivosBDIn archiBDIn = new ListArchivosBDIn();
	    	archiBDIn.setValue("idemisor",idemisor);
	    	archiBDIn.setValue("filepath",filepath);
	    	archiBDIn.setValue("txnombre",txnombre);
	    	archiBDIn.setValue("tipofich","F");
	    	
	    	
	    	ListArchivosBD archivBD = new ListArchivosBD(archiBDIn);
	    	archivBD.setConnection(con);
	    	grArchiv = archivBD.execSelect();
	    	
	    	idinodox = grArchiv.getStringCell(0, "idinodox");
	    	versionx = grArchiv.getStringCell(0, "lastvers");
	    
	    	UpdateVersionBDIn updVerBDIn = new UpdateVersionBDIn();
	    	updVerBDIn.setValue("idinodox",idinodox);
	    	updVerBDIn.setValue("usupload",cduserid);
	    	updVerBDIn.setValue("versionx",versionx);
	    	updVerBDIn.setValue("milisegu",milisegu);
	    	
	    	UpdateVersionBD updVerBD = new UpdateVersionBD(updVerBDIn);
	    	updVerBD.setConnection(con);
	    	updVerBD.execInsert();
	    	
	    	
    	}catch(Exception ex){
    		System.out.println("Error al listar ficheros para versionar "+ ex.getMessage());
    		ex.printStackTrace();
    	}
    	
    	CloudFunctions.versionarFichero(director, txnombre);
    }
    
    
    
 public void crearConexion(){
		
		String usuariox = "";
		String password = "";
		String bdschema = "";
		String bdhostxx = "";
		

		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/des_jletproj");
			con = ds.getConnection();
			//System.out.println(this.getClass().getName() +"Conexion realizada desde el Pool ");
		} catch (Exception e){
			System.err.println("No se ha podido obtener el Pool "+ e.getMessage());
			usuariox = PropiedadesJLet.getInstance().getProperty("jlet.bd.usuariox");
			password = PropiedadesJLet.getInstance().getProperty("jlet.bd.password");
			bdschema = PropiedadesJLet.getInstance().getProperty("jlet.bd.bdschema");
			bdhostxx = PropiedadesJLet.getInstance().getProperty("jlet.bd.bdhostxx");
			
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setUser(usuariox);
			dataSource.setPassword(password);
			dataSource.setDatabaseName(bdschema);
			dataSource.setServerName(bdhostxx);
			
			try {
				con = dataSource.getConnection();
				System.out.println(this.getClass().getName() +"Conexion realizada desde el Properties ");
			} catch (SQLException e2) {
				System.err.println("ERROR de conexion: "+ e2.getMessage());
			}
			
		}
		
	}
}