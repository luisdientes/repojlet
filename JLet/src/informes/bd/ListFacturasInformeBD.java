package informes.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListFacturasInformeBD extends SelectQueryBD {

    String idemisor = null;
    String aniofact = null;
    String fhdesdex = null;
    String fhhastax = null;
    String orderbyx = null;
        
    public ListFacturasInformeBD(){
    }
    
    public ListFacturasInformeBD(ObjectIO bdata) throws Exception {

    	idemisor = bdata.getStringValue("idemisor");
    	aniofact = bdata.getStringValue("aniofact");
    	fhdesdex = bdata.getStringValue("fhdesdex");
    	fhhastax = bdata.getStringValue("fhhastax");
    	orderbyx = bdata.getStringValue("orderbyx");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT fr.idfactur, fr.idemisor, fr.idclient, fr.tpclient, cl.cdintern, fr.cdfactur, cdcronol, DATE_FORMAT(fr.fhfactur, '%d/%m/%Y'), fr.tipofact,  ");
        sql.append(" fr.aniofact, fr.cddivisa, fr.baseimpo, fr.imptaxes, fr.imptotal, fr.filecrea, ");
        sql.append(" fr.cdestado,fr.mcpagado, fr.timecrea,fr.idtmpfra, cl.rzsocial, tp.txnombre, tp.txdescri, tp.preffact, tp.porctaxe, fr.formpago ");
        sql.append(" FROM jlfactur fr, jlclierc cl, jltpfac tp ");
        sql.append(" WHERE fr.idclient = cl.idclient ");
        sql.append(" AND fr.tpclient = cl.tpclient ");
        sql.append(" AND fr.tipofact = tp.tipofact ");
        sql.append(" AND fr.idemisor = tp.idemisor ");

        sql.append(" AND fr.idemisor = "+ idemisor);
        
        if ((aniofact != null) && (!aniofact.equals(""))){
        	sql.append(" AND fr.aniofact = "+ aniofact);
        }
        
        if (fhdesdex != null && !fhdesdex.equals("") && !fhdesdex.equals("0000-00-00")){
        	sql.append(" AND fr.fhfactur >= '"+fhdesdex+"'");
        }
        
        if (fhhastax != null && !fhhastax.equals("") && !fhhastax.equals("0000-00-00")){
        	sql.append(" AND fr.fhfactur <= '"+fhhastax+"'");
        }
        
        sql.append(" ORDER BY fr.fhfactur ASC ");
        
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	
    	
    	Vector<String> select = new Vector<String>();
		select.add("idfactur");
		select.add("idemisor");
		select.add("idclient");
		select.add("tpclient");
		select.add("cdintern");
		select.add("cdfactur");
		select.add("cdcronol");
		select.add("fhfactur");
		select.add("tipofact");
		select.add("aniofact");
		select.add("cddivisa");
		select.add("baseimpo");
		select.add("imptaxes");
		select.add("imptotal");
		select.add("filecrea");
		select.add("cdestado");
		select.add("mcpagado");
		select.add("timecrea");
		select.add("idtmpfra");
		select.add("rzsocial");
		select.add("txtpfact");
		select.add("txdesctf");
		select.add("preffact");
		select.add("porctaxe");
		select.add("formpago");
        return select;        
    }
    
    
}
