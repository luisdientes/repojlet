package facturacion.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListLineasCodBarrasBD extends SelectQueryBD {

	String idemisor = null;
    String idclient = null;
    String tpclient = null;
    String cdestado = null;
    String imeisxxx = null;
  
    
    
    public ListLineasCodBarrasBD(){
    
    }
    
    public ListLineasCodBarrasBD(ObjectIO bdata) throws Exception {
    	
    	idemisor = bdata.getStringValue("idemisor");
    	idclient = bdata.getStringValue("idclient");
    	tpclient = bdata.getStringValue("tpclient");
    	cdestado = bdata.getStringValue("cdestado");
    	imeisxxx = bdata.getStringValue("imeisxxx");
           
    	if ((tpclient == null) || (tpclient.equals(""))){
    		tpclient = "0";
    	}
    	
    	
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();
     

        sql.append(" SELECT imeicode, codprodu,txmarcax, txmodelo");
        sql.append(" FROM tradstoc ");
        sql.append(" WHERE idemisor = "+ idemisor);
        sql.append(" AND imeicode IN ('"+ imeisxxx +"')");
     //   sql.append(" AND (cdestado ='VENDIDO' OR cdestado='VENDIDOEXT' OR cdestado='VENDIDOINT' OR cdestado='DEPOSITO')");

       // sql.append(" GROUP BY codprodu ");
 
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
		select.add("idunicox");
		select.add("codprodu");
		select.add("txmarcax");
		select.add("txmodelo");	
		
        return select;        
    }
    
    
}
