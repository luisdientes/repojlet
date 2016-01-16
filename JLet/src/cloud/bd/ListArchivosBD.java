package cloud.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class ListArchivosBD extends UpdateQueryBD {

	String idemisor = "";
	String tpclient = "";
	String filepath = "";
	String tipofich = "";
	String txnombre = "";
	String idinodox = "";
    
    public ListArchivosBD(){
    	
    }
    
    public ListArchivosBD(ObjectIO bdata) throws Exception {
    	
    	idemisor = bdata.getStringValue("idemisor");
    	tpclient = bdata.getStringValue("tpclient");
    	filepath = bdata.getStringValue("filepath");
    	tipofich = bdata.getStringValue("tipofich");
    	txnombre = bdata.getStringValue("txnombre");
    	idinodox = bdata.getStringValue("idinodox");
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
		
		sql.append(" SELECT idinodox, filepath, txnombre, permgrup, propieta,tipofich,idtamano,lastvers ");
		sql.append(" FROM cloufich ");
		sql.append(" WHERE idemisor = "+ idemisor);
		
		if(filepath != null && !filepath.equals("")){
			sql.append(" AND filepath = '"+ filepath+"'");	
		}
		
		if(tipofich != null && !tipofich.equals("")){
			sql.append(" AND tipofich = '"+ tipofich+"'");	
		}
		
		if(txnombre != null && !txnombre.equals("")){
			sql.append(" AND txnombre = '"+ txnombre+"'");	
		}
		if(idinodox != null && !idinodox.equals("")){
			sql.append(" AND idinodox = '"+ idinodox+"'");	
		}
		
		sql.append(" AND mcactivo = 'S'");

		sql.append(" ORDER BY tipofich,txnombre ASC ");
		
		System.out.println(this.getClass().getName()+" [SQL] "+ sql);
		
	 return sql.toString(); 
   }
	
	@Override
	public Collection<String> defineSelect() {
	
    	Vector<String> select = new Vector<String>();
    	
    	select.add("idinodox");
    	select.add("filepath");
    	select.add("txnombre");
    	select.add("permgrup");
    	select.add("propieta");
    	select.add("tipofich");
    	select.add("idtamano");
    	select.add("lastvers");
		
        return select;        
        
    }
}	
