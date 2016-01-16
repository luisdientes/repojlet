package personal.bd;

import java.util.Collection;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdEmpleBD extends UpdateQueryBD {

	String txnombre = null;
	String txapelli = null;
	String txempres = null;
	String cdnifxxx = null;
	
	String txmailxx = null;
	String tfnomovi = null;
	String txcoment = null;
	String username = null;
	
	String userpass = null;
	String idusuari = null;
	String idproyec = null;
	String idacceso = null;
	String idemisor = null;

    
    public UpdEmpleBD(){
    }
    
    public UpdEmpleBD(ObjectIO bdata) throws Exception {
    	
    	txnombre = bdata.getStringValue("txnombre");
    	txapelli = bdata.getStringValue("txapelli");
    	txempres = bdata.getStringValue("txempres");
    	cdnifxxx = bdata.getStringValue("cdnifxxx");
    	txmailxx = bdata.getStringValue("txmailxx");
    	tfnomovi = bdata.getStringValue("tfnomovi");
    	txcoment = bdata.getStringValue("txcoment");
    	idusuari = bdata.getStringValue("idusuari");
    	idproyec = bdata.getStringValue("idproyec");
    	idacceso = bdata.getStringValue("idacceso");
    	idemisor = bdata.getStringValue("idemisor");
    }       
    
    public String getInsertStatement(){
        
        StringBuffer sql = new StringBuffer();
        
        sql.append(" INSERT INTO gstntrab ");
    	sql.append("(txnombre, txapelli, idempres, nifcifxx, txmailxx,telefono,txcoment,fhaltaxx, idacceso,idemisor)");
    	sql.append(" VALUES ");
    	sql.append("('"+txnombre+"' ,");
    	sql.append(" '"+txapelli+"' ,");
    	sql.append(" '"+txempres+"' ,");
    	sql.append(" '"+cdnifxxx+"' ,");
    	sql.append(" '"+txmailxx+"' ,");
    	sql.append(" '"+tfnomovi+"' ,");
    	sql.append(" '"+txcoment+"' ,");
    	sql.append(" CURRENT_DATE ,");
    	sql.append(" '"+idacceso+"' ,");
    	sql.append(" '"+idemisor+"')");
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
		
		sql.append(" UPDATE gstntrab SET");
    	sql.append(" txnombre='"+txnombre+"'");
    	sql.append(", txapelli='"+txapelli+"'");
    	sql.append(", idempres='"+txempres+"'");
    	sql.append(", nifcifxx='"+cdnifxxx+"'");
    	sql.append(", txmailxx='"+txmailxx+"'");
    	sql.append(", telefono='"+tfnomovi+"'");
    	sql.append(", txcoment='"+txcoment+"'");
    	sql.append(" WHERE idacceso ='"+idacceso+"' ");
    	
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
