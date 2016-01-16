package facturacion.bd;

import java.util.Collection;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdLineasBD extends UpdateQueryBD {

	String idemisor = null;
	String idclient = null;
	String tpclient = null;
	String cdfactur = null;
	String cdoldest = null;
	String cdnewest = null;
	String idtmpfra = null;
	String estaalba = null;
	String marcados = null;
	String imeicode = null;
    
    public UpdLineasBD(){
    }
    
    public UpdLineasBD(ObjectIO bdata) throws Exception {
    	
    	idemisor = bdata.getStringValue("idemisor");
    	idclient = bdata.getStringValue("idclient");
    	tpclient = bdata.getStringValue("tpclient");
    	cdfactur = bdata.getStringValue("cdfactur");
    	cdoldest = bdata.getStringValue("cdoldest");
    	cdnewest = bdata.getStringValue("cdnewest");
    	idtmpfra = bdata.getStringValue("idtmpfra");
    	estaalba = bdata.getStringValue("estaalba");
    	marcados = bdata.getStringValue("marcados"); 
    	imeicode = bdata.getStringValue("imeicode");
        
    }       
    
	public String getUpdateStatement() {
		  StringBuffer sql = new StringBuffer();

	        sql.append(" UPDATE tmpfactu SET ");
	        sql.append("  cdestado='"+ cdnewest +"' ");
	        if(idtmpfra != null && !idtmpfra.equals("")){
	        	 sql.append(", idtmpfra='"+ idtmpfra +"' ");
	        }
	       
	        if(estaalba != null && !estaalba.equals("")){
	            sql.append(", estaalba = '"+ estaalba +"'");
	        }
	        
	        sql.append(" WHERE idemisor = "+ idemisor +" AND idclient = "+ idclient +" AND tpclient = "+ tpclient );
	        //sql.append(" AND idtmpfra = '"+ cdfactur +"'");
	        sql.append(" AND cdestado = '"+ cdoldest +"'");
	        
	        if(estaalba != null && estaalba.equals("F") && idtmpfra != null && !idtmpfra.equals("") ){
	            sql.append(" AND  idtmpfra='"+ idtmpfra +"'");    
	        }
	        
	        if(imeicode!=null && !imeicode.equals("")){
	            sql.append(" AND  idunicox ='"+ imeicode +"'");
	        }
	        
	        
	        if(marcados!=null && !marcados.equals("")){
	            sql.append(" AND  idtmpreg IN("+ marcados +")");
	        }
	        
	        
	    
	        
	        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
	       
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

	@Override
	public String getDeleteStatement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInsertStatement() {
		// TODO Auto-generated method stub
		return null;
	}
    
    
}
