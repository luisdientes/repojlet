package cloud;
import java.io.*;
import java.util.zip.*;

public class UnzipArchivo {
	

	final static int BUFFER = 2048;
	public static void main (String []args)
	{
	try {
		File dirDestino = new File("prueba");
		BufferedOutputStream dest = null;
		FileInputStream fis = new FileInputStream("C:\\prueba.zip");
		ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
		FileOutputStream fos = null;
		ZipEntry entry;
		int count;
		int index = 0;
		byte data[] = new byte[BUFFER];
		String rutaarchivo = null;
		while((entry = zis.getNextEntry()) != null) {
				System.out.println("Extracting:"  +entry);
				rutaarchivo = entry.getName();
				// tenemos que quitar el primer directorio
				index = rutaarchivo.indexOf("/");
				rutaarchivo = rutaarchivo.substring(index+1);
			
				if(rutaarchivo.trim().length() > 0){
			// write the files to the disk
					try {
					dest = null;
					File fileDest = new File(dirDestino.getAbsolutePath() + "/" + rutaarchivo);
					
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
					while ((count = zis.read(data, 0, BUFFER)) != -1)
					{
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