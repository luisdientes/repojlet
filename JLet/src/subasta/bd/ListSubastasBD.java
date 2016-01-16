package subasta.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.SelectQueryBD;
import arquitectura.objects.ObjectIO;

public class ListSubastasBD extends SelectQueryBD {

    String idcodsub = null;
    String txwebxxx = null;
    String txnombre = null;
    String idpaisxx = null;
    String tipovent = null;
    String fhdesdex = null;
    String fhhastax = null;
    String mcactivo = null;
    String idprodwe = null;
    
        
    public ListSubastasBD(){
    }
    
    public ListSubastasBD(ObjectIO bdata) throws Exception {
    	
    	idcodsub = bdata.getStringValue("idcodsub");
    	txwebxxx = bdata.getStringValue("txwebxxx");
    	txnombre = bdata.getStringValue("txnombre");
    	idpaisxx = bdata.getStringValue("idpaisxx");
    	tipovent = bdata.getStringValue("tipovent");
    	fhdesdex = bdata.getStringValue("fhdesdex");
    	fhhastax = bdata.getStringValue("fhhastax");
    	mcactivo = bdata.getStringValue("mcactivo");
    	idprodwe = bdata.getStringValue("idprodwe");
        
    }
    
    public String getSelectStatment(){
        
        StringBuffer sql = new StringBuffer();

        
        
        sql.append(" SELECT v.idcodsub, v.txwebxxx, v.idpaisxx, v.txusuari, v.tipovent, v.idproduc, v.txnombre, v.preciosa ");
        sql.append(" , v.divisaxx, v.cdintern, v.urlexter, v.preciomi, DATE_FORMAT(v.fechvent,'%d/%m/%Y') AS fechvent ");
        sql.append(" , v.horavent, DATE_FORMAT(v.finfecve,'%d/%m/%Y') AS finfecve, v.finhorve,v.mcactivo, p.txnombre as txpaisxx ");
        sql.append(" FROM jlsubast v, jlpaises p ");
        sql.append(" WHERE 1 = 1 ");
        sql.append("  AND v.idpaisxx = p.isocodex");
       
        
        if ((idcodsub != null) && (!idcodsub.equals(""))){
        	sql.append(" AND  idcodsub = "+ idcodsub);
        }
        
        if ((txwebxxx != null) && (!txwebxxx.equals(""))){
        	sql.append(" AND  txwebxxx = '"+ txwebxxx +"'");
        }
        
        if ((txnombre != null) && (!txnombre.equals(""))){
        	sql.append(" AND  v.txnombre = '"+ txnombre +"'");
        }
        
        if ((idpaisxx!= null) && (!idpaisxx.equals(""))){
        	sql.append(" AND  idpaisxx = '"+ idpaisxx +"'");
        }
        
        if ((tipovent != null) && (!tipovent.equals(""))){
        	sql.append(" AND  tipovent = '"+ tipovent +"'");
        }
        
        if ((fhdesdex != null) && (!fhdesdex.equals(""))){
        	sql.append(" AND  fechvent >= '"+ fhdesdex +"'");
        }
        
        if ((mcactivo != null) && (!mcactivo.equals("") && (!mcactivo.equals("null")))){
        	sql.append(" AND  v.mcactivo = '"+ mcactivo +"'");
        }else{
        	
        	  if ((idcodsub == null) || (idcodsub.equals(""))){
        		  sql.append(" AND  v.mcactivo = 'S'");
              }
        	
        } 
        
        if ((idprodwe != null) && (!idprodwe.equals("") && (!idprodwe.equals("null")))){
        	sql.append(" AND  idproduc = '"+ idprodwe +"'");
        }
        
        
        
        sql.append(" ORDER BY idcodsub DESC "); 
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    
    public Collection<String> defineSelect() {

    	Vector<String> select = new Vector<String>();
		select.add("idcodsub");
		select.add("txwebxxx");
		select.add("idpaisxx");
		select.add("txusuari");
		select.add("tipovent");
		select.add("idproduc");
		select.add("txnombre");
		select.add("preciosa");
		select.add("divisaxx");
		select.add("cdintern");
		select.add("urlexter");
		select.add("preciomi");
		select.add("fechvent");
		select.add("horavent");
		select.add("finfecve");
		select.add("finhorve");
		select.add("mcactivo");
		select.add("txpaisxx");
        return select;        
    }
    
    
}
