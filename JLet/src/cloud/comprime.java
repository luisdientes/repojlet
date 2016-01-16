package cloud;

import java.io.*;
import java.util.zip.*;

/**
 * Compresses a file or directory into a Zip archive. Users of the
 * class supply the name of the file or directory as an argument.
 */
public class comprime {

   private static ZipOutputStream zos;

   public static void main(String[] args) {
      //User must specify a directory to compress      


/*

if (args.length < 1) {
         System.out.println("Usage: java SimpleZip directoryName");
         System.exit(0);
      }*/
      //Get the name of the file or directory to compress.      




String fileName = "E:\\DATOS\\tmp\\cloud\\emisor_2\\luis";
      //Use the makeZip method to create a Zip archive.


try {
         makeZip(fileName);
      }
      //Simply print out any errors we encounter.


catch (Exception e) {
         System.err.println(e);
      }
   }

   /**
    * Creates a Zip archive. If the name of the file passed in is a
    * directory, the directory's contents will be made into a Zip file.
    */
   public static void makeZip(String fileName)
         throws IOException, FileNotFoundException
   {
      File file = new File(fileName);
      zos = new ZipOutputStream(new FileOutputStream(file + ".zip"));
      //Call recursion.


recurseFiles(file);
      //We are done adding entries to the zip archive,


//so close the Zip output stream.


zos.close();
   }

   /**
    * Recurses down a directory and its subdirectories to look for
    * files to add to the Zip. If the current file being looked at
    * is not a directory, the method adds it to the Zip file.
    */
   private static void recurseFiles(File file)
      throws IOException, FileNotFoundException
   {
      if (file.isDirectory()) {
         //Create an array with all of the files and subdirectories         
 //of the current directory.
String[] fileNames = file.list();
         if (fileNames != null) {
            //Recursively add each array entry to make sure that we get
           //subdirectories as well as normal files in the directory.
            for (int i=0; i<fileNames.length; i++){ 
            	recurseFiles(new File(file, fileNames[i]));
            }
         }
      }
      //Otherwise, a file so add it as an entry to the Zip file.      
else {
         byte[] buf = new byte[1024];
         int len;
         //Create a new Zip entry with the file's name.         


ZipEntry zipEntry = new ZipEntry(file.toString());
         //Create a buffered input stream out of the file         


//we're trying to add into the Zip archive.         


FileInputStream fin = new FileInputStream(file);
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
}
