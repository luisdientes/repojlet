package subasta.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListSubasCotiBD extends SelectQueryBD {

    String idcodsub = null;
        
    public ListSubasCotiBD(){
    }
    
    public ListSubasCotiBD(ObjectIO bdata) throws Exception {
    	
    	idcodsub = bdata.getStringValue("idcodsub");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        
        sql.append(" SELECT sc.idcodsub, cotizaci, cddivisa, sc.fechahor ");
        sql.append(" FROM jlsubcot sc, ");
	        
        	sql.append("( SELECT idcodsub, MAX(fechahor) AS fechamax ");
	        sql.append(" FROM jlsubcot ");
	        sql.append(" WHERE 1 = 1 ");
	        if ((idcodsub != null) && (!idcodsub.equals(""))){
	        	sql.append(" AND  idcodsub IN ("+ idcodsub +")");
	        }  
	        sql.append(" GROUP BY idcodsub) mx "); 
	        
	    sql.append(" WHERE sc.idcodsub = mx.idcodsub AND sc.fechahor = mx.fechamax");
	    sql.append(" ORDER BY idcodsub ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
		select.add("idcodsub");
		select.add("cotizaci");
		select.add("cddivisa");
		select.add("fechahor");
		
        return select;        
    }
    
    
}
