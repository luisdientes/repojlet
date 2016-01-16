package usuarios.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class ListRolBD extends UpdateQueryBD {

	String idusuari = "";
	String cdrolxxx = "";
    
    public ListRolBD(){
    	
    }
    
    public ListRolBD(ObjectIO bdata) throws Exception {
    	
    	idusuari = bdata.getStringValue("idusuari");
    	cdrolxxx = bdata.getStringValue("cdrolxxx");
    	
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

	        sql.append(" SELECT cdrolxxx ");
	        sql.append(" FROM jlpermis ");
	        sql.append(" GROUP BY cdrolxxx ");
	       
	        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
	        
	        return sql.toString();
   }
	
	@Override
	public Collection<String> defineSelect() {
	
		Vector<String> select=new Vector<String>();
		
		select.add("cdrolxxx");
        return select;        
        
    }
}	
