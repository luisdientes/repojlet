package facturacion.bd; 

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListClienteWebBDIn extends ObjectBD {

    public ListClienteWebBDIn()
    {
        super();
    }

    public ListClienteWebBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idshopxx");
    	addVariable("idcustom");
    }
}
