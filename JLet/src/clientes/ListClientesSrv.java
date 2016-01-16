package clientes;


import java.util.ArrayList;

import clientes.bd.FacturasClientesBD;
import clientes.bd.FacturasClientesBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import common.bd.ListClientesBD;
import common.bd.ListClientesBDIn;
import common.constructors.xls.BasicExcel;
import common.constructors.xls.ExcelGenericoSrv;
import common.varstatic.VariablesStatic;



public class ListClientesSrv extends Service {

	
	  BasicExcel creador = null;
	  String filename = "";
	  String tpclient = ""; 
	
	public ListClientesSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			 input.addVariable("idemisor");
			 input.addVariable("tpclient");
			 input.addVariable("idclient");
			 input.addVariable("txrazons");
			 input.addVariable("asignafa");
			 input.addVariable("clientea");
			 
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
			output.addVariable("tpclient");
			output.addVariable("txrazons");
			output.addVariable("gdClient");
			output.addVariable("txmensaj");
			output.addVariable("factasoc");
			output.addVariable("clientea");
			output.addVariable("asignafa");
			output.addVariable("filename");
			
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor = "";
    	
    	String idclient = ""; 
    	String txrazons = "";
    	String factasoc = "0";
    	String asignafa = "";
    	String clientea = "";
        //Varibales de salida
    	Grid gdClient = null;
    	Grid gdFactur = null;
        
