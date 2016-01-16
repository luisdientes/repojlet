package reparaciones.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListReparaBD extends SelectQueryBD {

    String idemisor = null;
    String idrecibo = null;
    String tpclient = null;
    String cdrecibo = null;
    String fhdesdex = null;
    String fhhastax = null;
    String cdestado = null;
        
    public ListReparaBD(){
    }
    
    public ListReparaBD(ObjectIO bdata) throws Exception {
    	
    	idemisor = bdata.getStringValue("idemisor");
    	idrecibo = bdata.getStringValue("idrecibo");
    	tpclient = bdata.getStringValue("tpclient");
    	cdrecibo = bdata.getStringValue("cdrecibo");	
    	fhdesdex = bdata.getStringValue("fhdesdex");
    	fhhastax = bdata.getStringValue("fhhastax");
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT idrecibo, txnombre,idemisor, cdrecibo, tpclient, txmodelo, txcolorx, txmarcax, txdescri, tximeixx,DATE_FORMAT(fhentrad,'%d/%m/%Y') fhentrad,");
        sql.append(" filereci, costordx,costcheq, perconta,telefono, txmailxx, tiempent, garantia, entregad, recibido ");
        sql.append("  FROM izuentra ");
        sql.append("  WHERE 1=1 ");
        
        if ((idemisor != null) && (!idemisor.equals(""))){
        	sql.append(" AND  idemisor = "+ idemisor);
        }
        if ((cdrecibo != null) && (!cdrecibo.equals(""))){
        	sql.append(" AND  cdrecibo = '"+ cdrecibo+"'");
        } 
        if ((idrecibo != null) && (!idrecibo.equals(""))){
        	sql.append(" AND  idrecibo = '"+ idrecibo+"'");
        } 
        
        
        
        sql.append(" ORDER BY idrecibo DESC "); 
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
    	select.add("idrecibo");
		select.add("txnombre");
		select.add("idemisor");
		select.add("cdrecibo");
		select.add("tpclient");
		select.add("txmodelo");
		select.add("txcolorx");
		select.add("txmarcax");
		select.add("txdescri");
		select.add("tximeixx");
		select.add("fhentrad");
		select.add("filereci");
		select.add("costordx");
		select.add("costcheq");
		select.add("perconta");
		select.add("telefono");
		select.add("txmailxx");
		select.add("tiempent");
		select.add("garantia");
		select.add("entregad");
		select.add("recibido");
		
        return select;        
    }
    
    
}
