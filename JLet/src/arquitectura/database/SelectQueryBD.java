package arquitectura.database;

import java.sql.Connection;
import java.util.Collection;
import java.util.Iterator;

import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

public abstract class SelectQueryBD {
	
	private ExecuterSQL executerSQL = null;
	private Connection con = null;
	private String poolName = null;

	protected int pageSize 	= 0;
	protected int pageNum 	= 0;
	protected int pageFirst = 0;
	protected int pageLast	= 0;
	
	public abstract Collection defineSelect();
	public abstract String getSelectStatment();
	
	protected SelectQueryBD(){
		executerSQL = new ExecuterSQL();		
	}
	
	public SelectQueryBD(ObjectIO bdata){		
		try{
			this.pageSize 	= bdata.getIntValue("BD_PAGESIZE");
			this.pageNum	= bdata.getIntValue("BD_PAGENUM");			
			
			if (pageSize != 0){
				this.pageFirst = ((pageNum-1)*pageSize)+1;
				this.pageLast = ((pageNum-1)*pageSize)+1;
			}
		} catch (Exception e){
			//NO SE HAN RECIBIDO LOS PARÁMETROS DE PAGINACIÓN
		}
	}
	
	public SelectQueryBD(ObjectIO bdata, Connection con){		
		this(bdata);
		this.con = con;
	}
	
	public void setPoolName(String poolName){		
		this.poolName = poolName;
	}
	
	public Connection getConnection() throws Exception{
		
		return this.con;
		
	}
		
	public void setConnection(Connection con) throws Exception{
		if (this.con == null){
			this.con = con;
		} else {
			throw new Exception();
		}
	}
	
	public void freeConnection() throws Exception{
		this.con = null; 	
	}
	
	public Grid execSelect() throws Exception{	
		Connection con = null;
		String sql;
		Grid grid = new Grid();
		
		Collection cols = defineSelect();
		if (cols == null){			
			System.out.println(" -:[ERROR]:- No se ha recibido Select Statement");
			throw new Exception(" -:[ERROR]:- No se ha recibido Select Statement");
		}
		sql = getSelectStatment();
		
		if (pageSize != 0 && pageNum >0){
			sql = " SELECT * FROM ( SELECT "+ buildSelect(cols) +", ROWNUM rn ";
			sql+= " FROM ("+ sql +"))";
			sql+= " WHERE rn BETWEEN "+ pageFirst +" AND "+ pageLast;
			
			cols.add("rn");
		}
		
		if (sql != null){
			try{
				con = this.getConnection();				
				grid = executerSQL.executeSelect(con,sql,cols);
				
			} catch (Exception e1){
				throw new Exception(" -:[ERROR]:- Al ejecutar la query ");
			} finally {
				try {
					if (executerSQL != null){
						executerSQL.close();
					}					
				} catch (Exception e2){
					throw new Exception(" -:[ERROR]:- Liberando la conexión ");
				}
			}
		}
		return grid;
	}
	
	public String buildSelect(Collection col){
		StringBuffer select = new StringBuffer();
		String var = null;
		
		Iterator it = col.iterator();
		while (it.hasNext()){
			var = (String)it.next();
			select.append(var);
			if (it.hasNext()){
				select.append(",");
			}
		}
		return select.toString();
	}
	
	public String prepareSelectCountStatement(String selectQuery){
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(*) FROM (").append(selectQuery).append(")");
		return sql.toString();
	}
	
	public long getNumReg() throws Exception{
		long num = 0;
		String sql = getSelectStatment();
		sql = prepareSelectCountStatement(sql);
		if (sql != null){
			Connection con = getConnection();
			try {
				num = executerSQL.executeSelectCount(con,sql);
			} catch (Exception e) {
				throw e;
			} finally {
				freeConnection();
			}
		}
		return num;
	}
	
	
	
	
	
}
