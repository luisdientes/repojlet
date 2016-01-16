package upgrade.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class InsProdPiezaBD extends UpdateQueryBD {

	String imeiprod = null;
	String imeipiez = null;
	String cdestado = null;
    
    public InsProdPiezaBD(){
    }
    
    public InsProdPiezaBD(ObjectIO bdata) throws Exception {

    	imeiprod = bdata.getStringValue("imeiprod");
    	imeipiez = bdata.getStringValue("imeipiez");
    	cdestado = bdata.getStringValue("cdestado");
  
    }       
    
    public String getInsertStatement(){
        
        StringBuffer sql = new StringBuffer();
     
        sql.append(" INSERT INTO izumprpi (imeiprod, imeipiez )");
        sql.append(" VALUES ");
        sql.append(" ('"+imeiprod+"','"+imeipiez+"') ");
        
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    public String getDeleteStatement(){
  
    	return null;
    }

	@Override
	public String getUpdateStatement() {
		
		return null;
		
	}

	@Override
	public Collection defineSelect() {
		
		Vector<String> select = new Vector<String>();
    	select.add("txmarcax");
    	select.add("txmodelo");
    	select.add("pricevnt");    
        return select;        
	}

	@Override
	public String getSelectStatment() {
		
		StringBuffer sql = new StringBuffer();
	
		sql.append(" SELECT txmarcax,txmodelo, pricevnt ");
	    sql.append(" FROM tradstoc st, izumprpi pr ");
	    sql.append(" where pr.imeiprod ='"+imeiprod+"'");
	    sql.append(" AND st.imeicode = pr.imeipiez");
	    
	    if(cdestado !=null){
	    	sql.append(" AND st.cdestado = '"+cdestado+"'");
	    	
	    }
	    
	       
	    return sql.toString();	  
	}
    
    
}
