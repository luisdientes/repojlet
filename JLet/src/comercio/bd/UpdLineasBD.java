package comercio.bd;

import java.util.Collection;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdLineasBD extends UpdateQueryBD {

	String codeenvi = null;
	String imeicode = null;
	String txmarcax = null;
	String txmodelo = null;
	String idcolorx = null;
	String pricechf = null;
	String priceusd = null;
	String txprovid = null;
	String txbuyerx = null;
	String txfundin = null;
	String withboxx = null;
	String withusbx = null;
	String idcatego = null;
	String porcmarg = null;
	String idlineax = null;
	String mcactivo = "S";
	String quantity = null;
	String tpproduc = null;
	String idemisor = null;
    
    public UpdLineasBD(){
    }
    
    public UpdLineasBD(ObjectIO bdata) throws Exception {
    	    	
    	codeenvi = bdata.getStringValue("codeenvi");
    	imeicode = bdata.getStringValue("imeicode");
    	idemisor = bdata.getStringValue("idemisor");
    	txmarcax = bdata.getStringValue("txmarcax");
    	txmodelo = bdata.getStringValue("txmodelo");
    	idcolorx = bdata.getStringValue("idcolorx");
    	pricechf = bdata.getStringValue("pricechf");
    	priceusd = bdata.getStringValue("priceusd");
    	txprovid = bdata.getStringValue("txprovid");
    	txbuyerx = bdata.getStringValue("txbuyerx");
    	txfundin = bdata.getStringValue("txfundin");
    	withboxx = bdata.getStringValue("withboxx");
    	withusbx = bdata.getStringValue("withusbx");
    	idcatego = bdata.getStringValue("idcatego");
    	porcmarg = bdata.getStringValue("porcmarg");
    	idlineax = bdata.getStringValue("idlineax");
    	quantity = bdata.getStringValue("quantity");
    	tpproduc = bdata.getStringValue("tpproduc");
        
    }       
    
    public String getInsertStatement(){
        
        StringBuffer sql = new StringBuffer();
        
        sql.append(" INSERT INTO tradtmen (imeicode,idemisor,txmarcax, txmodelo, idcolorx, pricechf,");
        sql.append(" priceusd, porcmarg, txprovid, txbuyerx, txfundin, withboxx, withusbx, idcatego,mcactivo,quantity,tpproduc)");
        sql.append(" VALUES ");
        sql.append(" ('"+imeicode+"','"+idemisor+"','"+txmarcax+"','"+txmodelo+"','"+idcolorx+"',"+pricechf+","+priceusd+",");
        sql.append(" '"+porcmarg+"','"+txprovid+"','"+txbuyerx+"','"+txfundin+"','"+withboxx+"','"+withusbx+"','"+idcatego+"','"+mcactivo+"','"+quantity+"','"+tpproduc+"')");
        
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    public String getDeleteStatement(){
  
    	StringBuffer sql = new StringBuffer();
        
	    sql.append(" DELETE FROM tradtmen ");
	    sql.append(" WHERE idproenv  = '"+idlineax+"'");
	        
	    System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
	        
	    return sql.toString();
    }

	@Override
	public String getUpdateStatement() {
		
		StringBuffer sql = new StringBuffer();
	        
	    sql.append(" UPDATE tradtmen ");
	    sql.append(" SET codeenvi = '"+ codeenvi +"'");
	    sql.append(" WHERE codeenvi IS NULL AND mcactivo = 'S' ");
	    sql.append(" AND tpproduc='"+tpproduc+"' ");
	        
	    //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
	       
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
