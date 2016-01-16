package factura.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class FactAlbaraBD extends SelectQueryBD {

    String idalbara = null;
    String idemisor = null;
    String aniofact = null;
    String tipofact = null;
    String cdestado = null;
    String estaalba = null;
   
        
    public FactAlbaraBD(){
    }
    
    public FactAlbaraBD(ObjectIO bdata) throws Exception {

    	idalbara = bdata.getStringValue("idalbara");
    	idemisor = bdata.getStringValue("idemisor");
    	aniofact = bdata.getStringValue("aniofact");
    	tipofact = bdata.getStringValue("tipofact");
    	cdestado = bdata.getStringValue("cdestado");
    	estaalba = bdata.getStringValue("estaalba");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        /* LINEAS TMP QUE CORRESPONDAN AL ALBARAN */
        
        sql.append(" SELECT gr.imagedet, tmfa.idtmpreg, tmfa.codprodu, tmfa.cdestado, tmfa.estaalba, tmfa.precioun, tmfa.cantidad, tmfa.idtmpfra, ");
        sql.append(" tmfa.tpclient, tmfa.concepto, tmfa.descuent, tmfa.precioto, tmfa.idclient, DATE_FORMAT( tmfa.fhfechax, '%d/%m/%Y' ) AS fecha, DATE_FORMAT( fa.fhfactur, '%d/%m/%Y' ) AS fechafac");
        sql.append(" FROM jlfactur fa, tmpfactu tmfa ");
        sql.append(" LEFT JOIN izumgrph gr ON ( gr.idgrupox = Replace( LTrim( Replace( substring( tmfa.codprodu, 3 ) , '0', ' ' ) ) , ' ', 0 ) )  ");
        sql.append(" WHERE  fa.idtmpfra = tmfa.idtmpfra ");
        sql.append(" AND fa.cdfactur = "+ idalbara +" "); 
        sql.append(" AND fa.tipofact = "+ tipofact +" ");
        sql.append(" AND fa.idemisor = "+ idemisor +" ");
        sql.append(" AND fa.aniofact = '"+ aniofact +"' ");
        /*
        if ((cdestado != null) && (!cdestado.equals(""))){
        	sql.append(" AND tmfa.cdestado='"+ cdestado +"' ");
        }
        
        if ((estaalba != null) && (!estaalba.equals(""))){
        	sql.append(" AND tmfa.estaalba='"+ estaalba +"' ");
        }*/
        
        sql.append(" ORDER BY tmfa.codprodu ");
        
        System.out.println("QUERY - "+ this.getClass().getName() +" - "+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
		select.add("imagedet");
		select.add("idtmpreg");
		select.add("codprodu");
		select.add("cdestado");
		select.add("estaalba");
		select.add("precioun");
		select.add("cantidad");
		select.add("idtmpfra");
		select.add("tpclient");
		select.add("concepto");
		select.add("descuent");
		select.add("precioto");
		select.add("idclient");
		select.add("fecha");		//REVISAR!! QUE DEPENDECIAS TIENE ESTE CAMPO si no tienen ninguna quitarlo... 
		select.add("fechafac");	
        return select;        
    }
    
    
}
