package facturacion.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListLineasTmpBD extends SelectQueryBD {

	String idemisor = null;
    String idclient = null;
    String tpclient = null;
    String cdestado = null;
    String idtmpfra = null;
    String marcados = null;
    
    
    public ListLineasTmpBD(){
    
    }
    
    public ListLineasTmpBD(ObjectIO bdata) throws Exception {
    	
    	idemisor = bdata.getStringValue("idemisor");
    	idclient = bdata.getStringValue("idclient");
    	tpclient = bdata.getStringValue("tpclient");
    	cdestado = bdata.getStringValue("cdestado");
    	idtmpfra = bdata.getStringValue("idtmpfra");
    	marcados = bdata.getStringValue("marcados");
    	
        
    	if ((tpclient == null) || (tpclient.equals(""))){
    		tpclient = "0";
    	}
    	
    	
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT idtmpreg, idtmpfra,idunicox, codprodu, idclient, fhfechax, nlineaxx, ");
        sql.append(" cantidad, concepto, precioun, descuent, precioto ");
        sql.append(" FROM tmpfactu ");
        sql.append(" WHERE idemisor = '"+ idemisor +"' AND idclient = '"+ idclient +"'");
        
        if (!tpclient.equals("-1")){
        	sql.append(" AND tpclient = "+ tpclient +" ");
    	}
        
        if ((idtmpfra != null) && (!idtmpfra.equals(""))){
        	sql.append(" AND idtmpfra='"+ idtmpfra +"' ");
    	}
        
        if ((cdestado != null) && (cdestado.equals("R"))){
        	sql.append(" AND (cdestado='F' OR cdestado='D')");
    	}else{
    		 sql.append(" AND cdestado='"+ cdestado +"' ");
    	}
        
        	
       

        
        if ((marcados != null) && (!marcados.equals(""))){
        	sql.append(" AND  idtmpreg IN ("+ marcados +") ");
    	}
        
        
        sql.append(" ORDER BY idtmpreg ");
        
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
		select.add("idtmpreg");
		select.add("idtmpfra");
		select.add("idunicox");
		select.add("codprodu");
		select.add("idclient");
		select.add("fhfechax");
		select.add("nlineaxx");
		select.add("cantidad");
		select.add("concepto");
		select.add("precioun");
		select.add("descuent");
		select.add("precioto");
		
        return select;        
    }
    
    
}
