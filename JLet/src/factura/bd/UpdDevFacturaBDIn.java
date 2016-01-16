package factura.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdDevFacturaBDIn extends ObjectBD {

    public UpdDevFacturaBDIn()
    {
        super();
    }

    public UpdDevFacturaBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idfactur");
    	addVariable("cdestado");
    }
}
