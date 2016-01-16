package personal.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class ListProyectEmpleBD extends UpdateQueryBD {

    String cduserid = null;
    String cdrolxxx = null;
    String username = null;
    String password = null;
    
    public ListProyectEmpleBD(){
    
    }
    
    public ListProyectEmpleBD(ObjectIO bdata) throws Exception {
    	
        cduserid = bdata.getStringValue("cduserid");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT txnombre,tr.idproyec FROM gstnproy pro, gstnprtra tr ");
        sql.append(" WHERE pro.idproyec = tr.idproyec ");
        
        if ((cduserid != null) && (!cduserid.equals("")) && (!cduserid.equals("0"))){
        	sql.append(" AND idtrabaj = "+ cduserid);
        }
        
        sql.append(" ORDER BY txnombre ");
        
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    public Collection<String> defineSelect() {

    	Vector<String> select=new Vector<String>();
		select.add("txnombre");
		select.add("idproyec");
        return select;        
    }

	@Override
	public String getInsertStatement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUpdateStatement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDeleteStatement() {
		
		StringBuffer sql = new StringBuffer();
    	
    	sql.append(" DELETE FROM  gstnprtra ");
    	sql.append(" WHERE idtrabaj ='"+cduserid+"' ");
    	
    	System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
         
         return sql.toString();
	}
    
    
}
