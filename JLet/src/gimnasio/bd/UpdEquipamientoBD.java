package gimnasio.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdEquipamientoBD extends UpdateQueryBD {

	String idclient = null;
	String cdequipa = null;
	String idmarcax = null;
	String txmodelo = null;
	String tipogama = null;
	String cantidad = null;
	String aniocomp = null;
	String cdestado = null;
	

  
  	 
    public UpdEquipamientoBD(){
    }
    
    public UpdEquipamientoBD(ObjectIO bdata) throws Exception {
    	    	
    	idclient = bdata.getStringValue("idclient");
    	cdequipa = bdata.getStringValue("cdequipa");
    	idmarcax = bdata.getStringValue("idmarcax");
    	txmodelo = bdata.getStringValue("txmodelo");
    	tipogama = bdata.getStringValue("tipogama");
    	cantidad = bdata.getStringValue("cantidad");
    	aniocomp = bdata.getStringValue("aniocomp");
    	cdestado = bdata.getStringValue("cdestado");
    

        
    }       
    
    public String getInsertStatement(){
        
        StringBuffer sql = new StringBuffer();
        
        sql.append(" INSERT INTO rf4ucleq (idclient, cdequipa, idmarcax, txmodelo, tipogama, cantidad, aniocomp,cdestado)");
        sql.append(" VALUES ");
        sql.append(" ('"+ idclient +"','"+ cdequipa +"','"+ idmarcax +"','"+ txmodelo +"','"+ tipogama +"','"+ cantidad +"',");
        sql.append(" '"+ aniocomp +"','"+ cdestado +"')");
        
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    public String getDeleteStatement(){
  
    	StringBuffer sql = new StringBuffer();
		
		sql.append(" DELETE FROM rf4ucleq ");
		sql.append(" WHERE ");
		sql.append(" idclient = '"+idclient+"'");
        
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }

	@Override
	public String getUpdateStatement() {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" UPDATE rf4ucleq SET");
		sql.append(" cdequipa = '"+cdequipa+"',"); 
		sql.append(" idmarcax = '"+idmarcax+"',");
		sql.append(" txmodelo = '"+txmodelo+"',");
		sql.append(" tipogama = '"+tipogama+"',");
		sql.append(" cantidad = '"+cantidad+"',");
		sql.append(" aniocomp = '"+aniocomp+"',");
		sql.append(" cdestado = '"+cdestado+"' ");
		sql.append(" WHERE ");
		sql.append(" idclient = '"+idclient+"'");
        
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
		
	}



	@Override
	public String getSelectStatment() {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT idclient, cdequipa, idmarcax,txmodelo, tipogama, cantidad, aniocomp,cdestado ");
		sql.append(" FROM rf4ucleq");
		sql.append(" WHERE 1=1 ");
		    
		if (idclient != null && !idclient.equals("")){
	        	sql.append(" AND idclient = '"+idclient+"'");
	    }
	    
		System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
	    return sql.toString();
	}
	
	@Override
	public Collection defineSelect() {
	
		Vector<String> select = new Vector<String>();
		select.add("idclient");	
		select.add("cdequipa");		
		select.add("idmarcax");	
		select.add("txmodelo");	
		select.add("tipogama");		
		select.add("cantidad");
		select.add("aniocomp");		
		select.add("cdestado");
        return select; 
	}
    
    
}
