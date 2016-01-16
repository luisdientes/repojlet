package comercio.bd;

import java.util.Collection;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdLineasIdProdBD extends UpdateQueryBD {

	String idproduc = null;
	String idlineax = null;
	String mcactivo = "S";
    
    public UpdLineasIdProdBD(){
    }
    
    public UpdLineasIdProdBD(ObjectIO bdata) throws Exception {
    	    	
    	idproduc = bdata.getStringValue("idproduc");
    	idlineax = bdata.getStringValue("codeenvi");

        
    }       
    
    public String getInsertStatement(){
    	
		return null;
        
    }
    
    public String getDeleteStatement(){
  
        return null;
    }

	@Override
	public String getUpdateStatement() {
		
		StringBuffer sql = new StringBuffer();
	        
	    sql.append(" UPDATE tradtmen ");
	    sql.append(" SET idproduc = '"+ idproduc +"'");
	    sql.append(" WHERE idproenv='"+idlineax+"' AND mcactivo = 'S' ");
	        
	    //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
	       
	    System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
	        
	    return sql.toString();
		
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
