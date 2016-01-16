package facturacion.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListDetalleClienteBD extends SelectQueryBD {

    String idclient = null;
    String idemisor = null;
    String tpclient = null;
    
    public ListDetalleClienteBD(){
    
    }
    
    public ListDetalleClienteBD(ObjectIO bdata) throws Exception {
    	
    	idclient = bdata.getStringValue("idclient");
    	idemisor = bdata.getStringValue("idemisor");
    	tpclient = bdata.getStringValue("tpclient");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT dt.linea1xx, dt.linea2xx, dt.linea3xx, dt.linea4xx, dt.linea5xx, dt.logoclie ");
        sql.append(" FROM jlcliedt dt, jlclierc rc ");
        sql.append(" WHERE rc.idclient = dt.idclient ");
        sql.append(" AND rc.tpclient = dt.tpclient ");
        
        if ((idemisor != null) && (!idemisor.equals(""))){
        	sql.append(" AND rc.idemisor = "+ idemisor);  
        }

        if (idclient.equals("0")){
        	sql.append(" AND rc.cdintern = 0 ");
        } else {
        	sql.append(" AND rc.idclient = "+ idclient);
        }
      
        if ((tpclient != null) && (!tpclient.equals(""))){
        	sql.append(" AND rc.tpclient = '"+ tpclient +"'");
        }
        
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    public Collection<String> defineSelect() {

    	Vector<String> select=new Vector<String>();
		select.add("linea1xx");
		select.add("linea2xx");
		select.add("linea3xx");
		select.add("linea4xx");
		select.add("linea5xx");
		select.add("logoclie");
		
        return select;        
    }
    
    
}
