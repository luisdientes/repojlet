package personal;


import personal.bd.ListEmpleadosBD;
import personal.bd.ListEmpleadosBDIn;
import personal.bd.ListEmpreBD;
import personal.bd.ListEmpreBDIn;
import personal.bd.ListFechasBD;
import personal.bd.ListFechasBDIn;
import personal.bd.ListProyectBD;
import personal.bd.ListProyectBDIn;
import personal.bd.ListProyectEmpleBD;
import personal.bd.ListProyectEmpleBDIn;
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
			input.addVariable("idempres");
			input.addVariable("idemisor");
						
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
			output.addVariable("gridEmpr");
			output.addVariable("gridProT");
			output.addVariable("idempres");
			output.addVariable("tprolxxx");
			output.addVariable("idemisor");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
	
	 String idemisor = "";
     String tipodato  = "";
     String fhpulsad  = "";
     String fhdesdex  = "";
     String fhhastax  = "";
     String txmesxxx  = "";
     String txanioxx  = "";
     String idproyec	=  "";
     String idempres	 = "";
     Grid gridFech = null;
     Grid gridProy = null;
     Grid gAccesos = null;
     Grid gridEmpl = null;
     Grid gridProT = null;
     Grid gridEmpr = null;
     String tprolxxx = "";
     String cduserid = "";
     String idtrabaj = "";
    
    
    public void process(ObjectIO input, ObjectIO output){
        
        int npantall 	 = 0;
        
        //Varibales de salida
        String cderror   = "";
        String txerror   = "";
        
        //Otras Variables
       
        
        try{        	
        	
        	//RECUPERO SESION y DATOS QUE ME INTERESAN
        	cduserid = input.getStringValue("cduserid");
        
        	if(cduserid!=null && !cduserid.equals("")){
        		cduserid = input.getStringValue("cduserid");
        	}else{
        		cduserid = (String) sesion.getAttribute("idusuari");
        	}
        	
        	
        	tprolxxx = (String) sesion.getAttribute("tprolxxx");
        	
        	//RECUPERO LOS VALORES DEL INPUT
        	tipodato = input.getStringValue("tipoda");
        	idtrabaj = input.getStringValue("cduserid");
        	idproyec = input.getStringValue("idproyec");
        	fhpulsad = input.getStringValue("fhpulsad");
        	fhdesdex = input.getStringValue("fhdesdex");
        	fhhastax = input.getStringValue("fhhastax");
        	idempres = input.getStringValue("idempres");
        	idemisor = input.getStringValue("idemisor");
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
        	
        	if(tipodato.equals("AP")){
            	
        		gridEmpr = ListEmpre(idemisor);
        	}
        	
        	
        	/*Alta empleado*/
        	if(tipodato.equals("IE")){
        		
        		gridProy = ListProyect();
        	}
        	/*Detalle Empleados*/
        	if(tipodato.equals("DET")){
        		
        		gridProT = ListProyect(idtrabaj);
        		gridEmpl = DetalleUsu(idtrabaj);
        		gridProy = ListProyect();
        		tprolxxx = getRol(idtrabaj);
        	}
        	
        	
        	/*Listado de empleados*/
        	if(tipodato.equals("LE")){
        		
        		ListEmpleadosBDIn listEmpBDIn = new ListEmpleadosBDIn();
        		listEmpBDIn.setValue("cduserid",idtrabaj);
        		ListEmpleadosBD listEmpBD = new ListEmpleadosBD(listEmpBDIn);
        		listEmpBD.setConnection(con);
        		gridEmpl = listEmpBD.execSelect();
        	}
        	
        	/*Listado empresas*/
        	if(tipodato.equals("LEM")){
        		gridEmpr = ListEmpre(idemisor);
        	}
        	
        	/*Detalle empresas*/
        	
        	if(tipodato.equals("DEM")){
        		gridEmpr = DetalleEmpre(idempres);
        	}
        	
        	
        	/*Detalle Empleados*/
        	if(tipodato.equals("DET")){
        		
        		gridProT = ListProyect(idtrabaj);
        		gridEmpl = DetalleUsu(idtrabaj);
        		gridProy = ListProyect();
        	}
        	
        	
        	/*Detalle Proyecto*/
        	if(tipodato.equals("DP")){

        		gridProy = DetalleProy(idproyec);
        		gridEmpr = ListEmpre(idemisor);
        	}
        	
        	
        /*	output.setValue("txmesxxx", txmesxxx);
        	output.setValue("txanioxx", txanioxx);*/
        	output.setValue("fhpulsad", fhpulsad);
        	output.setValue("cduserid", cduserid);
        	output.setValue("tprolxxx", tprolxxx);
        	output.setValue("idemisor", idemisor);
        	output.setGrid("gridFech", gridFech);
        	output.setGrid("gridProy", gridProy);
        	output.setGrid("gridEmpl", gridEmpl);
        	output.setGrid("gridProT", gridProT);
        	output.setGrid("gridEmpr", gridEmpr);
        	
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
    	listProBDIn.setValue("idemisor", idemisor);
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
  
  private Grid ListEmpre(String idemisor) throws Exception{
	  	
		Grid gridEmpre = null;
		ListEmpreBDIn listEmpBDIn = new ListEmpreBDIn();
		listEmpBDIn.setValue("idemisor", idemisor);
		ListEmpreBD listEmpBD = new ListEmpreBD(listEmpBDIn);
		listEmpBD.setConnection(con);
		gridEmpre = listEmpBD.execSelect();
		
		return gridEmpre;
  }
  
  
  private Grid DetalleEmpre(String idempres) throws Exception{
	  	
		Grid gridEmpre = null;
		ListEmpreBDIn listEmpBDIn = new ListEmpreBDIn();
		listEmpBDIn.setValue("idempres", idempres);
		ListEmpreBD listEmpBD = new ListEmpreBD(listEmpBDIn);
		listEmpBD.setConnection(con);
		gridEmpre = listEmpBD.execSelect();
		
		return gridEmpre;
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
    
 private String getRol(String cduserid) throws Exception{
    	
    /*	Grid gridRol = null;
    	String tpRol = null;
    	UpdLoginBDIn getRolBDIn = new UpdLoginBDIn();
    	getRolBDIn.setValue("idtrabaj",cduserid);
    	UpdLoginBD getRolBD = new UpdLoginBD(getRolBDIn);
    	getRolBD.setConnection(con);
		gridRol = getRolBD.execSelect();
		
		tpRol = gridRol.getStringCell(0, "tprolxxx");*/
		return "n";
    	
    }
       
}
	