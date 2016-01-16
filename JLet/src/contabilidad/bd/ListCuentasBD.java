package contabilidad.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class ListCuentasBD extends UpdateQueryBD {

	String idemisor = null;
	String idcuenta = null;
	String ultsaldo = null;
	
	String txnombre = null;
	String txdetall = null;
	String cdpaisxx = null;
	String cddivisa = null;
	String tipocuen = null;
	String numeroid = null;
;
    public ListCuentasBD(){
    	
    }
    
    public ListCuentasBD(ObjectIO bdata) throws Exception {
    	    	
    	idemisor = bdata.getStringValue("idemisor");
    	idcuenta = bdata.getStringValue("idcuenta");
    	ultsaldo = bdata.getStringValue("ultsaldo");
    	
    	txnombre = bdata.getStringValue("txnombre");
    	txdetall = bdata.getStringValue("txdetall");
    	cdpaisxx = bdata.getStringValue("cdpaisxx");
    	cddivisa = bdata.getStringValue("cddivisa");
    	tipocuen = bdata.getStringValue("tipocuen");
    	numeroid = bdata.getStringValue("numeroid");

    }       
    
    public String getInsertStatement(){
        
    	StringBuffer sql = new StringBuffer();
    	sql.append(" INSERT INTO contcuen (idemisor, txnombre, txdetall, cdpaisxx,cddivisa, tipocuen, ");
		sql.append(" numeroid, ultsaldo) ");
		sql.append(" VALUES (");
		sql.append(" '"+idemisor+"', ");
		sql.append(" '"+txnombre+"', ");
		sql.append(" '"+txdetall+"', ");
		sql.append(" '"+cdpaisxx+"', ");
		sql.append(" '"+cddivisa+"', ");
		sql.append(" '"+tipocuen+"', ");
		sql.append(" '"+numeroid+"', ");
		sql.append(" '0') ");
		
		System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
	    return sql.toString();
    }
    
    public String getDeleteStatement(){
    	return null;
			
    }

	@Override
	public String getUpdateStatement() {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE contcuen SET ");
		sql.append(" ultsaldo = '"+ultsaldo+"' ");
		sql.append(" WHERE idemisor =  "+idemisor);
		sql.append(" AND idcuenta =  "+idcuenta);
		
		
		System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
	    return sql.toString();
		
	}

	@Override
	public String getSelectStatment() {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT  idcuenta,codinter, txnombre, txdetall, cdpaisxx,cddivisa, tipocuen, ");
		sql.append(" numeroid, cdpadrex, ultsaldo ");
		sql.append(" FROM contcuen ");
		sql.append(" WHERE idemisor =  "+idemisor);
		
		if(idcuenta !=null && idcuenta !=""){
			sql.append(" AND idcuenta =  "+idcuenta);
		}
		
		System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
	    return sql.toString();
	}
	
	@Override
	public Collection defineSelect() {
	
		Vector<String> select = new Vector<String>();		
			select.add("idcuenta");	
		    select.add("codinter");		
			select.add("txnombre");	
			select.add("txdetall");		
			select.add("cdpaisxx");	
			select.add("cddivisa");	
			select.add("tipocuen");	
			select.add("numeroid");	
			select.add("cdpadrex");	
			select.add("ultsaldo");	
		
		return select; 
	}
    
    
}
