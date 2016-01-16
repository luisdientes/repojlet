package factura.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdPagaFacturaBDIn extends ObjectBD {

    public UpdPagaFacturaBDIn()
    {
        super();
    }

    public UpdPagaFacturaBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idfactur");
    	addVariable("mcpagado");
    	addVariable("totalpag");
    	
    }
}
