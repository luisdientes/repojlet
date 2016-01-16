package recibos.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListFacturaBD extends SelectQueryBD {

    String idemisor = null;
    String tpclient = null;
        
    public ListFacturaBD(){
    }
    
    public ListFacturaBD(ObjectIO bdata) throws Exception {

    	idemisor = bdata.getStringValue("idemisor");
    	tpclient = bdata.getStringValue("tpclient");
 
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT idclient,cdfactur ");
        sql.append(" FROM jlfactur");
        sql.append(" WHERE idemisor ='"+idemisor+"'");
        sql.append(" AND tpclient ='"+tpclient+"'");
        sql.append(" AND tipofact > 0");
        sql.append(" ORDER BY cdfactur DESC ");
        
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	
    	
    	Vector<String> select = new Vector<String>();
		select.add("idclient");
		select.add("cdfactur");
		
        return select;        
    }
    
    
}
