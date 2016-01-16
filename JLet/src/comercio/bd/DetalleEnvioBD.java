package comercio.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class DetalleEnvioBD extends UpdateQueryBD {
	
	String idtradet = "";
  	String idenviox = "";
	String idempres = "";
	String medenvio = "";
	String txidenti = "";
	String perconta = "";
	String tfnocont = "";
	String txmailxx = "";
	String precioen = "";
	String impuesto = "";
	String txdivisa = "";
	String pagenvio = "";
	String totalenv = "";
	String fhrecogi = "";
	String horareco = "";
	String fhentreg = "";
	String horaentr = "";
	String cdestado = "";
        
    public DetalleEnvioBD(){
    }
    
    public DetalleEnvioBD(ObjectIO bdata) throws Exception {
    	
    	idtradet = bdata.getStringValue("idtradet"); 
    	idenviox = bdata.getStringValue("idenviox");
    	idempres = bdata.getStringValue("idempres");
    	medenvio = bdata.getStringValue("medenvio");
    	txidenti = bdata.getStringValue("txidenti");
    	perconta = bdata.getStringValue("perconta");
    	tfnocont = bdata.getStringValue("tfnocont");
    	txmailxx = bdata.getStringValue("txmailxx");
    	precioen = bdata.getStringValue("precioen");
    	impuesto = bdata.getStringValue("impuesto");
    	txdivisa = bdata.getStringValue("txdivisa");
    	pagenvio = bdata.getStringValue("pagenvio");
    	totalenv = bdata.getStringValue("totalenv");
    	fhrecogi = bdata.getStringValue("fhrecogi");
    	horareco = bdata.getStringValue("horareco");
    	fhentreg = bdata.getStringValue("fhentreg");
    	horaentr = bdata.getStringValue("horaentr");
    	cdestado = bdata.getStringValue("cdestado");

        
    }
    
    public String getInsertStatement(){
  	  
    	StringBuffer sql = new StringBuffer();
    	    sql.append(" INSERT INTO tradeten (idenviox, idempres, txidenti, perconta,tfnocont,txmailxx, ");
    	    sql.append(" precioen, impuesto, txdivisa, pagenvio,medenvio, totalenv, fhrecogi, horareco, fhentreg, horaentr, cdestado)");
    	    sql.append(" VALUES ");
    	    sql.append(" ('"+idenviox+"','"+idempres+"','"+txidenti+"','"+perconta+"','"+tfnocont+"','"+txmailxx+"',");  	
    	    sql.append(" '"+precioen+"','"+impuesto+"','"+txdivisa+"','"+pagenvio+"','"+medenvio+"','"+totalenv+"','"+fhrecogi+"','"+horareco+"',");  	
    	    sql.append(" '"+fhentreg+"','"+horaentr+"','"+cdestado+"')");

        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
	public String getSelectStatment() {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT idtradet, idenviox, idempres, medenvio, txidenti, perconta, tfnocont, txmailxx, precioen, impuesto,");
		sql.append(" txdivisa, pagenvio, totalenv, DATE_FORMAT(fhrecogi, '%d/%m/%Y'), horareco, DATE_FORMAT(fhentreg, '%d/%m/%Y'), horaentr, cdestado");
		sql.append(" FROM tradeten ");
		sql.append(" WHERE 1=1 ");
		
		if ((idenviox != null) && (!idenviox.equals(""))){
	        	sql.append(" AND idenviox='"+ idenviox +"'");
	    }
		
		System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
	        
	    return sql.toString();
	}
    
    public String getDeleteStatement(){
  
    
	    return null;
    }

	@Override
	public String getUpdateStatement() {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE tradeten SET");
		sql.append(" idempres='"+idempres+"',");
		sql.append(" medenvio='"+medenvio+"',");
		sql.append(" txidenti='"+txidenti+"',");
		sql.append(" perconta='"+perconta+"',");
		sql.append(" tfnocont='"+tfnocont+"',");
		sql.append(" txmailxx='"+txmailxx+"',");
		sql.append(" precioen='"+precioen+"',");
		sql.append(" txdivisa='"+txdivisa+"',");
		sql.append(" impuesto='"+impuesto+"',");
		sql.append(" pagenvio='"+pagenvio+"',");
		sql.append(" fhrecogi='"+fhrecogi+"',");
		sql.append(" horareco='"+horareco+"',");
		sql.append(" horareco='"+horareco+"',");
		sql.append(" fhentreg='"+fhentreg+"',");
		sql.append(" horaentr='"+horaentr+"',");
		sql.append(" cdestado='"+cdestado+"',");
		sql.append(" horareco='"+horareco+"'");
		sql.append(" WHERE idtradet='"+ idtradet +"'");
		
		System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
	        
	    return sql.toString();
	}

	@Override
	public Collection defineSelect() {
		
		
		Vector<String> select = new Vector<String>();
		select.add("idtradet");
		select.add("idenviox");
		select.add("idempres");
		select.add("medenvio");
		select.add("txidenti");
		select.add("perconta");
		select.add("tfnocont");
		select.add("txmailxx");
		select.add("precioen");
		select.add("impuesto");
		select.add("txdivisa");
		select.add("pagenvio");
		select.add("totalenv");
		select.add("fhrecogi");
		select.add("horareco");
		select.add("fhentreg");
		select.add("horaentr");
		select.add("cdestado");
		
        return select;   
	}


    
    
}
