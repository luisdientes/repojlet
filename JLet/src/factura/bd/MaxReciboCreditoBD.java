package factura.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class MaxReciboCreditoBD extends SelectQueryBD {
	
	String idemisor = null;
    String tpfactur = null;
   
        
    public MaxReciboCreditoBD(){
    	
    	
    }
    
    public MaxReciboCreditoBD(ObjectIO bdata) throws Exception {
    	
    	
        idemisor = bdata.getStringValue("idemisor");
        tpfactur = bdata.getStringValue("tpfactur");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT idrecibo ");
        sql.append(" FROM jlrecibo ");
        sql.append(" WHERE idemisor ="+idemisor);
        sql.append(" AND tpfactur ='"+tpfactur+"'");
        sql.append(" ORDER BY idrecibo DESC LIMIT 0,1 ");
     
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
		select.add("idrecibo");	
        return select;        
    }
    
    
}
