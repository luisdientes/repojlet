package facturacion.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class FacturaWebBDIn extends ObjectBD {

    public FacturaWebBDIn()
    {
        super();
    }

    public FacturaWebBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
  
    	addVariable("idorderx"); 
    	addVariable("filecrea");

    }
}
