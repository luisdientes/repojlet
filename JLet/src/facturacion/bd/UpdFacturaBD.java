package facturacion.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdFacturaBD extends UpdateQueryBD {

	String idfactur = null;
	String idemisor = null;
	String idclient = null;
	String tpclient = null;
	String cdfactur = null;
	String fhfactur = null;
	String tipofact = null;
	String aniofact = null;
	String cddivisa = null;
	String baseimpo = null;
	String imptaxes = null;
	String impreten = null;
	String imptotal = null;
	String filecrea = null;
	String cdestado = null;
	String idtmpfra = null;
	String formpago = null;
	String condpago = null;
	String catefact = null;
	String informda = null;
	String factasoc = null;
	String codvende = null;
	String mcpagado = null;
	String codcrono = null;
	String porcrete = null;
	String totalpag = null;
	String mcagrupa = null;
	
	
	
	
    public UpdFacturaBD(){
    }
    
    public UpdFacturaBD(ObjectIO bdata) throws Exception {
    	
    	idfactur = bdata.getStringValue("idfactur");
    	idemisor = bdata.getStringValue("idemisor");
    	idclient = bdata.getStringValue("idclient");
    	tpclient = bdata.getStringValue("tpclient");
    	cdfactur = bdata.getStringValue("cdfactur");
    	fhfactur = bdata.getStringValue("fhfactur");
    	tipofact = bdata.getStringValue("tipofact");
    	aniofact = bdata.getStringValue("aniofact");
    	cddivisa = bdata.getStringValue("cddivisa");
    	baseimpo = bdata.getStringValue("baseimpo");
    	imptaxes = bdata.getStringValue("imptaxes");
    	impreten = bdata.getStringValue("impreten");
    	imptotal = bdata.getStringValue("imptotal");
    	filecrea = bdata.getStringValue("filecrea");
    	cdestado = bdata.getStringValue("cdestado");
    	idtmpfra = bdata.getStringValue("idtmpfra");
    	formpago = bdata.getStringValue("formpago");
    	condpago = bdata.getStringValue("condpago");
    	catefact = bdata.getStringValue("catefact");
    	informda = bdata.getStringValue("informda");
    	factasoc = bdata.getStringValue("factasoc");
    	codvende = bdata.getStringValue("codvende"); 
    	mcpagado = bdata.getStringValue("mcpagado");
    	codcrono = bdata.getStringValue("codcrono");
    	porcrete = bdata.getStringValue("porcrete");
    	totalpag = bdata.getStringValue("totalpag");
    	mcagrupa = bdata.getStringValue("mcagrupa");
    	
    	if(impreten == null || impreten.equals("")){
    		impreten = "0";
        }
    

    }       
    
    public String getInsertStatement(){
        
        StringBuffer sql = new StringBuffer();
        
        if(idtmpfra == null || idtmpfra.equals("")){
        	idtmpfra = "0";
        }
        
        if(tpclient == null || tpclient.equals("")){
        	tpclient = "0";
        }
        if(condpago == null || condpago.equals("")){
        	condpago = "0";
        }
        if(formpago == null || formpago.equals("")){
        	formpago = "0";
        }
        if(catefact == null || catefact.equals("")){
        	catefact = "0";
        }
        
        if(informda == null || informda.equals("")){
        	informda = "";
        }
        
        if(codvende == null || codvende.equals("")){
        	codvende = "0";
        }
        
        if(mcpagado == null || mcpagado.equals("")){
        	mcpagado = "N";
        }
        
        
        if(factasoc == null){
        	factasoc = "";
        }
        
        if(codcrono == null){
    		codcrono = "";
        }
    	if(porcrete == null || porcrete.equals("")){
    		impreten = "0";
        }
    	
    	if(totalpag == null || totalpag.equals("")){
    		totalpag = "0";
        }
    	
    	if(mcagrupa == null || mcagrupa.equals("")){
    		mcagrupa = "N";
        }
    	
    	
        
        sql.append(" INSERT INTO jlfactur (idemisor, idclient, tpclient, codvende, mcpagado, cdfactur,cdcronol, factasoc, fhfactur, tipofact, aniofact, ");
        sql.append(" cddivisa, baseimpo, imptaxes, porcrete, impreten, imptotal,totalpag, filecrea, cdestado, idtmpfra, condpago, formpago, informad ,catefact,mcagrupa) ");
        sql.append(" VALUES ");
        sql.append(" ("+idemisor+","+idclient+","+tpclient+",'"+codvende+"','"+mcpagado+"','"+cdfactur+"','"+codcrono+"','"+factasoc+"','"+ fhfactur +"',"+tipofact+","+aniofact+",'"+ cddivisa +"',");
        sql.append( baseimpo +","+ imptaxes +","+ porcrete +","+ impreten +","+ imptotal +","+ totalpag +",'"+ filecrea +"','"+ cdestado +"','"+ idtmpfra +"','"+ condpago +"','"+ formpago +"','"+ informda +"','"+ catefact +"','"+ mcagrupa +"')");
        
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    public String getDeleteStatement(){
        
        StringBuffer sql = new StringBuffer();
        sql.append(" DELETE FROM jlfactur ");
        sql.append(" WHERE idfactur ="+idfactur);
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        return sql.toString();
    }

	@Override
	public String getUpdateStatement() {
		  StringBuffer sql = new StringBuffer();
		  
		  	sql.append(" UPDATE jlfactur SET");
		  	sql.append(" baseimpo = "+baseimpo+", ");
		  	sql.append(" imptaxes = "+imptaxes+", ");
		  	

		  	if(impreten != null && !impreten.equals("")){
		  		sql.append(" imptaxes = "+impreten+", ");
	        }
		  	
		  	if(totalpag != null && !totalpag.equals("")){
		  		sql.append(" totalpag = "+totalpag+", ");
	        }
		  	

		  	sql.append(" imptaxes = "+imptaxes+", ");
		  	sql.append(" filecrea = '"+filecrea+"', ");
		  	sql.append(" imptotal = "+imptotal+" ");
		  	sql.append(" WHERE idfactur = "+idfactur+" ");
	       
		  System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
	        
		  return sql.toString();
		  
	}

	@Override
	public Collection defineSelect() {

    	Vector<String> select=new Vector<String>();
		select.add("idfactur");
		
		return select;  
	}

	@Override
	public String getSelectStatment() {
		
		 StringBuffer sql = new StringBuffer();
		 
		 sql.append("SELECT idfactur FROM  jlfactur");
		 sql.append(" ORDER BY idfactur DESC LIMIT 0,1");
		 
		 System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
	        
		 return sql.toString();
	}
    
    
}
