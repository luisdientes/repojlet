package subasta.bd;

import java.util.Collection;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdSubastaBD extends UpdateQueryBD {

	String txwebxxx = null;
	String txpaisxx = null;
	String txusuari = null;
	String tipovent = null;
	String idproduc = null;
	String txnombre = null;
	String preciosa = null;
	String preciotr = null;
	String divisaxx = null;
	String cdintern = null;
	String urlexter = null;
	String preciomi = null;
	String fechvent = null;
	String horavent = null;
	String finfecve = null;
	String finhorve = null;
	String desactiv = null;
	String idcodsub = null;
    
    public UpdSubastaBD(){
    }
    
    public UpdSubastaBD(ObjectIO bdata) throws Exception {
    	    	
    	txwebxxx = bdata.getStringValue("txwebxxx");
    	txpaisxx = bdata.getStringValue("txpaisxx");
    	txusuari = bdata.getStringValue("txusuari");
    	tipovent = bdata.getStringValue("tipovent");
    	idproduc = bdata.getStringValue("idproduc");
    	txnombre = bdata.getStringValue("txnombre");
    	preciosa = bdata.getStringValue("preciosa");
    	preciotr = bdata.getStringValue("preciotr");
    	divisaxx = bdata.getStringValue("divisaxx");
    	cdintern = bdata.getStringValue("cdintern");
    	urlexter = bdata.getStringValue("urlexter");
    	preciomi = bdata.getStringValue("preciomi");
    	fechvent = bdata.getStringValue("fechvent");
    	horavent = bdata.getStringValue("horavent");
    	finfecve = bdata.getStringValue("finfecve");
    	finhorve = bdata.getStringValue("finhorve");
    	desactiv = bdata.getStringValue("desactiv");
    	idcodsub = bdata.getStringValue("idcodsub"); 

        
    }       
    
    public String getInsertStatement(){
        
        StringBuffer sql = new StringBuffer();
        
        sql.append(" INSERT INTO jlsubast (txwebxxx, idpaisxx, txusuari, tipovent, idproduc,");
        sql.append(" txnombre, preciosa, preciotr,  divisaxx, cdintern, urlexter, preciomi, fechvent, horavent,finfecve,finhorve)");
        sql.append(" VALUES ");
        sql.append(" ('"+txwebxxx+"','"+txpaisxx+"','"+txusuari+"','"+tipovent+"','"+idproduc+"','"+txnombre+"',");
        sql.append(" '"+preciosa+"','"+preciotr+"','"+divisaxx+"','"+cdintern+"','"+urlexter+"','"+preciomi+"','"+fechvent+"','"+horavent+"','"+finfecve+"','"+finhorve+"')");
        
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    public String getDeleteStatement(){
  
        
	    return null;
    }

	@Override
	public String getUpdateStatement() {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" UPDATE jlsubast");
		sql.append(" SET mcactivo ='"+desactiv+"' ");
		sql.append(" WHERE idcodsub ='"+idcodsub+"' ");
		 
		System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
	        
	    return sql.toString();
		
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
