package personal.bd;

import java.util.Collection;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class AsignaProyBD extends UpdateQueryBD {

	String idproyec = null;
	String idtrabaj = null;

    
    public AsignaProyBD(){
    }
    
    public AsignaProyBD(ObjectIO bdata) throws Exception {
    	
    	idproyec = bdata.getStringValue("idproyec");
    	idtrabaj = bdata.getStringValue("cduserid");
    
    }       
    
    public String getInsertStatement(){
        
        StringBuffer sql = new StringBuffer();
        
        if(idproyec.equals("")){
        	idproyec="0";
        }
        
        sql.append(" INSERT INTO gstnprtra ");
    	sql.append("(idtrabaj, idproyec)");
    	sql.append(" VALUES ");
    	sql.append("('"+idtrabaj+"' ,");
    	sql.append(" '"+idproyec+"' )");
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
