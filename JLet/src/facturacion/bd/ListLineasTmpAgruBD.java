package facturacion.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListLineasTmpAgruBD extends SelectQueryBD {

	String idemisor = null;
    String idclient = null;
    String tpclient = null;
    String cdestado = null;
    String idfactur = null;
  
    
    
    public ListLineasTmpAgruBD(){
    
    }
    
    public ListLineasTmpAgruBD(ObjectIO bdata) throws Exception {
    	
    	idemisor = bdata.getStringValue("idemisor"); 	
    	idfactur = bdata.getStringValue("idfactur");
    	
        
    	if ((tpclient == null) || (tpclient.equals(""))){
    		tpclient = "0";
    	}
    	
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();
     

        sql.append(" SELECT count(codprodu) unidades, codprodu,txmarcax, txmodelo, IFNULL(pricevnt,0)");
        sql.append(" FROM tradstoc ");
        sql.append(" WHERE idemisor = "+ idemisor);
        sql.append(" AND idfactur = "+ idfactur);
     //   sql.append(" AND (cdestado ='VENDIDO' OR cdestado='VENDIDOEXT' OR cdestado='VENDIDOINT' OR cdestado='DEPOSITO')");

        sql.append(" GROUP BY codprodu ");
 
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    public Collection<String> defineSelect() {


    	Vector<String> select = new Vector<String>();
		select.add("unidades");
		select.add("codprodu");
		select.add("txmarcax");
		select.add("txmodelo");	
		select.add("pricevnt");	
		
        return select;        
    }
    
    
}
