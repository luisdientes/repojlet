package factura.bd;

import java.util.Collection;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdPagaFacturaBD extends UpdateQueryBD {

	String idfactur = null;
	String mcpagado = null;
	String totalpag = null;

	
	
    
    public UpdPagaFacturaBD(){
    }
    
    public UpdPagaFacturaBD(ObjectIO bdata) throws Exception {
    	
    	idfactur = bdata.getStringValue("idfactur");
    	mcpagado = bdata.getStringValue("mcpagado");
    	totalpag = bdata.getStringValue("totalpag");
    
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
	        sql.append("  mcpagado='"+ mcpagado +"' ");
	        
	        if(totalpag !=null && !totalpag.equals("")){
	        	sql.append(",  totalpag='"+ totalpag +"' ");
	        }
	      
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
