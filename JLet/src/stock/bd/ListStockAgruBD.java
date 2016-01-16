package stock.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListStockAgruBD extends SelectQueryBD {

    String cdestado = null;
        
    public ListStockAgruBD(){
    }
    
    public ListStockAgruBD(ObjectIO bdata) throws Exception {
    	
    	cdestado = bdata.getStringValue("cdestado");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT txmarcax, txmodelo, SUM(quantity) numunida ");
        sql.append(" FROM tradstoc ");
        sql.append(" WHERE mcactivo = 'S' ");
        sql.append(" GROUP BY idproduc ");
        sql.append(" ORDER BY numunida DESC ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
    	select.add("txmarcax");
    	select.add("txmodelo");
    	select.add("numunida");

        return select;        
    }
    
    
}
