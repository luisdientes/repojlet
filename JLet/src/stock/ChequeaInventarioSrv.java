package stock;


import java.util.ArrayList;
import java.util.HashSet;

import stock.bd.ListInventarioBD;
import stock.bd.ListInventarioBDIn;
import stock.bd.ListStockBD;
import stock.bd.ListStockBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import common.constructors.xls.BasicExcel;
import common.constructors.xls.ExcelGenericoSrv;


public class ChequeaInventarioSrv extends Service {

	BasicExcel creador = null;
	
	String filename = "";
	
    public ChequeaInventarioSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();	
			input.addVariable("idemisor");
			input.addVariable("nameinve");
			input.addVariable("codprodu");
			input.addVariable("tpproduc");
			input.addVariable("tipocheq");

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
			output.addVariable("nameinve");
			output.addVariable("codprodu");
			output.addVariable("tpproduc");
			output.addVariable("tipocheq");
			output.addVariable("filename");
			
			output.addVariable("cdestado");
			output.addVariable("gdcheqok");
			output.addVariable("gdnofisi");
			output.addVariable("gdnosist");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor = null;
    	String nameinve = null;
    	String codprodu = null;
    	String tpproduc = null;
    	String tipocheq = null;
        
        //Varibales de salida
    	Grid gridnoid = null;
    	Grid gridfalt = null; 
    	
    	//Otras Variables
    	String cdestado = "STOCK";
    	String resultad = "OK";
    	
    	Grid gridInve = null;
    	Grid gridStoc = null;
    	
    	Grid gdcheqok = null;
    	Grid gdnofisi = null;
    	Grid gdnosist = null;
    	
    	ArrayList<String> arNoSist = new ArrayList<String>();
    	ArrayList<String> arPerdid = new ArrayList<String>();
    	
    	HashSet<String> stockbdx = new HashSet<String>(); 
    	
