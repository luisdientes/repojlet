package factura.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListProductsEmisorBD extends SelectQueryBD {

    String cduserid = null;
    String idemisor = null;
        
    public ListProductsEmisorBD(){
    }
    
    public ListProductsEmisorBD(ObjectIO bdata) throws Exception {
    	
        cduserid = bdata.getStringValue("cduserid");
        idemisor = bdata.getStringValue("idemisor");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();
        
        sql.append(" SELECT codprodu, txmarcax, txdescri, impdefve, divisaxx FROM jlcodven");
        sql.append(" WHERE  idemisor = "+idemisor);
     
       
        //sql.append(" SELECT  pr.idproduc, pr.idgrupox, pr.txdescri, pr.impvenef, pr.impventa, gr.idmarcax,  ");
        
        /*
        sql.append(" SELECT  pr.idproduc, pr.idgrupox, pr.txdescri, pr.impvenef, pr.impventa, gr.idmarcax,  ");
        sql.append(" ma.txnombre AS txmarcax, txmodelo, txcatego, impdefco, impdefve ");
        sql.append(" FROM izumgrph gr, izummarc ma , izumprod pr ");
        sql.append(" WHERE  gr.idmarcax = ma.idmarcax AND gr.idgrupox = pr.idgrupox ");*/
        
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
		
    	select.add("idgrupox");
    	select.add("txmarcax");
		select.add("txmodelo");
		select.add("impdefve");
		select.add("divisaxx");

        return select;        
    }
    
    
}
