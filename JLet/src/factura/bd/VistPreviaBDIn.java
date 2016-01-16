package factura.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class VistPreviaBDIn extends ObjectBD {

    public VistPreviaBDIn()
    {
        super();
    }

    public VistPreviaBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idclient");
    	addVariable("tpclient");
    	addVariable("idemisor");
    }
}
