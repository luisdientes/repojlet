package factura.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class MaxReciboEntradaBD extends SelectQueryBD {

   
        
    public MaxReciboEntradaBD(){
    }
    
    public MaxReciboEntradaBD(ObjectIO bdata) throws Exception {
    	
  
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT idrecibo, txnombre,idemisor, txmodelo, txcolorx, txmarcax, txdescri, tximeixx, ");
        sql.append(" DATE_FORMAT(fhentrad,'%d/%m/%Y') fhentrad,costordx,costcheq, perconta, telefono, txmailxx, tiempent, garantia, entregad, recibido ");
        sql.append(" FROM izuentra ");
        sql.append(" ORDER BY idrecibo DESC LIMIT 0,1 ");
     
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
		select.add("idrecibo");
		select.add("txnombre");
		select.add("idemisor");
		select.add("txmodelo");
		select.add("txcolorx");
		select.add("txmarcax");
		select.add("txdescri");
		select.add("tximeixx");
		select.add("fhentrad");
		select.add("costordx");
		select.add("costcheq");
		select.add("perconta");
		select.add("telefono");
		select.add("txmailxx");
		select.add("tiempent");
		select.add("garantia");
		select.add("entregad");
		select.add("recibido");
		
        return select;        
    }
    
    
}
