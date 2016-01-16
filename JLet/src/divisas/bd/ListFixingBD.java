package divisas.bd;

import java.util.Collection;
import java.util.Vector;

import arquitectura.database.UpdateQueryBD;
import arquitectura.objects.ObjectIO;

public class ListFixingBD extends UpdateQueryBD {

	String fhdesdex = null;
	String fhhastax = null;
  	 
    public ListFixingBD(){
    	
    }
    
    public ListFixingBD(ObjectIO bdata) throws Exception {
    	    	
    	fhdesdex = bdata.getStringValue("fhdesdex");
    	fhhastax = bdata.getStringValue("fhhastax");

    }       
    
    public String getInsertStatement(){
        
       return null;
    }
    
    public String getDeleteStatement(){
  
        return null;
    }

	@Override
	public String getUpdateStatement() {
	
		return null;
		
	}

	@Override
	public String getSelectStatment() {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT  altatime, SUM(EUR), SUM(USD), SUM(CHF), SUM(GBP) ");
		sql.append(" FROM ( ");
		//USDEUR
		sql.append(" SELECT cdcotiza, DATE_FORMAT( altatime, '%d/%m/%Y' ) AS altatime, DATE_FORMAT(altatime, '%Y%m%d' ) AS masktime, MAX( altatime ) , fixingxx AS EUR, 0 AS USD, 0 AS CHF, 0 AS GBP ");
		sql.append(" FROM `divisatb` ");
		sql.append(" WHERE cdcotiza = 'USDEUR' ");
		sql.append(" GROUP BY cdcotiza, DATE_FORMAT( altatime, '%d/%m/%Y' ) ");
		sql.append(" UNION ");
		//USDDOP
		sql.append(" SELECT cdcotiza, DATE_FORMAT( altatime, '%d/%m/%Y' ) AS altatime , DATE_FORMAT(altatime, '%Y%m%d' ) AS masktime, MAX( altatime ) , 0 AS EUR, fixingxx AS USD, 0 AS CHF, 0 AS GBP ");
		sql.append(" FROM `divisatb` ");
		sql.append(" WHERE cdcotiza = 'USDDOP' ");
		sql.append(" GROUP BY cdcotiza, DATE_FORMAT( altatime, '%d/%m/%Y' ) ");
		sql.append(" UNION ");
		//USDCHF
		sql.append(" SELECT cdcotiza, DATE_FORMAT( altatime, '%d/%m/%Y' ) AS altatime , DATE_FORMAT(altatime, '%Y%m%d' ) AS masktime, MAX( altatime ) , 0 AS EUR, 0 AS USD, fixingxx AS CHF, 0 AS GBP ");
		sql.append(" FROM `divisatb` ");
		sql.append(" WHERE cdcotiza = 'USDCHF' ");
		sql.append(" GROUP BY cdcotiza, DATE_FORMAT( altatime, '%d/%m/%Y' ) ");
		sql.append(" UNION ");
		 //USDGBP
		sql.append(" SELECT cdcotiza, DATE_FORMAT( altatime, '%d/%m/%Y' ) AS altatime , DATE_FORMAT(altatime, '%Y%m%d' ) AS masktime, MAX( altatime ) , 0 AS EUR, 0 AS USD, 0 AS CHF, fixingxx AS GBP ");
		sql.append(" FROM `divisatb` ");
		sql.append(" WHERE cdcotiza = 'USDGBP' ");
		sql.append(" GROUP BY cdcotiza, DATE_FORMAT( altatime, '%d/%m/%Y' ) ");
		sql.append(" ) sq ");
		
		sql.append(" GROUP BY sq.altatime ");
		sql.append(" ORDER BY masktime DESC ");
		sql.append(" LIMIT 0 , 30 ");

		System.out.println("QUERY - "+ this.getClass().getName() +"-"+ sql);
        
	    return sql.toString();
	}
	
	@Override
	public Collection defineSelect() {
	
		Vector<String> select = new Vector<String>();		
		select.add("fhfechax");		
		select.add("fxusdeur");	
		select.add("fxusddop");		
		select.add("fxusdchf");	
		select.add("fxusdgbp");	

		return select; 
	}
    
    
}
