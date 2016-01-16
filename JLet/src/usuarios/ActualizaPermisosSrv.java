package usuarios;


import gestion.administracion.bd.ListPermisosBD;
import gestion.administracion.bd.ListPermisosBDIn;

import java.util.ArrayList;
import java.util.HashSet;

import usuarios.bd.ListPermisosRolBD;
import usuarios.bd.ListPermisosRolBDIn;
import usuarios.bd.ListRolBD;
import usuarios.bd.ListRolBDIn;
import usuarios.bd.ListUsuariosBD;
import usuarios.bd.ListUsuariosBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;


public class ActualizaPermisosSrv extends Service {

	
	
	public ActualizaPermisosSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("idusuari");
			input.addVariable("cdrolxxx");
			input.addVariable("tipoperm");
			input.addVariable("listperm");
			
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
			output.addVariable("cdrolxxx");
			output.addVariable("gdPermis");
			output.addVariable("gdPantal");
			output.addVariable("gdUsuari");
			output.addVariable("gdRolxxx");
			
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
    	String listperm = "";
    	String tipoperm = "";
    	String txmensaj = "";
    
 

        //Varibales de salida
 
    	Grid gdUsuari = null;
    	Grid gdPermis = null;
    	Grid gdPantal = null;
    	Grid gdRolxxx = null;
    	Grid gdLisRol = null;
        
        //Otras Variables
        HashSet<String> hsPermis = new HashSet<String>();
        
        try {
        	idusuari = input.getStringValue("idusuari");
        	cdrolxxx = input.getStringValue("cdrolxxx");
        	listperm = input.getStringValue("listperm");
        	tipoperm = input.getStringValue("tipoperm");
        	String [] listPerm = null;
   	     int cantperm = 0;
   	     int totalinse = 0;
   	     listPerm = listperm.split(";");
        	try{
        		
        		
        		//Recupero todos los permisos de este rol en un grid
        		if(tipoperm!= null && tipoperm.equals("PANTALLA")){
        		
	        		ListPermisosRolBDIn listRolAntBDIn = new ListPermisosRolBDIn();
	        		listRolAntBDIn.setValue("cdrolxxx",cdrolxxx);
	        		listRolAntBDIn.setValue("tipoperm",tipoperm);
	        		ListPermisosRolBD listRolAntBD = new ListPermisosRolBD(listRolAntBDIn);
	        		listRolAntBD.setConnection(con);
	        		gdLisRol = listRolAntBD.execSelect();
        		}else{
        			ListPermisosBDIn permEmiBDIn = new ListPermisosBDIn();
		    		permEmiBDIn.setValue("cdrolxxx", cdrolxxx);
		    		permEmiBDIn.setValue("tipoperm", tipoperm);
		    		ListPermisosBD permEmiBD = new ListPermisosBD(permEmiBDIn);
		    		permEmiBD.setConnection(con);
		    		gdLisRol = permEmiBD.execSelect();
        			
        		}
				
				
				
				for (int i = 0; i < gdLisRol.rowCount(); i++) {
					hsPermis.add(gdLisRol.getStringCell(i,"valorper"));	
				}
				
				
        		for (int i = 0; i < listPerm.length; i++) {
        			//COMO SI HAGO UN SELECT 
        			
        			if(hsPermis.contains(listPerm[i])) {  //ENCUENTRO VALOR EN EL SELECT??????
        				hsPermis.remove(listPerm[i]);
        			} else {
        				//HAGO INSERT    
						
						ListPermisosRolBDIn updateRolBDIn = new ListPermisosRolBDIn();
						updateRolBDIn.setValue("cdrolxxx",cdrolxxx);
						updateRolBDIn.setValue("valorper",listPerm[i]);
						updateRolBDIn.setValue("tipoperm",tipoperm);
						
						ListPermisosRolBD updateRolBD = new ListPermisosRolBD(updateRolBDIn);
						updateRolBD.setConnection(con);
						int exito = updateRolBD.execInsert();
						
						if (exito == 1){
							System.out.println("Exito al dar de alta el permiso  --->"+listPerm[i]);
						}else{
							System.out.println("Fallo al dar de alta permisos");
						}
            		
        			}
        		}
        		
        		
        		ArrayList<String> permDele = new ArrayList<String>(hsPermis);
        		
        		for (int i = 0; i < permDele.size(); i++) {
        			//HAGO DELETE
        			ListPermisosRolBDIn deleteRolBDIn = new ListPermisosRolBDIn();
            		deleteRolBDIn.setValue("cdrolxxx",cdrolxxx);
            		deleteRolBDIn.setValue("tipoperm",tipoperm);
            		deleteRolBDIn.setValue("valorper",permDele.get(i).toString() );
    				ListPermisosRolBD deleteRolBD = new ListPermisosRolBD(deleteRolBDIn);
    				deleteRolBD.setConnection(con);
    				deleteRolBD.execDelete();
        		}
        		
        		ListUsuariosBDIn listUsuarioBDIn = new ListUsuariosBDIn();
        		listUsuarioBDIn.setValue("idusuari",idusuari);
        		//listUsuarioBDIn.setValue("cdrolxxx",cdrolxxx);
				ListUsuariosBD listUsuarioBD = new ListUsuariosBD(listUsuarioBDIn);
				listUsuarioBD.setConnection(con);
				
				ListRolBDIn listadoRolBDIn = new ListRolBDIn();
				ListRolBD listadoRolBD = new ListRolBD(listadoRolBDIn);
				listadoRolBD.setConnection(con);
				gdRolxxx = listadoRolBD.execSelect();
    			
				gdUsuari = listUsuarioBD.execSelect();
				txmensaj = "Exito al modificar permisos";	
				
        	   }catch(Exception e1){
        		   e1.printStackTrace();
        		   txmensaj = "Fallo al modificar permisos";
        	   }
        	
        	
        	output.setValue("idusuari", idusuari);
        	output.setValue("cdrolxxx", cdrolxxx);
        	output.setValue("gdPermis", gdPermis);
        	output.setValue("gdPantal", gdPantal);
        	output.setValue("gdUsuari", gdUsuari);
        	output.setValue("gdRolxxx", gdRolxxx);
        	
        	output.setValue("txmensaj", txmensaj);
        	
        
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
}
	