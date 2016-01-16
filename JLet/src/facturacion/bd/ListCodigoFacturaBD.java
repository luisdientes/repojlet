package facturacion.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListCodigoFacturaBD extends SelectQueryBD {

    String idemisor = null;
    String aniofact = null;
    String tipofact = null;
    
    public ListCodigoFacturaBD(){
    
    }
    
    public ListCodigoFacturaBD(ObjectIO bdata) throws Exception {
    	
    	idemisor = bdata.getStringValue("idemisor");
    	aniofact = bdata.getStringValue("aniofact");
    	tipofact = bdata.getStringValue("tipofact");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT cdfactur ");
        sql.append(" FROM jlcodfra ");
        sql.append(" WHERE idemisor = "+ idemisor +" AND aniofact = "+ aniofact);
        sql.append(" AND tipofact = "+ tipofact);
      
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    public Collection<String> defineSelect() {

    	Vector<String> select=new Vector<String>();
		select.add("cdfactur");
		
        return select;        
    }
    
    
}
