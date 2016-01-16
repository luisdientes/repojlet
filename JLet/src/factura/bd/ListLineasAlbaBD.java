package factura.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListLineasAlbaBD extends SelectQueryBD {

    String idtmpfra = null;
    String cdestado = null;
    String idemisor = null;
    String estaalba = null;
    
    public ListLineasAlbaBD(){
    }
    
    public ListLineasAlbaBD(ObjectIO bdata) throws Exception {
    	
    	idtmpfra = bdata.getStringValue("idtmpfra");
        cdestado = bdata.getStringValue("cdestado");
        idemisor = bdata.getStringValue("idemisor");
        estaalba = bdata.getStringValue("estaalba");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT idtmpreg, codprodu, idclient,idemisor,idtmpfra, fhfechax, nlineaxx, ");
        sql.append(" cantidad, concepto, precioun, descuent, precioto,fhaltaxx ");
        sql.append(" FROM tmpfactu ");
        sql.append(" WHERE idemisor ='"+idemisor+"' AND idtmpfra = '"+ idtmpfra +"'");
        
        if ((cdestado != null) && (!cdestado.equals(""))){
        	sql.append(" AND cdestado='"+ cdestado +"'");
        }
        
        if ((estaalba != null) && (!estaalba.equals(""))){
        	sql.append(" AND estaalba='"+ estaalba +"'");
        }
        
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    public String getDeleteStatment(){
        
    	return null;
    }
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
		select.add("idtmpreg");
		select.add("codprodu");
		select.add("idclient");
		select.add("idemisor");
		select.add("idtmpfra");
		select.add("fhfechax");
		select.add("nlineaxx");
		select.add("cantidad");
		select.add("concepto");
		select.add("precioun");
		select.add("descuent");
		select.add("precioto");
		select.add("fhaltaxx");
        return select;        
    }
    
    
}
