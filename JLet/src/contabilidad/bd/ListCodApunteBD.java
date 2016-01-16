package contabilidad.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class ListCodApunteBD extends UpdateQueryBD {

	String idemisor = null;
	String txnombre = null;
	String ultsaldo = null;
    public ListCodApunteBD(){
    	
    }
    
    public ListCodApunteBD(ObjectIO bdata) throws Exception {
    	    	
    	idemisor = bdata.getStringValue("idemisor");
    	txnombre = bdata.getStringValue("txnombre");
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
		
		sql.append(" SELECT  codapunt,concepto ");
		sql.append(" FROM contcodi ");
		sql.append(" WHERE idemisor =  "+idemisor);
		
		if(txnombre !=null && txnombre !=""){
			sql.append(" AND txnombre LIKE ='% "+txnombre+"'");
		}
		
		System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
	    return sql.toString();
	}
	
	@Override
	public Collection defineSelect() {
	
		Vector<String> select = new Vector<String>();		
			select.add("codapunt");	
		    select.add("concepto");		
		return select; 
	}
    
    
}
