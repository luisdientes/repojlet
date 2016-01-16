package comercio.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListFixingBD extends SelectQueryBD {

    String cddivisa = null;
    String diviscam = null;
        
    public ListFixingBD(){
    }
    
    public ListFixingBD(ObjectIO bdata) throws Exception {
    	
    	cddivisa = bdata.getStringValue("cddivisa");
    	diviscam = bdata.getStringValue("diviscam");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT cdcotiza, divisuno, diviscam, fixingxx, altatime ");
        sql.append(" FROM divisatb ");
        sql.append(" WHERE divisuno = '"+ cddivisa +"'");
       
        if ((diviscam != null) && (!diviscam.equals(""))){
        	sql.append(" AND diviscam = '"+ diviscam +"'");
        }
        
        sql.append(" ORDER BY altatime DESC, diviscam DESC");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
		select.add("cdcotiza");
		select.add("divisuno");
		select.add("diviscam");
		select.add("fixingxx");
		select.add("altatime");
		
        return select;        
    }
    
    
}
