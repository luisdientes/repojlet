package factura.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListAlbaranesWebBD extends SelectQueryBD {

    String idemisor = null;
    String idclient = null;
    String aniofact = null;
    String fhfechax = null;
    String tipofact = null;
    String cdestado = null;
    String isalbara = null;
    String fhdesdex = null;
    String fhhastax = null;
    String predesde = null;
    String prehasta = null;
    String tpclient = null;
        
    public ListAlbaranesWebBD(){
    }
    
    public ListAlbaranesWebBD(ObjectIO bdata) throws Exception {

    	idemisor = bdata.getStringValue("idemisor");
    	idclient = bdata.getStringValue("idclient");
    	aniofact = bdata.getStringValue("aniofact");
    	fhfechax = bdata.getStringValue("fhfechax");
    	tipofact = bdata.getStringValue("tipofact");
    	cdestado = bdata.getStringValue("cdestado");
    	isalbara = bdata.getStringValue("isalbara");
    	fhdesdex = bdata.getStringValue("fhdesdex");
    	fhhastax = bdata.getStringValue("fhhastax");
    	predesde = bdata.getStringValue("predesde");
    	prehasta = bdata.getStringValue("prehasta");
    	tpclient = bdata.getStringValue("tpclient");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();
        /*
        sql.append(" SELECT cdfactur ");        
        sql.append(" FROM jlfactur ");
        sql.append(" WHERE idemisor = "+ idemisor);
        sql.append(" AND tipofact = 0 ");
        sql.append(" ORDER BY cdfactur ");
       */
          
        
        sql.append(" SELECT fr.idfactur, fr.idemisor, fr.idclient, fr.tpclient, fr.cdfactur, DATE_FORMAT(fr.fhfactur, '%d/%m/%Y'),"); 
        sql.append(" fr.tipofact, fr.aniofact, fr.cddivisa, fr.baseimpo, fr.imptaxes, fr.imptotal, fr.filecrea,fr.informad,"); 
        sql.append(" fr.cdestado, fr.timecrea, tp.txnombre, tp.txdescri, tp.preffact,tm.idtmpfra, ");
        sql.append(" SUM(CASE tm.estaalba when 'P' THEN 1 ELSE 0 END) pendient");
     
        sql.append(" FROM jlfactur fr, jltpfac tp, tmpfactu tm");
        sql.append(" WHERE fr.tipofact = tp.tipofact");
        sql.append(" AND fr.idemisor = tp.idemisor");
        sql.append(" AND fr.idemisor = "+ idemisor);
        
        if (aniofact != null && !aniofact.equals("")){
        	sql.append(" AND fr.aniofact = "+ aniofact);
        }
        sql.append(" AND fr.idtmpfra = tm.idtmpfra");
        sql.append(" AND tm.cdestado = 'F'");
        sql.append(" and fr.idtmpfra >0");
        sql.append(" AND tp.isalbara = 'W'");
        
        if (fhdesdex != null && !fhdesdex.equals("")){
        	sql.append(" AND fr.fhfactur >= '"+fhdesdex+"'");
        }
        
        if (fhhastax != null && !fhhastax.equals("")){
        	sql.append(" AND fr.fhfactur <= '"+fhhastax+"'");
        }
        
        if (predesde != null && !predesde.equals("")){
        	sql.append(" AND fr.imptotal >= '"+predesde+"'");
        }
        
        if (prehasta != null && !prehasta.equals("")){
        	sql.append(" AND fr.imptotal <= '"+prehasta+"'");
        }
        
        if (idclient != null && !idclient.equals("")){
        	sql.append(" AND fr.idclient = '"+idclient+"'");
        }
        
        if (tpclient != null && !tpclient.equals("")){
        	sql.append(" AND fr.tpclient = '"+tpclient+"'");
        }
        
        sql.append(" GROUP BY idfactur ORDER BY fr.tipofact, fr.cdfactur DESC ");
        
        
        
        
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
		select.add("idfactur");
		select.add("idemisor");
		select.add("idclient");
		select.add("tpclient");
		select.add("cdfactur");
		select.add("fhfactur");
		select.add("tipofact");
		select.add("aniofact");
		select.add("cddivisa");
		select.add("baseimpo");
		select.add("imptaxes");
		select.add("imptotal");
		select.add("filecrea");
		select.add("informad");
		select.add("cdestado");
		select.add("timecrea");
		select.add("txtpfact");
		select.add("txdesctf");
		select.add("preffact");
		select.add("idtmpfra");
		select.add("pendient");
        return select;        
    }
    
    
}
