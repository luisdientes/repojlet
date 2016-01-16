package common.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListEmisoresBDIn extends ObjectBD {

    public ListEmisoresBDIn()
    {
        super();
    }

    public ListEmisoresBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("tpclient");
    }
}
