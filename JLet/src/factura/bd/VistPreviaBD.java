package factura.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class VistPreviaBD extends SelectQueryBD {

    String idclient = null;
    String tpclient = null;
    String idemisor = null;
    
    public VistPreviaBD(){
    }
    
    public VistPreviaBD(ObjectIO bdata) throws Exception {
    	
        idclient = bdata.getStringValue("idclient");
        tpclient = bdata.getStringValue("tpclient");
        idemisor = bdata.getStringValue("idemisor");
        
        if ((tpclient == null) || (tpclient.equals(""))){
        	tpclient = "0";
        }
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();
        
        sql.append(" SELECT fa.codprodu,fa.idunicox,fa.precioun, ");
        sql.append(" fa.cantidad,fa.concepto,fa.descuent,fa.precioto,DATE_FORMAT(fa.fhfechax,'%d/%m/%Y') as fecha");
        sql.append(" FROM tmpfactu fa ");
        sql.append(" WHERE fa.idclient=" + idclient);
        sql.append(" AND idemisor = "+ idemisor);
        sql.append(" AND tpclient = "+ tpclient);
        sql.append(" AND cdestado='P'"); 
        sql.append(" ORDER BY codprodu ");
        
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
		
		select.add("codprodu");
		select.add("idunicox");
		select.add("precioun");
		select.add("cantidad");
		select.add("concepto");
		select.add("descuent");
		select.add("precioto");
		select.add("fecha");	
        return select;    	       
    }
    
    
}
