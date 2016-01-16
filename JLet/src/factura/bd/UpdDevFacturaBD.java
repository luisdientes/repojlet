package factura.bd;

import java.util.Collection;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdDevFacturaBD extends UpdateQueryBD {

	String idfactur = null;
	String cdestado = null;

	
	
    
    public UpdDevFacturaBD(){
    }
    
    public UpdDevFacturaBD(ObjectIO bdata) throws Exception {
    	
    	idfactur = bdata.getStringValue("idfactur");
    	cdestado = bdata.getStringValue("cdestado");
    
    }       
    
    public String getInsertStatement(){

        return null;
    }
    
    public String getDeleteStatement(){
        
       return null;
    }

	@Override
	public String getUpdateStatement() {
		  StringBuffer sql = new StringBuffer();

	        sql.append(" UPDATE jlfactur SET ");
	        sql.append("  cdestado='"+ cdestado +"' ");
	      
	        sql.append(" WHERE idfactur = '"+ idfactur +"'");
	       
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
