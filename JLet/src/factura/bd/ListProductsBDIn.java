package factura.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListProductsBDIn extends ObjectBD {

    public ListProductsBDIn()
    {
        super();
    }

    public ListProductsBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("cduserid");
    }
}
