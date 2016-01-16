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



public class DelDirectorioSrv extends Service {
	
	
	String idemisor = null;
	String idinodox = null;
	String tipofich = null;
	String rutaabso = null;
	String idtamano = null;
	String txnombre = null;
	String txmensaj = null;
	String tipoperm = "";
	String propieta = "";
	String cduserid = null;
	Grid   grArchiv = null;

    public DelDirectorioSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();						 			
			
			input.addVariable("idemisor");
			input.addVariable("filepath");
			input.addVariable("tipofich");
			input.addVariable("txnombre");
			input.addVariable("idinodox");
			
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
			output.addVariable("txmensaj");
			output.addVariable("rutaabso");
			output.addVariable("creadire");
			output.addVariable("cduserid");
			output.addVariable("propieta");
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
    
    	String listDire[] = null;
    	String listfile[] = null;
    	String folderin = "";
    	String folderde = "";
    	String idtamano = "";
    	long milisegu = 0;
    	int cuantos = 0;
        
        //Varibales de salida
        int idfactur = 0;
        Grid gridClie = null;
        Grid gridPhon = null;
        
        
        
        
        
        try{
        	
        	idemisor = input.getStringValue("idemisor");
        	filepath = input.getStringValue("filepath");
        	tipofich = input.getStringValue("tipofich");
        	txnombre = input.getStringValue("txnombre");
        	idinodox = input.getStringValue("idinodox");
        	listfile =txnombre.split(",");
         	
        	try{
        		cduserid = (String) sesion.getAttribute("idusuari");
        	}catch(Exception ex){
        		System.out.println("Error al recuperar sesion de usuario");
        		cduserid = "-1";
        	}
        	
        	
        }catch(Exception ex){
        	System.out.println("Error al listar archivos o directorios "+ex.getMessage());
        }
     
