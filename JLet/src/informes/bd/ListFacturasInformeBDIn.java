package informes.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListFacturasInformeBDIn extends ObjectBD {

    public ListFacturasInformeBDIn()
    {
        super();
    }

    public ListFacturasInformeBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	
    	addVariable("idemisor");
    	addVariable("aniofact");
    	addVariable("fhdesdex");
    	addVariable("fhhastax");
    	addVariable("orderbyx");
    	
    }
}
