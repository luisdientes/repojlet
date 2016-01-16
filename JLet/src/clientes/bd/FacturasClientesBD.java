package clientes.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class FacturasClientesBD extends UpdateQueryBD {


	String idclient = "";
	String tpclient = "";
	String idemisor = "";
	String clientea = "";
    
    public FacturasClientesBD(){
    }
    
    public FacturasClientesBD(ObjectIO bdata) throws Exception {

    	idclient = bdata.getStringValue("idclient");
    	tpclient = bdata.getStringValue("tpclient");
    	idemisor = bdata.getStringValue("idemisor");
    	clientea = bdata.getStringValue("clientea");
    
    }       
    
    public String getInsertStatement(){
        
     return null;
    }
    
    public String getDeleteStatement(){
    	StringBuffer sql = new StringBuffer();
    	sql.append("DELETE FROM jlclierc");
    	sql.append(" WHERE idclient ="+clientea);
    	sql.append(" AND idemisor ="+idemisor);
    	sql.append(" AND tpclient ="+tpclient);
        
    	System.out.println(sql.toString());
	    return sql.toString();
    }

	@Override
	public String getUpdateStatement() {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("UPDATE jlfactur SET ");
		sql.append("idclient = '"+idclient+"' ");
		sql.append(" WHERE  idclient = "+clientea);
		sql.append(" AND   idemisor = "+idemisor);
		sql.append(" AND   tpclient = "+tpclient);
		
		System.out.println(sql.toString());
		return sql.toString();
	}

	@Override
	public String getSelectStatment() {
	
	
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT idfactur ");
		sql.append(" FROM jlfactur ");
		sql.append(" WHERE  idemisor = "+idemisor);
		sql.append(" AND  tpclient ='"+tpclient+"'"); // "+tpclient);
		sql.append(" AND  idclient ='"+idclient+"'");
		System.out.println(sql.toString());
	 return sql.toString(); 
   }
	
	@Override
	public Collection defineSelect() {
	
    	Vector<String> select = new Vector<String>();
    	
    	select.add("idfactur");
        return select;        
    }
}	
