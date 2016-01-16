package stock.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class ListTmpStockBD extends UpdateQueryBD {

	String idemisor = "";
	String cdimeixx = "";
	String pricecmp = "";
	String divisaxx = "";
	String divisvnt = "";
	String pricevnt = "";
	String proveedo = "";
	String clasexxx = "";
	String cargador = "";
	String enchufex = "";
	String usbxxxxx = "";
	String cajaxxxx = "";
	String cascosxx = "";
	String tpproduc = "";
	String codprodu = "";
	String mcestado = "";
	String fhcompra = "";
	String idgralta = "";
	String idcolorx = "";
    
    public ListTmpStockBD(){
    }
    
    public ListTmpStockBD(ObjectIO bdata) throws Exception {
    	
    	idemisor = bdata.getStringValue("idemisor");
    	cdimeixx = bdata.getStringValue("cdimeixx");
    	pricecmp = bdata.getStringValue("pricecmp");
    	divisaxx = bdata.getStringValue("divisaxx");
    	proveedo = bdata.getStringValue("proveedo");
    	clasexxx = bdata.getStringValue("clasexxx");
    	cargador = bdata.getStringValue("cargador");
    	enchufex = bdata.getStringValue("enchufex");
    	usbxxxxx = bdata.getStringValue("usbxxxxx");
    	cajaxxxx = bdata.getStringValue("cajaxxxx");
    	cascosxx = bdata.getStringValue("cascosxx");
    	tpproduc = bdata.getStringValue("tpproduc");
    	codprodu = bdata.getStringValue("codprodu");
    	mcestado = bdata.getStringValue("cdestado");
    	fhcompra = bdata.getStringValue("fechacmp");
    	idgralta = bdata.getStringValue("idgralta");
    	divisvnt = bdata.getStringValue("divisvnt");
    	pricevnt = bdata.getStringValue("pricevnt");
    	idcolorx = bdata.getStringValue("idcolorx");
    }
    
    public String getSelectStatment(){
    	
    	StringBuffer sql = new StringBuffer();
        sql.append(" SELECT cdimeixx, idcatego, diviscmp, pricecmp,divisvnt,pricevnt, fhcompra, mcestado,codprodu,txprovid,  ");
        sql.append("  withchar, tipocone, withusbx,withboxx,withheph,tpproduc,idcolorx ");
        sql.append("  FROM tmpstock" );
        sql.append("  WHERE idemisor= "+idemisor);
        
        if(mcestado!=null){
        		sql.append(" AND mcestado = '"+ mcestado +"'");
        }
        
        if(codprodu!=null){
    		sql.append(" AND codprodu = '"+ codprodu +"'");
    }
 
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
    	select.add("cdimeixx");
    	select.add("idcatego");
    	select.add("diviscmp");
    	select.add("pricecmp");
    	select.add("divisvnt");
    	select.add("pricevnt");
    	select.add("fhcompra");
    	select.add("mcestado");
    	select.add("codprodu");
    	select.add("txprovid");
    	
    	select.add("withchar");
    	select.add("tipocone");
    	select.add("withusbx");
    	select.add("withboxx");
    	select.add("withheph");
    	select.add("tpproduc");
    	select.add("idcolorx");
        return select;        
    }

	@Override
	public String getInsertStatement() {
		StringBuffer sql = new StringBuffer();
		
		sql.append("INSERT INTO tmpstock ( idgralta, fhcompra,idemisor,cdimeixx,pricecmp,diviscmp,pricevnt,divisvnt,txprovid,idcatego,withchar,");
		sql.append(" tipocone, withusbx,withboxx,withheph,tpproduc,codprodu,idcolorx,mcestado)");
		sql.append(" VALUES ");
		sql.append("('"+idgralta+"','"+fhcompra+"','"+idemisor+"','"+cdimeixx+"','"+pricecmp+"','"+divisaxx+"','"+pricevnt+"','"+divisvnt+"','"+proveedo+"',");
		sql.append("'"+clasexxx+"','"+cargador+"','"+enchufex+"','"+usbxxxxx+"','"+cajaxxxx+"','"+cascosxx+"','"+tpproduc+"','"+codprodu+"','"+idcolorx+"','P')");
        
		
		System.out.println(sql.toString());
		return sql.toString();
	}

	@Override
	public String getUpdateStatement() {
			
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE tmpstock ");
		sql.append(" SET mcestado='V'"); 
		sql.append("  WHERE idemisor= "+idemisor);
		
	    if(mcestado!=null){
	        sql.append(" AND mcestado = '"+ mcestado +"'");
	        
	       }
	    if(codprodu!=null){
	    	sql.append(" AND codprodu = '"+ codprodu +"'");
	    }
	
	    System.out.println(sql.toString());
	    return sql.toString();
	}

	@Override
	public String getDeleteStatement() {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" DELETE FROM tmpstock ");
		sql.append(" WHERE idemisor= "+idemisor);
		sql.append(" AND cdimeixx= '"+cdimeixx+"'");

		System.out.println(sql.toString());
	    return sql.toString();
	}
    
    
}
