package arquitectura.database;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import arquitectura.objects.Grid;

public class ExecuterSQL {
	
	private Statement statement  = null;
	private long fetchSize		 = 500;
	
	public ExecuterSQL(){
		
	}
	
	private void createStatement(Connection con) throws Exception{
		
		//Por defecto establecemos Tipo de Desplazamiento: 	ResulSet.TYPE_FORWARD_ONLY
		//Por defecto establecemos Tipo de Concurrencia: 	ResulSet.CONCUR_READ_ONLY
		
		try{			
			statement = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);									
		} catch (Exception e1) {
			try {
				statement = con.createStatement();
			} catch (Exception e2){
				System.out.println(" -:[ERROR]:- No se puede crear un Statement con la BBDD");
				throw new Exception(" -:[ERROR]:- No se puede crear un Statement con la BBDD");
			}
		}
		
	}
	
	public void setFetchSize(long size) throws Exception{
		
		try {							
			fetchSize = size;
		} catch (Exception e){
			System.out.println(" -:[WARNING]:- No se puede establecer el FetchSize "+size);
		}
		
	}
	
	
	
	public ResultSet executeSelect(Connection con, String sql) throws Exception{
		
		ResultSet rs = null;
		createStatement(con);
		
		try {
			long t0 = System.currentTimeMillis();
			rs = statement.executeQuery(sql);
			long t1 = System.currentTimeMillis();
			long tquery = t1 - t0;
			//Podemos mostrar el tiempo de conexión
			//System.out.println(" -:[TEST]:- Query a sql "+ sql);
			//System.out.println(" -:[TEST]:- Tiempo de proceso query :"+ tquery +" ms");					
		} catch (Exception e){
			//System.out.println(" -:[ERROR]:- Al ejecutar query ");
			e.printStackTrace();
			throw new Exception(" -:[ERROR]:- Al ejecutar query ");
		}
				
		return rs;
		
	}
	
	public Grid executeSelect(Connection con, String sql, Collection cols) throws Exception{
		
		ResultSet rs = null;
		Grid grid = null;
				
		try {
			rs = executeSelect(con,sql);
			grid = getGrid(rs, cols);
			rs.close();
		} catch (Exception e){
			System.out.println(" -:[ERROR]:- Al ejecutar query con cabecera ");
			e.printStackTrace();
			throw new Exception(" -:[ERROR]:- Al ejecutar query con cabecera ");
		}
				
		return grid;
		
	}
	
	public long executeUpdate(Connection con, String sql) throws Exception{
		
		long result = 0;
		createStatement(con);
			
		try{
			long t0 = System.currentTimeMillis();
			result = statement.executeUpdate(sql);
			long t1 = System.currentTimeMillis();
			long tquery = t1 - t0;
			//Podemos mostrar el tiempo de conexión
			System.out.println(" -:[TEST]:- Query a sql "+ sql);
			System.out.println(" -:[TEST]:- Tiempo de proceso query :"+ tquery +" ms");					
		} catch (Exception e){
			throw new Exception(" -:[ERROR]:- Al ejecutar query con cabecera ");
		}
		
		return result;
		
	}
	
	public long executeInsert(Connection con, String sql) throws Exception{
		
		long result = 0;
		createStatement(con);
			
		try{
			long t0 = System.currentTimeMillis();
			result = statement.executeUpdate(sql);
			long t1 = System.currentTimeMillis();
			long tquery = t1 - t0;
			//Podemos mostrar el tiempo de conexión
			System.out.println(" -:[TEST]:- Query a sql "+ sql);
			System.out.println(" -:[TEST]:- Tiempo de proceso query :"+ tquery +" ms");					
		} catch (Exception e){
			throw new Exception(" -:[ERROR]:- Al ejecutar query con cabecera ");
		}
		
		return result;
		
	}
	
	//Verificar su utilidad
	public long executeSelectCount(Connection con, String sql) throws Exception{
		
		long num = 0;
		if (sql != null){
			try {
				ResultSet resultSet = executeSelect(con, sql);
				resultSet.next();
				num = resultSet.getLong(1);
			} catch (Exception e){
				System.out.println(" -:[ERROR]:- con la BBDD");
				throw new Exception(" -:[ERROR]:- con la BBDD");
			}
		}
		return num;		
	}
	
	private static Grid getGrid(ResultSet rs, Collection cols) throws Exception{
		
		Grid grid = new Grid(cols);
		
		if (rs != null){
			for (int i=0; rs.next(); i++){
				ArrayList row = new ArrayList();
				for (int j=0; j<cols.size(); j++){
					Object object = rs.getObject(j+1);							
					//SUSTITUCIÓN DE CARACTERES ESPECIALES (posibles errores en la capa de presentación (WEBCHANNEL))
					if (object instanceof java.lang.String){
						object = ((String) object).replace('"',' ');
						object = ((String) object).replace('\'',' ');
						object = ((String) object).replace('|',' ');
						object = ((String) object).replace('#',' ');
					} else if (object instanceof java.math.BigDecimal && rs.getMetaData().getScale(j+1) == 0){
						if (object != null && ((java.math.BigDecimal)object).scale() == 0){
							object = ((java.math.BigDecimal)object).toBigInteger();
						}
					} else if (object instanceof java.sql.Blob){
						try {
							java.sql.Blob blob = (Blob)object;
							InputStream is = blob.getBinaryStream();
							byte[] aux = new byte[(int)blob.length()];
							is.read(aux);
							is.close();
							
							object = aux;
						} catch (Exception excep){
							object = null;
							}
						}
						row.add(object);
				}
					grid.addRow(row);
			}
		}
		
		return grid;
		
	}
	
	
	public void close() throws Exception{
		
		try{
			if (statement != null){
				statement.close();
			}
		} catch (Exception e){
			System.out.println(" -:[ERROR]:- Al cerrar el Statement");
			throw new Exception(" -:[ERROR]:- Al cerrar el Statement");
		}
		
		
	}
	
	
	
	
}
