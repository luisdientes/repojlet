package facturacion.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListCodigoFacturaBDIn extends ObjectBD {

    public ListCodigoFacturaBDIn()
    {
        super();
    }

    public ListCodigoFacturaBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idemisor");
    	addVariable("aniofact");
    	addVariable("tipofact");
    }
}
