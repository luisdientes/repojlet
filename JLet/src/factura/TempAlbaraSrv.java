package factura;


import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import factura.bd.UpdLineasAlbaBD;
import factura.bd.UpdLineasAlbaBDIn;
import factura.bd.UpdLineasBD;
import factura.bd.UpdLineasBDIn;


public class TempAlbaraSrv extends Service {

    public TempAlbaraSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();	
			input.addVariable("tipoOper");					 			
			input.addVariable("concepto");
			input.addVariable("idclient");
			input.addVariable("tpclient");
			input.addVariable("codprodu");
			input.addVariable("idunicox");
			input.addVariable("idlineax");
			input.addVariable("idfactu");
			input.addVariable("idemisor");
			input.addVariable("nlineaxx");
			input.addVariable("precLinea");
			input.addVariable("descLinea");
			input.addVariable("cantLinea");
			input.addVariable("totalLinea");
			input.addVariable("fechafac");
			input.addVariable("fhalta");
			input.addVariable("tipoFact");
			input.addVariable("tipoPorc");
			input.addVariable("formpago");
			input.addVariable("condpago");
			input.addVariable("catefact");
			input.addVariable("tipologo");
			input.addVariable("idalbara");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
			output.addVariable("idfactur");
			output.addVariable("txmensaj");
			output.addVariable("gridLine");
			output.addVariable("fechafac");
			output.addVariable("aniofact");
			output.addVariable("idclient");
			output.addVariable("tpclient");
			output.addVariable("idemisor");
			output.addVariable("tipofact");
			output.addVariable("porcenta");
			output.addVariable("formpago");
			output.addVariable("condpago");
			output.addVariable("catefact");
			output.addVariable("tipologo");
			output.addVariable("cdfactur");
			
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String tipoOper = "";
    	String concepto = "";
		String idclient = "";
		String tpclient = "";
		String codprodu = "";
		String idunicox = "";
		String idlineax = "";
		String idfactur = "";
	    String idemisor = "";
	    String nlineaxx = "";
	    String precioun = "";
	    String descuent = "";
	    String cantidad = "";
	    String precioto = "";
		String fechafac = "";
		String porcenta = "";
		String formpago = "";
		String condpago = "";
		String catefact = "";
		String tipologo = "";
		String idalbara = "";
		
        //Varibales de salida
        String txmensaj = "";
        String aniofact = "";
        String tipofact = "";
        Grid gridResu= null;
        
        //Otras Variables
        int nfactura = 0;
     
