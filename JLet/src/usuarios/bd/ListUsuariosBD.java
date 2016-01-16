package usuarios.bd;

import java.util.Collection;
import java.util.Vector;
import java.util.logging.Logger;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class ListUsuariosBD extends UpdateQueryBD {

	String idusuari = "";
	String cdrolxxx = "";
    
    public ListUsuariosBD(){
    	
    }
    
    public ListUsuariosBD(ObjectIO bdata) throws Exception {
    	
    	idusuari = bdata.getStringValue("idusuari");
    	cdrolxxx = bdata.getStringValue("cdrolxxx");
    	
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
		 	sql.append(" UPDATE jlusuari");
		 	sql.append(" SET cdrolxxx='"+cdrolxxx+"'");
		 	sql.append(" WHERE idusuari="+idusuari);
		 	
		 	System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
		    return sql.toString();		
	}



	@Override
	public String getSelectStatment() {
	
	
		  StringBuffer sql = new StringBuffer();

	        sql.append(" SELECT idusuari, username, password, perfiltp, cdrolxxx,  ");
	        sql.append(" txmailxx, telefono,DATE_FORMAT(fhaltaxx, '%d/%m/%Y') fhaltaxx,DATE_FORMAT(fhbajaxx, '%d/%m/%Y') fhbajaxx , mcactivo ");
	        sql.append(" FROM jlusuari ");
	        sql.append(" WHERE 1 = 1 ");
	        
	        if ((idusuari != null)  && (!idusuari.equals(""))){
	        	sql.append(" AND idusuari = '"+idusuari+"' ");
	        } 
	        
	        if ((cdrolxxx != null)  && (!cdrolxxx.equals(""))){
	        	sql.append(" AND cdrolxxx = '"+cdrolxxx+"' ");
	        } 
	       
	        Logger.getLogger("BBDD").fine("QUERY - "+ this.getClass().getName() +"-"+ sql);
	        
	        return sql.toString();
   }
	
	@Override
	public Collection<String> defineSelect() {
	
		Vector<String> select=new Vector<String>();
		
		select.add("idusuari");
		select.add("username");
		select.add("password");
		select.add("perfiltp");
		select.add("cdrolxxx");
		select.add("txmailxx");
		select.add("telefono");
		select.add("fhaltaxx");
		select.add("fhbajaxx");
		select.add("mcactivo");
		
        return select;        
        
    }
}	
