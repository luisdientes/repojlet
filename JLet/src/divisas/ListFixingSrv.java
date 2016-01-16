package divisas;


import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import divisas.bd.ListFixingBD;
import divisas.bd.ListFixingBDIn;

public class ListFixingSrv extends Service {

	public ListFixingSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("fhdesdex");
			input.addVariable("fhhastax");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
			output.addVariable("gdFixing");
			output.addVariable("fhdesdex");
			output.addVariable("fhhastax");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String fhdesdex 	= "";
    	String fhhastax 	= "";
    

        //Varibales de salida
    	Grid gdFixing 	= null;
        
        //Otras Variables
        
        try {
        	
        	fhdesdex = input.getStringValue("fhdesdex");
        	fhhastax = input.getStringValue("fhhastax");
        	try {
        		
        		
        		ListFixingBDIn listFixingBDIn = new ListFixingBDIn();
        		listFixingBDIn.setValue("fhdesdex", fhdesdex);
        		listFixingBDIn.setValue("fhhastax", fhhastax);
        		ListFixingBD listFixingBD = new ListFixingBD(listFixingBDIn);
        		listFixingBD.setConnection(con);
        		gdFixing = listFixingBD.execSelect();
				
        	} catch (Exception e) {
        		System.err.println(this.getClass().getName() +" [ERROR ] Recuperando subastas. "+ e.getMessage());
        	}
        	
        	output.setValue("gdFixing", gdFixing);
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
    
    
}
	