package stock.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdInventBD extends UpdateQueryBD {

	String idemisor = null;
	String cdestado = null;
	String imeicode = null;
	String tpclient = null;
	String idinvent = null;
	String nameinve = null;
	String tpproduc = null;

    
    public UpdInventBD(){
    }
    
    public UpdInventBD(ObjectIO bdata) throws Exception {

    	idemisor = bdata.getStringValue("idemisor");
    	tpclient = bdata.getStringValue("tpclient");
    	imeicode = bdata.getStringValue("idunicox"); 
    	cdestado = bdata.getStringValue("cdestado");
    	nameinve = bdata.getStringValue("nameinve");
    	idinvent = bdata.getStringValue("idinvent");
    	tpproduc = bdata.getStringValue("tpproduc");
    	
    	if (cdestado == null || cdestado.equals("")){
    		cdestado ="N";
    	}
    	
    	if (tpclient == null || tpclient.equals("") || tpclient.equals("null") ){
    		tpclient ="0";
    	}
    }       
    
    public String getInsertStatement(){
        
        StringBuffer sql = new StringBuffer();
        
        sql.append(" INSERT INTO tmpinven (idunicox, idemisor, tpclient, fhaltaxx, consolid, nameinve,tpproduc)");
        sql.append(" VALUES ");
        sql.append(" ('"+ imeicode +"','"+idemisor+"','"+tpclient+"',CURRENT_DATE,'"+ cdestado +"','"+ nameinve +"','"+ tpproduc +"')");
   
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    public String getDeleteStatement(){
  
    	StringBuffer sql = new StringBuffer();
        
	    sql.append(" DELETE FROM tmpinven ");
	    sql.append(" WHERE idinvent  = '"+idinvent+"'");
	        
	    System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
	        
	    return sql.toString();
    }

	@Override
	public String getUpdateStatement() {
		
		return null;
		
	}

	@Override
	public Collection defineSelect() {
		
		Vector<String> select = new Vector<String>();
		
    	select.add("idinvent");
    	select.add("idunicox");
    	select.add("fhaltaxx");
    	select.add("idemisor");
    	select.add("tpclient");
    	select.add("consolid");
    	select.add("nameinve");
    	select.add("tpproduc");
    	select.add("nproduct");
        return select;        
	}
	@Override
	public String getSelectStatment() {
		
		 StringBuffer sql = new StringBuffer();
		 sql.append(" SELECT idinvent, idunicox, DATE_FORMAT(fhaltaxx,'%d/%m/%Y') as fecha, idemisor, tpclient, consolid, nameinve,tpproduc ");
		  if (nameinve == null || nameinve.equals("")){
		    	 sql.append(",  COUNT(nameinve) AS nproduct");  
		  }else{
			  sql.append(", idinvent");  
		  }
		
		 sql.append(" FROM tmpinven ");
		 sql.append(" WHERE consolid = 'N' ");
	     sql.append(" AND idemisor = '"+ idemisor +"'");
	     //sql.append(" AND consolid = '"+ cdestado +"'");
	     
	     if (nameinve != null && !nameinve.equals("")){
	    	 sql.append(" AND nameinve = '"+ nameinve +"'"); 
	     }else{
	    	 sql.append(" GROUP BY  nameinve ");  
	     }
	     
	     sql.append(" ORDER BY fhaltaxx DESC");
	     
	     System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
	        
	     return sql.toString();
	        
	}
    
    
}
