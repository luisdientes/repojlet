package clientes.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class ListMaxClientesBD extends UpdateQueryBD {

	String idemisor = "";
	String tpclient = "";
    
    public ListMaxClientesBD(){
    	
    }
    
    public ListMaxClientesBD(ObjectIO bdata) throws Exception {
    	
    	idemisor = bdata.getStringValue("idemisor");
    	tpclient = bdata.getStringValue("tpclient");
    	
    }       
    
    public String getInsertStatement(){
        
    
        return null;
    }
    
    public String getDeleteStatement(){
  
	    return null;
    }

	@Override
	public String getUpdateStatement() {
		
		return null;
		
	}



	@Override
	public String getSelectStatment() {
	
	
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT idclient, tpclient ");
		sql.append(" FROM jlclierc rc ");
		sql.append(" WHERE idemisor = "+ idemisor);
		sql.append(" AND tpclient = '"+ tpclient +"'");
		sql.append(" AND cdintern > 0 ");
		
		sql.append(" ORDER BY idclient DESC ");
		
		System.out.println(this.getClass().getName()+" [SQL] "+ sql);
		
	 return sql.toString(); 
   }
	
	@Override
	public Collection<String> defineSelect() {
	
    	Vector<String> select = new Vector<String>();
    	
    	select.add("idclient");
    	select.add("tpclient");
		
        return select;        
        
    }
}	
