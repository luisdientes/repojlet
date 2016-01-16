package factura.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class MaxReciboCreditoBDIn extends ObjectBD {

    public MaxReciboCreditoBDIn()
    {
        super();
    }

    public MaxReciboCreditoBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("tpfactur");
    	addVariable("idemisor");
    
    }
}
