package cloud;


import java.io.*;
import java.util.zip.*;

import utils.CloudFunctions;
import utils.PropiedadesJLet;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import cloud.bd.ListArchivosBD;
import cloud.bd.ListArchivosBDIn;
import cloud.bd.UpdateCloudBD;
import cloud.bd.UpdateCloudBDIn;



public class DesComprimeFicheroSrv extends Service {
	
	
	String idemisor = null;
	String pathfile = null;
	String txdirect = null;
	
	String namezipx  = "prueba";
	String rutaabso = null;
	String folderin = "";
	String folderou = "";
	String txnombre = "";
	String cduserid = "";
	Grid grArchiv = null;
	File file = null;
	final static int BUFFER = 2048;
    public DesComprimeFicheroSrv() {
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
        	
        	
        	
        	
        
        	//namezipx = input.getStringValue("namezipx");
        	folderin = PropiedadesJLet.getInstance().getProperty("path.gen.cloud") + "emisor_"+ idemisor + "/"+pathfile+"/"; 
        	folderou = PropiedadesJLet.getInstance().getProperty("path.gen.cloud") + "emisor_"+ idemisor + "/"+txdirect+"/"; 
        	
        	 int pos = txnombre.lastIndexOf(".");
        	 namezipx = txnombre.substring(0, pos);
        	file = new File(folderou);
        	
        	/*if(!file.isDirectory()){
        		
        		file.mkdirs();
        		UpdateCloudBDIn archiBDIn = new UpdateCloudBDIn();
            	archiBDIn.setValue("idemisor",idemisor);
            	archiBDIn.setValue("txnombre",namezipx);
            	archiBDIn.setValue("filepath",txdirect);
            	archiBDIn.setValue("permgrup","S");
            	archiBDIn.setValue("tipofich","D");
            	archiBDIn.setValue("idtamano","0");
            	archiBDIn.setValue("propieta",cduserid); // cambiar y coger idpropieta de la sesion
            	
            	UpdateCloudBD archivBD = new UpdateCloudBD(archiBDIn);
            	archivBD.setConnection(con);
            	archivBD.execInsert();
        	}*/
        	 descomprime();
        /*	if(!file.exists()){
        		
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
        	}*/
        	
  
           	ListArchivosBDIn archiBDIn = new ListArchivosBDIn();
        	archiBDIn.setValue("idemisor",idemisor);
        	archiBDIn.setValue("filepath",txdirect);
        	
        	ListArchivosBD archivBD = new ListArchivosBD(archiBDIn);
        	archivBD.setConnection(con);
        	grArchiv = archivBD.execSelect();


  /*      	CloudFunctions.comprimirFicheros(folderou,folderin, listfile,txnombre);*/

        	output.setValue("idemisor", idemisor);
        	output.setValue("rutaabso", folderin); // para imagenes 
        	output.setValue("folderin", txdirect);
        	output.setValue("cduserid", cduserid);
        	output.setValue("grArchiv", grArchiv);


        } catch (Exception e1) {
			e1.printStackTrace();
		}    
        
        
    }
    
    public void descomprime(){
    	try {
    		String tipofich= "";
    		String newfolde= "";
    		String nombrefi= "";
    		//File dirDestino = new File("prueba");
    		BufferedOutputStream dest = null;
    		FileInputStream fis = new FileInputStream(folderin);
    		ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
    		FileOutputStream fos = null;
    		ZipEntry entry;
    		int count;
    		int index = 0;
    		byte data[] = new byte[BUFFER];
    		String rutaarchivo = null;
    		String nomdirec = "";
    		
    		while((entry = zis.getNextEntry()) != null) {
    				System.out.println("Extracting:"  +entry);
    				rutaarchivo = entry.getName();
    				// tenemos que quitar el primer directorio
    				//index = rutaarchivo.indexOf("/");
    				//rutaarchivo = rutaarchivo.substring(index+1);
    				
    				/*INSERTA EN BBDD*/
    				ListArchivosBDIn archivoBDIn = new ListArchivosBDIn();
    	        	archivoBDIn.setValue("idemisor",idemisor);
    	        	archivoBDIn.setValue("filepath",txdirect);
    	        	archivoBDIn.setValue("txnombre",txnombre);
    	        	ListArchivosBD archivoBD = new ListArchivosBD(archivoBDIn);
    	        	archivoBD.setConnection(con);
    	        //	grArchiv = archivoBD.execSelect();
    	        	
    	        	//int existe = grArchiv.rowCount();
    	        	
    	        	if(entry.isDirectory()){
    	        		tipofich="D";
    	        		String cadena= "";
    	        		int  indexfile =0;
    	        		
    	        		try{
    	        			cadena = rutaarchivo.substring(0, rutaarchivo.length()-1); 
    	        		   indexfile = cadena.lastIndexOf("/");
    	        		   nomdirec = rutaarchivo.substring(indexfile);
    	        		   nombrefi = nomdirec.replaceAll("/", "");
    	        		}catch(Exception ex){
    	        		   indexfile = rutaarchivo.lastIndexOf("/");
        	        	   nomdirec = rutaarchivo.substring(indexfile);
        	        	   nombrefi = nomdirec.replaceAll("/", "");
    	        		}
    	        		 
    	        		 if(nomdirec.equals("/")){
    	        			 nomdirec = "";
    	        		 }else{
    	        			 
    	        			  cadena = rutaarchivo.substring(0, rutaarchivo.length()-1); 
	      	        		   indexfile = cadena.lastIndexOf("/");
	      	        		   nomdirec = rutaarchivo.substring(0,indexfile+1);
	      	        		   //nomdirec = nomdirec.replaceAll("/", "");
	    	        			 
	    	        			// nomdirec= rutaarchivo.replaceAll(nomdirec, "/"); 
    	        			// nomdirec= rutaarchivo.replaceAll(nomdirec, "/"); 
    	        		 }

    	        		 
    	        		 if(nombrefi.equals("")){
    	        			 nombrefi = rutaarchivo;
    	        			
    	        			 nombrefi = nombrefi.replaceAll("/", "");
    	        		 }
    	        		 
    	        		//entry = ZipEntry.newfolde;
    	        	}else{
    	        		tipofich="F";	
    	        		newfolde ="";
    	        		try{
	    	        		index = rutaarchivo.lastIndexOf("/");
	    	        		nombrefi = rutaarchivo.substring(index+1);
	    	        		
	    	        		 int  indexfile =rutaarchivo.lastIndexOf("/"); //rutaarchivo.indexOf("/");
	    	        		 nomdirec = rutaarchivo.substring(indexfile);
	    	        		 
	    	        		 if(nomdirec.equals("/")){
	    	        			 nomdirec = "";
	    	        		 }else{
	    	        			 nomdirec= rutaarchivo.replaceAll(nomdirec, "/"); 
	    	        		 }
    	        		  }catch(Exception ex){
    	        			  
    	        		  }
    	        	}
    	        	
    	        	
    	        	UpdateCloudBDIn archiBDIn = new UpdateCloudBDIn();
                	archiBDIn.setValue("idemisor",idemisor);
                	archiBDIn.setValue("txnombre",nombrefi);
                	archiBDIn.setValue("filepath",txdirect+nomdirec); //carpeta 1 falta
                	archiBDIn.setValue("permgrup","S");
                	archiBDIn.setValue("tipofich",tipofich);
                	archiBDIn.setValue("idtamano","0");
                	archiBDIn.setValue("propieta",cduserid); // cambiar y coger idpropieta de la sesion
                	
                	UpdateCloudBD archivBD = new UpdateCloudBD(archiBDIn);
                	archivBD.setConnection(con);
                	archivBD.execInsert();
    				
    				/*INSETTA EN BBDD*/
    				if(rutaarchivo.trim().length() > 0){
    			// write the files to the disk
    					try {
	    					dest = null;
	    					File fileDest = new File(file.getAbsolutePath() + "/" + rutaarchivo);
	    					
	    					System.out.println("destino: "+ fileDest);
	    					if(entry.isDirectory())
	    					{
	    						fileDest.mkdirs();
	    					}
	    					else
	    					{
	    						if(fileDest.getParentFile().exists() == false)
	    							fileDest.getParentFile().mkdirs();
	    				
	    							fos = new FileOutputStream(fileDest);
	    							dest = new BufferedOutputStream(fos, BUFFER);
	    							
	    							while ((count = zis.read(data, 0, BUFFER)) != -1){
	    								dest.write(data, 0, count);
	    							}
	    						dest.flush();
	    					}
    					} finally {
    						try { if(dest != null) dest.close(); } catch(Exception e) {;}
    					}
    				}
    		}
    		zis.close();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	
    	
    }

}