package excelXLSX;

import java.sql.Connection;
import java.sql.SQLException;

import arquitectura.controller.Service;
import arquitectura.objects.ObjectIO;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import facturacion.CodBarrasPiezasSrv;
import facturacion.FRAtradensESSrv;

public class launcherExcel {

	static Connection con = null;
	
	public static void main(String[] args) {
		
		ObjectIO input 	= null;
		ObjectIO output = null;
		
		String cdgestor = "1";
		String cdcompan = "2";
		String txnombre = "3";
		String cdholder = "4";
		String cdnifxxx = "5";
		String naturale = "6";
		String catecont = "7";
		String typecont = "8";
		String tpcountr = "9";
		String cdcountr = "10";
		String cdprovin = "11";
		String primtitu = "12";
		String tituunic = "13";
		String tituvari = "14";
		String segregad = "15";
		String consulta = "16";
		String pantalla = "17";
		
		
		//String idorderx = "81";
		
	
		//Service srv = new FRAvetustaCSSrv();
		//Service srv = new FRAtradensESSrv();
	Service srv = new ExcelSrv();
		//Service srv= new FRAizumbaWebSrv(); 
		
		
		input  = srv.instanceOfInput();
		output = srv.instanceOfOutput();
		
		try {
			/*
			input.setValue("emisclie", emisclie);
			input.setValue("receclie", receclie);
			input.setValue("tpclient", tpclient);
			input.setValue("aniofact", aniofact);
			input.setValue("tipofact", tipofact);
			input.setValue("mcagrupa", mcagrupa);
			input.setValue("fhfactur", fhfactur);
			input.setValue("listimei", listimei);*/
		
			
			/*
			input.setValue("idemisor", "1");
			input.setValue("aniofact", "2014");
			input.setValue("fhdesdex", "01/01/2014");
			input.setValue("fhhastax", "31/12/2014");
			*/
			crearConexion();
			srv.setConnection(con);
			srv.process(input, output);
			//cerrarConexion();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void crearConexion(){							
		
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("");
		dataSource.setServerName("localhost");
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
	

}
