package factura.bd;

import java.util.Collection;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdLineasBD extends UpdateQueryBD {

	String idlineax = null;
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
	String estaalba = null;
	
	String actualiz = null;  // si viene de modifcar lineas de factura
	
	
    
    public UpdLineasBD(){
    }
    
    public UpdLineasBD(ObjectIO bdata) throws Exception {
    	
    	idlineax = bdata.getStringValue("idlineax");
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
    	estaalba = bdata.getStringValue("estaalba");
    	actualiz = bdata.getStringValue("actualiz");

    	if ((tpclient == null) || (tpclient.equals(""))){
        	tpclient = "0";
        }
    	
    	if ((actualiz != null) && (actualiz.equals("S"))){
    		cdestado = "F";
        }
        
    }       
    
    public String getInsertStatement(){
        
        StringBuffer sql = new StringBuffer();
        
        if ((cdestado == null) || (cdestado.equals(""))){
        	cdestado = "P";
        }
        
        if ((estaalba == null) || (estaalba.equals(""))){
        	estaalba = "N";
        }
        
        
        
        sql.append(" INSERT INTO tmpfactu (idtmpreg, codprodu,idunicox, idtmpfra, idemisor, idclient, tpclient, fhfechax,");
        sql.append(" nlineaxx, cantidad, concepto, precioun, descuent, precioto, fhaltaxx, cdestado,estaalba)");
        sql.append(" VALUES ");
        sql.append(" ('"+idlineax+"','"+codprodu+"','"+idunicox+"','"+idfactur+"',"+idemisor+","+idclient+","+tpclient+",CURRENT_DATE,"+nlineaxx+",");
        sql.append(""+cantidad+",'"+concepto+"','"+precioun+"',"+descuent+","+precioto+",CURRENT_DATE,'"+ cdestado +"','"+estaalba+"')");
        
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    public String getDeleteStatement(){
        
        StringBuffer sql = new StringBuffer();

        sql.append(" DELETE FROM tmpfactu ");
        sql.append(" WHERE idtmpreg = '"+ idlineax +"'");
        
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }

	@Override
	public String getUpdateStatement() {
		  StringBuffer sql = new StringBuffer();

	        sql.append(" UPDATE tmpfactu SET ");
	        
	        	if(actualiz != null && actualiz.equals("S")){
	        		sql.append("  idclient='"+ idclient +"',");
	    	        sql.append("  tpclient= '"+tpclient+"' ");
	    	        sql.append(" WHERE idemisor = '"+ idemisor +"' AND idtmpfra = '"+ idfactur +"'");
	    	        //sql.append(" AND  cdestado = 'P'");
	        	
	        	}else{
	        		sql.append("  cdestado='"+ cdestado +"',");
	    	        sql.append("  fhfechax= '"+fechafac+"' ");
	    	        sql.append(" WHERE idclient = '"+ idclient +"' AND tpclient = '"+ tpclient +"'");
	    	        sql.append(" AND  cdestado = 'P'");
	        	}
	        
	        
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
