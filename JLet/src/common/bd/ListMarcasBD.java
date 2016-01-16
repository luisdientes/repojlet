package common.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListMarcasBD extends SelectQueryBD {

    String idemisor = null;
        
    public ListMarcasBD(){
    }
    
    public ListMarcasBD(ObjectIO bdata) throws Exception {
    	
    
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT idmarcax, txnombre ");
        sql.append(" FROM izummarc ");
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
		select.add("idmarcax");
		select.add("txnombre");
        return select;        
    }
    
    
}
