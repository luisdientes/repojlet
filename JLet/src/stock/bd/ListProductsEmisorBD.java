package stock.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListProductsEmisorBD extends SelectQueryBD {

    String codprodu = null;
    String idemisor = null;
        
    public ListProductsEmisorBD(){
    }
    
    public ListProductsEmisorBD(ObjectIO bdata) throws Exception {
    	
    	codprodu = bdata.getStringValue("tpproduc");
        idemisor = bdata.getStringValue("idemisor");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();
        
        sql.append(" SELECT codprodu, txmarcax, txdescri, impdefve, divisaxx,tpproduc, imagenxx FROM jlcodven");
        sql.append(" WHERE  idemisor = "+idemisor);
        
        if( codprodu != null && !codprodu.equals("") ){
        	sql.append(" and codprodu = '"+codprodu+"'");
    	}
     
       
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
		
    	select.add("codprodu");
    	select.add("txmarcax");
		select.add("txmodelo");
		select.add("impdefve");
		select.add("divisaxx");
		select.add("tpproduc");
		select.add("imagenxx");
        return select;        
    }
    
    
}
