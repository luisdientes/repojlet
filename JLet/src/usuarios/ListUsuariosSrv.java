package usuarios;


import recibos.bd.UpdRecibosBD;
import recibos.bd.UpdRecibosBDIn;
import usuarios.bd.ListRolBD;
import usuarios.bd.ListRolBDIn;
import usuarios.bd.ListUsuariosBD;
import usuarios.bd.ListUsuariosBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import common.ExcelCreator;


public class ListUsuariosSrv extends Service {

	
	
	public ListUsuariosSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			input.addVariable("idusuari");
			input.addVariable("cdrolxxx");
			input.addVariable("tpaccion");
			
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
			output.addVariable("gdUsuari");
			output.addVariable("gdRolxxx");
			output.addVariable("idusuari");
			output.addVariable("txmensaj");
		
			
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
    	String tpaccion = "";
    	String txmensaj = "";
    
 

        //Varibales de salida
 
    	Grid gdUsuari = null;
    	Grid gdRolxxx = null;
        String maxgimna = "";
        
        //Otras Variables
        
        
        try {
        	idusuari = input.getStringValue("idusuari");
        	cdrolxxx = input.getStringValue("cdrolxxx");
        	tpaccion = input.getStringValue("tpaccion");
   
        
        	try{
        			if(tpaccion != null && tpaccion.equals("UPD")){
        				ListUsuariosBDIn updUsuarioBDIn = new ListUsuariosBDIn();
        				updUsuarioBDIn.setValue("idusuari",idusuari);
        				updUsuarioBDIn.setValue("cdrolxxx",cdrolxxx);
        				ListUsuariosBD updUsuarioBD = new ListUsuariosBD(updUsuarioBDIn);
        				updUsuarioBD.setConnection(con);
        				int i = updUsuarioBD.execUpdate();
        				
        				if( i >0){
        					txmensaj = "Rol actualizado correctamente";
        				}else{
        					txmensaj = "Fallo al actualizar rol";
        				}
        				
        			}
        		
        		
        		ListUsuariosBDIn listUsuarioBDIn = new ListUsuariosBDIn();
        		listUsuarioBDIn.setValue("idusuari",idusuari);
        		listUsuarioBDIn.setValue("cdrolxxx",cdrolxxx);
				ListUsuariosBD listUsuarioBD = new ListUsuariosBD(listUsuarioBDIn);
				listUsuarioBD.setConnection(con);
    			
				gdUsuari = listUsuarioBD.execSelect();
				
				ListRolBDIn listadoRolBDIn = new ListRolBDIn();
				ListRolBD listadoRolBD = new ListRolBD(listadoRolBDIn);
				listadoRolBD.setConnection(con);
				gdRolxxx = listadoRolBD.execSelect();
        		 
        	   }catch(Exception e1){
        		   e1.printStackTrace();
        	   }
        	

        	output.setValue("gdUsuari", gdUsuari);
        	output.setValue("gdRolxxx", gdRolxxx);
        	output.setValue("txmensaj", txmensaj);
        	output.setValue("idusuari", idusuari);
        
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
}
	