        try {
        	
        	idemisor = input.getStringValue("idemisor");
        	nameinve = input.getStringValue("nameinve");
        	codprodu = input.getStringValue("codprodu"); 
        	tpproduc = input.getStringValue("tpproduc");
        	tipocheq = input.getStringValue("tipocheq");
        	
        	tipocheq = tipocheq.replaceAll("'OT'", "'OT',''");
	        try {
	        	 
	        	ListInventarioBDIn inventBDIn= new ListInventarioBDIn();
	        	inventBDIn.setValue("idemisor",idemisor);
	        	inventBDIn.setValue("nameinve",nameinve);
	        	//inventBDIn.setValue("tpproduc",tpproduc);
	        	inventBDIn.setValue("tipocheq",tipocheq);
	        	
	        	
	        	ListInventarioBD inventBD= new ListInventarioBD(inventBDIn);
	        	inventBD.setConnection(this.getConnection());
	        	gridInve = inventBD.execSelect();
	        	
	        	ListStockBDIn stockBDIn= new ListStockBDIn();
	        	stockBDIn.setValue("idemisor",idemisor);
	        	stockBDIn.setValue("cdestado",cdestado);
	        	stockBDIn.setValue("codprodu",codprodu);
	        	//stockBDIn.setValue("tpproduc",tpproduc);
	        	stockBDIn.setValue("tipocheq",tipocheq);
	        	ListStockBD stockBD= new ListStockBD(stockBDIn);
	        	stockBD.setConnection(this.getConnection());
	        	gridStoc = stockBD.execSelect();
	        	
	        	
	        	for (int i = 0; i < gridStoc.rowCount(); i++) {
		        
	        		stockbdx.add(gridStoc.getStringCell(i,"imeicode"));
	        		
	        	}
	        	
	        	//Inicializo los grids de salida
	        	gdcheqok = new Grid();
	        	gdcheqok.addColumn("imeicode");
	        	gdcheqok.addColumn("codprodu");
	        	gdcheqok.addColumn("txmarcax");
	        	gdcheqok.addColumn("txmodelo");
	        	gdcheqok.addColumn("idcolorx");
	        	gdcheqok.addColumn("prusdcmp");
	        	gdcheqok.addColumn("pricevnt");
	        	gdcheqok.addColumn("prusdvnt");
	        	gdcheqok.addColumn("withboxx");
	        	gdcheqok.addColumn("withusbx");
	        	gdcheqok.addColumn("idcatego");
	        	gdcheqok.addColumn("tpproduc");
	        	
	        	gdnofisi = new Grid();
	        	gdnofisi.addColumn("imeicode");
	        	gdnofisi.addColumn("codprodu");
	        	gdnofisi.addColumn("txmarcax");
	        	gdnofisi.addColumn("txmodelo");
	        	gdnofisi.addColumn("idcolorx");
	        	gdnofisi.addColumn("prusdcmp");
	        	gdnofisi.addColumn("pricevnt");
	        	gdnofisi.addColumn("prusdvnt");
	        	gdnofisi.addColumn("withboxx");
	        	gdnofisi.addColumn("withusbx");
	        	gdnofisi.addColumn("idcatego");
	        	gdnofisi.addColumn("tpproduc");
	        	
	        	gdnosist = new Grid();
	        	gdnosist.addColumn("imeicode");

	        	
	        	
	        	for (int i = 0; i < gridInve.rowCount(); i++) {
	        		
	        		if (stockbdx.contains(gridInve.getStringCell(i,"idunicox"))){
	        			stockbdx.remove(gridInve.getStringCell(i,"idunicox"));
	        			//System.out.println("STOCK OK : "+ gridInve.getStringCell(i,"idunicox"));
	        			
	        			ArrayList<String> rwcheqok = new ArrayList<String>();
	        			rwcheqok.add(gridInve.getStringCell(i,"idunicox"));
	        			
	        			try {
	        			
	        				ListStockBDIn detStockBDIn = new ListStockBDIn();
			        		detStockBDIn.setValue("idemisor", idemisor);
			        		detStockBDIn.setValue("imeicode", gridInve.getStringCell(i,"idunicox"));
			        		detStockBDIn.setValue("cdestado", "NOAPLICA");
			        		ListStockBD detStockBD = new ListStockBD(detStockBDIn);
			        		detStockBD.setConnection(con);
			        		Grid gdDetSto = detStockBD.execSelect();
			        		
		        			rwcheqok.add(gdDetSto.getStringCell(0,"codprodu"));
		        			rwcheqok.add(gdDetSto.getStringCell(0,"txmarcax"));
		        			rwcheqok.add(gdDetSto.getStringCell(0,"txmodelo"));
		        			rwcheqok.add(gdDetSto.getStringCell(0,"idcolorx"));
		        			rwcheqok.add(gdDetSto.getStringCell(0,"prusdcmp"));
		        			rwcheqok.add(gdDetSto.getStringCell(0,"pricevnt"));
		        			rwcheqok.add(gdDetSto.getStringCell(0,"prusdvnt"));
		        			rwcheqok.add(gdDetSto.getStringCell(0,"withboxx"));
		        			rwcheqok.add(gdDetSto.getStringCell(0,"withusbx"));
		        			rwcheqok.add(gdDetSto.getStringCell(0,"idcatego"));
		        			rwcheqok.add(gdDetSto.getStringCell(0,"tpproduc"));
		        			
	        			} catch (Exception e) {
	        				System.out.println("ERROR recuperando el detalle del imei perdido: "+ gridInve.getStringCell(i,"idunicox"));
	        			}
	        			
	        			gdcheqok.addRow(rwcheqok);
	        			
	        		} else {
	        			arNoSist.add(gridInve.getStringCell(i,"idunicox"));
	        			
	        			ArrayList<String> rwnosist= new ArrayList<String>();
	        			rwnosist.add(gridInve.getStringCell(i,"idunicox"));
	        			gdnosist.addRow(rwnosist);
	        			
	        		}
	        		
	        	}
	        	
	        	System.out.println("     ");
	        	System.out.println(" --- ");
	        	System.out.println("     ");
	        	
	        	arPerdid = new ArrayList<String>(stockbdx);
	        	
	        	for (int i = 0; i < arPerdid.size(); i++){
	        		//System.out.println("STOCK NO SE ENCUENTRA EN STOCK : "+ arPerdid.get(i));
	        		ArrayList<String> rwnfisi = new ArrayList<String>();
        			rwnfisi.add(arPerdid.get(i));
        			
        			try {
	        		
		        		ListStockBDIn detStockBDIn = new ListStockBDIn();
		        		detStockBDIn.setValue("idemisor", idemisor);
		        		detStockBDIn.setValue("imeicode", arPerdid.get(i));
		        		detStockBDIn.setValue("cdestado", "NOAPLICA");
		        		ListStockBD detStockBD = new ListStockBD(detStockBDIn);
		        		detStockBD.setConnection(con);
		        		Grid gdDetSto = detStockBD.execSelect();
		        		
		        		rwnfisi.add(gdDetSto.getStringCell(0,"codprodu"));
		        		rwnfisi.add(gdDetSto.getStringCell(0,"txmarcax"));
		        		rwnfisi.add(gdDetSto.getStringCell(0,"txmodelo"));
		        		rwnfisi.add(gdDetSto.getStringCell(0,"idcolorx"));
		        		rwnfisi.add(gdDetSto.getStringCell(0,"prusdcmp"));
		        		rwnfisi.add(gdDetSto.getStringCell(0,"pricevnt"));
		        		rwnfisi.add(gdDetSto.getStringCell(0,"prusdvnt"));
		        		rwnfisi.add(gdDetSto.getStringCell(0,"withboxx"));
		        		rwnfisi.add(gdDetSto.getStringCell(0,"withusbx"));
		        		rwnfisi.add(gdDetSto.getStringCell(0,"idcatego"));
		        		rwnfisi.add(gdDetSto.getStringCell(0,"tpproduc"));
	        			
        			} catch (Exception e) {
        				System.out.println("ERROR recuperando el detalle del imei perdido: "+ arPerdid.get(i));
        			}
        			
        			
        			gdnofisi.addRow(rwnfisi);
	        		
	        	}
	        	
	        	for (int i = 0; i < arNoSist.size(); i++){
	        		//System.out.println("STOCK NO SE ENCUENTRA FISICAMENTE : "+ arNoSist.get(i));
	        	}
	        	
	        	
	        } catch (Exception e) {
				System.err.println(this.getClass().getName() +" ERROR recogiendo productos. "+ e.getMessage());
			}
    

	        generacionExcelCoincide(idemisor,gdcheqok);
	        generacionExcelNoFisico(idemisor,gdnofisi);
	        generacionExcelNoSistem(idemisor,gdnosist);
	        
	        output.setValue("idemisor", idemisor);
	        output.setValue("nameinve", nameinve);
	        output.setValue("tpproduc", tpproduc);
	        output.setValue("tipocheq", tipocheq);
	        output.setValue("cdestado", resultad);
	        output.setValue("codprodu", codprodu);
	        output.setValue("filename", filename);
	        output.setValue("gdcheqok", gdcheqok);	//Se encuentra en el sistema y se ha punteado con la pistoal. TODO OK
	        output.setValue("gdnofisi", gdnofisi);	//Esta dado en el sistema y no se ha punteado con la pistola
	        output.setValue("gdnosist", gdnosist);	//Se ha punteado con la pistola pero no esta dado de alta en el sistema
	        

        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
	public void generacionExcelCoincide(String idemisor, Grid gdChecOK) {
			
		Grid gdValore = new Grid();
		gdValore.addColumn("cabcampo");
		gdValore.addColumn("cdcampox");
		gdValore.addColumn("anchocam");
		gdValore.addColumn("tipocamp");
		gdValore.addColumn("alincamp");
		
		ArrayList<String> rowvalor = new ArrayList<String>();
		rowvalor.add("IMEICODE");
		rowvalor.add("imeicode");
		rowvalor.add("24");
		rowvalor.add("S");
		rowvalor.add("I");
		gdValore.addRow(rowvalor);

		rowvalor = new ArrayList<String>();
		rowvalor.add("COD.");
		rowvalor.add("codprodu");
		rowvalor.add("10");
		rowvalor.add("S");
		rowvalor.add("I");
		gdValore.addRow(rowvalor);
		
		rowvalor = new ArrayList<String>();
		rowvalor.add("MARCA");
		rowvalor.add("txmarcax");
		rowvalor.add("12");
		rowvalor.add("S");
		rowvalor.add("I");
		gdValore.addRow(rowvalor);
		
		rowvalor = new ArrayList<String>();
		rowvalor.add("MODELO");
		rowvalor.add("txmodelo");
		rowvalor.add("30");
		rowvalor.add("S");
		rowvalor.add("I");
		gdValore.addRow(rowvalor);
		
		rowvalor = new ArrayList<String>();
		rowvalor.add("COLOR");
		rowvalor.add("idcolorx");
		rowvalor.add("8");
		rowvalor.add("S");
		rowvalor.add("C");
		gdValore.addRow(rowvalor);

		rowvalor = new ArrayList<String>();
		rowvalor.add("P.COMPRA USD");
		rowvalor.add("prusdcmp");
		rowvalor.add("12");
		rowvalor.add("2D");
		rowvalor.add("D");
		gdValore.addRow(rowvalor);
		
		rowvalor = new ArrayList<String>();
		rowvalor.add("P.VENTA RD$");
		rowvalor.add("pricevnt");
		rowvalor.add("12");
		rowvalor.add("2D");
		rowvalor.add("D");
		gdValore.addRow(rowvalor);
		
		rowvalor = new ArrayList<String>();
		rowvalor.add("P.VENTA USD");
		rowvalor.add("prusdvnt");
		rowvalor.add("12");
		rowvalor.add("2D");
		rowvalor.add("D");
		gdValore.addRow(rowvalor);
		
		rowvalor = new ArrayList<String>();
		rowvalor.add("BOX");
		rowvalor.add("withboxx");
		rowvalor.add("5");
		rowvalor.add("S");
		rowvalor.add("C");
		gdValore.addRow(rowvalor);

		rowvalor = new ArrayList<String>();
		rowvalor.add("USB");
		rowvalor.add("withusbx");
		rowvalor.add("5");
		rowvalor.add("S");
		rowvalor.add("C");
		gdValore.addRow(rowvalor);
		
		rowvalor = new ArrayList<String>();
		rowvalor.add("CATEGORIA");
		rowvalor.add("idcatego");
		rowvalor.add("12");
		rowvalor.add("S");
		rowvalor.add("C");
		gdValore.addRow(rowvalor);
		
		rowvalor = new ArrayList<String>();
		rowvalor.add("TIPO");
		rowvalor.add("tpproduc");
		rowvalor.add("10");
		rowvalor.add("S");
		rowvalor.add("C");
		gdValore.addRow(rowvalor);
		
		
		Grid gdParame = new Grid();
		gdParame.addColumn("nomparam");
		gdParame.addColumn("valparam");
		
		ArrayList<String> rowparam = new ArrayList<String>();
		
		rowparam = new ArrayList<String>();
		rowparam.add("Fecha Asiento");
		rowparam.add("dd/mm/yyyy");
		gdParame.addRow(rowparam);
		
		
		ExcelGenericoSrv genExcelSrv = new ExcelGenericoSrv();
		ObjectIO inputXLS  = genExcelSrv.instanceOfInput();
		ObjectIO outputXLS = genExcelSrv.instanceOfOutput();
		
		try {
			inputXLS.setValue("username", "AUTOMAT");
			inputXLS.setValue("idemisor", idemisor);
			inputXLS.setValue("filename", filename);
			inputXLS.setValue("hojaname", "Chequeo OK");
			inputXLS.setValue("gdParame", gdParame);
			inputXLS.setValue("gdValore", gdValore);
			inputXLS.setValue("gdResult", gdChecOK);
			inputXLS.setValue("creador" , creador);
			genExcelSrv.setConnection(this.getConnection());
			genExcelSrv.process(inputXLS, outputXLS);
			filename = outputXLS.getStringValue("filecrea");
			creador  = (BasicExcel) outputXLS.getValue("creador");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void generacionExcelNoFisico(String idemisor, Grid gdNoSist) {
		
		Grid gdValore = new Grid();
		gdValore.addColumn("cabcampo");
		gdValore.addColumn("cdcampox");
		gdValore.addColumn("anchocam");
		gdValore.addColumn("tipocamp");
		gdValore.addColumn("alincamp");
		
		ArrayList<String> rowvalor = new ArrayList<String>();
		rowvalor.add("IMEICODE");
		rowvalor.add("imeicode");
		rowvalor.add("24");
		rowvalor.add("S");
		rowvalor.add("I");
		gdValore.addRow(rowvalor);

		rowvalor = new ArrayList<String>();
		rowvalor.add("COD.");
		rowvalor.add("codprodu");
		rowvalor.add("10");
		rowvalor.add("S");
		rowvalor.add("I");
		gdValore.addRow(rowvalor);
		
		rowvalor = new ArrayList<String>();
		rowvalor.add("MARCA");
		rowvalor.add("txmarcax");
		rowvalor.add("12");
		rowvalor.add("S");
		rowvalor.add("I");
		gdValore.addRow(rowvalor);
		
		rowvalor = new ArrayList<String>();
		rowvalor.add("MODELO");
		rowvalor.add("txmodelo");
		rowvalor.add("30");
		rowvalor.add("S");
		rowvalor.add("I");
		gdValore.addRow(rowvalor);
		
		rowvalor = new ArrayList<String>();
		rowvalor.add("COLOR");
		rowvalor.add("idcolorx");
		rowvalor.add("8");
		rowvalor.add("S");
		rowvalor.add("C");
		gdValore.addRow(rowvalor);

		rowvalor = new ArrayList<String>();
		rowvalor.add("P.COMPRA USD");
		rowvalor.add("prusdcmp");
		rowvalor.add("12");
		rowvalor.add("2D");
		rowvalor.add("D");
		gdValore.addRow(rowvalor);
		
		rowvalor = new ArrayList<String>();
		rowvalor.add("P.VENTA RD$");
		rowvalor.add("pricevnt");
		rowvalor.add("12");
		rowvalor.add("2D");
		rowvalor.add("D");
		gdValore.addRow(rowvalor);
		
		rowvalor = new ArrayList<String>();
		rowvalor.add("P.VENTA USD");
		rowvalor.add("prusdvnt");
		rowvalor.add("12");
		rowvalor.add("2D");
		rowvalor.add("D");
		gdValore.addRow(rowvalor);
		
		rowvalor = new ArrayList<String>();
		rowvalor.add("BOX");
		rowvalor.add("withboxx");
		rowvalor.add("5");
		rowvalor.add("S");
		rowvalor.add("C");
		gdValore.addRow(rowvalor);

		rowvalor = new ArrayList<String>();
		rowvalor.add("USB");
		rowvalor.add("withusbx");
		rowvalor.add("5");
		rowvalor.add("S");
		rowvalor.add("C");
		gdValore.addRow(rowvalor);
		
		rowvalor = new ArrayList<String>();
		rowvalor.add("CATEGORIA");
		rowvalor.add("idcatego");
		rowvalor.add("12");
		rowvalor.add("S");
		rowvalor.add("C");
		gdValore.addRow(rowvalor);
		
		rowvalor = new ArrayList<String>();
		rowvalor.add("TIPO");
		rowvalor.add("tpproduc");
		rowvalor.add("10");
		rowvalor.add("S");
		rowvalor.add("C");
		gdValore.addRow(rowvalor);
		
		
		Grid gdParame = new Grid();
		gdParame.addColumn("nomparam");
		gdParame.addColumn("valparam");
		
		ArrayList<String> rowparam = new ArrayList<String>();
		
		rowparam = new ArrayList<String>();
		rowparam.add("Fecha Asiento");
		rowparam.add("dd/mm/yyyy");
		gdParame.addRow(rowparam);
		
		
		ExcelGenericoSrv genExcelSrv = new ExcelGenericoSrv();
		ObjectIO inputXLS  = genExcelSrv.instanceOfInput();
		ObjectIO outputXLS = genExcelSrv.instanceOfOutput();
		
		try {
			inputXLS.setValue("username", "AUTOMAT");
			inputXLS.setValue("idemisor", idemisor);
			inputXLS.setValue("filename", filename);
			inputXLS.setValue("hojaname", "NO Fisico");
			inputXLS.setValue("gdParame", gdParame);
			inputXLS.setValue("gdValore", gdValore);
			inputXLS.setValue("gdResult", gdNoSist);
			inputXLS.setValue("creador" , creador);
			genExcelSrv.setConnection(this.getConnection());
			genExcelSrv.process(inputXLS, outputXLS);
			filename = outputXLS.getStringValue("filecrea");
			creador  = (BasicExcel) outputXLS.getValue("creador");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void generacionExcelNoSistem(String idemisor, Grid gdNoFisi) {
		
		Grid gdValore = new Grid();
		gdValore.addColumn("cabcampo");
		gdValore.addColumn("cdcampox");
		gdValore.addColumn("anchocam");
		gdValore.addColumn("tipocamp");
		gdValore.addColumn("alincamp");
		
		ArrayList<String> rowvalor = new ArrayList<String>();
		rowvalor.add("IMEICODE");
		rowvalor.add("imeicode");
		rowvalor.add("24");
		rowvalor.add("S");
		rowvalor.add("I");
		gdValore.addRow(rowvalor);
		
		Grid gdParame = new Grid();
		gdParame.addColumn("nomparam");
		gdParame.addColumn("valparam");
		
		ArrayList<String> rowparam = new ArrayList<String>();
		
		rowparam = new ArrayList<String>();
		rowparam.add("Fecha Asiento");
		rowparam.add("dd/mm/yyyy");
		gdParame.addRow(rowparam);
		
		
		ExcelGenericoSrv genExcelSrv = new ExcelGenericoSrv();
		ObjectIO inputXLS  = genExcelSrv.instanceOfInput();
		ObjectIO outputXLS = genExcelSrv.instanceOfOutput();
		
		try {
			inputXLS.setValue("username", "AUTOMAT");
			inputXLS.setValue("idemisor", idemisor);
			inputXLS.setValue("filename", filename);
			inputXLS.setValue("hojaname", "NO Sistema");
			inputXLS.setValue("gdParame", gdParame);
			inputXLS.setValue("gdValore", gdValore);
			inputXLS.setValue("gdResult", gdNoFisi);
			inputXLS.setValue("creador" , creador);
			genExcelSrv.setConnection(this.getConnection());
			genExcelSrv.process(inputXLS, outputXLS);
			filename = outputXLS.getStringValue("filecrea");
			creador  = (BasicExcel) outputXLS.getValue("creador");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
    
}
	