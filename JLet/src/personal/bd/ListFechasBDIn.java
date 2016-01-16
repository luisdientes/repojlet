package personal.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListFechasBDIn extends ObjectBD {

    public ListFechasBDIn()
    {
        super();
    }

    public ListFechasBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("cduserid");
    }
}
