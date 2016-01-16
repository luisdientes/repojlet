package comercio.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListComparaEnvioBD extends SelectQueryBD {

    String codeenvi = null;
        
    public ListComparaEnvioBD(){
    }
    
    public ListComparaEnvioBD(ObjectIO bdata) throws Exception {
    	
    	codeenvi = bdata.getStringValue("codeenvi");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT idproenv,codeenvi, txmarcax, txmodelo,idproduc,imeicode ");
        sql.append(" FROM tradtmen ");
        sql.append(" WHERE mcactivo = 'S' ");
        if ((codeenvi != null) && (!codeenvi.equals(""))){
        	sql.append(" AND codeenvi = '"+ codeenvi +"'");
        } else {
        	sql.append(" AND codeenvi IS NULL ");
        }
        sql.append(" ORDER BY idproenv ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
    	select.add("idproenv");
    	select.add("codeenvi");
    	select.add("txmarcax");
    	select.add("txmodelo");
    	select.add("idproduc");
    	select.add("imeicode");
        return select;        
    }
    
    
}
