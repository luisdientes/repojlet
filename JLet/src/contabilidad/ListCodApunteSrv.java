package contabilidad;


import common.bd.ListDivEmisorBD;
import common.bd.ListDivEmisorBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import contabilidad.bd.ListCodApunteBD;
import contabilidad.bd.ListCodApuntesBDIn;
import contabilidad.bd.ListCuentasBD;
import contabilidad.bd.ListCuentasBDIn;
import contabilidad.bd.UpdateApunteBD;
import contabilidad.bd.UpdateApunteBDIn;

public class ListCodApunteSrv extends Service {

	public ListCodApunteSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("idemisor");
			input.addVariable("txnombre");
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
			output.addVariable("idemisor");
			output.addVariable("txnombre");
			output.addVariable("cddivisa");
			output.addVariable("gdCodApu");
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor = "";
    	String txnombre = "";
    	String cddivisa = "";
    	Grid gdCodApu = null;
    	Grid gridDivi = null;
    
    

        //Varibales de salida
    	
    	String txmensaj = "";
        //Otras Variables
        
        try {
        	
        	idemisor = input.getStringValue("idemisor");
        	txnombre = input.getStringValue("txnombre");
        	
        	/*SACO LA CUENTA PARA SACAR EL SALDO*/
        		try {
	        		ListCodApuntesBDIn listCodigoBDIn = new ListCodApuntesBDIn();
	        		listCodigoBDIn.setValue("idemisor", idemisor);
	        		listCodigoBDIn.setValue("txnombre", txnombre);
	        		ListCodApunteBD listCodigoBD = new ListCodApunteBD(listCodigoBDIn);
	        		listCodigoBD.setConnection(con);
	        		gdCodApu = listCodigoBD.execSelect();
	        		
	        		
        		}catch(Exception ex){
        			
        		}
        	
       //// divisa
       			ListDivEmisorBDIn divisaBDIn= new ListDivEmisorBDIn();
       			divisaBDIn.setValue("idemisor", idemisor);
       			ListDivEmisorBD divisaBD = new ListDivEmisorBD(divisaBDIn);
       			divisaBD.setConnection(con);
       			gridDivi = divisaBD.execSelect();
       			cddivisa = gridDivi.getStringCell(0, "divsimbo");
       			
       			
        	output.setValue("idemisor", idemisor);
        	output.setValue("txnombre", txnombre);
        	output.setValue("gdCodApu", gdCodApu);	
        	output.setValue("cddivisa", cddivisa);
        	
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
public String fechaMySQL(String fhfechax){
        
  		String fhMySql = "";
  		try {
  			String [] arrFecha = fhfechax.split("/");
  			fhMySql = arrFecha[2]+"-"+ arrFecha[1]+"-"+arrFecha[0];
  		} catch (Exception e) {
  			return "0000-00-00";
  		}
  		return fhMySql;
      }
    
    
    
}
	