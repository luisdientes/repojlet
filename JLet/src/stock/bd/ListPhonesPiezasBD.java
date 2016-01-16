package stock.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListPhonesPiezasBD extends SelectQueryBD {

    String piezasph = null;
  
    
    public ListPhonesPiezasBD(){
    }
    
    public ListPhonesPiezasBD(ObjectIO bdata) throws Exception {
    	
    	piezasph = bdata.getStringValue("piezasph");
    	
 
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();
        
        sql.append(" SELECT ma.txnombre, ph.idgrupox, ph.txmodelo, ph.imagedet from izummarc ma, izumgrph ph ");
        sql.append("where ma.idmarcax = ph.idmarcax ");
        sql.append("AND idgrupox IN("+piezasph+")");   
        sql.append(" ORDER BY txnombre, txmodelo ASC");
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
    	select.add("txnombre");  
    	select.add("idgrupox");
    	select.add("txmodelo");
    	select.add("imagedet");
        return select;        
    }
    
    
}
