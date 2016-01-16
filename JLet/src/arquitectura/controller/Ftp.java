package arquitectura.controller;

import java.io.IOException;

import com.enterprisedt.net.ftp.FTPClient;
import com.enterprisedt.net.ftp.FTPConnectMode;
import com.enterprisedt.net.ftp.FTPException;
import com.enterprisedt.net.ftp.FTPTransferType;


public class Ftp {
	FTPClient ftp;
	FTPConnectMode MODE= FTPConnectMode.PASV;
	FTPTransferType TRANSFER= FTPTransferType.ASCII;
	
	final int CODE_TRANSFER_OK= 125;
	
	
	public String connect(String host, String user, String password){
		String resultado  = "OK";
		try {ftp = new FTPClient();
			ftp.setRemoteHost(host);
			ftp.connect();
			ftp.login(user,password);
//	       set up passive ASCII transfers
			ftp.setConnectMode(MODE);
	        ftp.setType(TRANSFER);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FTPException e) {
			e.printStackTrace();
		}
         //System.out.println("modos ok");
         if (ftp.connected()){
			System.out.println("Hemos conectado a la maquina : " + host);
		}
		else{
			System.out.println("No estamos conectados a la maquina : " + host); 
			resultado = "NOT CONNECTED";
		}
         return resultado;
	}
	
	//El directorio si es con puntos va con comilla simple incluida como p.e. "'PRVL.JSPAHI.L3BSPA00.SALIDA.NNBAA'"
	public String cd(String directory){
		String resultado = "OK";
		try {
			ftp.chdir(directory);
			ftp.pwd();
			String [] files = ftp.dir(".", true);
			String sDir="";
			for (int i = 0; i < files.length; i++)
	            sDir+= files[i];
			//System.out.println("El directorio en el que nos encontramos es : " + sDir);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FTPException e) {
			e.printStackTrace();
		}		
		return resultado;
	}
	
	public String download(String remote, String local){
		String resultado = "OK";
		if (ftp.connected()){
			String direccion=setDirectory(local,"./pending/");
			try {
				ftp.get(direccion,remote);
				System.out.println("Fichero Obtenido: "+direccion);
			} catch (IOException e) {
				e.printStackTrace();
				this.disconnect();
			} catch (FTPException e) {
				return "NO "+e.getMessage();
				
			}
			
			
		}
		else{
			resultado="Error: ftp is disconnected";
			System.out.println(resultado);
			return "NO "+resultado;
		}
		
		return resultado;
	}
	
	public String upload(String local, String remote){
		String resultado = "OK";
		if (ftp.connected()){
			String directory = setDirectory(local,"./pruebaFTP/");
			System.out.println("El directorioes = "+directory);
			System.out.println("local:"+local);
			System.out.println("remote:"+remote);
			try {
				ftp.put(directory,remote);
			} catch (IOException e) {
				e.printStackTrace();
				this.disconnect();
			} catch (FTPException e) {
				e.printStackTrace();
				this.disconnect();
			}
			
		}else{
			resultado="Error: ftp is disconnected";
			System.out.println(resultado);
		}
		return resultado;
	}
	public String disconnect(){
		String resultado = "OK";
		try {
			ftp.quit();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FTPException e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	public FTPConnectMode getMODE() {
		return MODE;
	}
	public void setMODE(FTPConnectMode mode) {
		MODE = mode;
	}
	public FTPTransferType getTRANSFER() {
		return TRANSFER;
	}
	public void setTRANSFER(FTPTransferType transfer) {
		TRANSFER = transfer;
	}
	
	/* setDirectory:
	 * Es una función que se utiliza (o se puede utilizar en diferentes lugares) y puede ser comoda tenerla aqui.
	 * d--> es el directorio que se le pasa como origen y en caso de no tener una ruta concreta sino ser tan solo un nombre de archivo
	 * 		se le incluye en el directorio contenido en 'defaultDir'. Si tiene directorio se queda como estaba.
	 * Devolvemos el archivo con su ruta. 	 
	 */
	private String setDirectory(String d, String defaultDir){
		String directorio="";
		if (d.lastIndexOf("/")>0){
			//Este if y el siguiente son un poco absurdos ya que troceo para luego juntarlo... son mas graficos que otra cosa
			directorio = d.substring(0, d.lastIndexOf("/"));
			d = d.substring(d.lastIndexOf("/"), d.length());
		}else{
			if (d.lastIndexOf("\\")>0){
				directorio = d.substring(0, d.lastIndexOf("\\"));
				d = d.substring(d.lastIndexOf("\\"), d.length());
			}
			else{//No encuentra barras por tanto lo pone en el directorio por defecto
				directorio=defaultDir;//"./download/";
			}
			
		}
		 return directorio+d;
	}
}
