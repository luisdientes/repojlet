package factura.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListAreasneBD extends SelectQueryBD {

    String idemisor = null;
        
    public ListAreasneBD(){
    }
    
    public ListAreasneBD(ObjectIO bdata) throws Exception {
    	
    	idemisor = bdata.getStringValue("idemisor");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT areanego, txnombre ");
        sql.append(" FROM jlareane ");
        sql.append(" WHERE mcactivo = 'S'");
        if(idemisor !=null){
        	sql.append(" AND idemisor= "+idemisor);
        }
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
		select.add("areanego");
		select.add("txnombre");
        return select;        
    }
    
    
}
