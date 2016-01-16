package stock.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdCodVentasBD extends UpdateQueryBD {

	String idemisor = null;
	String divisaxx = null;
	String txmarcax = null;
	String txdescri = null;
	String impdefve = null;
	String cantidad = null;
	String codprodu = null;
	
    
    public UpdCodVentasBD(){
    }
    
    public UpdCodVentasBD(ObjectIO bdata) throws Exception {

    	idemisor = bdata.getStringValue("idemisor");
    	codprodu = bdata.getStringValue("codprodu");
    	txmarcax = bdata.getStringValue("txmarcax");
    	txdescri = bdata.getStringValue("txdescri");
    	impdefve = bdata.getStringValue("impdefve");
    	cantidad = bdata.getStringValue("cantidad");  
    	divisaxx = bdata.getStringValue("divisaxx");  
    }       
    
    public String getInsertStatement(){
        
        StringBuffer sql = new StringBuffer();
        
        StringBuffer campos  = new StringBuffer();
        StringBuffer valores = new StringBuffer();
        
        campos.append(" INSERT INTO jlcodven (idemisor,codprodu, txmarcax, txdescri, impdefve, cantidad,divisaxx ");
        valores.append(" VALUES ("+ idemisor +",'"+codprodu+"','"+txmarcax+"','"+txdescri+"','"+ impdefve +"','"+cantidad+"','"+divisaxx+"'");

        
     	campos.append(")");
    	valores.append(")");
   
        sql.append(campos);
        sql.append(valores);
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    public String getDeleteStatement(){
  
    return null;
    }

	@Override
	public String getUpdateStatement() {
		
		
		return null;
	}

	@Override
	public Collection defineSelect() {
		Vector<String> select = new Vector<String>();
    	select.add("codprodu");
    	select.add("txmarcax");
    	select.add("txdescri");
    	select.add("impdefve");
    	select.add("cantidad");
    	select.add("divisaxx");
        return select;        
	}

	@Override
	public String getSelectStatment() {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT codprodu, txmarcax, txdescri, impdefve, cantidad, divisaxx ");
		sql.append(" FROM jlcodven ");
		sql.append(" WHERE idemisor ='"+idemisor+"'");
		if(txdescri !=null && !txdescri.equals("")){
			sql.append(" AND  txdescri LIKE'%"+txdescri+"%'");
		}
		if(txmarcax !=null && !txmarcax.equals("")){
			sql.append(" AND  txmarcax LIKE'%"+txmarcax+"%'");
		}
		
		sql.append(" order by txmarcax, txdescri");
		
		System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
	    return sql.toString();
	}
    
    
}
