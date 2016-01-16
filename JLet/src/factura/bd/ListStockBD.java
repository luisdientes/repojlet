package factura.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class ListStockBD extends UpdateQueryBD {

    String cduserid = null;
    String idemisor = null;
    String cdestado = null;
    String idunicox = null;
    String idclient = null;
    String stockyde = null;
    public ListStockBD(){
    }
    
    public ListStockBD(ObjectIO bdata) throws Exception {
    	
        cduserid = bdata.getStringValue("cduserid");
        idemisor = bdata.getStringValue("idemisor");
        cdestado = bdata.getStringValue("cdestado");
        idunicox = bdata.getStringValue("idunicox");
        idclient = bdata.getStringValue("idclient");
        stockyde = bdata.getStringValue("stockyde");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();
        
        sql.append(" SELECT idproduc, idclient, txmarcax, txmodelo,codprodu, imeicode, pricevnt,porcmarg " );
        sql.append(" FROM tradstoc WHERE idemisor='"+idemisor+"'");
        
        if ((cdestado == null) || (cdestado.equals(""))){
        	sql.append(" AND cdestado='PRESTOCK' ");
        } else if (cdestado.equals("VENDIDO")){
        		sql.append(" AND (cdestado = 'VENDIDO' OR cdestado = 'VENDIDOINT' OR cdestado='VENDIDOEXT')");
         }else if ((stockyde != null) && (stockyde.equals("S"))){
        	 	sql.append(" AND cdestado = 'STOCK' OR cdestado='DEPOSITO' ");
         }else{
        	 	sql.append(" AND cdestado='"+ cdestado +"' ");
    		}
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
		
    	select.add("idunicox");
    	select.add("idclient");
		select.add("txmarcax");
		select.add("txmodelo");
		select.add("codprodu");
		select.add("imeicode");
		select.add("pricechf");
		select.add("porcmarg");
		
        return select;        
    }

	@Override
	public String getInsertStatement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUpdateStatement() {
		StringBuffer sql = new StringBuffer();
	      
		sql.append(" UPDATE tradstoc SET" );
		sql.append(" CDESTADO='"+cdestado+"'" );
		
		if(idclient != null && !idclient.equals("")){
			sql.append(", idclient='"+idclient+"'" );
		}
		  
		sql.append(", CDESTADO='"+cdestado+"'" );  
		sql.append(" WHERE imeicode ='"+idunicox+"'" ); 
		
		 System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
	        
	        return sql.toString();
	}

	@Override
	public String getDeleteStatement() {
		// TODO Auto-generated method stub
		return null;
	}
    
    
}
