package facturacion.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdCodigoFacturaBDIn extends ObjectBD {

    public UpdCodigoFacturaBDIn()
    {
        super();
    }

    public UpdCodigoFacturaBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idemisor");
    	addVariable("aniofact");
    	addVariable("tipofact");
    	addVariable("restacod");
    	
    }
}
