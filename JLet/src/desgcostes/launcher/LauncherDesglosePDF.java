package desgcostes.launcher;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import desgcostes.PdfDesgloseSrv;
import desgcostes.bd.ListIMEIDesgloseBD;
import desgcostes.bd.ListIMEIDesgloseBDIn;
import desgcostes.bd.ListIMEIFacturadoBD;
import desgcostes.bd.ListIMEIFacturadoBDIn;
 
public class LauncherDesglosePDF {

	static String idemisor = "8";
	static int contador = 0;
	
	static Connection con = null;
	
	public static void main(String[] args) {
		
		String mostriva = "S";
		
		ObjectIO input 	= null;
		ObjectIO output = null;
		
		ArrayList<String> arInform = new ArrayList<String>(); 
		
		/*
		arInform.add("AMAZON");
		arInform.add("VENTADIRECTA");
		arInform.add("EBAY");
		*/
		
		/*
		arInform.add("ES");
		arInform.add("FR");
		arInform.add("GB");
		arInform.add("DE");
		arInform.add("AT");		
		arInform.add("IE");
		arInform.add("IT");		
		*/
		
		arInform.add("Tradens_ES");
		
		for (int i = 0; i < arInform.size(); i++){
			
			Service srv = new PdfDesgloseSrv();
			crearConexion();
			
			input  = srv.instanceOfInput();
			output = srv.instanceOfOutput();
			
			String idunicox = dameIMEIVendidos("01/08/2015","15/08/2015");
			
			try {
				
				input.setValue("idemisor", idemisor);
				input.setValue("idunicox", idunicox);
				input.setValue("mostriva", mostriva);
				input.setValue("codinfor", arInform.get(i));
				input.setValue("gdMedias", new Grid());
				input.setValue("fhdesdex", "01/12/2013");
				input.setValue("fhhastax", "01/12/2014");
				input.setValue("txpaisxx", "pais");
				input.setValue("txcanalx", "canal");
				input.setValue("txemisor", "emisor");
				input.setValue("ingrtota", "11.1");
				input.setValue("benebrut", "231.2");
				
				srv.setConnection(con);
				srv.process(input, output);
				cerrarConexion();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		System.out.println("Se han pintado "+ contador +" informes.");
		
	}
	
	public static String dameIMEI (String desvalue, String substrin) {
		
		String lsimeixx = "";

		try {
			ListIMEIDesgloseBDIn lisIMEIBDIn =	 new ListIMEIDesgloseBDIn();
			lisIMEIBDIn.setValue("idemisor",idemisor);
			lisIMEIBDIn.setValue("desvalue",desvalue);
			lisIMEIBDIn.setValue("substrin",substrin);
			ListIMEIDesgloseBD desDetalleBD = new ListIMEIDesgloseBD(lisIMEIBDIn);
	    	desDetalleBD.setConnection(con);
	    	Grid gdIMEIls = desDetalleBD.execSelect();
	    	
	    	for (int i = 0; i < gdIMEIls.rowCount(); i++){
	    		
	    		if (i > 0){
	    			lsimeixx += ",";
	    		} 
	    		
	    		lsimeixx += gdIMEIls.getStringCell(i, "idunicox"); 
	    		contador++;
	    		
	    	}
	    	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    	
		return lsimeixx;
		
	}
	
	public static String dameIMEIVendidos (String fhdesdex, String fhhastax) {
		
		String lsimeixx = "";

		try {
			
			if ((fhdesdex != null) && (!fhdesdex.equals(""))){
				fhdesdex = fechaMySQL(fhdesdex);
			}
			
			if ((fhhastax != null) && (!fhhastax.equals(""))){
				fhhastax = fechaMySQL(fhhastax);
			}
			
			ListIMEIFacturadoBDIn lisIMEIBDIn =	 new ListIMEIFacturadoBDIn();
			lisIMEIBDIn.setValue("idemisor",idemisor);
			lisIMEIBDIn.setValue("fhdesdex",fhdesdex);
			lisIMEIBDIn.setValue("fhhastax",fhhastax);
			ListIMEIFacturadoBD desDetalleBD = new ListIMEIFacturadoBD(lisIMEIBDIn);
	    	desDetalleBD.setConnection(con);
	    	Grid gdIMEIls = desDetalleBD.execSelect();
	    	
	    	for (int i = 0; i < gdIMEIls.rowCount(); i++){
	    		
	    		if (!gdIMEIls.getStringCell(i, "idunicox").equals("")){
	    			
	    			if (contador > 0){
		    			lsimeixx += ",";
		    		}
	    			
	    			lsimeixx += gdIMEIls.getStringCell(i, "idunicox"); 
	    			contador++;
	    		}
	    		
	    	}
	    	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    	
		return lsimeixx;
		
	}
	
	
	public static void crearConexion(){							
		
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("");
		dataSource.setServerName("127.0.0.1");
		//dataSource.setUser("root");
		//dataSource.setPassword("");
		dataSource.setDatabaseName("des_jletproj");
		//dataSource.setServerName("127.0.0.1");

		try {
			con = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		System.out.println("[OPEN] - Abro Conexion");
		
	}
	
	public static void cerrarConexion(){							
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		System.out.println("[OPEN] - Abro Conexion");
		
	}
	
	 public static String fechaMySQL(String fhfechax){
	        
	  		String fhMySql = "";
	  		try {
	  			String [] arrFecha = fhfechax.split("/");
	  			fhMySql = arrFecha[2]+"-"+ arrFecha[1]+"-"+arrFecha[0];
	  		} catch (Exception e) {
	  			return "0000-00-00";
	  		}
	  		return fhMySql;
	      }

}
