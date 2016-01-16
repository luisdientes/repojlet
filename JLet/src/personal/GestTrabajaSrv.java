package personal;


import personal.bd.AsignaProyBD;
import personal.bd.AsignaProyBDIn;
import personal.bd.AsignaProyDefaultBD;
import personal.bd.AsignaProyDefaultBDIn;
import personal.bd.ExisteUsuarioBD;
import personal.bd.ExisteUsuarioBDIn;
import personal.bd.ListProyectEmpleBD;
import personal.bd.ListProyectEmpleBDIn;
import personal.bd.UpdEmpleBD;
import personal.bd.UpdEmpleBDIn;
import personal.bd.UpdLoginBD;
import personal.bd.UpdLoginBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;


public class GestTrabajaSrv extends Service {
	
 	//Varibales de entrada
	String txnombre = null;
	String txapelli = null;
	String txempres = null;
	String cdnifxxx = null;
	
	String txmailxx = null;
	String tfnomovi = null;
	String txcoment = null;
	String username = null;
	
	String userpass = null;
	String idusuari = null;
	String tprolxxx = null;
	String proSelec = null;
	String resopera = null;
	
	
    int npantall 	= 0;
    
    //Varibales de salida
    String cderror	= "";
    String txerror  = "";
    
    //Otras Variables
    String tipoOper = null;
    String txmensaj = "";
    String idemisor = "";
    Grid gridProy = null;
    Grid gFechasx = new Grid();
    String cduserid = null;
	StringBuffer cadenaxx = new StringBuffer();
	
	

    public GestTrabajaSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();						 
			input.addVariable("txnombre");				 
			input.addVariable("txapelli");						 
			input.addVariable("txempres");
			input.addVariable("cdnifxxx");
			input.addVariable("txmailxx");
        	input.addVariable("tfnomovi");
        	input.addVariable("txcoment");
        	input.addVariable("username");				 
			input.addVariable("userpass");						 
			input.addVariable("idusuari");
			input.addVariable("tprolxxx");
			input.addVariable("proSelec");
			input.addVariable("tipoOper");
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
			output.addVariable("gridProy");
			output.addVariable("fhfechax");
			output.addVariable("txmensaj");
			output.addVariable("idemisor");
			output.addVariable("resopera");
			
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
        	txnombre = input.getStringValue("txnombre");
        	txapelli = input.getStringValue("txapelli");
        	txempres = input.getStringValue("txempres");
        	cdnifxxx = input.getStringValue("cdnifxxx");
        	txmailxx = input.getStringValue("txmailxx");
        	tfnomovi = input.getStringValue("tfnomovi");
        	txcoment = input.getStringValue("txcoment");
        	username = input.getStringValue("username");
        	userpass = input.getStringValue("userpass");
        	idusuari = input.getStringValue("idusuari");
        	tprolxxx = input.getStringValue("tprolxxx");
        	proSelec = input.getStringValue("proSelec");
        	idemisor = input.getStringValue("idemisor");
        	tipoOper = input.getStringValue("tipoOper");

        	System.out.println("FLAG Tipo dato: - "+tipoOper);

        	
        	// ACTUALIZAR TRABAJADOR
        	if (tipoOper.equals("AT")){
        		
        		if(updTrabajador()){
            		txmensaj = "Trabajador actualizado correctamente";
            		resopera = "OK";
            	}else{
            		txmensaj = "Fallo al actualizar el trabajador";
            		resopera = "KO";
            	}
        	}
        	
