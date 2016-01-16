package factura.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class MaxFacturaBD extends SelectQueryBD {

    String idemisor = null;
        
    public MaxFacturaBD(){
    }
    
    public MaxFacturaBD(ObjectIO bdata) throws Exception {
    	
        idemisor = bdata.getStringValue("idemisor");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT MAX(idtmpfra) as idfactur ");
        sql.append(" FROM tmpfactu ");
        
        if ((idemisor != null) && (!idemisor.equals(""))){
        	sql.append(" WHERE idemisor = "+ idemisor);
        }
        
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
		select.add("idfactur");
		
        return select;        
    }
    
    
}
