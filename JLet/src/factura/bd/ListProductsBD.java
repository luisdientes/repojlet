package factura.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListProductsBD extends SelectQueryBD {

    String cduserid = null;
        
    public ListProductsBD(){
    }
    
    public ListProductsBD(ObjectIO bdata) throws Exception {
    	
        cduserid = bdata.getStringValue("cduserid");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();
        
        sql.append(" SELECT 'PH', ph.idgrupox, ph.txmodelo, ph.impdefve, ma.txnombre AS txmarcax FROM izumgrph ph, izummarc ma");
        sql.append(" WHERE  ph.idmarcax = ma.idmarcax ");
        sql.append("  UNION SELECT 'PI', idpiezax, txdescri, preciopr,txmarcax FROM izumgrpi");
       
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
		
    	select.add("prefijox");
    	select.add("idgrupox");
		select.add("txmodelo");
		select.add("impdefve");
		select.add("txmarcax");

		
        return select;        
    }
    
    
}
