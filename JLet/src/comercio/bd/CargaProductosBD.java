package comercio.bd;

import java.util.Collection;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class CargaProductosBD extends UpdateQueryBD {

	String codeenvi = null;
	String imeicode = null;
	String idproduc = null;
	String txmodelo = null;
	String idcolorx = null;
	String pricechf = null;
	String idcatego = null;
	String tablaBDX = null;
    
    public CargaProductosBD(){
    }
    
    public CargaProductosBD(ObjectIO bdata) throws Exception {
    	    	
    	codeenvi = bdata.getStringValue("codeenvi");
    	imeicode = bdata.getStringValue("imeicode");
    	idproduc = bdata.getStringValue("idproduc");
    	idcolorx = bdata.getStringValue("idcolorx");
    	pricechf = bdata.getStringValue("pricechf");
    	idcatego = bdata.getStringValue("idcatego");
    	tablaBDX = bdata.getStringValue("tablaBDX");
  
        
    }       
    
    public String getInsertStatement(){
    	  
    	StringBuffer sql = new StringBuffer();
    	
    	if(tablaBDX.equalsIgnoreCase("izumphon")){
    	    sql.append(" INSERT INTO izumphon (cdimeixx, cdgrupox, idcolorx, impventa, tipophon,numlotex,cdestado)");
    	    sql.append(" VALUES ");
    	    sql.append(" ('"+imeicode+"','"+idproduc+"','"+idcolorx+"',"+pricechf+",'"+idcatego+"','"+codeenvi+"','S')");  
    		
    	}
    	
    	if(tablaBDX.equalsIgnoreCase("izumprod")){
    	    sql.append(" INSERT INTO izumprod (iditemxx, idgrupox, idcolorx, impventa, tipodisp, numlotex,cdestado)");
    	    sql.append(" VALUES ");
    	    sql.append(" ('"+imeicode+"','"+idproduc+"','"+idcolorx+"',"+pricechf+",'"+idcatego+"','"+codeenvi+"','S')");  
    		
    	}
    	
    	if(tablaBDX.equalsIgnoreCase("izumpiec")){
    	    sql.append(" INSERT INTO izumprod (iditemxx, idgrupox, idcolorx, impventa, tipodisp");
    	    sql.append(" VALUES ");
    	    sql.append(" ('"+imeicode+"','"+idproduc+"','"+idcolorx+"',"+pricechf+",'"+idcatego+"')");  
    		
    	}
         
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSelectStatment() {
		// TODO Auto-generated method stub
		return null;
	}
    
    
}
