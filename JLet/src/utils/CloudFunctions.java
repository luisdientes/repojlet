package utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import arquitectura.objects.Grid;

public class CloudFunctions {

	static Connection con = null;
	//private static ZipOutputStream zos;
	private static ZipOutputStream zos;
	public static void main(String[] args) {
		
		String pathfile ="E:\\DATOS\\tmp\\cloud\\emisor_1\\ppapappapa"; //args[0];
		//String altafile = "AltaMasiva.xls";
		
		 String listfile = "ALBARANX3 - copia.png,ALBARANX3.png";
		//String listfile = "Nueva carpeta,AltaMasiva.xls,AltaMasiva_ver1.xls";
	//	String listfile = "Nueva carpeta";
		
		CloudFunctions cloud = new CloudFunctions();
		//cloud.listarDirectorios(pathfile);
		//cloud.altaFichero(pathfile,altafile);
		//cloud.altaFichero(pathfile,altafile);
		//cloud.comprimirFicheros(pathfile,listfile,"pruebazip");
		
	}
	
	public static Grid listarDirectorios(String pathfile){
		
		Grid lsDirect = null;
		
		File dir = new File(pathfile);
		String[] ficheros = dir.list();
		
	    if (ficheros == null) {
	        System.out.println("No hay ficheros en el directorio especificado");
	    } else {
	        for (int x=0; x < ficheros.length; x++){
	        	String tipofich = "";
	        	String absoPath = pathfile +"/"+ ficheros[x];
	        	
	        	File tmpfilex = new File(absoPath);
	        	if (tmpfilex.isDirectory()){
	        		tipofich = "(D)";
	        	} else {
	        		tipofich = "   ";
	        	}
	        	
	        	System.out.println(tipofich +"  "+ ficheros[x]);
	        }
	    }
	    
		return lsDirect;
		
	}
	
	public static Grid altaFichero(String pathfile, String namefile){
		
		Grid lsDirect = null;
	    String absoPath = pathfile +"/"+ namefile;

	    File tmpfilex = new File(absoPath);
	    
	    if (tmpfilex.isFile() && !tmpfilex.isDirectory()){
	    	System.out.println("EXISTE: "+ absoPath +" (hay que versionar)");
	    	versionarFichero(pathfile,namefile);
	    }
	        	
	    
		return lsDirect;
		
	}
	
