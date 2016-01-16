package informes.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListFacturasInformeClienteBD extends SelectQueryBD {

    String idemisor = null;
    String aniofact = null;
    String fhdesdex = null;
    String fhhastax = null;
    String mcpagado = null;
    String cdintern = null;
    String mcagrcli = null;
    String orderbyx = null;
        
    public ListFacturasInformeClienteBD(){
    }
    
    public ListFacturasInformeClienteBD(ObjectIO bdata) throws Exception {

    	idemisor = bdata.getStringValue("idemisor");
    	aniofact = bdata.getStringValue("aniofact");
    	fhdesdex = bdata.getStringValue("fhdesdex");
    	fhhastax = bdata.getStringValue("fhhastax");
    	mcpagado = bdata.getStringValue("mcpagado");
    	cdintern = bdata.getStringValue("cdintern");	//CDINTERN Cliente
    	mcagrcli = bdata.getStringValue("mcagrcli");
    	orderbyx = bdata.getStringValue("orderbyx");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        if ((mcagrcli != null) && (mcagrcli.equals("S"))) {
        	sql.append(" SELECT sq.rzsocial, sq.cdintern,sq.idclient,sq.tpclient, sq.idfiscal, SUM(sq.baseimpo), SUM(sq.imptaxes), SUM(sq.porcrete) , SUM(sq.imptotal), SUM(sq.imptotal - sq.totalpag) AS saldopen ");
        	sql.append(" FROM ( ");
        }
        
	        sql.append(" SELECT fr.idfactur, fr.mcpagado, fr.cdfactur, fr.tipofact, fr.cddivisa, ");
	        sql.append(" fr.baseimpo, fr.imptaxes, fr.porcrete, fr.imptotal, fr.totalpag, fr.filecrea, fr.formpago, ");
	        sql.append(" fr.catefact, cl.rzsocial, cl.cdintern,cl.idclient,cl.tpclient, cl.idfiscal, cl.txmailxx, cl.tfnofijo, cl.tfnomovi, ");
	        sql.append(" tp.txnombre AS txtpfact, tp.preffact, tp.porctaxe ");
	        sql.append(" FROM jlfactur fr, jlclierc cl, jltpfac tp ");
	        sql.append(" WHERE fr.idclient = cl.idclient ");
	        sql.append(" AND fr.tpclient = cl.tpclient ");
	        sql.append(" AND fr.tipofact = tp.tipofact ");
	        sql.append(" AND fr.idemisor = tp.idemisor ");
	        sql.append(" AND tp.isalbara = 'N' ");
	        
	
	        sql.append(" AND fr.idemisor = "+ idemisor);
	        
	        if ((aniofact != null) && (!aniofact.equals(""))){
	        	sql.append(" AND fr.aniofact = "+ aniofact);
	        }
	        
	        if (fhdesdex != null && !fhdesdex.equals("") && !fhdesdex.equals("0000-00-00")){
	        	sql.append(" AND fr.fhfactur >= '"+ fhdesdex +"'");
	        }
	        
	        if (fhhastax != null && !fhhastax.equals("") && !fhdesdex.equals("0000-00-00")){
	        	sql.append(" AND fr.fhfactur <= '"+ fhhastax +"'");
	        }
	        
	        if (mcpagado != null && !mcpagado.equals("")){
	        	if (mcpagado != null && !mcpagado.equals("N")){
	        		sql.append(" AND fr.mcpagado IN  ('N','R') ");
	        	} else {
	        		sql.append(" AND fr.mcpagado = '"+ mcpagado +"' ");
	        	}
	        }
	        
	        if (cdintern != null && !cdintern.equals("")){
	        	sql.append(" AND cl.cdintern = '"+ cdintern +"'");
	        }
        
	        if ((mcagrcli == null) || (mcagrcli.equals("N"))) {
	        	sql.append(" ORDER BY fr.fhfactur ASC, fr.cdfactur ASC ");
	        }
	    
        if ((mcagrcli != null) && (mcagrcli.equals("S"))) {
        	sql.append(" ) sq");
        	sql.append(" GROUP BY sq.rzsocial, sq.cdintern, sq.idfiscal ");
        	sql.append(" HAVING SUM(sq.imptotal) > 0 ");
        	sql.append(" ORDER BY SUM(sq.imptotal) DESC ");
        }
        
        return sql.toString();
        
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
    	
    	if ((mcagrcli != null) && (mcagrcli.equals("S"))) {
    		select.add("rzsocial");
			select.add("cdintern");
			select.add("idclient");
			select.add("tpclient");
			select.add("idfiscal");
			select.add("baseimpo");
			select.add("imptaxes");
			select.add("porcrete");
			select.add("imptotal");
			select.add("saldopen");
    	} else {
			select.add("idfactur");
			select.add("tpclient");
			select.add("mcpagado");
			select.add("cdfactur");
			select.add("tipofact");
			select.add("cddivisa");
			select.add("baseimpo");
			select.add("imptaxes");
			select.add("porcrete");
			select.add("imptotal");
			select.add("totalpag");
			select.add("filecrea");
			select.add("formpago");
			select.add("catefact");
			select.add("rzsocial");
			select.add("cdintern");
			select.add("idclient");
			select.add("tpclient");
			select.add("idfiscal");
			select.add("txmailxx");
			select.add("tfnofijo");
			select.add("tfnomovi");
			select.add("txtpfact");
			select.add("preffact");
			select.add("porctaxe");
    	}
			
			
		return select;        
    }
    
}
