package facturacion.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListTipoFacturaBD extends SelectQueryBD {

	String tipofact = null;
    String idemisor = null;
    String codespec = null;
    
    public ListTipoFacturaBD(){
    
    }
    
    public ListTipoFacturaBD(ObjectIO bdata) throws Exception {
    	
    	tipofact = bdata.getStringValue("tipofact");
    	idemisor = bdata.getStringValue("idemisor");
    	codespec = bdata.getStringValue("codespec");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT tipofact, idemisor, txnombre, txdescri, preffact, porctaxe, porcrete, isalbara, codespec ");
        sql.append(" FROM jltpfac ");
        sql.append(" WHERE 1=1 ");
        
        if(tipofact!=null && !tipofact.equals("")){
        	sql.append(" AND tipofact = "+ tipofact);	
        }
        
        if(codespec!=null && !codespec.equals("")){
        	sql.append(" AND codespec = '"+ codespec+"'");	
        }
        
        sql.append("  AND idemisor = "+ idemisor);
      
        
      
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    public Collection<String> defineSelect() {

    	Vector<String> select=new Vector<String>();
		select.add("tipofact");
		select.add("idemisor");
		select.add("txnombre");
		select.add("txdescri");
		select.add("preffact");
		select.add("porctaxe");
		select.add("porcrete");
		select.add("isalbara");
		select.add("codespec");
        return select;        
    }
    
    
}