	public static int versionarFichero(String pathfile, String namefile){
		
		int numVersi = 0;
		
		String absoPath = pathfile +"/"+ namefile;
		Grid lsDirect = null;
		String cabecera = "";
		String extensio = "";
		
		String[] partFich = namefile.split("\\.");
		
		cabecera = partFich[partFich.length-2];
		extensio = partFich[partFich.length-1];
		
		// ATENCIÓN: Probar que pasa si recibo un fichero sin extension
		
	    for (int i=99; i > 0; i--){
	    	
	    	String tmpfilex = pathfile + cabecera +"_ver"+ i +"."+ extensio;
	    	File filetemp = new File(tmpfilex);
	    	String existe = " ";
	    	
	    	if (filetemp.exists()){
	    		existe = "Si";
	    		int oldversi = i+1;
	    		File newfilev = new File(pathfile + cabecera +"_ver"+ oldversi +"."+ extensio);
	    		filetemp.renameTo(newfilev);
	    		numVersi++;
	    	}
	    	
	    	System.out.println(tmpfilex +" "+ existe);
	    	
	    }
	    
	    String tmpfilex = pathfile + cabecera +"."+ extensio;
    	File filetemp = new File(tmpfilex);
    	
	    if (filetemp.exists()){
    		File newfilev = new File(pathfile + cabecera +"_ver1."+ extensio);
	    	filetemp.renameTo(newfilev);
    	}
	    
	    //SUBIR FICHERO!!!
	    
	    
		return numVersi;
		
	}
	
	
	

	
	public static int comprimirFicheros(String directo,String pathfile, String listfile, String fnamezip) throws IOException{
		
	/*	int nfichcom = 0;
		ZipOutputStream zos = null;
		
		File dir = new File(pathfile);
		
	    try {
	    	
			zos = new ZipOutputStream(new FileOutputStream(directo +"/"+ fnamezip + ".zip"));
		
		    String[] lsfiletz = listfile.split(",");
		    
		    if (lsfiletz.length == 1){
		    	 File unicfile = new File(pathfile + lsfiletz[0]);
		    	 //Si lo seleccionado es un direcctorio listo los ficheros de dentro del directorio y fijo el nombre del zip al nombre de la carpeta sin espacios
		    	 if (unicfile.isDirectory()){
		    		 String[] ficheros = unicfile.list();
		    		 pathfile += lsfiletz[0];
		    		 
		    		 if (ficheros == null) {
		    			 System.out.println("No hay ficheros en el directorio especificado");
		    		 } else {
		    			 listfile = "";		    			 
		    			 for (int x=0; x < ficheros.length; x++){
		    				 String absoPath = pathfile +"/"+ ficheros[x];
	    		        	
		    				 File tmpfilex = new File(absoPath);
		    				 if (!tmpfilex.isDirectory()){
		    					 if (!listfile.equals("")){
		    						 listfile+= ",";
		    					 }
		    					 listfile+= ficheros[x];
		    				 }
	    		        }
	    		    }
		    		lsfiletz = listfile.split(",");
		    	 }
		    } 
		    
		    
		    for (int i = 0; i < lsfiletz.length; i++){
		    
		    	String filename = lsfiletz[i];
		    	String absoPath = pathfile +"/"+ filename;
			    File file = new File(absoPath);
				
				byte[] buf = new byte[1024];
				int len;     
				
				ZipEntry zipEntry 		= new ZipEntry(filename);
				FileInputStream fin 	= new FileInputStream(file);
				BufferedInputStream in = new BufferedInputStream(fin);
				zos.putNextEntry(zipEntry);
	                  
				while ((len = in.read(buf)) >= 0) {
					zos.write(buf, 0, len);
				}
				
				in.close();
				 
				zos.closeEntry();
				nfichcom++;
				
			 }*/
		    
		    
		   
		    
		    
		    
		    
		 
		  
		    
	//	directo = directo.replaceAll("/","\\");
		    /*forrrrr*/
		    
		zos = new ZipOutputStream(new FileOutputStream(directo +"/"+ fnamezip + ".zip"));
		    
		recurseFiles(new File("Q:\\pup\\")); //Replace this with a suitable directory  
		
		 zos.close();
		    
		/*} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				zos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
	    
		return 1;//nfichcom;
		
	}
	
	
	 public static void recurseFiles(File path) throws IOException {
		 
		 if (path.isDirectory()) {
			 String[] fileNames = path.list();
	         if (fileNames != null) {
	            //Recursively add each array entry to make sure that we get
	           //subdirectories as well as normal files in the directory.
	            for (int i=0; i<fileNames.length; i++){ 
	            	recurseFiles(new File(path, fileNames[i]));
	            }
	         }
	      }
	      //Otherwise, a file so add it as an entry to the Zip file.      
	else {
		
			byte[] buf = new byte[1024];
	         int len;
	         //Create a new Zip entry with the file's name.         
	         ZipEntry zipEntry = new ZipEntry(path.toString());
	         //Create a buffered input stream out of the file         


	         //we're trying to add into the Zip archive.         


	         FileInputStream fin = new FileInputStream(path);
	         BufferedInputStream in = new BufferedInputStream(fin);
	         zos.putNextEntry(zipEntry);
	         //Read bytes from the file and write into the Zip archive.         


	         while ((len = in.read(buf)) >= 0) {
	            zos.write(buf, 0, len);
	         }
	         //Close the input stream.         
	         in.close();
	         //Close this entry in the Zip stream.         
	        zos.closeEntry();
	      }
	   }
	
	public static int borrarDirectorio(String pathfile){
		
		
		return 0;
		
	}
	
	public int moverFichero(String fichorig, String fichdest){
		
		int numMovim = 0;
		
		return numMovim;
		
	}
	
    
}