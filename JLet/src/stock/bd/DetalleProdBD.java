package stock.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class DetalleProdBD extends UpdateQueryBD {

	String idemisor = null;
	String cdestado = null;
	String imeicode = null;
	String txmarcax = null;
	String txmodelo = null;
	String idcolorx = null;
	String pricechf = null;
	String priceusd = null;
	
	String pricecmp = null;
	String diviscmp = null;
	String prusdcmp = null;
	String pricevnt = null;
	String divisvnt = null;
	String prusdvnt = null;
	
	String txprovid = null;
	String txbuyerx = null;
	String txfundin = null;
	String withboxx = null;
	String withusbx = null;
	String idcatego = null;
	String porcmarg = null;
	String idlineax = null;
	String idclient = null;
	String codprodu = null;
	String tpclient = null;
	String mcactivo = "S";
	String tpproduc = null;
	String tipocone = null;
	String withheph = null;
	String withchar = null;
	String idproved = null;
	String oldcdest = null;
    
    public DetalleProdBD(){
    }
    
    public DetalleProdBD(ObjectIO bdata) throws Exception {

    	idemisor = bdata.getStringValue("idemisor");
    	cdestado = bdata.getStringValue("cdestado");
    	imeicode = bdata.getStringValue("imeicode");
    	txmarcax = bdata.getStringValue("txmarcax");
    	txmodelo = bdata.getStringValue("txmodelo");
    	idcolorx = bdata.getStringValue("idcolorx");
    	pricechf = bdata.getStringValue("pricechf");
    	priceusd = bdata.getStringValue("priceusd");
    	
    	pricecmp = bdata.getStringValue("pricecmp");
    	diviscmp = bdata.getStringValue("diviscmp");
    	prusdcmp = bdata.getStringValue("prusdcmp");
    	pricevnt = bdata.getStringValue("pricevnt");
    	divisvnt = bdata.getStringValue("divisvnt");
    	prusdvnt = bdata.getStringValue("prusdvnt");
    	
    	txprovid = bdata.getStringValue("txprovid");
    	txbuyerx = bdata.getStringValue("txbuyerx");
    	txfundin = bdata.getStringValue("txfundin");
    	withboxx = bdata.getStringValue("withboxx");
    	withusbx = bdata.getStringValue("withusbx");
    	idcatego = bdata.getStringValue("idcatego");
    	porcmarg = bdata.getStringValue("porcmarg");
    	idlineax = bdata.getStringValue("idlineax");
    	idclient = bdata.getStringValue("idclient");
    	idproved = bdata.getStringValue("idproved");
    	oldcdest = bdata.getStringValue("oldcdest");
    	codprodu = bdata.getStringValue("codprodu");
    	tpclient = bdata.getStringValue("tpclient");
    	tpproduc = bdata.getStringValue("tpproduc");
    	tipocone = bdata.getStringValue("tipocone");
    	
    	if(idclient == null || idclient.equals("null")){
    		idclient="0";
    	}
    	if(codprodu == null || codprodu.equals("null")){
    		codprodu="0";
    	}
    	
    	if(tpclient == null || tpclient.equals("null")){
    		tpclient="-0";
    	}
    	
    	if(pricecmp == null || pricecmp.equals("null") || pricecmp.equals("")){
    		pricecmp="0";
    	}
    	
    	if(pricevnt == null || pricevnt.equals("null") || pricevnt.equals("")){
    		pricevnt="0";
    	}
    	
    	if(prusdcmp == null || prusdcmp.equals("null") || prusdcmp.equals("")){
    		prusdcmp="0";
    	}
    	
    	if(prusdvnt == null || prusdvnt.equals("null") || prusdvnt.equals("")){
    		prusdvnt="0";
    	}
    	
    	if(porcmarg == null || porcmarg.equals("null") || porcmarg.equals("")){
    		porcmarg="0";
    	}
    	
    	
 
        
    }       
    
    public String getInsertStatement(){
               
        return null;
    }
    
    public String getDeleteStatement(){
  
    	StringBuffer sql = new StringBuffer();
        
	    sql.append(" DELETE FROM tradstoc ");
	    sql.append(" WHERE idstockx  = '"+idlineax+"'");
	        
	    System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
	        
	    return sql.toString();
    }

	@Override
	public String getUpdateStatement() {
		
		StringBuffer sql = new StringBuffer();
	        
	    sql.append(" UPDATE tradstoc ");
	    sql.append(" SET cdestado = '"+ cdestado +"',");
	    sql.append(" idclient = '"+ idclient +"',");
	    sql.append(" tpclient = '"+ tpclient +"'");
	    sql.append(" WHERE 1 = 1 ");
	    
	    if ((oldcdest != null) && (!oldcdest.equals(""))){
	    	sql.append(" AND cdestado = '"+ oldcdest +"' ");
	    }
	    
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
    	select.add("fhcompra");
    	select.add("idemisor");
    	select.add("cdestado");
    	select.add("tpclient");
    	select.add("idclient");
    	select.add("idfactur");
    	select.add("codprodu");
    	
      	select.add("imeicode");
    	select.add("txmarcax");
    	select.add("txmodelo");
    	select.add("pricechf");
    	select.add("priceusd");
    	select.add("pricecmp");
    	select.add("diviscmp");
    	
    	select.add("prusdcmp");
    	select.add("porcmarg");
    	select.add("pricevnt");
    	select.add("divisvnt");
    	select.add("prusdvnt");
    	select.add("txprovid");
    	select.add("txbuyerx");
    	
    	select.add("txfundin");
    	select.add("withboxx");
    	select.add("withusbx");
    	select.add("withheph");
    	select.add("withchar");
    	select.add("tipocone");
    	select.add("idcatego");
    	select.add("tpproduc");
    	select.add("filecrea");
    	select.add("cdfactur");
    	select.add("mcpagado");
    	select.add("imagenxx");
        return select; 
	}

	@Override
	public String getSelectStatment() {
		
		StringBuffer sql = new StringBuffer();
		
		String tipoprod = imeicode.substring(0,2);
		
		System.out.println("Tipo producto  "+tipoprod);
		
			sql.append(" SELECT DATE_FORMAT( st.fhcompra, '%d/%m/%Y' ), st.idemisor, st.cdestado, st.tpclient, st.idclient, st.idfactur, st.codprodu,st.imeicode, st.txmarcax, st.txmodelo, st.pricechf, st.priceusd,");
			sql.append(" st.pricecmp, st.diviscmp, st.prusdcmp, st.porcmarg, st.pricevnt, st.divisvnt, st.prusdvnt, st.txprovid, st.txbuyerx, st.txfundin, st.withboxx, st.withusbx,");
			sql.append(" st.withheph, st.withchar, st.tipocone, st.idcatego, st.tpproduc,fa.filecrea,fa.cdfactur, fa.mcpagado, ");
			
			if (tipoprod.equals("PI")){
				sql.append(" ph.imgdefau ");
			} else{
				sql.append(" ph.imagedet ");
			}
			
			sql.append(" FROM tradstoc st ");
			sql.append(" LEFT JOIN ");

			if (tipoprod.equals("PI")) {
				sql.append(" izumgrpi ph ");				
				sql.append(" ON ph.idpiezax = CONVERT(SUBSTRING(st.codprodu,3),UNSIGNED INTEGER)");
			} else {
				sql.append(" izumgrph ph ");
				sql.append(" ON ph.idgrupox = CONVERT(SUBSTRING(st.codprodu,3),UNSIGNED INTEGER)");
			}
			
			sql.append(" LEFT JOIN ");
			sql.append(" jlfactur fa ");
			sql.append(" ON st.idfactur = fa.idfactur ");
			
			sql.append(" WHERE st.idemisor='"+idemisor+"'");
			sql.append(" AND imeicode='"+imeicode+"'");
			//sql.append(" AND CONCAT('PH',ph.idgrupox) = st.codprodu");
			
		return sql.toString();
		
	}
    
    
}
