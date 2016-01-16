package upgrade.bd;

import java.util.Collection;
import java.util.Vector;

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
	
	String pricecmp = null;
	String diviscmp = null;
	String prusdcmp = null;
	String pricevnt = null;
	String divisvnt = null;
	String prusdvnt = null;
	
	String txprovid = null;
	String txbuyerx = null;
	String txfundin = null;
	String withboxx = null;
	String withusbx = null;
	String withheph = null;
	String idcatego = null;
	String porcmarg = null;
	String idlineax = null;
	String idclient = null;
	String codprodu = null;
	String tpclient = null;
	String withchar = null;
	String mcactivo = "S";
	String withadap = null;
	String idproved = null;
	String oldcdest = null;
    
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
    	
    	pricecmp = bdata.getStringValue("pricecmp");
    	diviscmp = bdata.getStringValue("diviscmp");
    	prusdcmp = bdata.getStringValue("prusdcmp");
    	pricevnt = bdata.getStringValue("pricevnt");
    	divisvnt = bdata.getStringValue("divisvnt");
    	prusdvnt = bdata.getStringValue("prusdvnt");
    	
    	txprovid = bdata.getStringValue("txprovid");
    	txbuyerx = bdata.getStringValue("txbuyerx");
    	txfundin = bdata.getStringValue("txfundin");
    	withboxx = bdata.getStringValue("withboxx");
    	withusbx = bdata.getStringValue("withusbx");
    	withheph = bdata.getStringValue("withheph");
    	withchar = bdata.getStringValue("withchar");
    	withadap = bdata.getStringValue("withadap");
    	idcatego = bdata.getStringValue("idcatego");
    	porcmarg = bdata.getStringValue("porcmarg");
    	idlineax = bdata.getStringValue("idlineax");
    	idclient = bdata.getStringValue("idclient");
    	idproved = bdata.getStringValue("idproved");
    	oldcdest = bdata.getStringValue("oldcdest");
    	codprodu = bdata.getStringValue("codprodu");
    	tpclient = bdata.getStringValue("tpclient");
    	
    	if(idclient == null || idclient.equals("null")){
    		idclient="0";
    	}
    	if(codprodu == null || codprodu.equals("null")){
    		codprodu="0";
    	}
    	
    	if(tpclient == null || tpclient.equals("null")){
    		tpclient="-0";
    	}
    	
    	if(pricecmp == null || pricecmp.equals("null") || pricecmp.equals("")){
    		pricecmp="0";
    	}
    	
    	if(pricevnt == null || pricevnt.equals("null") || pricevnt.equals("")){
    		pricevnt="0";
    	}
    	
    	if(prusdcmp == null || prusdcmp.equals("null") || prusdcmp.equals("")){
    		prusdcmp="0";
    	}
    	
    	if(prusdvnt == null || prusdvnt.equals("null") || prusdvnt.equals("")){
    		prusdvnt="0";
    	}
 
        
    }       
    
    public String getInsertStatement(){
        
        StringBuffer sql = new StringBuffer();
        
        StringBuffer campos  = new StringBuffer();
        StringBuffer valores = new StringBuffer();
        
        campos.append(" INSERT INTO izumoldtr (idemisor,imeicode, txmarcax, codprodu, cdestado, txmodelo ");
        valores.append(" VALUES ("+ idemisor +",'"+imeicode+"','"+txmarcax+"','"+codprodu+"','"+ cdestado +"','"+txmodelo+"'");
        
        if ((idcolorx != null) && (!idcolorx.equals(""))){
        	campos.append(", idcolorx");
        	valores.append(", '"+ idcolorx +"'");
        }
        
        if ((priceusd != null) && (!priceusd.equals(""))){
        	campos.append(", priceusd");
        	valores.append(", '"+ priceusd+"'");
        }

        if ((pricecmp != null) && (!pricecmp.equals(""))){
        	campos.append(", pricecmp");
        	valores.append(", '"+ pricecmp+"'");
        }  
        
        if ((diviscmp != null) && (!diviscmp.equals(""))){
        	campos.append(", diviscmp");
        	valores.append(", '"+ diviscmp+"'");
        } 
        
        if ((prusdcmp != null) && (!prusdcmp.equals(""))){
        	campos.append(", prusdcmp");
        	valores.append(", '"+ prusdcmp+"'");
        } 
        
        if ((pricevnt != null) && (!pricevnt.equals(""))){
        	campos.append(", pricevnt");
        	valores.append(", '"+ pricevnt+"'");
        } 
        
        if ((divisvnt != null) && (!divisvnt.equals(""))){
        	campos.append(", divisvnt");
        	valores.append(", '"+ divisvnt+"'");
        } 
        
        if ((prusdvnt != null) && (!prusdvnt.equals(""))){
        	campos.append(", prusdvnt");
        	valores.append(", '"+ prusdvnt+"'");
        } 
        
        if ((porcmarg != null) && (!porcmarg.equals(""))){
        	campos.append(", porcmarg");
        	valores.append(", '"+ porcmarg +"'");
        }  
        
        if ((txprovid != null) && (!txprovid.equals(""))){
        	campos.append(", txprovid");
        	valores.append(", '"+ txprovid +"'");
        } 
        
        if ((txbuyerx != null) && (!txbuyerx.equals(""))){
        	campos.append(", txbuyerx");
        	valores.append(", '"+ txbuyerx+"'");
        } 
        
        if ((txfundin != null) && (!txfundin.equals(""))){
        	campos.append(", txfundin");
        	valores.append(", '"+ txfundin+"'");
        } 
        
        if ((withboxx != null) && (!withboxx.equals(""))){
        	campos.append(", withboxx");
        	valores.append(", '"+ withboxx+"'");
        } 
        
        
        if ((withheph != null) && (!withheph.equals(""))){
        	campos.append(", withheph");
        	valores.append(", '"+ withheph+"'");
        } 
        
        if ((withusbx != null) && (!withusbx.equals(""))){
        	campos.append(", withusbx");
        	valores.append(", '"+ withusbx+"'");
        } 
        if ((withchar != null) && (!withchar.equals(""))){
        	campos.append(", withchar");
        	valores.append(", '"+ withchar+"'");
        } 
        if ((withadap != null) && (!withadap.equals(""))){
        	campos.append(", withadap");
        	valores.append(", '"+ withadap+"'");
        } 
        

        if ((idcatego != null) && (!idcatego.equals(""))){
        	campos.append(", idcatego");
        	valores.append(", '"+ idcatego+"'");
        } 
        
        if ((mcactivo != null) && (!mcactivo.equals(""))){
        	campos.append(", mcactivo");
        	valores.append(", '"+ mcactivo+"'");
        } 
        
        if ((idclient != null) && (!idclient.equals(""))){
        	campos.append(", idclient");
        	valores.append(", '"+ idclient+"'");
        }
        
        if ((tpclient != null) && (!tpclient.equals(""))){
        	campos.append(", tpclient");
        	valores.append(", '"+ tpclient+"'");
        }
        
        
        
        
        //LUIS TERMINAR 
        
       /* 
        idcolorx, pricechf,");
        campos.append(" priceusd, pricecmp, diviscmp, prusdcmp, pricevnt, divisvnt, prusdvnt,porcmarg, txprovid, txbuyerx, txfundin, withboxx, withusbx, idcatego,mcactivo,idclient,tpclient)");
        sql.append(" VALUES ");
        sql.append(" ("+ idemisor +",'"+imeicode+"','"+txmarcax+"','"+codprodu+"','"+ cdestado +"','"+txmodelo+"','"+idcolorx+"',"+pricechf+","+priceusd+",");
        sql.append(" '"+pricecmp+"','"+diviscmp+"','"+prusdcmp+"','"+pricevnt+"','"+divisvnt+"','"+prusdvnt+"',");
        sql.append(" '"+porcmarg+"','"+txprovid+"','"+txbuyerx+"','"+txfundin+"','"+withboxx+"','"+withusbx+"','"+idcatego+"','"+mcactivo+"','"+idclient+"','"+tpclient+"')");
        */
        
     	campos.append(")");
    	valores.append(")");
   
        sql.append(campos);
        sql.append(valores);
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
	        
	    sql.append(" UPDATE tradstoc ");
	    sql.append(" SET idcatego = '"+ idcatego +"'");
	    
	    if ((cdestado != null) && (!cdestado.equals(""))){
	    	sql.append(",cdestado = '"+ cdestado +"' ");
	    }
	    
	    
	    if ((withboxx != null) && (!withboxx.equals(""))){
	    	sql.append(",withboxx = '"+ withboxx +"' ");
	    }
	    if ((withchar != null) && (!withchar.equals(""))){
	    	sql.append(",withchar = '"+ withchar +"' ");
	    }
	    
	    if ((withheph != null) && (!withheph.equals(""))){
	    	sql.append(",withheph = '"+ withheph +"' ");
	    }
	    if ((withadap != null) && (!withadap.equals(""))){
	    	sql.append(",withadap = '"+ withadap +"' ");
	    }
	    if ((withusbx != null) && (!withusbx.equals(""))){
	    	sql.append(",withusbx = '"+ withusbx +"' ");
	    }
	    
	    
	    
	    
	    if ((pricecmp != null) && (!pricecmp.equals("")) && (!pricecmp.equals("0")) ){
	    	sql.append(",pricecmp = '"+ pricecmp +"' ");
	    }
	    
	    sql.append(" WHERE 1 = 1 ");
	    
	    if ((idemisor != null) && (!idemisor.equals(""))){
	    	sql.append(" AND idemisor = '"+ idemisor +"' ");
	    }
	    
	    if ((imeicode != null) && (!imeicode.equals(""))){
	    	sql.append(" AND imeicode = '"+ imeicode +"' ");
	    }
	    
	    
	        
	    //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
	       
	    System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
	        
	    return sql.toString();
		
	}

	@Override
	public Collection defineSelect() {
		
		Vector<String> select = new Vector<String>();
    	select.add("idgrupox");
    	select.add("idemisor");
    	select.add("codprodu");
    	select.add("imeicode");
    	select.add("idcatego");
    	select.add("tpproduc");
    	select.add("pricevnt");
    	select.add("pricecmp");
    	select.add("withusbx");
    	select.add("withboxx");
    	select.add("withchar");
    	select.add("withheph");
    	select.add("withadap");
    	select.add("txmarcax");
    	select.add("txmodelo");
    	select.add("imagewpt");
    
        return select;        
	}

	@Override
	public String getSelectStatment() {
		
		StringBuffer sql = new StringBuffer();
		
			sql.append("  SELECT idgrupox, st.idemisor,st.codprodu, st.imeicode,idcatego,st.tpproduc,st.pricevnt,st.pricecmp,withusbx, withboxx ,withchar,withheph,st.withadap, ma.txnombre AS txmarcax,  ");
	        sql.append(" gr.txmodelo, imagedet as imagewpt ");
	        sql.append(" FROM izumgrph gr, izummarc ma, tradstoc st");
	        sql.append(" WHERE gr.idmarcax = ma.idmarcax ");
	        sql.append(" AND gr.idgrupox = CONVERT(SUBSTRING(st.codprodu,3),UNSIGNED INTEGER)");
	        sql.append(" AND st.imeicode='"+imeicode+"'");
	        sql.append(" AND st.idemisor = '"+ idemisor +"'");
	        sql.append(" AND (cdestado = 'STOCK' OR cdestado = 'RECDEPOS')");
	       
	     return sql.toString();	  
	}
    
    
}
