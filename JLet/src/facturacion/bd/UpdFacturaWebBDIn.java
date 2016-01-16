package facturacion.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdFacturaWebBDIn extends ObjectBD {

    public UpdFacturaWebBDIn()
    {
        super();
    }

    public UpdFacturaWebBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
  
    	addVariable("idorderx"); 
    	addVariable("filecrea");

    }
}
