package stock.bd;

import java.util.Collection;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdEstadoProBD extends UpdateQueryBD {

	String idemisor = null;
	String idunicox = null;
	String iduserxx = null;
	String newstate = null;
	String idclient = null;
	String tpclient = null;
	
    public UpdEstadoProBD(){
    }
    
    public UpdEstadoProBD(ObjectIO bdata) throws Exception {

    	idemisor = bdata.getStringValue("idemisor");
    	idunicox = bdata.getStringValue("idunicox");
    	iduserxx = bdata.getStringValue("iduserxx");
    	newstate = bdata.getStringValue("newstate");
    	idclient = bdata.getStringValue("idclient");
    	tpclient = bdata.getStringValue("tpclient");
    	
        
    }       
    
    public String getInsertStatement(){
        
        StringBuffer sql = new StringBuffer();
        
        if(tpclient == null || tpclient.equals("")){
        	tpclient = "0";
        }
        
        
        sql.append(" INSERT INTO prodstat (idunicox, idemisor, iduserxx, idclient, tpclient, newstate)");
        sql.append(" VALUES ");
        sql.append(" ('"+ idunicox +"','"+idemisor+"','"+iduserxx+"','"+idclient+"','"+tpclient+"','"+newstate+"')");
       
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSelectStatment() {
		// TODO Auto-generated method stub
		return null;
	}
    
    
}
