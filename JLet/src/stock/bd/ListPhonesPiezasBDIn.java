package stock.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListPhonesPiezasBDIn extends ObjectBD {

    public ListPhonesPiezasBDIn()
    {
        super();
    }

    public ListPhonesPiezasBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("piezasph");
    
    }
}
