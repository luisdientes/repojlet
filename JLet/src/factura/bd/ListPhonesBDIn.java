package factura.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListPhonesBDIn extends ObjectBD {

    public ListPhonesBDIn()
    {
        super();
    }

    public ListPhonesBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("cduserid");
    }
}
