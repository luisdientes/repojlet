package common.varstatic;

import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.Logger;

import arquitectura.objects.Grid;

import common.bd.ListDivEmisorBD;
import common.bd.ListDivEmisorBDIn;
import common.bd.ListEmisoresBD;
import common.bd.ListEmisoresBDIn;
import common.bd.ListMarcasBD;
import common.bd.ListMarcasBDIn;
import common.bd.ListMarcasEmisoresBD;
import common.bd.ListMarcasEmisoresBDIn;

public class VariablesStatic {

	static HashMap<String,String> hmEmisor = new HashMap<String,String>();
	static HashMap<String,String> hmLogoEm = new HashMap<String,String>();
	static HashMap<String,String> hmMarcas = new HashMap<String,String>();
	static HashMap<String,String> hmColorc = new HashMap<String,String>();
	static HashMap<String,String> hmPaisEm = new HashMap<String,String>();
	static HashMap<String,String> hmDivisa = new HashMap<String,String>();
	static HashMap<String,String> hmDivSim = new HashMap<String,String>();
	
	static ArrayList<Grid> arMarcas = new ArrayList<Grid>();
	
	static Grid gdEmisor = null;
	static Grid gdMarcas = null;
	static Grid gdDaEmis = null;
	
	public static void cargaCommonHashMap(String reseteo, Connection con) {
		
		if ((hmEmisor.size() == 0) || (reseteo.equals("S"))){
		
			try {
				ListEmisoresBDIn emisorBDIn = new ListEmisoresBDIn();
				ListEmisoresBD emisorBD = new ListEmisoresBD(emisorBDIn);
				emisorBD.setConnection(con);
				gdEmisor = emisorBD.execSelect();
				
				for (int i = 0; i < gdEmisor.rowCount(); i++) {
					hmEmisor.put(gdEmisor.getStringCell(i, "idclient")+gdEmisor.getStringCell(i, "tpclient"),gdEmisor.getStringCell(i, "rzsocial"));
					hmLogoEm.put(gdEmisor.getStringCell(i, "idclient")+gdEmisor.getStringCell(i, "tpclient"),gdEmisor.getStringCell(i, "logoclie"));
				//	hmColorc.put(gdEmisor.getStringCell(i, "idclient"),gdEmisor.getStringCell(i, "colocorp"));
				//	hmPaisEm.put(gdEmisor.getStringCell(i, "idclient"),gdEmisor.getStringCell(i, "idpaisxx"));
				//	hmDivisa.put(gdEmisor.getStringCell(i, "idclient"),gdEmisor.getStringCell(i, "cddivisa"));
				//	hmDivSim.put(gdEmisor.getStringCell(i, "cddivisa"),gdEmisor.getStringCell(i, "divsimbo"));
				}
				
				Logger.getLogger("LRA").info("VariablesStatic.cargaCommonHashMap()   - Se han cargado "+ hmLogoEm.size() +"/"+ gdEmisor.rowCount() +" emisores (nombre y logo).");
				
			} catch (Exception e) {
				Logger.getLogger("LRA").warning("VariablesStatic.cargaCommonHashMap()   - ERROR al cargar hmCompan "+ e.getMessage());
			}
			
			try {				
				ListMarcasBDIn marcasBDIn = new ListMarcasBDIn();
				ListMarcasBD marcasBD = new ListMarcasBD(marcasBDIn);
				marcasBD.setConnection(con);
				gdMarcas = marcasBD.execSelect();

				for (int i = 0; i < gdMarcas.rowCount(); i++) {
					hmMarcas.put(gdMarcas.getStringCell(i, "idmarcax"), gdMarcas.getStringCell(i, "txnombre"));
				}
				
				ListMarcasEmisoresBDIn emismarcBDIn = new ListMarcasEmisoresBDIn();				
				ListMarcasEmisoresBD emismarcBD = new ListMarcasEmisoresBD(emismarcBDIn);
				emismarcBD.setConnection(con);
				Grid gdMarEmi = emismarcBD.execSelect();
				Grid emMarcas = null;
				String antEmiso = "";
				
				for (int i = 0; i < gdMarEmi.rowCount(); i++){
					if (!antEmiso.equals(gdMarEmi.getStringCell(i,"idemisor"))){
						
						antEmiso = gdMarEmi.getStringCell(i,"idemisor");
						
						if (emMarcas != null) {
							arMarcas.add(emMarcas);
						}
						
						antEmiso = gdMarEmi.getStringCell(i,"idemisor");
						emMarcas = new Grid();
						emMarcas.addColumn("idemisor");
						emMarcas.addColumn("idmarcax");
						emMarcas.addColumn("txnombre");
						
					}
					
					ArrayList<String> rowMarca = new ArrayList<String>();
					rowMarca.add(gdMarEmi.getStringCell(i,"idemisor"));
					rowMarca.add(gdMarEmi.getStringCell(i,"idmarcax"));
					rowMarca.add(gdMarEmi.getStringCell(i,"txnombre"));
					emMarcas.addRow(rowMarca);					
					
				}
				
				arMarcas.add(emMarcas);
				
				Logger.getLogger("LRA").info(" VariablesStatic.cargaCommonHashMap()   - Se han cargado "+ hmLogoEm.size() +"/"+ gdEmisor.rowCount() +" emisores (nombre y logo).");
				
			} catch (Exception e) {
				Logger.getLogger("LRA").warning("VariablesStatic.cargaCommonHashMap()   - ERROR al cargar hmCompan "+ e.getMessage());
			}
			
			
			try{
				ListDivEmisorBDIn listDatosEmiBDin = new ListDivEmisorBDIn();
				ListDivEmisorBD datosEmiBD = new ListDivEmisorBD(listDatosEmiBDin);
				datosEmiBD.setConnection(con);
				gdDaEmis = datosEmiBD.execSelect();
				
				for (int i = 0; i < gdDaEmis.rowCount(); i++) {
					
					hmColorc.put(gdDaEmis.getStringCell(i, "idemisor"),gdDaEmis.getStringCell(i, "colocorp"));
					hmPaisEm.put(gdDaEmis.getStringCell(i, "idemisor"),gdDaEmis.getStringCell(i, "idpaisxx"));
					hmDivisa.put(gdDaEmis.getStringCell(i, "idemisor"),gdDaEmis.getStringCell(i, "cddivisa"));
					hmDivSim.put(gdDaEmis.getStringCell(i, "idemisor"),gdDaEmis.getStringCell(i, "divsimbo"));
				}

				
			}catch (Exception e){
				Logger.getLogger("LRA").warning("VariablesStatic.cargaCommonHashMap()   - ERROR al cargar color, divisa emisor "+ e.getMessage());
			}
		
		}
	}
	
