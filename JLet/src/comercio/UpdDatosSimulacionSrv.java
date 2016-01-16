package comercio;


import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;


public class UpdDatosSimulacionSrv extends Service {

    public UpdDatosSimulacionSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("imptotal");
			input.addVariable("custotax");
			input.addVariable("consutax");
			input.addVariable("fletecst");
			input.addVariable("itbisimp");
			input.addVariable("tramadua");
			input.addVariable("almacena");
			input.addVariable("movconte");
			input.addVariable("cargnavi");
			input.addVariable("prcustotax");
			input.addVariable("prconsutax");
			input.addVariable("prfletecst");
			input.addVariable("pritbisimp");
			input.addVariable("prtramadua");
			input.addVariable("pralmacena");
			input.addVariable("prmovconte");
			input.addVariable("prcargnavi");
			input.addVariable("prmargorig");
			input.addVariable("prmargmpsp");
			input.addVariable("prmargizum");

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
    	String imptotal 	= "";
    	String custotax 	= "";
    	String consutax 	= "";
    	String fletecst 	= "";
    	String itbisimp 	= "";
    	String tramadua 	= "";
    	String almacena 	= "";
    	String movconte 	= "";
    	String cargnavi 	= "";
    	String prcustotax	= "";
    	String prconsutax	= "";
    	String prfletecst	= "";
    	String pritbisimp	= "";
    	String prtramadua	= "";
    	String pralmacena	= "";
    	String prmovconte	= "";
    	String prcargnavi	= "";
    	String prmargorig	= "";
    	String prmargmpsp	= "";
    	String prmargizum	= "";

        
        //Varibales de salida
        String txmensaj = "";
        String aniofact = "";
        String tipofact = "";
        Grid gridResu= null;
        
        //Otras Variables
        int nfactura = 0;
     
        try {
        	
        	imptotal = input.getStringValue("imptotal");    
        	custotax = input.getStringValue("custotax");    
        	consutax = input.getStringValue("consutax");    
        	fletecst = input.getStringValue("fletecst");    
        	itbisimp = input.getStringValue("itbisimp");    
        	tramadua = input.getStringValue("tramadua");    
        	almacena = input.getStringValue("almacena");    
        	movconte = input.getStringValue("movconte");    
        	cargnavi = input.getStringValue("cargnavi");    
        	prcustotax = input.getStringValue("prcustotax");
        	prconsutax = input.getStringValue("prconsutax");
        	prfletecst = input.getStringValue("prfletecst");
        	pritbisimp = input.getStringValue("pritbisimp");
        	prtramadua = input.getStringValue("prtramadua");
        	pralmacena = input.getStringValue("pralmacena");
        	prmovconte = input.getStringValue("prmovconte");
        	prcargnavi = input.getStringValue("prcargnavi");
        	prmargorig = input.getStringValue("prmargorig");
        	prmargmpsp = input.getStringValue("prmargmpsp");
        	prmargizum = input.getStringValue("prmargizum");

        	System.out.println("Valor imptotal || "+ imptotal +" - ");
        	System.out.println("Valor custotax || "+ custotax +" - ");
        	System.out.println("Valor consutax || "+ consutax +" - ");
        	System.out.println("Valor fletecst || "+ fletecst +" - ");
        	System.out.println("Valor itbisimp || "+ itbisimp +" - ");
        	System.out.println("Valor tramadua || "+ tramadua +" - ");
        	System.out.println("Valor almacena || "+ almacena +" - ");
        	System.out.println("Valor movconte || "+ movconte +" - ");
        	System.out.println("Valor cargnavi || "+ cargnavi +" - ");
        	System.out.println("Valor prcustotax || "+ prcustotax +" - ");
        	System.out.println("Valor prconsutax || "+ prconsutax +" - ");
        	System.out.println("Valor prfletecst || "+ prfletecst +" - ");
        	System.out.println("Valor pritbisimp || "+ pritbisimp +" - ");
        	System.out.println("Valor prtramadua || "+ prtramadua +" - ");
        	System.out.println("Valor pralmacena || "+ pralmacena +" - ");
        	System.out.println("Valor prmovconte || "+ prmovconte +" - ");
        	System.out.println("Valor prcargnavi || "+ prcargnavi +" - ");
        	System.out.println("Valor prmargorig || "+ prmargorig +" - ");
        	System.out.println("Valor prmargmpsp || "+ prmargmpsp +" - ");
        	System.out.println("Valor prmargizum || "+ prmargizum +" - ");
		    
		    output.setValue("txmensaj", "OK");
        	
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
	