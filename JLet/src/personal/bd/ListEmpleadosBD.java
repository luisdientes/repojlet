package personal.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListEmpleadosBD extends SelectQueryBD {

    String cduserid = null;
    String cdrolxxx = null;
    String username = null;
    String password = null;
    String idemisor = null;
    
    public ListEmpleadosBD(){
    
    }
    
    public ListEmpleadosBD(ObjectIO bdata) throws Exception {
    	
        cduserid = bdata.getStringValue("cduserid");
        idemisor = bdata.getStringValue("idemisor");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT idacceso, txnombre, txapelli, telefono, idempres, nifcifxx, txmailxx, txcoment, txdirecc,Date_format(fhaltaxx,'%d/%m/%Y')  ");
        sql.append(" FROM gstntrab ");
        sql.append(" WHERE 1 = 1 ");
        
        if ((cduserid != null) && (!cduserid.equals("")) && (!cduserid.equals("0"))){
        	sql.append(" AND idacceso = "+ cduserid);
        }
        if ((idemisor != null) && (!idemisor.equals("")) && (!idemisor.equals("0"))){
        	sql.append(" AND idemisor = "+ idemisor);
        }
        
        sql.append(" ORDER BY txnombre ");
        
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("F QUERY - "+ this.getClass().getName() +"-"+ sql);
        
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
