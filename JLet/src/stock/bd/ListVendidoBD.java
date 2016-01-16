package stock.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListVendidoBD extends SelectQueryBD {

    String cdestado = null;
    String idemisor = null;
    String imeicode = null;
    String idclient = null;
    
    public ListVendidoBD(){
    }
    
    public ListVendidoBD(ObjectIO bdata) throws Exception {
    	
    	idemisor = bdata.getStringValue("idemisor");
    	cdestado = bdata.getStringValue("cdestado");
    	idclient = bdata.getStringValue("idclient");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT st.codprodu, st.imeicode, st.txmarcax, st.txmodelo, st.idcolorx, st.pricevnt, st.divisvnt ");
        sql.append(", st.idcatego, cl.rzsocial "); 
        sql.append(" FROM tradstoc st ");
        sql.append(" LEFT OUTER JOIN jlclierc cl ");
        sql.append(" ON st.idclient = cl.idclient ");        
        sql.append(" WHERE st.idemisor = "+ idemisor);

        if ((cdestado != null) && (!cdestado.equals(""))){
        	if (cdestado.equals("STOCK")){
        		sql.append(" AND (cdestado = 'STOCK' OR cdestado = 'RECDEPOS')");
        	} else if (cdestado.equals("VENDIDO")){
        		sql.append(" AND (cdestado = 'VENDIDO' OR cdestado = 'VENDIDOINT' OR cdestado='VENDIDOEXT')");
        	} else{
        		sql.append(" AND cdestado = '"+ cdestado +"'");
        	}
        } 
        
        if ((idclient != null) && (!idclient.equals(""))){
     	   sql.append(" AND st.idclient = '"+ idclient +"'");
        }
        
        sql.append(" ORDER BY st.codprodu, st.txmarcax, st.txmodelo, st.idcolorx ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
    	select.add("codprodu");
    	select.add("imeicode");
    	select.add("txmarcax");
    	select.add("txmodelo");
    	select.add("idcolorx");
    	select.add("pricevnt");
    	select.add("divisvnt");
    	select.add("idcatego");
    	select.add("rzsocial");

        return select;        
    }
    
    
}
