package factura;


import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import common.bd.ListDivEmisorBD;
import common.bd.ListDivEmisorBDIn;

import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import factura.bd.ListLineasBD;
import factura.bd.ListLineasBDIn;
import factura.bd.MaxFacturaBD;
import factura.bd.MaxFacturaBDIn;
import factura.bd.UpdLineasBD;
import factura.bd.UpdLineasBDIn;


public class TempFacturaSrv extends Service {

    public TempFacturaSrv() {
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
			input.addVariable("tipoPorc");
			input.addVariable("estaalba");
			input.addVariable("cdestado");	
			input.addVariable("porcrete");	
			input.addVariable("actualiz");	
			
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
			output.addVariable("idfactur");
			output.addVariable("txmensaj");
			output.addVariable("gridLine");
			output.addVariable("porcenta");
			output.addVariable("porcrete");
			output.addVariable("cddivisa");
			
			
			
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
		String estaalba = "";
		String cdestado = "";
		String porcrete = "";
		String cddivisa = "";
		String actualiz = "";
		
        //Varibales de salida
        String txmensaj = "";
        Grid gridResu= null;
        Grid gridDivi = null;
        //Otras Variables
        int nfactura = 0;
        try {
			tipoOper = input.getStringValue("tipoOper");
			concepto = CodificaCadena(input.getStringValue("concepto"));// CodificaCadena();
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
			porcenta = input.getStringValue("tipoPorc");
			estaalba = input.getStringValue("estaalba"); 
			cdestado = input.getStringValue("cdestado"); 
			porcrete = input.getStringValue("porcrete"); 
			actualiz = input.getStringValue("actualiz");

		    
		    try{        
		    	
		    	if (concepto != null){
		    		concepto = concepto.replaceAll("porcient", "%");
			    }
	        	
			    if (descuent == null || descuent.equals("")){
			    	descuent="0";
			    }
			    
			    if (porcrete == null || porcrete.equals("")){
			    	porcrete="0";
			    }

			    if ((tipoOper != null) && (tipoOper.equals("C"))){ // SI ES CLIENTE.
	
			    	MaxFacturaBDIn factBDIn = new MaxFacturaBDIn();
					MaxFacturaBD factBD = new MaxFacturaBD(factBDIn);
					factBD.setConnection(con);
					Grid gridFact = factBD.execSelect();
					if (gridFact.rowCount() > 0){
						idfactur = gridFact.getStringCell(0, "idfactur");
					}
				   
			    } else if ((tipoOper != null) && (tipoOper.equals("B"))){  // borrar lineas TMP
	  			    		
			    	UpdLineasBDIn DelLinBDIn= new UpdLineasBDIn();
			    	DelLinBDIn.setValue("idlineax",idlineax);
					UpdLineasBD updLinBD = new UpdLineasBD(DelLinBDIn);
					updLinBD.setConnection(con);
					int liDelete = updLinBD.execDelete();
						
					if (liDelete == 1){
						txmensaj = "Se ha borrado una línea correctamente.";
					} else {
						txmensaj = "Se ha producido un error en el borrado ("+ liDelete +")";
					}
						
						
			    } else if ((tipoOper != null) && (tipoOper.equals("I"))){  // inserta lineas
			    		
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
			    		InsLinBDIn.setValue("estaalba",estaalba);
			    		InsLinBDIn.setValue("cdestado",cdestado);
			    		InsLinBDIn.setValue("actualiz",actualiz);
			    		
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
			    
			    //listado lineas cliente
			    
				    ListLineasBDIn lineasBDIn= new ListLineasBDIn();
					lineasBDIn.setValue("idclient",idclient);
					lineasBDIn.setValue("tpclient",tpclient);
					lineasBDIn.setValue("idemisor",idemisor);
					lineasBDIn.setValue("cdestado","P");
					lineasBDIn.setValue("actualiz",actualiz);
					lineasBDIn.setValue("idtmpfra",idfactur);
					
					ListLineasBD lineasBD = new ListLineasBD(lineasBDIn);
					lineasBD.setConnection(con);
					gridResu = lineasBD.execSelect();
					
					
				// divisa
					ListDivEmisorBDIn divisaBDIn= new ListDivEmisorBDIn();
					divisaBDIn.setValue("idemisor", idemisor);
					ListDivEmisorBD divisaBD = new ListDivEmisorBD(divisaBDIn);
					divisaBD.setConnection(con);
					gridDivi = divisaBD.execSelect();
					cddivisa = gridDivi.getStringCell(0, "divsimbo");
					
		        
				if ((tipoOper != null) && (tipoOper.equals("C"))  && (gridResu.rowCount() == 0)){
					nfactura = Integer.parseInt(idfactur);
					idfactur = String.valueOf(++nfactura);
				}
				
				
	        	//RECUPERO LOS VALORES DEL INPUT
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    output.setValue("idemisor", idemisor);
		    output.setValue("cddivisa", cddivisa);
		    output.setValue("idfactur", idfactur);
        	output.setValue("txmensaj", txmensaj);
        	output.setGrid("gridLine", gridResu);
        	output.setValue("porcenta", porcenta);
        	output.setValue("porcrete", porcrete);
        	
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
    
    public String CodificaCadena(String cadena){
    	
    	
    	
    	String originalText = "";
  
    	System.out.println("cadena sincodifica  :"+cadena);

    	if(cadena !=null){
    		
    		try {
    			CharsetEncoder isoEncoder = Charset.forName("ISO-8859-1").newEncoder();
    			CharsetEncoder utf8Encoder = Charset.forName("UTF-8").newEncoder();
    			Boolean isISO = isoEncoder.canEncode(cadena);
    			Boolean isUTF8 =  utf8Encoder.canEncode(cadena);
    			isoEncoder.canEncode(cadena);
    			
    			
    			
    			    Charset utf8charset = Charset.forName("UTF-8");
    			    Charset iso88591charset = Charset.forName("ISO-8859-1");
    			 
    			    // Decode UTF-8
    			    ByteBuffer bb = ByteBuffer.wrap(cadena.getBytes());
    			    CharBuffer data = utf8charset.decode(bb);
    			 
    			    // Encode ISO-8559-1
    			    ByteBuffer outputBuffer = iso88591charset.encode(data);
    			    byte[] outputData = outputBuffer.array();
    			 
    			    String datos = new String(outputData);
    			    byte[] utf8BytesStream = cadena.getBytes("ISO-8859-1");
	    		//	cadena = new String(utf8BytesStream, "UTF8");
    		
	    	
	    
	    			
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	
   	 }
    	
    	System.out.println("cadena codifica  :"+cadena);

 			return cadena;
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
	