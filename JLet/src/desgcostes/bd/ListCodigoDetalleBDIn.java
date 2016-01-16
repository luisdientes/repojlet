package desgcostes.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListCodigoDetalleBDIn extends ObjectBD {

    public ListCodigoDetalleBDIn()
    {
        super();
    }

    public ListCodigoDetalleBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	
    	addVariable("mcactivo");

    }
}
 