package productos.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdPiezasBD extends UpdateQueryBD {

   	String txdescri = null; 
	String nameespa = null; 
	String namephon = null; 
	String codepiez = null;
	String preciopr = null; 
	String txmarcax = null; 
	String cdcolorx = null;
    
    public UpdPiezasBD(){
    }
    
    public UpdPiezasBD(ObjectIO bdata) throws Exception {
    	
    	txdescri = bdata.getStringValue("txdescri");
    	nameespa = bdata.getStringValue("nameespa");
    	namephon = bdata.getStringValue("namephon");
    	codepiez = bdata.getStringValue("codepiez");
    	preciopr = bdata.getStringValue("preciopr");
    	txmarcax = bdata.getStringValue("txmarcax");
    	cdcolorx = bdata.getStringValue("cdcolorx");
    }       
    
    public String getInsertStatement(){
        
        StringBuffer sql = new StringBuffer();
        
        sql.append(" INSERT INTO izumgrpi (txdescri, nameespa, namephon, codepiez, preciopr, ");
        sql.append(" txmarcax, cdcolorx, costecom, mcmostra) ");
        sql.append(" VALUES ");
        sql.append(" ('"+txdescri+"','"+nameespa+"','"+namephon+"','"+codepiez+"',"+preciopr+",'"+ txmarcax +"',");
        sql.append( "'"+ cdcolorx +"','0','S')");
        
        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
       
        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
        return sql.toString();
    }
    
    public String getDeleteStatement(){
        
        StringBuffer sql = new StringBuffer();

        return sql.toString();
    }

	@Override
	public String getUpdateStatement() {
		  StringBuffer sql = new StringBuffer();

	        //sql.append(" ORDER BY menu.childofx, menu.ordenpan ");
	       
	        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
	        
	        return sql.toString();
	}

	@Override
	public Collection defineSelect() {
		
		Vector<String> select = new Vector<String>();
		
    	select.add("idpiezax");
    	select.add("txdescri");
		select.add("nameespa");
		select.add("txmarcax");
		select.add("preciopr");
        return select;        
	}

	@Override
	public String getSelectStatment() {
		 StringBuffer sql = new StringBuffer();
		 sql.append("SELECT idpiezax, txdescri, nameespa,  txmarcax, preciopr ");
		 sql.append(" FROM izumgrpi ");
		 sql.append(" ORDER BY idpiezax DESC ");
	     sql.append(" LIMIT 0,1"); 
		
		return sql.toString();
	}
    
    
}
