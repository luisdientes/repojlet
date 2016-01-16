package upgrade;

import stock.bd.ListStockBD;
import stock.bd.ListStockBDIn;
import upgrade.bd.UpdStockBD;
import upgrade.bd.UpdStockBDIn;
import upgrade.bd.UpdUpgradeBD;
import upgrade.bd.UpdUpgradeBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;



public class InsOldStockSrv extends Service {

    public InsOldStockSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();	
	
			input.addVariable("idemisor");
			input.addVariable("imeicode");
			input.addVariable("claseact");
			input.addVariable("horacomi");
			input.addVariable("costtime");
			input.addVariable("costlimp");
			input.addVariable("costsoft");
			input.addVariable("costpiez");
			input.addVariable("hardrese");
			input.addVariable("cuantasp");
			input.addVariable("tpclient");
			
			
			 

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
			output.addVariable("claseant");
			output.addVariable("idemisor");
			output.addVariable("horacomi");
			output.addVariable("imeicode");
			output.addVariable("costpiez");
			output.addVariable("costlimp");
			output.addVariable("costsoft");
			output.addVariable("tiepmtra");
			output.addVariable("cuantasp");
			output.addVariable("manoobra");
			output.addVariable("hardrese");
			output.addVariable("tpclient");
			output.addVariable("gridLine");
			output.addVariable("gridPiez");
			output.addVariable("grPrAgru");
			
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor = null;
    	String imeicode = null;
    	String claseact = null;
    	String costtime = null;
    	String costpiez = null;
    	String hardrese = null;
    	String cuantasp = null;
        
        //Varibales de salida
    	Grid gridProd = null;
    	Grid grPrAgru = null; 
    	Grid gridLine = null;
    	Grid gridPiez = null;
    	
    	int horasxxx = 0;
		int minutoxx = 0;
		Double precionu = 0.00;
    	
    	
    	
    	//Otras Variables
    	String codprodu = "";
    	String txmarcax = "";
    	String txmodelo = "";
    	String idcolorx = "";
    	String pricechf = "";
    	String priceusd = "";
    	String pricecmp = "";
    	String diviscmp = "";
    	String prusdcmp = "";
    	String pricevnt = "";
    	String divisvnt = "";
    	String prusdvnt = "";
    	String costlimp = "";
    	String costsoft = "";
    	String porcmarg = "";
    	String txprovid = "";
    	String txbuyerx = "";
    	String txfundin = "";
    	String withboxx = "";
    	String withusbx = "";
    	String idcatego = ""; 
    	String quantity = "";
    	String tpproduc = "";
    	String tipofact = "";
    	String tpclient = "";
    	String tpclienx = "";
    	String horacomi = "";
    	
    	double precminu = 1.00;
    	double totmanox = 0.00;
    	
