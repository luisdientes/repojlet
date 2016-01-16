package factura.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListTipoFacBDIn extends ObjectBD {

    public ListTipoFacBDIn()
    {
        super();
    }

    public ListTipoFacBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("cdemisor");
    	addVariable("modofact");
    	addVariable("tipofact");
    	
    	
    }
}
