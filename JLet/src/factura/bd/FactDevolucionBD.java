package factura.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class FactDevolucionBD extends SelectQueryBD {

    String idalbara = null;
    String idemisor = null;
    String aniofact = null;
    String tipofact = null;
    String cdestado = null;
    String estaalba = null;
    String idfactur = null;
   
        
    public FactDevolucionBD(){
    }
    
    public FactDevolucionBD(ObjectIO bdata) throws Exception {

    	idalbara = bdata.getStringValue("idalbara");
    	idemisor = bdata.getStringValue("idemisor");
    	aniofact = bdata.getStringValue("aniofact");
    	tipofact = bdata.getStringValue("tipofact");
    	cdestado = bdata.getStringValue("cdestado");
    	estaalba = bdata.getStringValue("estaalba");
    	idfactur = bdata.getStringValue("idfactur");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        /* LINEAS TMP QUE CORRESPONDAN AL ALBARAN */
        
        sql.append(" SELECT tmfa.idtmpreg, tmfa.codprodu, tmfa.precioun, tmfa.cantidad, tmfa.idtmpfra,fa.imptaxes,fa.idclient, ");
        sql.append(" fa.tpclient, tmfa.cdestado, tmfa.concepto, tmfa.descuent, tmfa.precioto, DATE_FORMAT( tmfa.fhfechax, '%d/%m/%Y' ) AS fecha, DATE_FORMAT( fa.fhfactur, '%d/%m/%Y' ) AS fechafac");
        sql.append(" FROM jlfactur fa, tmpfactu tmfa ");
        sql.append(" WHERE  fa.idtmpfra = tmfa.idtmpfra ");
        sql.append(" AND fa.idfactur = "+ idfactur +" "); 
        sql.append(" AND tmfa.idemisor = "+ idemisor +" "); 
        sql.append(" ORDER BY tmfa.codprodu ");
        
        System.out.println("QUERY - "+ this.getClass().getName() +" - "+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
		select.add("idtmpreg");
		select.add("codprodu");
		select.add("precioun");
		select.add("cantidad");
		select.add("idtmpfra");
		select.add("imptaxes");
		select.add("idclient");
		select.add("tpclient");
		select.add("cdestado");
		select.add("concepto");
		select.add("descuent");
		select.add("precioto");

		select.add("fecha");		//REVISAR!! QUE DEPENDECIAS TIENE ESTE CAMPO si no tienen ninguna quitarlo... 
		select.add("fechafac");	
        return select;        
    }
    
    
}
