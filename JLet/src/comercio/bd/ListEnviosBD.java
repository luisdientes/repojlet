package comercio.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListEnviosBD extends SelectQueryBD {

    String codeenvi = null;
    String fhdesdex = null;
    String fhhastax = null;
    String idemisor = null;
        
    public ListEnviosBD(){
    }
    
    public ListEnviosBD(ObjectIO bdata) throws Exception {
    	
    	codeenvi = bdata.getStringValue("codeenvi");
    	idemisor = bdata.getStringValue("idemisor");
    	fhdesdex = bdata.getStringValue("fhdesdex");
    	fhhastax = bdata.getStringValue("fhhastax");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT idenviox, codeenvi, idemisor, DATE_FORMAT(fhcreaci,'%d/%m/%Y') as fecha, fileenvi, ");
        sql.append(" DATE_FORMAT(fhcotiza,'%d/%m/%Y'), filecoti, DATE_FORMAT(fhfactur,'%d/%m/%Y'), filefact, mcintern ");
        sql.append(" FROM tradenvi ");
        sql.append(" WHERE 1 = 1 ");
        if ((codeenvi != null) && (!codeenvi.equals(""))){
        	sql.append(" AND  codeenvi like '%"+ codeenvi +"%'");
        }
        if ((idemisor != null) && (!idemisor.equals(""))){
        	sql.append(" AND  idemisor = '"+ idemisor +"'");
        } 
        sql.append(" ORDER BY idenviox DESC "); 
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
		select.add("idenviox");
		select.add("codeenvi");
		select.add("idemisor");
		select.add("fhcreaci");
		select.add("fileenvi");
		select.add("fhcotiza");
		select.add("filecoti");
		select.add("fhfactur");
		select.add("filefact");
		select.add("mcintern");
		
        return select;        
    }
    
    
}
