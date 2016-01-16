package facturacion.launcher;

import java.sql.Connection;
import java.sql.SQLException;

import arquitectura.controller.Service;
import arquitectura.objects.ObjectIO;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import facturacion.CodBarrasPiezasSrv;
import facturacion.FRAtradensESSrv;
import facturacion.TablaColumnas;
import facturacion.TablaSrv;

public class launcherFRA {

	static Connection con = null;
	
	public static void main(String[] args) {
		
		ObjectIO input 	= null;
		ObjectIO output = null;
		String texto="";
		texto+="- Santander Factoring y Confirming, S.A., E.F.C.: pr�stamo y cr�dito, factoring, arrendamiento financiero y no financiero.";
		texto+="- Santander Asset Management, S.A. SGIIC: administraci�n y representaci�n de Instituciones de Inversi�n Colectiva y Fondos de Inversi�n.";
		texto+="- Santander de Titulizaci�n S.G.F.T., S.A.: constituci�n, administraci�n y representaci�n de Fondos de Titulizaci�n Hipotecaria.";
		texto+="- Santander Pensiones, S.A., E.G.F.P.: administraci�n de fondos de pensiones.";
		texto+="- Santander Carteras, S.G.C., S.A.: gesti�n individualizada de carteras de inversi�n y asesoramiento de empresas en materia de fusiones y adquisiciones, estructura de capital y cuestiones afines.";
		texto+="- Santander Seguros y Reaseguros, Compa��a Aseguradora, S.A.: Seguros.";
		texto+="- Santander Mediaci�n Operador de Banca-Seguros Vinculado, S.A.: Mediaci�n de Seguros.";
		texto+="- Santander Lease, S.A., E.F.C.: arrendamiento financiero y no financiero.";
		texto+="- Santander Factoring y Confirming, S.A., E.F.C.: pr�stamo y cr�dito, factoring, arrendamiento financiero y no financiero.";
		texto+="- Santander Asset Management, S.A. SGIIC: administraci�n y representaci�n de Instituciones de Inversi�n Colectiva y Fondos de Inversi�n.";
		texto+="- Santander de Titulizaci�n S.G.F.T., S.A.: constituci�n, administraci�n y representaci�n de Fondos de Titulizaci�n Hipotecaria.";
		texto+="- Santander Pensiones, S.A., E.G.F.P.: administraci�n de fondos de pensiones.";
		texto+="- Santander Carteras, S.G.C., S.A.: gesti�n individualizada de carteras de inversi�n y asesoramiento de empresas en materia de fusiones y adquisiciones, estructura de capital y cuestiones afines.";
		texto+="- Santander Seguros y Reaseguros, Compa��a Aseguradora, S.A.: Seguros.";
		texto+="- Santander Mediaci�n Operador de Banca-Seguros Vinculado, S.A.: Mediaci�n de Seguros.";
		texto+="- Santander Lease, S.A., E.F.C.: arrendamiento financiero y no financiero.";
		texto+="- Santander Factoring y Confirming, S.A., E.F.C.: pr�stamo y cr�dito, factoring, arrendamiento financiero y no financiero.";
		texto+="- Santander Asset Management, S.A. SGIIC: administraci�n y representaci�n de Instituciones de Inversi�n Colectiva y Fondos de Inversi�n.";
		texto+="- Santander de Titulizaci�n S.G.F.T., S.A.: constituci�n, administraci�n y representaci�n de Fondos de Titulizaci�n Hipotecaria.";
		texto+="- Santander Pensiones, S.A., E.G.F.P.: administraci�n de fondos de pensiones.";
		texto+="- Santander Carteras, S.G.C., S.A.: gesti�n individualizada de carteras de inversi�n y asesoramiento de empresas en materia de fusiones y adquisiciones, estructura de capital y cuestiones afines.";
		texto+="- Santander Seguros y Reaseguros, Compa��a Aseguradora, S.A.: Seguros.";
		texto+="- Santander Mediaci�n Operador de Banca-Seguros Vinculado, S.A.: Mediaci�n de Seguros.";
		texto+="- Santander Lease, S.A., E.F.C.: arrendamiento financiero y no financiero.";
		texto+="- Santander Factoring y Confirming, S.A., E.F.C.: pr�stamo y cr�dito, factoring, arrendamiento financiero y no financiero.";
		texto+="- Santander Asset Management, S.A. SGIIC: administraci�n y representaci�n de Instituciones de Inversi�n Colectiva y Fondos de Inversi�n.";
		texto+="- Santander de Titulizaci�n S.G.F.T., S.A.: constituci�n, administraci�n y representaci�n de Fondos de Titulizaci�n Hipotecaria.";
		texto+="- Santander Pensiones, S.A., E.G.F.P.: administraci�n de fondos de pensiones.";
		texto+="- Santander Carteras, S.G.C., S.A.: gesti�n individualizada de carteras de inversi�n y asesoramiento de empresas en materia de fusiones y adquisiciones, estructura de capital y cuestiones afines.";
		texto+="- Santander Seguros y Reaseguros, Compa��a Aseguradora, S.A.: Seguros.";
		texto+="- Santander Mediaci�n Operador de Banca-Seguros Vinculado, S.A.: Mediaci�n de Seguros.";
		texto+="- Santander Lease, S.A., E.F.C.: arrendamiento financiero y no financiero.";
		texto+="- Santander Factoring y Confirming, S.A., E.F.C.: pr�stamo y cr�dito, factoring, arrendamiento financiero y no financiero.";
		texto+="- Santander Asset Management, S.A. SGIIC: administraci�n y representaci�n de Instituciones de Inversi�n Colectiva y Fondos de Inversi�n.";
		texto+="- Santander de Titulizaci�n S.G.F.T., S.A.: constituci�n, administraci�n y representaci�n de Fondos de Titulizaci�n Hipotecaria.";
		texto+="- Santander Pensiones, S.A., E.G.F.P.: administraci�n de fondos de pensiones.";
		texto+="- Santander Carteras, S.G.C., S.A.: gesti�n individualizada de carteras de inversi�n y asesoramiento de empresas en materia de fusiones y adquisiciones, estructura de capital y cuestiones afines.";
		texto+="- Santander Seguros y Reaseguros, Compa��a Aseguradora, S.A.: Seguros.";
		texto+="- Santander Mediaci�n Operador de Banca-Seguros Vinculado, S.A.: Mediaci�n de Seguros.";
		texto+="- Santander Lease, S.A., E.F.C.: arrendamiento financiero y no financiero.";
		texto+="- Santander Factoring y Confirming, S.A., E.F.C.: pr�stamo y cr�dito, factoring, arrendamiento financiero y no financiero.";
		texto+="- Santander Asset Management, S.A. SGIIC: administraci�n y representaci�n de Instituciones de Inversi�n Colectiva y Fondos de Inversi�n.";
		texto+="- Santander de Titulizaci�n S.G.F.T., S.A.: constituci�n, administraci�n y representaci�n de Fondos de Titulizaci�n Hipotecaria.";
		texto+="- Santander Pensiones, S.A., E.G.F.P.: administraci�n de fondos de pensiones.";
		texto+="- Santander Carteras, S.G.C., S.A.: gesti�n individualizada de carteras de inversi�n y asesoramiento de empresas en materia de fusiones y adquisiciones, estructura de capital y cuestiones afines.";
		texto+="- Santander Seguros y Reaseguros, Compa��a Aseguradora, S.A.: Seguros.";
		texto+="- Santander Mediaci�n Operador de Banca-Seguros Vinculado, S.A.: Mediaci�n de Seguros.";
		texto+="- Santander Lease, S.A., E.F.C.: arrendamiento financiero y no financiero.";
		texto+="- Santander Factoring y Confirming, S.A., E.F.C.: pr�stamo y cr�dito, factoring, arrendamiento financiero y no financiero.";
		texto+="- Santander Asset Management, S.A. SGIIC: administraci�n y representaci�n de Instituciones de Inversi�n Colectiva y Fondos de Inversi�n.";
		texto+="- Santander de Titulizaci�n S.G.F.T., S.A.: constituci�n, administraci�n y representaci�n de Fondos de Titulizaci�n Hipotecaria.";
		texto+="- Santander Pensiones, S.A., E.G.F.P.: administraci�n de fondos de pensiones.";
		texto+="- Santander Carteras, S.G.C., S.A.: gesti�n individualizada de carteras de inversi�n y asesoramiento de empresas en materia de fusiones y adquisiciones, estructura de capital y cuestiones afines.";
		texto+="- Santander Seguros y Reaseguros, Compa��a Aseguradora, S.A.: Seguros.";
		texto+="- Santander Mediaci�n Operador de Banca-Seguros Vinculado, S.A.: Mediaci�n de Seguros.";
		texto+="- Santander Lease, S.A., E.F.C.: arrendamiento financiero y no financiero.";
		texto+="- Santander Factoring y Confirming, S.A., E.F.C.: pr�stamo y cr�dito, factoring, arrendamiento financiero y no financiero.";
		texto+="- Santander Asset Management, S.A. SGIIC: administraci�n y representaci�n de Instituciones de Inversi�n Colectiva y Fondos de Inversi�n.";
		texto+="- Santander de Titulizaci�n S.G.F.T., S.A.: constituci�n, administraci�n y representaci�n de Fondos de Titulizaci�n Hipotecaria.";
		texto+="- Santander Pensiones, S.A., E.G.F.P.: administraci�n de fondos de pensiones.";
		texto+="- Santander Carteras, S.G.C., S.A.: gesti�n individualizada de carteras de inversi�n y asesoramiento de empresas en materia de fusiones y adquisiciones, estructura de capital y cuestiones afines.";
		texto+="- Santander Seguros y Reaseguros, Compa��a Aseguradora, S.A.: Seguros.";
		texto+="- Santander Mediaci�n Operador de Banca-Seguros Vinculado, S.A.: Mediaci�n de Seguros.";
		texto+="- Santander Lease, S.A., E.F.C.: arrendamiento financiero y no financiero.";
		texto+="- Santander Factoring y Confirming, S.A., E.F.C.: pr�stamo y cr�dito, factoring, arrendamiento financiero y no financiero.";
		texto+="- Santander Asset Management, S.A. SGIIC: administraci�n y representaci�n de Instituciones de Inversi�n Colectiva y Fondos de Inversi�n.";
		texto+="- Santander de Titulizaci�n S.G.F.T., S.A.: constituci�n, administraci�n y representaci�n de Fondos de Titulizaci�n Hipotecaria.";
		texto+="- Santander Pensiones, S.A., E.G.F.P.: administraci�n de fondos de pensiones.";
		texto+="- Santander Carteras, S.G.C., S.A.: gesti�n individualizada de carteras de inversi�n y asesoramiento de empresas en materia de fusiones y adquisiciones, estructura de capital y cuestiones afines.";
		texto+="- Santander Seguros y Reaseguros, Compa��a Aseguradora, S.A.: Seguros.";
		texto+="- Santander Mediaci�n Operador de Banca-Seguros Vinculado, S.A.: Mediaci�n de Seguros.";
		texto+="- Santander Lease, S.A., E.F.C.: arrendamiento financiero y no financiero.";
		

		//String idorderx = "81";
		
	
		//Service srv = new FRAvetustaCSSrv();
		//Service srv = new FRAtradensESSrv();
		//Service srv = new TablaSrv();
		Service srv = new TablaColumnas();
		//Service srv= new FRAizumbaWebSrv(); 
		
		
		input  = srv.instanceOfInput();
		output = srv.instanceOfOutput();
		
		try {
			
			input.setValue("textocol", texto);
			/*
			input.setValue("receclie", receclie);
			input.setValue("tpclient", tpclient);
			input.setValue("aniofact", aniofact);
			input.setValue("tipofact", tipofact);
			input.setValue("mcagrupa", mcagrupa);
			input.setValue("fhfactur", fhfactur);
			input.setValue("listimei", listimei);*/
		
			
			/*
			input.setValue("idemisor", "1");
			input.setValue("aniofact", "2014");
			input.setValue("fhdesdex", "01/01/2014");
			input.setValue("fhhastax", "31/12/2014");
			*/
			crearConexion();
			srv.setConnection(con);
			srv.process(input, output);
			//cerrarConexion();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void crearConexion(){							
		
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("");
		dataSource.setServerName("localhost");
		//dataSource.setUser("root");
		//dataSource.setPassword("");
		dataSource.setDatabaseName("des_jletproj");
		//dataSource.setServerName("127.0.0.1");

		try {
			con = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		System.out.println("[OPEN] - Abro Conexion");
		
	}
	
	public static void cerrarConexion(){							
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		System.out.println("[OPEN] - Abro Conexion");
		
	}
	

}
