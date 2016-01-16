package gestion.administracion;


import gestion.administracion.bd.ListEmpleadosBD;
import gestion.administracion.bd.ListEmpleadosBDIn;
import gestion.administracion.bd.ListFechasBD;
import gestion.administracion.bd.ListFechasBDIn;
import gestion.administracion.bd.ListProyectBD;
import gestion.administracion.bd.ListProyectBDIn;
import gestion.administracion.bd.ListProyectEmpleBD;
import gestion.administracion.bd.ListProyectEmpleBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;


public class ListAccesosSrv extends Service {
	
	
	

    public ListAccesosSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();						 
			input.addVariable("tipoda");				 
			input.addVariable("cduserid");						 
			input.addVariable("fhpulsad");					 
			input.addVariable("fhdesdex");					 
			input.addVariable("fhhastax");
			input.addVariable("idproyec");
						
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
			output.addVariable("gridFech");
			output.addVariable("fhpulsad");
			output.addVariable("gridProy");
			output.addVariable("gridEmpl");
			output.addVariable("gridProT");
			output.addVariable("idempres");
			output.addVariable("tprolxxx");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
        String tipodato  = "";
        String fhpulsad  = "";
        String fhdesdex  = "";
        String fhhastax  = "";
        String txmesxxx  = "";
        String txanioxx  = "";
        String idproyec	=  "";
        Grid gridFech = null;
        Grid gridProy = null;
        Grid gAccesos = null;
        Grid gridEmpl = null;
        Grid gridProT = null;
        
        String cduserid = "";
        
        String tprolxxx = "";
        
        int npantall 	 = 0;
        
        //Varibales de salida
        String cderror   = "";
        String txerror   = "";
        
        //Otras Variables
       
        
        try{        	
        	
        	//RECUPERO SESION y DATOS QUE ME INTERESAN
        	
        	cduserid = (String) sesion.getAttribute("idusuari");
        	tprolxxx = (String) sesion.getAttribute("tprolxxx");
        	
        	//RECUPERO LOS VALORES DEL INPUT
        	tipodato = input.getStringValue("tipoda");
        	cduserid = input.getStringValue("cduserid");
        	idproyec = input.getStringValue("idproyec");
        	fhpulsad = input.getStringValue("fhpulsad");
        	fhdesdex = input.getStringValue("fhdesdex");
        	fhhastax = input.getStringValue("fhhastax");
        	/*txmesxxx = input.getStringValue("txmesxxx");
        	txanioxx = input.getStringValue("txanioxx");*/
        	
        	System.out.println("FLAG Tipo dato: - "+tipodato);

        	if (tipodato.equals("F")){
        		ListFechasBDIn fhDatosBDIn = new ListFechasBDIn();
        		fhDatosBDIn.setValue("cduserid",cduserid);
        		ListFechasBD fhDatosBD = new ListFechasBD(fhDatosBDIn);
        		fhDatosBD.setConnection(con);
        		gridFech = fhDatosBD.execSelect();
        	}
        	
        	if(tipodato.equals("LP")){
        	
        		gridProy = ListProyect();
   
        	}
        	
        	
        	/*Listado de empleados*/
        	if(tipodato.equals("LE")){
        		
        		ListEmpleadosBDIn listEmpBDIn = new ListEmpleadosBDIn();
        		listEmpBDIn.setValue("cduserid",cduserid);
        		ListEmpleadosBD listEmpBD = new ListEmpleadosBD(listEmpBDIn);
        		listEmpBD.setConnection(con);
        		gridEmpl = listEmpBD.execSelect();
        	}
        	
        	
        	/*Detalle Empleados*/
        	if(tipodato.equals("DET")){
        		
        		gridProT = ListProyect(cduserid);
        		gridEmpl = DetalleUsu(cduserid);
        		gridProy = ListProyect();
        	}
        	
        	
        	/*Detalle Proyecto*/
        	if(tipodato.equals("DP")){

        		gridProy = DetalleProy(idproyec);
        	}
        	
        	
        /*	output.setValue("txmesxxx", txmesxxx);
        	output.setValue("txanioxx", txanioxx);*/
        	output.setValue("fhpulsad", fhpulsad);
        	output.setValue("cduserid", cduserid);
        	output.setValue("tprolxxx", tprolxxx);
        	output.setGrid("gridFech", gridFech);
        	output.setGrid("gridProy", gridProy);
        	output.setGrid("gridEmpl", gridEmpl);
        	output.setGrid("gridProT", gridProT);
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
    
    private Grid ListProyect( String cduserid) throws Exception{
    	
    	Grid gridProy = null;
    	ListProyectEmpleBDIn listProBDIn = new ListProyectEmpleBDIn();
		listProBDIn.setValue("cduserid",cduserid);
		ListProyectEmpleBD listProBD = new ListProyectEmpleBD(listProBDIn);
		listProBD.setConnection(con);
		gridProy = listProBD.execSelect();
		
		return gridProy;
    	
    }
    
    private Grid ListProyect() throws Exception{
    	
    	Grid gridProy = null;
    	ListProyectBDIn listProBDIn = new ListProyectBDIn();
		ListProyectBD listProBD = new ListProyectBD(listProBDIn);
		listProBD.setConnection(con);
		gridProy = listProBD.execSelect();
		
		return gridProy;
    	
    }
    
  private Grid DetalleProy( String idproyec) throws Exception{
    	
    	Grid gridProy = null;
    	ListProyectBDIn listProBDIn = new ListProyectBDIn();
		listProBDIn.setValue("idproyec",idproyec);
		ListProyectBD listProBD = new ListProyectBD(listProBDIn);
		listProBD.setConnection(con);
		gridProy = listProBD.execSelect();
		
		return gridProy;
    	
    }
    
    
    private Grid DetalleUsu( String cduserid) throws Exception{
    	
    	Grid gridEmpl = null;
    	ListEmpleadosBDIn listEmpBDIn = new ListEmpleadosBDIn();
		listEmpBDIn.setValue("cduserid",cduserid);
		ListEmpleadosBD listEmpBD = new ListEmpleadosBD(listEmpBDIn);
		listEmpBD.setConnection(con);
		gridEmpl = listEmpBD.execSelect();
		
		return gridEmpl;
    	
    }
       
}
	