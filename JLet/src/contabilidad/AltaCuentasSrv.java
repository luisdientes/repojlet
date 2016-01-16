package contabilidad;


import common.bd.ListDivEmisorBD;
import common.bd.ListDivEmisorBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import contabilidad.bd.ListCuentasBD;
import contabilidad.bd.ListCuentasBDIn;

public class AltaCuentasSrv extends Service {

	public AltaCuentasSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("idemisor");
			input.addVariable("txnombre");
			input.addVariable("txdetall");
			input.addVariable("cdpaisxx");
			input.addVariable("cddivisa");
			input.addVariable("tipocuen");
			input.addVariable("numeroid");
			
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
			output.addVariable("txmensaj");
			
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
    	
    	String txnombre = "";
    	String txdetall = "";
    	String cdpaisxx = "";
    	String cddivisa = "";
    	String tipocuen = "";
    	String numeroid = "";
    	
    	String txmensaj = "";
    
    

        //Varibales de salida
    	Grid gdCuentas 	= null;
    	Grid gridDivi = null;
        
        //Otras Variables
        
        try {
        	
        	idemisor = input.getStringValue("idemisor");
        	txnombre = input.getStringValue("txnombre");
        	txdetall = input.getStringValue("txdetall");
        	cdpaisxx = input.getStringValue("cdpaisxx");
        	cddivisa = input.getStringValue("cddivisa");
        	tipocuen = input.getStringValue("tipocuen");
        	numeroid = input.getStringValue("numeroid");
        	
        	if(cddivisa != null && cddivisa.equals("EUR")){
        		cddivisa ="&euro;";
        	}
        	
        	try {
        		ListCuentasBDIn listCuentasBDIn = new ListCuentasBDIn();
        		listCuentasBDIn.setValue("idemisor", idemisor);
        		listCuentasBDIn.setValue("txnombre", txnombre);
        		listCuentasBDIn.setValue("txdetall", txdetall);
        		listCuentasBDIn.setValue("cdpaisxx", cdpaisxx);
        		listCuentasBDIn.setValue("cddivisa", cddivisa);
        		listCuentasBDIn.setValue("tipocuen", tipocuen);
        		listCuentasBDIn.setValue("numeroid", numeroid);
        		ListCuentasBD listCuentasBD = new ListCuentasBD(listCuentasBDIn);
        		listCuentasBD.setConnection(con);
        		int i = listCuentasBD.execInsert();
        		
        		if( i > 0){
        			txmensaj ="Cuenta dada de alta correctamente";
        		}else{
        			txmensaj ="Fallo al dar de alta cuenta";
        		}
				
        	} catch (Exception e) {
        		System.err.println(this.getClass().getName() +" [ERROR ] Alta cuentas. "+ e.getMessage());
        	}
        	
         	
        	output.setValue("txmensaj", txmensaj);
        	output.setValue("idemisor", idemisor);
        	output.setValue("gdCuentas", gdCuentas);
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
    
    
}
	