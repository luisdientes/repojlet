
package arquitectura.database;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class MySQLconnector {
	
	String srvrname = "";
	String database = "";
	String cdusuari = "";
	String password = "";
	
	public MySQLconnector(){
		
	}
	
	public MySQLconnector(String servidor, String basedato, String usuario, String contrase){
		
		srvrname = servidor;
		database = basedato;
		cdusuari = usuario;
		password = contrase;
		
	}
	
	public Connection getConnection(){
		try {
		    MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setUser(cdusuari);
			dataSource.setPassword(password);
			dataSource.setDatabaseName(database);
			dataSource.setServerName(srvrname);			
			    
			return dataSource.getConnection();
		} catch (SQLException e) {
			
			System.out.println(" - ERROR ARQUIT - No se ha podido crear la conexión ");
			
			e.printStackTrace();
			return null;
		}
	}
	
}
