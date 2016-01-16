package comercio.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListEmpreEnvioBDIn extends ObjectBD {

    public ListEmpreEnvioBDIn()
    {
        super();
    }

    public ListEmpreEnvioBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idemisor");
    }
}
