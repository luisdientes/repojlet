package stock.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListStockImgBD extends SelectQueryBD {

    String cdestado = null;
    String idemisor = null;
    String imeicode = null;
    String idclient = null;
    String txmarcax = null;
    String tpproduc = null;
    
    public ListStockImgBD(){
    }
    
    public ListStockImgBD(ObjectIO bdata) throws Exception {
    	
    	idemisor = bdata.getStringValue("idemisor");
    	cdestado = bdata.getStringValue("cdestado");
    	imeicode = bdata.getStringValue("imeicode");
    	idclient = bdata.getStringValue("idclient");
    	txmarcax = bdata.getStringValue("txmarcax");
    	tpproduc = bdata.getStringValue("tpproduc");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();
        /*
        sql.append(" SELECT gr.codprodu, st.idemisor, COUNT(gr.codprodu) unidades,gr.tpproduc, gr.txmarcax,");  
        sql.append(" gr.txdescri, imagenxx as imagewpt  FROM jlcodven gr, tradstoc st"); 
        sql.append(" WHERE  gr.codprodu =st.codprodu");
        sql.append(" AND gr.idemisor = st.idemisor ");
        sql.append(" AND st.idemisor ="+idemisor);
        
        if ((txmarcax != null) && (!txmarcax.equals(""))){
        	sql.append(" AND gr.txmarcax ='"+txmarcax+"' ");
        }
        
        if ((tpproduc != null) && (!tpproduc.equals(""))){
        	sql.append(" AND gr.tpproduc ='"+tpproduc+"' ");
        }
        
        
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
        
        sql.append(" GROUP BY gr.codprodu ");
        sql.append(" ORDER BY gr.tpproduc,gr.txmarcax,  DESC");*/
        
        
        
        sql.append("  SELECT idgrupox, st.idemisor,st.codprodu, st.tpproduc, COUNT(idgrupox) unidades, ma.txnombre AS txmarcax,  ");
        sql.append(" gr.txmodelo, imagedet as imagewpt ");
        sql.append(" FROM izumgrph gr, izummarc ma, tradstoc st");
        sql.append(" WHERE gr.idmarcax = ma.idmarcax ");
        sql.append(" AND gr.idgrupox = CONVERT(SUBSTRING(st.codprodu,3),UNSIGNED INTEGER)");
        sql.append(" AND (SUBSTRING(st.codprodu,1,2)='PH' OR SUBSTRING(st.codprodu,1,2)='EL')");
        sql.append(" AND st.idemisor = '"+ idemisor +"'");
        
        if(txmarcax != null && !txmarcax.equals("")){
          sql.append(" AND st.txmarcax = '"+ txmarcax +"'");
        }
        
        if(tpproduc != null && !tpproduc.equals("")){
            sql.append(" AND st.tpproduc = '"+ tpproduc +"'");
        }
        
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
        
        sql.append(" GROUP BY gr.idgrupox");
        sql.append(" UNION ");
        
        sql.append("  SELECT idpiezax as idgrupox, st.idemisor,st.codprodu, st.tpproduc, COUNT(idpiezax) unidades, gr.txmarcax,  ");
        sql.append(" gr.nameespa as txmodelo, imgdefau as imagewpt ");
        sql.append(" FROM izumgrpi gr, tradstoc st");
        sql.append(" WHERE ");
        sql.append("  gr.idpiezax = CONVERT(SUBSTRING(st.codprodu,3),UNSIGNED INTEGER)");
        sql.append(" AND SUBSTRING(st.codprodu,1,2)='PI'");
        sql.append(" AND st.idemisor = '"+ idemisor +"'");
        
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
        
        if(txmarcax != null && !txmarcax.equals("")){
            sql.append(" AND st.txmarcax = '"+ txmarcax +"'");
          }
        
        if(tpproduc != null && !tpproduc.equals("")){
            sql.append(" AND st.tpproduc = '"+ tpproduc +"'");
        }
        
        sql.append(" GROUP BY gr.idpiezax");
        sql.append(" ORDER BY tpproduc, txmarcax, txmodelo DESC");
        
        
/*
        sql.append(" SELECT idstockx, milisegu, idemisor, cdestado, tpclient, idclient, codprodu,  ");
        sql.append(" imeicode, quantity, txmarcax, txmodelo, idcolorx, pricechf, priceusd, pricecmp, ");
        sql.append(" diviscmp, prusdcmp, porcmarg, pricevnt, divisvnt, prusdvnt, txprovid, txbuyerx, ");
        sql.append(" txfundin, withboxx,withusbx, idcatego, mcactivo,idproduc,tpproduc ");
        sql.append(" FROM tradstoc ");
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
        
        if ((idclient != null) && (!idclient.equals(""))){
     	   sql.append(" AND idclient = '"+ idclient +"'");
        }
        
        if ((imeicode != null) && (!imeicode.equals(""))){
        	sql.append(" AND imeicode = '"+ imeicode +"'");
        }
        
 
        
        sql.append(" ORDER BY codprodu, txmarcax, txmodelo, idcolorx ");
       */
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
    	select.add("idgrupox");
    	select.add("idemisor");
    	select.add("codprodu");
    	select.add("tpproduc");
    	select.add("unidades");
    	select.add("txmarcax");
    	select.add("txmodelo");
    	select.add("imagewpt");
    
        return select;        
    }
    
    
}
