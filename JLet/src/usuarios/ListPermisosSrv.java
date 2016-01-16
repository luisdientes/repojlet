package usuarios;


import gestion.administracion.bd.ListPermisosBD;
import gestion.administracion.bd.ListPermisosBDIn;
import common.bd.ListEmisoresBD;
import common.bd.ListEmisoresBDIn;
import usuarios.bd.ListPantallasBD;
import usuarios.bd.ListPantallasBDIn;
import usuarios.bd.ListPermisosRolBD;
import usuarios.bd.ListPermisosRolBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;


public class ListPermisosSrv extends Service {

	
	
	public ListPermisosSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("idusuari");
			input.addVariable("cdrolxxx");
			input.addVariable("tipoperm");
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
			output.addVariable("idusuari");
			output.addVariable("txmensaj");
			output.addVariable("tipoperm");
			
			output.addVariable("cdrolxxx");
			output.addVariable("gdPermis");
			output.addVariable("gdPantal");
			output.addVariable("gdEmisor");
			
		
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	
    	String idusuari = ""; 
    	String cdrolxxx = ""; 
    	String tpclient = ""; 
    	String tipoperm = "";
    
 

        //Varibales de salida
 
    	Grid gdUsuari = null;
    	Grid gdPermis = null;
    	Grid gdPantal = null;
    	Grid gdEmisor = null;
        String maxgimna = "";
        
        //Otras Variables
        
        
        try {
        	idusuari = input.getStringValue("idusuari");
        	cdrolxxx = input.getStringValue("cdrolxxx");
        	tipoperm = input.getStringValue("tipoperm");
        
        	try{
				
				
				if(tipoperm != null && tipoperm.equals("PANTALLA")){
					
					ListPermisosRolBDIn listadoRolBDIn = new ListPermisosRolBDIn();
					listadoRolBDIn.setValue("cdrolxxx",cdrolxxx);
					listadoRolBDIn.setValue("tipoperm",tipoperm);
					
					ListPermisosRolBD listadoRolBD = new ListPermisosRolBD(listadoRolBDIn);
					listadoRolBD.setConnection(con);
					gdPermis = listadoRolBD.execSelect();
				
					ListPantallasBDIn listadoPantallasBDIn = new ListPantallasBDIn();
					ListPantallasBD listadoPantallasBD = new ListPantallasBD(listadoPantallasBDIn);
					listadoPantallasBD.setConnection(con);
					gdPantal = listadoPantallasBD.execSelect();
				}else{
					// muestro emisores;
					ListEmisoresBDIn emisorBDIn = new ListEmisoresBDIn();
					ListEmisoresBD emisorBD = new ListEmisoresBD(emisorBDIn);
					emisorBD.setConnection(con);
					gdEmisor = emisorBD.execSelect();
					
					
					ListPermisosBDIn permEmiBDIn = new ListPermisosBDIn();
		    		permEmiBDIn.setValue("cdrolxxx", cdrolxxx);
		    		permEmiBDIn.setValue("tipoperm", tipoperm);
		    		ListPermisosBD permEmiBD = new ListPermisosBD(permEmiBDIn);
		    		permEmiBD.setConnection(con);
		    		gdPermis = permEmiBD.execSelect();
				}
				
        	   }catch(Exception e1){
        		   e1.printStackTrace();
        	   }
        	
        	
        	output.setValue("idusuari", idusuari);
        	output.setValue("tipoperm", tipoperm);
        	
        	output.setValue("cdrolxxx", cdrolxxx);
        	output.setValue("gdPermis", gdPermis);
        	output.setValue("gdPantal", gdPantal);
        	output.setValue("gdEmisor", gdEmisor);
        	output.setValue("txmensaj", "OK");
        	
        
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
}
	