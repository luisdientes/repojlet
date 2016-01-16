package factura.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListAnioFactBD extends SelectQueryBD {

    String idemisor = null;
    String isalbara = null;
  
        
    public ListAnioFactBD(){
    }
    
    public ListAnioFactBD(ObjectIO bdata) throws Exception {

    	idemisor = bdata.getStringValue("idemisor");   
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT DISTINCT aniofact FROM jlfactur where 1=1   ");
        sql.append(" AND idemisor ='"+idemisor+"' ");
        
        if ((isalbara != null) && (isalbara.equals("S"))){
        	sql.append(" AND tipofact = 0 ");
        } else {
        	sql.append(" AND tipofact <> 0");
        }
       
     

    
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	
    	
    	Vector<String> select = new Vector<String>();
		select.add("aniofact");
		
        return select;        
    }
    
    
}
