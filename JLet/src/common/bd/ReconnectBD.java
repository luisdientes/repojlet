package common.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class ReconnectBD extends UpdateQueryBD {

	
    public ReconnectBD(){
    	
    }
    
    public ReconnectBD(ObjectIO bdata) throws Exception {
    	
    	
    	
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
		
		sql.append(" SELECT 1 FROM dual ");
		
		System.out.println(this.getClass().getName() +" QUERY "+ sql);
		
	 return sql.toString(); 
   }
	
	@Override
	public Collection<String> defineSelect() {
	
    	Vector<String> select = new Vector<String>();
    	
    	select.add("reconnec");
		
		
        return select;        
        
    }
}	
