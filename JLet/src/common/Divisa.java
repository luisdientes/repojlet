package common;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import utils.PropiedadesJLet;
import arquitectura.objects.Grid;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import comercio.bd.ListFixingBD;
import comercio.bd.ListFixingBDIn;

public class Divisa {
	
	private Grid gdDivisa	 = null;
	private Connection con 	 = null;

    public Divisa(){
        
        try{
        	
        	if (gdDivisa == null){
        		
        		System.out.println("Voy a rellenar el grid Divisa");
        		
	        	try {
	        		
	    	    	if (con == null){
	    	    		createConnection();
	    	    	}
	    	    	
	    	    	ListFixingBDIn fixingBDIn = new ListFixingBDIn();
	            	fixingBDIn.setValue("cddivisa","USD");
	            	ListFixingBD fixingBD = new ListFixingBD(fixingBDIn);
	            	fixingBD.setConnection(con);
	            	gdDivisa = fixingBD.execSelect();
	    		    
	    		    con.close();
	    		    con = null;
	    		    
	        	} catch (Exception e) {
	        		System.out.println(" ERROR Recuperando los datos de Fixing - Exeption: "+ e.getMessage());
	    			//e.printStackTrace();
	    		}
        	} else {
        		System.out.println("Grid Divisa relleno");
        	}
        } catch (Exception e){
        	System.out.println(" ERROR Inicializando cineytele.properties");
        	e.printStackTrace();
        }
    }
    
    public void setConnection(Connection conexion){
        
    	con = conexion;    	
    	
    }
    
    public String getFixingUSD(String cdDivisa){

    	String cotizval = "";
    	boolean fixingOK = false;
    	
    	try {
	 	    for (int i = 0; i < gdDivisa.rowCount() && !fixingOK; i++){
	 	    	
	 	    	if (gdDivisa.getStringCell(i, "diviscam").equals(cdDivisa)){
	 	    		cotizval = gdDivisa.getStringCell(i, "fixingxx");
	 	    		fixingOK = true;
	 	    	}
	 	    	
	 	    }
    	} catch (Exception e) {
    		System.out.println(" ERROR Recuperando los datos de Fixing del USD - Exeption: "+ e.getMessage());
    	}
    	
    	return cotizval;
    }
    
    public String getCambioUSD(String cdDivisa, String fhfechax){

    	
    	
    	String cotizval = "";
    	/*
    	boolean fixingOK = false;
    	
    	try {
	 	    for (int i = 0; i < gdDivisa.rowCount() && !fixingOK; i++){
	 	    	
	 	    	if (gdDivisa.getStringCell(i, "diviscam").equals(cdDivisa)){
	 	    		if (fhfechax < gdDivisa.getStringCell(i, "altatime")){
	 	    			fixingOK = true;
	 	    		} else {
	 	    			cotizval = gdDivisa.getStringCell(i, "fixingxx");
	 	    		}
	 	    	}
	 	    	
	 	    }
    	} catch (Exception e) {
    		System.out.println(" ERROR Recuperando los datos de Fixing del USD - Exeption: "+ e.getMessage());
    	}
    	
    	*/
    	
    	return cotizval;
    }
    
   	public void createConnection(){
    	
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
   	
   	public void closeConnection(){
	   		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
   	
   	
   	
    	
}