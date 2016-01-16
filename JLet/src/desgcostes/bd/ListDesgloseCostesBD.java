package desgcostes.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class ListDesgloseCostesBD extends UpdateQueryBD {
 
	String idemisor = null;
	String idunicox = null;
	String mostriva = null;
	String cdgrupox = null;
	String tipooper = null;
	
    public ListDesgloseCostesBD(){
    }
    
    public ListDesgloseCostesBD(ObjectIO bdata) throws Exception {
    	
    	idemisor = bdata.getStringValue("idemisor");
    	idunicox = bdata.getStringValue("idunicox");
    	mostriva = bdata.getStringValue("mostriva");
    	cdgrupox = bdata.getStringValue("cdgrupox");
    	tipooper = bdata.getStringValue("tipooper");
        
    }       
    
    public String getInsertStatement(){
        
       return null;
    }
    
    public String getDeleteStatement(){
  
        return null;
    }

	@Override
	public String getUpdateStatement() {
	
		return null;
		
	}



	@Override
	public String getSelectStatment() {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT cs.idunicox, cs.idemisor, cs.codedesg, cs.desvalue, cs.cddivisa, cd.txnombre, cd.cdgrupox, cd.txdescri ");
		sql.append(" FROM dglscost cs, dglscode cd");
		sql.append(" WHERE cs.codedesg = cd.codedesg ");		
		sql.append(" AND cs.idemisor = '"+ idemisor +"'");
		
		if ((idunicox != null) && (!idunicox.equals(""))){
			sql.append(" AND cs.idunicox = '"+ idunicox +"'");
		}
		
		if ((cdgrupox != null) && (!cdgrupox.equals(""))){
			sql.append(" AND cd.cdgrupox = '"+ cdgrupox +"'");
		}
		
		if ((tipooper != null) && (!tipooper.equals("") && (!tipooper.equals("T")))){
			sql.append(" AND cd.tipooper = '"+ tipooper +"'");
		}
		
		if ((mostriva != null) && (!mostriva.equals("")) && (mostriva.equals("N"))) {
			sql.append(" AND cd.codedesg NOT LIKE '%IVA%' ");
		}
		
		sql.append(" ORDER BY tipooper, REPLACE(cd.codedesg, 'IVA', ''), REPLACE(cd.codedesg, 'IVA', 'Z') ");
		
		System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
	    return sql.toString();
	}
	
	@Override
	public Collection defineSelect() {
	
		Vector<String> select = new Vector<String>();
		select.add("idunicox");	
		select.add("idemisor");		
		select.add("codedesg");		
		select.add("desvalue");	
		select.add("cddivisa");		
		select.add("txnombre");
		select.add("cdgrupox");	
		select.add("txdescri");	
		
        return select; 
	}
    
    
}

