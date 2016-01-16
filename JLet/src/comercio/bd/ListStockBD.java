package comercio.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListStockBD extends SelectQueryBD {

    String cdestado = null;
    String idemisor = null;
    String imeicode = null;
    
    public ListStockBD(){
    }
    
    public ListStockBD(ObjectIO bdata) throws Exception {
    	
    	idemisor = bdata.getStringValue("idemisor");
    	cdestado = bdata.getStringValue("cdestado");
    	imeicode = bdata.getStringValue("imeicode");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT idstockx, milisegu, idemisor, cdestado, imeicode, quantity,txmarcax, txmodelo, ");
        sql.append(" idcolorx, pricechf, priceusd, porcmarg, txprovid, txbuyerx, ");
        sql.append(" txfundin, withboxx, withusbx, idcatego, mcactivo,idproduc,tpproduc ");
        sql.append(" FROM tradstoc ");
        sql.append(" WHERE mcactivo = 'S' ");
        sql.append(" AND idemisor = '"+ idemisor +"'");
        
        if ((cdestado != null) && (!cdestado.equals(""))){
        	sql.append(" AND cdestado = '"+ cdestado +"'");
        } else {
        	sql.append(" AND cdestado = 'PRESTOCK' ");
        }
        
        if ((imeicode != null) && (!imeicode.equals(""))){
        	sql.append(" AND imeicode = '"+ imeicode +"'");
        }
        
 
        
        sql.append(" ORDER BY milisegu ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
    	select.add("idstockx");
    	select.add("milisegu");
    	select.add("idemisor");
    	select.add("cdestado");
    	select.add("imeicode");
    	select.add("quantity");
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
    	select.add("tpproduc");

        return select;        
    }
    
    
}
