package factura.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListFacturasBDIn extends ObjectBD {

    public ListFacturasBDIn()
    {
        super();
    }

    public ListFacturasBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idemisor");
    	addVariable("idfactur");
    	addVariable("tpclient");
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
    	addVariable("mcpagado");
    	addVariable("orderbyx");
    	addVariable("conitbis");
    	addVariable("intpfact");
    	
    }
}
