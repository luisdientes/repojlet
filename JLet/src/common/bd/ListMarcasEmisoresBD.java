package common.bd;

import java.util.Collection;
import java.util.Vector;

import com.enterprisedt.util.debug.Logger;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListMarcasEmisoresBD extends SelectQueryBD {

    String idemisor = null;
        
    public ListMarcasEmisoresBD(){
    }
    
    public ListMarcasEmisoresBD(ObjectIO bdata) throws Exception {
    	
    
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT me.idemisor, ma.idmarcax, ma.txnombre ");
        sql.append(" FROM izummarc ma, izmaremi me ");
        sql.append(" WHERE ma.idmarcax = me.idmarcax ");
        sql.append(" ORDER BY me.idemisor, ma.txnombre ");
       
        Logger.getLogger("BBDD").debug("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
        
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
    	select.add("idemisor");
    	select.add("idmarcax");
		select.add("txnombre");
        return select;        
    }
    
    
}
