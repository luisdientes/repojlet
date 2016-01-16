package stock;



import common.Divisa;
import facturacion.parents.FacturaPDF;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import stock.bd.ListTmpStockBD;
import stock.bd.ListTmpStockBDIn;
import stock.bd.UpdStockBD;
import stock.bd.UpdStockBDIn;


public class AltaProdSrv extends Service {
	
	
	FacturaPDF FRAparen = new FacturaPDF();

    public AltaProdSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			/*input.addVariable("idemisor");
			input.addVariable("codprodu");
			input.addVariable("txmarcax");
			input.addVariable("txmodelo");
			input.addVariable("tpclient");
			input.addVariable("tpproduc");
			input.addVariable("arrayPro");*/
		
			input.addVariable("idemisor");
			input.addVariable("cdimeixx");
			input.addVariable("pricecmp");
			input.addVariable("divisaxx");
			input.addVariable("divisvnt");
			input.addVariable("pricevnt");
			input.addVariable("proveedo");
			input.addVariable("clasexxx");
			input.addVariable("cargador");
			input.addVariable("enchufex");
			input.addVariable("tpclient");
			input.addVariable("usbxxxxx");
			input.addVariable("cajaxxxx");
			input.addVariable("cascosxx");
			input.addVariable("tpproduc");
			input.addVariable("codprodu");
			input.addVariable("tipoalta");
			input.addVariable("cdestado");
			input.addVariable("fechacmp");
			input.addVariable("txmarcax");
			input.addVariable("txmodelo");
			input.addVariable("idgralta");
			input.addVariable("idcolorx");
			
			
			

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
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output) throws Exception{
        
    	//Varibales de entrada
    	String idemisor = "";
    	String cdimeixx = "";
    	String pricecmp = "";
    	String divisaxx = "";
    	String divisvnt = "";
    	String pricevnt = "";
    	String proveedo = "";
    	String clasexxx = "";
    	String cargador = "";
    	String enchufex = "";
    	String usbxxxxx = "";
    	String cajaxxxx = "";
    	String cascosxx = "";
    	String tpproduc = "";
    	String codprodu = "";
    	String tipoalta = "";
    	String cdestado = "";
    	String tpclient = "";
    	String txmensaj = "";
    	String txmarcax = "";
    	String txmodelo = "";
    	String fechacmp = "";
    	String idgralta = "";
    	String divisaal = "";
    	String idcolorx = "";
    	
    	Grid gridLine = null;
    	
    	String stfixing = "";
    	
    	double dbfixing = 1;
    	double dbprecmp = 0;
    	double prcmpusd = 0;
    	double dbprevnt = 0;
    	double prvntusd = 0;
    	
    	
    	//nuevo
    	
    	
    	/*
    	String idemisor = "";
    	String codprodu = "";
    	String txmarcax = "";
    	String txmodelo = "";
    	String tpclient = "";
    	String cdimeixx = "";
    	String pricecmp = "";
    	String divisaxx = "";
    	String proveedo = "";
    	String clasexxx = "";
    	String cargador = "";
    	String enchufex = "";
    	String usbxxxxx = "";
    	String cajaxxxx = "";
    	String cascosxx = "";
    	String tpproduc = "";
    	
    	String arrayPro = "";
    	String arrEleme[] = null;
    	String arrValores[] = null;
        
        //Varibales de salida
        String txmensaj = "";   	
			
        //Otras Variables
        String priceusd = "";*/
      //  ajaxAltaProd(cdimeixx,pricecmp,divisaxx,proveedo,clasexxx,cargador,enchufex,usbxxxxx,cajaxxxx,cascosxx)
        
	    try{
	    	
	    	idemisor = input.getStringValue("idemisor");
	    	cdimeixx = input.getStringValue("cdimeixx");
	    	divisvnt = input.getStringValue("divisvnt");
	    	pricevnt = input.getStringValue("pricevnt");
	    	pricecmp = input.getStringValue("pricecmp");
	    	divisaxx = input.getStringValue("divisaxx");
	    	proveedo = input.getStringValue("proveedo");
	    	clasexxx = input.getStringValue("clasexxx");
	    	cargador = input.getStringValue("cargador");
	    	enchufex = input.getStringValue("enchufex");
	    	usbxxxxx = input.getStringValue("usbxxxxx");
	    	cajaxxxx = input.getStringValue("cajaxxxx");
	    	cascosxx = input.getStringValue("cascosxx");
	    	codprodu = input.getStringValue("codprodu");
	    	tpproduc = input.getStringValue("tpproduc");
	    	tipoalta = input.getStringValue("tipoalta");
	    	cdestado = input.getStringValue("cdestado");
	    	txmarcax = input.getStringValue("txmarcax");
	    	txmodelo = input.getStringValue("txmodelo");
	    	fechacmp = input.getStringValue("fechacmp");
	    	idgralta = input.getStringValue("idgralta");
	    	idcolorx = input.getStringValue("idcolorx");
	    	fechacmp = fechaMySQL(fechacmp);
	    	
	    	
	    	//alta AJAX
	    	
	    	if(tipoalta !=null && tipoalta.equals("LINEA")){
		    	ListTmpStockBDIn altaStockTmpBDIn = new ListTmpStockBDIn();
		    	altaStockTmpBDIn.setValue("idemisor", idemisor);
		    	altaStockTmpBDIn.setValue("cdimeixx", cdimeixx);
		    	altaStockTmpBDIn.setValue("pricevnt", pricevnt);
		    	altaStockTmpBDIn.setValue("divisvnt", divisvnt);
		    	altaStockTmpBDIn.setValue("pricecmp", pricecmp);
		    	altaStockTmpBDIn.setValue("divisaxx", divisaxx);
		    	altaStockTmpBDIn.setValue("proveedo", proveedo);
		    	altaStockTmpBDIn.setValue("clasexxx", clasexxx);
		    	altaStockTmpBDIn.setValue("cargador", cargador);
		    	altaStockTmpBDIn.setValue("enchufex", enchufex);
		    	altaStockTmpBDIn.setValue("usbxxxxx", usbxxxxx);
		    	altaStockTmpBDIn.setValue("cajaxxxx", cajaxxxx);
		    	altaStockTmpBDIn.setValue("cascosxx", cascosxx);
		    	altaStockTmpBDIn.setValue("codprodu", codprodu);
		    	altaStockTmpBDIn.setValue("tpproduc", tpproduc);
		    	altaStockTmpBDIn.setValue("cdestado", cdestado);
		    	altaStockTmpBDIn.setValue("fechacmp", fechacmp);
		    	altaStockTmpBDIn.setValue("idgralta", idgralta);
		    	altaStockTmpBDIn.setValue("idcolorx", idcolorx);
		    	
		    	
		    	
		    	ListTmpStockBD altaStockTmpBD = new ListTmpStockBD(altaStockTmpBDIn);  
		    	altaStockTmpBD.setConnection(con);
		    	altaStockTmpBD.execInsert();
		    	
	    	}else if(tipoalta !=null && tipoalta.equals("STOCK")){
	    		
		    		ListTmpStockBDIn LineasStockTmpBDIn = new ListTmpStockBDIn();
			    	LineasStockTmpBDIn.setValue("idemisor", idemisor);
			    	LineasStockTmpBDIn.setValue("cdestado", cdestado);
			    	LineasStockTmpBDIn.setValue("codprodu", codprodu);
			    	
			    	ListTmpStockBD ListStockTmpBD = new ListTmpStockBD(LineasStockTmpBDIn);  
			    	
			    	ListStockTmpBD.setConnection(con);
			    	gridLine = ListStockTmpBD.execSelect();
			    	
			    	if ((gridLine != null) && (gridLine.rowCount() > 0)) { 
			  			
			  			for (int i = 0; i < gridLine.rowCount(); i++){
			  				
			  				try {
					    		cdimeixx = gridLine.getStringCell(i,"cdimeixx");
					    		clasexxx = gridLine.getStringCell(i,"idcatego");
								divisaxx = gridLine.getStringCell(i,"diviscmp");
								pricecmp = gridLine.getStringCell(i,"pricecmp");
								cargador = gridLine.getStringCell(i,"withchar");	  					
								enchufex = gridLine.getStringCell(i,"tipocone");	
								usbxxxxx = gridLine.getStringCell(i,"withusbx");
								cajaxxxx = gridLine.getStringCell(i,"withboxx");
								cascosxx = gridLine.getStringCell(i,"withheph");
								proveedo = gridLine.getStringCell(i,"txprovid");
								fechacmp = gridLine.getStringCell(i,"fhcompra");
								divisvnt = gridLine.getStringCell(i,"divisvnt");
								pricevnt = gridLine.getStringCell(i,"pricevnt");
								tpproduc = gridLine.getStringCell(i,"tpproduc");
								idcolorx = gridLine.getStringCell(i,"idcolorx");
								
								
								//Obtengo el fixing del CHF
					        	try {
					        		if(divisaxx.equals("RD$")){
					        			divisaal ="DOP";
					        		}else{
					        			divisaal = divisaxx;
					        		}
					        		
					 	        	Divisa divisa = new Divisa();
					 	        	divisa.setConnection(con);

					 	        	stfixing = divisa.getFixingUSD(divisaal);
					 	        	dbfixing = Double.parseDouble(stfixing);
					 	        	dbprecmp = Double.parseDouble(pricecmp);
					 	        	prcmpusd = dbprecmp / dbfixing;
					 	        	
					 	        	
					 	        	// precio de venta en dolares*/
					 	        	
					 	        	if(divisvnt.equals("RD$")){
					        			divisaal ="DOP";
					        		}else{
					        			divisaal = divisvnt;
					        		}
					 	        	stfixing = divisa.getFixingUSD(divisaal);
					 	        	dbfixing = Double.parseDouble(stfixing);
					 	        	dbprevnt = Double.parseDouble(pricevnt);
					 	        	prvntusd = dbprevnt / dbfixing;
					 	        	
					 	        	
					 	        } catch (Exception e) {
					 	        	System.out.println("ERROR realizando operaciones de Fixing");
					 	        }
								
								UpdStockBDIn insStockBDIn = new UpdStockBDIn();
						    	insStockBDIn.setValue("idemisor",idemisor);
						    	insStockBDIn.setValue("cdestado","STOCK");
						    	insStockBDIn.setValue("imeicode",cdimeixx);
						    	insStockBDIn.setValue("txmarcax",txmarcax);
						    	insStockBDIn.setValue("txmodelo",txmodelo);
						    	insStockBDIn.setValue("pricecmp",pricecmp);
						    	insStockBDIn.setValue("diviscmp",divisaxx);
						    	insStockBDIn.setValue("txprovid",proveedo);
						    	insStockBDIn.setValue("withboxx",cajaxxxx);
						    	insStockBDIn.setValue("withusbx",usbxxxxx);
						    	insStockBDIn.setValue("withheph",cascosxx);
						    	insStockBDIn.setValue("withchar",cargador);
						    	insStockBDIn.setValue("tipocone",enchufex);
						    	insStockBDIn.setValue("tpproduc",tpproduc);
						    	insStockBDIn.setValue("codprodu",codprodu);
						    	insStockBDIn.setValue("idcatego",clasexxx);
						    	insStockBDIn.setValue("tpclient",tpclient);
						    	insStockBDIn.setValue("fhcompra",fechacmp);
						    	insStockBDIn.setValue("prusdcmp",prcmpusd);
						    	insStockBDIn.setValue("divisvnt",divisvnt);
						    	insStockBDIn.setValue("pricevnt",pricevnt);
						    	insStockBDIn.setValue("prusdvnt",prvntusd);
						    	insStockBDIn.setValue("idcolorx",idcolorx);
						    	
					    		UpdStockBD insStockBD = new UpdStockBD(insStockBDIn);
					    		insStockBD.setConnection(con);
					    		
								int liInsert = insStockBD.execInsert();
								
								if (liInsert == 1){
									txmensaj = "Se han insertado las líneas en el stock correctamente.";
									
									
									FRAparen.actualizaCostes(cdimeixx,idemisor,"CP00001",String.valueOf(pricecmp),divisaxx);
									FRAparen.actualizaCostes(cdimeixx,idemisor,"CPTOTAL",String.valueOf(pricecmp),divisaxx);
									
									
									ListTmpStockBDIn UpdateStockTmpBDIn = new ListTmpStockBDIn();
									UpdateStockTmpBDIn.setValue("idemisor", idemisor);
									UpdateStockTmpBDIn.setValue("cdestado", cdestado);
									UpdateStockTmpBDIn.setValue("codprodu", codprodu);
							    	
							    	ListTmpStockBD UpdateStockTmpBD = new ListTmpStockBD(UpdateStockTmpBDIn);  
							    	
							    	UpdateStockTmpBD.setConnection(con);
							    	UpdateStockTmpBD.execUpdate();
									
									
							    } else {
							    	txmensaj = "Se ha producido un error al insertar la línea ("+ liInsert +")";
							    }
			  				}catch(Exception ex){
			  					System.out.println("Error al dar de alta");
			  					}
			  			}
			    	}
		    	}else if(tipoalta !=null && tipoalta.equals("BORRAR")){
		    		ListTmpStockBDIn DeleStockTmpBDIn = new ListTmpStockBDIn();
		    		DeleStockTmpBDIn.setValue("idemisor", idemisor);
		    		DeleStockTmpBDIn.setValue("cdimeixx", cdimeixx);
		    		
		    		ListTmpStockBD DelStockTmpBD = new ListTmpStockBD(DeleStockTmpBDIn);  
		    		DelStockTmpBD.setConnection(con);
		    		
		    		try{
		    			int borrado = DelStockTmpBD.execDelete();
		    			
		    			if(borrado ==1){
		    				System.out.println("Borrado correcto del Stock temporal");
		    			}else{
		    				System.out.println("Error al borrar linea del Stock temporal");
		    			}
		    			
		    		}catch(Exception ex){
		    			System.out.println("Error al borrar linea del Stock temporal");
		    		}
		    	}

	    	
	    	
	    	ListTmpStockBDIn LineasStockTmpBDIn = new ListTmpStockBDIn();
	    	LineasStockTmpBDIn.setValue("idemisor", idemisor);
	    	LineasStockTmpBDIn.setValue("cdestado", cdestado);
	    	LineasStockTmpBDIn.setValue("codprodu", codprodu);
	    	
	    	ListTmpStockBD ListStockTmpBD = new ListTmpStockBD(LineasStockTmpBDIn);  
	    	
	    	ListStockTmpBD.setConnection(con);
	    	gridLine = ListStockTmpBD.execSelect();
	    	
	    	output.setValue("txmensaj", txmensaj);
	    	output.setValue("idemisor", idemisor);
	    	output.setValue("gridLine", gridLine);
	
	    	
	    	
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
	