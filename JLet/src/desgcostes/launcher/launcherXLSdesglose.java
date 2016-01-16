package desgcostes.launcher;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import desgcostes.XlsDesgloseSrv;
import desgcostes.bd.ListIMEIDesgloseBD;
import desgcostes.bd.ListIMEIDesgloseBDIn;
import desgcostes.bd.ListIMEIFacturadoBD;
import desgcostes.bd.ListIMEIFacturadoBDIn;
 
public class launcherXLSdesglose {

	static String idemisor = "1";
	static int contador = 0;
	
	static Connection con = null;
	
	public static void main(String[] args) {
		
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
		
		arInform.add("Izumba");
		
		/*
		String idunicox = "353942042220299,353942041010998,353942042187035,353942042195509,353942042195798";
		idunicox += ",353942042196929,353942042220596,353942042220590,353942042203154,353942041010790,353942042202107,353942042220026";
		idunicox += ",353942040989010,353942042195004,353942040887479,353942042175196,253942042194981,353942040887594,353942042194825";
		idunicox += ",353942040984169,353942042195699,353942040984946,353942042203071,353942040887461,353942042177721,353942042202198";
		idunicox += ",353942042203584,353942042220380,353942042180774,353942042181145,353942042195350,353942040887065,353942040887727";
		idunicox += ",353942042193413,353942042120986,353942042212825,353942042177879,353942042186888,353942042203188,353942040887800";
		idunicox += ",353942042177598,353942042193595,353942042193348,353942042203170,353942041151180,353942040984789,353942040989051";
		idunicox += ",353942042203030,353942042187704,353942042186805,353942042193504,353942042193744,353942042196978,353942042219879";
		idunicox += ",353942042220307,352961049345934,352961049419739,353942040984433";
		
		idunicox = "PH73400001,PH7341P4Z8,PH7340T5W1,PH734G0B0K";
		*/
		
		
		for (int i = 0; i < arInform.size(); i++){
			
			Service srv = new XlsDesgloseSrv();
			crearConexion();
			
			input  = srv.instanceOfInput();
			output = srv.instanceOfOutput();
			
			String substrin = null;
			//substrin = "5";
			
			//String idunicox = dameIMEI(arInform.get(i),substrin);
			
			String idunicox = dameIMEIVendidos("01/01/2014","01/03/2015");
			
			/*
			String idunicox = "";
			idunicox += "352961049293589,";
			idunicox += "352961049345892,";
			idunicox += "353059029217691,";
			idunicox += "353059029225025,";
			idunicox += "353059029758009,";
			idunicox += "353059029765509,";
			idunicox += "353942040883825,";
			idunicox += "353942040887057,";
			idunicox += "353942040976421,";
			idunicox += "353942040984458,";
			idunicox += "353942041010956,";
			idunicox += "353942041151131,";
			idunicox += "353942042175238,";
			idunicox += "353942042175279,";
			idunicox += "353942042175287,";
			idunicox += "353942042175295,";
			idunicox += "353942042177853,";
			idunicox += "353942042180852,";
			idunicox += "353942042180857,";
			idunicox += "353942042180865,";
			idunicox += "353942042181269,";
			idunicox += "353942042193678,";
			idunicox += "353942042195202,";
			idunicox += "353942042195434,";
			idunicox += "353942042195814,";
			idunicox += "353942042196085,";
			idunicox += "353942042196135,";
			idunicox += "353942042196861,";
			idunicox += "353942042196911,";
			idunicox += "353942042197141,";
			idunicox += "353942042202115,";
			idunicox += "353942042203550,";
			idunicox += "353942042213856,";
			idunicox += "353942042219036,";
			idunicox += "353942042219960,";
			idunicox += "353942042220026,";
			idunicox += "353942042220240,";
			idunicox += "353942042220554,";
			idunicox += "353942042220570,";
			idunicox += "353942042222733,";
			idunicox += "354427061918097,";
			idunicox += "354807059868794,";
			idunicox += "355898061818686,";
			idunicox += "355899065745420,";
			idunicox += "356503056532289,";
			idunicox += "356556051501535,";
			idunicox += "356636051035880,";
			idunicox += "356636051035906,";
			idunicox += "356636051036003,";
			idunicox += "356636051039650,";
			idunicox += "356636051041243,";
			idunicox += "358271052654741,";
			idunicox += "359043050583191,";
			idunicox += "359043050631248,";
			idunicox += "359043050639191,";
			idunicox += "359608050014155,";
			idunicox += "863962028175849,";
			idunicox += "863962028181599,";
			idunicox += "869563010169586"; 
 			*/

			
			try {
				
				input.setValue("idemisor", idemisor);
				input.setValue("idunicox", idunicox);
				input.setValue("codinfor", arInform.get(i));
				
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
