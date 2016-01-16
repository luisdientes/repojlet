package stock.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListUpgradeBD extends SelectQueryBD {

    String idemisor = null;
    String imeicode = null;
   
    
    public ListUpgradeBD(){
    }
    
    public ListUpgradeBD(ObjectIO bdata) throws Exception {
    	
    	idemisor = bdata.getStringValue("idemisor");
    	imeicode = bdata.getStringValue("imeicode"); 
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();
        
        sql.append(" SELECT idemisor, codprodu, imeicode, costpiez, costlimp, costsoft, manoobra, hardrese, costtime, claseant, claseact,st.txmarcax, st.txmodelo,st.txprovid, st.txbuyerx, st.txfundin, st.withboxx, st.withusbx,");  
        sql.append(" FROM izumupgr up, tradstoc tr "); 
        sql.append(" WHERE  up.idemisor ="+idemisor);
        
        if ((imeicode != null) && (!imeicode.equals(""))){
        	 sql.append(" up.imeicode = tr.imeicode ");
	    	 sql.append(" AND imeicode = '"+ imeicode +"' ");
	    }
        
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
    	select.add("idemisor");
    	select.add("codprodu");
    	select.add("imeicode");
    	select.add("costpiez");
    	select.add("costlimp");
    	select.add("costsoft");
    	select.add("manoobra");
    	select.add("hardrese");
    	select.add("costtime");
    	select.add("claseant");
    	select.add("claseact");
    	select.add("txmarcax");
    	select.add("txmodelo");
    	select.add("withboxx");
    	select.add("withusbx");
    	select.add("withheph");
    	select.add("withchar");
    
        return select;        
    }
    
    
}
