package upgrade;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import stock.bd.ListStockBD;
import stock.bd.ListStockBDIn;
import upgrade.bd.InsProdPiezaBD;
import upgrade.bd.InsProdPiezaBDIn;
import upgrade.bd.ListStockPiezasBD;
import upgrade.bd.ListStockPiezasBDIn;
import upgrade.bd.UpdStockBD;
import upgrade.bd.UpdStockBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import common.bd.ListClientesBD;
import common.bd.ListClientesBDIn;
import factura.bd.UpdLineasBD;


public class InsPiezasProdSrv extends Service {

    public InsPiezasProdSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();	
			input.addVariable("idemisor");
			input.addVariable("listimei");
			input.addVariable("claseant");
			input.addVariable("idgrupox");
			input.addVariable("tipocons");
			input.addVariable("idclient");
			input.addVariable("tpclient");
			input.addVariable("imeiprod");
			input.addVariable("horacomi");
			
 
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
			output.addVariable("claseant");
			output.addVariable("tpclient");
			output.addVariable("horacomi");
			output.addVariable("idemisor");
			output.addVariable("imeicode");
			output.addVariable("horacomp");
			output.addVariable("gridLine");
			output.addVariable("gridPiez");
			output.addVariable("grPrAgru");
			
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor = null;
    	String idgrupox = null;
    	String listimei = null;
    	String idclient = null;
    	String tpclient = null;
    	String logoemis = null;
    	String imeiprod = null;
    	String claseant = null;
    	String horacomi = null;
    	
    	String [] arrImeis = null;
    	String [] horasCom = null;
	     int numeroIme = 0;
	     int totalinse = 0;
	    
        
        //Varibales de salida
    	Grid gridProd = null;
    	Grid grPrAgru = null; 
    	Grid gridLine = null;
    	Grid gridPiez = null;
    	
