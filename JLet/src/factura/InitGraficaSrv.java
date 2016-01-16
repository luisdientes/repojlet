package factura;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import arquitectura.controller.Service;
import arquitectura.objects.ObjectIO;


public class InitGraficaSrv extends Service {
	
	HashMap<String,String> permEmis = new HashMap<String,String>();
	
    public InitGraficaSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();						 			
			
			input.addVariable("idemisor");
			
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
			output.addVariable("fhdesdex");
			output.addVariable("fhhastax");
			
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor = null;
        
    	//Variables de salida
    	String fhdesdex = "";
    	String fhhastax = "";
    	
        //Otras Variables
     
        try{        	
        	        	
        	idemisor = input.getStringValue("idemisor");
        	fhdesdex = fechaHoy();
        	fhhastax = fhdesdex;
        	
        	System.out.println("Idemisor: "+ idemisor);
        	
        	output.setValue("idemisor", idemisor);
        	output.setValue("fhdesdex", fhdesdex);
        	output.setValue("fhhastax", fhhastax);
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
    public String fechaHoy(){
        
    	String fechaHoy = "dd/mm/yyyy";
    	
    	try {
		    Calendar cal = GregorianCalendar.getInstance();
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    
		    System.out.println(" Fecha Hoy: "+sdf.format(cal.getTime()));
		    fechaHoy = sdf.format(cal.getTime());
		    
    	} catch (Exception e) {
    		fechaHoy = "01/01/2014";
    	}
    	
		return fechaHoy;
	    
    }
    
       
}