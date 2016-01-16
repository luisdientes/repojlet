package stock;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import stock.bd.UpdEstadoProBD;
import stock.bd.UpdEstadoProBDIn;
import stock.bd.UpdStockBD;
import stock.bd.UpdStockBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import factura.GeneraFacturaSrv;
import factura.bd.MaxFacturaBD;
import factura.bd.MaxFacturaBDIn;
import factura.bd.UpdLineasBD;
import factura.bd.UpdLineasBDIn;


public class RealizaTransaccionSrv extends Service {
	
	
	int nlineaxx = 1;
	String filecrea = "";
	String idemisor = "";
	String cduserid = "";
	String listimei = "";
	String tipoclie = null;
	
    public RealizaTransaccionSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();	
			input.addVariable("idemisor");
			input.addVariable("txrazons");
			input.addVariable("tipoclie");
			input.addVariable("idclient");
			input.addVariable("tptransa");
			input.addVariable("listimei");
			input.addVariable("tpclient");
			input.addVariable("tipofact");

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
			output.addVariable("txrazons");
			output.addVariable("listimei");
			output.addVariable("gridLine");
			output.addVariable("gridInte");
			output.addVariable("gridExte");
			output.addVariable("txmensaj");
			output.addVariable("filecrea");

		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	
    	
    	String tpclient = null;
    	String idclient = null;
    	String tptransa = null;
    	
    	String txrazons = "";
    	
    	

        
        
        //Varibales de salida
    	Grid gridLine = null;
    	Grid gridTota = null;
    	Grid gridExte = null;
    	String txmensaj = "";
    	
        //Otras Variables
    	String codprodu = "";
    	String imeicode = "";
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
    	Grid gridResul = new Grid();

