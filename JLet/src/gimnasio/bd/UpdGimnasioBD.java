package gimnasio.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdGimnasioBD extends UpdateQueryBD {

	String idclient = null;
	String txnombre = null;
	String nifcifxx = null;
	String txcatego = null;
	String txciudad = null;
	String txdirecc = null;
	String cdpostal = null;
	String telefono = null;
	String txmailxx = null;
	String txrespon = null;
	String sexoxxxx = null;
	String txnomres = null;
	String tfnomovi = null;
	String mailresp = null;
	String longitud = null;
	String latitudx = null;
	

  
  	 
    public UpdGimnasioBD(){
    }
    
    public UpdGimnasioBD(ObjectIO bdata) throws Exception {
    	
    	idclient = bdata.getStringValue("idclient"); 
    	txnombre = bdata.getStringValue("txnombre");
    	nifcifxx = bdata.getStringValue("nifcifxx");
    	txcatego = bdata.getStringValue("txcatego");
    	txciudad = bdata.getStringValue("txciudad");
    	txdirecc = bdata.getStringValue("txdirecc");
    	cdpostal = bdata.getStringValue("cdpostal");
    	telefono = bdata.getStringValue("telefono");
    	txmailxx = bdata.getStringValue("txmailxx");
    	txrespon = bdata.getStringValue("txrespon");
    	sexoxxxx = bdata.getStringValue("sexoxxxx");
    	txnomres = bdata.getStringValue("txnomres");
    	tfnomovi = bdata.getStringValue("tfnomovi");
    	mailresp = bdata.getStringValue("mailresp");
    	longitud = bdata.getStringValue("longitud");
    	latitudx = bdata.getStringValue("latitudx");

        
    }       
    
    public String getInsertStatement(){
        
        StringBuffer sql = new StringBuffer();
        
        sql.append(" INSERT INTO rf4uclie (txnombre, nifcifxx, txcatego, txciudad,txdirecc,cdpostal, telefono, txmailxx,");
        sql.append(" txrespon, sexoxxxx,txnomres,tfnomovi,mailresp,longitud,latitudx)");
        sql.append(" VALUES ");
        sql.append(" ('"+ txnombre +"','"+ nifcifxx +"','"+ txcatego +"','"+ txciudad +"','"+ txdirecc +"','"+ cdpostal +"','"+ telefono +"',");
        sql.append(" '"+ txmailxx +"','"+ txrespon +"','"+ sexoxxxx +"','"+ txnomres +"','"+ tfnomovi +"','"+ mailresp +"','"+ longitud +"','"+ latitudx +"')");
        
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    public String getDeleteStatement(){
  
        return null;
    }

	@Override
	public String getUpdateStatement() {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" UPDATE rf4uclie SET");
		sql.append(" txnombre = '"+txnombre+"',"); 
		sql.append(" nifcifxx = '"+nifcifxx+"',");
		sql.append(" txcatego = '"+txcatego+"',");
		sql.append(" txciudad = '"+txciudad+"',");
		sql.append(" txdirecc = '"+txdirecc+"',");
		sql.append(" cdpostal = '"+cdpostal+"',");
		sql.append(" telefono = '"+telefono+"',");
		sql.append(" txmailxx = '"+txmailxx+"',");
		sql.append(" txrespon = '"+txrespon+"',");
		sql.append(" sexoxxxx = '"+sexoxxxx+"',");
		sql.append(" txnomres = '"+txnomres+"',");
		sql.append(" tfnomovi = '"+tfnomovi+"',");
		sql.append(" longitud = '"+longitud+"',");
		sql.append(" latitudx = '"+latitudx+"',");
		sql.append(" mailresp = '"+mailresp+"' ");
		sql.append(" WHERE ");
		sql.append(" idclient = '"+idclient+"'");
		
		 System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
	        
	    return sql.toString();
		
	}



	@Override
	public String getSelectStatment() {
		
		StringBuffer sql = new StringBuffer();
        
	    sql.append(" SELECT idclient, txnombre, nifcifxx, txcatego, txciudad, txdirecc,cdpostal, telefono,txmailxx,txrespon,");
	    sql.append(" sexoxxxx, txnomres, tfnomovi, mailresp,longitud,latitudx  ");
	    sql.append(" FROM rf4uclie ");
	    sql.append(" WHERE 1=1 ");
	    
	    if (idclient != null && !idclient.equals("")){
        	sql.append(" AND idclient = '"+idclient+"'");
        }
	    return sql.toString();
	}
   
	
	public Collection defineSelect() {
		
		Vector<String> select = new Vector<String>();
		select.add("idclient");	
		select.add("txnombre");	
		select.add("nifcifxx");	
		select.add("txcatego");	
		select.add("txciudad");	
		select.add("txdirecc");	
		select.add("cdpostal");	
		select.add("telefono");	
		select.add("txmailxx");	
		select.add("txrespon");	
		select.add("sexoxxxx");	
		select.add("txnomres");	
		select.add("tfnomovi");	
		select.add("mailresp");	
		select.add("longitud");	
		select.add("latitudx");	
        return select; 
	}
    
}
