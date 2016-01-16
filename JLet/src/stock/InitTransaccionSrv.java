package stock;


import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import common.bd.ListClientesBD;
import common.bd.ListClientesBDIn;

import factura.bd.ListTipoFacBD;
import factura.bd.ListTipoFacBDIn;


public class InitTransaccionSrv extends Service {

    public InitTransaccionSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();	
			input.addVariable("idemisor");
			input.addVariable("tpclient");
			input.addVariable("tptransa");
			input.addVariable("listimei");

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
			output.addVariable("tpclient");
			output.addVariable("tptransa");
			output.addVariable("listimei");
			output.addVariable("gridInte");
			output.addVariable("gridExte");
			output.addVariable("gridTpFa");
			
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor = null;
    	String tpclient = null;
    	String tptransa = null;
    	String listimei = null;
    	String modofact = "F";
        
        //Varibales de salida
    	Grid gridInte = null;
    	Grid gridExte = null;
    	Grid gridTpFa = null;
    	
        //Otras Variables

    	try {

    		idemisor = input.getStringValue("idemisor");
    		tpclient = input.getStringValue("tpclient");
			tptransa = input.getStringValue("tptransa");
			listimei = input.getStringValue("listimei");
        	
	        try {
	        	ListClientesBDIn lineasBDIn= new ListClientesBDIn();
	        	lineasBDIn.setValue("cdintern","0");
	        	//lineasBDIn.setValue("tpclient",tpclient);
	        	ListClientesBD lineasBD = new ListClientesBD(lineasBDIn);
	        	lineasBD.setConnection(con);
	        	gridInte = lineasBD.execSelect();
	        	
	        	lineasBDIn= new ListClientesBDIn();
	        	lineasBDIn.setValue("idemisor",idemisor);
	        	//lineasBDIn.setValue("tpclient",tpclient);
	        	lineasBD = new ListClientesBD(lineasBDIn);
	        	lineasBD.setConnection(con);
	        	gridExte = lineasBD.execSelect();
	        	
	        	
	        	ListTipoFacBDIn ListTipoBDIn  = new ListTipoFacBDIn();  
	        	ListTipoBDIn.setValue("cdemisor", idemisor);
	        	ListTipoBDIn.setValue("modofact", modofact);
	        	
	        	ListTipoFacBD ListTipoBD = new ListTipoFacBD(ListTipoBDIn);
	        	ListTipoBD.setConnection(con);
	        	gridTpFa = ListTipoBD.execSelect();
	        		        		        	
	        } catch (Exception e) {
				System.err.println(this.getClass().getName() +" ERROR recogiendo lineas. "+ e.getMessage());
			}        				
        	
			output.setValue("idemisor", idemisor);
			//output.setValue("tpclient", tpclient);
			output.setValue("tptransa", tptransa);
		    output.setValue("listimei", listimei);
			output.setValue("gridInte", gridInte);
		    output.setValue("gridExte", gridExte);
		    output.setValue("gridTpFa", gridTpFa);

        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
}
	