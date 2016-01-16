package desgcostes.bd;

import java.util.Collection;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdCostesBD extends UpdateQueryBD {

	String idunicox = null;
	String idemisor = null;
	String codedesg = null;
	String desvalue = null;
	String cddivisa = null;
	
    
    public UpdCostesBD(){
    }
    
    public UpdCostesBD(ObjectIO bdata) throws Exception {
    	
    	idunicox = bdata.getStringValue("idunicox");
    	idemisor = bdata.getStringValue("idemisor");
    	codedesg = bdata.getStringValue("codedesg");
    	desvalue = bdata.getStringValue("desvalue");
    	cddivisa = bdata.getStringValue("cddivisa");

    }       
    
    public String getInsertStatement(){
        
        StringBuffer sql = new StringBuffer();
        
        sql.append(" INSERT INTO dglscost (idunicox, idemisor, codedesg, desvalue, cddivisa) ");
        sql.append(" VALUES");
        sql.append(" ('"+ idunicox +"',"+ idemisor +",'"+ codedesg +"',"+ desvalue +",'"+ cddivisa +"')");
        
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

	    sql.append(" UPDATE dglscost SET ");
        sql.append("  desvalue = "+ desvalue +"");
        sql.append(" WHERE idunicox = '"+ idunicox +"' ");
        sql.append(" AND idemisor = '"+ idemisor +"'");
        sql.append(" AND codedesg = '"+ codedesg +"'");
        
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
