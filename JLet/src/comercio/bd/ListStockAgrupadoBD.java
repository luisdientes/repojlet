package comercio.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListStockAgrupadoBD extends SelectQueryBD {

    String cdestado = null;
    String idemisor = null;
    String tipocons = null;
    
    public ListStockAgrupadoBD(){
    }
    
    public ListStockAgrupadoBD(ObjectIO bdata) throws Exception {
    	
    	idemisor = bdata.getStringValue("idemisor");
    	cdestado = bdata.getStringValue("cdestado");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT txmarcax, txmodelo, SUM(quantity)");
        sql.append(" FROM tradstoc ");
        sql.append(" WHERE mcactivo = 'S' ");
        sql.append(" AND idemisor = '"+ idemisor +"'");
        
        if ((cdestado != null) && (!cdestado.equals(""))){
        	sql.append(" AND cdestado = '"+ cdestado +"'");
        } else {
        	sql.append(" AND cdestado = 'PRESTOCK' ");
        }
        
        sql.append(" GROUP BY txmarcax, txmodelo ");
        sql.append(" ORDER BY txmarcax, txmodelo ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
    	select.add("txmarcax");
    	select.add("txmodelo");
    	select.add("quantity");
    	
        return select;        
    }
    
    
}
