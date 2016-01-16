package stock.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class MaxAltaTmpStockBD extends SelectQueryBD {
	
	String idemisor = null;
	String mcestado = null;
    String codprodu = null;
   
        
    public MaxAltaTmpStockBD(){
    	
    	
    }
    
    public MaxAltaTmpStockBD(ObjectIO bdata) throws Exception {
    	
    	
        idemisor = bdata.getStringValue("idemisor");
        mcestado = bdata.getStringValue("mcestado");
        codprodu = bdata.getStringValue("codprodu");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT idgralta ");
        sql.append(" FROM tmpstock ");
        sql.append(" WHERE idemisor ="+idemisor);
        sql.append(" AND mcestado ='"+mcestado+"'");
        sql.append(" AND codprodu ='"+codprodu+"'");
        sql.append(" ORDER BY idgralta DESC LIMIT 0,1 ");
     
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
		select.add("idgralta");	
        return select;        
    }
    
    
}
