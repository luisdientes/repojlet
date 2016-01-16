package facturacion.bd;

import java.util.Collection;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdFacturaWebBD extends UpdateQueryBD {

	String idorderx = null;
	String filecrea = null;
	
	
	
    public UpdFacturaWebBD(){
    }
    
    public UpdFacturaWebBD(ObjectIO bdata) throws Exception {
    	
    	idorderx = bdata.getStringValue("idorderx");
    	filecrea = bdata.getStringValue("filecrea");
    	
    }       
    
    public String getInsertStatement(){
        
        return null;
    }
    
    public String getDeleteStatement(){
        
        StringBuffer sql = new StringBuffer();

        return sql.toString();
    }

	@Override
	public String getUpdateStatement() {
	
		  StringBuffer sql = new StringBuffer();
		  sql.append(" UPDATE izumba_orders SET txconduc='"+filecrea+"' ");
	      sql.append(" WHERE id_order='"+idorderx+"' "); 
	      
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
