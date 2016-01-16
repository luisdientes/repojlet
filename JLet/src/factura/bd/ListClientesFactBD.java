package factura.bd;

import java.util.Collection;
import java.util.Vector;
import java.util.logging.Logger;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListClientesFactBD extends SelectQueryBD {

    String idemisor = null;
    String idclient = null;
    String aniofact = null;
    String fhfechax = null;
    String tipofact = null;
    String cdestado = null;
    String isalbara = null;
        
    public ListClientesFactBD(){
    }
    
    public ListClientesFactBD(ObjectIO bdata) throws Exception {

    	idemisor = bdata.getStringValue("idemisor");
    	idclient = bdata.getStringValue("idclient");
    	aniofact = bdata.getStringValue("aniofact");
    	fhfechax = bdata.getStringValue("fhfechax");
    	tipofact = bdata.getStringValue("tipofact");
    	cdestado = bdata.getStringValue("cdestado");
    	isalbara = bdata.getStringValue("isalbara");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT DISTINCT fr.idclient, fr.tpclient, cl.cdintern, cl.rzsocial ");
        sql.append(" FROM jlfactur fr, jlclierc cl, jltpfac tp ");
        sql.append(" WHERE fr.idclient = cl.idclient ");
        sql.append(" AND fr.tpclient = cl.tpclient ");
        sql.append(" AND fr.tipofact = tp.tipofact AND fr.idemisor = tp.idemisor ");
        sql.append(" AND fr.idemisor = cl.idemisor ");
        sql.append(" AND cl.idemisor = "+ idemisor);
        
        if (aniofact != null && !aniofact.equals("")){
        	sql.append(" AND fr.aniofact = "+ aniofact);
        }
        
        if ((isalbara != null) && (isalbara.equals("S"))){
        	sql.append(" AND tp.isalbara = 'S'");
        } else {
        	sql.append(" AND tp.isalbara = 'N'");
        }  
        
        sql.append(" ORDER BY fr.tipofact, fr.cdfactur DESC ");
        
       
        Logger.getLogger("BBDD").fine("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
		
		select.add("idclient");
		select.add("tpclient");
		select.add("cdintern");
		select.add("rzsocial");
        return select;        
    }
    
    
}
