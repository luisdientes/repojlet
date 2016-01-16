package contabilidad.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class CompUltSaldoBD extends UpdateQueryBD {

	String idemisor = null;
	String idcuenta = null;
    public CompUltSaldoBD(){
    	
    }
    
    public CompUltSaldoBD(ObjectIO bdata) throws Exception {
    	    	
    	idemisor = bdata.getStringValue("idemisor");
    	idcuenta = bdata.getStringValue("idcuenta");
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
	
		sql.append(" SELECT SUM(CASE debhaber  ");
		sql.append("  WHEN 'D' THEN cantidad ");
		sql.append("  WHEN 'H' THEN -cantidad" );
		sql.append("  ELSE cantidad END)," );
		sql.append("  COUNT(*)" );
		sql.append("  From contapun " );
		sql.append(" WHERE idemisor ='"+idemisor+"'");
		sql.append(" AND idcuenta ='"+idcuenta+"'");
		
		
		System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
	    return sql.toString();
	}
	
	@Override
	public Collection defineSelect() {
	
		Vector<String> select = new Vector<String>();		
			select.add("ultsaldo");	
		    select.add("numapunt");		
		return select; 
	}
    
    
}
