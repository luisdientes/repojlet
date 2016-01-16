package factura.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class MaxFacturaTipoBDIn extends ObjectBD {

    public MaxFacturaTipoBDIn()
    {
        super();
    }

    public MaxFacturaTipoBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idemisor");
    }
}
