package factura.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class MaxReciboEntradaBDIn extends ObjectBD {

    public MaxReciboEntradaBDIn()
    {
        super();
    }

    public MaxReciboEntradaBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idemisor");
    
    }
}
