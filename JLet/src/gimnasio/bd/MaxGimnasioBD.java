package gimnasio.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class MaxGimnasioBD extends SelectQueryBD {

   
        
    public MaxGimnasioBD(){
    }
    
    public MaxGimnasioBD(ObjectIO bdata) throws Exception {
    	
  
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT idclient FROM rf4uclie");
        sql.append(" ORDER BY idclient DESC LIMIT 0,1 ");
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
		select.add("idclient");		
        return select;        
    }
    
    
}
