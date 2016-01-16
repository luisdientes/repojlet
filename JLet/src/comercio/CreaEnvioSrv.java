package comercio;


import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;


public class CreaEnvioSrv extends Service {

    public CreaEnvioSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();	
			input.addVariable("idemisor");
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
			output.addVariable("txmensaj");
			output.addVariable("idemisor");
			output.addVariable("gridLine");
			output.addVariable("gridColo");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor = null;
    	
    	String imeicode = "";
    	String txmarcax = "";
    	String txmodelo = "";
    	String idcolorx = "";
    	String pricechf = "";
    	String porcmarg = "";
    	String txprovid = "";
    	String txbuyerx = "";
    	String txfundin = "";
    	String withboxx = "";
    	String withusbx = "";
    	String idcatego = ""; 
    	String quantity = "";
    	String tpproduc = "";
        
        //Varibales de salida
    	Grid gridLine = null;
    	String listimei = null;
    	String txmensaj = null;
    	
        //Otras Variables
    	
        try {
        	String [] arrImeis = null;
        	int numeroIme = 0;
        	int totalinse = 0;
        	
        	idemisor = input.getStringValue("idemisor");
        	listimei = input.getStringValue("listimei");
        	arrImeis = listimei.split(";");
        	numeroIme = arrImeis.length;
	        try {
	        	
				InitStockSrv lstStockSrv = new InitStockSrv();
	    		ObjectIO srvIn 	= lstStockSrv.instanceOfInput();
	    		ObjectIO srvOut = lstStockSrv.instanceOfOutput();
	    		srvIn.setValue("idemisor", idemisor);
	    		srvIn.setValue("cdestado", "STOCK");
	    		lstStockSrv.setConnection(this.getConnection());
	        	
	        	TempEnvioSrv insEnvioSrv = new TempEnvioSrv();
	    		ObjectIO envSrvInp 	= insEnvioSrv.instanceOfInput();
	    		ObjectIO envsrvOut = insEnvioSrv.instanceOfOutput(); 
	    		insEnvioSrv.setConnection(this.getConnection());
	    		
	    		for(int i=0;i<arrImeis.length;i++){
	    			
	    			/*Saco los datos de la linea*/
		    		srvIn.setValue("imeicode", arrImeis[i]);
		    	
		    		lstStockSrv.process(srvIn, srvOut);
		    		gridLine = srvOut.getGrid("gridLine");
		    	
					imeicode = gridLine.getStringCell(0,"imeicode");
					txmarcax = gridLine.getStringCell(0,"txmarcax");
					txmodelo = gridLine.getStringCell(0,"txmodelo");
					idcolorx = gridLine.getStringCell(0,"idcolorx");
					pricechf = gridLine.getStringCell(0,"pricechf");
					porcmarg = gridLine.getStringCell(0,"porcmarg");
					txprovid = gridLine.getStringCell(0,"txprovid");
					txbuyerx = gridLine.getStringCell(0,"txbuyerx");
					txfundin = gridLine.getStringCell(0,"txfundin");
					withboxx = gridLine.getStringCell(0,"withboxx");
					withusbx = gridLine.getStringCell(0,"withusbx");
					idcatego = gridLine.getStringCell(0,"idcatego");
					quantity = gridLine.getStringCell(0,"quantity");
					tpproduc = gridLine.getStringCell(0,"tpproduc");
					idemisor = gridLine.getStringCell(0,"idemisor");
					
					/*INSERTO LAS LINEAS*/
		
					envSrvInp.setValue("imeicode", imeicode);
					envSrvInp.setValue("txmarcax", txmarcax);
					envSrvInp.setValue("txmodelo", txmodelo);
					envSrvInp.setValue("idcolorx", idcolorx);
					envSrvInp.setValue("pricechf", pricechf);
					envSrvInp.setValue("porcmarg", porcmarg);
					envSrvInp.setValue("txprovid", txprovid);
					envSrvInp.setValue("txbuyerx", txbuyerx);
					envSrvInp.setValue("txfundin", txfundin);
					envSrvInp.setValue("withboxx", withboxx);
					envSrvInp.setValue("withusbx", withusbx);
					envSrvInp.setValue("idcatego", idcatego);
					envSrvInp.setValue("quantity", quantity);
					envSrvInp.setValue("tpproduc", tpproduc);
					envSrvInp.setValue("idemisor", idemisor);
		    		insEnvioSrv.process(envSrvInp, envsrvOut);
		    		totalinse ++;
	    		}
	    		
	    		if((totalinse >0) && (numeroIme == totalinse)){
	    			txmensaj = " Se han insertado "+totalinse+" lineas en el Envío"; 
	    		}else{
	    			txmensaj = "ERROR -- Fallo el insertar alguna linea en el envío";
	    		}
	        
	        } catch (Exception e) {
				System.err.println(this.getClass().getName() +"  ERROR recogiendo lineas. "+ e.getMessage());
				txmensaj = "Error al crear Envio";
			}
        	
		    output.setValue("gridLine", gridLine);
		    output.setValue("txmensaj", txmensaj);
		    output.setValue("idemisor", idemisor);

        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
}
	
	