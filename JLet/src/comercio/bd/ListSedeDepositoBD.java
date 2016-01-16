package comercio.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListSedeDepositoBD extends SelectQueryBD {

    String mcintern = null;
        
    public ListSedeDepositoBD(){
    }
    
    public ListSedeDepositoBD(ObjectIO bdata) throws Exception {
    	
    	mcintern = bdata.getStringValue("mcintern");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT idsedepo, cdcountr, txnombre, idclient, mcintern ");
        sql.append(" FROM tradsede ");
        
        sql.append(" WHERE 1 = 1 ");
        if ((mcintern != null) && (!mcintern.equals(""))){
        	sql.append(" AND  mcintern = '"+ mcintern +"'");
        }        
        sql.append(" ORDER BY idsedepo DESC "); 
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
		select.add("idsedepo");
		select.add("cdcountr");
		select.add("txnombre");
		select.add("idclient");
		select.add("mcintern");
		
        return select;        
    }
    
    
}
