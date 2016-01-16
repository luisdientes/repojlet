package contabilidad;


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

public class AltaApunteSrv extends Service {

	public AltaApunteSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("idemisor");
			input.addVariable("txnombre");
			input.addVariable("idcuenta");
			input.addVariable("cantidad");
			input.addVariable("debhaber");
			input.addVariable("concepto");
			input.addVariable("fhapunte");
			input.addVariable("coddocum");
			input.addVariable("documint");
			input.addVariable("tpapunte");
			input.addVariable("numeroid");
			input.addVariable("cdpaisxx");
			input.addVariable("tipocuen");
			input.addVariable("idapunte");
			input.addVariable("filedocu");
			
			
			
			
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
			output.addVariable("idcuenta");
			output.addVariable("cantidad");
			output.addVariable("debhaber");
			output.addVariable("concepto");
			output.addVariable("fhapunte");
			output.addVariable("coddocum");
			output.addVariable("documint");
			output.addVariable("tpapunte");
			output.addVariable("numeroid");
			output.addVariable("txnombre");
			output.addVariable("cdpaisxx");
			output.addVariable("tipocuen");
			output.addVariable("txmensaj");
			output.addVariable("cddivisa");
			output.addVariable("filedocu");
			
			
			
			
	
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor = "";
    	String tpclient = "";
    	String idcuenta = "";
    	String txnombre = "";
    	String cdpaisxx = "";
    	String tipocuen = "";
    	String numeroid = "";
    	String debhaber = "";
    	String concepto = "";
    	String fhapunte = "";
    	String coddocum = "";
    	String tpapunte = "";
    	String cantidad = "";
    	String documint = "";
    	String cduserid = "";
    	String cddivisa = "";
    	String idapunte = "";
    	String calcimpt  ="";
    	String filedocu  = "";
    	double ultsaldo = 0;
    	double sumaapun = 0;
    	Grid gdCuentas = null;
    	Grid gridDivi = null;
    
    

        //Varibales de salida
    	
    	String txmensaj = "";
        //Otras Variables
        
