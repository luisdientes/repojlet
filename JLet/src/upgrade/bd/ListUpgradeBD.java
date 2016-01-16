package upgrade.bd;

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
         
        sql.append(" SELECT up.idemisor, up.imeicode,DATE_FORMAT(up.fhrepara, '%d/%m/%Y'), up.costpiez, up.costlimp, up.costsoft, up.manoobra, up.hardrese, up.costtime, up.claseant, up.claseact,st.txmarcax, st.txmodelo,st.codprodu, st.withboxx, st.withusbx,st.withheph,st.withchar,st.withadap, ph.imagedet");  
        sql.append(" FROM izumupgr up, tradstoc st, izumgrph ph "); 
        sql.append(" WHERE  up.idemisor = "+idemisor);
        sql.append(" AND  up.imeicode = st.imeicode ");
        sql.append(" AND CONCAT('PH',ph.idgrupox) = up.codprodu");
        
        if ((imeicode != null) && (!imeicode.equals(""))){
	    	 sql.append(" AND up.imeicode = '"+ imeicode +"' ");
	    }
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
    	select.add("idemisor");
    	select.add("imeicode");
    	select.add("fhrepara");
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
    	select.add("codprodu");
    	select.add("withboxx");
    	select.add("withusbx");
    	select.add("withheph");
    	select.add("withchar");
    	select.add("withadap");
    	select.add("imagenxx");
    
        return select;        
    }
    
    
}
