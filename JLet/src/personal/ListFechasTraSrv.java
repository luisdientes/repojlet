package personal;



import personal.bd.ListEmpleadosBD;
import personal.bd.ListEmpleadosBDIn;
import personal.bd.ListRangoFechasBD;
import personal.bd.ListRangoFechasBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;


public class ListFechasTraSrv extends Service {
	
 	//Varibales de entrada
    String cduserid	= "";
    String idemplea	= "";
    
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
	
	

    public ListFechasTraSrv() {
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
        	input.addVariable("idemplea");
						
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
			output.addVariable("gdEmplea");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
        try{        	
     
        	//RECUPERO SESION y DATOS QUE ME INTERESAN
        	
        	idemplea = input.getStringValue("idemplea");
        	
        	if(idemplea!=null && !idemplea.equals("")){
        		cduserid = idemplea;
        	}else{
        		cduserid = (String) sesion.getAttribute("idusuari");
        	}
        	      	
        	//RECUPERO LOS VALORES DEL INPUT
        
        	fhdesdex = input.getStringValue("fhdesdex");
        	fhhastax = input.getStringValue("fhhastax");
        	
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
    		
    		
    		ListEmpleadosBDIn ListEmpleBDIn = new ListEmpleadosBDIn();
    		ListEmpleBDIn.setValue("cduserid", iduserlo);
    		ListEmpleadosBD ListEmpleBD = new ListEmpleadosBD(ListEmpleBDIn);
    		ListEmpleBD.setConnection(con);
    		Grid gdEmplea = ListEmpleBD.execSelect();

    		output.setGrid("gFechasx", gFechasx);
    		output.setGrid("gdEmplea", gdEmplea);
    	}catch(Exception ex){
    		System.out.println("------ERROR-------   Error al filtrar fechas");
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
	