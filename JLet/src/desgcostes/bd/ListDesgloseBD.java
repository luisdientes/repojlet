package desgcostes.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class ListDesgloseBD extends UpdateQueryBD {
 
	String idemisor = null;
	String txpaisxx = null;
	String mostriva = null;
	String canalven = null;
	String fhdesdex = null;
	String fhhastax = null;
	
    public ListDesgloseBD(){
    }
    
    public ListDesgloseBD(ObjectIO bdata) throws Exception {
    	
    	idemisor = bdata.getStringValue("idemisor");
    	txpaisxx = bdata.getStringValue("txpaisxx");
    	canalven = bdata.getStringValue("canalven");
    	fhdesdex = bdata.getStringValue("fhdesdex");
    	fhhastax = bdata.getStringValue("fhhastax");
        
    }       
    
    public String getInsertStatement(){
        
       return null;
    }
    
    public String getDeleteStatement(){
  
        return null;
    }

	@Override
	public String getUpdateStatement() {
	
		return null;
		
	}



	@Override
	public String getSelectStatment() {
		
		

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT sq.idunicox, st.txmarcax, st.txmodelo,st.idcatego, sqca.desvalue, sqfa.desvalue, sqpa.desvalue, ROUND(SUM(catotal),2), ROUND(SUM(cptotal),2), ROUND(SUM(cttotal),2), ROUND(SUM(vatotal),2), ROUND(SUM(vptotal),2), ROUND(SUM(vttotal),2), ROUND(SUM(igtotal),2), ");
		sql.append(" ROUND(SUM(caivatotal),2), ROUND(SUM(cpivatotal),2), ROUND(SUM(ctivatotal),2), ROUND(SUM(vaivatotal),2), ROUND(SUM(vpivatotal),2), ROUND(SUM(vtivatotal),2), ROUND(SUM(igivatotal),2) ");
		
		sql.append(" FROM ");
		sql.append("(");
		sql.append("SELECT idunicox,  IFNULL(desvalue,0) AS catotal, 0 AS cptotal, 0 AS cttotal, 0 AS vatotal, 0 AS vptotal, 0 AS vttotal, 0 AS igtotal, 0 AS caivatotal, 0 AS cpivatotal, 0 AS ctivatotal, 0 AS vaivatotal, 0 AS vpivatotal, 0 AS vtivatotal, 0 AS igivatotal FROM dglscost WHERE idemisor='"+idemisor+"' AND codedesg='CATOTAL' ");
		sql.append(" UNION ");
		sql.append(" SELECT idunicox,  0 AS catotal, IFNULL(desvalue,0) AS cptotal, 0 AS cttotal, 0 AS vatotal, 0 AS vptotal, 0 AS vttotal, 0 AS igtotal, 0  AS caivatotal, 0 AS cpivatotal, 0 AS ctivatotal, 0 AS vaivatotal, 0 AS vpivatotal, 0 AS vtivatotal, 0 AS igivatotal FROM dglscost WHERE idemisor='"+idemisor+"' AND codedesg='CPTOTAL' ");
		sql.append(" UNION ");	 
		sql.append(" SELECT idunicox,  0 AS catotal, 0 AS cptotal, IFNULL(desvalue,0) AS cttotal, 0 AS vatotal, 0 AS vptotal, 0 AS vttotal, 0 AS igtotal, 0  AS caivatotal, 0 AS cpivatotal, 0 AS ctivatotal, 0 AS vaivatotal, 0 AS vpivatotal, 0 AS vtivatotal, 0 AS igivatotal FROM dglscost WHERE idemisor='"+idemisor+"' AND codedesg='CTTOTAL' ");
		sql.append(" UNION "); 
		sql.append(" SELECT idunicox,  0 AS catotal, 0 AS cptotal, 0 AS cttotal, IFNULL(desvalue,0) AS vatotal, 0 AS vptotal, 0 AS vttotal, 0 AS igtotal,0 AS caivatotal, 0 AS cpivatotal, 0 AS ctivatotal, 0 AS vaivatotal, 0 AS vpivatotal, 0 AS vtivatotal, 0 AS igivatotal FROM dglscost WHERE idemisor='"+idemisor+"' AND codedesg='VATOTAL' "); 
		sql.append(" UNION "); 
		sql.append(" SELECT idunicox,  0 AS catotal, 0 AS cptotal, 0 AS cttotal, 0 AS vatotal, IFNULL(desvalue,0) AS vptotal, 0 AS vttotal, 0 AS igtotal, 0 AS caivatotal, 0 AS cpivatotal, 0 AS ctivatotal, 0 AS vaivatotal, 0 AS vpivatotal, 0 AS vtivatotal, 0 AS igivatotal FROM dglscost WHERE idemisor='"+idemisor+"' AND codedesg='VPTOTAL' "); 
		sql.append(" UNION "); 
		sql.append(" SELECT idunicox,  0 AS catotal, 0 AS cptotal, 0 AS cttotal, 0 AS vatotal, 0 AS vptotal, IFNULL(desvalue,0) AS vttotal, 0 AS igtotal,0  AS caivatotal, 0 AS cpivatotal, 0 AS ctivatotal, 0 AS vaivatotal, 0 AS vpivatotal, 0 AS vtivatotal, 0 AS igivatotal FROM dglscost WHERE idemisor='"+idemisor+"' AND codedesg='VTTOTAL' "); 
		sql.append(" UNION ");
		sql.append(" SELECT idunicox,  0 AS catotal, 0 AS cptotal, 0 AS cttotal, 0 AS vatotal, 0 AS vptotal, 0 AS vttotal, IFNULL(desvalue,0) AS igtotal,0 AS caivatotal, 0 AS cpivatotal, 0 AS ctivatotal, 0 AS vaivatotal, 0 AS vpivatotal, 0 AS vtivatotal, 0 AS igivatotal FROM dglscost WHERE idemisor='"+idemisor+"' AND codedesg='IGTOTAL' ");
		
		sql.append(" UNION ");
		/*CON IVA*/
		sql.append(" SELECT idunicox,  0 AS catotal, 0 AS cptotal, 0 AS cttotal, 0 AS vatotal, 0 AS vptotal, 0 AS vttotal, 0 AS igtotal,  IFNULL(desvalue,0) AS caivatotal, 0 AS cpivatotal, 0 AS ctivatotal, 0 AS vaivatotal, 0 AS vpivatotal, 0 AS vtivatotal, 0 AS igivatotal FROM dglscost WHERE idemisor='"+idemisor+"' AND codedesg='CAIVATOTAL' ");
		sql.append(" UNION ");
		sql.append(" SELECT idunicox,  0 AS catotal, 0 AS cptotal, 0 AS cttotal, 0 AS vatotal, 0 AS vptotal, 0 AS vttotal, 0 AS igtotal,  0 AS caivatotal, IFNULL(desvalue,0) AS cpivatotal, 0 AS ctivatotal, 0 AS vaivatotal, 0 AS vpivatotal, 0 AS vtivatotal, 0 AS igivatotal FROM dglscost WHERE idemisor='"+idemisor+"' AND codedesg='CPIVATOTAL' ");
		sql.append(" UNION ");	 
		sql.append(" SELECT idunicox,  0 AS catotal, 0 AS cptotal, 0 AS cttotal, 0 AS vatotal, 0 AS vptotal, 0 AS vttotal, 0 AS igtotal,  0 AS caivatotal, 0 AS cpivatotal, IFNULL(desvalue,0) AS ctivatotal, 0 AS vaivatotal, 0 AS vpivatotal, 0 AS vtivatotal, 0 AS igivatotal FROM dglscost WHERE idemisor='"+idemisor+"' AND codedesg='CTIVATOTAL' ");
		sql.append(" UNION "); 
		sql.append(" SELECT idunicox,  0 AS catotal, 0 AS cptotal, 0 AS cttotal, 0 AS vatotal, 0 AS vptotal, 0 AS vttotal, 0 AS igtotal,  0 AS caivatotal, 0 AS cpivatotal, 0 AS ctivatotal, IFNULL(desvalue,0) AS vaivatotal, 0 AS vpivatotal, 0 AS vtivatotal, 0 AS igivatotal FROM dglscost WHERE idemisor='"+idemisor+"' AND codedesg='VAIVATOTAL' "); 
		sql.append(" UNION "); 
		sql.append(" SELECT idunicox,  0 AS catotal, 0 AS cptotal, 0 AS cttotal, 0 AS vatotal, 0 AS vptotal, 0 AS vttotal, 0 AS igtotal,  0 AS caivatotal, 0 AS cpivatotal, 0 AS ctivatotal, 0 AS vaivatotal, IFNULL(desvalue,0) AS vpivatotal, 0 AS vtivatotal, 0 AS igivatotal FROM dglscost WHERE idemisor='"+idemisor+"' AND codedesg='VPIVATOTAL' "); 
		sql.append(" UNION "); 
		sql.append(" SELECT idunicox,  0 AS catotal, 0 AS cptotal, 0 AS cttotal, 0 AS vatotal, 0 AS vptotal, 0 AS vttotal, 0 AS igtotal,  0 AS caivatotal, 0 AS cpivatotal, 0 AS ctivatotal, 0 AS vaivatotal, 0 AS vpivatotal, IFNULL(desvalue,0) AS vtivatotal, 0 AS igivatotal FROM dglscost WHERE idemisor='"+idemisor+"' AND codedesg='VTIVATOTAL' "); 
		sql.append(" UNION ");
		sql.append(" SELECT idunicox,  0 AS catotal, 0 AS cptotal, 0 AS cttotal, 0 AS vatotal, 0 AS vptotal, 0 AS vttotal, 0 AS igtotal,  0 AS caivatotal, 0 AS cpivatotal, 0 AS ctivatotal, 0 AS vaivatotal, 0 AS vpivatotal, 0 AS vtivatotal, IFNULL(desvalue,0) AS igivatotal FROM dglscost WHERE idemisor='"+idemisor+"' AND codedesg='IGIVATOTAL' ");
		
		
		/*CON IVA*/
		
		sql.append(" ) sq, ");
		sql.append(" (SELECT desvalue, idunicox FROM dglsdeta WHERE idemisor= '"+idemisor+"' AND codedeta='CANALVEN') sqca, ");
		sql.append(" (SELECT desvalue, idunicox FROM dglsdeta WHERE idemisor= '"+idemisor+"' AND codedeta='FHFACTUR') sqfa, ");
		sql.append(" (SELECT desvalue, idunicox FROM dglsdeta WHERE idemisor= '"+idemisor+"' AND codedeta='CDPAISXX') sqpa,  ");
		sql.append(" (SELECT imeicode,txmarcax, txmodelo,idcatego FROM tradstoc WHERE idemisor= '"+idemisor+"') st  ");
		
		sql.append(" WHERE sq.idunicox = sqca.idunicox ");
		sql.append(" AND sq.idunicox = sqfa.idunicox");
		sql.append(" AND sq.idunicox = sqpa.idunicox");
		sql.append(" AND sq.idunicox = st.imeicode ");
		
		if(canalven!= null && !canalven.equals("")){
			sql.append(" AND sqca.desvalue LIKE '%"+canalven+"%'");
		}
		
		if(fhdesdex!= null && !fhdesdex.equals("")){
			sql.append(" AND STR_TO_DATE(sqfa.desvalue, '%d/%m/%Y') >=STR_TO_DATE('"+fhdesdex+"','%d/%m/%Y')");
		}
		
		if(fhhastax!= null && !fhhastax.equals("")){
			sql.append(" AND STR_TO_DATE(sqfa.desvalue, '%d/%m/%Y') <=STR_TO_DATE('"+fhhastax+"','%d/%m/%Y')");
		}
		
		
		if(txpaisxx!= null && txpaisxx.equals("OT")){
			sql.append(" AND sqpa.desvalue NOT IN ('ES','DE','GB','IT','FR','CH')");
		}else{
			if(txpaisxx!= null && !txpaisxx.equals("")){
				sql.append(" AND sqpa.desvalue='"+txpaisxx+"'");
			}
		}
		
		sql.append(" GROUP BY sq.idunicox, sqca.desvalue, sqfa.desvalue, sqpa.desvalue ");
		sql.append("  ORDER BY STR_TO_DATE(sqfa.desvalue, '%d/%m/%Y') DESC, sq.idunicox"); 

		
		
	
		System.out.println(sql.toString());
	    return sql.toString();
	}
	
	@Override
	public Collection defineSelect() {
	
		Vector<String> select = new Vector<String>();
		select.add("idunicox");	
		select.add("txmarcax");
		select.add("txmodelo");
		select.add("idcatego");
		select.add("canalven");		
		select.add("fhventax");		
		select.add("txpaisxx");	
		select.add("catotalx");		
		select.add("cptotalx");
		select.add("cttotalx");	
		select.add("vatotalx");	
		select.add("vptotalx");
		select.add("vttotalx");	
		select.add("igtotalx");
		
		select.add("caivatot");		
		select.add("cpivatot");
		select.add("ctivatot");	
		select.add("vaivatot");	
		select.add("vpivatot");
		select.add("vtivatot");	
		select.add("igivatot");
		

		
        return select; 
	}
    
    
}

