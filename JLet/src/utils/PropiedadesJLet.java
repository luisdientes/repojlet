package utils;

import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Properties;

public class PropiedadesJLet{
    
    private static PropiedadesJLet propertiesInstance = new PropiedadesJLet();
    private Properties p;

    private PropiedadesJLet(){
        
    	String fileName = System.getProperty("jlet.properties");
    	
        try{
        	
        	if ((fileName == null) || (!fileName.equals(""))){
        		System.out.println(" ATENCION la System.Propertie 'jlet.properties' no esta definida");
        	}
        	
        	FileInputStream f = null;
        	
        	try {
        		f = new FileInputStream(fileName);
        	} catch (Exception e) {
        		f = new FileInputStream("E:/DATOS/ProyectosJAVA_EE/home_trabajo/JLet_home/cfg/local.jlet.properties");
        		System.out.println(" ATENCION Properties recuperado por ruta absoluta en: E:\\DATOS\\ProyectosJAVA_EE\\home_trabajo\\JLet_home\\cfg\\local.jlet.properties");
        	}

        	p = new Properties();
	        p.load(f);
	        
	        Properties props = System.getProperties();
	        Enumeration<?> e = p.propertyNames();

	        while (e.hasMoreElements()) {
	          String key = (String) e.nextElement();
	          System.out.println(key + " -- " + p.getProperty(key));
	        }
	        
	        f.close();
        } catch (Exception e){
        	System.out.println(" ERROR Inicializando jlet.properties");
        	e.printStackTrace();
        }
    }

    public static PropiedadesJLet getInstance(){
       
    	return PropiedadesJLet.propertiesInstance;
    }

    public String getProperty(String propiedad){
	   
    	String value = ""; 
    			
    	if (!p.containsKey(propiedad)) {
    		System.out.println(" ATENCION !!! ERROR al recuperar un valor en el properties. Nombre:"+ propiedad);
    	} 
    	
    	value = p.getProperty(propiedad);
    		
    	return value;

    } 

}