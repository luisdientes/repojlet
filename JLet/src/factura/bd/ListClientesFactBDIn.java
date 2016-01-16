package factura.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListClientesFactBDIn extends ObjectBD {

    public ListClientesFactBDIn()
    {
        super();
    }

    public ListClientesFactBDIn(ObjectIO bdata)
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
    }
}
