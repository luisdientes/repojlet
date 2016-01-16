package gestion.administracion.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListProyectEmpleBD extends SelectQueryBD {

    String cduserid = null;
    String cdrolxxx = null;
    String username = null;
    String password = null;
    
    public ListProyectEmpleBD(){
    
    }
    
    public ListProyectEmpleBD(ObjectIO bdata) throws Exception {
    	
        cduserid = bdata.getStringValue("cduserid");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT txnombre,tr.idproyec FROM gstnproy pro, gstnprtra tr ");
        sql.append(" WHERE pro.idproyec = tr.idproyec ");
        
        if ((cduserid != null) && (!cduserid.equals("")) && (!cduserid.equals("0"))){
        	sql.append(" AND idtrabaj = "+ cduserid);
        }
        
        sql.append(" ORDER BY txnombre ");
        
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    public Collection<String> defineSelect() {

    	Vector<String> select=new Vector<String>();
		select.add("txnombre");
		select.add("idproyec");
        return select;        
    }
    
    
}