        try {
        	
        	idemisor = input.getStringValue("idemisor");
        	imeicode = input.getStringValue("imeicode");
        	claseact = input.getStringValue("claseact");
        	costtime = input.getStringValue("costtime");
        	costpiez = input.getStringValue("costpiez");
        	horacomi = input.getStringValue("horacomi");
        	cuantasp = input.getStringValue("cuantasp");
        	costlimp = input.getStringValue("costlimp");
        	costsoft = input.getStringValue("costsoft");
        	tpclienx = input.getStringValue("tpclient");
        	hardrese = input.getStringValue("hardrese");
        		
	        try {
	        	
	        	 try {
	 	        	ListStockBDIn lineasBDIn= new ListStockBDIn();
	 	        	lineasBDIn.setValue("idemisor",idemisor);
	 	        	lineasBDIn.setValue("imeicode",imeicode);
	 	        	lineasBDIn.setValue("cdestado","STOCK");
	 			  	ListStockBD lineasBD = new ListStockBD(lineasBDIn);
	 			  	lineasBD.setConnection(con);
	 				gridLine = lineasBD.execSelect();	        	
	 	        } catch (Exception e) {
	 				System.err.println(this.getClass().getName() +" ERROR recogiendo lineas. "+ e.getMessage());
	 			}

	        	

        		imeicode = gridLine.getStringCell(0,"imeicode");
				txmarcax = gridLine.getStringCell(0,"txmarcax");
				txmodelo = gridLine.getStringCell(0,"txmodelo");
				idcolorx = gridLine.getStringCell(0,"idcolorx");
				pricechf = gridLine.getStringCell(0,"pricechf");
				priceusd = gridLine.getStringCell(0,"priceusd");
				porcmarg = gridLine.getStringCell(0,"porcmarg");
				txprovid = gridLine.getStringCell(0,"txprovid");
				txbuyerx = gridLine.getStringCell(0,"txbuyerx");
				txfundin = gridLine.getStringCell(0,"txfundin");
				withboxx = gridLine.getStringCell(0,"withboxx");
				withusbx = gridLine.getStringCell(0,"withusbx");
				idcatego = gridLine.getStringCell(0,"idcatego");
				quantity = gridLine.getStringCell(0,"quantity");
				tpproduc = gridLine.getStringCell(0,"tpproduc");
				codprodu = gridLine.getStringCell(0,"codprodu");
				pricecmp = gridLine.getStringCell(0,"pricecmp");
				diviscmp = gridLine.getStringCell(0,"diviscmp");
				prusdcmp = gridLine.getStringCell(0,"prusdcmp");
				pricevnt = gridLine.getStringCell(0,"pricevnt");
				divisvnt = gridLine.getStringCell(0,"divisvnt");
				prusdvnt = gridLine.getStringCell(0,"prusdvnt");
				tpclient = gridLine.getStringCell(0,"tpclient");
					//idemisor = gridLine.getStringCell(0,"idemisor");
					
					/* inserta stock */
				
				UpdStockBDIn insStockBDIn = new UpdStockBDIn();
				insStockBDIn.setValue("idemisor",idemisor);
		    	insStockBDIn.setValue("imeicode",imeicode);
		    	insStockBDIn.setValue("txmarcax",txmarcax);
		    	insStockBDIn.setValue("txmodelo",txmodelo);
		    	insStockBDIn.setValue("idcolorx",idcolorx);
		    	insStockBDIn.setValue("pricechf",pricechf);
		    	insStockBDIn.setValue("priceusd",priceusd);
		    	insStockBDIn.setValue("porcmarg",porcmarg);
		    	insStockBDIn.setValue("txprovid",txprovid);
		    	insStockBDIn.setValue("txbuyerx",txbuyerx);
		    	insStockBDIn.setValue("txfundin",txfundin);
		    	insStockBDIn.setValue("withboxx",withboxx);
		    	insStockBDIn.setValue("withusbx",withusbx);
		    	insStockBDIn.setValue("idcatego",idcatego);
		    	insStockBDIn.setValue("codprodu",codprodu);
		    	insStockBDIn.setValue("tpclient",tpclient);
		    	insStockBDIn.setValue("cdestado","STOCK");
		   		insStockBDIn.setValue("pricecmp",pricecmp);
		    	insStockBDIn.setValue("diviscmp",diviscmp);
		    	insStockBDIn.setValue("prusdcmp",prusdcmp);
		    	insStockBDIn.setValue("pricevnt",pricevnt);
		    	insStockBDIn.setValue("divisvnt",divisvnt);
		    	insStockBDIn.setValue("prusdvnt",prusdvnt);
		    	
		    	UpdStockBD insStockBD = new UpdStockBD(insStockBDIn);
		    	insStockBD.setConnection(con);
				insStockBD.execInsert();
				
				try{
					precionu = Double.parseDouble(pricecmp);
					precionu = precionu + Double.parseDouble(costpiez)+ Double.parseDouble(costlimp)+Double.parseDouble(costsoft) + Double.parseDouble(hardrese);
	
				}catch(Exception ex){
					System.out.println("Error al sumar precio");
				}
				

				
				try{
				    horasxxx = Integer.parseInt(costtime.substring(0, 2));
					minutoxx = Integer.parseInt(costtime.substring(3, 5));
					minutoxx = (horasxxx * 60) + minutoxx;
					
					totmanox = minutoxx * precminu;
				}catch(Exception ex){
					System.out.println("Error al calcular los minutos "+ex.getMessage());
				}
				
				//añadimos la mano de obra
				
				precionu = precionu + totmanox;
				
				
				UpdStockBDIn updStockBDIn = new UpdStockBDIn();
				updStockBDIn.setValue("idcatego",claseact);
				updStockBDIn.setValue("idemisor",idemisor);
				updStockBDIn.setValue("imeicode",imeicode);
				updStockBDIn.setValue("pricecmp",precionu);
				UpdStockBD updStockBD = new UpdStockBD(updStockBDIn);
				updStockBD.setConnection(con);
				updStockBD.execUpdate();
				
				
				/*update upgrade*/
				
				
				
				
				
				UpdUpgradeBDIn udgradeBDIn = new UpdUpgradeBDIn();
				udgradeBDIn.setValue("costpiez",costpiez); //cambiar
				udgradeBDIn.setValue("costlimp",costlimp);
				udgradeBDIn.setValue("costsoft",costsoft);
				udgradeBDIn.setValue("costtime",minutoxx);
				udgradeBDIn.setValue("claseact",claseact);
				udgradeBDIn.setValue("idemisor",idemisor);
				udgradeBDIn.setValue("imeicode",imeicode);
				udgradeBDIn.setValue("hardrese",hardrese);
				udgradeBDIn.setValue("manoobra",totmanox);
				
				
				UpdUpgradeBD updgradeBD = new UpdUpgradeBD(udgradeBDIn);
				updgradeBD.setConnection(con);
				updgradeBD.execUpdate();
		
				/*vuelvo a listar el producto*/
				
				
				Service srv = null;
	        	
	        	ObjectIO srvIn = null;
	        	ObjectIO srvOut = null;
	        	
	        	srv = new SelecImeiSrv();
	        	
	        	srvIn  = srv.instanceOfInput();
				srvOut = srv.instanceOfOutput();
				srvIn.setValue("idemisor", idemisor);
				srvIn.setValue("imeicode", imeicode);
				srvIn.setValue("inspieza", "S");
				
				
				srv.setConnection(con);
				srv.process(srvIn, srvOut);
				gridLine = srvOut.getGrid("gridLine");


	        } catch (Exception e) {
				System.err.println(this.getClass().getName() +" ERROR recogiendo productos. "+ e.getMessage());
			}
	        
	        output.setValue("idemisor", idemisor);
	        output.setValue("cuantasp", cuantasp);
	        output.setValue("costpiez", costpiez);
	        output.setValue("costlimp", costlimp);
	        output.setValue("costsoft", costsoft);
	        output.setValue("hardrese", hardrese);
	        output.setValue("tiepmtra", minutoxx);
	        output.setValue("tpclient", tpclienx);
	        output.setValue("manoobra", totmanox );
	        
        	output.setValue("claseant", "");
        	output.setValue("gridLine", gridLine);

        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
}
	