package gestion.administracion.bd;

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

        sql.append(" SELECT pr.cdrolxxx, pr.tipoperm, pr.valorper, pr.nivelper, pn.cdpantal, pn.txnombre, ");
        sql.append(" pn.iconfile, pn.cdpadrex, pn.controll, pn.services, pn.viewjspx, pn.ordenpan ");
        sql.append(" FROM jlpermis pr, jlpantal pn ");        
        sql.append(" WHERE pr.tipoperm = 'PANTALLA' ");
        sql.append(" AND pn.mcactivo = 'S' ");
        sql.append(" AND pr.valorper = pn.cdpantal ");
        sql.append(" AND pr.cdrolxxx = '"+ cdrolxxx +"'");
        
        if ((cdpadrex != null) && (!cdpadrex.equals(""))){
        	sql.append(" AND pn.cdpadrex = '"+ cdpadrex +"'");
        }
        
        sql.append(" ORDER BY pn.cdpadrex, pn.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    public Collection<String> defineSelect() {

    	Vector<String> select=new Vector<String>();
		select.add("cdrolxxx");
		select.add("tipoperm");
		select.add("valorper");
		select.add("nivelper");
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
