package pdfStamper;

import java.sql.Connection;
import java.sql.SQLException;

import arquitectura.controller.Service;
import arquitectura.objects.ObjectIO;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import facturacion.CodBarrasPiezasSrv;
import facturacion.FRAtradensESSrv;

public class launcherPDF {

	static Connection con = null;
	
	public static void main(String[] args) {
		
		ObjectIO input 	= null;
		ObjectIO output = null;
		
		//Código de Barras
		String codvalue = "446783";
		String anchcdba = "200";
		String altocdba = "40";
		String xposicio = "320";
		String yposicio = "583";
		
		//Texto 1 (cdcontra)
		String texto1xx = "99281000239910";
		String xpostex1 = "420";
		String ypostex1 = "710";
		
		//Texto 1 (cdcontra)
		String texto2xx = "189.000";
		String xpostex2 = "420";
		String ypostex2 = "682";

		Service srv = new GenCodBarrasPDF();
		
		input  = srv.instanceOfInput();
		output = srv.instanceOfOutput();
		
		try {
			
			input.setValue("codvalue", codvalue);
			input.setValue("anchcdba", anchcdba);
			input.setValue("altocdba", altocdba);
			input.setValue("xposicio", xposicio);
			input.setValue("yposicio", yposicio);
			input.setValue("texto1xx", texto1xx);
			input.setValue("xpostex1", xpostex1);
			input.setValue("ypostex1", ypostex1);
			input.setValue("texto2xx", texto2xx);
			input.setValue("xpostex2", xpostex2);
			input.setValue("ypostex2", ypostex2);
			
			crearConexion();
			srv.setConnection(con);
			srv.process(input, output);
			
			System.out.println("El nombre fichero es "+ output.getValue("filecrea"));
			
			
			Service srvPDf = new PdfStamperSrv();
			//Service srv= new FRAizumbaWebSrv(); 
			
			
			input  = srvPDf.instanceOfInput();
			output = srvPDf.instanceOfOutput();
			srvPDf.process(input, output);
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
