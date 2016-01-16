package factura;


import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import factura.bd.ListAlbaranesBD;
import factura.bd.ListAlbaranesBDIn;


public class ListAlbaranesSrv extends Service {

    public ListAlbaranesSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();						 			
			
			input.addVariable("idemisor");
			input.addVariable("tpclient");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
			output.addVariable("grAlbara");
			output.addVariable("idemisor");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor = null;
    	String tpclient = null;
        
        //Varibales de salida
        Grid gridAlba = null;
        
        
        //Otras Variables
     
        
        try{        	
        	
        	idemisor = input.getStringValue("idemisor");
        	tpclient = input.getStringValue("tpclient");
        	
        	// tipo facturas
        	ListAlbaranesBDIn ListTipoBDIn  = new ListAlbaranesBDIn();  
        	ListTipoBDIn.setValue("idemisor", idemisor);
        	ListTipoBDIn.setValue("tpclient", tpclient);
        	ListAlbaranesBD ListTipoBD = new ListAlbaranesBD(ListTipoBDIn);
        	ListTipoBD.setConnection(con);
        	gridAlba = ListTipoBD.execSelect();
        	
        	//RECUPERO LOS VALORES DEL INPUT

        	output.setValue("grAlbara", gridAlba);
        	output.setValue("idemisor",idemisor);
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
       
}