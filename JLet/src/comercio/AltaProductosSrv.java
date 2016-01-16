package comercio;


import java.io.File;

import jxl.Sheet;
import jxl.Workbook;
import utils.PropiedadesJLet;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;


public class AltaProductosSrv extends Service {

    public AltaProductosSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("filename");
			
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
			output.addVariable("gdDivisa");
			output.addVariable("imptotal");
			
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
        Grid gdDivisa = null;
        
        
        //Otras Variables
        int filaInic 	= 1;
        int calcImpt	= 0;
        String pathinpf = "";
        String pathfile = "";
        
        
        Workbook workbook;
        
        try {
        	
        	filename = input.getStringValue("filename");
        	System.out.println(this.getClass().getName() +"Filename "+ filename +" - ");
        	
        	pathinpf = PropiedadesJLet.getInstance().getProperty("comercio.file.input");
        	pathfile = pathinpf + filename;
			
			workbook = Workbook.getWorkbook(new File(pathfile));
	        Sheet sheet = workbook.getSheet(0);
	 
        	double dimptotal = 0;
	        
	        for (int i = filaInic; i < sheet.getRows(); i++) {
	        
	        	double dunidades = 1;
	        	double dimprecio = 0;
	        	
		        String idproduc  = sheet.getCell(0,i).getContents();
		        String unidades  = sheet.getCell(1,i).getContents();
		        String txnombre  = sheet.getCell(2,i).getContents();
		        String imprecio  = sheet.getCell(3,i).getContents().replaceAll(",",".");
		        
		        try {
		        	dunidades = Double.parseDouble(unidades); 
		        } catch (Exception e) {
		        	dunidades = 1;
		        	txmensaj = "WARNING - Line "+ i +" Establecemos numero unidades por defecto (1)\n";
		        	System.out.println("WARNING - Line "+ i +" Establecemos numero unidades por defecto (1)");
		        }
		        
		        try {
		        	dimprecio = Double.parseDouble(imprecio); 
		        } catch (Exception e) {
		        	dimprecio = 0;
		        	txmensaj = "ERROR - Line "+ i +" No se ha recibido precio. Establecemos precio por defecto (0 USD)\n";
		        	System.out.println("WARNING - Line "+ i +" No se ha recibido precio. Establecemos precio por defecto (0 USD)");
		        }
		       		        
		        dimptotal += (dunidades * dimprecio);
		        System.out.println("idproduc "+ idproduc +" | unidades "+ unidades +" | txnombre "+ txnombre +" | imprecio "+ imprecio);
		    
		        
	        }
	        
	        InitDivisasSrv initDivisasSrv = new InitDivisasSrv();
	        ObjectIO inputDiv  = initDivisasSrv.instanceOfInput();
	        ObjectIO outputDiv = initDivisasSrv.instanceOfOutput();
	        initDivisasSrv.setConnection(con);
	        initDivisasSrv.process(inputDiv, outputDiv);
	        
	        gdDivisa = outputDiv.getGrid("gdDivisa");
	        
	        
		    output.setValue("txmensaj", txmensaj);
		    output.setValue("gdDivisa", gdDivisa);
		    output.setValue("imptotal", dimptotal);
        	
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
	