package factura.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListFacturasBD extends SelectQueryBD {

    String idemisor = null;
    String tpclient = null;
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
    String mcpagado = null;
    String orderbyx = null;
    String conitbis = null;
    String intpfact = null;
    String idfactur = null;
 
        
    public ListFacturasBD(){
    }
    
    public ListFacturasBD(ObjectIO bdata) throws Exception {

    	idemisor = bdata.getStringValue("idemisor");
    	tpclient = bdata.getStringValue("tpclient");
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
    	mcpagado = bdata.getStringValue("mcpagado");
    	orderbyx = bdata.getStringValue("orderbyx");
    	aniofact = bdata.getStringValue("aniofact");
    	conitbis = bdata.getStringValue("conitbis");
    	intpfact = bdata.getStringValue("intpfact");
    	idfactur = bdata.getStringValue("idfactur");
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT fr.idfactur, fr.idemisor, fr.idclient, fr.tpclient, cl.cdintern, fr.cdfactur,cdcronol, DATE_FORMAT(fr.fhfactur, '%d/%m/%Y'), fr.tipofact,  ");
        sql.append(" fr.aniofact, fr.cddivisa, fr.baseimpo, fr.imptaxes, fr.imptotal, fr.filecrea, ");
        sql.append(" fr.cdestado,fr.mcpagado,fr.totalpag, fr.timecrea,fr.idtmpfra, cl.rzsocial, tp.txnombre, tp.txdescri, tp.preffact,tp.porctaxe,tp.admdevol,fr.formpago ");
        sql.append(" FROM jlfactur fr, jlclierc cl, jltpfac tp ");
        sql.append(" WHERE fr.idclient = cl.idclient ");
        sql.append(" AND fr.tpclient = cl.tpclient ");
        sql.append(" AND fr.tipofact = tp.tipofact AND fr.idemisor = tp.idemisor ");
       // sql.append(" AND fr.idemisor = cl.idemisor ");
        sql.append(" AND fr.idemisor = "+ idemisor);
        
        if ((idfactur != null) && (!idfactur.equals(""))){
        	sql.append(" AND fr.idfactur = "+ idfactur);
        }
        
        if ((aniofact != null) && (!aniofact.equals(""))){
        	sql.append(" AND fr.aniofact = "+ aniofact);
        }
        
        if ((conitbis != null) && (conitbis.equals("S"))){
        	sql.append(" AND fr.tipofact IN("+intpfact+")");
        }
        
        if ((isalbara != null) && (isalbara.equals("S"))){
        	sql.append(" AND tp.isalbara = 'S'");
        } else {
        	sql.append(" AND tp.isalbara = 'N'");
        }
        if (fhdesdex != null && !fhdesdex.equals("") && !fhdesdex.equals("0000-00-00")){
        	sql.append(" AND fr.fhfactur >= '"+fhdesdex+"'");
        }
        
        if (fhhastax != null && !fhhastax.equals("") && !fhhastax.equals("0000-00-00")){
        	sql.append(" AND fr.fhfactur <= '"+fhhastax+"'");
        }
        
        if (predesde != null && !predesde.equals("")){
        	sql.append(" AND fr.imptotal >= '"+predesde+"'");
        }
        
        if (prehasta != null && !prehasta.equals("")){
        	sql.append(" AND fr.imptotal <= '"+prehasta+"'");
        }
        
        if (tpclient != null && !tpclient.equals("")){
        	sql.append(" AND cl.tpclient = '"+tpclient+"'");
        }
        
        if (idclient != null && !idclient.equals("")){
        	sql.append(" AND cl.idclient = '"+idclient+"'");
        }
        
        if (mcpagado != null && !mcpagado.equals("")){
        	if (mcpagado.equals("N")){
        		sql.append(" AND fr.mcpagado IN ('N','R')");
        	}else{
        		sql.append(" AND fr.mcpagado = '"+mcpagado+"'");
        	}

        }
        
        
        if ((conitbis != null) && (conitbis.equals("S"))){
        		sql.append(" ORDER BY fr.cdcronol ");
        }else{
        	if ((orderbyx != null) && (!orderbyx.equals("")) && (orderbyx.equals("fhfactur"))){
            	sql.append(" ORDER BY fr.fhfactur DESC ");
            } else {
            	sql.append(" ORDER BY fr.tipofact, fr.fhfactur DESC, fr.cdfactur DESC ");
            }
        	
        }
        
        
        
       
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
		select.add("totalpag");
		select.add("timecrea");
		select.add("idtmpfra");
		select.add("rzsocial");
		select.add("txtpfact");
		select.add("txdesctf");
		select.add("preffact");
		select.add("porctaxe");
		select.add("admdevol");
		select.add("formpago");
        return select;        
    }
    
    
}
