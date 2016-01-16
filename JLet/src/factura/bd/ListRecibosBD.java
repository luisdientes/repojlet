package factura.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class ListRecibosBD extends UpdateQueryBD {
	
	String idrecibo = null;
    String idemisor = null;
    String idfactur = null;
    String idclient = null;
    String tpclient = null;
    String fhrecibo = null;
    String canrecib = null;
    String concepto = null;
    String cdfactur = null;
    String formpago = null;
    String txbancox = null;
    String numrecib = null;
    String namefile = null;
    String fecharec = null;
    String tpfacrec = null;
    String idformpa = null;

        
    public ListRecibosBD(){
    }
    
    public ListRecibosBD(ObjectIO bdata) throws Exception {
    	
    	idrecibo = bdata.getStringValue("idrecibo");
        idemisor = bdata.getStringValue("idemisor");
        idfactur = bdata.getStringValue("idfactur");
        idclient = bdata.getStringValue("idclient");
        tpclient = bdata.getStringValue("tpclient");
        fhrecibo = bdata.getStringValue("fhrecibo");
        canrecib = bdata.getStringValue("canrecib");
        concepto = bdata.getStringValue("concepto");
        cdfactur = bdata.getStringValue("cdfactur");
        formpago = bdata.getStringValue("formpago");
        txbancox = bdata.getStringValue("txbancox");
        numrecib = bdata.getStringValue("numrecib");
        namefile = bdata.getStringValue("namefile");
        fecharec = bdata.getStringValue("fecharec"); 
        tpfacrec = bdata.getStringValue("tpfacrec");
        idformpa = bdata.getStringValue("idformpa");
        
       // tpclient = "0";
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer(); 

        sql.append(" SELECT re.idrecibo, re.idlineax, re.idfactur, re.cdfactur, re.canrecib, ");
        sql.append(" DATE_FORMAT(re.fhrecibo, '%d/%m/%Y'), re.filecrea, re.concepto,re.formpago, re.txbancox, cl.rzsocial  ");
        sql.append(" FROM jlrecibo re, jlclierc cl ");
        sql.append(" WHERE re.idfactur = "+idfactur);
        sql.append(" AND re.idemisor =  "+ idemisor  );
        sql.append(" AND re.idclient = cl.idclient  ");
        sql.append(" AND re.tpclient = cl.tpclient ");
        
        if(numrecib != null && numrecib!=""){
        	sql.append(" AND re.idlineax ='"+numrecib+"'");
        }
        
        if(tpclient != null && tpclient!=""){
        	sql.append(" AND cl.tpclient ='"+tpclient+"'");
        }
        
        
        
        
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
    	

		
    	select.add("idrecibo");
		select.add("numrecib");
		select.add("idfactur");
		select.add("cdfactur");
		select.add("cantidad");
		select.add("fecharec");
		select.add("filecrea");
		select.add("concepto");
		select.add("formpago");
		select.add("txbancox");
		select.add("rzsocial");
		
        return select;        
    }

	@Override
	public String getInsertStatement() {
		StringBuffer sql = new StringBuffer();
        
        sql.append(" INSERT INTO jlrecibo (idrecibo,idlineax ,idfactur, idemisor, idclient,tpfactur, tpclient, fhrecibo, ");
        sql.append(" canrecib, concepto, cdfactur, formpago, idfrmpag, txbancox) ");
        sql.append(" VALUES ");
        sql.append(" ('"+idrecibo+"','"+numrecib+"','"+idfactur+"','"+idemisor+"','"+idclient+"','"+tpfacrec+"','"+tpclient+"','"+fecharec+"','"+ canrecib +"',");
        sql.append("'"+concepto +"','"+ cdfactur +"','"+ formpago +"','"+idformpa+"','"+ txbancox +"')");
        
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
	}

	@Override
	public String getUpdateStatement() {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE jlrecibo SET");
		sql.append(" filecrea='"+namefile+"'");
		sql.append(" WHERE idlineax = "+numrecib);
		sql.append(" AND idemisor = "+idemisor);
		sql.append(" AND idfactur = "+idfactur);
		System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
	}

	@Override
	public String getDeleteStatement() {
		// TODO Auto-generated method stub
		return null;
	}
    
    
}
