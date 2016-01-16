package arquitectura.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import arquitectura.objects.ObjectIO;

public abstract class UpdateQueryBD extends SelectQueryBD{
	
	protected ObjectIO iobd; 
	
	protected UpdateQueryBD(){
	}
	
	public UpdateQueryBD(ObjectIO bdata){		
		super(bdata);
		iobd = bdata;
	}
	
	public UpdateQueryBD(ObjectIO bdata, Connection con){		
		super(bdata,con);
		iobd = bdata;
	}
	
	public abstract String getInsertStatement();
	public abstract String getUpdateStatement();
	public abstract String getDeleteStatement();
	
	public int executeQuery(String sql)  throws Exception {
        Connection con = null;
        int result=0;
        
        try{
                
            if (sql!=null){            
                con = this.getConnection();
                PreparedStatement s = con.prepareStatement(sql);
                result = s.executeUpdate();                                              
            } else {
                throw new Exception("Sentencia SQL Nula");
            }
            
        } catch (Exception e){
            throw e;
        } finally{
            try{
                freeConnection();
            } catch(Exception e){}
        }
        return result;
    }
	
	public int execInsert() throws Exception {
		String sql = getInsertStatement();
    	return executeQuery(sql);
    }
	
    public int execUpdate() throws Exception {
    	String sql = getUpdateStatement();
    	return executeQuery(sql);
    }
    
    public int execDelete() throws Exception {
    	String sql = getDeleteStatement();
    	return executeQuery(sql);
    }
        
    public long nextValue(String seq) throws Exception{
        long value = -1;
        
        Connection con = getConnection();

        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("select " + seq + ".nextval from dual");

        if(rs.next()){
            value = rs.getInt(1);
        }

        if (s != null)
        {
          s.close();
        }

        freeConnection();
        
        return value;              
    }
    	
}
