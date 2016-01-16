package comercio.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListLineasEnvioTasasBD extends SelectQueryBD {

    String codeenvi = null;
    String fhdesdex = null;
    String fhhastax = null;
        
    public ListLineasEnvioTasasBD(){
    }
    
    public ListLineasEnvioTasasBD(ObjectIO bdata) throws Exception {
    	
    	codeenvi = bdata.getStringValue("codeenvi");
    	fhdesdex = bdata.getStringValue("fhdesdex");
    	fhhastax = bdata.getStringValue("fhhastax");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT en.idproenv, en.milisegu, en.codeenvi, en.imeicode, en.txmarcax, en.txmodelo, ");
        sql.append(" en.idcolorx, en.pricechf, en.priceusd, en.porcmarg, en.txprovid, en.txbuyerx, ");
        sql.append(" en.txfundin, en.withboxx, en.withusbx, en.idcatego, en.mcactivo, en.idproduc, en.quantity, ");
        sql.append(" tx.custotax, tx.consutax, tx.fletecst, tx.itbisimp, tx.tramadua, tx.almacena, tx.movconte, tx.cargnavi ");
        sql.append(" FROM tradtmen en, tradtaxe tx ");
        sql.append(" WHERE en.idproenv = tx.idtaxexx ");
       	sql.append(" AND  en.codeenvi = '"+ codeenvi +"'");
        sql.append(" ORDER BY en.idproenv "); 
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
    	select.add("idproenv");
    	select.add("milisegu");
    	select.add("codeenvi");
    	select.add("imeicode");
    	select.add("txmarcax");
    	select.add("txmodelo");
    	select.add("idcolorx");
    	select.add("pricechf");
    	select.add("priceusd");
    	select.add("porcmarg");
    	select.add("txprovid");
    	select.add("txbuyerx");
    	select.add("txfundin");
    	select.add("withboxx");
    	select.add("withusbx");
    	select.add("idcatego");
    	select.add("mcactivo");
    	select.add("idproduc");
    	select.add("quantity");
		select.add("custotax");
		select.add("consutax");
		select.add("fletecst");
		select.add("itbisimp");
		select.add("tramadua");
		select.add("almacena");
		select.add("movconte");
		select.add("cargnavi");
		
        return select;        
    }
    
    
}
