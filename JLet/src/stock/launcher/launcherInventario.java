package stock.launcher;

import java.sql.Connection;
import java.sql.SQLException;

import stock.ChequeaInventarioSrv;
import arquitectura.controller.Service;
import arquitectura.objects.ObjectIO;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class launcherInventario {

	static Connection con = null;
	
	public static void main(String[] args) {
		
		ObjectIO input 	= null;
		ObjectIO output = null;
		
		
		Service srv = new ChequeaInventarioSrv();

		input  = srv.instanceOfInput();
		output = srv.instanceOfOutput();
		
		try {
			
			input.setValue("idemisor","1");
			input.setValue("nameinve","20141231DENNIS");
			
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
