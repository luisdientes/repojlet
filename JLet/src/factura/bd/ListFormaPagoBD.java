package factura.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListFormaPagoBD extends SelectQueryBD {

    String idemisor = null;
    String idfrmpag = null;
        
    public ListFormaPagoBD(){
    }
    
    public ListFormaPagoBD(ObjectIO bdata) throws Exception {
    	
    	idemisor = bdata.getStringValue("idemisor");
    	idfrmpag = bdata.getStringValue("idfrmpag");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT idfrmpag, idemisor, txnombre, txbancox, txcuenta, txotrosx, numdiasx, mcefecti,autopaga, cuentapu, mcdiasxx ");
        sql.append(" FROM jlfrmpag ");
        sql.append(" WHERE idemisor= "+ idemisor);
        
        if ((idfrmpag != null) && (!idfrmpag.equals(""))){
        	sql.append(" AND idfrmpag = '"+ idfrmpag +"'");
        }
        
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
		select.add("idfrmpag");
		select.add("idemisor");
		select.add("txnombre");
		select.add("txbancox");
		select.add("txcuenta");
		select.add("txotrosx");
		select.add("numdiasx");
		select.add("mcefecti");
		select.add("autopaga");
		select.add("cuentapu");
		select.add("mcdiasxx");
		
        return select;        
    }
    
    
}
