package factura.bd;

import java.util.Collection;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdFacturaBD extends UpdateQueryBD {

	String idfactur = null;
	String idemisor = null;
	String idclient = null;
	String tpclient = null;
	String cdfactur = null;
	String tipofact = null;
	String aniofact = null;
	String cddivisa = null;
	String baseimpo = null;
	String imptaxes = null;
	String imptotal = null;
	String cdestado = null;
	String formpago = null;
	String condpago = null;
    
    public UpdFacturaBD(){
    }
    
    public UpdFacturaBD(ObjectIO bdata) throws Exception {
    	
    	idfactur = bdata.getStringValue("idfactur");
    	idemisor = bdata.getStringValue("idemisor");
    	idclient = bdata.getStringValue("idclient");
    	cdfactur = bdata.getStringValue("cdfactur");
    	tipofact = bdata.getStringValue("tipofact");
    	aniofact = bdata.getStringValue("aniofact");
    	cddivisa = bdata.getStringValue("cddivisa");
    	baseimpo = bdata.getStringValue("baseimpo");
    	imptaxes = bdata.getStringValue("imptaxes");
    	imptotal = bdata.getStringValue("imptotal");
    	cdestado = bdata.getStringValue("cdestado");
    	formpago = bdata.getStringValue("formpago");
    	condpago = bdata.getStringValue("condpago");
    }       
    
    public String getInsertStatement(){
        
        StringBuffer sql = new StringBuffer();
        
        sql.append(" INSERT INTO jlfactur (idemisor, idclient, cdfactur, tipofact, aniofact ");
        sql.append(" cddivisa, baseimpo, imptaxes, imptotal, cdestado) ");
        sql.append(" VALUES ");
        sql.append(" ("+idemisor+","+idclient+",'"+cdfactur+"',"+tipofact+","+aniofact+",'"+ cddivisa +"',");
        sql.append( baseimpo +","+ imptaxes +","+ imptotal +",'"+ cdestado +"");
        
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    public String getDeleteStatement(){
        
        StringBuffer sql = new StringBuffer();

        return sql.toString();
    }

	@Override
	public String getUpdateStatement() {
		 StringBuffer sql = new StringBuffer();
		  
	  	sql.append(" UPDATE jlfactur SET");
	  	sql.append(" idclient = '"+idclient+"' ");
	  	
	  	if(formpago != null && !formpago.equals("")){
	  		sql.append(", formpago = '"+formpago+"' ");
        }
	  	
	  	if(condpago != null && !condpago.equals("")){
	  		sql.append(", condpago = '"+condpago+"' ");
        }
	  	sql.append(" WHERE idfactur = "+idfactur+" ");
       
		System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
	        
		return sql.toString();

	}

	@Override
	public Collection defineSelect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSelectStatment() {
		// TODO Auto-generated method stub
		return null;
	}
    
    
}
