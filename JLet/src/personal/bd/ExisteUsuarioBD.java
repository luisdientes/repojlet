package personal.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ExisteUsuarioBD extends SelectQueryBD {

    String username = null;
    String password = null;
    
    public ExisteUsuarioBD(){
    
    }
    
    public ExisteUsuarioBD(ObjectIO bdata) throws Exception {
    	
        username = bdata.getStringValue("username");
        password = bdata.getStringValue("password");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT idusuari, username, password ");
        sql.append(" FROM jlusuari ");
        
        if ((username != null)  && (!username.equals(""))){
        	sql.append(" WHERE username = '"+ username +"'");
        } else {
        	sql.append(" WHERE 1 = 0 ");
        }
        
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    public Collection<String> defineSelect() {

    	Vector<String> select=new Vector<String>();
		select.add("idusuari");
		select.add("username");
		select.add("password");
        return select;        
    }
    
    
}
