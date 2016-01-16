package facturacion.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListClienteWebBD extends SelectQueryBD {

    String idshopxx = null;
    String idcustom = null;
    
    public ListClienteWebBD(){
    
    }
    
    public ListClienteWebBD(ObjectIO bdata) throws Exception {
    	
    	idshopxx = bdata.getStringValue("idshopxx");
    	idcustom = bdata.getStringValue("idcustom");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT cu.id_customer, cu.email, ad.company, ad.firstname, ad.lastname ");
        sql.append(" , ad.address1, ad.address2, ad.postcode, ad.city, ad.other, ad.phone ");
        sql.append(" FROM izumba_customer cu, izumba_address ad ");
        sql.append(" WHERE cu.id_customer = ad.id_customer ");
  
        
        if ((idcustom != null) && (!idcustom.equals(""))){
        	sql.append(" AND cu.id_customer = "+ idcustom );
        }
        
        if ((idshopxx != null) && (!idshopxx.equals(""))){
        	sql.append(" AND cu.id_shop = "+ idshopxx);
        }
      	
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    public Collection<String> defineSelect() {

    	Vector<String> select=new Vector<String>();
		select.add("idclient");
		select.add("txmailxx");
		select.add("rzsocial");
		select.add("txnombre");
		select.add("txapelli");
		select.add("direcci1");
		select.add("direcci2");
		select.add("cdpostal");
		select.add("txciudad");
		select.add("otrdirec");
		select.add("telefono");
		
        return select;        
    }
    
    
}
