package comercio;


import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import comercio.bd.DetalleEnvioBD;
import comercio.bd.DetalleEnvioBDIn;
import comercio.bd.ListEmpreEnvioBD;
import comercio.bd.ListEmpreEnvioBDIn;


public class AltaDetalleEnvioSrv extends Service {

    public AltaDetalleEnvioSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();	
			input.addVariable("idtradet");
			input.addVariable("codeenvi");
			input.addVariable("idempres");
			input.addVariable("medenvio");
			input.addVariable("txidenti");
			input.addVariable("perconta");
			input.addVariable("tfnocont");
			input.addVariable("txmailxx");
			input.addVariable("precioen");
			input.addVariable("impuesto");
			input.addVariable("txdivisa");
			input.addVariable("pagenvio");
			input.addVariable("totalenv");
			input.addVariable("fhrecogi");
			input.addVariable("horareco");
			input.addVariable("fhentreg");
			input.addVariable("horaentr");
			input.addVariable("cdestado");
			input.addVariable("mostrdet");
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
			output.addVariable("txmensaj");
			output.addVariable("codeenvi");
			output.addVariable("gridDeta");
			output.addVariable("grEmpres");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor = "";
    	String idtradet = "";
    	String codeenvi = "";
    	String idempres = "";
    	String medenvio = "";
    	String txidenti = "";
    	String perconta = "";
    	String tfnocont = "";
    	String txmailxx = "";
    	String precioen = "";
    	String impuesto = "";
    	String txdivisa = "";
    	String pagenvio = "";
    	String totalenv = "";
    	String fhrecogi = "";
    	String horareco = "";
    	String fhentreg = "";
    	String horaentr = "";
    	String cdestado = "";
    	String mostrdet = "";
        
        //Varibales de salida
    	Grid gridDeta = null;
    	Grid grEmpres = null;
    	String listimei = null;
    	String txmensaj = null;
    	int insertado = 0;
    	
        //Otras Variables
    	
        try {
     
        	int numeroIme = 0;
        	int totalinse = 0;
        	idtradet = input.getStringValue("idtradet");
        	codeenvi = input.getStringValue("codeenvi");
        	idempres = input.getStringValue("idempres");
        	medenvio = input.getStringValue("medenvio");
        	txidenti = input.getStringValue("txidenti");
        	perconta = input.getStringValue("perconta");
        	tfnocont = input.getStringValue("tfnocont");
        	txmailxx = input.getStringValue("txmailxx");
        	precioen = input.getStringValue("precioen");
        	impuesto = input.getStringValue("impuesto");
        	txdivisa = input.getStringValue("txdivisa");
        	pagenvio = input.getStringValue("pagenvio");
        	totalenv = input.getStringValue("totalenv");
        	fhrecogi = input.getStringValue("fhrecogi");
        	horareco = input.getStringValue("horareco");
        	fhentreg = input.getStringValue("fhentreg");
        	horaentr = input.getStringValue("horaentr");
        	cdestado = input.getStringValue("cdestado");
        	mostrdet = input.getStringValue("mostrdet");
        	idemisor = input.getStringValue("idemisor");

	        try {
	        	
	        	if( mostrdet!=null && mostrdet.equalsIgnoreCase("S") ){
	        		
	        		DetalleEnvioBDIn DetaBDIn= new DetalleEnvioBDIn();
		        	DetaBDIn.setValue("idenviox",codeenvi);
		        	DetalleEnvioBD DetaBD = new DetalleEnvioBD(DetaBDIn);
		        	DetaBD.setConnection(con);
		        	gridDeta = DetaBD.execSelect();
		        	grEmpres = getEmpresas(idemisor);
	        	
	        	}else{
	        		
	        		
	        		
	        		
	        		fhrecogi =fechaMySQL(fhrecogi);
	        		fhentreg =fechaMySQL(fhentreg);
	        	
		        	DetalleEnvioBDIn InsDetaBDIn= new DetalleEnvioBDIn();
		        	InsDetaBDIn.setValue("idenviox",codeenvi);
		        	InsDetaBDIn.setValue("idempres",idempres);
		        	InsDetaBDIn.setValue("medenvio",medenvio);
		        	InsDetaBDIn.setValue("txidenti",txidenti);
		        	InsDetaBDIn.setValue("perconta",perconta);
		        	InsDetaBDIn.setValue("tfnocont",tfnocont);
		        	InsDetaBDIn.setValue("txmailxx",txmailxx);
		        	InsDetaBDIn.setValue("precioen",precioen);
		        	InsDetaBDIn.setValue("impuesto",impuesto);
		        	InsDetaBDIn.setValue("txdivisa",txdivisa);
		        	InsDetaBDIn.setValue("pagenvio",pagenvio);
		        	InsDetaBDIn.setValue("totalenv",totalenv);
		        	InsDetaBDIn.setValue("fhrecogi",fhrecogi);
		        	InsDetaBDIn.setValue("horareco",horareco);
		        	InsDetaBDIn.setValue("fhentreg",fhentreg);
		        	InsDetaBDIn.setValue("horaentr",horaentr);
		        	InsDetaBDIn.setValue("cdestado",cdestado);
		        	InsDetaBDIn.setValue("idtradet",idtradet);
		        	DetalleEnvioBD InsDetaBD = new DetalleEnvioBD(InsDetaBDIn);
		        	InsDetaBD.setConnection(con);
		        	
		         	if( idtradet!=null && !idtradet.equalsIgnoreCase("") ){
		         		insertado = InsDetaBD.execUpdate();
		         		txmensaj = " Se ha actualizado correctamente el detalle del Envío"; 
		         	}else{
		         		txmensaj = " Se han insertado correctamente el detalle del Envío"; 
		         		insertado = InsDetaBD.execInsert();	
		         	}
		        	
		        	
		        	
		        	if((insertado <1)){
		    			txmensaj = "ERROR -- Fallo el insertar el detalle del envío";
		    		}
		        }	

	        
	        } catch (Exception e) {
				System.err.println(this.getClass().getName() +"  ERROR recogiendo lineas. "+ e.getMessage());
				txmensaj = "Error al crear Envio";
			}
        	
		    output.setValue("gridDeta", gridDeta);
		    output.setValue("codeenvi", codeenvi);
		    output.setValue("grEmpres", grEmpres);
		    output.setValue("txmensaj", txmensaj);

        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
    
  public Grid getEmpresas(String idemisor){
    	
    	Grid gdLineas = null;
    	
    	try{
    		
    		ListEmpreEnvioBDIn empreBDIn= new ListEmpreEnvioBDIn();
    		empreBDIn.setValue("idemisor",idemisor);
    		ListEmpreEnvioBD lineasBD = new ListEmpreEnvioBD(empreBDIn);
    		lineasBD.setConnection(con);
    		gdLineas = lineasBD.execSelect();
    		
    	}catch(Exception ex){
    		System.out.println("Error al recuperar Empresas --Envio");
    	}
    	
		return gdLineas;	
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
	