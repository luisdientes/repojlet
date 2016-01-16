package personal.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class MaxTrabajBD extends SelectQueryBD {

    String idtrabaj = null;
        
    public MaxTrabajBD(){
    }
    
    public MaxTrabajBD(ObjectIO bdata) throws Exception {
    	
    	//idtrabaj = bdata.getStringValue("idemisor");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT MAX(idacceso) as maxidtra ");
        sql.append(" FROM gstntrab ");
        
       // sql.append(" WHERE idemisor = "+ idemisor);
        
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
		select.add("maxidtra");
		
        return select;        
    }
    
    
}
