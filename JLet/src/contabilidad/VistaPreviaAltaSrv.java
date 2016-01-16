package contabilidad;


import common.bd.ListDivEmisorBD;
import common.bd.ListDivEmisorBDIn;

import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import contabilidad.bd.ListCuentasBD;
import contabilidad.bd.ListCuentasBDIn;

public class VistaPreviaAltaSrv extends Service {

	public VistaPreviaAltaSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("idemisor");
			input.addVariable("txnombre");
			input.addVariable("idcuenta");
			input.addVariable("cantidad");
			input.addVariable("debhaber");
			input.addVariable("concepto");
			input.addVariable("fhapunte");
			input.addVariable("coddocum");
			input.addVariable("documint");
			input.addVariable("tpapunte");
			input.addVariable("numeroid");
			input.addVariable("cdpaisxx");
			input.addVariable("tipocuen");
			input.addVariable("idapunte");
			input.addVariable("cddivisa");
			input.addVariable("filedocu");
			
		
			
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
			output.addVariable("idcuenta");
			output.addVariable("cantidad");
			output.addVariable("debhaber");
			output.addVariable("concepto");
			output.addVariable("fhapunte");
			output.addVariable("coddocum");
			output.addVariable("documint");
			output.addVariable("tpapunte");
			output.addVariable("numeroid");
			output.addVariable("txnombre");
			output.addVariable("cdpaisxx");
			output.addVariable("tipocuen");
			output.addVariable("cddivisa");
			output.addVariable("idapunte");
			output.addVariable("filedocu");
			
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor = "";
    	String tpclient = "";
    	String idcuenta = "";
    	String txnombre = "";
    	String cdpaisxx = "";
    	String tipocuen = "";
    	String numeroid = "";
    	String debhaber = "";
    	String concepto = "";
    	String fhapunte = "";
    	String coddocum = "";
    	String tpapunte = "";
    	String cantidad = "";
    	String cddivisa = "";
    	String idapunte = "";
    	String documint = "";
    	String filedocu = "";
    	
    	Grid gridDivi = null;
    

        //Varibales de salida
    	
        
        //Otras Variables
        
        try {
        	
        	idemisor = input.getStringValue("idemisor");
        	idcuenta = input.getStringValue("idcuenta");
        	txnombre = input.getStringValue("txnombre");
        	cantidad = input.getStringValue("cantidad");
        	cdpaisxx = input.getStringValue("cdpaisxx");
        	tipocuen = input.getStringValue("tipocuen");
        	numeroid = input.getStringValue("numeroid");
        	debhaber = input.getStringValue("debhaber");
        	concepto = input.getStringValue("concepto");
        	fhapunte = input.getStringValue("fhapunte");
        	coddocum = input.getStringValue("coddocum");
        	tpapunte = input.getStringValue("tpapunte");
        	idapunte = input.getStringValue("idapunte");
        	cddivisa = input.getStringValue("cddivisa");
        	documint = input.getStringValue("documint");
        	filedocu = input.getStringValue("filedocu");
 
        	output.setValue("idemisor", idemisor);
        	output.setValue("idcuenta", idcuenta);
        	output.setValue("txnombre", txnombre);
        	output.setValue("cantidad", cantidad);	
        	output.setValue("cdpaisxx", cdpaisxx);
        	output.setValue("tipocuen", tipocuen);
        	output.setValue("numeroid", numeroid);
        	output.setValue("debhaber", debhaber);
        	output.setValue("concepto", concepto);
        	output.setValue("fhapunte", fhapunte);
        	output.setValue("coddocum", coddocum);
        	output.setValue("tpapunte", tpapunte);
        	output.setValue("cddivisa", cddivisa);
        	output.setValue("idapunte", idapunte);
        	output.setValue("documint", documint);
        	output.setValue("filedocu", filedocu);

        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
    
    
}
	