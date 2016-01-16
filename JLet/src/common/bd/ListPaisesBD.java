package common.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class ListPaisesBD extends UpdateQueryBD {

	String isocodex = "";
	String txnombre = "";
	String txmoneda = "";
	String simbolmo = "";
	String txcontin = "";
	String mcactivo = "";

    
    public ListPaisesBD(){
    	
    }
    
    public ListPaisesBD(ObjectIO bdata) throws Exception {
    	
    	isocodex = bdata.getStringValue("isocodex");
    	txnombre = bdata.getStringValue("txnombre");
    	txmoneda = bdata.getStringValue("txmoneda");
    	simbolmo = bdata.getStringValue("simbolmo");
    	txcontin = bdata.getStringValue("txcontin");
    	mcactivo = bdata.getStringValue("mcactivo");
    	
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
		
		sql.append(" SELECT isocodex, txnombre, txmoneda, simbolmo, txcontin, mcactivo ");
		sql.append(" FROM jlpaises ");
		sql.append(" WHERE  1 = 1 ");
		
		if ((isocodex != null) && (!isocodex.equals(""))){
			sql.append(" AND isocodex = '"+ isocodex +"'");
		}

		if ((txnombre != null) && (!txnombre.equals(""))){
			sql.append(" AND txnombre = '"+ isocodex +"'");
		}

		if ((mcactivo != null) && (!mcactivo.equals(""))){
			sql.append(" AND mcactivo = '"+ mcactivo +"'");
		}
		
		sql.append(" ORDER BY txnombre ");
		
		System.out.println(this.getClass().getName() +" QUERY "+ sql);
		
	 return sql.toString(); 
   }
	
	@Override
	public Collection<String> defineSelect() {
	
    	Vector<String> select = new Vector<String>();
    	
    	select.add("isocodex");
    	select.add("txnombre");
    	select.add("txmoneda");
    	select.add("simbolmo");
    	select.add("txcontin");
    	select.add("mcactivo");
		
        return select;        
        
    }
}	
