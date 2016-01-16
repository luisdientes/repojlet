package comercio.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListEmpreEnvioBD extends SelectQueryBD {

    String cdestado = null;
    String idemisor = null;
    String imeicode = null;
    
    public ListEmpreEnvioBD(){
    }
    
    public ListEmpreEnvioBD(ObjectIO bdata) throws Exception {
    	
    	idemisor = bdata.getStringValue("idemisor");    
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT idempres, txrazons, txdirecc, txpaisxx, telefono");
        sql.append(" FROM emprenvi ");
        sql.append(" WHERE idemisor = '"+ idemisor +"'");
        sql.append(" ORDER BY txrazons ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
    	select.add("idempres");
    	select.add("txrazons");
    	select.add("txdirecc");
    	select.add("txpaisxx");
    	select.add("telefono");

        return select;        
    }
    
    
}
