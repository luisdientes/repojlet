package personal.bd;

import java.util.Collection;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class AsignaProyDefaultBD extends UpdateQueryBD {

	String idproyec = null;
	String idtrabaj = null;

    
    public AsignaProyDefaultBD(){
    }
    
    public AsignaProyDefaultBD(ObjectIO bdata) throws Exception {
    	
    	idproyec = bdata.getStringValue("idproyec");
    	idtrabaj = bdata.getStringValue("cduserid");
    
    }       
    
    public String getInsertStatement(){
        
        StringBuffer sql = new StringBuffer();
        
        sql.append(" INSERT INTO gstnprtra ");
    	sql.append("(idtrabaj, idproyec)");
    	sql.append(" SELECT "+idtrabaj+",idproyec FROM gstnproy WHERE mcestado='E' ");
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
