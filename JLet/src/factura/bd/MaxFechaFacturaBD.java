package factura.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class MaxFechaFacturaBD extends SelectQueryBD {

    String idemisor = null;
        
    public MaxFechaFacturaBD(){
    }
    
    public MaxFechaFacturaBD(ObjectIO bdata) throws Exception {
    	
        idemisor = bdata.getStringValue("idemisor");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();
        sql.append("  SELECT tipofact,Date_format(MAX(fhfactur),'%d/%m/%Y') as fechamax ");
        sql.append(" FROM jlfactur ");
        
        if ((idemisor != null) && (!idemisor.equals(""))){
        	sql.append(" WHERE idemisor = "+ idemisor);
        }
        
        sql.append(" GROUP BY tipofact ");
        sql.append(" ORDER BY tipofact ");
       
        
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
    	select.add("tipofact");
		select.add("fechafac");
		
        return select;        
    }
    
    
}
