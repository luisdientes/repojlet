package cloud.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class ListVersionesBD extends UpdateQueryBD {

	String idemisor = "";
	String idinodox = "";
	String filepath = "";
	String tipofich = "";
	String txnombre = "";
    
    public ListVersionesBD(){
    	
    }
    
    public ListVersionesBD(ObjectIO bdata) throws Exception {
    	idinodox = bdata.getStringValue("idinodox");
    	
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
		
		sql.append(" SELECT txnombre,count(ver.idinodox) from cloufich fic, clouvers ver   ");
		sql.append(" WHERE fic.idinodox = ver.idinodox");
		sql.append(" AND ver.idinodox='"+idinodox+"'");	
		sql.append(" ORDER BY txnombre ASC ");
		
		System.out.println(this.getClass().getName()+" [SQL] "+ sql);
		
	 return sql.toString(); 
   }
	
	@Override
	public Collection<String> defineSelect() {
	
    	Vector<String> select = new Vector<String>();
    	
    	select.add("txnombre");
    	select.add("cuantosx");
		
        return select;        
        
    }
}	
