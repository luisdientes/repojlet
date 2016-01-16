package facturacion.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdCodigoFacturaCronBDIn extends ObjectBD {

    public UpdCodigoFacturaCronBDIn()
    {
        super();
    }

    public UpdCodigoFacturaCronBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idemisor");
    	addVariable("aniofact");
    	addVariable("tipofact");
    }
}
