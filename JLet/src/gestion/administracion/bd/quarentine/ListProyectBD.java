package gestion.administracion.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListProyectBD extends SelectQueryBD {

    String cduserid = null;
    String idproyec = null;
    String cdrolxxx = null;
    String username = null;
    String password = null;
    String idemisor = null;
    
    public ListProyectBD(){
    
    }
    
    public ListProyectBD(ObjectIO bdata) throws Exception {
    	
        cduserid = bdata.getStringValue("cduserid");
        idproyec = bdata.getStringValue("idproyec");
        idemisor = bdata.getStringValue("idemisor");
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT txnombre, idproyec, idempres, tpproyect, numperso, txdescri, txduracio, fhinicio, fhfinxxx ");
        sql.append(" FROM gstnproy ");
        sql.append("WHERE 1 = 1");
        
        if ((cduserid != null) && (!cduserid.equals("")) && (!cduserid.equals("0"))){
        	sql.append(" AND idtrabaj = "+ cduserid);
        }
        
        if ((idproyec != null) && (!idproyec.equals("")) && (!idproyec.equals("0"))){
        	sql.append(" AND idproyec = "+ idproyec);
        }
        
        if ((idemisor != null) && (!idemisor.equals("")) && (!idemisor.equals("0"))){
        	sql.append(" AND idemisor = "+ idemisor);
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
		select.add("idempres");
		select.add("tpproyect");
		select.add("numperso");
		select.add("txdescri");
		select.add("txduracio");
		/*select.add("fhinicio");
		select.add("fhfinxxx");*/
        return select;        
    }
    
    
}
