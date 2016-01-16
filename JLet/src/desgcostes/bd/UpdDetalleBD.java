package desgcostes.bd;

import java.util.Collection;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdDetalleBD extends UpdateQueryBD {

	String idunicox = null;
	String idemisor = null;
	String codedeta = null;
	String desvalue = null;
	
    
    public UpdDetalleBD(){
    }
    
    public UpdDetalleBD(ObjectIO bdata) throws Exception {
    	
    	idunicox = bdata.getStringValue("idunicox");
    	idemisor = bdata.getStringValue("idemisor");
    	codedeta = bdata.getStringValue("codedeta");
    	desvalue = bdata.getStringValue("desvalue");

    }       
    
    public String getInsertStatement(){
        
        StringBuffer sql = new StringBuffer();
        
        sql.append(" INSERT INTO dglsdeta (idunicox, idemisor, codedeta, desvalue) ");
        sql.append(" VALUES");
        sql.append(" ('"+ idunicox +"',"+ idemisor +",'"+ codedeta +"','"+ desvalue +"')");
        
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    public String getDeleteStatement(){
        
        StringBuffer sql = new StringBuffer();

        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }

	@Override
	public String getUpdateStatement() {
		  
		StringBuffer sql = new StringBuffer();

	    sql.append(" UPDATE dglsdeta SET ");
        sql.append(" desvalue = '"+ desvalue +"'");
        sql.append(" WHERE idunicox = '"+ idunicox +"' ");
        sql.append(" AND idemisor = '"+ idemisor +"'");
        sql.append(" AND codedeta = '"+ codedeta +"'");
        
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
