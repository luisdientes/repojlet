package stock.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListStockBD extends SelectQueryBD {

    String cdestado = null;
    String idemisor = null;
    String imeicode = null;
    String idclient = null;
    String codprodu = null;
    String txmarcax = null;
    String tpproduc = null;
    String tipocheq = null;
    String tpclient = null;
    
    public ListStockBD(){
    }
    
    public ListStockBD(ObjectIO bdata) throws Exception {
    	
    	idemisor = bdata.getStringValue("idemisor");
    	cdestado = bdata.getStringValue("cdestado");
    	imeicode = bdata.getStringValue("imeicode");
    	idclient = bdata.getStringValue("idclient");
    	codprodu = bdata.getStringValue("codprodu");
    	txmarcax = bdata.getStringValue("txmarcax");
    	tpproduc = bdata.getStringValue("tpproduc");
    	tipocheq = bdata.getStringValue("tipocheq");
    	tpclient = bdata.getStringValue("tpclient");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT idstockx, milisegu, idemisor, cdestado, tpclient, idclient, codprodu,  ");
        sql.append(" imeicode, quantity, txmarcax, txmodelo, idcolorx, pricechf, priceusd, pricecmp, ");
        sql.append(" diviscmp, prusdcmp, porcmarg, pricevnt, divisvnt, prusdvnt, txprovid, txbuyerx, ");
        sql.append(" txfundin, withboxx,withusbx, idcatego, mcactivo,idproduc,tpproduc ");
        //meter fhventax y fhcompra
        sql.append(" FROM tradstoc ");
        sql.append(" WHERE mcactivo = 'S' ");
        sql.append(" AND idemisor = '"+ idemisor +"'");
        
        if ((cdestado != null) && (!cdestado.equals(""))){
        	if (cdestado.equals("STOCK")){
        		sql.append(" AND (cdestado = 'STOCK' OR cdestado = 'RECDEPOS')");
        	} else if (cdestado.equals("VENDIDO")){
        		sql.append(" AND (cdestado = 'VENDIDO' OR cdestado = 'VENDIDOINT' OR cdestado='VENDIDOEXT')");
        	} else if (cdestado.equals("NOAPLICA")){
        		sql.append(" AND 1 = 1");
        	} else {
        		sql.append(" AND cdestado = '"+ cdestado +"'");
        	}
        } else {
        	sql.append(" AND cdestado = 'PRESTOCK' ");
        }
        
        if ((idclient != null) && (!idclient.equals(""))){
     	   sql.append(" AND idclient = '"+ idclient +"'");
        }
        
        if ((tpclient != null) && (!tpclient.equals(""))){
      	   sql.append(" AND tpclient = '"+ tpclient +"'");
         }
        
        
        
        if ((imeicode != null) && (!imeicode.equals(""))){
        	sql.append(" AND imeicode = '"+ imeicode +"'");
        }
        
        if ((codprodu != null) && (!codprodu.equals(""))){
        	sql.append(" AND codprodu = '"+ codprodu +"'");
        }
        
        if(txmarcax != null && !txmarcax.equals("")){
            sql.append(" AND txmarcax = '"+ txmarcax +"'");
          }
          
        if(tpproduc != null && !tpproduc.equals("") && !tpproduc.equals("TO")){
              sql.append(" AND tpproduc = '"+ tpproduc +"'");
         }
        
        if(tipocheq!=null){
        	if(!tipocheq.equals("TO") && !tipocheq.equals("")){
        		sql.append(" AND tpproduc IN("+ tipocheq +")");
        	}
        }
        
        
        
        sql.append(" ORDER BY codprodu, milisegu DESC, imeicode ASC ");
       
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
    	//select.add("fhcompra");
    	//select.add("fhventax");

        return select;        
    }
    
    
}
