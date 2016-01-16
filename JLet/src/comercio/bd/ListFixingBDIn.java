package comercio.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListFixingBDIn extends ObjectBD {

    public ListFixingBDIn()
    {
        super();
    }

    public ListFixingBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("cddivisa");
    	addVariable("diviscam");
    }
}
