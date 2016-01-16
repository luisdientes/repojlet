package gestion.administracion.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListPermisosBD extends SelectQueryBD {

    String cdrolxxx = null;
    String tipoperm = null;
    String nivelper = null;
    
    public ListPermisosBD(){
    
    }
    
    public ListPermisosBD(ObjectIO bdata) throws Exception {
    	
    	cdrolxxx = bdata.getStringValue("cdrolxxx");
    	tipoperm = bdata.getStringValue("tipoperm");
    	nivelper = bdata.getStringValue("nivelper");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT cdrolxxx, tipoperm, valorper, nivelper  ");
        sql.append(" FROM jlpermis ");
        
        if ((cdrolxxx != null) && (!cdrolxxx.equals(""))){
        	sql.append(" WHERE cdrolxxx = '"+ cdrolxxx +"' ");
        } else {
        	sql.append(" WHERE 1 = 0 ");
        }
        
        sql.append(" AND tipoperm = '"+ tipoperm +"' ");
        
        if ((nivelper != null) && (!nivelper.equals(""))){
        	sql.append(" AND permtipo = '"+ nivelper +"' ");
        }
        
        
        sql.append(" ORDER BY valorper ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    public Collection<String> defineSelect() {

    	Vector<String> select=new Vector<String>();
		select.add("cdrolxxx");
		select.add("tipoperm");
		select.add("valorper");
		select.add("nivelper");
		
        return select;        
    }
    
    
}
