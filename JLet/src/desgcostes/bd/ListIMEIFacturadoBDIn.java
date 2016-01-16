package desgcostes.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListIMEIFacturadoBDIn extends ObjectBD {

    public ListIMEIFacturadoBDIn()
    {
        super();
    }

    public ListIMEIFacturadoBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	
    	addVariable("idemisor");
    	addVariable("fhdesdex");
    	addVariable("fhhastax");
 
    }
}
