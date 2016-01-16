package contabilidad;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import common.constructors.xls.BasicExcel;
import common.constructors.xls.ExcelContabilidadSrv;
import common.varstatic.VariablesStatic;
import common.bd.ListDivEmisorBD;
import common.bd.ListDivEmisorBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import contabilidad.bd.CompUltSaldoBD;
import contabilidad.bd.CompUltSaldoBDIn;
import contabilidad.bd.ListCuentasBD;
import contabilidad.bd.ListCuentasBDIn;
import contabilidad.bd.UpdateApunteBD;
import contabilidad.bd.UpdateApunteBDIn;

public class ListApuntesSrv extends Service {
	
	  BasicExcel creador = null;
	  String filename = "";
	  String txcuenta = "";
	  String numeroid = "";

	public ListApuntesSrv() {
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
			output.addVariable("gdCuentas");
			output.addVariable("gdApuntes");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor 	= "";
    	String idcuenta 	= "";
    	String cddivisa 	= "";
    	String fhdesdex		= "";
    	String fhhastax		= "";
    	String fhdesmys 	= "";
    	String fhhasmys 	= "";
    	String idapunte = "";
    	
    	double ultsaldo = 0;
    	double sumaapun = 0;
    

        //Varibales de salida
    	Grid gdCuentas 	= null;
    	Grid gdApuntes 	= null;
    	Grid gridDivi = null;
        
        //Otras Variables
        
        try {
        	
        	idemisor = input.getStringValue("idemisor");
        	idcuenta = input.getStringValue("idcuenta");
        	idapunte = input.getStringValue("idapunte");
        	fhdesdex = input.getStringValue("fhdesdex");
        	fhhastax = input.getStringValue("fhhastax");
        	try {
        		
        		sumaapun = comprobarUltSaldo(idemisor,idcuenta);
        		
        		ListCuentasBDIn listCuentasBDIn = new ListCuentasBDIn();
        		listCuentasBDIn.setValue("idemisor", idemisor);
        		listCuentasBDIn.setValue("idcuenta", idcuenta);
        		ListCuentasBD listCuentasBD = new ListCuentasBD(listCuentasBDIn);
        		listCuentasBD.setConnection(con);
        		gdCuentas = listCuentasBD.execSelect();
        		
        		txcuenta = gdCuentas.getStringCell(0, "txnombre");
        		numeroid = gdCuentas.getStringCell(0, "numeroid");
        		
        		try{
        			ultsaldo = Double.parseDouble(gdCuentas.getStringCell(0, "ultsaldo"));
        			
        			if(ultsaldo != sumaapun){
        				
        				ListCuentasBDIn updCuentasBDIn = new ListCuentasBDIn();
    	        		updCuentasBDIn.setValue("idemisor", idemisor);
    	        		updCuentasBDIn.setValue("idcuenta", idcuenta);
    	        		updCuentasBDIn.setValue("ultsaldo", sumaapun);
    	        		ListCuentasBD updCuentasBD = new ListCuentasBD(updCuentasBDIn);
    	        		updCuentasBD.setConnection(con);
    	        		updCuentasBD.execUpdate();	
        			}
        			
        		}catch (Exception ex){
        			System.out.println("Error al comprobar ultimo saldo");
        		}
        		
        		
        		if(fhdesdex != null && !fhdesdex.equals("")){
        			fhdesmys = fechaMySQL(fhdesdex);
        		}else{
        			if (fhdesdex == null){
        				fhdesdex = FechaMesAnterior();
        				fhdesmys = fechaMySQL(fhdesdex);
        			}else{
        				fhdesdex = "";
        			}
        		}
        		
        		if(fhhastax != null && !fhhastax.equals("")){
        			fhhasmys = fechaMySQL(fhhastax);
        		}else{
        			fhhastax = "";
        		}
        		
     
        		
        		
        		UpdateApunteBDIn updApuntesBDIn = new UpdateApunteBDIn();
            	updApuntesBDIn.setValue("idemisor", idemisor);
            	updApuntesBDIn.setValue("idcuenta", idcuenta);
            	updApuntesBDIn.setValue("idapunte", idapunte);
            	
            	if(idapunte == null || idapunte.equals("")){
            		updApuntesBDIn.setValue("fhdesdex", fhdesmys);
                	updApuntesBDIn.setValue("fhhastax", fhhasmys);
            		
            	}
            	
            	
            	
            	UpdateApunteBD updApuntesBD = new UpdateApunteBD(updApuntesBDIn);
            	updApuntesBD.setConnection(con);
            	gdApuntes = updApuntesBD.execSelect();
            	
            	ListDivEmisorBDIn divisaBDIn= new ListDivEmisorBDIn();
    			divisaBDIn.setValue("idemisor", idemisor);
    			ListDivEmisorBD divisaBD = new ListDivEmisorBD(divisaBDIn);
    			divisaBD.setConnection(con);
    			gridDivi = divisaBD.execSelect();
    			cddivisa = gridDivi.getStringCell(0, "divsimbo");
    			
    			
    			generacionExcel(idemisor, gdApuntes);
            	
				
        	} catch (Exception e) {
        		System.err.println(this.getClass().getName() +" [ERROR ] Recuperando subastas. "+ e.getMessage());
        	}
        	
        	output.setValue("idemisor", idemisor);
        	output.setValue("cddivisa", cddivisa);
        	output.setValue("idapunte", idapunte);
        	output.setValue("ultsaldo", sumaapun);
        	output.setValue("fhdesdex", fhdesdex);
        	output.setValue("fhhastax", fhhastax);
        	output.setValue("filename", filename);
        	output.setValue("gdCuentas", gdCuentas);
        	output.setValue("gdApuntes", gdApuntes);
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
    public double comprobarUltSaldo(String idemisor, String idcuenta){
    	
    	Grid gdSaldox = null;
    	double ultsaldo = 0;
    	
    	try{
    	
	    	CompUltSaldoBDIn comSaldoBDIn = new CompUltSaldoBDIn();
	    	comSaldoBDIn.setValue("idemisor", idemisor);
	    	comSaldoBDIn.setValue("idcuenta", idcuenta);
	    	CompUltSaldoBD comSaldoBD = new CompUltSaldoBD(comSaldoBDIn);
	    	comSaldoBD.setConnection(con);
	    	gdSaldox = comSaldoBD.execSelect();
	    	
	    	ultsaldo = Double.parseDouble(gdSaldox.getStringCell(0, "ultsaldo"));
	    	
    	}catch(Exception ex){
    		System.out.println("Error al recuperar Ultimo Saldo");
    	}
    	
    	return ultsaldo;
    	
    }
    

    public void generacionExcel(String idemisor, Grid gdApunte) {
    	
    	
    	
        Grid gdValore = new Grid();
        gdValore.addColumn("cabcampo");
        gdValore.addColumn("cdcampox");
        gdValore.addColumn("anchocam");
        gdValore.addColumn("tipocamp");
        gdValore.addColumn("alincamp");
       
        ArrayList<String> rowvalor = new ArrayList<String>();
        rowvalor.add("IDAPUNTE");
        rowvalor.add("idapunte");
        rowvalor.add("24");
        rowvalor.add("S");
        rowvalor.add("I");
        gdValore.addRow(rowvalor);
       /*
        rowvalor = new ArrayList();
        rowvalor.add("EMISOR");
        rowvalor.add("idemisor");
        rowvalor.add("10");
        rowvalor.add("S");
        rowvalor.add("I");
        gdValore.addRow(rowvalor);*/
       
        
        rowvalor = new ArrayList();
        rowvalor.add("CUENTA");
        rowvalor.add("idcuenta");
        rowvalor.add("30");
        rowvalor.add("S");
        rowvalor.add("I");
        gdValore.addRow(rowvalor);
        
        rowvalor = new ArrayList();
        rowvalor.add("DEBE");
        rowvalor.add("debhaber");
        rowvalor.add("15");
        rowvalor.add("S");
        rowvalor.add("C");
        gdValore.addRow(rowvalor);
        
       
        
        rowvalor = new ArrayList();
        rowvalor.add("HABER");
        rowvalor.add("-");
        rowvalor.add("15");
        rowvalor.add("S");
        rowvalor.add("C");
        gdValore.addRow(rowvalor);
        
        rowvalor = new ArrayList();
        rowvalor.add("CONCEPTO");
        rowvalor.add("concepto");
        rowvalor.add("50");
        rowvalor.add("S");
        rowvalor.add("I");
        gdValore.addRow(rowvalor);
        
        rowvalor = new ArrayList();
        rowvalor.add("IMPORTE");
        rowvalor.add("cantidad");
        rowvalor.add("14");
        rowvalor.add("2D");
        rowvalor.add("D");
        gdValore.addRow(rowvalor);
        
        rowvalor = new ArrayList();
        rowvalor.add("COD DOCUMENTO");
        rowvalor.add("coddocum");
        rowvalor.add("25");
        rowvalor.add("S");
        rowvalor.add("I");
        
        gdValore.addRow(rowvalor);
        rowvalor = new ArrayList();
        rowvalor.add("TIPO");
        rowvalor.add("tpapunte");
        rowvalor.add("5");
        rowvalor.add("S");
        rowvalor.add("C");
        
        gdValore.addRow(rowvalor);
        rowvalor = new ArrayList();
        rowvalor.add("Fecha");
        rowvalor.add("fhapunte");
        rowvalor.add("15");
        rowvalor.add("S");
        rowvalor.add("C");
        gdValore.addRow(rowvalor);
       
        
        String txemisor = VariablesStatic.getEmisor(idemisor, "0");
        
        Grid gdParame = new Grid();
        gdParame.addColumn("nomparam");
        gdParame.addColumn("valparam");
        ArrayList<String> rowparam = new ArrayList<String>();
        rowparam.add("Empresa");
        rowparam.add(txemisor);
        gdParame.addRow(rowparam);
        rowparam = new ArrayList();
        rowparam.add("Cuenta");
        rowparam.add(txcuenta);
        gdParame.addRow(rowparam);
        
        rowparam = new ArrayList();
        rowparam.add("Numero ID");
        rowparam.add(numeroid);
        gdParame.addRow(rowparam);
        
        
        
        ExcelContabilidadSrv genExcelSrv = new ExcelContabilidadSrv();
        ObjectIO inputXLS = genExcelSrv.instanceOfInput();
        ObjectIO outputXLS = genExcelSrv.instanceOfOutput();
        try {
            inputXLS.setValue("username", "APUNTE");
            inputXLS.setValue("idemisor", idemisor);
            inputXLS.setValue("filename", filename);
            inputXLS.setValue("hojaname","Listado apuntes");
            inputXLS.setValue("gdParame", gdParame);
            inputXLS.setValue("gdValore", gdValore);
            inputXLS.setValue("gdResult", gdApunte);
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
	