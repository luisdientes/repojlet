package factura;


import comercio.bd.UpdStockBD;
import comercio.bd.UpdStockBDIn;
import factura.bd.ListFacturasBD;
import factura.bd.ListFacturasBDIn;
import facturacion.bd.UpdCodigoFacturaBD;
import facturacion.bd.UpdCodigoFacturaBDIn;
import facturacion.bd.UpdFacturaBD;
import facturacion.bd.UpdFacturaBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;


public class BorraFacturaSrv extends Service {
	
	//Varibales de entrada
    String idfactur  = "";
    String idemisor  = "";
    String tipofact  = "";
    String aniofact  = ""; 
    
    Grid gridFact = null;
    
    //variable salida
    
    String txmensaj = "";
	
	

    public BorraFacturaSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();						 
			input.addVariable("idfactur");				 
			input.addVariable("idemisor");		
			input.addVariable("tpfactur");	
			input.addVariable("aniofact");
					
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
			output.addVariable("txmensaj");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	
       
        
        int npantall 	 = 0;
        
        //Varibales de salida
        String cderror   = "";
        String txerror   = "";
      
        
        //Otras Variables
       
        
        try{        	
        	
        	//RECUPERO SESION y DATOS QUE ME INTERESAN
        	
     
        	
        	//RECUPERO LOS VALORES DEL INPUT
        	idfactur = input.getStringValue("idfactur");
        	idemisor = input.getStringValue("idemisor");
        	tipofact = input.getStringValue("tpfactur");
        	aniofact = input.getStringValue("aniofact");
        	
        	try{
        		actualizaStock();
            	actualizaCodigoF();
            	eliminaFactura();
            	
            	txmensaj = "Factura borrada correctamente";
        		
        	}catch(Exception ex){
        		txmensaj = "Error al borrar factura";
        	}
        	
        	output.setValue("idemisor", idemisor);
        	output.setValue("txmensaj", txmensaj);

        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    

    
    public void recuperoLineasFact(String idtmpfra){
    	
    }
    
    public void actualizaStock(){
    	
    	try{
    		UpdStockBDIn devStockBDIn = new UpdStockBDIn();
			devStockBDIn.setValue("idemisor",idemisor);
			devStockBDIn.setValue("idfactur",idfactur);
			devStockBDIn.setValue("cdestado","STOCK");
			UpdStockBD devStockBD = new UpdStockBD(devStockBDIn);
			devStockBD.setConnection(con);
			devStockBD.execUpdate();

    	}catch(Exception ex){
    		System.out.println("Error al actualizar estado Stock "+ex.getMessage());
    		txmensaj = "Error al borrar factura";
    	}
    	
    }
    
    
    public void actualizaCodigoF(){
    	int nresults ;
    	try{
			
			UpdCodigoFacturaBDIn codFacturaBDIn = new UpdCodigoFacturaBDIn();
        	codFacturaBDIn.setValue("idemisor",idemisor);
        	codFacturaBDIn.setValue("aniofact",aniofact);
        	codFacturaBDIn.setValue("tipofact",tipofact);
        	codFacturaBDIn.setValue("restacod","S");
        	
        	UpdCodigoFacturaBD codFacturaBD = new UpdCodigoFacturaBD(codFacturaBDIn);
        	codFacturaBD.setConnection(con);
        	nresults = codFacturaBD.execUpdate();
			
	        if (nresults != 1){
	        	throw new Exception();
	        }
		} catch (Exception e) {
			System.err.println(this.getClass().getName() +" ERROR GRAVE - No se ha podido actualizar el codigo de factura. "+ e.getMessage());
			txmensaj = "Error al borrar factura";
		}
    	
    }
    
    public void eliminaFactura(){
    	
    	int nresults ;
    	try{
    		UpdFacturaBDIn delFacturaBDIn = new UpdFacturaBDIn();
    		delFacturaBDIn.setValue("idemisor",idemisor);
    		delFacturaBDIn.setValue("idfactur",idfactur);
    		
    		UpdFacturaBD delFacturaBD = new UpdFacturaBD(delFacturaBDIn);
    		delFacturaBD.setConnection(con);
    		
    		nresults = delFacturaBD.execDelete();
			
	        if (nresults != 1){
	        	throw new Exception();
	        }	
    		
    	}catch(Exception e) {
    		System.out.println("Error al borrar Factura");
    	}
    	
    }
    
    

    

       
}
	