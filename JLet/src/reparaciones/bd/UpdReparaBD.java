package reparaciones.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class UpdReparaBD extends UpdateQueryBD {

	String idrecibo = null;
	String idemisor = null;
	String tpclient = null;
	String costcheq = null;
	String txnombre = null;
	String txmodelo = null;
	String txcolorx = null;
	String txmarcax = null;
	String txdescri = null;
	String tximeixx = null;
	String fhentrad = null;
	String costordx = null;
	String perconta = null;
	String telefono = null;
	String txmailxx = null;
	String tiempent = null;
	String garantia = null;
	String entregad = null;
	String recibido = null;
	String cdrecibo = null;
	String filereci = null;
  
  	 
    public UpdReparaBD(){
    }
    
    public UpdReparaBD(ObjectIO bdata) throws Exception {
    	    	
    	idrecibo = bdata.getStringValue("idrecibo");
    	tpclient = bdata.getStringValue("tpclient");
    	idemisor = bdata.getStringValue("idemisor"); 
    	costcheq = bdata.getStringValue("costcheq"); 
    	txnombre = bdata.getStringValue("txnombre"); 
    	txmodelo = bdata.getStringValue("txmodelo"); 
    	txcolorx = bdata.getStringValue("txcolorx"); 
    	txmarcax = bdata.getStringValue("txmarcax"); 
    	txdescri = bdata.getStringValue("txdescri"); 
    	tximeixx = bdata.getStringValue("tximeixx"); 
    	fhentrad = bdata.getStringValue("fhentrad"); 
    	costordx = bdata.getStringValue("costordx");
    	perconta = bdata.getStringValue("perconta");
    	telefono = bdata.getStringValue("telefono"); 
    	txmailxx = bdata.getStringValue("txmailxx"); 
    	tiempent = bdata.getStringValue("tiempent"); 
    	garantia = bdata.getStringValue("garantia"); 
    	entregad = bdata.getStringValue("entregad"); 
    	recibido = bdata.getStringValue("recibido"); 
    	cdrecibo = bdata.getStringValue("cdrecibo"); 
    	filereci = bdata.getStringValue("filereci"); 

    }       
    
    public String getInsertStatement(){
        
    	 StringBuffer sql = new StringBuffer();
         
         StringBuffer campos  = new StringBuffer();
         StringBuffer valores = new StringBuffer();
        
        campos.append(" INSERT INTO izuentra (txnombre, idemisor, txmodelo, txcolorx, tpclient, txmarcax, txdescri, tximeixx,fhentrad");
        valores.append(" VALUES ('"+txnombre+"','"+idemisor+"','"+txmodelo+"','"+ txcolorx +"','"+tpclient+"','"+txmarcax+"','"+txdescri+"','"+tximeixx+"','"+fhentrad+"'");
        
        
        
        if ((costordx != null) && (!costordx.equals(""))){
        	campos.append(", costordx");
        	valores.append(", '"+ costordx +"'");
        }
        
        if ((costcheq != null) && (!costcheq.equals(""))){
        	campos.append(", costcheq");
        	valores.append(", '"+ costcheq +"'");
        }
        if ((garantia != null) && (!garantia.equals(""))){
        	campos.append(", garantia");
        	valores.append(", '"+ garantia +"'");
        }
        
        
        
        campos.append(", perconta, telefono, txmailxx, tiempent, entregad, recibido)");
      

        valores.append(",'"+perconta+"','"+telefono+"','"+txmailxx+"','"+tiempent+"','"+entregad+"','"+recibido+"')");
        sql.append(campos);
        sql.append(valores);
        
        return sql.toString();
    }
    
    public String getDeleteStatement(){
  
        return null;
    }

	@Override
	public String getUpdateStatement() {
		
		 StringBuffer sql = new StringBuffer();
		 sql.append("  UPDATE izuentra SET ");
		 sql.append("idemisor = idemisor ");

		 if(txnombre != null && !txnombre.equals("")){
			 sql.append(", txnombre='"+txnombre+"'");
		 }
		 
		 if(txmodelo != null && !txmodelo.equals("")){
			 sql.append(", txmodelo='"+txmodelo+"'");
		 }
		 
		 if(txcolorx != null && !txcolorx.equals("")){
			 sql.append(", txcolorx='"+txcolorx+"'");
		 }
		 
		 if(txmarcax != null && !txmarcax.equals("")){
			 sql.append(", txmarcax='"+txmarcax+"'");
		 }
		 
		 if(txdescri != null && !txdescri.equals("")){
			 sql.append(", txdescri='"+txdescri+"'");
		 }
		 
		 if(tximeixx != null && !tximeixx.equals("")){
			 sql.append(", tximeixx='"+tximeixx+"'");
		 }
		 
		 if(fhentrad != null && !fhentrad.equals("")){
			 sql.append(", fhentrad='"+fhentrad+"'");
		 }
		 
		 if(costordx != null && !costordx.equals("")){
			 sql.append(", costordx='"+costordx+"'");
		 }
		 
		 if (costcheq != null && !costcheq.equals("")){
	        sql.append(", costcheq='"+costcheq+"'");
	     }
	     if (garantia != null && !garantia.equals("")){
	        sql.append(", garantia='"+garantia+"'");
	     }

		 if(perconta != null && !perconta.equals("")){
			 sql.append(", perconta='"+perconta+"'");
		 }
		 
		 if(telefono != null && !telefono.equals("")){
			 sql.append(", telefono='"+telefono+"'");
		 }
		 
		 if(txmailxx != null && !txmailxx.equals("")){
			 sql.append(", txmailxx='"+txmailxx+"'");
		 }
		 
		 if(tiempent!=null && !tiempent.equals("")){
			 sql.append(", tiempent='"+tiempent+"'");
		 }
		 
		 if (entregad != null && !entregad.equals("")){
	        sql.append(", entregad='"+entregad+"'");
	     }
	     if (recibido != null && !recibido.equals("")){
	        sql.append(", recibido='"+recibido+"'");
	     }
	     
		 if(cdrecibo != null && !cdrecibo.equals("")){
			 sql.append(", cdrecibo='"+cdrecibo+"'");
		 }
		 
		 if(filereci != null && !filereci.equals("")){
			 sql.append(", filereci='"+filereci+"'");
		 }
		 
		 sql.append(" WHERE idrecibo='"+idrecibo+"'");
		 sql.append(" AND idemisor='"+idemisor+"'");
	       
	    System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
	        
	    return sql.toString();
		
	}

	@Override
	public Collection defineSelect() {
		Vector<String> select = new Vector<String>();
		select.add("idrecibo");
		select.add("txnombre");
		select.add("idemisor");
		select.add("txmodelo");
		select.add("txcolorx");
		select.add("txmarcax");
		select.add("txdescri");
		select.add("tximeixx");
		select.add("fhentrad");
		select.add("costordx");
		select.add("costcheq");
		select.add("perconta");
		select.add("telefono");
		select.add("txmailxx");
		select.add("tiempent");
		select.add("garantia");
		select.add("entregad");
		select.add("recibido");
		
        return select;        
	}


	public String getSelectStatment() {
		  StringBuffer sql = new StringBuffer();

	        sql.append(" SELECT idrecibo, txnombre,idemisor, txmodelo, txcolorx, txmarcax, txdescri, tximeixx, ");
	        sql.append(" DATE_FORMAT(fhentrad,'%d/%m/%Y') fhentrad,costordx,costcheq, perconta, telefono, txmailxx, tiempent, garantia, entregad, recibido ");
	        sql.append(" FROM izuentra ");
	        sql.append(" WHERE idemisor = '"+idemisor+"'");
	        
	        	if(idrecibo != null && !idrecibo.equals("")){
	        		sql.append(" AND idrecibo='"+idrecibo+"'");	
	        	}
	        
	        sql.append(" ORDER BY idrecibo DESC LIMIT 0,1 ");
	     
	        System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
	        
	        return sql.toString();
	    }
    
    
}
