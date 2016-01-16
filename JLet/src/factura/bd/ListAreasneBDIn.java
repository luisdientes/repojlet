package factura.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListAreasneBDIn extends ObjectBD {

    public ListAreasneBDIn()
    {
        super();
    }

    public ListAreasneBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idemisor");
    }
}
