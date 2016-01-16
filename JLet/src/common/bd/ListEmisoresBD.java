package common.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListEmisoresBD extends SelectQueryBD {

    String tpclient = null;
        
    public ListEmisoresBD(){
    }
    
    public ListEmisoresBD(ObjectIO bdata) throws Exception {
    	
        tpclient = bdata.getStringValue("tpclient");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        
        sql.append(" SELECT rc.idclient, rc.rzsocial, rc.idfiscal, rc.txdirecc, rc.txciudad, rc.cdpostal, ");
        sql.append(" rc.txmailxx, rc.tfnofijo, rc.tfnomovi, rc.tfnofaxx, rc.txwebxxx,rc.fhaltaxx, dt.logoclie, ");
        sql.append(" dt.idclient, dt.tpclient, dt.linea1xx, dt.linea2xx, dt.linea3xx, dt.linea4xx, dt.linea5xx ");
        sql.append(" FROM jlclierc rc, jlcliedt dt ");
        sql.append(" WHERE rc.idclient = dt.idclient ");
        sql.append(" AND rc.tpclient = dt.tpclient ");
        
        //POR DEFINICION LOS cdintern 0 SON LOS EMISORES
        sql.append(" AND rc.cdintern = 0 ");
        
        sql.append(" ORDER BY rc.rzsocial ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
		select.add("idclient");
		select.add("rzsocial");
		select.add("idfiscal");
		select.add("txdirecc");
		select.add("txciudad");
		select.add("cdpostal");
		select.add("txmailxx");
		select.add("tfnofijo");
		select.add("tfnomovi");
		select.add("tfnofaxx");
		select.add("txwebxxx");
		select.add("fhaltaxx");
		select.add("logoclie");
		select.add("idclient");
		select.add("tpclient");
		select.add("linea1xx");
		select.add("linea2xx");
		select.add("linea3xx");
		select.add("linea4xx");
		select.add("linea5xx");
		
        return select;        
    }
    
    
}
