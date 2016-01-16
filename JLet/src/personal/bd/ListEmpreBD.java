package personal.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class ListEmpreBD extends UpdateQueryBD {

	String txrazons = null;		
	String txdirecc = null;	 
	String nifcifxx = null;	 
	String txwebxxx = null;	 
	String txmailxx = null;  
	String txcontac = null;  
	String idempres = null;
	String idemisor = null;  

    
   
    
    public ListEmpreBD(){
    
    }
    
    public ListEmpreBD(ObjectIO bdata) throws Exception {
    	
    	txrazons = bdata.getStringValue("txrazons");
    	txdirecc = bdata.getStringValue("txdirecc");
    	nifcifxx = bdata.getStringValue("nifcifxx");
    	txwebxxx = bdata.getStringValue("txwebxxx");
    	txmailxx = bdata.getStringValue("txmailxx");
    	txcontac = bdata.getStringValue("txcontac");
    	idempres = bdata.getStringValue("idempres");
    	idemisor = bdata.getStringValue("idemisor");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT idempres,txrazons,txdirecc,nifcifxx,txwebxxx,Date_format(fhaltaxx,'%d/%m/%Y'),txmailxx,txcontac FROM gstnempr ");
        sql.append(" WHERE 1 = 1 ");
        
        if ((idempres != null) && (!idempres.equals("")) && (!idempres.equals("0"))){
        	sql.append(" AND idempres = "+ idempres);
        }
        if ((idemisor != null) && (!idemisor.equals("")) && (!idemisor.equals("0"))){
        	sql.append(" AND idemisor = "+ idemisor);
        }
        
        sql.append(" ORDER BY txrazons ");
        
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    


	@Override
	public String getInsertStatement() {
		
	    StringBuffer sql = new StringBuffer();

        sql.append(" INSERT INTO gstnempr (txrazons,txdirecc, nifcifxx, txwebxxx, fhaltaxx, txmailxx, txcontac,idemisor )");
        sql.append(" VALUES ");
        sql.append("('"+txrazons+"' ,");
    	sql.append(" '"+txdirecc+"' ,");
    	sql.append(" '"+nifcifxx+"' ,");
    	sql.append(" '"+txwebxxx+"' ,");
    	sql.append(" CURRENT_DATE ,");
    	sql.append(" '"+txmailxx+"' ,");
    	sql.append(" '"+txcontac+"', ");
    	sql.append(" '"+idemisor+"' )");

        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
		
		return sql.toString();
	}

	@Override
	public String getUpdateStatement() {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" UPDATE gstnempr SET ");
		sql.append(" txrazons= '"+txrazons+"' ,");
		sql.append(" txdirecc= '"+txdirecc+"' ,");
		sql.append(" nifcifxx= '"+nifcifxx+"' ,");
		sql.append(" txwebxxx= '"+txwebxxx+"' ,");
		sql.append(" txmailxx= '"+txmailxx+"' ,");
		sql.append(" txcontac= '"+txcontac+"' ");
		sql.append("WHERE idempres='"+idempres+"'");
    	
    	System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
 		
 		return sql.toString();
	}

	@Override
	public String getDeleteStatement() {
		// TODO Auto-generated method stub
		return null;
	}
	
    public Collection<String> defineSelect() {

    	Vector<String> select=new Vector<String>();
		select.add("idempres");
		select.add("txrazons");
		select.add("txdirecc");
		select.add("nifcifxx");
		select.add("txwebxxx");
		select.add("fhaltaxx");
		select.add("txmailxx");
		select.add("txcontac");
		
        return select;        
    }
    
    
}
