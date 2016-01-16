package factura.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListClientesBD extends SelectQueryBD {

    String idclient = null;
    String idemisor = null;
    String tpclient = null;
        
    public ListClientesBD(){
    }
    
    public ListClientesBD(ObjectIO bdata) throws Exception {
    	
        idclient = bdata.getStringValue("idclient");
        idemisor = bdata.getStringValue("idemisor");
        tpclient = bdata.getStringValue("tpclient");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        if ((tpclient == null) || (tpclient.equals(""))){
        	tpclient = "0";
        }
        
        
        sql.append(" SELECT clirc.rzsocial txrazons, clirc.idclient idclient, clirc.cdintern cdintern, clirc.tfnofijo telefono,  ");
        sql.append(" clirc.txmailxx, clirc.idfiscal txidenti ");
        sql.append(" FROM jlclierc clirc ");
        sql.append(" WHERE clirc.cdintern > 0  ");
        sql.append(" AND tpclient = "+ tpclient);
       
        if ((idclient !=null) && (!idclient.equals(""))){
        	sql.append(" AND clirc.idclient= "+idclient);
        }
        
        if ((idemisor !=null) && (!idemisor.equals(""))){
        	sql.append(" and clirc.idemisor= "+idemisor);
        }
        sql.append(" ORDER BY clirc.rzsocial ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
		select.add("txrazons");
		select.add("idclient");
		select.add("cdintern");
		select.add("telefono");
		select.add("txmailxx");
		select.add("txidenti");
		
        return select;        
    }
    
    
}
