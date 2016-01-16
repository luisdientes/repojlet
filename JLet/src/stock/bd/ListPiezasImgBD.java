package stock.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListPiezasImgBD extends SelectQueryBD {

	String txmarcax = null;
    String idemisor = null;
    String idgrupox = null;
        
    public ListPiezasImgBD(){
    }
    
    public ListPiezasImgBD(ObjectIO bdata) throws Exception {
    	
    	txmarcax = bdata.getStringValue("txmarcax");
        idemisor = bdata.getStringValue("idemisor");
        idgrupox = bdata.getStringValue("idgrupox");
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();


        sql.append(" SELECT distinct pi.idpiezax,txdescri,namephon,imgdefau ");
        sql.append(" from izumgrpi pi ");
        sql.append(" LEFT JOIN izumpiph ph ");
        sql.append(" ON pi.idpiezax = ph.idpiezax  ");
        sql.append(" WHERE 1 = 1  ");
        
        if(idgrupox != null && !idgrupox.equals("")){
        	sql.append(" AND ph.cdgroupx ='"+idgrupox+"'");
        }
        
        sql.append(" order by txdescri ");
 
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
    	select.add("idpiezax");
		select.add("txdescri");
		select.add("namephon");
		select.add("imgdefau");		
        return select;        
    }
    
    
}
