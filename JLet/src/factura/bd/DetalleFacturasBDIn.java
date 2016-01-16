package factura.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class DetalleFacturasBDIn extends ObjectBD {

    public DetalleFacturasBDIn()
    {
        super();
    }

    public DetalleFacturasBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idfactur");
  
    }
}
