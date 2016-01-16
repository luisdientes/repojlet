package stock.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListInventarioBD extends SelectQueryBD {

    String idemisor = null;
    String nameinve = null;
    String tpproduc = null;
    String tipocheq = null;
    
    public ListInventarioBD(){
    }
    
    public ListInventarioBD(ObjectIO bdata) throws Exception {
    	
    	idemisor = bdata.getStringValue("idemisor");
    	nameinve = bdata.getStringValue("nameinve");
    	tpproduc = bdata.getStringValue("tpproduc");
    	tipocheq = bdata.getStringValue("tipocheq");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT idinvent, nameinve, idunicox, idemisor, tpclient, fhaltaxx, consolid,tpproduc  ");
        sql.append(" FROM tmpinven  ");
        sql.append(" WHERE nameinve = '"+ nameinve +"'");
        sql.append(" AND idemisor = '"+ idemisor +"'");
        
        if(tpproduc!=null){
        	if(!tpproduc.equals("TO")){
        		sql.append(" AND tpproduc = '"+ tpproduc +"'");
        	}
        }
        
        if(tipocheq!=null){
        	if(!tipocheq.equals("TO") && !tipocheq.equals("")){
        		sql.append(" AND tpproduc IN("+ tipocheq +")");
        	}
        }
        
        
    
        
        sql.append(" ORDER BY fhaltaxx desc ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
    	select.add("idinvent");
    	select.add("nameinve");
    	select.add("idunicox");
    	select.add("idemisor");
    	select.add("tpclient");
    	select.add("fhaltaxx");
    	select.add("consolid");
    	select.add("tpproduc");
    	

        return select;        
    }
    
    
}
