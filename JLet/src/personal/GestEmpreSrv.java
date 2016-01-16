package personal;



import personal.bd.ListEmpreBD;
import personal.bd.ListEmpreBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.ObjectIO;


public class GestEmpreSrv extends Service {
	
 	//Varibales de entrada
	String txrazons = null;
	String txdirecc = null;
	String nifcifxx = null;
	String txwebxxx = null;
	String txmailxx = null;
	String txcontac = null;
	String idempres = null;
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
	
	

    public GestEmpreSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();	
			
			input.addVariable("txrazons");				 
			input.addVariable("txdirecc");						 
			input.addVariable("nifcifxx");
			input.addVariable("txwebxxx");
			input.addVariable("txmailxx");
        	input.addVariable("txcontac");
        	input.addVariable("idempres");
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
        	txrazons = input.getStringValue("txrazons");
        	txdirecc = input.getStringValue("txdirecc");
        	nifcifxx = input.getStringValue("nifcifxx");
        	txwebxxx = input.getStringValue("txwebxxx");
        	txmailxx = input.getStringValue("txmailxx");
        	txcontac = input.getStringValue("txcontac");
        	idempres = input.getStringValue("idempres");
        	idemisor = input.getStringValue("idemisor");
        	

        	
        	tipoOper = input.getStringValue("tipoOper");

        	System.out.println("FLAG Tipo dato: - "+tipoOper);

        	
        	// ACTUALIZAR Empresa
        	if (tipoOper.equals("AEM")){
        		
        		if(updEmpres()){
        			txmensaj = "Empresa actualizada correctamente";
        			resopera = "OK";
        			
        		}
        		else{
        			txmensaj = "Fallo al actualizar empresa";
        			resopera = "KO";
        		}
        		
        		
        	}
        	
        	// INSERTAR Empresa
        	if (tipoOper.equals("IEM")){
        		
        		if(insEmpresa()){
        			txmensaj = "Empresa dado de alta correctamente";
        			resopera = "OK";
        		}
        		else{
        			txmensaj = "Fallo al dar de alta empresa";
        			resopera = "KO";
        		}
        	}
        	
        	
        	output.setValue("txmensaj", txmensaj);
        	output.setValue("resopera", resopera);
        	
          }catch (Exception e1) {
        	  e1.printStackTrace();
          	}              
    }
    
    
    
    public boolean insEmpresa() throws Exception{
    	
    	boolean exito= false;
    	
    	try{
    	     ListEmpreBDIn InsEmpre = new ListEmpreBDIn();
    		
    		 InsEmpre.setValue("txrazons",txrazons);
    		 InsEmpre.setValue("txdirecc",txdirecc);
    		 InsEmpre.setValue("nifcifxx",nifcifxx);
    		 InsEmpre.setValue("txwebxxx",txwebxxx);
    		 InsEmpre.setValue("txmailxx",txmailxx);
    		 InsEmpre.setValue("txcontac",txcontac);
    		 InsEmpre.setValue("idempres",idempres);
    		 InsEmpre.setValue("idemisor",idemisor);
    		 

    		 ListEmpreBD InsEmpreBD = new ListEmpreBD(InsEmpre);
    		 InsEmpreBD.setConnection(con);
    		 InsEmpreBD.execInsert();
   
    		exito = true;
    	}catch(Exception ex){
    		System.out.println("Error insertar empresa");
    	}
		return exito;
    	
    }
    
    
  public boolean updEmpres() throws Exception{
    	
    	boolean exito= false;
    	
    	try{
    	     ListEmpreBDIn InsEmpre = new ListEmpreBDIn();
    		
    		 InsEmpre.setValue("txrazons",txrazons);
    		 InsEmpre.setValue("txdirecc",txdirecc);
    		 InsEmpre.setValue("nifcifxx",nifcifxx);
    		 InsEmpre.setValue("txwebxxx",txwebxxx);
    		 InsEmpre.setValue("txmailxx",txmailxx);
    		 InsEmpre.setValue("txcontac",txcontac);
    		 InsEmpre.setValue("idempres",idempres);

    		 ListEmpreBD InsEmpreBD = new ListEmpreBD(InsEmpre);
    		 InsEmpreBD.setConnection(con);
    		 InsEmpreBD.execUpdate();
   
    		exito = true;
    	}catch(Exception ex){
    		System.out.println("Error actualizar empresa");
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
	