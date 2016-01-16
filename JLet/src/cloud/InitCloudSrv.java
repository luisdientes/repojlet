package cloud;


import java.io.File;

import utils.PropiedadesJLet;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import cloud.bd.ListArchivosBD;
import cloud.bd.ListArchivosBDIn;

import common.bd.ReconnectBD;
import common.bd.ReconnectBDIn;



public class InitCloudSrv extends Service {
	
	
	String idemisor = "";
	String tpclient = "";
	String filepath = "";
	String txdirect = "";
	String rutaabso = "";
	String tipoperm = "";
	String propieta = "";
	String txmensaj = "";
	String cduserid = "";
	Grid   grArchiv = null;

    public InitCloudSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();						 			
			
			input.addVariable("idemisor");
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
			output.addVariable("propieta");
			output.addVariable("tipoperm");
			output.addVariable("grArchiv");
		
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    
     
        try{   
        	
        	idemisor = input.getStringValue("idemisor");
        	tpclient = input.getStringValue("tpclient");
        	filepath = input.getStringValue("filepath");
        	txdirect = input.getStringValue("txdirect");
        	tipoperm = input.getStringValue("tipoperm");
        	propieta = input.getStringValue("propieta");
        	
        	System.out.println("ANTES DE user id");
        	
        	try{
        		cduserid = (String) sesion.getAttribute("idusuari");
        	}catch(Exception ex){
        		System.out.println("Error al recuperar sesion de usuario");
        		cduserid = "-1";
        	}
        	
        	if(txdirect == null){
        		txdirect = "";
        	}
        	
        	String folderin = PropiedadesJLet.getInstance().getProperty("path.gen.cloud") + "emisor_"+ idemisor + "/"; 
        	
        	rutaabso = folderin +txdirect +filepath;
 	        File infolder = new File(folderin);
 	        File folderpa = new File(folderin+"papelera/");
 	        
 	        if (!infolder.isDirectory()){
 	        	infolder.mkdirs();
 	        	folderpa.mkdirs();
 	        }
 	        
 	        String dirpermi = "";
 	        String nomdirec = "";
 	        
 	        int  indexfile = txdirect.lastIndexOf('/');
 	        
 	        if(filepath != null){
 	        	folderin =  txdirect +filepath +"/";
 	        }else{
 	        		folderin = "/";
 	        		tipoperm ="S";
 	        }
 	        
 	        
 	       Grid gdPermisos = null;
 	        
 	        if(filepath != null && filepath.equals("")){ //subiendo directorio
 	        	 nomdirec = txdirect.substring(indexfile + 1);
 	  	         dirpermi = txdirect.substring(0,indexfile+1);
 	  	         gdPermisos =  tipoPermisos(dirpermi,nomdirec);
	        	
 	 	 	    propieta  = gdPermisos.getStringCell(0,"propieta");
 	 	 	    tipoperm = gdPermisos.getStringCell(0,"permgrup"); 
 	        }
 	        
 	       if(folderin.equals("/")){
  	    	  tipoperm ="S";
  	       }

        	
           try{
        	   ReconnectBDIn reconecBDIn = new ReconnectBDIn();
        	   ReconnectBD reconecBD = new ReconnectBD(reconecBDIn);
        	   reconecBD.setConnection(con);
        	   reconecBD.execSelect();
           } catch(Exception ex){
        	   System.out.println(this.getClass().getName()+"  Error controlado -------> reconect "+ex.getMessage());
           }
 	       
        	//archivos
        	ListArchivosBDIn archiBDIn = new ListArchivosBDIn();
        	archiBDIn.setValue("idemisor",idemisor);
        	archiBDIn.setValue("tpclient",tpclient);
        	archiBDIn.setValue("filepath",folderin);
        	archiBDIn.setValue("cduserid",cduserid);
        	
        	ListArchivosBD archivBD = new ListArchivosBD(archiBDIn);
        	archivBD.setConnection(con);
        	grArchiv = archivBD.execSelect();

        	//RECUPERO LOS VALORES DEL INPUT
        	output.setValue("idemisor", idemisor);
        	output.setValue("tpclient", tpclient);
        	output.setValue("propieta", propieta);
        	output.setValue("tipoperm", tipoperm);
        	output.setValue("rutaabso", rutaabso); // para imagenes 
        	output.setValue("folderin", folderin);
        	output.setValue("cduserid", cduserid);
        	output.setValue("txmensaj", txmensaj);
        	output.setValue("grArchiv", grArchiv);
        	
        	

        } catch (Exception e1) {
			e1.printStackTrace();
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