    	try {
    		idemisor = input.getStringValue("idemisor");
    		tipoclie = input.getStringValue("tipoclie");
    		idclient = input.getStringValue("idclient");
			tptransa = input.getStringValue("tptransa");
			listimei = input.getStringValue("listimei");
			tpclient = input.getStringValue("tpclient");
			txrazons = input.getStringValue("txrazons"); 
			tipofact = input.getStringValue("tipofact"); 
			
			 cduserid = (String) sesion.getAttribute("idusuari");
    
    	     String [] arrImeis = null;
    	     int numeroIme = 0;
    	     int totalinse = 0;
    	     arrImeis = listimei.split(";");
    	     numeroIme = arrImeis.length;
    	     
    	     String cdestadoUpd ="";
    	     String cdestadoIns ="";
	        	
    	     /* PARA LISTAR EL STOCK DEL EMISOR SEGUN LOS IMEIS*/
 				InitStockSrv lstStockSrv = new InitStockSrv();
 	    		ObjectIO srvIn 	= lstStockSrv.instanceOfInput();
 	    		ObjectIO srvOut = lstStockSrv.instanceOfOutput();
 	    		srvIn.setValue("idemisor", idemisor);
 	    		srvIn.setValue("cdestado", "STOCK");
 	    		
 	    		UpdStockBDIn insStockBDIn = new UpdStockBDIn();
 	    		UpdStockBDIn updStockBDIn = new UpdStockBDIn();
 	    		
 	    		if (tptransa !=null && tptransa.equals("D") ){
 	    				updStockBDIn.setValue("idemisor",idemisor);
 	    				updStockBDIn.setValue("idclient",idclient); 	//UPDATE
 	    				updStockBDIn.setValue("cdestado","DEPOSITO"); 	//UPDATE
 	    				cdestadoUpd ="DEPOSITO";
 	    		
	    				if (tipoclie !=null && tipoclie.equals("I") ){
	 	    				insStockBDIn.setValue("idemisor",idclient);		//INSERT
	    					insStockBDIn.setValue("idclient",idemisor); 	//INSERT
	    					insStockBDIn.setValue("cdestado","RECDEPOS"); 	//INSERT
	    					cdestadoIns ="RECDEPOS";
	    					
	    				} 
 	    		} else if(tptransa !=null && tptransa.equals("F")) {
 	    				updStockBDIn.setValue("idemisor",idemisor);		//UPDATE
 	    				updStockBDIn.setValue("idclient",idclient); 	//UPDATE
 	    				insStockBDIn.setValue("idemisor",idclient); 	//INSERT
    					insStockBDIn.setValue("idclient",idemisor); 	//INSERT
 	    			
 	    				if (tipoclie !=null && tipoclie.equals("I") ){
	    					updStockBDIn.setValue("cdestado","VENDIDOINT"); //update
	    					insStockBDIn.setValue("cdestado","STOCK"); //INSERT
	    					cdestadoUpd = "VENDIDOINT";
	    					cdestadoIns = "STOCK";
	    					
 	    				} else if(tipoclie !=null && tipoclie.equals("E") ){
	    					updStockBDIn.setValue("cdestado","VENDIDOEXT"); //update
	    					cdestadoUpd = "VENDIDOINT";
 	    				}

 	    		}
	    		
 	    		lstStockSrv.setConnection(this.getConnection());
 	    		
 	    		ArrayList<String> prodVend 	= new ArrayList<String>();
 	    		ArrayList<Integer> prodUnit = new ArrayList<Integer>();
 	    		ArrayList<String> listIMEI 	= new ArrayList<String>();
 	    		
    			gridResul.addColumn("imeicode");
	    		gridResul.addColumn("txmarcax");
	    		gridResul.addColumn("txmodelo");
	        	gridResul.addColumn("idcolorx");
	        	gridResul.addColumn("pricevnt");
	        	gridResul.addColumn("divisvnt");
	    		gridResul.addColumn("prusdvnt");
	        	gridResul.addColumn("withboxx");
	        	gridResul.addColumn("withusbx");
	        	gridResul.addColumn("idcatego");
	        	gridResul.addColumn("codprodu");
 	    		
 	    		
 	    		
 	    		for(int i=0;i<arrImeis.length;i++){
 	    			 	    			 	    			
 	    			/*Saco los datos de la linea*/
 		    		srvIn.setValue("imeicode", arrImeis[i]);
 		    	
 		    		lstStockSrv.process(srvIn, srvOut);
 		    		gridLine = srvOut.getGrid("gridLine");
 		    	
 		    		if (gridLine.rowCount() > 0) {
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
	 					//idemisor = gridLine.getStringCell(0,"idemisor");
	 					
	 					/* inserta stock */
	 				
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
	 			    	
		 			   	insStockBDIn.setValue("pricecmp",pricecmp);
	 			    	insStockBDIn.setValue("diviscmp",diviscmp);
	 			    	insStockBDIn.setValue("prusdcmp",prusdcmp);
	 			    	insStockBDIn.setValue("pricevnt",pricevnt);
	 			    	insStockBDIn.setValue("divisvnt",divisvnt);
	 			    	insStockBDIn.setValue("prusdvnt",prusdvnt);
	 			    	
	 			    	
	 			    	
	
	 			   	if ((tptransa !=null && tptransa.equals("D") || tptransa !=null && tptransa.equals("F"))
	 			   		&& (tipoclie !=null && tipoclie.equals("I"))){
	 			    	
	 		    		UpdStockBD insStockBD = new UpdStockBD(insStockBDIn);
	 		    		insStockBD.setConnection(con);
	 					insStockBD.execInsert();
	 			   	}	
	 					
	 					
	 					/* update stock*/
	 					updStockBDIn.setValue("imeicode",imeicode);
	 					updStockBDIn.setValue("tpclient",tpclient);
	 		    		UpdStockBD updStockBD = new UpdStockBD(updStockBDIn);
	 		    		updStockBD.setConnection(con);
	 		    		updStockBD.execUpdate();
	 		    		
	 		    		
	 		    		/*ACTUALIZO ESTADO PARA CALCULAR ROTACION*/
	 		    		
	 		    		if(!cdestadoUpd.equals("")){
	 			    		updEstadoProducto(imeicode,cdestadoUpd,idclient);
	 			    	}
	 		    		if(!cdestadoIns.equals("")){
	 			    		updEstadoProducto(imeicode,cdestadoIns,idclient);
	 			    	}
	 				  /*----------------------------------*/
	 		    		totalinse ++;
	 		    		
	 		    		if (tipoclie !=null && tipoclie.equals("F")){
	 		    			boolean encontrado = false;
	 		    			for (int j = 0; j < prodVend.size() && !encontrado; j++){
	 		    				if (prodVend.get(j).equals(txmarcax +" "+ txmodelo)){
	 		    					encontrado = true;
	 		    					prodUnit.set(j, prodUnit.get(j) + 1);
	 		    				}
	 		    			}
	 		    			
	 		    			if (!encontrado){
	 		    				prodVend.add(txmarcax +" "+ txmodelo);
	 		    				prodUnit.add(1);
	 		    			}
	 		    		} 
	 		    		
	 		    		gridResul.addColumn("imeicode");
	 		    		gridResul.addColumn("txmarcax");
	 		    		gridResul.addColumn("txmodelo");
	 		        	gridResul.addColumn("idcolorx");
	 		        	gridResul.addColumn("pricevnt");
	 		        	gridResul.addColumn("divisvnt");
	 		    		gridResul.addColumn("prusdvnt");
	 		        	gridResul.addColumn("withboxx");
	 		        	gridResul.addColumn("withusbx");
	 		        	gridResul.addColumn("idcatego");
	 		        	gridResul.addColumn("codprodu");
	 		    		
	 		    		ArrayList<String> row = new ArrayList<String>();
	 		    		row.add(imeicode);
	        			row.add(txmarcax);
	        			row.add(txmodelo);
	        			row.add(idcolorx);
	        			row.add(pricevnt);
	        			row.add(divisvnt);
	        			row.add(prusdvnt);
	        			
	        			row.add(withboxx);
	        			row.add(withusbx);
	        			row.add(idcatego);
	        			row.add(codprodu);
	        	
	        			gridResul.addRow(row);
	 		    		listIMEI.add(imeicode);
 		    		
 		    		}
 		    		
 	    		}
 	    		
 	    		if((totalinse >0) && (numeroIme == totalinse)){
 	    			txmensaj = " Se han insertado "+totalinse+" lineas en el Envío"; 
 	    		}else{
 	    			txmensaj = "ERROR -- Fallo el insertar alguna linea en el envío";
 	    		} 
 	    		
 	    		
 	    		if (tptransa !=null){
 					//Se emitirá una factura
 					emiteFactura(gridResul,idemisor,tpclient,idclient,tipofact);
 				}
        	
 	    		
 	    	output.setValue("idemisor", idemisor);
 	    	output.setValue("tptransa", tptransa);
 	    	output.setValue("txrazons", txrazons);
 	    	output.setValue("gridLine", gridResul);
			output.setValue("txmensaj", txmensaj);
			output.setValue("filecrea", filecrea);
			

        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
    
    public void emiteFactura(Grid grStock,String idemisor, String tpclient, String idclient, String tipofact){
    	
    	String idlineax = "";
    	String codprodu = "";
    	int    idfactur = 0;	//Debo recuperarlo
    	String fechafac = "";
    	String cdestado = "V";
    	String txmarcax = "";
    	String txmodelo = "";
    	String concepto = "";	//Debo recuperar el nombre del producto
    	String precioun = ""; 	//Debo recuperar el precio venta
    	String descuent = "0"; 	//Debo recuperar el descuento
    	String precioto = "";
    	String idtmpfra = "";
    	String idunicox = "";
    	String numunida = "1";
    	String pricevnt = "";
    	String txmensaj = "";
    	String informad = "";
    	fechafac = fechaHoy();
    	
    	try {
    		
    		MaxFacturaBDIn factBDIn = new MaxFacturaBDIn();
        	factBDIn.setValue("idemisor",idemisor);
        	MaxFacturaBD factBD = new MaxFacturaBD(factBDIn);
        	factBD.setConnection(con);
        	Grid gridFact = factBD.execSelect();
        	
    		
        	if (gridFact.rowCount() > 0){
        		idfactur = Integer.parseInt(gridFact.getStringCell(0,"idfactur"));
        		idfactur++;
        	}
    	} catch (Exception e) {
    		idfactur = 1;
    	}
    	
    	
    	
    	
    	if (grStock.rowCount() > 0) {
    		
    		for(int i= 0; i < grStock.rowCount(); i++){
    		
	    		idlineax = String.valueOf(System.currentTimeMillis());
	    		codprodu = grStock.getStringCell(i,"codprodu");
	    		txmarcax = grStock.getStringCell(i,"txmarcax");
				idunicox = grStock.getStringCell(i,"imeicode");
				
				txmodelo = grStock.getStringCell(i,"txmodelo");
				pricevnt = grStock.getStringCell(i,"pricevnt");
				
				if(pricevnt.equals("") || pricevnt.equals("null")){
					pricevnt = "0";
				}
				
				concepto = txmarcax +" "+txmodelo+" Imei ( "+idunicox+" )";
				
				try{
					
			    	UpdLineasBDIn InsLinBDIn= new UpdLineasBDIn();
			    	InsLinBDIn.setValue("idlineax",idlineax);
					InsLinBDIn.setValue("codprodu",codprodu);
					InsLinBDIn.setValue("idfactur",idfactur);
					InsLinBDIn.setValue("idemisor",idemisor);
					InsLinBDIn.setValue("idclient",idclient);
					InsLinBDIn.setValue("tpclient",tpclient);
					InsLinBDIn.setValue("fechafac",fechafac);
					InsLinBDIn.setValue("nlineaxx",String.valueOf(nlineaxx++));
					InsLinBDIn.setValue("cantidad",numunida);
					InsLinBDIn.setValue("concepto",concepto);
					InsLinBDIn.setValue("precioun",pricevnt);
					InsLinBDIn.setValue("descuent",descuent);
					InsLinBDIn.setValue("precioto",pricevnt); //son cantidades de 1 siempre es el mismo el precioventa que el total
					InsLinBDIn.setValue("idunicox",idunicox);
					InsLinBDIn.setValue("cdestado",cdestado);
					UpdLineasBD insLinBD = new UpdLineasBD(InsLinBDIn);
					insLinBD.setConnection(con);
					int liInsert = insLinBD.execInsert();
					
					informad += idunicox +"\n";
					System.out.println("Se han insertado "+liInsert+" lineas para crear factura / conduce");
				}catch(Exception ex){
					System.out.println("Error al insertar las lineas");
				}
    		}
	    }
    	
    	
    	Service srv = null;
    	
    	ObjectIO srvIn = null;
    	ObjectIO srvOut = null;
    	//String aniofact = "2015";
    
    	
    	Calendar c1 = Calendar.getInstance();
    	String aniofact = Integer.toString(c1.get(Calendar.YEAR));
    	
    	try{
	    	srv = new GeneraFacturaSrv();
	 		srvIn  = srv.instanceOfInput();
			srvOut = srv.instanceOfOutput();
	    	srvIn.setValue("idemisor", idemisor);
			srvIn.setValue("idclient", idclient);
			srvIn.setValue("tpclient", tpclient);
			srvIn.setValue("aniofact", aniofact);
			srvIn.setValue("tipofact", tipofact);
			srvIn.setValue("mcagrupa", 1);
			srvIn.setValue("fhfactur", fechafac);
			srvIn.setValue("formpago", "");
			srvIn.setValue("condpago", "");
			srvIn.setValue("catefact", "MV");
			
			if(idemisor.equals("1")){
				srvIn.setValue("tipologo", tpclient);
				srvIn.setValue("tipoclie", tipoclie);
				
			}else{
				srvIn.setValue("tipologo", idemisor);
			}
			
			
			srvIn.setValue("informda","");
			srvIn.setValue("listimei",listimei);
			srv.setConnection(con);
			srv.process(srvIn, srvOut);
			filecrea = srvOut.getStringValue("filecrea");
	    	
    	}catch(Exception ex){
    		System.out.println("Fallo al crear PDF factura " + ex.getMessage());
    	}
	
    }
    
    
  
    
    public void updEstadoProducto( String idunicox, String newstate, String idclient){
    	try{
	    	UpdEstadoProBDIn estadoBDIn = new UpdEstadoProBDIn();
	    	estadoBDIn.setValue("idemisor",idemisor);
	    	estadoBDIn.setValue("idclient",idclient);
	    	estadoBDIn.setValue("idunicox",idunicox);
			estadoBDIn.setValue("iduserxx",cduserid);
			
			estadoBDIn.setValue("newstate",newstate);
			
			UpdEstadoProBD estadoBD = new UpdEstadoProBD(estadoBDIn);
			estadoBD.setConnection(con);
			estadoBD.execInsert();
			
    	}catch(Exception ex){
    		System.err.println("Error al actualizar estado del producto");
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
      
      public String fechaHoy(){
      
      	String fechaHoy = "dd/mm/yyyy";
      	
      	try {
  		    Calendar cal = GregorianCalendar.getInstance();
  		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
  		    
  		    System.out.println(" Fecha Hoy: "+sdf.format(cal.getTime()));
  		    fechaHoy = sdf.format(cal.getTime());
  		    
      	} catch (Exception e) {
      		fechaHoy = "01/01/2014";
      	}
      	
  		return fechaHoy;
  	    
      }
    
}
	