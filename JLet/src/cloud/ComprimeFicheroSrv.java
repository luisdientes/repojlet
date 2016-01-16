package cloud;


import java.io.File;

import utils.CloudFunctions;
import utils.PropiedadesJLet;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import cloud.bd.ListArchivosBD;
import cloud.bd.ListArchivosBDIn;
import cloud.bd.UpdateCloudBD;
import cloud.bd.UpdateCloudBDIn;



public class ComprimeFicheroSrv extends Service {
	
	
	String idemisor = null;
	String pathfile = null;
	String txdirect = null;
	String txnombre = null;
	String namezipx  = "prueba";
	String rutaabso = null;

    public ComprimeFicheroSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();						 			
			
			input.addVariable("idemisor");
			input.addVariable("filepath");
			input.addVariable("txdirect");
			input.addVariable("listfile");
			input.addVariable("txnombre");
			input.addVariable("namezipx");
			
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
			output.addVariable("grArchiv");
		
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	
    	Grid grArchiv = null;
    	String  listfile ="";
    	
     
        try{  
        	
        	String folderin = "";
        	String folderou = "";
        	String txnombre = "";
        	String cduserid = "";
        	
        	idemisor = input.getStringValue("idemisor");
        	pathfile = input.getStringValue("filepath");
        	txdirect = input.getStringValue("txdirect");
        	txnombre = input.getStringValue("txnombre");
        	
        	
        	
        	try{
        		cduserid = (String) sesion.getAttribute("idusuari");
        	}catch(Exception ex){
        		System.out.println("Error al recuperar sesion de usuario");
        		cduserid = input.getStringValue("cduserid"); 
        		
        		if(cduserid.equals("")){
        			cduserid ="-1";
        		}
        		
        	}
        	
        	
        	ListArchivosBDIn archivoBDIn = new ListArchivosBDIn();
        	archivoBDIn.setValue("idemisor",idemisor);
        	archivoBDIn.setValue("filepath",pathfile);
        	archivoBDIn.setValue("cduserid",cduserid);
        	archivoBDIn.setValue("tipofich","F");
        	
        	ListArchivosBD archivoBD = new ListArchivosBD(archivoBDIn);
        	archivoBD.setConnection(con);
        	grArchiv = archivoBD.execSelect();
        	
        	
        	for ( int i= 0; i < grArchiv.rowCount(); i++){ 
        		listfile += grArchiv.getStringCell(i, "txnombre")+",";
        	}
        	
        	
        	
        	File file = null;
        	//namezipx = input.getStringValue("namezipx");
        	folderin = PropiedadesJLet.getInstance().getProperty("path.gen.cloud") + "emisor_"+ idemisor + "/"+pathfile+"/"; 
        	folderou = PropiedadesJLet.getInstance().getProperty("path.gen.cloud") + "emisor_"+ idemisor + "/"+txdirect+"/"; 
        	
        	file = new File( folderou,txnombre+".zip");
        	
        	if(!file.exists()){
        		
        		System.out.println("Existe");
        		
        		UpdateCloudBDIn updarchiBDIn = new UpdateCloudBDIn();
            	updarchiBDIn.setValue("idemisor",idemisor);
            	updarchiBDIn.setValue("txnombre",txnombre+".zip");
            	updarchiBDIn.setValue("filepath",txdirect);
            	updarchiBDIn.setValue("permgrup","N");
            	updarchiBDIn.setValue("tipofich","Z");
            	updarchiBDIn.setValue("idtamano","0");
            	updarchiBDIn.setValue("propieta",cduserid); // cambiar y coger idpropieta de la sesion
               	
               	UpdateCloudBD updarchivBD = new UpdateCloudBD(updarchiBDIn);
               	updarchivBD.setConnection(con);
               	updarchivBD.execInsert();
        	}
        	
  
           	ListArchivosBDIn archiBDIn = new ListArchivosBDIn();
        	archiBDIn.setValue("idemisor",idemisor);
        	archiBDIn.setValue("filepath",txdirect);
        	
        	ListArchivosBD archivBD = new ListArchivosBD(archiBDIn);
        	archivBD.setConnection(con);
        	grArchiv = archivBD.execSelect();


        	CloudFunctions.comprimirFicheros(folderou,folderin, listfile,txnombre);

        	output.setValue("idemisor", idemisor);
        	output.setValue("rutaabso", folderin); // para imagenes 
        	output.setValue("folderin", txdirect);
        	output.setValue("cduserid", cduserid);
        	output.setValue("grArchiv", grArchiv);


        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
     
}