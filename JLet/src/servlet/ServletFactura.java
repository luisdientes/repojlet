package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import utils.PropiedadesJLet;
import arquitectura.objects.ObjectIO;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import factura.GeneraFacturaSrv;

public class ServletFactura extends HttpServlet {

	Connection con = null;
	
	String emisclie = null;
	String receclie = null;
	String aniofact = null;
	String tipofact = null;
	String mcagrupa = null;
	String fhfactur = null;
	String tipologo = null;
	String formpago = null;
	String filecrea = null;
	String txmensaj = null;
	String tpclient = null;
	String idorderx = null;
	String informda = null;
	
	
	private static final long serialVersionUID = 1L;
	

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
    	doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{    	
		
    	
    	System.out.println("LLEGA A SERVLET      -------------------->>>>> ");
    	emisclie = request.getParameter("emisclie");
    	receclie = request.getParameter("receclie");
    	aniofact = request.getParameter("aniofact");
    	tipofact = request.getParameter("tipofact");
    	mcagrupa = request.getParameter("mcagrupa");
    	fhfactur = request.getParameter("fhfactur");
    	formpago = request.getParameter("formpago");
    	tipologo = request.getParameter("tipologo");
    	tpclient = request.getParameter("tpclient");
    	idorderx = request.getParameter("idorderx");
    	informda = request.getParameter("informda");
    	
    	fhfactur = fechaNormal(fhfactur);
    	
    	System.out.println("IDORDERXX -------------------->>>>> "+fhfactur);
    	
    	String urldestin = "";
    	
    	if(emisclie.equals("1")){
    		
    		urldestin = "http://www.izumbashop.com/rd/index.php?controller=history";
    	}else if(emisclie.equals("8")){
    		urldestin = "http://www.izumbashop.com/es/index.php?controller=history";
    	}else if(emisclie.equals("3")){
    		urldestin = "http://www.izumbashop.com/ch/index.php?controller=history";
    	}
    	
    	
    	
    	
    	
		try {
			
			crearConexion();
		    generaFactur();
		    response.sendRedirect(urldestin); 
		    
		} catch (Exception e){
			System.err.println(" - ERROR - Al intentar generar factura desde tienda: "+ e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		 
    }
    
    public void generaFactur(){
   
    	try { 
    		GeneraFacturaSrv genfactuSrv = new GeneraFacturaSrv();
    		ObjectIO input  = genfactuSrv.instanceOfInput();
    		ObjectIO output = genfactuSrv.instanceOfOutput();
    		
    		input.setValue("idemisor", emisclie);
    		input.setValue("idclient", receclie);
    		input.setValue("aniofact", aniofact);
    		input.setValue("tipofact", tipofact);
    		input.setValue("mcagrupa", mcagrupa);
    		input.setValue("fhfactur", fhfactur);
    		input.setValue("tipologo", tipologo);
    		input.setValue("formpago", formpago);
    		input.setValue("idorderx", idorderx);
    		input.setValue("tpclient", tpclient);
    		input.setValue("informda", informda);
    		
    		genfactuSrv.setConnection(con);
    		genfactuSrv.process(input, output);
    		filecrea = output.getStringValue("filecrea");
			txmensaj = output.getStringValue("txmensaj");
    		
    	} catch(Exception e) {
    		System.out.println("Error al crear factura desde tienda izumba");
    	}
    }
 

    public void crearConexion(){
		
		String usuariox = "";
		String password = "";
		String bdschema = "";
		String bdhostxx = "";
		
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/des_jletproj");
			con = ds.getConnection();
			System.out.println(this.getClass().getName() +"Conexion realizada desde el Pool Luissss---------------------->>> OK");
		} catch (Exception e){
			System.err.println("No se ha podido obtener el Pool "+ e.getMessage());
			usuariox = PropiedadesJLet.getInstance().getProperty("jlet.bd.usuariox");
			password = PropiedadesJLet.getInstance().getProperty("jlet.bd.password");
			bdschema = PropiedadesJLet.getInstance().getProperty("jlet.bd.bdschema");
			bdhostxx = PropiedadesJLet.getInstance().getProperty("jlet.bd.bdhostxx");
			
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setUser(usuariox);
			dataSource.setPassword(password);
			dataSource.setDatabaseName(bdschema);
			dataSource.setServerName(bdhostxx);
			
			try {
				con = dataSource.getConnection();
				System.out.println(this.getClass().getName() +"Conexion realizada desde el Properties ");
			} catch (SQLException e2) {
				System.err.println("ERROR de conexion: "+ e2.getMessage());
			}
			
		}
		
	}
    
    public String fechaNormal(String fhfechax){
        
  		String fhSpanis = "";
  		try {
  			String [] arrFecha = fhfechax.split("-");
  			fhSpanis = arrFecha[2]+"/"+ arrFecha[1]+"/"+arrFecha[0];
  		} catch (Exception e) {
  			return "00/00/0000";
  		}
  		return fhSpanis;
      }
	
}


