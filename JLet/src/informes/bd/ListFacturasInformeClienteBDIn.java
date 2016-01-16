package informes.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListFacturasInformeClienteBDIn extends ObjectBD {

    public ListFacturasInformeClienteBDIn()
    {
        super();
    }

    public ListFacturasInformeClienteBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	
    	addVariable("idemisor");
    	addVariable("aniofact");
    	addVariable("fhdesdex");
    	addVariable("fhhastax");
    	addVariable("mcpagado");
    	addVariable("cdintern");
    	addVariable("mcagrcli");
    	addVariable("orderbyx");
    	
    }
}
