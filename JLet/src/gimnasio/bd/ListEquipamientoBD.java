package gimnasio.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class ListEquipamientoBD extends UpdateQueryBD {

	String idclient = null;
	String cdequipa = null;
	String idmarcax = null;
	String txmodelo = null;
	String tipogama = null;
	String cantidad = null;
	String aniocomp = null;
	String cdestado = null;
	

  
  	 
    public ListEquipamientoBD(){
    }
    
    public ListEquipamientoBD(ObjectIO bdata) throws Exception {
    	    	
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
		
		sql.append(" SELECT cli.txmodelo, cli.tipogama, cli.cantidad, cli.aniocomp,cli.tipogama,cli.cdestado, eq.cdfamili, eq.txnombre,ma.txmarcax ");
		sql.append(" FROM rf4ucleq cli, rf4uequi eq, rf4umarc ma");
		sql.append(" WHERE cli.cdequipa = eq.cdequipa ");
		sql.append(" AND cli.idmarcax = ma.idmarcax ");
		sql.append(" AND idclient = '"+idclient+"'");
		sql.append(" ORDER BY eq.cdfamili ASC, eq.txnombre ASC ");
		System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
	    return sql.toString();
	}
	
	@Override
	public Collection defineSelect() {
	
		Vector<String> select = new Vector<String>();
		select.add("txmodelo");	
		select.add("tipogama");		
		select.add("cantidad");	
		select.add("aniocomp");	
		select.add("tipogama");		
		select.add("cdestado");
		select.add("cdfamili");		
		select.add("txnombre");
		select.add("txmarcax");
        return select; 
	}
    
    
}
