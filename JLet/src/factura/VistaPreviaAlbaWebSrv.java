package factura;


import java.sql.Connection;
import java.sql.SQLException;

import utils.PropiedadesJLet;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import factura.bd.FactAlbaraBD;
import factura.bd.FactAlbaraBDIn;
import factura.bd.ListAreasneBD;
import factura.bd.ListAreasneBDIn;
import factura.bd.ListClientesBD;
import factura.bd.ListClientesBDIn;
import factura.bd.ListFormaPagoBD;
import factura.bd.ListFormaPagoBDIn;
import factura.bd.ListTipoFacBD;
import factura.bd.ListTipoFacBDIn;
import factura.bd.MaxFechaFacturaBD;
import factura.bd.MaxFechaFacturaBDIn;
import facturacion.bd.ListClienteWebBD;
import facturacion.bd.ListClienteWebBDIn;


public class VistaPreviaAlbaWebSrv extends Service {
	Connection izucon = null;
	
	//Varibales de entrada
			String idclient = "";
			String tpclient = "";
			String fechafac = "";
			String tipologo = "";
			String formpago = "";
			String condpago = "";
			String catefact = "";
			String informad = "";
	
	
    public VistaPreviaAlbaWebSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();						 			
			input.addVariable("idclient");
			input.addVariable("tpclient");
			input.addVariable("fechafac");
			input.addVariable("tipoPorc");
			input.addVariable("tipofact");
			input.addVariable("idemisor");
			input.addVariable("tipovist");
			input.addVariable("idalbara");
			input.addVariable("idtmpfra");
			input.addVariable("tipologo");
			input.addVariable("formpago");
			input.addVariable("condpago");
			input.addVariable("catefact");
			input.addVariable("aniofact");
			input.addVariable("factasoc");
			input.addVariable("informad");
		
			
			
				
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
			output.addVariable("txmensaj");
			output.addVariable("gridLine");
			output.addVariable("idclient");
			output.addVariable("tpclient");
			output.addVariable("gridClie");
			output.addVariable("fechafac");
			output.addVariable("tipoPorc");
			output.addVariable("tipoFact");
			output.addVariable("idemisor");
			output.addVariable("tipovist");
			output.addVariable("gridTpFa");
			output.addVariable("fecmaxfa");
			output.addVariable("cdfactur");
			output.addVariable("tipologo");
			output.addVariable("formpago");
			output.addVariable("condpago");
			output.addVariable("catefact");
			output.addVariable("idtmpfra");
			output.addVariable("idalbara");
			output.addVariable("informad");
			
			output.addVariable("factasoc");
			output.addVariable("gridArne");
			output.addVariable("gridFrPg");
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	
        
        //Varibales de salida
        String txmensaj = "";
        Grid gridResu = null;
        Grid gridClie = null;
        Grid gridTpFa = null;
        Grid gridFech = null;
        Grid gridArne = null;
        Grid gridFrPg = null;
        String tipoPorc = "";
        String tipofact = "";
        String idemisor = "";
        String tipovist = "";
        String idalbara = "";
        String factasoc = "";
        //Otras Variables
        
		String idtmpfra = "";
		String aniofact = "";
		
