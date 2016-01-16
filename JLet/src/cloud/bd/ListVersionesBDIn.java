package cloud.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListVersionesBDIn extends ObjectBD {

    public ListVersionesBDIn()
    {
        super();
    }

    public ListVersionesBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	
    	addVariable("idinodox");
    }
}
