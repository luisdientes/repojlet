package common.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListMarcasEmisoresBDIn extends ObjectBD {

    public ListMarcasEmisoresBDIn()
    {
        super();
    }

    public ListMarcasEmisoresBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idemisor");
    }
}
