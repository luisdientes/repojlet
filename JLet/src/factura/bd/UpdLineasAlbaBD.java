package factura.bd;

import java.util.Collection;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdLineasAlbaBD extends UpdateQueryBD {

	String idtmpreg = null;
	String codprodu = null;
	String idunicox = null;
	String idfactur = null;
	String idemisor = null;
	String idclient = null;
	String tpclient = null;
	String fechafac = null;
	String nlineaxx = null;
	String cantidad = null;
	String concepto = null;
	String precioun = null;
	String descuent = null;
	String precioto = null;
	String cdestado = null;
    
    public UpdLineasAlbaBD(){
    }
    
    public UpdLineasAlbaBD(ObjectIO bdata) throws Exception {
    	
    	idtmpreg = bdata.getStringValue("idlineax");
    	codprodu = bdata.getStringValue("codprodu");
    	idunicox = bdata.getStringValue("idunicox");
    	idfactur = bdata.getStringValue("idfactur");
    	idemisor = bdata.getStringValue("idemisor");
    	idclient = bdata.getStringValue("idclient");
    	tpclient = bdata.getStringValue("tpclient");
    	fechafac = bdata.getStringValue("fechafac");
    	nlineaxx = bdata.getStringValue("nlineaxx");
    	cantidad = bdata.getStringValue("cantidad");
    	concepto = bdata.getStringValue("concepto");
    	precioun = bdata.getStringValue("precioun");
    	descuent = bdata.getStringValue("descuent");
    	precioto = bdata.getStringValue("precioto");
    	cdestado = bdata.getStringValue("cdestado");

    	if ((tpclient == null) || (tpclient.equals(""))){
        	tpclient = "0";
        }
        
    }       
    
    public String getInsertStatement(){
        
    	return null;
    }
    
    public String getDeleteStatement(){
    	return null;
    }

	@Override
	public String getUpdateStatement() {
		  StringBuffer sql = new StringBuffer();

	        sql.append(" UPDATE tmpfactu SET ");
	        sql.append("  precioun='"+ precioun +"',");
	        sql.append("  descuent='"+ descuent +"',");
	        sql.append("  precioto='"+ precioto +"' ");
	        sql.append(" WHERE 1=1 ");
	        
	        
	        
	        if(idtmpreg!=null && !idtmpreg.equals("") ){
	        	sql.append(" AND idtmpreg = '"+ idtmpreg +"'");
	        }
	       // sql.append(" AND  cdestado = 'P'");
	        
	        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
	       
	        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
	        
	        return sql.toString();
	}

	@Override
	public Collection defineSelect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSelectStatment() {
		// TODO Auto-generated method stub
		return null;
	}
    
    
}