        try {
        	
        	idemisor = input.getStringValue("idemisor");
        	idcuenta = input.getStringValue("idcuenta");
        	txnombre = input.getStringValue("txnombre");
        	cantidad = input.getStringValue("cantidad");
        	cdpaisxx = input.getStringValue("cdpaisxx");
        	tipocuen = input.getStringValue("tipocuen");
        	numeroid = input.getStringValue("numeroid");
        	debhaber = input.getStringValue("debhaber");
        	concepto = input.getStringValue("concepto");
        	fhapunte = fechaMySQL(input.getStringValue("fhapunte"));
        	coddocum = input.getStringValue("coddocum");
        	tpapunte = input.getStringValue("tpapunte");
        	idapunte = input.getStringValue("idapunte");
        	documint = input.getStringValue("documint");
        	filedocu = input.getStringValue("filedocu");
        	cduserid = (String) sesion.getAttribute("idusuari");
        	
        	
        	
        	
        	/*SACO LA CUENTA PARA SACAR EL SALDO*/
        	
        	
        	/**/
        	
        		if (idapunte != null && !idapunte.equals("")){
        			
        			UpdateApunteBDIn updApuntesBDIn = new UpdateApunteBDIn();
                	updApuntesBDIn.setValue("idemisor", idemisor);
                	updApuntesBDIn.setValue("idcuenta", idcuenta);
                	updApuntesBDIn.setValue("cantidad", cantidad);
                	updApuntesBDIn.setValue("debhaber", debhaber);
                	updApuntesBDIn.setValue("concepto", concepto);
                	updApuntesBDIn.setValue("fhapunte", fhapunte);
                	updApuntesBDIn.setValue("coddocum", coddocum);
                	updApuntesBDIn.setValue("tpapunte", tpapunte);
                	updApuntesBDIn.setValue("documint", documint);
                	updApuntesBDIn.setValue("idapunte", idapunte);
                	updApuntesBDIn.setValue("filedocu", filedocu);
                	
                	
                	UpdateApunteBD updApuntesBD = new UpdateApunteBD(updApuntesBDIn);
                	updApuntesBD.setConnection(con);
                	
                	try{
                		int exito = updApuntesBD.execUpdate();
                    	
                    	if(exito == 1){
                    		txmensaj = "Apunte modificado correctamente";
                    	}else{
                    		txmensaj = "Error al modificar apunte";
                    	}
                		
                	}catch(Exception ex){
                		txmensaj = "Error al modificar apunte";
                	}
        			
        		}else{
        			UpdateApunteBDIn updApuntesBDIn = new UpdateApunteBDIn();
                	updApuntesBDIn.setValue("idemisor", idemisor);
                	updApuntesBDIn.setValue("idcuenta", idcuenta);
                	updApuntesBDIn.setValue("cantidad", cantidad);
                	updApuntesBDIn.setValue("debhaber", debhaber);
                	updApuntesBDIn.setValue("concepto", concepto);
                	updApuntesBDIn.setValue("fhapunte", fhapunte);
                	updApuntesBDIn.setValue("coddocum", coddocum);
                	updApuntesBDIn.setValue("tpapunte", tpapunte);
                	updApuntesBDIn.setValue("documint", documint);
                	updApuntesBDIn.setValue("cduserid", cduserid);
                	updApuntesBDIn.setValue("filedocu", filedocu);
                	
                	
                	UpdateApunteBD updApuntesBD = new UpdateApunteBD(updApuntesBDIn);
                	updApuntesBD.setConnection(con);
                	
                	try{
                		int exito = updApuntesBD.execInsert();
                    	
                    	if(exito == 1){
                    		txmensaj = "Apunte dado de alta correctamente";
                    	}else{
                    		txmensaj = "Error al dar de alta apunte";
                    	}
                		
                	}catch(Exception ex){
                		txmensaj = "Error al dar de alta apunte";
                	}
        			
        		}
        		
        		/*COMPRUEBO SALDO Y ACTUALIZO*/
        		try {
        			ListCuentasBDIn listCuentasBDIn = new ListCuentasBDIn();
	        		listCuentasBDIn.setValue("idemisor", idemisor);
	        		listCuentasBDIn.setValue("idcuenta", idcuenta);
	        		ListCuentasBD listCuentasBD = new ListCuentasBD(listCuentasBDIn);
	        		listCuentasBD.setConnection(con);
	        		gdCuentas = listCuentasBD.execSelect();
	        		
	        		ultsaldo = Double.parseDouble(gdCuentas.getStringCell(0, "ultsaldo"));
	        		sumaapun = comprobarUltSaldo(idemisor,idcuenta);
	        		
	        		if(ultsaldo != sumaapun){
        				ListCuentasBDIn updCuentasBDIn = new ListCuentasBDIn();
    	        		updCuentasBDIn.setValue("idemisor", idemisor);
    	        		updCuentasBDIn.setValue("idcuenta", idcuenta);
    	        		updCuentasBDIn.setValue("ultsaldo", sumaapun);
    	        		ListCuentasBD updCuentasBD = new ListCuentasBD(updCuentasBDIn);
    	        		updCuentasBD.setConnection(con);
    	        		updCuentasBD.execUpdate();	
        			}
	        		
        		}catch(Exception ex){
        			
        		}
        	
        	
        	
       //// divisa
       			ListDivEmisorBDIn divisaBDIn= new ListDivEmisorBDIn();
       			divisaBDIn.setValue("idemisor", idemisor);
       			ListDivEmisorBD divisaBD = new ListDivEmisorBD(divisaBDIn);
       			divisaBD.setConnection(con);
       			gridDivi = divisaBD.execSelect();
       			cddivisa = gridDivi.getStringCell(0, "divsimbo");
        	
        	
        	
    
        	output.setValue("idemisor", idemisor);
        	output.setValue("idcuenta", idcuenta);
        	output.setValue("txnombre", txnombre);
        	output.setValue("cantidad", cantidad);	
        	output.setValue("cdpaisxx", cdpaisxx);
        	output.setValue("tipocuen", tipocuen);
        	output.setValue("numeroid", numeroid);
        	output.setValue("debhaber", debhaber);
        	output.setValue("concepto", concepto);
        	output.setValue("fhapunte", fhapunte);
        	output.setValue("coddocum", coddocum);
        	output.setValue("tpapunte", tpapunte);	
        	output.setValue("txmensaj", txmensaj);	
        	output.setValue("cddivisa", cddivisa);
        	
        	
        } catch (Exception e1) {
			e1.printStackTrace();
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
    
    
    
}
	