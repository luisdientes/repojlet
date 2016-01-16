package facturacion.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListCodigoFacturaCronBDIn extends ObjectBD {

    public ListCodigoFacturaCronBDIn()
    {
        super();
    }

    public ListCodigoFacturaCronBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idemisor");
    	addVariable("aniofact");
    	addVariable("tipofact");
    }
}
