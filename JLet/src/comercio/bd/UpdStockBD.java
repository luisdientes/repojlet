package comercio.bd;

import java.util.Collection;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdStockBD extends UpdateQueryBD {

	String idemisor = null;
	String cdestado = null;
	String imeicode = null;
	String txmarcax = null;
	String txmodelo = null;
	String idcolorx = null;
	String pricechf = null;
	String priceusd = null;
	String pricevnt = null;
	String txprovid = null;
	String txbuyerx = null;
	String txfundin = null;
	String withboxx = null;
	String withusbx = null;
	String idcatego = null;
	String porcmarg = null;
	String idlineax = null;
	String mcactivo = "S";
	String idclient = null;
	String idproved = null;
	String oldcdest = null;
	String idfactur = null;
	String tpclient = null;
	String prusdvnt = null;
	String divisvnt = null;
    
    public UpdStockBD(){
    }
    
    public UpdStockBD(ObjectIO bdata) throws Exception {

    	idemisor = bdata.getStringValue("idemisor");
    	cdestado = bdata.getStringValue("cdestado");
    	imeicode = bdata.getStringValue("imeicode");
    	txmarcax = bdata.getStringValue("txmarcax");
    	txmodelo = bdata.getStringValue("txmodelo");
    	idcolorx = bdata.getStringValue("idcolorx");
    	pricechf = bdata.getStringValue("pricechf");
    	priceusd = bdata.getStringValue("priceusd");
    	pricevnt = bdata.getStringValue("pricevnt");
    	txprovid = bdata.getStringValue("txprovid");
    	txbuyerx = bdata.getStringValue("txbuyerx");
    	txfundin = bdata.getStringValue("txfundin");
    	withboxx = bdata.getStringValue("withboxx");
    	withusbx = bdata.getStringValue("withusbx");
    	idcatego = bdata.getStringValue("idcatego");
    	porcmarg = bdata.getStringValue("porcmarg");
    	idlineax = bdata.getStringValue("idlineax");
    	idclient = bdata.getStringValue("idclient");
    	idproved = bdata.getStringValue("idproved");
    	oldcdest = bdata.getStringValue("oldcdest");
    	idfactur = bdata.getStringValue("idfactur");
    	tpclient = bdata.getStringValue("tpclient");
    	prusdvnt = bdata.getStringValue("prusdvnt");
    	divisvnt = bdata.getStringValue("divisvnt");
        
    }       
    
    public String getInsertStatement(){
        
        StringBuffer sql = new StringBuffer();
        
        sql.append(" INSERT INTO tradstoc (idemisor,imeicode, txmarcax, cdestado, txmodelo, idcolorx, pricechf,");
        sql.append(" priceusd, porcmarg, txprovid, txbuyerx, txfundin, withboxx, withusbx, idcatego,mcactivo)");
        sql.append(" VALUES ");
        sql.append(" ("+ idemisor +",'"+imeicode+"','"+txmarcax+"','"+ cdestado +"','"+txmodelo+"','"+idcolorx+"',"+pricechf+","+priceusd+",");
        sql.append(" '"+porcmarg+"','"+txprovid+"','"+txbuyerx+"','"+txfundin+"','"+withboxx+"','"+withusbx+"','"+idcatego+"','"+mcactivo+"')");
        
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    public String getDeleteStatement(){
  
    	StringBuffer sql = new StringBuffer();
        
	    sql.append(" DELETE FROM tradstoc ");
	    sql.append(" WHERE idstockx  = '"+idlineax+"'");
	        
	    System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
	        
	    return sql.toString();
    }

	@Override
	public String getUpdateStatement() {
		
		StringBuffer sql = new StringBuffer();
	        
	    sql.append(" UPDATE tradstoc ");
	    sql.append(" SET cdestado = '"+ cdestado +"'");
	    
	    if ((idclient != null) && (!idclient.equals(""))){
	    	sql.append(",idclient = '"+ idclient +"' ");
	    }
	    
	    if ((idfactur != null) && (!idfactur.equals(""))){
	    	sql.append(",idfactur = '"+ idfactur +"' ");
	    }
	    
	    if ((tpclient != null) && (!tpclient.equals(""))){
	    	sql.append(",tpclient = '"+ tpclient +"' ");
	    }
	    
	    if ((pricevnt != null) && (!pricevnt.equals(""))){
	    	sql.append(",pricevnt = '"+ pricevnt +"' ");
	    }
	    if ((prusdvnt != null) && (!prusdvnt.equals(""))){
	    	sql.append(",prusdvnt = '"+ prusdvnt +"' ");
	    }
	    if ((divisvnt != null) && (!divisvnt.equals(""))){
	    	sql.append(",divisvnt = '"+ divisvnt +"' ");
	    }
	    
	    sql.append(" WHERE 1 = 1 ");
	    
	    if ((oldcdest != null) && (!oldcdest.equals(""))){
	    	sql.append(" AND cdestado = '"+ oldcdest +"' ");
	    }
	    
	    if ((idemisor != null) && (!idemisor.equals(""))){
	    	sql.append(" AND idemisor = '"+ idemisor +"' ");
	    }
	    
	    if ((imeicode != null) && (!imeicode.equals(""))){
	    	sql.append(" AND imeicode = '"+ imeicode +"' ");
	    }
	    
	    if(cdestado.equals("STOCK") && (idfactur != null && !idfactur.equals(""))){
	    	sql.append(" AND idfactur = '"+ idfactur +"' ");
	    }
	    
	   
	        
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
