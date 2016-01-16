package factura;



import arquitectura.controller.Service;
import arquitectura.objects.ObjectIO;
import factura.bd.UpdLineasBD;
import factura.bd.UpdLineasBDIn;


public class ValidarFacturaSrv extends Service {

    public ValidarFacturaSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();	
							 			
		
			input.addVariable("idclient");
			input.addVariable("tpclient");
			input.addVariable("idemisor");
			input.addVariable("fhfactur");	
				
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
			output.addVariable("txmensaj");

		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
   
    
		String idclient = "";
		String tpclient = "";
	    String idemisor = "";
		String fechafac = "";
        //Varibales de salida
        String txmensaj = "";
        String aniofact = "";

        try {
			
			idclient = input.getStringValue("idclient");
			tpclient = input.getStringValue("tpclient");
		    idemisor = input.getStringValue("idemisor");
			fechafac = input.getStringValue("fhfactur");
			
			
			if(fechafac!=null && !fechafac.equals("")){
				aniofact = fechafac.substring(6, 10);
			}	
			
			fechafac = fechaMySQL(fechafac);
		    
		    try{        	
	    			UpdLineasBDIn InsLinBDIn= new UpdLineasBDIn();
	    			InsLinBDIn.setValue("idclient",idclient);
	    			InsLinBDIn.setValue("tpclient",tpclient);
	    			InsLinBDIn.setValue("fechafac",fechafac);
	    			InsLinBDIn.setValue("idemisor",idemisor);
	    			InsLinBDIn.setValue("cdestado","V");
	    			UpdLineasBD insLinBD = new UpdLineasBD(InsLinBDIn);
		    		insLinBD.setConnection(con);
		    		int liUpdate = insLinBD.execUpdate();
		    		int listock =0;
							
				
							if (liUpdate > 0){
								txmensaj = "Se ha validado la factura correctamente.";
						    } else {
						    	txmensaj = "Se ha producido un error al validar la factura ("+ liUpdate +")";
						    }

	        	//RECUPERO LOS VALORES DEL INPUT
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	output.setValue("txmensaj", txmensaj);

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
	