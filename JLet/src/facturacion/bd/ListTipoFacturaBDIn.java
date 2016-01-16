package facturacion.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListTipoFacturaBDIn extends ObjectBD {

    public ListTipoFacturaBDIn()
    {
        super();
    }

    public ListTipoFacturaBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("tipofact");
    	addVariable("idemisor");
    	addVariable("codespec");
    }
}
