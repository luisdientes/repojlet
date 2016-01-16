package factura.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListTipoFacBD extends SelectQueryBD {

    String cdemisor = null;
    String modofact = null;
    String tipofact = null;
        
    public ListTipoFacBD(){
    }
    
    public ListTipoFacBD(ObjectIO bdata) throws Exception {
    	
    	cdemisor = bdata.getStringValue("cdemisor");
    	modofact = bdata.getStringValue("modofact");  
    	tipofact = bdata.getStringValue("tipofact"); 
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT tipofact, txnombre, porctaxe, porcrete ");
        sql.append(" FROM jltpfac ");
        sql.append(" WHERE 1=1 ");
        
        if(cdemisor !=null){
        	sql.append(" AND idemisor= "+cdemisor);
        }
        if(modofact !=null){
        	sql.append(" AND codespec='"+modofact+"'");
        }
        
        if(tipofact !=null){
        	sql.append(" AND tipofact='"+tipofact+"'");
        }
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
		select.add("tipofact");
		select.add("txnombre");
		select.add("porctaxe");
		select.add("porcrete");
		
		
        return select;        
    }
    
    
}
