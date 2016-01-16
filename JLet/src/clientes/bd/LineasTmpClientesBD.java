package clientes.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class LineasTmpClientesBD extends UpdateQueryBD {


	String idclient = "";
	String tpclient = "";
	String idemisor = "";
	String clientea = "";
    
    public LineasTmpClientesBD(){
    }
    
    public LineasTmpClientesBD(ObjectIO bdata) throws Exception {

    	idclient = bdata.getStringValue("idclient");
    	tpclient = bdata.getStringValue("tpclient");
    	idemisor = bdata.getStringValue("idemisor");
    	clientea = bdata.getStringValue("clientea");
    
    }       
    
    public String getInsertStatement(){
        
     return null;
    }
    
    public String getDeleteStatement(){
    
	    return null;
    }

	@Override
	public String getUpdateStatement() {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("UPDATE tmpfactu SET ");
		sql.append("idclient = '"+idclient+"' ");
		sql.append(" WHERE  idclient = "+clientea);
		sql.append(" AND   idemisor = "+idemisor);
		sql.append(" AND   tpclient = "+tpclient);
		
		System.out.println(sql.toString());
		return sql.toString();
	}

	@Override
	public String getSelectStatment() {
		
	 return null;
   }
	
	@Override
	public Collection defineSelect() {
	
    	Vector<String> select = new Vector<String>();
    	
    	select.add("idfactur");
        return select;        
    }
}	
