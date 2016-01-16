package personal.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdLoginBD extends UpdateQueryBD {

	String idtrabaj = null;
	String username = null;
	String password = null;
	String tfnomovi = null;
	String txmailxx = null;
	
    
    public UpdLoginBD(){
    }
    
    public UpdLoginBD(ObjectIO bdata) throws Exception {
    	
    	idtrabaj = bdata.getStringValue("idtrabaj");
    	username = bdata.getStringValue("username");
    	password = bdata.getStringValue("password");
    	tfnomovi = bdata.getStringValue("tfnomovi");
    	txmailxx = bdata.getStringValue("txmailxx"); 
 
    }       
    
    public String getInsertStatement(){
        
        StringBuffer sql = new StringBuffer();
        
        sql.append(" INSERT INTO jlusuari ");
    	sql.append("(username,password,perfiltp, cdrolxxx,txmailxx,telefono,fhaltaxx,mcactivo)");
    	sql.append(" VALUES ");
    	sql.append(" ('"+username+"' ,");
    	sql.append(" '"+password+"' ,");
    	sql.append(" 'T' ,");
    	sql.append(" 'EMPLEADO' ,");
    	sql.append(" '"+txmailxx+"' ,");
    	sql.append(" '"+tfnomovi+"' ,");
    	sql.append(" CURRENT_DATE ,");
    	sql.append(" 'S' )");
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
	
	 	sql.append(" UPDATE jlusuari SET ");
	 	
	 	 if ((username != null) && (!username.equals("")) && (!username.equals("0"))){
	 		 	sql.append(" username='"+username+"',");
	        }
	 	 if ((password != null) && (!password.equals("")) && (!password.equals("0"))){
	 		 	sql.append(" password='"+password+"' ");
	        }
      	sql.append(" WHERE idusuari='"+idtrabaj+"'");
      	
      	 System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
         
         return sql.toString();
	}

	@Override
	public Collection defineSelect() {
		Vector<String> select=new Vector<String>();
		select.add("idusuari");
        return select;
	
	}

	@Override
	public String getSelectStatment() {
		StringBuffer sql = new StringBuffer();

	    sql.append(" SELECT idusuari");
	    sql.append(" FROM jlusuari ");
	    sql.append(" ORDER BY idusuari DESC ");
	    sql.append(" LIMIT 0,1");
	    
	    System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
	}
    
    
}
