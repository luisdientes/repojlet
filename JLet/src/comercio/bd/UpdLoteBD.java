package comercio.bd;

import java.util.Collection;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdLoteBD extends UpdateQueryBD {

	String codeenvi = null;
	String fhcreaci = null;
	String mcactivo = "S";
  
  	 
    public UpdLoteBD(){
    }
    
    public UpdLoteBD(ObjectIO bdata) throws Exception {
    	    	
    	codeenvi = bdata.getStringValue("codeenvi");

        
    }       
    
    public String getInsertStatement(){
        
        StringBuffer sql = new StringBuffer();
        
        sql.append(" INSERT INTO izumlote (cdextern,mcactivo,idprovee)");
        sql.append(" VALUES ");
        sql.append(" ('"+ codeenvi +"','"+ mcactivo +"','43')");
        
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
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
	public Collection defineSelect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSelectStatment() {
		// TODO Auto-generated method stub
		return null;
	}
    
    
}
