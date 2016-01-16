/*
 * IOobject.java
 *
 * Created on 19 de febrero de 2002, 11:38
 */

package arquitectura.objects;

import java.text.SimpleDateFormat;
import java.util.Date;



public abstract class ObjectBD extends ObjectIO{
    
    protected static String dateFormat = "dd/MM/yyyy";

    public ObjectBD(){
        super();        
        try{
            addVariable("BD_PAGESIZE");
            addVariable("BD_PAGENUM");
            defineVars();
        } catch(Exception e){            
        }
    }
    
    
    public ObjectBD(ObjectIO input){
        try{
            addVariable("BD_PAGESIZE");
            addVariable("BD_PAGENUM");
            defineVars();
           //ATENCIÓN, DESARROLLAR EL INPUT
        } catch(Exception e){            
        }
    }
        
    public boolean confirmCopy(String var, Object value){                
        //Cambiamos el valor solo de lo definido
        return isVarDefined(var);
    }
    
    //Definición para las variables de BD
    public abstract void defineVars() throws Exception;    
    
    public String getStringValue(String var) throws Exception {        
        Object value;
        String strValue = null;
        value = getValue(var);
        if (value!=null){
            if(value instanceof java.util.Date){
                Date date = (Date)value;
                strValue = (new SimpleDateFormat(dateFormat)).format(date);
                
            } else{               
                strValue = value.toString();
            }
        }
        
        return strValue;
    }
    
    public void setDateFormat(String format){
        this.dateFormat = format;
    }
}
