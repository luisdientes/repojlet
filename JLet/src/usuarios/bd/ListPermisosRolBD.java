package usuarios.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class ListPermisosRolBD extends UpdateQueryBD {

	String idusuari = "";
	String cdrolxxx = "";
	String valorper = "";
	String tipoperm = "";
    
    public ListPermisosRolBD(){
    	
    }
    
    public ListPermisosRolBD(ObjectIO bdata) throws Exception {
    	
    	idusuari = bdata.getStringValue("idusuari");
    	cdrolxxx = bdata.getStringValue("cdrolxxx");
    	valorper = bdata.getStringValue("valorper");
    	tipoperm = bdata.getStringValue("tipoperm");
    	
    }       
    
    public String getInsertStatement(){
    	StringBuffer sql = new StringBuffer();
        
        sql.append(" INSERT INTO jlpermis (cdrolxxx, tipoperm, valorper, nivelper )");
        sql.append(" VALUES ('"+ cdrolxxx +"','"+tipoperm+"','"+valorper+"','TOTAL')");
        
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        return sql.toString();
    }
    
    public String getDeleteStatement(){
  
    	StringBuffer sql = new StringBuffer();
        
        sql.append(" DELETE FROM jlpermis");
        sql.append(" WHERE cdrolxxx='"+cdrolxxx+"'");
        sql.append(" AND tipoperm='"+tipoperm+"'");
        sql.append(" AND valorper='"+valorper+"'");
        sql.append(" AND nivelper <>'ESPECIAL'");
        
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        return sql.toString();
    }

	@Override
	public String getUpdateStatement() {
		
		return null;
		
	}



	@Override
	public String getSelectStatment() {
	
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT pr.cdrolxxx, pr.tipoperm, pr.valorper, pr.nivelper, pn.cdpantal, pn.txnombre, ");
        sql.append(" pn.iconfile, pn.cdpadrex, pn.controll, pn.services, pn.viewjspx, pn.ordenpan ");
        sql.append(" FROM jlpermis pr, jlpantal pn ");        
        sql.append(" WHERE pr.tipoperm = '"+tipoperm+"' ");
        sql.append(" AND pn.mcactivo = 'S' ");
        sql.append(" AND pr.valorper = pn.cdpantal ");
        sql.append(" AND pr.cdrolxxx = '"+ cdrolxxx +"'");
        sql.append(" ORDER BY pn.cdpadrex, pn.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
   }
	
	@Override
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
