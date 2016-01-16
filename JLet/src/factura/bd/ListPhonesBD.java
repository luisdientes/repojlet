package factura.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListPhonesBD extends SelectQueryBD {

    String cduserid = null;
        
    public ListPhonesBD(){
    }
    
    public ListPhonesBD(ObjectIO bdata) throws Exception {
    	
        cduserid = bdata.getStringValue("cduserid");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT ph.idphonex, ph.cdgrupox, ph.txdescri, ph.cdimeixx, ph.impvenef, ph.impventa,  ");
        sql.append(" gr.idmarcax, gr.imagedet, ma.txnombre AS txmarcax, txmodelo, txcatego, impdefco, impdefve ");
        sql.append(" FROM izumgrph gr, izummarc ma , izumphon ph ");
        sql.append(" WHERE gr.idmarcax = ma.idmarcax AND gr.idgrupox = ph.cdgrupox ");
        
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
		
    	select.add("idphonex");
		select.add("cdgrupox");
		select.add("txdescri");
		select.add("cdimeixx");
		select.add("impvenef");
		select.add("impventa");
		select.add("idmarcax");
		select.add("imagedet");
		select.add("txmarcax");
		select.add("txmodelo");
		select.add("txcatego");
		select.add("impdefco");
		select.add("impdefve");
		
        return select;        
    }
    
    
}
