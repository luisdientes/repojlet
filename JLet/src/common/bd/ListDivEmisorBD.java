package common.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class ListDivEmisorBD extends UpdateQueryBD {

	String idemisor = "";
    
    public ListDivEmisorBD(){
    	
    }
    
    public ListDivEmisorBD(ObjectIO bdata) throws Exception {
    	
    	idemisor = bdata.getStringValue("idemisor");
    	
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
		
		sql.append(" SELECT idemisor, txnombre, cddivisa, divsimbo, idpaisxx, colocorp ");
		sql.append(" FROM jlemisor ");
		sql.append(" WHERE 1=1" );
		
		if(idemisor !=null && !idemisor.equals("")){
			sql.append(" AND idemisor = "+ idemisor);
		}
		System.out.println(this.getClass().getName() +" QUERY "+ sql);
		
	 return sql.toString(); 
   }
	
	@Override
	public Collection<String> defineSelect() {
	
    	Vector<String> select = new Vector<String>();
    	
    	select.add("idemisor");
		select.add("txnombre");
    	select.add("cddivisa");
		select.add("divsimbo");
    	select.add("idpaisxx");
    	select.add("colocorp");

    	return select;        
        
    }
}	
