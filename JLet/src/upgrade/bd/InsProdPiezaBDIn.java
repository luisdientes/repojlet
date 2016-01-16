package upgrade.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class InsProdPiezaBDIn extends ObjectBD {

    public InsProdPiezaBDIn()
    {
        super();
    }

    public InsProdPiezaBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("imeipiez");
    	addVariable("imeiprod");
    	addVariable("cdestado");
    }
}
