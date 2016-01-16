package contabilidad.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdateApunteBD extends UpdateQueryBD {

	String idemisor = null;
	String idcuenta = null;
	String cantidad = null;
	String debhaber = null;
	String concepto = null;
	String coddocum = null;
	String fhapunte = null;
	String tpapunte = null;
	String cduserid = null;
	String documint = null;
	String filedocu = null;
	String idapunte = null;
	String fhdesdex = null;
	String fhhastax = null;
	

  	 
    public UpdateApunteBD(){
    	
    }
    
    public UpdateApunteBD(ObjectIO bdata) throws Exception {
    	    	
    	idemisor = bdata.getStringValue("idemisor");
    	idcuenta = bdata.getStringValue("idcuenta");
    	cantidad = bdata.getStringValue("cantidad");
    	debhaber = bdata.getStringValue("debhaber");
    	concepto = bdata.getStringValue("concepto");
    	coddocum = bdata.getStringValue("coddocum");
    	fhapunte = bdata.getStringValue("fhapunte");
    	tpapunte = bdata.getStringValue("tpapunte");
    	cduserid = bdata.getStringValue("cduserid");
    	documint = bdata.getStringValue("documint");
    	filedocu = bdata.getStringValue("filedocu");
    	idapunte = bdata.getStringValue("idapunte");
    	fhdesdex = bdata.getStringValue("fhdesdex");
    	fhhastax = bdata.getStringValue("fhhastax");
    }       
    
    public String getInsertStatement(){
    	
    	StringBuffer sql = new StringBuffer();
		
		sql.append("INSERT INTO contapun ");
		sql.append("( idemisor, idcuenta, cantidad , debhaber, concepto, coddocum, documint, filedocu, tpapunte, fhapunte, fhaltaxx, useridxx)  ");
		sql.append(" VALUES ");
		sql.append(" ('"+idemisor+"', ");
		sql.append(" '"+idcuenta+"', ");
		sql.append(" '"+cantidad+"', ");
		sql.append(" '"+debhaber+"', ");
		sql.append(" '"+concepto+"', ");
		sql.append(" '"+coddocum+"', ");
		sql.append(" '"+documint+"', ");
		sql.append(" '"+filedocu+"', ");
		sql.append(" '"+tpapunte+"', ");
		sql.append(" '"+fhapunte+"', ");
		sql.append(" CURRENT_DATE, ");
		sql.append(" '"+cduserid+"' )");
		
		System.out.println("QUERY ---> "+sql.toString());
		
       return sql.toString();
    }
    
    public String getDeleteStatement(){
  
        return null;
    }

	@Override
	public String getUpdateStatement() {
	
StringBuffer sql = new StringBuffer();
		
		sql.append("UPDATE contapun SET");
		sql.append(" cantidad = '"+cantidad+"', ");
		sql.append(" debhaber = '"+debhaber+"', ");
		sql.append(" concepto = '"+concepto+"', ");
		sql.append(" coddocum = '"+coddocum+"', ");
		sql.append(" documint = '"+documint+"', ");
		sql.append(" filedocu = '"+filedocu+"', ");
		sql.append(" tpapunte = '"+tpapunte+"', ");
		sql.append(" fhapunte = '"+fhapunte+"' ");
		sql.append(" WHERE idemisor =  "+idemisor);
		
		if(idcuenta !=null && idcuenta !=""){
			sql.append(" AND idcuenta =  "+idcuenta);
		}
		
		if(idapunte !=null && idapunte !=""){
			sql.append(" AND idapunte =  "+idapunte);
		}
		
		
		System.out.println("QUERY ---> "+sql.toString());
		
       return sql.toString();
		
	}

	@Override
	public String getSelectStatment() {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT  idapunte,idemisor,idcuenta, debhaber, concepto, cantidad, coddocum, tpapunte,documint, filedocu,DATE_FORMAT( fhapunte, '%d/%m/%Y' ) AS fhapunt ");
		sql.append(" FROM contapun ");
		sql.append(" WHERE idemisor =  "+idemisor);
		
		if(idcuenta !=null && idcuenta !=""){
			sql.append(" AND idcuenta =  "+idcuenta);
		}
		
		if(idapunte !=null && idapunte !=""){
			sql.append(" AND idapunte =  "+idapunte);
		}
		
		if(fhdesdex !=null && fhdesdex !=""){
			sql.append(" AND fhapunte >= '"+fhdesdex+"'");
		}
		
		if(fhhastax !=null && fhhastax !=""){
			sql.append(" AND fhapunte <= '"+fhhastax+"'");
		}
		
		
		sql.append(" ORDER BY fhapunte DESC");
		
		System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
	    return sql.toString();
	}
	
	@Override
	public Collection defineSelect() {
	
		Vector<String> select = new Vector<String>();		
			select.add("idapunte");	
		    select.add("idemisor");		
			select.add("idcuenta");	
			select.add("debhaber");		
			select.add("concepto");	
			select.add("cantidad");	
			select.add("coddocum");	
			select.add("tpapunte");	
			select.add("documint");	
			select.add("filedocu");
			select.add("fhapunte");	
		
		return select; 
	}
    
    
}
