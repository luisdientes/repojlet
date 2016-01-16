package gestion.administracion.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListUsuariosBD extends SelectQueryBD {

    String username = null;
    String password = null;
    
    public ListUsuariosBD(){
    
    }
    
    public ListUsuariosBD(ObjectIO bdata) throws Exception {
    	
        username = bdata.getStringValue("username");
        password = bdata.getStringValue("password");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT idusuari, username, password, perfiltp, cdrolxxx,  ");
        sql.append(" txmailxx, telefono, fhaltaxx, fhbajaxx, mcactivo ");
        sql.append(" FROM jlusuari ");
        
        if ((username != null) && (password != null) && (!username.equals("")) && (!password.equals(""))){
        	sql.append(" WHERE username = '"+ username +"' AND password = '"+ password +"'");
        } else {
        	sql.append(" WHERE 1 = 0 ");
        }
        
        sql.append(" AND mcactivo = 'S' ");
        
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
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
