package facturacion.bd;

import java.util.Collection;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdCodigoFacturaBD extends UpdateQueryBD {

    String idemisor = null;
    String aniofact = null;
    String tipofact = null;
    String restacod = null;
    
    public UpdCodigoFacturaBD(){
    
    }
    
    public UpdCodigoFacturaBD(ObjectIO bdata) throws Exception {
    	
    	idemisor = bdata.getStringValue("idemisor");
    	aniofact = bdata.getStringValue("aniofact");
    	tipofact = bdata.getStringValue("tipofact");
    	restacod = bdata.getStringValue("restacod"); 
        
    }
    

	@Override
	public String getDeleteStatement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInsertStatement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUpdateStatement() {
		
		StringBuffer sql = new StringBuffer();

        sql.append(" UPDATE jlcodfra ");
        
        if(restacod != null && !restacod.equals("")){
        	sql.append(" SET cdfactur = cdfactur - 1 ");
        }else{
        	sql.append(" SET cdfactur = cdfactur + 1 ");
        }
        
        sql.append(" WHERE idemisor = "+ idemisor +" AND aniofact = "+ aniofact +" AND tipofact = "+ tipofact);
      
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
