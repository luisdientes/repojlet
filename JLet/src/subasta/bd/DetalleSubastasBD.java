package subasta.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class DetalleSubastasBD extends SelectQueryBD {

    String idcodsub = null;
    String mcactivo = null;
    
        
    public DetalleSubastasBD(){
    }
    
    public DetalleSubastasBD(ObjectIO bdata) throws Exception {
    	
    	idcodsub = bdata.getStringValue("idcodsub");
    	mcactivo = bdata.getStringValue("mcactivo");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        
        
        sql.append(" SELECT idcodsub, tpevento, iduserin, txusuari, txmailxx, telefono, isopaisx, cantidad ");
        sql.append(" , cddivisa, direnvio, txcoment, DATE_FORMAT(fhaltaxx,'%d/%m/%Y') AS fhaltaxx, pretrans,DATE_FORMAT(fhventax,'%d/%m/%Y') AS fhventax, nunidade ");
        sql.append(" FROM jlsubinf ");
        sql.append(" WHERE 1 = 1 ");
        
       
        
        if ((idcodsub != null) && (!idcodsub.equals(""))){
        	sql.append(" AND  idcodsub = "+ idcodsub);
        }
        
        sql.append(" ORDER BY tpevento");
        
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
		select.add("idcodsub");
		select.add("tpevento");
		select.add("iduserin");
		select.add("txusuari");
		select.add("txmailxx");
		select.add("telefono");
		select.add("isopaisx");
		select.add("cantidad");
		select.add("cddivisa");
		select.add("direnvio");
		select.add("txcoment");
		select.add("fhaltaxx");
		
		select.add("pretrans");
		select.add("fhventax");
		select.add("nunidade");
		
        return select;        
    }
    
    
}
