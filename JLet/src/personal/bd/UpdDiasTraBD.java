package personal.bd;

import java.util.Collection;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdDiasTraBD extends UpdateQueryBD {

	String fechasel = null;
	String horaselx = null;
	String proyecto = null;
	String cduserid = null;
    
    public UpdDiasTraBD(){
    }
    
    public UpdDiasTraBD(ObjectIO bdata) throws Exception {
    	
    	fechasel = bdata.getStringValue("fechasel");
    	horaselx = bdata.getStringValue("horaselx");
    	proyecto = bdata.getStringValue("proyecto");
    	cduserid = bdata.getStringValue("cduserid");        
    }       
    
    public String getInsertStatement(){
        
        StringBuffer sql = new StringBuffer();
        
        sql.append( " INSERT INTO gsthoras (fhfechax, horasinv, idproyec, idtrabaj)");
        sql.append(" VALUES");
        sql.append("('"+fechasel+"','"+horaselx+"','"+proyecto+"','"+cduserid+"')");
        
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
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
