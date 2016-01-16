package factura.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class DetalleFacturasBD extends SelectQueryBD {

    String idfactur = null;
        
    public DetalleFacturasBD(){
    }
    
    public DetalleFacturasBD(ObjectIO bdata) throws Exception {

    	idfactur = bdata.getStringValue("idfactur");
  
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" SELECT fr.idfactur, fr.idemisor, fr.idclient, fr.tpclient, fr.cdfactur,fr.cdcronol,fr.fhfactur, fr.tipofact,  ");
        sql.append(" fr.aniofact, fr.cddivisa, fr.baseimpo, fr.imptaxes, fr.imptotal,fr.totalpag, fr.filecrea, ");
        sql.append(" fr.cdestado, fr.timecrea,fr.idtmpfra,fr.condpago,fr.formpago,fr.mcpagado,fr.catefact, fr.informad, fr.factasoc, ");
        sql.append(" fr.codvende,fr.mcagrupa,fr.porcrete ");
        sql.append(" FROM jlfactur fr");
        sql.append(" WHERE fr.idfactur = "+ idfactur);
 

        
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	
    	
    	Vector<String> select = new Vector<String>();
		select.add("idfactur");
		select.add("idemisor");
		select.add("idclient");
		select.add("tpclient");
		select.add("cdfactur");
		select.add("cdcronol");
		select.add("fhfactur");
		select.add("tipofact");
		select.add("aniofact");
		select.add("cddivisa");
		select.add("baseimpo");
		select.add("imptaxes");
		select.add("imptotal");
		select.add("totalpag");
		select.add("filecrea");
		select.add("cdestado");
		select.add("timecrea");
		select.add("idtmpfra");
		select.add("condpago");
		select.add("formpago");
		select.add("mcpagado");
		select.add("catefact");
		select.add("informad");
		select.add("factasoc");
		select.add("codvende");
		select.add("mcagrupa");
		select.add("porcrete");
		
        return select;        
    }
    
    
}
