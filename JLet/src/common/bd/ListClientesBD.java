package common.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class ListClientesBD extends UpdateQueryBD {

	String idclient = "";
	String tpclient = "";
	String rzsocial = "";
	String cdintern = "";
	String idemisor = "";
	String idfiscal = "";
	String txdirecc = "";
	String txciudad = "";
	String cdpostal = "";
	String txmailxx = "";
	String tfnofijo = "";
	String tfnomovi = "";
	String tfnofaxx = "";
	String txwebxxx = "";
    
    public ListClientesBD(){
    	
    }
    
    public ListClientesBD(ObjectIO bdata) throws Exception {
    	
    	idclient = bdata.getStringValue("idclient");
    	tpclient = bdata.getStringValue("tpclient");
    	idemisor = bdata.getStringValue("idemisor");
    	rzsocial = bdata.getStringValue("rzsocial");
    	cdintern = bdata.getStringValue("cdintern");
    	
    }       
    
    public String getInsertStatement(){
        
    
        return null;
    }
    
    public String getDeleteStatement(){
  
        
	    return null;
    }

	@Override
	public String getUpdateStatement() {
		
		return null;
		
	}



	@Override
	public String getSelectStatment() {
	
	
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT rc.idclient, IFNULL(rc.rzsocial,'') , rc.cdintern, rc.idemisor, IFNULL(rc.idfiscal,''), IFNULL(rc.txdirecc,''), IFNULL(rc.txciudad,'') ");
		sql.append(" , IFNULL(rc.cdpostal,''),rc.txpaisxx,IFNULL(rc.txprovin,''), IFNULL(rc.txmailxx,''), IFNULL(rc.tfnofijo,''),IFNULL(rc.tfnomovi,''),IFNULL(rc.tfnofaxx,''), IFNULL(rc.txwebxxx,'') ");
		sql.append(" , dt.tpclient, dt.linea1xx, dt.linea2xx, dt.linea3xx, dt.linea4xx, dt.linea5xx, dt.logoclie ");
		sql.append(" FROM jlcliedt dt, jlclierc rc ");
		sql.append(" WHERE  dt.idclient = rc.idclient ");
		sql.append(" AND dt.tpclient = rc.tpclient ");
		
		
		//sql.append(" AND rc.tpclient = '"+ tpclient +"'");
		
		
		if ((idemisor != null) && (!idemisor.equals(""))){
			sql.append(" AND rc.idemisor = "+ idemisor);
		}
		
		if ((idclient != null) && (!idclient.equals(""))){
			sql.append(" AND rc.idclient = "+ idclient);
		}
		
		if ((tpclient != null) && (!tpclient.equals(""))){
			sql.append(" AND rc.tpclient = "+ tpclient);
		}
		
		if ((rzsocial != null) && (!rzsocial.equals(""))){
			sql.append(" AND rc.rzsocial like '%"+ rzsocial +"%'");
		}
		
		if ((cdintern != null) && (cdintern.equals("0"))) {
			sql.append(" AND rc.cdintern = 0 ");
		} else {
			sql.append(" AND rc.cdintern > 0 ");
		}
		
		sql.append(" ORDER BY rc.rzsocial ");
		
		System.out.println(this.getClass().getName() +" QUERY "+ sql);
		
	 return sql.toString(); 
   }
	
	@Override
	public Collection<String> defineSelect() {
	
    	Vector<String> select = new Vector<String>();
    	
    	select.add("idclient");
		select.add("rzsocial");
    	select.add("cdintern");
		select.add("idemisor");
    	select.add("idfiscal");
		select.add("txdirecc");
    	select.add("txciudad");
		select.add("cdpostal");
		select.add("txpaisxx");
		select.add("txprovin");
    	select.add("txmailxx");
		select.add("tfnofijo");
    	select.add("tfnomovi");
		select.add("tfnofaxx");
		select.add("txwebxxx");
		select.add("tpclient");
    	select.add("linea1xx");
		select.add("linea2xx");
    	select.add("linea3xx");
		select.add("linea4xx");
		select.add("linea5xx");
    	select.add("logoclie");
		
        return select;        
        
    }
}	
