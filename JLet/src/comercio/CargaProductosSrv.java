package comercio;


import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import comercio.bd.CargaProductosBD;
import comercio.bd.CargaProductosBDIn;
import comercio.bd.ListLineasEnvioBD;
import comercio.bd.ListLineasEnvioBDIn;
import comercio.bd.UpdLoteBD;
import comercio.bd.UpdLoteBDIn;


public class CargaProductosSrv extends Service {

    public CargaProductosSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("codeenvi");
			input.addVariable("imeicode");
			
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
			output.addVariable("gridResu");
			output.addVariable("numcarga");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String filename 	= "";

        
        //Varibales de salida
        String txmensaj = "";
        String imptotal = "";
        Grid gridResu = null;
        
        
        //Otras Variables
        String imeicode = "";
        String idcatego = "";
        String codeenvi = "";
        String idproduc = "";
        String pricechf = "";
        String idcolorx = "";
        String prefijox = "";
        String tablaBDX = "";
        
        try {
        	
        	codeenvi = input.getStringValue("codeenvi");
        	int lineasIns = 0;
        	
        
        	/*LEO LAS LINEAS DEL ENVIO QUE QUIERO IMPORTAR A PRODUCTOS*/
    		
        	ListLineasEnvioBDIn envioBDIn = new ListLineasEnvioBDIn();
    		envioBDIn.setValue("codeenvi", codeenvi);
    		ListLineasEnvioBD envioBD = new ListLineasEnvioBD(envioBDIn);
    		envioBD.setConnection(con);
    		gridResu = envioBD.execSelect();
    		
    		for(int i = 0; i< gridResu.rowCount(); i++){
    			
    			imeicode = gridResu.getStringCell(i, "imeicode");
    			
    			//SACAR PREFIJO PARA SABER SI ES PH- PI- LA- TA- Y GUARDAR EN TABLA CORRESPONDIENTE
    			prefijox = gridResu.getStringCell(i, "idproduc").substring(0,2);
    			idproduc = gridResu.getStringCell(i, "idproduc").substring(2); 
    			
    			/*QUITAMOS CEROS A LA IZQUIERDA*/
    			int producInt = Integer.parseInt(idproduc);
				idproduc = "" + producInt;
    			
    			/*OBTENGO LA TABLA*/
    		    tablaBDX =  getTabla(prefijox);
    			
    	        pricechf = gridResu.getStringCell(i, "pricechf");
    	        idcolorx = gridResu.getStringCell(i, "idcolorx");
    	        idcatego = gridResu.getStringCell(i, "idcatego");
    	        
    	        
    	        CargaProductosBDIn cargaBDIn = new CargaProductosBDIn();
    	        cargaBDIn.setValue("imeicode", imeicode);
    	        cargaBDIn.setValue("idproduc", idproduc);
    	        cargaBDIn.setValue("idcolorx", idcolorx);
    	        cargaBDIn.setValue("pricechf", pricechf);
    	        cargaBDIn.setValue("idcatego", idcatego);
    	        cargaBDIn.setValue("codeenvi", codeenvi);
    	        cargaBDIn.setValue("tablaBDX", tablaBDX);
    	    
        		CargaProductosBD cargaBD = new CargaProductosBD(cargaBDIn);
        		cargaBD.setConnection(con);
        		lineasIns = lineasIns + cargaBD.execInsert();
            	
    		}
    		/* POR ULTIMO DAR DE ALTA EL LOTE CON EL CODIGO DE ENVIO*/
    		if((lineasIns > 0) && (lineasIns == gridResu.rowCount()) ){
    			UpdLoteBDIn loteBDIn = new UpdLoteBDIn();
    			loteBDIn.setValue("codeenvi", codeenvi);
    			UpdLoteBD loteBD = new UpdLoteBD(loteBDIn);
    			loteBD.setConnection(con);
    			loteBD.execInsert();
    			
    			txmensaj = "Productos cargados con Exito, TOTAL cargados :";
    		}else{
    			txmensaj = "Hubo un error al cargar productos, TOTAL cargados :";
    		}
	        
	        
		    output.setValue("txmensaj", txmensaj);
		    output.setValue("numcarga",lineasIns);
		    output.setValue("gridResu", gridResu); //PRODUCTOS CARGADOS DEL ENVIO SELECCIONADO
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
    
    public String getTabla(String prefijox){
    		
    		String tablexx = "";
    	
			if( prefijox.equalsIgnoreCase("PH")){
				tablexx = "izumphon";
			}
			else if (prefijox.equalsIgnoreCase("PR")){
				tablexx = "izumprod";
			}
			else if (prefijox.equalsIgnoreCase("PI")){
				tablexx = "piec";
			}
			/*
			case "PE":
			case "LA":*/
    	
    	return tablexx;
    	
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
	