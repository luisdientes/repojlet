package contabilidad;


import common.bd.ListDivEmisorBD;
import common.bd.ListDivEmisorBDIn;

import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import contabilidad.bd.ListCuentasBD;
import contabilidad.bd.ListCuentasBDIn;

public class InitCuentasSrv extends Service {

	public InitCuentasSrv() {
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
			output.addVariable("cddivisa");
			output.addVariable("gdCuentas");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor 	= "";
    	String cddivisa = "";
    
    

        //Varibales de salida
    	Grid gdCuentas 	= null;
    	Grid gridDivi = null;
        
        //Otras Variables
        
        try {
        	
        	idemisor = input.getStringValue("idemisor");
        	try {
        		ListCuentasBDIn listCuentasBDIn = new ListCuentasBDIn();
        		listCuentasBDIn.setValue("idemisor", idemisor);
        		ListCuentasBD listCuentasBD = new ListCuentasBD(listCuentasBDIn);
        		listCuentasBD.setConnection(con);
        		gdCuentas = listCuentasBD.execSelect();
				
        	} catch (Exception e) {
        		System.err.println(this.getClass().getName() +" [ERROR ] Recuperando subastas. "+ e.getMessage());
        	}
        	
         	
            //// divisa
			ListDivEmisorBDIn divisaBDIn= new ListDivEmisorBDIn();
			divisaBDIn.setValue("idemisor", idemisor);
			ListDivEmisorBD divisaBD = new ListDivEmisorBD(divisaBDIn);
			divisaBD.setConnection(con);
			gridDivi = divisaBD.execSelect();
			cddivisa = gridDivi.getStringCell(0, "divsimbo");
        	
        	output.setValue("idemisor", idemisor);
        	output.setValue("cddivisa", cddivisa);
        	output.setValue("gdCuentas", gdCuentas);
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
    
    
}
	