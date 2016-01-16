package factura;


import java.sql.Connection;
import java.sql.SQLException;

import utils.PropiedadesJLet;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import factura.bd.ListAlbaranesWebBD;
import factura.bd.ListAlbaranesWebBDIn;
import factura.bd.ListAnioFactBD;
import factura.bd.ListAnioFactBDIn;
import facturacion.bd.ListClienteWebBD;
import facturacion.bd.ListClienteWebBDIn;


public class ListAlbaranesWebEmisorSrv extends Service {
	Connection izucon = null;

    public ListAlbaranesWebEmisorSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();

			input.addVariable("idemisor");
			input.addVariable("aniofact");		
			input.addVariable("isalbara");
			input.addVariable("idcliere");
			input.addVariable("fhdesdex");
			input.addVariable("fhhastax");
			input.addVariable("predesde");
			input.addVariable("prehasta");
			input.addVariable("tpclient");
			input.addVariable("logoemis");
						
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
			output.addVariable("aniofact");	
			output.addVariable("logoemis");
			output.addVariable("grfactur");		
			output.addVariable("isalbara");	
			output.addVariable("gridClie");
			output.addVariable("fhdesdex");
			output.addVariable("fhhastax");
			output.addVariable("idcliere");
			output.addVariable("predesde");
			output.addVariable("prehasta");
			output.addVariable("tpclient");
			output.addVariable("gdAniosx");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor = null;
    	String tpclient = null;
    	String isalbara = null;
    	String aniofact = null;
    	String idcliere = null;
    	String fhdesdexMy = null;
    	String fhhastaMy = null;
    	String predesde = null;
    	String prehasta = null;
    	String logoemis = null;
        
        //Varibales de salida
        Grid gridAlba = null;
        Grid gridClie = null;
        String fhdesdex = null;
    	String fhhastax = null;
        
        //Otras Variables
     
        
        try{        	
        	
        	idemisor = input.getStringValue("idemisor");
        	aniofact = input.getStringValue("aniofact");
        	logoemis = input.getStringValue("logoemis");
        	isalbara = input.getStringValue("isalbara");
        	idcliere = input.getStringValue("idcliere");
        	fhdesdex = input.getStringValue("fhdesdex");
        	fhhastax = input.getStringValue("fhhastax");
        	predesde = input.getStringValue("predesde");
        	prehasta = input.getStringValue("prehasta");
        	tpclient = input.getStringValue("tpclient");
        	
        	if((fhdesdex  != null) && (!fhdesdex.equals(""))){
        		fhdesdexMy = fechaMySQL(fhdesdex);
        	}
        	
        	if((fhhastax != null) && (!fhhastax.equals(""))){
        		fhhastaMy = fechaMySQL(fhhastax);
        	}
        	
        	
        	// listado albaranes 
        	ListAlbaranesWebBDIn ListAlbaBDIn  = new ListAlbaranesWebBDIn();  
        	ListAlbaBDIn.setValue("idemisor", idemisor);
        	//ListAlbaBDIn.setValue("aniofact", aniofact);
        	ListAlbaBDIn.setValue("idclient", idcliere);
        	ListAlbaBDIn.setValue("fhdesdex", fhdesdexMy);
        	ListAlbaBDIn.setValue("fhhastax", fhhastaMy);
        	ListAlbaBDIn.setValue("predesde", predesde);
        	ListAlbaBDIn.setValue("prehasta", prehasta);
        	ListAlbaBDIn.setValue("isalbara", "W");
        	
        	ListAlbaranesWebBD ListTipoBD = new ListAlbaranesWebBD(ListAlbaBDIn);
        	ListTipoBD.setConnection(con);
        	gridAlba = ListTipoBD.execSelect();
        	
        	/*Clientes con albaranes del emisor*/
          
        	gridClie= obtenerDatosClienteWeb(idemisor); 
        	
        	
        	/*anios factura*/
         	ListAnioFactBDIn ListAnioFaBDIn  = new ListAnioFactBDIn();  
         	ListAnioFaBDIn.setValue("idemisor", idemisor);
        	ListAnioFactBD ListAnioFaBD = new ListAnioFactBD(ListAnioFaBDIn);
        	ListAnioFaBD.setConnection(con);
        	Grid gdAniosx = ListAnioFaBD.execSelect();
        	
        	
        	//INGRESO LOS VALORES DEL SALIDA
        	
        	output.setValue("idemisor", idemisor);
        	output.setValue("aniofact", aniofact);
        	output.setValue("logoemis", logoemis);
        	output.setValue("isalbara", isalbara);
        	output.setGrid("grfactur", gridAlba);
        	output.setGrid("gridClie", gridClie);
        	output.setValue("gdAniosx", gdAniosx);
        	output.setValue("fhdesdex", fhdesdex);
        	output.setValue("fhhastax", fhhastax);
        	output.setValue("predesde", predesde);
        	output.setValue("prehasta", prehasta);
        	output.setValue("idcliere", idcliere);
        	
        	
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
 public Grid obtenerDatosClienteWeb(String idemisor) {
    	
    	Grid gdCliWeb  = null;
    	String idshopx = "";
    	
    	if(idemisor.equals("1")){
    		idshopx = "1";
    	}else if(idemisor.equals("3")){
    		idshopx = "2";
    	}else if(idemisor.equals("8")){
    		idshopx = "3";
    	}
    	
    	try {
    		createIzuConnection();
    		ListClienteWebBDIn clieWebBDIn= new ListClienteWebBDIn();
    		//JEJ de momento para una sola tienda
			clieWebBDIn.setValue("idshopxx",idshopx);
	    	ListClienteWebBD clieWebBD = new ListClienteWebBD(clieWebBDIn);
	    	clieWebBD.setConnection(izucon);
			gdCliWeb = clieWebBD.execSelect();
			closeIzuConnection();
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return gdCliWeb;
    }
    
public void createIzuConnection(){
    	
   		String usuariox = "";
		String password = "";
		String bdschema = "";
		String bdhostxx = "";

		usuariox = PropiedadesJLet.getInstance().getProperty("izum.bd.usuariox");
		password = PropiedadesJLet.getInstance().getProperty("izum.bd.password");
		bdschema = PropiedadesJLet.getInstance().getProperty("izum.bd.bdschema");
		bdhostxx = PropiedadesJLet.getInstance().getProperty("izum.bd.bdhostxx");
		
		usuariox = "izumba_jlet";
		password = "^0xDh15f";
		bdschema = "izumba";
		bdhostxx = "85.214.140.64";
		
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser(usuariox);
		dataSource.setPassword(password);
		dataSource.setDatabaseName(bdschema);
		dataSource.setServerName(bdhostxx);
		dataSource.setPort(3306);
	
		try {
			izucon = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		System.out.println("[OPEN] - Abro Conexion Izumba SHOP");
		
	}
   	
   	public void closeIzuConnection(){
	   		
		try {
			izucon.close();
		} catch (SQLException e) {
			e.printStackTrace();
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
       
}
