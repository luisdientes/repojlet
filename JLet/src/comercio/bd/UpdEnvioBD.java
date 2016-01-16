package comercio.bd;

import java.util.Collection;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdEnvioBD extends UpdateQueryBD {

	String codeenvi = null;
	String fhcreaci = null;
	String fileenvi = null;
	String fhcotiza = null;
	String filecoti = null;
	String fhfactur = null;
	String filefact = null;
	String idemisor = null;
  
  	 
    public UpdEnvioBD(){
    }
    
    public UpdEnvioBD(ObjectIO bdata) throws Exception {
    	    	
    	codeenvi = bdata.getStringValue("codeenvi");
    	fhcreaci = bdata.getStringValue("fhcreaci");
    	fileenvi = bdata.getStringValue("fileenvi");
    	fhcotiza = bdata.getStringValue("fhcotiza");
    	filecoti = bdata.getStringValue("filecoti");
    	fhfactur = bdata.getStringValue("fhfactur");
    	filefact = bdata.getStringValue("filefact");
    	idemisor = bdata.getStringValue("idemisor");

        
    }       
    
    public String getInsertStatement(){
        
        StringBuffer sql = new StringBuffer();
        
        sql.append(" INSERT INTO tradenvi (codeenvi, fhcreaci, idemisor, fileenvi)");
        sql.append(" VALUES ");
        sql.append(" ('"+ codeenvi +"','"+ fhcreaci +"','"+ idemisor +"','"+ fileenvi +"')");
        
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    public String getDeleteStatement(){
  
        return null;
    }

	@Override
	public String getUpdateStatement() {
		
		StringBuffer sql = new StringBuffer();
	        
	    sql.append(" UPDATE tradenvi ");
	    sql.append(" SET codeenvi = '"+ codeenvi +"'");
	    
	    if ((fhcotiza != null) && (!fhcotiza.equals(""))){
	    	sql.append(", fhcotiza = '"+ fhcotiza +"'");
	    } 
	    
	    if ((filecoti != null) && (!filecoti.equals(""))){
	    	sql.append(", filecoti = '"+ filecoti +"'");
	    } 
	    
	    if ((fhfactur != null) && (!fhfactur.equals(""))){
	    	sql.append(", fhfactur = '"+ fhfactur +"'");
	    } 
	    
	    if ((filefact != null) && (!filefact.equals(""))){
	    	sql.append(", filefact = '"+ filefact +"'");
	    } 
	    
	    sql.append(" WHERE codeenvi = '"+ codeenvi +"'");
	        
	    //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
	       
	    System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
	        
	    return sql.toString();
		
	}

	@Override
	public Collection defineSelect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSelectStatment() {
		// TODO Auto-generated method stub
		return null;
	}
    
    
}
