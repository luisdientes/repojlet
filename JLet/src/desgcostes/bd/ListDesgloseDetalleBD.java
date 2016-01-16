package desgcostes.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;
 
public class ListDesgloseDetalleBD extends UpdateQueryBD {

	String idemisor = null;
	String idunicox = null;
	String cdgrupox = null;
	String tipooper = null;
	
    public ListDesgloseDetalleBD(){
    }
    
    public ListDesgloseDetalleBD(ObjectIO bdata) throws Exception {
    	
    	idemisor = bdata.getStringValue("idemisor");
    	idunicox = bdata.getStringValue("idunicox");
    	cdgrupox = bdata.getStringValue("cdgrupox");
        
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
		
		sql.append(" SELECT cs.idunicox, cs.idemisor, cs.codedeta, cs.desvalue, cd.txnombre, cd.cdgrupox, cd.txdescri ");
		sql.append(" FROM dglsdeta cs, dglscddt cd ");
		sql.append(" WHERE cs.codedeta = cd.codedeta ");
		sql.append(" AND cs.idunicox = '"+ idunicox +"'");
		sql.append(" AND cs.idemisor = '"+ idemisor +"'");
		
		if ((cdgrupox != null) && (!cdgrupox.equals(""))){
			sql.append(" AND cd.cdgrupox = '"+ cdgrupox +"'");
		}
		
		sql.append(" ORDER BY tipooper, codedeta ");
		
		System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
	    return sql.toString();
	}
	
	@Override
	public Collection defineSelect() {
	
		Vector<String> select = new Vector<String>();
		select.add("idunicox");	
		select.add("idemisor");		
		select.add("codedeta");		
		select.add("desvalue");	
		select.add("txnombre");
		select.add("cdgrupox");	
		select.add("txdescri");	
		
        return select; 
	}
    
    
}
