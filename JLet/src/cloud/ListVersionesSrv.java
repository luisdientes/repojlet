package cloud;


import utils.PropiedadesJLet;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import cloud.bd.ListVersionesBD;
import cloud.bd.ListVersionesBDIn;



public class ListVersionesSrv extends Service {
	
	
	String idemisor = "";
	String idinodox = "";
	String filepath = "";
	String txdirect = "";
	String rutaabso = "";
	String tipoperm = "";
	String propieta = "";
	String txmensaj = "";
	String cduserid = "";
	String numversi = "0";
	String txnombre = "0";
	Grid   grArchiv = null;

    public ListVersionesSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();						 			
			
			input.addVariable("idemisor");
			input.addVariable("idinodox");
			input.addVariable("tpclient");
			input.addVariable("filepath");
			input.addVariable("txdirect");
			input.addVariable("tipoperm");
			input.addVariable("propieta");
			
			
			
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
			output.addVariable("folderin");
			output.addVariable("rutaabso");
			output.addVariable("cduserid");
			output.addVariable("txmensaj");
			output.addVariable("txnombre");
			output.addVariable("numversi");
		
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    
     
        try{   
        	
        	idemisor = input.getStringValue("idemisor");
        	idinodox = input.getStringValue("idinodox");
        	filepath = input.getStringValue("filepath");
        	txdirect = input.getStringValue("txdirect");
        	tipoperm = input.getStringValue("tipoperm");
        	propieta = input.getStringValue("propieta");
        	
 
        	
        	String folderin = PropiedadesJLet.getInstance().getProperty("path.gen.cloud") + "emisor_"+ idemisor + "/"; 
        	
        	rutaabso = folderin +txdirect +filepath;
 	        
 	        if(filepath != null){
 	        	folderin =  txdirect +filepath;
 	        }else{
 	        		folderin = "/";
 	        	}
 	     
        	//archivos
        	ListVersionesBDIn archiBDIn = new ListVersionesBDIn();
        	archiBDIn.setValue("idinodox",idinodox);
        	ListVersionesBD archivBD = new ListVersionesBD(archiBDIn);
        	archivBD.setConnection(con);
        	grArchiv = archivBD.execSelect();
        	
        	numversi   = grArchiv.getStringCell(0, "cuantosx");
			txnombre   = grArchiv.getStringCell(0, "txnombre");

        	//RECUPERO LOS VALORES DEL INPUT
        	output.setValue("idemisor", idemisor);
        	output.setValue("rutaabso", rutaabso); // para imagenes 
        	output.setValue("folderin", folderin);
        	output.setValue("cduserid", cduserid);
        	output.setValue("txmensaj", txmensaj);
        	output.setValue("numversi", numversi);
        	output.setValue("txnombre", txnombre);
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
    
       
}