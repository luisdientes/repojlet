package personal.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListRangoFechasBDIn extends ObjectBD {

    public ListRangoFechasBDIn()
    {
        super();
    }

    public ListRangoFechasBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("cduserid");
    	addVariable("fhdesdex");
    	addVariable("fhhastax");
    }
}
