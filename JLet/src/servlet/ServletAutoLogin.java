package servlet;

import gestion.administracion.bd.ListPantallasBD;
import gestion.administracion.bd.ListPantallasBDIn;
import gestion.administracion.bd.ListPermisosBD;
import gestion.administracion.bd.ListPermisosBDIn;
import gestion.administracion.bd.ListUsuariosBD;
import gestion.administracion.bd.ListUsuariosBDIn;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import utils.PropiedadesJLet;
import arquitectura.objects.Grid;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import common.bd.ListEmisoresBD;
import common.bd.ListEmisoresBDIn;
import common.bd.ReconnectBD;
import common.bd.ReconnectBDIn;
import common.varstatic.VariablesStatic;

public class ServletAutoLogin extends HttpServlet {

	Connection con = null;
	HttpSession session = null;
	
	
	Grid gridUser = null;
	Grid gridPant = null;
	Grid gridPerm = null;
	Grid gridEmis = null;
	
	
	HashMap<String,String> permEmis = new HashMap<String,String>();
	
	String cduserid = null;
	String username = null;
	String cdrolxxx = null;
	String perfiltp = "N";
	String txnombre = null;
	
	private static final long serialVersionUID = 1L;
	

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
    	doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{    	
		
    	//String usuariox = request.getParameter("username");
    	//String password = request.getParameter("userpass");
    	
    	//-------------------------------------->>> PASO 1. Indicar Usuario / Password con el que se quiere logar
    	String usuariox = "julioejl";
    	String password = "vetustacs";
    	String urldestin = "";
    	
    	System.out.println("Usuario "+ usuariox);
    	System.out.println("pass "+ password);
    	
		try {
			
			username = usuariox;
			session = request.getSession();
			crearConexion();
		    RequestDispatcher redireccion = null;
		    
		    try{
	        	   ReconnectBDIn reconecBDIn = new ReconnectBDIn();
	        	   ReconnectBD reconecBD = new ReconnectBD(reconecBDIn);
	        	   reconecBD.setConnection(con);
	        	   reconecBD.execSelect();
	           } catch(Exception ex){
	        	   System.out.println(this.getClass().getName()+ " Error controlado -------> reconect "+ex.getMessage());
	        }
		    
		    if (comprobarAcceso(usuariox,password) == 1){
		    	
		    	recuperarDatos();
		    	estableceLocale();
		    	obtengoPermisos();
		    	obtengoEmisores();
		    	obtengoPantallas();
		    	crearSesion();
		    	VariablesStatic.cargaCommonHashMap("S", con);
		    	urldestin = redireccionInicio();
		    	
		    } else {
		    	System.out.println("Se han introducido datos incorrectos: "+ usuariox +"/"+ password);
		    }
		    
		    redireccion = request.getRequestDispatcher(urldestin);
		    redireccion.forward(request, response); 
		    
		} catch (Exception e){
			System.err.println(" - ERROR - Al intentar obtener Datos: "+ e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
    
    public String fechaNormal(String fhfechax){
        
		String fhMySql = "";
		try {
			String [] arrFecha = fhfechax.split("-");
			fhMySql = arrFecha[2]+"-"+ arrFecha[1]+"-"+arrFecha[0];
		} catch (Exception e) {
			return "00/00/0000";
		}
		return fhMySql;
    }
    
    private int comprobarAcceso(String username, String password){
    	
    	int nresults = -1;
    	
    	try {
    		
    		ListUsuariosBDIn userBDIn = new ListUsuariosBDIn();
    		userBDIn.setValue("username", username);
    		userBDIn.setValue("password", password);
    		ListUsuariosBD userBD = new ListUsuariosBD(userBDIn);
    		userBD.setConnection(con);
    		Grid gridUser = userBD.execSelect();
    		
    		nresults = gridUser.rowCount();
    		
    		if (nresults == 1){
    			cduserid = gridUser.getStringCell(0, "idusuari");
    			perfiltp = gridUser.getStringCell(0, "perfiltp");
    			cdrolxxx = gridUser.getStringCell(0, "cdrolxxx");
    			txnombre = gridUser.getStringCell(0, "cdrolxxx");
    		}
    		
    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() + " ERROR comprobarAcceso() "+ e.getMessage());
    	}
		
    	return nresults;
    }
    
    private void recuperarDatos(){
    	
    	try {
    		// JEJ lo recuperamos directamente en el login
    		/*
    		ListEmpleadosBDIn emplBDIn = new ListEmpleadosBDIn();
    		emplBDIn.setValue("cduserid", cduserid);
    		ListEmpleadosBD emplBD = new ListEmpleadosBD(emplBDIn);
    		emplBD.setConnection(con);
    		Grid gridEmple = emplBD.execSelect();
    		
    		String txnombre = gridEmple.getStringCell(0, "txnombre");
    		*/
    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() + "  ERROR recuperarDatos() "+ e.getMessage());
    	}
    	
    }
    
    private void estableceLocale(){
    	
    	try {
    		
    		Date date = new Date(); 
    		Locale locale = new Locale("es","ES");
    		
    		Calendar miCalendario = Calendar.getInstance(locale);
    		date = miCalendario.getTime();
    		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
    		System.out.println("Esta es la fecha del locale: "+ format1.format(date));

    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() + "  ERROR recuperarDatos() "+ e.getMessage());
    	}
    	
    }
    
