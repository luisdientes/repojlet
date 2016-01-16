package comercio;


import java.text.DecimalFormat;

import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;


public class ListSimuladorSrv extends Service {

    public ListSimuladorSrv() {
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
			output.addVariable("imptotal");
			output.addVariable("custotax");
			output.addVariable("consutax");
			output.addVariable("fletecst");
			output.addVariable("itbisimp");
			output.addVariable("tramadua");
			output.addVariable("almacena");
			output.addVariable("movconte");
			output.addVariable("cargnavi");
			output.addVariable("prcustotax");
			output.addVariable("prconsutax");
			output.addVariable("prfletecst");
			output.addVariable("pritbisimp");
			output.addVariable("prtramadua");
			output.addVariable("pralmacena");
			output.addVariable("prmovconte");
			output.addVariable("prcargnavi");
			output.addVariable("prmargorig");
			output.addVariable("prmargmpsp");
			output.addVariable("prmargizum");
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

    	double dimptotal 	= 0;
    	double dcustotax 	= 0;
    	double dconsutax 	= 0;
    	double dfletecst 	= 0;
    	double ditbisimp 	= 0;
    	double dtramadua 	= 0;
    	double dalmacena 	= 0;
    	double dmovconte 	= 0;
    	double dcargnavi 	= 0;
    	double dprcustotax	= 0;
    	double dprconsutax	= 0;
    	double dprfletecst	= 0;
    	double dpritbisimp	= 0;
    	double dprtramadua	= 0;
    	double dpralmacena	= 0;
    	double dprmovconte	= 0;
    	double dprcargnavi	= 0;
    	double dprmargorig	= 0;
    	double dprmargmpsp	= 0;
    	double dprmargizum	= 0;
        
        //Varibales de salida
        String txmensaj = "";
        String aniofact = "";
        String tipofact = "";
        Grid gridResu= null;
        
        //Otras Variables
        int nfactura = 0;
     
        DecimalFormat deciForm = new DecimalFormat("0.00#");
        
        try {
        	
        	imptotal = input.getStringValue("imptotal").replaceAll(",",".");    
        	custotax = input.getStringValue("custotax").replaceAll(",",".");    
        	consutax = input.getStringValue("consutax").replaceAll(",",".");    
        	fletecst = input.getStringValue("fletecst").replaceAll(",",".");    
        	itbisimp = input.getStringValue("itbisimp").replaceAll(",",".");    
        	tramadua = input.getStringValue("tramadua").replaceAll(",",".");    
        	almacena = input.getStringValue("almacena").replaceAll(",",".");    
        	movconte = input.getStringValue("movconte").replaceAll(",",".");    
        	cargnavi = input.getStringValue("cargnavi").replaceAll(",",".");    
        	prcustotax = input.getStringValue("prcustotax").replaceAll(",",".");
        	prconsutax = input.getStringValue("prconsutax").replaceAll(",",".");
        	prfletecst = input.getStringValue("prfletecst").replaceAll(",",".");
        	pritbisimp = input.getStringValue("pritbisimp").replaceAll(",",".");
        	prtramadua = input.getStringValue("prtramadua").replaceAll(",",".");
        	pralmacena = input.getStringValue("pralmacena").replaceAll(",",".");
        	prmovconte = input.getStringValue("prmovconte").replaceAll(",",".");
        	prcargnavi = input.getStringValue("prcargnavi").replaceAll(",",".");
        	prmargorig = input.getStringValue("prmargorig").replaceAll(",",".");
        	prmargmpsp = input.getStringValue("prmargmpsp").replaceAll(",",".");
        	prmargizum = input.getStringValue("prmargizum").replaceAll(",",".");
        	
        	try {
        		dimptotal   = Double.parseDouble(imptotal);
        	} catch (Exception e) {
        		new Exception("No se ha recibido el Importe Total");
        	}
        	
        	try {
        		dcustotax   = Double.parseDouble(custotax);
        	} catch (Exception e) {
        		
        	}
        	
        	try {
        		dconsutax   = Double.parseDouble(consutax);
        	} catch (Exception e) {
        		
        	}
        	
        	try {
        		dfletecst   = Double.parseDouble(fletecst);
        	} catch (Exception e) {
        		
        	}
        	try {
        		ditbisimp   = Double.parseDouble(itbisimp);
        	} catch (Exception e) {
        		
        	}
        	try {
        		dtramadua   = Double.parseDouble(tramadua);
        	} catch (Exception e) {
        		
        	}
        	try {
        		dalmacena   = Double.parseDouble(almacena);
        	} catch (Exception e) {
        		
        	}
        	try {
        		dmovconte   = Double.parseDouble(movconte);
        	} catch (Exception e) {
        		
        	}
        	try {
        		 dcargnavi   = Double.parseDouble(cargnavi);
        	} catch (Exception e) {
        		
        	}
        	try {
        		dprcustotax = Double.parseDouble(prcustotax) / 100;
        	} catch (Exception e) {
        		if (dcustotax > 0){
        			dprcustotax = dcustotax / dimptotal;
        		}
        	}
        	try {
        		dprconsutax = Double.parseDouble(prconsutax) / 100;
        	} catch (Exception e) {
        		if (dconsutax > 0){
        			dprconsutax = dconsutax / dimptotal;
        		}
        	}
        	try {
        		dprfletecst = Double.parseDouble(prfletecst) / 100;
        	} catch (Exception e) {
        		if (dfletecst > 0){
        			dprfletecst = dfletecst / dimptotal;
        		}
        	}
        	try {
        		dpritbisimp = Double.parseDouble(pritbisimp) / 100;
        	} catch (Exception e) {
        		if (ditbisimp > 0){
        			dpritbisimp = ditbisimp / dimptotal;
        		}
        	}
        	try {
        		dprtramadua = Double.parseDouble(prtramadua) / 100;
        	} catch (Exception e) {
        		if (dtramadua > 0){
        			dprtramadua = dtramadua / dimptotal;
        		}
        	}
        	try {
        		dpralmacena = Double.parseDouble(pralmacena) / 100;
        	} catch (Exception e) {
        		if (dalmacena > 0){
        			dpralmacena = dalmacena / dimptotal;
        		}
        	}
        	try {
        		dprmovconte = Double.parseDouble(prmovconte) / 100;
        	} catch (Exception e) {
        		if (dmovconte > 0){
        			dprmovconte = dmovconte / dimptotal;
        		}
        	}
        	try {
        		dprcargnavi = Double.parseDouble(prcargnavi) / 100;
        	} catch (Exception e) {
        		if (dcargnavi > 0){
        			dprcargnavi = dcargnavi / dimptotal;
        		}
        	}
        	try {
        		dprmargorig = Double.parseDouble(prmargorig) / 100;
        	} catch (Exception e) {
        		
        	}
        	try {
        		dprmargmpsp = Double.parseDouble(prmargmpsp) / 100;
        	} catch (Exception e) {
        		
        	}
        	try {
        		dprmargizum = Double.parseDouble(prmargizum) / 100;
        	} catch (Exception e) {
        		
        	}        	        
        	
        	//UNA VEZ OBTENIDOS LOS PORCENTAJES, CALCULO LOS IMPORTES TOTALES
        	if (dcustotax <= 0){
        		dcustotax = dimptotal * dprcustotax;
        	}
        	
        	if (dconsutax <= 0){
        		dconsutax = dimptotal * dprconsutax;
        	}
        	if (dfletecst <= 0){
        		dfletecst = dimptotal * dprfletecst;
        	}
        	if (ditbisimp <= 0){
        		ditbisimp = dimptotal * dpritbisimp;
        	}
        	if (dtramadua <= 0){
        		dtramadua = dimptotal * dprtramadua;
        	}
        	if (dalmacena <= 0){
        		dalmacena = dimptotal * dpralmacena;
        	}
        	if (dmovconte <= 0){
        		dmovconte = dimptotal * dprmovconte;
        	}
        	if (dcargnavi <= 0){
        		dcargnavi = dimptotal * dprcargnavi;
        	}
        	
        	System.out.println("Valor imptotal -| "+ dimptotal +" - ");
        	System.out.println("Valor custotax -| "+ dcustotax +" - ");
        	System.out.println("Valor consutax -| "+ dconsutax +" - ");
        	System.out.println("Valor fletecst -| "+ dfletecst +" - ");
        	System.out.println("Valor itbisimp -| "+ ditbisimp +" - ");
        	System.out.println("Valor tramadua -| "+ dtramadua +" - ");
        	System.out.println("Valor almacena -| "+ dalmacena +" - ");
        	System.out.println("Valor movconte -| "+ dmovconte +" - ");
        	System.out.println("Valor cargnavi -| "+ dcargnavi +" - ");
        	System.out.println("Valor prcustotax -| "+ dprcustotax +" - ");
        	System.out.println("Valor prconsutax -| "+ dprconsutax +" - ");
        	System.out.println("Valor prfletecst -| "+ dprfletecst +" - ");
        	System.out.println("Valor pritbisimp -| "+ dpritbisimp +" - ");
        	System.out.println("Valor prtramadua -| "+ dprtramadua +" - ");
        	System.out.println("Valor pralmacena -| "+ dpralmacena +" - ");
        	System.out.println("Valor prmovconte -| "+ dprmovconte +" - ");
        	System.out.println("Valor prcargnavi -| "+ dprcargnavi +" - ");
        	System.out.println("Valor prmargorig -| "+ dprmargorig +" - ");
        	System.out.println("Valor prmargmpsp -| "+ dprmargmpsp +" - ");
        	System.out.println("Valor prmargizum -| "+ dprmargizum +" - ");
		    
        	try {
	        	imptotal   = deciForm.format(dimptotal).replaceAll(",",".");
	        	custotax   = deciForm.format(dcustotax).replaceAll(",",".");
	        	consutax   = deciForm.format(dconsutax).replaceAll(",",".");
	        	fletecst   = deciForm.format(dfletecst).replaceAll(",",".");
	        	itbisimp   = deciForm.format(ditbisimp).replaceAll(",",".");
	        	tramadua   = deciForm.format(dtramadua).replaceAll(",",".");
	        	almacena   = deciForm.format(dalmacena).replaceAll(",",".");
	        	movconte   = deciForm.format(dmovconte).replaceAll(",",".");
	        	cargnavi   = deciForm.format(dcargnavi).replaceAll(",",".");
	        	prcustotax = deciForm.format(dprcustotax).replaceAll(",",".");
	        	prconsutax = deciForm.format(dprconsutax).replaceAll(",",".");
	        	prfletecst = deciForm.format(dprfletecst).replaceAll(",",".");
	        	pritbisimp = deciForm.format(dpritbisimp).replaceAll(",",".");
	        	prtramadua = deciForm.format(dprtramadua).replaceAll(",",".");
	        	pralmacena = deciForm.format(dpralmacena).replaceAll(",",".");
	        	prmovconte = deciForm.format(dprmovconte).replaceAll(",",".");
	        	prcargnavi = deciForm.format(dprcargnavi).replaceAll(",",".");
	        	prmargorig = deciForm.format(dprmargorig).replaceAll(",",".");
	        	prmargmpsp = deciForm.format(dprmargmpsp).replaceAll(",",".");
	        	prmargizum = deciForm.format(dprmargizum).replaceAll(",",".");
        	} catch (Exception e) {
        		System.err.println(" - ERROR - formateando los Double en String "+ e.getMessage());
        	}
        	
        	
        	output.setValue("imptotal",imptotal);
        	output.setValue("custotax",custotax);
        	output.setValue("consutax",consutax);
        	output.setValue("fletecst",fletecst);
        	output.setValue("itbisimp",itbisimp);
        	output.setValue("tramadua",tramadua);
        	output.setValue("almacena",almacena);
        	output.setValue("movconte",movconte);
        	output.setValue("cargnavi",cargnavi);
        	output.setValue("prcustotax",prcustotax);
        	output.setValue("prconsutax",prconsutax);
        	output.setValue("prfletecst",prfletecst);
        	output.setValue("pritbisimp",pritbisimp);
        	output.setValue("prtramadua",prtramadua);
        	output.setValue("pralmacena",pralmacena);
        	output.setValue("prmovconte",prmovconte);
        	output.setValue("prcargnavi",prcargnavi);
        	output.setValue("prmargorig",prmargorig);
        	output.setValue("prmargmpsp",prmargmpsp);
        	output.setValue("prmargizum",prmargizum);
		    
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
	