/*
 * SessionHelper.java
 *
 * Created on 25 de abril de 2002, 21:30
 */

package arquitectura.session;

import java.util.Locale;
import java.util.StringTokenizer;

import arquitectura.objects.ObjectIO;

public class SessionHelper extends ObjectIO {

    /** Creates new SessionHelper */
    public SessionHelper() {
        super();
        try{
            // Valores por defecto           
            addVariable("locale", "es_ES");      
            addVariable("localeObject", new Locale("es", "ES", "EUR"));
            addVariable("formatBundle");
            addVariable("cduserid");
            addVariable("cdrolxxx");
            addVariable("miperro","ten");
        } catch(Exception e){
            
        }                        
    }

    public void setValue(String var, Object value) throws Exception {
        if("locale".equals(var)){            
            String country = null;
            String language = null;
            String variant = null;
            StringTokenizer st = new StringTokenizer((String)value, "_");
            int c = st.countTokens();
            if(c > 1){
                language = st.nextToken();
                country = st.nextToken();
                if(c > 2){
                    variant = st.nextToken();
                }
            }
            Locale locale;
            if(language==null || country==null){
                locale = Locale.getDefault();
            } else{
                if(variant==null){
                    locale = new Locale(language, country);
                } else{
                    locale = new Locale(language, country, variant);
                } 
            }
            setValue("localeObject", locale);
        }
        super.setValue(var, value);
    }
    
}