	public static String getEmisor(String idemisor,String tpclient){
		
		String txnombre = "No Determinado";
		
		if (hmEmisor.containsKey(idemisor+tpclient)){
			txnombre = hmEmisor.get(idemisor+tpclient);
		}
		
		return txnombre;
	}
	
	public static String getLogoEmisor(String idemisor,String tpclient){
		
		String logoemis = "No Determinado";
		
		if (hmLogoEm.containsKey(idemisor+tpclient)){
			logoemis = hmLogoEm.get(idemisor+tpclient);
		}
		
		return logoemis;
	}
	
	public static String getColorc(String idemisor){
		
		String cdcolorh = "0033DD";
		
		if (hmColorc.containsKey(idemisor)){
			cdcolorh = hmColorc.get(idemisor);
		}
		
		return cdcolorh;
		
	}
	
	public static String getDivisa(String idemisor){
		
		String cddivisa = "";
		
		if (hmDivisa.containsKey(idemisor)){
			cddivisa = hmDivisa.get(idemisor);
		}
		
		return cddivisa;
		
	}
	
	public static String getDivisaSim(String idemisor){
		
		String cddivisa = "";
		
		if (hmDivSim.containsKey(idemisor)){
			cddivisa = hmDivSim.get(idemisor);
		}
		
		return cddivisa;
		
	}
	
	public static Grid getMarcasEmisor(String idemisor){
		
		Grid gdMarcas = null;
		Grid gdTmpMar = null;
		
		for (int i = 0; i < arMarcas.size(); i++) {
			gdTmpMar = arMarcas.get(i);
			if (idemisor.equals(gdTmpMar.getStringCell(0, "idemisor"))) {
				gdMarcas = arMarcas.get(i);
			}
		}
		
		return gdMarcas;
		
	}
	
	public static String getColorDecimal(String idemisor){
		
		String cdcolord = "";
		String hexadeci  = "";
		
		hexadeci = getColorc(idemisor);
		int decimal1 = Integer.parseInt(hexadeci.substring(0,2), 16);
		int decimal2 = Integer.parseInt(hexadeci.substring(2,4), 16);
		int decimal3 = Integer.parseInt(hexadeci.substring(4,6), 16);
		
		cdcolord = String.valueOf(decimal1) +","+ String.valueOf(decimal2) +","+ String.valueOf(decimal3);
		
		return cdcolord;
	}
	
	public static String fechaHoy(){
	    	
		 DecimalFormat formatFecha = new DecimalFormat("00");
		 
		 Calendar c = Calendar.getInstance();
	    	
		 String fhtemp = "";
		 String stryear  = "";
		 String strmonth = "";
		 String strday	= "";
	    	
		 int year 	= c.get(Calendar.YEAR);
		 int month 	= c.get(Calendar.MONTH)+1;
		 int day	= c.get(Calendar.DAY_OF_MONTH);
			
		 stryear  = String.valueOf(year);
		 strmonth = formatFecha.format(month);
		 strday   = formatFecha.format(day);
		
		 fhtemp = strday +"/"+ strmonth +"/"+ stryear; 
		
		 return fhtemp;
		 
	 }
	 
	 public static String horaSeg(){
	    	
		 DecimalFormat formatFecha = new DecimalFormat("00");
		 
		 Calendar c = Calendar.getInstance();
	    	
		 String fhtemp = "";
		 String strhora  = "";
		 String strminu = "";
		 String strsegu	= "";
	    	
		 int hora 	= c.get(Calendar.HOUR_OF_DAY);
		 int minu 	= c.get(Calendar.MINUTE);
		 int segu	= c.get(Calendar.SECOND);
			
		 strhora  = formatFecha.format(hora);
		 strminu = formatFecha.format(minu);
		 strsegu  = formatFecha.format(segu);
		
		 fhtemp = strhora +":"+ strminu +":"+ strsegu; 
		
		 return fhtemp;
		 
	 }
	 
	 public static String randomCaracteres(int numcarac) {
		 
		 String randomCa = "";
		 
		 String [] abecedario = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
					"K", "L", "M","N","O","P","Q","R","S","T","U","V","W", "X","Y","Z" };
		 
		 for (int i = 0; i < numcarac; i++) {
			 randomCa += abecedario[(int) Math.round(Math.random() * 25 )];
		 }
		 
		 return randomCa;
		 
	 }
	 
	   public static String addSlashes( String text ){    	
		   text = text.replaceAll("\\\\", "\\\\\\\\");
		   text = text.replaceAll("\\n", "\\\\n");
		   text = text.replaceAll("\\r", "\\\\r");
		   text = text.replaceAll("\\00", "\\\\0");
		   text = text.replaceAll("'", "´");
	        return text;
	    }
		
       
}
