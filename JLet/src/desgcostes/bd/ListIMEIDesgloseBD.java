package desgcostes.bd;
 
import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class ListIMEIDesgloseBD extends UpdateQueryBD {

	String idemisor = null;
	String desvalue = null;
	String substrin = null;
	
    public ListIMEIDesgloseBD(){
    }
    
    public ListIMEIDesgloseBD(ObjectIO bdata) throws Exception {
    	
    	idemisor = bdata.getStringValue("idemisor");
    	desvalue = bdata.getStringValue("desvalue");
    	substrin = bdata.getStringValue("substrin");
        
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
		
		sql.append(" SELECT cs.idunicox ");
		sql.append(" FROM dglsdeta cs ");
		sql.append(" WHERE cs.idemisor = '"+ idemisor +"'");
		
		if ((substrin != null) && (!substrin.equals(""))){
			sql.append(" AND SUBSTRING(cs.desvalue,1,5) = SUBSTRING('"+ desvalue +"',1,"+ substrin +") ");			
		} else {
			sql.append(" AND cs.desvalue = '"+ desvalue +"' ");
		}
		
		sql.append(" ORDER BY cs.idunicox ");
		
		System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
	    return sql.toString();
	}
	
	@Override
	public Collection<String> defineSelect() {
	
		Vector<String> select = new Vector<String>();
		select.add("idunicox");	
		
        return select; 
	}
    
    
}


