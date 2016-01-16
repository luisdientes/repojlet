package factura.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListAnioFactBDIn extends ObjectBD {

    public ListAnioFactBDIn()
    {
        super();
    }

    public ListAnioFactBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idemisor");
    	addVariable("idclient");
    	addVariable("aniofact");
    	addVariable("fhfechax");
    	addVariable("tipofact");
    	addVariable("cdestado");
    	addVariable("isalbara");
    	addVariable("fhdesdex");
    	addVariable("fhhastax");
    	addVariable("predesde");
    	addVariable("prehasta");
    	addVariable("orderbyx");
    }
}