        	// INSERTAR TRABAJADOR
        	if (tipoOper.equals("IE")){
        		
        		Grid grUsuar	= null;
        		
        		/*comprobamos que no existe el usuario*/
        		
        		ExisteUsuarioBDIn ListUsuBDIn = new ExisteUsuarioBDIn();
        		ListUsuBDIn.setValue("username", username);
        		ExisteUsuarioBD existeUsuBD = new ExisteUsuarioBD(ListUsuBDIn);
        		existeUsuBD.setConnection(con);
        		grUsuar = existeUsuBD.execSelect(); 
        		
        		if(grUsuar.rowCount()>0){
        			txmensaj = "ERROR AL DAR DE ALTA---- El usuario de acceso ya existe.";
        			resopera = "KO";
        		}else{
        		
        		
	        		if(insTrabajador()){
	        			txmensaj = "Trabajador dado de alta correctamente";
	        			resopera = "OK";
	        			
	        		}else{
	        			txmensaj = "Fallo al dar de alta el trabajador";
	        			resopera = "KO";
	        		}
        		}	
        		//insFechasSel(cduserid,fechasSe,horasSel,proyecSe,output);
        	}
        	
        	
        	output.setValue("txmensaj", txmensaj);
        	output.setValue("resopera", resopera);
        	
          }catch (Exception e1) {
        	  e1.printStackTrace();
          	}              
    }
    
    
    
    public boolean insTrabajador() throws Exception{
    	
    	boolean exito	= false;
    	
    	
    	try{
    		String[] proyectos = proSelec.split(",");
    		
    		
	/*Asignamos el login*/
    		
    		UpdLoginBDIn InsLogin = new UpdLoginBDIn();
    		
    		InsLogin.setValue("username",username);
    		InsLogin.setValue("password",userpass);
    		InsLogin.setValue("txmailxx",txmailxx);
    		InsLogin.setValue("tfnomovi",tfnomovi);
    		UpdLoginBD  InsLogBD = new UpdLoginBD(InsLogin);
    		InsLogBD.setConnection(con);
    		InsLogBD.execInsert();
    		
    		
    		/*sacamos el idlogin*/
    		
    		UpdLoginBDIn MaxLoginBDIn = new UpdLoginBDIn();
    		UpdLoginBD  MaxLoginBD = new UpdLoginBD(MaxLoginBDIn);
    		MaxLoginBD.setConnection(con);
    		
    		Grid MaxLog = MaxLoginBD.execSelect();
    		String idacceso = MaxLog.getStringCell(0, "idusuari");
    		
    		UpdEmpleBDIn InsEmple = new UpdEmpleBDIn();
    		
    		InsEmple.setValue("txnombre",txnombre);
    		InsEmple.setValue("txapelli",txapelli);
    		InsEmple.setValue("txempres",txempres);
    		InsEmple.setValue("cdnifxxx",cdnifxxx);
    		InsEmple.setValue("txmailxx",txmailxx);
    		InsEmple.setValue("tfnomovi",tfnomovi);
    		InsEmple.setValue("txcoment",txcoment);
    		InsEmple.setValue("idacceso",idacceso);
    		InsEmple.setValue("idemisor",idemisor);
    		//InsEmple.setValue("idproyec",idproyec);
    		
    		UpdEmpleBD InsEmpleBD = new UpdEmpleBD(InsEmple);
    		InsEmpleBD.setConnection(con);
    		InsEmpleBD.execInsert();

    		/* Asignamos Proyectos seleccionados para el trabajador*/
    		
    		for (int i=0; i< proyectos.length;i++){
    			AsignaProyBDIn AsigProBDIn = new AsignaProyBDIn();
    			AsigProBDIn.setValue("idproyec", proyectos[i]);
    			AsigProBDIn.setValue("cduserid", idacceso);
    			AsignaProyBD AsigProBD = new AsignaProyBD(AsigProBDIn);
    			AsigProBD.setConnection(con);
    			AsigProBD.execInsert();
    		}
    		
    	
    		
    		/*Asignamos proyectos por defecto*/
    		AsignaProyDefaultBDIn asigProDefBDIn = new AsignaProyDefaultBDIn();
    		asigProDefBDIn.setValue("cduserid",idacceso);
    		AsignaProyDefaultBD asignaProDefBD =  new  AsignaProyDefaultBD(asigProDefBDIn);
    		asignaProDefBD.setConnection(con);
    		asignaProDefBD.execInsert();
    		
    		exito = true;
    		//output.setValue("txmensaj", "Trabajador dado de alta correctamente");
    	}catch(Exception ex){
    		System.out.println("Error insertar trabajador "+ex.getMessage());
    	}
		return exito;
    	
    }
  
    
public boolean updTrabajador() throws Exception{
    	
    	boolean exito= false;
    	
    	try{
    		String[] proyectos = proSelec.split(",");
    		
    		/*Borramos los proyectos asignados anteriormente*/
    		
    		ListProyectEmpleBDIn delProBDIn = new  ListProyectEmpleBDIn();
    		delProBDIn.setValue("cduserid", idusuari);
    		
    		ListProyectEmpleBD delProBD = new  ListProyectEmpleBD(delProBDIn);
    		delProBD.setConnection(con);
    		delProBD.execDelete();
    		
    		UpdEmpleBDIn InsEmple = new UpdEmpleBDIn();
    		
    		InsEmple.setValue("txnombre",txnombre);
    		InsEmple.setValue("txapelli",txapelli);
    		InsEmple.setValue("txempres",txempres);
    		InsEmple.setValue("cdnifxxx",cdnifxxx);
    		InsEmple.setValue("txmailxx",txmailxx);
    		InsEmple.setValue("tfnomovi",tfnomovi);
    		InsEmple.setValue("txcoment",txcoment);
    		InsEmple.setValue("idacceso",idusuari);
    		//InsEmple.setValue("idproyec",idproyec);
    		
    		UpdEmpleBD InsEmpleBD = new UpdEmpleBD(InsEmple);
    		InsEmpleBD.setConnection(con);
    		InsEmpleBD.execUpdate();
  
    		/* Asignamos Proyectos seleccionados para el trabajador*/
    		
    		for (int i=0; i< proyectos.length;i++){
    			AsignaProyBDIn AsigProBDIn = new AsignaProyBDIn();
    			AsigProBDIn.setValue("idproyec", proyectos[i]);
    			AsigProBDIn.setValue("cduserid", idusuari);
    			AsignaProyBD AsigProBD = new AsignaProyBD(AsigProBDIn);
    			AsigProBD.setConnection(con);
    			AsigProBD.execInsert();
    		
    		}
    		
    		/*Asignamos proyectos por defecto*/
    		AsignaProyDefaultBDIn asigProDefBDIn = new AsignaProyDefaultBDIn();
    		asigProDefBDIn.setValue("cduserid",idusuari);
    		AsignaProyDefaultBD asignaProDefBD =  new  AsignaProyDefaultBD(asigProDefBDIn);
    		asignaProDefBD.setConnection(con);
    		asignaProDefBD.execInsert();
    		
    		/*Asignamos el login*/
    		if((username!=null  && !username.equals("")) && (userpass!=null  && !userpass.equals(""))){
    		
    		UpdLoginBDIn InsLogin = new UpdLoginBDIn();
    		
	    		InsLogin.setValue("username",username);
	    		InsLogin.setValue("password",userpass);
	    		InsLogin.setValue("txmailxx",txmailxx);
	    		InsLogin.setValue("tfnomovi",tfnomovi);
	    		InsLogin.setValue("idtrabaj",idusuari);
	    		
	    		UpdLoginBD  InsLogBD = new UpdLoginBD(InsLogin);
				InsLogBD.setConnection(con);
				InsLogBD.execUpdate();
	    	}
    		exito = true;
    		//output.setValue("txmensaj", "Trabajador dado de alta correctamente");
    	}catch(Exception ex){
    		System.out.println("Error insertar trabajador");
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
	