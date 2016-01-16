package personal.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListFechasBD extends SelectQueryBD {

    String cduserid = null;
        
    public ListFechasBD(){
    }
    
    public ListFechasBD(ObjectIO bdata) throws Exception {
    	
        cduserid = bdata.getStringValue("cduserid");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT fhfechax ");
        sql.append(" FROM gsthoras ");
        sql.append(" WHERE idtrabaj = "+ cduserid);
        
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    public Collection defineFrom() {
        return null;
    }
    
    public Collection defineGroupBy() {
        return null;
    }
    
    public Collection defineWhere() {
        return null;
    }
    
    public Collection defineSelect() {

    	Vector select=new Vector();
		select.add("fechatra");
		
        return select;        
    }
    
    public Collection defineOrderBy() {
        return null;
    }
    
}