        //Otras Variables
        
        
        try {

        	idemisor = input.getStringValue("idemisor");
        	tpclient = input.getStringValue("tpclient");
        	idclient = input.getStringValue("idclient");
        	txrazons = input.getStringValue("txrazons"); 
        	asignafa = input.getStringValue("asignafa");
        	clientea = input.getStringValue("clientea");
        		
       		ListClientesBDIn listCliBDIn = new ListClientesBDIn();
       		listCliBDIn.setValue("idemisor",idemisor);
       		listCliBDIn.setValue("tpclient",tpclient);
       		listCliBDIn.setValue("idclient",idclient);
       		listCliBDIn.setValue("rzsocial",txrazons);
       		ListClientesBD listCliBD = new ListClientesBD(listCliBDIn);
       		listCliBD.setConnection(con);
        			
       		gdClient = listCliBD.execSelect();
       		
       		
       		FacturasClientesBDIn factCliBDIn = new FacturasClientesBDIn();
       		factCliBDIn.setValue("idemisor",idemisor);
       		factCliBDIn.setValue("tpclient",tpclient);
       		factCliBDIn.setValue("idclient",idclient);
       		FacturasClientesBD factCliBD = new FacturasClientesBD(factCliBDIn);
       		factCliBD.setConnection(con);
       		
       		gdFactur = factCliBD.execSelect();
       		
       		if(gdFactur.rowCount()>0){
       			factasoc = "1";
       		}else{
       			factasoc = "0";
       		}
       		
       		generacionExcel(idemisor, gdClient);
        			
        	output.setValue("idemisor", idemisor);
        	output.setValue("tpclient", tpclient);
        	output.setValue("gdClient", gdClient);
        	output.setValue("factasoc", factasoc);
        	output.setValue("asignafa", asignafa);
        	output.setValue("clientea", clientea);
        	output.setValue("filename", filename);
        	
        	output.setValue("txmensaj", "OK");
        
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
 public void generacionExcel(String idemisor, Grid gdResult) {
	 
    	
        Grid gdValore = new Grid();
        gdValore.addColumn("cabcampo");
        gdValore.addColumn("cdcampox");
        gdValore.addColumn("anchocam");
        gdValore.addColumn("tipocamp");
        gdValore.addColumn("alincamp");
       
        ArrayList<String> rowvalor = new ArrayList<String>();
        rowvalor.add("ID CLIENTE");
        rowvalor.add("idclient");
        rowvalor.add("13");
        rowvalor.add("S");
        rowvalor.add("I");
        gdValore.addRow(rowvalor);
       
        rowvalor = new ArrayList();
        rowvalor.add("NOMBRE O RAZON SOCIAL");
        rowvalor.add("rzsocial");
        rowvalor.add("45");
        rowvalor.add("S");
        rowvalor.add("I");
        gdValore.addRow(rowvalor);
       
        
        rowvalor = new ArrayList();
        rowvalor.add("NIF / CIF");
        rowvalor.add("idfiscal");
        rowvalor.add("15");
        rowvalor.add("S");
        rowvalor.add("C");
        gdValore.addRow(rowvalor);
        
       
        
        rowvalor = new ArrayList();
        rowvalor.add("DIRECCION");
        rowvalor.add("txdirecc");
        rowvalor.add("40");
        rowvalor.add("S");
        rowvalor.add("I");
        gdValore.addRow(rowvalor);
        
        rowvalor = new ArrayList();
        rowvalor.add("CIUDAD");
        rowvalor.add("txciudad");
        rowvalor.add("35");
        rowvalor.add("S");
        rowvalor.add("I");
        gdValore.addRow(rowvalor);
        
        rowvalor = new ArrayList();
        rowvalor.add("COD. POSTAL");
        rowvalor.add("cdpostal");
        rowvalor.add("15");
        rowvalor.add("S");
        rowvalor.add("D");
        gdValore.addRow(rowvalor);
        
        rowvalor = new ArrayList();
        rowvalor.add("PAIS");
        rowvalor.add("txpaisxx");
        rowvalor.add("25");
        rowvalor.add("S");
        rowvalor.add("I");
        gdValore.addRow(rowvalor);
        
        rowvalor = new ArrayList();
        rowvalor.add("PROVINCIA");
        rowvalor.add("txprovin");
        rowvalor.add("25");
        rowvalor.add("S");
        rowvalor.add("I");
        
        gdValore.addRow(rowvalor);
        rowvalor = new ArrayList();
        rowvalor.add("MAIL");
        rowvalor.add("txmailxx");
        rowvalor.add("35");
        rowvalor.add("S");
        rowvalor.add("I");
        gdValore.addRow(rowvalor);
        
        rowvalor = new ArrayList();
        rowvalor.add("TELF. FIJO");
        rowvalor.add("tfnofijo");
        rowvalor.add("15");
        rowvalor.add("S");
        rowvalor.add("C");
        gdValore.addRow(rowvalor);
        
      
        rowvalor = new ArrayList();
        rowvalor.add("TELF. MOVIL");
        rowvalor.add("tfnomovi");
        rowvalor.add("15");
        rowvalor.add("S");
        rowvalor.add("C");
        gdValore.addRow(rowvalor);
        
      
        rowvalor = new ArrayList();
        rowvalor.add("FAX");
        rowvalor.add("tfnofaxx");
        rowvalor.add("15");
        rowvalor.add("S");
        rowvalor.add("C");
        gdValore.addRow(rowvalor);
 
       
        String txemisor = VariablesStatic.getEmisor(idemisor, tpclient);
        
        Grid gdParame = new Grid();
        gdParame.addColumn("nomparam");
        gdParame.addColumn("valparam");
        ArrayList<String> rowparam = new ArrayList<String>();
        rowparam = new ArrayList();
        rowparam.add("Fecha Asiento");
        rowparam.add("dd/mm/yyyy");
        gdParame.addRow(rowparam);
        rowparam = new ArrayList();
        rowparam.add("Empresa");
        rowparam.add(txemisor);
        gdParame.addRow(rowparam);
        
        ExcelGenericoSrv genExcelSrv = new ExcelGenericoSrv();
        ObjectIO inputXLS = genExcelSrv.instanceOfInput();
        ObjectIO outputXLS = genExcelSrv.instanceOfOutput();
        try {
            inputXLS.setValue("username", "CLIENTES");
            inputXLS.setValue("idemisor", idemisor);
            inputXLS.setValue("filename", filename);
            inputXLS.setValue("hojaname","Listado Clientes");
            inputXLS.setValue("gdParame", gdParame);
            inputXLS.setValue("gdValore", gdValore);
            inputXLS.setValue("gdResult", gdResult);
            inputXLS.setValue("creador", creador);
            genExcelSrv.setConnection(this.getConnection());
            genExcelSrv.process(inputXLS, outputXLS);
            filename = outputXLS.getStringValue("filecrea");
            creador =  (BasicExcel) outputXLS.getValue("creador");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
	