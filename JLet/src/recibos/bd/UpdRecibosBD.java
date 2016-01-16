package recibos.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdRecibosBD extends UpdateQueryBD {

	String idclient = null;
	String idemisor = null;
	String tpclient = null;
	String cantidad = null;
	String concepto = null;
	String formpago = null;
	String txcajero = null;
	String cdfactur = null;
	String valortot = null;
 
    public UpdRecibosBD(){
    }
    
    public UpdRecibosBD(ObjectIO bdata) throws Exception {
    	
    	idclient = bdata.getStringValue("idclient"); 
    	idemisor = bdata.getStringValue("idemisor");
    	tpclient = bdata.getStringValue("tpclient");
    	cantidad = bdata.getStringValue("cantidad");
    	concepto = bdata.getStringValue("concepto");
    	formpago = bdata.getStringValue("formpago");
    	txcajero = bdata.getStringValue("txcajero");
    	cdfactur = bdata.getStringValue("cdfactur");
    	valortot = bdata.getStringValue("valortot");
        
    }       
    
    public String getInsertStatement(){
        
        StringBuffer sql = new StringBuffer();
        
        sql.append(" INSERT INTO recibopa (idclient, idemisor, tpclient, cantidad,concepto,formpago, txcajero,");
        sql.append(" cdfactur, valortot)");
        sql.append(" VALUES ");
        sql.append(" ('"+ idclient +"','"+ idemisor +"','"+ tpclient +"','"+ cantidad +"','"+ concepto +"','"+ formpago +"',");
        sql.append(" '"+ txcajero +"','"+ cdfactur +"','"+ valortot +"')");
        
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    public String getDeleteStatement(){
  
        return null;
    }

	@Override
	public String getUpdateStatement() {
		
		StringBuffer sql = new StringBuffer();
	        
	    sql.append(" UPDATE tradenvi ");
	   // sql.append(" SET codeenvi = '"+ codeenvi +"'");
	    
	   /* if ((fhcotiza != null) && (!fhcotiza.equals(""))){
	    	sql.append(", fhcotiza = '"+ fhcotiza +"'");
	    } 
	    
*/
	  
	        
	    //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
	       
	    System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
	        
	    return sql.toString();
		
	}



	@Override
	public String getSelectStatment() {
		
		StringBuffer sql = new StringBuffer();
        
	    sql.append(" SELECT re.idrecibo,cl.rzsocial, re.cantidad,re.concepto,re.formpago, re.txcajero,re.cdfactur, re.valortot");
	    sql.append(" FROM recibopa re,jlclierc cl ");
	    sql.append(" WHERE re.idclient = cl.idclient ");
	    sql.append(" AND re.tpclient = cl.tpclient ");
	    
	    if (idemisor != null && !idemisor.equals("")){
        	sql.append(" AND re.idemisor = '"+idemisor+"'");
        }
	    if (tpclient != null && !tpclient.equals("")){
        	sql.append(" AND re.tpclient = '"+tpclient+"'");
        } 
	    return sql.toString();
	}
   
	
	public Collection defineSelect() {
		
		Vector<String> select = new Vector<String>();
		select.add("idrecibo");	
		select.add("rzsocial");	
		select.add("cantidad");	
		select.add("concepto");	
		select.add("formpago");	
		select.add("txcajero");	
		select.add("cdfactur");	
		select.add("valortot");	
        return select; 
	}
    
}
