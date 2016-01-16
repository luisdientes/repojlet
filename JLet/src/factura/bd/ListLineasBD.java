package factura.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListLineasBD extends SelectQueryBD {

    String idclient = null;
    String tpclient = null;
    String cdestado = null;
    String idtmpreg = null;
    String imeicode = null;
    String idtmpfra = null;
    String idemisor = null;
    String marcados = null;
    String actualiz = null;
    
    public ListLineasBD(){
    }
    
    public ListLineasBD(ObjectIO bdata) throws Exception {
    	
        idclient = bdata.getStringValue("idclient");
        tpclient = bdata.getStringValue("tpclient");
        cdestado = bdata.getStringValue("cdestado");
        idtmpreg = bdata.getStringValue("idtmpreg");
        imeicode = bdata.getStringValue("imeicode");
        idtmpfra = bdata.getStringValue("idtmpfra");
        idemisor = bdata.getStringValue("idemisor");
        marcados = bdata.getStringValue("marcados");
        actualiz = bdata.getStringValue("actualiz");
        
        if(actualiz !=null && actualiz.equals("S")){
        	cdestado = "F";	
        }
    
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        if ((tpclient == null) || (tpclient.equals(""))){
        	tpclient = "0";
        }
        
        sql.append(" SELECT idtmpreg, codprodu,idunicox, idclient, fhfechax, nlineaxx, ");
        sql.append(" cantidad, concepto, precioun, descuent, precioto,idtmpfra ");
        sql.append(" FROM tmpfactu ");
        sql.append(" WHERE 1=1 ");
        
        if(actualiz !=null && actualiz.equals("S")){
        	
        	sql.append(" AND idtmpfra = '"+ idtmpfra +"'");	
        	sql.append(" AND idemisor = '"+ idemisor +"'");
        	
        }else{
        	
        	if ((idclient != null) && (!idclient.equals(""))){
            	sql.append(" AND idclient = '"+ idclient +"'");
            }
            
            if ((tpclient != null) && (!tpclient.equals("") && (!tpclient.equals("-1")))){
            	sql.append(" AND tpclient = '"+ tpclient +"'");  	
            }
            
            if ((cdestado != null) && (!cdestado.equals(""))){
            	sql.append(" AND cdestado = '"+ cdestado +"'");
            }
            
            if ((marcados != null) && (!marcados.equals(""))){
            	sql.append(" AND idtmpreg IN ("+ marcados +")");
            }else if ((idtmpfra != null) && (!idtmpfra.equals(""))){
            	sql.append(" AND idtmpfra = '"+ idtmpfra +"'");
            }
            
            
            if ((idemisor != null) && (!idemisor.equals(""))){
            	sql.append(" AND idemisor = '"+ idemisor +"'");
            }
        }
        
        sql.append(" ORDER BY idtmpreg ");
        
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    public String getDeleteStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" UPDATE tmpfactu ");
        sql.append(" SET cdestado = '"+ cdestado +"'");
        sql.append(" WHERE idtmpreg = "+ idtmpreg);
        
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
		select.add("idtmpreg");
		select.add("codprodu");
		select.add("idunicox");
		select.add("idclient");
		select.add("fhfechax");
		select.add("nlineaxx");
		select.add("cantidad");
		select.add("concepto");
		select.add("precioun");
		select.add("descuent");
		select.add("precioto");
		select.add("idtmpfra");
        return select;        
    }
    
    
}
