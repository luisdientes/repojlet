package cloud.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdateVersionBD extends UpdateQueryBD {

	String idinodox = "";
	String usupload = "";
	String versionx = "";
	String milisegu = "";
	
    public UpdateVersionBD(){
    	
    }
    
    public UpdateVersionBD(ObjectIO bdata) throws Exception {
    
    	idinodox = bdata.getStringValue("idinodox");
    	usupload = bdata.getStringValue("usupload");
    	versionx = bdata.getStringValue("versionx");
    	milisegu = bdata.getStringValue("milisegu");
    	
    }       
    
    public String getInsertStatement(){
    	
    	StringBuffer sql = new StringBuffer();
    	sql.append(" INSERT INTO clouvers ");
    	sql.append(" ( idinodox, versionx, usupload )");
    	sql.append(" VALUES ");
    	sql.append(" ( '"+idinodox+"','"+versionx+"','"+usupload+"')");
    
    	System.out.println("VERSIONAR ----------------->>>>>>>> "+sql.toString());
    
    return sql.toString(); 
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
		
		
		
	 return sql.toString(); 
   }
	
	@Override
	public Collection<String> defineSelect() {
	
    	Vector<String> select = new Vector<String>();
    	
    	select.add("filepath");
    	select.add("txnombre");
    	select.add("tipofich");
		
        return select;        
        
    }
}	
