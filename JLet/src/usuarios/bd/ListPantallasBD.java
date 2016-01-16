package usuarios.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListPantallasBD extends SelectQueryBD {

    String cdrolxxx = null;
    String cdpadrex = null;
    String cdpantal = null;
    
    public ListPantallasBD(){
    
    }
    
    public ListPantallasBD(ObjectIO bdata) throws Exception {
    	
    	cdrolxxx = bdata.getStringValue("cdrolxxx");
    	cdpadrex = bdata.getStringValue("cdpadrex");
    	cdpantal = bdata.getStringValue("cdpantal");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT pn.cdpantal, pn.txnombre, ");
        sql.append(" pn.iconfile, pn.cdpadrex, pn.controll, pn.services, pn.viewjspx, pn.ordenpan ");
        sql.append(" FROM jlpantal pn ");        
        sql.append(" WHERE pn.mcactivo = 'S'  ");
        sql.append(" ORDER BY pn.txnombre ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    public Collection<String> defineSelect() {

    	Vector<String> select=new Vector<String>();
		select.add("cdpantal");
		select.add("txnombre");
		select.add("iconfile");
		select.add("cdpadrex");
		select.add("controll");
		select.add("services");
		select.add("viewjspx");
		select.add("ordenpan");
		
        return select;        
    }
    
    
}
