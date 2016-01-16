package personal.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListRangoFechasBD extends SelectQueryBD {

    String cduserid = null;
    String fhdesdex = null;
    String fhhastax = null;
        
    public ListRangoFechasBD(){
    }
    
    public ListRangoFechasBD(ObjectIO bdata) throws Exception {
    	
        cduserid = bdata.getStringValue("cduserid");
        fhdesdex = bdata.getStringValue("fhdesdex");
        fhhastax = bdata.getStringValue("fhhastax");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT Date_format(fhfechax,'%d/%m/%Y') as fechatra, horasinv, pr.txnombre ");
        sql.append(" from gsthoras ho,gstnproy pr ");
        sql.append(" WHERE idtrabaj = "+ cduserid);
        
        if ((fhdesdex != null) && (!fhdesdex.equals("")) && (!fhdesdex.equals("0"))){
        	sql.append(" AND fhfechax>='"+fhdesdex+"'");
	    }
       	
        if ((fhhastax != null) && (!fhhastax.equals("")) && (!fhhastax.equals("0"))){
       		sql.append(" AND fhfechax<='"+fhhastax+"'");
  	    }
        
     
        sql.append(" AND pr.idproyec = ho.idproyec ");
    	sql.append(" ORDER BY fechatra DESC");
       
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
		select.add("horasinv");
		select.add("txnombre");
		
        return select;        
    }
    
    public Collection defineOrderBy() {
        return null;
    }
    
}
