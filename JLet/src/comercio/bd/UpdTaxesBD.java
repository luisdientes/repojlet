package comercio.bd;

import java.util.Collection;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdTaxesBD extends UpdateQueryBD {
	
	String idlineax = null;
	String custotax = null;
	String consutax = null;
	String fletecst = null;
	String itbisimp = null;
	String tramadua = null;
	String almacena = null;
	String movconte = null;
	String cargnavi = null;

	
  
  	 
    public UpdTaxesBD(){
    }
    
    public UpdTaxesBD(ObjectIO bdata) throws Exception {
    	  
    	 idlineax = bdata.getStringValue("idlineax"); 
    	 custotax = bdata.getStringValue("custotax");
    	 consutax = bdata.getStringValue("consutax");
    	 fletecst = bdata.getStringValue("fletecst");
    	 itbisimp = bdata.getStringValue("itbisimp");
    	 tramadua = bdata.getStringValue("tramadua");
    	 almacena = bdata.getStringValue("almacena");
    	 movconte = bdata.getStringValue("movconte");
    	 cargnavi = bdata.getStringValue("cargnavi");

        
    }       
    
    public String getInsertStatement(){
        
        StringBuffer sql = new StringBuffer();
        
        sql.append(" INSERT INTO tradtaxe (idtaxexx, custotax, consutax,fletecst, itbisimp, tramadua, almacena, movconte,cargnavi)");
        sql.append(" VALUES ");
        sql.append(" ('"+ idlineax +"','"+ custotax +"','"+ consutax +"','"+ fletecst +"','"+ itbisimp +"'");
        sql.append(",'"+ tramadua +"','"+ almacena +"','"+ movconte +"','"+ cargnavi +"')");
        
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
