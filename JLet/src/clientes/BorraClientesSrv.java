package clientes;


import clientes.bd.FacturasClientesBD;
import clientes.bd.FacturasClientesBDIn;
import clientes.bd.LineasTmpClientesBD;
import clientes.bd.LineasTmpClientesBDIn;
import clientes.bd.UpdClientesBD;
import clientes.bd.UpdClientesBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import common.ExcelCreator;
import common.bd.ListClientesBD;
import common.bd.ListClientesBDIn;


public class BorraClientesSrv extends Service {

	ExcelCreator creador = null;
	
	public BorraClientesSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			 input.addVariable("idemisor");
			 input.addVariable("tpclient");
			 input.addVariable("idclient");
			 input.addVariable("clientea");
			 
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
			output.addVariable("txrazons");
			output.addVariable("gdClient");
			output.addVariable("txmensaj");
			output.addVariable("factasoc");
			output.addVariable("clientea");
			output.addVariable("asignafa");
			
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor = "";
    	String tpclient = ""; 
    	String idclient = ""; 
    	String txrazons = "";
    	String factasoc = "0";
    	String asignafa = "";
    	String clientea = "";
    	String txmensaj = "";
        //Varibales de salida
    	Grid gdClient = null;
    	Grid gdFactur = null;
        
        //Otras Variables
        
        
        try {

        	idemisor = input.getStringValue("idemisor");
        	tpclient = input.getStringValue("tpclient");
        	idclient = input.getStringValue("idclient"); 
        	clientea = input.getStringValue("clientea");
        		
        	try{
		       		if (clientea!= null && !clientea.equals("")){
		       		
			       		FacturasClientesBDIn factCliBDIn = new FacturasClientesBDIn();
			       		factCliBDIn.setValue("idemisor",idemisor);
			       		factCliBDIn.setValue("tpclient",tpclient);
			       		factCliBDIn.setValue("idclient",idclient);
			       		factCliBDIn.setValue("clientea",clientea);
			       		FacturasClientesBD factCliBD = new FacturasClientesBD(factCliBDIn);
			       		factCliBD.setConnection(con);
			       	
			       		factCliBD.execUpdate();
			       		
			       		LineasTmpClientesBDIn lineasCliBDIn = new LineasTmpClientesBDIn();
			       		lineasCliBDIn.setValue("idemisor",idemisor);
			       		lineasCliBDIn.setValue("tpclient",tpclient);
			       		lineasCliBDIn.setValue("idclient",idclient);
			       		lineasCliBDIn.setValue("clientea",clientea);
			       		LineasTmpClientesBD lineasCliBD = new LineasTmpClientesBD(lineasCliBDIn);
			       		lineasCliBD.setConnection(con);
			       		lineasCliBD.execUpdate();
			       		idclient = clientea;
			       	}
		       		
		       		//borrar clientea
		       		
		       		FacturasClientesBDIn factCliBDIn = new FacturasClientesBDIn();
		       		factCliBDIn.setValue("idemisor",idemisor);
		       		factCliBDIn.setValue("tpclient",tpclient);
		       		factCliBDIn.setValue("clientea",idclient);
		       		FacturasClientesBD factCliBD = new FacturasClientesBD(factCliBDIn);
		       		factCliBD.setConnection(con);
		       		 factCliBD.execDelete();
		       		 
		       		UpdClientesBDIn upCliBDIn = new UpdClientesBDIn();
	    			upCliBDIn.setValue("idclient",idclient);
	    			upCliBDIn.setValue("tpclient",tpclient);
	    			upCliBDIn.setValue("idemisor",idemisor);
	    			UpdClientesBD upCliBD = new UpdClientesBD(upCliBDIn);
	    			upCliBD.setConnection(con);
	    			upCliBD.execDelete();
		       		txmensaj = "Cliente eliminado correctamente";
        		}catch(Exception ex){
        			System.out.println("Error al borrar o asignar facturas");
        			System.out.println(ex.getMessage());
        			txmensaj = "Error al eliminar cliente";
        		}
		        			
        	output.setValue("idemisor", idemisor);
        	output.setValue("tpclient", tpclient);
        	output.setValue("gdClient", gdClient);
        	output.setValue("factasoc", factasoc);
        	output.setValue("asignafa", asignafa);
        	output.setValue("clientea", clientea);
        	
        	output.setValue("txmensaj", txmensaj);
        
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }

}
	