        try {
			tipoOper = input.getStringValue("tipoOper");
			
			concepto = input.getStringValue("concepto");
			idclient = input.getStringValue("idclient");
			tpclient = input.getStringValue("tpclient");
			codprodu = input.getStringValue("codprodu");
			idunicox = input.getStringValue("idunicox");
			idlineax = input.getStringValue("idlineax");
			idfactur = input.getStringValue("idfactu");
		    idemisor = input.getStringValue("idemisor");
		    nlineaxx = input.getStringValue("nlineaxx");
		    precioun = input.getStringValue("precLinea");
		    descuent = input.getStringValue("descLinea");
		    cantidad = input.getStringValue("cantLinea");
		    precioto = input.getStringValue("totalLinea");
			fechafac = input.getStringValue("fechafac");
			tipofact = input.getStringValue("tipoFact");
			porcenta = input.getStringValue("tipoPorc");
			formpago = input.getStringValue("formpago");
			condpago = input.getStringValue("condpago");
			catefact = input.getStringValue("catefact");
			tipologo = input.getStringValue("tipologo");
			idalbara = input.getStringValue("idalbara");
			
			System.out.println(this.getClass() +"tpclient:  "+ tpclient);
			
			if(fechafac!=null && !fechafac.equals("")){
				aniofact = fechafac.substring(6, 10);
			}	
			
			fechafac = fechaMySQL(fechafac);
		    
		    try{        	
	        	
			    if (descuent == null || descuent.equals("")){
			    	descuent="0";
			    }
		    
			    if ((tipoOper != null) && (tipoOper.equals("AC"))){
			    	
			    	
	    			UpdLineasAlbaBDIn upLineasAlbaBDin = new UpdLineasAlbaBDIn();
	    			upLineasAlbaBDin.setValue("idlineax", idlineax);
	    			upLineasAlbaBDin.setValue("precioun", precioun);
	    			upLineasAlbaBDin.setValue("descuent", descuent);
	    			upLineasAlbaBDin.setValue("precioto", precioto);
	    			UpdLineasAlbaBD upLinAlbaBD = new UpdLineasAlbaBD(upLineasAlbaBDin);
	    			upLinAlbaBD.setConnection(con);
					int actualiza = upLinAlbaBD.execUpdate();
					
					System.out.println("Se han actualizado "+actualiza+" lineas del Albaran");
					if (actualiza > 0){
						txmensaj = "Se ha validado la factura correctamente.";
				    } else {
				    	txmensaj = "Se ha producido un error al validar la factura ("+ actualiza +")";
				    }
			    			
			    }  else if ((tipoOper != null) && (tipoOper.equals("I"))){
			    		
			    	UpdLineasBDIn InsLinBDIn= new UpdLineasBDIn();
			    	InsLinBDIn.setValue("idlineax",idlineax);
			    	InsLinBDIn.setValue("codprodu",codprodu);
		    		InsLinBDIn.setValue("idfactur",idfactur);
		    		InsLinBDIn.setValue("idemisor",idemisor);
		    		InsLinBDIn.setValue("idclient",idclient);
		    		InsLinBDIn.setValue("tpclient",tpclient);
		    		InsLinBDIn.setValue("fechafac",fechafac);
		    		InsLinBDIn.setValue("nlineaxx",nlineaxx);
		    		InsLinBDIn.setValue("cantidad",cantidad);
		    		InsLinBDIn.setValue("concepto",concepto);
		    		InsLinBDIn.setValue("precioun",precioun);
		    		InsLinBDIn.setValue("descuent",descuent);
		    		InsLinBDIn.setValue("precioto",precioto);
		    		InsLinBDIn.setValue("idunicox",idunicox);
		    		UpdLineasBD insLinBD = new UpdLineasBD(InsLinBDIn);
		    		insLinBD.setConnection(con);
					int liInsert = insLinBD.execInsert();
					
					if (liInsert == 1){
						txmensaj = "Se ha insertado la línea correctamente.";
				    } else {
				    	txmensaj = "Se ha producido un error al insertar la línea ("+ liInsert +")";
				    }
					
			    } else {
			    	txmensaj = "NO se recibio ningún tipo de operación";
			    	System.err.println(this.getClass().getName() +"- ERROR - "+ txmensaj);
			    }
			    
			    Service srv = null;
		    	
		    	ObjectIO srvIn = null;
		    	ObjectIO srvOut = null;
		    	srv = new VistaPreviaAlbaSrv();
		    	
		    	
		    	srvIn  = srv.instanceOfInput();
				srvOut = srv.instanceOfOutput();
				srvIn.setValue("idclient", idclient);
				srvIn.setValue("idalbara", idalbara);
				srvIn.setValue("idemisor", idemisor);
				srvIn.setValue("tipovist", "AL");
				
				srv.setConnection(con);
				srv.process(srvIn, srvOut);
				
				gridResu = srvOut.getGrid("gridLine");
			    
				
				
	        	//RECUPERO LOS VALORES DEL INPUT
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    output.setValue("cdfactur", idalbara);
		    output.setValue("idfactur", idfactur);
		    output.setValue("idclient", idclient);
		    output.setValue("tpclient", tpclient);
		    output.setValue("aniofact", aniofact);
		    output.setValue("fechafac", fechafac);
        	output.setValue("txmensaj", txmensaj);
        	output.setGrid("gridLine", gridResu);
        	output.setValue("idemisor", idemisor);
        	output.setValue("tipofact", tipofact);
        	output.setValue("porcenta", porcenta);
        	output.setValue("formpago", formpago);
        	output.setValue("condpago", condpago);
        	output.setValue("catefact", catefact);
        	output.setValue("tipologo", tipologo);
        	
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
	