package upgrade.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdUpgradeBD extends UpdateQueryBD {
	
	

	String idemisor = null;
	String codprodu = null;
	String imeicode = null;
	String horacomi = null;
	String costpiez = null;
	String costtime = null;
	String claseant = null;
	String claseact = null;
	String costsoft = null;
	String costlimp = null;
	String manoobra = null;
	String hardrese = null;
	
    
    public UpdUpgradeBD(){
    }
    
    public UpdUpgradeBD(ObjectIO bdata) throws Exception {

    	idemisor = bdata.getStringValue("idemisor");
    	codprodu = bdata.getStringValue("codprodu");
    	imeicode = bdata.getStringValue("imeicode");
    	horacomi = bdata.getStringValue("horacomi");
    	costpiez = bdata.getStringValue("costpiez");
    	costtime = bdata.getStringValue("costtime");
    	claseant = bdata.getStringValue("claseant");
    	claseact = bdata.getStringValue("claseact");
    	costsoft = bdata.getStringValue("costsoft");
    	costlimp = bdata.getStringValue("costlimp");
    	manoobra = bdata.getStringValue("manoobra");
    	hardrese = bdata.getStringValue("hardrese");
   
    }       
    
    public String getInsertStatement(){
        
        StringBuffer sql = new StringBuffer();

        
        sql.append(" INSERT INTO izumupgr (idemisor,imeicode, codprodu,claseant,costtime,fhrepara) ");
        sql.append(" VALUES ("+ idemisor +",'"+imeicode+"','"+codprodu+"','"+claseant+"','0',CURRENT_DATE)");
        

        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    public String getDeleteStatement(){
  
    	return null;
    }

	@Override
	public String getUpdateStatement() {
		
		StringBuffer sql = new StringBuffer();
	        
	    sql.append(" UPDATE izumupgr ");
	    sql.append(" SET costpiez = '"+ costpiez +"',");
	    sql.append(" costtime = '"+ costtime +"',");
	    sql.append(" costlimp = '"+ costlimp +"',");
	    sql.append(" costsoft = '"+ costsoft +"',");
	    sql.append(" claseact = '"+ claseact +"',");
	    sql.append(" hardrese = '"+ hardrese +"',");
	    sql.append(" manoobra = '"+ manoobra +"'");
	    sql.append(" WHERE 1 = 1 ");
	    
	    if ((idemisor != null) && (!idemisor.equals(""))){
	    	sql.append(" AND idemisor = '"+ idemisor +"' ");
	    }
	    
	    if ((imeicode != null) && (!imeicode.equals(""))){
	    	sql.append(" AND imeicode = '"+ imeicode +"' ");
	    }
	        
	    //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
	       
	    System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
	        
	    return sql.toString();
		
	}

	@Override
	public Collection defineSelect() {
		
		Vector<String> select = new Vector<String>();
    	select.add("idgrupox");
    	select.add("idemisor");
    	select.add("codprodu");
    	select.add("imeicode");
    	select.add("idcatego");
    	select.add("tpproduc");
    	select.add("pricevnt");
    	select.add("withusbx");
    	select.add("withboxx");
    	select.add("txmarcax");
    	select.add("txmodelo");
    	select.add("imagewpt");
    
        return select;        
	}

	@Override
	public String getSelectStatment() {
		
		StringBuffer sql = new StringBuffer();
		
			sql.append("  SELECT idgrupox, st.idemisor,st.codprodu, st.imeicode,idcatego,st.tpproduc,st.pricevnt,withusbx, withboxx , ma.txnombre AS txmarcax,  ");
	        sql.append(" gr.txmodelo, imagedet as imagewpt ");
	        sql.append(" FROM izumgrph gr, izummarc ma, tradstoc st");
	        sql.append(" WHERE gr.idmarcax = ma.idmarcax ");
	        sql.append(" AND gr.idgrupox = CONVERT(SUBSTRING(st.codprodu,3),UNSIGNED INTEGER)");
	        sql.append(" AND st.imeicode='"+imeicode+"'");
	        sql.append(" AND st.idemisor = '"+ idemisor +"'");
	        sql.append(" AND (cdestado = 'STOCK' OR cdestado = 'RECDEPOS')");
	       
	     return sql.toString();	  
	}
    
    
}
