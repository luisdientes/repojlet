package cloud.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdateCloudBD extends UpdateQueryBD {

	String idemisor = "";
	String propieta = "";
	String permgrup = "";
	String filepath = "";
	String txnombre = "";
	String idtamani = "";
	String tipofich = "";
	String cdestado = "";
    
    public UpdateCloudBD(){
    	
    }
    
    public UpdateCloudBD(ObjectIO bdata) throws Exception {
    	
    	idemisor = bdata.getStringValue("idemisor");
    	propieta = bdata.getStringValue("propieta");
    	permgrup = bdata.getStringValue("permgrup");
    	filepath = bdata.getStringValue("filepath");
    	txnombre = bdata.getStringValue("txnombre");
    	idtamani = bdata.getStringValue("idtamano");
    	tipofich = bdata.getStringValue("tipofich");
    	cdestado = bdata.getStringValue("cdestado");
    	
    }       
    
    public String getInsertStatement(){
    	
    	StringBuffer sql = new StringBuffer();
    	sql.append(" INSERT INTO cloufich ");
    	sql.append(" ( idemisor, propieta, permgrup, filepath, txnombre, tipofich ,mcactivo, lastvers,fhaltaxx , idtamano )");
    	sql.append(" VALUES ");
    	sql.append(" ( '"+idemisor+"','"+propieta+"','"+permgrup+"','"+filepath+"','"+txnombre+"', '"+tipofich+"','S','0',CURRENT_DATE,");
    	
    	if(idtamani == null || idtamani.equals("")){
    		idtamani = "0";
    	}
    	sql.append("'"+idtamani+"')");
    
    return sql.toString(); 
    }
    
    public String getDeleteStatement(){
    	
    	StringBuffer sql = new StringBuffer();
    	sql.append(" UPDATE cloufich ");
    	sql.append(" SET  mcactivo='N'");
    	sql.append(" WHERE txnombre='"+txnombre+"'  ");
    	sql.append(" AND idemisor ='"+idemisor+"'  ");
    	sql.append(" AND tipofich ='"+tipofich+"'  ");
    	sql.append(" AND filepath ='"+filepath+"'  ");
  
    	  return sql.toString(); 
    }

	@Override
	public String getUpdateStatement() {
		
		StringBuffer sql = new StringBuffer();
    	sql.append(" UPDATE  cloufich ");
    	sql.append(" SET  idtamano='"+idtamani+"', ");
    	sql.append(" lastvers=lastvers +1 ");
    	
    	sql.append(" WHERE txnombre='"+txnombre+"'  ");
    	sql.append(" AND idemisor ='"+idemisor+"'  ");
    	sql.append(" AND tipofich ='"+tipofich+"'  ");
    	sql.append(" AND filepath ='"+filepath+"'  ");
    	
    	System.out.println("SQL --> vERSION -------->>>"+sql.toString());
    	
    	return sql.toString(); 
		
	}



	@Override
	public String getSelectStatment() {
	
	
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT filepath, txnombre, tipofich ");
		sql.append(" FROM cloufich ");
		sql.append(" WHERE idemisor = "+ idemisor);
		
		if(filepath != null && !filepath.equals("")){
			sql.append(" AND filepath = '"+ filepath+"'");	
		}
		
		sql.append(" ORDER BY txnombre DESC ");
		
		System.out.println(this.getClass().getName()+" [SQL] "+ sql);
		
	 return sql.toString(); 
   }
	
	@Override
	public Collection<String> defineSelect() {
	
    	Vector<String> select = new Vector<String>();
    	
    	select.add("filepath");
    	select.add("txnombre");
    	select.add("tipofich");
		
        return select;        
        
    }
}	
