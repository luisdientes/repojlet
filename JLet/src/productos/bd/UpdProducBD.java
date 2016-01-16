package productos.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdProducBD extends UpdateQueryBD {

	String idmarcax = null;
	String txmodelo = null;
	String txcatego = null;
	String impdefco = null;
	String impdefve = null;
	String imagefil = null;
	String idgrupox = null;
	
    
    public UpdProducBD(){
    }
    
    public UpdProducBD(ObjectIO bdata) throws Exception {
    	
    	idmarcax = bdata.getStringValue("idmarcax");
    	txmodelo = bdata.getStringValue("txmodelo");
    	txcatego = bdata.getStringValue("txcatego");
    	impdefco = bdata.getStringValue("impdefco");
    	impdefve = bdata.getStringValue("impdefve");
    	imagefil = bdata.getStringValue("imagefil");
    	idgrupox = bdata.getStringValue("idgrupox");
    	
    }       
    
    public String getInsertStatement(){
        
        StringBuffer sql = new StringBuffer();
        
        sql.append(" INSERT INTO izumgrph (idmarcax, idzwipit, idizumba, tacximei, txmodelo, txcatego, impdefco, impdefve,imagewpt,txmodelo_ord )");
        sql.append(" VALUES ");
        sql.append(" ("+idmarcax+",0,0,0,'"+txmodelo+"','"+txcatego+"',"+impdefco+","+impdefve+",'','"+txmodelo+"')");       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    public String getDeleteStatement(){
        
        StringBuffer sql = new StringBuffer();

        return sql.toString();
    }

	@Override
	public String getUpdateStatement() {
		  StringBuffer sql = new StringBuffer();
		  
		  sql.append(" UPDATE izumgrph SET ");
		  sql.append(" imagewpt ='"+imagefil+"'");
		  sql.append(" WHERE idgrupox ='"+idgrupox+"'");

	        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
	       
	        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
	        
	        return sql.toString();
	}

	@Override
	public Collection defineSelect() {
Vector<String> select = new Vector<String>();
		
    	select.add("idgrupox");
    	select.add("txmodelo");
		select.add("txcatego");
		select.add("impdefve");
		select.add("txmarcax");
		select.add("imagewpt");
		
        return select;        
	}

	@Override
	public String getSelectStatment() {
		
		StringBuffer sql = new StringBuffer();
		
		 sql.append(" SELECT ph.idgrupox, ph.txmodelo,txcatego, ph.impdefve, ma.txnombre AS txmarcax,ph.imagewpt FROM izumgrph ph, izummarc ma");
	     sql.append(" WHERE  ph.idmarcax = ma.idmarcax ");
	     sql.append(" ORDER BY ph.idgrupox DESC ");
	     sql.append(" LIMIT 0,1"); 
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        return sql.toString();
	}
    
    
}
