package subasta.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListSubasCotiBDIn extends ObjectBD {

    public ListSubasCotiBDIn()
    {
        super();
    }

    public ListSubasCotiBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {

    	addVariable("idcodsub");
    	
    }
}
