package desgcostes.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class ListIMEIFacturadoBD extends UpdateQueryBD {

	String idemisor = null;
	String fhdesdex = null;
	String fhhastax = null;
	
    public ListIMEIFacturadoBD(){
    }
    
    public ListIMEIFacturadoBD(ObjectIO bdata) throws Exception {
    	
    	idemisor = bdata.getStringValue("idemisor");
    	fhdesdex = bdata.getStringValue("fhdesdex");
    	fhhastax = bdata.getStringValue("fhhastax");
        
    }       
    
    public String getInsertStatement(){
        
       return null;
    }
    
    public String getDeleteStatement(){
  
        return null;
    }

	@Override
	public String getUpdateStatement() {
	
		return null;
		
	}



	@Override
	public String getSelectStatment() {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT DISTINCT tm.idunicox ");
		sql.append(" FROM tmpfactu tm, jlfactur fa, dglscost dg ");
		sql.append(" WHERE tm.idemisor = '"+ idemisor +"'");
		sql.append(" AND fa.tipofact IN (1,2,3,4,5) ");
		sql.append(" AND tm.idtmpfra = fa.idtmpfra ");
		sql.append(" AND dg.idunicox = tm.idunicox ");
		sql.append(" AND tm.idunicox != '' ");
		sql.append(" AND dg.codedesg='IGTOTAL' ");
		
		if ((fhdesdex != null) && (!fhdesdex.equals(""))){
			sql.append(" AND fhfactur >= '"+ fhdesdex +"'");
		}
		
		if ((fhhastax != null) && (!fhhastax.equals(""))){
			sql.append(" AND fhfactur <= '"+ fhhastax +"'");
		}
		
		//sql.append(" AND tm.idunicox NOT IN ('353942042180857','354807051014173','353942041210001','358033043589513','353487046748002','354807051017556','352062067878567','355037052191636') ");
		
		sql.append(" ORDER BY fa.fhfactur ");
		
		System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
	    return sql.toString();
	}
	
	@Override
	public Collection<String> defineSelect() {
	
		Vector<String> select = new Vector<String>();
		select.add("idunicox");	
		
        return select; 
	}
    
     
}

