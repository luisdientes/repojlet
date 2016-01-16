package arquitectura.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import utils.PropiedadesJLet;
import arquitectura.objects.ObjectIO;
import arquitectura.webchannel.HttpHandler;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


public abstract class ServiceRequestHandler extends RequestHandler{
	
	Connection con = null;
	
	private static final String defaultFormatBundleName = "arquitectura.objects.Format";

	protected abstract String getServicePackage();
	
	public ServiceRequestHandler(){
		super();
	}
	
	public ObjectIO throwService(String name, ObjectIO input) throws Exception{
		
		Service service = null;
		
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
		
		try{				
			
			service = getServiceInstance(name);
			service.setSesion(sesion);
			service.setSessionHelper(sessionHelper);
			service.setConnection(con);
			
			ObjectIO srvIn = service.instanceOfInput();

			ArrayList<String> claves = new ArrayList<String>();
			claves = input.getValues();
			
			for (int i = 0; i < claves.size(); i++){
				
				String clave = claves.get(i).toString();
				String valor = input.getValue(clave).toString();
				
				if (srvIn.isVarDefined(clave)){					
					srvIn.setValue(clave, valor);
				}
				
			}
			
			ObjectIO srvOut = service.instanceOfOutput();
			
			if (!srvIn.isVarDefined("sessionHelper")){
				srvIn.addVariable("sessionHelper",sessionHelper);
			}
			
			if (!srvIn.isVarDefined("session")){
				//srvIn.addVariable("session",input.getValue("session"));
			}
			
			service._process(srvIn, srvOut);						
		
			
			con.close();
			
			if (!srvOut.isVarDefined("sessionHelper")){
				srvOut.addVariable("sessionHelper",sessionHelper);
			}
			
			return srvOut;
			
		} catch (Exception e){
			throw e;
		}										
	}
	
	
	public void createConnection(String dbType,String cdUser,String password,String dbSchema,String nameServ,int portNumber){
	
		con = getConnnection(dbType,cdUser,password,dbSchema,nameServ,portNumber);
		
	}
	
	private Service getServiceInstance(String serviceName) throws Exception {
		
		Exception lastException = null;
		Service service = null;
		
		Iterator i = getServicePackages().iterator();
		String message = "";
		
		while (i.hasNext() && service == null){
			
			String pack = (String) i.next();
			try{
				Class cls = Class.forName(pack + HttpHandler.SERVICES_DELIMITER + serviceName);
				service = (Service)cls.newInstance();
			} catch (Exception e){
				service = null;
				lastException = e;
				System.err.println("-- QUARENTENA BORRAR -- ERR ERROR IMPORTANTE - No se recupera la clase ¿Existe?"+ e.getMessage());
			}
			
		}
		
		if (service == null){
			if (lastException == null){
				message = "Package not found for service";
			}
			System.err.println("ERR ERROR IMPORTANTE - No se recupera la clase "+ serviceName +" ¿Existe? ");
			throw new Exception();
		}
		return service;				
	}

	protected Collection getServicePackages(){
		Vector packages = new Vector();
		packages.add(getServicePackage());
		return packages;
	}
	
	protected String getFormatBundleName(){
		return defaultFormatBundleName;
	}
	
	 protected ResourceBundle getFormatProperties(){
		 
	        ResourceBundle rb = null;
	        
	        try{
	            rb = (ResourceBundle)sessionHelper.getValue("formatBundle");
	            Locale locale = (Locale)sessionHelper.getValue("localeObject");
	            if(rb==null && locale!=null){
	                String name = getFormatBundleName();
	                if(name!=null){
	                    //rb = ResourceBundle.getBundle(name, locale);
	                    //sessionHelper.setValue("formatBundle", rb);
	                }
	            }
	        } catch(Exception e){
	        }
	        return rb;
	    }
	
}
