package factura.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class MaxFechaFacturaBDIn extends ObjectBD {

    public MaxFechaFacturaBDIn()
    {
        super();
    }

    public MaxFechaFacturaBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idemisor");
    }
}