        try{   

        	if(tipofich.equals("D")){
        		
        		for(int i = 0 ; i < listfile.length;i++){
        			
        			
        	       	
        	    	/* compruebo que el directorio no tiene nada*/
        	        	
        	        	ListArchivosBDIn archivoBDIn = new ListArchivosBDIn();
        	        	archivoBDIn.setValue("idemisor",idemisor);
        	        	archivoBDIn.setValue("filepath",filepath+listfile[i]+"/");
        	        	
        	        	ListArchivosBD archivoBD = new ListArchivosBD(archivoBDIn);
        	        	archivoBD.setConnection(con);
        	        	grArchiv = archivoBD.execSelect();
        	        	
        	        	cuantos = grArchiv.rowCount();
        	        	
        	        	/*compruebo que el directorio no tiene nada*/
        		
		        		folderin = PropiedadesJLet.getInstance().getProperty("path.gen.cloud") + "emisor_"+ idemisor + "/"+filepath+"/"+listfile[i]+"/"; 
		        		File infolder = new File(folderin);
		 	        
		        		if (infolder.isDirectory()){
		        			
		        			listDire = infolder.list();
		        			
		        			//if(listDire.length < 1){ // si el directorio esta vacio
		        				//infolder.delete();
		        			if(cuantos ==0){ //MARCO EN bbdd como no activo pero no elimino nada.
			        			UpdateCloudBDIn archiBDIn = new UpdateCloudBDIn();
			                	archiBDIn.setValue("idemisor",idemisor);
			                	archiBDIn.setValue("filepath",filepath);
			                	archiBDIn.setValue("txnombre",listfile[i]);
			                	archiBDIn.setValue("tipofich",tipofich);
			                	archiBDIn.setValue("propieta",cduserid); // cambiar y coger idpropieta de la sesion
			                	
			                	UpdateCloudBD archivBD = new UpdateCloudBD(archiBDIn);
			                	archivBD.setConnection(con);
			                	archivBD.execDelete();
			                	
			                	txmensaj ="<div style='color:#266A2E'>Directorio eliminado";
		        			}else{
		        				txmensaj ="<div style='color:#8B0000'>Directorio no eliminado, contiene ficheros</div>";
		        			}
		        		}
        	  }		
        	}else{
        		for(int i = 0 ; i < listfile.length;i++){
        			
        			milisegu = System.currentTimeMillis();            		
	        		folderin = PropiedadesJLet.getInstance().getProperty("path.gen.cloud") + "emisor_"+ idemisor + "/"+filepath+"/"+listfile[i]; 
	        		
	        		folderde = PropiedadesJLet.getInstance().getProperty("path.gen.cloud") + "emisor_"+ idemisor + "/papelera/"+milisegu+"_"+listfile[i]; 
	        		File infolder = new File(folderin);
	        		File oufolder = new File(folderde);
	 	        
	        		if (infolder.isFile()){
	        			
	        		//	listDire = infolder.list();
	        		 // si el directorio esta vacio
	        			
	        			boolean borrado =infolder.renameTo(oufolder);
	        			//infolder.delete();
	        			
		        			UpdateCloudBDIn archiBDIn = new UpdateCloudBDIn();
		                	archiBDIn.setValue("idemisor",idemisor);
		                	archiBDIn.setValue("filepath",filepath);
		                	archiBDIn.setValue("txnombre",listfile[i]);
		                	archiBDIn.setValue("tipofich",tipofich);
		                	archiBDIn.setValue("propieta",cduserid); // cambiar y coger idpropieta de la sesion
		                	
		                	UpdateCloudBD archivBD = new UpdateCloudBD(archiBDIn);
		                	archivBD.setConnection(con);
		                	archivBD.execDelete();
		                	
		                	if(borrado){
		                		txmensaj ="<div style='color:#266A2E'>Fichero eliminado</div>";
		                	}
	        			}
	        		}
    	  }		
        	
        	
        	ListArchivosBDIn archivoBDIn = new ListArchivosBDIn();
        	archivoBDIn.setValue("idemisor",idemisor);
        	archivoBDIn.setValue("filepath",filepath);
        	
        	ListArchivosBD archivoBD = new ListArchivosBD(archivoBDIn);
        	archivoBD.setConnection(con);
        	grArchiv = archivoBD.execSelect();
        	
        	/* permisosss*/
        	String dirpermi = "";
 	        String nomdirec = "";
        	Grid gdPermisos = null;
        	
        	int  indexfile = filepath.substring(0,filepath.length()-1).lastIndexOf('/');
 	        
 	         if(filepath != null && !filepath.equals("/")){ //subiendo directorio
 	        	 nomdirec = filepath.substring(indexfile+1);
 	        	 nomdirec = nomdirec.substring(0,nomdirec.length()-1);
 	  	         dirpermi = filepath.substring(0,indexfile+1);
 	  	         gdPermisos =  tipoPermisos(dirpermi,nomdirec);
	        	
 	  	         propieta  = gdPermisos.getStringCell(0,"propieta");
 	  	         tipoperm  = gdPermisos.getStringCell(0,"permgrup"); 
 	        }else{
 	        	 tipoperm ="S"; //si es el directorio raiz	
 	        }
        	
        	/*permisosss*/
 	       

        	//RECUPERO LOS VALORES DEL INPUT
        	output.setValue("idemisor", idemisor);
        	output.setValue("rutaabso", folderin);
        	output.setValue("cduserid", cduserid);
        	output.setValue("propieta", propieta);
        	output.setValue("tipoperm", tipoperm);
        	output.setValue("txmensaj", txmensaj);
        	output.setValue("grArchiv", grArchiv);
        	output.setValue("folderin", filepath);
        	
        	
        	

        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
public Grid tipoPermisos(String filepath, String txnombre){
    	
    	Grid gdPermiso = null;
    	
    	try{
	    	ListArchivosBDIn archiBDIn = new ListArchivosBDIn();
	    	archiBDIn.setValue("idemisor",idemisor);
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