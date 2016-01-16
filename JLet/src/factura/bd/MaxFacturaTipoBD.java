package factura.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class MaxFacturaTipoBD extends SelectQueryBD {

    String idemisor = null;
        
    public MaxFacturaTipoBD(){
    }
    
    public MaxFacturaTipoBD(ObjectIO bdata) throws Exception {
    	
        idemisor = bdata.getStringValue("idemisor");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();
        sql.append("  SELECT aniofact,tipofact, cdfactur - 1 ");
        sql.append(" FROM jlcodfra cf");
        sql.append(" WHERE idemisor = "+ idemisor);
        sql.append(" AND aniofact = (SELECT MAX(aniofact) from jlcodfra co where cf.idemisor = co.idemisor )");
        
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
    	select.add("aniofact");
		select.add("tipofact");
		select.add("cdfactur");
		
        return select;        
    }
    
    
}
