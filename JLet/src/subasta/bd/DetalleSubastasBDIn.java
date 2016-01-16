package subasta.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class DetalleSubastasBDIn extends ObjectBD {

    public DetalleSubastasBDIn()
    {
        super();
    }

    public DetalleSubastasBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {

    	addVariable("idcodsub");
    	addVariable("mcactivo");
    }
}