        try {
        	
        	idemisor = input.getStringValue("idemisor");
        	idgrupox = input.getStringValue("idgrupox");
        	listimei = input.getStringValue("listimei");
        	imeiprod = input.getStringValue("imeiprod");
        	claseant = input.getStringValue("claseant");
        	horacomi = input.getStringValue("horacomi");
        	tpclient = input.getStringValue("tpclient");
        	
        	horasCom = horacomi.split(":");
        	
        	arrImeis = listimei.split(";");
    	    numeroIme = arrImeis.length;
    	    
    		int horarepa = 0;
        	int minutosc = 0;
        	
        	int horacore = 0;
        	int mincomre = 0;
        	
        	int horafinxx = 0;
        	int minutofin = 0;
        	
        	
        	
        	String horacomp = "";
        	String strHoraf = "";
        	String strMinut = "";
        	String codPieza ="";
        

	        try {

	        	for(int i=0;i<arrImeis.length;i++){
	        		InsProdPiezaBDIn insProPiezBDIn = new InsProdPiezaBDIn();
	        		insProPiezBDIn.setValue("imeipiez", arrImeis[i]);
	        		insProPiezBDIn.setValue("imeiprod", imeiprod);
	        		InsProdPiezaBD insLinBD = new InsProdPiezaBD(insProPiezBDIn);
					insLinBD.setConnection(con);
					insLinBD.execInsert();
					
					
					codPieza = arrImeis[i].substring(0,8);
					if(codPieza.equals("PI001231")){
						UpdStockBDIn updStockBDIn = new UpdStockBDIn();
						updStockBDIn.setValue("idemisor",idemisor);
						updStockBDIn.setValue("imeicode",imeiprod);
						updStockBDIn.setValue("idcatego",claseant);
						updStockBDIn.setValue("withboxx","S");
						UpdStockBD updStockBD = new UpdStockBD(updStockBDIn);
						updStockBD.setConnection(con);
						updStockBD.execUpdate();
					}
					
					if(codPieza.equals("PI001242")){
						UpdStockBDIn updStockBDIn = new UpdStockBDIn();
						updStockBDIn.setValue("idemisor",idemisor);
						updStockBDIn.setValue("imeicode",imeiprod);
						updStockBDIn.setValue("idcatego",claseant);
						updStockBDIn.setValue("withheph","S");
						UpdStockBD updStockBD = new UpdStockBD(updStockBDIn);
						updStockBD.setConnection(con);
						updStockBD.execUpdate();
					}
					
					if(codPieza.equals("PI001153")){
						UpdStockBDIn updStockBDIn = new UpdStockBDIn();
						updStockBDIn.setValue("idemisor",idemisor);
						updStockBDIn.setValue("imeicode",imeiprod);
						updStockBDIn.setValue("idcatego",claseant);
						updStockBDIn.setValue("withchar","S");
						UpdStockBD updStockBD = new UpdStockBD(updStockBDIn);
						updStockBD.setConnection(con);
						updStockBD.execUpdate();
					}
					
					if(codPieza.equals("PI001314")){
						UpdStockBDIn updStockBDIn = new UpdStockBDIn();
						updStockBDIn.setValue("idemisor",idemisor);
						updStockBDIn.setValue("imeicode",imeiprod);
						updStockBDIn.setValue("idcatego",claseant);
						updStockBDIn.setValue("withusbx","S");
						UpdStockBD updStockBD = new UpdStockBD(updStockBDIn);
						updStockBD.setConnection(con);
						updStockBD.execUpdate();
					}
					
					if(codPieza.equals("PI001229")){
						UpdStockBDIn updStockBDIn = new UpdStockBDIn();
						updStockBDIn.setValue("idemisor",idemisor);
						updStockBDIn.setValue("imeicode",imeiprod);
						updStockBDIn.setValue("idcatego",claseant);
						updStockBDIn.setValue("withadap","S");
						UpdStockBD updStockBD = new UpdStockBD(updStockBDIn);
						updStockBD.setConnection(con);
						updStockBD.execUpdate();
					}
		
					
					try{
						UpdStockBDIn updStockBDIn = new UpdStockBDIn();
						updStockBDIn.setValue("idemisor",idemisor);
						updStockBDIn.setValue("imeicode",arrImeis[i]);
						updStockBDIn.setValue("cdestado","CONSINT");
						updStockBDIn.setValue("idcatego",claseant);
						UpdStockBD updStockBD = new UpdStockBD(updStockBDIn);
						updStockBD.setConnection(con);
						updStockBD.execUpdate();
						
					}catch(Exception ex){
						System.out.println("Error update stoc piezasssss  -->stock");
					}
	        	}
	        	
	        	
	        	InsProdPiezaBDIn insProPiezBDIn = new InsProdPiezaBDIn();
        		insProPiezBDIn.setValue("imeiprod", imeiprod);
        		insProPiezBDIn.setValue("cdestado","CONSINT");
        		InsProdPiezaBD insLinBD = new InsProdPiezaBD(insProPiezBDIn);
				insLinBD.setConnection(con);
				gridPiez = insLinBD.execSelect();

				Calendar calendario = new GregorianCalendar();
				
				horarepa = calendario.get(Calendar.HOUR_OF_DAY);
				minutosc = calendario.get(Calendar.MINUTE);
				
				horacore = Integer.parseInt(horasCom[0]);
	        	mincomre = Integer.parseInt(horasCom[1]);
	        	
	        	horafinxx = horarepa - horacore;
	        	minutofin = minutosc - mincomre;
	        	
	        	if (minutofin <0){
	        		minutofin+=60;
	        		horafinxx-=1;
	        		
	        		strHoraf = Integer.toString(horafinxx);
	        		strMinut = Integer.toString(minutofin); 
	        	}
	        	
	        	if(horafinxx<10){
	        		if(strHoraf.equals("")){
	        			strHoraf ="0";
	        		}
	        		strHoraf="0"+strHoraf;
	        	}
	        	
	        	if(minutofin<10){
	        		strMinut="0"+minutofin;
	        	}
				
	        	horacomp = strHoraf +":"+strMinut;
	        	
	        	
	        	if(imeiprod !=null && !imeiprod.equals("")){
	            	
	        		UpdStockBDIn selStockBDIn = new UpdStockBDIn();
	        		selStockBDIn.setValue("idemisor",idemisor);
	        		selStockBDIn.setValue("imeicode",imeiprod);
	        		selStockBDIn.setValue("cdestado","STOCK");
					UpdStockBD lineasBD = new UpdStockBD(selStockBDIn);
					lineasBD.setConnection(con);
					gridLine = lineasBD.execSelect();
	        	}
	       

	        } catch (Exception e) {
				System.err.println(this.getClass().getName() +" ERROR recogiendo productos. "+ e.getMessage());
			}
	        
	        output.setValue("imeicode", imeiprod);
        	output.setValue("idemisor", idemisor);
        	output.setValue("horacomp", horacomp);
        	output.setValue("claseant", claseant);
        	output.setValue("tpclient", tpclient);
        	output.setValue("gridLine", gridLine);
		    output.setValue("gridPiez", gridPiez);
		    
		    output.setValue("txmensaj", "OK");

        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
}
	