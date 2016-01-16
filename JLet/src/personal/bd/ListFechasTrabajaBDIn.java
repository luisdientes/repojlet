package personal.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListFechasTrabajaBDIn extends ObjectBD {

    public ListFechasTrabajaBDIn()
    {
        super();
    }

    public ListFechasTrabajaBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("cduserid");
    }
}
