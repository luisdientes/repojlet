package clientes.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdClientesDetalleBD extends UpdateQueryBD {


	String idclient = "";
	String tpclient = "";
	String rzsocial = "";
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
	String txpaisxx = "";
	String txprovin = "";
	
	String linea1xx = "";
	String linea2xx = "";
	String linea3xx = "";
	String linea4xx = "";
	String linea5xx = "";
    
    public UpdClientesDetalleBD(){
    }
    
    public UpdClientesDetalleBD(ObjectIO bdata) throws Exception {

    	idclient = bdata.getStringValue("idclient");
    	tpclient = bdata.getStringValue("tpclient");
    	rzsocial = bdata.getStringValue("rzsocial");
    	idemisor = bdata.getStringValue("idemisor");
    	idfiscal = bdata.getStringValue("idfiscal");
    	txdirecc = bdata.getStringValue("txdirecc");
    	txciudad = bdata.getStringValue("txciudad");
    	cdpostal = bdata.getStringValue("cdpostal");
    	txmailxx = bdata.getStringValue("txmailxx");
    	tfnofijo = bdata.getStringValue("tfnofijo");
    	tfnomovi = bdata.getStringValue("tfnomovi");
    	tfnofaxx = bdata.getStringValue("tfnofaxx");
    	txwebxxx = bdata.getStringValue("txwebxxx");
    	txpaisxx = bdata.getStringValue("txpaisxx");
    	txprovin = bdata.getStringValue("txprovin");
      
    	linea1xx = rzsocial;
    	linea2xx = idfiscal;
    	linea3xx = txdirecc;
    	linea4xx = cdpostal +", "+ txciudad;        
    	linea5xx = txmailxx;
    }       
    
    public String getInsertStatement(){
        
        StringBuffer sql = new StringBuffer();
        sql.append(" INSERT INTO jlcliedt (idclient,tpclient,linea1xx,linea2xx,linea3xx,linea4xx,linea5xx)");
        sql.append(" VALUES ");
        sql.append(" ("+ idclient +",'"+ tpclient +"','"+ linea1xx +"','"+ linea2xx +"','"+ linea3xx +"'");
        sql.append(" , '"+ linea4xx +"','"+ linea5xx +"');");
        
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    public String getDeleteStatement(){
  
        
	    return null;
    }

	@Override
	public String getUpdateStatement() {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("UPDATE jlcliedt SET ");
		sql.append("linea1xx = '"+linea1xx+"', ");
		sql.append("linea2xx = '"+linea2xx+"', ");
		sql.append("linea3xx = '"+linea3xx+"', ");
		sql.append("linea4xx = '"+linea4xx+"', ");
		sql.append("linea5xx = '"+linea5xx+"' ");
		sql.append("WHERE  idclient = "+idclient);
		return sql.toString();
		
	}

	@Override
	public String getSelectStatment() {
	
	
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT idclient, rzsocial, idemisor,txdirecc ,idfiscal, txciudad, tfnofijo,cdpostal,txpaisxx,txprovin,txmailxx, tfnofijo,tfnomovi,tfnofaxx,txwebxxx, DATE_FORMAT(fhaltaxx, '%d/%m/%Y'),dt.linea1xx as emisorxx ");
		sql.append(" FROM jlclierc,jlcliedt dt ");
		sql.append(" WHERE  idemisor = dt.idclient ");
		
		 if ((idclient !=null) && (!idclient.equals(""))){
	        	sql.append(" AND idclient = "+idclient);
	        }
	
	 return sql.toString(); 
   }
	
	@Override
	public Collection defineSelect() {
	
    	Vector<String> select = new Vector<String>();
    	
    	select.add("idclient");
		select.add("rzsocial");
		select.add("idemisor");
		select.add("txdirecc");
		select.add("idfiscal");
		select.add("txciudad");
		select.add("tfnofijo");
		select.add("cdpostal");
		select.add("txpaisxx");
		select.add("txprovin");
		select.add("txmailxx");
		select.add("tfnofijo");
		select.add("tfnomovi");
		select.add("tfnofaxx");
		select.add("txwebxxx");
		select.add("fhaltaxx");
		select.add("emisorxx");
		
        return select;        
    }
}	
