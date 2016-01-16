package personal.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class ListProyectBD extends UpdateQueryBD {

    String cduserid = null;
    String idproyec = null;
    String cdrolxxx = null;
   	String txnombre = null;
   	String idempres = null;
   	String tpproyec = null;
   	String numperso = null;
   	String txduraci = null;
   	String txdescri = null;
   	String fhinicio = null;
   	String fhfinxxx = null;
	String mcestado = "S";
	String idemisor = "S";
    
    public ListProyectBD(){
    
    }
    
    public ListProyectBD(ObjectIO bdata) throws Exception {
    	
    	   cduserid = bdata.getStringValue("cduserid");
           idproyec = bdata.getStringValue("idproyec");
           txnombre = bdata.getStringValue("txnombre");
           idempres = bdata.getStringValue("idempres");
           tpproyec = bdata.getStringValue("tpproyec");
           numperso = bdata.getStringValue("numperso");
           txduraci = bdata.getStringValue("txduraci");
           txdescri = bdata.getStringValue("txdescri");
           fhinicio = bdata.getStringValue("fhinicio");
           fhfinxxx = bdata.getStringValue("fhfinxxx");
           idemisor = bdata.getStringValue("idemisor");
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT txnombre, idproyec, pr.idempres, txrazons, tpproyect, numperso, txdescri, txduracio,Date_format(fhinicio,'%d/%m/%Y') as fhinicio,Date_format(fhfinxxx,'%d/%m/%Y') as fhfinxxx");
        sql.append(" FROM gstnproy pr ");
        sql.append(" LEFT JOIN gstnempr em ON (pr.idempres = em.idempres )");
        sql.append(" WHERE 1=1 ");
        sql.append(" AND mcestado <> 'E'");
        if ((cduserid != null) && (!cduserid.equals("")) && (!cduserid.equals("0"))){
        	sql.append(" AND idtrabaj = "+ cduserid);
        }
        
        if ((idproyec != null) && (!idproyec.equals("")) && (!idproyec.equals("0"))){
        	sql.append(" AND idproyec = "+ idproyec);
        }
        
        if ((idemisor != null) && (!idemisor.equals("")) && (!idemisor.equals("0"))){
        	sql.append(" AND pr.idemisor = "+ idemisor);
        }
        
        
        sql.append(" ORDER BY txnombre ");
        
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    


	@Override
	public String getInsertStatement() {
		
		 StringBuffer sql = new StringBuffer();
		    
		    if ((numperso == null) || (numperso.equals(""))){
	        	numperso ="0";
	        }

	        sql.append(" INSERT INTO gstnproy (txnombre,idempres,tpproyect,numperso,txdescri,txduracio,fhinicio,fhfinxxx,mcestado,idemisor )");
	        sql.append(" VALUES ");
	        sql.append("('"+txnombre+"' ,");
	    	sql.append(" '"+idempres+"' ,");
	    	sql.append(" '"+tpproyec+"' ,");
	    	sql.append(" '"+numperso+"' ,");
	    	sql.append(" '"+txdescri+"' ,");
	    	sql.append(" '"+txduraci+"' ,");
	    	sql.append(" '"+fhinicio+"' ,");
	    	sql.append(" '"+fhfinxxx+"' ,");
	    	sql.append(" '"+mcestado+"' ,");
	    	sql.append(" '"+idemisor+"' )");

	        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
			
		return sql.toString();
	}

	@Override
	public String getUpdateStatement() {
		StringBuffer sql = new StringBuffer();
		
		 if ((numperso == null) || (numperso.equals(""))){
	        	numperso ="0";
	        }
	 	
		sql.append(" UPDATE gstnproy SET");
	   	sql.append(" txnombre	='"+txnombre+"'" );
	   	sql.append(", idempres	='"+idempres+"'" );
	   	sql.append(", tpproyect	='"+tpproyec+"'" );
	   	sql.append(", numperso	='"+numperso+"'" );
	   	sql.append(", txdescri	='"+txdescri+"'");
	   	sql.append(", txduracio	='"+txduraci+"'");
	   	sql.append(", fhinicio	='"+fhinicio+"'");
	   	sql.append(", fhfinxxx	='"+fhfinxxx+"'");
	   	sql.append(" WHERE idproyec ='"+idproyec+"' ");
		
	   	System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
  		
  	return sql.toString();
	}

	@Override
	public String getDeleteStatement() {
		// TODO Auto-generated method stub
		return null;
	}
	
    public Collection<String> defineSelect() {

    	Vector<String> select=new Vector<String>();
		select.add("txnombre");
		select.add("idproyec");
		select.add("idempres");
		select.add("txrazons");
		select.add("tpproyect");
		select.add("numperso");
		select.add("txdescri");
		select.add("txduracio");
		select.add("fhinicio");
		select.add("fhfinxxx");
        return select;        
    }
    
    
}
