package excelXLSX.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListPiezasBD extends SelectQueryBD {

    String txmarcax = null;
    String idemisor = null;
    String idpiezas = null;
        
    public ListPiezasBD(){
    }
    
    public ListPiezasBD(ObjectIO bdata) throws Exception {
    	
    	txmarcax = bdata.getStringValue("txmarcax");
        idemisor = bdata.getStringValue("idemisor"); 
        idpiezas = bdata.getStringValue("idpiezas");
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT txdescri,namephon,imgdefau  ");
        sql.append(" FROM izumgrpi ");
        sql.append(" where idpiezax IN ("+idpiezas+")");
 
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
		select.add("txdescri");
		select.add("namephon");
		select.add("imgdefau");		
        return select;        
    }
    
    
}