    private void obtengoPermisos(){
    	
    	try {
    		
    		permEmis = new HashMap<String, String>();
    		
    		ListPermisosBDIn permEmiBDIn = new ListPermisosBDIn();
    		permEmiBDIn.setValue("cdrolxxx", cdrolxxx);
    		permEmiBDIn.setValue("tipoperm", "EMISOR");
    		ListPermisosBD permEmiBD = new ListPermisosBD(permEmiBDIn);
    		permEmiBD.setConnection(con);
    		gridPerm = permEmiBD.execSelect();
    		
    		for (int i = 0; i < gridPerm.rowCount(); i++){
    			permEmis.put(gridPerm.getStringCell(i,"valorper"), gridPerm.getStringCell(i,"nivelper"));
    		}

    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() + " ERROR recuperarDatos() "+ e.getMessage());
    	}
    	
    }
    
    private void obtengoEmisores(){
		
		try{
				ListEmisoresBDIn ListEmiBDIn  = new ListEmisoresBDIn();  
		    	ListEmisoresBD ListEmiBD = new ListEmisoresBD(ListEmiBDIn);
		    	ListEmiBD.setConnection(con);
		    	gridEmis = ListEmiBD.execSelect();
		    	
		    	gridPerm = new Grid();
		    	
		    	gridPerm.addColumn("idemisor");
		    	gridPerm.addColumn("tpclient");
		    	gridPerm.addColumn("rzsocial");
		    	gridPerm.addColumn("logoimgx");
		    	
		    	for (int i = 0; i < gridEmis.rowCount(); i++){
	        		
	        		String idemisor = gridEmis.getStringCell(i,"idclient");
	        		String tpclient = gridEmis.getStringCell(i,"tpclient");
	        		
	        		if (permEmis.containsKey(idemisor)){
	        			ArrayList<String> row = new ArrayList<String>();
	        			
	        			row.add(idemisor);
	        			row.add(tpclient);
	        			row.add(gridEmis.getStringCell(i,"rzsocial"));
	        			row.add(gridEmis.getStringCell(i,"logoclie"));
	        			
	        			gridPerm.addRow(row);
	        			
	        		}
	        	}
		    	
			}catch(Exception ex){
				System.out.println("Error al obtener emisores");
			}
    }
    
    private void obtengoPantallas(){
    	
    	try {
    		
    		ListPantallasBDIn permPantBDIn = new ListPantallasBDIn();
    		permPantBDIn.setValue("cdrolxxx", cdrolxxx);
    		ListPantallasBD permEmiBD = new ListPantallasBD(permPantBDIn);
    		permEmiBD.setConnection(con);
    		gridPant = permEmiBD.execSelect();
    		
    	} catch (Exception e) {
    		System.err.println(this.getClass().getName() + " ERROR recuperarDatos() "+ e.getMessage());
    	}
    	
    }
    

    private void crearSesion(){
    	
    	try {
			session.setAttribute("idusuari", cduserid);
			session.setAttribute("username", username);
			session.setAttribute("cdrolxxx", cdrolxxx);
			session.setAttribute("tprolxxx", perfiltp);
			session.setAttribute("permEmis", permEmis);
			session.setAttribute("gridEmis", gridPerm);
			session.setAttribute("gridPant", gridPant);
			
			//-------------------------------------->>> PASO 2. Seleccionar el emisor por defecto
	    	session.setAttribute("idemisor","8");
	    	session.setAttribute("tpclient","0");
			
    	} catch (Exception e) {
    		System.out.println(this.getClass().getName() + " ERROR c rearSesion() "+ e.getMessage());
    	}
	    
    }

    private String redireccionInicio(){
    	
    	String urldesti = "";
    	
    	//-------------------------------------->>> PASO 3. Seleccionar la URL destino
    	urldesti = "/common/jsp/autoMenu.jsp";
	    		
    	return urldesti;
    	
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
			//System.out.println(this.getClass().getName() +"Conexion realizada desde el Pool ");
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
	
}


