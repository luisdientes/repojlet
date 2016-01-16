package cloud;


import java.io.File;

import utils.PropiedadesJLet;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import cloud.bd.ListArchivosBD;
import cloud.bd.ListArchivosBDIn;
import cloud.bd.UpdateCloudBD;
import cloud.bd.UpdateCloudBDIn;



public class CreaDirectorioSrv extends Service {
	
	
	String idemisor = null;
	String tpclient = null;
	String rutaabso = null;
	String idtamano = null;
	String txmensaj = null;
	String cduserid = null;
	Grid   grArchiv = null;

    public CreaDirectorioSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();						 			
			
			input.addVariable("idemisor");
			input.addVariable("tipofich");
			input.addVariable("txdirect");
			input.addVariable("permgrup");
			input.addVariable("txnombre");
			input.addVariable("idtamano");
			input.addVariable("cduserid");
			
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
			output.addVariable("tipoperm");
			output.addVariable("grArchiv");
		
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	
    	String tipofich = null;
    	String filepath = null;
    	String permgrup = null;
    	String txnombre = null;
    	String folderin = "";
    	String idtamano = "";
        
        //Varibales de salida
        int idfactur = 0;
        Grid gridClie = null;
        Grid gridPhon = null;
  
        
        //Otras Variables
     
        try{   
        	
        	idemisor = input.getStringValue("idemisor");
        	tipofich = input.getStringValue("tipofich");
        	filepath = input.getStringValue("txdirect");
        	permgrup = input.getStringValue("permgrup");
        	txnombre = input.getStringValue("txnombre"); 
        	idtamano = input.getStringValue("idtamano"); 
        	
        	try{
        		cduserid = (String) sesion.getAttribute("idusuari");
        	}catch(Exception ex){
        		System.out.println("Error al recuperar sesion de usuario");
        		cduserid = input.getStringValue("cduserid"); 
        		
        		if(cduserid.equals("")){
        			cduserid ="-1";
        		}
        		
        	}
        	
        	
        	/*COMPRUEBO SI EXISTE EN BBDD*/
        	
        	ListArchivosBDIn archivoBDIn = new ListArchivosBDIn();
        	archivoBDIn.setValue("idemisor",idemisor);
        	archivoBDIn.setValue("filepath",filepath);
        	archivoBDIn.setValue("txnombre",txnombre);
        	ListArchivosBD archivoBD = new ListArchivosBD(archivoBDIn);
        	archivoBD.setConnection(con);
        	grArchiv = archivoBD.execSelect();
        	
        	int existe = grArchiv.rowCount();
        	
        	/*COMPURBEO SI EXISTE EN BBDD*/
        	
        
        	
        	if(tipofich.equals("D")){
        		folderin = PropiedadesJLet.getInstance().getProperty("path.gen.cloud") + "emisor_"+ idemisor + "/"+filepath+"/"+txnombre; 
        		File infolder = new File(folderin);
 	        
        		if (!infolder.isDirectory() || existe == 0){
        			infolder.mkdirs();
        			
        			UpdateCloudBDIn archiBDIn = new UpdateCloudBDIn();
                	archiBDIn.setValue("idemisor",idemisor);
                	archiBDIn.setValue("txnombre",txnombre);
                	archiBDIn.setValue("filepath",filepath);
                	archiBDIn.setValue("permgrup",permgrup);
                	archiBDIn.setValue("tipofich",tipofich);
                	archiBDIn.setValue("idtamano",idtamano);
                	archiBDIn.setValue("propieta",cduserid); // cambiar y coger idpropieta de la sesion
                	
                	UpdateCloudBD archivBD = new UpdateCloudBD(archiBDIn);
                	archivBD.setConnection(con);
                	archivBD.execInsert();
        		}else{
        			txmensaj = "El directorio existe";
        		}
        	}else{
        		UpdateCloudBDIn archiBDIn = new UpdateCloudBDIn();
            	archiBDIn.setValue("idemisor",idemisor);
            	archiBDIn.setValue("txnombre",txnombre);
            	archiBDIn.setValue("filepath",filepath);
            	archiBDIn.setValue("permgrup",permgrup);
            	archiBDIn.setValue("tipofich",tipofich);
            	archiBDIn.setValue("idtamano",idtamano);
            	archiBDIn.setValue("propieta",cduserid); // cambiar y coger idpropieta de la sesion
            	
            	UpdateCloudBD archivBD = new UpdateCloudBD(archiBDIn);
            	archivBD.setConnection(con);
            	archivBD.execInsert();
        		
        	}
        	
        	
        	
        	
        	ListArchivosBDIn archiBDIn = new ListArchivosBDIn();
        	archiBDIn.setValue("idemisor",idemisor);
        	archiBDIn.setValue("tpclient",tpclient);
        	archiBDIn.setValue("filepath",filepath);
        	
        	ListArchivosBD archivBD = new ListArchivosBD(archiBDIn);
        	archivBD.setConnection(con);
        	grArchiv = archivBD.execSelect();
        
        	//CLIENTES
 	       

        	//RECUPERO LOS VALORES DEL INPUT
        	output.setValue("idemisor", idemisor);
        	output.setValue("tpclient", tpclient);
        	output.setValue("rutaabso", folderin);
        	output.setValue("cduserid", cduserid);
        	output.setValue("tipoperm", "S");
        	output.setValue("txmensaj", txmensaj);
        	output.setValue("grArchiv", grArchiv);
        	output.setValue("folderin", filepath);
        	
        	
        	

        } catch (Exception e1) {
			e1.getMessage();
		}            
        
    }
    
    
    public Grid tipoPermisos(String filepath, String txnombre){
    	
    	Grid gdPermiso = null;
    	
    	try{
	    	ListArchivosBDIn archiBDIn = new ListArchivosBDIn();
	    	archiBDIn.setValue("idemisor",idemisor);
	    	archiBDIn.setValue("tpclient",tpclient);
	    	archiBDIn.setValue("filepath",filepath);
	    	archiBDIn.setValue("txnombre",txnombre);
	    	ListArchivosBD archivBD = new ListArchivosBD(archiBDIn);
	    	archivBD.setConnection(con);
	    	gdPermiso = archivBD.execSelect();
	    }catch(Exception ex){
    		
	    	System.out.println("Error al recuperar Permisos "+ex.getMessage());
    	}
    	
    	return gdPermiso;
    }
     
}