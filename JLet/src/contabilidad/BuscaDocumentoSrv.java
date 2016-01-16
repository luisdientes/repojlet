package contabilidad;



import cloud.bd.ListArchivosBD;
import cloud.bd.ListArchivosBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import contabilidad.bd.UpdateApunteBD;
import contabilidad.bd.UpdateApunteBDIn;
import factura.bd.DetalleFacturasBD;
import factura.bd.DetalleFacturasBDIn;

public class BuscaDocumentoSrv extends Service {

	public BuscaDocumentoSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("idemisor");
			input.addVariable("tpdocume");
			input.addVariable("iddocume");
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
			output.addVariable("filecrea");
			output.addVariable("iddocume");
			output.addVariable("tipofile");
			output.addVariable("directcl");
			
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor = "";
    	String tpdocume = "";
    	String iddocume = "";
    	String filename = "";
    	String tipofile = "";
    	String directcl = "";
    	Grid gdDocumen = null;
    
    

        //Varibales de salida
    	
    	String txmensaj = "";
        //Otras Variables
        
        try {
        	
        	idemisor = input.getStringValue("idemisor");
        	tpdocume = input.getStringValue("tpdocume");
        	iddocume = input.getStringValue("iddocume");
        	
        		if (tpdocume != null && tpdocume.equals("G")){ // FACTURAS;
        			
        			DetalleFacturasBDIn detalleFaBDIn = new DetalleFacturasBDIn(); 
		        	detalleFaBDIn.setValue("idfactur", iddocume);
		        	DetalleFacturasBD detalleFaBD = new DetalleFacturasBD(detalleFaBDIn); 
		        	detalleFaBD.setConnection(con);
		        	gdDocumen = detalleFaBD.execSelect();
		        	filename = gdDocumen.getStringCell(0, "filecrea");
		        	tipofile ="FRA";
        			
        		}else{ //CLOUD
        			ListArchivosBDIn listCloudBDIn = new ListArchivosBDIn();
        			listCloudBDIn.setValue("idemisor", idemisor);
        			listCloudBDIn.setValue("idinodox", iddocume);
                	ListArchivosBD updApuntesBD = new ListArchivosBD(listCloudBDIn);
                	updApuntesBD.setConnection(con);
                	gdDocumen = updApuntesBD.execSelect();
		        	filename = gdDocumen.getStringCell(0, "txnombre");
		        	directcl = gdDocumen.getStringCell(0, "filepath");
                	tipofile ="CLO";
           
        			
        		}
        	output.setValue("idemisor", idemisor);
        	output.setValue("filecrea", filename);
        	output.setValue("tipofile", tipofile);
        	output.setValue("directcl", directcl);
        	output.setValue("txmensaj", txmensaj);	

        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
    
    
}
	