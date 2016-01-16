package personal;



import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import personal.bd.ListProyectEmpleBD;
import personal.bd.ListProyectEmpleBDIn;
import personal.bd.ListRangoFechasBD;
import personal.bd.ListRangoFechasBDIn;
import personal.bd.UpdDiasTraBD;
import personal.bd.UpdDiasTraBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;


public class ListFechasSelSrv extends Service {
	
 	//Varibales de entrada
    String tipodato = "";
    String fhfechax = ""; 
    String cduserid	= "";
    String tprolxxx = "";
    String[] fechas = null;
    String fechasSe = "";
    String horasSel = ""; 
	String proyecSe = "";
	String fhdesdex = ""; 
	String fhhastax = "";
    
    int npantall 	= 0;
    
    //Varibales de salida
    String cderror	= "";
    String txerror  = "";
    
    //Otras Variables
    Grid gridProy = null;
    Grid gFechasx = null;
    
	StringBuffer cadenaxx = new StringBuffer();
	
	

    public ListFechasSelSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();						 
			input.addVariable("tipoda");				 
			input.addVariable("cduserid");						 
			input.addVariable("fhfechax");
			input.addVariable("fechas");
			input.addVariable("horas");
        	input.addVariable("proyectos");
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
			output.addVariable("cduserid");
			output.addVariable("gridProy");
			output.addVariable("fhfechax");
			output.addVariable("gFechasx");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
        try{        	
     
        	//RECUPERO SESION y DATOS QUE ME INTERESAN
        	
        	cduserid = (String) sesion.getAttribute("idusuari");
        	tprolxxx = (String) sesion.getAttribute("tprolxxx");
        	
        	//RECUPERO LOS VALORES DEL INPUT
        	tipodato = input.getStringValue("tipoda");
        	fhfechax = input.getStringValue("fhfechax");
        	fechasSe = input.getStringValue("fechas");
        	horasSel = input.getStringValue("horas");
        	proyecSe = input.getStringValue("proyectos");
        	fhdesdex = input.getStringValue("fhdesdex");
        	fhhastax = input.getStringValue("fhhastax");
        
        
     
        	System.out.println("FLAG Tipo dato: - "+tipodato);

        	if (tipodato.equals("P")){
        		
        		muestraFechasSel(cduserid,output);
        	}
        	
        	if (tipodato.equals("DET")){
        		
        		if(!fhdesdex.equals("")){
        			fhdesdex = fechaMySQL(fhdesdex);
		    	}
		    	else{
		    		fhdesdex = "";
		    	}
		    	
		    	if(!fhhastax.equals("")){
		    		fhhastax = fechaMySQL(fhhastax);
		    	}
		    	else{
		    		fhhastax = "";
		    	}
        		
		    	muestraFiltroFec(cduserid,fhdesdex,fhhastax,output);
        	}
        	
        	if (tipodato.equals("I")){
        		insFechasSel(cduserid,fechasSe,horasSel,proyecSe,output);
        	}
          }catch (Exception e1) {
        	  e1.printStackTrace();
          }              
    }
    
    public void muestraFiltroFec(String iduserlo, String fechaDes, String fechaHas,ObjectIO output){
    	
    	try{
    		ListRangoFechasBDIn fhDatosBDIn = new ListRangoFechasBDIn();
    		fhDatosBDIn.setValue("cduserid",iduserlo);
    		fhDatosBDIn.setValue("fhdesdex",fhdesdex);
    		fhDatosBDIn.setValue("fhhastax",fhhastax);
    		ListRangoFechasBD fhDatosBD = new ListRangoFechasBD(fhDatosBDIn);
    		fhDatosBD.setConnection(con);
    		gFechasx = fhDatosBD.execSelect();
    		output.setGrid("gFechasx", gFechasx);
    	}catch(Exception ex){
    		System.out.println("------ERROR-------   Error al filtrar fechas");
    	}
    }
    
    public void muestraFechasSel(String cduserid, ObjectIO output){
    	
    	try{
    		
    		Grid fechaord = new Grid();
    		
	 		ListProyectEmpleBDIn ListBDIn = new ListProyectEmpleBDIn();
			ListBDIn.setValue("cduserid",cduserid);
			ListProyectEmpleBD ListBD = new ListProyectEmpleBD(ListBDIn);
			ListBD.setConnection(con);
			gridProy = ListBD.execSelect();
			
			fechas = fhfechax.split(",");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
		/* ordenamos fechas para mostrarlas*/
			
			fechaord.addColumn("fhfechax");
        	ArrayList<String> rowGrid = new ArrayList<String>();
			
			for (int i=0; i<(fechas.length); i++){
				for(int j=i+1;j<fechas.length;j++){
					Date fecha1 = sdf.parse(fechas[i] , new ParsePosition(0));
	                	Date fecha2 = sdf.parse(fechas[j] , new ParsePosition(0));
	                	
	                	
	                    if(fecha1.after(fecha2)){
	                        String fecAux=fechas[i];
	                        fechas[i]=fechas[j];
	                        fechas[j]=fecAux;
	                    }
	                }
	              	
					rowGrid.add(fechas[i]);
					fechaord.addRow(rowGrid);
	                cadenaxx.append(fechas[i]+",");
	               /* gFechasx.addRow(fechas[i]);
	                gFechasx.setCell(i, i, fechas[i]);
	               */
	                
	            	fhfechax = cadenaxx.toString();
	            	/*Establecemos variables de salida*/
	            	output.setValue("fhfechax", fhfechax);
	            	output.setValue("cduserid", cduserid);
	            	output.setGrid("gridProy", gridProy);
	            }
    	}catch(Exception ex){
    		System.out.println("Error al mostrar fechas seleccionadas");
    	}
    	
    }
    
    private void insFechasSel(String cduserid, String fecha, String horas, String proyec, ObjectIO output){
    	try{
    		
    		String[] fechaSe = fecha.split(",");
	    	String[] hora = horas.split(",");
	    	String[] proyectos = proyec.split(",");
    		
	    	for (int i=0; i< fechaSe.length;i++){
	    		
	    		fecha = fechaMySQL(fechaSe[i]);
	    		UpdDiasTraBDIn UpdDiasBDIn = new UpdDiasTraBDIn();
	    		UpdDiasBDIn.setValue("cduserid",cduserid);
	    		UpdDiasBDIn.setValue("fechasel",fecha);
	    		UpdDiasBDIn.setValue("horaselx",hora[i]);
	    		UpdDiasBDIn.setValue("proyecto",proyectos[i]);
	    		UpdDiasTraBD UpdDiasBD = new UpdDiasTraBD(UpdDiasBDIn);
	    		UpdDiasBD.setConnection(con);
	    		UpdDiasBD.execInsert();
	    	}	
	    }catch(Exception ex){
    		System.out.println("Error al insertar las fechas del trabajador");
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
	