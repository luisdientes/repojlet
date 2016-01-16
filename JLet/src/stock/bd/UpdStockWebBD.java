package stock.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdStockWebBD extends UpdateQueryBD {

	String id_shopx = null;
	String refeprod = null;
	String idproduc = null;
	
    public UpdStockWebBD(){
    }
    
    public UpdStockWebBD(ObjectIO bdata) throws Exception {

    	id_shopx = bdata.getStringValue("id_shopx");
    	refeprod = bdata.getStringValue("refeprod"); 
    	idproduc = bdata.getStringValue("idproduc"); 

    }       
    
    public String getInsertStatement(){
        
        StringBuffer sql = new StringBuffer();
        
      
        
        return sql.toString();
    }
    
    public String getDeleteStatement(){
  
    	StringBuffer sql = new StringBuffer();
        
	    
	        
	    return sql.toString();
    }

	@Override
	public String getUpdateStatement() {
		
	        
		StringBuffer sql = new StringBuffer();
		  sql.append(" UPDATE izumba_stock_available SET quantity = quantity -1 ");
	      sql.append(" WHERE id_shop='"+id_shopx+"' "); 
	      sql.append(" AND id_product_attribute='0' "); 
	      sql.append(" AND id_product='"+idproduc+"' "); 
	      
	      System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
	      
	        
	    return sql.toString();
		
	}

	@Override
	public Collection defineSelect() {
		Vector<String> select = new Vector<String>();
    	select.add("id_product");
    	 return select;
	}

	@Override
	public String getSelectStatment() {
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id_product from izumba_product WHERE reference = '"+refeprod+"'");
		
		return sql.toString();
	}
    
    
}
