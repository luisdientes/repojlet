package desgcostes.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class ListCodigoDetalleBD extends UpdateQueryBD {

	String cdgrupox = null;
	String tipooper = null;
	String mcactivo = null;
  
  	  
    public ListCodigoDetalleBD(){
    }
    
    public ListCodigoDetalleBD(ObjectIO bdata) throws Exception {
    	    	
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
		
		sql.append(" SELECT codedeta, txnombre, cdgrupox, txdescri ");
		sql.append(" FROM dglscddt ");
		sql.append(" WHERE 1 = 1 ");
		
		if ((tipooper != null) && (!tipooper.equals("") && (!tipooper.equals("T")))){
			sql.append(" AND tipooper = '"+ tipooper +"'");
		}
		
		if ((cdgrupox != null) && (!cdgrupox.equals(""))){
			sql.append(" AND cdgrupox = '"+ cdgrupox +"'");
		}
		
		if ((mcactivo != null) && (!mcactivo.equals(""))){
			sql.append(" AND mcactivo = '"+ mcactivo +"'");
		}
		
		sql.append(" ORDER BY tipooper, codedesg ");
		
		System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
	    return sql.toString();
	}
	
	@Override
	public Collection defineSelect() {
	
		Vector<String> select = new Vector<String>();
		select.add("codedeta");	
		select.add("txnombre");		
		select.add("cdgrupox");		
		select.add("txdescri");	
		
        return select; 
	}
    
    
}
