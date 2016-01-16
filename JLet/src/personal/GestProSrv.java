package personal;



import personal.bd.ListProyectBD;
import personal.bd.ListProyectBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.ObjectIO;


public class GestProSrv extends Service {
	
 	//Varibales de entrada
    String idproyec = null;
	String txnombre = null;
	String idempres = null;
	String tpproyec = null;
	String numperso = null;
	String txduraci = null;
	String txdescri = null;
	String fhinicio = null;
	String fhfinxxx = null;
	String resopera = null;
	String idemisor = null;

	//ArrayList idproyec = new ArrayList(); 
	String proSelec = null;
	
	
    int npantall 	= 0;
    
    //Varibales de salida
    String cderror	= "";
    String txerror  = "";
    
    //Otras Variables
    String tipoOper = null;
    String txmensaj = "";
    String cduserid = null;
    String tprolxxx = null;
	StringBuffer cadenaxx = new StringBuffer();
	
	

    public GestProSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();	
			
			input.addVariable("idproyec");
			input.addVariable("txnombre");
			input.addVariable("idempres");
			input.addVariable("tpproyec");
			input.addVariable("numperso");
			input.addVariable("txduraci");
			input.addVariable("txdescri");
			input.addVariable("fhinicio");
			input.addVariable("fhfinxxx");
			input.addVariable("idemisor");
			input.addVariable("tipoOper");
						
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
			output.addVariable("txmensaj");
			output.addVariable("resopera");
			output.addVariable("idemisor");
			
			
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
        	idproyec = input.getStringValue("idproyec");
        	txnombre = input.getStringValue("txnombre");
        	idempres = input.getStringValue("idempres");
        	tpproyec = input.getStringValue("tpproyec");
        	numperso = input.getStringValue("numperso");
        	txduraci = input.getStringValue("txduraci");
        	txdescri = input.getStringValue("txdescri");
        	fhinicio = input.getStringValue("fhinicio");
        	fhfinxxx = input.getStringValue("fhfinxxx");
        	idemisor = input.getStringValue("idemisor");

        	
        	tipoOper = input.getStringValue("tipoOper");

        	System.out.println("FLAG Tipo dato: - "+tipoOper);

        	
        	// ACTUALIZAR Proyect
        	if (tipoOper.equals("APR")){
        		
        		if( updatePro()){
        			txmensaj = "Proyecto Actualizado correctamente";
        			resopera = "OK";
    			
        		}
        		else{
    			txmensaj = "Fallo al actualizar el Proyecto";
    			resopera = "KO";
        		}
        	}
        	
        	// INSERTAR Proyecto
        	if (tipoOper.equals("IPR")){
        		
        		if ( insProyecto()){
        			txmensaj = "Proyecto dado de alta correctamente";
        			resopera = "OK";
        			
        		}
        		else{
        			txmensaj = "Fallo al dar de alta el Proyecto";
        			resopera = "KO";
        		}
        		//insFechasSel(cduserid,fechasSe,horasSel,proyecSe,output);
        	}
        	
        	
        	output.setValue("txmensaj", txmensaj);
        	output.setValue("resopera", resopera);
        	
          }catch (Exception e1) {
        	  e1.printStackTrace();
          }              
    }
    
    
    
    public boolean insProyecto() throws Exception{
    	
    	boolean exito= false;
    	
    	try{
    		fhinicio = fechaMySQL(fhinicio);
    		fhfinxxx = fechaMySQL(fhfinxxx);
    		 
    	     ListProyectBDIn InsPro = new ListProyectBDIn();
    	     InsPro.setValue("txnombre",txnombre);
    	     InsPro.setValue("idempres",idempres);
    	     InsPro.setValue("tpproyec",tpproyec);
    	     InsPro.setValue("numperso",numperso);
    	     InsPro.setValue("txduraci",txduraci);
    	     InsPro.setValue("txdescri",txdescri);
    	     InsPro.setValue("fhinicio",fhinicio);
    	     InsPro.setValue("fhfinxxx",fhfinxxx);
    	     InsPro.setValue("idemisor",idemisor);

    	     ListProyectBD InsEmpreBD = new ListProyectBD(InsPro);
    	     InsEmpreBD.setConnection(con);
    	     InsEmpreBD.execInsert();
   
    		exito = true;
    	}catch(Exception ex){
    		System.out.println("Error insertar Proyecto");
    	}
    	
		return exito;
    }
    
    
  public boolean updatePro() throws Exception{
    	
    	boolean exito= false;
    	
    	try{
    		fhinicio = fechaMySQL(fhinicio);
    		fhfinxxx = fechaMySQL(fhfinxxx); 
    	    
    		ListProyectBDIn InsPro = new ListProyectBDIn();
    	    InsPro.setValue("idproyec",idproyec);
    	    InsPro.setValue("txnombre",txnombre);
    	    InsPro.setValue("idempres",idempres);
    	    InsPro.setValue("tpproyec",tpproyec);
    	    InsPro.setValue("numperso",numperso);
    	    InsPro.setValue("txduraci",txduraci);
    	    InsPro.setValue("txdescri",txdescri);
    	    InsPro.setValue("fhinicio",fhinicio);
    	    InsPro.setValue("fhfinxxx",fhfinxxx);

    		ListProyectBD InsEmpreBD = new ListProyectBD(InsPro);
    		InsEmpreBD.setConnection(con);
    		InsEmpreBD.execUpdate();
   
    		exito = true;
    	}catch(Exception ex){
    		System.out.println("Error al modificar el Proyecto");
    	}
		return exito;
    	
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
	