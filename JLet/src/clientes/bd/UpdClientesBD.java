package clientes.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdClientesBD extends UpdateQueryBD {

	String idclient = "";
	String tpclient = "";
	String rzsocial = "";
	String cdintern = "";
	String idfiscal = "";
	String txdirecc = "";
	String txciudad = "";
	String cdpostal = "";
	String txmailxx = "";
	String tfnofijo = "";
	String tfnomovi = "";
	String tfnofaxx = "";
	String txwebxxx = "";
	String idemisor = "";
	String txpaisxx = "";
	String txprovin = "";
    
    public UpdClientesBD(){
    }
    
    public UpdClientesBD(ObjectIO bdata) throws Exception {

    	idemisor = bdata.getStringValue("idemisor");
    	idclient = bdata.getStringValue("idclient");
    	tpclient = bdata.getStringValue("tpclient");
    	rzsocial = bdata.getStringValue("rzsocial");
    	idemisor = bdata.getStringValue("idemisor");
    	cdintern = bdata.getStringValue("cdintern");
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
    	
    }       
    
    public String getInsertStatement(){
        
        StringBuffer sql = new StringBuffer();
        
        sql.append(" INSERT INTO jlclierc (idclient, tpclient, rzsocial,cdintern,idemisor,idfiscal, txdirecc, txciudad, cdpostal,");
        sql.append(" txpaisxx,txprovin,txmailxx, tfnofijo, tfnomovi, tfnofaxx, txwebxxx,fhaltaxx)");
        sql.append(" VALUES ");
        sql.append(" ("+ idclient +",'"+ tpclient +"','"+ rzsocial +"',"+ cdintern +",'"+idemisor+"','"+idfiscal+"','"+txdirecc+"','"+txciudad+"','"+cdpostal+"',");
        sql.append(" '"+txpaisxx+"','"+txprovin+"','"+txmailxx+"','"+tfnofijo+"','"+tfnomovi+"','"+tfnofaxx+"','"+txwebxxx+"',CURRENT_DATE)");
        
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    public String getDeleteStatement(){
  
    	StringBuffer sql = new StringBuffer();
    	sql.append("DELETE FROM jlcliedt");
    	sql.append(" WHERE idclient ="+idclient);
    	sql.append(" AND tpclient ="+tpclient);
        
    	System.out.println(sql.toString());
	    return sql.toString();
    }
    

	@Override
	public String getUpdateStatement() {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" UPDATE jlclierc SET ");
		sql.append(" rzsocial = '"+rzsocial+"', ");
		sql.append(" idemisor = '"+idemisor+"', ");
		sql.append(" idfiscal = '"+idfiscal+"', ");
		sql.append(" txdirecc = '"+txdirecc+"', ");
		sql.append(" txciudad = '"+txciudad+"', ");
		sql.append(" cdpostal = '"+cdpostal+"', ");
		sql.append(" txpaisxx = '"+txpaisxx+"', ");
		sql.append(" txprovin = '"+txprovin+"', ");
		sql.append(" txmailxx = '"+txmailxx+"', ");
		sql.append(" tfnofijo = '"+tfnofijo+"', ");
		sql.append(" tfnomovi = '"+tfnomovi+"', ");
		sql.append(" tfnofaxx = '"+tfnofaxx+"', ");
		sql.append(" txwebxxx = '"+txwebxxx+"' ");
		sql.append(" WHERE  idclient = "+idclient);
		sql.append(" AND  tpclient = "+ tpclient);
		return sql.toString();
		
	}



	@Override
	public String getSelectStatment() {
	
	
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT idlcient, tpclient, rzsocial, idemisor,txdirecc ,idfiscal, txciudad, tfnofijo,cdpostal,txpaisxx,txprovin,txmailxx, tfnofijo,tfnomovi,tfnofaxx,txwebxxx, DATE_FORMAT(fhaltaxx, '%d/%m/%Y'),dt.linea1xx as emisorxx ");
		sql.append(" FROM jlclierc rc,jlcliedt dt ");
		sql.append(" WHERE  rc idemisor = dt.idclient ");
		sql.append(" AND rc.tpclient = dt.tpclient ");
		
		 if ((idclient !=null) && (!idclient.equals(""))){
	        	sql.append(" AND idlcient= "+idclient);
	        }
	
	 return sql.toString(); 
   }
	
	@Override
	public Collection defineSelect() {
	
    	Vector<String> select = new Vector<String>();
    	
    	select.add("idclient");
    	select.add("tpclient");
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