        try {

			   idclient = input.getStringValue("idclient");
			   fechafac = input.getStringValue("fechafac");
			   tipoPorc = input.getStringValue("tipoPorc");
			   tipofact = input.getStringValue("tipofact");
			   idemisor = input.getStringValue("idemisor");
			   tipovist = input.getStringValue("tipovist");
			   idalbara = input.getStringValue("idalbara");
			   tipologo = input.getStringValue("tipologo");
			   formpago = input.getStringValue("formpago");
			   condpago = input.getStringValue("condpago");
			   catefact = input.getStringValue("catefact");
			   aniofact = input.getStringValue("aniofact");
			   factasoc = input.getStringValue("factasoc"); 
			   informad = input.getStringValue("informad");
		
					/* lineas albaran*/
				   
				    FactAlbaraBDIn FactAlbaBDIn= new FactAlbaraBDIn();
				    FactAlbaBDIn.setValue("idalbara",idalbara);
				    FactAlbaBDIn.setValue("idemisor", idemisor);
				    FactAlbaBDIn.setValue("aniofact", aniofact);
				    FactAlbaBDIn.setValue("tipofact", tipofact);
				    FactAlbaBDIn.setValue("cdestado", "F");
				    FactAlbaBDIn.setValue("estaalba", "P");
				    FactAlbaraBD lineasAlBD = new FactAlbaraBD(FactAlbaBDIn);
				    lineasAlBD.setConnection(con);
				    gridResu = lineasAlBD.execSelect();
				    
				    //para sacar luego los datos del cliente en la Vista previa
				    String idclidet = gridResu.getStringCell(0, "idclient"); 
				    tpclient = gridResu.getStringCell(0, "tpclient"); 
						// saco el idtmpfra del idalbaran que halla introducido
					idtmpfra = gridResu.getStringCell(0, "idtmpfra");
					fechafac = gridResu.getStringCell(0, "fechafac");
		
					
					/*MAX FACTURA PARA ACTUALIZAR EL IDTMPFRA DE LAS facturas*/
						
				   
				   
				   gridClie= obtenerDatosClienteWeb(idemisor); /*Detalle cliente*/
					
					
				  /* ListClientesBDIn DetCliBDIn= new ListClientesBDIn();
				   DetCliBDIn.setValue("idclient",idclidet);
				   ListClientesBD DetCliBD = new ListClientesBD(DetCliBDIn);
				   DetCliBD.setConnection(con);
				   gridClie = DetCliBD.execSelect();*/
				   tipoPorc= "0";
				   
				   // tipo facturas
				   ListTipoFacBDIn ListTipoBDIn  = new ListTipoFacBDIn();  
				   ListTipoBDIn.setValue("cdemisor", idemisor);
				   ListTipoFacBD ListTipoBD = new ListTipoFacBD(ListTipoBDIn);
				   ListTipoBD.setConnection(con);
				   gridTpFa = ListTipoBD.execSelect();
		        	
		        	
				   /* Fecha ultima factura*/
				   MaxFechaFacturaBDIn FechaFaBDIn = new MaxFechaFacturaBDIn();
				   FechaFaBDIn.setValue("idemisor", idemisor);
				   MaxFechaFacturaBD FechaFacBD = new MaxFechaFacturaBD(FechaFaBDIn);
				   FechaFacBD.setConnection(con);
				   gridFech = FechaFacBD.execSelect();
		        	
				   /*Areas Negocio*/
				   ListAreasneBDIn areasneBDIn = new ListAreasneBDIn();
				   areasneBDIn.setValue("idemisor",idemisor);
				   ListAreasneBD areasneBD = new ListAreasneBD(areasneBDIn);
				   areasneBD.setConnection(con);
				   gridArne = areasneBD.execSelect();
		        	
		        	
				   /*Forma pago*/
				   ListFormaPagoBDIn formPagoBDIn = new ListFormaPagoBDIn();
				   formPagoBDIn.setValue("idemisor",idemisor);
				   ListFormaPagoBD formPagoBD = new ListFormaPagoBD(formPagoBDIn);
				   formPagoBD.setConnection(con);
				   gridFrPg = formPagoBD.execSelect();
			   
			   output.setValue("txmensaj", txmensaj);
	           output.setValue("idclient", idclient);
	           output.setValue("tpclient", tpclient);
	           output.setValue("fechafac", fechafac);
	           output.setValue("tipoPorc", tipoPorc);
	           output.setValue("tipoFact", tipofact);
	           output.setValue("idemisor", idemisor);
	           output.setValue("tipovist", tipovist);
	           output.setValue("cdfactur", idalbara);
	           output.setValue("tipologo", tipologo);
	           output.setValue("formpago", formpago);
	           output.setValue("condpago", condpago);
	           output.setValue("catefact", catefact);
	           output.setValue("idtmpfra", idtmpfra);
	           output.setValue("factasoc", factasoc);
	           output.setValue("informad", informad);
	           
	           
	           output.setGrid("gridLine", gridResu);
	           output.setGrid("gridClie", gridClie);
	           output.setGrid("gridTpFa", gridTpFa);
	           output.setGrid("gridArne", gridArne);
	           output.setGrid("gridFrPg", gridFrPg);
	           
	           output.setGrid("fecmaxfa",gridFech);
	         
        	
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
			clieWebBDIn.setValue("idcustom",idclient);
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
	