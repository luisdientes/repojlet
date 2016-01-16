package stock;



import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import stock.bd.UpdStockBD;
import stock.bd.UpdStockBDIn;


public class UpdateStockSrv extends Service {

    public UpdateStockSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("idemisor");
			input.addVariable("txmarcax");
			input.addVariable("txmodelo");
			input.addVariable("fhcompra");
			input.addVariable("diviscmp");
			input.addVariable("pricecmp");
			input.addVariable("prusdcmp");
			input.addVariable("pricevnt");
			input.addVariable("divisvnt");
			input.addVariable("prusdvnt");
			input.addVariable("txprovid");
			input.addVariable("txbuyerx");
			input.addVariable("txfundin");
			input.addVariable("withboxx");
			input.addVariable("withusbx");
			input.addVariable("withchar");
			input.addVariable("withheph");
			input.addVariable("tipocone");
			input.addVariable("cdestado");
			input.addVariable("imeicode");
			input.addVariable("tpclient");
			input.addVariable("idclient");
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
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output) throws Exception{
        
    	//Varibales de entrada

    	String idemisor = "";
    	String txmarcax = "";
    	String txmodelo = "";
    	String fhcompra = "";
    	String diviscmp = "";
    	String pricecmp = "";
    	String prusdcmp = "";
    	String pricevnt = "";
    	String divisvnt = "";
    	String prusdvnt = "";
    	String txprovid = "";
    	String txbuyerx = "";
    	String txfundin = "";
    	String withboxx = "";
    	String withusbx = "";
    	String withchar = "";
    	String withheph = "";
    	String tipocone = "";
    	String cdestado = "";
    	String imeicode = "";
    	String txmensaj = "";
    	String idclient = "";
    	String tpclient = "";

	    try{
	    	idemisor = input.getStringValue("idemisor");
	    	tpclient = input.getStringValue("tpclient");
	    	idclient = input.getStringValue("idclient");
	    	
	    	
			txmarcax = input.getStringValue("txmarcax");
			txmodelo = input.getStringValue("txmodelo");
			fhcompra = input.getStringValue("fhcompra");
			diviscmp = input.getStringValue("diviscmp");
			pricecmp = input.getStringValue("pricecmp");
			prusdcmp = input.getStringValue("prusdcmp");
			pricevnt = input.getStringValue("pricevnt");
			divisvnt = input.getStringValue("divisvnt");
			prusdvnt = input.getStringValue("prusdvnt");
			txprovid = input.getStringValue("txprovid");
			txbuyerx = input.getStringValue("txbuyerx");
			txfundin = input.getStringValue("txfundin");
			withboxx = input.getStringValue("withboxx");
			withusbx = input.getStringValue("withusbx");
			withchar = input.getStringValue("withchar");
			withheph = input.getStringValue("withheph");
			tipocone = input.getStringValue("tipocone");
			cdestado = input.getStringValue("cdestado");
			imeicode = input.getStringValue("imeicode");
	
				
			fhcompra = fechaMySQL(fhcompra);
				 
				//CORREGIR DIVISA EURO, NO LLEGA BIEN
				if(!diviscmp.equals("CHF") && !diviscmp.equals("RD$")){
					diviscmp = "&euro;";
				}
			
				if(!divisvnt.equals("CHF") && !divisvnt.equals("RD$")){
					divisvnt = "&euro;";
				}
				
				try{
						UpdStockBDIn insStockBDIn = new UpdStockBDIn();
				    	insStockBDIn.setValue("idemisor",idemisor);
				    	insStockBDIn.setValue("tpclient",tpclient);
				    	insStockBDIn.setValue("idclient",idclient);
				    	insStockBDIn.setValue("txmarcax",txmarcax);
				    	insStockBDIn.setValue("txmodelo",txmodelo);
				    	insStockBDIn.setValue("fhcompra",fhcompra);
				    	insStockBDIn.setValue("diviscmp",diviscmp);
				    	insStockBDIn.setValue("pricecmp",pricecmp);
				    	insStockBDIn.setValue("prusdcmp",prusdcmp);
				    	
				    	insStockBDIn.setValue("pricevnt",pricevnt);
				    	insStockBDIn.setValue("divisvnt",divisvnt);
				    	insStockBDIn.setValue("prusdvnt",prusdvnt);
				    	insStockBDIn.setValue("txprovid",txprovid);
				    	insStockBDIn.setValue("txbuyerx",txbuyerx);
				    	insStockBDIn.setValue("txfundin",txfundin);
				    	
				    	insStockBDIn.setValue("withboxx",withboxx);
				    	insStockBDIn.setValue("withusbx",withusbx);
				    	insStockBDIn.setValue("withheph",withheph);
				    	insStockBDIn.setValue("withchar",withchar);
				    	insStockBDIn.setValue("tipocone",tipocone);
				    	insStockBDIn.setValue("cdestado",cdestado);
				    	insStockBDIn.setValue("imeicode",imeicode);
				    	
			    		UpdStockBD insStockBD = new UpdStockBD(insStockBDIn);
			    		insStockBD.setConnection(con);
			    		
						int liInsert = insStockBD.execUpdate();
						
						if (liInsert == 1){
							txmensaj = "Se han modificado las líneas en el stock correctamente.";
					    } else {
					    	txmensaj = "Se ha producido un error al modificar la línea ("+ liInsert +")";
					    }
				}catch(Exception ex){
					System.out.println("Error al inssertar alguna linea "+ex.getMessage());
					txmensaj = "Se ha producido un error al insertar la línea";
				}
				
			
	    	
	    	
	    	// variables de salida
	    	output.setValue("txmensaj", txmensaj);
	    	output.setValue("idemisor", idemisor);
	
	    	
	    	
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
	