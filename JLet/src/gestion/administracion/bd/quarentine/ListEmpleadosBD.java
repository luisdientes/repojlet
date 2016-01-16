package gestion.administracion.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListEmpleadosBD extends SelectQueryBD {

    String cduserid = null;
    String cdrolxxx = null;
    String username = null;
    String password = null;
    
    public ListEmpleadosBD(){
    
    }
    
    public ListEmpleadosBD(ObjectIO bdata) throws Exception {
    	
        cduserid = bdata.getStringValue("cduserid");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT idtrabaj, txnombre, txapelli, telefono, idempres, nifcifxx, txmailxx, txcoment, txdirecc, fhaltaxx ");
        sql.append(" FROM gstntrab ");
        sql.append(" WHERE 1 = 1 ");
        
        if ((cduserid != null) && (!cduserid.equals("")) && (!cduserid.equals("0"))){
        	sql.append(" AND idtrabaj = "+ cduserid);
        }
        
        sql.append(" ORDER BY txnombre ");
        
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("FAAAAAAAAAAAAAAAAAAAAAA QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    public Collection<String> defineSelect() {

    	Vector<String> select=new Vector<String>();
		select.add("idtrabaj");
		select.add("txnombre");
		select.add("txapelli");
		select.add("telefono");
		select.add("idempres");
		select.add("nifcifxx");
		select.add("txmailxx");
		select.add("txcoment");
		select.add("txdirecc");
		select.add("fhaltaxx");
		
        return select;        
    }
    
    
}
