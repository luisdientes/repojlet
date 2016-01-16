package contabilidad;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import cloud.bd.ListArchivosBD;
import cloud.bd.ListArchivosBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;


public class ListDirectoriosSrv extends Service {
	
	 
	  String filename = "";
	  String txcuenta = "";
	  String numeroid = "";
	  String idemisor = "";

	  Grid grOrdena  = null;
	  
	public ListDirectoriosSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("idemisor");
			input.addVariable("idcuenta");
			input.addVariable("idapunte");
			input.addVariable("fhdesdex");
			input.addVariable("fhhastax");
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
			output.addVariable("cddivisa");
			output.addVariable("fhdesdex");
			output.addVariable("fhhastax");
			output.addVariable("idapunte");
			output.addVariable("ultsaldo");
			output.addVariable("filename");
			output.addVariable("grOrdena");
			output.addVariable("gdApuntes");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada

        
        //Otras Variables
        	
    	try {
    		
    		idemisor = input.getStringValue("idemisor");
    		
    		//saco los padres principales
    		ListArchivosBDIn listDirecBDin = new ListArchivosBDIn();
    		listDirecBDin.setValue("idemisor", idemisor);
    		listDirecBDin.setValue("filepath", "/");
    		ListArchivosBD listDirecBD = new ListArchivosBD(listDirecBDin);
    		listDirecBD .setConnection(con);
    		Grid gdDirecto = listDirecBD.execSelect();
    		
    		grOrdena = new Grid();
    		grOrdena.addColumn("idnivelx");
    		grOrdena.addColumn("txnombre");
    		grOrdena.addColumn("tipofich");
    		grOrdena.addColumn("idinodox");
    		grOrdena.addColumn("cdpadrex");
    		grOrdena.addColumn("filepath");
    		
    		for(int i = 0; i < gdDirecto.rowCount(); i++){
        		
    			int nivelarb = 0;
    			
    			String filepath = gdDirecto.getStringCell(i, "filepath");
				String idinodox = gdDirecto.getStringCell(i, "idinodox");
				String txnombre = gdDirecto.getStringCell(i, "txnombre");
				String tipofich = gdDirecto.getStringCell(i, "tipofich");
				
				ArrayList<String> rowArbol = new ArrayList<String>();
				rowArbol.add(String.valueOf(nivelarb));
				rowArbol.add(txnombre);
				rowArbol.add(tipofich);
				rowArbol.add(idinodox);
				rowArbol.add("/");
				rowArbol.add(filepath);
				grOrdena.addRow(rowArbol);
				
				if (tipofich.equals("D")) {
					listaDirectorio(filepath+txnombre+"/",nivelarb+1);
				}
        		
    		}
    		
   
	        
        	output.setValue("idemisor", idemisor);
        	output.setValue("filename", filename);
        	output.setValue("grOrdena", grOrdena);
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
    

    public void listaDirectorio(String nombredi, int nivelarb) {
	 
		 try {
			 
		 	ListArchivosBDIn listDirecBDin = new ListArchivosBDIn();
			listDirecBDin.setValue("idemisor", idemisor);
			listDirecBDin.setValue("filepath", nombredi);
			ListArchivosBD listDirecBD = new ListArchivosBD(listDirecBDin);
			listDirecBD .setConnection(con);
			Grid gdDirecto = listDirecBD.execSelect();
			
			for (int i = 0; i < gdDirecto.rowCount(); i++) {
				
				String filepath = gdDirecto.getStringCell(i, "filepath");
				String idinodox = gdDirecto.getStringCell(i, "idinodox");
				String txnombre = gdDirecto.getStringCell(i, "txnombre");
				String tipofich = gdDirecto.getStringCell(i, "tipofich");
				
				ArrayList<String> rowArbol = new ArrayList<String>();
				rowArbol.add(String.valueOf(nivelarb));
				rowArbol.add(txnombre);
				rowArbol.add(tipofich);
				rowArbol.add(idinodox);
				rowArbol.add(nombredi);
				rowArbol.add(filepath);
				grOrdena.addRow(rowArbol);
				
				if (tipofich.equals("D")) {
					listaDirectorio(filepath+txnombre+"/",nivelarb+1);
				}
				
			}
			
			
			
		 } catch (Exception e) {
			 
		 }
		 
    }
    
    
 public String fechaMySQL(String fhfechax){
        
  		String fhMySql = "";
  		try {
  			String [] arrFecha = fhfechax.split("/");
  			fhMySql = arrFecha[2]+"-"+ arrFecha[1]+"-"+arrFecha[0];
  		} catch (Exception e) {
  			return "0000-00-00";
  		}
  		return fhMySql;
      }
 
 public String FechaMesAnterior(){
	 
	  Calendar c1 = GregorianCalendar.getInstance();
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMMM/yyyy hh:mm:ss");
      sdf = new SimpleDateFormat("dd/MM/yyyy");
      c1.add(Calendar.MONTH, -1);
      
      return sdf.format(c1.getTime());
 }
    
    
    
}
	