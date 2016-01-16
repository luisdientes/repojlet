package comercio.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListLineasEnvioBD extends SelectQueryBD {

    String codeenvi = null;
    String tipoenvi = null;
    String idemisor = null;
        
    public ListLineasEnvioBD(){
    }
    
    public ListLineasEnvioBD(ObjectIO bdata) throws Exception {
    	
    	codeenvi = bdata.getStringValue("codeenvi");
    	tipoenvi = bdata.getStringValue("tipoenvi");
    	idemisor = bdata.getStringValue("idemisor");
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT idproenv,idemisor ,milisegu, codeenvi, imeicode, txmarcax, txmodelo, ");
        sql.append(" idcolorx, pricechf, priceusd, porcmarg, txprovid, txbuyerx, ");
        sql.append(" txfundin, withboxx, withusbx, idcatego, mcactivo,idproduc,quantity ");
        sql.append(" FROM tradtmen ");
        sql.append(" WHERE mcactivo = 'S' ");
        if ((codeenvi != null) && (!codeenvi.equals(""))){
        	sql.append(" AND codeenvi = '"+ codeenvi +"'");
        } else {
        	sql.append(" AND codeenvi IS NULL ");
        }
        
        if ((tipoenvi != null) && (!tipoenvi.equals(""))){
        	sql.append(" AND tpproduc = '"+ tipoenvi +"'");
        }
        
        if ((idemisor != null) && (!idemisor.equals(""))){
        	sql.append(" AND idemisor = '"+ idemisor +"'");
        }
        
        sql.append(" ORDER BY milisegu ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
    	select.add("idproenv");
    	select.add("idemisor");
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

        return select;        
    }
    
    
}
