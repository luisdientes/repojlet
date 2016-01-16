package gimnasio.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdMarcasBD extends UpdateQueryBD {

	String txnombre = null;
	String nifcifxx = null;
	String txcatego = null;
	String txciudad = null;
	String txdirecc = null;
	String telefono = null;
	String txmailxx = null;
	String txrespon = null;
	String sexoxxxx = null;
	String txnomres = null;
	String tfnomovi = null;
	String mailresp = null;
	

  
  	 
    public UpdMarcasBD(){
    }
    
    public UpdMarcasBD(ObjectIO bdata) throws Exception {
    	    	
    /*	txnombre = bdata.getStringValue("txnombre");
    	nifcifxx = bdata.getStringValue("nifcifxx");
    	txcatego = bdata.getStringValue("txcatego");
    	txciudad = bdata.getStringValue("txciudad");
    	txdirecc = bdata.getStringValue("txdirecc");
    	telefono = bdata.getStringValue("telefono");
    	txmailxx = bdata.getStringValue("txmailxx");
    	txrespon = bdata.getStringValue("txrespon");
    	sexoxxxx = bdata.getStringValue("sexoxxxx");
    	txnomres = bdata.getStringValue("txnomres");
    	tfnomovi = bdata.getStringValue("tfnomovi");
    	mailresp = bdata.getStringValue("mailresp");*/

        
    }       
    
    public String getInsertStatement(){
        
    	return null;
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
		
		sql.append(" SELECT idmarcax, txmarcax FROM rf4umarc");
		sql.append(" ORDER BY txmarcax ASC");
	    
		System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
	    return sql.toString();
	}
	
	@Override
	public Collection defineSelect() {
	
		Vector<String> select = new Vector<String>();
		select.add("idmarcax");		
		select.add("txmarcax");		
        return select; 
	}
    
    
}
