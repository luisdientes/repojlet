package subasta.bd;

import java.util.Collection;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdInfoSubastaBD extends UpdateQueryBD {

	String idcodsub = null;
	String tpevento = null;
	String cduserxx = null;
	String txusuari = null;
	String txmailxx = null;
	String telefono = null;
	String isopaisx = null;
	String cantidad = null;
	String cddivisa = null;
	String direnvio = null;
	String txcoment = null;
	String nunidade = null;
	String pretrans = null;
	String fhventax = null;

    
    public UpdInfoSubastaBD(){
    }
    
    public UpdInfoSubastaBD(ObjectIO bdata) throws Exception {
    	    	
    	idcodsub = bdata.getStringValue("idcodsub");
    	tpevento = bdata.getStringValue("tpevento");
    	cduserxx = bdata.getStringValue("cduserxx");
    	txusuari = bdata.getStringValue("txusuari");
    	txmailxx = bdata.getStringValue("txmailxx");
    	telefono = bdata.getStringValue("telefono");
    	isopaisx = bdata.getStringValue("isopaisx");
    	cantidad = bdata.getStringValue("cantidad");
    	cddivisa = bdata.getStringValue("cddivisa");
    	direnvio = bdata.getStringValue("direnvio");
    	txcoment = bdata.getStringValue("txcoment");
    	nunidade = bdata.getStringValue("nunidade");
    	pretrans = bdata.getStringValue("pretrans");
    	fhventax = bdata.getStringValue("fhventax");
        
    }       
    
    public String getInsertStatement(){
        
        StringBuffer sql = new StringBuffer();
        
        sql.append(" INSERT INTO jlsubinf (idcodsub, tpevento, iduserin, txusuari, txmailxx, ");
        sql.append(" telefono, isopaisx, cantidad, cddivisa, direnvio, txcoment, nunidade, pretrans, fhventax)");
        sql.append(" VALUES ");
        sql.append(" ('"+idcodsub+"','"+tpevento+"','"+cduserxx+"','"+txusuari+"','"+txmailxx+"',");
        sql.append(" '"+telefono+"','"+isopaisx+"',"+cantidad+",'"+cddivisa+"','"+direnvio+"',");
        sql.append(" '"+txcoment+"','"+nunidade+"','"+pretrans+"','"+fhventax+"')");
        
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
