package factura.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class MaxFacturaBDIn extends ObjectBD {

    public MaxFacturaBDIn()
    {
        super();
    }

    public MaxFacturaBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idemisor");
    }
}
