package common.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListColoresBD extends SelectQueryBD {

    String cddivisa = null;
        
    public ListColoresBD(){
    }
    
    public ListColoresBD(ObjectIO bdata) throws Exception {
    	   
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT idcolorx, txnombre ");
        sql.append(" FROM izumcolo ");
        sql.append(" ORDER BY txnombre DESC");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
		select.add("idcolorx");
		select.add("txnombre");
		
        return select;        
    }
    
    
}
