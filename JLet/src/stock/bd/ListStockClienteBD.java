package stock.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListStockClienteBD extends SelectQueryBD {

    String cdestado = null;
    String idemisor = null;
    String imeicode = null;
    
    public ListStockClienteBD(){
    }
    
    public ListStockClienteBD(ObjectIO bdata) throws Exception {
    	
    	idemisor = bdata.getStringValue("idemisor");
    	cdestado = bdata.getStringValue("cdestado");
    	imeicode = bdata.getStringValue("imeicode");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT idstockx, milisegu, idemisor, cdestado, tpclient, idclient, codprodu,  ");
        sql.append(" imeicode, quantity, txmarcax, txmodelo, idcolorx, pricechf, priceusd, pricecmp, ");
        sql.append(" diviscmp, prusdcmp, porcmarg, pricevnt, divisvnt, prusdvnt, txprovid, txbuyerx, ");
        sql.append(" txfundin, withboxx, withusbx, idcatego, mcactivo, idproduc, tpproduc ");
        sql.append(" FROM tradstoc st, jlclierc cl, jlf ");
        sql.append(" WHERE mcactivo = 'S' ");
        sql.append(" AND idemisor = '"+ idemisor +"'");
        
        if ((cdestado != null) && (!cdestado.equals(""))){
        	if (cdestado.equals("STOCK")){
        		sql.append(" AND (cdestado = 'STOCK' OR cdestado = 'RECDEPOS')");
        	} else if (cdestado.equals("VENDIDO")){
        		sql.append(" AND (cdestado = 'VENDIDO' OR cdestado = 'VENDIDOINT' OR cdestado='VENDIDOEXT')");
        	} else{
        		sql.append(" AND cdestado = '"+ cdestado +"'");
        	}
        } else {
        	sql.append(" AND cdestado = 'PRESTOCK' ");
        }
        
        if ((imeicode != null) && (!imeicode.equals(""))){
        	sql.append(" AND imeicode = '"+ imeicode +"'");
        }
        
 
        
        sql.append(" ORDER BY codprodu, txmarcax, txmodelo, idcolorx ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
    	select.add("idstockx");
    	select.add("milisegu");
    	select.add("idemisor");
    	select.add("cdestado");
    	select.add("tpclient");
    	select.add("idclient");
    	select.add("codprodu");
    	select.add("imeicode");
    	select.add("quantity");
    	select.add("txmarcax");
    	select.add("txmodelo");
    	select.add("idcolorx");
    	select.add("pricechf");
    	select.add("priceusd");
    	select.add("pricecmp");
    	select.add("diviscmp");
    	select.add("prusdcmp");
    	select.add("porcmarg");
    	select.add("pricevnt");
    	select.add("divisvnt");
    	select.add("prusdvnt");
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
